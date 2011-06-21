package fr.generali.gfb.ws.sinistre.pdf;

import java.awt.Color;

import org.apache.commons.lang.StringUtils;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

import fr.generali.espaceclient.common.util.DateUtils;
import fr.generali.espaceclient.common.util.FormattingUtils;
import fr.generali.espaceclient.proxy.generic.domain.Bureau;
import fr.generali.espaceclient.proxy.generic.domain.InformationIntermediaire;
import fr.generali.gfb.ws.sinistre.domain.reduit.contract.CoordonneesIntermediaires;
import fr.generali.gfb.ws.sinistre.dto.OrigineDeclaration.TypeOrigine;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.Assure;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.Sinistre;
import fr.generali.gfb.ws.sinistre.util.pdf.IPdfCreator;
import fr.generali.gfb.ws.sinistre.util.pdf.TextPdfCreator;

/**
 * @author Mickael Morier
 */
public class CommonPdfSinistreService {

    public CommonPdfSinistreService(IPdfCreator pdfCreator) {
        super();
        this.pdfCreator = pdfCreator;
    }

    /**
     * Utilisé dans les tests unitaires
     */
    protected CommonPdfSinistreService() {
        this.pdfCreator = new TextPdfCreator();
    }

    /**
     * Service technique de génération de PDF.
     */
    protected final transient IPdfCreator pdfCreator;

    protected static final Color GENERALI_COLOR = new Color(158, 54, 33);

    protected static final Font ETAPE_FONT = new Font(Font.HELVETICA, 10, Font.BOLD, new Color(255, 255, 255));

    protected static final float MARGIN_LEFT = 50f;

    protected static final float MARGIN_RIGHT = 50f;

    protected static final float MARGIN_TOP = 60f;

    protected static final float MARGIN_BOTTOM = 50f;

    protected static final float LIST_PROPERTY_INDENTATION = 20f;

    protected static final float LIST_VALUE_INDENTATION = 25f;

    protected static final boolean NO_BORDER = true;

    protected static final float OBJECT_SPACING_BEFORE = 30f;

    protected static final float OBJECT_SPACING_AFTER = 10f;

    protected static final String TYPE_DECLA_MRH = "habitation";

    protected static final String TYPE_DECLA_AUTO = "auto";

    protected static final String LIBELLE_SINISTRE_VOL = "Vol";

    protected static final String LIBELLE_SINISTRE_VANDALISME = "Vandalisme";

    protected static final String LIBELLE_HEURE_ZERO = "0";

    protected static final String LIBELLE_MINUTE_ZERO = "0";

    /**
     * initialise les coordonnées du client dans le document PDF
     * 
     * @param declarationSinistre
     * @return PdfPTable
     */
    protected PdfPTable initializeCoordonneesClient(DeclarationSinistre declarationSinistre) {

        int[] tableTotalSize = {50, 30 };
        int[] tableWidthColumn1 = {50 };
        int[] tableWidthColumn2 = {30 };

        PdfPTable table = pdfCreator.createTable(tableTotalSize);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingBefore(20f);
        table.setSpacingAfter(20f);
        if (NO_BORDER) {
            table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        }

        PdfPTable tableColumn1 = pdfCreator.createTable(tableWidthColumn1);
        tableColumn1.setWidthPercentage(80);
        tableColumn1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        if (NO_BORDER) {
            tableColumn1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        }

        PdfPTable tableColumn2 = createCoordonneesClient(tableWidthColumn2, declarationSinistre.getAssure());

        table.addCell(tableColumn1);
        table.addCell(tableColumn2);

        return table;
    }

