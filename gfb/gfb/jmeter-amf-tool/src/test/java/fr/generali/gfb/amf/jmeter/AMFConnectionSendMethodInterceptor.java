package fr.generali.gfb.amf.jmeter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import com.thoughtworks.xstream.XStream;

import flex.messaging.io.MessageDeserializer;
import flex.messaging.io.amf.ActionContext;
import flex.messaging.io.amf.ActionMessage;

/**
 * proxy pour intercepter l'appel à AMFConnection.send afin de tracer ce qui est envoyé (binaire) !
 */
public class AMFConnectionSendMethodInterceptor implements MethodInterceptor {
    private static Logger LOGGER = LoggerFactory.getLogger(AMFConnectionSendMethodInterceptor.class);

    private boolean dumpAMFBinaryFile = true; 
    
    private boolean logData = true;

    private String fileNameSuffix = "-post.amf";

    public AMFConnectionSendMethodInterceptor(boolean dump, String fSuffix) {
        this.dumpAMFBinaryFile = dump;
        this.fileNameSuffix = fSuffix;
    }

    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        if ("send".equals(method.getName())) {
            final ByteArrayOutputStream outBuffer = (ByteArrayOutputStream) args[0];
            final byte[] data = outBuffer.toByteArray();
            
            if (logData) {
                final ByteArrayInputStream bais = new ByteArrayInputStream(data);
                final MessageDeserializer md = AMFSerializerTest.createMessageDeserializer(bais);
                final ActionMessage actionMessage = new ActionMessage();
                final ActionContext actionContext = new ActionContext();
                
                md.readMessage(actionMessage, actionContext);
                final XStream xstream = new XStream();
                LOGGER.info(xstream.toXML(actionMessage) + "\n");
            }
            
            if (dumpAMFBinaryFile) {
                String filename = AMFSerializerTest.writeByteArrayToTimestampedTempFile(data, fileNameSuffix);
                LOGGER.info("Binary AMF saved to : " + filename);
            }
        }
        // on appelle la méthode normale pour continuer...
        return methodProxy.invokeSuper(object, args);
    }
    
    
}