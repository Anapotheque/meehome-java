package Ajax.COURRIER.COURRIER_ES.SELECTION.Suivi;

import Ajax.COURRIER.COURRIER_ES.SELECTION.Onglets.OngletCourrier;
import Models.COURRIER.COURRIER_ES.CourriersDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.SelectDAO;
import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Controleur.MegaControleur;
import Models.TOOLS.Utilisateurs.UtilisateursDAO;
import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class Suivi extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //CHARGEMENT DES OBJETS
        CourriersDAO courrier = null;
        courrier = creerObjet(request,courrier);
        
        UtilisateursDAO utilisateur = null;
        utilisateur = creerObjet(request,utilisateur);
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        OngletCourrier.getOnglet(request,out,"Suivi","EnregistrementSuivi");
        out.println("<br>");
        out.println("<div id=save>");
        out.println("</div>"); //FIN save
        
        ArrayList list_sesco = null;
        list_sesco = new ArrayList();
        
        for(int i = 1 ; i <= 6 ; i ++ )
            list_sesco.add("A"+i);
        for(int i = 1 ; i <= 6 ; i ++ )
            list_sesco.add("E"+i);
        for(int i = 1 ; i <= 6 ; i ++ )
            list_sesco.add("K"+i);
        
        out.println("<br><br><br>");
        
        TOOLS_HTML.getMessage(out,"h2","Selectionnez un SESCO");
        
        if(courrier.numeroSesco.equals(""))
            SelectDAO.AddSelect(out,"sesco",list_sesco,list_sesco,1);
        else
            SelectDAO.GetSelect(out,"sesco",courrier.numeroSesco,courrier.numeroSesco,list_sesco,list_sesco,1);
        
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
