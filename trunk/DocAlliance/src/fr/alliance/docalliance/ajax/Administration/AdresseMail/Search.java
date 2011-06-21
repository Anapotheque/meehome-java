/*
 * Search.java
 *
 * Created on 26 mars 2007, 16:32
 */

package fr.alliance.docalliance.ajax.Administration.AdresseMail;

import fr.alliance.docalliance.controleur.MetaControleur;
import fr.alliance.docalliance.tools.enumeration.SearchAdresseMail;
import fr.alliance.docalliance.tools.html.Data.GestionData;
import fr.alliance.docalliance.tools.html.Input.GestionInput;
import fr.alliance.docalliance.tools.html.select.GestionSelect;
import fr.alliance.docalliance.tools.html.select.Option;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet de recherche d'adresseMail
 * @author Yves Le Rumeur, Romain Raballand
 * @version 1.0
 */
public class Search extends MetaControleur {
    
    private static final String TITRE = "Recherche d'une nouvelle adresse";
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //initialisation du flux de sortie
        PrintWriter out = response.getWriter();

        //Declaration du titre
        GestionData title = new GestionData("titre");
        title.add(TITRE);
        title.close();
        
        //Declaration du filtre de recherche
        GestionSelect gestionselect = new GestionSelect("filter");
        gestionselect.add(new Option(""+SearchAdresseMail.BYFIRSTNAME,"Par nom"));
        gestionselect.add(new Option(""+SearchAdresseMail.BYLASTNAME,"Par prenom"));
        gestionselect.add(new Option(""+SearchAdresseMail.BYADRESSEMAIL,"Par adresse mail"));
        gestionselect.add(new Option(""+SearchAdresseMail.ACTIVES,"Actifs"));
        gestionselect.add(new Option(""+SearchAdresseMail.INACTIVES,"Inactifs"));
        gestionselect.close();
        
        //Declaration du champ pour le search
        GestionInput search = new GestionInput();
        search.addtype("text");
        search.addId("search");
        search.close();
        
        //Declaration du button de recherche
        GestionInput recherche = new GestionInput();
        recherche.addtype("submit");
        recherche.addValue("Rechercher");
        recherche.close();

        //Ecriture du deuxieme flux
        out.print(title.getFLUX());
        out.print("<form id='SearchAdresse' action=javascript:Ajax('AJAXMAINFRAME','Show','SearchAdresse','filter="+SearchAdresseMail.ALL+"')>");
        out.print(gestionselect.getFLUX());
        out.print(search.getFLUX());
        out.print(recherche.getFLUX());
        out.print("</form>");
        out.print("<div id='AJAXMAINFRAME'></div>");
        
        //Fermeture du flux de sortie
        out.flush();
        out.close();
    }
    
}
