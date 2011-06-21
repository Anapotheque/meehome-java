/**
 * Generali Solutions d'assurances - Tous droits réservés &copy; 2007 - 2010
 */
package fr.generali.gfb.services.exemple.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Business object contenant la liste des entiers à calculer
 */
public class ListeNombresEntier {

    private List<Integer> listNombresEntier = new ArrayList<Integer>();

    public List<Integer> getListNombresEntier() {
        return listNombresEntier;
    }

    public void setListNombresEntier(List<Integer> listNombresEntier) {
        this.listNombresEntier = listNombresEntier;
    }
}