package Ajax.ETUDES.Rch;

import Models.TOOLS.Ajaxmodels.DVSG.AjaxModels_Onglets;
import Models.TOOLS.Controleur.MegaControleur;
import Models.ETUDES.Agents.AgentsDAO;
import Models.ETUDES.Entreprises.EntreprisesDAO;
import Models.ETUDES.Etudes.EtudesDAO;
import Models.ETUDES.Rch.RchDAO;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class ModifyRch extends MegaControleur {
    
    String codeRch = "";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        
        //Recuperation des données envoyées -----------------------------------------------
        
        String choix = new String(request.getParameter("choix_menu"));
        
        if(choix.equals("null")){
            
            codeRch= new String(request.getParameter("codeRch"));
            
            try {
                
                //Creation de l'objet associé -----------------------------------------------------
                
                RchDAO rch = null;
                rch= creerObjet(request, rch);
                
                EtudesDAO etude = null;
                etude = creerObjet(request, etude);
                
                AgentsDAO agent = null;
                agent = creerObjet(request, agent);
                
                EntreprisesDAO entreprise = null;
                entreprise = creerObjet(request, entreprise);
                
                //Definition du type de texte pour l'envoie à la page jsp -----------------------------------------------------
                
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                
                //Affichage des informations ------------------------------------------------------
                
                //On affiche les options menu liées à la base
                AjaxModels_Onglets.getOnglet(request,response,"Modify","Rch");
                

                
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
                
                
                
                int i = rch.list_CodeRch.indexOf(codeRch);
                
                out.println("<form name='champ'>");
                
                out.println("<td>"+rch.list_CodeRch.get(i)+"</td>");
                
                
                String codeEtude = ""+rch.list_CodeEtude.get(i)+"";
                out.println("<td><select name='indices'>");
                out.println("<option value='"+codeEtude+"' selected>"+etude.GetindiceEtudeSolo(codeEtude)+"</option>");
                for(int j=0; j< etude.list_codeEtude.size(); j++){
                    if(!etude.list_codeEtude.get(j).equals(codeEtude))
                        out.println("<option value='"+etude.list_codeEtude.get(j)+"'>"+etude.list_indice.get(j)+"</option>");
                }
                out.println("</select></td>");
                
                
                String codeAgent = ""+rch.list_CodeAgent.get(i)+"";
                out.println("<td><select name='agents'>");
                out.println("<option value='"+codeAgent+"' selected>"+agent.GetnomAgent(codeAgent)+"</option>");
                for(int j=0; j< agent.list_CodeAgent.size(); j++){
                    if(!agent.list_CodeAgent.get(j).equals(codeAgent))
                        out.println("<option value='"+agent.list_CodeAgent.get(j)+"'>"+agent.list_Nom.get(j)+"</option>");
                }
                out.println("</select></td>");
                
                
                
                
                String codeEntreprise = ""+rch.list_CodeEntreprise.get(i)+"";
                out.println("<td><select name='entreprises'>");
                out.println("<option value='"+codeEntreprise+"' selected>"+entreprise.GetnomEntreprise(codeEntreprise)+"</option>");
                for(int j=0; j< entreprise.list_CodeEntreprise.size(); j++){
                    if(!entreprise.list_CodeEntreprise.get(j).equals(codeAgent))
                        out.println("<option value='"+entreprise.list_CodeEntreprise.get(j)+"'>"+entreprise.list_Entreprise.get(j)+"</option>");
                }
                out.println("</select></td>");
                
                out.println("<td><input type='text' value='"+rch.list_Emission.get(i)+"' name='emission'></td>");
                out.println("<td><input type='text' value='"+rch.list_Reception.get(i)+"' name='reception'></td>");
                
                out.println("<td><input type='text' value='"+rch.list_RemisAgent.get(i)+"' name='remisAgent'></td>");
                out.println("<td><input type='text' value='"+rch.list_Expedition.get(i)+"' name='expedition'></td>");
                out.println("<td><input type='text' value='"+rch.list_Archive.get(i)+"' name='archive'></td>");
                out.println("<td><input type='text' value='"+rch.list_Observations.get(i)+"' name='observations'></td>");
                out.println("<td><a href=javascript:getHTMLCodeRequest('informations','ModifyRch','choix_menu=Modify','champ','null')><img src='images/ajouter.gif' width='48' height='48' alt='Enregistrer' border='0' onMouseOver='change_image(1,ajoutergris)' onMouseOut='change_image(1,ajouter)'/></a></td>");
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
        } else if(choix.equals("Modify")){
            
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
                
                rch.Mod(codeRch, list); // Charge toutes les informations
                
                //Operations effectuées avec succès------------------------------------------------
                
                retourVue(request, response,"/ShowRch?choix_menu=Null");
                
            } catch(SQLException ex) {
                retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState()+"&retour=ModifyRch&nomCode=codeRch&codeRenvoie="+codeRch+"");
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