    /**
     * @param tableColumn2
     * @param assure
     * @return PdfPTable
     */
    private PdfPTable createCoordonneesClient(int[] tableWidthColumn2, Assure assure) {
        PdfPTable tableColumn2 = pdfCreator.createTable(tableWidthColumn2);
        tableColumn2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        if (NO_BORDER) {
            tableColumn2.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        }

        if (assure != null) {
            if (assure.getNom() != null || assure.getPrenom() != null) {
                Phrase phrase = new Phrase();
                phrase.add(new Chunk(assure.getNom().toUpperCase() + " ", IPdfCreator.SINISTRE_BODY_BOLD));

                String prenom =
                                assure.getPrenom().substring(0, 1).toUpperCase()
                                                + assure.getPrenom().substring(1, assure.getPrenom().length());
                phrase.add(new Chunk(prenom, IPdfCreator.SINISTRE_BODY_BOLD));
                tableColumn2.addCell(phrase);
            }
            if (assure.getAdresse() != null) {
                tableColumn2.addCell(new Phrase(assure.getAdresse(), IPdfCreator.SINISTRE_BODY_BOLD));
            }
            if (assure.getCodePostal() != null || assure.getVille() != null) {
                Phrase phrase = new Phrase();
                phrase.add(new Chunk(assure.getCodePostal() + " ", IPdfCreator.SINISTRE_BODY_BOLD));
                phrase.add(new Chunk(assure.getVille(), IPdfCreator.SINISTRE_BODY_BOLD));
                tableColumn2.addCell(phrase);
            }
        }

        return tableColumn2;
    }

    /**
     * initialise la date du document PDF
     * 
     * @param declarationSinistre
     * @return PdfPTable
     */
    protected PdfPTable initializeDate(DeclarationSinistre declarationSinistre) {

        int[] tableTotalSize = {50, 30 };
        int[] tableWidthColumn1 = {50 };
        int[] tableWidthColumn2 = {30 };

        PdfPTable table = pdfCreator.createTable(tableTotalSize);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        if (NO_BORDER) {
            table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        }

        table.setSpacingBefore(20f);
        table.setSpacingAfter(0f);

        PdfPTable tableColumn1 = pdfCreator.createTable(tableWidthColumn1);
        tableColumn1.setWidthPercentage(80);
        tableColumn1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        if (NO_BORDER) {
            tableColumn1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        }

        PdfPTable tableColumn2 = pdfCreator.createTable(tableWidthColumn2);
        tableColumn2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        if (NO_BORDER) {
            tableColumn2.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        }

        Phrase phrase = new Phrase();
        phrase.add(new Chunk(createDate(declarationSinistre.getSinistre()), IPdfCreator.SINISTRE_BODY_FONT));
        tableColumn2.addCell(phrase);

        table.addCell(tableColumn1);
        table.addCell(tableColumn2);

        return table;
    }

    /**
     * Retourne le texte de la date du PDF
     * 
     * @return String
     */
    private String createDate(Sinistre sinistre) {
        String dateDeclaration = "";
        if (sinistre != null && sinistre.getDateDeclaration() != null) {
            dateDeclaration = FormattingUtils.formatToDate(sinistre.getDateDeclaration());
        }

        StringBuffer content = new StringBuffer();
        content.append("Paris, le ").append(dateDeclaration.substring(0, 2)).append(" ").append(
                        DateUtils.getMonthInWords(sinistre.getDateDeclaration())).append(" ").append(
                        dateDeclaration.substring(dateDeclaration.length() - 4, dateDeclaration.length()));

        return content.toString();
    }

    /**
     * @param objet
     * @return Paragraph
     * @throws DocumentException
     */
    protected Paragraph initializeObjet(String objet) throws DocumentException {
        Paragraph paragraphObjet =
                        pdfCreator.createParagraph(objet, IPdfCreator.SINISTRE_BODY_BOLD, Paragraph.ALIGN_LEFT);
        paragraphObjet.setSpacingBefore(OBJECT_SPACING_BEFORE);
        paragraphObjet.setSpacingAfter(OBJECT_SPACING_AFTER);
        return paragraphObjet;
    }

