/*
 * Show.java
 *
 * Created on 26 mars 2007, 16:38
 */

package fr.alliance.docalliance.ajax.Administration.AdresseMail;

import fr.alliance.docalliance.controleur.MetaControleur;
import fr.alliance.docalliance.dao.DaoFindException;
import fr.alliance.docalliance.model.AdresseMail;
import fr.alliance.docalliance.service.IAdresseMail;
import fr.alliance.docalliance.service.IGlobalService;
import fr.alliance.docalliance.tools.enumeration.SearchAdresseMail;
import fr.alliance.docalliance.tools.html.Data.GestionData;
import fr.alliance.docalliance.tools.html.Input.GestionInput;
import fr.alliance.docalliance.tools.html.message.MessageAlert;
import fr.alliance.docalliance.tools.html.table.GestionTable;
import fr.alliance.docalliance.tools.html.table.Ligne;
import fr.alliance.docalliance.tools.html.table.Titre;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * Servlet d'affichage d'adresseMail
 * @author Yves Le Rumeur, Romain Raballand
 * @version 1.0
 */
public class Show extends MetaControleur {
    
    private static final String TITRE = "Affichage des adresses";
    
    private GestionData title = null;
    private GestionInput buttonModifier = null;
    private GestionInput buttonEnabled = null;
    private GestionInput buttonDisabled = null;
    private GestionInput buttonToutSelectionner = null;
    private GestionInput buttonToutDeSelectionner = null;
    private GestionTable gestiontable = null;
    
    int filter = 0;
    
    //Methode por l'initialisation des informations HTML
    private void initObjectHTML(){
        
        //Declaration du titre
        title = new GestionData("titre");
        title.add(TITRE);
        title.close();
        
        //Declaration du bouton pour la modification
        buttonModifier = new GestionInput();
        buttonModifier.addtype("button");
        //Si les ordres emmanent de la recherche ou de l'affichage général on change la balise ajax
        if(filter == SearchAdresseMail.ALL){
            //Provient de l'affichage général
            buttonModifier.addOnclick("javascript:Ajax('Accueil','Modify','champ')");
        }else{
            //Provient de la recherche
            buttonModifier.addOnclick("javascript:Ajax('AJAXMAINFRAME','Modify','champ')");
        }
        buttonModifier.addValue("Modifier");
        buttonModifier.close();
        
        //Declaration du bouton d'activation
        buttonEnabled = new GestionInput();
        buttonEnabled.addtype("button");
        //Si les ordres emmanent de la recherche ou de l'affichage général on change la balise ajax
        if(filter == SearchAdresseMail.ALL){
            //Provient de l'affichage général
            buttonEnabled.addOnclick("javascript:Ajax('Accueil','Enabled','champ')");
        }else{
            //Provient de la recherche
            buttonEnabled.addOnclick("javascript:Ajax('AJAXMAINFRAME','Enabled','champ')");
        }
        buttonEnabled.addValue("Activer");
        buttonEnabled.close();
        
        //Declaration du bouton de desactivation
        buttonDisabled = new GestionInput();
        buttonDisabled.addtype("button");
        //Si les ordres emmanent de la recherche ou de l'affichage général on change la balise ajax
        if(filter == SearchAdresseMail.ALL){
            //Provient de l'affichage général
            buttonDisabled.addOnclick("javascript:Ajax('Accueil','Disabled','champ')");
        }else{
            //Provient de la recherche
            buttonDisabled.addOnclick("javascript:Ajax('AJAXMAINFRAME','Disabled','champ')");
        }
        buttonDisabled.addValue("Désactiver");
        buttonDisabled.close();
        
        //Declaration du bouton pour la selection complete
        buttonToutSelectionner = new GestionInput();
        buttonToutSelectionner.addtype("button");
        buttonToutSelectionner.addOnclick("javascript:SelectAll('idAdresseMail')");
        buttonToutSelectionner.addValue("Tout sélectionner");
        buttonToutSelectionner.close();
        
        //Declaration du bouton pour tout deselectionner
        buttonToutDeSelectionner = new GestionInput();
        buttonToutDeSelectionner.addtype("button");
        buttonToutDeSelectionner.addOnclick("javascript:DeSelectAll('idAdresseMail')");
        buttonToutDeSelectionner.addValue("Tout désélectionner");
        buttonToutDeSelectionner.close();
    }
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        IGlobalService service = getService(request);
        
        //initialisation du flux de sortie
        PrintWriter out = response.getWriter();
        
