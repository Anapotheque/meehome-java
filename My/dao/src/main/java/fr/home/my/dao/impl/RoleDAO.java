package fr.home.my.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import fr.home.my.dao.IRoleDao;
import fr.home.my.dao.model.Role;
import fr.home.my.dao.util.DaoFindException;

public class RoleDAO extends GenericDAO implements IRoleDao {

	@Override
	public List<Role> getRoleByFilters(HashMap<String, Object> filter) throws DaoFindException {

//		if (filter == null )
//			log.debug("Recuperation de tous les roles");
//		else 
//			log.debug("Recuperation du role " + filter.get("libRole"));

		Criteria criteriaRole = this.makeCriteria(filter, Role.class);
		criteriaRole.addOrder(Order.desc("libRole"));
        ArrayList<Role> listRole = (ArrayList<Role>) rechercher(criteriaRole);
        return listRole;
	}
	
//	@Override
//	public void ajouter(AbstractTO aBean) throws DaoCreateException {
//		Role role = (Role)aBean;
//		log.debug("ajout d'un role : " + role.getLibRole());
//		super.ajouter(aBean);
//	}
//
//	@Override
//	public void supprimer(AbstractTO aBean) throws DaoDeleteException {
//		Role role = (Role)aBean;
//		log.debug("suppression d'un role : " + role.getLibRole());
//		super.supprimer(aBean);
//	}
}
