package Ajax.ETUDES.Etudes;

import Models.TOOLS.Ajaxmodels.DVSG.AjaxModels_Onglets;
import Models.TOOLS.Ajaxmodels.OBJETS.SelectDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.InputDAO;
import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Ajaxmodels.OBJETS.TextAreaDAO;
import Models.TOOLS.Controleur.MegaControleur;
import Models.ETUDES.Affaires.AffairesDAO;
import Models.ETUDES.Agents.AgentsDAO;
import Models.ETUDES.Entreprises.EntreprisesDAO;
import Models.ETUDES.Etudes.EtudesDAO;
import Models.ETUDES.Etudes.Link.LinkDAOEtudes_Affaires;
import Models.ETUDES.Etudes.Link.LinkDAOEtudes_Agents;
import Models.ETUDES.Gares.GaresDAO;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class NewEtudes extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        //GET DATA
        String choix = new String(request.getParameter("choix_menu"));
        
        if(choix.equals("Null")){
            
            try {
                
                //AFFICHAGE DU MENU DE NAVIGATION
                AjaxModels_Onglets.getOnglet(request,response,"Creation","Etudes");
                
                out.println("<table width=100% >");
                out.println("<form name=champ>");
                
                out.println("<th>AFFAIRES</th>");
                out.println("<th>AGENTS</th>");
                out.println("<th>ENTREPRISE</th>");
                
                out.println("<tr>");
                out.println("<td colspan='3'><hr with=100% size='1'>");
                out.println("</tr>");
                
                out.println("<tr>");
                //SELECTION POUR AFFAIRES
                out.println("<td>");
                
                AffairesDAO affaire = null;
                affaire= creerObjet(request, affaire);
                affaire.Set();
                
                SelectDAO.AddSelect(out,"Affaires",affaire.list_CodeAffaire,affaire.list_Nom,true,5);
                
                out.println("</td>");
                //FIN SELECTION POUR AFFAIRES
                
                
                //SELECTION POUR AGENTS
                out.println("<td>");
                
                AgentsDAO agent = null;
                agent = creerObjet(request, agent);
                agent.Set();
                
                SelectDAO.AddSelect(out,"Agents",agent.list_CodeAgent,agent.list_Nom,true,5);
                
                out.println("</td>");
                //FIN SELECTION POUR AGENTS
                
                //SELECTION POUR ENTREPRISES
                out.println("<td>");
                
                EntreprisesDAO entreprise = null;
                entreprise = creerObjet(request, entreprise);
                entreprise.Set();
                
                SelectDAO.AddSelect(out,"Entreprises",entreprise.list_CodeEntreprise,entreprise.list_Entreprise,1);
                
                out.println("</td>");
                //FIN SELECTION POUR ENTREPRISES
                out.println("</tr>");
                
                
                
                
                
                
                
                
                out.println("<th>GARES</th>");
                out.println("<th>INDICE</th>");
                
                out.println("<tr>");
                out.println("<td colspan='2'><hr with=100% size='1'>");
                out.println("</tr>");
                
                
                
                
                out.println("<tr>");
                
                //SELECTION POUR GARES
                out.println("<td>");
                
                GaresDAO gare = null;
                gare = creerObjet(request, gare);
                gare.SetGareSE();
                
                SelectDAO.AddSelect(out,"Gares",gare.list_CodeGare,gare.list_Gare,1,"onChange=javascript:getHTMLCodeRequest('indice','NewEtudes','choix_menu=null','champ',-1)");
                
                out.println("</td>");
                //FIN SELECTION POUR GARES
                
                out.println("<td>");
                out.println("<div id='indice'>");
                InputDAO.AddInput(out,"text","indice","145px","choisissez une gare");
                out.println("</div>");
                out.println("</td>");
                
                out.println("</tr>");
                
                
                
                out.println("</tr>");
                
                out.println("<tr>");
                out.println("<td>ACTIONS</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>");
                out.println("<div id='bouton1'>");
                out.println("<ul>");
                out.println("<li><a href=javascript:getHTMLCodeRequest('informations','NewEtudes','choix_menu=New','champ')>ENREGISTRER</a></li><br>");
                out.println("</ul></div>");
                out.println("</td>");
                
                out.println("</tr>");
                out.println("</form>");
                
                out.println("</table>");
                
                
                out.println("</div>");
                
                out.close();
                
            } catch(SQLException ex) {
                System.out.println("\n\nDEBUT ERREUR<==========================================\n");
                ex.printStackTrace();
                System.out.println("\nFIN ERREUR<==============================================\n\n");
                
            }
        }
        
        else if(!choix.equals("New") && !choix.equals("null")){
            try {
                EtudesDAO etude = null;
                etude = creerObjet(request, etude);
                
                etude.GetindiceEtude(""+choix+"");
                InputDAO.AddInput(out,"text","indice","145px");
                
                if(etude.indice.size()!=0){
                    TOOLS_HTML.getMessage(out,"h6","LES INDICES : ");
                    for(int i=0; i<etude.indice.size(); i++)
                        TOOLS_HTML.getMessage(out,"h6",""+etude.indice.get(i)+"");
                } else
                    out.println("<br>AUCUN INDICE");
                
                out.println("</select>");
            }   catch(SQLException ex) {
                retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState());
                System.out.println("\n\nDEBUT ERREUR<==========================================\n");
                ex.printStackTrace();
                System.out.println("\nFIN ERREUR<==============================================\n\n");
            }
        }
        
        // On enregistre le resultat si le choix vaut 'New'--------------------------------------
        
        else if(choix.equals("New")){
            
            String indice = new String(request.getParameter("indice"));
            String codeGare = new String(request.getParameter("codeGare"));
            if(codeGare.equals("null"))
                codeGare = "-1";
            
            String codeEntreprise = new String(request.getParameter("codeEntreprise"));
            
            int NombreSelectAffaires = Integer.parseInt(request.getParameter("NombreSelectAffaires"));
            int NombreSelectAgents = Integer.parseInt(request.getParameter("NombreSelectAgents"));
            
            ArrayList list = new ArrayList();
            
            list.add(codeGare);
            list.add(codeEntreprise);
            list.add(indice);
            
            
            list = TransformString(list);
            
            //Gestion de la liaison vers les tables infini infini
            ArrayList listcodeAffaires = new ArrayList();
            ArrayList listcodeAgents = new ArrayList();
            
            String detail = "";
            AffairesDAO affaire = null;
            affaire= creerObjet(request, affaire);
            
            // On recupere chaque elements selectionnés
            for(int i=1; i<=NombreSelectAffaires; i++){
                String codeAffaire = new String(request.getParameter("codeAffaire"+i+""));
                listcodeAffaires.add(codeAffaire);
                
                //On recupere le nom de l'affaire
                try {
                    detail = detail + affaire.GetnomAffaire(codeAffaire);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                detail = detail + "\n";
                
            }
            
            list.add(detail);
            
            for(int i=1; i<=NombreSelectAgents; i++){
                String codeAgent = new String(request.getParameter("codeAgent"+i+""));
                listcodeAgents.add(codeAgent);
            }
            
            
            try {
                
                //Creation de l'objet associé -----------------------------------------------------
                
                EtudesDAO etude = null;
                etude = creerObjet(request, etude);
                
                LinkDAOEtudes_Agents linkE_Ag = null;
                linkE_Ag = creerObjet(request, linkE_Ag);
                
                LinkDAOEtudes_Affaires linkE_A = null;
                linkE_A = creerObjet(request, linkE_A);
                
                etude.New(list); // Charge toutes les informations
                
                String codeEtude = etude.GetLastCodeEtude();
                
                //On ajoute dans la table de liaison des deux table si et seulement si on a selectionné une section de gare
                if(!listcodeAffaires.get(0).equals("null")){
                    
                    //On ajoute dans la table interposé les element qui vont bien
                    
                    for(int i=0;i<=NombreSelectAffaires-1;i++)
                        linkE_A.NewLink(""+codeEtude+"", ""+listcodeAffaires.get(i)+"");
                }
                
                //On ajoute dans la table de liaison des deux table si et seulement si on a selectionné une section de gare
                if(!listcodeAgents.get(0).equals("null")){
                    
                    //On ajoute dans la table interposé les element qui vont bien
                    
                    for(int i=0;i<=NombreSelectAgents-1;i++)
                        linkE_Ag.NewLink(""+codeEtude+"", ""+listcodeAgents.get(i)+"");
                }
                
                //Operations effectuées avec succès------------------------------------------------
                
                retourVue(request, response,"/ShowEtudes?choix_menu=Null");
                
            }
            
            catch(SQLException ex) {
                retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState()+"&retour=NewEtudes");
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
