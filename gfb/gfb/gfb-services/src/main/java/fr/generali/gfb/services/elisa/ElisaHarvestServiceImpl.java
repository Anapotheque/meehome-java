/**
 * Generali Solutions d'assurances - Tous droits réservés &copy; 2007 - 2010
 */
package fr.generali.gfb.services.elisa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.generali.gvie.elisa.framework.ws.fault.ElisaWSAuthException;
import fr.generali.gvie.elisa.framework.ws.fault.ElisaWSHypothesesException;
import fr.generali.gvie.elisa.framework.ws.fault.ElisaWSTechnicalException;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoClient;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoDernierePeriode;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoHypotheses;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoInformationComplementaire;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoProfession;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoResultats;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.services.IRetraiteObligatoireWebService;

/**
 * Implémentation passe-plat des webservices ELISA.
 */
@Service("elisaHarvestService")
public class ElisaHarvestServiceImpl implements IElisaHarvestService {

    /**
     * Proxy XFire du WebService fourni par ELISA.
     */
    @Autowired
    private IRetraiteObligatoireWebService service;

    /**
     * {@inheritDoc}
     */
    public List<DtoRoProfession> recupererListeProfessions(String login, String mdp) throws ElisaWSTechnicalException,
                    ElisaWSHypothesesException, ElisaWSAuthException {

        return service.recupererListeProfessions(login, mdp);
    }

    /**
     * {@inheritDoc}
     */
    public DtoRoResultats tariferRetraiteObligatoire(DtoRoHypotheses dtoRoHypotheses, String login, String mdp)
                    throws ElisaWSTechnicalException, ElisaWSHypothesesException, ElisaWSAuthException {

        return service.tariferRetraiteObligatoire(dtoRoHypotheses, login, mdp);
    }
    

    /**
     * {@inheritDoc}
     */
    public List<DtoRoInformationComplementaire> recupererInformationsComplementaires(DtoRoClient dtoRoClient, DtoRoDernierePeriode dtoRoDernierePeriode, String login, String mdp)
                    throws ElisaWSTechnicalException, ElisaWSHypothesesException, ElisaWSAuthException {

        return service.recupererInformationsComplementaires(dtoRoClient, dtoRoDernierePeriode, login, mdp);
    }

}
