package Ajax.TOOLS.Utilisateurs;

import Models.TOOLS.Ajaxmodels.DVSG.AjaxModels_Onglets;
import Models.TOOLS.Controleur.MegaControleur;
import Models.TOOLS.Utilisateurs.UtilisateursDAO;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

//@author RABALLAND Romain ------------------------------------------------------------------------------
//@version v3.0 ------------------------------------------------------------------------------------------------------------

public class NewUtilisateurs extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //Recuperation des donn�es envoy�es -----------------------------------------------
        
        String choix = new String(request.getParameter("choix_menu"));
        
        // On affiche la page si le choix est egale � 'Null'--------------------------------------
        
        if(choix.equals("Null")){
            
            try {

                //Definition du type de texte pour l'envoie � la page jsp -----------------------------------------------------
                
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                
                //Affichage des informations ------------------------------------------------------
                
                //On affiche les options menu li�es � la base
                
                AjaxModels_Onglets.getOnglet(request,response,"Creation","Utilisateurs");
                
                //ON affiche les informations concernant la table
                out.println("<h4>UTILISATEURS</h4>");
                out.println("<div id='corps_table'>");
                
                out.println("<table width ='100%' >");
                
                out.println("<tr>");
                out.println("<td>IDENTIFIANT</td>");
                out.println("<td>MOT DE PASSE</td>");
                out.println("<td>NOM</td>");
                out.println("<td>PRENOM</td>");
                out.println("<td>SOUSGROUPE</td>");
                out.println("<td>ROLE</td>");
                out.println("<td>ACTIONS</td>");
                out.println("</tr>");
                
                out.println("<tr>");
                out.println("<td colspan='7'><hr with=100%>");
                
                out.println("</tr>");
                
                out.println("<tr>");
                
                out.println("<form name='champ'>");
                out.println("<td><input type='text' value='' name='login'></td>");
                out.println("<td><input type='password' value='' name='password'></td>");
                out.println("<td><input type='text' value='' name='nom'></td>");
                out.println("<td><input type='text' value='' name='prenom'></td>");
                
                out.println("<td><select name='sous_groupe'>");
                out.println("<option value='Estimation'>Estimation</option>");
                out.println("<option value='SE'>Sch�mas d'executions</option>");
                out.println("<option value='PT'>Plans Techniques</option>");
                out.println("<option value='Verification'>Verification</option>");
                out.println("<option value='Management'>Management</option>");
                out.println("<option value='Stagiaire'>Stagiaire</option>");
                out.println("</select></td>");
                
                out.println("<td><select name='role'>");
                out.println("<option value='Administrateur'>Administrateur</option>");
                out.println("<option value='Moderateur'>Moderateur</option>");
                out.println("<option value='Utilisateur'>Utilisateur</option>");
                out.println("<option value='Intrus'>Intrus</option>");
                out.println("</select></td>");
                
                
                out.println("<td><a href=javascript:getHTMLCodeRequest('informations','NewUtilisateurs','choix_menu=New','champ')><img src='images/ajouter.gif' width='48' height='48' alt='Enregistrer' border='0' onMouseOver='change_image(0,ajoutergris)' onMouseOut='change_image(0,ajouter)'/></a></td>");
                out.println("</tr>");
                out.println("</form>");
                
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
        
        // On enregistre le resultat si le choix vaut 'NewUtilisateurs'--------------------------------------
        
        else if(choix.equals("New")){
            
            String login = new String(request.getParameter("login"));
            String password = new String(request.getParameter("password"));
            String nom = new String(request.getParameter("nom"));
            String prenom = new String(request.getParameter("prenom"));
            String sous_groupe = new String(request.getParameter("sous_groupe"));
            String role = new String(request.getParameter("role"));
            
            ArrayList list = new ArrayList();
            
            list.add(login);
            list.add(password);
            list.add(nom);
            list.add(prenom);
            list.add(sous_groupe);
            list.add(role);
            
            list = TransformString(list);
            
            try {
                
                //Creation de l'objet associ� -----------------------------------------------------
                
                UtilisateursDAO utilisateur = null;
                utilisateur= creerObjet(request, utilisateur);
                
                //Actions du controleur ------------------------------------------------------------
                
                utilisateur.NewUtilisateur(list); // Charge toutes les informations
                
                //Operations effectu�es avec succ�s------------------------------------------------
                
                retourVue(request, response,"/DoneUtilisateurs?choix_menu=null");
                
            }
            
            catch(Exception e) {
                retourVue(request, response,"/Erreur?message="+e.getMessage());
                e.getMessage();
                
            }
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
