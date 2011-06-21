package Ajax.COURRIER.COURRIER_DEPART.SAISIE;

import Models.COURRIER.COURRIERDEPART.CourrierDepartDAO;
import Models.ETUDES.Affaires.AffairesDAO;
import Models.ETUDES.Agents.AgentsDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.ButtonDAO;
import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Controleur.MegaControleur;
import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class NumeroLettre extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            
            CourrierDepartDAO courrier = null;
            courrier = creerObjet(request, courrier);
            
            AffairesDAO affaire = null;
            affaire = creerObjet(request, affaire);
            
            AgentsDAO agent = null;
            agent = creerObjet(request, agent);
            
            String designation = request.getParameter("designation");
            designation = TransformString(designation);
            designation = DetectionCaractere(designation);
            courrier.designation = designation;
            
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            String codeLettre = courrier.GetCodeLettre();
            courrier.codeLettre = Integer.parseInt(codeLettre)+1;
            
            TOOLS_HTML.getMessage(out,"h2","Votre numero de lettre est : "+courrier.codeLettre+"<br>");
            TOOLS_HTML.getMessage(out,"h2","Pour l'affaire : "+affaire.GetnomAffaire(courrier.codeAffaire)+"<br>");
            TOOLS_HTML.getMessage(out,"h2","Suivi par : "+agent.GetnomAgent(courrier.codeAgent)+"<br>");
            
            TOOLS_HTML.getMessage(out,"h2","Destinataire : "+courrier.destinataire+"<br>");
            TOOLS_HTML.getMessage(out,"h2","Designation : "+courrier.designation+"<br>");
            
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCourrier('data','EnregistrerLettre','choix_menu=Null','null')","ENREGISTRER");
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCourrier('data','Saisie_Designation','retour=retour','null')","RETOUR");
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCourrier('informations','Quitter','choix_menu=Null','null')","QUITTER");
            
            out.close();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
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
