package fr.generali.gfb.amf.jmeter;

import net.sf.cglib.proxy.Enhancer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;

import flex.messaging.io.amf.client.AMFConnection;

public class AbstractAMFCallIT {
    protected static Logger LOGGER = LoggerFactory.getLogger(ElisaRetraiteAMFCallIT.class);

    protected static final String ENV_INT_URL = "http://www.frontbroker.int.groupe.generali.fr:8080/gfb-ws-webapp/messagebroker/amf";
    protected static final String ENV_REC_URL = "http://www.frontbroker.rec.groupe.generali.fr:8080/gfb-ws-webapp/messagebroker/amf";
    protected static final String ENV_PPROD_URL = "http://www.frontbroker.pprod.generali.fr:80/gfb-ws-webapp/messagebroker/amf";
    protected static final String ENV_PROD_URL = "http://www.frontbroker.generali.fr:80/gfb-ws-webapp/messagebroker/amf";

    protected static final XStream XSTREAM = new XStream ();

    /**
     * @param createCGLIBProxy si on veut créé un proxy CGLIB avec un intercepteur de méthode qui sauvegarde le fichier binaire AMF
     * @return
     */
    protected AMFConnection getConnection(boolean createCGLIBProxy, boolean dumpFile, String fileNameSuffix) {
        AMFConnection amfConnection = null; 
        if(createCGLIBProxy) {
            final Enhancer cglibEnhancer = new Enhancer();
            cglibEnhancer.setSuperclass(AMFConnection.class);
            cglibEnhancer.setCallback(new AMFConnectionSendMethodInterceptor(dumpFile, fileNameSuffix));
    
            amfConnection = (AMFConnection) cglibEnhancer.create();
        } else {
            amfConnection = new AMFConnection();
        }
        return amfConnection;
    }
}
