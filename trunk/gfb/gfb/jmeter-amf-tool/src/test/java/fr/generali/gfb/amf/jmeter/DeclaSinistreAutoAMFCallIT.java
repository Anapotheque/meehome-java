package fr.generali.gfb.amf.jmeter;

import org.junit.Test;

import flex.messaging.io.amf.client.AMFConnection;
import flex.messaging.io.amf.client.exceptions.ClientStatusException;
import flex.messaging.io.amf.client.exceptions.ServerStatusException;
import fr.generali.gfb.ws.sinistre.dto.auto.DeclarationSinistreAccidentIncendieInput;
import fr.generali.gfb.ws.sinistre.dto.auto.DeclarationSinistreVandalismeInput;
import fr.generali.gfb.ws.sinistre.dto.auto.DeclarationSinistreVolInput;

public class DeclaSinistreAutoAMFCallIT extends AbstractAMFCallIT {

    private static final String DEFAULT_URL = ENV_REC_URL;
    
    private static final String ENDPOINT = "autoAMFServiceDest";

    @Test
    public void amfConnectionCallDeclarerAccident() {
        
        final AMFConnection amfConnection = getConnection(true, true, "-post-auto-declarerAccident-amfconnection.amf");
        DeclarationSinistreAccidentIncendieInput input = new DeclarationSinistreAccidentIncendieInput();
        
        // TODO : ajouter des choses dans "input"
        
        try {
            amfConnection.connect(DEFAULT_URL);

            final Object result = amfConnection.call(ENDPOINT + ".declarerAccident", input);

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
    public void amfConnectionCallDeclarerIncendie() {
        final AMFConnection amfConnection = getConnection(true, true, "-post-auto-declarerIncendie-amfconnection.amf");
        DeclarationSinistreAccidentIncendieInput input = new DeclarationSinistreAccidentIncendieInput();
        
        // TODO : ajouter des choses dans "input"
        
        try {
            amfConnection.connect(DEFAULT_URL);

            final Object result = amfConnection.call(ENDPOINT + ".declarerIncendie", input);

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
    public void amfConnectionCallDeclarerVandalisme() {
        final AMFConnection amfConnection = getConnection(true, true, "-post-auto-declarerVandalisme-amfconnection.amf");
        DeclarationSinistreVandalismeInput input = new DeclarationSinistreVandalismeInput();
        
        // TODO : ajouter des choses dans "input"
        
        try {
            amfConnection.connect(DEFAULT_URL);

            final Object result = amfConnection.call(ENDPOINT + ".declarerVandalisme", input);

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
    public void amfConnectionCallDeclarerVol() {
        final AMFConnection amfConnection = getConnection(true, true, "-post-auto-declarerVol-amfconnection.amf");
        DeclarationSinistreVolInput input = new DeclarationSinistreVolInput();
        
        // TODO : ajouter des choses dans "input"
        
        try {
            amfConnection.connect(DEFAULT_URL);

            final Object result = amfConnection.call(ENDPOINT + ".declarerVol", input);

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