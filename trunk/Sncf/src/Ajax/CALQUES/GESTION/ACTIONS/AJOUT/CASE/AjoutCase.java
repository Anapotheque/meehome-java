package Ajax.CALQUES.GESTION.ACTIONS.AJOUT.CASE;

import Models.TOOLS.Ajaxmodels.OBJETS.ButtonDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.SelectDAO;
import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class AjoutCase extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
//----------------------------------------------------------------------------------------------------------------
//    GESTION OBJET DE LA SERLVET
//----------------------------------------------------------------------------------------------------------------
        
        //CHARGEMENT DE LA LISTE DE LETTRE
        ArrayList listAlpha = new ArrayList();
        
        listAlpha.add("A");
        listAlpha.add("B");
        listAlpha.add("C");
        listAlpha.add("D");
        listAlpha.add("E");
        listAlpha.add("F");
        listAlpha.add("G");
        listAlpha.add("H");
        listAlpha.add("I");
        listAlpha.add("J");
        listAlpha.add("K");
        listAlpha.add("L");
        listAlpha.add("M");
        listAlpha.add("N");
        listAlpha.add("O");
        listAlpha.add("P");
        listAlpha.add("Q");
        listAlpha.add("R");
        listAlpha.add("S");
        listAlpha.add("T");
        listAlpha.add("U");
        listAlpha.add("V");
        listAlpha.add("X");
        listAlpha.add("Y");
        listAlpha.add("Z");
        
        //CHARGEMENT DE LA LISTE DE CODE NUMERIQUE DE 0 A 9
        ArrayList listNum1 = new ArrayList();
        for(int i = 0 ; i < 10; i++)
            listNum1.add(""+i);
        
        //CHARGEMENT DE LA LISTE DE CODE NUMERIQUE DE 0 A 7
        ArrayList listNum2 = new ArrayList();
        for(int i = 0 ; i < 8; i++)
            listNum2.add(""+i);
        
//----------------------------------------------------------------------------------------------------------------
//    GESTION DES ELEMENTS A ENVOYER A LA SERVLET
//----------------------------------------------------------------------------------------------------------------
        
        //INITIALISATION DU FLUX DE COMMUNICATION AVEC LA PAGE JSP
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        //DIV DE RECHARGEMENT AJAX
        out.println("<div id=Enregistrement>");
        
        //TITRE
        TOOLS_HTML.getMessage(out,"h2","Nom de la nouvelle case");
        
        //DIV DE RECHARGEMENT AJAX
        out.println("<div id=alpha>");
        SelectDAO.AddSelect(out,"alpha",listAlpha,listAlpha,1);
        out.println("  ");
        SelectDAO.AddSelect(out,"Numerique1",listNum1,listNum1,1,"onChange=javascript:getHTMLCodeRequestCalques('alpha','GetNumeroCalquesAjout','null','Champ')");
        out.println(" . ");
        SelectDAO.AddSelect(out,"Numerique2",listNum2,listNum2,1,"disabled");
        out.println("</div>"); //FIN DIV ALPHA
        
        //CHARGEMENT DU MENU
        TOOLS_HTML.OpenTable(out,"30%");
        out.println("<tr><td>");
        ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCalques('Enregistrement','EnregistrerAjoutCase','null','Champ')","Enregistrer");
        out.println("</td><td>");
        ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCalques('calques','GetCalquesGestions','SauvegardeCodeCalque_OR_Case=YES','Null')","Retour");
        out.println("</td></tr></table>");
        
        out.println("</div>"); //FIN DIV ENREGISTREMENT
        
        //FERMETURE DU FLUX DE SORTIE
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
