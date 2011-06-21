package fr.generali.gfb.ws.sinistre.converter.auto;

import fr.generali.gfb.ws.sinistre.dto.IDeclarationSinistreInput;
import fr.generali.gfb.ws.sinistre.dto.auto.DeclarationSinistreVolInput;
import fr.generali.gfb.ws.sinistre.dto.auto.DommageVolInput;
import fr.generali.gfb.ws.sinistre.persistence.domain.auto.vol.DommagesAutoVol;
import fr.generali.gfb.ws.sinistre.persistence.domain.auto.vol.SinistreAutoVol;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.Sinistre;
import fr.generali.gfb.ws.sinistre.persistence.domain.constants.TypeSinistreEnum;

public final class DeclarationSinistreVolConverter extends AbstractDeclarationSinistreAutoConverter {
    private static DeclarationSinistreVolConverter instance;

    private DeclarationSinistreVolConverter() {
        super();
    }

    public static DeclarationSinistreVolConverter getInstance() {
        if (instance == null) {
            instance = new DeclarationSinistreVolConverter();
        }
        return instance;
    }

    @Override
    protected Sinistre convertSinistre(final IDeclarationSinistreInput input) {
        final DeclarationSinistreVolInput vInput = (DeclarationSinistreVolInput ) input;

        final SinistreAutoVol sinistreOutput = new SinistreAutoVol();
        sinistreOutput.setTypeSinistre(TypeSinistreEnum.AUTO_VOL);

        // Partie commune à tous les dommages auto
        convertCommonSinistre(vInput, sinistreOutput);

        // partie spécifique vol
        sinistreOutput.setPlainte(vInput.getPlainte());
        sinistreOutput.setType(vInput.getType());

        final DommageVolInput dommageInput = vInput.getDommage();
        final DommagesAutoVol dommageOutput = new DommagesAutoVol();
        if (dommageInput != null) {
            convertCommonDommage(dommageInput, dommageOutput);
            dommageOutput.setEffetsPersonnels(dommageInput.getEffetsPersonnels());
            sinistreOutput.setDommage(dommageOutput);
        } else {
            sinistreOutput.setDommage(null);
        }
        return sinistreOutput;
    }
}
