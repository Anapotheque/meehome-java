/**
 * 
 */
package fr.generali.gfb.ws.sinistre.persistence.domain.mrh.tempetegrele;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import fr.generali.gfb.ws.sinistre.persistence.domain.common.BienConcerne;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.Sinistre;

/**
 * @author ARUL Aguilane
 */
@Entity
@Table(name = "DS_SIN_TEMPETE_GRELE")
@DiscriminatorValue("TEMPETE_GRELE")
public class SinistreMRHTempeteGrele extends Sinistre {

    /**
     * Le bien concerne
     */
    @Embedded
    private BienConcerne bienConcerne;

    /**
     * Date de construction du bâtiment
     */
    @Column(name = "DATE_CONSTRUCTION_BATIMENT")
    private Date dateConstructionBatiment;

    /**
     * Dans quelles circonstances
     */
    @Column(name = "CIRCONSTANCES", length = 1000)
    private String circonstances;

    /**
     * les éléments endommagés
     * <ul>
     * <li>Toiture</li>
     * <li>Murs</li>
     * <li>Cheminée</li>
     * <li>Portail/Clotûre</li>
     * <li>Plantations</li>
     * <li>Installations</li>
     * <li>extérieures</li>
     * <li>Autre</li>
     * </ul>
     */
    // @CollectionOfElements
    @OneToMany(cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    @JoinColumn(name = "ELEMENT_ENDOMMAGES_FK")
    private List<ElementsEndommages> elementsEndommages;

    /**
     * les dommages
     */
    @Column(name = "DOMMAGES", length = 1000)
    private String dommages;

    /**
     * Si logement encore habitable ?
     */
    @Column(name = "IS_LOGEMENT_HABITABLE")
    private Boolean isLogementHabitable;

    /**
     * @return the bienConcerne
     */
    public BienConcerne getBienConcerne() {
        return bienConcerne;
    }

    /**
     * @param bienConcerne the bienConcerne to set
     */
    public void setBienConcerne(BienConcerne bienConcerne) {
        this.bienConcerne = bienConcerne;
    }

    /**
     * @return the dateConstructionBatiment
     */
    public Date getDateConstructionBatiment() {
        return dateConstructionBatiment;
    }

    /**
     * @param dateConstructionBatiment the dateConstructionBatiment to set
     */
    public void setDateConstructionBatiment(Date dateConstructionBatiment) {
        this.dateConstructionBatiment = dateConstructionBatiment;
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
     * @return the elementsEndommages
     */
    public List<ElementsEndommages> getElementsEndommages() {
        return elementsEndommages;
    }

    /**
     * @param elementsEndommages the elementsEndommages to set
     */
    public void setElementsEndommages(List<ElementsEndommages> elementsEndommages) {
        this.elementsEndommages = elementsEndommages;
    }

    /**
     * @return the dommages
     */
    public String getDommages() {
        return dommages;
    }

    /**
     * @param dommages the dommages to set
     */
    public void setDommages(String dommages) {
        this.dommages = dommages;
    }

    /**
     * @return the isLogementHabitable
     */
    public Boolean getIsLogementHabitable() {
        return isLogementHabitable;
    }

    /**
     * @param isLogementHabitable the isLogementHabitable to set
     */
    public void setIsLogementHabitable(Boolean isLogementHabitable) {
        this.isLogementHabitable = isLogementHabitable;
    }

}
