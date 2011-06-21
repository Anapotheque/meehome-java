
package Ajax.CALQUES.SELECTION.HISTORIQUE.AGENTS;

import Models.CALQUES.HistoriqueCalquesDAO;
import Models.ETUDES.Agents.AgentsDAO;
import Models.TOOLS.Ajaxmodels.ShowDAO;
import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Controleur.MegaControleur;
import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class AffichageInformationCalquesAgents extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        try {
            
            //RECUPERATION DES OBJETS
            HistoriqueCalquesDAO historique = null;
            historique = creerObjet(request, historique);
            
            AgentsDAO agent = null;
            agent = creerObjet(request, agent);
            
            //GET DATA ON RECUPERE LE CODE AGENT
            String codeAgent = request.getParameter("codeAgent");
            
            //INITIALISATION DU FLUX DE COMMUNICATION AVEC LA PAGE JSP
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            if(historique.Search(codeAgent,"Agent"))
                ShowDAO.Show(out,"HISTORIQUE DES CALQUES POUR L'AGENT : "+agent.GetnomAgent(codeAgent),historique.list_Titre,historique.list,historique.list_Size);
            else
                TOOLS_HTML.getMessage(out,"h2","AUCUN HISTORIQUE");
            
            //FERMETURE DU FLUX DE SORTIE
            out.close();
            
        } catch (SQLException ex) {
            retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState());
            System.out.println("\n\nDEBUT ERREUR<==========================================\n");
            ex.printStackTrace();
            System.out.println("\nFIN ERREUR<==============================================\n\n");
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
