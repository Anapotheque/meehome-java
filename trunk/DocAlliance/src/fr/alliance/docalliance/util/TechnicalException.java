/**
 * Groupe Generali - Tous droits r�serv�s &copy;
 */
package fr.alliance.docalliance.util;

/**
 * Exptions Technique.
 * @author EXTK9
 *
 * Pour changer le mod�le de ce commentaire de type g�n�r�, allez � :
 * Fen�tre&gt;Pr�f�rences&gt;Java&gt;G�n�ration de code&gt;Code et commentaires
 */
public class TechnicalException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5352441979004071869L;

	/**
	 * Constructeur.
	 */
	public TechnicalException() {
		super();
	}
    
	/**
	 * Constructeur.
	 * @param aMessage Message.
	 */
	public TechnicalException(String aMessage) {
		super(aMessage);
	}
}
