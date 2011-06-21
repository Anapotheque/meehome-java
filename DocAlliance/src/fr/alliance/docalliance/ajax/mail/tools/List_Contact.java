/*
 * List_Contact.java
 *
 * Created on 20 mars 2007, 09:28
 */

package fr.alliance.docalliance.ajax.mail.tools;

import fr.alliance.docalliance.tools.html.Input.GestionInput;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import fr.alliance.docalliance.controleur.MetaControleur;
import fr.alliance.docalliance.dao.DaoFindException;
import fr.alliance.docalliance.model.AdresseMail;
import fr.alliance.docalliance.model.User;
import fr.alliance.docalliance.service.IAdresseMail;
import fr.alliance.docalliance.service.IGlobalService;
import fr.alliance.docalliance.service.IUserService;
import fr.alliance.docalliance.tools.html.checkbox.GestionCheckBox;
import fr.alliance.docalliance.tools.html.checkbox.Option;
import fr.alliance.docalliance.tools.html.message.MessageAlert;

/**
 * Servlet permettant l'affichage d'une liste de checkbox
 * @author Yves Le Rumeur, Romain Raballand
 * @version 1.0
 */

public class List_Contact extends MetaControleur {
    
    private static final long serialVersionUID = 1L;
    private static PrintWriter out = null;
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        try {
            //Affichage de la liste
            List_Contact.GetList_Contact(request, response);
            
            //Traitement des erreurs liées au modele de données ( Hibernate )
        } catch (DaoFindException e) {
            MessageAlert message = new MessageAlert("Probleme : <br><br>"+e.getMessage(),MessageAlert.ERREUR);
            out.print(message.getFLUX());
            e.printStackTrace();
        }
    }
    
    /**
     * Initialisation des listes de contacts
     * @param request TODO
     */
    public static void GetList_Contact(HttpServletRequest request, HttpServletResponse response) throws DaoFindException, IOException{
        
        IGlobalService service = getService(request);
        
        //Initialisation du flux de sortie
        out = response.getWriter();
        
        //Recuperation de tous les objets AdresseMail
        ArrayList<AdresseMail> listContacts = (ArrayList<AdresseMail>) service.getAdresseMails();
        
        //Declaration d'un button parcourir
        GestionInput retour = new GestionInput();
        retour.addtype("button");
        retour.addOnclick("javascript:Ajax('Accueil','AccueilNewsLetter','null')");
        retour.addValue("Retour");
        retour.close();
        
        //Declaration d'un button selectionner tout
        GestionInput Toutselectionner = new GestionInput();
        Toutselectionner.addtype("button");
        Toutselectionner.addOnclick("javascript:SelectAll('idContact')");
        Toutselectionner.addValue("Tout selectionner");
        Toutselectionner.close();
        
        //Declaration d'un button deselectionner tout
        GestionInput Toutdeselectionner = new GestionInput();
        Toutdeselectionner.addtype("button");
        Toutdeselectionner.addOnclick("javascript:DeSelectAll('idContact')");
        Toutdeselectionner.addValue("Tout deselectionner");
        Toutdeselectionner.close();
        
        //Declaration d'un button joindre
        GestionInput Ajouter = new GestionInput();
        Ajouter.addtype("button");
        Ajouter.addOnclick("javascript:Ajax('AJAXMAINFRAME','ActionNewsLetter','champ')");
        Ajouter.addValue("Ajouter");
        Ajouter.close();
        
        //Si il n'y a pas d'elements
        if(listContacts.isEmpty()){
            out.print("Aucun contact référencé<br>");
        }
        
        //Sinon on affiche ces elements
        else{
            out.print(retour.getFLUX());
            out.print(Toutselectionner.getFLUX());
            out.print(Toutdeselectionner.getFLUX());
            out.print(Ajouter.getFLUX());
            
            //Nouvel checkbox
            GestionCheckBox gestioncheckbox = new GestionCheckBox("idContact");
            for(int i=0 ; i< listContacts.size() ; i++){
                
                //Si les contacts sont actifs alors on les affiches sinon pas d'affichage
                if(listContacts.get(i).getActive()){
                    gestioncheckbox.add(new Option(""+listContacts.get(i).getIdAdresseMail(),""+listContacts.get(i).getFirstName()+" "+listContacts.get(i).getLastName()));
                }
            }
            //Fermture du checkbox
            gestioncheckbox.close();
            //Ecriture du flux
            out.print(gestioncheckbox.getFLUX());
        }
        
        //Fermeture du flux de sortie
        out.flush();
        out.close();
    }
}
