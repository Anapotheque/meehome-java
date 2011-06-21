package fr.generali.gfb.ws.sinistre;

import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import fr.generali.espaceclient.proxy.generic.domain.InformationIntermediaire;
import fr.generali.espaceclient.proxy.generic.exception.GenericAccessException;
import fr.generali.espaceclient.proxy.generic.service.IGenericFct01Service;
import fr.generali.gfb.ws.sinistre.converter.auto.AbstractDeclarationSinistreAutoConverter;
import fr.generali.gfb.ws.sinistre.dto.AssureInput;
import fr.generali.gfb.ws.sinistre.dto.IDeclarationSinistreInput;
import fr.generali.gfb.ws.sinistre.dto.OrigineDeclaration;
import fr.generali.gfb.ws.sinistre.dto.OrigineDeclaration.TypeOrigine;
import fr.generali.gfb.ws.sinistre.dto.auto.DeclarationSinistreAccidentIncendieInput;
import fr.generali.gfb.ws.sinistre.dto.auto.DeclarationSinistreVandalismeInput;
import fr.generali.gfb.ws.sinistre.dto.auto.DeclarationSinistreVolInput;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;
import fr.generali.gfb.ws.sinistre.persistence.domain.constants.TypeDeclaEnum;
import fr.generali.gfb.ws.sinistre.persistence.domain.constants.TypeSinistreEnum;
import fr.generali.gfb.ws.sinistre.util.EspaceClientEnvironnementEnum;

@Service
@Qualifier("defaultDeclarationSinistreAutoService")
public class DefaultDeclarationSinistreAutoService extends AbstractDeclarationSinistreService implements IDeclarationSinistreAutoService {
    private static final String CODE_PORTEFEUILLE_ECOLE = "client.sinistre.code.portefeuille.ecole";

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultDeclarationSinistreAutoService.class);

    public void declarerAccident(DeclarationSinistreAccidentIncendieInput input) throws DeclarationSinistreException {
        traiterDeclarationSinistre(input, TypeSinistreEnum.AUTO_ACCIDENT);
    }

    public void declarerIncendie(DeclarationSinistreAccidentIncendieInput input) throws DeclarationSinistreException {
        traiterDeclarationSinistre(input, TypeSinistreEnum.AUTO_INCENDIE);
    }

    public void declarerVandalisme(DeclarationSinistreVandalismeInput input) throws DeclarationSinistreException {
        traiterDeclarationSinistre(input, TypeSinistreEnum.AUTO_VANDALISME);
    }

    public void declarerVol(DeclarationSinistreVolInput input) throws DeclarationSinistreException {
        traiterDeclarationSinistre(input, TypeSinistreEnum.AUTO_VOL);
    }

    private void traiterDeclarationSinistre(final IDeclarationSinistreInput input, TypeSinistreEnum typeSinistre)
                    throws DeclarationSinistreException {
        verifierInput(input);

        /*
         * Construction de la déclaration de sinistre
         */
        final DeclarationSinistre declarationSinistre = declaratrionSinistre(input, typeSinistre);

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
        
//        /*
//         * Traiter le sinistre
//         */
//        sinistreOrigineConnecteService.traitementSinistreAuto(declarationSinistre, mailApporteur, isClientInternet,
//                        infosIntermediaire, mailTrieste, isCourtier);
        
        sendDeclarationSinistre(input,  declarationSinistre,   TypeDeclaEnum.AUTO, typeSinistre,  mailApporteur, isClientInternet, infosIntermediaire, mailTrieste, isCourtier);
        
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

        if (TypeOrigine.DECONNECTE == OrigineDeclaration.valueOf(input.getOrigine()).typeOrigine) {
            throw new IllegalArgumentException(
                            "Les déclarations de sinistre auto ne sont pas disponibles en environnement DECONNECTE");
        }

        verifierAssureInput(input.getAssure());

    }

    protected static void verifierAssureInput(AssureInput assureInput) {
        if (assureInput == null) {
            throw new IllegalArgumentException("L'assuré est obligatoire");
        }

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
            LOGGER.error("Problème lors de l'appel Generic. Input:" + input, e);
            throw new DeclarationSinistreException(e, input);
        }
    }

    private DeclarationSinistre declaratrionSinistre(IDeclarationSinistreInput input, TypeSinistreEnum typeSinistre) {
        return AbstractDeclarationSinistreAutoConverter.getInstance(typeSinistre).convert(input);
    }

    private Boolean isClientInternet(DeclarationSinistre declarationSinistre) {
        String codePortefeuilleEcole = properties.getProperty(CODE_PORTEFEUILLE_ECOLE);
        return codePortefeuilleEcole.equalsIgnoreCase(declarationSinistre.getCodePortefeuille());
    }

    private String recupererMailApporteur(InformationIntermediaire infosIntermediaire)
                    throws DeclarationSinistreException {
        if (infosIntermediaire == null || infosIntermediaire.getBureau() == null
                        || StringUtils.isBlank(infosIntermediaire.getBureau().getEmail())) {
        	LOGGER.error("Récupération du mail de l'agent impossible");
        	throw new DeclarationSinistreException("Récupération du mail de l'agent impossible");
        }
        return infosIntermediaire.getBureau().getEmail();

    }

   

}
