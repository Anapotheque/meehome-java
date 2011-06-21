package fr.generali.gfb.ws.exemple;

import fr.generali.gfb.ws.exemple.dto.ListeNombresEntierDto;

/**
 * Interface du web-service CalculWebService
 * 
 * @author Stéphane Bouclier <sbouclier@generali.fr>
 */
public interface ICalculWebService {
    public Integer additionner(ListeNombresEntierDto nb);

    public Integer multiplier(ListeNombresEntierDto nb);
}
