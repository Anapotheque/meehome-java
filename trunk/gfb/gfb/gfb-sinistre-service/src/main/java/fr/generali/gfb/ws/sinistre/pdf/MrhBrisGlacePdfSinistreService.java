package fr.generali.gfb.ws.sinistre.pdf;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPTable;

import fr.generali.espaceclient.common.util.FormattingUtils;
import fr.generali.espaceclient.proxy.generic.domain.InformationIntermediaire;
import fr.generali.gfb.ws.sinistre.dto.OrigineDeclaration.TypeOrigine;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.Assure;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.brisglace.SinistreBrisGlace;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.brisglace.TypeBiensEndommages;
import fr.generali.gfb.ws.sinistre.util.pdf.IPdfCreator;
import fr.generali.socle.exceptions.TechnicalException;

/**
 * @author Mickael Morier
 */
public class MrhBrisGlacePdfSinistreService extends CommonPdfSinistreService {

    private final TypeOrigine typeOrigine;

    public MrhBrisGlacePdfSinistreService(IPdfCreator pdfCreator, TypeOrigine typeOrigine) {
        super(pdfCreator);
        if (typeOrigine == null) {
            throw new IllegalArgumentException("Le type origine (CONNECTE ou DECONNECTE) est obligatoire");
        }

        this.typeOrigine = typeOrigine;
    }

