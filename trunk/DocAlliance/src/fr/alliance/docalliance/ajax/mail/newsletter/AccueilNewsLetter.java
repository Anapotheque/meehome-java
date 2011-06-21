/*
 * AccueilNewsLetter.java
 *
 * Created on 20 mars 2007, 09:28
 */

package fr.alliance.docalliance.ajax.mail.newsletter;

import fr.alliance.docalliance.ajax.mail.tools.List_PDF;
import fr.alliance.docalliance.controleur.MetaControleur;
import fr.alliance.docalliance.tools.html.Data.GestionData;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Accueil de la gestion d'envoi des newsletter
 * @author Yves Le Rumeur, Romain Raballand
 * @version 1.0
 */

public class AccueilNewsLetter extends MetaControleur {
    
    private static final String TITRE = "NewsLetter";
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //Initialisation du flux de sortie
        PrintWriter out = response.getWriter();
        
        //Declaration du titre
        GestionData title = new GestionData("titre");
        title.add(TITRE);
        title.close();
        
        //Ecriture du flux
        out.print(title.getFLUX());
        out.print("<div id='AJAXMAINFRAME'>");
        out.print("<form id='champ'>");
        List_PDF.GetList(request,response, "NEWSLETTER");
        out.print("</form>");
        out.print("</div>");
        
        //Fermeture du flux de sortie
        out.flush();
        out.close();
    }
    
}
