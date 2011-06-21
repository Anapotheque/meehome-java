package Ajax.CALQUES.SELECTION.SORTIE;

import Models.CALQUES.CalquesDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.ButtonDAO;
import Models.TOOLS.Ajaxmodels.CALQUES.ShowCalquesInputDAO;
import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Controleur.MegaControleur;
import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class GetCalquesSelected extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        try {
            
            CalquesDAO calque = null;
            calque = creerObjet(request,calque);
            
            calque.codeCollection = request.getParameter("codeCollection");
            
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            out.println("<div id=DivSaisie>");
            
            if(calque.GetListCalquesNonSortis(calque.codeCollection)){
                TOOLS_HTML.getMessage(out,"h2","Selectionnez parmis les calques sortis dans cette collection");
                ShowCalquesInputDAO.show(out,calque.listCodeCalques,calque.listnumeroCalques,true);
            }else
                TOOLS_HTML.getMessage(out,"h2","AUCUNS CALQUES REFERENCES");
            
            out.println("</div>");  //FIN Divcontenu
            
            //DIV DE RECHARGEMENT AJAX
            out.println("<div id=Enregistrement>");
            
            TOOLS_HTML.OpenTable(out,"100%");
            out.println("<tr><td>");
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCalques('calques','GetCalquesSortis','choix_menu=Null','Champ')","CALQUES SORTIS");
            out.println("</td><td>");
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCalques('calques','GetCalques','choix_menu=Null','Champ')","TOUT DESELECTIONNER");
            out.println("</td><td>");
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCalques('informations','Quitter','choix_menu=Null','null')","DERNIERS RENTRES");
            out.println("</td><td>");
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCalques('calques','VoirCases','choix_menu=Null','Champ')","VOIR CASE");
            out.println("</td><td>");
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCalques('Enregistrement','SortirCalquesActions','choix_menu=Null','Champ')","SORTIR CALQUES");
            out.println("</td></tr></table>");
            
            out.println("</div>");  //FIN Enregistrement
            
            out.close();
            
        } catch (SQLException ex) {
            retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState());
            System.out.println("\n\nDEBUT ERREUR<==========================================\n");
            ex.printStackTrace();
            System.out.println("\nFIN ERREUR<==============================================\n\n");
        }
        
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
