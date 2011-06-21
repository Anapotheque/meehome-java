package Ajax.ETUDES.Gares;

import Models.TOOLS.Ajaxmodels.DVSG.AjaxModels_Onglets;
import Models.TOOLS.Controleur.MegaControleur;
import Models.ETUDES.Gares.GaresDAO;
import Models.ETUDES.Gares.Link.LinkDAOGare_Even;
import Models.ETUDES.Even.EvenDAO;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

//@author RABALLAND Romain ------------------------------------------------------------------------------
//@version v3.0 ------------------------------------------------------------------------------------------------------------

public class ModifyGares extends MegaControleur {
    
    String codeGare = "";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        
        //Recuperation des données envoyées -----------------------------------------------
        
        String choix = request.getParameter("choix_menu");
        
        if(choix.equals("null")){
            
            codeGare= request.getParameter("codeGare");
            
            try {
                
                //Creation de l'objet associé -----------------------------------------------------
                
                GaresDAO gare = null;
                gare= creerObjet(request, gare);
                
                EvenDAO even = null;
                even= creerObjet(request, even);
                even.Set();
                
                LinkDAOGare_Even link = null;
                link= creerObjet(request, link);
                link.SetLink(codeGare,null);
                
                //Definition du type de texte pour l'envoie à la page jsp -----------------------------------------------------
                
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                
                //Affichage des informations ------------------------------------------------------
                
                //On affiche les options menu liées à la base
                AjaxModels_Onglets.getOnglet(request,response,"Modify","Gares");
                
                out.println("<div id='corps_table'>");
                
                out.println("<table width=100% >");
                
                out.println("<tr>");
                out.println("<td>GARE</td>");
                out.println("<td>LIGNE / KM</td>");
                out.println("<td>GROUPE</td>");
                out.println("<td>INFOS</td>");
                out.println("<td>ACTIONS</td>");
                out.println("</tr>");
                
                out.println("<tr>");
                out.println("<td colspan='5'><hr with=100%>");
                out.println("</tr>");
                
                out.println("<tr>");
                int i = gare.list_CodeGare.indexOf(codeGare);
                
                out.println("<form name='champ'>");
                
                out.println("<td><input type='text' value='"+gare.list_Gare.get(i)+"' name='gare'></td>");
                out.println("<td><input type='text' value='"+gare.list_Km.get(i)+"' name='km'></td>");
                
                if(gare.list_Groupe.get(i).equals("SE")){
                    out.println("<td><select name='groupe'>");
                    out.println("<option value='SE' selected>Schemas Electriques</option>");
                    out.println("<option value='PT'>Plans Techniques</option>");
                    out.println("<option value='LesDeux'>Les Deux</option>");
                    out.println("</select></td>");
                } else if(gare.list_Groupe.get(i).equals("PT")){
                    out.println("<td><select name='groupe'>");
                    out.println("<option value='SE'>Estimation</option>");
                    out.println("<option value='PT' selected>Plans Techniques</option>");
                    out.println("<option value='LesDeux'>Les Deux</option>");
                    out.println("</select></td>");
                } else if(gare.list_Groupe.get(i).equals("LesDeux")){
                    out.println("<td><select name='groupe'>");
                    out.println("<option value='SE'>Estimation</option>");
                    out.println("<option value='PT'>Plans Techniques</option>");
                    out.println("<option value='LesDeux' selected>Les Deux</option>");
                    out.println("</select></td>");
                }
                
                out.println("<td><input type='text' value='"+gare.list_Infos.get(i)+"' name='infos'></td>");
                out.println("<td><a href=javascript:getHTMLCodeRequest('informations','ModifyGares','choix_menu=Modify','champ','null')><img src='images/ajouter.gif' width='48' height='48' alt='Enregistrer' border='0' onMouseOver='change_image(1,ajoutergris)' onMouseOut='change_image(1,ajouter)'/></a></td>");
                
                out.println("</tr>");
                out.println("</table>");
                out.println("</div><br>");
                
                //-----------------------------------------------------------------------------------
                //ON AFFICHE LA PARTIE DE GESTION DES LIENS AVEC LA TABLE EVEN------------------------------
                //-----------------------------------------------------------------------------------
                
                out.println("<div id='corps_table'>");
                
                out.println("<table width=100% >");
                
                out.println("<tr>");
                out.println("<td>TOUTS LES EVENS</td>");
                out.println("<td>ACTIONS</td>");
                out.println("<td>EVENS LIES</td>");
                out.println("</tr>");
                
                out.println("<tr>");
                out.println("<td colspan='3'><hr with=100%>");
                out.println("</tr>");
                
                out.println("<tr>");
                
                //--------ON AFFICHE LA PREMIERE FENETRE CONTENANT TOUS LES ELEMENTS DE LA LISTE QUE L'ON PEUT LIER-------------
                
                out.println("<td><select name='association1' size=5>");
                
                for(int j=0; j<even.list_CodeEven.size(); j++){ //On parcours tt la liste des SECTIONS
                    out.println("<option value='"+even.list_CodeEven.get(j)+"'>"+even.list_nom.get(j)+"</option>");
                    
                }
                out.println("</select></td>");
                
                //------BOUTTONS DE CHOIX----------------------------------------------------------------------
                
                out.println("<td><div id='bouton1'>");
                out.println("<ul><li>");
                out.println("<a href=javascript:getHTMLCodeRequest('informations','ModifyGares','choix_menu=NewLink','champ','association1')>AJOUTER</a>");
                out.println("</li></ul></div>");
                
                out.println("<div id='bouton2'>");
                out.println("<ul><li>");
                out.println("<a href=javascript:getHTMLCodeRequest('informations','ModifyGares','choix_menu=DeleteLink','champ','association2')>RETIRER</a>");
                out.println("</li></ul></div>");
                out.println("</td>");
                
                //------ON AFFICHE LES ELEMENTS DEJA LIES A LA TABLE------------------------------------------
                
                out.println("<td><select name='association2' size=5 >");
                
                
                
                for(int j=0; j<link.list_codeEven.size(); j++){ //On parcours tt la liste des SECTIONS
                    link.GetnomEven(""+link.list_codeEven.get(j)+"");
                    out.println("<option value='"+link.list_codeEven.get(j)+"'>"+link.nomEven+"</option>");
                    
                }
                out.println("</select></td>");
                
                //-----------------------------------------------------------------------------------
                
                
                out.println("</tr>");
                out.println("</form>");
                out.println("</table>");
                
                out.println("</div>");
                
                
                out.println("<h3>ATTENTION!<br>Toutes modifications concernant les differents liens sont immediates</h3>");
                
                
                out.println("</div>");
                
                out.close();
                
            }
            
            //Gestion des erreurs -------------------------------------------------------------
            
            catch(SQLException ex) {
                retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState());
                System.out.println("\n\nDEBUT ERREUR<==========================================\n");
                ex.printStackTrace();
                System.out.println("\nFIN ERREUR<==============================================\n\n");
                
            }
        } else if(choix.equals("Modify")){
            
            String nom_gare = new String(request.getParameter("gare"));
            String km = new String(request.getParameter("km"));
            String groupe = new String(request.getParameter("groupe"));
            String infos = new String(request.getParameter("infos"));
            
            ArrayList list = new ArrayList();
            
            list.add(nom_gare);
            list.add(km);
            list.add(groupe);
            list.add(infos);
            
            list = TransformString(list);
            
            try {
                
                //Creation de l'objet associé -----------------------------------------------------
                
                GaresDAO gare = null;
                gare = creerObjet(request, gare);
                
                //Actions du controleur ------------------------------------------------------------
                
                gare.ModGare(codeGare, list); // Charge toutes les informations
                
                //Operations effectuées avec succès------------------------------------------------
                
                retourVue(request, response,"/ShowGares?choix_menu=Null");
                
            }
            
            catch(SQLException ex) {
                retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState()+"&retour=ModifyGares&nomCode=codeGare&codeRenvoie="+codeGare+"");
                System.out.println("\n\nDEBUT ERREUR<==========================================\n");
                ex.printStackTrace();
                System.out.println("\nFIN ERREUR<==============================================\n\n");
                
            }
            
        } else if(choix.equals("NewLink")){
            
            
            String codeEven = new String(request.getParameter("codeEven"));
            
            //Creation de l'objet associé -----------------------------------------------------
            
            LinkDAOGare_Even link = null;
            link= creerObjet(request, link);
            
            try {
                link.NewLink(codeGare, codeEven);
            }         catch(SQLException ex) {
                retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState());
                System.out.println("\n\nDEBUT ERREUR<==========================================\n");
                ex.printStackTrace();
                System.out.println("\nFIN ERREUR<==============================================\n\n");
            }
            
            //Operations effectuées avec succès------------------------------------------------
            
            retourVue(request, response,"/ModifyGares?choix_menu=null&codeGare="+codeGare+"");
        } else if(choix.equals("DeleteLink")){
            
            String codeEven = new String(request.getParameter("codeEven"));
            
            //Creation de l'objet associé -----------------------------------------------------
            
            LinkDAOGare_Even link = null;
            link= creerObjet(request, link);
            
            try {
                link.DeleteLink(codeGare, codeEven);
            } catch(SQLException ex) {
                retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState());
                System.out.println("\n\nDEBUT ERREUR<==========================================\n");
                ex.printStackTrace();
                System.out.println("\nFIN ERREUR<==============================================\n\n");
            }
            
            //Operations effectuées avec succès------------------------------------------------
            
            retourVue(request, response,"/ModifyGares?choix_menu=null&codeGare="+codeGare+"");
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
