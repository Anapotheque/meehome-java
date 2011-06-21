package fr.generali.gfb.ws.sinistre.converter.mrh;

import org.apache.commons.lang.NotImplementedException;

import fr.generali.gfb.ws.sinistre.converter.AbstractDeclarationSinistreConverter;
import fr.generali.gfb.ws.sinistre.converter.IDeclarationSinistreConverter;
import fr.generali.gfb.ws.sinistre.persistence.domain.constants.TypeSinistreEnum;

/**
 * Mappe tous les champs communs à tous les types de sinistre
 */
abstract public class AbstractDeclarationSinistreMRHConverter extends AbstractDeclarationSinistreConverter implements
                IDeclarationSinistreConverter {
    protected AbstractDeclarationSinistreMRHConverter() {

    }

    public static IDeclarationSinistreConverter getInstance(TypeSinistreEnum typeSinistre) {
        if (typeSinistre == TypeSinistreEnum.MRH_BRIS_GLACE) {
            return DeclarationSinistreBrisDeGlaceConverter.getInstance();
        } else if (typeSinistre == TypeSinistreEnum.MRH_DEGATS_DES_EAUX) {
            return DeclarationSinistreDegatsDesEauxConverter.getInstance();
        } else if (typeSinistre == TypeSinistreEnum.MRH_TEMPETE_GRELE) {
            return DeclarationSinistreTempeteGreleConverter.getInstance();
        } else if (typeSinistre == TypeSinistreEnum.MRH_DOMMAGES_ELECTRIQUES) {
            return DeclarationSinistreDommageElectriqueConverter.getInstance();
        } else if (typeSinistre == TypeSinistreEnum.MRH_VOL_CAMBRIOLAGE) {
            return DeclarationSinistreVolConverter.getInstance();
        } else {
            throw new NotImplementedException("Le type de sinistre " + typeSinistre + " n'est pas géré");
        }
    }

}
