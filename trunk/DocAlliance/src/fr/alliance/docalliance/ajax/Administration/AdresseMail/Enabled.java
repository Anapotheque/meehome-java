/*
 * Enabled.java
 *
 * Created on 11 avril 2007, 13:54
 */

package fr.alliance.docalliance.ajax.Administration.AdresseMail;

import fr.alliance.docalliance.controleur.MetaControleur;
import fr.alliance.docalliance.dao.DaoCreateException;
import fr.alliance.docalliance.dao.DaoFindException;
import fr.alliance.docalliance.model.AdresseMail;
import fr.alliance.docalliance.service.IGlobalService;
import fr.alliance.docalliance.tools.enumeration.SearchAdresseMail;
import fr.alliance.docalliance.tools.html.message.MessageAlert;
import java.io.*;
import java.net.*;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Servlet pour la activation d'un element AdresseMail
 * @author Yves Le Rumeur, Romain Raballand
 * @version 1.0
 */
public class Enabled extends MetaControleur {
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        IGlobalService service = getService(request);
        
        //initialisation du flux de sortie
        PrintWriter out = response.getWriter();
        
        try {
            
            //Pour chaque element selectionné on supprime les données dans la base de données
            for(int i=1; i<=Integer.parseInt(request.getParameter("NombreSelect")); i++){
                AdresseMail adresseMail = service.getAdresseMailById(Integer.parseInt(request.getParameter("idAdresseMail"+i)));
                
                //On verifie que l'adresse n'est une adresse de user system
                if(service.getUserByAdresseMail(adresseMail) == null){
                    adresseMail.setActive(true);
                    service.saveOneAdresseMail(adresseMail);
                }
            }
            
            //On retourne l'affichage des informations.Ce retour depend entierement de la div de remplacement ajax.
            retourVue(request,response,"/Show?idfilter="+SearchAdresseMail.ALL);
            
            //Traitement des erreurs liées au modele de données ( Hibernate )
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
