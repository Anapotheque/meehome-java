/*
 * Historique.java
 *
 * Created on 20 mars 2007, 09:28
 */

package fr.alliance.docalliance.ajax.historique;

import fr.alliance.docalliance.model.Destinataire;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import fr.alliance.docalliance.controleur.MetaControleur;
import fr.alliance.docalliance.dao.DaoFindException;
import fr.alliance.docalliance.model.AdresseMail;
import fr.alliance.docalliance.model.Mail;
import fr.alliance.docalliance.service.IGlobalService;
import fr.alliance.docalliance.service.IHistoriqueService;
import fr.alliance.docalliance.tools.html.Data.GestionData;
import fr.alliance.docalliance.tools.html.message.MessageAlert;
import fr.alliance.docalliance.tools.html.table.GestionTable;
import fr.alliance.docalliance.tools.html.table.Ligne;
import fr.alliance.docalliance.tools.html.table.Titre;
import java.util.Iterator;

/**
 * Servlet pour l'affichage de l'historique d'envoie mail
 * @author Yves Le Rumeur, Romain Raballand
 * @version 1.0
 */

public class Historique extends MetaControleur {
    
    //Serialisation de la servlet
    private static final long serialVersionUID = 1L;
    private static final String TITRE = "Historique";
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        IGlobalService service = getService(request);
        
        //Initialisation du flux de sortie
        PrintWriter out = response.getWriter();
        
        try {
            
            //Declaration du titre de l'historique
            GestionData title = new GestionData("titre");
            title.add(TITRE);
            title.close();
            out.print(title.getFLUX());
            
            //On recupere une list d'objet de type Historique
            List list = service.getMailHistorique();
            
            //Si nous avons des données
            if(!list.isEmpty()){
                
                //Declaration de la table contenant tout l'historique
                GestionTable gestionTable = new GestionTable("200px");
                
                //Declaration du titre de l'historique
                ArrayList<String> list_Titre = new ArrayList<String>();
                list_Titre.add("DATE");
                list_Titre.add("EXPEDITEUR");
                list_Titre.add("DESTINATAIRE");
                list_Titre.add("PDF");
                
                //Insertion du tire au tableau
                gestionTable.Add(new Titre(list_Titre));
                
                //On parcours la liste et on ajoute les données au 'Main' du tableau
                ArrayList<String> list_ligne = null;
                boolean first = true;
                for (int i = 0; i < list.size(); i++) {
                    
                    //Initialisation d'un objet Mail
                    Mail mail = (Mail) list.get(i);
                    
                    list_ligne = new ArrayList<String>();
                    
                    //On ajoute la date d'expedition
                    list_ligne.add(mail.getDateExpedition().toString());
                    
                    //On recupere l'expediteur
                    list_ligne.add(mail.getAdresseMail().getFirstName()+" "+mail.getAdresseMail().getLastName());
                    
                    //Affichage de tout les destinataires
                    Iterator<Destinataire> itr = (Iterator<Destinataire>)mail.getDestinataires().iterator();
                    String data = new String();
                    while(itr.hasNext()){
                        
                        //Insertion du nom du destinataire
                        Destinataire destinataire = (Destinataire) itr.next();
                        data += destinataire.getAdresseMail().getFirstName()+" "+destinataire.getAdresseMail().getLastName();
                        data += "<br>";
                    }
                    list_ligne.add(data);
                    list_ligne.add(mail.getPdf().getNom());
                    
                    //On affiche une ligne en bleu puis la ligne suivante en Navy ect ect
                    if(first){
                        gestionTable.Add(new Ligne(list_ligne, "blue"));
                        first = false;
                    }else{
                        gestionTable.Add(new Ligne(list_ligne, "navy"));
                        first = true;
                    }
                }
                
                //Fermeture du tableau
                gestionTable.close();
                //On affiche les données
                out.print(gestionTable.getFLUX());
                
                //Si il n'y a pas d'historique referencé
            }else{
                out.print("Historique vide");
            }
            
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
