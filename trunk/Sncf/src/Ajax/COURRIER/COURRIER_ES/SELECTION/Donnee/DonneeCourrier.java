package Ajax.COURRIER.COURRIER_ES.SELECTION.Donnee;

import Models.COURRIER.COURRIER_ES.CourriersDAO;
import Models.ETUDES.Etudes.EtudesDAO;
import Models.ETUDES.Even.EvenDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.InputDAO;
import Ajax.COURRIER.COURRIER_ES.SELECTION.Onglets.OngletCourrier;
import Models.ETUDES.Affaires.AffairesDAO;
import Models.ETUDES.Agents.AgentsDAO;
import Models.ETUDES.Gares.GaresDAO;
import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Ajaxmodels.OBJETS.TextAreaDAO;
import Models.TOOLS.Controleur.MegaControleur;
import Models.TOOLS.Utilisateurs.UtilisateursDAO;
import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class DonneeCourrier extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            
            //CHARGEMENT DES OBJETS
            CourriersDAO courrier = null;
            courrier = creerObjet(request,courrier);
            
            AgentsDAO agent = null;
            agent = creerObjet(request,agent);
            
            EtudesDAO etude = null;
            etude = creerObjet(request,etude);
            
            EvenDAO even = null;
            even = creerObjet(request,even);
            
            AffairesDAO affaire = null;
            affaire = creerObjet(request,affaire);
            
            UtilisateursDAO utilisateur = null;
            utilisateur = creerObjet(request,utilisateur);
            
            GaresDAO gare = null;
            gare = creerObjet(request,gare);
            
            even.Set();
            gare.Set();
            
            //GET DATA
            String codeLettre = request.getParameter("codeLettre");
            courrier.codeLettre = codeLettre;
            courrier.modification = false;
            
            if(!courrier.modification)
                courrier.SetDefautSetting(even,etude,affaire,gare);
            
            retourVue(request,response,"/ShowDonneeCourrier");
                        
        } catch (IOException ex) {
            ex.printStackTrace();
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
