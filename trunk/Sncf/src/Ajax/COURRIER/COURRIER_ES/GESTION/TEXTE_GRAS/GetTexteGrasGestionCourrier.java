package Ajax.COURRIER.COURRIER_ES.GESTION.TEXTE_GRAS;

import Ajax.COURRIER.COURRIER_ES.GESTION.ONGLET.Onglet_Courrier_Gestion;
import Models.COURRIER.COURRIER_ES.CourriersDAO;
import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Ajaxmodels.OBJETS.TextAreaDAO;
import Models.TOOLS.Controleur.MegaControleur;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class GetTexteGrasGestionCourrier extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        CourriersDAO courrier = null;
        courrier = creerObjet(request,courrier);
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        Onglet_Courrier_Gestion.getOnglet(request,out,"TEXTEGRAS","GestionSaveTexteGras");
        
        out.println("<div id=save>");
        out.println("</div>"); //FIN save
        
        TOOLS_HTML.getMessage(out,"h2","<br><br>Avertissement :");
        TextAreaDAO.TextArea(out,"Texte",25,2,"100%",courrier.texteGras);
        
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
