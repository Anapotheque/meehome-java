package Ajax.ETUDES.Etudes;

import Models.TOOLS.Ajaxmodels.DVSG.AjaxModels_Onglets;
import Models.TOOLS.Ajaxmodels.OBJETS.SelectDAO;
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

public class ModifyEtudes extends MegaControleur {
    
    String codeEtude = "";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        
        //Recuperation des données envoyées -----------------------------------------------
        
        String choix = new String(request.getParameter("choix_menu"));
        
        if(choix.equals("null")){
            
            codeEtude= new String(request.getParameter("codeEtude"));
            
            try {
                
                //Creation de l'objet associé -----------------------------------------------------
                
                EtudesDAO etude = null;
                etude = creerObjet(request, etude);
                
                
                AffairesDAO affaire = null;
                affaire = creerObjet(request, affaire);
                affaire.Set();
                
                AgentsDAO agent = null;
                agent = creerObjet(request, agent);
                agent.Set();
                
                GaresDAO gare = null;
                gare = creerObjet(request, gare);
                gare.Set();
                
                EntreprisesDAO entreprise = null;
                entreprise = creerObjet(request, entreprise);
                entreprise.Set();
                
                LinkDAOEtudes_Affaires linkE_A = null;
                linkE_A = creerObjet(request, linkE_A);
                linkE_A.SetLink(codeEtude,null);
                
                LinkDAOEtudes_Agents linkE_Ag = null;
                linkE_Ag = creerObjet(request, linkE_Ag);
                linkE_Ag.SetLink(codeEtude,null);
                
                //Definition du type de texte pour l'envoie à la page jsp -----------------------------------------------------
                
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                
                //Affichage des informations ------------------------------------------------------
                
                //On affiche les options menu liées à la base
                AjaxModels_Onglets.getOnglet(request,response,"Modify","Etudes");
                
                out.println("<table width=100% >");
                
                out.println("<th>INDICE</th>");
                out.println("<th>DETAIL</th>");
                out.println("<th>MISE EN SERVICE</th>");
                out.println("<th>GARE</th>");
                out.println("<th>ENTREPRISE</th>");
                
                out.println("<tr>");
                out.println("<td colspan='9'><hr with=100% size='1'></td>");
                out.println("</tr>");
                
                out.println("<form name='champ'>");
                
                out.println("<tr>");
                
                int i = etude.list_codeEtude.indexOf(codeEtude);
                
                out.println("<td><input type='text' value='"+etude.list_indice.get(i)+"' name='indice'></td>");
                TOOLS_HTML.OpenTdTable(out);
                TextAreaDAO.TextArea(out,"detail",4,4,"400px",""+etude.list_detail.get(i));
                TOOLS_HTML.CloseTdTable(out);
                out.println("<td><input type='text' value='"+etude.list_miseEnService.get(i)+"' name='miseEnService'></td>");
                
                out.println("<td>");
                SelectDAO.GetSelect(out,"Gares",""+etude.list_codeGare.get(i),gare.GetnomGare(""+etude.list_codeGare.get(i)),gare.list_CodeGare,gare.list_Gare,1);
                out.println("</td>");
                
                
                String codeEntreprise = ""+etude.list_codeEntreprise.get(i)+"";
                out.println("<td>");
                SelectDAO.GetSelect(out,"Entreprises",""+etude.list_codeEntreprise.get(i),entreprise.GetnomEntreprise(""+etude.list_codeEntreprise .get(i)),entreprise.list_CodeEntreprise,entreprise.list_Entreprise,1);
                out.println("</td>");
                
                
                
                out.println("</tr>");
                
                
                out.println("<th>ENVOI TX</th>");
                out.println("<th>SESCO TX</th>");
                out.println("<th>PROJET</th>");
                out.println("<th>ETUDE TRAVAUX</th>");
                out.println("<th>CONFORME</th>");
                
                out.println("<tr>");
                
                out.println("<td><input type='text' value='"+etude.list_envoiTx.get(i)+"' name='envoiTx'></td>");
                out.println("<td><input type='text' value='"+etude.list_sescoTx.get(i)+"' name='sescoTx' size='2' maxlength='2'></td>");
                out.println("<td><input type='text' value='"+etude.list_Projet.get(i)+"' name='projet'></td>");
                out.println("<td><input type='text' value='"+etude.list_EtudeTravaux.get(i)+"' name='etudeTravaux'></td>");
                out.println("<td><input type='text' value='"+etude.list_Conforme.get(i)+"' name='conforme'></td>");
                
                out.println("</tr>");
                
                out.println("</table>");
                
                out.println("<br>");
                out.println("<h4>QUALITE</h4>");
                
                
                
                
                
                
                out.println("<table width=100% >");
                
                out.println("<th>DIFFICULTE</th>");
                out.println("<th>POIDS KG</th>");
                out.println("<th>CONTRAIRES</th>");
                out.println("<th>AUTRES</th>");
                out.println("<th>RELATIONS</th>");
                out.println("<th>MATERIELS</th>");
                out.println("<th>DELAIS</th>");
                out.println("<th>REPORTS</th>");
                out.println("<th>METRE</th>");
                out.println("<th>ACTION</th>");
                
                
                out.println("<tr>");
                out.println("<td colspan='10'><hr with=100% size='1'></td>");
                out.println("</tr>");
                
                out.println("<tr>");
                
                out.println("<td>");
                ArrayList listNumDiff = null;
                ArrayList listDiff = null;
                listNumDiff = new ArrayList();
                listDiff = new ArrayList();
                
                listNumDiff.add("0");
                listNumDiff.add("1");
                listNumDiff.add("2");
                listNumDiff.add("3");
                
                listDiff.add("undefined");
                listDiff.add("facile");
                listDiff.add("moyen");
                listDiff.add("difficile");
                
                String Numdifficulte = ""+etude.list_Difficulte.get(i);
                String difficulte = "";
                
                if(Numdifficulte.equals("1"))
                    difficulte = "facile";
                if(Numdifficulte.equals("2"))
                    difficulte = "moyen";
                if(Numdifficulte.equals("3"))
                    difficulte = "difficile";
                
                SelectDAO.GetSelect(out,"difficulte",Numdifficulte,difficulte,listNumDiff,listDiff,1);
                
                out.println("</td>");
                
                out.println("<td><input type='text' value='"+etude.list_PoidsKg.get(i)+"' name='poidsKg'></td>");
                out.println("<td><input type='text' value='"+etude.list_Contraires.get(i)+"' name='contraires'></td>");
                out.println("<td><input type='text' value='"+etude.list_Autres.get(i)+"' name='autres'></td>");
                
                if(etude.list_Relations.get(i).equals("1"))
                    out.println("<td><input type='checkbox' value='1' name='relations' checked></td>");
                else out.println("<td><input type='checkbox' value='0' name='relations'></td>");
                
                if(etude.list_Materiel.get(i).equals("1"))
                    out.println("<td><input type='checkbox' value='1' name='materiel' checked></td>");
                else out.println("<td><input type='checkbox' value='0' name='materiel'></td>");
                
                if(etude.list_Delais.get(i).equals("1"))
                    out.println("<td><input type='checkbox' value='1' name='delais' checked></td>");
                else out.println("<td><input type='checkbox' value='0' name='delais'></td>");
                
                if(etude.list_Reports.get(i).equals("1"))
                    out.println("<td><input type='checkbox' value='1' name='reports' checked></td>");
                else out.println("<td><input type='checkbox' value='0' name='reports'></td>");
                
                if(etude.list_Metre.get(i).equals("1"))
                    out.println("<td><input type='checkbox' value='1' name='metre' checked></td>");
                else out.println("<td><input type='checkbox' value='0' name='metre'></td>");
                
                //-----------------------------------------------------------------------------------
                
                out.println("<td><a href=javascript:getHTMLCodeRequest('informations','ModifyEtudes','choix_menu=Modify','champ','null')><img src='images/ajouter.gif' width='48' height='48' alt='Enregistrer' border='0' onMouseOver='change_image(1,ajoutergris)' onMouseOut='change_image(1,ajouter)'/></a></td>");
                out.println("</tr>");
                out.println("</table>");
                out.println("<br>");
                
                
                
                //-----------------------------------------------------------------------------------
                //AFFAIRES------------------------------------------------------------------------------
                //-----------------------------------------------------------------------------------
                
                out.println("<table width=100% >");
                
                out.println("<tr>");
                out.println("<td>TOUTES LES AFFAIRES</td>");
                out.println("<td>ACTIONS</td>");
                out.println("<td>AFFAIRES LIEES</td>");
                out.println("</tr>");
                
                out.println("<tr>");
                out.println("<td colspan='3'><hr with=100% size='1'>");
                out.println("</tr>");
                
                out.println("<tr>");
                
                //--------ON AFFICHE LA PREMIERE FENETRE CONTENANT TOUS LES ELEMENTS DE LA LISTE QUE L'ON PEUT LIER-------------
                
                out.println("<td><select name='Affaire1' size=5>");
                
                for(int j=0; j<affaire.list_CodeAffaire.size(); j++){ //On parcours tt la liste des SECTIONS
                    out.println("<option value='"+affaire.list_CodeAffaire.get(j)+"'>"+affaire.list_Nom.get(j)+"</option>");
                    
                }
                out.println("</select></td>");
                
                //------BOUTTONS DE CHOIX----------------------------------------------------------------------
                
                out.println("<td><div id='bouton1'>");
                out.println("<ul><li>");
                out.println("<a href=javascript:getHTMLCodeRequest('informations','ModifyEtudes','choix_menu=NewLinkAffaires','champ','Affaire1')>AJOUTER</a>");
                out.println("</li></ul></div>");
                
                out.println("<div id='bouton2'>");
                out.println("<ul><li>");
                out.println("<a href=javascript:getHTMLCodeRequest('informations','ModifyEtudes','choix_menu=DeleteLinkAffaires','champ','Affaire2')>RETIRER</a>");
                out.println("</li></ul></div>");
                out.println("</td>");
                
                //------ON AFFICHE LES ELEMENTS DEJA LIES A LA TABLE------------------------------------------
                
                out.println("<td><select name='Affaire2' size=5 >");
                
                
                
                for(int j=0; j<linkE_A.list_codeAffaires.size(); j++){ //On parcours tt la liste des SECTIONS
                    linkE_A.GetnomAffaire(""+linkE_A.list_codeAffaires.get(j)+"");
                    out.println("<option value='"+linkE_A.list_codeAffaires.get(j)+"'>"+linkE_A.nomAffaire+"</option>");
                    
                }
                out.println("</select></td>");
                
                //-----------------------------------------------------------------------------------
                
                
                out.println("</tr>");
                out.println("</table>");
                
                out.println("<br>");
                //-----------------------------------------------------------------------------------
                //--------------------------- FIN GESTION AFFAIRES ------------------------------------
                //-----------------------------------------------------------------------------------
                
                
                //-----------------------------------------------------------------------------------
                //AGENTS------------------------------------------------------------------------------
                //-----------------------------------------------------------------------------------
                
                out.println("<table width=100% >");
                
                out.println("<tr>");
                out.println("<td>TOUTES LES AGENTS</td>");
                out.println("<td>ACTIONS</td>");
                out.println("<td>AGENTS LIES</td>");
                out.println("</tr>");
                
                out.println("<tr>");
                out.println("<td colspan='3'><hr with=100% size='1'>");
                out.println("</tr>");
                
                out.println("<tr>");
                
                //--------ON AFFICHE LA PREMIERE FENETRE CONTENANT TOUS LES ELEMENTS DE LA LISTE QUE L'ON PEUT LIER-------------
                
                out.println("<td><select name='Agent1' size=5>");
                
                for(int j=0; j<agent.list_CodeAgent.size(); j++){ //On parcours tt la liste des SECTIONS
                    out.println("<option value='"+agent.list_CodeAgent.get(j)+"'>"+agent.list_Nom.get(j)+" "+agent.list_Prenom.get(j)+"</option>");
                    
                }
                out.println("</select></td>");
                
                //------BOUTTONS DE CHOIX----------------------------------------------------------------------
                
                out.println("<td><div id='bouton1'>");
                out.println("<ul><li>");
                out.println("<a href=javascript:getHTMLCodeRequest('informations','ModifyEtudes','choix_menu=NewLinkAgents','champ','Agent1')>AJOUTER</a>");
                out.println("</li></ul></div>");
                
                out.println("<div id='bouton2'>");
                out.println("<ul><li>");
                out.println("<a href=javascript:getHTMLCodeRequest('informations','ModifyEtudes','choix_menu=DeleteLinkAgents','champ','Agent2')>RETIRER</a>");
                out.println("</li></ul></div>");
                out.println("</td>");
                
                //------ON AFFICHE LES ELEMENTS DEJA LIES A LA TABLE------------------------------------------
                
                out.println("<td><select name='Agent2' size=5 >");
                
                
                
                for(int j=0; j<linkE_Ag.list_codeAgents.size(); j++){ //On parcours tt la liste des SECTIONS
                    linkE_Ag.GetnomAgent(""+linkE_Ag.list_codeAgents.get(j)+"");
                    out.println("<option value='"+linkE_Ag.list_codeAgents.get(j)+"'>"+linkE_Ag.nomAgent+" "+linkE_Ag.prenomAgent+"</option>");
                    
                }
                out.println("</select></td>");
                
                //-----------------------------------------------------------------------------------
                
                
                out.println("</tr>");
                out.println("</table>");
                
                //-----------------------------------------------------------------------------------
                //--------------------------- FIN GESTION AGENTS ------------------------------------
                //-----------------------------------------------------------------------------------
                
                out.println("<h3>ATTENTION!<br>Toutes modifications concernant les differents liens entre les affaires et les agents sont immediates</h3>");
                
                out.println("</form>");
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
            
            String indice = new String(request.getParameter("indice"));
            String detail = new String(request.getParameter("detail"));
            String miseEnService = new String(request.getParameter("miseEnService"));
            String codeGare = new String(request.getParameter("codeGare"));
            String codeEntreprise = new String(request.getParameter("codeEntreprise"));
            String envoiTx = new String(request.getParameter("envoiTx"));
            String sescoTx = new String(request.getParameter("sescoTx"));
            String difficulte = new String(request.getParameter("difficulte"));
            String poidsKg = new String(request.getParameter("poidsKg"));
            String contraires = new String(request.getParameter("contraires"));
            String autres = new String(request.getParameter("autres"));
            String relations = new String(request.getParameter("relations"));
            String materiel = new String(request.getParameter("materiel"));
            String delais = new String(request.getParameter("delais"));
            String reports = new String(request.getParameter("reports"));
            String metre = new String(request.getParameter("metre"));
            String projet = new String(request.getParameter("projet"));
            String etudeTravaux = new String(request.getParameter("etudeTravaux"));
            String conforme = new String(request.getParameter("conforme"));
            
            ArrayList list = new ArrayList();
            
            list.add(codeGare);
            list.add(codeEntreprise);
            list.add(indice);
            list.add(detail);
            list.add(miseEnService);
            list.add(envoiTx);
            list.add(sescoTx);
            list.add(difficulte);
            list.add(poidsKg);
            list.add(contraires);
            list.add(autres);
            list.add(relations);
            list.add(materiel);
            list.add(delais);
            list.add(reports);
            list.add(metre);
            list.add(projet);
            list.add(etudeTravaux);
            list.add(conforme);
            
            list = TransformString(list);
            
            try {
                
                //Creation de l'objet associé -----------------------------------------------------
                
                EtudesDAO etude = null;
                etude = creerObjet(request, etude);
                
                //Actions du controleur ------------------------------------------------------------
                
                etude.Mod(codeEtude, list); // Charge toutes les informations
                
                //Operations effectuées avec succès------------------------------------------------
                
                retourVue(request, response,"/ShowEtudes?choix_menu=Null");
                
            }
            
            catch(SQLException ex) {
                retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState()+"&retour=ModifyEtudes&nomCode=codeEtude&codeRenvoie="+codeEtude+"");
                System.out.println("\n\nDEBUT ERREUR<==========================================\n");
                ex.printStackTrace();
                System.out.println("\nFIN ERREUR<==============================================\n\n");
                
            }
            
        } else if(choix.equals("NewLinkAffaires")){
            
            String codeAffaire = new String(request.getParameter("codeAffaire"));
            
            //Creation de l'objet associé -----------------------------------------------------
            
            LinkDAOEtudes_Affaires link = null;
            link= creerObjet(request, link);
            
            try {
                link.NewLink(codeEtude, codeAffaire);
            }            catch(SQLException ex) {
                retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState());
                
                System.out.println("\n\nDEBUT ERREUR<==========================================\n");
                ex.printStackTrace();
                System.out.println("\nFIN ERREUR<==============================================\n\n");
            }
            