    /**
     * initialise l'introduction du document PDF
     * 
     * @param pdf
     * @param declarationSinistre
     * @param isClientInternet
     * @param typeDeclaration
     * @throws DocumentException
     */
    protected void initializeIntroductionText(Document pdf, DeclarationSinistre declarationSinistre,
                    Boolean isClientInternet, String typeDeclaration, TypeOrigine typeOrigine, boolean isCourtier)
                    throws DocumentException {

        pdf.add(pdfCreator.createParagraph("Cher assuré,", IPdfCreator.SINISTRE_BODY_FONT, Paragraph.ALIGN_LEFT));
        pdf.add(Chunk.NEWLINE);

        String paragraphContent = createIntroductionText(declarationSinistre, typeDeclaration, isCourtier);
        pdf.add(pdfCreator.createParagraph(paragraphContent, IPdfCreator.SINISTRE_BODY_FONT, Paragraph.ALIGN_LEFT));
        pdf.add(Chunk.NEWLINE);

        if (TypeOrigine.CONNECTE.equals(typeOrigine)) {
            paragraphContent = createIntroductionTextClientInternet(isClientInternet, isCourtier);
            pdf.add(Chunk.NEWLINE);
            pdf.add(pdfCreator.createParagraph(paragraphContent, IPdfCreator.SINISTRE_BODY_FONT, Paragraph.ALIGN_LEFT));

            if (isClientInternet) {
                pdf.add(Chunk.NEWLINE);
            }
        } else {
            paragraphContent =
                            createIntroductionTextOrigineDeconnecte(pdf, "67".equals(declarationSinistre
                                            .getCodeCompagnie()));
            pdf.add(pdfCreator.createParagraph(paragraphContent, IPdfCreator.SINISTRE_BODY_FONT, Paragraph.ALIGN_LEFT));
            pdf.add(Chunk.NEWLINE);
        }
    }

    private String createIntroductionTextOrigineDeconnecte(Document pdf, boolean isGProx) throws DocumentException {
        String retour =
                        "Nous vous recontacterons rapidement pour recueillir les éléments complémentaires "
                                        + "nécessaires au traitement de votre demande. Dans cette attente, nous sommes à votre "
                                        + "disposition pour répondre à vos questions";

        if (isGProx) {
            // retour = retour + " par téléphone au 0 969 369 969.";
        } else {
            retour = retour + " par mail à l'adresse webmaster@agence.generali.fr.";// ou
                                                                                    // par
                                                                                    // " + "téléphone
                                                                                    // au
                                                                                    // 01.58.38.43.96.";
        }

        return retour;
    }

    /**
     * Retourne le texte d'introduction du PDF
     * 
     * @param declarationSinistre
     * @param typeDeclaration
     * @return String
     */
    private String createIntroductionText(DeclarationSinistre declarationSinistre, String typeDeclaration,
                    boolean isCourtier) {
        String dateDeclaration = "";
        if (declarationSinistre.getSinistre() != null && declarationSinistre.getSinistre().getDateDeclaration() != null) {
            dateDeclaration = FormattingUtils.formatToDate(declarationSinistre.getSinistre().getDateDeclaration());
        }

        StringBuffer content = new StringBuffer();

        if (isCourtier) {
            content.append("Vous nous avez déclaré un sinistre ").append(typeDeclaration)

            .append(" sur votre espace client le ").append(dateDeclaration.substring(0, 2)).append(" ").append(
                            DateUtils.getMonthInWords(declarationSinistre.getSinistre().getDateDeclaration())).append(
                            " ").append(
                            dateDeclaration.substring(dateDeclaration.length() - 4, dateDeclaration.length())).append(
                            ". Afin de faciliter le suivi de votre déclaration, ").append(
                            "nous vous adressons ci-dessous le récapitulatif des informations que vous avez saisies.");
        } else {
            content.append("Vous nous avez déclaré un sinistre ").append(typeDeclaration).append(
                            " sur votre espace client le ").append(dateDeclaration.substring(0, 2)).append(" ").append(
                            DateUtils.getMonthInWords(declarationSinistre.getSinistre().getDateDeclaration())).append(
                            " ").append(
                            dateDeclaration.substring(dateDeclaration.length() - 4, dateDeclaration.length())).append(
                            ". Afin de faciliter le suivi de votre déclaration, ").append(
                            "nous vous adressons ci-dessous le récapitulatif des informations que vous avez saisies.");
        }
        return content.toString();
    }

