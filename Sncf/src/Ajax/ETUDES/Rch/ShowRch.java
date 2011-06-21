package Ajax.ETUDES.Rch;

import Models.TOOLS.Ajaxmodels.DVSG.AjaxModels_Onglets;
import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Controleur.MegaControleur;
import Models.ETUDES.Rch.RchDAO;
import Models.TOOLS.iText.DVSG.Impression;

import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class ShowRch extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //RECUPERATION DES OBJETS
        RchDAO rch = null;
        rch = creerObjet(request, rch);
        
        //GET DATA
        String choix = request.getParameter("choix_menu");
        
        //INITIALISATION DU FLUX DE COMMUNICATION AVEC LA PAGE JSP
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        //AFFICHAGE
        AjaxModels_Onglets.getOnglet(request,response,"Affichage","Rch");
        
        //REINITIALISATION DES DONNEES
        try {
            rch.Set(request);
        }        catch(SQLException ex) {
            retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState());
            System.out.println("\n\nDEBUT ERREUR<==========================================\n");
            ex.printStackTrace();
            System.out.println("\nFIN ERREUR<==============================================\n\n");
        }
        
        //GENERATION DU PDF LIE
        Impression.genererPDF(response, request,rch.widthsShow,rch.list_Titre,rch.list_show,"RCH");
        
        if(rch.list_show.size()!=0){
            if(choix.equals("Null"))
                TOOLS_HTML.NewButtonMenu(out,"Rch","Afficher","show");
            else if(choix.equals("show"))
                TOOLS_HTML.Show(request,out,"Rch",rch.list_Titre,rch.list_show,rch.list_CodeRch,rch.list_SizeTitre,rch.list_Titre.size(),false);
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
