/*
 * List_PDF.java
 *
 * Created on 20 mars 2007, 09:28
 */

package fr.alliance.docalliance.ajax.mail;

import fr.alliance.docalliance.controleur.MetaControleur;
import fr.alliance.docalliance.tools.html.applet.GestionApplet;
import fr.alliance.docalliance.tools.html.applet.Parametre;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet d'appel d'applet pour la selection de fichier multiple
 * @author Yves Le Rumeur, Romain Raballand
 * @version 1.0
 */

public class RecuperationPDF extends MetaControleur {
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //Initialisation du flux de sortie
        PrintWriter out = response.getWriter();
        
        //Recuperation des données formulaire
        String typeFile = request.getParameter("typeFile");
        
        //JUSTE POUR RIRE A LIRE TRES VITE )))
        //Chargement de l'applet qui se chargera d'appeller l'ajax chargeant le code de chargement de l'applet
        GestionApplet gestionApplet = new GestionApplet("JExplorateur","fr.alliance.docalliance.applet.AppletExplorer.class","SDocAllianceApplet.jar","50","50");
        
        //Ajout de nouveaux parametres
        gestionApplet.add(new Parametre("TypeFile",typeFile));
        gestionApplet.add(new Parametre("funcJScript","Ajax"));
        gestionApplet.add(new Parametre("servlet","UploadPDF"));
        gestionApplet.add(new Parametre("idDiv","AJAXAPPLET"));
        gestionApplet.add(new Parametre("port",""+request.getServerPort()));
        gestionApplet.add(new Parametre("host",""+request.getServerName()));
        gestionApplet.add(new Parametre("nomRetour","retour"));
        
        //Fermture de l'applet
        gestionApplet.close();
        
        //Ecriture du flux de sortie
        out.print(gestionApplet.getFLUX());
        
        //Fermeture du flux de sortie
        out.flush();
        out.close();
    }
    
}
