package fr.generali.gfb.ws.exemple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.generali.gfb.services.exemple.ICalculService;
import fr.generali.gfb.services.exemple.domain.ListeNombresEntier;
import fr.generali.gfb.ws.exemple.dto.ListeNombresEntierDto;

@Service("exempleCalculWs")
public class CalculWebServiceImpl implements ICalculWebService {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CalculWebServiceImpl.class);

    /**
     * Le service exposé en web service.
     */
    @Autowired
    private ICalculService calculService;

    public void setCalculService(ICalculService calculService) {
        this.calculService = calculService;
    }

    /**
     * {@inheritDoc}
     */
    public Integer additionner(ListeNombresEntierDto nb) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Appel au WS additionner");
        }

        Integer i = calculService.additionner(this.getListeNombresEntierFromDto(nb));

        return i;
    }

    /**
     * {@inheritDoc}
     */
    public Integer multiplier(ListeNombresEntierDto nb) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Appel au WS multiplier");
        }

        return calculService.multiplier(this.getListeNombresEntierFromDto(nb));
    }

    /**
     * Permet de créer un dto de ListeNombresEntierDto à partir d'un
     * ListeNombresEntier
     * 
     * @param liste ListeNombresEntier à copier en Dto.
     * @return le Dto correspondant au contrat passé en parametre.
     */
    @SuppressWarnings("unused")
    private ListeNombresEntierDto getDtoFromListeNombresEntier(ListeNombresEntier liste) {
        final ListeNombresEntierDto dto = new ListeNombresEntierDto();
        if (liste == null) {
            return null;
        }
        dto.setListNombresEntier(liste.getListNombresEntier());
        return dto;
    }

    /**
     * Permet de créer un BO de ListeNombresEntier à partir d'un
     * ListeNombresEntierDto
     * 
     * @param liste ListeNombresEntierDto à copier en BO.
     * @return le BO correspondant au contrat passé en parametre.
     */
    private ListeNombresEntier getListeNombresEntierFromDto(ListeNombresEntierDto liste) {
        final ListeNombresEntier vo = new ListeNombresEntier();
        if (liste == null) {
            return null;
        }
        vo.setListNombresEntier(liste.getListNombresEntier());
        return vo;
    }
}
