package Ajax.COURRIER.COURRIER_ES.SELECTION;

import Ajax.COURRIER.COURRIER_ES.SELECTION.AR.ARCourrier;
import Models.COURRIER.COURRIER_ES.CourriersDAO;
import Models.ETUDES.Affaires.AffairesDAO;
import Models.ETUDES.Agents.AgentsDAO;
import Models.ETUDES.Etudes.EtudesDAO;
import Models.ETUDES.Even.EvenDAO;
import Models.ETUDES.Gares.GaresDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.ButtonDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.SelectDAO;
import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Controleur.MegaControleur;
import Models.TOOLS.Utilisateurs.UtilisateursDAO;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class IndexCourrier extends MegaControleur {
    
    boolean AutorizeChangeAgent = true;
    boolean AutorizeChangeCollection = true;
    boolean AutorizeChangeEtude = true;
    
    void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        
        
        //CHARGEMENT DES OBJETS
        CourriersDAO courrier = null;
        courrier = creerObjet(request,courrier);
        
        EvenDAO even = null;
        even = creerObjet(request,even);
        
        EtudesDAO etude = null;
        etude = creerObjet(request,etude);
        
        AgentsDAO agent = null;
        agent = creerObjet(request,agent);
        
        GaresDAO gare = null;
        gare = creerObjet(request,gare);
        
        AffairesDAO affaire = null;
        affaire = creerObjet(request,affaire);

        UtilisateursDAO utilisateur = null;
        utilisateur = creerObjet(request,utilisateur);
        
        //GET DATA
        String choix_menu = request.getParameter("choix_menu");
        String choix_onglet = request.getParameter("choix_onglet");
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        
        try {
            
            if(!choix_menu.equals("LoadData") && !choix_menu.equals("DataLettre")){
                
                TOOLS_HTML.Br(out,3);
                
                out.println("<form name=Champ>");
                
                //CHARGEMENT DE LA PREMIERE PAGE CHOIX DU L'AGENT
                
                if(choix_menu.equals("AffChoixAgent")){
                    
                    AutorizeChangeAgent = true;
                    agent.Set();
                    
                    out.println("<div id=DivSaisie>");
                    out.println("<div id=Divcontenu>");
                    
                    TOOLS_HTML.getMessage(out,"h2","Selectionnez un agent<br>");
                    SelectDAO.AddSelect(out,"Agents",agent.list_CodeAgent,agent.list_Nom,1,"onChange=javascript:getHTMLCodeRequestCourrier('informations','IndexCourrier','choix_menu=AffChoixGare','Champ')");
                    
                    out.println("<table>");
                    if(utilisateur.TestAdministrateur() || utilisateur.TestModerateur()){
                        out.println("<th>");
                        ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCourrier('informations','index_Courrier_Es_Gestion','choix_menu=Null','Champ')","GESTION LETTRE");
                        out.println("</th>");
                    }
                    
                    out.println("</div>"); //FIN Divcontenu
                    out.println("</div>"); //FIN DivSaisie
                    
                    
                    
                    
                    
                    
                    
                    
                    //CHARGEMENT DE LA DEUXIEME PAGE CHOIX DE LA GARE
                    
                } else if(choix_menu.equals("AffChoixGare")){
                    
                    //GET DATA
                    String codeAgent = request.getParameter("codeAgent");
                    AutorizeChangeCollection = true;
                    if(AutorizeChangeAgent)
                        courrier.codeAgent = codeAgent;
                    AutorizeChangeAgent = false;
                    
                    gare.Set();
                    
                    ArrayList list_Gare = null;
                    ArrayList list_CodeGare = null;
                    list_Gare = new ArrayList();
                    list_CodeGare = new ArrayList();
                    
                    for(int i = 0 ; i < gare.list_CodeGare.size() ; i++)
                        if(gare.list_Groupe.get(i).equals("SE")){
                        list_Gare.add(gare.list_Gare.get(i));
                        list_CodeGare.add(gare.list_CodeGare.get(i));
                        }
                    
                    out.println("<div id=DivSaisie>");
                    out.println("<div id=DivFont>");
                    TOOLS_HTML.getMessage(out,"h2","Agent : <span style='color:orange; text-transform: uppercase;'>"+agent.GetnomAgent(courrier.codeAgent)+"</span>");
                    out.println("</div>"); //FIN DivFont
                    out.println("</div>"); //FIN DivSaisie
                    
                    out.println("<div id=DivSaisie>");
                    out.println("<div id=Divcontenu>");
                    
                    TOOLS_HTML.getMessage(out,"h2","Selectionnez une collection<br>");
                    SelectDAO.AddSelect(out,"Gares",list_CodeGare,list_Gare,1,"onChange=javascript:getHTMLCodeRequestCourrier('informations','IndexCourrier','choix_menu=AffChoixIndice','Champ')");
                    
                    out.println("<table><th>");
                    ButtonDAO.AddButton(out,1,"IndexCourrier","choix_menu=AffChoixAgent","RETOUR");
                    out.println("</th>");
                    
                    out.println("</div>"); //FIN DivFont
                    out.println("</div>"); //FIN DivSaisie
                    
                    
                    
                    
                    
                    
                    
                    //CHARGEMENT DE LA TROISIEME PAGE CHOIX DE L'INDICE
                } else if(choix_menu.equals("AffChoixIndice")){
                    
                    //GET DATA
                    String codeGare = request.getParameter("codeGare");
                    
                    AutorizeChangeEtude = true;
                    if(AutorizeChangeCollection)
                        courrier.codeGare = codeGare;
                    AutorizeChangeCollection = false;
                    
                    ArrayList list_codeEtude = null;
                    ArrayList list_Indice = null;
                    ArrayList list_Detail = null;
                    ArrayList list_IndiceDetail = null;
                    list_codeEtude = new ArrayList();
                    list_Indice = new ArrayList();
                    list_Detail = new ArrayList();
                    list_IndiceDetail = new ArrayList();
                    
                    list_Indice = etude.GetListIndice(courrier.codeGare);
                    list_Detail = etude.GetListDetail(courrier.codeGare);
                    list_codeEtude = etude.GetListCodeEtude(courrier.codeGare);
                    for(int i = 0 ; i < list_Indice.size() ; i++)
                        list_IndiceDetail.add(list_Indice.get(i)+" -- "+list_Detail.get(i));
                    
                    out.println("<div id=DivSaisie>");
                    out.println("<div id=DivFont>");
                    TOOLS_HTML.getMessage(out,"h2","Agent : <span style='color:orange; text-transform: uppercase;'>"+agent.GetnomAgent(courrier.codeAgent)+"</span>");
                    TOOLS_HTML.getMessage(out,"h2","Collection : <span style='color:orange; text-transform: uppercase;'>"+gare.GetnomGare(courrier.codeGare)+"</span>");
                    out.println("</div>"); //FIN DivFont
                    out.println("</div>"); //FIN DivSaisie
                    
                    out.println("<div id=DivSaisie>");
                    out.println("<div id=Divcontenu>");
                    
                    TOOLS_HTML.getMessage(out,"h2","Selectionnez un indice<br>");
                    SelectDAO.AddSelect(out,"Indice",list_codeEtude,list_IndiceDetail,1,"onChange=javascript:getHTMLCodeRequestCourrier('informations','IndexCourrier','choix_menu=AffChoixCategorie','Champ')");
                    
                    out.println("<table><th>");
                    ButtonDAO.AddButton(out,1,"IndexCourrier","choix_menu=AffChoixGare","RETOUR");
                    out.println("</th>");
                    
                    out.println("</div>"); //FIN Divcontenu
                    out.println("</div>"); //FIN DivSaisie
                    
                }
                
                
                
                
                
                
                //CHARGEMENT DE LA QUATRIEME PAGE CHOIX DE LA CATEGORIE LETTRE EXEMPLAIRE
                
                if(choix_menu.equals("AffChoixCategorie")){
                    
                    
                    courrier.SetCategorie();
                    
                    //GET DATA
                    String codeEtude = request.getParameter("codeEtude");
                    
                    if(AutorizeChangeEtude)
                        courrier.codeEtude = codeEtude;
                    AutorizeChangeEtude = false;
                    
                    out.println("<div id=DivSaisie>");
                    out.println("<div id=DivFont>");
                    TOOLS_HTML.getMessage(out,"h2","Agent : <span style='color:orange; text-transform: uppercase;'>"+agent.GetnomAgent(courrier.codeAgent)+"</span>");
                    TOOLS_HTML.getMessage(out,"h2","Collection : <span style='color:orange; text-transform: uppercase;'>"+gare.GetnomGare(courrier.codeGare)+"</span>");
                    TOOLS_HTML.getMessage(out,"h2","Indice étude : <span style='color:orange; text-transform: uppercase;'>"+etude.GetindiceEtudeSolo(courrier.codeEtude)+"</span>");
                    TOOLS_HTML.getMessage(out,"h2","Détail étude : <span style='color:orange; text-transform: uppercase;'>"+etude.GetDetail(courrier.codeEtude)+"</span>");
                    out.println("</div>"); //FIN DivFont
                    out.println("</div>"); // FIN DivSaisie
                    
                    out.println("<div id=DivSaisie>");
                    out.println("<div id=Divcontenu>");
                    TOOLS_HTML.OpenTable(out,"100%");
                    out.println("<th>");
                    TOOLS_HTML.getMessage(out,"h2","Veuillez selectionner une categorie de lettre");
                    SelectDAO.AddSelect(out,"Categories",courrier.list_CodeCategorie,courrier.list_Categorie,1,"onChange=javascript:getHTMLCodeRequestCourrier('TypeLettre','IndexCourrier','choix_menu=LoadData','Champ')");
                    
                    out.println("</th><th>");
                    out.println("<div id='TypeLettre'>");
                    out.println("</div>");
                    out.println("</th><th>");
                    
                    out.println("</th>");
                    
                    out.println("</table>");
                    
                    
                    out.println("<div id=DataLettre>");
                    out.println("</div>");
                    
                    out.println("<table><th>");
                    ButtonDAO.AddButton(out,1,"IndexCourrier","choix_menu=AffChoixIndice","RETOUR");
                    out.println("</th>");
                    
                }
                
                out.println("</form>");
                out.println("<th>");
                ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCourrier('informations','Accueil_COURRIER','choix_menu=Null','null')","QUITTER");
                out.println("</th>");
                out.println("</table>");
                
                out.println("</div>");  //FIN DivFont
                out.println("</div>");  //FIN Divcontenu
                
                out.close();
                
            }
            //ON CHARGE LE SELECTEUR DE LETTRE
            
            else if(choix_menu.equals("LoadData")){
                
                courrier.modification = false;
                
                String codeCategorie = request.getParameter("codeCategorie");
                courrier.codeCategorie = codeCategorie;
                
                courrier.SetLettre();
                TOOLS_HTML.getMessage(out,"h2","Veuillez selectionner un type de lettre");
                
                SelectDAO.AddSelect(out,"Lettres",courrier.list_CodeLettre,courrier.list_nomLettre,1,"onChange=javascript:getHTMLCodeRequestCourrier('DataLettre','DonneeCourrier','Null','Champ')");
                
            }
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
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
