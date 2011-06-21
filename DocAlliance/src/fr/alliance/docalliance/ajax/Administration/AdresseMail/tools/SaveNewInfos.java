/*
 * SaveNewInfos.java
 *
 * Created on 30 mars 2007, 17:09
 */

package fr.alliance.docalliance.ajax.Administration.AdresseMail.tools;

import fr.alliance.docalliance.controleur.MetaControleur;
import fr.alliance.docalliance.dao.DaoCreateException;
import fr.alliance.docalliance.dao.DaoFindException;
import fr.alliance.docalliance.model.AdresseMail;
import fr.alliance.docalliance.service.IGlobalService;
import fr.alliance.docalliance.tools.html.message.MessageAlert;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * Servlet pour l'upload, le traitement et la sauvegarde d'adresse contenu dans le fichier
 * @author Yves Le Rumeur, Romain Raballand
 * @version
 */
public class SaveNewInfos extends MetaControleur {
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        IGlobalService service = getService(request);
        
        // Recuperation de la session
        HttpSession session = request.getSession(true);
        
        //initialisation du flux de sortie
        PrintWriter out = response.getWriter();
        
        //Recuperation des informations du formulaire
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String adresse = request.getParameter("adresseMail");
        String active = request.getParameter("active");
        
        //Creation d'un nouvel objet AdresseMail et Set des valeurs
        AdresseMail adresseMail = null;
        
        if(session.getAttribute("adresseMail") != null){
            adresseMail = (AdresseMail)session.getAttribute("adresseMail");
        } else{
            adresseMail = new AdresseMail();
        }
        
        adresseMail.setFirstName(nom);
        adresseMail.setLastName(prenom);
        adresseMail.setAdresseMail(adresse);
        
        if(active.equals("true")){
            adresseMail.setActive(true);
        }else{
            adresseMail.setActive(false);
        }
        
        //Sauvegarde de l'adresseMail
        try {
            
            //Si on vient de modifier on verifie que l'ojet soit en session
            //On verifie que l'adresse n'existe pas deja
            if(service.getAdresseMailByNomPrenom(nom,prenom)== null || session.getAttribute("adresseMail") != null){
                
                service.saveOneAdresseMail(adresseMail);
                
                //Message de confirmation d'enregistrement
                MessageAlert message = new MessageAlert("Les données ont été correctement enregistrés",MessageAlert.VALIDATION);
                out.print(message.getFLUX());
                
            } else{
                
                //Message de confirmation d'enregistrement
                MessageAlert message = new MessageAlert("L'adresse est deja présente",MessageAlert.WARNING);
                out.print(message.getFLUX());
                
            }
            
        } catch (DaoFindException e) {
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
