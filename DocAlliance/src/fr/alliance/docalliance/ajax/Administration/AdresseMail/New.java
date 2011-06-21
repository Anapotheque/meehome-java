/*
 * New.java
 *
 * Created on 26 mars 2007, 16:32
 */

package fr.alliance.docalliance.ajax.Administration.AdresseMail;

import fr.alliance.docalliance.controleur.MetaControleur;
import fr.alliance.docalliance.tools.html.Data.GestionData;
import fr.alliance.docalliance.tools.html.Input.GestionInput;
import fr.alliance.docalliance.tools.html.select.GestionSelect;
import fr.alliance.docalliance.tools.html.select.Option;
import fr.alliance.docalliance.tools.html.table.GestionTable;
import fr.alliance.docalliance.tools.html.table.Ligne;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet de creation d'une nouvelle adresse
 * @author Yves Le Rumeur, Romain Raballand
 * @version 1.0
 */
public class New extends MetaControleur {
    
    private static final String TITRE1 = "Nouvelle adresse";
    private static final String TITRE2 = "Chargement";
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //initialisation du flux de sortie
        PrintWriter out = response.getWriter();
        
        //Declaration du titre
        GestionData title1 = new GestionData("titre");
        title1.add(TITRE1);
        title1.close();
        
        //Declaration d'un input contenant le nom
        GestionInput nom = new GestionInput();
        nom.addtype("text");
        nom.addId("nom");
        //La fonction marche correctement si on rempli le formulaire de façon linéaire
        //nom.addFunction("onkeyup=javascript:GenerationAdresseMail('New')");
        nom.close();
        
        //Declaration d'un input contenant le prenom
        GestionInput prenom = new GestionInput();
        prenom.addtype("text");
        prenom.addId("prenom");
        //La fonction marche correctement si on rempli le formulaire de façon linéaire
        //prenom.addFunction("onkeyup=javascript:GenerationAdresseMail('New')");
        prenom.close();
        
        //Declaration d'un input contenant l'AdresseMail
        GestionInput adresseMail = new GestionInput();
        adresseMail.addtype("text");
        adresseMail.addId("adressemail");
        adresseMail.addValue("@alliance-concept.fr");
        adresseMail.addStyle("width: 70%");
        adresseMail.close();
        
        //Declaration d'un select contenant active ou pas
        GestionSelect active = new GestionSelect("active");
        active.add(new Option("true","Enabled"));
        active.add(new Option("false","Disabled"));
        active.close();
        
        //Declaration d'un input pour l'enregistrement des informations
        GestionInput buttonenregistrerSolo = new GestionInput();
        buttonenregistrerSolo.addtype("submit");
        buttonenregistrerSolo.addValue("Enregistrer");
        buttonenregistrerSolo.close();
        
        //Declaration d'un input pour le chargement des fichiers
        GestionInput file = new GestionInput();
        file.addtype("file");
        file.addId("file");
        file.close();
        
        //Declaration d'un input l'upload du fichier
        GestionInput buttonenregistrerUpload = new GestionInput();
        buttonenregistrerUpload.addtype("submit");
        buttonenregistrerUpload.addValue("Update");
        buttonenregistrerUpload.close();
        
        //Creation d'un tableau pour contenir les données
        GestionTable gestionTable = new GestionTable("auto");
        
        //Nouvelle ligne pour le nom
        ArrayList<String> ligne = new ArrayList<String>();
        ligne.add("Nom");
        ligne.add(nom.getFLUX());
        gestionTable.Add(new Ligne(ligne,"black"));
        
        //Nouvelle ligne pour le prenom
        ligne = new ArrayList<String>();
        ligne.add("Prenom");
        ligne.add(prenom.getFLUX());
        gestionTable.Add(new Ligne(ligne,"black"));
        
        //Nouvelle ligne pour l'adresseMail
        ligne = new ArrayList<String>();
        ligne.add("Adresse mail");
        ligne.add(adresseMail.getFLUX());
        gestionTable.Add(new Ligne(ligne,"black"));
        
        //Nouvelle ligne pour active
        ligne = new ArrayList<String>();
        ligne.add("Active");
        ligne.add(active.getFLUX());
        gestionTable.Add(new Ligne(ligne,"black"));
        
        //Nouvelle ligne pour enregistrement
        ligne = new ArrayList<String>();
        ligne.add("");
        ligne.add(buttonenregistrerSolo.getFLUX());
        gestionTable.Add(new Ligne(ligne,"black"));
        
        //Fermeture du tableau
        gestionTable.close();
        
        //Declaration du titre
        GestionData title2 = new GestionData("titre");
        title2.add(TITRE2);
        title2.close();
                
        //Ecriture du flux
        out.print("<form id='New' action=javascript:Ajax('AJAXRESULT','SaveNewInfos','New')>");
        out.print(title1.getFLUX());
        out.print(gestionTable.getFLUX());
        out.print("<div id='AJAXRESULT'></div>");
        out.print("</form>");
        
        //Ecriture du deuxieme flux
        out.print(title2.getFLUX());
        out.print("<form action='UploadData' enctype='multipart/form-data'  method='post' target='returnForm'>");
        out.print(file.getFLUX());
        out.print(buttonenregistrerUpload.getFLUX());
        out.print("<br><iframe name='returnForm'></iframe>");
        out.print("</form>");
        
        //Fermeture du flux de sortie
        out.flush();
        out.close();
    }
    
}
