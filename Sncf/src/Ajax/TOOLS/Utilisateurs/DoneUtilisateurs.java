package Ajax.TOOLS.Utilisateurs;

import Models.TOOLS.Ajaxmodels.DVSG.AjaxModels_Onglets;
import Models.TOOLS.Controleur.MegaControleur;


import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

//@author RABALLAND Romain
//@version v3.0

public class DoneUtilisateurs extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //Recuperation des données envoyées -----------------------------------------------
        
        String choix = new String(request.getParameter("choix_menu"));
        
        if(choix.equals("null")){
        
        try {
            
            //Definition du type de texte pour l'envoie à la page jsp -----------------------------------------------------
            
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            //Affichage des informations ------------------------------------------------------
            
            //On affiche les options menu liées à la base
            AjaxModels_Onglets.getOnglet(request,response,"Done","Utilisateurs");
            
            //ON affiche les informations concernant la table
            out.println("<h4>UTILISATEURS</h4>");
            out.println("<br><h1>L'operation s'est effectuée avec succés !</h1>");
            
            out.close();
            
        }
        //Gestion des erreurs -------------------------------------------------------------
        
        catch(Exception e) {
            retourVue(request, response,"/Erreur?message="+e.getMessage());
            e.getMessage();
            
        }
        
    }
    else if(choix.equals("inscription")){
    
                try {

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            out.println("<div id=TexteInformation5>L'operation s'est effectuée avec succés !<br>Veuillez attendre que votre administrateur active votre compte</div>");
            
            out.close();
        }
        catch(Exception e) {
            retourVue(request, response,"/Erreur?message="+e.getMessage());
            e.getMessage(); 
        }
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
