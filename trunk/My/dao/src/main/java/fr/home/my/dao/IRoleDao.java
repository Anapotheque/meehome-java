package fr.home.my.dao;

import java.util.HashMap;
import java.util.List;

import fr.home.my.dao.model.Role;
import fr.home.my.dao.util.DaoFindException;

public interface IRoleDao extends IGenericDao {

	List<Role> getRoleByFilters(HashMap<String, Object> filter) throws DaoFindException;
	
}
