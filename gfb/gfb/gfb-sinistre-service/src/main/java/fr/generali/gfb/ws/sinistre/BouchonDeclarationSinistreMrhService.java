package fr.generali.gfb.ws.sinistre;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import fr.generali.gfb.ws.sinistre.dto.AssureInput;
import fr.generali.gfb.ws.sinistre.dto.IDeclarationSinistreInput;
import fr.generali.gfb.ws.sinistre.dto.OrigineDeclaration.TypeOrigine;
import fr.generali.gfb.ws.sinistre.dto.mrh.DeclarationSinistreBrisGlaceInput;
import fr.generali.gfb.ws.sinistre.dto.mrh.DeclarationSinistreDegatsDesEauxInput;
import fr.generali.gfb.ws.sinistre.dto.mrh.DeclarationSinistreDommageElectriqueInput;
import fr.generali.gfb.ws.sinistre.dto.mrh.DeclarationSinistreTempeteGreleInput;
import fr.generali.gfb.ws.sinistre.dto.mrh.DeclarationSinistreVolInput;
import fr.generali.gfb.ws.sinistre.persistence.domain.constants.TypeSinistreEnum;

@Service
public class BouchonDeclarationSinistreMrhService implements IDeclarationSinistreMrhService {

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

    }

    /**
     * Si l'origine est un espace connecté (type espace client), certains
     * paramètres sont obligatoires et leurs valeurs sont trustables. Dans le
     * cas déconnecté, aucunne vérification n'est faite.
     * 
     * @param input
     */
    static protected void verifierInput(IDeclarationSinistreInput input) {
        /*if (input.getOrigine() == null) {
            throw new IllegalArgumentException("L'origine est obligatoire");
        }*/

        if (input.getAssure() == null) {
            throw new IllegalArgumentException("L'assuré est obligatoire");
        }
/*
        if (TypeOrigine.CONNECTE == input.getOrigine().typeOrigine) {
            if (input.getAssure() == null) {
                throw new IllegalArgumentException("L'assure est obligatoire");
            }

            verifierAssureInputModeConnecte(input.getAssure());
        }*/
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

}
