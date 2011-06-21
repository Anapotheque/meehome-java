/**
 * 
 */
package fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

/**
 * @author ARUL Aguilane
 */
@Embeddable
public class Consequence {

    /**
     * indique si un tiers a subi des dommages.
     */
    @Column(name = "IS_DOMMAGE_SUBI_PAR_TIERS")
    private String isDommageSubiParTiers;

    /**
     * informations concernant la personne tiers ayant subi des dommages.
     */
    @Embedded
    private Tiers dommageSubiParTiers;

    /**
     * indique si l'assuré a subi des dommages
     */
    @Column(name = "IS_DOMMAGE_SUBI")
    private Boolean isDommageSubi;

    /**
     * @return the isDommageSubiParTiers
     */
    public String getIsDommageSubiParTiers() {
        return isDommageSubiParTiers;
    }

    /**
     * @param isDommageSubiParTiers the isDommageSubiParTiers to set
     */
    public void setIsDommageSubiParTiers(String isDommageSubiParTiers) {
        this.isDommageSubiParTiers = isDommageSubiParTiers;
    }

    /**
     * @return the dommageTiers
     */
    public Tiers getDommageTiers() {
        return dommageSubiParTiers;
    }

    /**
     * @param dommageTiers the dommageTiers to set
     */
    public void setDommageTiers(Tiers dommageTiers) {
        this.dommageSubiParTiers = dommageTiers;
    }

    /**
     * @return the isDommageSubi
     */
    public Boolean getIsDommageSubi() {
        return isDommageSubi;
    }

    /**
     * @param isDommageSubi the isDommageSubi to set
     */
    public void setIsDommageSubi(Boolean isDommageSubi) {
        this.isDommageSubi = isDommageSubi;
    }

}
