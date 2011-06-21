package Ajax.TRAVAUX.TRAVAUX.MODIFICATION;

import Models.ETUDES.Affaires.AffairesDAO;
import Models.ETUDES.Agents.AgentsDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.ButtonDAO;
import Models.TOOLS.Ajaxmodels.GetMessageInformationDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.InputDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.SelectDAO;
import Models.TOOLS.Controleur.MegaControleur;
import Models.TRAVAUX.CommandesDAO;
import Models.TRAVAUX.TravauxDAO;
import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class ModifyTravaux extends MegaControleur {
    
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
            
            String numeroFiche = request.getParameter("numeroFiche");
            travaux.codeFiche = numeroFiche;
            
            //INITIALISATION DU FLUX DE COMMUNICATION AVEC LA PAGE JSP
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            //LIGNE ROUGE
            out.println("<div id=ajout>");
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation1",".: SNCF :: TRAVAUX :: MODIFICATION FICHE DE TRAVAIL :.<br>");
            out.println("</div>");  //FIN AJOUT
            
            //DIV DE MISE EN FORME CADRE GRIS
            out.println("<div id=Divcontenu>");
            
            //DIV DE MISE EN FORME ALIGNEMENT GAUCHE
            out.println("<div Style='text-align: left'>");
            
            //CONTENU DE LA PAGE AFFICHAGE
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>Fiche de travail numero ");
            InputDAO.AddInput(out,"text","EtablissementDocumentPrixTotale","100px",numeroFiche,true);
            
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>Agent : ");
            SelectDAO.GetSelect(out,"agent",travaux.GetNumeroAgent(numeroFiche),agent.GetnomAgent(travaux.GetNumeroAgent(numeroFiche)),agent.list_CodeAgent,agent.list_Nom,1);
            
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>Commande : ");
            SelectDAO.GetMultSelect(out,"commande",travaux.GetNumeroCommande(numeroFiche),commande.GetNumCommande(travaux.GetNumeroCommande(numeroFiche)),commande.listCode,commande.listNumCommande,commande.listNomEntreprise,1);
            
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>Affaire : ");
            SelectDAO.GetMultSelect(out,"affaire",travaux.GetNumeroAffaire(numeroFiche),affaire.GetnomAffaire(travaux.GetNumeroAffaire(numeroFiche)),affaire.list_CodeAffaire,affaire.list_CodeAffaire,affaire.list_Nom,1);
            
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>Imputation : ");
            
            if(affaire.GetImputationAffaire(travaux.GetNumeroAffaire(numeroFiche)).equals(affaire.GetCompteEtude(travaux.GetNumeroAffaire(numeroFiche)))){
                
                InputDAO.AddInput(out,"radio","imputation","20px",affaire.GetImputationAffaire(travaux.GetNumeroAffaire(numeroFiche)),"checked");
                GetMessageInformationDAO.getMessageInformation(out,"TexteInformation1",affaire.GetImputationAffaire(travaux.GetNumeroAffaire(numeroFiche)));
                
            } else{
                if(!affaire.GetImputationAffaire(travaux.GetNumeroAffaire(numeroFiche)).equals("")){
                    if(travaux.GetImputation(numeroFiche).equals(affaire.GetImputationAffaire(travaux.GetNumeroAffaire(numeroFiche))))
                        InputDAO.AddInput(out,"radio","imputation","20px",affaire.GetImputationAffaire(travaux.GetNumeroAffaire(numeroFiche)),"checked");
                    else
                        InputDAO.AddInput(out,"radio","imputation","20px",affaire.GetImputationAffaire(travaux.GetNumeroAffaire(numeroFiche)));
                    GetMessageInformationDAO.getMessageInformation(out,"TexteInformation1",affaire.GetImputationAffaire(travaux.GetNumeroAffaire(numeroFiche)));
                }
                
                if(!affaire.GetCompteEtude(travaux.GetNumeroAffaire(numeroFiche)).equals("")){
                    if(travaux.GetImputation(numeroFiche).equals(affaire.GetCompteEtude(travaux.GetNumeroAffaire(numeroFiche))))
                        InputDAO.AddInput(out,"radio","imputation","20px",affaire.GetCompteEtude(travaux.GetNumeroAffaire(numeroFiche)),"checked");
                    else
                        InputDAO.AddInput(out,"radio","imputation","20px",affaire.GetCompteEtude(travaux.GetNumeroAffaire(numeroFiche)));
                    GetMessageInformationDAO.getMessageInformation(out,"TexteInformation1",""+affaire.GetCompteEtude(travaux.GetNumeroAffaire(numeroFiche)));
                }
            }
            
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>Date : ");
            out.println("<input id=date value='"+travaux.GetDateFiche(numeroFiche)+"' name=date style='width:145px' onClick=toggleCalendar('date')> ");
            
            out.println("<div id=Enregistrement></div>");
            
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestTravaux('Enregistrement','EnregistrerModifyTravaux','Champ')","Enregistrer");
            ButtonDAO.AddButtonStyle(out,1,"javascript:Ajax('data','ListeFicheTravail')","Retour");
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
