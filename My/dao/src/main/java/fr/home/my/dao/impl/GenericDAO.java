package fr.home.my.dao.impl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.QueryException;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import fr.home.my.dao.IGenericDao;
import fr.home.my.dao.model.AbstractTO;
import fr.home.my.dao.util.DaoCreateException;
import fr.home.my.dao.util.DaoDeleteException;
import fr.home.my.dao.util.DaoFindException;
import fr.home.my.dao.util.DaoUpdateException;

public class GenericDAO extends HibernateDaoSupport implements IGenericDao {

    protected Log log = LogFactory.getFactory().getInstance(this.getClass());

    /** 
     * Voir le parent
     * 
     * @param aBean Bean
     * @throws BeanCreateException
     * @see fr.alliance.docalliance.dao.IGenericDao#ajouter(fr.generali.dop.to.AbstractTO)
     */
    public void ajouter(AbstractTO aBean) throws DaoCreateException {
        
        try {
            getHibernateTemplate().saveOrUpdate(aBean);
        } catch (DataAccessException e) {
            String message = getClass().getName() + ": Pb accès base en création " + e.getMessage();
            throw new DaoCreateException(message);
        }
    }	

    /** 
     * Voir le parent
     * 
     * @param aBean Bean
     * @throws BeanUpdateException
     * @see fr.alliance.docalliance.dao.IGenericDao#modifier(fr.generali.dop.to.AbstractTO)
     */
    public void modifier(AbstractTO aBean) throws DaoUpdateException {
        
        try {	
            getHibernateTemplate().saveOrUpdate(aBean);
        } catch (DataAccessException e) {
            String message = getClass().getName() + ": Pb accès base en modification " + e.getMessage();
            throw new DaoUpdateException(message);
        }
    }

    /** 
     * Voir le parent
     * 
     * @param aBean Bean
     * @throws BeanDeleteException
     * @see fr.alliance.docalliance.dao.IGenericDao#supprimer(fr.generali.dop.to.AbstractTO)
     */
    public void supprimer(AbstractTO aBean) throws DaoDeleteException {
        
        try {	
            getHibernateTemplate().delete(aBean);
        } catch (DataAccessException e) {
            String message = getClass().getName() + ": Pb accès base en suppression " + e.getMessage();
            throw new DaoDeleteException(message);
        }
    }
    
    /** 
     * Voir le parent
     * 
     * @param aQueryString Critères.
     * @return Liste des bean.
     * @throws BeanFindException
     * @see fr.alliance.docalliance.dao.IGenericDao#rechercher(java.lang.String)
     */
    public List rechercher(String aQueryString) throws DaoFindException {
        
        try {
            return getHibernateTemplate().find(aQueryString);
        } catch (DataAccessException e) {
            String message = "Pb accès base sur recherche " + e.getMessage();
            throw new DaoFindException(message);
        }
    }
    
    /** 
     * Voir le parent
     * 
     * @param aCriteria Critères.
     * @return Liste des bean.
     * @throws BeanFindException
     * @see fr.alliance.docalliance.dao.IGenericDao#rechercher(java.lang.String)
     */
    public List rechercher(Criteria aCriteria) throws DaoFindException {
        
        try {	
            return aCriteria.list(); 
        }        
        catch (QueryException e) {
            String message = getClass().getName() + ": Pb de syntaxe sur recherche " + e.getMessage();
            throw new DaoFindException(message);
        }
        catch (ClassCastException e) {
            String message = getClass().getName() + ": Pb de cast de class sur recherche " + e.getMessage();
            throw new DaoFindException(message);
        }
        catch (HibernateException e) {
            String message = getClass().getName() + ": Pb accès base sur recherche " + e.getMessage();
            throw new DaoFindException(message);
        }
    }
    
	/**
     * @param filtre un hasmap contenant les paramètres de la requête de type couple clef/valeur
     * @param criteria le criteria pour lequel les critères vont être ajouter
     * @return le criteria contenant la totalité du filtre
     */
    public Criteria makeCriteria(HashMap<String, Object> filtre, Class classe){
        Criteria criteria = getSession().createCriteria(classe);
        if(filtre != null && !filtre.isEmpty()){
            Iterator itr = filtre.keySet().iterator();
            while(itr.hasNext()){
                String key = (String) itr.next();
                Object value = filtre.get(key);
                if (value.getClass() == String.class) {
                	criteria.add(Restrictions.ilike(key, value+"%"));
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
}