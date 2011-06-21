package fr.generali.gfb.ws.sinistre.dto.auto;

import java.util.Date;

import fr.generali.gfb.ws.sinistre.dto.AssureInput;

public class DeclarationSinistreAccidentIncendieInput extends AbstractDeclarationSinistreAutoInput {

    private String lieu;

    private DommageAccidentIncendieInput dommages;

    /**
     * @param lieu the lieu to set
     */
    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    /**
     * @param dommages the dommages to set
     */
    public void setDommages(DommageAccidentIncendieInput dommages) {
        this.dommages = dommages;
    }

    public String getLieu() {
        return lieu;
    }

    public DommageAccidentIncendieInput getDommages() {
        return dommages;
    }

    public String getLieuVehicule() {
        return lieu;
    }

    public DeclarationSinistreAccidentIncendieInput dommages(DommageAccidentIncendieInput dommages) {
        this.dommages = dommages;
        return this;
    }

    public DeclarationSinistreAccidentIncendieInput lieu(String lieuVehicule) {
        this.lieu = lieuVehicule;
        return this;
    }

    @Override
    public DeclarationSinistreAccidentIncendieInput circonstances(String circonstances) {
        super.circonstances(circonstances);
        return this;
    }

    @Override
    public DeclarationSinistreAccidentIncendieInput heureDebut(String heureDebut) {
        super.heureDebut(heureDebut);
        return this;
    }

    @Override
    public DeclarationSinistreAccidentIncendieInput heureFin(String heureFin) {
        super.heureFin(heureFin);
        return this;
    }

    @Override
    public DeclarationSinistreAccidentIncendieInput immatriculation(String immatriculation) {
        super.immatriculation(immatriculation);
        return this;
    }

    @Override
    public DeclarationSinistreAccidentIncendieInput minuteDebut(String minuteDebut) {
        super.minuteDebut(minuteDebut);
        return this;
    }

    @Override
    public DeclarationSinistreAccidentIncendieInput minuteFin(String minuteFin) {
        super.minuteFin(minuteFin);
        return this;
    }

    @Override
    public DeclarationSinistreAccidentIncendieInput assure(AssureInput assure) {
        super.assure(assure);
        return this;
    }

    @Override
    public DeclarationSinistreAccidentIncendieInput dateSinistre(Date dateSinistre) {
        super.dateSinistre(dateSinistre);
        return this;
    }

    @Override
    public DeclarationSinistreAccidentIncendieInput origine(String origineString) {
        super.origine(origineString);
        return this;
    }

    @Override
    public DeclarationSinistreAccidentIncendieInput mailTrieste(String mailTrieste) {
        this.mailTrieste = mailTrieste;
        return this;
    }
}
