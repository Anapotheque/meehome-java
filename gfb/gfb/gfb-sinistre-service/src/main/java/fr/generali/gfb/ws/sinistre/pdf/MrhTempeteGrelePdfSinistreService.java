package fr.generali.gfb.ws.sinistre.pdf;

import java.io.ByteArrayOutputStream;
import java.util.List;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.ListItem;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPTable;

import fr.generali.espaceclient.common.util.FormattingUtils;
import fr.generali.espaceclient.proxy.generic.domain.InformationIntermediaire;
import fr.generali.gfb.ws.sinistre.dto.OrigineDeclaration.TypeOrigine;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.Assure;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.BienConcerne;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.tempetegrele.ElementsEndommages;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.tempetegrele.SinistreMRHTempeteGrele;
import fr.generali.gfb.ws.sinistre.util.pdf.IPdfCreator;
import fr.generali.socle.exceptions.TechnicalException;

/**
 * @author Mickael Morier
 */
public class MrhTempeteGrelePdfSinistreService extends CommonPdfSinistreService {
    private final TypeOrigine typeOrigine;

    public MrhTempeteGrelePdfSinistreService(IPdfCreator pdfCreator, TypeOrigine typeOrigine) {
        super(pdfCreator);
        if (typeOrigine == null) {

            throw new IllegalArgumentException("Le type origine (CONNECTE ou DECONNECTE) est obligatoire");
        }

        this.typeOrigine = typeOrigine;
    }

