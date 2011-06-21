package fr.home.my.dao;

import fr.home.my.dao.model.AbstractTO;
import fr.home.my.dao.util.DaoCreateException;
import fr.home.my.dao.util.DaoDeleteException;
import fr.home.my.dao.util.DaoFindException;
import fr.home.my.dao.util.DaoUpdateException;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;

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
    List<AbstractTO> rechercher(String aQueryString) throws DaoFindException;
    
    /**
     * 
     * Rechercher liste de bean.  
     *
     * @author ETDW7
     * @param aCriteria Crit�res de recherches.
     * @return Liste de Bean.
     * @throws BeanFindException
     */
    List<AbstractTO> rechercher(Criteria aCriteria) throws DaoFindException;
    
    /**
     * @param filtre un hasmap contenant les param�tres de la requ�te de type couple clef/valeur
     * @param criteria le criteria pour lequel les crit�res vont �tre ajouter
     * @return le criteria contenant la totalit� du filtre
     */
    public Criteria makeCriteria(HashMap<String, Object> filtre, Class classe);
}
