package fr.generali.gfb.amf.jmeter;

import java.util.Arrays;

import org.junit.Test;

import flex.messaging.io.amf.client.AMFConnection;
import flex.messaging.io.amf.client.exceptions.ClientStatusException;
import flex.messaging.io.amf.client.exceptions.ServerStatusException;
import fr.generali.gfb.ws.exemple.dto.ListeNombresEntierDto;

/**
 *   public Integer additionner(ListeNombresEntierDto nb);
 *   public Integer multiplier(ListeNombresEntierDto nb); 
 */
public class ExempleCalculAMFCallIT extends AbstractAMFCallIT {
    private static final String ENDPOINT = "exempleCalculAMFServiceDest";

    private static final String DEFAULT_URL = ENV_REC_URL;
    
    @Test
    public void amfConnectionCallAdditionner() {
        AMFConnection amfConnection = getConnection(true, true, "-calcul-addition-1.amf");
        ListeNombresEntierDto dto = new ListeNombresEntierDto();
        
        dto.setListNombresEntier(Arrays.asList(new Integer[]{2,3,7}));
        
        try {
            amfConnection.connect(DEFAULT_URL);

            final Object result = amfConnection.call(ENDPOINT + ".additioner", dto);

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
    public void amfConnectionCallMultiplier() {
        AMFConnection amfConnection = getConnection(true, true, "-calcul-multiplication-1.amf");
        ListeNombresEntierDto dto = new ListeNombresEntierDto(); 
        
        dto.setListNombresEntier(Arrays.asList(new Integer[]{2,3,7}));
        
        try {
            amfConnection.connect(DEFAULT_URL);

            final Object result = amfConnection.call(ENDPOINT + ".multiplier", dto);

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
