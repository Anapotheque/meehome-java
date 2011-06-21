package fr.generali.gfb.ws.sinistre.pdf.origineconnecte;

import org.apache.commons.lang.StringUtils;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

import fr.generali.espaceclient.common.util.FormattingUtils;
import fr.generali.espaceclient.proxy.generic.domain.InformationIntermediaire;
import fr.generali.gfb.ws.sinistre.dto.OrigineDeclaration.TypeOrigine;
import fr.generali.gfb.ws.sinistre.pdf.CommonPdfSinistreService;
import fr.generali.gfb.ws.sinistre.persistence.domain.auto.DommagesAuto;
import fr.generali.gfb.ws.sinistre.persistence.domain.auto.SinistreAuto;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.Assure;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;
import fr.generali.gfb.ws.sinistre.util.pdf.IPdfCreator;
import fr.generali.gfb.ws.sinistre.util.pdf.TextPdfCreator;

/**
 * @author Mickael Morier
 */
public class CommonPdfSinistreOrigineConnecteService extends CommonPdfSinistreService {

    public CommonPdfSinistreOrigineConnecteService(IPdfCreator pdfCreator) {
        super(pdfCreator);
    }

    /**
     * Réservé aux tests unitaires
     */
    protected CommonPdfSinistreOrigineConnecteService() {
        super(new TextPdfCreator());
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
                    Boolean isClientInternet, String typeDeclaration, Boolean isCourtier) throws DocumentException {
        initializeIntroductionText(pdf, declarationSinistre, isClientInternet, typeDeclaration, TypeOrigine.CONNECTE,isCourtier);
    }

    /**
     * initialise les infos de l'assuré
     * 
     * @param pdf
     * @param assure
     * @param numeroContrat
     * @return PdfPTable
     */
    protected PdfPTable initializeAssure(float width, Assure assure, String numeroContrat) {
        return initializeAssure(width, assure, numeroContrat, null, TypeOrigine.CONNECTE);
    }

    protected Document initializeAutoDeclaSinistre(Document pdf, DeclarationSinistre declarationSinistre,
                    Boolean isClientInternet, InformationIntermediaire infosPortefeuille, String libelleTypeSinistre, boolean isCourtier)
                    throws DocumentException {

        final SinistreAuto sinistre = (SinistreAuto ) declarationSinistre.getSinistre();

        pdf.add(initializeCoordonneesClient(declarationSinistre));
        pdf.add(initializeDate(declarationSinistre));

        // Initialise Objet du document
        pdf.add(initializeObjet("Votre déclaration en ligne d'un sinistre " + libelleTypeSinistre));

        // Texte d'introduction
        initializeIntroductionText(pdf, declarationSinistre, isClientInternet, TYPE_DECLA_AUTO, isCourtier);

        // Init des coordonnées intermediaire
        pdf.add(initCoordonneesAgence(infosPortefeuille,isCourtier));

        final Assure assure = declarationSinistre.getAssure();

        final PdfPTable tableEtape1Title = pdfCreator.createTable(new int[] {100 });
        tableEtape1Title.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        tableEtape1Title.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        tableEtape1Title.getDefaultCell().setBackgroundColor(GENERALI_COLOR);
        tableEtape1Title.addCell(new Phrase("ETAPE 1 : L'ASSURE", ETAPE_FONT));
        tableEtape1Title.setSpacingAfter(10f);

        pdf.add(tableEtape1Title);

        pdf.add(initializeAssureAuto(pdf.getPageSize().getWidth(), assure, declarationSinistre.getNumeroContrat(),
                        sinistre.getImmatriculation(), declarationSinistre.getNumClient()));

        final PdfPTable tableEtape2Title = pdfCreator.createTable(new int[] {100 });
        tableEtape2Title.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        tableEtape2Title.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        tableEtape2Title.getDefaultCell().setBackgroundColor(GENERALI_COLOR);
        tableEtape2Title.addCell(new Phrase("ETAPE 2 : LE SINISTRE", ETAPE_FONT));

        pdf.add(tableEtape2Title);
        pdf.add(Chunk.NEWLINE);
        if (sinistre != null) {
            final StringBuffer dateSinistreAsString = new StringBuffer("");
            if (declarationSinistre.getSinistre().getDateSinistre() != null) {
                dateSinistreAsString.append(FormattingUtils.formatToDate(declarationSinistre.getSinistre()
                                .getDateSinistre()));
            }
            // Etape 2 : le sinistre
            if (dateSinistreAsString != null && !dateSinistreAsString.equals("")) {
                // Heure et date du sinistre
                final Phrase phrase = new Phrase();
                final String content = "Date du sinistre (ou à défaut date à laquelle vous en avez eu connaissance) : ";
                // Si heure debut renseignée :
                dateSinistreAsString.append(afficherHeureSinistre(sinistre));
                phrase.add(new Chunk(content, IPdfCreator.SINISTRE_BODY_BOLD));
                phrase.add(new Chunk(dateSinistreAsString.toString(), IPdfCreator.SINISTRE_BODY_BOLD));
                pdf.add(phrase);

                pdf.add(Chunk.NEWLINE);
                pdf.add(Chunk.NEWLINE);
            }
            // Circonstances du sinistre
            if (sinistre.getCirconstances() != null && !sinistre.getCirconstances().equals("")) {
                pdf.add(new Phrase("Circonstances du sinistre : ", IPdfCreator.SINISTRE_BODY_BOLD));
                pdf.add(new Phrase(sinistre.getCirconstances(), IPdfCreator.SINISTRE_BODY_FONT));
                pdf.add(Chunk.NEWLINE);
                pdf.add(Chunk.NEWLINE);
            }
        }

        return pdf;
    }

