package Ajax.COURRIER.COURRIER_ES.SELECTION.Texte;

import Models.COURRIER.COURRIER_ES.CourriersDAO;
import Ajax.COURRIER.COURRIER_ES.SELECTION.Onglets.OngletCourrier;
import Models.TOOLS.Ajaxmodels.OBJETS.TextAreaDAO;
import Models.TOOLS.Controleur.MegaControleur;
import Models.TOOLS.Utilisateurs.UtilisateursDAO;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class TexteCourrier extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        try {
            
            //CHARGEMENT DES OBJETS
            CourriersDAO courrier = null;
            courrier = creerObjet(request,courrier);
            
            UtilisateursDAO utilisateur = null;
            utilisateur = creerObjet(request,utilisateur);
            
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            OngletCourrier.getOnglet(request,out,"Texte","EnregistrementTexte");
            out.println("<br>");
            out.println("<div id=save>");
            out.println("</div>"); //FIN save
            
            TextAreaDAO.TextArea(out,"texte",25,2,"100%",courrier.texte);
            TextAreaDAO.TextArea(out,"textegras",1,2,"100%",courrier.texteGras);
            
            out.close();
            
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
