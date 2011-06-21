package Ajax.ETUDES.Gares;

import Models.TOOLS.Ajaxmodels.DVSG.AjaxModels_Onglets;
import Models.TOOLS.Controleur.MegaControleur;
import Models.ETUDES.Gares.GaresDAO;
import Models.ETUDES.Gares.Link.LinkDAOGare_Even;
import Models.ETUDES.Even.EvenDAO;
import Models.TOOLS.Ajaxmodels.TOOLS_HTML;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

//@author RABALLAND Romain ------------------------------------------------------------------------------
//@version v3.0 ------------------------------------------------------------------------------------------------------------

public class NewGares extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //Recuperation des données envoyées -----------------------------------------------
        
        String choix = request.getParameter("choix_menu");
        
        // On affiche la page si le choix est egale à 'Null'--------------------------------------
        
        if(choix.equals("Null")){
            
            try {
                
                //Creation de l'objet associé -----------------------------------------------------
                
                EvenDAO even = null;
                even = creerObjet(request, even);
                even.Set();
                
                //Definition du type de texte pour l'envoie à la page jsp -----------------------------------------------------
                
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                
                //Affichage des informations ------------------------------------------------------
                
                //On affiche les options menu liées à la base
                
                AjaxModels_Onglets.getOnglet(request,response,"Creation","Gares");
                
                //ON affiche les informations concernant la table
                out.println("<div id='corps_table'>");
                
                out.println("<table width=100% >");
                
                out.println("<tr>");
                out.println("<td>GARE</td>");
                out.println("<td>LIGNE / KM</td>");
                out.println("<td>GROUPE</td>");
                out.println("<td>INFOS</td>");
                out.println("<td>EVEN</td>");
                out.println("<td>ACTIONS</td>");
                out.println("</tr>");
                
                out.println("<tr>");
                out.println("<td colspan='6'><hr with=100%>");
                
                out.println("</tr>");
                
                out.println("<tr>");
                
                out.println("<form name='champ'>");
                out.println("<td><input type='text' value='A REMPLIR' name='gare'></td>");
                out.println("<td><input type='text' value='A REMPLIR' name='km'></td>");
                
                out.println("<td><select name='groupe'>");
                out.println("<option value='SE'>Schemas Electriques</option>");
                out.println("<option value='PT'>Plans Techniques</option>");
                out.println("<option value='LesDeux'>Les Deux</option>");
                out.println("</select></td>");
                
                out.println("<td><input type='text' value='' name='infos'></td>");
                
                //-----------------------------------------------------------------------------
                //----------  On enregistre l'association -------------------------------------
                //-----------------------------------------------------------------------------
                
                out.println("<td><select name='Even' size=5 multiple>");
                out.println("<option value='null' selected>Aucunes sections</option>");
                
                for(int i=0; i<even.list_CodeEven.size(); i++)
                    out.println("<option value='"+even.list_CodeEven.get(i)+"'>"+even.list_nom.get(i)+"</option>");
                
                out.println("</select></td>");
                
                //-----------------------------------------------------------------------------
                //----------   THAT'S ALL FOLKS -----------------------------------------------
                //-----------------------------------------------------------------------------
                
                out.println("<td><a href=javascript:getHTMLCodeRequest('informations','NewGares','choix_menu=New','champ')><img src='images/ajouter.gif' width='48' height='48' alt='Enregistrer' border='0' onMouseOver='change_image(1,ajoutergris)' onMouseOut='change_image(1,ajouter)'/></a></td>");
                out.println("</tr>");
                out.println("</form>");
                
                out.println("</table>");
                
                out.println("</div>");
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
        }
        
        // On enregistre le resultat si le choix vaut 'New'--------------------------------------
        
        else if(choix.equals("New")){
            
            try {
                
                String nom_gare = new String(request.getParameter("gare"));
                String km = new String(request.getParameter("km"));
                String groupe = new String(request.getParameter("groupe"));
                String infos = new String(request.getParameter("infos"));
                int nombreSelect = Integer.parseInt(request.getParameter("NombreSelect"));
                
                ArrayList list = new ArrayList();
                
                list.add(nom_gare);
                list.add(km);
                list.add(groupe);
                list.add(infos);
                
                list = TransformString(list);
                
                //Gestion de la liaison vers les tables infini infini
                ArrayList listcodeEven = new ArrayList();
                
                // On recupere chaque elements selectionnés
                
                for(int i=1; i<=nombreSelect; i++){
                    String even = new String(request.getParameter("codeEven"+i+""));
                    listcodeEven.add(even);
                }
                
                //Creation de l'objet associé -----------------------------------------------------
                
                GaresDAO gare = null;
                gare = creerObjet(request, gare);
                
                LinkDAOGare_Even link = null;
                link = creerObjet(request, link);
                
                //Actions du controleur ------------------------------------------------------------
                
                gare.NewGare(list); // Charge toutes les informations
                
                //On ajoute dans la table de liaison des deux table si et seulement si on a selectionné une section de gare
                if(!listcodeEven.get(0).equals("null")){
                    
                    String codeGare = gare.GetLastCodeGare();
                    
                    //On ajoute dans la table interposé les element qui vont bien
                    
                    for(int i=0;i<=nombreSelect-1;i++)
                        link.NewLink(""+codeGare+"", ""+listcodeEven.get(i)+"");
                }
                
                //Operations effectuées avec succès------------------------------------------------
                
                retourVue(request, response,"/ShowGares?choix_menu=Null");
                
            }
            
            catch(SQLException ex) {      
                
                retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState()+"&retour=NewGares");
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
