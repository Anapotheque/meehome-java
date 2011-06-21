package Ajax.COURRIER.COURRIER_ES.GESTION;

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

public class index_Courrier_Es_Gestion extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            
            CourriersDAO courrier = null;
            courrier = creerObjet(request,courrier);
            
            courrier.SetCategorie();
            
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            out.println("<form name=Champ>");
            
            out.println("<div id=DivSaisie>");
            out.println("<div id=DivFont>");
            
            out.println("<div style='text-align: right'>");
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCourrier('informations','Accueil_COURRIER','choix_menu=Null','null')","Retour");
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCourrier('informations','GestionCategorieAjout','choix_menu=Null','null')","Gestion");
            out.println("</div>");
            
            TOOLS_HTML.getMessage(out,"h2","Categorie de lettre");
            SelectDAO.AddSelect(out,"Categories",courrier.list_CodeCategorie,courrier.list_Categorie,1,"onChange=javascript:getHTMLCodeRequestCourrier('TypeLettreGestion','GetCourrierGestionTypeLettre','choix_menu=Null','Champ')");
            
            out.println("<div id=TypeLettreGestion>");
            out.println("</div>"); //FIN 
            
            out.println("<div id=dataGestion>");
            out.println("</div>"); //FIN 
            
            out.println("</div>"); //FIN DivFont
            out.println("</div>"); //FIN DivSaisie
            
            out.println("</form>");
            
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
