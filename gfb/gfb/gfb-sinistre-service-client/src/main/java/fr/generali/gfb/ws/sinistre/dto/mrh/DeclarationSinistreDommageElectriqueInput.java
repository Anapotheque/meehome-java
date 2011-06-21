package fr.generali.gfb.ws.sinistre.dto.mrh;

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import fr.generali.gfb.ws.sinistre.dto.AbstractDeclarationSinistreInput;
import fr.generali.gfb.ws.sinistre.dto.AssureInput;

public class DeclarationSinistreDommageElectriqueInput extends AbstractDeclarationSinistreInput {

    private String type;

    private String marque;

    private String modele;

    private Date dateAchat;

    private String valeurAchat;

    private String descriptionDommage;

    /**
     * @param origine
     * @param dateSinistre
     * @param assure
     * @param type d'appareil
     * @param marque de l'appareil
     * @param modele de l'appareil
     * @param dateAchat de l'appareil
     * @param valeurAchat de l'appareil
     * @param description du dommage
     */
    public DeclarationSinistreDommageElectriqueInput(String origineString, Date dateSinistre, AssureInput assure,
                    String type, String marque, String modele, Date dateAchat, String valeurAchat, String description) {
        super(origineString, dateSinistre, assure);
        this.type = type;
        this.marque = marque;
        this.modele = modele;
        this.dateAchat = dateAchat;
        this.valeurAchat = valeurAchat;
        this.descriptionDommage = description;
    }

    public DeclarationSinistreDommageElectriqueInput() {
        super();
    }

    @Override
    public String toString() {
        return super.toString() + ReflectionToStringBuilder.toString(this);
    }

    public String getType() {
        return type;
    }

    public DeclarationSinistreDommageElectriqueInput type(String type) {
        this.type = type;
        return this;
    }

    public String getMarque() {
        return marque;
    }

    public DeclarationSinistreDommageElectriqueInput marque(String marque) {
        this.marque = marque;
        return this;
    }

    public String getModele() {
        return modele;
    }

    public DeclarationSinistreDommageElectriqueInput modele(String modele) {
        this.modele = modele;
        return this;
    }

    public Date getDateAchat() {
        return dateAchat;
    }

    public DeclarationSinistreDommageElectriqueInput dateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
        return this;
    }

    public String getValeurAchat() {
        return valeurAchat;
    }

    public DeclarationSinistreDommageElectriqueInput valeurAchat(String valeurAchat) {
        this.valeurAchat = valeurAchat;
        return this;
    }

    public String getDescriptionDommage() {
        return descriptionDommage;
    }

    public DeclarationSinistreDommageElectriqueInput description(String description) {
        this.descriptionDommage = description;
        return this;
    }

    @Override
    public DeclarationSinistreDommageElectriqueInput assure(AssureInput assure) {
        super.assure(assure);
        return this;
    }

    @Override
    public DeclarationSinistreDommageElectriqueInput dateSinistre(Date dateSinistre) {
        super.dateSinistre(dateSinistre);
        return this;
    }

    @Override
    public DeclarationSinistreDommageElectriqueInput origine(String origineString) {
        super.origine(origineString);
        return this;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @param marque the marque to set
     */
    public void setMarque(String marque) {
        this.marque = marque;
    }

    /**
     * @param modele the modele to set
     */
    public void setModele(String modele) {
        this.modele = modele;
    }

    /**
     * @param dateAchat the dateAchat to set
     */
    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }

    /**
     * @param valeurAchat the valeurAchat to set
     */
    public void setValeurAchat(String valeurAchat) {
        this.valeurAchat = valeurAchat;
    }

    /**
     * @param descriptionDommage the descriptionDommage to set
     */
    public void setDescriptionDommage(String descriptionDommage) {
        this.descriptionDommage = descriptionDommage;
    }

    @Override
    public DeclarationSinistreDommageElectriqueInput mailTrieste(String mailTrieste) {
        this.mailTrieste = mailTrieste;
        return this;
    }
}
