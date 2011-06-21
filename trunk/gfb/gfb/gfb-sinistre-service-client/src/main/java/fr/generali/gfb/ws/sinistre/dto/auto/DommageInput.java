package fr.generali.gfb.ws.sinistre.dto.auto;

public class DommageInput {
    private String description;

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @param depotGarage the depotGarage to set
     */
    public void setDepotGarage(Boolean depotGarage) {
        this.depotGarage = depotGarage;
    }

    /**
     * @param coordonnesGarage the coordonnesGarage to set
     */
    public void setCoordonnesGarage(String coordonnesGarage) {
        this.coordonnesGarage = coordonnesGarage;
    }

    /**
     * @param lieuVehicule the lieuVehicule to set
     */
    public void setLieuVehicule(String lieuVehicule) {
        this.lieuVehicule = lieuVehicule;
    }

    private Boolean depotGarage;

    private String coordonnesGarage;

    private String lieuVehicule;

    public String getDescription() {
        return description;
    }

    public Boolean getDepotGarage() {
        return depotGarage;
    }

    public String getCoordonnesGarage() {
        return coordonnesGarage;
    }

    public String getLieuVehicule() {
        return lieuVehicule;
    }

    public DommageInput description(String description) {
        this.description = description;
        return this;
    }

    public DommageInput depotGarage(Boolean depotGarage) {
        this.depotGarage = depotGarage;
        return this;
    }

    public DommageInput coordonnesGarage(String coordonnesGarage) {
        this.coordonnesGarage = coordonnesGarage;
        return this;
    }

    public DommageInput lieuVehicule(String lieuVehicule) {
        this.lieuVehicule = lieuVehicule;
        return this;
    }

}