    /**
     * Retourne la suite du texte d'introduction selon que le client est
     * Internet ou pas
     * 
     * @param isClientInternet
     * @return String
     */
    private String createIntroductionTextClientInternet(Boolean isClientInternet, boolean isCourtier) {
        StringBuffer content = null;
        if (isClientInternet) {
            content = new StringBuffer();
            content
                            .append("Nous vous recontacterons rapidement pour recueillir les éléments complémentaires ")
                            .append(
                                            "nécessaires au traitement de votre demande. Dans cette attente, nous sommes à votre ")
                            .append(
                                            "disposition pour répondre à vos questions par mail à l'adresse serviceclientinternet@generali.fr.");
        } else if (isCourtier) {
            content = new StringBuffer();
            content.append("Votre courtier vous recontactera rapidement pour recueillir les éléments ").append(
                            "complémentaires nécessaires au traitement de votre demande. Nous vous rappelons ").append(
                            "ci-dessous ses coordonnées : ");
        } else {
            content = new StringBuffer();
            content.append("Votre agence GENERALI vous recontactera rapidement pour recueillir les éléments ").append(
                            "complémentaires nécessaires au traitement de votre demande. Nous vous rappelons ").append(
                            "ci-dessous ses coordonnées : ");
        }

        return content.toString();
    }

