/*
 * AccueilPaye.java
 *
 * Created on 20 mars 2007, 09:28
 */

package fr.alliance.docalliance.ajax.mail.paye;

import fr.alliance.docalliance.dao.DaoCreateException;
import fr.alliance.docalliance.tools.html.message.MessageAlert;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import fr.alliance.docalliance.controleur.MetaControleur;
import fr.alliance.docalliance.model.AdresseMail;
import fr.alliance.docalliance.model.Mail;
import fr.alliance.docalliance.model.Pdf;
import fr.alliance.docalliance.model.User;
import fr.alliance.docalliance.service.IGlobalService;
import fr.alliance.docalliance.tools.system.DataDate;
import fr.alliance.docalliance.tools.system.MailSend;

/**
 * Servlet permettant l'envoi de mail de type Feuille de paye
 * @author Yves Le Rumeur, Romain Raballand
 * @version 1.0
 */

public class ActionMail extends MetaControleur {
    
    private static final long serialVersionUID = 1L;
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    @SuppressWarnings("unchecked")
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        // Recuperation de la session
        HttpSession session = request.getSession(true);
        
        //Initialisation du flux de sortie
        PrintWriter out = response.getWriter();
        
        IGlobalService service = getService(request);
        
        try {
            
            //Initialisation de la HashTable pour PDF / ADRESSEMAIL
            Hashtable< Pdf, AdresseMail > mainHashPdf = (Hashtable<Pdf, AdresseMail>) session.getAttribute("mainHashPdf");
            
            //On recupere le user connecté
            User user = (User) session.getAttribute("user");
            
            //Declaration des objets necessaires à l'affichage
            Pdf pdf = null;
            AdresseMail adresseMail = null;
            Mail mail = null;
            String sujet = null;
            HashSet hashSet = null;
            
            //Recuperation des informations formulaires ( Liste des pdfs selectionnés )
            //Nombre de pdf selectionnés
            int nombreSelect = Integer.parseInt(request.getParameter("NombreSelect"));
            
            //Initialisation de la list de PDF
            ArrayList<Pdf> listPDF = new ArrayList<Pdf>();
            
            //Pour chaque pdf selectionné
            for(int i=1; i<=nombreSelect; i++){
                
                //on recupere l'ID
                String idPDF = new String(request.getParameter("idPDF"+i));
                
                //Passage du Hastable en iterator
                Iterator it = mainHashPdf.keySet().iterator();
                
                //Parcour de l'ensemble de la liste
                while(it.hasNext()){
                    
                    //On recupere l'objet PDF
                    pdf = (Pdf) it.next();
                    
                    //Si l'objet correspond au pdf selectionné alors on l'envoi
                    if(pdf.getIdPdf()==Integer.parseInt(idPDF)){
                        
                        //Ajout de l'objet à une liste
                        listPDF.add(pdf);
                        
                        //On recupere l'objet AdresseMail associé
                        adresseMail = mainHashPdf.get(pdf);
                        
                        //Initialisation du sujet de la lettre
                        sujet = "Feuille de paye du "+DataDate.GetDate();
                        
                        //On split les adresses mail pour envoyer un mail à chaque adresse mail referencées
                        String allAdresseMail = adresseMail.getAdresseMail();
                        String []tabAdresseMail = allAdresseMail.split(";");
                        
                        //On parcours la liste du tableau d'adresse mail
                        for(int j = 0; j < tabAdresseMail.length; j ++){
                            //Envoi du PDF au bon destinataire avec initialisation de l'envoyeur
                            MailSend.send(tabAdresseMail[j],user.getAdresseMail().getAdresseMail(),sujet,"",pdf.getAbsolutPath(),pdf.getNom());
                        }
                        
                        //Creation de l'historique
                        mail = new Mail();
                        
                        //Attribution de l'expediteur
                        mail.setAdresseMail(user.getAdresseMail());
                        
                        //Initialisation de la liste de destinataire
                        hashSet = new HashSet<AdresseMail>();
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
                        mail.setContenu("");
                        
                        //On defini le PDF comme envoyé
                        pdf.setSend(true);
                        
                        //Sauvegarde du PDF
                        service.saveOnePdf(pdf);
                        
                        //Sauvegarde du mail
                        service.saveOneMail(mail);
                    }
                }
                //On change d'adresseMail
                adresseMail = mainHashPdf.get(pdf);
            }
            
            //Affichage du rapport
            out.print("<table>");
            out.print("<tr><td><span style='font-weight : bold'>Rapport</span></td><td><a href=javascript:Ajax('Accueil','AccueilPaye','null')>RETOUR</a></td></tr>");
            
            //Affichage de réussite
            out.print("<tr><td><a href=javascript:Ajax('Accueil','Historique','null')>Mise à jour de l'historique : </a></td><td><span style='color: green; font-weight : bold'>SUCESS</span></td></tr>");
            out.print("<tr><td>PDF retiré de la liste active : </td><td><span style='color: green; font-weight : bold'>SUCESS</span></td></tr>");
            out.print("</table>");
            
            //Message success
            MessageAlert message = new MessageAlert("SUCCESS",MessageAlert.VALIDATION);
            out.print(message.getFLUX());
            
            //Traitement des erreurs
        }  catch (MessagingException e) {
            MessageAlert message = new MessageAlert("Probleme : <br><br>"+e.getMessage(),MessageAlert.ERREUR);
            out.print(message.getFLUX());
            e.printStackTrace();
        }catch (DaoCreateException e) {
            MessageAlert message = new MessageAlert("Probleme : <br><br>"+e.getMessage(),MessageAlert.ERREUR);
            out.print(message.getFLUX());
            e.printStackTrace();
        }
        //Fermeture du flux de sortie
        out.flush();
        out.close();
    }
}
