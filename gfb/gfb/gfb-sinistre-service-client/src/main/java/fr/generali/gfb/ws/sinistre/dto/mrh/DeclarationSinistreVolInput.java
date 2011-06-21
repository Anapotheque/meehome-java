package fr.generali.gfb.ws.sinistre.dto.mrh;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import fr.generali.gfb.ws.sinistre.dto.AbstractDeclarationSinistreInput;
import fr.generali.gfb.ws.sinistre.dto.AssureInput;

public class DeclarationSinistreVolInput extends AbstractDeclarationSinistreInput {

    private BienInput bien;

    private Boolean isOccupantPresent;

    private String dureeAbsence;

    private List<String> modesOperatoire;

    private String autreModeOperatoire;

    private String biensVoles;

    private String dommagesImmobiliers;

    private Boolean isPlainteDepose;

    public DeclarationSinistreVolInput() {
        super();
    }

    public DeclarationSinistreVolInput(String origineString, Date dateSinistre, AssureInput assure, BienInput bien,
                    Boolean isOccupantPresent, String dureeAbsence, List<String> modesOperatoire,
                    String autreModeOperatoire, String biensVoles, String dommagesImmobiliers, Boolean isPlainteDepose) {
        super(origineString, dateSinistre, assure);
        this.bien = bien;
        this.isOccupantPresent = isOccupantPresent;
        this.dureeAbsence = dureeAbsence;
        this.modesOperatoire = modesOperatoire;
        this.autreModeOperatoire = autreModeOperatoire;
        this.biensVoles = biensVoles;
        this.dommagesImmobiliers = dommagesImmobiliers;
        this.isPlainteDepose = isPlainteDepose;
    }

    @Override
    public String toString() {
        return super.toString() + ReflectionToStringBuilder.toString(this);
    }

    public BienInput getBien() {
        return bien;
    }

    public Boolean getIsOccupantPresent() {
        return isOccupantPresent;
    }

    public String getDureeAbsence() {
        return dureeAbsence;
    }

    public List<String> getModesOperatoire() {
        return modesOperatoire;
    }

    public String getAutreModeOperatoire() {
        return autreModeOperatoire;
    }

    public String getBiensVoles() {
        return biensVoles;
    }

    public String getDommagesImmobiliers() {
        return dommagesImmobiliers;
    }

    public Boolean getIsPlainteDepose() {
        return isPlainteDepose;
    }

    public DeclarationSinistreVolInput bien(BienInput bien) {
        this.bien = bien;
        return this;
    }

    public DeclarationSinistreVolInput isOccupantPresent(Boolean isOccupantPresent) {
        this.isOccupantPresent = isOccupantPresent;
        return this;
    }

    public DeclarationSinistreVolInput dureeAbsence(String dureeAbsence) {
        this.dureeAbsence = dureeAbsence;
        return this;
    }

    public DeclarationSinistreVolInput modeOperatoire(List<String> modesOperatoire) {
        this.modesOperatoire = modesOperatoire;
        return this;
    }

    public DeclarationSinistreVolInput autreModeOperatoire(String autreModeOperatoire) {
        this.autreModeOperatoire = autreModeOperatoire;
        return this;
    }

    public DeclarationSinistreVolInput biensVoles(String biensVoles) {
        this.biensVoles = biensVoles;
        return this;
    }

    public DeclarationSinistreVolInput dommagesImmobiliers(String dommagesImmobiliers) {
        this.dommagesImmobiliers = dommagesImmobiliers;
        return this;
    }

    public DeclarationSinistreVolInput isPlainteDepose(Boolean isPlainteDepose) {
        this.isPlainteDepose = isPlainteDepose;
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

    /**
     * @param bien the bien to set
     */
    public void setBien(BienInput bien) {
        this.bien = bien;
    }

    /**
     * @param isOccupantPresent the isOccupantPresent to set
     */
    public void setIsOccupantPresent(Boolean isOccupantPresent) {
        this.isOccupantPresent = isOccupantPresent;
    }

    /**
     * @param dureeAbsence the dureeAbsence to set
     */
    public void setDureeAbsence(String dureeAbsence) {
        this.dureeAbsence = dureeAbsence;
    }

    /**
     * @param modesOperatoire the modesOperatoire to set
     */
    public void setModesOperatoire(List<String> modesOperatoire) {
        this.modesOperatoire = modesOperatoire;
    }

    /**
     * @param autreModeOperatoire the autreModeOperatoire to set
     */
    public void setAutreModeOperatoire(String autreModeOperatoire) {
        this.autreModeOperatoire = autreModeOperatoire;
    }

    /**
     * @param biensVoles the biensVoles to set
     */
    public void setBiensVoles(String biensVoles) {
        this.biensVoles = biensVoles;
    }

    /**
     * @param dommagesImmobiliers the dommagesImmobiliers to set
     */
    public void setDommagesImmobiliers(String dommagesImmobiliers) {
        this.dommagesImmobiliers = dommagesImmobiliers;
    }

    /**
     * @param isPlainteDepose the isPlainteDepose to set
     */
    public void setIsPlainteDepose(Boolean isPlainteDepose) {
        this.isPlainteDepose = isPlainteDepose;
    }

    @Override
    public DeclarationSinistreVolInput mailTrieste(String mailTrieste) {
        this.mailTrieste = mailTrieste;
        return this;
    }
}
