/**
 * Generali Solutions d'assurances - Tous droits réservés &copy; 2007 - 2010
 */
package fr.generali.gfb.services.exemple;

import fr.generali.gfb.services.exemple.domain.ListeNombresEntier;

/**
 * Interface d'exemple d'un service qui permet de faire des calculs sur une
 * liste d'entier contenu dans une classe ListeNombresEntierVo
 */
public interface ICalculService {

    /**
     * @param nb
     * @return le résultat de l'addition
     */
    public Integer additionner(ListeNombresEntier nb);

    /**
     * @param nb
     * @return le résultat de la multiplication
     */
    public Integer multiplier(ListeNombresEntier nb);
}
