/**
 * 
 */
package fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author ARUL Aguilane
 */
/**
 * Dommages sur les plafonds
 */
// @Entity
// @Table(name = "DS_DDE_PLAFONDS")
@Embeddable
public class Plafonds {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "PLAFONDS_PAPIER_PEINT")
    private String papierPeint;

    @Column(name = "PLAFONDS_PEINTURE")
    private String peinture;

    @Column(name = "PLAFONDS_AUTRE")
    private String autre;

    @Column(name = "PLAFONDS_SURFACE_AUTRES")
    private String surfaceAutres;

    public Plafonds() {
        super();
    }

    public Plafonds(String papierPeint, String peinture, String autre, String surfaceAutres) {
        super();
        this.papierPeint = papierPeint;
        this.peinture = peinture;
        this.autre = autre;
        this.surfaceAutres = surfaceAutres;
    }

    /**
     * @return the papierPeint
     */
    public String getPapierPeint() {
        return papierPeint;
    }

    /**
     * @param papierPeint the papierPeint to set
     */
    public void setPapierPeint(String papierPeint) {
        this.papierPeint = papierPeint;
    }

    /**
     * @return the peinture
     */
    public String getPeinture() {
        return peinture;
    }

    /**
     * @param peinture the peinture to set
     */
    public void setPeinture(String peinture) {
        this.peinture = peinture;
    }

    /**
     * @return the autre
     */
    public String getAutre() {
        return autre;
    }

    /**
     * @param autre the autre to set
     */
    public void setAutre(String autre) {
        this.autre = autre;
    }

    /**
     * @return the surfaceAutres
     */
    public String getSurfaceAutres() {
        return surfaceAutres;
    }

    /**
     * @param surfaceAutres the surfaceAutres to set
     */
    public void setSurfaceAutres(String surfaceAutres) {
        this.surfaceAutres = surfaceAutres;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

}
