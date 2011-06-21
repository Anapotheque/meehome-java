/**
 * Groupe Alliance Concept - Tous droits réservés &copy;
 */
package fr.alliance.docalliance.dao;

import fr.alliance.docalliance.util.TechnicalException;


/**
 * Exception levée sur la tentative de modification d'un objet.
 *
 * @author Yves Le Rumeur, Romain Raballand
 */
public class DaoUpdateException extends TechnicalException {
    
    /**
     *
     */
    private static final long serialVersionUID = 93167754797854035L;
    
    /**
     * Constructeur.
     *
     * @author ETDW7
     */
    public DaoUpdateException() {
        super();
    }
    
    /**
     * Constructeur.
     *
     * @author ETDW7
     * @param aMessage Message.
     */
    public DaoUpdateException(String aMessage) {
        super(aMessage);
    }
}
