package fr.generali.gfb.ws.exemple.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Structure d'�change pour les donn�es de type ListeNombresEntierVo
 * 
 * @author St�phane Bouclier <sbouclier@generali.fr>
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
