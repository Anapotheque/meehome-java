package fr.generali.gfb.ws.sinistre.converter.auto;

import java.util.Date;

import org.apache.commons.lang.NotImplementedException;

import fr.generali.gfb.ws.sinistre.converter.AbstractDeclarationSinistreConverter;
import fr.generali.gfb.ws.sinistre.converter.IDeclarationSinistreConverter;
import fr.generali.gfb.ws.sinistre.dto.auto.DommageInput;
import fr.generali.gfb.ws.sinistre.dto.auto.IDeclarationSinistreAutoInput;
import fr.generali.gfb.ws.sinistre.persistence.domain.auto.DommagesAuto;
import fr.generali.gfb.ws.sinistre.persistence.domain.auto.SinistreAuto;
import fr.generali.gfb.ws.sinistre.persistence.domain.constants.TypeSinistreEnum;

abstract public class AbstractDeclarationSinistreAutoConverter extends AbstractDeclarationSinistreConverter {

    protected static void convertCommonSinistre(final IDeclarationSinistreAutoInput input,
                    final SinistreAuto sinistreOutput) {
        sinistreOutput.setCirconstances(input.getCirconstances());
        sinistreOutput.setImmatriculation(input.getImmatriculation());
        if (input.getHeureDebut() != null) {
            sinistreOutput.setHeureDebut(Integer.valueOf(input.getHeureDebut()));
        }
        if (input.getMinuteDebut() != null) {
            sinistreOutput.setMinuteDebut(Integer.valueOf(input.getMinuteDebut()));
        }
        if (input.getHeureFin() != null) {
            sinistreOutput.setHeureFin(Integer.valueOf(input.getHeureFin()));
        }
        if (input.getMinuteFin() != null) {
            sinistreOutput.setMinuteFin(Integer.valueOf(input.getMinuteFin()));
        }
        sinistreOutput.setDateDeclaration(new Date());
        sinistreOutput.setDateSinistre(input.getDateSinistre());
    }

    protected static void convertCommonDommage(final DommageInput dommageInput, final DommagesAuto dommageOutput) {
        dommageOutput.setCoordonnesGarage(dommageInput.getCoordonnesGarage());
        dommageOutput.setDepotGarage(dommageInput.getDepotGarage());
        dommageOutput.setDescription(dommageInput.getDescription());
        dommageOutput.setLieuVehicule(dommageInput.getLieuVehicule());
    }

    public static IDeclarationSinistreConverter getInstance(TypeSinistreEnum typeSinistre) {
        if (typeSinistre == TypeSinistreEnum.AUTO_ACCIDENT) {
            return DeclarationSinistreAccidentConverter.getInstance();
        } else if (typeSinistre == TypeSinistreEnum.AUTO_INCENDIE) {
            return DeclarationSinistreIncendieConverter.getInstance();
        } else if (typeSinistre == TypeSinistreEnum.AUTO_VANDALISME) {
            return DeclarationSinistreVandalismeConverter.getInstance();
        } else if (typeSinistre == TypeSinistreEnum.AUTO_VOL) {
            return DeclarationSinistreVolConverter.getInstance();
        } else {
            throw new NotImplementedException("Le type de sinistre " + typeSinistre + " n'est pas géré");
        }
    }

}
