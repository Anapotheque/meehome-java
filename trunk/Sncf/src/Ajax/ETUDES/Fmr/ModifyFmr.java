package Ajax.ETUDES.Fmr;

import Models.TOOLS.Ajaxmodels.DVSG.AjaxModels_Onglets;
import Models.TOOLS.Controleur.MegaControleur;
import Models.ETUDES.Fmr.FmrDAO;
import Models.ETUDES.Gares.GaresDAO;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

//@author RABALLAND Romain ------------------------------------------------------------------------------
//@version v3.0 ------------------------------------------------------------------------------------------------------------

public class ModifyFmr extends MegaControleur {
    
    String codeFmr = "";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //Recuperation des données envoyées -----------------------------------------------
        
        String choix = new String(request.getParameter("choix_menu"));
        
        if(choix.equals("null")){
            
            codeFmr = new String(request.getParameter("codeFmr"));
            
            try {
                
                //Creation de l'objet associé -----------------------------------------------------
                
                FmrDAO fmr = null;
                fmr= creerObjet(request, fmr);
                
                GaresDAO gare = null;
                gare= creerObjet(request, gare);
                gare.Set();
                //Definition du type de texte pour l'envoie à la page jsp -----------------------------------------------------
                
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                
                //Affichage des informations ------------------------------------------------------
                
                //On affiche les options menu liées à la base
                AjaxModels_Onglets.getOnglet(request,response,"Modify","Fmr");
                
                out.println("<div id='corps_table'>");
                
                out.println("<table width=80% >");
                
                out.println("<tr>");
                out.println("<td>GARE</td>");
                out.println("<td>NUMERO FMR</td>");
                out.println("<td>ETABLISSEMENT</td>");
                out.println("<td>ENVOI</td>");
                out.println("<td>TRAITEMENT</td>");
                out.println("<td>ACCORD</td>");
                out.println("<td>INCORPORATION</td>");
                out.println("<td>OBSERVATIONS</td>");
                out.println("<td>ACTION</td>");
                out.println("</tr>");
                
                out.println("<tr>");
                out.println("<td colspan='9'><hr with=100%>");
                
                out.println("</tr>");
                
                out.println("<tr>");
                
                int i = fmr.list_CodeFmr.indexOf(codeFmr);
                out.println("<form name='champ'>");
                
                String codeGare = ""+fmr.list_CodeGare.get(i);
                out.println("<td><select name='Gare'>");
                
                out.println("<option value='"+codeGare+"' selected>"+gare.GetnomGare(codeGare)+"</option>");
                
                for(int j=0; j<gare.list_CodeGare.size(); j++){ //On parcours tt la liste des SECTIONS
                    
                    if(!gare.list_CodeGare.get(j).equals(codeGare))
                        out.println("<option value='"+gare.list_CodeGare.get(j)+"'>"+gare.list_Gare.get(j)+"</option>");
                }
                
                out.println("</select></td>");
                
                
                out.println("<td><input type='text' value='"+fmr.list_NumeroFmr.get(i)+"' name='numeroFmr'></td>");
                out.println("<td><input type='text' value='"+fmr.list_Etablissement.get(i)+"' name='etablissement'></td>");
                out.println("<td><input type='text' value='"+fmr.list_envoi.get(i)+"' name='envoi'></td>");
                out.println("<td><input type='text' value='"+fmr.list_traitement.get(i)+"' name='traitement'></td>");
                
                out.println("<td><select name='accord'>");
                
                if(fmr.list_accord.get(i).equals("O")){
                    out.println("<option value='O' selected>Oui</option>");
                    out.println("<option value='N'>Non</option>");
                } else{
                    out.println("<option value='O'>Oui</option>");
                    out.println("<option value='N' selected>Non</option>");
                }
                
                out.println("</select></td>");
                
                out.println("<td><input type='text' value='"+fmr.list_incorporation.get(i)+"' name='incorporation'></td>");
                out.println("<td><input type='text' value='"+fmr.list_memo.get(i)+"' name='memo'></td>");
                
                out.println("<td><a href=javascript:getHTMLCodeRequest('informations','ModifyFmr','choix_menu=Modify','champ','null')><img src='images/ajouter.gif' width='48' height='48' alt='Enregistrer' border='0' onMouseOver='change_image(1,ajoutergris)' onMouseOut='change_image(1,ajouter)'/></a></td>");
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
        } else if(choix.equals("Modify")){
            
            String codeGare = new String(request.getParameter("codeGare"));
            String numeroFmr = new String(request.getParameter("numeroFmr"));
            String etablissement = new String(request.getParameter("etablissement"));
            String envoi = new String(request.getParameter("envoi"));
            String traitement = new String(request.getParameter("traitement"));
            String accord = new String(request.getParameter("accord"));
            String incorporation = new String(request.getParameter("incorporation"));
            String memo = new String(request.getParameter("memo"));
            
            ArrayList list = new ArrayList();
            
            list.add(codeGare);
            list.add(numeroFmr);
            list.add(etablissement);
            list.add(envoi);
            list.add(traitement);
            list.add(accord);
            list.add(incorporation);
            list.add(memo);
            
            list = TransformString(list);
            
            try {
                
                //Creation de l'objet associé -----------------------------------------------------
                
                FmrDAO fmr = null;
                fmr= creerObjet(request, fmr);
                
                //Actions du controleur ------------------------------------------------------------
                
                fmr.ModFmr(codeFmr, list); // Charge toutes les informations
                
                //Operations effectuées avec succès------------------------------------------------
                
                retourVue(request, response,"/ShowFmr?choix_menu=Null");
                
            } catch(SQLException ex) {
                retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState()+"&retour=ModifyFmr&nomCode=codeFmr&codeRenvoie="+codeFmr+"");
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
