package Ajax.TOOLS.Utilisateurs;

import Models.TOOLS.Controleur.MegaControleur;
import Models.TOOLS.Utilisateurs.UtilisateursDAO;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

//@author RABALLAND Romain ------------------------------------------------------------------------------
//@version v3.0 ------------------------------------------------------------------------------------------------------------

public class DeleteUtilisateurs extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        String code = new String(request.getParameter("codeUser"));
        
        try {
            
            //Creation de l'objet associé -----------------------------------------------------
            
            UtilisateursDAO utilisateur = null;
            utilisateur= creerObjet(request, utilisateur);
            
            //Actions du controleur ------------------------------------------------------------
            
            utilisateur.Delete(code,"codeUser","Users"); // Charge toutes les informations
            
            //Operations effectuées avec succès------------------------------------------------
            
            retourVue(request, response,"/ShowUtilisateurs");
            
        }
        
        catch(Exception e) {
            retourVue(request, response,"/Erreur?message="+e.getMessage());
            e.getMessage();
            
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
