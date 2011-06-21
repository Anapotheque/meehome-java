package Models.TOOLS.iText.COURRIER;

import Models.COURRIER.COURRIER_ES.CourriersDAO;
import Models.ETUDES.Affaires.AffairesDAO;
import Models.ETUDES.Agents.AgentsDAO;
import Models.ETUDES.Etudes.EtudesDAO;
import Models.ETUDES.Even.EvenDAO;
import Models.ETUDES.Gares.GaresDAO;
import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Controleur.MegaControleur;
import Models.TOOLS.Date.DataDate;
import Models.TOOLS.Utilisateurs.UtilisateursDAO;
import Models.TOOLS.iText.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import java.awt.Font;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

//RABALLAND Romain v3.0

public class GenererCourrier extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        UtilisateursDAO utilisateur = null;
        utilisateur = creerObjet(request,utilisateur);
        
        CourriersDAO courrier = null;
        courrier = creerObjet(request,courrier);
        
        for(int i = 1; i <= courrier.nombreExemplaire; i ++)
            ImpressionPDF.ImprimePDF(utilisateur.NamePDF, "Courrier");
        
        if(!courrier.copie.equals(""))
            ImpressionPDF.ImprimePDF(utilisateur.NamePDF, "Copie");
        
        if(!courrier.numeroSesco.equals(""))
            ImpressionPDF.ImprimePDF(utilisateur.NamePDF, "Suivi");
        
        if(!courrier.dateIndice.equals("") || courrier.codeLettre.equals("33"))
            ImpressionPDF.ImprimePDF(utilisateur.NamePDF, "AccuseReception");
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        TOOLS_HTML.getMessage(out,"h2","Vos fichiers sont en cours d'impression");
    }
    
    public static void genererPDF(HttpServletRequest request)
    throws ServletException, IOException, DocumentException {
        
        GregorianCalendar d = new GregorianCalendar();
        
        int annee = d.get(Calendar.YEAR); 
        
        String years = ""+annee;
        char num1 = years.charAt(2);
        char num2 = years.charAt(3);
        
        years = ""+num1+num2;
        
        String Date = DataDate.GetDate();
        
        UtilisateursDAO utilisateur = null;
        utilisateur = creerObjet(request, utilisateur);
        
        AgentsDAO agent = null;
        agent = creerObjet(request, agent);
        
        CourriersDAO courrier = null;
        courrier = creerObjet(request, courrier);
        
        EtudesDAO etude = null;
        etude = creerObjet(request, etude);
        
        GaresDAO gare = null;
        gare = creerObjet(request, gare);
        
        AffairesDAO affaire = null;
        affaire = creerObjet(request, affaire);
        
        EvenDAO even = null;
        even = creerObjet(request, even);
        
        try {
            
            String police = "Univers 57 Condensed";
            
            Document document = null;
            
            document = GetDeclarationPDF(document, utilisateur,"Courrier");
            
            Image png = Image.getInstance("D:\\images\\sncf-ptt.jpg");
            png.setAbsolutePosition(450, 750);
            document.add(png);
            
            Paragraph titre = new Paragraph("DIRECTION DE MARSEILLE",FontFactory.getFont(police, 10, Font.ITALIC, new Color(0xD2,0x69,0x1E)));
            titre.setAlignment(Element.ALIGN_LEFT) ;
            document.add(titre) ;
            
            Paragraph adresse = new Paragraph("\nDELEGATION REGIONALE INFRASTRUCTURE\nPOLE REGIONAL INGENIERIE\nGROUPE ETUDES SIGNALISATION (PRI-MR-ES)\nBP 22 - 1, Bd Camille Flammarion 13234 MARSEILLE CEDEX 04",FontFactory.getFont(police, 8, 0, new Color(0, 0, 0))) ;
            adresse.setAlignment(Element.ALIGN_LEFT) ;
            document.add(adresse) ;
            
            ArrayList listInfos = null;
            listInfos = new ArrayList();
            listInfos = agent.GetInfosAgentCourrier(courrier.codeAgent);
            
            Paragraph entete1 = new Paragraph("\nAffaire suivie par "+listInfos.get(0)+" "+listInfos.get(1),FontFactory.getFont(police, 10, Font.ITALIC, new Color(0, 0, 0))) ;
            entete1.setAlignment(Element.ALIGN_LEFT) ;
            document.add(entete1) ;
            
            Paragraph entete1_1 = new Paragraph("\nTel : "+listInfos.get(2)+" ("+listInfos.get(3)+")\nMail : "+listInfos.get(4),FontFactory.getFont(police, 10, 0, new Color(0, 0, 0))) ;
            entete1_1.setAlignment(Element.ALIGN_LEFT) ;
            document.add(entete1_1) ;
            
            Paragraph entete2 = new Paragraph("Monsieur le Directeur de l'EVEN de "+courrier.destinataire,FontFactory.getFont(police, 12, Font.BOLD, new Color(0, 0, 0))) ;
            entete2.setAlignment(Element.ALIGN_RIGHT) ;
            document.add(entete2) ;
            
            Paragraph entete3 = new Paragraph("N/REF : PRI.MR.ES / "+years+" / "+listInfos.get(5),FontFactory.getFont(police, 10, Font.BOLD, new Color(0, 0, 0))) ;
            entete3.setAlignment(Element.ALIGN_LEFT) ;
            document.add(entete3) ;
            
            Paragraph entete4 = new Paragraph("Affaire n° "+courrier.Num_Affaire,FontFactory.getFont(police, 10, Font.ITALIC, new Color(0, 0, 0))) ;
            entete4.setAlignment(Element.ALIGN_LEFT) ;
            document.add(entete4) ;
            
            Paragraph objet = new Paragraph("\nObjet : "+courrier.objet,FontFactory.getFont(police, 10, Font.BOLD, new Color(0, 0, 0))) ;
            objet.setAlignment(Element.ALIGN_LEFT) ;
            document.add(objet) ;
            
            Paragraph date = new Paragraph("\n\n\n\nMarseille, le "+Date,FontFactory.getFont(police, 10, 0, new Color(0, 0, 0))) ;
            date.setAlignment(Element.ALIGN_LEFT) ;
            document.add(date) ;
            
            Paragraph lettre = new Paragraph("\n"+courrier.texte,FontFactory.getFont(police, 10, 0, new Color(0, 0, 0))) ;
            lettre.setAlignment(Element.ALIGN_JUSTIFIED) ;
            document.add(lettre) ;
            
            Paragraph texteEnGras = new Paragraph("\n"+courrier.texteGras,FontFactory.getFont(police, 10, Font.BOLD, new Color(0, 0, 0))) ;
            texteEnGras.setAlignment(Element.ALIGN_JUSTIFIED) ;
            document.add(texteEnGras) ;
            
            Image jpg = Image.getInstance("D:\\images\\AFAQ.jpg");
            jpg.setAbsolutePosition(10, 10);
            document.add(jpg);
            
            if(!courrier.PJ.equals("")){
                Paragraph pj = new Paragraph("\n\n\nPIECES JOINTES : "+courrier.PJ,FontFactory.getFont(police, 10, Font.BOLD, new Color(0, 0, 0)));
                pj.setAlignment(Element.ALIGN_LEFT) ;
                document.add(pj) ;
            }          
            
            // step 5: we close the document (the outputstream is also closed internally)
            document.close();
            
            if(!courrier.copie.equals("")){
                document = GetDeclarationPDF(document, utilisateur,"Copie");
                
                Paragraph copies = new Paragraph("Copies : "+courrier.copie,FontFactory.getFont(police, 10, Font.BOLD, new Color(0, 0, 0))) ;
                copies.setAlignment(Element.ALIGN_LEFT) ;
                document.add(copies) ;
                
                document.close();
            }
            
            if(!courrier.numeroSesco.equals("")){
                document = GetDeclarationPDF(document, utilisateur,"Suivi");
                
                Paragraph suivi = new Paragraph("suivi : \n" +
                        "Imprimé destiné au suivi des etudes à remettre à Denis LECLERE.\n" +
                        "\n\n\n" +
                        "Envoi des schémas concernant : \n" +
                        "Code Affaire : "+courrier.Num_Affaire+"\n" +
                        "Agent : "+agent.GetnomAgent(courrier.codeAgent)+"\n" +
                        "Collection : "+gare.GetnomGare(courrier.codeGare)+"\n" +
                        "Objet Affaire : "+courrier.objet+"\n" +
                        "Indice : "+courrier.indice+"\n" +
                        "Schéma envoyé le : "+Date+"\n" +
                        "Classé dans SESCO "+courrier.numeroSesco,FontFactory.getFont(police, 10, Font.BOLD, new Color(0, 0, 0))) ;
                
                suivi.setAlignment(Element.ALIGN_LEFT) ;
                document.add(suivi) ;
                
                document.close();
            }
            
            if(!courrier.dateIndice.equals("") || courrier.codeLettre.equals("33")){
                
                document = GetDeclarationPDF(document, utilisateur,"AccuseReception");
                
                Image img = Image.getInstance("D:\\images\\sncf-ptt.jpg");
                img.setAbsolutePosition(450, 750);
                document.add(img);
                
                Paragraph titre2 = new Paragraph("DIRECTION DE MARSEILLE",FontFactory.getFont(police, 10, Font.ITALIC, new Color(0xD2,0x69,0x1E)));
                titre2.setAlignment(Element.ALIGN_LEFT) ;
                document.add(titre2) ;
                
                Paragraph adresse2 = new Paragraph("\nDELEGATION REGIONALE INFRASTRUCTURE\nPOLE REGIONAL INGENIERIE\nGROUPE ETUDES SIGNALISATION (PRI-MR-ES)\nBP 22 - 1, Bd Camille Flammarion 13234 MARSEILLE CEDEX 04",FontFactory.getFont(FontFactory.TIMES, 8, 0, new Color(0, 0, 0))) ;
                adresse2.setAlignment(Element.ALIGN_LEFT) ;
                document.add(adresse2) ;
                
                ArrayList listInfos2 = null;
                listInfos2 = new ArrayList();
                listInfos2 = agent.GetInfosAgentCourrier(courrier.codeAgent);
                
                Paragraph entete5 = new Paragraph("\nAffaire suivie par "+listInfos2.get(0)+" "+listInfos2.get(1),FontFactory.getFont(police, 10, Font.ITALIC, new Color(0, 0, 0))) ;
                entete5.setAlignment(Element.ALIGN_LEFT) ;
                document.add(entete5) ;
                
                Paragraph entete2_1 = new Paragraph("\nTel : "+listInfos2.get(2)+" ("+listInfos2.get(3)+")\nMail : "+listInfos2.get(4),FontFactory.getFont(police, 10, 0, new Color(0, 0, 0))) ;
                entete2_1.setAlignment(Element.ALIGN_LEFT) ;
                document.add(entete2_1) ;
                
                Paragraph entete6 = new Paragraph("N/REF : PRI.MR.ES / "+years+" / "+listInfos.get(5),FontFactory.getFont(police, 10, Font.BOLD, new Color(0, 0, 0))) ;
                entete6.setAlignment(Element.ALIGN_LEFT) ;
                document.add(entete6) ;
                
                Paragraph entete7 = new Paragraph("Affaire n° "+courrier.Num_Affaire,FontFactory.getFont(police, 10, Font.ITALIC, new Color(0, 0, 0))) ;
                entete7.setAlignment(Element.ALIGN_LEFT) ;
                document.add(entete7) ;
                
                Paragraph entete8 = new Paragraph("ACCUSE DE RECEPTION",FontFactory.getFont(police, 18, Font.BOLD, new Color(0, 0, 0))) ;
                entete8.setAlignment(Element.ALIGN_CENTER) ;
                document.add(entete8) ;
                
                Paragraph entete9 = new Paragraph("\nObjet : "+courrier.objet,FontFactory.getFont(police, 10, Font.BOLD, new Color(0, 0, 0))) ;
                entete9.setAlignment(Element.ALIGN_LEFT) ;
                document.add(entete9) ;
                
                Paragraph entete10 = new Paragraph("\nDATE D'EXPEDITION : "+Date,FontFactory.getFont(police, 10, Font.BOLD, new Color(0, 0, 0))) ;
                entete10.setAlignment(Element.ALIGN_LEFT) ;
                document.add(entete10) ;
                
                Paragraph entete11 = new Paragraph("\nDocuments d'exécution : "+courrier.GetnomLettre(courrier.codeLettre),FontFactory.getFont(police, 10, 0, new Color(0, 0, 0))) ;
                entete11.setAlignment(Element.ALIGN_LEFT) ;
                document.add(entete11) ;
                
                Paragraph entete12 = new Paragraph("\nIndice : "+courrier.indice+"      Date de l'indice : "+courrier.dateIndice,FontFactory.getFont(police, 10, 0, new Color(0, 0, 0))) ;
                entete12.setAlignment(Element.ALIGN_LEFT) ;
                document.add(entete12) ;
                
                Paragraph contenu1 = new Paragraph("\nRemarques et particularités : \n" +
                        "...................................................................................................................................................................................................\n" +
                        "...................................................................................................................................................................................................\n" +
                        "...................................................................................................................................................................................................\n" +
                        "...................................................................................................................................................................................................\n" +
                        "",FontFactory.getFont(police, 10, 0, new Color(0, 0, 0))) ;
                contenu1.setAlignment(Element.ALIGN_LEFT) ;
                document.add(contenu1) ;
                
                Paragraph contenu2 = new Paragraph("\nDès réception de ces documents, merci de retourner au Pôle Régional Ingénierie, à l'attention de Monsieur Robert MANCINI, Chef du groupe PRI.MR.ES, cet accusé de réception dûment daté et signé après vérification du contenu de l'envoi",FontFactory.getFont(police, 12, 0, new Color(0, 0, 0))) ;
                contenu2.setAlignment(Element.ALIGN_CENTER) ;
                document.add(contenu2) ;
                
                Paragraph contenu3 = new Paragraph("\nDATE DE RECEPTION                                       SIGNATURE",FontFactory.getFont(police, 10, Font.BOLD, new Color(0, 0, 0))) ;
                contenu3.setAlignment(Element.ALIGN_CENTER) ;
                document.add(contenu3) ;
                
                Image jpg1 = Image.getInstance("D:\\images\\AFAQ.jpg");
                jpg1.setAbsolutePosition(10, 10);
                document.add(jpg1);
                
                document.close();
            }
            
        } catch(DocumentException de) {
            de.printStackTrace();
            System.err.println("document: " + de.getMessage());
        } catch(SQLException ex) {
            ex.printStackTrace();
            System.err.println("SQL ERREUR : " + ex.getMessage());
        }
    }
    
    private static Document GetDeclarationPDF(Document document, UtilisateursDAO utilisateur, String nom_Fichier)
    throws DocumentException {
        try {
            
            // step 1
            document = new Document(PageSize.A4, 25, 25, 25, 25);
            
            // step 2:
            // we create a writer that listens to the document
            // and directs a PDF-stream to a file
            PdfWriter.getInstance(document, new FileOutputStream(""+cheminEnregistrementPDF+utilisateur.NamePDF+"_"+nom_Fichier+".pdf"));
            
            // step 3: we open the document
            document.open();
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (DocumentException ex) {
            ex.printStackTrace();
        }
        return document;
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
}
