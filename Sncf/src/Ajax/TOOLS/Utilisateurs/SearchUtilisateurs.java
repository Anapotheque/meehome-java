package Ajax.TOOLS.Utilisateurs;

import Models.TOOLS.Ajaxmodels.DVSG.AjaxModels_Onglets;
import Models.TOOLS.Controleur.MegaControleur;
import Models.TOOLS.Utilisateurs.UtilisateursDAO;
import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

//@author RABALLAND Romain ------------------------------------------------------------------------------
//@version v3.0 ------------------------------------------------------------------------------------------------------------

public class SearchUtilisateurs extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //Recuperation des données envoyées -----------------------------------------------
        
        String choix = new String(request.getParameter("choix_menu"));
        
        if(choix.equals("null")){
            
            try {
                
                //Definition du type de texte pour l'envoie à la page jsp -----------------------------------------------------
                
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                
                //Affichage des informations ------------------------------------------------------
                
                //On affiche les options menu liées à la base
                AjaxModels_Onglets.getOnglet(request,response,"Recherche","Utilisateurs");
                
                //ON affiche les informations concernant la table
                out.println("<h4>UTILISATEURS</h4>");
                out.println("<br>");
                out.println("<form name='champ'>");
                
                out.println("<h1>Recherche par mots clefs selon le 'NOM' de l'utilisateur</h1>");
                out.println("<table><tr><td><input type='text' name='mot_clef' style='width: 145px'></td>");
                out.println("<td><a href=javascript:getHTMLCodeRequest('informations','SearchUtilisateurs','choix_menu=Search','champ')><img src='images/search.gif' width='48' height='48' alt='Rechercher' border='0' onMouseOver='change_image(0,searchgris)' onMouseOut='change_image(0,search)'/></a></td>");
                out.println("</tr></table>");
                out.println("</form>");
                out.println("<br><br>");
                
                out.println("</div>");
                
                out.close();
                
            }
            //Gestion des erreurs -------------------------------------------------------------
            
            catch(Exception e) {
                retourVue(request, response,"/Erreur?message="+e.getMessage());
                e.getMessage();
                
            }
        }
        
        else if(choix.equals("Search")){
            
            //Recuperation des données envoyées -----------------------------------------------
            
            String mot_clef = new String(request.getParameter("mot_clef"));
            
            //Creation de l'objet associé -----------------------------------------------------
            
            UtilisateursDAO utilisateur = null;
            utilisateur= creerObjet(request, utilisateur);
            boolean result = false;
            
            try {
                result = utilisateur.SearchUtilisateur(mot_clef);
            } catch (SQLException ex) {
                retourVue(request, response,"/Erreur?message="+ex.getMessage());
                ex.printStackTrace();
            } // Charge toutes les informations
            
            //Definition du type de texte pour l'envoie à la page jsp -----------------------------------------------------
            
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            //Affichage des informations ------------------------------------------------------
            
            //On affiche les options menu liées à la base
            AjaxModels_Onglets.getOnglet(request,response,"Recherche","Utilisateurs");
            
            //ON affiche les informations concernant la table
            
            out.println("<br>");
            out.println("<form name='champ'>");
            
            out.println("<h1>Recherche par mots clefs selon le 'NOM' de l'utilisateur</h1>");
            out.println("<table><tr><td><input type='text' name='mot_clef' style='width: 145px'></td>");
            out.println("<td><a href=javascript:getHTMLCodeRequest('informations','Search','choix_menu=Search','champ')><img src='images/search.gif' width='48' height='48' alt='Rechercher' border='0' onMouseOver='change_image(0,searchgris)' onMouseOut='change_image(0,search)'/></a></td>");
            out.println("</tr></table>");
            out.println("</form>");
            out.println("<br><br>");
            
            if(result){
                out.println("<div id='corps_table'>");
                
                out.println("<table width=80%>");
                
                out.println("<th>IDENTIFIANT</th>");
                out.println("<th>NOM</th>");
                out.println("<th>PRENOM</th>");
                out.println("<th>SOUSGROUPE</th>");
                out.println("<th>ROLE</th>");
                out.println("<th>ACTIONS</th>");
                
                int h=1;
                int j=2;
                for(int i=0 ; i < utilisateur.list_login.size() ; i++ ){
                    
                    out.println("<tr><td colspan='7'><hr with=100%></td></tr>");
                    out.println("<tr>");
                    out.println("<td>"+utilisateur.list_login.get(i)+"</td>");
                    out.println("<td>"+utilisateur.list_pwd.get(i)+"</td>");
                    out.println("<td>"+utilisateur.list_nom.get(i)+"</td>");
                    out.println("<td>"+utilisateur.list_prenom.get(i)+"</td>");
                    out.println("<td>"+utilisateur.list_sous_groupe.get(i)+"</td>");
                    out.println("<td>"+utilisateur.list_role.get(i)+"</td>");
                    
                    out.println("<td><a href=javascript:getHTMLCodeRequest('informations','ModifyUtilisateurs','choix_menu=null','no_form','"+utilisateur.list_codeUser.get(i)+"')><img src='images/modifier.gif' alt='Modifier' onMouseOver='change_image("+h+",modifiergris)' border='0' onMouseOut='change_image("+h+",modifier)'/></a>");
                    out.println("<a href=javascript:getHTMLCodeRequest('informations','DeleteUtilisateurs','choix_menu=Delete','no_form','"+utilisateur.list_codeUser.get(i)+"')><img src='images/supprimer.gif' alt='Supprimer' onMouseOver='change_image("+j+",supprimergris)' border='0' onMouseOut='change_image("+j+",supprimer)'/></a></td>");
                    
                    out.println("</tr>");
                    h = h +2;
                    j = j +2;
                }
                
                out.println("</table>");
                
                out.println("</div>");
            } else {
                
                out.println("<h3>AUCUN RESULTATS N'A ETE OBTENU VEUILLEZ RECOMMENCER</h3>");
                
            }
            
            out.println("</div>");
            
            out.close();
            
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
