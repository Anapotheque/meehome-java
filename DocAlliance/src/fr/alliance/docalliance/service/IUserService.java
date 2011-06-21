package fr.alliance.docalliance.service;

import fr.alliance.docalliance.dao.DaoCreateException;
import fr.alliance.docalliance.dao.DaoDeleteException;
import fr.alliance.docalliance.model.AdresseMail;
import java.util.HashMap;
import java.util.List;

import fr.alliance.docalliance.dao.DaoFindException;
import fr.alliance.docalliance.model.User;

public interface IUserService {
	
    static final String USER_SERVICE = "userService";

    List getUsers() throws DaoFindException;
    boolean isAuthorized(String login, String pass) throws DaoFindException;
    void saveOneUser(User user)  throws DaoCreateException;
    void deleteOneUser(User user)throws DaoDeleteException ;
    User getUserById(int idUser) throws DaoFindException;
    User getUserByLogin(String login) throws DaoFindException;
    User getUserByAdresseMail(AdresseMail adresseMail) throws DaoFindException;
}
