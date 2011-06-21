package Ajax.ETUDES.Entreprises;

import Models.TOOLS.Ajaxmodels.DVSG.AjaxModels_Onglets;
import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Controleur.MegaControleur;
import Models.ETUDES.Entreprises.EntreprisesDAO;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

//@author RABALLAND Romain ------------------------------------------------------------------------------
//@version v3.0 ------------------------------------------------------------------------------------------------------------

public class NewEntreprises extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //RECUPERATION DES OBJETS
        EntreprisesDAO entreprise = null;
        entreprise = creerObjet(request, entreprise);
        
        //GET DATA
        String choix = request.getParameter("choix_menu");
        
        if(choix.equals("Null")){
            
            //INITIALISATION DU FLUX DE COMMUNICATION AVEC LA PAGE JSP
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            //AFFICHAGE
            AjaxModels_Onglets.getOnglet(request,response,"Creation","Entreprises");
            TOOLS_HTML.New(out,entreprise.list_New,entreprise.list_Ressource,entreprise.list_NewMod_taille,entreprise.list_NewMod_tailleMax,entreprise.list_Value,"Entreprises");
            TOOLS_HTML.CloseDiv(out);
            out.close();
        }
        
        else if(choix.equals("New")){
            
            ArrayList list = new ArrayList();
            
            for(int i=0; i < entreprise.list_New.size(); i++)
                list.add(request.getParameter(""+entreprise.list_New.get(i)+""));
            
            list = TransformString(list);
            
            try {
                entreprise.New(list);
            }
            
            catch(SQLException ex) {
                retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState()+"&retour=NewEntreprises");
                System.out.println("\n\nDEBUT ERREUR<==========================================\n");
                ex.printStackTrace();
                System.out.println("\nFIN ERREUR<==============================================\n\n");
            }
            retourVue(request, response,"/ShowEntreprises?choix_menu=Null");
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
