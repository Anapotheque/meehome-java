package Ajax.COURRIER.COURRIER_DEPART.AFFAIRES;

import Models.ETUDES.Affaires.AffairesDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.ButtonDAO;
import Models.TOOLS.Ajaxmodels.ShowDAO;
import Models.TOOLS.Controleur.MegaControleur;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class AffairesParCode extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        try {
            
            AffairesDAO affaire = null;
            affaire = creerObjet(request,affaire);
            
            
            
            affaire.GetListCodeAffaire("ParCodeAffaire");
            
            ArrayList list_Titre = null;
            list_Titre = new ArrayList();
            
            list_Titre.add("Code Affaire");
            list_Titre.add("Onjet affaire");
            
            ArrayList list_Size = null;
            list_Size = new ArrayList();
            
            list_Size.add("10%");
            list_Size.add("90%");
            
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCourrier('data','AffaireChoix','choix_menu=Null','null')","RETOUR");
            
            ShowDAO.Show(out,"Filtré par numéro d'affaires",list_Titre,affaire.list_show,list_Size);
            
            out.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
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
