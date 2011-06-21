package Ajax.TOOLS.Connection;

import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Controleur.MegaControleur;
import Models.TOOLS.Utilisateurs.UtilisateursDAO;
import java.io.*;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.*;
import javax.servlet.http.*;

//@author RABALLAND Romain
//@version v3.0

public class connection extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        
        
        //GET DATA
        String mode_connect = request.getParameter("mode_connect");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        
        //INITIALISATION DES OBJETS
        UtilisateursDAO utilisateur = null;
        utilisateur= creerObjet(request, utilisateur);
        
        //TEST DU LOGIN ET PASSWORD
        if(!mode_connect.equals("Null"))
            try {
                utilisateur.ControleIdentity(login, password);
            } catch (SQLException ex) {
                retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState());
                System.out.println("\n\nDEBUT ERREUR<==========================================\n");
                ex.printStackTrace();
                System.out.println("\nFIN ERREUR<==============================================\n\n");
            }
        
        //DEFINITION DU TEMPS DE LA SESSION 15 MIN (900s)
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(36000);
        int time = session.getMaxInactiveInterval();
        time = time / 60;
        
        Mouchard(request, utilisateur,"Connection","");
        
        //INITIALISATION DU FLUX DE COMMUNICATION AVEC LA PAGE JSP
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        //CONNECTION AUTORISEE
        if(mode_connect.equals("connection") && (utilisateur.TestUtilisateur()) || utilisateur.TestModerateur() || utilisateur.TestAdministrateur()){
            
            //CREATION DU COOKIE DE CONNECTION
            javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie(login,password);
            response.addCookie(cookie);
            
            //OUVERTURE DIV DE MENU
            TOOLS_HTML.OpenDiv(out,"menu");
            
            //MENU DECONNEXION
            TOOLS_HTML.OpenDL(out);
            out.println("<dt onmouseover=javascript:montre()><a href=javascript:Ajax('listInfos','Deconnection')>Deconnexion</a></dt>");
            TOOLS_HTML.CloseDL(out);
            //FIN DECONNEXION
            
            //MENU APPLICATIONS
            TOOLS_HTML.OpenDL(out);
            out.println("<dt onmouseover=javascript:montre('smenu1')>Applications</dt>");
            out.println("<dd id='smenu1' onmouseover=javascript:montre('smenu1') onmouseout=javascript:montre('')>");
            TOOLS_HTML.OpenUL(out);
            out.println("<li><a href=javascript:getHTMLCodeRequest('informations','IndexDVSG','','null')>Etudes</a></li>");
            out.println("<li><a href=javascript:getHTMLCodeRequest('informations','Accueil_COURRIER','','null')>Courrier</a></li>");
            out.println("<li><a href=javascript:getHTMLCodeRequest('informations','IndexCalques','l','null')>Calques</a></li>");
            out.println("<li><a href=javascript:getHTMLCodeRequest('informations','AccueilTravaux','','null')>Travaux</a></li>");
            TOOLS_HTML.CloseUL(out);
            TOOLS_HTML.CloseDD(out);
            TOOLS_HTML.CloseDL(out);
            //FIN APPLICATIONS
            
            //MENU OUTILS
            TOOLS_HTML.OpenDL(out);
            out.println("<dt onmouseover=javascript:montre('smenu2')>Outils</dt>");
            out.println("<dd id='smenu2' onmouseover=javascript:montre('smenu2') onmouseout=javascript:montre('')>");
            TOOLS_HTML.OpenUL(out);
            out.println("<li><a href='http://10.152.179.108:8001/forum/' target='_blank'>Forum</a></li>");
            
            //AUTORISE SI ADMINISTRATEUR
            if(utilisateur.TestAdministrateur())
                out.println("<li><a href=javascript:getHTMLCodeRequest('informations','ShowUtilisateurs','choix_menu=null','null')>Utilisateurs</a></li>");
            
            //AUTORISE SI ADMINISTRATEUR
            if(utilisateur.TestAdministrateur())
                out.println("<li><a href='http://10.152.179.108:8001/mysql/' target='_blank'>MySQL</a></li>");
            
            //AUTORISE SI ADMINISTRATEUR
            if(utilisateur.TestAdministrateur())
                out.println("<li><a href='http://10.152.179.108:8001/home/' target='_blank'>Apache</a></li>");
            
            //AUTORISE SI ADMINISTRATEUR
            if(utilisateur.TestAdministrateur())
                out.println("<li><a href='http://10.152.179.108:8080/admin/' target='_blank'>Tomcat</a></li>");
            
            //AUTORISE SI ADMINISTRATEUR
            if(utilisateur.TestAdministrateur() || utilisateur.TestBonus())
                out.println("<li><a href=javascript:getHTMLCodeRequest('informations','IndexJeux')>Jeux</a></li>");
            
            TOOLS_HTML.CloseUL(out);
            TOOLS_HTML.CloseDD(out);
            TOOLS_HTML.CloseDL(out);
            //FIN OUTILS
            
            //MENU INFORMATIONS
            TOOLS_HTML.OpenDL(out);
            out.println("<dt onmouseover=javascript:montre('smenu3')>Informations</dt>");
            out.println("<dd id='smenu3' onmouseover=javascript:montre('smenu3') onmouseout=javascript:montre('')>");
            TOOLS_HTML.OpenUL(out);
            out.println("<li><a href='javascript:tpsConnection();'>session "+time+" min</a></li>");
            out.println("<li><a href='javascript:contact();'>Contacts</a></li>");
            out.println("<li><a href='#'>Version v3.0</a></li>");
            TOOLS_HTML.CloseUL(out);
            TOOLS_HTML.CloseDD(out);
            TOOLS_HTML.CloseDL(out);
            //MENU INFORMATIONS
            
            TOOLS_HTML.CloseDiv(out);
            //FERMETURE DIV DE MENU
            
            
            out.println("<div id=informations>");
            /*
            TOOLS_HTML.OpenTable(out,"100%");
            out.println("<th>");
            out.println("<div id=icone_dvsg ondblclick=javascript:getHTMLCodeRequest('informations','IndexDVSG',null,'null')><h2>BASE ETUDE</h2></div>");
            out.println("</th><th>");
            out.println("<div id=icone_travaux ondblclick=javascript:getHTMLCodeRequest('informations','AccueilTravaux',null,'null')><h2>BASE TRAVAUX</h2></div>");
            out.println("</th><th>");
            out.println("<div id=icone_courrier ondblclick=javascript:getHTMLCodeRequest('informations','IndexCourrier',null,'null')><h2>BASE COURRIER</h2></div>");
            out.println("</th><th>");
            out.println("<div id=icone_courrier_enregistrement ondblclick=javascript:getHTMLCodeRequest('informations','CourrierDepart',null,'null')><h2>BASE ENREGISTREMENT COURRIER</h2></div>");
            out.println("</th><th>");
            out.println("<div id=icone_calques ondblclick=javascript:getHTMLCodeRequest('informations','IndexCalques',null,'null')><h2>BASE CALQUE</h2></div>");
            out.println("</th></table>");
             */
            out.println("</div>");
            
            
        }
        
        //CONNEXION REFUSEE
        else if(utilisateur.TestIntru()){
            
            out.println("<div id='ConnectionWindows'>");
            out.println("<div id='head'>connexion</div>");
            
            out.println("<form id='champ'><br>");
            out.println("<h2>LOGIN</h2><input type='text' id='login' name='login' value='"+login+"'>");
            out.println("<h2>PASSWORD</h2><input type='password' id='password' name='password' value='"+password+"'>");
            out.println("</form>");
            
            out.println("<div id='bouton1'>");
            out.println("<ul>");
            out.println("<li>");
            out.println("<a href=javascript:getHTMLCodeRequest('listInfos','connection','mode_connect=connection','champ')>Connexion</a>");
            out.println("<a href=javascript:getHTMLCodeRequest('listInfos','InscriptionUtilisateurs','choix_menu=null','null')>Inscription</a>");
            out.println("</li>");
            out.println("</ul>");
            out.println("</div>");
            
            out.println("<h3>IDENTIFIANT OU MOT DE PASSE INCORRECT<br>Votre compte à peut etre été temporairement desactivé.<br>Contactez votre administrateur pour plus d'informations</h3>");
            out.println("</div>");
        }
        
        
        
        out.close();
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
