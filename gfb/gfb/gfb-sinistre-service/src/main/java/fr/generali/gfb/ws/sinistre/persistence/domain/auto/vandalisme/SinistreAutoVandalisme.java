/**
 * 
 */
package fr.generali.gfb.ws.sinistre.persistence.domain.auto.vandalisme;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import fr.generali.gfb.ws.sinistre.persistence.domain.auto.DommagesAuto;
import fr.generali.gfb.ws.sinistre.persistence.domain.auto.SinistreAuto;

/**
 * @author Moncoutier Christophe
 */
@Entity
@Table(name = "DS_SIN_VANDALISME_AUTO")
@DiscriminatorValue("VANDALISME_AUTO")
public class SinistreAutoVandalisme extends SinistreAuto {
    
    
    @Column(name = "PLAINTE")
    private Boolean plainte;

    @Embedded
    private DommagesAuto dommage;

    public Boolean getPlainte() {
        return plainte;
    }

    public void setPlainte(Boolean plainte) {
        this.plainte = plainte;
    }

    public DommagesAuto getDommage() {
        return dommage;
    }

    public void setDommage(DommagesAuto dommage) {
        this.dommage = dommage;
    }
    
    
}