    /**
     * Crée le document PDF d'un Tempête Grêle à partir de sa déclaration de
     * sinistre
     * 
     * @param declarationSinistre
     * @param isClientInternet
     * @param infosPortefeuille
     * @return document PDF sous forme d'un tableau de byte
     */
    public byte[] createPdfTempetesGrelesMRH(DeclarationSinistre declarationSinistre, Boolean isClientInternet,
                    InformationIntermediaire infosPortefeuille, boolean isCourtier) {

        String content;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document pdf = pdfCreator.createPdfSinistreDocument(baos, MARGIN_LEFT, MARGIN_RIGHT, MARGIN_TOP, MARGIN_BOTTOM);

        SinistreMRHTempeteGrele sinistreTempeteGrele = (SinistreMRHTempeteGrele ) declarationSinistre.getSinistre();

        try {

            pdf.add(initializeCoordonneesClient(declarationSinistre));

            pdf.add(initializeDate(declarationSinistre));

            // Initialise Objet du document
            pdf.add(initializeObjet("Votre déclaration en ligne d'un sinistre Tempête / Neige / Grêle"));

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

            if (sinistreTempeteGrele != null) {
                if (sinistreTempeteGrele.getBienConcerne() != null) {
                    BienConcerne bienConcerne = sinistreTempeteGrele.getBienConcerne();

                    if (bienConcerne.getIsResidencePrincipale() != null) {
                        Phrase phrase = new Phrase();
                        phrase.add(new Chunk("Le bien concerné ", IPdfCreator.SINISTRE_BODY_BOLD));

                        if (bienConcerne.getIsResidencePrincipale()) {
                            phrase.add(new Chunk("est ma résidence principale.", IPdfCreator.SINISTRE_BODY_FONT));
                            pdf.add(phrase);
                        } else {
                            phrase
                                            .add(new Chunk("est situé à l'adresse ci dessous : ",
                                                            IPdfCreator.SINISTRE_BODY_FONT));
                            pdf.add(phrase);

                            com.lowagie.text.List adresseDifferenteList = null;
                            adresseDifferenteList = new com.lowagie.text.List(com.lowagie.text.List.UNORDERED);
                            adresseDifferenteList.setListSymbol("");
                            adresseDifferenteList.setIndentationLeft(LIST_PROPERTY_INDENTATION);

                            if (bienConcerne.getAdresse() != null) {
                                adresseDifferenteList.add(new ListItem(bienConcerne.getAdresse(),
                                                IPdfCreator.SINISTRE_BODY_FONT));
                            }
                            content = "";
                            if (bienConcerne.getCodePostal() != null || bienConcerne.getVille() != null) {
                                if (bienConcerne.getCodePostal() != null) {
                                    content = bienConcerne.getCodePostal() + " ";
                                }
                                if (bienConcerne.getVille() != null) {
                                    content += bienConcerne.getVille();
                                }
                                adresseDifferenteList.add(new ListItem(content, IPdfCreator.SINISTRE_BODY_FONT));
                            }

                            pdf.add(adresseDifferenteList);

                        }
                        pdf.add(Chunk.NEWLINE);
                    }
                }

                // Date de construction du batiment
                if (sinistreTempeteGrele.getDateConstructionBatiment() != null) {
                    String dateConstructionAsString =
                                    FormattingUtils.formatToDate(sinistreTempeteGrele.getDateConstructionBatiment());
                    Phrase phrase = new Phrase();
                    phrase.add(new Chunk("Date de construction du bâtiment : ", IPdfCreator.SINISTRE_BODY_BOLD));
                    phrase.add(new Chunk(dateConstructionAsString, IPdfCreator.SINISTRE_BODY_FONT));
                    pdf.add(phrase);
                    pdf.add(Chunk.NEWLINE);
                    pdf.add(Chunk.NEWLINE);
                }

                // Date du sinistre
                if (sinistreTempeteGrele.getDateSinistre() != null) {
                    String dateConstructionAsString =
                                    FormattingUtils.formatToDate(sinistreTempeteGrele.getDateSinistre());
                    Phrase phrase = new Phrase();
                    phrase.add(new Chunk(
                                    "Date du sinistre (ou à défaut date à laquelle vous en avez eu connaissance) : ",
                                    IPdfCreator.SINISTRE_BODY_BOLD));
                    phrase.add(new Chunk(dateConstructionAsString, IPdfCreator.SINISTRE_BODY_FONT));
                    pdf.add(phrase);
                    pdf.add(Chunk.NEWLINE);
                    pdf.add(Chunk.NEWLINE);
                }

                // description des circonstances
                if (sinistreTempeteGrele.getCirconstances() != null
                                && !sinistreTempeteGrele.getCirconstances().equals("")) {
                    Phrase phrase = new Phrase();
                    phrase
                                    .add(new Chunk("Description des circonstances du sinistre : ",
                                                    IPdfCreator.SINISTRE_BODY_BOLD));
                    phrase.add(new Chunk(sinistreTempeteGrele.getCirconstances(), IPdfCreator.SINISTRE_BODY_FONT));
                    pdf.add(phrase);
                    pdf.add(Chunk.NEWLINE);
                    pdf.add(Chunk.NEWLINE);
                }

                // Elements endommagés
                if (sinistreTempeteGrele.getElementsEndommages() != null) {

                    int[] tableTotalSize = {10, 30 };
                    int[] tableWidthColumn1 = {10 };
                    int[] tableWidthColumn2 = {30 };

                    PdfPTable table = pdfCreator.createTable(tableTotalSize);

                    table.setSpacingBefore(0f);
                    table.setSpacingAfter(0f);
                    table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                    if (NO_BORDER) {
                        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                    }

                    PdfPTable tableColumn1 = pdfCreator.createTable(tableWidthColumn1);
                    tableColumn1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                    if (NO_BORDER) {
                        tableColumn1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                    }

                    PdfPTable tableColumn2 = pdfCreator.createTable(tableWidthColumn2);
                    tableColumn2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                    if (NO_BORDER) {
                        tableColumn2.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                    }

                    tableColumn1.addCell(new Phrase("Eléments endommagés : ", IPdfCreator.SINISTRE_BODY_BOLD));

                    List<ElementsEndommages> elementsEndommages = sinistreTempeteGrele.getElementsEndommages();
                    if (elementsEndommages != null) {
                        for (ElementsEndommages elementEndommage : elementsEndommages) {
                            if (elementEndommage != null && elementEndommage.getValue() != null
                                            && !elementEndommage.getValue().equals("")) {
                                tableColumn2.addCell(new Phrase("- " + elementEndommage.getValue(),
                                                IPdfCreator.SINISTRE_BODY_FONT));
                            }
                        }
                    }

                    table.addCell(tableColumn1);
                    table.addCell(tableColumn2);

                    pdf.add(table);

                    pdf.add(Chunk.NEWLINE);
                }

                // description des dommages
                if (sinistreTempeteGrele.getDommages() != null && !sinistreTempeteGrele.getDommages().equals("")) {
                    pdf.add(new Phrase("Description des dommages : ", IPdfCreator.SINISTRE_BODY_BOLD));
                    pdf.add(new Phrase(sinistreTempeteGrele.getDommages(), IPdfCreator.SINISTRE_BODY_FONT));
                    pdf.add(Chunk.NEWLINE);
                    pdf.add(Chunk.NEWLINE);
                }

                // Logement habitable
                if (sinistreTempeteGrele.getIsLogementHabitable() != null) {
                    if (Boolean.TRUE.equals(sinistreTempeteGrele.getIsLogementHabitable())) {
                        pdf.add(new Phrase("Vous nous déclarez que votre logement est encore habitable.",
                                        IPdfCreator.SINISTRE_BODY_FONT));
                    } else {
                        pdf.add(new Phrase("Vous nous déclarez que votre logement n'est plus habitable.",
                                        IPdfCreator.SINISTRE_BODY_FONT));
                    }
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
