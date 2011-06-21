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
 * Dommages sur les murs
 */
@Embeddable
public class Murs {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "MURS_PAPIER_PEINT")
    private String papierPeint;

    @Column(name = "MURS_PEINTURE")
    private String peinture;

    @Column(name = "MURS_AUTRE")
    private String autre;

    @Column(name = "MURS_SURFACE_AUTRE")
    private String surfaceAutre;

    public Murs() {
        super();
    }

    public Murs(String papierPeint, String peinture, String autre, String surfaceAutre) {
        super();
        this.papierPeint = papierPeint;
        this.peinture = peinture;
        this.autre = autre;
        this.surfaceAutre = surfaceAutre;
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
     * @return the surfaceAutre
     */
    public String getSurfaceAutre() {
        return surfaceAutre;
    }

    /**
     * @param surfaceAutre the surfaceAutre to set
     */
    public void setSurfaceAutre(String surfaceAutre) {
        this.surfaceAutre = surfaceAutre;
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
