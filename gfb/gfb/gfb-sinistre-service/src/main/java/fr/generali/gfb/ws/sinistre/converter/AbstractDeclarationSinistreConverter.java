package fr.generali.gfb.ws.sinistre.converter;

import fr.generali.gfb.ws.sinistre.dto.AbstractDeclarationSinistreInput;
import fr.generali.gfb.ws.sinistre.dto.AssureInput;
import fr.generali.gfb.ws.sinistre.dto.IDeclarationSinistreInput;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.Assure;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.Sinistre;

/**
 * Mappe tous les champs communs à tous les types de sinistre
 */
abstract public class AbstractDeclarationSinistreConverter implements IDeclarationSinistreConverter {
    /**
     * Ce constructeur ne fait rien
     */
    protected AbstractDeclarationSinistreConverter() {
        super();
    }

    public DeclarationSinistre convert(final IDeclarationSinistreInput input) {
        AbstractDeclarationSinistreInput abstractInput = (AbstractDeclarationSinistreInput ) input;
        final DeclarationSinistre ret = new DeclarationSinistre();
        /*
         * Champs communs à toutes les déclarations
         */
        final AssureInput assure = abstractInput.getAssure();
        ret.setNumeroContrat(assure.getNumeroContrat());
        ret.setNumClient(assure.getNumeroClient());
        ret.setIdClientRce(assure.getIdRceClient());
        ret.setCodeCompagnie(assure.getCodeCompagnie());
        ret.setCodePortefeuille(assure.getCodePortefeuille());
        ret.setAssure(extraireAssure(abstractInput.getAssure()));
        ret.setSinistre(convertSinistre(input));
        return ret;
    }

    protected static Assure extraireAssure(final AssureInput assure) {
        final Assure ret = new Assure();

        ret.setNom(assure.getNom());
        ret.setPrenom(assure.getPrenom());
        ret.setAdresse(assure.getAdresse());
        ret.setCodePostal(assure.getCodePostal());
        ret.setVille(assure.getVille());
        ret.setEmail(assure.getEmail());
        ret.setTelephoneMobile(assure.getTelephoneMobile());
        ret.setTelephoneDomicile(assure.getTelephoneDomicile());
        ret.setTelephoneBureau(assure.getTelephoneBureau());
        return ret;
    }

    /**
     * Cette méthode est à surcharger par les classes filles. Elle consiste à
     * mapper les champs spécifiques.
     * 
     * @param input
     * @return
     */
    abstract protected Sinistre convertSinistre(IDeclarationSinistreInput input);
}
