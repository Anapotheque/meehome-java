package fr.generali.gfb.ws.elisa.v1;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.generali.gfb.services.elisa.IElisaHarvestService;
import fr.generali.gvie.elisa.framework.ws.fault.ElisaWSAuthException;
import fr.generali.gvie.elisa.framework.ws.fault.ElisaWSHypothesesException;
import fr.generali.gvie.elisa.framework.ws.fault.ElisaWSTechnicalException;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoClient;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoDernierePeriode;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoHypotheses;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoInformationComplementaire;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoProfession;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoResultats;

@Service("elisaHarvestWs")
public class ElisaHarvestWebServiceImpl implements IElisaHarvestWebService {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ElisaHarvestWebServiceImpl.class);
    
    /**
     * Le service exposé en web service.
     */
    @Autowired
    private IElisaHarvestService elisaHarvestService;
    
    /**
     * {@inheritDoc}
     */
    public List<DtoRoProfession> recupererListeProfessions(String login, String mdp) throws ElisaWSTechnicalException,
                    ElisaWSHypothesesException, ElisaWSAuthException {

        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("Appel au WS recupererListeProfessions");
        }
        
        List<DtoRoProfession> lRes = elisaHarvestService.recupererListeProfessions(login, mdp);     
        return lRes;
    }

    /**
     * {@inheritDoc}
     */
    public DtoRoResultats tariferRetraiteObligatoire(DtoRoHypotheses wsRoHypotheses, String login, String mdp)
                    throws ElisaWSTechnicalException, ElisaWSHypothesesException, ElisaWSAuthException {

        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("Appel au WS tariferRetraiteObligatoire");
            LOGGER.debug("Client date de naissance: {}", wsRoHypotheses.getDtoRoClient().getDateNaissance());
            LOGGER.debug("Client salaire: {}", wsRoHypotheses.getDtoRoClient().getSalaire());
        }
        DtoRoResultats res = elisaHarvestService.tariferRetraiteObligatoire(wsRoHypotheses, login, mdp);
        return res;
    }
    
    public List<DtoRoInformationComplementaire> recupererInformationsComplementaires(DtoRoClient dtoRoClient, DtoRoDernierePeriode dtoRoDernierePeriode, String login, String mdp)
                    throws ElisaWSTechnicalException, ElisaWSHypothesesException, ElisaWSAuthException {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("Appel au WS recupererInformationsComplementaires");
            LOGGER.debug("Client date de naissance: {}", dtoRoClient.getDateNaissance());
            LOGGER.debug("Client salaire: {}", dtoRoClient.getSalaire());
        }
        List<DtoRoInformationComplementaire> res = elisaHarvestService.recupererInformationsComplementaires(dtoRoClient, dtoRoDernierePeriode, login, mdp);
        return res;
    }
}