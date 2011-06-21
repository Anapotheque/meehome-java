/*
 * MailTampon.java
 *
 * Created on 20 mars 2007, 09:28
 */

package fr.alliance.docalliance.ajax.mail.tools;

import fr.alliance.docalliance.controleur.MetaControleur;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet permettant de vider un div lors de son appel via Ajax
 * @author Yves Le Rumeur, Romain Raballand
 * @version 1.0
 */

public class Vide extends MetaControleur {
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //Initialisation du flux de sortie
        PrintWriter out = response.getWriter();
        
        //Fermeture du flux de sortie
        out.flush();
        out.close();
    }
}
