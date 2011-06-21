package fr.generali.gfb.ws.sinistre;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import fr.generali.espaceclient.proxy.generic.domain.InformationIntermediaire;
import fr.generali.espaceclient.proxy.generic.exception.GenericAccessException;
import fr.generali.gfb.ws.sinistre.converter.mrh.AbstractDeclarationSinistreMRHConverter;
import fr.generali.gfb.ws.sinistre.dto.AssureInput;
import fr.generali.gfb.ws.sinistre.dto.IDeclarationSinistreInput;
import fr.generali.gfb.ws.sinistre.dto.OrigineDeclaration;
import fr.generali.gfb.ws.sinistre.dto.OrigineDeclaration.TypeOrigine;
import fr.generali.gfb.ws.sinistre.dto.mrh.DeclarationSinistreBrisGlaceInput;
import fr.generali.gfb.ws.sinistre.dto.mrh.DeclarationSinistreDegatsDesEauxInput;
import fr.generali.gfb.ws.sinistre.dto.mrh.DeclarationSinistreDommageElectriqueInput;
import fr.generali.gfb.ws.sinistre.dto.mrh.DeclarationSinistreTempeteGreleInput;
import fr.generali.gfb.ws.sinistre.dto.mrh.DeclarationSinistreVolInput;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;
import fr.generali.gfb.ws.sinistre.persistence.domain.constants.TypeDeclaEnum;
import fr.generali.gfb.ws.sinistre.persistence.domain.constants.TypeSinistreEnum;
import fr.generali.gfb.ws.sinistre.util.EspaceClientEnvironnementEnum;
import fr.generali.socle.exceptions.TechnicalException;

