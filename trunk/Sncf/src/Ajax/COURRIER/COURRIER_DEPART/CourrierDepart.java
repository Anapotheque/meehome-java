package Ajax.COURRIER.COURRIER_DEPART;

import Models.TOOLS.Ajaxmodels.OBJETS.ButtonDAO;
import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class CourrierDepart extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<form name=Champ>");
        
        out.println("<div id=DivSaisie>");
        out.println("<div id=Divcontenu>");
        
        TOOLS_HTML.OpenDiv(out,"data");
        
        ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCourrier('data','SelectionAffaire','choix_menu=Null','null')","SAISIE");
        ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCourrier('data','AffaireChoix','choix_menu=AffChoixAgent','null')","AFFAIRES");
        ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCourrier('data','ListeChoix','choix_menu=AffChoixAgent','null')","LISTE");
        ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCourrier('informations','Accueil_COURRIER','choix_menu=Null','null')","QUITTER");

        out.println("</div>");
        
        out.println("</div>"); //FIN Divcontenu
        out.println("</div>"); //FIN DivSaisie
        
         out.println("</form>");
        
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
