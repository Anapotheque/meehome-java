package fr.generali.gfb.ws.sinistre.converter.auto;

import fr.generali.gfb.ws.sinistre.dto.IDeclarationSinistreInput;
import fr.generali.gfb.ws.sinistre.dto.auto.DeclarationSinistreAccidentIncendieInput;
import fr.generali.gfb.ws.sinistre.dto.auto.DommageAccidentIncendieInput;
import fr.generali.gfb.ws.sinistre.persistence.domain.auto.accident.DommagesAutoIncendieAccident;
import fr.generali.gfb.ws.sinistre.persistence.domain.auto.accident.SinistreAutoAccidentIncendie;

abstract public class AbstractDeclarationSinistreAccidentIncendieConverter extends
                AbstractDeclarationSinistreAutoConverter {

    protected static SinistreAutoAccidentIncendie convertSinistreAccidentIndcendie(IDeclarationSinistreInput input) {
        DeclarationSinistreAccidentIncendieInput iaInput = (DeclarationSinistreAccidentIncendieInput ) input;

        SinistreAutoAccidentIncendie sinistreOutput = new SinistreAutoAccidentIncendie();

        convertCommonSinistre(iaInput, sinistreOutput);

        sinistreOutput.setLieu(iaInput.getLieu());

        DommageAccidentIncendieInput dommageInput = iaInput.getDommages();
        if (dommageInput != null) {
            DommagesAutoIncendieAccident dommageOutput = new DommagesAutoIncendieAccident();
            convertCommonDommage(dommageInput, dommageOutput);

            dommageOutput.setAutresDommagesMateriels(dommageInput.getAutresDommagesMateriels());
            dommageOutput.setAutresPersonnes(dommageInput.getAutresPersonnes());
            dommageOutput.setDescriptionDommagesMateriels(dommageInput.getDescriptionDommagesMateriels());
            dommageOutput.setDommagesCorporel(dommageInput.getDommagesCorporel());
            sinistreOutput.setDommage(dommageOutput);
        } else {
            sinistreOutput.setDommage(null);
        }
        return sinistreOutput;
    }

}
