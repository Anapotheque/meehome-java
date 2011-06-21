package Ajax.COURRIER.COURRIER_DEPART.GESTION;

import Models.COURRIER.COURRIERDEPART.CourrierDepartDAO;
import Models.ETUDES.Affaires.AffairesDAO;
import Models.ETUDES.Agents.AgentsDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.ButtonDAO;
import Models.TOOLS.Ajaxmodels.GetMessageInformationDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.InputDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.SelectDAO;
import Models.TOOLS.Controleur.MegaControleur;
import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class ModifyCourrierDepart extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        try {
            
            //RECUPERATION DES OBJETS
            AgentsDAO agent = null;
            agent = creerObjet(request,agent);
            
            AffairesDAO affaire = null;
            affaire = creerObjet(request,affaire);
            
            CourrierDepartDAO courrierDepart = null;
            courrierDepart = creerObjet(request,courrierDepart);
            
            agent.Set();
            affaire.Set();
            
            courrierDepart.codeLettreModification = request.getParameter("CodeLettre");
            
            courrierDepart.GetListCourrierModification(courrierDepart.codeLettreModification);
            
            //INITIALISATION DU FLUX DE COMMUNICATION AVEC LA PAGE JSP
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            //LIGNE ROUGE
            out.println("<div id=ajout>");
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation1",".: SNCF :: COURRIER DEPART :: MODIFICATION :.<br>");
            out.println("</div>");  //FIN AJOUT
            
            //DIV DE MISE EN FORME ALIGNEMENT GAUCHE
            out.println("<div Style='text-align: left'>");
            
            //CONTENU DE LA PAGE AFFICHAGE
            
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>Affaire : ");
            SelectDAO.GetMultSelect(out,"affaire",""+courrierDepart.list.get(0),affaire.GetnomAffaire(""+courrierDepart.list.get(0)),affaire.list_CodeAffaire,affaire.list_Nom,affaire.list_CodeAffaire,1);
            
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>Agent : ");
            SelectDAO.GetSelect(out,"agent",""+courrierDepart.list.get(1),agent.GetnomAgent(""+courrierDepart.list.get(1)),agent.list_CodeAgent,agent.list_Nom,1);
            
            String destination = ""+courrierDepart.list.get(2);
            destination = destination.replace("'","&acute;");
            String designation = ""+courrierDepart.list.get(3);
            designation = designation.replace("'","&acute;");
            
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>Destination : ");
            InputDAO.AddInput(out,"text","destination","600px",destination);
            
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>Destinataire : ");
            InputDAO.AddInput(out,"text","destinataire","600px",designation);
            
            //DIV DE MISE EN FORME ENREGISTREMENT
            out.println("<div id=Enregistrement>");
            out.println("</div>");  //FIN Enregistrement
            
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCourrier('Enregistrement','EnregistrerModificationCourrierDepart','','Champ')","Enregistrer");
            ButtonDAO.AddButtonStyle(out,1,"javascript:Ajax('data','ListeChoix')","Retour");
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
