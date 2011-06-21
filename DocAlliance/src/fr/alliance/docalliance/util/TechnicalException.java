/**
 * Groupe Generali - Tous droits réservés &copy;
 */
package fr.alliance.docalliance.util;

/**
 * Exptions Technique.
 * @author EXTK9
 *
 * Pour changer le modèle de ce commentaire de type généré, allez à :
 * Fenêtre&gt;Préférences&gt;Java&gt;Génération de code&gt;Code et commentaires
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
