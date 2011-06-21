package fr.generali.gfb.amf.jmeter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;

import flex.messaging.io.MessageDeserializer;
import flex.messaging.io.MessageSerializer;
import flex.messaging.io.SerializationContext;
import flex.messaging.io.amf.ActionContext;
import flex.messaging.io.amf.ActionMessage;
import flex.messaging.io.amf.AmfMessageDeserializer;
import flex.messaging.io.amf.AmfMessageSerializer;
import flex.messaging.io.amf.AmfTrace;
import flex.messaging.io.amf.MessageBody;
import flex.messaging.messages.RemotingMessage;

public class AMFSerializerTest {

    private static Logger LOGGER = LoggerFactory.getLogger(ElisaRetraiteAMFCallIT.class);

    final static String basePath = "src/test/resources/fr/generali/gfb/amf/jmeter/";

    /**
     * Ces fichiers ont été générés via JMeter en capturant les requetes HTTP AMF via son Proxy.
     * J'ai renommé les fichiers pour donner une indication de ce qu'ils contiennent.
     */
    final static String[] postFiles = new String[]{
        
        "POST-AMF-ping-01.binary",
        "POST-AMF-recupererInformationsComplementaires-01.binary",
        "POST-AMF-recupererListeProfessions-01.binary",
        "POST-AMF-tariferRetraiteObligatoire-01.binary",
        "POST5122892210104197306.binary",
        "POST553586681044428813.binary",
        "20100716-050516-post-recupererListeProfessions.amf"};
    
    /**
     * Ces fichiers ont été sauvegardé via JMeter (un listener dans chaque HTTP GET AMF).
     */
    final static String[] responseFiles = new String[]{
        //"AMF-ping-server-011.x-amf",
        "AMF-recupInfosComp3.x-amf",
        "AMF-recupListeProfs2.x-amf",
        "AMF-tarif4.x-amf"};

    //@Test
    public void testDeserializeResponses() throws ClassNotFoundException, IOException {
        XStream xstream = new XStream();

        for (String fileName : responseFiles) {
            LOGGER.info(fileName);
            final FileInputStream fis = new FileInputStream(new File(basePath + fileName));
            MessageDeserializer md = createMessageDeserializer(fis);
            ActionMessage actionMessage = new ActionMessage();
            ActionContext actionContext = new ActionContext();
            
            md.readMessage(actionMessage, actionContext);
            
            LOGGER.info(xstream.toXML(actionMessage) + "\n");

        }
    }

    //@Test
    public void testDeserializePOSTs() throws ClassNotFoundException, IOException {
        XStream xstream = new XStream();

        for (String fileName : postFiles) {
            LOGGER.info(fileName);
            final FileInputStream fis = new FileInputStream(new File(basePath + fileName));
            MessageDeserializer md = createMessageDeserializer(fis);
            ActionMessage actionMessage = new ActionMessage();
            ActionContext actionContext = new ActionContext();
            
            md.readMessage(actionMessage, actionContext);
            
            LOGGER.info(xstream.toXML(actionMessage) + "\n");

        }
    }
    
    @Test
    public void testSerializePOSTs() throws ClassNotFoundException, IOException {
        createPOSTFileRecupererListeProfessions();
    }
    
    public static String createPOSTFileRecupererListeProfessions() throws ClassNotFoundException, IOException {
        XStream xstream = new XStream();

        byte[] result = generate(new AmfRequestParameters("elisaHarvestAMFServiceDest", "recupererListeProfessions", new String[] {"servicero", "passwd"}));
        String filename = writeByteArrayToTimestampedTempFile(result, "-post-recupererListeProfessions.amf");
        
        final FileInputStream fis = new FileInputStream(new File(filename));
        MessageDeserializer md = createMessageDeserializer(fis);
        ActionMessage actionMessage = new ActionMessage();
        ActionContext actionContext = new ActionContext();
        
        md.readMessage(actionMessage, actionContext);
        
        LOGGER.info(xstream.toXML(actionMessage) + "\n");
        
        return filename;
    }

