package fr.home.my.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import fr.home.my.dao.IUtilisateurDao;
import fr.home.my.dao.model.AbstractTO;
import fr.home.my.dao.model.Utilisateur;
import fr.home.my.dao.util.DaoCreateException;
import fr.home.my.dao.util.DaoDeleteException;
import fr.home.my.dao.util.DaoFindException;

public class UtilisateurDAO extends GenericDAO implements IUtilisateurDao{

	@Override
	public List<Utilisateur> getUsersByFilters(HashMap<String, Object> filter) throws DaoFindException {
		
//		if (filter == null )
//			log.debug("Recuperation de tous les utilisateurs");
//		else 
//			log.debug("Recuperation de l'utilisateur " + filter.get("nomUtilisateur") + ", mot de passe : " + filter.get("passwordUtilisateur"));
		
		Criteria criteriaUtilisateur = this.makeCriteria(filter, Utilisateur.class);
		criteriaUtilisateur.addOrder(Order.desc("nomUtilisateur"));
        ArrayList<Utilisateur> listUtilisateur = (ArrayList<Utilisateur>) rechercher(criteriaUtilisateur);
        return listUtilisateur;
	}
	
//	@Override
//	public void ajouter(AbstractTO aBean) throws DaoCreateException {
//		Utilisateur utilisateur = (Utilisateur)aBean;
//		log.debug("ajout d'un utilisateur : " + utilisateur.getNomUtilisateur());
//		super.ajouter(aBean);
//	}
//
//	@Override
//	public void supprimer(AbstractTO aBean) throws DaoDeleteException {
//		Utilisateur utilisateur = (Utilisateur)aBean;
//		log.debug("suppression d'un utilisateur : " + utilisateur.getNomUtilisateur());
//		super.supprimer(aBean);
//	}
}
