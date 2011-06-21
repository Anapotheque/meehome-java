package fr.alliance.docalliance.tools.system;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;



public class MailSend {
     
    private static final String HOSTINT = "ACI-MES";
    //private static final String HOST = "mail.alliance-concept.fr";
    private static final String HOSTEXT = "smtp.noos.fr";
    private static final String MASQUE_INTERNE = "alliance-concept.fr";
    
    /**
     * "send" method to send the message.
     */
    public static void send(String to, String from, String subject, String body, String path, String file)
    throws MessagingException {
        Properties props = System.getProperties();
        InternetAddress iAdrFrom = new InternetAddress(from);
        if(!iAdrFrom.toString().contains("@")){
        	throw new MessagingException("adresse expediteur invalide");
        }
        InternetAddress iAdrDest = new InternetAddress(to);
        if(!iAdrDest.toString().contains("@")){
        	throw new MessagingException("adresse destinataire invalide");
        }
        if(isInterne(iAdrDest.getAddress())){
            props.put("mail.host", HOSTINT);
        }
        else{
            props.put("mail.host", HOSTEXT);
        }
        props.put("mail.debug", "true");
        props.put("mail.transport.protocol", "smtp");
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);
        // NOUVELLE INSTANCE
        Message msg = new MimeMessage(session);
        // AJOUT DES ADRESSE DE DESTINATION, D'ENVOI ET DE COPIE
        msg.setFrom(iAdrFrom);
        msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to, false));
        // AJOUT DU SUJET ET DU CORPS
        msg.setSubject(subject);
        
        if(path != null && file != null){
            // Première partie du message
            BodyPart messageBodyPart = new MimeBodyPart();
            
            // Contenu du message
            messageBodyPart.setText(body);
            
            //Ajout de la première partie du message dans un objet Multipart
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            
            // Partie de la pièce jointe
            messageBodyPart = new MimeBodyPart();
            
            path = path.replace("\\","\\\\");
            DataSource source = new FileDataSource(path);
            
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(file);
            
            //Ajout de la partie pièce jointe
            multipart.addBodyPart(messageBodyPart);
            msg.setContent(multipart);
        }else{
            msg.setText(body);
        }
        
        //AJOUT DE DIVERSES INFORMATIONS
        msg.setHeader("X-Mailer", "Alliance-Concept");
        msg.setSentDate(new Date());
        
        // ENVOI DU MESSAGE
        Transport.send(msg);
    }
    
    private static boolean isInterne(String chaineAdresse){
    	return chaineAdresse.endsWith(MASQUE_INTERNE);
    }
}
