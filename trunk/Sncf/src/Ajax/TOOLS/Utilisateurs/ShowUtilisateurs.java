
package Ajax.TOOLS.Utilisateurs;

import Models.TOOLS.Ajaxmodels.DVSG.AjaxModels_Onglets;
import Models.TOOLS.Controleur.MegaControleur;
import Models.TOOLS.Utilisateurs.UtilisateursDAO;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

//@author RABALLAND Romain ------------------------------------------------------------------------------
//@version v3.0 ------------------------------------------------------------------------------------------------------------

public class ShowUtilisateurs extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        try {
            
            //Creation de l'objet associé -----------------------------------------------------
            
            UtilisateursDAO utilisateur = null;
            utilisateur= creerObjet(request, utilisateur);
            
            //Actions du controleur ------------------------------------------------------------
            
            utilisateur.SetUtilisateur(); // Charge toutes les informations
            
            //Definition du type de texte pour l'envoie à la page jsp -----------------------------------------------------
            
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            //Affichage des informations ------------------------------------------------------
            
            //On affiche les options menu liées à la base
            AjaxModels_Onglets.getOnglet(request,response,"Affichage","Utilisateurs");
            
            //ON affiche les informations concernant la table
            out.println("<h4>UTILISATEURS</h4>");
            out.println("<div id='corps_table'>");
            
            out.println("<table width=100%>");
            
            
            out.println("<th>IDENTIFIANT</th>");
            out.println("<th>PWD</th>");
            out.println("<th>NOM</th>");
            out.println("<th>PRENOM</th>");
            out.println("<th>SOUSGROUPE</th>");
            out.println("<th>ROLE</th>");
            out.println("<th>ACTIONS</th>");
            
            int h=0;
            int j=1;
            
            
            
            for(int i=0 ; i < utilisateur.list_codeUser.size() ; i++ ){
                
                out.println("<tr><td colspan='7'><hr with=100%></td></tr>");
                
                out.println("<tr>");
                out.println("<td>"+utilisateur.list_login.get(i)+"</td>");
                out.println("<td>"+utilisateur.list_pwd.get(i)+"</td>");
                out.println("<td>"+utilisateur.list_nom.get(i)+"</td>");
                out.println("<td>"+utilisateur.list_prenom.get(i)+"</td>");
                out.println("<td>"+utilisateur.list_sous_groupe.get(i)+"</td>");
                out.println("<td>"+utilisateur.list_role.get(i)+"</td>");
                
                
                out.println("<td><a href=javascript:getHTMLCodeRequest('informations','ModifyUtilisateurs','choix_menu=null','no_form','"+utilisateur.list_codeUser.get(i)+"')><span>MODIFIER</span></a>");
                out.println("<a href=javascript:getHTMLCodeRequest('informations','DeleteUtilisateurs','choix_menu=Delete','no_form','"+utilisateur.list_codeUser.get(i)+"')><span>SUPPRIMER</span></a></td>");
                
                out.println("</tr>");
                h = h +2;
                j = j +2;
            }
            
            out.println("</table>");
            
            out.println("</div>");
            out.println("</div>");
            
            out.close();
            
        }
        //Gestion des erreurs -------------------------------------------------------------
        
        catch(Exception e) {
            retourVue(request, response,"/Erreur?message="+e.getMessage());
            e.getMessage();
            
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
