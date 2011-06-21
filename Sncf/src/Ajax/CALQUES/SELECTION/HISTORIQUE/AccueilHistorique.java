package Ajax.CALQUES.SELECTION.HISTORIQUE;

import Models.TOOLS.Ajaxmodels.OBJETS.ButtonDAO;
import Models.TOOLS.Ajaxmodels.GetMessageInformationDAO;
import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;


public class AccueilHistorique extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //INITIALISATION DU FLUX DE COMMUNICATION AVEC LA PAGE JSP
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        //LIGNE ROUGE
        out.println("<div id=ajout>");
        GetMessageInformationDAO.getMessageInformation(out,"TexteInformation1",".: SNCF :: CALQUES :: RETOUR :.<br>");
        out.println("</div>");  //FIN AJOUT
        
        //DIV DE MISE EN FORME CADRE GRIS
        out.println("<div id=Divcontenu>");
        
        //DIV DE RECHARGEMENT AJAX
        out.println("<div id=Enregistrement>");
        
        TOOLS_HTML.getMessage(out,"h2","RECHERCHE DE CALQUES SELON : ");
        
        TOOLS_HTML.OpenTable(out,"30%");
        out.println("<tr><td>");
        ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCalques('Enregistrement','SearchAgent','','')","AGENT");
        out.println("</td><td>");
        ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCalques('Enregistrement','SearchGaresCalques','','')","GARE");
        out.println("</td><td>");
        ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCalques('Enregistrement','SearchEntreprisesCalques','','')","ENTREPRISE");
        out.println("</td></tr></table>");
        
        ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCalques('informations','IndexCalques','choix_menu=Null','Null')","INDEX");

        out.println("</div>");  //FIN Enregistrement
        
        out.println("</div>");  //FIN Divcontenu
        
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
