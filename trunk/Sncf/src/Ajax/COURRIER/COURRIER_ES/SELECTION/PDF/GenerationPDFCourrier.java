package Ajax.COURRIER.COURRIER_ES.SELECTION.PDF;

import Models.COURRIER.COURRIER_ES.CourriersDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.ButtonDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.SelectDAO;
import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Controleur.MegaControleur;
import Models.TOOLS.Utilisateurs.UtilisateursDAO;
import Models.TOOLS.iText.COURRIER.GenererCourrier;
import com.lowagie.text.DocumentException;
import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class GenerationPDFCourrier extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            UtilisateursDAO utilisateur = null;
            utilisateur = creerObjet(request, utilisateur);
            
            CourriersDAO courrier = null;
            courrier = creerObjet(request, courrier);
            
            GenererCourrier.genererPDF(request);
            
            TOOLS_HTML.getMessage(out,"h2","Votre fichier PDF à été créé");
            
            TOOLS_HTML.OpenTable(out,"30%");
            out.println("<tr><td>");
            ButtonDAO.AddButtonStyle(out,1,""+cheminOuverturePDF+utilisateur.NamePDF+"_Courrier.pdf target='_blank'","Voir le PDF");
            out.println("</td>");
            if(!courrier.numeroSesco.equals("")){
                out.println("<td>");
                ButtonDAO.AddButtonStyle(out,1,""+cheminOuverturePDF+utilisateur.NamePDF+"_Suivi.pdf target='_blank'","Voir le Suivi");
                out.println("</td>");
            }
            if(!courrier.copie.equals("")){
                out.println("<td>");
                ButtonDAO.AddButtonStyle(out,1,""+cheminOuverturePDF+utilisateur.NamePDF+"_AccuseReception.pdf target='_blank'","Voir l'accusé");
                out.println("</td>");
            }
            if(!courrier.dateIndice.equals("")){
                out.println("<td>");
                ButtonDAO.AddButtonStyle(out,1,""+cheminOuverturePDF+utilisateur.NamePDF+"_Copie.pdf target='_blank'","Voir les Copies");
                out.println("</td>");
            }
            out.println("</tr>");
            out.println("<td>");
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCourrier('Save','GenererCourrier','Null')","IMPRIMER");
            out.println("</td><td>");
            ArrayList list_Exemplaire = null;
            list_Exemplaire = new ArrayList();
            
            for(int i = 1; i <= 100; i++)
                list_Exemplaire.add(""+i);
            
            SelectDAO.GetSelect(out,"Exemplaire","3","3",list_Exemplaire,list_Exemplaire,1,"onChange=javascript:getHTMLCodeRequestCourrier('infoExemplaire','EnregistrementExemplaire','choix_menu=Null','Champ')");
            
            out.println("</td></tr></table>");
            
            out.println("<div id=infoExemplaire>");
            out.println("</div>");
            
            out.close();
            
        } catch (ServletException ex) {
            ex.printStackTrace();
        } catch (DocumentException ex) {
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
