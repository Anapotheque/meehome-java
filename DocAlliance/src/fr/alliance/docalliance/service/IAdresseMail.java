package fr.alliance.docalliance.service;

import fr.alliance.docalliance.dao.DaoCreateException;
import fr.alliance.docalliance.dao.DaoDeleteException;
import java.util.HashMap;
import java.util.List;

import fr.alliance.docalliance.dao.DaoFindException;
import fr.alliance.docalliance.model.AdresseMail;

/**
 * @author Yves Le Rumeur, Romain Raballand
 *
 */
public interface IAdresseMail {
    
    /**
     * @return list toutes les adresses mails (objet {@link AdresseMail})
     * @throws DaoFindException
     */
    List getAdresseMails() throws DaoFindException;
    
    
    /**
     * @param c le charactère par lequel doit commencer le nom du posséseur de {@link AdresseMail}
     * @return la liste 
     * @throws DaoFindException
     */
    List getAdresseMailsByLetter(char c) throws DaoFindException;
    List getAdresseMailByFilters(HashMap<String, Object> filtre) throws DaoFindException;
    AdresseMail getAdresseMailById(int idAdr) throws DaoFindException;
    AdresseMail getAdresseMailByNomPrenom(String nom,String prenom)throws DaoFindException;
    void saveOneAdresseMail(AdresseMail adresseMail) throws DaoCreateException;
    void deleteOneAdresseMail(AdresseMail adresseMail) throws DaoDeleteException;
    
}
