package fr.alliance.docalliance.dao;

import java.util.HashMap;
import java.util.List;

public interface IAdresseMailDao extends IGenericDao {
    
    static final String ADRESSE_MAIL_DAO = "AdresseMailDAO";
    
    /**
     * Liste toutes les adresses mails via un filtre
     * @param filtre filtre correspondant à la clause where
     * @return List liste des adresses mails correpondant au critère
     * @throws DaoFindException
     */
    List getAdresseMailByFilters(HashMap<String, Object> filtre) throws DaoFindException;
    
}
