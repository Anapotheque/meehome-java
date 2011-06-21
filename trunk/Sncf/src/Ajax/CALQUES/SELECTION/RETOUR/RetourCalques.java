package Ajax.CALQUES.SELECTION.RETOUR;

import Models.ETUDES.Gares.GaresDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.ButtonDAO;
import Models.TOOLS.Ajaxmodels.GetMessageInformationDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.SelectDAO;
import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Controleur.MegaControleur;
import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class RetourCalques extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        try {
            
            //RECUPERATION DES OBJETS
            GaresDAO gare = null;
            gare = creerObjet(request, gare);
            
            //INITIALISATIO NDES LISTE DE GARE SE
            gare.SetGareSE();
            
            //INITIALISATION DU FLUX DE COMMUNICATION AVEC LA PAGE JSP
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            //LIGNE ROUGE
            out.println("<div id=ajout>");
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation1",".: SNCF :: CALQUES :: RETOUR :.<br>");
            out.println("</div>");  //FIN AJOUT
            
            //DIV DE MISE EN FORME CADRE GRIS
            out.println("<div id=Divcontenu>");
            
            //DIV DE MISE EN FORME POUR COLLER LE TEXTE A GAUCHE
            out.println("<div Style='text-align: left'>");
            
            //LISTE DES INFORMATIONS
            TOOLS_HTML.getMessage(out,"h2","COLLECTION");
            SelectDAO.AddSelect(out,"collection",gare.list_CodeGare,gare.list_Gare,1,"onChange=javascript:getHTMLCodeRequestCalques('calques','GetRetourCalques','choix_menu=Null','Champ')");
            
            out.println("</div>");  //FIN DIV ALIGN LEFT
            
            //DIV DE RECHARGEMENT AJAX
            out.println("<div id=calques>");
            //DIV DE MISE EN FORME CADRE BLANC
            out.println("<div id=DivSaisie>");
            
            TOOLS_HTML.getMessage(out,"h2","SELECTIONNEZ UNE COLLECTION");
            
            out.println("</div>");  //FIN DivSaisie
            out.println("</div>");  //FIN calques
            
            //CHARGEMENT DEU MENU DE NAVIGATION
            TOOLS_HTML.OpenTable(out,"100%");
            out.println("<tr><td>");
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCalques('informations','IndexCalques','choix_menu=Null','Null')","INDEX");
            out.println("</td></tr></table>");
            
            out.println("</div>");  //FIN Divcontenu
            
            //FERMETURE DU FLUX DE SORTIE
            out.close();
            
        } catch (SQLException ex) {
            retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState());
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
