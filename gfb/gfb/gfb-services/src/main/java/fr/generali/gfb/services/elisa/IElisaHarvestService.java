/**
 * Generali Solutions d'assurances - Tous droits réservés &copy; 2007 - 2010
 */
package fr.generali.gfb.services.elisa;

import java.util.List;

import fr.generali.gvie.elisa.framework.ws.fault.ElisaWSAuthException;
import fr.generali.gvie.elisa.framework.ws.fault.ElisaWSHypothesesException;
import fr.generali.gvie.elisa.framework.ws.fault.ElisaWSTechnicalException;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoClient;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoDernierePeriode;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoHypotheses;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoInformationComplementaire;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoProfession;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoResultats;

public interface IElisaHarvestService {

    /**
     * Récupération de la liste des professions Harvest
     * 
     * @return liste des professions
     */
    List<DtoRoProfession> recupererListeProfessions(String login, String mdp) throws ElisaWSTechnicalException,
                    ElisaWSHypothesesException, ElisaWSAuthException;

    /**
     * Tarification
     * 
     * @param wsRoHypotheses Hypothèses Harvest
     * @param login Login du WS
     * @param mdp Password du WS
     * @return résultats
     */
    DtoRoResultats tariferRetraiteObligatoire(DtoRoHypotheses wsRoHypotheses, String login, String mdp)
                    throws ElisaWSTechnicalException, ElisaWSHypothesesException, ElisaWSAuthException;
    

    /**
     * Récupération de la liste des informations complementaires
     * 
     * @param dtoRoClient info
     * @param dtoRoDernierePeriode info
     * @param login login compte technique
     * @param mdp mot de passe
     * @return liste d'informations complémentaires
     */
    List<DtoRoInformationComplementaire> recupererInformationsComplementaires(DtoRoClient dtoRoClient, DtoRoDernierePeriode dtoRoDernierePeriode, String login, String mdp)
                    throws ElisaWSTechnicalException, ElisaWSHypothesesException, ElisaWSAuthException;
    
}
