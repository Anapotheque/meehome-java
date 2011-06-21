/*
 * UploadPayePDF.java
 *
 * Created on 10 avril 2007, 11:46
 */

package fr.alliance.docalliance.ajax.mail.tools;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;
import com.oreilly.servlet.MultipartRequest;
import fr.alliance.docalliance.controleur.MetaControleur;
import fr.alliance.docalliance.dao.DaoCreateException;
import fr.alliance.docalliance.model.Pdf;
import fr.alliance.docalliance.service.IGlobalService;
import fr.alliance.docalliance.tools.html.Input.GestionInput;
import fr.alliance.docalliance.tools.html.Scripts.GestionScripts;
import fr.alliance.docalliance.tools.html.css.GestionCss;
import fr.alliance.docalliance.tools.html.message.MessageAlert;
import fr.alliance.docalliance.tools.system.DataDate;
import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.commons.lang.StringUtils;

/**
 * Servlet pour uploader un fichier pdf contenant plusieurs PDF
 * @author Yves Le Rumeur, Romain Raballand
 * @version 1.0
 */
public class UploadPayePDF extends MetaControleur {
    
    private static String saveFile;
    private final static String mademoiselle = "mademoiselle";
    private final static String madame = "madame";
    private final static String monsieur = "monsieur";
    private final static String emploi = "emploi";
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        // Recuperation de la session
        HttpSession session = request.getSession(true);
        
        PrintWriter out = response.getWriter();
        
        //Definition du fichier de reception
        saveFile = new String();
        saveFile = "C:\\PDF\\PAYE\\"+DataDate.getActuallyDate()+"\\";
        File rep = new File(saveFile);
        rep.mkdir();
        
        //UPLOAD du fichier selectionné
        String nameFile = UpLoad(request,out);
        
