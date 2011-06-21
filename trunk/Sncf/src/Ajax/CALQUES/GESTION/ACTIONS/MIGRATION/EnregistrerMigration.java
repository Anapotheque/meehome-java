package Ajax.CALQUES.GESTION.ACTIONS.MIGRATION;

import Models.CALQUES.CalquesDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.ButtonDAO;
import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Controleur.MegaControleur;
import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class EnregistrerMigration extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        try {
            
//----------------------------------------------------------------------------------------------------------------
//    GESTION OBJET DE LA SERLVET
//----------------------------------------------------------------------------------------------------------------
            
            //RECUPERATION DE MES OBJETS
            CalquesDAO calques = null;
            calques = creerObjet(request,calques);
            
            //GET DATA ON RECUPERE LE CODE COLLECTION
            String codeCollection = request.getParameter("codeCollectionMigration");
            String codeCase = request.getParameter("codeCaseMigration");
            
            //ON MIGRE LE CALQUE VERS SES NOUVELLES COORDONNEES
            calques.Migration(codeCase);
            
//----------------------------------------------------------------------------------------------------------------
//    GESTION DES ELEMENTS A ENVOYER A LA SERVLET
//----------------------------------------------------------------------------------------------------------------
            
            //INITIALISATION DU FLUX DE COMMUNICATION AVEC LA PAGE JSP
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            TOOLS_HTML.getMessage(out,"h2","DONNEES ENREGISTREES DANS LA BASE DE DONNEE");
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCalques('calques','GetCalquesGestions','SauvegardeCodeCalque_OR_Case=SauvegardeCodeCalque_OR_Case','Null')","OK");
            
            //FERMETURE DU FLUX DE SORTIE
            out.close();
            
//----------------------------------------------------------------------------------------------------------------
//    GESTION DES ERREURS
//----------------------------------------------------------------------------------------------------------------

            
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
