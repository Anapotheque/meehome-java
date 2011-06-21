package Ajax.ETUDES.Affaires;

import Models.TOOLS.Ajaxmodels.DVSG.AjaxModels_Onglets;
import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Controleur.MegaControleur;
import Models.ETUDES.Affaires.AffairesDAO;
import Models.TOOLS.iText.DVSG.Impression;

import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

//@author RABALLAND Romain ------------------------------------------------------------------------------
//@version v3.0 ------------------------------------------------------------------------------------------------------------

public class ShowAffaires extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //RECUPERATION DES OBJETS
        AffairesDAO affaire = null;
        affaire = creerObjet(request, affaire);
        
        //GET DATA
        String choix = request.getParameter("choix_menu");
        
        //INITIALISATION DU FLUX DE COMMUNICATION AVEC LA PAGE JSP
        response.setContentType("text/html;charset=ISO-8859-1");
        PrintWriter out = response.getWriter();
        
        //AFFICHAGE
        AjaxModels_Onglets.getOnglet(request,response,"Affichage","Affaires");
        
        //GENERATION DU PDF LIE
        Impression.genererPDF(response, request,affaire.widthsShow,affaire.list_TitreShow,affaire.list_show,"AFFAIRES ACTIVES");
        
        //REINITIALISATION DES DONNEES
        try {
            affaire.Set();
        } catch (SQLException ex) {
            retourVue(request, response,"/Erreur?message="+ex.getMessage());
            ex.printStackTrace();
        }
        
        if(affaire.list_show.size()!=0){
            if(choix.equals("Null"))
                TOOLS_HTML.NewButtonMenu(out,"Affaires","Afficher","show");
            else if(choix.equals("show"))
                TOOLS_HTML.Show(request,out,"Affaires",affaire.list_TitreShow,affaire.list_show,affaire.list_CodeAffaire,affaire.list_SizeTitre_Show,affaire.list_TitreShow.size(),false);
        }else
            TOOLS_HTML.getMessage(out,"h1","La table ne contient aucun elements veuillez renseigner la table");
        
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
