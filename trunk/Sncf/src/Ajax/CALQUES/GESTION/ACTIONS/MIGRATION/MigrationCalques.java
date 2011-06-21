package Ajax.CALQUES.GESTION.ACTIONS.MIGRATION;

import Models.CALQUES.CalquesDAO;
import Models.CALQUES.CasesDAO;
import Models.ETUDES.Gares.GaresDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.ButtonDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.InputDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.SelectDAO;
import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Controleur.MegaControleur;
import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class MigrationCalques extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            
            //RECUPERATION DES OBJETS
            CasesDAO cases = null;
            cases = creerObjet(request,cases);
            
            CalquesDAO calque = null;
            calque = creerObjet(request,calque);
            
            GaresDAO gare = null;
            gare = creerObjet(request, gare);
            
            //RECUPERATION DE TOUT LES CODES CASES
            cases.GetListCases(calque.codeCollection);
            
            //INITIALISATION DU FLUX DE COMMUNICATION AVEC LA PAGE JSP
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            TOOLS_HTML.OpenTable(out,"100%");
            out.println("<th style='width: 20%'>");
            TOOLS_HTML.getMessage(out,"h2","COLLECTION");
            out.println("</th>");
            out.println("<th style='width: 20%'>");
            TOOLS_HTML.getMessage(out,"h2","CASE");
            out.println("</th>");
            
            out.println("<tr><td>");
            //AFFICHAGE DE LA SELECTION D'UNE COLLECTION
            SelectDAO.GetSelect(out,"collectionSelection",calque.codeCollection,gare.GetnomGare(calque.codeCollection),gare.list_CodeGare,gare.list_Gare,1,"onChange=javascript:getHTMLCodeRequestCalques('GetCases','GetSelectedCases','choix_menu=Null','Champ')");
            out.println("</td><td>");
            
            //DIV DE RECHARGEMENT AJAX
            out.println("<div id=GetCases>");
            //AFFICHAGE DE LA LISTE DES COLLECTIONS DISPONIBLES
            SelectDAO.GetSelect(out,"casesSelection",cases.codeCase,cases.GetNumeroCases(cases.codeCase),cases.listCodeCases,cases.listnumeroCases,1);
            out.println("</div>"); //FIN GetCases
            
            out.println("</td><td>");
            
            //MENU DE SELECTION
            out.println("<td>");
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCalques('Enregistrement','EnregistrerMigration','choix_menu=Null','Champ')","MIGRER");
            out.println("</td><td>");
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCalques('calques','GetCalquesGestions','SauvegardeCodeCalque_OR_Case=YES','Null')","RETOUR");
            out.println("</td></table>");
            
            out.close();
            
//----------------------------------------------------------------------------------------------------------------
//    GESTION DES ERREURS
//----------------------------------------------------------------------------------------------------------------

            
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
