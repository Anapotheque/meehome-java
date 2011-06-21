/**
 *
 */
package fr.alliance.docalliance.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import fr.alliance.docalliance.dao.DaoCreateException;
import fr.alliance.docalliance.dao.DaoDeleteException;
import fr.alliance.docalliance.dao.DaoFindException;
import fr.alliance.docalliance.dao.DaoUpdateException;
import fr.alliance.docalliance.dao.IAdresseMailDao;
import fr.alliance.docalliance.dao.IMailDao;
import fr.alliance.docalliance.dao.IPdfDao;
import fr.alliance.docalliance.dao.IUserDao;
import fr.alliance.docalliance.model.AbstractTO;
import fr.alliance.docalliance.model.AdresseMail;
import fr.alliance.docalliance.model.Destinataire;
import fr.alliance.docalliance.model.DestinataireId;
import fr.alliance.docalliance.model.Mail;
import fr.alliance.docalliance.model.Pdf;
import fr.alliance.docalliance.model.User;
import fr.alliance.docalliance.service.IGlobalService;

/**
 * @author ylr
 *
 */
public class GlobalService extends AbstractService implements IGlobalService {
    
    @Override
    public void finalize() throws Throwable {
        // TODO Auto-generated method stub
        super.finalize();
    }
    
        /* (non-Javadoc)
         * @see fr.alliance.docalliance.service.IUserService#isAuthorized(java.lang.String, java.lang.String)
         */
    public boolean isAuthorized(String login, String pass) throws DaoFindException {
        HashMap<String, Object> filter = new HashMap<String, Object>();
        filter.put("login", login);
        filter.put("pass", pass);
        ArrayList<User> listUser = null;
        listUser = (ArrayList<User>) ((IUserDao) dao).getUsersByFilters(filter);
        if(listUser != null && listUser.size() == 1){
            return true;
        }else{
            return false;
        }
    }
    
    public User getUserById(int idUser) throws DaoFindException {
        HashMap<String, Object> filter = new HashMap<String, Object>();
        filter.put("idUser", idUser);
        List usersByFilters = ((IUserDao) dao).getUsersByFilters(filter);
        return (User) this.listToObject(usersByFilters);
    }
    
    public User getUserByLogin(String login) throws DaoFindException {
        HashMap<String, Object> filter = new HashMap<String, Object>();
        filter.put("login", login);
        List usersByFilters = ((IUserDao) dao).getUsersByFilters(filter);
        return (User) this.listToObject(usersByFilters);
    }
    
    public List getUsers() throws DaoFindException {
        return ((IUserDao) dao).getUsersByFilters(null);
    }
    
        /* (non-Javadoc)
         * @see fr.alliance.docalliance.service.IPdfService#creerPdf(fr.alliance.docalliance.model.Pdf)
         */
    public void saveOnePdf(Pdf pdf) throws DaoCreateException {
        dao.ajouter(pdf);
    }
    
    public void deleteOnePdf(Pdf pdf) throws DaoDeleteException {
        dao.supprimer(pdf);
    }
    
    
        /* (non-Javadoc)
         * @see fr.alliance.docalliance.service.IPdfService#getPdfById(int)
         */
    public Pdf getPdfById(int idPdf) throws DaoFindException {
        HashMap<String, Object> filter = new HashMap<String, Object>();
        filter.put("idPdf",idPdf);
        List pdfsByFilter = ((IPdfDao) dao).getPdfsByFilter(filter);
        return (Pdf) this.listToObject(pdfsByFilter);
    }
    
        /* @param typePdf the int wich represent the kind of document must be return
         * @throws DaoFindException
         * @return the list of pdf wich have the parameter typePdf in a field section
         * @see fr.alliance.docalliance.service.IPdfService#getPdfsByType(java.lang.Integer)
         */
    public List getPdfsByType(Integer typePdf) throws DaoFindException {
        HashMap<String, Object> filter = new HashMap<String, Object>();
        filter.put("Section",typePdf);
        return ((IPdfDao) dao).getPdfsByFilter(filter);
    }
    
        /* @param pdf the pdf object wich will be modified in his persistence (database)
         * @throws DaoUpdateException
         * @see fr.alliance.docalliance.service.IPdfService#modifierPdf(fr.alliance.docalliance.model.Pdf)
         */
    public void modifierPdf(Pdf pdf) throws DaoUpdateException {
        dao.modifier(pdf);
    }
    
        /* (non-Javadoc)
         * @see fr.alliance.docalliance.service.IHistoriqueService#getMailHistorique()
         */
    public List getMailHistorique() throws DaoFindException {
        return ((IMailDao) dao).getHistoriqueMail();
    }
    
