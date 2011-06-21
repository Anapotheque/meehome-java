package fr.generali.gfb.amf.exemple;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;
import flex.messaging.io.amf.client.AMFConnection;
import flex.messaging.io.amf.client.exceptions.ClientStatusException;
import flex.messaging.io.amf.client.exceptions.ServerStatusException;
import fr.generali.gfb.ws.exemple.dto.ListeNombresEntierDto;

/**
 * Cette classe est un exemple d'invocation de web service en HTTP/HTTPS. Ce
 * test n'est pas automatise (le nom de la classe ne contient pas Test pour ne
 * pas etre execute par Maven).
 * 
 * @author Stéphane Bouclier
 */
public class CalculWebServiceIT extends TestCase {

    private static final String DEFAULT_DESTINATION_ID = "exempleCalculAMFServiceDest";

    private static final String DEFAULT_URL = "http://localhost:8080/gfb-ws-webapp/messagebroker/amf";

    private static final String SECURE_URL = "https://localhost:8443/gfb-ws-webapp/messagebroker/amfsecure";

    private static String getOperationCall(String method) {
        return DEFAULT_DESTINATION_ID + "." + method;
    }

    public void testConnection() {
        System.out.println("Test HTTP");

        AMFConnection amfConnection = new AMFConnection();

        try {
            amfConnection.connect(DEFAULT_URL);
        } catch (ClientStatusException cse) {
            System.out.println("Erreur: " + cse);
            Assert.fail();
        }

        try {
            ListeNombresEntierDto dto = new ListeNombresEntierDto();
            List<Integer> list = new ArrayList<Integer>();
            list.add(new Integer(3));
            list.add(new Integer(6));
            dto.setListNombresEntier(list);
            Object resultA = amfConnection.call(getOperationCall("additionner"), dto);
            Object resultM = amfConnection.call(getOperationCall("multiplier"), dto);

            System.out.println("Resultat Addition: " + resultA);
            System.out.println("Resultat Multiplication: " + resultM);

            // Conversion en Double pour le Number de Flex
            Assert.assertEquals("9.0", resultA.toString());
            Assert.assertEquals("18.0", resultM.toString());

        } catch (ClientStatusException cse) {
            System.out.println("Erreur: " + cse);
            Assert.fail();
        } catch (ServerStatusException sse) {
            System.out.println("Erreur: " + sse);
            Assert.fail();
        }

        amfConnection.close();
    }

    public void testSecureConnection() {
        System.out.println("Test HTTPS");
        System.setProperty("javax.net.ssl.trustStore",
                        "E:/jtb/servers/jboss-4.3-EAP-CP02.1/server/default/conf/nrl.keystore");
        System.setProperty("javax.net.ssl.trustStorePassword", "generali");

        AMFConnection amfConnection = new AMFConnection();

        try {
            amfConnection.connect(SECURE_URL);
        } catch (ClientStatusException cse) {
            System.out.println("Erreur: " + cse);
            Assert.fail();
        }

        try {
            ListeNombresEntierDto dto = new ListeNombresEntierDto();
            List<Integer> list = new ArrayList<Integer>();
            list.add(new Integer(3));
            list.add(new Integer(6));
            dto.setListNombresEntier(list);
            Object resultA = amfConnection.call(getOperationCall("additionner"), dto);
            Object resultM = amfConnection.call(getOperationCall("multiplier"), dto);

            System.out.println("Resultat Addition: " + resultA);
            System.out.println("Resultat Multiplication: " + resultM);

            // Conversion en Double pour le Number de Flex
            Assert.assertEquals("9.0", resultA.toString());
            Assert.assertEquals("18.0", resultM.toString());
        } catch (ClientStatusException cse) {
            System.out.println("Erreur: " + cse);
            Assert.fail();
        } catch (ServerStatusException sse) {
            System.out.println("Erreur: " + sse);
            Assert.fail();
        }

        amfConnection.close();
    }
}
