/*
 * UploadData.java
 *
 * Created on 29 mars 2007, 15:37
 */

package fr.alliance.docalliance.ajax.Administration.AdresseMail.tools;

import fr.alliance.docalliance.dao.DaoCreateException;
import fr.alliance.docalliance.dao.DaoFindException;
import fr.alliance.docalliance.util.TechnicalException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import com.oreilly.servlet.MultipartRequest;

import fr.alliance.docalliance.controleur.MetaControleur;
import fr.alliance.docalliance.model.AdresseMail;
import fr.alliance.docalliance.service.IGlobalService;
import fr.alliance.docalliance.tools.html.message.Message;
import fr.alliance.docalliance.tools.html.message.MessageAlert;

/**
 * Servlet pour la sauvegarde d'une adresseMail
 * @author Yves Le Rumeur, Romain Raballand
 * @version
 */
public class UploadData extends MetaControleur {
    
    private final static String saveFile = "C:\\";
    
    private int TAILLEMINTABLEAU = 3;
    private Message msg = null;
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //initialisation du flux de sortie
        PrintWriter out = response.getWriter();
        
        //UPLOAD du fichier selectionné
        String nameFile = UpLoad(request,out);
        
        //Si le fichier n'a pas été correctement uploadé
        if(nameFile == null){
            MessageAlert message = new MessageAlert("Pas de fichier selectionné",MessageAlert.WARNING);
            out.print(message.getFLUX());
        }else{
            ArrayList<AdresseMail> listAdresseMail = null;
            
            try {
                listAdresseMail = openFile(saveFile + nameFile);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (TechnicalException ex) {
                ex.printStackTrace();
            }
            
            //On va parcourir la liste d'adresseMail créé par openFile et sauvegarder chacun des elements
            Iterator iterator = listAdresseMail.iterator();
            
            //Boolean pour le retour d'erreur
            boolean noPb = true;
            
            //Tantqu'il y a des adresseMail
            while(iterator.hasNext()){
                
                //Recuperation de l'objet
                AdresseMail adresseMail = (AdresseMail)iterator.next();
                
                //On verifie si l'objet repond bien au critere ( Nom, Prenom, AdresseMail)
                //Si correct, On enregistre les données
//                if(isCorrect(adresseMail)){
                if(true){
                    try {
                        SaveAdresseMailUpload(request,adresseMail);
                    } catch (DaoFindException e) {
                        MessageAlert message = new MessageAlert("Probleme : <br><br>"+e.getMessage(),MessageAlert.ERREUR);
                        out.print(message.getFLUX());
                        e.printStackTrace();
                    }catch (DaoCreateException e) {
                        MessageAlert message = new MessageAlert("Probleme : <br><br>"+e.getMessage(),MessageAlert.ERREUR);
                        out.print(message.getFLUX());
                        e.printStackTrace();
                    }
                    //Sinon affichage d'un message d'erreur
                }else{
                    MessageAlert message = new MessageAlert("DATA BASE UPDATE : ECHEC<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+adresseMail.getFirstName()+" "+adresseMail.getLastName()+" "+adresseMail.getAdresseMail(),MessageAlert.WARNING);
                    out.print(message.getFLUX());
                    //On declare un probleme
                    noPb = false;
                }
            }
            //Si il n'y a pas eu de probleme alors message de sucess
            if(noPb){
                MessageAlert message = new MessageAlert("Les données ont été correctement enregistrés",MessageAlert.VALIDATION);
                out.print(message.getFLUX());
            }
        }
        
        //Fermeture du flux de sortie
        out.flush();
        out.close();
    }
    
    
    /**
     * Permet de uploader un fichier du post client vers le post server
     * @param request Requete multipart obligatoire ( method = 'POST' )
     * @param out Flux de sortie
     * @return Retourne le nom du fichier uploadé si réussi sinon null
     */
    private String UpLoad(HttpServletRequest request,PrintWriter out){
        
        MultipartRequest  multi = null;
        
        try{
            //Traitement de la requete multi part et enregistrement du fichier du le chemin SAVEFILE
            multi = new MultipartRequest(request,saveFile);
            //Traitement de l'erreur
        } catch (IOException ex) {
            MessageAlert message = new MessageAlert("Upload Failed",MessageAlert.WARNING);
            out.print(message.getFLUX());
            ex.printStackTrace();
        }
        //On retourne le nom du fichier recement enregister comme preuve que les operations se sont deroulées convenablement
        if(multi == null){
            return null;
        } else{
            return multi.getFilesystemName("file");
        }
    }
    
    /**
     * Permet d'ouvrir un fichier et d'extraire en format LIST Objet AdresseMail chaque ligne du fichier
     * @param out Flux de sortie
     * @param fileName Chemin du fichier à extraire
     * @return Liste d'objet AdresseMail
     */
    private ArrayList<AdresseMail> openFile(String chemin) throws TechnicalException, IOException{
        
        //Declaration d'une liste d'object AdresseMail
        ArrayList<AdresseMail> listAdresseMail = new ArrayList<AdresseMail>();
        //Declaration d'une liste recevant une ligne du fichier importé
        ArrayList<String> list = null;
        //Declaration d'un object AdresseMail
        AdresseMail adresseMail = null;
        
        LineNumberReader lnr = null;
        
        //Ouverture du fichier en lecture
        lnr = new LineNumberReader(new FileReader(chemin));
        String ligneLue = new String();
        
        //Tantque le fichier contient une ligne
        while((ligneLue = lnr.readLine()) != null){
            
            //On decoupe la ligne en autant de colonne qu'il y a de de ';'
            String[] buffer = ligneLue.split(";");
            
            //Taille minimum du tableau
            if(buffer.length < TAILLEMINTABLEAU){
                throw new TechnicalException();
            }
            
            for(int i = 1; i < buffer.length; i++){
                
                //Creation d'une nouvelle adresse
                adresseMail = new AdresseMail();
                
                //On donne le prenom à l'objet adresseMail
                adresseMail.setLastName(buffer[i++]);
                
                //On donne le nom à l'objet adresseMail
                adresseMail.setFirstName(buffer[i++]);
                
                //Concatenation de tout les adresses mails
                String chaineAdresseMail = null;
                
                while(i < buffer.length){
                    
                    //Recuperation de la valeur du champ
                    String temp = buffer[i++];
                    
                    //Si c'est le debut de liste alors on creer une nouvelle adresse sinon on fait un retour à la ligne
                    if(chaineAdresseMail == null){
                        chaineAdresseMail = new String();
                    }else{
                        if(buffer.length >= i && !temp.equals("\"") && !temp.equals("")){
                            chaineAdresseMail += ";";
                        }
                    }
                    
                    //Si temp n'est pas un champ vide
                    if(!temp.equals("")){
                        
                        //On supprime le retour à la ligne si il existe
                        if(temp.substring(temp.length()-1,temp.length()).equals("\"")){
                            temp = temp.substring(0,temp.length()-1);
                        }
                        
                        //On ajoute l'adresse à la liste d'adresseMail
                        chaineAdresseMail += temp;
                    }
                }
                
                //On donne l'adresse à l'objet adresseMail
                adresseMail.setAdresseMail(chaineAdresseMail);
                
                //On donne l'adresse à l'objet adresseMail
                adresseMail.setActive(true);
                
                //On ajoute à la liste notre objet
                listAdresseMail.add(adresseMail);
                
            }
        }
        
        //On retire le premier element correspondant à la ligne 'Nom, Prenom, Adresse1, Adresse2 etc etc'
        listAdresseMail.remove(0);
        
        return listAdresseMail;
    }
    
    /**
     * Verifie si l'objet correspond aux critere pour l'entree dans la base : Nom, Prenom, Adresse
     * @param adresseMail Un objet AdresseMail
     * @return Retourn True si l'object est correct ( Si il possede un nom , un prenom et une adresse
     */
    private boolean isCorrect(AdresseMail adresseMail){
        boolean result = true;
        if(adresseMail.getFirstName().equals("") || adresseMail.getLastName().equals("") || adresseMail.getAdresseMail().equals("")){
            result = false;
        }
        return result;
    }
    
    /**
     * Sauvegarde d'une adresseMail
     * @param adresseMail Un objet AdresseMail
     */
    private void SaveAdresseMailUpload(HttpServletRequest request,AdresseMail adresseMail) throws DaoFindException, DaoCreateException{
        
        IGlobalService service = getService(request);
        
        //On verifie que l'adresse mail n'existe pas deja en base de donnée
        if(service.getAdresseMailByNomPrenom(adresseMail.getFirstName(),adresseMail.getLastName()) == null){
            service.saveOneAdresseMail(adresseMail);
        }
    }
}
