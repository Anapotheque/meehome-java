
package Ajax.CALQUES.GESTION.ACTIONS.AJOUT.CASE;

import Models.TOOLS.Ajaxmodels.OBJETS.SelectDAO;
import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

public class GetNumeroCalquesAjout extends HttpServlet {
    
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
        
        //GET DATA ON RECUPERE LE CODE COLLECTION
        String codeAlpha = request.getParameter("alpha");
        String codeNum1 = request.getParameter("Numerique1");
        
        
//----------------------------------------------------------------------------------------------------------------
//    GESTION DES ELEMENTS A ENVOYER A LA SERVLET
//----------------------------------------------------------------------------------------------------------------
        
        //INITIALISATION DU FLUX DE COMMUNICATION AVEC LA PAGE JSP
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        //CHARGEMENT DU MENU DE SELECTION
        SelectDAO.GetSelect(out,"alpha",codeAlpha,codeAlpha,listAlpha,listAlpha,1);
        out.println("  ");
        SelectDAO.GetSelect(out,"Numerique1",codeNum1,codeNum1,listNum1,listNum1,1,"onChange=javascript:getHTMLCodeRequestCalques('alpha','GetNumeroCalquesAjout','null','Champ')");
        out.println(" . ");
        if(codeNum1.equals("0"))  SelectDAO.AddSelect(out,"Numerique2",listNum2,listNum2,1,"disabled");
        else SelectDAO.AddSelect(out,"Numerique2",listNum2,listNum2,1);
        
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