    /**
     * 
     * @param data
     * @param fileNameSuffix
     * @return le nom de fichier complet
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static String writeByteArrayToTimestampedTempFile(byte[] data, String fileNameSuffix) throws ClassNotFoundException, IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-hhmmss");
        
        File output = new File("E:\\jtb\\workspaces\\jtb5\\gfb-reactor\\jmeter-amf-tool\\src\\test\\resources\\fr\\generali\\gfb\\amf\\jmeter\\" + sdf.format(new Date()) + fileNameSuffix );
        FileOutputStream fos = new FileOutputStream(output);
        fos.write(data);
        fos.flush();
        fos.close();
        
        return output.getAbsolutePath();
    }
    
    @Test
    public void testSerializeDeserialise() throws ClassNotFoundException, IOException {
        XStream xstream = new XStream();

        byte[] result = generate(new AmfRequestParameters("elisaHarvestAMFServiceDest", "recupererListeProfessions", new String[] {"servicero", "passwd"}));

        final ByteArrayInputStream bais = new ByteArrayInputStream(result);
        MessageDeserializer md = createMessageDeserializer(bais);
        ActionMessage actionMessage = new ActionMessage();
        ActionContext actionContext = new ActionContext();
        
        md.readMessage(actionMessage, actionContext);
        
        LOGGER.info(xstream.toXML(actionMessage) + "\n");
    }

    public static MessageDeserializer createMessageDeserializer(InputStream in) {
        SerializationContext context = new SerializationContext();
        context.setDeserializerClass(AmfMessageDeserializer.class);
        SerializationContext.setSerializationContext(context);

        MessageDeserializer deserializer = context.newMessageDeserializer();
        AmfTrace trace = null;
        deserializer.initialize(context, in, trace);

        return deserializer;
    }
    
    public static MessageSerializer createMessageSerializer(OutputStream out) {
        SerializationContext context = new SerializationContext();
        context.setSerializerClass(AmfMessageSerializer.class);
        SerializationContext.setSerializationContext(context);

        MessageSerializer serializer = context.newMessageSerializer();
        serializer.initialize(context, out, null);

        return serializer;
    }

    
    public static byte[] generate(AmfRequestParameters params) {
        ActionMessage message = new ActionMessage();
        message.addBody(createMessageBody(params));
        
        message.setVersion(3);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        MessageSerializer serializer = createMessageSerializer(out);
        try {
            serializer.writeMessage(message);
        } catch (IOException e) {
            throw new AmfRequestGenerationException(e);
        }

        return out.toByteArray();
    }

    public static MessageBody createMessageBody(AmfRequestParameters params) {
        RemotingMessage message = new RemotingMessage();
        message.setHeader(RemotingMessage.ENDPOINT_HEADER, "default");
        //message.setHeader(RemotingMessage.ENDPOINT_HEADER, "default");
        //message.setHeader(RemotingMessage.DESTINATION_CLIENT_ID_HEADER, "1");
        message.setHeader(RemotingMessage.FLEX_CLIENT_ID_HEADER, "1");
        message.setMessageId("1");
        message.setDestination(params.destination);
        message.setOperation(params.operation);
        message.setBody(params.parameters);
        
        final MessageBody mb = new MessageBody(null, null, new RemotingMessage[] {message});
        mb.setTargetURI(params.destination + "." + params.operation);
        mb.setResponseURI("/0");

        return mb;
    }

    public static class AmfRequestGenerationException extends RuntimeException {
        public AmfRequestGenerationException() {
            super();
        }

        public AmfRequestGenerationException(String message, Throwable cause) {
            super(message, cause);
        }

        public AmfRequestGenerationException(String message) {
            super(message);
        }

        public AmfRequestGenerationException(Throwable cause) {
            super(cause);
        }
    }
    
    public static class AmfRequestParameters {

        public String destination;
        public String operation;
        public Object[] parameters;
        public String bodyTarget = null;
        public String bodyResponse = "/2";

        /**
         * Creates a new {@code AmfRequestParameters} with the given fields.
         *
         * @param destination destination of the amf request
         * @param operation operation to call on the destination
         * @param parameters parameters of the operation
         */
        public AmfRequestParameters(String destination, String operation, Object[] parameters) {
            this.destination = destination;
            this.operation = operation;
            this.parameters = parameters;
        }

        // getters and setters omitted
    }
}