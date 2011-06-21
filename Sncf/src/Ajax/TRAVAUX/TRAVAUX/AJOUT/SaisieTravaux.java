package Ajax.TRAVAUX.TRAVAUX.AJOUT;

import Models.ETUDES.Affaires.AffairesDAO;
import Models.ETUDES.Agents.AgentsDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.ButtonDAO;
import Models.TOOLS.Ajaxmodels.GetMessageInformationDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.InputDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.SelectDAO;
import Models.TOOLS.Controleur.MegaControleur;
import Models.TOOLS.Date.DataDate;
import Models.TRAVAUX.CommandesDAO;
import Models.TRAVAUX.TravauxDAO;
import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class SaisieTravaux extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        try {
            
            //RECUPERATION DES OBJETS
            AgentsDAO agent= null;
            agent = creerObjet(request,agent);
            
            CommandesDAO commande = null;
            commande = creerObjet(request,commande);
            
            TravauxDAO travaux = null;
            travaux = creerObjet(request,travaux);
            
            AffairesDAO affaire = null;
            affaire= creerObjet(request, affaire);
            
            agent.Set();
            affaire.Set();
            commande.Set();
            
            //INITIALISATION DU FLUX DE COMMUNICATION AVEC LA PAGE JSP
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            //LIGNE ROUGE
            out.println("<div id=ajout>");
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation1",".: SNCF :: TRAVAUX :: SAISIE FICHE DE TRAVAIL :.<br>");
            out.println("</div>");  //FIN AJOUT
            
            //DIV DE MISE EN FORME CADRE GRIS
            out.println("<div id=Divcontenu>");
            
            //DIV DE MISE EN FORME ALIGNEMENT GAUCHE
            out.println("<div Style='text-align: left'>");
            
            //CONTENU DE LA PAGE AFFICHAGE
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>Fiche de travail numero ");
            InputDAO.AddInput(out,"text","EtablissementDocumentPrixTotale","100px",""+travaux.GetLastnumero(),true);
            
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>Agent : ");
            SelectDAO.AddSelect(out,"agent",agent.list_CodeAgent,agent.list_Nom,1);
            
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>Commande : ");
            SelectDAO.AddMultSelect(out,"commande",commande.listCode,commande.listNumCommande,commande.listNomEntreprise,1,"");
            
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>Affaire : ");
            SelectDAO.AddMultSelect(out,"affaire",affaire.list_CodeAffaire,affaire.list_CodeAffaire,affaire.list_Nom,1,"onChange=javascript:getHTMLCodeRequestTravaux('imputation','GetImputation','Champ')");
            
            out.println("<div id=imputation></div>");
            
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>Date : ");
            out.println("<input id=date value='"+DataDate.GetDate()+"' name=date style='width:145px' onClick=toggleCalendar('date')> ");
            
            out.println("<div id=Enregistrement></div>");
            
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestTravaux('Enregistrement','EnregistrerFichedeTravail','Champ')","Enregistrer");
            ButtonDAO.AddButtonStyle(out,1,"javascript:Ajax('informations','AccueilTravaux')","Retour");
            //FIN DU CONTENU DE LA PAGE
            
            out.println("</div>");  //FIN ALIGN LEFT
            out.println("</div>");  //FIN Divcontenu
            
            //FERMETURE DU FLUX DE SORTIE
            out.close();
            
        } catch (SQLException ex) {
            retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState());
            System.out.println("\n\nDEBUT ERREUR<==========================================\n");
            ex.printStackTrace();
            System.out.println("\nFIN ERREUR<==============================================\n\n");
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
