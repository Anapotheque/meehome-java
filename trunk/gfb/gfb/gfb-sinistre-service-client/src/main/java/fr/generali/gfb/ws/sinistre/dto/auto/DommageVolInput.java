package fr.generali.gfb.ws.sinistre.dto.auto;

public class DommageVolInput extends DommageInput {
    /**
     * @param effetsPersonnels the effetsPersonnels to set
     */
    public void setEffetsPersonnels(String effetsPersonnels) {
        this.effetsPersonnels = effetsPersonnels;
    }

    private String effetsPersonnels;

    public String getEffetsPersonnels() {
        return effetsPersonnels;
    }

    public DommageVolInput effetsPersonnels(String effetsPersonnels) {
        this.effetsPersonnels = effetsPersonnels;
        return this;
    }

    @Override
    public DommageVolInput coordonnesGarage(String coordonnesGarage) {
        return (DommageVolInput ) super.coordonnesGarage(coordonnesGarage);
    }

    @Override
    public DommageVolInput depotGarage(Boolean depotGarage) {
        return (DommageVolInput ) super.depotGarage(depotGarage);
    }

    @Override
    public DommageVolInput description(String description) {
        return (DommageVolInput ) super.description(description);
    }

    @Override
    public DommageVolInput lieuVehicule(String lieuVehicule) {
        return (DommageVolInput ) super.lieuVehicule(lieuVehicule);
    }

}
