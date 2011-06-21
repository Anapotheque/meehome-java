/*
 * Modify.java
 *
 * Created on 26 mars 2007, 16:32
 */

package fr.alliance.docalliance.ajax.Administration.AdresseMail;

import fr.alliance.docalliance.controleur.MetaControleur;
import fr.alliance.docalliance.dao.DaoFindException;
import fr.alliance.docalliance.model.AdresseMail;
import fr.alliance.docalliance.service.IAdresseMail;
import fr.alliance.docalliance.service.IGlobalService;
import fr.alliance.docalliance.tools.html.Data.GestionData;
import fr.alliance.docalliance.tools.html.Input.GestionInput;
import fr.alliance.docalliance.tools.html.message.MessageAlert;
import fr.alliance.docalliance.tools.html.select.GestionSelect;
import fr.alliance.docalliance.tools.html.select.Option;
import fr.alliance.docalliance.tools.html.table.GestionTable;
import fr.alliance.docalliance.tools.html.table.Ligne;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * Servlet de modification d'un element AdresseMail
 * @author Yves Le Rumeur, Romain Raballand
 * @version 1.0
 */
public class Modify extends MetaControleur {
    
    private static final String TITRE = "Modification des adresses";
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        // Recuperation de la session
        HttpSession session = request.getSession(true);
        
        IGlobalService service = getService(request);
        
        //initialisation du flux de sortie
        PrintWriter out = response.getWriter();
        
        try {
            
            //On recupere l'objet correspond à l'id de l'adresseMail selectionné
            AdresseMail adressemail = service.getAdresseMailById(Integer.parseInt(request.getParameter("idAdresseMail")));
            
            //Mise en session de l'objet AdresseMail
            session.setAttribute("adresseMail",adressemail);
            
            //Declaration du titre
            GestionData title = new GestionData("titre");
            title.add(TITRE);
            title.close();
            
            //Declaration d'un input contenant le nom
            GestionInput nom = new GestionInput();
            nom.addtype("text");
            nom.addId("nom");
            nom.addValue(adressemail.getFirstName());
            nom.close();
            
            //Declaration d'un input contenant le prenom
            GestionInput prenom = new GestionInput();
            prenom.addtype("text");
            prenom.addId("prenom");
            prenom.addValue(adressemail.getLastName());
            prenom.close();
            
            //Declaration d'un input contenant l'adresseMail
            GestionInput adresseMail = new GestionInput();
            adresseMail.addtype("text");
            adresseMail.addId("adressemail");
            adresseMail.addValue(adressemail.getAdresseMail());
            adresseMail.addStyle("width: 70%");
            adresseMail.close();
            
            //Declaration d'un select contenant active ou pas
            GestionSelect active = new GestionSelect("active");
            if(adressemail.getActive()){
                active.add(new Option("true","Enabled"));
                active.add(new Option("false","Disabled"));
            }else{
                active.add(new Option("false","Disabled"));
                active.add(new Option("true","Enabled"));
            }
            active.close();
            
            
            //Declaration d'un input pour l'enregistrement des informations
            GestionInput buttonenregistrerSolo = new GestionInput();
            buttonenregistrerSolo.addtype("submit");
            buttonenregistrerSolo.addValue("Enregistrer");
            buttonenregistrerSolo.close();
            
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
            
            //Ecriture du flux
            out.print("<form id='Modify' action=javascript:Ajax('AJAXRESULT','SaveNewInfos','Modify')>");
            out.print(title.getFLUX());
            out.print(gestionTable.getFLUX());
            out.print("<div id='AJAXRESULT'></div>");
            out.print("</form>");
            
            //Traitement des erreurs liées au modele de données ( Hibernate )
        } catch (DaoFindException e) {
            MessageAlert message = new MessageAlert("Probleme : <br><br>"+e.getMessage(),MessageAlert.ERREUR);
            out.print(message.getFLUX());
            e.printStackTrace();
        }
        
        //Fermeture du flux de sortie
        out.flush();
        out.close();
    }
}
