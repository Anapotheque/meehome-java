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

public class ModifyUtilisateurs extends MegaControleur {
    
    private String code="";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //Recuperation des données envoyées -----------------------------------------------
        
        String choix = new String(request.getParameter("choix_menu"));
        
        if(choix.equals("null")){
            
            code = new String(request.getParameter("codeUser"));
            try {
                
                //Creation de l'objet associé -----------------------------------------------------
                
                UtilisateursDAO utilisateur = null;
                utilisateur= creerObjet(request, utilisateur);
                
                //Definition du type de texte pour l'envoie à la page jsp -----------------------------------------------------
                
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                
                //Affichage des informations ------------------------------------------------------
                
                //On affiche les options menu liées à la base
                AjaxModels_Onglets.getOnglet(request,response,"Modify","Utilisateurs");
                
                //ON affiche les informations concernant la table
                out.println("<h4>UTILISATEURS</h4>");
                out.println("<div id='corps_table'>");
                
                out.println("<table width=100% >");
                
                out.println("<tr>");
                out.println("<td>IDENTIFIANT</td>");
                out.println("<td>MOT DE PASSE</td>");
                out.println("<td>NOM</td>");
                out.println("<td>PRENOM</td>");
                out.println("<td>SOUSGROUPE</td>");
                out.println("<td>ROLE</td>");
                out.println("<td>BONUS</td>");
                out.println("<td>Actions</td>");
                out.println("</tr>");
                
                out.println("<tr>");
                out.println("<td colspan='8'><hr with=100%>");
                
                out.println("</tr>");
                
                out.println("<tr>");
                
                int i = utilisateur.list_codeUser.indexOf(code);
                
                out.println("<form name='champ'>");
                out.println("<td><input type='text' name='login' value='"+utilisateur.list_login.get(i)+"'></td>");
                out.println("<td><input type='password' name='password' value='"+utilisateur.list_pwd.get(i)+"'></td>");
                out.println("<td><input type='text' name='nom' value='"+utilisateur.list_nom.get(i)+"'></td>");
                out.println("<td><input type='text' name='prenom' value='"+utilisateur.list_prenom.get(i)+"'></td>");
                
                
                
                if(utilisateur.list_sous_groupe.get(i).equals("Estimation")){
                    out.println("<td><select name='sous_groupe'>");
                    out.println("<option value='Estimation' selected>Estimation</option>");
                    out.println("<option value='SE'>Schémas d'executions</option>");
                    out.println("<option value='PT'>Plans Techniques</option>");
                    out.println("<option value='Verification'>Verification</option>");
                    out.println("<option value='Management'>Management</option>");
                    out.println("<option value='Stagiaire'>Stagiaire</option>");
                    out.println("</select></td>");
                } else if(utilisateur.list_sous_groupe.get(i).equals("SE")){
                    out.println("<td><select name='sous_groupe'>");
                    out.println("<option value='Estimation'>Estimation</option>");
                    out.println("<option value='SE' selected>Schémas d'executions</option>");
                    out.println("<option value='PT'>Plans Techniques</option>");
                    out.println("<option value='Verification'>Verification</option>");
                    out.println("<option value='Management'>Management</option>");
                    out.println("<option value='Stagiaire'>Stagiaire</option>");
                    out.println("</select></td>");
                } else if(utilisateur.list_sous_groupe.get(i).equals("PT")){
                    out.println("<td><select name='sous_groupe'>");
                    out.println("<option value='Estimation'>Estimation</option>");
                    out.println("<option value='SE'>Schémas d'executions</option>");
                    out.println("<option value='PT' selected>Plans Techniques</option>");
                    out.println("<option value='Verification'>Verification</option>");
                    out.println("<option value='Management'>Management</option>");
                    out.println("<option value='Stagiaire'>Stagiaire</option>");
                    out.println("</select></td>");
                } else if(utilisateur.list_sous_groupe.get(i).equals("Verification")){
                    out.println("<td><select name='sous_groupe'>");
                    out.println("<option value='Estimation'>Estimation</option>");
                    out.println("<option value='SE'>Schémas d'executions</option>");
                    out.println("<option value='PT'>Plans Techniques</option>");
                    out.println("<option value='Verification' selected>Verification</option>");
                    out.println("<option value='Management'>Management</option>");
                    out.println("<option value='Stagiaire'>Stagiaire</option>");
                    out.println("</select></td>");
                } else if(utilisateur.list_sous_groupe.get(i).equals("Management")){
                    out.println("<td><select name='sous_groupe'>");
                    out.println("<option value='Estimation'>Estimation</option>");
                    out.println("<option value='SE'>Schémas d'executions</option>");
                    out.println("<option value='PT'>Plans Techniques</option>");
                    out.println("<option value='Verification'>Verification</option>");
                    out.println("<option value='Management' selected>Management</option>");
                    out.println("<option value='Stagiaire'>Stagiaire</option>");
                    out.println("</select></td>");
                } else if(utilisateur.list_sous_groupe.get(i).equals("Stagiaire")){
                    out.println("<td><select name='sous_groupe'>");
                    out.println("<option value='Estimation'>Estimation</option>");
                    out.println("<option value='SE'>Schémas d'executions</option>");
                    out.println("<option value='PT'>Plans Techniques</option>");
                    out.println("<option value='Verification'>Verification</option>");
                    out.println("<option value='Management'>Management</option>");
                    out.println("<option value='Stagiaire' selected>Stagiaire</option>");
                    out.println("</select></td>");
                }
                
                if(utilisateur.list_role.get(i).equals("Administrateur")){
                    out.println("<td><select name='role'>");
                    out.println("<option value='Administrateur' selected>Administrateur</option>");
                    out.println("<option value='Moderateur'>Moderateur</option>");
                    out.println("<option value='Utilisateur'>Utilisateur</option>");
                    out.println("<option value='Intrus'>Intrus</option>");
                    out.println("</select></td>");
                }
                if(utilisateur.list_role.get(i).equals("Moderateur")){
                    out.println("<td><select name='role'>");
                    out.println("<option value='Administrateur'>Administrateur</option>");
                    out.println("<option value='Moderateur' selected>Moderateur</option>");
                    out.println("<option value='Utilisateur'>Utilisateur</option>");
                    out.println("<option value='Intrus'>Intrus</option>");
                    out.println("</select></td>");
                }
                if(utilisateur.list_role.get(i).equals("Utilisateur")){
                    out.println("<td><select name='role'>");
                    out.println("<option value='Administrateur'>Administrateur</option>");
                    out.println("<option value='Moderateur'>Moderateur</option>");
                    out.println("<option value='Utilisateur' selected>Utilisateur</option>");
                    out.println("<option value='Intrus'>Intrus</option>");
                    out.println("</select></td>");
                }
                if(utilisateur.list_role.get(i).equals("Intrus")){
                    out.println("<td><select name='role'>");
                    out.println("<option value='Administrateur'>Administrateur</option>");
                    out.println("<option value='Moderateur'>Moderateur</option>");
                    out.println("<option value='Utilisateur'>Utilisateur</option>");
                    out.println("<option value='Intrus' selected>Intrus</option>");
                    out.println("</select></td>");
                }
                
                if(utilisateur.list_bonus.get(i).equals("1")){
                    out.println("<td><select name='bonus'>");
                    out.println("<option value='1' selected>Autoriser Bonus</option>");
                    out.println("<option value='0'>Non Autoriser</option>");
                    out.println("</select></td>");
                }
                if(utilisateur.list_bonus.get(i).equals("0")){
                    out.println("<td><select name='bonus'>");
                    out.println("<option value='1'>Autoriser Bonus</option>");
                    out.println("<option value='0' selected>Non Autoriser</option>");
                    out.println("</select></td>");
                }
                
                out.println("<td><a href=javascript:getHTMLCodeRequest('informations','ModifyUtilisateurs','choix_menu=Modify','champ','null')><img src='images/ajouter.gif' width='48' height='48' alt='Enregistrer' border='0' onMouseOver='change_image(0,ajoutergris)' onMouseOut='change_image(0,ajouter)'/></a></td>");
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
        } else if(choix.equals("Modify")){
            
            String login = new String(request.getParameter("login"));
            String password = new String(request.getParameter("password"));
            String nom = new String(request.getParameter("nom"));
            String prenom = new String(request.getParameter("prenom"));
            String sous_groupe = new String(request.getParameter("sous_groupe"));
            String role = new String(request.getParameter("role"));
            String bonus = new String(request.getParameter("bonus"));
            
            ArrayList list = new ArrayList();
            
            list.add(login);
            list.add(password);
            list.add(nom);
            list.add(prenom);
            list.add(sous_groupe);
            list.add(role);
            list.add(bonus);
            
            list = TransformString(list);
            
            try {
                
                //Creation de l'objet associé -----------------------------------------------------
                
                UtilisateursDAO utilisateur = null;
                utilisateur= creerObjet(request, utilisateur);
                
                //Actions du controleur ------------------------------------------------------------
                
                utilisateur.ModUtilisateur(code, list); // Charge toutes les informations
                
                //Operations effectuées avec succès------------------------------------------------
                
                retourVue(request, response,"/ShowUtilisateurs?choix_menu=null");
                
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
