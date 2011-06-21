package Ajax.CALQUES.GESTION.ACTIONS.AJOUT.CALQUE;

import Models.CALQUES.CalquesDAO;
import Models.CALQUES.CasesDAO;
import Models.TOOLS.Controleur.MegaControleur;
import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class EnregistrerAjout extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        try {
            
            CalquesDAO calque = null;
            calque = creerObjet(request,calque);
            
            CasesDAO cases = null;
            cases = creerObjet(request,cases);
            
            String entete = request.getParameter("enteteCalque");
            String numeroCalque = request.getParameter("numeroCalque");
            
            if(entete.equals("Execution"))
                numeroCalque = "E"+numeroCalque;
            else if ( entete.equals("Plan"))
                numeroCalque = "P"+numeroCalque;
            
            calque.New(numeroCalque,cases.codeCase);
            
            retourVue(request,response,"/AjoutCalques?entete="+entete);
            
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
