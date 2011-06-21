package fr.alliance.docalliance.dao;

import java.util.List;

import org.hibernate.Criteria;

import fr.alliance.docalliance.model.AbstractTO;

public interface IGenericDao {

    /**
     * 
     * Ajouter bean.
     *
     * @author ETDW7
     * @param aBean Bean.
     * @throws BeanCreateException
     */
    void ajouter(AbstractTO aBean) throws DaoCreateException; 
    
    /**
     * 
     * Modifier Bean.
     *
     * @author ETDW7
     * @param aBean Bean.
     * @throws BeanUpdateException
     */
    void modifier(AbstractTO aBean) throws DaoUpdateException;
    
    /**
     * 
     * Supprimer Bean.
     *
     * @author ETDW7
     * @param aBean Bean.
     * @throws BeanDeleteException
     */
    void supprimer(AbstractTO aBean) throws DaoDeleteException;
    
    /**
     * 
     * Rechercher liste de bean. 
     *
     * @author ETDW7
     * @param aQueryString Chaine SQL de recherches.
     * @return Liste de Bean.
     * @throws BeanFindException
     */
    List rechercher(String aQueryString) throws DaoFindException;
    
    /**
     * 
     * Rechercher liste de bean.  
     *
     * @author ETDW7
     * @param aCriteria Critères de recherches.
     * @return Liste de Bean.
     * @throws BeanFindException
     */
    List rechercher(Criteria aCriteria) throws DaoFindException;
}
