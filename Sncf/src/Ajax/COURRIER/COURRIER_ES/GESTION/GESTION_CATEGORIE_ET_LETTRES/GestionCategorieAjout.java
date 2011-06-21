package Ajax.COURRIER.COURRIER_ES.GESTION.GESTION_CATEGORIE_ET_LETTRES;

import Models.COURRIER.COURRIER_ES.CourriersDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.ButtonDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.SelectDAO;
import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Controleur.MegaControleur;
import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class GestionCategorieAjout extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        try {
            
            CourriersDAO courrier = null;
            courrier = creerObjet(request, courrier);
            
            courrier.SetCategorie();
            
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            out.println("<div id=DivSaisie>");
            out.println("<div id=DivFont>");
            
            
            
            out.println("<form name=Champ>");
            
            out.println("<table><tr>");
            
            out.println("<td>");
            TOOLS_HTML.getMessage(out,"h2","CATEGORIES");
            SelectDAO.AddSelect(out,"Categories",courrier.list_CodeCategorie,courrier.list_Categorie,10, "onChange=javascript:getHTMLCodeRequestCourrier('GetLettre','AffichageGestionLettre','choix_menu=Null','Champ')");
            out.println("</td>");
            
            out.println("<td>");
            out.println("<div id=GESTION_COURRIER_CATEGORIE_TABLE>");
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCourrier('GESTION_COURRIER_CATEGORIE_TABLE','AjouterCategorie','choix_menu=Null','null')","Ajouter");
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCourrier('GESTION_COURRIER_CATEGORIE_TABLE','ModifierCategorie','choix_menu=Null','Champ')","Modifier");
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCourrier('informations','SupprimerCategorie','choix_menu=Null','Champ')","Supprimer");
            out.println("</div>"); //FIN
            out.println("</td>");
            
            out.println("<td>");
            out.println("<div id=GetLettre>");
            out.println("</div>"); //FIN
            out.println("</td>");
            out.println("</tr></table>");
            
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCourrier('informations','index_Courrier_Es_Gestion','choix_menu=Null','null')","Retour");
            
            out.println("</form>");
            
            out.println("</div>"); //FIN DivFont
            out.println("</div>"); //FIN DivSaisie
            
            out.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
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