    private String afficherHeureSinistre(SinistreAuto sinistre) {
        String ret = "";
        if (isAfficherHeure(sinistre)) {
            ret =
                            " entre " + pad0(sinistre.getHeureDebut()) + "h" + pad0(sinistre.getMinuteDebut()) + " et "
                                            + pad0(sinistre.getHeureFin()) + "h" + pad0(sinistre.getMinuteFin());

        }
        return ret;
    }

    static boolean isAfficherHeure(SinistreAuto sinistre) {
        final boolean notNull =
                        sinistre.getHeureDebut() != null && sinistre.getMinuteDebut() != null
                                        && sinistre.getHeureFin() != null && sinistre.getMinuteFin() != null;
        final boolean afficher =
                        notNull
                                        && !(sinistre.getHeureDebut() == 0 && sinistre.getMinuteDebut() == 0
                                                        && sinistre.getHeureFin() == 0 && sinistre.getMinuteFin() == 0);

        return afficher;

    }

    static String pad0(Integer str) {
        return StringUtils.leftPad(str.toString(), 2, '0');
    }

    /**
     * initialise les données de l'assuré Auto
     * 
     * @param width
     * @param assure
     * @param numeroContrat
     * @param immatriculation
     * @param idRce
     * @return PdfPTable
     */
    private PdfPTable initializeAssureAuto(float width, Assure assure, String numeroContrat, String immatriculation,
                    String idRce) {
        final int tableTotalSize = 30;
        final int tableTopWidthColumn1 = 6;
        final int tableTopWidthColumn2 = 24;
        final int tableBottomWidthColumn1 = 9;
        final int tableBottomWidthColumn2 = 21;

        final PdfPTable tableEtape1Assure = initTableEtape1AssureAuto(tableTopWidthColumn1, tableTopWidthColumn2);
        remplirTableAssure(assure, tableEtape1Assure);

        final PdfPTable tableEtape1NumContrat =
                        initTableEtape1AssureAuto(tableBottomWidthColumn1, tableBottomWidthColumn2);
        remplirTableNumContrat(numeroContrat, immatriculation, idRce, tableEtape1NumContrat);

        final PdfPTable table = initTableAssureAuto(width, tableTotalSize);
        table.addCell(tableEtape1Assure);
        table.addCell(tableEtape1NumContrat);
        return table;
    }

    private void remplirTableNumContrat(String numeroContrat, String immatriculation, String idRce,
                    final PdfPTable tableEtape1NumContrat) {

        tableEtape1NumContrat.addCell(new Phrase("Numéro de client : ", IPdfCreator.SINISTRE_BODY_BOLD));
        tableEtape1NumContrat.addCell(new Phrase(idRce, IPdfCreator.SINISTRE_BODY_BOLD));

        tableEtape1NumContrat.addCell(new Phrase("Numéro de contrat auto : ", IPdfCreator.SINISTRE_BODY_BOLD));
        tableEtape1NumContrat.addCell(new Phrase(numeroContrat, IPdfCreator.SINISTRE_BODY_BOLD));

        tableEtape1NumContrat.addCell(new Phrase("Immatriculation du véhicule : ", IPdfCreator.SINISTRE_BODY_BOLD));
        tableEtape1NumContrat.addCell(new Phrase(immatriculation, IPdfCreator.SINISTRE_BODY_BOLD));
    }

