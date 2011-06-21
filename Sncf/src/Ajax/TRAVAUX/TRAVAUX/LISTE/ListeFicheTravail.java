package Ajax.TRAVAUX.TRAVAUX.LISTE;

import Models.TOOLS.Ajaxmodels.GetMessageInformationDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.ButtonDAO;
import Models.TOOLS.Ajaxmodels.TRAVAUX.ShowTravauxDAO;
import Models.TOOLS.Controleur.MegaControleur;
import Models.TRAVAUX.TravauxDAO;
import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class ListeFicheTravail extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            
            //RECUPERATION OBJET
            TravauxDAO travaux = null;
            travaux = creerObjet(request, travaux);
            
            travaux.Set(false);
            
            //INITIALISATION DU FLUX DE COMMUNICATION AVEC LA PAGE JSP
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            //LIGNE ROUGE
            out.println("<div id=ajout>");
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation1",".: SNCF :: TRAVAUX :: LISTE :.<br>");
            out.println("</div>");  //FIN AJOUT
            
            //DIV DE MISE EN FORME CADRE GRIS
            out.println("<div id=Divcontenu>");
            
            ButtonDAO.AddButtonStyle(out,1,"javascript:Ajax('informations','AccueilTravaux')","Retour");
            
            if(travaux.list.size() != 0)
                ShowTravauxDAO.Show(request,out,"Travaux",travaux.list_Titre,travaux.list,travaux.listCode,travaux.list_Size,false,true);
            else
                GetMessageInformationDAO.getMessageInformation(out,"TexteInformation3","AUCUNE FICHE DE TRAVAIL N'EST ENREGISTRE POUR LA PROCHAINE FACTURE<br>");
            out.println("</div>");  //FIN Divcontenu
            
            //FERMETURE DU FLUX DE SORTIE
            out.close();
            
        }catch (SQLException ex) {
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