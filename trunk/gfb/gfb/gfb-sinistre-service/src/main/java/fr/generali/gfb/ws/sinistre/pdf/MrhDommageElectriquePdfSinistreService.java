package fr.generali.gfb.ws.sinistre.pdf;

import java.io.ByteArrayOutputStream;

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
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.dommageselectriques.SinistreMRHElectrique;
import fr.generali.gfb.ws.sinistre.util.pdf.IPdfCreator;
import fr.generali.socle.exceptions.TechnicalException;

/**
 * @author Mickael Morier
 */
public class MrhDommageElectriquePdfSinistreService extends CommonPdfSinistreService {

    private final TypeOrigine typeOrigine;

    public MrhDommageElectriquePdfSinistreService(IPdfCreator pdfCreator, TypeOrigine typeOrigine) {
        super(pdfCreator);
        if (typeOrigine == null) {
            throw new IllegalArgumentException("Le type origine (CONNECTE ou DECONNECTE) est obligatoire");
        }

        this.typeOrigine = typeOrigine;
    }

    /**
     * Crée le document PDF d'un Dommage Electrique à partir de sa déclaration
     * de sinistre
     * 
     * @param declarationSinistre
     * @param isClientInternet
     * @param infosPortefeuille
     * @return document PDF sous forme d'un tableau de byte
     */
    public byte[] createPdfDommageElectriqueMRH(DeclarationSinistre declarationSinistre, Boolean isClientInternet,
                    InformationIntermediaire infosPortefeuille, boolean isCourtier) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document pdf = pdfCreator.createPdfSinistreDocument(baos, MARGIN_LEFT, MARGIN_RIGHT, MARGIN_TOP, MARGIN_BOTTOM);

        SinistreMRHElectrique sinistreElectrique = (SinistreMRHElectrique ) declarationSinistre.getSinistre();

        try {
            pdf.add(initializeCoordonneesClient(declarationSinistre));

            pdf.add(initializeDate(declarationSinistre));

            // Initialise Objet du document
            pdf.add(initializeObjet("Votre déclaration en ligne d'un sinistre Dommages électriques"));

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

            if (sinistreElectrique != null) {

                if (sinistreElectrique.getDateSinistre() != null) {
                    String dateSinistreAsString = FormattingUtils.formatToDate(sinistreElectrique.getDateSinistre());
                    Phrase phrase = new Phrase();
                    phrase.add(new Chunk(
                                    "Date du sinistre (ou à défaut date à laquelle vous en avez eu connaissance) : ",
                                    IPdfCreator.SINISTRE_BODY_BOLD));
                    phrase.add(new Chunk(dateSinistreAsString, IPdfCreator.SINISTRE_BODY_FONT));
                    pdf.add(phrase);
                    pdf.add(Chunk.NEWLINE);
                }

                if (sinistreElectrique.getTypeAppareil() != null && !sinistreElectrique.getTypeAppareil().equals("")) {
                    Phrase phrase = new Phrase();
                    phrase.add(new Chunk("Type de l'appareil endommagé : ", IPdfCreator.SINISTRE_BODY_BOLD));
                    phrase.add(new Chunk(sinistreElectrique.getTypeAppareil(), IPdfCreator.SINISTRE_BODY_FONT));
                    pdf.add(phrase);
                    pdf.add(Chunk.NEWLINE);
                }

                if (sinistreElectrique.getMarque() != null && !sinistreElectrique.getMarque().equals("")) {
                    Phrase phrase = new Phrase();
                    phrase.add(new Chunk("Marque de l'appareil endommagé : ", IPdfCreator.SINISTRE_BODY_BOLD));
                    phrase.add(new Chunk(sinistreElectrique.getMarque(), IPdfCreator.SINISTRE_BODY_FONT));
                    pdf.add(phrase);
                    pdf.add(Chunk.NEWLINE);
                }

                if (sinistreElectrique.getModele() != null && !sinistreElectrique.getModele().equals("")) {
                    Phrase phrase = new Phrase();
                    phrase.add(new Chunk("Modèle de l'appareil endommagé : ", IPdfCreator.SINISTRE_BODY_BOLD));
                    phrase.add(new Chunk(sinistreElectrique.getModele(), IPdfCreator.SINISTRE_BODY_FONT));
                    pdf.add(phrase);
                    pdf.add(Chunk.NEWLINE);
                }

                if (sinistreElectrique.getDateAchat() != null) {
                    String dateAchatAsString = FormattingUtils.formatToDate(sinistreElectrique.getDateAchat());
                    Phrase phrase = new Phrase();
                    phrase.add(new Chunk("Date d'achat de l'appareil endommagé : ", IPdfCreator.SINISTRE_BODY_BOLD));
                    phrase.add(new Chunk(dateAchatAsString, IPdfCreator.SINISTRE_BODY_FONT));
                    pdf.add(phrase);
                    pdf.add(Chunk.NEWLINE);
                }

                if (sinistreElectrique.getValeurAchat() != null && !sinistreElectrique.getValeurAchat().equals("")) {
                    String valeurAchatAsString = String.valueOf(sinistreElectrique.getValeurAchat()).replace(".", ",");
                    Phrase phrase = new Phrase();
                    phrase.add(new Chunk("Valeur à l'achat : ", IPdfCreator.SINISTRE_BODY_BOLD));
                    phrase.add(new Chunk(valeurAchatAsString + " EUR", IPdfCreator.SINISTRE_BODY_FONT));
                    pdf.add(phrase);
                    pdf.add(Chunk.NEWLINE);
                }

                if (sinistreElectrique.getDescriptionDommage() != null
                                && !sinistreElectrique.getDescriptionDommage().equals("")) {
                    Phrase phrase = new Phrase();
                    phrase.add(new Chunk("Description du dommage : ", IPdfCreator.SINISTRE_BODY_BOLD));
                    phrase.add(new Chunk(sinistreElectrique.getDescriptionDommage(), IPdfCreator.SINISTRE_BODY_FONT));
                    pdf.add(phrase);
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
