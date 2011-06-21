package Ajax.NavyBattle;

import Models.TOOLS.Ajaxmodels.OBJETS.ButtonDAO;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class CommandeUnits extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        String unit = request.getParameter("unit");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        if(unit.equals("tanker")){
            ButtonDAO.AddButtonStyle(out,1,"javascript:AjaxNavyBattle('','','','')","BOUGER");
            ButtonDAO.AddButtonStyle(out,1,"javascript:AjaxNavyBattle('','','','')","ATTAQUER");
            ButtonDAO.AddButtonStyle(out,1,"javascript:AjaxNavyBattle('','','','')","PILLONER");
            ButtonDAO.AddButtonStyle(out,1,"javascript:AjaxNavyBattle('','','','')","SABOTER");
        }
        if(unit.equals("soldier")){
            ButtonDAO.AddButtonStyle(out,1,"javascript:AjaxNavyBattle('','','','')","BOUGER");
            ButtonDAO.AddButtonStyle(out,1,"javascript:AjaxNavyBattle('','','','')","ATTAQUER");
            ButtonDAO.AddButtonStyle(out,1,"javascript:AjaxNavyBattle('','','','')","ARAKIRI");
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
