package Models.TOOLS.iText.TRAVAUX;

import Models.ETUDES.Affaires.AffairesDAO;
import Models.TOOLS.Controleur.MegaControleur;
import Models.TOOLS.Utilisateurs.UtilisateursDAO;
import Models.TRAVAUX.CommandesDAO;
import Models.TRAVAUX.TravauxDAO;
import com.lowagie.text.BadElementException;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

//RABALLAND Romain v3.0

public class GenerationPDFTRAVAUX extends MegaControleur {
    
    public static void genererPDF(HttpServletRequest request, String codeCommande, String numOE)
    throws ServletException, IOException, DocumentException {
        
        try {
            
            // <editor-fold defaultstate="collapsed" desc="OBJETS">
            UtilisateursDAO utilisateur = null;
            utilisateur = creerObjet(request,utilisateur);
            
            TravauxDAO travaux = null;
            travaux = creerObjet(request,travaux);
            
            AffairesDAO affaire = null;
            affaire = creerObjet(request,affaire);
            
            CommandesDAO commande = null;
            commande = creerObjet(request,commande);
            
            travaux.SetPDF(numOE);
            
            Paragraph data = null;
            Document document = null;
            String police = "Univers 57 Condensed";
            // </editor-fold>
            
            //DECLARATION DU DOCUMENT PDF
            document = GetDeclarationPDF(document, utilisateur,"FicheTravaux");
            
            // <editor-fold defaultstate="collapsed" desc="ENTETE DU PDF">
            Image png = Image.getInstance("D:\\images\\sncf-ptt.jpg");
            png.setAbsolutePosition(10, 540);
            document.add(png);
            
            data = new Paragraph("\nTABLEAU RECAPITULATIF DES TRAVAUX EFFECTUES A FACTURER",FontFactory.getFont(police, 12, Font.BOLD, new Color(0, 0, 255))) ;
            data.setAlignment(Element.ALIGN_CENTER) ;
            document.add(data) ;
            
            data = new Paragraph("\nEntreprise : "+commande.GetEntrepriseCommande(codeCommande)+" Commande : "+commande.GetNumCommande(codeCommande)+"\n\n",FontFactory.getFont(police, 10, 0, new Color(0, 0, 0))) ;
            data.setAlignment(Element.ALIGN_MIDDLE) ;
            document.add(data) ;
            // </editor-fold>
            
            // <editor-fold defaultstate="collapsed" desc="PREMIER TABLEAU RECAPITULATIF DE LA COMMANDE">
            String codeAffaire = ""+travaux.listPDF.get(0);
            
            data = new Paragraph("CODE AFFAIRE : "+codeAffaire+"        SYCOMORE : "+affaire.GetImputationAffaire(codeAffaire)+"    OBJET : "+travaux.InverseTransform(""+affaire.GetnomAffaire(codeAffaire))+"\n\n",FontFactory.getFont(police, 10, 0, new Color(0, 0, 255))) ;
            document.add(data) ;
            
            //NOUVEAU TABLEAU
            PdfPTable table = new PdfPTable(travaux.widthsShow);
            PdfPCell cell = null;
            
            //AFFICHAGE DU TITRE DES DONNEES
            for(int i = 0 ; i < travaux.list_TitrePDF.size() ; i ++){
                String chaine = ""+travaux.list_TitrePDF.get(i);
                data = new Paragraph(chaine.toUpperCase(),FontFactory.getFont(police, 6, 0, new Color(0, 0, 0))) ;
                cell = new PdfPCell(data);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(new Color(0xAA, 0xAA, 0xAA));
                table.addCell(cell);
            }
            
            int m= 1;
            
            //AFFICHAGE DES DONNEES
            for(int i = 1 ; i < travaux.listPDF.size() ; i ++) {
                String donnee = ""+travaux.listPDF.get(i);
                if(donnee.equals("0"))
                    donnee = "";
                data = new Paragraph(donnee,FontFactory.getFont(police, 6, 0, new Color(0, 0, 0))) ;
                cell = new PdfPCell(data);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                if(m == travaux.list_TitrePDF.size() && i!=travaux.listPDF.size()-1 && !codeAffaire.equals(""+travaux.listPDF.get(i+1))){
                    
                    //AJOUT DU TABLEAU AU DOCUMENT
                    table.setWidthPercentage(100);
                    document.add(table);
                    
                    i++;
                    m = 0;
                    
                    codeAffaire = ""+travaux.listPDF.get(i);
                    
                    data = new Paragraph("CODE AFFAIRE : "+codeAffaire+"        SYCOMORE : "+affaire.GetImputationAffaire(codeAffaire)+"    OBJET : "+travaux.InverseTransform(""+affaire.GetnomAffaire(codeAffaire))+"\n\n",FontFactory.getFont(police, 10, 0, new Color(0, 0, 255))) ;
                    document.add(data) ;
                    
                    table = new PdfPTable(travaux.widthsShow);
                    
                    for(int j = 0 ; j < travaux.list_TitrePDF.size() ; j ++){
                        String chaine = ""+travaux.list_TitrePDF.get(j);
                        data = new Paragraph(chaine.toUpperCase(),FontFactory.getFont(police, 6, 0, new Color(0, 0, 0))) ;
                        cell = new PdfPCell(data);
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setBackgroundColor(new Color(0xAA, 0xAA, 0xAA));
                        table.addCell(cell);
                    }
                } else if(m == travaux.list_TitrePDF.size() && i!=travaux.listPDF.size()-1) {
                    i++;
                    m = 0;
                }
                m++;
            }
            
            //AJOUT DU TABLEAU AU DOCUMENT
            table.setWidthPercentage(100);
            document.add(table);
            
            // </editor-fold>
            
            // <editor-fold defaultstate="collapsed" desc="DEUXIEME TABLEAU RECAPITULATIF DES TOTAUX">
            ArrayList list = new ArrayList();
            int j= 0, donnee = 0;
            
            for(int g = 0 ; g < travaux.list_TitrePDF.size()-1 ; g ++)
                list.add("0");
            
            //CALCULE DES NOMBRES MAXIMUM
            for(int i = 2 ; i < travaux.listPDF.size() ; i ++ ){
                if(j == travaux.list_TitrePDF.size()-1){
                    j = 0;
                    i = i +2;
                }
                donnee = Integer.parseInt(""+travaux.listPDF.get(i)) + Integer.parseInt(""+list.get(j));
                list.remove(j);
                list.add(j,""+donnee);
                j++;
            }
            
            //SEPERATEUR
            data = new Paragraph("\n",FontFactory.getFont(police, 12, 0, new Color(0, 0, 0))) ;
            document.add(data) ;
            
            
            //NOUVEAU TABLEAU
            table = new PdfPTable(travaux.widthsShow);
            
            list.add(0,"Total Quantité");
            
            //AFFICHAGE DU NOMBRE MAXIMAL
            for(int i = 0 ; i < travaux.list_TitrePDF.size() ; i ++) {
                data = new Paragraph(""+list.get(i),FontFactory.getFont(police, 6, 0, new Color(0, 0, 0))) ;
                cell = new PdfPCell(data);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                if( i == 0 ) cell.setBackgroundColor(new Color(0xAA, 0xAA, 0xAA));
                table.addCell(cell);
            }
            
            ArrayList list1 = new ArrayList();
            
            commande.GetPrixPDF(codeCommande);
            list1.addAll(commande.list);
            list1.add(0,"Prix unitaire série HT");
            
            //AFFICHAGE DU¨PRIX UNITAIRE SERIE
            for(int i = 0 ; i < list1.size() ; i ++) {
                if( i == 0 )
                    data = new Paragraph(""+list1.get(i)+" €",FontFactory.getFont(police, 6, 0, new Color(0, 0, 0))) ;
                else
                    data = new Paragraph(""+list1.get(i),FontFactory.getFont(police, 6, 0, new Color(0, 0, 0))) ;
                cell = new PdfPCell(data);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                if( i == 0 ) cell.setBackgroundColor(new Color(0xAA, 0xAA, 0xAA));
                table.addCell(cell);
            }
            
            ArrayList list2 = new ArrayList();
            
            commande.GetPourcentPDF(codeCommande);
            list2.addAll(commande.list);
            list2.add(0,"Majoration");
            
            //AFFICHAGE DE LA MAJORATION
            for(int i = 0 ; i < list2.size() ; i ++) {
                
                if( i == 0 )
                    data = new Paragraph(""+list2.get(i),FontFactory.getFont(police, 6, 0, new Color(0, 0, 0))) ;
                else
                    data = new Paragraph(""+list2.get(i)+" %",FontFactory.getFont(police, 6, 0, new Color(0, 0, 0))) ;
                
                cell = new PdfPCell(data);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                if( i == 0 ) cell.setBackgroundColor(new Color(0xAA, 0xAA, 0xAA));
                
                table.addCell(cell);
            }
            
            ArrayList list3 = new ArrayList();
            
            for(int i = 1 ; i < list1.size(); i ++)
                list3.add(""+(Float.parseFloat(""+list1.get(i))+(Float.parseFloat(""+list1.get(i))+(Float.parseFloat(""+list1.get(i))*Float.parseFloat(""+list2.get(i))/100))));
            
            
            list3.add(0,"Prix unitaire HT");
            
            //AFFICHAGE DU PRIX UNITAIRE HT
            for(int i = 0 ; i < list3.size() ; i ++) {
                
                int n = 0, k = 0;
                String temp3 = "";
                while( n < list3.size() && k < 3){
                    if ( k > 0) k++;
                    String temp1 = ""+list3.get(i);
                    char temp2 = temp1.charAt(n);
                    String detect = ""+temp2;
                    temp3 = temp3 +""+temp2;
                    if(detect.equals(".")) k ++;
                    n++;
                }
                
                if( i == 0 )
                    data = new Paragraph(temp3+" €",FontFactory.getFont(police, 6, 0, new Color(0, 0, 0))) ;
                else
                    data = new Paragraph(temp3,FontFactory.getFont(police, 6, 0, new Color(0, 0, 0))) ;
                cell = new PdfPCell(data);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                if( i == 0 ) cell.setBackgroundColor(new Color(0xAA, 0xAA, 0xAA));
                table.addCell(cell);
            }
            
            ArrayList list4 = new ArrayList();
            list.remove(0);
            list3.remove(0);
            for(int i = 0 ; i < list3.size(); i ++)
                list4.add(""+(Float.parseFloat(""+list.get(i))*Float.parseFloat(""+list3.get(i))));
            
            list4.add(0,"Prix total HT");
            
            //AFFICHAGE DU PRIX TOTAL HT
            for(int i = 0 ; i < list4.size() ; i ++) {
                
                if( i == 0 )
                    data = new Paragraph(""+list4.get(i)+" €",FontFactory.getFont(police, 6, 0, new Color(0, 0, 0))) ;
                else
                    data = new Paragraph(""+list4.get(i),FontFactory.getFont(police, 6, 0, new Color(0, 0, 0))) ;
                cell = new PdfPCell(data);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                if( i == 0 ) cell.setBackgroundColor(new Color(0xAA, 0xAA, 0xAA));
                table.addCell(cell);
            }
            
            
            //AJOUT DU TABLEAU AU DOCUMENT
            table.setWidthPercentage(100);
            document.add(table);
            // </editor-fold>
            
            // <editor-fold defaultstate="collapsed" desc="TROISIEME TABLEAU RECAPITULATIF DES TOTAUX">
            //SEPARATEUR
            data = new Paragraph("\n",FontFactory.getFont(police, 12, 0, new Color(0, 0, 0))) ;
            document.add(data) ;
            
            float[] widths =       {0.5f,0.5f};
            
            //DECLARATION DU NOUVEAU TABLEAU
            table = new PdfPTable(widths);
            list4.remove(0);
            float totalHT = 0.00f, totalTTC = 0, TVA = 0.196f;
            for(int i =0; i< list4.size(); i++)
                totalHT += Float.parseFloat(""+list4.get(i));
            
            totalTTC = totalHT + totalHT * TVA ;
            
            
            //AFFICHAGE DU PRIX TOTAL
            data = new Paragraph("Total général HT",FontFactory.getFont(police, 12, 0, new Color(0, 0, 0))) ;
            cell = new PdfPCell(data);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(new Color(0xAA, 0xAA, 0xAA));
            table.addCell(cell);
            data = new Paragraph(""+totalHT+" €",FontFactory.getFont(police, 12, 0, new Color(0, 0, 0))) ;
            cell = new PdfPCell(data);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            data = new Paragraph("TVA 19.6 %",FontFactory.getFont(police, 12, 0, new Color(0, 0, 0))) ;
            cell = new PdfPCell(data);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(new Color(0xAA, 0xAA, 0xAA));
            table.addCell(cell);
            data = new Paragraph(""+totalHT*TVA+" €",FontFactory.getFont(police, 12, 0, new Color(0, 0, 0))) ;
            cell = new PdfPCell(data);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            data = new Paragraph("Total général TTC",FontFactory.getFont(police, 12, 0, new Color(0, 0, 0))) ;
            cell = new PdfPCell(data);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(new Color(0xAA, 0xAA, 0xAA));
            table.addCell(cell);
            data = new Paragraph(""+totalTTC+" €",FontFactory.getFont(police, 12, 0, new Color(0, 0, 0))) ;
            cell = new PdfPCell(data);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            
            table.setWidthPercentage(50);
            document.add(table);
            // </editor-fold>
            
            //FERMETURE DU DOCUMENT
            document.close();
            
            
        } catch (BadElementException ex) {
            ex.printStackTrace();
        } catch (DocumentException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("\n\nDEBUT ERREUR<==========================================\n");
            ex.printStackTrace();
            System.out.println("\nFIN ERREUR<==============================================\n\n");
        }
        
    }
    
    private static Document GetDeclarationPDF(Document document, UtilisateursDAO utilisateur, String nom_Fichier)
    throws DocumentException {
        try {
            document = new Document(PageSize.A4.rotate(), 25, 25, 25, 25);
            PdfWriter.getInstance(document, new FileOutputStream(""+cheminEnregistrementPDF+utilisateur.NamePDF+"_"+nom_Fichier+".pdf"));
            document.open();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (DocumentException ex) {
            ex.printStackTrace();
        }
        return document;
    }
}
