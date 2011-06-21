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

public class GetCalquesGestions extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        try {
            
            //RECUPERATION DES OBJETS
            CalquesDAO calque = null;
            calque = creerObjet(request,calque);
            
            CasesDAO cases = null;
            cases = creerObjet(request,cases);
            
            //GET DATA SI ON REVIENT DE LA SERVLET 'SuppressionCalques'
            //Evite de supprimer les 'codeCollection' et 'codeCase'
            String SauvegardeCodeCalque_OR_Case = request.getParameter("SauvegardeCodeCalque_OR_Case");
            
            //SI ON NE REVIENS PAS DE LA SERVLET 'SuppressionCalques' ON RECUPERE LES CODES
            if(SauvegardeCodeCalque_OR_Case == null){
                calque.codeCollection = request.getParameter("codeCollection");
                cases.codeCase = request.getParameter("codeCase");
            }
            
            //INITIALISATION DU FLUX DE COMMUNICATION AVEC LA PAGE JSP
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            //CHARGEMENT DE LA LISTE DE CASE POUR LA COLLECTION CHOISIE
            cases.GetListCases(calque.codeCollection);
            
            //DIV DE MISE EN FORME POUR COLLER LE TEXTE A GAUCHE
            out.println("<div Style='text-align: left'>");
            
            //AFFICHAGE DE LA LISTE DES CASES DISPONIBLES POUR LE CODE COLLECTION SELECTIONNE
            //SI ON CLIQUE ALORS ON RECHARGE CETTE LISTE POUR LE CODE CASE
            TOOLS_HTML.getMessage(out,"h2","CASE");
            SelectDAO.GetSelect(out,"cases",cases.codeCase,cases.GetNumeroCases(cases.codeCase),cases.listCodeCases,cases.listnumeroCases,1,"onChange=javascript:getHTMLCodeRequestCalques('calques','GetCalquesGestions','NumeroCase','Champ')");
            
            out.println("</div>");
            
            //DIV DE MISE EN FORME CADRE BLANC
            out.println("<div id=DivSaisie>");
            
            //AFFICHAGE DU CONTENU************************************************************************************
            
            boolean presenceCalques = false;
            
            // SI AUCUNE CASE DE RANGEMENT N'A ETE SELECTIONNE
            if(cases.codeCase == null){
                
                //ON CHARGE LA LISTE DES CALQUES POUR LE CODE COLLECTION CHOISI
                presenceCalques = calque.GetCalques(calque.codeCollection);
                
                // SI IL Y A DES CALQUES DANS LA COLLECTION CHOISIE ON LES AFFICHES TOUS
                if(presenceCalques){
                    
                    //AFFICHAGE DE LA LISTE DES CALQUES
                    TOOLS_HTML.getMessage(out,"h2","Selectionnez parmis les calques de cette collection");
                    ShowCalquesInputDAO.show(out,calque.listCodeCalques,calque.listnumeroCalques,false);
                    
                }else // SINON ON AFFICHE LE MESSAGE
                    TOOLS_HTML.getMessage(out,"h2","AUCUNS CALQUES REFERENCES");
                
            } else{ // SI UN CODE CASE DE RANGEMENT A ETE CHOISI
                
                //ON CHARGE LA LISTE DES CALQUES POUR LE CODE COLLECTION ET UN CODE CASE CHOISI
                presenceCalques = calque.GetCalques(calque.codeCollection,cases.codeCase);
                
                // SI IL Y A DES CALQUES DANS LA CASE CHOISIE POUR LA COLLECION ON LES AFFICHE
                if(presenceCalques){
                    
                    //AFFICHAGE DE LA LISTE DES CALQUES
                    TOOLS_HTML.getMessage(out,"h2","Selectionnez parmis les calques de cette collection");
                    ShowCalquesInputDAO.show(out,calque.listCodeCalques,calque.listnumeroCalques,false);
                    
                } else // SINON ON AFFICHE LE MESSAGE
                    TOOLS_HTML.getMessage(out,"h2","AUCUNS CALQUES REFERENCES");
            }
            
            //FIN DU CONTENU****************************************************************************************
            
            out.println("</div>");  //FIN DivSaisie
            
            //DIV DE RECHARGEMENT AJAX
            out.println("<div id=Enregistrement>");
            
            //DIV DE MISE EN FORME POUR COLLER LE TEXTE A GAUCHE
            out.println("<div Style='text-align: left'>");
            
            //MENU DE GESTION DES CALQUES
            TOOLS_HTML.OpenTable(out,"20%");
            out.println("<tr>");
            
            //SI ON A SELECTIONNE UNE CASE DE RANGEMENT
            if(cases.codeCase != null){
                out.println("<td>");
                ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCalques('Enregistrement','AjoutCalques','choix_menu=Null','null')","AJOUTER CALQUES");
                out.println("</td>");
            }
            
            //MENU AFFICHE SI IL Y A DES CALQUES
            if(presenceCalques){
                
                //SI ON A SELECTIONNE UNE CASE DE RANGEMENT
                if(cases.codeCase != null){
                    out.println("<td>");
                    ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCalques('Enregistrement','ModifierCalques','choix_menu=Null','Champ')","MODIFIER");
                    out.println("</td>");
                }
                
                out.println("<td>");
                ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCalques('calques','SuppressionCalques','choix_menu=Null','Champ')","SUPPRIMER CALQUES");
                out.println("</td><td>");
                ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCalques('calques','GetCalquesSelectedGestions','NumeroCase','Champ')","TOUT SELECTIONNER");
                out.println("</td>");
                
            }
            out.println("<td>");
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCourrier('Enregistrement','AjoutCase','choix_menu=Null','null')","AJOUTER CASE");
            
            //SI ON A SELECTIONNE UNE CASE DE RANGEMENT
            if(cases.codeCase != null && !presenceCalques){
                out.println("</td><td>");
                ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCourrier('Enregistrement','SuppressionCase','choix_menu=Null','null')","SUPPRIMER CASE");
            }
            
            out.println("</td></tr></table>");
            //FIN DU MENU DE GESTION
            
            out.println("</div>");  //FIN Enregistrement
            
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
