package Ajax.ETUDES;

import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Controleur.MegaControleur;
import Models.ETUDES.Affaires.AffairesDAO;
import Models.ETUDES.Agents.AgentsDAO;
import Models.ETUDES.Entreprises.EntreprisesDAO;
import Models.ETUDES.Etudes.EtudesDAO;
import Models.ETUDES.Even.EvenDAO;
import Models.ETUDES.Gares.GaresDAO;
import Models.TOOLS.Utilisateurs.UtilisateursDAO;
import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

//@author RABALLAND Romain v3.0

public class IndexDVSG extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        AffairesDAO affaire = null;
        affaire = creerObjet(request, affaire);
        
        EtudesDAO etude = null;
        etude = creerObjet(request, etude);
        
        AgentsDAO agent = null;
        agent = creerObjet(request, agent);
        
        GaresDAO gare = null;
        gare = creerObjet(request, gare);
        
        EntreprisesDAO entreprise = null;
        entreprise = creerObjet(request, entreprise);
        
        EvenDAO even = null;
        even = creerObjet(request, even);
        
        UtilisateursDAO utilisateur = null;
        utilisateur = creerObjet(request, utilisateur);
        
        //INITIALISATION DES DONNEES
        try {
            etude.Set();
            agent.Set();
            entreprise.Set();
            affaire.Set();
            gare.Set();
            even.Set();
        } catch (SQLException ex) {
            retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState());
            System.out.println("\n\nDEBUT ERREUR<==========================================\n");
            ex.printStackTrace();
            System.out.println("\nFIN ERREUR<==============================================\n\n");
        }
        
        //Definition du type de texte pour l'envoie à la page jsp -----------------------------------------------------
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<div id='corps'>");
        
        TOOLS_HTML.OpenDiv(out,"DivLoad");
        TOOLS_HTML.getMessage(out,"h2","VOTRE PAGE EST EN COURS DE CHARGEMENT VUEILLEZ PATIENTER");
        TOOLS_HTML.CloseDiv(out);
        
        TOOLS_HTML.getMessage(out,"h4","ETUDES SIGNALISATION");
        
        TOOLS_HTML.getMessage(out,"h5","SNCF.ETUDE");
        
        out.println("<table><tr>");

        TOOLS_HTML.AddButtonMenuTable(out,"ShowEtudes","ETUDES");
        TOOLS_HTML.AddButtonMenuTable(out,"ShowAffaires","AFFAIRES");
        TOOLS_HTML.AddButtonMenuTable(out,"ShowGares","GARES");
        if(utilisateur.TestModerateur() || utilisateur.TestManageur() || utilisateur.TestAdministrateur())
        TOOLS_HTML.AddButtonMenuTable(out,"ShowAgents","AGENTS");

        out.println("<tr>");

        TOOLS_HTML.AddButtonMenuTable(out,"ShowFactures","FACTURES");
        TOOLS_HTML.AddButtonMenuTable(out,"ShowRch","R CHANTIER");
        TOOLS_HTML.AddButtonMenuTable(out,"ShowEntreprises","ENTREPRISES");
        TOOLS_HTML.AddButtonMenuTable(out,"ShowFmr","FMR");
        
        out.println("</tr>");
        out.println("<tr>");
        
        out.println("<td colspan = '4'>");
        TOOLS_HTML.NewButtonMenu(out,"Outils","RECHERCHE");
        
        out.println("</tr>");
        
        out.println("</table>");
        
        //FERMETURE
        out.close();
        
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
