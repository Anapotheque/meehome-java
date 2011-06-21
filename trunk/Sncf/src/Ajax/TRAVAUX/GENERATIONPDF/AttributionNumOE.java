package Ajax.TRAVAUX.GENERATIONPDF;

import Models.TOOLS.Ajaxmodels.OBJETS.ButtonDAO;
import Models.TOOLS.Ajaxmodels.GetMessageInformationDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.InputDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.SelectDAO;
import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Controleur.MegaControleur;
import Models.TRAVAUX.CommandesDAO;
import Models.TRAVAUX.TravauxDAO;
import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class AttributionNumOE extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        try {
            
            //RECUPERATION DES OBJETS
            CommandesDAO commande= null;
            commande = creerObjet(request,commande);
            
            TravauxDAO travaux= null;
            travaux = creerObjet(request,travaux);
                        
            commande.Set();
            
            //INITIALISATION DU FLUX DE COMMUNICATION AVEC LA PAGE JSP
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            //LIGNE ROUGE
            out.println("<div id=ajout>");
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation1",".: SNCF :: TRAVAUX :: GENERATION NUMERO D'OE :.<br>");
            out.println("</div>");  //FIN AJOUT
            
            //DIV DE MISE EN FORME CADRE GRIS
            out.println("<div id=Divcontenu>");
            
            //DIV DE RECHARGEMENT AJAX
            out.println("<div id=Suite>");
            
            TOOLS_HTML.getMessage(out,"h2","Selectionnez une commande");
            SelectDAO.AddMultSelect(out,"commande",commande.listCode,commande.listNumCommande,commande.listNomEntreprise,1,"");
            TOOLS_HTML.getMessage(out,"h2","Numéro d'OE");
            InputDAO.AddInput(out,"text","NumOE","145px",travaux.numOE);
            
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestTravaux('Suite','EnregistrementNumOE','Champ')","Continuer");
            
            ButtonDAO.AddButtonStyle(out,1,"javascript:Ajax('informations','AccueilTravaux')","Retour");
            //FIN DU CONTENU DE LA PAGE
            
            out.println("</div>");  //FIN Suite
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
