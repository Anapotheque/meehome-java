/**
 * 
 */
package fr.generali.gfb.ws.sinistre.persistence.domain.auto.accident;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import fr.generali.gfb.ws.sinistre.persistence.domain.auto.SinistreAuto;

/**
 * @author Moncoutier Christophe
 */
@Entity
@Table(name = "DS_SIN_ACCIDENT_AUTO")
@DiscriminatorValue("ACCIDENT_AUTO")
public class SinistreAutoAccidentIncendie extends SinistreAuto {
    
    
    @Column(name = "LIEU", length = 1000)
    private String lieu;

    @Embedded
    private DommagesAutoIncendieAccident dommage;
    

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public DommagesAutoIncendieAccident getDommage() {
        return dommage;
    }

    public void setDommage(DommagesAutoIncendieAccident dommage) {
        this.dommage = dommage;
    }
    
    
}
