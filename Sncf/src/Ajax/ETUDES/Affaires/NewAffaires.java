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

public class NewAffaires extends MegaControleur {
    
    static PrintWriter out = null;
    static private String chaine = "";
    AffairesDAO affaire = null;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //Recuperation des données envoyées -----------------------------------------------
        
        String choix = request.getParameter("choix_menu");
        
        if(choix.equals("Null")){
            
            try {
                
                //Creation de l'objet associé -----------------------------------------------------
                
                affaire = creerObjet(request, affaire);
                
                //Definition du type de texte pour l'envoie à la page jsp -----------------------------------------------------
                
                response.setContentType("text/html;charset=ISO-8859-1");
                out = response.getWriter();
                
                //Affichage des informations ------------------------------------------------------
                
                //On affiche les options menu liées à la base
                
                AjaxModels_Onglets.getOnglet(request,response,"Creation","Affaires");
                
                TOOLS_HTML.getMessage(out,"h1","Veuillez renseignez tous les champs qui vous seront utiles");
                
                TOOLS_HTML.OpenDiv(out,"corps_table");
                TOOLS_HTML.OpenTable(out,"80%",0);
                TOOLS_HTML.OpenForm(out,"Champ");
                
                out.println("<span style='color: black; text-align : left;'>");
                TOOLS_HTML.OpenTrTable(out);
                out.println("<td align = right>NUMERO AFFAIRE : </td><td align = left>"+affaire.GetCodeAffaire()+"</td>");
                TOOLS_HTML.CloseTrTable(out);
                
                TOOLS_HTML.OpenTrTable(out);
                out.println("<td align = right>NOM : </td><td align = left><input type='text' value='A REMPLIR' name='nom' style='width:600px'></td>");
                TOOLS_HTML.CloseTrTable(out);
                
                TOOLS_HTML.OpenTrTable(out);
                out.println("<td align = right>VERIF PT : </td><td align = left><input type='text' value='' name='imputation' style='width:145px' maxlength='12'></td>");
                
                out.println("<td align = right width=10%>ETUDE SE : </td><td align = left><input type='text' value='' name='compteEtude' style='width:145px' maxlength='12'></td>");
                
                out.println("<td align = right width=10%>PARAMETRAGE : </td><td align = left><input type='text' value='' name='parametrage' style='width:145px' maxlength='12'></td>");
                TOOLS_HTML.CloseTrTable(out);
                
                TOOLS_HTML.OpenTrTable(out);
                out.println("<td align = right>SYCOMORE : </td><td align = left><input type='text' value='' name='sycomore1' style='width:145px' maxlength='12'></td>");
                
                out.println("<td align = right width=10%></td><td align = left><input type='text' value='' name='sycomore2' style='width:145px' maxlength='12'></td>");
                
                out.println("<td align = right width=10%></td><td align = left><input type='text' value='' name='sycomore3' style='width:145px' maxlength='12'></td>");
                TOOLS_HTML.CloseTrTable(out);
                
                TOOLS_HTML.OpenTrTable(out);
                out.println("<td align = right>OBSERVATIONS : </td><td align = left colspan = 5><input type='text' value='' name='observations' style='width:600px'></td>");
                TOOLS_HTML.CloseTrTable(out);
                
                TOOLS_HTML.OpenTrTable(out);
                TOOLS_HTML.OpenTdTable(out,6,"right");
                TOOLS_HTML.AddButton(out,"informations","NewAffaires","New","champ","Enregistrer","ajouter.gif","ajouter");
                TOOLS_HTML.CloseTdTable(out);
                TOOLS_HTML.CloseTrTable(out);
                TOOLS_HTML.CloseForm(out);
                TOOLS_HTML.CloseTable(out);
                TOOLS_HTML.CloseDiv(out);
                TOOLS_HTML.CloseDiv(out);
                out.println("</span>");
                out.close();
                
            }
            //Gestion des erreurs -------------------------------------------------------------
            
            catch(SQLException ex) {
                retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState());
                System.out.println("\n\nDEBUT ERREUR<==========================================\n");
                ex.printStackTrace();
                System.out.println("\nFIN ERREUR<==============================================\n\n");
            }
        }
        
        // On enregistre le resultat si le choix vaut 'New'--------------------------------------
        
        else if(choix.equals("New")){
            
            affaire = creerObjet(request, affaire);
            
            String nom = new String(request.getParameter("nom").replaceAll("Ã©","é"));
            String observations = new String(request.getParameter("observations").replaceAll("Ã©","é"));
            String imputation = new String(request.getParameter("imputation").replaceAll("Ã©","é"));
            String compteEtude = new String(request.getParameter("compteEtude").replaceAll("Ã©","é"));
            String sycomore = new String(request.getParameter("sycomore").replaceAll("Ã©","é"));
            String parametrage = new String(request.getParameter("parametrage").replaceAll("Ã©","é"));
            
            ArrayList list = new ArrayList();
            
            try {
                list.add(""+affaire.GetCodeAffaire()+"");
            } catch (SQLException ex) {
                retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState());
                System.out.println("\n\nDEBUT ERREUR<==========================================\n");
                ex.printStackTrace();
                System.out.println("\nFIN ERREUR<==============================================\n\n");
            }
            list.add(nom);
            list.add(observations);
            list.add(imputation);
            list.add(compteEtude);
            list.add(sycomore);
            list.add(parametrage);
            
            list = TransformString(list);
            
            try {
                
                //Creation de l'objet associé -----------------------------------------------------
                
                AffairesDAO affaire = null;
                affaire= creerObjet(request, affaire);
                
                UtilisateursDAO utilisateur = null;
                utilisateur = creerObjet(request, utilisateur);
                
                //Actions du controleur ------------------------------------------------------------
                
                affaire.New(list); // Charge toutes les informations
                Mouchard(request, utilisateur,"Ajout","Affaires");
                //Operations effectuées avec succès------------------------------------------------
                
                retourVue(request, response,"/ShowAffaires?choix_menu=Null");
                
            }
            
            catch(SQLException ex) {
                retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState()+"&retour=NewAffaires");
                System.out.println("\n\nDEBUT ERREUR<==========================================\n");
                ex.printStackTrace();
                System.out.println("\nFIN ERREUR<==============================================\n\n");
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
