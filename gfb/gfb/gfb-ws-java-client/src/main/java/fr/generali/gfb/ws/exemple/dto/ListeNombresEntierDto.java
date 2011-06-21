package fr.generali.gfb.ws.exemple.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Structure d'échange pour les données de type ListeNombresEntierVo
 * 
 * @author Stéphane Bouclier <sbouclier@generali.fr>
 */
public class ListeNombresEntierDto {

    private List<Integer> listNombresEntier = new ArrayList<Integer>();

    public List<Integer> getListNombresEntier() {
        return listNombresEntier;
    }

    public void setListNombresEntier(List<Integer> listNombresEntier) {
        this.listNombresEntier = listNombresEntier;
    }
}
