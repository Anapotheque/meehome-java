package Ajax.TOOLS.Erreur;

import Models.TOOLS.Ajaxmodels.OBJETS.ButtonDAO;
import Models.TOOLS.Controleur.MegaControleur;
import Models.TOOLS.Utilisateurs.UtilisateursDAO;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

//@author RABALLAND Romain v3.0

public class Erreur extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //RECUPERATION OBJET
        UtilisateursDAO utilisateur = null;
        utilisateur = creerObjet(request, utilisateur);
        
        //GET DATA
        String message = request.getParameter("message");
        String codeErreur = request.getParameter("code");
        String etat = request.getParameter("etat");
        
        //POUR SAVOIR VERS ON RETOURNE LA PAGE
        String retour = request.getParameter("retour");
        
        //POUR RETROUNER LES INFORMATION VERS LA PAGE DE MODIFICATION AVEC SAUVEGARDE DU CODE
        String nomCode = request.getParameter("nomCode");
        String code = request.getParameter("codeRenvoie");
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<br><br><br><br><div id='warning'><br><br><h3>ATTENTION ERREUR !</h3><br>");
        
        if(etat.equals("01004")){
            out.println("<br><br><h2>PROBLEME AVEC L'AJOUT, LA MODIFICATION OU LA SUPPRESSION DES DONNEES</h2>");
            out.println("<h2>VEUILLEZ SIGNALER LE PROBLEME A L'ADMINISTRATEUR</h2>");
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCourrier('informations','"+retour+"','choix_menu=Null','null')","RETOUR");
        }
        
        if(codeErreur.equals("0") && !etat.equals("01004")){
            out.println("<br><br><h2>VOUS N'AVEZ AUCUNE CONNECTION AVAEC LA BASE DE DONNEES MYSQL</h2>");
            out.println("<h2>VEUILLEZ DEMARER LE SERVEUR MYSQL</h2>");
            out.println("<br><br><h2>SI VOTRE SERVEUR EST DEJA DEMARER VEUILLEZ SUIVRE CES OPERATIONS ( ADMINISTRATEUR UNIQUEMENT )</h2>");
            out.println("<h3>ETAPE 1 : ETEINDRE LE SERVEUR MYSQL</h5>");
            out.println("<h3>ETAPE 2 : ETEINDRE LE SERVEUR TOMCAT</h5>");
            out.println("<h3>ETAPE 3 : DEMARER LE SERVEUR TOMCAT</h5>");
            out.println("<h3>ETAPE 4 : DEMARER LE SERVEUR MYSQL</h5>");
            out.println("<br><br><h2>SI VOTRE APPLICATION NE DETECTE TOUJOURS PAS DE CONNECTION MYSQL, VEUILLEZ VERIFIER LA PRESENCE DES PILOTES MYSQL DANS LE SERVEUR TOMCAT</h2>");
            out.println("<br><h3>Chemin d'acces : D:\\SERVEUR\\Tomcat 5.5\\common\\lib\\</h5>");
            out.println("<br><br><h2>UNE COPIE DU PILOTE SE TROUVE DANS LE REPERTOIRE : </h2>");
            out.println("<br><h3>D:\\SAUVEGARDE SERVEUR\\LIBRAIRIES\\mysql-connector-java-5.0.0-beta-bin.jar</h5>");
            out.println("<br><br><h2>SI PROBLEME, CONTACTER ROMAIN : <br>raballand.romain@gmail.com<br>06.63.96.89.55</h2>");
        }
        
        if(codeErreur.equals("1062")){
            out.println("<br><br><h2>LA NOUVELLE DONNEE QUE VOUS ESSAYEZ D'ENREGISTRER EST DEJA PRESENTE DANS LA BASE</h2>");
            if(nomCode == null)
                ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCourrier('informations','"+retour+"','choix_menu=Null','null')","RETOUR");
            else
                ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestCourrier('informations','"+retour+"','choix_menu=null&"+nomCode+"="+code+"','null')","RETOUR");
        }
        
        if(utilisateur.TestAdministrateur()){
            out.println("<h6>Code Erreur : "+codeErreur+"<br>");
            out.println("Etat : "+etat+"<br>");
            out.println("Cause : "+message+"</h6></div>");
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
