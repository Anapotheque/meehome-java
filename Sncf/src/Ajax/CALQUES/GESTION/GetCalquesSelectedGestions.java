package Ajax.CALQUES.GESTION;

import Models.CALQUES.CalquesDAO;
import Models.CALQUES.CasesDAO;
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

public class GetCalquesSelectedGestions extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        try {
            
            //RECUPERATION DES OBJETS
            CalquesDAO calque = null;
            calque = creerObjet(request,calque);
            
            CasesDAO cases = null;
            cases = creerObjet(request,cases);
            
            //GET DATA ON RECUPERE LE CODE COLLECTION
            calque.codeCollection = request.getParameter("codeCollection");
            cases.codeCase = request.getParameter("codeCase");
            
            //INITIALISATION DU FLUX DE COMMUNICATION AVEC LA PAGE JSP
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            //DIV DE MISE EN FORME POUR COLLER LE TEXTE A GAUCHE
            out.println("<div Style='text-align: left'>");
            
            //AFFICHAGE DE LA LISTE DES CASES DISPONIBLES POUR LE CODE COLLECTION SELECTIONNE
            //SI ON CLIQUE ALORS ON RECHARGE CETTE LISTE POUR LE CODE CASE
            TOOLS_HTML.getMessage(out,"h2","CASE");
            SelectDAO.GetSelect(out,"cases",cases.codeCase,cases.GetNumeroCases(cases.codeCase),cases.listCodeCases,cases.listnumeroCases,1,"onChange=javascript:getHTMLCodeRequestCalques('calques','GetCalquesSelectedGestions','NumeroCase','Champ')");
            
            out.println("</div>");  //FIN DIV ALIGN LEFT
            
            //DIV DE MISE EN FORME CADRE BLANC
            out.println("<div id=DivSaisie>");
            
            //AFFICHAGE DU CONTENU*******************************************************************************
            
            // SI AUCUNE CASE DE RANGEMENT N'A ETE SELECTIONNE
            if(cases.codeCase.equals("null")){
                
                //AFFICHAGE DE LA LISTE DES CALQUES
                TOOLS_HTML.getMessage(out,"h2","Selectionnez parmis les calques de cette collection");
                ShowCalquesInputDAO.show(out,calque.listCodeCalques,calque.listnumeroCalques,true);
                
            }else{ // SI UN CODE CASE DE RANGEMENT A ETE CHOISI
                
                //ON CHARGE LA LISTE DES CALQUES POUR LE CODE COLLECTION ET UN CODE CASE CHOISI
                calque.GetCalques(calque.codeCollection,cases.codeCase);
                
                //AFFICHAGE DE LA LISTE DES CALQUES
                TOOLS_HTML.getMessage(out,"h2","Selectionnez parmis les calques de cette collection");
                ShowCalquesInputDAO.show(out,calque.listCodeCalques,calque.listnumeroCalques,true);
                
            }
            
            //FIN DU CONTENU************************************************************************************
            
            out.println("</div>");  //FIN DivSaisie
            
            //DIV DE MISE EN FORME POUR COLLER LE TEXTE A GAUCHE
            out.println("<div Style='text-align: left'>");
            
            //MENU DE GESTION DES CALQUES
            TOOLS_HTML.OpenTable(out,"20%");
            out.println("<tr><td>");
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCalques('calques','SuppressionCalques','choix_menu=Null','Champ')","SUPPRIMER CALQUES");
            out.println("</td><td>");
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCalques('calques','GetCalquesGestions','retourDeselectionCalques=retourDeselectionCalques','Champ')","TOUT DESELECTIONNER");
            out.println("</td></tr></table>");
            //FIN DU MENU DE GESTION
            
            out.println("</div>");  //FIN DIV ALIGN LEFT
            
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
