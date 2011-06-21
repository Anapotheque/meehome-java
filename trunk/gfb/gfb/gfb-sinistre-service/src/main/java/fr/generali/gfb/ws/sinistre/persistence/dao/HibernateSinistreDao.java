/**
 * 
 */
package fr.generali.gfb.ws.sinistre.persistence.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;
import fr.generali.socle.persistence.hibernate.HibernateGenericDao;

/**
 * @author ARUL AGUILANE
 */
@Component
@Repository
public class HibernateSinistreDao extends HibernateGenericDao<DeclarationSinistre, Long> implements ISinistreDao {

    /**
     * Constructeur à partir d'une session factory.
     * 
     * @param sessionFactory session factory à injecter.
     */
    @Autowired
    public HibernateSinistreDao(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
