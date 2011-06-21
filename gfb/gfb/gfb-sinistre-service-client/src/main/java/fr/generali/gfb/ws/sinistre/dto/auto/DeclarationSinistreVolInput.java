package fr.generali.gfb.ws.sinistre.dto.auto;

import java.util.Date;

import fr.generali.gfb.ws.sinistre.dto.AssureInput;

//import fr.generali.gfb.ws.sinistre.dto.OrigineDeclaration;

public class DeclarationSinistreVolInput extends AbstractDeclarationSinistreAutoInput {

    /**
     * @param plainte the plainte to set
     */
    public void setPlainte(Boolean plainte) {
        this.plainte = plainte;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @param dommage the dommage to set
     */
    public void setDommage(DommageVolInput dommage) {
        this.dommage = dommage;
    }

    private Boolean plainte;

    private String type;

    private DommageVolInput dommage;

    public DommageVolInput getDommage() {
        return dommage;
    }

    public DeclarationSinistreVolInput dommage(DommageVolInput dommage) {
        this.dommage = dommage;
        return this;
    }

    public Boolean getPlainte() {
        return plainte;
    }

    public DeclarationSinistreVolInput plainte(Boolean plainte) {
        this.plainte = plainte;
        return this;
    }

    public String getType() {
        return type;
    }

    public DeclarationSinistreVolInput type(String type) {
        this.type = type;
        return this;
    }

    @Override
    public DeclarationSinistreVolInput circonstances(String circonstances) {
        super.circonstances(circonstances);
        return this;
    }

    @Override
    public DeclarationSinistreVolInput heureDebut(String heureDebut) {
        super.heureDebut(heureDebut);
        return this;
    }

    @Override
    public DeclarationSinistreVolInput heureFin(String heureFin) {
        super.heureFin(heureFin);
        return this;
    }

    @Override
    public DeclarationSinistreVolInput immatriculation(String immatriculation) {
        super.immatriculation(immatriculation);
        return this;
    }

    @Override
    public DeclarationSinistreVolInput minuteDebut(String minuteDebut) {
        super.minuteDebut(minuteDebut);
        return this;
    }

    @Override
    public DeclarationSinistreVolInput minuteFin(String minuteFin) {
        super.minuteFin(minuteFin);
        return this;
    }

    @Override
    public DeclarationSinistreVolInput assure(AssureInput assure) {
        super.assure(assure);
        return this;
    }

    @Override
    public DeclarationSinistreVolInput dateSinistre(Date dateSinistre) {
        super.dateSinistre(dateSinistre);
        return this;
    }

    @Override
    public DeclarationSinistreVolInput origine(String origineString) {
        super.origine(origineString);
        return this;
    }

    @Override
    public DeclarationSinistreVolInput mailTrieste(String mailTrieste) {
        this.mailTrieste = mailTrieste;
        return this;
    }

}