            //Operations effectuées avec succès------------------------------------------------
            
            retourVue(request, response,"/ModifyEtudes?choix_menu=null&codeEtude="+codeEtude+"");
        } else if(choix.equals("DeleteLinkAffaires")){
            
            String codeAffaire = new String(request.getParameter("codeAffaire"));
            
            //Creation de l'objet associé -----------------------------------------------------
            
            LinkDAOEtudes_Affaires link = null;
            link= creerObjet(request, link);
            try {
                link.DeleteLink(codeEtude, codeAffaire);
            }             catch(SQLException ex) {
                retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState());
                
                System.out.println("\n\nDEBUT ERREUR<==========================================\n");
                ex.printStackTrace();
                System.out.println("\nFIN ERREUR<==============================================\n\n");
            }
            
            //Operations effectuées avec succès------------------------------------------------
            
            retourVue(request, response,"/ModifyEtudes?choix_menu=null&codeEtude="+codeEtude+"");
        } else if(choix.equals("NewLinkAgents")){
            
            String codeAgent = new String(request.getParameter("codeAgent"));
            
            //Creation de l'objet associé -----------------------------------------------------
            
            LinkDAOEtudes_Agents link = null;
            link= creerObjet(request, link);
            
            try {
                link.NewLink(codeEtude, codeAgent);
            }            catch(SQLException ex) {
                retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState());
                
                System.out.println("\n\nDEBUT ERREUR<==========================================\n");
                ex.printStackTrace();
                System.out.println("\nFIN ERREUR<==============================================\n\n");
            }
            
            //Operations effectuées avec succès------------------------------------------------
            
            retourVue(request, response,"/ModifyEtudes?choix_menu=null&codeEtude="+codeEtude+"");
        } else if(choix.equals("DeleteLinkAgents")){
            
            String codeAgent = new String(request.getParameter("codeAgent"));
            
            //Creation de l'objet associé -----------------------------------------------------
            
            LinkDAOEtudes_Agents link = null;
            link= creerObjet(request, link);
            
            try {
                link.DeleteLink(codeEtude, codeAgent);
            }             catch(SQLException ex) {
                retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState());
                
                System.out.println("\n\nDEBUT ERREUR<==========================================\n");
                ex.printStackTrace();
                System.out.println("\nFIN ERREUR<==============================================\n\n");
            }
            
            //Operations effectuées avec succès------------------------------------------------
            
            retourVue(request, response,"/ModifyEtudes?choix_menu=null&codeEtude="+codeEtude+"");
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
