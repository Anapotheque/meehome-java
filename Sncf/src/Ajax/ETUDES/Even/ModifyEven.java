package Ajax.ETUDES.Even;

import Models.TOOLS.Ajaxmodels.DVSG.AjaxModels_Onglets;
import Models.TOOLS.Controleur.MegaControleur;
import Models.ETUDES.Even.EvenDAO;
import Models.ETUDES.Gares.GaresDAO;
import Models.ETUDES.Gares.Link.LinkDAOGare_Even;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

//@author RABALLAND Romain ------------------------------------------------------------------------------
//@version v3.0 ------------------------------------------------------------------------------------------------------------

public class ModifyEven extends MegaControleur {
    
    String codeEven = "";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        
        //Recuperation des données envoyées -----------------------------------------------
        
        String choix = new String(request.getParameter("choix_menu"));
        
        if(choix.equals("null")){
            
            codeEven= new String(request.getParameter("codeEven"));
            
            try {
                
                //Creation de l'objet associé -----------------------------------------------------
                
                EvenDAO even = null;
                even= creerObjet(request, even);
                
                //Creation de l'objet associé -----------------------------------------------------
                
                GaresDAO gare = null;
                gare= creerObjet(request, gare);
                
                //Creation de l'objet associé -----------------------------------------------------
                
                LinkDAOGare_Even link = null;
                link= creerObjet(request, link);
                
                link.SetLink(null,codeEven);
                
                //Definition du type de texte pour l'envoie à la page jsp -----------------------------------------------------
                
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                
                //Affichage des informations ------------------------------------------------------
                
                //On affiche les options menu liées à la base
                AjaxModels_Onglets.getOnglet(request,response,"Modify","Even");
                
                out.println("<div id='corps_table'>");
                
                out.println("<table width=100% >");
                
                out.println("<tr>");
                out.println("<td>NOM</td>");
                out.println("<td>ACTIONS</td>");
                out.println("</tr>");
                
                out.println("<tr>");
                out.println("<td colspan='4'><hr with=100%>");
                
                out.println("</tr>");
                
                out.println("<tr>");
                
                
                
                int i = even.list_CodeEven.indexOf(codeEven);
                
                out.println("<form name='champ'>");
                
                out.println("<td><input type='text' value='"+even.list_nom.get(i)+"' name='nom'></td>");
                
                out.println("<td><a href=javascript:getHTMLCodeRequest('informations','ModifyEven','choix_menu=Modify','champ','null')><img src='images/ajouter.gif' width='48' height='48' alt='Enregistrer' border='0' onMouseOver='change_image(1,ajoutergris)' onMouseOut='change_image(1,ajouter)'/></a></td>");
                out.println("</tr>");
                
                out.println("</table>");
                out.println("</div><br>");
                
                out.println("<div id='corps_table'>");
                
                out.println("<table width=100% >");
                
                
                out.println("<tr>");
                out.println("<td>TOUS LES GARES</td>");
                out.println("<td>ACTIONS</td>");
                out.println("<td>GARES LIEES</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td colspan='3'><hr with=100%></td>");
                out.println("</tr>");
                out.println("<tr>");
                
                //-----------------------------------------------------------------------------------
                //ON AFFICHE LA PARTIE DE GESTION DES LIENS AVEC LA TABLE EVEN------------------------------
                
                
                //--------ON AFFICHE LA PREMIERE FENETRE CONTENANT TOUS LES ELEMENTS DE LA LISTE QUE L'ON PEUT LIER-------------
                
                out.println("<td><select name='association1' size=5>");
                
                for(int j=0; j<gare.list_CodeGare.size(); j++){ //On parcours tt la liste des gares
                    out.println("<option value='"+gare.list_CodeGare.get(j)+"'>"+gare.list_Gare.get(j)+"</option>");
                    
                }
                out.println("</select></td>");
                
                //------BOUTTONS DE CHOIX----------------------------------------------------------------------
                
                out.println("<td><div id='bouton1'>");
                out.println("<ul><li>");
                out.println("<a href=javascript:getHTMLCodeRequest('informations','ModifyEven','choix_menu=NewGare','champ','association1')>AJOUTER</a>");
                out.println("</li></ul></div>");
                
                out.println("<div id='bouton2'>");
                out.println("<ul><li>");
                out.println("<a href=javascript:getHTMLCodeRequest('informations','ModifyEven','choix_menu=DeleteGare','champ','association2')>RETIRER</a>");
                out.println("</li></ul></div>");
                out.println("</td>");
                
                //------ON AFFICHE LES ELEMENTS DEJA LIES A LA TABLE------------------------------------------
                
                out.println("<td><select name='association2' size=5 >");
                
                for(int j=0; j<link.list_codeGare.size(); j++){ //On parcours tt la liste des SECTIONS
                    link.GetnomGare(""+link.list_codeGare.get(j)+"");
                    out.println("<option value='"+link.list_codeGare.get(j)+"'>"+link.nomGare+"</option>");
                    
                }
                out.println("</select></td>");
                
                //-----------------------------------------------------------------------------------
                
                
                out.println("</table>");
                out.println("</tr></form>");
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
            
            String nom = new String(request.getParameter("nom"));
            
            ArrayList list = new ArrayList();
            
            list.add(nom);
            
            list = TransformString(list);
            
            try {
                
                //Creation de l'objet associé -----------------------------------------------------
                
                EvenDAO even = null;
                even = creerObjet(request, even);
                
                //Actions du controleur ------------------------------------------------------------
                
                even.ModEven(codeEven, list); // Charge toutes les informations
                
                //Operations effectuées avec succès------------------------------------------------
                
                retourVue(request, response,"/ShowEven?choix_menu=Null");
                
            }
            
            catch(SQLException ex) {
                retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState()+"&retour=ModifyEven&nomCode=codeEven&codeRenvoie="+codeEven+"");
                System.out.println("\n\nDEBUT ERREUR<==========================================\n");
                ex.printStackTrace();
                System.out.println("\nFIN ERREUR<==============================================\n\n");
            }
            
        } else if(choix.equals("NewGare")){
            
            
            String codeGare = new String(request.getParameter("codeGare"));
            
            //Creation de l'objet associé -----------------------------------------------------
            
            LinkDAOGare_Even link = null;
            link= creerObjet(request, link);
            try {
                link.NewLink(codeGare, codeEven);
            } catch (SQLException ex) {
                retourVue(request, response,"/Erreur?message="+ex.getMessage());
                ex.printStackTrace();
            }
            
            //Operations effectuées avec succès------------------------------------------------
            
            retourVue(request, response,"/ModifyEven?choix_menu=null&codeEven="+codeEven+"");
        } else if(choix.equals("DeleteGare")){
            
            String codeGare = new String(request.getParameter("codeGare"));
            
            //Creation de l'objet associé -----------------------------------------------------
            
            LinkDAOGare_Even link = null;
            link= creerObjet(request, link);
            
            try {
                link.DeleteLink(codeGare, codeEven);
            }        catch(SQLException ex) {
                retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState());
                System.out.println("\n\nDEBUT ERREUR<==========================================\n");
                ex.printStackTrace();
                System.out.println("\nFIN ERREUR<==============================================\n\n");
            }
            
            //Operations effectuées avec succès------------------------------------------------
            
            retourVue(request, response,"/ModifyEven?choix_menu=null&codeEven="+codeEven+"");
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
