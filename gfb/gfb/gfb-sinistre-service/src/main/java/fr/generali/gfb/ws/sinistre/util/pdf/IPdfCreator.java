package fr.generali.gfb.ws.sinistre.util.pdf;

import java.awt.Color;
import java.io.OutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

/**
 * Service technique de cr�ation de PDF.
 * 
 * @author Christian Blavier <cblavier@generali.fr>
 */
public interface IPdfCreator {

    /**
     * Polices par d�faut.
     */
    Font BODY_FONT = new Font(Font.HELVETICA, 9);

    Font BODY_FONT_PLUS_1 = new Font(Font.HELVETICA, 10);

    Font BODY_FONT_8 = new Font(Font.HELVETICA, 8);

    Font BODY_FONT_8_UNDERLINE = new Font(Font.HELVETICA, 8, Font.UNDERLINE);

    Font BODY_BOLD = new Font(Font.HELVETICA, 10, Font.BOLD);

    Font BODY_BOLD_PLUS_1 = new Font(Font.HELVETICA, 11, Font.BOLD);

    Font BODY_BOLD_9 = new Font(Font.HELVETICA, 9, Font.BOLD);

    Font BODY_BOLD_GENERALI = new Font(Font.HELVETICA, 10, Font.BOLD, new Color(158, 54, 33));

    Font BODY_BOLD_SIZE8 = new Font(Font.HELVETICA, 8, Font.BOLD);

    Font BODY_ITALIC = new Font(Font.HELVETICA, 10, Font.ITALIC);

    Font BODY_ITALIC_PLUS_1 = new Font(Font.HELVETICA, 10, Font.ITALIC);

    Font BODY_ITALIC_SIZE_8 = new Font(Font.HELVETICA, 8, Font.ITALIC);

    Font BODY_BOLD_RED = new Font(Font.HELVETICA, 10, Font.BOLD, Color.RED);

    Font BODY_BOLDITALIC = new Font(Font.HELVETICA, 10, Font.BOLDITALIC);

    Font BODY_BOLDITALIC_PLUS_1 = new Font(Font.HELVETICA, 10, Font.BOLDITALIC);

    Font BODY_FONT_BOLD_UNDERLINE = new Font(Font.HELVETICA, 10, Font.UNDERLINE + Font.BOLD);

    Font BODY_FONT_UNDERLINE = new Font(Font.HELVETICA, 10, Font.UNDERLINE);

    Font TABLE_HEADER_SIZE_10 = new Font(Font.HELVETICA, 10, Font.BOLD, Color.WHITE);

    Font TABLE_HEADER_SIZE_11 = new Font(Font.HELVETICA, 11, Font.BOLD, Color.WHITE);

    Font TABLE_HEADER_SIZE_12 = new Font(Font.HELVETICA, 12, Font.BOLD, Color.WHITE);

    Font FOOTER_FONT_GRAY_SIZE_9 = new Font(Font.HELVETICA, 10, Font.BOLD, Color.GRAY);

    Font FOOTER_FONT_GRAY_SIZE_6 = new Font(Font.HELVETICA, 6, Font.BOLD, Color.GRAY);

    Font TITLE_2 = new Font(Font.HELVETICA, 12, Font.BOLD);

    Font TITLE_2_PLUS_1 = new Font(Font.HELVETICA, 13, Font.BOLD);

    Font H1_FONT = new Font(Font.HELVETICA, 18, Font.BOLD);

    Font H1_FONT_PLUS_1 = new Font(Font.HELVETICA, 19, Font.BOLD);

    Font H2_FONT = new Font(Font.HELVETICA, 14, Font.BOLD);

    Font H2_FONT_PLUS_1 = new Font(Font.HELVETICA, 15, Font.BOLD);

    Font H2_FONT_UNDERLINE = new Font(Font.HELVETICA, 14, Font.BOLD + Font.UNDERLINE);

    Font ZAPFDINGBATS_FONT = new Font(Font.ZAPFDINGBATS, 9, Font.BOLD, new Color(158, 54, 33));

    /**
     * Polices pour Sinistre.
     */
    Font SINISTRE_COMMENT_FONT = new Font(Font.HELVETICA, 8);

    Font SINISTRE_BODY_FONT = new Font(Font.HELVETICA, 9);

    Font SINISTRE_BODY_BOLD = new Font(Font.HELVETICA, 9, Font.BOLD);

    Font SINISTRE_BODY_FONT_GRAY = new Font(Font.HELVETICA, 9, Font.NORMAL, Color.GRAY);

    /**
     * @return le writer
     */
    PdfWriter getWriter();

    /**
     * Cr�e un objet paragraphe.
     * 
     * @param texte le texte du paragraphe.
     * @param font la police de caract�re.
     * @return le paragraphe cr��.
     */
    Paragraph createParagraph(String texte, Font font);

    /**
     * Cr�e un objet paragraphe.
     * 
     * @param texte le texte du paragraphe.
     * @param font la police de caract�re.
     * @param alignement l'alignement du texte.
     * @return le paragraphe cr��.
     */
    Paragraph createParagraph(String texte, Font font, int alignement);

    /**
     * Permet de cr�er une table.
     * 
     * @param columnWidths la largeur des colomnes en pourcentage. Le nombre
     *            d'�l�ment dans ce tableau fixe le nombre de colomnes de la
     *            table.
     * @return la table cr��e.
     */
    PdfPTable createTable(int[] columnWidths);

    /**
     * Cr�e un nouveau document pdf sur un OutputStream.
     * 
     * @param os l'outputstream sur lequel sera �crit le pdf.
     * @param marginLeft la marge gauche en px.
     * @param marginRight la marge droite en px.
     * @param marginTop la marge haute en px.
     * @param marginBottom la marge basse en px.
     * @return le document pdf � manipuler.
     */
    Document createPdfSinistreDocument(OutputStream os, float marginLeft, float marginRight, float marginTop,
                    float marginBottom);

}
