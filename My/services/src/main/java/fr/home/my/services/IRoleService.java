package fr.home.my.services;

import java.util.List;

import fr.home.my.dao.model.Role;
import fr.home.my.dao.util.DaoFindException;

public interface IRoleService {

	static final String ROLE_SERVICE = "roleService";

    List<Role> getRoleParLibelle(String libelle) throws DaoFindException;
    Role getRoleAdministrateur() throws DaoFindException;
    Role getRoleUtilisateur() throws DaoFindException;
	
}