    public void deleteOneUser(User user) throws DaoDeleteException {
        dao.supprimer(user.getAdresseMail());
        dao.supprimer(user);
    }
    
    public void saveOneUser(User user)  throws DaoCreateException {
        dao.ajouter(user.getAdresseMail());
        dao.ajouter(user);
    }
    
    public void saveOneMail(Mail mail) throws DaoCreateException{
        
        //1 save/update des adresses mails des destinataires
        HashSet<AdresseMail> listDest = (HashSet<AdresseMail>) mail.getDestinataires();
        Iterator<AdresseMail> it = listDest.iterator();
        AdresseMail adresseMail = null;
        while(it.hasNext()){
            adresseMail = it.next();
            dao.ajouter(adresseMail);
        }
        
        //2 remove de la liste des dest de l'objet
        //puis sauvegarde de l'objet mail
        HashSet<Destinataire> listVide = new HashSet<Destinataire>();
        mail.setDestinataires(listVide);
        dao.ajouter(mail);
        
        //3 Constitution de la liste des destinataires avec l'id du mail
        it = listDest.iterator();
        HashSet<Destinataire>  newListDest = new HashSet<Destinataire>(listDest.size());
        adresseMail = null;
        Destinataire dest = null;
        DestinataireId destId = null;
        while(it.hasNext()){
            adresseMail = it.next();
            dest = new Destinataire();
            destId = new DestinataireId();
            destId.setIdAdresseMail(adresseMail.getIdAdresseMail());
            destId.setIdMail(mail.getIdMail());
            dest.setId(destId);
            dest.setAdresseMail(adresseMail);
            dest.setMail(mail);
            dao.ajouter(dest);
            newListDest.add(dest);
        }
        
        //4 mets la liste des desintaires dans l'objet mail et resauvegarde l'objet
        mail.setDestinataires(newListDest);
        dao.ajouter(mail);
    }
    
    public void deleteOneMail(Mail mail) throws DaoDeleteException{
        dao.supprimer(mail);
    }
    
    private AbstractTO listToObject(List<AbstractTO> list){
        if(list == null || list.isEmpty()){
            return null;
        } else{
            return list.get(0);
        }
    }
    
    public AdresseMail getAdresseMailById(int idAdr) throws DaoFindException {
        HashMap<String, Object> filter = new HashMap<String, Object>();
        List<AbstractTO> list = null;
        filter.put("idAdresseMail", idAdr);
        list = (List<AbstractTO>) ((IAdresseMailDao) dao).getAdresseMailByFilters(filter);
        return (AdresseMail) listToObject(list);
    }
    
    public List getAdresseMails() throws DaoFindException {
        return ((IAdresseMailDao) dao).getAdresseMailByFilters(null);
    }
    
    public AdresseMail getAdresseMailByNomPrenom(String nom, String prenom) throws DaoFindException {
        HashMap<String, Object> filter = new HashMap<String, Object>();
        filter.put("firstName", nom);
        filter.put("lastName", prenom);
        List<AbstractTO> listAdresseMail = (List<AbstractTO>) ((IAdresseMailDao) dao).getAdresseMailByFilters(filter);
        return (AdresseMail) listToObject(listAdresseMail);
    }
    
    public List getAdresseMailByFilters(HashMap<String, Object> filtre) throws DaoFindException {
        return ((IAdresseMailDao) dao).getAdresseMailByFilters(filtre);
    }
    
    
    public List getAdresseMailsByLetter(char c) throws DaoFindException {
        HashMap<String, Object> filter = new HashMap<String, Object>();
        //convertion du caractère en string
        char[] tabChar = new char[1];
        tabChar[0]=c;
        filter.put("firstName", new String(tabChar));
        return ((IAdresseMailDao) dao).getAdresseMailByFilters(filter) ;
    }
    
    public void saveOneAdresseMail(AdresseMail adresseMail) throws DaoCreateException{
        dao.ajouter(adresseMail);
    }
    
    public void deleteOneAdresseMail(AdresseMail adresseMail)throws DaoDeleteException {
        dao.supprimer(adresseMail);
    }
    
    public User getUserByAdresseMail(AdresseMail adresseMail) throws DaoFindException {
        HashMap<String, Object> filter = new HashMap<String, Object>();
        filter.put("AdresseMail", adresseMail);
        List<AbstractTO> list = null;
        list = (List<AbstractTO>)((IUserDao) dao).getUsersByAdresseMail(filter);
        return (User) listToObject(list);
    }
}
