package fr.home.my.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "utilisateur", catalog = "my")
public class Utilisateur extends AbstractTO implements java.io.Serializable {

	private Integer idUtilisateur;
	private Role role;
	private String nomUtilisateur;
	private String passwordUtilisateur;

	public Utilisateur() {
	}

	public Utilisateur(Role role, String nomUtilisateur,
			String passwordUtilisateur) {
		this.role = role;
		this.nomUtilisateur = nomUtilisateur;
		this.passwordUtilisateur = passwordUtilisateur;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_utilisateur", unique = true, nullable = false)
	public Integer getIdUtilisateur() {
		return this.idUtilisateur;
	}

	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_role", nullable = false)
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Column(name = "nom_utilisateur", nullable = false, length = 20)
	public String getNomUtilisateur() {
		return this.nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	@Column(name = "password_utilisateur", nullable = false, length = 20)
	public String getPasswordUtilisateur() {
		return this.passwordUtilisateur;
	}

	public void setPasswordUtilisateur(String passwordUtilisateur) {
		this.passwordUtilisateur = passwordUtilisateur;
	}

}
