package Ajax.CALQUES.SELECTION.SORTIE;

import Models.CALQUES.CalquesDAO;
import Models.ETUDES.Agents.AgentsDAO;
import Models.ETUDES.Entreprises.EntreprisesDAO;
import Models.ETUDES.Gares.GaresDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.ButtonDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.SelectDAO;
import Models.TOOLS.Ajaxmodels.CALQUES.ShowCalquesInputDAO;
import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Controleur.MegaControleur;
import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class GetCalques extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            
            //RECUPERATION DES OBJETS
            CalquesDAO calque = null;
            calque = creerObjet(request,calque);
            
            GaresDAO gare = null;
            gare = creerObjet(request, gare);
            
            EntreprisesDAO entreprise = null;
            entreprise = creerObjet(request, entreprise);
            
            AgentsDAO agent = null;
            agent = creerObjet(request, agent);
            
            //GET DATA ON RECUPERE LE CODECOLLECTION
            calque.codeCollection = request.getParameter("codeCollection");
            
            //INITIALISATION DU FLUX DE COMMUNICATION AVEC LA PAGE JSP
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
                        
            //DIV DE MISE EN FORME CADRE BLANC
            out.println("<div id=DivSaisie>");
            
            if(calque.GetListCalquesNonSortis(calque.codeCollection)){
                TOOLS_HTML.getMessage(out,"h2","Selectionnez parmis les calques sortis dans cette collection");
                ShowCalquesInputDAO.show(out,calque.listCodeCalques,calque.listnumeroCalques,false);
            } else
                TOOLS_HTML.getMessage(out,"h2","AUCUNS CALQUES REFERENCES");
            
            out.println("</div>");  //FIN DivSaisie
            
            //DIV DE RECHARGEMENT AJAX
            out.println("<div id=Enregistrement>");
            
            //CHARGEMENT DU MENU DE NAVIGATION
            TOOLS_HTML.OpenTable(out,"100%");
            out.println("<tr><td>");
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCalques('calques','GetCalquesSortis','choix_menu=Null','Champ')","CALQUES SORTIS");
            out.println("</td><td>");
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCalques('calques','GetCalquesSelected','choix_menu=Null','Champ')","TOUT SELECTIONNER");
            out.println("</td><td>");
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCalques('calques','GetDernierSorti','choix_menu=Null','Champ')","DERNIERS RENTRES");
            out.println("</td><td>");
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCalques('calques','VoirCases','choix_menu=Null','Champ')","VOIR CASE");
            out.println("</td><td>");
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCalques('Enregistrement','SortirCalquesActions','choix_menu=Null','Champ')","SORTIR CALQUES");
            out.println("</td></tr></table>");
            
            out.println("</div>");  //FIN Enregistrement
            
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
