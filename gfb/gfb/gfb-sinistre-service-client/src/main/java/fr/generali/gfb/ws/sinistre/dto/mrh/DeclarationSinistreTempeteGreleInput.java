package fr.generali.gfb.ws.sinistre.dto.mrh;

import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import fr.generali.gfb.ws.sinistre.dto.AbstractDeclarationSinistreInput;
import fr.generali.gfb.ws.sinistre.dto.AssureInput;

public class DeclarationSinistreTempeteGreleInput extends AbstractDeclarationSinistreInput {

    private BienInput bien;

    private Date dateConstruction;

    private String circonstances;

    private Collection<String> elementsEndommages;

    private String dommages;

    private Boolean isLogementHabitable;

    public DeclarationSinistreTempeteGreleInput(String origineString, Date dateSinistre, AssureInput assure,
                    BienInput bien, Date dateConstruction, String circonstances, Collection<String> elementsEndommages,
                    String dommages, Boolean isLogementHabitable) {
        super(origineString, dateSinistre, assure);
        this.bien = bien;
        this.dateConstruction = dateConstruction;
        this.circonstances = circonstances;
        this.elementsEndommages = elementsEndommages;
        this.dommages = dommages;
        this.isLogementHabitable = isLogementHabitable;
    }

    public DeclarationSinistreTempeteGreleInput() {
        super();
    }

    @Override
    public String toString() {
        return super.toString() + ReflectionToStringBuilder.toString(this);
    }

    public BienInput getBien() {
        return bien;
    }

    public Date getDateConstruction() {
        return dateConstruction;
    }

    public String getCirconstances() {
        return circonstances;
    }

    public Collection<String> getElementsEndommages() {
        return elementsEndommages;
    }

    public String getDommages() {
        return dommages;
    }

    public Boolean getIsLogementHabitable() {
        return isLogementHabitable;
    }

    public DeclarationSinistreTempeteGreleInput bien(BienInput bien) {
        this.bien = bien;
        return this;
    }

    public DeclarationSinistreTempeteGreleInput dateConstruction(Date dateConstruction) {
        this.dateConstruction = dateConstruction;
        return this;
    }

    public DeclarationSinistreTempeteGreleInput circonstances(String circonstances) {
        this.circonstances = circonstances;
        return this;
    }

    public DeclarationSinistreTempeteGreleInput elementsEndommages(Collection<String> elementsEndommages) {
        this.elementsEndommages = elementsEndommages;
        return this;
    }

    public DeclarationSinistreTempeteGreleInput dommages(String dommages) {
        this.dommages = dommages;
        return this;
    }

    public DeclarationSinistreTempeteGreleInput isLogementHabitable(Boolean isLogementHabitable) {
        this.isLogementHabitable = isLogementHabitable;
        return this;
    }

    @Override
    public DeclarationSinistreTempeteGreleInput assure(AssureInput assure) {
        super.assure(assure);
        return this;
    }

    @Override
    public DeclarationSinistreTempeteGreleInput dateSinistre(Date dateSinistre) {
        super.dateSinistre(dateSinistre);
        return this;
    }

    @Override
    public DeclarationSinistreTempeteGreleInput origine(String origineString) {
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
     * @param dateConstruction the dateConstruction to set
     */
    public void setDateConstruction(Date dateConstruction) {
        this.dateConstruction = dateConstruction;
    }

    /**
     * @param circonstances the circonstances to set
     */
    public void setCirconstances(String circonstances) {
        this.circonstances = circonstances;
    }

    /**
     * @param elementsEndommages the elementsEndommages to set
     */
    public void setElementsEndommages(Collection<String> elementsEndommages) {
        this.elementsEndommages = elementsEndommages;
    }

    /**
     * @param dommages the dommages to set
     */
    public void setDommages(String dommages) {
        this.dommages = dommages;
    }

    /**
     * @param isLogementHabitable the isLogementHabitable to set
     */
    public void setIsLogementHabitable(Boolean isLogementHabitable) {
        this.isLogementHabitable = isLogementHabitable;
    }

    @Override
    public DeclarationSinistreTempeteGreleInput mailTrieste(String mailTrieste) {
        this.mailTrieste = mailTrieste;
        return this;
    }
}
