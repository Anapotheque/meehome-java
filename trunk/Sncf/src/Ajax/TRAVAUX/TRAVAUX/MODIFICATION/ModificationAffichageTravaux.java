package Ajax.TRAVAUX.TRAVAUX.MODIFICATION;

import Models.ETUDES.Affaires.AffairesDAO;
import Models.ETUDES.Agents.AgentsDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.ButtonDAO;
import Models.TOOLS.Ajaxmodels.GetMessageInformationDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.InputDAO;
import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Controleur.MegaControleur;
import Models.TRAVAUX.CommandesDAO;
import Models.TRAVAUX.TravauxDAO;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class ModificationAffichageTravaux extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        try {
            
            //RECUPERATION OBJET
            AgentsDAO agent= null;
            agent = creerObjet(request,agent);
            
            CommandesDAO commande = null;
            commande = creerObjet(request,commande);
            
            TravauxDAO travaux = null;
            travaux = creerObjet(request,travaux);
            
            AffairesDAO affaire = null;
            affaire= creerObjet(request, affaire);
            
            String numeroFiche = request.getParameter("numeroFiche");
            travaux.codeFiche = numeroFiche;
            
            //INITIALISATION DU FLUX DE COMMUNICATION AVEC LA PAGE JSP
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            //DIV DE MISE EN FORME ALIGNEMENT GAUCHE
            out.println("<div Style='text-align: left'>");
            
            //CONTENU DE LA PAGE AFFICHAGE
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>Fiche de travail numero ");
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation1",""+numeroFiche);
            
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>Agent : ");
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation1",""+agent.GetnomAgent(travaux.GetNumeroAgent(numeroFiche)));
            
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>Commande : ");
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation1",""+commande.GetNumCommande(travaux.GetNumeroCommande(numeroFiche)));
            
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>Affaire : ");
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation1",""+affaire.GetnomAffaire(travaux.GetNumeroAffaire(numeroFiche)));
            
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>Facturation :");
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation1",""+travaux.GetImputation(numeroFiche));
            
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>Date :");
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation1",""+travaux.GetDateFiche(numeroFiche));
            
            TOOLS_HTML.OpenTable(out,"100%");
            out.println("<tr>");
            out.println("<td>");
            
            ArrayList list = new ArrayList();
            list = travaux.GetInfosTravaux(numeroFiche);
            
            //DATE DE LIVRAISON
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>Date de livraison ");
            out.println("<input id=MyDate value='"+list.get(0)+"' name=MyDate style='width:145px' onClick=toggleCalendar('MyDate')> ");
            
            //TIRAGE EN KG
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>Tirage en kg ");
            InputDAO.AddInput(out,"text","Tirage","250px",""+list.get(1));
            
            //PHOTOCOPIE EN KG
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>Photocopie en kg ");
            InputDAO.AddInput(out,"text","Photocopie","250px",""+list.get(2));
            
            //TEINTAGE DE PLAN
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>Teintage de plan en Kg");
            InputDAO.AddInput(out,"text","Teintage","250px",""+list.get(3));
            
            //TRACES DAO 1 Pli A4
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>Tracé DAO 1 pli A4 ");
            InputDAO.AddInput(out,"text","DAO1","250px",""+list.get(4));
            
            //TRACES DAO 2 Pli A4
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>Tracé DAO 2 pli A4 ");
            InputDAO.AddInput(out,"text","DAO2","250px",""+list.get(5));
            
            //TRACES DAO 3 Pli A4
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>Tracé DAO 3 pli A4 ");
            InputDAO.AddInput(out,"text","DAO3","250px",""+list.get(6));
            
            //SUPPLEMENT
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>Supplement pour tirage couleur ");
            
            if(list.get(7).equals("1"))
                InputDAO.AddInput(out,"checkbox","Supplement","20px","1","Checked");
            else
                InputDAO.AddInput(out,"checkbox","Supplement","20px","1");
            
            out.println("</td>");
            out.println("<td>");
            
            //ETABLISSEMENT DE DOCUMENTS
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>Etablissement de documents ( le pli A4 )");
            InputDAO.AddInput(out,"text","Etablissement","250px",""+list.get(8));
            
            //MODIFICATION DE DOCUMENTS
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>Modification de documents ( le pli A4 )");
            InputDAO.AddInput(out,"text","Modification","250px",""+list.get(9));
            
            //TRAITEMENT RC
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>Traitement RC ( le folio )");
            InputDAO.AddInput(out,"text","Traitement","250px",""+list.get(10));
            
            //BORDAGE
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>Bordage ");
            InputDAO.AddInput(out,"text","Bordage","250px",""+list.get(11));
            
            //CC1
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>CC1 ");
            InputDAO.AddInput(out,"text","CC1","250px",""+list.get(12));
            //CC2
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>CC2 ");
            InputDAO.AddInput(out,"text","CC2","250px",""+list.get(13));
            //CC3
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>CC3 ");
            InputDAO.AddInput(out,"text","CC3","250px",""+list.get(14));
            //CC+
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>CC+ ");
            InputDAO.AddInput(out,"text","CC","250px",""+list.get(15));
            
            //NUM OE
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>Numero d'OE ");
            InputDAO.AddInput(out,"text","NumOE","250px",""+list.get(16));
            
            out.println("</td>");
            out.println("</tr>");
            out.println("</table>");
            
            out.println("<div id=Enregistrement></div>");
            
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestTravaux('Enregistrement','EnregistrementModificationAffichageTravaux','Champ')","Enregistrer");
            ButtonDAO.AddButtonStyle(out,1,"javascript:Ajax('data','ModificationDataFicheTravail')","Retour");
            //FIN DU CONTENU DE LA PAGE
            
            out.println("</div>");  //FIN ALIGN LEFT
            
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
