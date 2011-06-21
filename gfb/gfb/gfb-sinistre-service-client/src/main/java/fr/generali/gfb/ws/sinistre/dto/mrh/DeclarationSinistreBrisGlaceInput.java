package fr.generali.gfb.ws.sinistre.dto.mrh;

import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import fr.generali.gfb.ws.sinistre.dto.AbstractDeclarationSinistreInput;
import fr.generali.gfb.ws.sinistre.dto.AssureInput;

public class DeclarationSinistreBrisGlaceInput extends AbstractDeclarationSinistreInput {

    /*
     * Informations relatives au sinistre bris de glace
     */
    private String circonstances;

    private Date dateAchatBien;

    private String commentaires;

    private Collection<TypeBienEndommageBDG> typeBiensEndommages;

    /**
     * Default constructor
     */
    public DeclarationSinistreBrisGlaceInput() {
        super();
    }

    /**
     * Full constructor
     * 
     * @param dateSinistre
     * @param assure
     * @param circonstances
     * @param dateAchatBien
     * @param commentaires
     * @param typeBiensEndommages
     */
    public DeclarationSinistreBrisGlaceInput(String origineString, Date dateSinistre, AssureInput assure,
                    String circonstances, Date dateAchatBien, String commentaires,
                    Collection<TypeBienEndommageBDG> typeBiensEndommages) {
        super(origineString, dateSinistre, assure);
        this.circonstances = circonstances;
        this.dateAchatBien = dateAchatBien;
        this.commentaires = commentaires;
        this.typeBiensEndommages = typeBiensEndommages;
    }

    @Override
    public String toString() {
        return super.toString() + ReflectionToStringBuilder.toString(this);
    }

    public String getCirconstances() {
        return circonstances;
    }

    public Date getDateAchatBien() {
        return dateAchatBien;
    }

    public String getCommentaires() {
        return commentaires;
    }

    public Collection<TypeBienEndommageBDG> getTypeBiensEndommages() {
        return typeBiensEndommages;
    }

    public DeclarationSinistreBrisGlaceInput circonstances(String circonstances) {
        this.circonstances = circonstances;
        return this;
    }

    public DeclarationSinistreBrisGlaceInput dateAchatBien(Date dateAchatBien) {
        this.dateAchatBien = dateAchatBien;
        return this;
    }

    public DeclarationSinistreBrisGlaceInput commentaires(String commentaires) {
        this.commentaires = commentaires;
        return this;
    }

    public DeclarationSinistreBrisGlaceInput typeBiensEndommages(Collection<TypeBienEndommageBDG> typeBiensEndommages) {
        this.typeBiensEndommages = typeBiensEndommages;
        return this;
    }

    @Override
    public DeclarationSinistreBrisGlaceInput assure(AssureInput assure) {
        super.assure(assure);
        return this;
    }

    @Override
    public DeclarationSinistreBrisGlaceInput dateSinistre(Date dateSinistre) {
        super.dateSinistre(dateSinistre);
        return this;
    }

    @Override
    public DeclarationSinistreBrisGlaceInput origine(String origineString) {
        super.origine(origineString);
        return this;
    }

    public void setCirconstances(String circonstances) {
        this.circonstances = circonstances;
    }

    public void setDateAchatBien(Date dateAchatBien) {
        this.dateAchatBien = dateAchatBien;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }

    public void setTypeBiensEndommages(Collection<TypeBienEndommageBDG> typeBiensEndommages) {
        this.typeBiensEndommages = typeBiensEndommages;
    }

    @Override
    public DeclarationSinistreBrisGlaceInput mailTrieste(String mailTrieste) {
        this.mailTrieste = mailTrieste;
        return this;
    }
}
