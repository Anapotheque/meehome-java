package fr.generali.gfb.ws.sinistre.converter.mrh;

import fr.generali.gfb.ws.sinistre.dto.mrh.BienInput;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.BienConcerne;

public final class BienConcerneHelper {

    private BienConcerneHelper() {
        super();
    }

    protected static BienConcerne convert(BienInput bienInput) {
        if (bienInput != null) {
            BienConcerne bienOutput = new BienConcerne();
            bienOutput.setIsResidencePrincipale(bienInput.getIsResidencePrincipale());
            bienOutput.setAdresse(bienInput.getAdresse());
            bienOutput.setCodePostal(bienInput.getCodePostal());
            bienOutput.setVille(bienInput.getVille());
            bienOutput.setQualite(bienInput.getQualite());
            return bienOutput;
        } else {
            return null;
        }
    }

}
