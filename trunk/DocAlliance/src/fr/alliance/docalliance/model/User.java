//Generated 12 mars 2007 23:20:24 by Hibernate Tools 3.2.0.b9
package fr.alliance.docalliance.model;

/**
 * @author Yves Le Rumeur, Romain Raballand
 *
 */
public class User extends AbstractTO {

	public final static Integer ADMNISTRATEUR = new Integer(0);
	public final static Integer UTILISATEUR = new Integer(1);

	/**
	 * 
	 */
	private static final long serialVersionUID = -5288346838973909912L;

	private int idUser;
	private AdresseMail adresseMail;
	private String login;
	private String pass;

	public User() {
		super();
	}

	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public AdresseMail getAdresseMail() {
		return adresseMail;
	}

	public void setAdresseMail(AdresseMail adresseMail) {
		this.adresseMail = adresseMail;
	}

	public int getRole(){
		return ADMNISTRATEUR;
	}
}