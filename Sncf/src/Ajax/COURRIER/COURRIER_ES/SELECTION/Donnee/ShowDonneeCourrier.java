package Ajax.COURRIER.COURRIER_ES.SELECTION.Donnee;

import Ajax.COURRIER.COURRIER_ES.SELECTION.Onglets.OngletCourrier;
import Models.COURRIER.COURRIER_ES.CourriersDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.InputDAO;
import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Ajaxmodels.OBJETS.TextAreaDAO;
import Models.TOOLS.Controleur.MegaControleur;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class ShowDonneeCourrier extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //CHARGEMENT DES OBJETS
        CourriersDAO courrier = null;
        courrier = creerObjet(request,courrier);
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
                
        OngletCourrier.getOnglet(request,out,"Donnee","EnregistrementDonnee");
        out.println("<br>");
        out.println("<div id=save>");
        out.println("</div>"); //FIN save
        
        TOOLS_HTML.getMessage(out,"h2","Destinataire");
        TextAreaDAO.TextArea(out,"Destinataire",1,1,"500",courrier.destinataire);
        out.println("<br>");
        TOOLS_HTML.getMessage(out,"h2","Indice");
        InputDAO.AddInput(out,"text","Indice","40px",courrier.indice);
        out.println("<br>");
        TOOLS_HTML.getMessage(out,"h2","Affaires");
        InputDAO.AddInput(out,"text","Affaires","500px",courrier.Num_Affaire);
        out.println("<br>");
        TOOLS_HTML.getMessage(out,"h2","Object");
        TextAreaDAO.TextArea(out,"Object",4,2,"500px",courrier.objet);
        out.println("<br>");
        TOOLS_HTML.getMessage(out,"h2","Detail");
        TextAreaDAO.TextArea(out,"Detail",4,2,"500px",courrier.detail);
        
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
