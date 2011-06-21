package fr.alliance.docalliance.service;

import fr.alliance.docalliance.dao.DaoCreateException;
import fr.alliance.docalliance.dao.DaoDeleteException;
import java.util.List;

import fr.alliance.docalliance.dao.DaoFindException;
import fr.alliance.docalliance.model.Mail;

public interface IHistoriqueService {
    
    static final String HISTORIQUE_SERVICE = "historiqueService";
    
    List getMailHistorique() throws DaoFindException;
    void saveOneMail(Mail mail) throws DaoCreateException;
    void deleteOneMail(Mail mail)throws DaoDeleteException;
}
