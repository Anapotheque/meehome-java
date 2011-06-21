package Ajax.TRAVAUX.TRAVAUX.AJOUT;

import Models.ETUDES.Affaires.AffairesDAO;
import Models.ETUDES.Agents.AgentsDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.ButtonDAO;
import Models.TOOLS.Ajaxmodels.GetMessageInformationDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.InputDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.SelectDAO;
import Models.TOOLS.Controleur.MegaControleur;
import Models.TOOLS.Date.DataDate;
import Models.TRAVAUX.CommandesDAO;
import Models.TRAVAUX.TravauxDAO;
import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class GetImputation extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        try {
            
            //RECUPERATION DES OBJETS
            AffairesDAO affaire = null;
            affaire= creerObjet(request, affaire);
            
            String codeAffaire = request.getParameter("codeAffaire");
            
            //INITIALISATION DU FLUX DE COMMUNICATION AVEC LA PAGE JSP
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>Imputation : ");
            
            if(!affaire.GetImputationAffaire(codeAffaire).equals("")){
                InputDAO.AddInput(out,"radio","imputation","20px",affaire.GetImputationAffaire(codeAffaire),"checked");
                GetMessageInformationDAO.getMessageInformation(out,"TexteInformation1",""+affaire.GetImputationAffaire(codeAffaire));
            }
            
            if(!affaire.GetCompteEtude(codeAffaire).equals("")){
                if(affaire.GetImputationAffaire(codeAffaire).equals(""))
                    InputDAO.AddInput(out,"radio","imputation","20px",affaire.GetCompteEtude(codeAffaire),"checked");
                else
                    InputDAO.AddInput(out,"radio","imputation","20px",affaire.GetCompteEtude(codeAffaire));
                GetMessageInformationDAO.getMessageInformation(out,"TexteInformation1",""+affaire.GetCompteEtude(codeAffaire));
            }
            
            if(affaire.GetImputationAffaire(codeAffaire).equals("") && affaire.GetCompteEtude(codeAffaire).equals("")){
                GetMessageInformationDAO.getMessageInformation(out,"TexteInformation1","Pas de compte d'imputation pour cette affaire");
            }
            
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
