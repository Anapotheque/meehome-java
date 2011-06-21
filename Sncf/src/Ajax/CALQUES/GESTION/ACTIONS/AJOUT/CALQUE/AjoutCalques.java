package Ajax.CALQUES.GESTION.ACTIONS.AJOUT.CALQUE;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.CALQUES.CasesDAO;
import Models.TOOLS.Ajaxmodels.GetMessageInformationDAO;
import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Ajaxmodels.OBJETS.ButtonDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.InputDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.RadioDAO;
import Models.TOOLS.Controleur.MegaControleur;

//RABALLAND Romain v3.0

public class AjoutCalques extends MegaControleur {
    
    //VARIABLE D'INCREMENTATION POUR L'ENREGISTREMENT DES CALQUES
    int reload = 0;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        try {
            
            //ON INCREMENTE NOTRE VARIABLE DE 1 POUR L'ENREGISTREMENT RAPIDE'
            reload++;
            
            //RECUPERATION DES OBJETS
            CasesDAO cases = null;
            cases = creerObjet(request,cases);
            
            //GET DATA POUR MEMORISER LE TYPE D'ENTETE ET LA REMISE A UN DU COMPTEUR
            String entete = request.getParameter("entete");
            String reset = request.getParameter("Reset");
            
            if(reset != null){
            reload = 1;
            }
            
            //INITIALISATION DU FLUX DE COMMUNICATION AVEC LA PAGE JSP
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            //LIGNE ROUGE
            out.println("<div id=ajout>");
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation1",".: SNCF :: CALQUES :: GESTION :: AJOUT :.<br>");
            out.println("</div>");  //FIN div align left
            
            //AFFICHAGE DE l'ENTETE DU MENU AVEC LE NUMERO DE CASE OU L'ON VA INCREMENTER DES CALQUES
            out.println("<div Style='text-align: left'>");
            TOOLS_HTML.getMessage(out,"h2","AJOUT D'UN NOUVEAU CALQUE DANS LA CASE \" <span style='color: red'>"+cases.GetNumeroCases(cases.codeCase)+"</span> \"");
            out.println("</div>");  //FIN
            
            //OUVERTURE DES DIVS GRAPHIQUES
            out.println("<div id=calques>");
            out.println("<div id=DivSaisie>");
            
            //DATA
            TOOLS_HTML.OpenTable(out,"100%");
            out.println("<th>");
            TOOLS_HTML.getMessage(out,"h2","ENTETE DU NOM");
            out.println("</th><th>");
            TOOLS_HTML.getMessage(out,"h2","NUMERO DU CALQUE");
            out.println("</th>");
            
            out.println("<tr><td>");
            
            if(entete != null){
                
                if(entete.equals("Execution"))  RadioDAO.AddInputRadio(out,"option","Execution",true);
                else                            RadioDAO.AddInputRadio(out,"option","Execution",false);
                out.println("<span style='font: bold'>Schéma d'exécution ( Adjonction automatique du \" E \" )<br>");
                
                if(entete.equals("Plan"))  RadioDAO.AddInputRadio(out,"option","Plan",true);
                else                       RadioDAO.AddInputRadio(out,"option","Plan",false);
                out.println("Schéma de principe ( Adjonction automatique du \" P \" )<br>");
                
                if(entete.equals("Libre"))  RadioDAO.AddInputRadio(out,"option","Libre",true);
                else                        RadioDAO.AddInputRadio(out,"option","Libre",false);
                out.println("Saisie libre ( Pas d'adjonction automatique )<br></span>");
                
            }else{
                RadioDAO.AddInputRadio(out,"option","Execution",false);
                out.println("<span style='font: bold'>Schéma d'exécution ( Adjonction automatique du \" E \" )<br>");
                RadioDAO.AddInputRadio(out,"option","Plan",false);
                out.println("Schéma de principe ( Adjonction automatique du \" P \" )<br>");
                RadioDAO.AddInputRadio(out,"option","Libre",false);
                out.println("Saisie libre ( Pas d'adjonction automatique )<br></span>");
            }
            
            out.println("</td><td>");
            
            InputDAO.AddInput(out,"text","numeroCalque","145px",""+reload);
            
            out.println("</td></tr><tr><td colspan = 3>");
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation1","<br>Vous pouvez incrementer les calques en tapant sur la commande \" Alt 'A' \" puis  \" ENTRER \" autant de fois que vous voulez incrementer");
            out.println("</tr></table>");
            
            out.println("</div>");  //FIN Divcontenu
            out.println("</div>");  //FIN Divcontenu
            
            //AFFICHAGE DU MENU DE GESTION
            out.println("<div Style='text-align: left'>");
            TOOLS_HTML.OpenTable(out,"20%");
            out.println("<tr><td>");
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCalques('calques','GetCalquesGestions','SauvegardeCodeCalque_OR_Case=YES','Null')","RETOUR");
            out.println("</td><td>");
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCalques('Enregistrement','EnregistrerAjout','choix_menu=Null','Champ') accesskey='a'","ENREGISTRER");
            out.println("</td><td>");
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCalques('Enregistrement','EnregistrerAjout','Reset=Reset','Champ') ","RESET");
            out.println("</td></tr></table>");
            out.println("</div>");  //FIN
            
            
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
