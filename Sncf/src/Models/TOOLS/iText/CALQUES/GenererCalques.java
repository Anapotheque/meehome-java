package Models.TOOLS.iText.CALQUES;

import Models.CALQUES.CalquesDAO;
import Models.ETUDES.Agents.AgentsDAO;
import Models.ETUDES.Entreprises.EntreprisesDAO;
import Models.ETUDES.Gares.GaresDAO;
import Models.TOOLS.Controleur.MegaControleur;
import Models.TOOLS.Date.DataDate;
import Models.TOOLS.Utilisateurs.UtilisateursDAO;
import com.lowagie.text.BadElementException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;

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
import com.lowagie.text.Chunk;
import com.lowagie.text.Image;
import java.awt.Font;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

//RABALLAND Romain v3.0

public class GenererCalques extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        
        
        out.close();
    }
    
    public static void genererPDF(HttpServletRequest request, ArrayList listCalque)
    throws ServletException, IOException, DocumentException {
        
        try {
            
            UtilisateursDAO utilisateur = null;
            utilisateur = creerObjet(request,utilisateur);
            
            CalquesDAO calque = null;
            calque = creerObjet(request,calque);
            
            GaresDAO gare = null;
            gare = creerObjet(request,gare);
            
            EntreprisesDAO entreprise = null;
            entreprise = creerObjet(request,entreprise);
            
            AgentsDAO agent = null;
            agent = creerObjet(request,agent);
            
            String police = "Univers 57 Condensed";
            
            Document document = null;
            
            document = GetDeclarationPDF(document, utilisateur,"CourrierCalques");
            
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
            listInfos = agent.GetInfosAgentCourrier(calque.codeAgent);
            
            Paragraph entete1 = new Paragraph("\nAffaire suivie par "+listInfos.get(0)+" "+listInfos.get(1),FontFactory.getFont(police, 10, Font.ITALIC, new Color(0, 0, 0))) ;
            entete1.setAlignment(Element.ALIGN_LEFT) ;
            document.add(entete1) ;
            
            Paragraph entete1_1 = new Paragraph("\nTel : "+listInfos.get(2)+" ("+listInfos.get(3)+")\nMail : "+listInfos.get(4),FontFactory.getFont(police, 10, 0, new Color(0, 0, 0))) ;
            entete1_1.setAlignment(Element.ALIGN_LEFT) ;
            document.add(entete1_1) ;
            
            Paragraph corps1 = new Paragraph("\n\nEntreprise : "+entreprise.GetnomEntreprise(calque.codeEntreprise),FontFactory.getFont(police, 12, 0, new Color(0, 0, 0))) ;
            corps1.setAlignment(Element.ALIGN_LEFT) ;
            document.add(corps1) ;
            Paragraph corps2 = new Paragraph("Objet : remise de calques",FontFactory.getFont(police, 12, 0, new Color(0, 0, 0))) ;
            corps2.setAlignment(Element.ALIGN_LEFT) ;
            document.add(corps2) ;
            Paragraph corps3 = new Paragraph("Marseille, le "+DataDate.GetDate(),FontFactory.getFont(police, 12, 0, new Color(0, 0, 0))) ;
            corps3.setAlignment(Element.ALIGN_LEFT) ;
            document.add(corps3) ;
            Paragraph corps4 = new Paragraph("Collection : "+gare.GetnomGare(calque.codeCollection),FontFactory.getFont(police, 12, 0, new Color(0, 0, 0))) ;
            corps4.setAlignment(Element.ALIGN_LEFT) ;
            document.add(corps4) ;
            Paragraph corps5 = new Paragraph("Km : "+gare.GetKmGare(calque.codeCollection),FontFactory.getFont(police, 12, 0, new Color(0, 0, 0))) ;
            corps5.setAlignment(Element.ALIGN_LEFT) ;
            document.add(corps5) ;
            Paragraph corps6 = new Paragraph("\n\nVeuillez trouver ci-joint les calques suivants : \n\n",FontFactory.getFont(police, 12, 0, new Color(0, 0, 0))) ;
            corps6.setAlignment(Element.ALIGN_LEFT) ;
            document.add(corps6) ;
            
            for(int i = 0 ; i < listCalque.size() ; i++){
                Chunk calques = null;
                if(listCalque.size()-1 != i)
                    calques = new Chunk(""+calque.GetNumeroCalque(""+listCalque.get(i))+",",FontFactory.getFont(police, 12, Font.BOLD, new Color(0, 0, 0))) ;
                else
                    calques = new Chunk(""+calque.GetNumeroCalque(""+listCalque.get(i)),FontFactory.getFont(police, 12, Font.BOLD, new Color(0, 0, 0))) ;
                document.add(calques) ;
            }
            
            Paragraph corps8 = new Paragraph("\n\nVeuillez me retourner copie de cette lettre apres en avoir signé l'accusé de réception.\n\n\n                                Signature : ",FontFactory.getFont(police, 12, 0, new Color(0, 0, 0))) ;
            corps8.setAlignment(Element.ALIGN_LEFT) ;
            document.add(corps8) ;
            
            // step 5: we close the document (the outputstream is also closed internally)
            document.close();
            
            
        } catch (BadElementException ex) {
            ex.printStackTrace();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (DocumentException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
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
