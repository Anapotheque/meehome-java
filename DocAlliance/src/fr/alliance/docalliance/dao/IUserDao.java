package fr.alliance.docalliance.dao;

import java.util.HashMap;
import java.util.List;

/**
 * @author Yves Le Rumeur, Romain Raballand
 *
 */
public interface IUserDao extends IGenericDao {
    
    static final String USER_DAO = "UserDAO";
    
    /**
     * Recuperation de la liste des user
     * @param filter hashmap qui contient les arguments de la requêtes de selection sous forme de couple clef/valeur
     * @return liste d'user répondant aux paramètres passer dans l'argument
     * @throws DaoFindException
     */
    List getUsersByFilters(HashMap<String, Object> filter) throws DaoFindException;
    List getUsersByAdresseMail(HashMap<String, Object> filter) throws DaoFindException;
}

