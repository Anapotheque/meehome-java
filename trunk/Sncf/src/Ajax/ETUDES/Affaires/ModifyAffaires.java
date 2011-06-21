package Ajax.ETUDES.Affaires;

import Models.TOOLS.Ajaxmodels.DVSG.AjaxModels_Onglets;
import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Controleur.MegaControleur;
import Models.ETUDES.Affaires.AffairesDAO;
import Models.TOOLS.Utilisateurs.UtilisateursDAO;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

//@author RABALLAND Romain ------------------------------------------------------------------------------
//@version v3.0 ------------------------------------------------------------------------------------------------------------

public class ModifyAffaires extends MegaControleur {
    
    String codeAffaire = "";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //RECUPERATION DATA
        String choix = new String(request.getParameter("choix_menu"));
        
        if(choix.equals("null")){
            
            //RECUPERATION DATA
            codeAffaire = new String(request.getParameter("codeAffaire"));
            
            //Creation de l'objet associé -----------------------------------------------------
            
            AffairesDAO affaire = null;
            affaire= creerObjet(request, affaire);
            
            //Definition du type de texte pour l'envoie à la page jsp -----------------------------------------------------
            
            response.setContentType("text/html;charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            
            //Affichage des informations ------------------------------------------------------
            
            //On affiche les options menu liées à la base
            AjaxModels_Onglets.getOnglet(request,response,"Modify","Affaires");
            
            try {
                affaire.GetCodeAffaire();
            } catch (SQLException ex) {
                retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState());
                System.out.println("\n\nDEBUT ERREUR<==========================================\n");
                ex.printStackTrace();
                System.out.println("\nFIN ERREUR<==============================================\n\n");
            }
            
            int i = affaire.list_CodeAffaire.indexOf(codeAffaire);
            
            //TITRE DE LA PAGE
            TOOLS_HTML.getMessage(out,"h4","AFFAIRES");
            
            out.println("<div id='corps_table'>");
            
            
            
            
            out.println("<table width=80% border = 0>");
            
            
            out.println("<form name='champ'>");
            
            out.println("<tr>");
            out.println("<td align = right width=15%>NUMERO AFFAIRE : </td><td align = left width=85% colspan = 5>"+codeAffaire+"</td>");
            out.println("</tr>");
            
            out.println("<tr>");
            out.println("<td align = right>NOM : </td><td align = left colspan = 5><input type='text' value='"+affaire.list_Nom.get(i)+"' name='nom' style='width:600px'></td>");
            out.println("</tr>");
            
            out.println("<tr>");
            out.println("<td align = right>VERIF PT : </td><td align = left width=10%><input type='text' value='"+affaire.list_Imputation.get(i)+"' name='imputation' style='width:145px' maxlength='12'></td>");
            
            out.println("<td align = right width=5%>ETUDE SE : </td><td align = left width=10%><input type='text' value='"+affaire.list_Compte_Etude.get(i)+"' name='compteEtude' style='width:145px' maxlength='12'></td>");
            
            out.println("<td align = right width=5%>PARAMETRAGE : </td><td align = left width=10%><input type='text' value='"+affaire.list_Parametrage.get(i)+"' name='parametrage' style='width:145px' maxlength='12'></td>");
            out.println("</tr>");
            
            out.println("<tr>");
            out.println("<td align = right>SYCOMORE : </td><td align = left colspan = 5><input type='text' value='"+affaire.list_Sycomore.get(i)+"' name='sycomore' style='width:300px'></td>");
            out.println("</tr>");
            
            out.println("<tr>");
            out.println("<td align = right>OBSERVATIONS : </td><td align = left colspan = 5><input type='text' value='"+affaire.list_Observations.get(i)+"' name='observations' style='width:600px'></td>");
            out.println("</tr>");
            
            
            out.println("<tr><td></td><td>");
            
            out.println("<div id='bouton1'>");
            out.println("<ul>");
            out.println("<li><a href=javascript:getHTMLCodeRequest('informations','ModifyAffaires','choix_menu=Modify','champ','null')>ENREGISTRER</a></li><br>");
            out.println("</ul></div>");
            out.println("</td></tr>");
            
            out.println("</form>");
            out.println("</table>");
            
            out.println("</div>");
            out.println("</div>");
            
            out.close();
            
            
        } else if(choix.equals("Modify")){
            
            String nom = new String(request.getParameter("nom"));
            String observations = new String(request.getParameter("observations"));
            String imputation = new String(request.getParameter("imputation"));
            String compteEtude = new String(request.getParameter("compteEtude"));
            String sycomore = new String(request.getParameter("sycomore"));
            String parametrage = new String(request.getParameter("parametrage"));
            
            ArrayList list = new ArrayList();
            
            list.add(nom);
            list.add(observations);
            list.add(imputation);
            list.add(compteEtude);
            list.add(sycomore);
            list.add(parametrage);
            
            list = TransformString(list);
            
            //Creation de l'objet associé -----------------------------------------------------
            
            AffairesDAO affaire = null;
            affaire= creerObjet(request, affaire);
            
            UtilisateursDAO utilisateur = null;
            utilisateur = creerObjet(request, utilisateur);
            
            try {
                affaire.Mod(codeAffaire, list); // Charge toutes les informations
                Mouchard(request, utilisateur,"Modification","Affaires");
            } catch (SQLException ex) {
                retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState()+"&retour=ModifyAffaires&nomCode=codeAffaire&codeRenvoie="+codeAffaire+"");
                System.out.println("\n\nDEBUT ERREUR<==========================================\n");
                ex.printStackTrace();
                System.out.println("\nFIN ERREUR<==============================================\n\n");
            }
            
            //Operations effectuées avec succès------------------------------------------------
            
            retourVue(request, response,"/ShowAffaires?choix_menu=Null");
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
