package fr.generali.gfb.ws.sinistre;

import fr.generali.gfb.ws.sinistre.dto.AssureInput;

public final class MockInputs {
    private MockInputs() {
        super();
    }

    public static final AssureInput INPUT_ASSURE =
                    new AssureInput("numeroContrat", "numeroClient", "idRce", "codeCompagnie", "codePortefeuille",
                                    "nom", "prenom", "adresse", "codePostal", "ville", "email", "telephoneMobile",
                                    "telephoneDomicile", "telephoneBureau","true");
}