    /**
     * initialise les coordonnées de l'agence
     * 
     * @param infosPortefeuille
     * @return PdfPTable
     */
    protected PdfPTable initCoordonneesAgence(InformationIntermediaire infosPortefeuille, boolean isCourtier) {

        int[] tableTotalSize = {0, 70 };
        int[] tableWidthColumn1 = {0 };
        int[] tableWidthColumn2 = {70 };

        PdfPTable table = pdfCreator.createTable(tableTotalSize);

        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingBefore(0f);
        table.setSpacingAfter(0f);
        if (NO_BORDER) {
            table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        }
        PdfPTable tableColumn1 = pdfCreator.createTable(tableWidthColumn1);
        tableColumn1.setWidthPercentage(10);
        tableColumn1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        if (NO_BORDER) {
            tableColumn1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        }
        PdfPTable tableColumn2 = pdfCreator.createTable(tableWidthColumn2);
        tableColumn2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        if (NO_BORDER) {
            tableColumn2.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        }

        // Si courtier on insere "Votre Cabinet"
        if (isCourtier) {
            tableColumn2.addCell(pdfCreator.createParagraph("Votre cabinet de courtage :",
                            IPdfCreator.SINISTRE_BODY_FONT, Paragraph.ALIGN_JUSTIFIED));
        } else {
            tableColumn2.addCell(pdfCreator.createParagraph("Votre agent :", IPdfCreator.SINISTRE_BODY_FONT,
                            Paragraph.ALIGN_JUSTIFIED));
        }

        // Init des coordonnées intermediaire
        final Bureau bureau = infosPortefeuille.getBureau();
        final String intermediraire = infosPortefeuille.getIntermediaire().getLibelle();

        if (intermediraire != null) {
            tableColumn2.addCell(pdfCreator.createParagraph(intermediraire, IPdfCreator.SINISTRE_BODY_FONT,
                            Paragraph.ALIGN_JUSTIFIED));
        }

        final String ligne2 = bureau.getLigne2();
        if (ligne2 != null && StringUtils.isNotBlank(ligne2)) {
            tableColumn2.addCell(pdfCreator.createParagraph(ligne2, IPdfCreator.SINISTRE_BODY_FONT,
                            Paragraph.ALIGN_MIDDLE));
        }

        final String ligne3 = bureau.getLigne3();
        if (ligne3 != null && StringUtils.isNotBlank(ligne3)) {
            tableColumn2.addCell(pdfCreator.createParagraph(ligne3, IPdfCreator.SINISTRE_BODY_FONT,
                            Paragraph.ALIGN_MIDDLE));
        }

        final String ligne4 = bureau.getLigne4();
        if (ligne4 != null && StringUtils.isNotBlank(ligne4)) {
            tableColumn2.addCell(pdfCreator.createParagraph(ligne4, IPdfCreator.SINISTRE_BODY_FONT,
                            Paragraph.ALIGN_MIDDLE));
        }

        final String ligne5 = bureau.getLigne5();
        if (ligne5 != null && StringUtils.isNotBlank(ligne5)) {
            tableColumn2.addCell(pdfCreator.createParagraph(ligne5, IPdfCreator.SINISTRE_BODY_FONT,
                            Paragraph.ALIGN_MIDDLE));
        }

        final String ligne6 = bureau.getLigne6();
        if (ligne6 != null && StringUtils.isNotBlank(ligne6)) {
            tableColumn2.addCell(pdfCreator.createParagraph(ligne6, IPdfCreator.SINISTRE_BODY_FONT,
                            Paragraph.ALIGN_MIDDLE));
        }

        final String ligne7 = bureau.getLigne7();
        if (ligne7 != null && StringUtils.isNotBlank(ligne7)) {
            tableColumn2.addCell(pdfCreator.createParagraph(ligne7, IPdfCreator.SINISTRE_BODY_FONT,
                            Paragraph.ALIGN_MIDDLE));
        }

        final String telephone = bureau.getTelephone();
        if (telephone != null && StringUtils.isNotBlank(telephone)) {
            tableColumn2.addCell(pdfCreator.createParagraph(telephone, IPdfCreator.SINISTRE_BODY_FONT,
                            Paragraph.ALIGN_MIDDLE));
        }

        final String fax = bureau.getFax();
        if (fax != null && StringUtils.isNotBlank(fax)) {
            tableColumn2.addCell(pdfCreator
                            .createParagraph(fax, IPdfCreator.SINISTRE_BODY_FONT, Paragraph.ALIGN_MIDDLE));
        }

        final String email = bureau.getEmail();
        if (email != null && StringUtils.isNotBlank(email)) {
            tableColumn2.addCell(pdfCreator.createParagraph(email, IPdfCreator.SINISTRE_BODY_FONT,
                            Paragraph.ALIGN_MIDDLE));
        }

        table.addCell(tableColumn1);
        table.addCell(tableColumn2);

        return table;
    }

