package Ajax.ETUDES.Even;

import Models.TOOLS.Ajaxmodels.DVSG.AjaxModels_Onglets;
import Models.ETUDES.Even.EvenDAO;
import Models.ETUDES.Gares.GaresDAO;

import Models.TOOLS.Controleur.MegaControleur;
import Models.ETUDES.Gares.Link.LinkDAOGare_Even;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

//@author RABALLAND Romain ------------------------------------------------------------------------------
//@version v3.0 ------------------------------------------------------------------------------------------------------------

public class NewEven extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //Recuperation des données envoyées -----------------------------------------------
        
        String choix = new String(request.getParameter("choix_menu"));
        
        // On affiche la page si le choix est egale à 'Null'--------------------------------------
        
        if(choix.equals("Null")){
            
            try {
                
                //Creation de l'objet associé -----------------------------------------------------
                
                GaresDAO gare = null;
                gare = creerObjet(request, gare);
                gare.Set();
                //Definition du type de texte pour l'envoie à la page jsp -----------------------------------------------------
                
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                
                //Affichage des informations ------------------------------------------------------
                
                //On affiche les options menu liées à la base
                
                AjaxModels_Onglets.getOnglet(request,response,"Creation","Even");
                
                out.println("<div id='corps_table'>");
                
                out.println("<table width=100% >");
                
                out.println("<tr>");
                out.println("<td>NOM</td>");
                out.println("<td>GARE</td>");
                out.println("<td>ACTIONS</td>");
                out.println("</tr>");
                
                out.println("<tr>");
                out.println("<td colspan='3'><hr with=100% size='1'>");
                
                out.println("</tr>");
                
                out.println("<tr>");
                
                out.println("<form name='champ'>");
                out.println("<td><input type='text' value='A REMPLIR' name='nom'></td>");
                
                //-----------------------------------------------------------------------------
                //----------  On enregistre l'association -------------------------------------
                //-----------------------------------------------------------------------------
                
                
                out.println("<td><select name='Gare' size=5 multiple>");
                out.println("<option value='null' selected>Aucunes gares</option>");
                
                for(int i=0; i<gare.list_CodeGare.size(); i++)
                    out.println("<option value='"+gare.list_CodeGare.get(i)+"'>"+gare.list_Gare.get(i)+"</option>");
                
                out.println("</select></td>");
                
                
                out.println("<td><a href=javascript:getHTMLCodeRequest('informations','NewEven','choix_menu=New','champ')><img src='images/ajouter.gif' width='48' height='48' alt='Enregistrer' border='0' onMouseOver='change_image(1,ajoutergris)' onMouseOut='change_image(1,ajouter)'/></a></td>");
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
            
            String nom = new String(request.getParameter("nom"));
            int nombreSelect = Integer.parseInt(request.getParameter("NombreSelect"));
            
            ArrayList list = new ArrayList();
            
            list.add(nom);
            
            list = TransformString(list);
            
            //Gestion de la liaison vers les tables infini infini
            ArrayList listcodeGare = new ArrayList();
            
            // On recupere chaque elements selectionnés
            
            for(int i=1; i<=nombreSelect; i++){
                String gare = new String(request.getParameter("codeGare"+i+""));
                listcodeGare.add(gare);
            }
            
            try {
                
                //Creation de l'objet associé -----------------------------------------------------
                
                EvenDAO even = null;
                even = creerObjet(request, even);
                
                LinkDAOGare_Even link = null;
                link = creerObjet(request, link);
                //Actions du controleur ------------------------------------------------------------
                
                even.NewEven(list); // Charge toutes les informations
                
                //On ajoute dans la table de liaison des deux table si et seulement si on a selectionné une section de gare
                if(!listcodeGare.get(0).equals("null")){
                    
                    String codeEven = even.GetLastCodeEven();
                    
                    //On ajoute dans la table interposé les element qui vont bien
                    
                    for(int i=0;i<=nombreSelect-1;i++)
                        link.NewLink(""+listcodeGare.get(i)+"",""+codeEven+"");
                }
                
                //Operations effectuées avec succès------------------------------------------------
                
                retourVue(request, response,"/ShowEven?choix_menu=Null");
                
            }
            
            catch(SQLException ex) {
                
                retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState()+"&retour=NewEven");
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
