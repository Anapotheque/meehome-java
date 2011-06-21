package Ajax.COURRIER.COURRIER_ES.GESTION.GESTION_CATEGORIE_ET_LETTRES.ACTIONS;

import Models.COURRIER.COURRIER_ES.CourriersDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.ButtonDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.InputDAO;
import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Controleur.MegaControleur;
import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class ModifierCategorie extends MegaControleur {
    
    String code = "";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        CourriersDAO courrier = null;
        courrier = creerObjet(request, courrier);
        
        
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try {
            
            if(request.getParameter("Categorie") == null){

                code = request.getParameter("code");
                TOOLS_HTML.getMessage(out,"h2","Modifier le nom de la catégorie : ");
                InputDAO.AddInput(out,"text","categorie","300px",courrier.GetnomCategorie(request.getParameter("code")));
                ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCourrier('informations','ModifierCategorie','Enregistrer','Champ')","Enregistrer");
                ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCourrier('informations','GestionCategorieAjout','choix_menu=Null','null')","Retour");
                
            } else{

                
                
                courrier.ModificationCategorie(request.getParameter("Categorie"),code);
                retourVue(request,response,"/GestionCategorieAjout");

            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
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
