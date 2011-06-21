/**
 * 
 */
package fr.generali.gfb.ws.sinistre.persistence.domain.auto.vol;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import fr.generali.gfb.ws.sinistre.persistence.domain.auto.SinistreAuto;

/**
 * @author ARUL Aguilane
 */
@Entity
@Table(name = "DS_SIN_VOL_AUTO")
@DiscriminatorValue("VOL_AUTO")
public class SinistreAutoVol extends SinistreAuto {

    @Column(name = "PLAINTE")
    private Boolean plainte;

    @Column(name = "TYPE")
    private String type;

    @Embedded
    private DommagesAutoVol dommage;

    public Boolean getPlainte() {
        return plainte;
    }

    public void setPlainte(Boolean plainte) {
        this.plainte = plainte;
    }

    public DommagesAutoVol getDommage() {
        return dommage;
    }

    public void setDommage(DommagesAutoVol dommage) {
        this.dommage = dommage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
