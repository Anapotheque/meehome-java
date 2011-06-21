/*
 * AccueilPaye.java
 *
 * Created on 20 mars 2007, 09:28
 */

package fr.alliance.docalliance.ajax.mail.paye;

import fr.alliance.docalliance.ajax.mail.tools.List_PDF;
import fr.alliance.docalliance.controleur.MetaControleur;
import fr.alliance.docalliance.tools.html.Data.GestionData;
import fr.alliance.docalliance.tools.html.Input.GestionInput;
import fr.alliance.docalliance.tools.html.Scripts.GestionScripts;
import fr.alliance.docalliance.tools.html.css.GestionCss;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Accueil de la gestion d'envoi des feuilles de paye
 * @author Yves Le Rumeur, Romain Raballand
 * @version 1.0
 */

public class AccueilPaye extends MetaControleur {
    
    private static final String TITRE1 = "Feuille de paye";
    private static final String TITRE2 = "Chargement feuille de paye PDF";
    private static final long serialVersionUID = 1L;
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //Initialisation du flux de sortie
        PrintWriter out = response.getWriter();
        
        //Declaration du titre
        GestionData title1 = new GestionData("titre");
        title1.add(TITRE1);
        title1.close();
        
        //Declaration du titre de chargement pdf
        GestionData title2 = new GestionData("titre");
        title2.add(TITRE2);
        title2.close();
        
        //Creation du input parcourir
        GestionInput file = new GestionInput();
        file.addtype("file");
        file.addId("file");
        file.close();
        
        // Creation du bouton envoyer
        GestionInput upload = new GestionInput();
        upload.addtype("submit");
        upload.addId("upload");
        upload.addValue("Upload");
        upload.close();
        
        //Declaration d'un button pour le rafraichissement
        GestionInput rafraichir = new GestionInput();
        rafraichir.addtype("button");
        rafraichir.addOnclick("javascript:Ajax('Accueil','AccueilPaye','null')");
        rafraichir.addValue("Afficher la liste de pdf");
        rafraichir.close();
        
        //Ecriture du flux
        
        out.print("<form action='UploadPayePDF' enctype='multipart/form-data'  method='post' target='returnForm'>");
        out.print(title2.getFLUX());
        out.print(file.getFLUX());
        out.print(upload.getFLUX());
        out.print(rafraichir.getFLUX());
        out.print("<br><iframe name='returnForm'>");
        out.print("</iframe>");
        out.print("</form>");
        
        out.print("<form id='champ'>");
        out.print(title1.getFLUX());
        out.print("<div id='AJAXMAINFRAME'>");
        //Affichage de tout les pdfs disponibles
        List_PDF.GetList(request,response, "PAYE");
        out.print("</form>");
        out.print("</div>");
        
        //Fermeture du flux de sortie
        out.flush();
        out.close();
    }
    
}