    /**
     * initialise les infos de l'assuré
     * 
     * @param pdf
     * @param assure
     * @param numeroContrat
     * @return PdfPTable
     */
    protected PdfPTable initializeAssure(float width, Assure assure, String numeroContrat, String numeroClient,
                    TypeOrigine typeOrigine) {
        int tableTotalSize = 30;
        int tableTopWidthColumn1 = 6;
        int tableTopWidthColumn2 = 24;
        int tableBottomWidthColumn1 = 9;
        int tableBottomWidthColumn2 = 21;

        PdfPTable table = pdfCreator.createTable(new int[] {tableTotalSize });
        table.setTotalWidth(width);

        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        if (NO_BORDER) {
            table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        }
        table.getDefaultCell().setPadding(0f);
        table.setSpacingBefore(0f);
        table.setSpacingAfter(0f);

        PdfPTable tableEtape1 = pdfCreator.createTable(new int[] {tableTopWidthColumn1, tableTopWidthColumn2 });
        tableEtape1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        if (NO_BORDER) {
            tableEtape1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        }
        tableEtape1.getDefaultCell().setPaddingTop(5f);
        tableEtape1.getDefaultCell().setPaddingBottom(5f);
        tableEtape1.setSpacingBefore(0f);
        tableEtape1.setSpacingAfter(0f);

        PdfPTable tableEtape1NumContrat =
                        pdfCreator.createTable(new int[] {tableBottomWidthColumn1, tableBottomWidthColumn2 });
        tableEtape1NumContrat.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        if (NO_BORDER) {
            tableEtape1NumContrat.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        }
        tableEtape1NumContrat.getDefaultCell().setPaddingTop(5f);
        tableEtape1NumContrat.getDefaultCell().setPaddingBottom(5f);
        tableEtape1NumContrat.setSpacingBefore(0f);
        tableEtape1NumContrat.setSpacingAfter(0f);

        PdfPCell cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setFixedHeight(25f);
        cell.addElement(new Phrase(createNomPrenomAssure(assure), IPdfCreator.SINISTRE_BODY_BOLD));

        tableEtape1.addCell(new Phrase("Assuré : ", IPdfCreator.SINISTRE_BODY_BOLD));
        tableEtape1.addCell(cell);

        if (assure.getAdresse() != null) {
            tableEtape1.addCell(new Phrase("Adresse : ", IPdfCreator.SINISTRE_BODY_BOLD));
            tableEtape1.addCell(new Phrase(assure.getAdresse(), IPdfCreator.SINISTRE_BODY_BOLD));
        }

        tableEtape1.addCell(new Phrase("", IPdfCreator.SINISTRE_BODY_FONT));
        tableEtape1.addCell(new Phrase(createCodePostalAndVilleAssure(assure), IPdfCreator.SINISTRE_BODY_BOLD));

        if (assure.getQualite() != null) {
            tableEtape1.addCell(new Phrase("Qualité : ", IPdfCreator.SINISTRE_BODY_BOLD));
            tableEtape1.addCell(new Phrase(assure.getQualite(), IPdfCreator.SINISTRE_BODY_BOLD));
        }
        if (assure.getEmail() != null) {
            tableEtape1.addCell(new Phrase("E-mail : ", IPdfCreator.SINISTRE_BODY_BOLD));
            tableEtape1.addCell(new Phrase(assure.getEmail(), IPdfCreator.SINISTRE_BODY_BOLD));
        }

        if (assure.getTelephoneMobile() != null && !"".equals(assure.getTelephoneMobile())) {
            tableEtape1.addCell(new Phrase("Téléphone mobile : ", IPdfCreator.SINISTRE_BODY_BOLD));
            tableEtape1.addCell(new Phrase(insertSpaceCharacterToPhoneNumber(assure.getTelephoneMobile()),
                            IPdfCreator.SINISTRE_BODY_BOLD));
        }
        if (assure.getTelephoneDomicile() != null && !"".equals(assure.getTelephoneDomicile())) {
            tableEtape1.addCell(new Phrase("Téléphone domicile : ", IPdfCreator.SINISTRE_BODY_BOLD));
            tableEtape1.addCell(new Phrase(insertSpaceCharacterToPhoneNumber(assure.getTelephoneDomicile()),
                            IPdfCreator.SINISTRE_BODY_BOLD));
        }
        if (assure.getTelephoneBureau() != null && !"".equals(assure.getTelephoneBureau())) {
            tableEtape1.addCell(new Phrase("Téléphone bureau : ", IPdfCreator.SINISTRE_BODY_BOLD));
            tableEtape1.addCell(new Phrase(insertSpaceCharacterToPhoneNumber(assure.getTelephoneBureau()),
                            IPdfCreator.SINISTRE_BODY_BOLD));
        }

        table.addCell(tableEtape1);

        tableEtape1NumContrat.addCell(new Phrase("Numéro de client : ", IPdfCreator.SINISTRE_BODY_BOLD));
        tableEtape1NumContrat.addCell(new Phrase(numeroClient, IPdfCreator.SINISTRE_BODY_BOLD));

        tableEtape1NumContrat.addCell(new Phrase("Numéro de contrat habitation : ", IPdfCreator.SINISTRE_BODY_BOLD));
        tableEtape1NumContrat.addCell(new Phrase(numeroContrat, IPdfCreator.SINISTRE_BODY_BOLD));

        table.addCell(tableEtape1NumContrat);

        return table;
    }

