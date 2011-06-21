package Ajax.ETUDES.Rch;

import Models.TOOLS.Ajaxmodels.DVSG.AjaxModels_Onglets;
import Models.TOOLS.Controleur.MegaControleur;
import Models.ETUDES.Agents.AgentsDAO;
import Models.ETUDES.Entreprises.EntreprisesDAO;
import Models.ETUDES.Etudes.EtudesDAO;
import Models.ETUDES.Gares.GaresDAO;
import Models.ETUDES.Rch.RchDAO;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

//@author RABALLAND Romain ------------------------------------------------------------------------------
//@version v3.0 ------------------------------------------------------------------------------------------------------------

public class NewRch extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //Recuperation des données envoyées -----------------------------------------------
        
        String choix = new String(request.getParameter("choix_menu"));
        
        // On affiche la page si le choix est egale à 'Null'--------------------------------------
        
        if(choix.equals("Null")){
            
            try {
                
                RchDAO rch = null;
                rch = creerObjet(request, rch);
                
                EtudesDAO etude = null;
                etude = creerObjet(request, etude);
                etude.Set();
                
                GaresDAO gare = null;
                gare = creerObjet(request, gare);
                gare.Set();
                
                //Definition du type de texte pour l'envoie à la page jsp -----------------------------------------------------
                
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                
                //Affichage des informations ------------------------------------------------------
                
                //On affiche les options menu liées à la base
                
                AjaxModels_Onglets.getOnglet(request,response,"Creation","Rch");
                
                out.println("<div id='corps_table'>");
                
                out.println("<table width=80% >");
                
                out.println("<tr>");
                out.println("<td>NUMERO RCH</td>");
                out.println("<td>ETUDE</td>");
                out.println("<td>AGENT</td>");
                out.println("<td>ENTREPRISE</td>");
                out.println("<td>EMISSION</td>");
                out.println("<td>RECEPTION</td>");
                out.println("<td>REMIS AGENT</td>");
                out.println("<td>ENVOI CONFORME</td>");
                out.println("<td>ARCHIVE</td>");
                out.println("<td>OBSERVATIONS</td>");
                out.println("<td>ACTION</td>");
                out.println("</tr>");
                
                out.println("<tr>");
                out.println("<td colspan='11'><hr with=100%>");
                
                out.println("</tr>");
                
                out.println("<tr>");
                
                out.println("<form name='champ'>");
                
                int code = rch.GetLastCodeRch();
                
                out.println("<td><input type='text' value='"+code+"' name='codeRch'></td>");
                
                //-----------------------------------------------------------------------------
                //----------  On enregistre l'association pour ETUDES ------------------------
                //-----------------------------------------------------------------------------
                out.println("<td>");
                
                //Creation de l'objet associé -----------------------------------------------------
                
                
                
                
                out.println("<select name='Etudes' size=1>");
                for(int i=0; i<etude.list_codeEtude.size(); i++){
                    out.println("<option value='"+etude.list_codeEtude.get(i)+"'>"+etude.list_indice.get(i)+" -- "+gare.GetnomGare(""+etude.list_codeGare.get(i)+"")+"</option>");
                }
                out.println("</select>");
                out.println("</td>");
                //-----------------------------------------------------------------------------
                //----------   THAT'S ALL FOLKS -----------------------------------------------
                //-----------------------------------------------------------------------------
                
                //-----------------------------------------------------------------------------
                //----------  On enregistre l'association pour AGENT ------------------------
                //-----------------------------------------------------------------------------
                out.println("<td>");
                
                //Creation de l'objet associé -----------------------------------------------------
                
                AgentsDAO agent = null;
                agent = creerObjet(request, agent);
                
                
                out.println("<select name='Agents' size=1>");
                for(int i=0; i<agent.list_CodeAgent.size(); i++)
                    out.println("<option value='"+agent.list_CodeAgent.get(i)+"'>"+agent.list_Nom.get(i)+"</option>");
                
                out.println("</select>");
                out.println("</td>");
                //-----------------------------------------------------------------------------
                //----------   THAT'S ALL FOLKS -----------------------------------------------
                //-----------------------------------------------------------------------------
                
                //-----------------------------------------------------------------------------
                //----------  On enregistre l'association pour EENTREPRISE ------------------------
                //-----------------------------------------------------------------------------
                out.println("<td>");
                
                //Creation de l'objet associé -----------------------------------------------------
                
                EntreprisesDAO entreprise = null;
                entreprise = creerObjet(request, entreprise);
                
                
                out.println("<select name='Entreprises' size=1>");
                for(int i=0; i<entreprise.list_CodeEntreprise.size(); i++)
                    out.println("<option value='"+entreprise.list_CodeEntreprise.get(i)+"'>"+entreprise.list_Entreprise.get(i)+"</option>");
                
                out.println("</select>");
                out.println("</td>");
                //-----------------------------------------------------------------------------
                //----------   THAT'S ALL FOLKS -----------------------------------------------
                //-----------------------------------------------------------------------------
                
                
                out.println("<td><input type='text' value='JJ/MM/AAAA' name='emission'></td>");
                out.println("<td><input type='text' value='JJ/MM/AAAA' name='reception'></td>");
                
                out.println("<td><input type='text' value='JJ/MM/AAAA' name='remisAgent'></td>");
                out.println("<td><input type='text' value='JJ/MM/AAAA' name='expedition'></td>");
                out.println("<td><input type='text' value='null' name='observations'></td>");
                out.println("<td><input type='text' value='null' name='archive' size='4' maxlength='4'></td>");
                
                out.println("<td><a href=javascript:getHTMLCodeRequest('informations','NewRch','choix_menu=New','champ')><img src='images/ajouter.gif' width='48' height='48' alt='Enregistrer' border='0' onMouseOver='change_image(1,ajoutergris)' onMouseOut='change_image(1,ajouter)'/></a></td>");
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
            
            String codeRch = new String(request.getParameter("codeRch"));
            String codeEtude = new String(request.getParameter("codeEtude"));
            String codeAgent = new String(request.getParameter("codeAgent"));
            String codeEntreprise = new String(request.getParameter("codeEntreprise"));
            String emission = new String(request.getParameter("emission"));
            String reception = new String(request.getParameter("reception"));
            String observations = new String(request.getParameter("observations"));
            String remisAgent = new String(request.getParameter("remisAgent"));
            String expedition = new String(request.getParameter("expedition"));
            String archive = new String(request.getParameter("archive"));
            
            ArrayList list = new ArrayList();
            
            list.add(codeRch);
            list.add(codeEtude);
            list.add(codeAgent);
            list.add(codeEntreprise);
            list.add(emission);
            list.add(reception);
            list.add(observations);
            list.add(remisAgent);
            list.add(expedition);
            list.add(archive);
            
            list = TransformString(list);
            
            try {
                
                //Creation de l'objet associé -----------------------------------------------------
                
                RchDAO rch = null;
                rch = creerObjet(request, rch);
                
                //Actions du controleur ------------------------------------------------------------
                
                rch.New(list); // Charge toutes les informations
                
                //Operations effectuées avec succès------------------------------------------------
                
                retourVue(request, response,"/ShowRch?choix_menu=Null");
                
            }
            
            catch(SQLException ex) {
                retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState()+"&retour=NewRch");
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
