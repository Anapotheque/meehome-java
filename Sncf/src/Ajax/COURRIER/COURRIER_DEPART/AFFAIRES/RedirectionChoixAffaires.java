package Ajax.COURRIER.COURRIER_DEPART.AFFAIRES;

import Models.TOOLS.Controleur.MegaControleur;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class RedirectionChoixAffaires extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        String filtre = request.getParameter("Filtre");
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        if(filtre.equals("0")){
            retourVue(request,response,"/AffairesParCode");
        }
        else{
            retourVue(request,response,"/AffairesParObjet");
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