    /**
     * Crée la ligne du nom + prénom selon les renseignements disponibles de
     * l'assuré
     * 
     * @param assure
     * @return String
     */
    protected String createNomPrenomAssure(Assure assure) {
        String nom = assure.getNom();
        String prenom = assure.getPrenom();
        String nomPrenom = "";

        if (nom != null) {
            nomPrenom += nom.toUpperCase() + " ";
        }
        if (prenom != null) {
            nomPrenom += StringUtils.capitalize(prenom);
        }
        return nomPrenom;
    }

    /**
     * Crée la ligne du code postal + ville selon les renseignements disponibles
     * de l'assuré
     * 
     * @param assure
     * @return String
     */
    protected String createCodePostalAndVilleAssure(Assure assure) {
        String codePostal = assure.getCodePostal();
        String ville = assure.getVille();
        String codePostalAndVille = "";

        if (codePostal != null) {
            codePostalAndVille += codePostal + " ";
        }
        if (ville != null) {
            codePostalAndVille += ville;
        }
        return codePostalAndVille;
    }

    /**
     * initialise le texte de fin du document PDF
     * 
     * @return PdfPTable
     */
    protected PdfPTable initializeEndingText(boolean isGProx) {
        int[] tableTotalSize = {90 };

        PdfPTable table = pdfCreator.createTable(tableTotalSize);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingBefore(20f);
        table.setSpacingAfter(10f);
        table.setKeepTogether(Boolean.TRUE);
        if (NO_BORDER) {
            table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        }

        PdfPTable tableTop = pdfCreator.createTable(tableTotalSize);
        tableTop.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        tableTop.getDefaultCell().setBorderWidth(1.5f);
        tableTop.getDefaultCell().setPadding(5f);

        Phrase phrase = new Phrase();
        phrase.add(new Chunk(createBoxEndText(), IPdfCreator.SINISTRE_BODY_BOLD));
        tableTop.addCell(phrase);

        PdfPTable tableMiddle = pdfCreator.createTable(tableTotalSize);
        tableMiddle.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        tableMiddle.setSpacingBefore(10f);
        tableMiddle.getDefaultCell().setPaddingTop(30f);
        if (NO_BORDER) {
            tableMiddle.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        }
        tableMiddle.addCell(new Phrase("Votre Service Client Internet Generali", IPdfCreator.SINISTRE_BODY_BOLD));

        PdfPTable tableBottom = pdfCreator.createTable(tableTotalSize);
        tableBottom.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        tableBottom.setSpacingBefore(10f);
        tableBottom.getDefaultCell().setPaddingTop(30f);
        if (NO_BORDER) {
            tableBottom.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        }

        Phrase phraseLoiInfo = new Phrase();
        phraseLoiInfo.add(new Chunk(createLastEndText(isGProx), IPdfCreator.SINISTRE_BODY_FONT));
        tableBottom.addCell(phraseLoiInfo);

        table.addCell(tableTop);
        table.addCell(tableMiddle);
        table.addCell(tableBottom);

        return table;
    }

    /**
     * Retourne le texte de l'encadré de fin du PDF
     * 
     * @return String
     */
    private String createBoxEndText() {
        StringBuffer content = new StringBuffer();
        content
                        .append(
                                        "L'enregistrement de cette déclaration ne constitue pas un accord de prise en charge, ni")
                        .append(" une appréciation des responsabilités de la part de GENERALI IARD. Il vous incombe de")
                        .append(
                                        " conserver tous les éléments permettant la constatation et l'estimation de vos dommages.");
        return content.toString();
    }

