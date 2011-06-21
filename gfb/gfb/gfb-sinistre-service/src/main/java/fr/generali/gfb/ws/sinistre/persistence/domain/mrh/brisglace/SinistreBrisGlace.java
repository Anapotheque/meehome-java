/**
 * 
 */
package fr.generali.gfb.ws.sinistre.persistence.domain.mrh.brisglace;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import fr.generali.gfb.ws.sinistre.persistence.domain.common.Sinistre;
import fr.generali.gfb.ws.sinistre.persistence.domain.constants.TypeSinistreEnum;

/**
 * @author ARUL Aguilane
 */
@Entity
@Table(name = "DS_SIN_BRIS_GLACE")
@DiscriminatorValue("BRIS_GLACE")
public class SinistreBrisGlace extends Sinistre {

    /**
     * Dans quelles circonstances?
     */
    @Column(name = "CIRCONSTANCES", length = 1000)
    private String circonstances;

    /**
     * Date d’achat du bien
     */
    @Column(name = "DATE_ACHAT_BIEN")
    private Date dateAchatBien;

    /**
     * commentaires
     */
    @Column(name = "COMMENTAIRES", length = 1000)
    private String commentaires;

    /**
     * Type de bien endommagé
     * <ul>
     * <li>Simple vitrage</li>
     * <li>Double vitrage</li>
     * <li>Sur-vitrage</li>
     * <li>Insert</li>
     * <li>Vitre de meuble</li>
     * <li>Miroir</li>
     * <li>Autre</li>
     * </ul>
     */
    // @CollectionOfElements
    @OneToMany(cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    @JoinColumn(name = "TYPE_BIENS_ENDOMMAGES_FK")
    private List<TypeBiensEndommages> typeBiensEndommages;

    /**
     * Constructor. Initialise le type de sinistre
     */
    public SinistreBrisGlace() {
        setTypeSinistre(TypeSinistreEnum.MRH_BRIS_GLACE);
    }

    /**
     * @return the circonstances
     */
    public String getCirconstances() {
        return circonstances;
    }

    /**
     * @param circonstances the circonstances to set
     */
    public void setCirconstances(String circonstances) {
        this.circonstances = circonstances;
    }

    /**
     * @return the dateAchatBien
     */
    public Date getDateAchatBien() {
        return dateAchatBien;
    }

    /**
     * @param dateAchatBien the dateAchatBien to set
     */
    public void setDateAchatBien(Date dateAchatBien) {
        this.dateAchatBien = dateAchatBien;
    }

    /**
     * @return the commentaires
     */
    public String getCommentaires() {
        return commentaires;
    }

    /**
     * @param commentaires the commentaires to set
     */
    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }

    /**
     * @return the typeBienEndommage
     */
    public List<TypeBiensEndommages> getTypeBiensEndommages() {
        return typeBiensEndommages;
    }

    /**
     * @param typeBienEndommage the typeBienEndommage to set
     */
    public void setTypeBiensEndommages(List<TypeBiensEndommages> typeBienEndommage) {
        this.typeBiensEndommages = typeBienEndommage;
    }

}
