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

public class InscriptionUtilisateurs extends MegaControleur {
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        String choix = request.getParameter("choix_menu");
        
        if(choix.equals("null")){
            
            try {
                
                //Definition du type de texte pour l'envoie à la page jsp -----------------------------------------------------
                
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();

                out.println("<div id='corps'>");
                
                out.println("<table width=80%>");
                out.println("<th>IDENTIFIANT</th>");
                out.println("<th>MOT DE PASSE</th>");
                out.println("<th>NOM</th>");
                out.println("<th>PRENOM</th>");
                out.println("<th>SOUSGROUPE</th>");
                out.println("<th>ACTIONS</th>");

                out.println("<tr>");
                out.println("<td colspan='6'><hr with=100%></td>");
                out.println("</tr>");
                
                out.println("<tr>");
                out.println("<form name='champ2'>");
                out.println("<td><input type='text' value='' name='login' style='width:145px'></td>");
                out.println("<td><input type='password' value='' name='password' style='width:145px'></td>");
                out.println("<td><input type='text' value='' name='nom' style='width:145px'></td>");
                out.println("<td><input type='text' value='' name='prenom' style='width:145px'></td>");
                
                out.println("<td><select name='sous_groupe'>");
                out.println("<option value='Estimation'>Estimation</option>");
                out.println("<option value='SE'>Schémas d'executions</option>");
                out.println("<option value='PT'>Plans Techniques</option>");
                out.println("<option value='Verification'>Verification</option>");
                out.println("<option value='Management'>Management</option>");
                out.println("<option value='Stagiaire'>Stagiaire</option>");
                out.println("</select></td>");
                
                out.println("<td><a href=javascript:getHTMLCodeRequest('informations','InscriptionUtilisateurs','choix_menu=New','champ2')><img src='images/ajouter.gif' width='48' height='48' alt='Enregistrer' border='0' onMouseOver='change_image(0,ajoutergris)' onMouseOut='change_image(0,ajouter)'/></a></td>");
                out.println("</tr>");
                out.println("</form>");
                
                out.println("</table>");
                out.println("</div>");

                out.close();
                
            }
            //Gestion des erreurs -------------------------------------------------------------
            
            catch(Exception e) {
                retourVue(request, response,"/Erreur?message="+e.getMessage());
                e.getMessage(); 
            }
        }
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
                
                //Creation de l'objet associé -----------------------------------------------------
                
                UtilisateursDAO utilisateur = null;
                utilisateur= creerObjet(request, utilisateur);
                
                //Actions du controleur ------------------------------------------------------------
                
                utilisateur.NewUtilisateur(list); // Charge toutes les informations
                
                //Operations effectuées avec succès------------------------------------------------
                
                retourVue(request, response,"/DoneUtilisateurs?choix_menu=inscription");
                
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
