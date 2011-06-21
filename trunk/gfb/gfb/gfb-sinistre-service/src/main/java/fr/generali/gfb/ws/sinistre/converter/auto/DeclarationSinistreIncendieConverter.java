package fr.generali.gfb.ws.sinistre.converter.auto;

import fr.generali.gfb.ws.sinistre.dto.IDeclarationSinistreInput;
import fr.generali.gfb.ws.sinistre.dto.auto.DeclarationSinistreAccidentIncendieInput;
import fr.generali.gfb.ws.sinistre.persistence.domain.auto.accident.SinistreAutoAccidentIncendie;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.Sinistre;
import fr.generali.gfb.ws.sinistre.persistence.domain.constants.TypeSinistreEnum;

public final class DeclarationSinistreIncendieConverter extends AbstractDeclarationSinistreAccidentIncendieConverter {
    private static DeclarationSinistreIncendieConverter instance;

    private DeclarationSinistreIncendieConverter() {
    }

    public static DeclarationSinistreIncendieConverter getInstance() {
        if (instance == null) {
            instance = new DeclarationSinistreIncendieConverter();
        }
        return instance;
    }

    @Override
    protected Sinistre convertSinistre(IDeclarationSinistreInput input) {
        DeclarationSinistreAccidentIncendieInput vInput = (DeclarationSinistreAccidentIncendieInput ) input;
        SinistreAutoAccidentIncendie sinistreOutput = convertSinistreAccidentIndcendie(vInput);
        sinistreOutput.setTypeSinistre(TypeSinistreEnum.AUTO_INCENDIE);
        return sinistreOutput;
    }

}
