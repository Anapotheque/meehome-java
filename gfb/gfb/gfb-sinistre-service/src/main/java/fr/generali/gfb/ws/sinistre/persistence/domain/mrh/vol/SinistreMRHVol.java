/**
 * 
 */
package fr.generali.gfb.ws.sinistre.persistence.domain.mrh.vol;

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
@Table(name = "DS_SIN_VOL_CAMBRIOLAGE")
@DiscriminatorValue("VOL_CAMBRIOLAGE")
public class SinistreMRHVol extends Sinistre {

    /**
     * Le bien concerne
     */
    @Embedded
    private BienConcerne bienConcerne;

    /**
     * indique si un occupant etait present
     */
    @Column(name = "IS_OCCUPANT_PRESENT")
    private Boolean isOccupantPresent;

    /**
     * Duree de l'absence
     */
    @Column(name = "DUREE_ABSENCE")
    private String dureeAbsence;

    /**
     * Mode Operatoire :
     * <ul>
     * <li>Effraction</li>
     * <li>Escalade</li>
     * <li>Usage de fausse clé</li>
     * <li>Violence</li>
     * <li>Autre, précisez</li>
     * </ul>
     */
    // @CollectionOfElements
    @OneToMany(cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    @JoinColumn(name = "MODE_OPERATOIRE_FK")
    private List<ModeOperatoire> modeOperatoire;

    /**
     * Autre mode opératoire
     */
    @Column(name = "AUTRE_MODE_OPERATOIRE")
    private String autreModeOperatoire;

    /**
     * descriptions des biens volés
     */
    @Column(name = "BIENS_VOLES", length = 1000)
    private String biensVoles;

    /**
     * descriptions des mobiliers endommages
     */
    @Column(name = "DOM_IMMOBILIERS", length = 1000)
    private String dommagesImmobiliers;

    /**
     * indique si la plainte a ete depose
     */
    @Column(name = "IS_PLAINTE_DEPOSE")
    private Boolean isPlainteDepose;

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
     * @return the isOccupantPresent
     */
    public Boolean getIsOccupantPresent() {
        return isOccupantPresent;
    }

    /**
     * @param isOccupantPresent the isOccupantPresent to set
     */
    public void setIsOccupantPresent(Boolean isOccupantPresent) {
        this.isOccupantPresent = isOccupantPresent;
    }

    /**
     * @return the dureeAbsence
     */
    public String getDureeAbsence() {
        return dureeAbsence;
    }

    /**
     * @param dureeAbsence the dureeAbsence to set
     */
    public void setDureeAbsence(String dureeAbsence) {
        this.dureeAbsence = dureeAbsence;
    }

    /**
     * @return the modeOperatoire
     */
    public List<ModeOperatoire> getModeOperatoire() {
        return modeOperatoire;
    }

    /**
     * @param modeOperatoire the modeOperatoire to set
     */
    public void setModeOperatoire(List<ModeOperatoire> modeOperatoire) {
        this.modeOperatoire = modeOperatoire;
    }

    /**
     * @return the autreModeOperatoire
     */
    public String getAutreModeOperatoire() {
        return autreModeOperatoire;
    }

    /**
     * @param autreModeOperatoire the autreModeOperatoire to set
     */
    public void setAutreModeOperatoire(String autreModeOperatoire) {
        this.autreModeOperatoire = autreModeOperatoire;
    }

    /**
     * @return the biensVoles
     */
    public String getBiensVoles() {
        return biensVoles;
    }

    /**
     * @param biensVoles the biensVoles to set
     */
    public void setBiensVoles(String biensVoles) {
        this.biensVoles = biensVoles;
    }

    /**
     * @return the mobiliersEndommages
     */
    public String getDommagesImmobiliers() {
        return dommagesImmobiliers;
    }

    /**
     * @param mobiliersEndommages the mobiliersEndommages to set
     */
    public void setDommagesImmobiliers(String dommagesImmobiliers) {
        this.dommagesImmobiliers = dommagesImmobiliers;
    }

    /**
     * @return the isPlainteDepose
     */
    public Boolean getIsPlainteDepose() {
        return isPlainteDepose;
    }

    /**
     * @param isPlainteDepose the isPlainteDepose to set
     */
    public void setIsPlainteDepose(Boolean isPlainteDepose) {
        this.isPlainteDepose = isPlainteDepose;
    }

}
