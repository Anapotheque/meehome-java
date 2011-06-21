package fr.home.my.dao;

import java.util.HashMap;
import java.util.List;

import fr.home.my.dao.model.AbstractTO;
import fr.home.my.dao.model.Utilisateur;
import fr.home.my.dao.util.DaoFindException;

public interface IUtilisateurDao extends IGenericDao {

    static final String UTILISATEUR_DAO = "utilisateurDAO";
	
    List<Utilisateur> getUsersByFilters(HashMap<String, Object> filter) throws DaoFindException;
}
