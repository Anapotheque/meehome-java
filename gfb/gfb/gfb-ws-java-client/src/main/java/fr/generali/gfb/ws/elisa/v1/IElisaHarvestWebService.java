package fr.generali.gfb.ws.elisa.v1;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import fr.generali.gvie.elisa.framework.ws.fault.ElisaWSAuthException;
import fr.generali.gvie.elisa.framework.ws.fault.ElisaWSHypothesesException;
import fr.generali.gvie.elisa.framework.ws.fault.ElisaWSTechnicalException;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoClient;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoDernierePeriode;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoHypotheses;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoInformationComplementaire;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoProfession;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoResultats;

/**
 * Interface du web-service pluggé à Elisa. Cette interface va être utilisée
 * pour generer le WSDL.
 * 
 * @author Stéphane Bouclier <sbouclier@generali.fr>
 */

@WebService(name = "IElisaHarvestWebService", targetNamespace = "http://v1.elisa.ws.gfb.generali.fr")
public interface IElisaHarvestWebService {

    @WebMethod(operationName = "recupererListeProfessions")
    List<DtoRoProfession> recupererListeProfessions(
                    @WebParam(name = "login") String login,
                    @WebParam(name = "mdp") String mdp) 
                    throws ElisaWSTechnicalException, ElisaWSHypothesesException, ElisaWSAuthException;

    @WebMethod(operationName = "tariferRetraiteObligatoire")
    DtoRoResultats tariferRetraiteObligatoire(
                    @WebParam(name = "dtoRoHypotheses") DtoRoHypotheses wsRoHypotheses,
                    @WebParam(name = "login") String login, 
                    @WebParam(name = "mdp") String mdp)
                    throws ElisaWSTechnicalException, ElisaWSHypothesesException, ElisaWSAuthException;


    @WebMethod(operationName = "recupererInformationsComplementaires")
    List<DtoRoInformationComplementaire> recupererInformationsComplementaires(
                    @WebParam(name = "dtoRoClient") DtoRoClient dtoRoClient, 
                    @WebParam(name = "dtoRoDernierePeriode") DtoRoDernierePeriode dtoRoDernierePeriode, 
                    @WebParam(name = "login") String login,
                    @WebParam(name = "mdp") String mdp)
                    throws ElisaWSTechnicalException, ElisaWSHypothesesException, ElisaWSAuthException;
}
