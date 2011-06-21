package fr.generali.gfb.ws.sinistre.converter.auto;

import fr.generali.gfb.ws.sinistre.dto.IDeclarationSinistreInput;
import fr.generali.gfb.ws.sinistre.dto.auto.DeclarationSinistreVandalismeInput;
import fr.generali.gfb.ws.sinistre.dto.auto.DommageInput;
import fr.generali.gfb.ws.sinistre.persistence.domain.auto.DommagesAuto;
import fr.generali.gfb.ws.sinistre.persistence.domain.auto.vandalisme.SinistreAutoVandalisme;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.Sinistre;
import fr.generali.gfb.ws.sinistre.persistence.domain.constants.TypeSinistreEnum;

public final class DeclarationSinistreVandalismeConverter extends AbstractDeclarationSinistreAutoConverter {
    private static DeclarationSinistreVandalismeConverter instance;

    private DeclarationSinistreVandalismeConverter() {
    }

    public static DeclarationSinistreVandalismeConverter getInstance() {
        if (instance == null) {
            instance = new DeclarationSinistreVandalismeConverter();
        }
        return instance;
    }

    @Override
    protected Sinistre convertSinistre(IDeclarationSinistreInput input) {
        DeclarationSinistreVandalismeInput vInput = (DeclarationSinistreVandalismeInput ) input;

        SinistreAutoVandalisme sinistreOutput = new SinistreAutoVandalisme();
        sinistreOutput.setTypeSinistre(TypeSinistreEnum.AUTO_VANDALISME);

        // Partie commune à tous les dommages auto
        convertCommonSinistre(vInput, sinistreOutput);

        // partie spécifique vandalisme
        sinistreOutput.setPlainte(vInput.getPlainte());

        DommageInput dommageInput = vInput.getDommage();
        DommagesAuto dommageOutput = new DommagesAuto();
        if (dommageInput != null) {
            convertCommonDommage(dommageInput, dommageOutput);
            sinistreOutput.setDommage(dommageOutput);
        } else {
            sinistreOutput.setDommage(null);
        }
        return sinistreOutput;
    }

}
