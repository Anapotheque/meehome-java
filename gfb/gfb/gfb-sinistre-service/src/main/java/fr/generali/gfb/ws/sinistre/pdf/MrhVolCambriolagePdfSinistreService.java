package fr.generali.gfb.ws.sinistre.pdf;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.commons.lang.StringUtils;

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
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.vol.ModeOperatoire;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.vol.SinistreMRHVol;
import fr.generali.gfb.ws.sinistre.util.pdf.IPdfCreator;
import fr.generali.socle.exceptions.TechnicalException;

/**
 * @author Mickael Morier
 */
public class MrhVolCambriolagePdfSinistreService extends CommonPdfSinistreService {
    private final TypeOrigine typeOrigine;

    public MrhVolCambriolagePdfSinistreService(IPdfCreator pdfCreator, TypeOrigine typeOrigine) {
        super(pdfCreator);
        if (typeOrigine == null) {
            throw new IllegalArgumentException("Le type origine (CONNECTE ou DECONNECTE) est obligatoire");
        }

        this.typeOrigine = typeOrigine;
    }

    /**
     * Crée le document PDF d'un Vol Cambriolage à partir de sa déclaration de
     * sinistre
     * 
     * @param declarationSinistre
     * @param isClientInternet
     * @param infosPortefeuille
     * @return document PDF sous forme d'un tableau de byte
     */
    public byte[] createPdfVolCambriolageMRH(DeclarationSinistre declarationSinistre, Boolean isClientInternet,
                    InformationIntermediaire infosPortefeuille, boolean isCourtier) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document pdf = pdfCreator.createPdfSinistreDocument(baos, MARGIN_LEFT, MARGIN_RIGHT, MARGIN_TOP, MARGIN_BOTTOM);

        SinistreMRHVol sinistreVol = (SinistreMRHVol ) declarationSinistre.getSinistre();

        try {
            // Initialise les coordonnées du client
            pdf.add(initializeCoordonneesClient(declarationSinistre));

            // initialise date de création du document
            pdf.add(initializeDate(declarationSinistre));

            // initialise objet du document
            pdf.add(initializeObjet("Votre déclaration en ligne d'un sinistre Vol"));

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

            if (sinistreVol != null) {
                // bien concerne
                if (sinistreVol.getBienConcerne() != null) {
                    BienConcerne bienConcerne = sinistreVol.getBienConcerne();

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
                            String codePostalAndVille = "";
                            if (bienConcerne.getCodePostal() != null || bienConcerne.getVille() != null) {
                                if (bienConcerne.getCodePostal() != null) {
                                    codePostalAndVille = bienConcerne.getCodePostal() + " ";
                                }
                                if (bienConcerne.getVille() != null) {
                                    codePostalAndVille += bienConcerne.getVille();
                                }
                                adresseDifferenteList.add(new ListItem(codePostalAndVille,
                                                IPdfCreator.SINISTRE_BODY_FONT));
                            }

                            pdf.add(adresseDifferenteList);
                        }
                        pdf.add(Chunk.NEWLINE);
                    }
                }

                // Date du sinistre
                if (sinistreVol.getDateSinistre() != null) {
                    String dateSinistreAsString = FormattingUtils.formatToDate(sinistreVol.getDateSinistre());
                    Phrase phrase = new Phrase();
                    phrase.add(new Chunk(
                                    "Date du sinistre (ou à défaut date à laquelle vous en avez eu connaissance) : ",
                                    IPdfCreator.SINISTRE_BODY_BOLD));
                    phrase.add(new Chunk(dateSinistreAsString, IPdfCreator.SINISTRE_BODY_FONT));
                    pdf.add(phrase);
                    pdf.add(Chunk.NEWLINE);
                }

