package Models.TOOLS.iText.DVSG;

import Models.TOOLS.Controleur.MegaControleur;
import Models.TOOLS.Utilisateurs.UtilisateursDAO;
import Models.TOOLS.iText.*;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Font;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

//RABALLAND Romain v3.0

public class Impression extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        UtilisateursDAO utilisateur = null;
        utilisateur = creerObjet(request,utilisateur);
        
        ImpressionPDF.ImprimePDF(utilisateur.NamePDF, "data");
        
    }
    
    public static void genererPDF(HttpServletResponse response, HttpServletRequest request, float[] widths, ArrayList Titre, ArrayList Show, String NAME)
    throws ServletException, IOException {
        
        GregorianCalendar d = new GregorianCalendar();
        int jour = d.get(Calendar.DAY_OF_MONTH);
        int mois = d.get(Calendar.MONTH);
        int annee = d.get(Calendar.YEAR);
        
        String Date = jour+"-"+mois+"-"+annee;
        
        UtilisateursDAO utilisateur = null;
        utilisateur = creerObjet(request, utilisateur);
        
        
        // step 1
        Document document = new Document(PageSize.A4.rotate(), 10, 10, 10, 10);
        try {
            // step 2:
            // we create a writer that listens to the document
            // and directs a PDF-stream to a file
            PdfWriter.getInstance(document, new FileOutputStream(""+cheminEnregistrementPDF+utilisateur.NamePDF+"_data.pdf"));
            
            // step 3: we open the document
            document.open();
            
            Image png = Image.getInstance("D:\\images\\sncf-ptt.jpg");
            png.setAbsolutePosition(10, 540);
            document.add(png);
            
            Paragraph titre = new Paragraph("SNCF "+NAME+" ("+Show.size()/Titre.size()+" )",FontFactory.getFont(FontFactory.TIMES, 12, Font.BOLD, new Color(0, 0, 255))) ;
            titre.setAlignment(Element.ALIGN_CENTER) ;
            titre.setSpacingAfter(30f) ;
            document.add(titre) ;
            
            Paragraph divers = new Paragraph();
            
            PdfPTable table = new PdfPTable(widths);
            
            PdfPCell cell = null;
            
            for(int i = 0 ; i < Titre.size() ; i ++){
                String chaine = ""+Titre.get(i);
                Paragraph contenu = new Paragraph(chaine.toUpperCase(),FontFactory.getFont(FontFactory.TIMES, 8, Font.BOLD, new Color(0, 0, 0))) ;
                
                cell = new PdfPCell(contenu);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(new Color(0xAA, 0xAA, 0xAA));
                table.addCell(cell);
            }
            
            int h = 0;
            int j = 0;
            for(int i = 0 ; i < Show.size() ; i ++) {
                Paragraph contenu = new Paragraph(""+Show.get(i),FontFactory.getFont(FontFactory.TIMES, 7, Font.BOLD, new Color(0, 0, 0))) ;
                cell = new PdfPCell(contenu);
                
                if(j == 0 && h < Titre.size()){
                    cell.setBackgroundColor(new Color(0xFF, 0xFF, 0xFF));
                    h++;
                    j = 0;
                } else{
                    cell.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
                    j=1;
                    h++;
                }
                if(h == Titre.size()*2 && j == 1){
                    j=0;
                    h=0;
                }
                table.addCell(cell);
            }
            
            table.setWidthPercentage(100);
            document.add(table);
            
        } catch(DocumentException de) {
            de.printStackTrace();
            System.err.println("document: " + de.getMessage());
        }
        
        // step 5: we close the document (the outputstream is also closed internally)
        document.close();
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
