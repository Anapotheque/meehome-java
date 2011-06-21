package fr.alliance.docalliance.dao;

import java.util.List;

public interface IMailDao extends IGenericDao {
	
    static final String MAIL_DAO = "MailDAO";
    
    /**
     * Récupère l'historique des mails
     * @return List list de mail (pour afficher l'historique)
     * @throws DaoFindException 
     */
    List getHistoriqueMail() throws DaoFindException;
}
