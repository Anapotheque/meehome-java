package fr.generali.gfb.ws.sinistre.dto.auto;

public class DommageAccidentIncendieInput extends DommageInput {
    private Boolean autresDommagesMateriels;

    /**
     * @param autresDommagesMateriels the autresDommagesMateriels to set
     */
    public void setAutresDommagesMateriels(Boolean autresDommagesMateriels) {
        this.autresDommagesMateriels = autresDommagesMateriels;
    }

    /**
     * @param descriptionDommagesMateriels the descriptionDommagesMateriels to set
     */
    public void setDescriptionDommagesMateriels(String descriptionDommagesMateriels) {
        this.descriptionDommagesMateriels = descriptionDommagesMateriels;
    }

    /**
     * @param autresPersonnes the autresPersonnes to set
     */
    public void setAutresPersonnes(String autresPersonnes) {
        this.autresPersonnes = autresPersonnes;
    }

    /**
     * @param dommagesCorporel the dommagesCorporel to set
     */
    public void setDommagesCorporel(Boolean dommagesCorporel) {
        this.dommagesCorporel = dommagesCorporel;
    }

    private String descriptionDommagesMateriels;

    private String autresPersonnes;

    private Boolean dommagesCorporel;

    public Boolean getAutresDommagesMateriels() {
        return autresDommagesMateriels;
    }

    public String getDescriptionDommagesMateriels() {
        return descriptionDommagesMateriels;
    }

    public String getAutresPersonnes() {
        return autresPersonnes;
    }

    public Boolean getDommagesCorporel() {
        return dommagesCorporel;
    }

    public DommageAccidentIncendieInput autresDommagesMateriels(Boolean autresDommagesMateriels) {
        this.autresDommagesMateriels = autresDommagesMateriels;
        return this;
    }

    public DommageAccidentIncendieInput descriptionDommagesMateriels(String descriptionDommagesMateriels) {
        this.descriptionDommagesMateriels = descriptionDommagesMateriels;
        return this;
    }

    public DommageAccidentIncendieInput autresPersonnes(String autresPersonnes) {
        this.autresPersonnes = autresPersonnes;
        return this;
    }

    public DommageAccidentIncendieInput dommagesCorporel(Boolean dommagesCorporel) {
        this.dommagesCorporel = dommagesCorporel;
        return this;
    }

    @Override
    public DommageAccidentIncendieInput coordonnesGarage(String coordonnesGarage) {
        return (DommageAccidentIncendieInput ) super.coordonnesGarage(coordonnesGarage);
    }

    @Override
    public DommageAccidentIncendieInput depotGarage(Boolean depotGarage) {
        return (DommageAccidentIncendieInput ) super.depotGarage(depotGarage);
    }

    @Override
    public DommageAccidentIncendieInput description(String description) {
        return (DommageAccidentIncendieInput ) super.description(description);
    }

    @Override
    public DommageAccidentIncendieInput lieuVehicule(String lieuVehicule) {
        return (DommageAccidentIncendieInput ) super.lieuVehicule(lieuVehicule);
    }
    
    

}
