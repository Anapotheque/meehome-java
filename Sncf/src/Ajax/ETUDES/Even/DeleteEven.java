package Ajax.ETUDES.Even;


import Models.TOOLS.Controleur.MegaControleur;
import Models.ETUDES.Even.EvenDAO;
import Models.TOOLS.Utilisateurs.UtilisateursDAO;

import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

//@author RABALLAND Romain ------------------------------------------------------------------------------
//@version v3.0 ------------------------------------------------------------------------------------------------------------

public class DeleteEven extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        String code = new String(request.getParameter("codeEven"));
        
        try {
            
            //Creation de l'objet associ� -----------------------------------------------------
            
            EvenDAO even = null;
            even = creerObjet(request, even);
            
            UtilisateursDAO utilisateur = null;
            utilisateur = creerObjet(request, utilisateur);
            
            //Actions du controleur ------------------------------------------------------------
            
            even.Delete(code,"codeEven","Even"); // Suppression d'un N-Uplets'
            Mouchard(request, utilisateur,"Suppression","even");
            //Operations effectu�es avec succ�s------------------------------------------------
            
            retourVue(request, response,"/ShowEven?choix_menu=Null");
            
        }
        
        catch(SQLException ex) {
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
