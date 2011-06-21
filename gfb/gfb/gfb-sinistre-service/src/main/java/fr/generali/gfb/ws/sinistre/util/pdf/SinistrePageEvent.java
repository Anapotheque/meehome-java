/**
 * 
 */
package fr.generali.gfb.ws.sinistre.util.pdf;

import java.awt.Color;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEvent;
import com.lowagie.text.pdf.PdfWriter;

import fr.generali.socle.exceptions.TechnicalException;

/**
 * @author Aguilane ARUL
 */
public class SinistrePageEvent implements PdfPageEvent {

    /**
     * Logo generali
     */
    private final static String LOGO = "/fr/generali/gfb/ws/sinistre/pdf/generali2.bmp";

    /**
     * Texte des exceptions techniques.
     */
    protected static final String TECHNICAL_EXCEPTION = "Une exception est survenue lors de la génération du pdf";

    /**
     * Police utilisée pour les entêtes et pied de pages.
     */
    protected BaseFont font;

    /**
     * Constructeur
     */
    public SinistrePageEvent() {
        try {
            font = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);
        } catch (DocumentException e) {
            throw new TechnicalException(TECHNICAL_EXCEPTION, e);
        } catch (IOException e) {
            throw new TechnicalException(TECHNICAL_EXCEPTION, e);
        }
    }

    /*
     * (non-Javadoc)
     * @see
     * com.lowagie.text.pdf.PdfPageEvent#onOpenDocument(com.lowagie.text.pdf
     * .PdfWriter, com.lowagie.text.Document)
     */
    public void onOpenDocument(PdfWriter writer, Document document) {
    }

    /*
     * (non-Javadoc)
     * @see
     * com.lowagie.text.pdf.PdfPageEvent#onCloseDocument(com.lowagie.text.pdf
     * .PdfWriter, com.lowagie.text.Document)
     */
    public void onCloseDocument(PdfWriter writer, Document document) {
    }

    /*
     * (non-Javadoc)
     * @see
     * com.lowagie.text.pdf.PdfPageEvent#onStartPage(com.lowagie.text.pdf.PdfWriter
     * , com.lowagie.text.Document)
     */
    public void onStartPage(PdfWriter writer, Document document) {

        try {

            Rectangle page = document.getPageSize();

            URL url = this.getClass().getResource(LOGO);
            Image image = Image.getInstance(url);
            image.scalePercent(40);
            image.setSpacingAfter(30f);
            image.setAlignment(Element.ALIGN_LEFT);
            image.setAbsolutePosition((page.getWidth() / 2) - 50, document.top() + 10);
            writer.getDirectContent().addImage(image);

            PdfPTable head = new PdfPTable(1);
            head.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            head.getDefaultCell().setPadding(1f);
            head.getDefaultCell().setFixedHeight(20f);
            head.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            head.getDefaultCell().setBorderWidthBottom(1f);
            head.getDefaultCell().setBorderColorBottom(Color.GRAY);
            head.addCell(new Phrase("", IPdfCreator.FOOTER_FONT_GRAY_SIZE_6));
            head.setTotalWidth(page.getWidth() - document.leftMargin());
            head.writeSelectedRows(0, -1, page.getLeft() + document.leftMargin() - 25, document.top() + 23, writer
                            .getDirectContent());

        } catch (BadElementException e) {
            throw new TechnicalException(TECHNICAL_EXCEPTION, e);
        } catch (MalformedURLException e) {
            throw new TechnicalException(TECHNICAL_EXCEPTION, e);
        } catch (IOException e) {
            throw new TechnicalException(TECHNICAL_EXCEPTION, e);
        } catch (DocumentException e) {
            throw new TechnicalException(TECHNICAL_EXCEPTION, e);
        }

    }

    /*
     * (non-Javadoc)
     * @see
     * com.lowagie.text.pdf.PdfPageEvent#onEndPage(com.lowagie.text.pdf.PdfWriter
     * , com.lowagie.text.Document)
     */
    public void onEndPage(PdfWriter writer, Document document) {

        Rectangle page = document.getPageSize();

        PdfPTable head = new PdfPTable(1);
        head.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        head.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        head.getDefaultCell().setPadding(1f);
        head.addCell(new Phrase("Generali IARD, Société Anonyme " + "- Entreprise régie par le Code des assurances "
                        + "- au capital de 59 493 775 euros,", IPdfCreator.FOOTER_FONT_GRAY_SIZE_6));
        head.addCell(new Phrase(
                        "inscrite au RCS de Paris sous le numéro 552 062 663 et dont le siège social est situé au",
                        IPdfCreator.FOOTER_FONT_GRAY_SIZE_6));
        head.addCell(new Phrase("7 boulevard Haussmann 75456 Paris cedex 09", IPdfCreator.FOOTER_FONT_GRAY_SIZE_6));
        head.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
        head.writeSelectedRows(0, -1, page.getLeft() + 60, document.bottom() - 10, writer.getDirectContent());

    }

    /*
     * (non-Javadoc)
     * @see
     * com.lowagie.text.pdf.PdfPageEvent#onChapter(com.lowagie.text.pdf.PdfWriter
     * , com.lowagie.text.Document, float, com.lowagie.text.Paragraph)
     */
    public void onChapter(PdfWriter writer, Document document, float paragraphPosition, Paragraph title) {
    }

    /*
     * (non-Javadoc)
     * @see
     * com.lowagie.text.pdf.PdfPageEvent#onChapterEnd(com.lowagie.text.pdf.PdfWriter
     * , com.lowagie.text.Document, float)
     */
    public void onChapterEnd(PdfWriter writer, Document document, float paragraphPosition) {
    }

    /*
     * (non-Javadoc)
     * @see
     * com.lowagie.text.pdf.PdfPageEvent#onGenericTag(com.lowagie.text.pdf.PdfWriter
     * , com.lowagie.text.Document, com.lowagie.text.Rectangle,
     * java.lang.String)
     */
    public void onGenericTag(PdfWriter writer, Document document, Rectangle rect, String text) {
    }

    /*
     * (non-Javadoc)
     * @see
     * com.lowagie.text.pdf.PdfPageEvent#onParagraph(com.lowagie.text.pdf.PdfWriter
     * , com.lowagie.text.Document, float)
     */
    public void onParagraph(PdfWriter writer, Document document, float paragraphPosition) {
    }

    /*
     * (non-Javadoc)
     * @see
     * com.lowagie.text.pdf.PdfPageEvent#onParagraphEnd(com.lowagie.text.pdf
     * .PdfWriter, com.lowagie.text.Document, float)
     */
    public void onParagraphEnd(PdfWriter writer, Document document, float paragraphPosition) {
    }

    /*
     * (non-Javadoc)
     * @see
     * com.lowagie.text.pdf.PdfPageEvent#onSection(com.lowagie.text.pdf.PdfWriter
     * , com.lowagie.text.Document, float, int, com.lowagie.text.Paragraph)
     */
    public void onSection(PdfWriter writer, Document document, float paragraphPosition, int depth, Paragraph title) {
    }

    /*
     * (non-Javadoc)
     * @see
     * com.lowagie.text.pdf.PdfPageEvent#onSectionEnd(com.lowagie.text.pdf.PdfWriter
     * , com.lowagie.text.Document, float)
     */
    public void onSectionEnd(PdfWriter writer, Document document, float paragraphPosition) {
    }

}
