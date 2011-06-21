package Ajax.COURRIER.COURRIER_ES.GESTION;

import Models.COURRIER.COURRIER_ES.CourriersDAO;
import Models.TOOLS.Controleur.MegaControleur;
import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class RedirectGestion extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        try {
            
            CourriersDAO courrier = null;
            courrier = creerObjet(request,courrier);
            
            String codeLettre = request.getParameter("codeLettre");
            courrier.codeLettre = codeLettre;
            
            courrier.GetTexteLettre();
            courrier.SetDefautSettingGESTION();
            
            retourVue(request,response,"/GetTexteGestionCourrier");
            
        } catch (SQLException ex) {
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
