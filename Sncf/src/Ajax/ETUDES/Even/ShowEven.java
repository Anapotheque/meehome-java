package Ajax.ETUDES.Even;

import Models.TOOLS.Ajaxmodels.DVSG.AjaxModels_Onglets;
import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Controleur.MegaControleur;
import Models.ETUDES.Even.EvenDAO;
import Models.TOOLS.iText.DVSG.Impression;

import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

//@author RABALLAND Romain ------------------------------------------------------------------------------
//@version v3.0 ------------------------------------------------------------------------------------------------------------

public class ShowEven extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //RECUPERATION DES OBJETS
        EvenDAO even = null;
        even = creerObjet(request, even);
        
        //GET DATA
        String choix = request.getParameter("choix_menu");
        
        //REINITIALISATION DES DONNEES
        try {
            even.Set();
        }         catch(SQLException ex) {
            retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState());
            System.out.println("\n\nDEBUT ERREUR<==========================================\n");
            ex.printStackTrace();
            System.out.println("\nFIN ERREUR<==============================================\n\n");
        }
        
        //GENERATION DU PDF LIE
        Impression.genererPDF(response, request,even.widthsShow,even.list_Titre,even.list_show,"EVEN ACTIVES");
        
        
        //INITIALISATION DU FLUX DE COMMUNICATION AVEC LA PAGE JSP
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        //AFFICHAGE
        AjaxModels_Onglets.getOnglet(request,response,"Affichage","Even");
        
        if(even.list_show.size()!=0){
            if(choix.equals("Null"))
                TOOLS_HTML.NewButtonMenu(out,"Even","Afficher","show");
            else if(choix.equals("show"))
                TOOLS_HTML.Show(request,out,"Even",even.list_Titre,even.list_show,even.list_CodeEven,even.list_SizeTitre,even.list_Titre.size(),false);
        } else
            TOOLS_HTML.getMessage(out,"h1","La table ne contient aucun elements veuillez renseigner la table");
        
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
