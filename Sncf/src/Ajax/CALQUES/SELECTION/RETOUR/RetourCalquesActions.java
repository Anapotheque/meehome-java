package Ajax.CALQUES.SELECTION.RETOUR;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Ajaxmodels.OBJETS.ButtonDAO;
import Models.TOOLS.Controleur.MegaControleur;
import Models.TOOLS.Utilisateurs.UtilisateursDAO;
import Models.TOOLS.iText.CALQUES.GenererFichedeRetourCalques;

import com.lowagie.text.DocumentException;

//RABALLAND Romain v3.0

public class RetourCalquesActions extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        try {
            
            //RECUPERATION DES OBJETS
            UtilisateursDAO utilisateur = null;
            utilisateur = creerObjet(request,utilisateur);
            
            //GET DATA ON RECUPERE LE CODECOLLECTION
            String codeCalques = request.getParameter("codeCalques");
            
            //INITIALISATION DU ARRAYLIST CHARGE DE RECUPERER LES CODE CALQUES
            ArrayList list_Code;
            list_Code = new ArrayList();
            
            //FONCTION PERMETTANT DE DECHIFFRER LA LIGNE DE COMMANDE VENANT DU JS
            for(int i = 1 ; i < codeCalques.length(); i++){
                String detect = "";
                String code = "";
                while(!detect.equals("@")){
                    detect = ""+codeCalques.charAt(i+1);
                    code += codeCalques.charAt(i);
                    i++;
                }
                list_Code.add(code);
            }
            
            //INITIALISATION DU FLUX DE COMMUNICATION AVEC LA PAGE JSP
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            //CREATION DU FICHIER PDF
            GenererFichedeRetourCalques.genererPDF(request,list_Code);
            
            //MENU POUR VALIDATION DE L'IMPRESSION
            TOOLS_HTML.getMessage(out,"h2","LA CREATION DU PDF S'EST ELLE BIEN PASSEE ?<br>Veuillez effectuer une sauvegarde ou une impression de ce fichier");
            
            TOOLS_HTML.OpenTable(out,"30%");
            out.println("<tr><td>");
            ButtonDAO.AddButtonStyle(out,1,""+cheminOuverturePDF+utilisateur.NamePDF+"_FicheCalquesRetour.pdf target='_blank'","Fiche Calque");
            out.println("</td></tr></table>");
            
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCalques('Enregistrement','EnregistrementRetour','null','Champ')","OUI");
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCalques('calques','GetRetourCalques','null','Champ')","NON");
            
            //FERMETURE DU FLUX DE SORTIE
            out.close();
            
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
