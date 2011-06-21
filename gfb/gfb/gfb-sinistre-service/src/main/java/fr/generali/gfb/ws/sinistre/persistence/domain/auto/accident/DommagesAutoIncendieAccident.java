/**
 * 
 */
package fr.generali.gfb.ws.sinistre.persistence.domain.auto.accident;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import fr.generali.gfb.ws.sinistre.persistence.domain.auto.DommagesAuto;



/**
 * @author Moncoutier Christophe
 */

@Embeddable
public class DommagesAutoIncendieAccident extends DommagesAuto{
    
    @Column(name = "DOM_MATERIEL")
    private Boolean autresDommagesMateriels;
    
    @Column(name = "DOM_MATERIEL_DESCRIPTION")
    private String descriptionDommagesMateriels;
    
    @Column(name = "DOM_AUTRES_PERSONNES")
    private String autresPersonnes;
    
    @Column(name = "DOM_CORPOREL")
    private Boolean dommagesCorporel;

    
    public Boolean getAutresDommagesMateriels() {
        return autresDommagesMateriels;
    }

    public void setAutresDommagesMateriels(Boolean autresDommagesMateriels) {
        this.autresDommagesMateriels = autresDommagesMateriels;
    }

    public String getDescriptionDommagesMateriels() {
        return descriptionDommagesMateriels;
    }

    public void setDescriptionDommagesMateriels(String descriptionDommagesMateriels) {
        this.descriptionDommagesMateriels = descriptionDommagesMateriels;
    }

    public String getAutresPersonnes() {
        return autresPersonnes;
    }

    public void setAutresPersonnes(String autresPersonnes) {
        this.autresPersonnes = autresPersonnes;
    }

    public Boolean getDommagesCorporel() {
        return dommagesCorporel;
    }

    public void setDommagesCorporel(Boolean dommagesCorporel) {
        this.dommagesCorporel = dommagesCorporel;
    }
}