@Service
@Qualifier("defaultDeclarationSinistreMrhService")
public class DefaultDeclarationSinistreMrhService extends AbstractDeclarationSinistreService implements IDeclarationSinistreMrhService {
    private static final String CODE_PORTEFEUILLE_ECOLE = "client.sinistre.code.portefeuille.ecole";

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultDeclarationSinistreMrhService.class);

    @Autowired
    private ISinistreOrigineDeconnecteService sinistreOrigineDeconnecteService;


    public void declarerBrisDeGlace(final DeclarationSinistreBrisGlaceInput input) throws DeclarationSinistreException {
        traiterDeclarationSinistre(input, TypeSinistreEnum.MRH_BRIS_GLACE);
    }

    public void declarerDegatDesEaux(DeclarationSinistreDegatsDesEauxInput input) throws DeclarationSinistreException {
        traiterDeclarationSinistre(input, TypeSinistreEnum.MRH_DEGATS_DES_EAUX);
    }

    public void declarerDommageElectrique(DeclarationSinistreDommageElectriqueInput input)
                    throws DeclarationSinistreException {
        traiterDeclarationSinistre(input, TypeSinistreEnum.MRH_DOMMAGES_ELECTRIQUES);
    }

    public void declarerTempeteGrele(DeclarationSinistreTempeteGreleInput input) throws DeclarationSinistreException {
        traiterDeclarationSinistre(input, TypeSinistreEnum.MRH_TEMPETE_GRELE);
    }

    public void declarerVol(DeclarationSinistreVolInput input) throws DeclarationSinistreException {
        traiterDeclarationSinistre(input, TypeSinistreEnum.MRH_VOL_CAMBRIOLAGE);
    }

    private void traiterDeclarationSinistre(final IDeclarationSinistreInput input, TypeSinistreEnum typeSinistre)
                    throws DeclarationSinistreException {
        verifierInput(input);

        /*
         * Construction de la déclaration de sinistre
         */
        final DeclarationSinistre declarationSinistre = declaratrionSinistre(input, typeSinistre);

        if (OrigineDeclaration.valueOf(input.getOrigine()).typeOrigine == TypeOrigine.CONNECTE) {
            origineConnecte(input, declarationSinistre, typeSinistre);
            
            return;
        }   
        String codeRetour = null;
    	DeclarationSinistreException exception = null;
    	try {
    		sinistreOrigineDeconnecteService.traitementSinistre(declarationSinistre);
    	} catch (DeclarationSinistreException e){
    		codeRetour = "KO";
    		exception = e;
    		LOGGER.error(" traiterDeclarationSinistre :: ",e);
    	}
        
    	//FIXME Il faut faire un refactoring des deux classes MRH et AUTO 
        if(OrigineDeclaration.GPROX.name().equalsIgnoreCase(input.getOrigine())){
       		logAdmin(input, TypeDeclaEnum.MRH, typeSinistre, codeRetour, null);
        }
        
        if(exception != null){
        	throw exception;
        }
    }

    private void origineConnecte(final IDeclarationSinistreInput input,  final DeclarationSinistre declarationSinistre,  TypeSinistreEnum typeSinistre)
                    throws DeclarationSinistreException {
        /*
         * Récupération infos portefeuille
         */
        final InformationIntermediaire infosIntermediaire = infosIntermediaire(input, declarationSinistre);

        /*
         * Récupération du mail apporteur
         */
        final String mailApporteur = mailApporteur(input, infosIntermediaire);

        /*
         * Récupération du mail Trieste
         */
        final String mailTrieste = input.getMailTrieste();

        /*
         * Récupération client internet?
         */
        final Boolean isClientInternet = isClientInternet(declarationSinistre);

        /*
         * Récupération est courtier ?
         */
        boolean isCourtier = false; 
        if (input.getAssure().getIsCourtier() != null && input.getAssure().getIsCourtier().equals("true")){	
        	isCourtier = true;
        }
                
        sendDeclarationSinistre(input, declarationSinistre, TypeDeclaEnum.MRH, typeSinistre, mailApporteur, isClientInternet, infosIntermediaire, mailTrieste, isCourtier);
    }

    /**
     * Si l'origine est un espace connecté (type espace client), certains
     * paramètres sont obligatoires et leurs valeurs sont trustables. Dans le
     * cas déconnecté, aucunne vérification n'est faite.
     * 
     * @param input
     */
    static protected void verifierInput(IDeclarationSinistreInput input) {
        if (StringUtils.isEmpty(input.getOrigine())) {
            throw new IllegalArgumentException("L'origine est obligatoire");
        }

        if (input.getAssure() == null) {
            throw new IllegalArgumentException("L'assuré est obligatoire");
        }

        if (TypeOrigine.CONNECTE == OrigineDeclaration.valueOf(input.getOrigine()).typeOrigine) {
            if (input.getAssure() == null) {
                throw new IllegalArgumentException("L'assure est obligatoire");
            }

            verifierAssureInputModeConnecte(input.getAssure());
        }
    }

    protected static void verifierAssureInputModeConnecte(AssureInput assureInput) {
        if (StringUtils.isBlank(assureInput.getCodeCompagnie())) {
            throw new IllegalArgumentException("Le code compagnie est obligatoire");
        }

        if (StringUtils.isBlank(assureInput.getCodePortefeuille())) {
            throw new IllegalArgumentException("Le code portefeuille est obligatoire");
        }

        if (StringUtils.isBlank(assureInput.getIdRceClient())) {
            throw new IllegalArgumentException("L'id RCE est obligatoire");
        }

        if (StringUtils.isBlank(assureInput.getNumeroContrat())) {
            throw new IllegalArgumentException("Le numéro de contrat est obligatoire");
        }
    }

    private String mailApporteur(final IDeclarationSinistreInput input,
                    final InformationIntermediaire infosIntermediaire) throws DeclarationSinistreException {
        try {
            String mail = recupererMailApporteur(infosIntermediaire);

            final EspaceClientEnvironnementEnum env =
                            EspaceClientEnvironnementEnum.findByValue(properties
                                            .getProperty("espaceclient.client.environnement"));
            if (env != null && !env.equals(EspaceClientEnvironnementEnum.PRODUCTION)) {
                mail = properties.getProperty("sinistre.email.destinataire.gestionnaire.developpement");
            }
            return mail;
        } catch (DeclarationSinistreException e) {
            e.setInput(input);
            throw e;
        }
    }

    private InformationIntermediaire infosIntermediaire(final IDeclarationSinistreInput input,
                    DeclarationSinistre declarationSinistre) throws DeclarationSinistreException {
        try {
            return recupererInfosIntermediaire(declarationSinistre);
        } catch (GenericAccessException e) {
            LOGGER.error("Problème lors de l'appel Generic. Input:" + input);
            throw new DeclarationSinistreException(e, input);
        }
    }

    private DeclarationSinistre declaratrionSinistre(IDeclarationSinistreInput input, TypeSinistreEnum typeSinistre) {
        return AbstractDeclarationSinistreMRHConverter.getInstance(typeSinistre).convert(input);
    }

    private Boolean isClientInternet(DeclarationSinistre declarationSinistre) {
        String codePortefeuilleEcole = properties.getProperty(CODE_PORTEFEUILLE_ECOLE);
        return codePortefeuilleEcole.equalsIgnoreCase(declarationSinistre.getCodePortefeuille());
    }

    private String recupererMailApporteur(InformationIntermediaire infosIntermediaire)
                    throws DeclarationSinistreException {
        if (infosIntermediaire == null || infosIntermediaire.getBureau() == null
                        || StringUtils.isBlank(infosIntermediaire.getBureau().getEmail())) {
            throw new DeclarationSinistreException("Récupération du mail de l'agent impossible");
        }
        return infosIntermediaire.getBureau().getEmail();

    }

  

}
