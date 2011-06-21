package Ajax.TRAVAUX.TRAVAUX.MODIFICATION;

import Models.TOOLS.Ajaxmodels.GetMessageInformationDAO;
import Models.TOOLS.Controleur.MegaControleur;
import Models.TRAVAUX.TravauxDAO;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class EnregistrementModificationAffichageTravaux extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        try {
            
            //RECUPERATION DES OBJETS
            TravauxDAO travaux = null;
            travaux = creerObjet(request, travaux);
            
            ArrayList list = new ArrayList();
            
            list.add(request.getParameter("MyDate"));
            list.add(request.getParameter("Tirage"));
            list.add(request.getParameter("DAO1"));
            list.add(request.getParameter("DAO2"));
            list.add(request.getParameter("DAO3"));
            list.add(request.getParameter("Supplement"));
            list.add(request.getParameter("Photocopie"));
            list.add(request.getParameter("CC1"));
            list.add(request.getParameter("CC2"));
            list.add(request.getParameter("CC3"));
            list.add(request.getParameter("CC"));
            list.add(request.getParameter("Bordage"));
            list.add(request.getParameter("Etablissement"));
            list.add(request.getParameter("Modification"));
            list.add(request.getParameter("Traitement"));
            list.add(request.getParameter("Teintage"));
            list.add(request.getParameter("NumOE"));
            
            list = TransformString(list);
            travaux.ModFiche(list);
            
            //INITIALISATION DU FLUX DE COMMUNICATION AVEC LA PAGE JSP
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation1","<br><br>DONNEES ENREGISTREES DANS LA BASE<br><br>");
            
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