        //On recupere le filtre selectionné
        filter = Integer.parseInt(request.getParameter("idfilter"));
        
        //Initialisation des informations HTML
        this.initObjectHTML();
        
        //Initialisation de la list d'adresseMail
        ArrayList<AdresseMail> listAdresseMail = new ArrayList<AdresseMail>();
        
        try {
            //Si le filtre n'est pas un affichage général alors on affiche les données avec un filtre
            if(filter != SearchAdresseMail.ALL){
                if(request.getParameter("search") == null){
                    //Ecriture du flux
                    out.print(title.getFLUX());
                    out.print("Vous avez tapez un mauvais caractère");
                }else{
                    //Declaraiton de l'HashMap pour le filtre
                    HashMap<String,Object> filtre = new HashMap<String,Object>();
                    //Recuperation de la valeur entrée pour la selection
                    String search = new String();
                    search = request.getParameter("search");
                    
                    //On initialise le filtre
                    switch(filter){
                        case(SearchAdresseMail.BYFIRSTNAME):        filtre.put("firstName",search);break;
                        case(SearchAdresseMail.BYLASTNAME):         filtre.put("lastName",search);break;
                        case(SearchAdresseMail.BYADRESSEMAIL):      filtre.put("adresseMail",search);break;
                        case(SearchAdresseMail.ACTIVES):            filtre.put("active",true);break;
                        case(SearchAdresseMail.INACTIVES):          filtre.put("active",false);break;
                    }
                    
                    //Initialisation des list d'adresseMail pour le filtre selectionné
                    listAdresseMail = (ArrayList<AdresseMail>) service.getAdresseMailByFilters(filtre);
                }
                //Si on affiche tout alors on recupere toutes les adresses
            }else if(request.getParameter("search") != null || filter == SearchAdresseMail.ALL){
                listAdresseMail = (ArrayList<AdresseMail>) service.getAdresseMails();
            }
            
            //Si la list d'adresse n'est pas nulle alors affiche les données
            if(!listAdresseMail.isEmpty()){
                
                //Ecriture du flux
                out.print("<form id='champ'>");
                out.print(title.getFLUX());
                out.print(buttonModifier.getFLUX());
                out.print(buttonEnabled.getFLUX());
                out.print(buttonDisabled.getFLUX());
                out.print(buttonToutSelectionner.getFLUX());
                out.print(buttonToutDeSelectionner.getFLUX());
                
                //Declaration du tableau
                gestiontable = new GestionTable("50px");
                
                //Declaration du titre du tableau
                ArrayList<String> listTitre = new ArrayList<String>();
                listTitre.add("");
                listTitre.add("Nom");
                listTitre.add("Prénom");
                listTitre.add("Adresse mail");
                
                //Ajout du titre au tableau
                gestiontable.Add(new Titre(listTitre));
                
                //POur chaque element AdresseMail on ajoute un e ligne au tableau
                for(int i = 0; i < listAdresseMail.size(); i++){
                    ArrayList<String> listLigne = new ArrayList<String>();
                    listLigne.add("<input type='checkbox' name='idAdresseMail' value='"+listAdresseMail.get(i).getIdAdresseMail()+"'>");
                    listLigne.add(""+listAdresseMail.get(i).getFirstName());
                    listLigne.add(""+listAdresseMail.get(i).getLastName());
                    listLigne.add(""+listAdresseMail.get(i).getAdresseMail());
                    if(listAdresseMail.get(i).getActive())
                        gestiontable.Add(new Ligne(listLigne,"green"));
                    else{
                        gestiontable.Add(new Ligne(listLigne,"red"));
                    }
                }
                
                //Fermeture du tableau
                gestiontable.close();
                
                //Ecriture du flux
                out.print(gestiontable.getFLUX());
                out.print("</form>");
                
                
            }else if(request.getParameter("search") != null){
                //Si il n'y aucune adresse renseignée
                //Ecriture du flux
                out.print(title.getFLUX());
                out.print("Pas de résultat pour ce critère");
            }
            
            //Traitement des erreurs liées au modele de données ( Hibernate )
        }catch (DaoFindException e) {
            MessageAlert message = new MessageAlert("Problème : <br><br>"+e.getMessage(),MessageAlert.ERREUR);
            out.print(message.getFLUX());
            e.printStackTrace();
        }
        
        //Fermeture du flux de sortie
        out.flush();
        out.close();
    }
    
}
