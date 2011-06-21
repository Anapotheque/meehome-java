/*
 * ActionNewsLetter.java
 *
 * Created on 20 mars 2007, 09:28
 */

package fr.alliance.docalliance.ajax.mail.newsletter;

import fr.alliance.docalliance.controleur.MetaControleur;
import fr.alliance.docalliance.dao.DaoCreateException;
import fr.alliance.docalliance.dao.DaoFindException;
import fr.alliance.docalliance.model.AdresseMail;
import fr.alliance.docalliance.model.Mail;
import fr.alliance.docalliance.model.Pdf;
import fr.alliance.docalliance.model.User;
import fr.alliance.docalliance.service.IGlobalService;
import fr.alliance.docalliance.tools.html.message.MessageAlert;
import fr.alliance.docalliance.tools.system.DataDate;
import fr.alliance.docalliance.tools.system.MailSend;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * Servlet permettant l'envoi de mail de type Feuille de paye
 * @author Yves Le Rumeur, Romain Raballand
 * @version 1.0
 */

public class ActionNewsLetter extends MetaControleur {
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        // Recupere la session
        HttpSession session = request.getSession(true);
        
        IGlobalService service = getService(request);
        
        //Initialisation du flux de sortie
        PrintWriter out = response.getWriter();
        
        try {
            
            //declaration d'un liste d'adresseMail pour la reception des pdf selectionnés
            ArrayList list_idAdresseMail = new ArrayList();
            
            //On incremente notre liste avec les adresses selectionnées
            for(int j=1; j<= Integer.parseInt(request.getParameter("NombreSelect")); j++){
                list_idAdresseMail.add(Integer.parseInt(request.getParameter("idContact"+j)));
            }
            
            //Pour chaque destinataires selectionnés
            for(int i = 0 ; i < list_idAdresseMail.size() ; i++){
                
                AdresseMail adresseMail = service.getAdresseMailById((Integer) list_idAdresseMail.get(i));
                
                //Initialisation du sujet de la lettre
                String sujet = "NewsLetter du "+DataDate.GetDate();
                String contenu = "Votre NewsLetter";
                
                Pdf pdf  = (Pdf) session.getAttribute("Pdf");
                User user = (User) session.getAttribute("user");
                
                //On split les adresses mail pour envoyer un mail à chaque adresse mail referencées
                String allAdresseMail = adresseMail.getAdresseMail();
                String []tabAdresseMail = allAdresseMail.split(";");
                
                //On parcours la liste du tableau d'adresse mail
                for(int j = 0; j < tabAdresseMail.length; j ++){
                    //Envoi du PDF au bon destinataire avec initialisation de l'envoyeur
                    MailSend.send(tabAdresseMail[j],user.getAdresseMail().getAdresseMail(),sujet,contenu,pdf.getAbsolutPath(),pdf.getNom());
                }
                
                //Creation de l'historique
                Mail mail = new Mail();
                
                //Attribution de l'expediteur
                mail.setAdresseMail(user.getAdresseMail());
                
                //Initialisation de la liste de destinataire
                HashSet hashSet = new HashSet<AdresseMail>();
                hashSet.add(adresseMail);
                
                //Attribution des destinataires
                mail.setDestinataires(hashSet);
                
                //Attribution de la date d'envoi
                mail.setDateExpedition(DataDate.getTimeDate());
                
                //Attribution de l'objet PDF
                mail.setPdf(pdf);
                
                //Attribution d'un sujet
                mail.setSujet(sujet);
                
                //Attribution d'un contenu
                mail.setContenu(contenu);
                
                //On defini le PDF comme envoyé
                pdf.setSend(true);
                
                //Sauvegarde du PDF
                service.saveOnePdf(pdf);
                
                //Sauvegarde du mail
                service.saveOneMail(mail);
                
            }

            //Affichage du rapport
            out.print("<table>");
            out.print("<tr><td><span style='font-weight : bold'>Rapport</span></td><td><a href=javascript:Ajax('Accueil','AccueilNewsletter','null')>RETOUR</a></td></tr>");
            
            //Affichage de réussite
            out.print("<tr><td><a href=javascript:Ajax('Accueil','Historique','null')>Mise à jour de l'historique : </a></td><td><span style='color: green; font-weight : bold'>SUCESS</span></td></tr>");
            out.print("<tr><td>PDF retiré de la liste active : </td><td><span style='color: green; font-weight : bold'>SUCESS</span></td></tr>");
            out.print("</table>");
            
            //Message success
            MessageAlert message = new MessageAlert("SUCCESS",MessageAlert.VALIDATION);
            out.print(message.getFLUX());
            
            //Traitement des erreurs liées au modele de données ( Hibernate )
        }  catch (MessagingException e) {
            MessageAlert message = new MessageAlert("Probleme : <br><br>"+e.getMessage(),MessageAlert.ERREUR);
            out.print(message.getFLUX());
            e.printStackTrace();
        }catch (DaoFindException e) {
            MessageAlert message = new MessageAlert("Probleme : <br><br>"+e.getMessage(),MessageAlert.ERREUR);
            out.print(message.getFLUX());
            e.printStackTrace();
        } catch (DaoCreateException e) {
            MessageAlert message = new MessageAlert("Probleme : <br><br>"+e.getMessage(),MessageAlert.ERREUR);
            out.print(message.getFLUX());
            e.printStackTrace();
        }
        
        //Fermeture du flux de sortie
        out.flush();
        out.close();
    }
}