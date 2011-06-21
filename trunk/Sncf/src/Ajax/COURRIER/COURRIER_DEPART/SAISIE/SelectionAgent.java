package Ajax.COURRIER.COURRIER_DEPART.SAISIE;

import Models.COURRIER.COURRIERDEPART.CourrierDepartDAO;
import Models.ETUDES.Agents.AgentsDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.ButtonDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.SelectDAO;
import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Controleur.MegaControleur;
import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class SelectionAgent extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            AgentsDAO agent = null;
            agent = creerObjet(request, agent);
            
            CourrierDepartDAO courrier = null;
            courrier = creerObjet(request, courrier);
            
            String retour = request.getParameter("retour");
            
            if(retour == null)
                courrier.codeAffaire = request.getParameter("codeAffaire");
            
            agent.Set();
            
            String temp = courrier.GetCodeLettre();
            int codeLettre = Integer.parseInt(temp)+1;
            
            TOOLS_HTML.getMessage(out,"h2","Votre numero de lettre est : "+codeLettre+"<br>");
            
            TOOLS_HTML.getMessage(out,"h2","Selectionnez un agent");
            
            SelectDAO.AddSelect(out,"Agents",agent.list_CodeAgent,agent.list_Nom,1,"onChange=javascript:getHTMLCodeRequestCourrier('data','SaisieDestinataire','choix_menu=Redirection','Champ')");
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCourrier('data','SelectionAffaire','choix_menu=Null','null')","RETOUR");
            
            
            out.close();
            
        } catch (SQLException ex) {
            retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState()+"&retour=AccueilTravaux");
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
