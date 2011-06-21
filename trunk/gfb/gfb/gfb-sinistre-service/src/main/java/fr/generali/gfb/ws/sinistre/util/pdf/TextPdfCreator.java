package fr.generali.gfb.ws.sinistre.util.pdf;

import java.io.OutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import fr.generali.socle.exceptions.TechnicalException;

/**
 * Implémentation IText du PdfCreator. Ce bean est en scope prototype car il
 * conserve un état.
 * 
 * @author Christian Blavier <cblavier@generali.fr>
 */
public class TextPdfCreator implements IPdfCreator {

    private PdfWriter writer;

    /**
     * Texte des exceptions techniques.
     */
    private static final String TECHNICAL_EXCEPTION = "Une exception est survenue lors de la génération du pdf";

    /*
     * (non-Javadoc)
     * @see
     * fr.generali.melovie.common.pdf.IPdfCreator#createPdfSinistreDocument(
     * java.io.OutputStream, float, float, float, float)
     */
    public Document createPdfSinistreDocument(OutputStream os, float marginLeft, float marginRight, float marginTop,
                    float marginBottom) {
        try {
            Document pdf = new Document(PageSize.A4, marginLeft, marginRight, marginTop, marginBottom);
            writer = PdfWriter.getInstance(pdf, os);
            writer.setPageEvent(new SinistrePageEvent());
            pdf.open();
            return pdf;
        } catch (DocumentException e) {
            throw new TechnicalException(TECHNICAL_EXCEPTION, e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public Paragraph createParagraph(String texte, Font font) {
        return new Paragraph(texte, font);
    }

    /**
     * {@inheritDoc}
     */
    public Paragraph createParagraph(String texte, Font font, int alignement) {
        Paragraph p = createParagraph(texte, font);
        p.setAlignment(alignement);
        return p;
    }

    /**
     * {@inheritDoc}
     */
    public PdfPTable createTable(int[] columnWidths) {
        PdfPTable table = new PdfPTable(columnWidths.length);
        table.setSpacingBefore(20f);
        table.setWidthPercentage(100f);
        try {
            table.setWidths(columnWidths);
        } catch (DocumentException e) {
            throw new TechnicalException(TECHNICAL_EXCEPTION, e);
        }
        return table;
    }

    public PdfWriter getWriter() {
        return writer;
    }

}