    private void remplirTableAssure(Assure assure, final PdfPTable tableEtape1) {
        final PdfPCell cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setFixedHeight(25f);
        final String nomPrenomAssure = createNomPrenomAssure(assure);
        cell.addElement(new Phrase(nomPrenomAssure, IPdfCreator.SINISTRE_BODY_BOLD));

        tableEtape1.addCell(new Phrase("Assuré : ", IPdfCreator.SINISTRE_BODY_BOLD));
        tableEtape1.addCell(cell);

        ajouterAdresse(assure, tableEtape1);
        ajouterCodePostal(assure, tableEtape1);
        ajouterQualite(assure, tableEtape1);
        ajouterEmail(assure, tableEtape1);
        ajouterTelMobile(assure, tableEtape1);
        ajouterTelDomicile(assure, tableEtape1);
        ajouterTelBureau(assure, tableEtape1);
    }

    private void ajouterCodePostal(Assure assure, final PdfPTable tableEtape1) {
        final String codePostalAndVille = createCodePostalAndVilleAssure(assure);

        tableEtape1.addCell(new Phrase("", IPdfCreator.SINISTRE_BODY_FONT));
        tableEtape1.addCell(new Phrase(codePostalAndVille, IPdfCreator.SINISTRE_BODY_BOLD));
    }

    private void ajouterTelBureau(Assure assure, final PdfPTable tableEtape1) {
        if (assure.getTelephoneBureau() != null && !"".equals(assure.getTelephoneBureau())) {
            tableEtape1.addCell(new Phrase("Téléphone bureau : ", IPdfCreator.SINISTRE_BODY_BOLD));
            tableEtape1.addCell(new Phrase(insertSpaceCharacterToPhoneNumber(assure.getTelephoneBureau()),
                            IPdfCreator.SINISTRE_BODY_BOLD));
        }
    }

    private void ajouterTelDomicile(Assure assure, final PdfPTable tableEtape1) {
        if (assure.getTelephoneDomicile() != null && !"".equals(assure.getTelephoneDomicile())) {
            tableEtape1.addCell(new Phrase("Téléphone domicile : ", IPdfCreator.SINISTRE_BODY_BOLD));
            tableEtape1.addCell(new Phrase(insertSpaceCharacterToPhoneNumber(assure.getTelephoneDomicile()),
                            IPdfCreator.SINISTRE_BODY_BOLD));
        }
    }

    private void ajouterTelMobile(Assure assure, final PdfPTable tableEtape1) {
        if (assure.getTelephoneMobile() != null && !"".equals(assure.getTelephoneMobile())) {
            tableEtape1.addCell(new Phrase("Téléphone mobile : ", IPdfCreator.SINISTRE_BODY_BOLD));
            tableEtape1.addCell(new Phrase(insertSpaceCharacterToPhoneNumber(assure.getTelephoneMobile()),
                            IPdfCreator.SINISTRE_BODY_BOLD));
        }
    }

    private void ajouterEmail(Assure assure, final PdfPTable tableEtape1) {
        if (assure.getEmail() != null) {
            tableEtape1.addCell(new Phrase("E-mail : ", IPdfCreator.SINISTRE_BODY_BOLD));
            tableEtape1.addCell(new Phrase(assure.getEmail(), IPdfCreator.SINISTRE_BODY_BOLD));
        }
    }

    private void ajouterQualite(Assure assure, final PdfPTable tableEtape1) {
        if (assure.getQualite() != null) {
            tableEtape1.addCell(new Phrase("Qualité : ", IPdfCreator.SINISTRE_BODY_BOLD));
            tableEtape1.addCell(new Phrase(assure.getQualite(), IPdfCreator.SINISTRE_BODY_BOLD));
        }
    }

