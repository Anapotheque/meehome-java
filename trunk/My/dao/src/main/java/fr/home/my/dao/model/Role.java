package fr.home.my.dao.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role", catalog = "my")
public class Role extends AbstractTO implements java.io.Serializable {

	private int idRole;
	private String libRole;
	private Set<Utilisateur> utilisateurs = new HashSet<Utilisateur>(0);

	public Role() {
	}

	public Role(int idRole, String libRole) {
		this.idRole = idRole;
		this.libRole = libRole;
	}

	public Role(int idRole, String libRole, Set<Utilisateur> utilisateurs) {
		this.idRole = idRole;
		this.libRole = libRole;
		this.utilisateurs = utilisateurs;
	}

	@Id
	@Column(name = "id_role", unique = true, nullable = false)
	public int getIdRole() {
		return this.idRole;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}

	@Column(name = "lib_role", nullable = false, length = 20)
	public String getLibRole() {
		return this.libRole;
	}

	public void setLibRole(String libRole) {
		this.libRole = libRole;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
	public Set<Utilisateur> getUtilisateurs() {
		return this.utilisateurs;
	}

	public void setUtilisateurs(Set<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

}
