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
 * Dommages sur le sol
 */
// @Entity
// @Table(name = "DS_DDE_SOL")
@Embeddable
public class Sol {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "SOL_PARQUET")
    private String parquet;

    @Column(name = "SOL_CARRELAGE")
    private String carrelage;

    @Column(name = "SOL_MOQUETTE")
    private String moquette;

    @Column(name = "SOL_REVETEMENT_PLASTIQUE")
    private String revetementPlastique;

    @Column(name = "SOL_AUTRE")
    private String autre;

    @Column(name = "SOL_SURFACE_AUTRES")
    private String surfaceAutres;

    public Sol() {
        super();
    }

    public Sol(String parquet, String carrelage, String moquette, String revetementPlastique, String autre,
                    String surfaceAutres) {
        super();
        this.parquet = parquet;
        this.carrelage = carrelage;
        this.moquette = moquette;
        this.revetementPlastique = revetementPlastique;
        this.autre = autre;
        this.surfaceAutres = surfaceAutres;
    }

    /**
     * @return the parquet
     */
    public String getParquet() {
        return parquet;
    }

    /**
     * @param parquet the parquet to set
     */
    public void setParquet(String parquet) {
        this.parquet = parquet;
    }

    /**
     * @return the carrelage
     */
    public String getCarrelage() {
        return carrelage;
    }

    /**
     * @param carrelage the carrelage to set
     */
    public void setCarrelage(String carrelage) {
        this.carrelage = carrelage;
    }

    /**
     * @return the moquette
     */
    public String getMoquette() {
        return moquette;
    }

    /**
     * @param moquette the moquette to set
     */
    public void setMoquette(String moquette) {
        this.moquette = moquette;
    }

    /**
     * @return the revetementPlastique
     */
    public String getRevetementPlastique() {
        return revetementPlastique;
    }

    /**
     * @param revetementPlastique the revetementPlastique to set
     */
    public void setRevetementPlastique(String revetementPlastique) {
        this.revetementPlastique = revetementPlastique;
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
