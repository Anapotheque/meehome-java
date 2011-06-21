/**
 * 
 */
package fr.generali.gfb.ws.sinistre.persistence.domain.mrh.dommageselectriques;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import fr.generali.gfb.ws.sinistre.persistence.domain.common.Sinistre;

/**
 * @author ARUL Aguilane
 */
@Entity
@Table(name = "DS_SIN_ELECTRIQUE")
@DiscriminatorValue("DOM_ELECTRIQUES")
public class SinistreMRHElectrique extends Sinistre {

    /**
     * type de l'appareil
     */
    @Column(name = "TYPE_APPAREIL")
    private String typeAppareil;

    /**
     * marque de l'appareil
     */
    @Column(name = "MARQUE")
    private String marque;

    /**
     * date de l'appareil
     */
    @Column(name = "DATE_ACHAT")
    private Date dateAchat;

    /**
     * valeur à l'achat
     */
    @Column(name = "VALEUR_ACHAT")
    private String valeurAchat;

    /**
     * description du dommage
     */
    @Column(name = "DESCRIPTION_DOMMAGE", length = 1000)
    private String descriptionDommage;

    /**
     * Nom du modèle
     */
    @Column(name = "MODELE")
    private String modele;

    /**
     * @return the typeAppareil
     */
    public String getTypeAppareil() {
        return typeAppareil;
    }

    /**
     * @param typeAppareil the typeAppareil to set
     */
    public void setTypeAppareil(String typeAppareil) {
        this.typeAppareil = typeAppareil;
    }

    /**
     * @return the marque
     */
    public String getMarque() {
        return marque;
    }

    /**
     * @param marque the marque to set
     */
    public void setMarque(String marque) {
        this.marque = marque;
    }

    /**
     * @return the dateAchat
     */
    public Date getDateAchat() {
        return dateAchat;
    }

    /**
     * @param dateAchat the dateAchat to set
     */
    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }

    /**
     * @return the valeurAchat
     */
    public String getValeurAchat() {
        return valeurAchat;
    }

    /**
     * @param valeurAchat the valeurAchat to set
     */
    public void setValeurAchat(String valeurAchat) {
        this.valeurAchat = valeurAchat;
    }

    /**
     * @return the descriptionDommage
     */
    public String getDescriptionDommage() {
        return descriptionDommage;
    }

    /**
     * @param descriptionDommage the descriptionDommage to set
     */
    public void setDescriptionDommage(String descriptionDommage) {
        this.descriptionDommage = descriptionDommage;
    }

    /**
     * @return the nomModele
     */
    public String getModele() {
        return modele;
    }

    /**
     * @param nomModele the nomModele to set
     */
    public void setModele(String modele) {
        this.modele = modele;
    }

}
