package fr.generali.gfb.ws.sinistre.dto.auto;

import java.util.Date;

import fr.generali.gfb.ws.sinistre.dto.AssureInput;

//import fr.generali.gfb.ws.sinistre.dto.OrigineDeclaration;

public class DeclarationSinistreVandalismeInput extends AbstractDeclarationSinistreAutoInput {

    /**
     * @param plainte the plainte to set
     */
    public void setPlainte(Boolean plainte) {
        this.plainte = plainte;
    }

    /**
     * @param dommage the dommage to set
     */
    public void setDommage(DommageInput dommage) {
        this.dommage = dommage;
    }

    private Boolean plainte;

    private DommageInput dommage;

    public Boolean getPlainte() {
        return plainte;
    }

    public DommageInput getDommage() {
        return dommage;
    }

    public DeclarationSinistreVandalismeInput plainte(Boolean plainte) {
        this.plainte = plainte;
        return this;
    }

    public DeclarationSinistreVandalismeInput dommage(DommageInput dommage) {
        this.dommage = dommage;
        return this;
    }

    @Override
    public DeclarationSinistreVandalismeInput circonstances(String circonstances) {
        return (DeclarationSinistreVandalismeInput ) super.circonstances(circonstances);
    }

    @Override
    public DeclarationSinistreVandalismeInput heureDebut(String heureDebut) {
        return (DeclarationSinistreVandalismeInput ) super.heureDebut(heureDebut);
    }

    @Override
    public DeclarationSinistreVandalismeInput heureFin(String heureFin) {
        return (DeclarationSinistreVandalismeInput ) super.heureFin(heureFin);
    }

    @Override
    public DeclarationSinistreVandalismeInput immatriculation(String immatriculation) {
        return (DeclarationSinistreVandalismeInput ) super.immatriculation(immatriculation);
    }

    @Override
    public DeclarationSinistreVandalismeInput minuteDebut(String minuteDebut) {
        return (DeclarationSinistreVandalismeInput ) super.minuteDebut(minuteDebut);
    }

    @Override
    public DeclarationSinistreVandalismeInput minuteFin(String minuteFin) {
        return (DeclarationSinistreVandalismeInput ) super.minuteFin(minuteFin);
    }

    @Override
    public DeclarationSinistreVandalismeInput assure(AssureInput assure) {
        return (DeclarationSinistreVandalismeInput ) super.assure(assure);
    }

    @Override
    public DeclarationSinistreVandalismeInput dateSinistre(Date dateSinistre) {
        return (DeclarationSinistreVandalismeInput ) super.dateSinistre(dateSinistre);
    }

    @Override
    public DeclarationSinistreVandalismeInput origine(String origineString) {
        super.origine(origineString);
        return this;
    }

    @Override
    public DeclarationSinistreVandalismeInput mailTrieste(String mailTrieste) {
        this.mailTrieste = mailTrieste;
        return this;
    }

}
