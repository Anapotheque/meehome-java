/**
 * 
 */
package fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux;

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
@Table(name = "DS_SIN_DEGATS_DES_EAUX")
@DiscriminatorValue("DEGATS_DES_EAUX")
public class SinistreMRHDegatsEaux extends Sinistre {

    // TODO : Savoir la chaine de caractère exacte transmis
    public static final String SINISTRE_DEGATS_EAUX_CAUSE_INFILTRATION = "Infiltrations";

    // TODO : Savoir la chaine de caractère exacte transmis
    public static final String SINISTRE_DEGATS_EAUX_CAUSE_AUTRE = "Autre cause";

    /**
     * Le bien concerne
     */
    @Embedded
    private BienConcerne bienConcerne;

    /**
     * Cause du sinistre. peut prendre les valeurs suivantes
     * <ul>
     * <li>Fuite sur canalisation</li>
     * <li>Fuite ou débordement de chéneaux ou de gouttières</li>
     * <li>Débordements d’appareils à effet d’eau (évier, lavabo, machine à
     * laver)</li>
     * <li>Débordement ou renversement de récipients</li>
     * <li>Infiltrations</li>
     * <li>Autre cause</li>
     * <li>Cause inconnue</li>
     * </ul>
     */
    // @CollectionOfElements
    @OneToMany(cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    @JoinColumn(name = "CAUSE_FK")
    private List<Cause> cause;

    /**
     * Endroit de l'infiltration :
     * <ul>
     * <li>Toiture</li>
     * <li>Terrasse</li>
     * <li>Façade</li>
     * <li>Balcon</li>
     * <li>Autre</li>
     * </ul>
     */
    // @CollectionOfElements
    @OneToMany(cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    @JoinColumn(name = "INFILTRATION_FK")
    private List<Infiltration> infiltration;

    /**
     * si il y a une autre cause que celle définit par les valeurs que peut
     * prendre cause.
     */
    @Column(name = "AUTRE_CAUSE")
    private String autreCause;

    /**
     * Son origine se situe dans :
     * <ul>
     * <li>Dans les locaux occupés par l’assuré</li>
     * <li>Dans les parties communes de l’immeuble</li>
     * <li>Dans un appartement voisin</li>
     * <li>Dans un immeuble voisin</li>
     * <li>Autre</li>
     * </ul>
     */
    @Column(name = "ORIGINE_SINISTRE")
    private String origineSinistre;

    /**
     * indique si la cause du sinistre est réparée.
     */
    @Column(name = "IS_CAUSE_REPAREE")
    private Boolean isCauseReparee;

    /**
     * les conséquences
     */
    @Embedded
    private Consequence consequence;

    /**
     * Les dommages sur les biens
     */
    @Embedded
    private Dommages dommages;

    /**
     * @return the cause
     */
    public List<Cause> getCause() {
        return cause;
    }

    /**
     * @param cause the cause to set
     */
    public void setCause(List<Cause> cause) {
        this.cause = cause;
    }

    /**
     * @return the infiltration
     */
    public List<Infiltration> getInfiltration() {
        return infiltration;
    }

    /**
     * @param infiltration the infiltration to set
     */
    public void setInfiltration(List<Infiltration> infiltration) {
        this.infiltration = infiltration;
    }

    /**
     * @return the autreCause
     */
    public String getAutreCause() {
        return autreCause;
    }

    /**
     * @param autreCause the autreCause to set
     */
    public void setAutreCause(String autreCause) {
        this.autreCause = autreCause;
    }

    /**
     * @return the origineSinistre
     */
    public String getOrigineSinistre() {
        return origineSinistre;
    }

    /**
     * @param origineSinistre the origineSinistre to set
     */
    public void setOrigineSinistre(String origineSinistre) {
        this.origineSinistre = origineSinistre;
    }

    /**
     * @return the isCauseReparee
     */
    public Boolean getIsCauseReparee() {
        return isCauseReparee;
    }

    /**
     * @param isCauseReparee the isCauseReparee to set
     */
    public void setIsCauseReparee(Boolean isCauseReparee) {
        this.isCauseReparee = isCauseReparee;
    }

    /**
     * @return the consequence
     */
    public Consequence getConsequence() {
        return consequence;
    }

    /**
     * @param consequence the consequence to set
     */
    public void setConsequence(Consequence consequence) {
        this.consequence = consequence;
    }

    /**
     * @return the bien
     */
    public Dommages getDommages() {
        return dommages;
    }

    /**
     * @param dommages the bien to set
     */
    public void setDommages(Dommages dommages) {
        this.dommages = dommages;
    }

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

}
