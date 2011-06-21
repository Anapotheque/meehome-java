package Ajax.CALQUES.SELECTION.SORTIE;

import Models.CALQUES.CalquesDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.ButtonDAO;
import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Controleur.MegaControleur;
import Models.TOOLS.Utilisateurs.UtilisateursDAO;
import Models.TOOLS.iText.CALQUES.AccuseCalque;
import Models.TOOLS.iText.CALQUES.GenererCalques;
import Models.TOOLS.iText.CALQUES.GenererFichedeSortieCalques;
import com.lowagie.text.DocumentException;
import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class SortirCalquesActions extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        try {
                        
            //RECUPERATION DES OBJETS
            CalquesDAO calque = null;
            calque = creerObjet(request,calque);
            
            UtilisateursDAO utilisateur = null;
            utilisateur = creerObjet(request,utilisateur);
            
            //SI ON NE VIENT PAS DE MODIFIER LE CODE ENTREPRISE OU LE CODE AGENT            
            if(request.getParameter("Retour") == null){
            //GET DATA ON RECUPERE LE CODECOLLECTION
            String codeCalques = request.getParameter("codeCalques");
            calque.codeAgent = request.getParameter("codeAgent");
            calque.codeEntreprise = request.getParameter("codeEntreprise");
            
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
            calque.listCodeCalqueSortie = list_Code;
            }
            
            //CREATION DU FICHIER PDF
            GenererCalques.genererPDF(request,calque.listCodeCalqueSortie);
            GenererFichedeSortieCalques.genererPDF(request,calque.listCodeCalqueSortie);
            AccuseCalque.genererPDF(request);
            
            calque.generatePDF = true;
            
            //INITIALISATION DU FLUX DE COMMUNICATION AVEC LA PAGE JSP
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            //MENU POUR VALIDATION DE L'IMPRESSION
            TOOLS_HTML.getMessage(out,"h3","JE VOUS RAPPELE QUE TOUTES LES MODIFICATIONS DE DONNEES APRES LA CREATION DES PDF N'EST PAS PRISE EN COMPTE !");
            
            TOOLS_HTML.getMessage(out,"h2","VOS FICHIER PDF ONT ETE CREES, VEUILLEZ LES CONSULTER.<br>( Si l'imprimante est en panne vous avez la possibilité d'enregistrer votre travail. )");
            
            TOOLS_HTML.OpenTable(out,"30%");
            out.println("<tr><td>");
            ButtonDAO.AddButtonStyle(out,1,""+cheminOuverturePDF+utilisateur.NamePDF+"_CourrierCalques.pdf target='_blank'","Courrier");
            out.println("</td><td>");
            ButtonDAO.AddButtonStyle(out,1,""+cheminOuverturePDF+utilisateur.NamePDF+"_FicheCalques.pdf target='_blank'","Fiche Calque");
            out.println("</td><td>");
            ButtonDAO.AddButtonStyle(out,1,""+cheminOuverturePDF+utilisateur.NamePDF+"_AccuseCalques.pdf target='_blank'","Accusé");
            out.println("</td></tr></table>");
            
            TOOLS_HTML.getMessage(out,"h2","LA CREATION DES PDF S'EST ELLE BIEN PASSEE ?<br>(Veuillez effectuer une sauvegarde ou une impression des ces fichiers)");
            
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCalques('Enregistrement','EnregistrementSortie','null','Champ')","OUI");
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCalques('calques','GetCalques','null','Champ')","NON");
            
            
            //FERMETURE DU FLUX DE SORTIE
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
