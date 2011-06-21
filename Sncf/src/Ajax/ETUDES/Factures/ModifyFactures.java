package Ajax.ETUDES.Factures;

import Models.TOOLS.Ajaxmodels.DVSG.AjaxModels_Onglets;
import Models.TOOLS.Controleur.MegaControleur;
import Models.ETUDES.Entreprises.EntreprisesDAO;
import Models.ETUDES.Factures.FacturesDAO;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

//@author RABALLAND Romain ------------------------------------------------------------------------------
//@version v3.0 ------------------------------------------------------------------------------------------------------------

public class ModifyFactures extends MegaControleur {
    
    String codeFacture = "";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //Recuperation des données envoyées -----------------------------------------------
        
        String choix = new String(request.getParameter("choix_menu"));
        
        if(choix.equals("null")){
            
            codeFacture = new String(request.getParameter("codeFacture"));
            
            try {
                
                //Creation de l'objet associé -----------------------------------------------------
                
                FacturesDAO facture = null;
                facture= creerObjet(request, facture);
                
                //Definition du type de texte pour l'envoie à la page jsp -----------------------------------------------------
                
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                
                //Affichage des informations ------------------------------------------------------
                
                //On affiche les options menu liées à la base
                AjaxModels_Onglets.getOnglet(request,response,"Modify","Factures");
                
                
                out.println("<div id='corps_table'>");
                
                out.println("<table width=80% >");
                
                out.println("<tr>");
                out.println("<td>OBJET</td>");
                out.println("<td>NOM ENTREPRISE</td>");
                out.println("<td>REFERENCE FACTURE</td>");
                out.println("<td>DATE FACTURE</td>");
                out.println("<td>IMPUTATION</td>");
                out.println("<td>DEBIT</td>");
                out.println("<td>CREDIT</td>");
                out.println("<td>ACTIONS</td>");
                out.println("</tr>");
                
                out.println("<tr>");
                out.println("<td colspan='8'><hr with=100%>");
                
                out.println("</tr>");
                
                out.println("<tr>");
                
                int i = facture.list_CodeFacture.indexOf(codeFacture);
                out.println("<form name='champ'>");
                
                
                
                //-----------------------------------------------------------------------------
                //----------  On enregistre l'association pour ENTREPRISE ------------------------
                //-----------------------------------------------------------------------------
                
                //Creation de l'objet associé -----------------------------------------------------
                
                EntreprisesDAO entreprise = null;
                entreprise = creerObjet(request, entreprise);
                entreprise.Set();
                
                out.println("<td><input type='text' value='"+facture.list_Objet.get(i)+"' name='objet'></td>");
                
                
                
                
                String codeEntreprise = ""+facture.list_CodeEntreprise.get(i)+"";
                
                out.println("<td><select name='Entreprises'>");
                
                out.println("<option value='"+codeEntreprise+"' selected>"+entreprise.GetnomEntreprise(codeEntreprise)+"</option>");
                
                for(int j=0; j<entreprise.list_CodeEntreprise.size(); j++){ //On parcours tt la liste des SECTIONS
                    
                    if(!entreprise.list_CodeEntreprise.get(j).equals(codeEntreprise))
                        out.println("<option value='"+entreprise.list_CodeEntreprise.get(j)+"'>"+entreprise.list_Entreprise.get(j)+"</option>");
                }
                
                out.println("</select></td>");
                
                
                
                
                
                
                
                
                
                out.println("</td>");
                //-----------------------------------------------------------------------------
                //----------   THAT'S ALL FOLKS -----------------------------------------------
                //-----------------------------------------------------------------------------
                
                
                
                
                
                out.println("<td><input type='text' value='"+facture.list_ReferenceFacture.get(i)+"' name='referenceFacture'></td>");
                out.println("<td><input type='text' value='"+facture.list_DateFacture.get(i)+"' name='dateFacture'></td>");
                out.println("<td><input type='text' value='"+facture.list_Imputation.get(i)+"' name='imputation'></td>");
                out.println("<td><input type='text' value='"+facture.list_Debit.get(i)+"' name='debit'></td>");
                out.println("<td><input type='text' value='"+facture.list_Credit.get(i)+"' name='credit'></td>");
                
                out.println("<td><a href=javascript:getHTMLCodeRequest('informations','ModifyFactures','choix_menu=Modify','champ','null')><img src='images/ajouter.gif' width='48' height='48' alt='Enregistrer' border='0' onMouseOver='change_image(1,ajoutergris)' onMouseOut='change_image(1,ajouter)'/></a></td>");
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
            
            String codeEntreprise = request.getParameter("codeEntreprise");
            String objet = request.getParameter("objet");
            String referenceFacture = request.getParameter("referenceFacture");
            String dateFacture = request.getParameter("dateFacture");
            String imputation = request.getParameter("imputation");
            String debit = request.getParameter("debit");
            String credit = request.getParameter("credit");
            
            ArrayList list = new ArrayList();
            
            list.add(codeEntreprise);
            list.add(objet);
            list.add(referenceFacture);
            list.add(dateFacture);
            list.add(imputation);
            list.add(debit);
            list.add(credit);
            
            list = TransformString(list);
            
            try {
                
                //Creation de l'objet associé -----------------------------------------------------
                
                FacturesDAO facture = null;
                facture = creerObjet(request, facture);
                
                //Actions du controleur ------------------------------------------------------------
                
                facture.Mod(codeFacture, list); // Charge toutes les informations
                
                //Operations effectuées avec succès------------------------------------------------
                
                retourVue(request, response,"/ShowFactures?choix_menu=Null");
                
            } catch(SQLException ex) {
                retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState()+"&retour=ModifyFactures&nomCode=codeFacture&codeRenvoie="+codeFacture+"");
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
