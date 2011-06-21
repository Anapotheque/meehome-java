package fr.generali.gfb.ws.sinistre.dto.auto;

import fr.generali.gfb.ws.sinistre.dto.AbstractDeclarationSinistreInput;

//import fr.generali.gfb.ws.sinistre.dto.OrigineDeclaration;

public abstract class AbstractDeclarationSinistreAutoInput extends AbstractDeclarationSinistreInput implements
                IDeclarationSinistreAutoInput {
    private String heureDebut;

    /**
     * @param heureDebut the heureDebut to set
     */
    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    /**
     * @param minuteDebut the minuteDebut to set
     */
    public void setMinuteDebut(String minuteDebut) {
        this.minuteDebut = minuteDebut;
    }

    /**
     * @param heureFin the heureFin to set
     */
    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    /**
     * @param minuteFin the minuteFin to set
     */
    public void setMinuteFin(String minuteFin) {
        this.minuteFin = minuteFin;
    }

    /**
     * @param circonstances the circonstances to set
     */
    public void setCirconstances(String circonstances) {
        this.circonstances = circonstances;
    }

    /**
     * @param immatriculation the immatriculation to set
     */
    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    private String minuteDebut;

    private String heureFin;

    private String minuteFin;

    private String circonstances;

    private String immatriculation;

    public String getHeureDebut() {
        return heureDebut;
    }

    public String getMinuteDebut() {
        return minuteDebut;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public String getMinuteFin() {
        return minuteFin;
    }

    public String getCirconstances() {
        return circonstances;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public AbstractDeclarationSinistreAutoInput heureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
        return this;
    }

    public AbstractDeclarationSinistreAutoInput minuteDebut(String minuteDebut) {
        this.minuteDebut = minuteDebut;
        return this;
    }

    public AbstractDeclarationSinistreAutoInput heureFin(String heureFin) {
        this.heureFin = heureFin;
        return this;
    }

    public AbstractDeclarationSinistreAutoInput minuteFin(String minuteFin) {
        this.minuteFin = minuteFin;
        return this;
    }

    public AbstractDeclarationSinistreAutoInput circonstances(String circonstances) {
        this.circonstances = circonstances;
        return this;
    }

    public AbstractDeclarationSinistreAutoInput immatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
        return this;
    }

    /*
     * public void setOrigine(OrigineDeclaration origine) { origine(origine); }
     */

}
