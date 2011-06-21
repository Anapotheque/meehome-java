package fr.home.my.services.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import fr.home.my.dao.IRoleDao;
import fr.home.my.dao.model.Role;
import fr.home.my.dao.util.DaoFindException;
import fr.home.my.services.IRoleService;

public class RoleService extends AbstractService implements IRoleService {

	@Override
	public List<Role> getRoleParLibelle(String libelle) throws DaoFindException {
		HashMap<String, Object> filter = new HashMap<String, Object>();
	    filter.put("libRole", libelle);
	    return ((IRoleDao) dao).getRoleByFilters(filter);
	}

	@Override
	public Role getRoleAdministrateur() throws DaoFindException {
		return getRoleParLibelle("administrateur").iterator().next();
	}

	@Override
	public Role getRoleUtilisateur() throws DaoFindException {
		return getRoleParLibelle("utilisateur").iterator().next();
	}

}