                // Occupant present
                if (sinistreVol.getIsOccupantPresent() != null) {
                    String txtPresenceOccupant = "";
                    if (Boolean.TRUE.equals(sinistreVol.getIsOccupantPresent())) {
                        txtPresenceOccupant = "Vous nous déclarez qu'un occupant était présent.";
                    } else if (Boolean.FALSE.equals(sinistreVol.getIsOccupantPresent())) {
                        txtPresenceOccupant = "Vous nous déclarez qu'aucun occupant n'était présent.";

                        if (sinistreVol.getDureeAbsence() != null
                                        && StringUtils.isNotBlank(sinistreVol.getDureeAbsence())) {
                            txtPresenceOccupant += " Durée de l'absence : " + sinistreVol.getDureeAbsence();
                        }
                    }
                    pdf.add(new Phrase(txtPresenceOccupant, IPdfCreator.SINISTRE_BODY_FONT));
                    pdf.add(Chunk.NEWLINE);
                }
            }

            // mode operatoire
            if (sinistreVol.getModeOperatoire() != null) {
                int[] tableTotalSize = {7, 30 };
                int[] tableWidthColumn1 = {7 };
                int[] tableWidthColumn2 = {30 };

                PdfPTable table = pdfCreator.createTable(tableTotalSize);
                table.setKeepTogether(Boolean.TRUE);
                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                table.setSpacingBefore(0f);
                table.setSpacingAfter(0f);
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

                tableColumn1.addCell(new Phrase("Mode d'effraction : ", IPdfCreator.SINISTRE_BODY_BOLD));

                List<ModeOperatoire> modeOperatoire = sinistreVol.getModeOperatoire();
                if (modeOperatoire != null) {
                    for (ModeOperatoire effraction : modeOperatoire) {
                        if (effraction != null && effraction.getValue() != null && !effraction.getValue().equals("")) {
                            tableColumn2.addCell(new Phrase("- " + effraction.getValue(),
                                            IPdfCreator.SINISTRE_BODY_FONT));
                        }
                    }
                }

                if (sinistreVol.getAutreModeOperatoire() != null && !sinistreVol.getAutreModeOperatoire().equals("")) {
                    tableColumn2.addCell(new Phrase(sinistreVol.getAutreModeOperatoire(),
                                    IPdfCreator.SINISTRE_BODY_FONT));
                }

                table.addCell(tableColumn1);
                table.addCell(tableColumn2);

                pdf.add(table);

                pdf.add(Chunk.NEWLINE);
            }

            // description des biens voles
            if (sinistreVol.getBiensVoles() != null && !sinistreVol.getBiensVoles().equals("")) {
                int[] tableTotalSize = {10, 30 };
                int[] tableWidthColumn1 = {10 };
                int[] tableWidthColumn2 = {30 };

                PdfPTable table = pdfCreator.createTable(tableTotalSize);
                table.setKeepTogether(Boolean.TRUE);
                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                table.setSpacingBefore(0f);
                table.setSpacingAfter(0f);
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

                tableColumn1.addCell(new Phrase("Liste des biens volés : ", IPdfCreator.SINISTRE_BODY_BOLD));
                tableColumn2.addCell(new Phrase(sinistreVol.getBiensVoles(), IPdfCreator.SINISTRE_BODY_FONT));

                table.addCell(tableColumn1);
                table.addCell(tableColumn2);

                pdf.add(table);

                pdf.add(Chunk.NEWLINE);
            }

            // description des dommages immobiliers
            if (sinistreVol.getDommagesImmobiliers() != null && !sinistreVol.getDommagesImmobiliers().equals("")) {
                int[] tableTotalSize = {20, 30 };
                int[] tableWidthColumn1 = {20 };
                int[] tableWidthColumn2 = {30 };

                PdfPTable table = pdfCreator.createTable(tableTotalSize);
                table.setKeepTogether(Boolean.TRUE);
                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                table.setSpacingBefore(0f);
                table.setSpacingAfter(0f);
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

                tableColumn1.addCell(new Phrase("Description des dommages immobiliers : ",
                                IPdfCreator.SINISTRE_BODY_BOLD));
                tableColumn2.addCell(new Phrase(sinistreVol.getDommagesImmobiliers(), IPdfCreator.SINISTRE_BODY_FONT));

                table.addCell(tableColumn1);
                table.addCell(tableColumn2);

                pdf.add(table);

                pdf.add(Chunk.NEWLINE);
            }

            // depot de plainte
            if (sinistreVol.getIsPlainteDepose() != null) {
                if (Boolean.TRUE.equals(sinistreVol.getIsPlainteDepose())) {
                    pdf.add(new Phrase("Vous nous déclarez avoir déposé plainte.", IPdfCreator.SINISTRE_BODY_FONT));
                } else if (Boolean.FALSE.equals(sinistreVol.getIsPlainteDepose())) {
                    pdf.add(new Phrase("Vous nous déclarez ne pas avoir déposé plainte.",
                                    IPdfCreator.SINISTRE_BODY_FONT));
                    pdf.add(Chunk.NEWLINE);
                    pdf.add(Chunk.NEWLINE);
                    pdf.add(new Phrase("Attention, le dépôt de plainte est un document indispensable et obligatoire. "
                                    + "Celui-ci conditionne la mise en  oeuvre de votre garantie "
                                    + "et votre indemnisation.", IPdfCreator.SINISTRE_COMMENT_FONT));
                }
                pdf.add(Chunk.NEWLINE);
                pdf.add(Chunk.NEWLINE);
            }

            pdf.add(initializeEndingText("67".equals(declarationSinistre.getCodeCompagnie())));
        } catch (DocumentException e) {
            throw new TechnicalException("Une exception est survenue lors de la génération du PDF", e);
        }

        pdf.close();
        return baos.toByteArray();
    }
}
