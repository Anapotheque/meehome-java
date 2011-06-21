/**
 * Groupe Generali - Tous droits réservés &copy;
 */
package fr.alliance.docalliance.dao;

import fr.alliance.docalliance.util.TechnicalException;


/**
 * Exception levée sur un select.
 *
 * @author ETDW7
 */
public class DaoFindException extends TechnicalException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 9157545176575810533L;

	/**
     * Constructeur.
     *
     * @author ETDW7
     */
    public DaoFindException() {
        super();
    }
    
        /**
     * Constructeur.
     *
     * @author ETDW7
     * @param aMessage Message.
     */
    public DaoFindException(String aMessage) {
        super(aMessage);
    }
    
}
