package Ajax.ETUDES.Outils;


import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Controleur.MegaControleur;
import Models.ETUDES.Outils.HistoriqueGaresDAO;

import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class Outils extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //INITIALISATION DU FLUX DE COMMUNICATION AVEC LA PAGE JSP
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<div id='corps'>");
        
        TOOLS_HTML.OpenDiv(out,"DivLoad");
        TOOLS_HTML.getMessage(out,"h2","VOTRE PAGE EST EN COURS DE CHARGEMENT VUEILLEZ PATIENTER");
        TOOLS_HTML.CloseDiv(out);
        
        TOOLS_HTML.getMessage(out,"h4","ETUDES SIGNALISATION");
        
        TOOLS_HTML.getMessage(out,"h5","SNCF.DVSG.OUTILS");
        
        out.println("<table><tr>");
        
        TOOLS_HTML.AddButtonMenuTable(out,"Qualites","QUALITE");
        TOOLS_HTML.AddButtonMenuTable(out,"HistoriquesPT","HISTORIQUE PT");
        TOOLS_HTML.AddButtonMenuTable(out,"HistoriquesSE","HISTORIQUE SE");
        
        out.println("</tr>");
        out.println("<tr>");
        
        TOOLS_HTML.AddButtonMenuTable(out,"SuivisAgentPT","SUIVI PAR AGENT PT");
        TOOLS_HTML.AddButtonMenuTable(out,"SuivisAgentSE","SUIVI PAR AGENT SE");
        TOOLS_HTML.AddButtonMenuTable(out,"#","SUIVI PAR AFFAIRE");
        
        out.println("</tr>");
        out.println("<tr>");
        
        out.println("<td colspan='3'>");
        TOOLS_HTML.NewButtonMenu(out,"IndexDVSG","RETOUR");
        out.println("</td>");
        
        out.println("</tr>");
        out.println("</table>");
        
        out.close();
        
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
}