    private void ajouterAdresse(Assure assure, final PdfPTable tableEtape1) {
        if (assure.getAdresse() != null) {
            tableEtape1.addCell(new Phrase("Adresse : ", IPdfCreator.SINISTRE_BODY_BOLD));
            tableEtape1.addCell(new Phrase(assure.getAdresse(), IPdfCreator.SINISTRE_BODY_BOLD));
        }
    }

    private PdfPTable initTableEtape1AssureAuto(final int tableTopWidthColumn1, final int tableTopWidthColumn2) {
        final PdfPTable tableEtape1 = pdfCreator.createTable(new int[] {tableTopWidthColumn1, tableTopWidthColumn2 });
        tableEtape1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        if (NO_BORDER) {
            tableEtape1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        }
        tableEtape1.getDefaultCell().setPaddingTop(5f);
        tableEtape1.getDefaultCell().setPaddingBottom(5f);
        tableEtape1.setSpacingBefore(0f);
        tableEtape1.setSpacingAfter(0f);
        return tableEtape1;
    }

    private PdfPTable initTableAssureAuto(float width, final int tableTotalSize) {
        final PdfPTable table = pdfCreator.createTable(new int[] {tableTotalSize });
        table.setTotalWidth(width);

        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        if (NO_BORDER) {
            table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        }
        table.getDefaultCell().setPadding(0f);
        table.setSpacingBefore(0f);
        table.setSpacingAfter(0f);
        return table;
    }

    /**
     * initialise les dommages auto
     * 
     * @param pdf
     * @param dommagesAuto
     * @throws DocumentException
     */
    protected void initializeAutoDommages(Document pdf, DommagesAuto dommagesAuto) throws DocumentException {
        if (dommagesAuto != null) {
            final PdfPTable tableEtape3Title = pdfCreator.createTable(new int[] {100 });
            tableEtape3Title.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            tableEtape3Title.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            tableEtape3Title.getDefaultCell().setBackgroundColor(GENERALI_COLOR);
            tableEtape3Title.addCell(new Phrase("ETAPE 3 : LES DOMMAGES", ETAPE_FONT));

            pdf.add(tableEtape3Title);
            pdf.add(Chunk.NEWLINE);
            // Dommages du vehicule
            if (StringUtils.isNotEmpty(dommagesAuto.getDescription())) {
                pdf.add(new Phrase("Dommages subis par le véhicule : ", IPdfCreator.SINISTRE_BODY_BOLD));
                pdf.add(new Phrase(dommagesAuto.getDescription(), IPdfCreator.SINISTRE_BODY_FONT));
                pdf.add(Chunk.NEWLINE);
                pdf.add(Chunk.NEWLINE);
            }
            if (Boolean.TRUE.equals(dommagesAuto.getDepotGarage())) {
                // On cherche le lieu du vehicule
                if (StringUtils.isNotEmpty(dommagesAuto.getLieuVehicule())) {
                    pdf.add(new Phrase("Lieu où votre véhicule peut être vu : ", IPdfCreator.SINISTRE_BODY_BOLD));
                    pdf.add(new Phrase(dommagesAuto.getLieuVehicule(), IPdfCreator.SINISTRE_BODY_FONT));
                    pdf.add(Chunk.NEWLINE);
                    pdf.add(Chunk.NEWLINE);
                }
            } else {
                if (StringUtils.isNotEmpty(dommagesAuto.getCoordonnesGarage())) {
                    pdf.add(new Phrase("Garage agréé choisi : ", IPdfCreator.SINISTRE_BODY_BOLD));
                    pdf.add(new Phrase(dommagesAuto.getCoordonnesGarage(), IPdfCreator.SINISTRE_BODY_FONT));
                    pdf.add(Chunk.NEWLINE);
                    pdf.add(Chunk.NEWLINE);
                }
            }
        }
    }

    /**
     * Renvoi un numéro de téléphone avec des espaces tous les 2 chiffres
     * 
     * @param telephone, le numéro de téléphone initial
     * @return Numéro de téléphone avec les espaces tous les 2 chiffres
     */
    private String insertSpaceCharacterToPhoneNumber(String telephone) {
        String ret = telephone;
        if (StringUtils.isNotEmpty(telephone) && telephone.length() == 10) {
            final char[] telephoneAsArray = telephone.toCharArray();
            char[] telephoneWithSpaceAsArray = new char[14];

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
            ret = String.valueOf(telephoneWithSpaceAsArray);

        }
        return ret;
    }

}
