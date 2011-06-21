/*
 * Deconnexion.java
 *
 * Created on 20 mars 2007, 09:28
 */

package fr.alliance.docalliance.ajax.accueil;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.alliance.docalliance.controleur.MetaControleur;

/**
 * Servlet de deconnection, detruit la session en cours
 * @author Yves Le Rumeur, Romain Raballand
 * @version 1.0
 */

public class Deconnexion extends MetaControleur {
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        // Recupere la session
        HttpSession session = request.getSession(true);
        
        //Fermeture des sessions ouvertes
        session.invalidate();
        
        //Redirection vers la page d'accueil
        retourVue(request,response,"/index.jsp");
    }
    
}
