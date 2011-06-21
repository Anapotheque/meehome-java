package Ajax.ETUDES.Agents;

import Models.TOOLS.Ajaxmodels.DVSG.AjaxModels_Onglets;
import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Controleur.MegaControleur;
import Models.ETUDES.Agents.AgentsDAO;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class NewAgents extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //RECUPERATION DES OBJETS
        AgentsDAO agent = null;
        agent = creerObjet(request, agent);
        
        //GET DATA
        String choix = request.getParameter("choix_menu");
        
        if(choix.equals("Null")){
            
            //INITIALISATION DU FLUX DE COMMUNICATION AVEC LA PAGE JSP
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            //AFFICHAGE
            AjaxModels_Onglets.getOnglet(request,response,"Creation","Agents");
            TOOLS_HTML.New(out,agent.list_New,agent.list_Ressource,agent.list_NewMod_taille,agent.list_NewMod_tailleMax,agent.list_Value,"Agents");
            out.close();
        }
        
        else if(choix.equals("New")){
            
            ArrayList list = new ArrayList();
            
            for(int i=0; i < agent.list_New.size(); i++)
                list.add(request.getParameter(""+agent.list_New.get(i)+""));
            
            list = TransformString(list);
            
            try {
                agent.New(list);
            }
            
            catch(SQLException ex) {
                retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState()+"&retour=NewAgents");
                System.out.println("\n\nDEBUT ERREUR<==========================================\n");
                ex.printStackTrace();
                System.out.println("\nFIN ERREUR<==============================================\n\n");
                
            }
            retourVue(request, response,"/ShowAgents?choix_menu=Null");
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

