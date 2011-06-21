/*
 * List_PDF.java
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

import fr.alliance.docalliance.controleur.MetaControleur;
import fr.alliance.docalliance.dao.DaoFindException;
import fr.alliance.docalliance.model.Pdf;
import fr.alliance.docalliance.service.IGlobalService;
import fr.alliance.docalliance.tools.html.checkbox.GestionCheckBox;
import fr.alliance.docalliance.tools.html.checkbox.Option;
import fr.alliance.docalliance.tools.html.message.MessageAlert;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * Servlet permettant l'affichage d'une liste de checkbox
 * @author Yves Le Rumeur, Romain Raballand
 * @version 1.0
 */

public class List_PDF extends MetaControleur {
    
    private static final long serialVersionUID = 1L;
    private static final int PAYE = 0;
    private static final int NEWSLETTER = 1;
    private static PrintWriter out = null;
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //Recuperation des données formulaire
        String typePDF = new String(request.getParameter("typeFile"));
        
        //Affichage de la liste
        List_PDF.GetList(null, response, typePDF);
        
    }
    
    /**
     * Genere le select de checkBox
     * @param out Flux de sortie
     * @param listPdf List de fichier pdf à afficher
     */
    private static void showCheckBox(ArrayList<Pdf> listPdf){
        
        //Declaration d'un nouveau select
        GestionCheckBox gestioncheckbox = new GestionCheckBox("idPDF");
        
        //Parcour de la list PDF
        for(int i=0 ; i< listPdf.size() ; i++){
            //Si le pdf à deja été envoyé alors on met une '*' devant le nom
            if(listPdf.get(i).getSend()){
                //Ajout d'une option au select
                gestioncheckbox.add(new Option(""+listPdf.get(i).getIdPdf(),"*"+listPdf.get(i).getNom()));
            }else{
                //Ajout d'une option au select
                gestioncheckbox.add(new Option(""+listPdf.get(i).getIdPdf(),""+listPdf.get(i).getNom()));
            }
        }
        //Fermeture du select
        gestioncheckbox.close();
        //Ecriture du flux
        out.print(gestioncheckbox.getFLUX());
    }
    
    
    /**
     * Affichage et gestion de l'affichage du select ( Recuperation des objets PDF )
     * @param request TODO
     * @param typePDF Type de fichier PDF
     * @param out Flux de sortie
     * @throws ServletException
     * @throws IOException
     */
    public static void GetList(HttpServletRequest request, HttpServletResponse response, String typePDF)
    throws ServletException, IOException{
        
        //Initialisation du flux de sortie
        out = response.getWriter();
        
        XmlBeanFactory bf = new XmlBeanFactory(new ClassPathResource("spring-config.xml"));
        IGlobalService service = (IGlobalService) bf.getBean("service");
        
        int intTypePdf = Pdf.InterpreteTypeFile(typePDF);
        
        //Declaration d'un button parcourir
        GestionInput parcourir = new GestionInput();
        parcourir.addtype("button");
        switch(intTypePdf){
            case 0 : parcourir.addOnclick("javascript:Ajax('AJAXAPPLET','RecuperationPDF','null','typeFile=PAYE')");break;
            case 1 : parcourir.addOnclick("javascript:Ajax('AJAXAPPLET','RecuperationPDF','null','typeFile=NEWSLETTER')");break;
        }
        
        parcourir.addValue("Parcourir");
        parcourir.close();
        
        //Declaration d'un button selectionner tout
        GestionInput Toutselectionner = new GestionInput();
        Toutselectionner.addtype("button");
        Toutselectionner.addOnclick("javascript:SelectAll('idPDF')");
        Toutselectionner.addValue("Tout selectionner");
        Toutselectionner.close();
        
        //Declaration d'un button deselectionner tout
        GestionInput Toutdeselectionner = new GestionInput();
        Toutdeselectionner.addtype("button");
        Toutdeselectionner.addOnclick("javascript:DeSelectAll('idPDF')");
        Toutdeselectionner.addValue("Tout deselectionner");
        Toutdeselectionner.close();
        
        //Declaration d'un button joindre
        GestionInput joindrePaye = new GestionInput();
        joindrePaye.addtype("button");
        joindrePaye.addOnclick("javascript:Ajax('AJAXMAINFRAME','SendMail','champ')");
        joindrePaye.addValue("Joindre");
        joindrePaye.close();
        
        //Declaration d'un button joindre
        GestionInput joindreNewsletter = new GestionInput();
        joindreNewsletter.addtype("button");
        joindreNewsletter.addOnclick("javascript:Ajax('AJAXMAINFRAME','ChoixNewsLetter','champ')");
        joindreNewsletter.addValue("Joindre");
        joindreNewsletter.close();
        
        try {
            
            ArrayList<Pdf> listPdf = null;
            
            //Si il n'y a aucun PDF
            if(service.getPdfsByType(intTypePdf).isEmpty()){
                out.print(parcourir.getFLUX());
                out.print("<div id='AJAXAPPLET'>");
                out.print("Aucun PDF référencés");
                out.print("</div>");
            }
            //Si il y a des PDF
            else{
                //Affichage des liens en fonction des types PDF
                switch (intTypePdf) {
                    case PAYE:
                        out.print(parcourir.getFLUX());
                        out.print(Toutselectionner.getFLUX());
                        out.print(Toutdeselectionner.getFLUX());
                        out.print(joindrePaye.getFLUX());
                        listPdf = (ArrayList<Pdf>) service.getPdfsByType(PAYE);
                        
                        out.print("<div id='AJAXAPPLET'>");
                        List_PDF.showCheckBox(listPdf);
                        out.print("</div>");
                        
                        break;
                    case NEWSLETTER :
                        out.print(parcourir.getFLUX());
                        out.print(joindreNewsletter.getFLUX());
                        listPdf = (ArrayList<Pdf>) service.getPdfsByType(NEWSLETTER);
                        
                        out.print("<div id='AJAXAPPLET'>");
                        List_PDF.showCheckBox(listPdf);
                        out.print("</div>");
                        
                        break;
                    default:
                        break;
                }
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