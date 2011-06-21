package Ajax.COURRIER.COURRIER_DEPART.LISTE;

import Models.TOOLS.Ajaxmodels.OBJETS.ButtonDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.SelectDAO;
import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class ListeChoix extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        ArrayList list1 = null;
        list1 = new ArrayList();
        
        list1.add("Liste de tout le courrier enregistré");
        list1.add("Liste du courrier enregistré par code affaire");
        list1.add("Liste du courrier enregistré par agent");
        
        ArrayList list2 = null;
        list2 = new ArrayList();
        
        list2.add("0");
        list2.add("1");
        list2.add("2");
               
        TOOLS_HTML.getMessage(out,"h2","Selectionnez un type de filtrage");
        SelectDAO.AddSelect(out,"Filtre",list2,list1,1,"onChange=javascript:getHTMLCodeRequestCourrier('data','RedirectionChoixListe','choix_menu=Redirection','Champ')");
        ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCourrier('informations','CourrierDepart','choix_menu=Null','null')","RETOUR");
        
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
