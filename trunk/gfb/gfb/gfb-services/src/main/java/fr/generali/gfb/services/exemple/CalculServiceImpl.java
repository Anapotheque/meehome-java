/**
 * Generali Solutions d'assurances - Tous droits réservés &copy; 2007 - 2010
 */
package fr.generali.gfb.services.exemple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import fr.generali.gfb.services.exemple.domain.ListeNombresEntier;

/**
 * Implémentation du service CalculService
 */
@Service("exempleCalculService")
public class CalculServiceImpl implements ICalculService {

    /**
     * Logger
     */
    private static Logger LOGGER = LoggerFactory.getLogger(CalculServiceImpl.class);

    /**
     * {@inheritDoc}
     */
    public Integer additionner(ListeNombresEntier liste) {
        Integer res = 0;

        for (Integer nb : liste.getListNombresEntier()) {
            res += nb;
        }
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Résultat addition = {}", res);
        }

        return res;
    }

    /**
     * {@inheritDoc}
     */
    public Integer multiplier(ListeNombresEntier liste) {
        Integer res = 1;

        for (Integer nb : liste.getListNombresEntier()) {
            res *= nb;
        }
        
        boolean b = LOGGER.isDebugEnabled();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Résultat multiplication = {}", res);
        }

        return res;
    }
}
