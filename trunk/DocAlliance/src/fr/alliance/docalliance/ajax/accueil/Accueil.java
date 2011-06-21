/*
 * Accueil.java
 *
 * Created on 20 mars 2007, 09:28
 */

package fr.alliance.docalliance.ajax.accueil;

import fr.alliance.docalliance.tools.html.Image.GestionImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.alliance.docalliance.controleur.MetaControleur;
import fr.alliance.docalliance.tools.enumeration.SearchAdresseMail;
import fr.alliance.docalliance.tools.html.menu.GestionMenu;
import fr.alliance.docalliance.tools.html.menu.Menu;
import fr.alliance.docalliance.tools.html.menu.SMenu;

/**
 * Servlet d'accueil : Affichage du menu apres identification
 * @author Yves Le Rumeur, Romain Raballand
 * @version 1.0
 */

public class Accueil extends MetaControleur {
    
    //Serialisation de la servlet
    private static final long serialVersionUID = 1L;
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //Initialisation du flux de sortie
        PrintWriter out = response.getWriter();
        
        //Declaration des images liées au menu
        GestionImage imageDeconnection = new GestionImage("./img/menu-deroulant/Deconnection.gif","150","40");
        imageDeconnection.addAlt("Deconnection");
        imageDeconnection.close();
        
        GestionImage imageContacts = new GestionImage("./img/menu-deroulant/Contacts.gif","150","40");
        imageContacts.addAlt("Contacts");
        imageContacts.close();
        
        GestionImage imageMail = new GestionImage("./img/menu-deroulant/Mail.gif","150","40");
        imageMail.addAlt("Mail");
        imageMail.close();
        
        GestionImage imageHistorique = new GestionImage("./img/menu-deroulant/Historique.gif","150","40");
        imageHistorique.addAlt("Historique");
        imageHistorique.close();
        
        GestionImage imageFeuillePaye = new GestionImage("./img/menu-deroulant/Feuillepaye.gif","150","40");
        imageFeuillePaye.addAlt("Feuille de paye");
        imageFeuillePaye.close();
        
        GestionImage imageNewsLetter = new GestionImage("./img/menu-deroulant/NewsLetter.gif","150","40");
        imageNewsLetter.addAlt("NewsLetter");
        imageNewsLetter.close();
        
        GestionImage imageNouveauContact = new GestionImage("./img/menu-deroulant/NouveauContact.gif","150","40");
        imageNouveauContact.addAlt("NouveauContact");
        imageNouveauContact.close();
        
        GestionImage imageRecherche = new GestionImage("./img/menu-deroulant/Rechercher.gif","150","40");
        imageRecherche.addAlt("Rechercher");
        imageRecherche.close();
        
        GestionImage imageAfficher = new GestionImage("./img/menu-deroulant/Afficher.gif","150","40");
        imageAfficher.addAlt("Afficher");
        imageAfficher.close();
        
        GestionImage imageParametres = new GestionImage("./img/menu-deroulant/Parametres.gif","150","40");
        imageParametres.addAlt("Parametres");
        imageParametres.close();
        
        GestionImage imageAdministration = new GestionImage("./img/menu-deroulant/Administration.gif","150","40");
        imageAdministration.addAlt("Users");
        imageAdministration.close();
        
        GestionImage imageNewUser = new GestionImage("./img/menu-deroulant/NewUser.gif","150","40");
        imageNewUser.addAlt("New user");
        imageNewUser.close();
        
        //Gestion du menu général
        GestionMenu gestionMenu = new GestionMenu();
        
        gestionMenu.add(new Menu(imageParametres.getFLUX(),"javascript:Ajax('Accueil','Parametres','null')"));
        
        ArrayList<SMenu> list_smenu = new ArrayList<SMenu>();
        list_smenu.add(new SMenu(imageNewUser.getFLUX(),"javascript:Ajax('Accueil','NewUsers','null')"));
        list_smenu.add(new SMenu(imageRecherche.getFLUX(),"javascript:Ajax('Accueil','SearchUsers','null')"));
        list_smenu.add(new SMenu(imageAfficher.getFLUX(),"javascript:Ajax('Accueil','ShowUsers','null','idfilter=0')"));

        gestionMenu.add(new Menu(imageAdministration.getFLUX(),list_smenu));
        
        list_smenu = new ArrayList<SMenu>();
        list_smenu.add(new SMenu(imageNouveauContact.getFLUX(),"javascript:Ajax('Accueil','New','null')"));
        list_smenu.add(new SMenu(imageRecherche.getFLUX(),"javascript:Ajax('Accueil','Search','null')"));
        list_smenu.add(new SMenu(imageAfficher.getFLUX(),"javascript:Ajax('Accueil','Show','null','idfilter="+SearchAdresseMail.ALL+"')"));
        gestionMenu.add(new Menu(imageContacts.getFLUX(),list_smenu));
        
        list_smenu = new ArrayList<SMenu>();
        list_smenu.add(new SMenu(imageFeuillePaye.getFLUX(),"javascript:Ajax('Accueil','AccueilPaye','null')"));
        list_smenu.add(new SMenu(imageNewsLetter.getFLUX(),"javascript:Ajax('Accueil','AccueilNewsLetter','null')"));
        list_smenu.add(new SMenu(imageHistorique.getFLUX(),"javascript:Ajax('Accueil','Historique','null')"));
        gestionMenu.add(new Menu(imageMail.getFLUX(),list_smenu));
        
        gestionMenu.add(new Menu(imageDeconnection.getFLUX(),"'Deconnexion'"));
        
        gestionMenu.close();
        
        out.print(gestionMenu.getFLUX());
        
        //Fermeture du flux de sortie
        out.flush();
        out.close();
    }
}