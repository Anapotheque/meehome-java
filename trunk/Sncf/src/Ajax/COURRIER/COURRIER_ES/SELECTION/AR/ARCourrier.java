package Ajax.COURRIER.COURRIER_ES.SELECTION.AR;

import Ajax.COURRIER.COURRIER_ES.SELECTION.Onglets.OngletCourrier;
import Models.COURRIER.COURRIER_ES.CourriersDAO;
import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Controleur.MegaControleur;
import Models.TOOLS.Utilisateurs.UtilisateursDAO;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class ARCourrier extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        UtilisateursDAO utilisateur = null;
        utilisateur = creerObjet(request,utilisateur);
        
        CourriersDAO courrier = null;
        courrier = creerObjet(request,courrier);
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        OngletCourrier.getOnglet(request,out,"AR","EnregistrementAR");
        out.println("<br>");
        out.println("<div id=save>");
        out.println("</div>"); //FIN save
        
        out.println("<br><br>");
        
        TOOLS_HTML.getMessage(out,"h2","Date de l'indice");
        out.println("<input id=MyDate value='"+courrier.dateIndice+"' name=MyDate style='width:145px' onClick=toggleCalendar('MyDate')> ");
        
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
