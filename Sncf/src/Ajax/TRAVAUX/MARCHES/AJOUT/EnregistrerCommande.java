package Ajax.TRAVAUX.MARCHES.AJOUT;

import Models.TOOLS.Ajaxmodels.GetMessageInformationDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.ButtonDAO;
import Models.TOOLS.Controleur.MegaControleur;
import Models.TRAVAUX.CommandesDAO;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class EnregistrerCommande extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        try {
            
            //RECUPERATION DES OBJETS
            CommandesDAO commande= null;
            commande = creerObjet(request,commande);
            
            ArrayList list = new ArrayList();
            
            list.add(request.getParameter("commande"));
            list.add(request.getParameter("codeEntreprise"));
            
            list.add(request.getParameter("EtablissementDocumentPrix"));
            list.add(request.getParameter("EtablissementDocumentPourcentage"));
            list.add(request.getParameter("ModificationDocumentPrix"));
            list.add(request.getParameter("ModificationDocumentPourcentage"));
            list.add(request.getParameter("TraitementPrix"));
            list.add(request.getParameter("TraitementPourcentage"));
            list.add(request.getParameter("TeintagePrix"));
            list.add(request.getParameter("TeintagePourcentage"));
            list.add(request.getParameter("BordagePrix"));
            list.add(request.getParameter("BordagePourcentage"));
            list.add(request.getParameter("HelioPrix"));
            list.add(request.getParameter("HelioPourcentage"));
            list.add(request.getParameter("PhotocopiePrix"));
            list.add(request.getParameter("PhotocopiePourcentage"));
            list.add(request.getParameter("CC1Prix"));
            list.add(request.getParameter("CC1Pourcentage"));
            list.add(request.getParameter("CC2Prix"));
            list.add(request.getParameter("CC2Pourcentage"));
            list.add(request.getParameter("CC3Prix"));
            list.add(request.getParameter("CC3Pourcentage"));
            list.add(request.getParameter("CCSupPrix"));
            list.add(request.getParameter("CCSupPourcentage"));
            list.add(request.getParameter("DAO1A4Prix"));
            list.add(request.getParameter("DAO1A4Pourcentage"));
            list.add(request.getParameter("DAO2A4Prix"));
            list.add(request.getParameter("DAO2A4Pourcentage"));
            list.add(request.getParameter("DAO3A4Prix"));
            list.add(request.getParameter("DAO3A4Pourcentage"));
            list.add(request.getParameter("SupplementPrix"));
            list.add(request.getParameter("SupplementPourcentage"));
            
            list = TransformString(list);
            commande.New(list);
            
            //INITIALISATION DU FLUX DE COMMUNICATION AVEC LA PAGE JSP
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation1","<br><br>DONNEES ENREGISTREES DANS LA BASE<br><br>");
            
            //FERMETURE DU FLUX DE SORTIE
            out.close();
            
        } catch (SQLException ex) {
            retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState()+"&retour=AccueilTravaux");
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
