package fr.generali.gfb.amf.jmeter;

import org.junit.Test;

import flex.messaging.io.amf.client.AMFConnection;
import flex.messaging.io.amf.client.exceptions.ClientStatusException;
import flex.messaging.io.amf.client.exceptions.ServerStatusException;
import fr.generali.gfb.ws.sinistre.dto.mrh.DeclarationSinistreBrisGlaceInput;
import fr.generali.gfb.ws.sinistre.dto.mrh.DeclarationSinistreDegatsDesEauxInput;
import fr.generali.gfb.ws.sinistre.dto.mrh.DeclarationSinistreDommageElectriqueInput;
import fr.generali.gfb.ws.sinistre.dto.mrh.DeclarationSinistreTempeteGreleInput;
import fr.generali.gfb.ws.sinistre.dto.mrh.DeclarationSinistreVolInput;

public class DeclaSinistreMrhAMFCallIT extends AbstractAMFCallIT {

    private static final String DEFAULT_URL = ENV_REC_URL;

    private static final String ENDPOINT = "mrhAMFServiceDest";
    
    @Test
    public void amfConnectionCallDeclarerBrisDeGlace() {

        final AMFConnection amfConnection = getConnection(true, true, "-post-mrh-declarerBrisDeGlace-amfconnection.amf");
        DeclarationSinistreBrisGlaceInput input = new DeclarationSinistreBrisGlaceInput();
        
        // TODO : ajouter des choses dans "input"
        
        try {
            amfConnection.connect(DEFAULT_URL);

            final Object result = amfConnection.call(ENDPOINT + ".declarerBrisDeGlace", input);

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
    public void amfConnectionCallDeclarerDegatDesEaux() {
        final AMFConnection amfConnection = getConnection(true, true, "-post-mrh-declarerDegatDesEaux-amfconnection.amf");
        DeclarationSinistreDegatsDesEauxInput input = new DeclarationSinistreDegatsDesEauxInput();
        
        // TODO : ajouter des choses dans "input"
        
        try {
            amfConnection.connect(DEFAULT_URL);

            final Object result = amfConnection.call(ENDPOINT + ".declarerDegatDesEaux", input);

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
    public void amfConnectionCallDeclarerDommageElectrique() {
        final AMFConnection amfConnection = getConnection(true, true, "-post-mrh-declarerDommageElectrique-amfconnection.amf");
        DeclarationSinistreDommageElectriqueInput input = new DeclarationSinistreDommageElectriqueInput();
        
        // TODO : ajouter des choses dans "input"
        
        try {
            amfConnection.connect(DEFAULT_URL);

            final Object result = amfConnection.call(ENDPOINT + ".declarerDommageElectrique", input);

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
    public void amfConnectionCallDeclarerTempeteGrele() {
        final AMFConnection amfConnection = getConnection(true, true, "-post-mrh-declarerTempeteGrele-amfconnection.amf");
        DeclarationSinistreTempeteGreleInput input = new DeclarationSinistreTempeteGreleInput();
        
        // TODO : ajouter des choses dans "input"
        
        try {
            amfConnection.connect(DEFAULT_URL);

            final Object result = amfConnection.call(ENDPOINT + ".declarerTempeteGrele", input);

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
        final AMFConnection amfConnection = getConnection(true, true, "-post-mrh-declarerVol-amfconnection.amf");
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
