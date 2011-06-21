/**
 * 
 */
package fr.generali.gfb.ws.sinistre.persistence.domain.auto.vol;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import fr.generali.gfb.ws.sinistre.persistence.domain.auto.DommagesAuto;



/**
 * @author Moncoutier Christophe
 */

@Embeddable
public class DommagesAutoVol extends DommagesAuto{
    
    @Column(name = "DOM_MATERIEL")
    private String effetsPersonnels;

    public String getEffetsPersonnels() {
        return effetsPersonnels;
    }

    public void setEffetsPersonnels(String effetsPersonnels) {
        this.effetsPersonnels = effetsPersonnels;
    }
}
