package fr.generali.gfb.amf.jmeter;

import java.util.Arrays;

import org.junit.Test;

import flex.messaging.io.amf.client.AMFConnection;
import flex.messaging.io.amf.client.exceptions.ClientStatusException;
import flex.messaging.io.amf.client.exceptions.ServerStatusException;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoClient;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoDernierePeriode;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoHypotheses;

/**
 * Test d'appel AMF et réception du message AMF Binaire.
 * 
 */
public class ElisaRetraiteAMFCallIT extends AbstractAMFCallIT {
    
    private static final String ENDPOINT = "elisaHarvestAMFServiceDest";

    private static final String DEFAULT_URL = ENV_PROD_URL;
    
    private static final String ELISA_LOGIN = "servicero";
    private static final String ELISA_PASSWORD = "obliga01";

    
    @Test
    public void amfConnectionCallRecupererListeProfessions() {
        
        final AMFConnection amfConnection = getConnection(true, true, "-post-recupererListeProfessions-amfconnection.amf");
        
        try {
            amfConnection.connect(DEFAULT_URL);

            final Object result = amfConnection.call(ENDPOINT + ".recupererListeProfessions", ELISA_LOGIN, ELISA_PASSWORD);

            LOGGER.info(XSTREAM.toXML(result));
            
        } catch (ClientStatusException e) {
            LOGGER.error("", e);
            org.junit.Assert.fail(e.getMessage());
        } catch (ServerStatusException e) {
            LOGGER.error("", e);
            org.junit.Assert.fail(e.getMessage());
        } finally {
            amfConnection.close();
        }
    }

    @Test
    public void amfConnectionCallRecupererInformationsComplementaires() {
        final AMFConnection amfConnection = getConnection(true, true, "-post-recupererInformationsComplementaires-amfconnection.amf");
        try {
            amfConnection.connect(DEFAULT_URL);
            
            final DtoRoClient dtoRoClient = ElisaRetraiteTestUtils.newDtoRoClient(65, "1978-05-30", "M", 43000D, 2);
            final DtoRoDernierePeriode dtoRoDernierePeriode = ElisaRetraiteTestUtils.newDtoRoDernierePeriode("P_CADRE", "2001-10-10", true);
            
            final Object result = amfConnection.call(ENDPOINT + ".recupererInformationsComplementaires", dtoRoClient, dtoRoDernierePeriode, ELISA_LOGIN, ELISA_PASSWORD);
            LOGGER.info(XSTREAM.toXML(result));
        } catch (ClientStatusException e) {
            LOGGER.error("", e);
            org.junit.Assert.fail(e.getMessage());
        } catch (ServerStatusException e) {
            LOGGER.error("", e);
            org.junit.Assert.fail(e.getMessage());
        } finally {
            amfConnection.close();
        }
    }
    
    @Test
    public void amfConnectionCallTariferRetraiteObligatoire() {
        final AMFConnection amfConnection = getConnection(true, true, "-post-tariferRetraiteObligatoire-amfconnection.amf");
        try {
            amfConnection.connect(DEFAULT_URL);
            
            // CADRE
            DtoRoHypotheses dtoRoHypotheses = ElisaRetraiteTestUtils.newDtoRoHypotheses(Arrays.asList(
                            ElisaRetraiteTestUtils.newDtoRoPeriode("P_CADRE", "1980-01-01", "2006-01-01", false, 40000d, 80000d,
                                            Arrays.asList(ElisaRetraiteTestUtils.newRepInfoComp(null, "TAUX", 0.6, "TX_ACT")))), 
                                            ElisaRetraiteTestUtils.newDtoRoClient(65, "1960-01-01", "M", 50000d, 0));
            
            final Object result = amfConnection.call(ENDPOINT + ".tariferRetraiteObligatoire", dtoRoHypotheses, ELISA_LOGIN, ELISA_PASSWORD);
            LOGGER.info(XSTREAM.toXML(result));
        } catch (ClientStatusException e) {
            LOGGER.error("", e);
            org.junit.Assert.fail(e.getMessage());
        } catch (ServerStatusException e) {
            LOGGER.error("", e);
            org.junit.Assert.fail(e.getMessage());
        } finally {
            amfConnection.close();
        }
    }

    
}
