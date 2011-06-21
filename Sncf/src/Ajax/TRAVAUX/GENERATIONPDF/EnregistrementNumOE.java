package Ajax.TRAVAUX.GENERATIONPDF;

import Models.TOOLS.Ajaxmodels.GetMessageInformationDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.ButtonDAO;
import Models.TOOLS.Controleur.MegaControleur;
import Models.TOOLS.Utilisateurs.UtilisateursDAO;
import Models.TOOLS.iText.TRAVAUX.GenerationPDFTRAVAUX;
import Models.TRAVAUX.TravauxDAO;
import com.lowagie.text.DocumentException;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class EnregistrementNumOE extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        try {
            
            //RECUPERATION DES OBJETS
            TravauxDAO travaux = null;
            travaux = creerObjet(request, travaux);
            
            UtilisateursDAO utilisateur = null;
            utilisateur = creerObjet(request, utilisateur);
            
            ArrayList list = new ArrayList();
            
            list.add(request.getParameter("numOE"));
            list.add(request.getParameter("numeroCommande"));
            
            list = TransformString(list);
            travaux.numOE = ""+list.get(0);
            
            travaux.AttributionNumOE(list);
            
            //INITIALISATION DU FLUX DE COMMUNICATION AVEC LA PAGE JSP
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            if(travaux.SetPDF(""+list.get(0))){
                GenerationPDFTRAVAUX.genererPDF(request,""+list.get(1),""+list.get(0));
                GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>Le numéro d'OE a été attribué<br>Le rapport pour la commande a été généré<br><br>");
                ButtonDAO.AddButtonStyle(out,1,""+cheminOuverturePDF+utilisateur.NamePDF+"_Travaux.pdf target='_blank'","Voir le PDF");
                
            }else
                GetMessageInformationDAO.getMessageInformation(out,"TexteInformation3","<br><br>IL N'Y A PAS FICHE DE TRAVAIL RECENSEE POUR LA CREATION DE LA FACTURE<br><br>");
            ButtonDAO.AddButtonStyle(out,1,"javascript:Ajax('data','AttributionNumOE')","Retour");
            
            //FERMETURE DU FLUX DE SORTIE
            out.close();
            
        } catch (SQLException ex) {
            retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState());
            System.out.println("\n\nDEBUT ERREUR<==========================================\n");
            ex.printStackTrace();
            System.out.println("\nFIN ERREUR<==============================================\n\n");
        } catch (DocumentException ex) {
            ex.printStackTrace();
        } catch (ServletException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
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
