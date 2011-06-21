/**
 * Groupe Generali - Tous droits réservés &copy;
 */
package fr.alliance.docalliance.dao;

import fr.alliance.docalliance.util.TechnicalException;


/**
 * Exception levée sur la tentative de creation d'un objet.
 *
 * @author ETDW7
 */
public class DaoCreateException extends TechnicalException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6345099717343658286L;

	/**
     * Constructeur.
     *
     * @author ETDW7
     */
    public DaoCreateException() {
        super();
    }
    
    /**
     * Constructeur.
     *
     * @author ETDW7
     * @param aMessage Message.
     */
    public DaoCreateException(String aMessage) {
        super(aMessage);
    }
}
