package fr.generali.gfb.ws.sinistre.dto.auto;

import java.util.Date;

public interface IDeclarationSinistreAutoInput {
    String getCirconstances();

    String getImmatriculation();

    String getHeureDebut();

    String getMinuteDebut();

    String getHeureFin();

    String getMinuteFin();

    Date getDateSinistre();
}
