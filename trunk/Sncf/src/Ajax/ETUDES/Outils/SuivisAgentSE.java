package Ajax.ETUDES.Outils;

import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Controleur.MegaControleur;
import Models.ETUDES.Agents.AgentsDAO;
import Models.ETUDES.Outils.SuivisDAO;
import Models.TOOLS.iText.DVSG.Impression;
import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain

public class SuivisAgentSE extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //INITIALISATION DU FLUX DE COMMUNICATION AVEC LA PAGE JSP
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        //RECUPERATION DES OBJETS
        AgentsDAO agent = null;
        agent = creerObjet(request, agent);
        
        //GET DATA
        String choix = request.getParameter("choix_menu");
        
        if(choix.equals("Null")){
            
            //AFFICHAGE
            TOOLS_HTML.Search(request,response,out,"SuivisAgentSE",agent,false);
            out.close();
            
        }
        
        else if(choix.equals("SuivisAgentSE")){
            
            //GET DATA
            int codeAgent = Integer.parseInt(request.getParameter("codeAgent"));
            
            SuivisDAO suivis = null;
            suivis = creerObjet(request, suivis);
            
            boolean result = false;
            
            try {
                result = suivis.SetSuiviSE(codeAgent);
            } catch (SQLException ex) {
                retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState());
                System.out.println("\n\nDEBUT ERREUR<==========================================\n");
                ex.printStackTrace();
                System.out.println("\nFIN ERREUR<==============================================\n\n");
            }
            
            if(result){
                try {
                    //GENERATION DU PDF LIE
                    Impression.genererPDF(response, request,suivis.widthsShowSE,suivis.list_TitreSE,suivis.list_Show,"SUIVI "+agent.GetnomAgent(""+codeAgent)+" SE");
                } catch (ServletException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                
                TOOLS_HTML.Show(request,out,suivis.list_TitreSE,suivis.list_Show,suivis.list_SizeTitreSE,suivis.list_TitreSE.size());
            } else
                TOOLS_HTML.getMessage(out,"h3","AUCUN RESULTAT N'A ETE OBTENU VEUILLEZ RECOMMENCER");
            out.close();
            
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
