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
 * Dommages sur mobiliers
 */
// @Entity
// @Table(name = "DS_DDE_MOBILIER")
@Embeddable
public class Mobilier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "MOBILIER_ENDOMMAGE", length = 1000)
    private String mobilierEndommage;

    public Mobilier() {
        super();
    }

    public Mobilier(String mobilierEndommage) {
        super();
        this.mobilierEndommage = mobilierEndommage;
    }

    /**
     * @return the mobilierEndommage
     */
    public String getMobilierEndommage() {
        return mobilierEndommage;
    }

    /**
     * @param mobilierEndommage the mobilierEndommage to set
     */
    public void setMobilierEndommage(String mobilierEndommage) {
        this.mobilierEndommage = mobilierEndommage;
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
