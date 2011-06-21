package Models.TOOLS.iText.CALQUES;

import Models.CALQUES.CalquesDAO;
import Models.CALQUES.CasesDAO;
import Models.ETUDES.Agents.AgentsDAO;
import Models.ETUDES.Entreprises.EntreprisesDAO;
import Models.ETUDES.Gares.GaresDAO;
import Models.TOOLS.Controleur.MegaControleur;
import Models.TOOLS.Date.DataDate;
import Models.TOOLS.Utilisateurs.UtilisateursDAO;
import com.lowagie.text.BadElementException;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import java.awt.Font;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

//RABALLAND Romain v3.0

public class GenererFichedeSortieCalques extends MegaControleur {
    
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
            
            CasesDAO cases = null;
            cases = creerObjet(request,cases);
            
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
            
            document = GetDeclarationPDF(document, utilisateur,"FicheCalques");
            
            ArrayList listInfos = null;
            listInfos = new ArrayList();
            listInfos = agent.GetInfosAgentCourrier(calque.codeAgent);
            
            Paragraph titre = new Paragraph("FICHE DE SORTIE DES CALQUES\n\n\n",FontFactory.getFont(police, 20, Font.BOLD, new Color(0, 0, 0))) ;
            titre.setAlignment(Element.ALIGN_CENTER) ;
            document.add(titre) ;
            
            Paragraph entete1 = new Paragraph("Agent : "+listInfos.get(0)+" "+listInfos.get(1),FontFactory.getFont(police, 12, 0, new Color(0, 0, 0))) ;
            entete1.setAlignment(Element.ALIGN_LEFT) ;
            document.add(entete1) ;
            
            Paragraph corps1 = new Paragraph("Entreprise : "+entreprise.GetnomEntreprise(calque.codeEntreprise),FontFactory.getFont(police, 12, 0, new Color(0, 0, 0))) ;
            corps1.setAlignment(Element.ALIGN_LEFT) ;
            document.add(corps1) ;
            
            Paragraph corps3 = new Paragraph("Date de sortie : "+DataDate.GetDate(),FontFactory.getFont(police, 12, 0, new Color(0, 0, 0))) ;
            corps3.setAlignment(Element.ALIGN_LEFT) ;
            document.add(corps3) ;
            
            Paragraph corps4 = new Paragraph("Collection : "+gare.GetnomGare(calque.codeCollection)+"     Km : "+gare.GetKmGare(calque.codeCollection)+"\n\n",FontFactory.getFont(police, 12, 0, new Color(0, 0, 0))) ;
            corps4.setAlignment(Element.ALIGN_LEFT) ;
            document.add(corps4) ;
            
            ArrayList list = null;
            list = new ArrayList();
            
            list = calque.ReOrganisationCaseCalquePourImpression(listCalque);
            
            String nomCase = "";
            
            for(int i = 0 ; i < list.size() ; i++){
                Chunk calques = null;
                
                if(!nomCase.equals(""+list.get(i)) || nomCase.equals("")){
                    calques = new Chunk("\n\nCase "+list.get(i)+"\n",FontFactory.getFont(police, 18, Font.BOLD, new Color(0, 0, 0))) ;
                    document.add(calques) ;
                }
                i++;
                if(nomCase != list.get(i-1)){
                    nomCase = ""+list.get(i-1);
                    calques = new Chunk(""+list.get(i)+",",FontFactory.getFont(police, 12, 0, new Color(0, 0, 0))) ;
                    document.add(calques) ;
                }
            }
            
            // step 5: we close the document (the outputstream is also closed internally)
            document.close();
            
            
        } catch (BadElementException ex) {
            ex.printStackTrace();
        } catch (DocumentException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
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
