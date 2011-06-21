package Ajax.COURRIER.COURRIER_DEPART.LISTE;

import Models.COURRIER.COURRIERDEPART.CourrierDepartDAO;
import Models.ETUDES.Agents.AgentsDAO;
import Models.TOOLS.Ajaxmodels.COURRIERDEPART.ShowCourrierDepartDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.ButtonDAO;
import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Controleur.MegaControleur;
import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class ListAffparCodeAgent extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try {
            
            String codeAgent = request.getParameter("codeAgent");
            
            CourrierDepartDAO courrier = null;
            courrier = creerObjet(request,courrier);
            
            AgentsDAO agent = null;
            agent = creerObjet(request, agent);
            
            TOOLS_HTML.getMessage(out,"h2","AGENT : "+agent.GetnomAgent(codeAgent));
            
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCourrier('data','ListeParCodeAgent','choix_menu=Null','null')","RETOUR");
            if(courrier.GetListCourrierEnregistrer("parCodeAgent",codeAgent))
                ShowCourrierDepartDAO.Show(request,out,"CourrierDepart",courrier.list_Titre,courrier.list,courrier.list_Code,courrier.list_Size,false);
            else
                TOOLS_HTML.getMessage(out,"h2","IL N'Y A AUCUNES DONNEES POUR CET AGENT");
            out.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
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
