package fr.alliance.docalliance.dao.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import fr.alliance.docalliance.dao.DaoFindException;
import fr.alliance.docalliance.dao.IAdresseMailDao;
import fr.alliance.docalliance.dao.IDestinataireDao;
import fr.alliance.docalliance.dao.IMailDao;
import fr.alliance.docalliance.dao.IPdfDao;
import fr.alliance.docalliance.dao.IUserDao;
import fr.alliance.docalliance.model.AbstractTO;
import fr.alliance.docalliance.model.AdresseMail;
import fr.alliance.docalliance.model.Destinataire;
import fr.alliance.docalliance.model.Mail;
import fr.alliance.docalliance.model.Pdf;
import fr.alliance.docalliance.model.User;

/**
 * @author Yves Le Rumeur, Romain Raballand
 *
 */
public class GlobalDao extends GenericDAO implements IPdfDao, IMailDao,
        IDestinataireDao, IUserDao, IAdresseMailDao {
    
    public List getPdfsByFilter(HashMap<String, Object> filter) throws DaoFindException {
        Criteria criteriaPdf = this.makeCriteria(filter, Pdf.class);
        criteriaPdf.addOrder(Order.desc("nom"));
        ArrayList<AbstractTO> listPdf;
        listPdf = (ArrayList<AbstractTO>) rechercher(criteriaPdf);
        return returnResult(listPdf);
    }
    
    public List getHistoriqueMail() throws DaoFindException {
        
        Criteria criteriaMail = getSession().createCriteria(Mail.class);
        List list;
        list = rechercher(criteriaMail);
        
        list = returnResult(list);
        
        Iterator iteMail = list.iterator();
        while (iteMail.hasNext()) {
            
            Mail mail = (Mail) iteMail.next();
            
            Hibernate.initialize(mail.getAdresseMail());
            Hibernate.initialize(mail.getPdf());
            
            Iterator iteDest = mail.getDestinataires().iterator();
            
            while (iteDest.hasNext()) {
                Destinataire destinataire = (Destinataire) iteDest.next();
                Hibernate.initialize(destinataire.getAdresseMail());
            }
            Hibernate.initialize(mail.getDestinataires());
        }
        return list;
    }
    
    public List getUsersByFilters(HashMap<String, Object> filter) throws DaoFindException {
        Criteria criteriaUser = this.makeCriteria(filter, User.class);
        criteriaUser.addOrder(Order.desc("login"));
        ArrayList<User> listUser;
        try{
            listUser= (ArrayList<User>) rechercher(criteriaUser);
        } catch(DaoFindException e){
            throw e;
        }
        
        listUser = (ArrayList<User>)returnResult(listUser);
        
        Iterator<User> it = listUser.iterator();
        while(it.hasNext()){
            User user = it.next();
            Hibernate.initialize(user.getAdresseMail());
        }
        return listUser;
    }
    
    public List getAdresseMailByFilters(HashMap<String, Object> filtre) throws DaoFindException {
        Criteria criteriaAdresseMail = this.makeCriteria(filtre, AdresseMail.class);
        criteriaAdresseMail.addOrder(Order.asc("firstName"));
        ArrayList<AdresseMail> listAdresseMail ;
        listAdresseMail = (ArrayList<AdresseMail>) rechercher(criteriaAdresseMail);
        return returnResult(listAdresseMail);
    }
    
    private List returnResult(List list){
        if (list == null || list.isEmpty()) {
            return new ArrayList<AbstractTO>();
        }else{
            return list;
        }
    }
    
    /**
     * @param filtre un hasmap contenant les paramètres de la requête de type couple clef/valeur
     * @param criteria le criteria pour lequel les critères vont être ajouter
     * @return le criteria contenant la totalité du filtre
     */
    private Criteria makeCriteria(HashMap<String, Object> filtre, Class classe){
        Criteria criteria = getSession().createCriteria(classe);
        if(filtre != null && !filtre.isEmpty()){
            Iterator itr = filtre.keySet().iterator();
            while(itr.hasNext()){
                String key = (String) itr.next();
                Object value = filtre.get(key);
                if (value.getClass() == String.class) {
                	if (value.equals("adresseMail")) {
                		criteria.add(Restrictions.ilike(key, "%"+value+"%"));
                	}
                	else {
                		criteria.add(Restrictions.ilike(key, value+"%"));
                	}
                } else {
                    //parcours de la liste des arguments de l'objet pour lequel la requete est généré
                    Field fields[] = classe.getDeclaredFields();
                    for(int i = 0;i<fields.length;i++){
                        //permet de se passer d'exigence sur les majuscules manipuler ailleurs
                        if(key.equalsIgnoreCase(fields[i].getName())){
                            if(fields[i].getName().substring(0,2).equalsIgnoreCase("id")){
                                criteria.add(Restrictions.idEq(value));
                            } else{
                                criteria.add(Restrictions.eq(fields[i].getName(), value));
                            }
                            break;
                        }
                    }
                }
            }
        }
        return criteria;
    }
    
    public List getUsersByAdresseMail(HashMap<String, Object> filter) throws DaoFindException {
        Criteria criteriaUser = this.makeCriteria(filter, User.class);
        ArrayList<User> listUser = (ArrayList<User>) rechercher(criteriaUser);
        return returnResult(listUser);
    }
    
    
    
}
