/**
 * 
 */
package fr.generali.gfb.ws.sinistre.persistence.domain.auto;

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * @author Moncoutier Christophe
 */

@Embeddable
public class DommagesAuto {
   
    
    @Column(name = "DOM_DESCRIPTION")
    private String description;
    
    @Column(name = "DOM_DEPOT_GARAGE")
    private Boolean depotGarage;
    
    @Column(name = "DOM_COORD_GARAGE")
    private String coordonnesGarage;
    
    @Column(name = "DOM_LIEU_VEHICULE")
    private String lieuVehicule;
    
    

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDepotGarage() {
        return depotGarage;
    }

    public void setDepotGarage(Boolean depotGarage) {
        this.depotGarage = depotGarage;
    }

    public String getCoordonnesGarage() {
        return coordonnesGarage;
    }

    public void setCoordonnesGarage(String coordonnesGarage) {
        this.coordonnesGarage = coordonnesGarage;
    }

    public String getLieuVehicule() {
        return lieuVehicule;
    }

    public void setLieuVehicule(String lieuVehicule) {
        this.lieuVehicule = lieuVehicule;
    }
}
