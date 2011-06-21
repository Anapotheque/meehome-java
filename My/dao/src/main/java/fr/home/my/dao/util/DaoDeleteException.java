package fr.home.my.dao.util;




/**
 * Exception levée sur la tentative de creation d'un objet.
 *
 * @author ETDW7
 */
public class DaoDeleteException extends TechnicalException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8147955468647218989L;

	/**
     * Constructeur.
     *
     * @author ETDW7
     */
    public DaoDeleteException() {
        super();
    }
    
    /**
     * Constructeur.
     *
     * @author ETDW7
     * @param aMessage Message.
     */
    public DaoDeleteException(String aMessage) {
        super(aMessage);
    }
}
