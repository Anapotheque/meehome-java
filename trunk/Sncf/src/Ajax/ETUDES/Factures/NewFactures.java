package Ajax.ETUDES.Factures;

import Models.TOOLS.Ajaxmodels.DVSG.AjaxModels_Onglets;
import Models.TOOLS.Ajaxmodels.OBJETS.SelectDAO;
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

public class NewFactures extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //Recuperation des données envoyées -----------------------------------------------
        
        String choix = new String(request.getParameter("choix_menu"));
        
        // On affiche la page si le choix est egale à 'Null'--------------------------------------
        
        if(choix.equals("Null")){
            
            try {
                
                //Creation de l'objet associé -----------------------------------------------------
                
                FacturesDAO facture = null;
                facture = creerObjet(request, facture);
                
                //Definition du type de texte pour l'envoie à la page jsp -----------------------------------------------------
                
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                
                //Affichage des informations ------------------------------------------------------
                
                //On affiche les options menu liées à la base
                
                AjaxModels_Onglets.getOnglet(request,response,"Creation","Factures");
                
                
                out.println("<table width=100% >");
                
                out.println("<tr>");
                out.println("<td>NOM ENTREPRISE</td>");
                out.println("<td>OBJET</td>");
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
                
                out.println("<form name='champ'>");
                
                //-----------------------------------------------------------------------------
                //----------  On enregistre l'association pour ENTREPRISE ------------------------
                //-----------------------------------------------------------------------------
                out.println("<td>");
                
                //Creation de l'objet associé -----------------------------------------------------
                
                EntreprisesDAO entreprise = null;
                entreprise = creerObjet(request, entreprise);
                entreprise.Set();
                
                SelectDAO.AddSelect(out,"Entreprises",entreprise.list_CodeEntreprise,entreprise.list_Entreprise,1);
                
//                out.println("<select name='Entreprises' size=1>");
//                
//                for(int i=0; i<entreprise.list_CodeEntreprise.size(); i++)
//                    out.println("<option value='"+entreprise.list_CodeEntreprise.get(i)+"'>"+entreprise.list_Entreprise.get(i)+"</option>");
//                out.println("</select>");
                
                out.println("</td>");
                //-----------------------------------------------------------------------------
                //----------   THAT'S ALL FOLKS -----------------------------------------------
                //-----------------------------------------------------------------------------
                out.println("<td><input type='text' value='null' name='objet'></td>");
                out.println("<td><input type='text' value='null' name='referenceFacture'></td>");
                out.println("<td><input type='text' value='JJ/MM/AAAA' name='dateFacture'></td>");
                out.println("<td><input type='text' value='null' name='imputation'></td>");
                out.println("<td><input type='text' value='000,00 E' name='debit'></td>");
                out.println("<td><input type='text' value='000,00 E' name='credit'></td>");
                
                out.println("<td><a href=javascript:getHTMLCodeRequest('informations','NewFactures','choix_menu=New','champ')><img src='images/ajouter.gif' width='48' height='48' alt='Enregistrer' border='0' onMouseOver='change_image(1,ajoutergris)' onMouseOut='change_image(1,ajouter)'/></a></td>");
                out.println("</tr>");
                out.println("</form>");
                
                out.println("</table>");
                
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
                
                facture.New(list); // Charge toutes les informations
                
                //Operations effectuées avec succès------------------------------------------------
                
                retourVue(request, response,"/ShowFactures?choix_menu=Null");
                
            } catch(SQLException ex) {
                retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState()+"&retour=NewFactures");
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
