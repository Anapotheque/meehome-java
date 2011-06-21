package Ajax.TRAVAUX;

import Models.TOOLS.Ajaxmodels.OBJETS.ButtonDAO;
import Models.TOOLS.Ajaxmodels.GetMessageInformationDAO;
import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class AccueilTravaux extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //INITIALISATION DU FLUX DE COMMUNICATION AVEC LA PAGE JSP
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        //DECLARATION DU FORMULAIRE GENERAL
        out.println("<form name='Champ'>");
        
        //DIV DE MISE EN FORME CADRE BLANC
        out.println("<div id=DivSaisie>");
        
        //DIV DE RECHARGEMENT AJAX
        out.println("<div id=data>");
        
        //LIGNE ROUGE
        out.println("<div id=ajout>");
        GetMessageInformationDAO.getMessageInformation(out,"TexteInformation1",".: SNCF :: TRAVAUX :.<br>");
        out.println("</div>");  //FIN AJOUT
        
        //DIV DE MISE EN FORME CADRE GRIS
        out.println("<div id=Divcontenu>");
        
        //CONTENU DE LA PAGE AFFICHAGE
        TOOLS_HTML.OpenTable(out,"40%");
        out.println("<tr><td>");
        TOOLS_HTML.getMessage(out,"h2","TRAVAUX");
        out.println("</td><td>");
        TOOLS_HTML.getMessage(out,"h2","MARCHES");
        out.println("</td></tr><tr><td>");
        ButtonDAO.AddButtonStyle(out,1,"javascript:Ajax('data','SaisieTravaux')","AJOUTER");
        out.println("</td><td>");
        ButtonDAO.AddButtonStyle(out,1,"javascript:Ajax('data','SaisieMarches')","AJOUTER");
        out.println("</td></tr><tr><td>");
        ButtonDAO.AddButtonStyle(out,1,"javascript:Ajax('data','ModificationDataFicheTravail')","MODIFICATION");
        out.println("</td><td>");
        ButtonDAO.AddButtonStyle(out,1,"javascript:Ajax('data','ListeMarches')","LISTE");
        out.println("</td></tr><tr><td>");
        ButtonDAO.AddButtonStyle(out,1,"javascript:Ajax('data','ListeFicheTravail')","LISTE");
        out.println("</td></tr></table>");
        
        ButtonDAO.AddButtonStyle(out,1,"javascript:Ajax('data','AttributionNumOE')","DOSSIER");
        ButtonDAO.AddButtonStyle(out,1,"javascript:Ajax('informations','Quitter')","QUITTER");
        //FIN DU CONTENU DE LA PAGE
        
        out.println("</div>"); //FIN Divcontenu
        
        out.println("</div>"); //FIN Data
        out.println("</div>"); //FIN DivSaisie
        
        //FERMETURE DU FORMULAIRE GENERAL
        out.println("</form>");
        
        //FERMETURE DU FLUX DE SORTIE
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