    /**
     * Retourne le dernier texte de fin du PDF
     * 
     * @return String
     */
    private String createLastEndText(boolean isGProx) {
        StringBuffer content = new StringBuffer();
        content
                        .append(
                                        "Conformément à la loi Informatique et Libertés du 6 janvier 1978, vous disposez d'un droit d'accès et de rectification ")
                        .append("des données qui vous concernent. Vous pouvez exercer ce droit en vous adressant à ");

        // if (isGProx) {
        // content.append("GENERALI IARD - 7 boulevard HAUSSMANN 75456 PARIS CEDEX 09 - Tél : 0 969 369 969. ");
        // } else {
        // content.append("GENERALI IARD - 7 boulevard HAUSSMANN 75456 PARIS CEDEX 09 - Tél : 01 58 38 40 00. ");
        // }
        content.append("GENERALI IARD - 7 boulevard HAUSSMANN 75456 PARIS CEDEX 09. ");

        content
                        .append(
                                        "Ces informations sont destinées à Generali IARD et sont nécessaires au traitement de votre dossier. ")
                        .append(
                                        "Ces informations sont susceptibles d'être transmises à des tiers pour les besoins de la gestion de votre dossier, ")
                        .append(
                                        "notamment à votre conseiller. Vous acceptez expressément que les données vous concernant leur soient ainsi ")
                        .append("transmises.");
        return content.toString();
    }

    /**
     * Renvoi un numéro de téléphone avec des espaces tous les 2 chiffres
     * 
     * @param telephone, le numéro de téléphone initial
     * @return Numéro de téléphone avec les espaces tous les 2 chiffres
     */
    private String insertSpaceCharacterToPhoneNumber(String telephone) {

        if (telephone != null && !telephone.equals("")) {
            char[] telephoneAsArray = telephone.toCharArray();
            char[] telephoneWithSpaceAsArray = new char[14];

            if (telephoneAsArray.length == 10) {
                telephoneWithSpaceAsArray[0] = telephoneAsArray[0];
                telephoneWithSpaceAsArray[1] = telephoneAsArray[1];
                telephoneWithSpaceAsArray[2] = ' ';
                telephoneWithSpaceAsArray[3] = telephoneAsArray[2];
                telephoneWithSpaceAsArray[4] = telephoneAsArray[3];
                telephoneWithSpaceAsArray[5] = ' ';
                telephoneWithSpaceAsArray[6] = telephoneAsArray[4];
                telephoneWithSpaceAsArray[7] = telephoneAsArray[5];
                telephoneWithSpaceAsArray[8] = ' ';
                telephoneWithSpaceAsArray[9] = telephoneAsArray[6];
                telephoneWithSpaceAsArray[10] = telephoneAsArray[7];
                telephoneWithSpaceAsArray[11] = ' ';
                telephoneWithSpaceAsArray[12] = telephoneAsArray[8];
                telephoneWithSpaceAsArray[13] = telephoneAsArray[9];
                return String.valueOf(telephoneWithSpaceAsArray);
            } else {
                return telephone;
            }
        } else {
            return telephone;
        }
    }

    /**
     * Crée la ligne du nom + prénom selon les renseignements disponibles de
     * l'assuré
     * 
     * @param coordonneesIntermedaire
     * @return String
     */
    protected String createNomPrenomIntermediaire(CoordonneesIntermediaires coordonneesIntermedaire) {
        String nomPrenom = "";

        if (coordonneesIntermedaire.getNom() != null && StringUtils.isNotBlank(coordonneesIntermedaire.getNom())) {
            nomPrenom = coordonneesIntermedaire.getNom() + " ";
        }
        if (coordonneesIntermedaire.getPrenom() != null && StringUtils.isNotBlank(coordonneesIntermedaire.getPrenom())) {
            nomPrenom += coordonneesIntermedaire.getPrenom();
        }

        if (StringUtils.isNotBlank(nomPrenom)) {
            return nomPrenom;
        } else {
            return null;
        }
    }

}