        if(nameFile != null){
            
            out.print("Le document "+nameFile +" est bien téléchargé<br>");
            
            try {
                ArrayList<String> listPdf = SplitPDF(saveFile+nameFile);
                for(int i = 0 ; i < listPdf.size(); i ++ ){
                    UpdateDataBaseForPDF(request,listPdf.get(i));
                }
                
            } catch (IOException e) {
                MessageAlert message = new MessageAlert("Probleme : <br><br>"+e.getMessage(),MessageAlert.ERREUR);
                out.print(message.getFLUX());
                e.printStackTrace();
            } catch (DocumentException e) {
                MessageAlert message = new MessageAlert("Probleme : <br><br>"+e.getMessage(),MessageAlert.ERREUR);
                out.print(message.getFLUX());
                e.printStackTrace();
            } catch (DaoCreateException e) {
                MessageAlert message = new MessageAlert("Probleme : <br><br>"+e.getMessage(),MessageAlert.ERREUR);
                out.print(message.getFLUX());
                e.printStackTrace();
            }
            
        }else{
            
            MessageAlert message = new MessageAlert("Problème lors du téléchargement du document",MessageAlert.ERREUR);
            out.print(message.getFLUX());
            
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
     * Permet de splitter le document PDF generique en plusieurs documents pdf portant le nom et le prenom du destinataire de la feuille de paye
     * les docuements pdf sont chargés au meme emplacement que le document generique
     * @param doc Chemin d'acces au document générique
     * @return ArrayList<String> une liste des noms des documents pdf
     */
    private ArrayList<String> SplitPDF(String doc) throws IOException, DocumentException{
        
        //declaration du nombre de page
        int pagenumber = 0;
        
        //Ouverture du flux de lecture du document générique
        PdfReader reader = new PdfReader(doc);
        //Recuperation du nombre de page
        pagenumber = reader.getNumberOfPages();
        
        //Creation d'un flux d'ecoute du document
        ArrayList<String> listPdf = new ArrayList<String>();
        ArrayList<PdfWriter> listPdfWriter = new ArrayList<PdfWriter>();
        ArrayList<Document> listDocument = new ArrayList<Document>();
        
        //On parcourt l'ensemble des du document générique
        for(int i = 0; i < pagenumber; i ++){
            
            //On ajoute à la liste le document courant
            listDocument.add(new Document(reader.getPageSizeWithRotation(i+1)));
            
            //On recupere le contenu de la page courante du document générique qui est en byte to string
            String oldtext = new String(reader.getPageContent(i+1));
            
            //traitement de la chaine pour recuperer les caracteres
            String text = new String();
            char temptext = ' ';
            char paranthese1 = '(';
            
            //on parcours notre chaine
            for(int k = 0; k < oldtext.length(); k ++){
                
                //recuperation du caractere courant
                char temp = oldtext.charAt(k);
                
                //on recupere la valeur contenue entre les parentheses '(x)'
                if(temp == paranthese1){
                    temptext = oldtext.charAt(k+1);
                    text += ""+temptext;
                    k++;
                }
            }
            
            //On split la chaine par rapport au caractere de separation ' '
            String[] tabText = text.split(" ");
            
            //Declaration des chaines de recuperations des differentes valeurs
            String nom = new String();
            String prenom = new String();
            String nomPdf = new String();
            
            //Declaration d'un tableau de string temporaire pour le traitement des noms et prenoms
            ArrayList<String> listChaineTemporaire = new ArrayList<String>();
            
            //Init d'un compteur de chaine de table
            int count = 0;
            
            //on parcours notre tableau jusqu'aux differentes civilités
            for(int k = 0 ; k < tabText.length ; k ++){
                
                if(tabText[k].equalsIgnoreCase(mademoiselle) ||  tabText[k].equalsIgnoreCase(madame) || tabText[k].equalsIgnoreCase(monsieur)){
                    
                    //Recuperation des valeurs nom et prenom et incrementation du compteur
                    for(int j = k+1 ; j < tabText.length; j ++){
                        if(tabText[j].equalsIgnoreCase(emploi)){
                            break;
                        }
                        listChaineTemporaire.add(tabText[j]);
                        count ++;
                    }
                    
                    //Traitement du cas : nom ou prenom uniquement
                    if(count == 1){
                        nom = listChaineTemporaire.get(0);
                        //Traitement du cas normal :  Nom et prenom
                    }else if(count == 2){
                        nom = listChaineTemporaire.get(0);
                        prenom = listChaineTemporaire.get(1);
                        //Traitement du cas : ex : Le Kerlann Jean-Christophe
                    }else if(count > 2){
                        for(int j = 0; j < listChaineTemporaire.size() ; j ++){
                            if(listChaineTemporaire.get(j).toUpperCase().equals(listChaineTemporaire.get(j))){
                                nom += listChaineTemporaire.get(j);
                            }else{
                                prenom += listChaineTemporaire.get(j);
                            }
                        }
                    }
                    break;
                }
            }
            
            //Concatenation du nom et prenom pour titre du document pdf
            if(count == 1){
                nomPdf = nom+".pdf";
            }else{
                nomPdf = nom+"_"+prenom+".pdf";
            }
            
            listPdfWriter.add(PdfWriter.getInstance(listDocument.get(i), new FileOutputStream(saveFile+nomPdf)));
            listPdf.add(nomPdf);
        }
        
        //Ouverture du document
        for(int j = 0; j < listDocument.size(); j ++){
            
            listDocument.get(j).open();
            PdfContentByte cb = listPdfWriter.get(j).getDirectContent();
            
            //Ajout du contenu
            listDocument.get(j).setPageSize(reader.getPageSizeWithRotation(j+1));
            listDocument.get(j).newPage();
            PdfImportedPage page = listPdfWriter.get(j).getImportedPage(reader, j+1);
            int rotation = reader.getPageRotation(j+1);
            if (rotation == 90 || rotation == 270) {
                cb.addTemplate(page, 0, -1f, 1f, 0, 0, reader.getPageSizeWithRotation(j+1).height());
            } else {
                cb.addTemplate(page, 1f, 0, 0, 1f, 0, 0);
            }
            //Fermeture du document
            listDocument.get(j).close();
        }
        //On retourne la liste des noms des pdf splittés
        return listPdf;
    }
    
    public void UpdateDataBaseForPDF(HttpServletRequest request, String namePdf) throws DaoCreateException{
        
        //Recuperation du service
        IGlobalService service = getService(request);
        
        //creation d'un nouvel objet PDF
        Pdf pdf = new Pdf();
        pdf.setNom(namePdf);
        pdf.setAbsolutPath(saveFile+namePdf);
        pdf.setSection(Pdf.TYPE_PAYE);
        pdf.setServeur("");
        pdf.setSend(false);
        
        //on sauvegarde notre pdf
        service.saveOnePdf(pdf);
        
    }
}
