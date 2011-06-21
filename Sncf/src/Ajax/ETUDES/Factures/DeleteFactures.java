package Ajax.ETUDES.Factures;

import Models.TOOLS.Controleur.MegaControleur;
import Models.ETUDES.Factures.FacturesDAO;
import Models.TOOLS.Utilisateurs.UtilisateursDAO;

import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class DeleteFactures extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        String code = request.getParameter("codeFacture");
        String ordre = request.getParameter("choix_menu");
        
        FacturesDAO facture = null;
        facture = creerObjet(request, facture);
        
        UtilisateursDAO utilisateur = null;
        utilisateur = creerObjet(request, utilisateur);
        
        if(ordre.equals("Delete")){
            try {
                
                //Actions du controleur ------------------------------------------------------------
                facture.Delete(code,"codeFacture","Factures"); // Suppression d'un N-Uplets'
                Mouchard(request, utilisateur,"Suppression","factures");
                //Operations effectuées avec succès------------------------------------------------
                
                retourVue(request, response,"/ShowFactures?choix_menu=Null");
                
            } catch(SQLException ex) {
                retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState());
                System.out.println("\n\nDEBUT ERREUR<==========================================\n");
                ex.printStackTrace();
                System.out.println("\nFIN ERREUR<==============================================\n\n");
                
            }
        } else if(ordre.equals("SupprAll")){
            
            try {
                facture.SupprAll();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            retourVue(request, response,"/ShowFactures?choix_menu=Null");
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
