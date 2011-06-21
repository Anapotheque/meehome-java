package Ajax.CALQUES.GESTION.ACTIONS.MODIFICATION;

import Models.CALQUES.CalquesDAO;
import Models.CALQUES.CasesDAO;
import Models.ETUDES.Gares.GaresDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.ButtonDAO;
import Models.TOOLS.Ajaxmodels.GetMessageInformationDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.InputDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.SelectDAO;
import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Controleur.MegaControleur;
import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class ModifierCalques extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        try {
            
            //RECUPERATION DES OBJETS
            CasesDAO cases = null;
            cases = creerObjet(request,cases);
            
            CalquesDAO calque = null;
            calque = creerObjet(request,calque);
            
            //GET DATA ON RECUPERE LE CODE DU CALQUE A MODIFIER
            calque.codeCalques = request.getParameter("codeCalques");
            cases.codeCase = request.getParameter("codeCase");
            
            //INITIALISATION DU FLUX DE COMMUNICATION AVEC LA PAGE JSP
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            //ON TEST SI LE CALQUE SELECTIONNE N'EST PAS SORTIE DE LA CALQUOTHEQUE
            if(calque.TestCalquesPresent(calque.codeCalques)){
                
                //RECUPERATION DU NUMERO DU CALQUE SELECTIONNE
                GetMessageInformationDAO.getMessageInformation(out,"TexteInformation2","Numéro :");
                InputDAO.AddInput(out,"text","numeroCalque","145px",calque.GetNumeroCalque(calque.codeCalques));
                
                //MENU DE SELECTION
                TOOLS_HTML.OpenTable(out,"50%");
                out.println("<th>");
                ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCalques('Enregistrement','EnregistrerModification','choix_menu=Null','Champ')","ENREGISTRER");
                out.println("</th><th>");
                ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCalques('Enregistrement','MigrationCalques','choix_menu=Null','Champ')","MIGRATION");
                out.println("</th><th>");
                ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCalques('calques','GetCalquesGestions','SauvegardeCodeCalque_OR_Case=YES','Null')","RETOUR");
                out.println("</th></table>");
                
                //SI IL EST SORTI MESSAGE D'ERREUR
            } else {
                GetMessageInformationDAO.getMessageInformation(out,"TexteInformation3","Modification impossible, le calques est actuellement sorti de la calquotheque");
                ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCalques('calques','GetCalquesGestions','SauvegardeCodeCalque_OR_Case=YES','Null')","RETOUR");
            }
            
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
