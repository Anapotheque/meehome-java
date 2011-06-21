package Ajax.TOOLS.System;

import Models.TOOLS.Ajaxmodels.OBJETS.ButtonDAO;
import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Controleur.MegaControleur;
import Models.TOOLS.Utilisateurs.UtilisateursDAO;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

//@author RABALLAND Romain ------------------------------------------------------------------------------
//@version v3.0 ------------------------------------------------------------------------------------------------------------

public class IndexJeux extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //Definition du type de texte pour l'envoie à la page jsp -----------------------------------------------------
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<div id='corps'>");
        out.println("<h3>TU DEVRAIS BOSSER PLUTOT QUE DE JOUER</h3>");
        
        UtilisateursDAO utilisateur = null;
        utilisateur = creerObjet(request,utilisateur);
        
        Mouchard(request, utilisateur,"Jeux","");
        
        TOOLS_HTML.OpenTable(out,"100%");
        out.println("<tr>");
        out.println("<td>");
        
        ButtonDAO.AddButtonStyle(out,1,"Jeux/yetisports1.exe","YETI SPORT 1");
        ButtonDAO.AddButtonStyle(out,1,"Jeux/yetisports2.exe","YETI SPORT 2");
        ButtonDAO.AddButtonStyle(out,1,"Jeux/yetisports3.exe","YETI SPORT 3");
        ButtonDAO.AddButtonStyle(out,1,"Jeux/yetisports4.exe","YETI SPORT 4");
        ButtonDAO.AddButtonStyle(out,1,"Jeux/yetisports5.exe","YETI SPORT 5");
        ButtonDAO.AddButtonStyle(out,1,"Jeux/yetisports6.exe","YETI SPORT 6");
        
        out.println("</td><td>");
        
        ButtonDAO.AddButtonStyle(out,1,"Jeux/battleships.exe","BATAILLE NAVALE");
        ButtonDAO.AddButtonStyle(out,1,"Jeux/canadair.exe","CANADAIR");
        ButtonDAO.AddButtonStyle(out,1,"Jeux/chasseur.exe","CHASSEUR");
        ButtonDAO.AddButtonStyle(out,1,"Jeux/Crab-ball.exe","CRABE BALL");
        ButtonDAO.AddButtonStyle(out,1,"Jeux/dinde.exe","DINDE");
        ButtonDAO.AddButtonStyle(out,1,"Jeux/Helisauvetage.exe","HELI SAUVETAGE");
        
        out.println("</td><td>");
        
        ButtonDAO.AddButtonStyle(out,1,"Jeux/TC101.exe","RPG BEST");
        ButtonDAO.AddButtonStyle(out,1,"Jeux/killbill.exe","KILL BILL");
        ButtonDAO.AddButtonStyle(out,1,"Jeux/mini_golf.exe","MINI GOLF");
        ButtonDAO.AddButtonStyle(out,1,"Jeux/pilsner.exe","PILSNER");
        ButtonDAO.AddButtonStyle(out,1,"Jeux/snow.exe","SNOW");
        ButtonDAO.AddButtonStyle(out,1,"Jeux/cat.exe","CHAT");
        
        out.println("</td><td>");
        
        ButtonDAO.AddButtonStyle(out,1,"Jeux/breakout.exe","BREAKOUT");
        ButtonDAO.AddButtonStyle(out,1,"Jeux/souvenirdecole.exe","CAPS");
        ButtonDAO.AddButtonStyle(out,1,"Jeux/HomeRun.exe","HOME RUN");
        
        out.println("</td></tr>");
        out.println("</table>");
        
        //Fermeture de l'envoie de données ------------------------------------------------
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
