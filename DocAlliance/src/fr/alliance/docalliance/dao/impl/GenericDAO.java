package fr.alliance.docalliance.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.QueryException;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import fr.alliance.docalliance.dao.DaoCreateException;
import fr.alliance.docalliance.dao.DaoDeleteException;
import fr.alliance.docalliance.dao.DaoFindException;
import fr.alliance.docalliance.dao.DaoUpdateException;
import fr.alliance.docalliance.dao.IGenericDao;
import fr.alliance.docalliance.model.AbstractTO;

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
}