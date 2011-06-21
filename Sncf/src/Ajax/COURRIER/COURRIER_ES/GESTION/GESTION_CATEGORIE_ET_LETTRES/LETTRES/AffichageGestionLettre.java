package Ajax.COURRIER.COURRIER_ES.GESTION.GESTION_CATEGORIE_ET_LETTRES.LETTRES;

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

public class AffichageGestionLettre extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        try {
            
            CourriersDAO courrier = null;
            courrier = creerObjet(request, courrier);
            
            courrier.codeCategorie = request.getParameter("code");
            
            courrier.SetLettre(courrier.codeCategorie);
            
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            out.println("<table><tr><td>");
            
            TOOLS_HTML.getMessage(out,"h2","LETTRES");
            SelectDAO.AddSelect(out,"Lettres",courrier.list_CodeLettre,courrier.list_nomLettre,10,false);
            
            out.println("</td><td>");
            out.println("<div id=GESTION_COURRIER_LETTRE_TABLE>");
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCourrier('GESTION_COURRIER_LETTRE_TABLE','AjouterLettre','choix_menu=Null','null')","Ajouter");
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCourrier('GESTION_COURRIER_LETTRE_TABLE','ModifierLettre','choix_menu=Null','Champ')","Modifier");
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCourrier('informations','SupprimerLettre','choix_menu=Null','Champ')","Supprimer");
            out.println("</div>");
            out.println("</td></tr></table>");
            
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