    /**
     * Crée le document PDF d'un Bris Glace à partir de sa déclaration de
     * sinistre
     * 
     * @param declarationSinistre
     * @param isClientInternet
     * @param infosPortefeuille
     * @return document PDF sous forme d'un tableau de byte
     */
    public byte[] createPdfBrisGlaceMRH(DeclarationSinistre declarationSinistre, Boolean isClientInternet,
                    InformationIntermediaire infosPortefeuille, boolean isCourtier) {

        String content;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document pdf = pdfCreator.createPdfSinistreDocument(baos, MARGIN_LEFT, MARGIN_RIGHT, MARGIN_TOP, MARGIN_BOTTOM);

        SinistreBrisGlace sinistreBrisGlace = (SinistreBrisGlace ) declarationSinistre.getSinistre();

        try {

            pdf.add(initializeCoordonneesClient(declarationSinistre));

            pdf.add(initializeDate(declarationSinistre));

            // Initialise Objet du document
            pdf.add(initializeObjet("Votre déclaration en ligne d'un sinistre Bris de glace"));

            // Texte d'introduction
            initializeIntroductionText(pdf, declarationSinistre, isClientInternet, TYPE_DECLA_MRH, typeOrigine, isCourtier);

            if (TypeOrigine.CONNECTE.equals(typeOrigine)) {
                // Init des coordonnées intermediaire
                pdf.add(initCoordonneesAgence(infosPortefeuille,isCourtier));
            }

            Assure assure = declarationSinistre.getAssure();

            PdfPTable tableEtape1Title = pdfCreator.createTable(new int[] {100 });
            tableEtape1Title.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            tableEtape1Title.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            tableEtape1Title.getDefaultCell().setBackgroundColor(GENERALI_COLOR);
            tableEtape1Title.addCell(new Phrase("ETAPE 1 : L'ASSURE", ETAPE_FONT));
            tableEtape1Title.setSpacingAfter(10f);

            pdf.add(tableEtape1Title);

            pdf.add(initializeAssure(pdf.getPageSize().getWidth(), assure, declarationSinistre.getNumeroContrat(),
                            declarationSinistre.getNumClient(), typeOrigine));

            PdfPTable tableEtape2Title = pdfCreator.createTable(new int[] {100 });
            tableEtape2Title.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            tableEtape2Title.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            tableEtape2Title.getDefaultCell().setBackgroundColor(GENERALI_COLOR);
            tableEtape2Title.addCell(new Phrase("ETAPE 2 : LE SINISTRE", ETAPE_FONT));

            pdf.add(tableEtape2Title);
            pdf.add(Chunk.NEWLINE);

            String dateSinistreAsString = "";
            if (declarationSinistre.getSinistre() != null
                            && declarationSinistre.getSinistre().getDateSinistre() != null) {
                dateSinistreAsString =
                                FormattingUtils.formatToDate(declarationSinistre.getSinistre().getDateSinistre());
            }

            if (dateSinistreAsString != null && !dateSinistreAsString.equals("")) {

                Phrase phrase = new Phrase();
                content = "Date du sinistre (ou à défaut date à laquelle vous en avez eu connaissance) : ";
                phrase.add(new Chunk(content, IPdfCreator.SINISTRE_BODY_BOLD));
                phrase.add(new Chunk(dateSinistreAsString, IPdfCreator.SINISTRE_BODY_BOLD));
                pdf.add(phrase);

                pdf.add(Chunk.NEWLINE);
                pdf.add(Chunk.NEWLINE);
            }

            if (sinistreBrisGlace != null) {

                if (sinistreBrisGlace.getCirconstances() != null && !sinistreBrisGlace.getCirconstances().equals("")) {
                    pdf.add(new Phrase("Circonstances du sinistre : ", IPdfCreator.SINISTRE_BODY_BOLD));
                    pdf.add(new Phrase(sinistreBrisGlace.getCirconstances(), IPdfCreator.SINISTRE_BODY_FONT));
                    pdf.add(Chunk.NEWLINE);
                }

                List<TypeBiensEndommages> typeBiensEndommages = sinistreBrisGlace.getTypeBiensEndommages();
                if (typeBiensEndommages != null && typeBiensEndommages.size() > 0) {

                    PdfPTable table = pdfCreator.createTable(new int[] {25, 70 });
                    table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                    if (NO_BORDER) {
                        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                    }
                    table.getDefaultCell().setPadding(0f);
                    table.setSpacingBefore(0f);
                    table.setSpacingAfter(0f);

                    PdfPTable tableColumn1 = pdfCreator.createTable(new int[] {25 });
                    tableColumn1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                    if (NO_BORDER) {
                        tableColumn1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                    }
                    PdfPTable tableColumn2 = pdfCreator.createTable(new int[] {70 });
                    tableColumn2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                    if (NO_BORDER) {
                        tableColumn2.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                    }

                    content = "Type de bien endommagé : ";
                    tableColumn1.addCell(new Phrase(content, IPdfCreator.SINISTRE_BODY_BOLD));

                    for (TypeBiensEndommages typeBienEndommage : typeBiensEndommages) {
                        if (typeBienEndommage != null && typeBienEndommage.getValue() != null) {
                            StringBuffer text = new StringBuffer();
                            text.append("- ").append(typeBienEndommage.getValue());
                            if (typeBienEndommage.getSurface() != null
                                            && StringUtils.isNotBlank(typeBienEndommage.getSurface())) {
                                text.append(" : ").append(typeBienEndommage.getSurface().replace(".", ","));
                                text.append(" m²");
                            }
                            tableColumn2.addCell(new Phrase(text.toString(), IPdfCreator.SINISTRE_BODY_FONT));
                        }
                    }

                    table.addCell(tableColumn1);
                    table.addCell(tableColumn2);

                    pdf.add(table);

                    pdf.add(Chunk.NEWLINE);

                }

                if (sinistreBrisGlace.getDateAchatBien() != null) {
                    String dateAchatBienAsString = FormattingUtils.formatToDate(sinistreBrisGlace.getDateAchatBien());

                    pdf.add(new Phrase("Date d'achat du bien (en cas d'objet non immobilier) : ",
                                    IPdfCreator.SINISTRE_BODY_BOLD));
                    pdf.add(new Phrase(dateAchatBienAsString, IPdfCreator.SINISTRE_BODY_FONT));
                    pdf.add(Chunk.NEWLINE);
                    pdf.add(Chunk.NEWLINE);
                }

                if (sinistreBrisGlace.getCommentaires() != null && !sinistreBrisGlace.getCommentaires().equals("")) {
                    pdf.add(new Phrase("Commentaires : ", IPdfCreator.SINISTRE_BODY_BOLD));
                    pdf.add(new Phrase(sinistreBrisGlace.getCommentaires(), IPdfCreator.SINISTRE_BODY_FONT));
                    pdf.add(Chunk.NEWLINE);
                }

            }

            pdf.add(initializeEndingText("67".equals(declarationSinistre.getCodeCompagnie())));

        } catch (DocumentException e) {
            throw new TechnicalException("Une exception est survenue lors de la génération du PDF", e);
        }

        pdf.close();
        return baos.toByteArray();
    }
}
