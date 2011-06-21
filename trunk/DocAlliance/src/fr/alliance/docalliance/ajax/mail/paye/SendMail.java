/*
 * SendMail.java
 *
 * Created on 20 mars 2007, 09:28
 */

package fr.alliance.docalliance.ajax.mail.paye;

import fr.alliance.docalliance.tools.html.Input.GestionInput;
import fr.alliance.docalliance.tools.html.message.MessageAlert;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import fr.alliance.docalliance.controleur.MetaControleur;
import fr.alliance.docalliance.dao.DaoFindException;
import fr.alliance.docalliance.model.AdresseMail;
import fr.alliance.docalliance.model.Pdf;
import fr.alliance.docalliance.service.IAdresseMail;
import fr.alliance.docalliance.service.IGlobalService;
import fr.alliance.docalliance.service.IPdfService;

/**
 *
 * @author Alliance-Concept
 * @version 1.0
 */

public class SendMail extends MetaControleur {
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        // Recupere la session
        HttpSession session = request.getSession(true);
        
        IGlobalService service = getService(request);
        
        //Initialisation du flux de sortie
        PrintWriter out = response.getWriter();
        
        //Declaration d'un button parcourir
        GestionInput retour = new GestionInput();
        retour.addtype("button");
        retour.addOnclick("javascript:Ajax('Accueil','AccueilPaye','null')");
        retour.addValue("Retour");
        retour.close();
        
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
        GestionInput envoyer = new GestionInput();
        envoyer.addtype("button");
        envoyer.addOnclick("javascript:Ajax('AJAXMAINFRAME','ActionMail','champ')");
        envoyer.addValue("Envoyer");
        envoyer.close();
                
        //Recuperation des informations formulaires ( Liste des pdfs selectionnés )
        ArrayList<Pdf> listPDF = new ArrayList<Pdf>();
        
        for(int i=1; i<= Integer.parseInt(request.getParameter("NombreSelect")); i++){
            String idPDF = new String(request.getParameter("idPDF"+i));
            try {
                listPDF.add(service.getPdfById(Integer.parseInt(idPDF)));
            } catch (DaoFindException e) {
                MessageAlert message = new MessageAlert("Probleme : <br><br>"+e.getMessage(),MessageAlert.ERREUR);
                out.print(message.getFLUX());
                e.printStackTrace();
            }
        }
        
        
        
        
        Hashtable<Pdf, AdresseMail> mainHashPdf = new Hashtable<Pdf, AdresseMail>(listPDF.size());
        ArrayList<AdresseMail> listAdresseMail = new ArrayList<AdresseMail>();
        try {
            //On traite les fichiers recuperés pour mettre à jour les listes nom et prenom
            listAdresseMail = this.getContactParPdf(listPDF,(IAdresseMail) service);
        } catch (DaoFindException e) {
            MessageAlert message = new MessageAlert("Probleme : <br><br>"+e.getMessage(),MessageAlert.ERREUR);
            out.print(message.getFLUX());
            e.printStackTrace();
        }
        boolean[] pdfIsOK = new boolean[listAdresseMail.size()];
        int nbErreur = 0;
        
        for(int i=0; i<listAdresseMail.size(); i++){
            //Si le resultat n'est pas null alors on ajoute une adresse mail
            //puis il est enlever de listPDF de tel sorte qu'il ne restera
            //dans cette liste que les pdf mal formattés
            if(listAdresseMail.get(i) != null){
                mainHashPdf.put(listPDF.get(i), listAdresseMail.get(i));
                pdfIsOK[i]=true;
            } else{
                pdfIsOK[i]=false;
                nbErreur ++;
            }
        }
        
        session.setAttribute("mainHashPdf", mainHashPdf);
        
        //Affichage du traitement
        out.print(retour.getFLUX());
        
        //Si il y a plus d'un envoi
        if(mainHashPdf.size() > 1){
            out.print(Toutselectionner.getFLUX());
            out.print(Toutdeselectionner.getFLUX());
        }
        
        if(!mainHashPdf.isEmpty()){
            out.print(envoyer.getFLUX());
        }
        
        out.print("<form id='champ'>");
        
        out.print("<table border=0 width=100% cellspacing = 0>");
        out.print("<tr style='font-weight : bold;'>");
        out.print("<td style ='color: red'>");
        
        if(!listPDF.isEmpty())
            out.print("PB : "+nbErreur);
        
        out.print("</td>");
        out.print("<td>NOM</td>");
        out.print("<td>PRENOM</td>");
        out.print("<td>ADRESSE</td>");
        out.print("<td>PDF</td>");
        out.print("</tr>");
        
        Iterator it = mainHashPdf.keySet().iterator();
        
        Pdf pdfTmp = null;
        AdresseMail adrMail = null;
        
        while(it.hasNext()){
            pdfTmp = (Pdf) it.next();
            adrMail = mainHashPdf.get(pdfTmp);
            out.print("<tr style='color: blue;'>");
            out.print("<td><input type='checkbox' name='idPDF' name='idPDF' value='"+pdfTmp.getIdPdf()+"' checked='checked'/></td>");
            out.println("<td>"+adrMail.getFirstName()+"</td>");
            out.println("<td>"+adrMail.getLastName()+"</td>");
            out.println("<td>"+adrMail.getAdresseMail()+"</td>");
            out.println("<td>"+pdfTmp.getNom()+"</td>");
            out.print("</tr>");
        }
        
        for(int i=0; i<listPDF.size(); i++){
            if(!pdfIsOK[i]){
                out.print("<tr style='color: red;'>");
                out.print("<td></td>");
                out.println("<td>"+"KO"+"</td>");
                out.println("<td>"+"KO"+"</td>");
                out.println("<td>Non referencé</td>");
                out.println("<td>"+listPDF.get(i).getNom()+"</td>");
                out.print("</tr>");
            }
        }
        
        out.print("</table>");
        out.print("</form>");
        
        //Fermeture du flux de sortie
        out.flush();
        out.close();
    }
    
    private ArrayList<AdresseMail> getContactParPdf(ArrayList<Pdf> listPdf, IAdresseMail service) throws DaoFindException{
        ArrayList<AdresseMail> listAdresseMail= new ArrayList<AdresseMail>();
        
        for(int j = 0; j<listPdf.size(); j++){
            String string = listPdf.get(j).getNom();
            string = string.replaceFirst(".pdf", "");
            String[] stringTab = string.split("_");
            if(stringTab.length != 2){
                listAdresseMail.add(j, null);
            } else{
                listAdresseMail.add(j,service.getAdresseMailByNomPrenom(stringTab[0], stringTab[1]));
            }
        }
        return listAdresseMail;
    }
}
