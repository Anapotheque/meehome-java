package fr.generali.gfb.ws.sinistre.converter.auto;

import fr.generali.gfb.ws.sinistre.dto.IDeclarationSinistreInput;
import fr.generali.gfb.ws.sinistre.dto.auto.DeclarationSinistreAccidentIncendieInput;
import fr.generali.gfb.ws.sinistre.persistence.domain.auto.accident.SinistreAutoAccidentIncendie;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.Sinistre;
import fr.generali.gfb.ws.sinistre.persistence.domain.constants.TypeSinistreEnum;

public final class DeclarationSinistreAccidentConverter extends AbstractDeclarationSinistreAccidentIncendieConverter {
    private static DeclarationSinistreAccidentConverter instance;

    private DeclarationSinistreAccidentConverter() {
    }

    public static DeclarationSinistreAccidentConverter getInstance() {
        if (instance == null) {
            instance = new DeclarationSinistreAccidentConverter();
        }
        return instance;
    }

    @Override
    protected Sinistre convertSinistre(IDeclarationSinistreInput input) {
        DeclarationSinistreAccidentIncendieInput vInput = (DeclarationSinistreAccidentIncendieInput ) input;
        SinistreAutoAccidentIncendie sinistreOutput = convertSinistreAccidentIndcendie(vInput);
        sinistreOutput.setTypeSinistre(TypeSinistreEnum.AUTO_ACCIDENT);
        return sinistreOutput;
    }

}
