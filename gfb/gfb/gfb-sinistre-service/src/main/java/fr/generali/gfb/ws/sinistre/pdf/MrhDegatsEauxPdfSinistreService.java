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
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

import fr.generali.espaceclient.common.util.FormattingUtils;
import fr.generali.espaceclient.proxy.generic.domain.InformationIntermediaire;
import fr.generali.gfb.ws.sinistre.dto.OrigineDeclaration.TypeOrigine;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.Assure;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux.Cause;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux.Dommages;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux.Infiltration;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux.Mobilier;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux.Murs;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux.Piece;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux.Plafonds;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux.SinistreMRHDegatsEaux;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux.Sol;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux.Tiers;
import fr.generali.gfb.ws.sinistre.util.pdf.IPdfCreator;
import fr.generali.socle.exceptions.TechnicalException;

/**
 * @author Mickael Morier
 */
public class MrhDegatsEauxPdfSinistreService extends CommonPdfSinistreService {

    private final TypeOrigine typeOrigine;

    public MrhDegatsEauxPdfSinistreService(IPdfCreator pdfCreator, TypeOrigine typeOrigine) {
        super(pdfCreator);
        if (typeOrigine == null) {
            throw new IllegalArgumentException("Le type origine (CONNECTE ou DECONNECTE) est obligatoire");
        }

        this.typeOrigine = typeOrigine;
    }

    /**
     * Crée le document PDF d'un Dégats Eaux à partir de sa déclaration de
     * sinistre
     * 
     * @param declarationSinistre
     * @param isClientInternet
     * @param infosPortefeuille
     * @return document PDF sous forme d'un tableau de byte
     */
    public byte[] createPdfDegatsEauxMRH(DeclarationSinistre declarationSinistre, Boolean isClientInternet,
                    InformationIntermediaire infosPortefeuille, boolean isCourtier) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document pdf = pdfCreator.createPdfSinistreDocument(baos, MARGIN_LEFT, MARGIN_RIGHT, MARGIN_TOP, MARGIN_BOTTOM);

        try {
            pdf.add(initializeCoordonneesClient(declarationSinistre));
            pdf.add(initializeDate(declarationSinistre));

            // Initialise Objet du document
            pdf.add(initializeObjet("Votre déclaration en ligne d'un sinistre Dégât des Eaux"));

            // Texte d'introduction
            initializeIntroductionText(pdf, declarationSinistre, isClientInternet, TYPE_DECLA_MRH, typeOrigine, isCourtier);

            if (TypeOrigine.CONNECTE.equals(typeOrigine)) {
                // Init des coordonnées intermediaire
                pdf.add(initCoordonneesAgence(infosPortefeuille,isCourtier));
            }

            // Bloc assuré
            createAssure(pdf, declarationSinistre);

            // Bloc sinistre
            createSinistre(pdf, declarationSinistre);

            pdf.add(initializeEndingText("67".equals(declarationSinistre.getCodeCompagnie())));
        } catch (DocumentException e) {
            throw new TechnicalException("Une exception est survenue lors de la génération du PDF", e);
        }

        pdf.close();
        return baos.toByteArray();
    }

    /**
     * Crée le bloc Assuré
     * 
     * @param declarationSinistre
     * @throws DocumentException
     */
    private void createAssure(Document pdf, DeclarationSinistre declarationSinistre) throws DocumentException {
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
    }

    /**
     * Crée le bloc lié au sinistre
     * 
     * @param declarationSinistre
     * @throws DocumentException
     */
    private void createSinistre(Document pdf, DeclarationSinistre declarationSinistre) throws DocumentException {
        SinistreMRHDegatsEaux sinistreBien = (SinistreMRHDegatsEaux ) declarationSinistre.getSinistre();

        PdfPTable tableEtape2Title = pdfCreator.createTable(new int[] {100 });
        tableEtape2Title.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        tableEtape2Title.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        tableEtape2Title.getDefaultCell().setBackgroundColor(GENERALI_COLOR);
        tableEtape2Title.addCell(new Phrase("ETAPE 2 : LE SINISTRE", ETAPE_FONT));

        pdf.add(tableEtape2Title);
        pdf.add(Chunk.NEWLINE);

        if (sinistreBien != null) {
            // adresse du bien
            createAdresseBien(pdf, sinistreBien);

            // date du sinistre
            createDateSinistre(pdf, sinistreBien);

            // cause du sinistre
            createCauseSinistre(pdf, sinistreBien);

            // Origine du sinistre
            createOrigineSinistre(pdf, sinistreBien);

            // Cause réparé
            createCauseRepare(pdf, sinistreBien);

            // Conséquences
            createConsequence(pdf, sinistreBien);

            // Dommages
            createDommages(pdf, sinistreBien);
        }
    }

    /**
     * Crée le bloc de l'adrese du bien
     * 
     * @param sinistreBien
     * @throws DocumentException
     */
    private void createAdresseBien(Document pdf, SinistreMRHDegatsEaux sinistreBien) throws DocumentException {
        if (sinistreBien.getBienConcerne() != null && sinistreBien.getBienConcerne().getIsResidencePrincipale() != null) {
            Phrase phrase = new Phrase();
            phrase.add(new Chunk("Le bien concerné ", IPdfCreator.SINISTRE_BODY_BOLD));

            if (sinistreBien.getBienConcerne().getIsResidencePrincipale()) {
                phrase.add(new Chunk("est ma résidence principale.", IPdfCreator.SINISTRE_BODY_FONT));
                pdf.add(phrase);
            } else {
                phrase.add(new Chunk("est situé à l'adresse ci dessous : ", IPdfCreator.SINISTRE_BODY_FONT));
                pdf.add(phrase);

                com.lowagie.text.List adresseDifferenteList = null;
                adresseDifferenteList = new com.lowagie.text.List(com.lowagie.text.List.UNORDERED);
                adresseDifferenteList.setListSymbol("");
                adresseDifferenteList.setIndentationLeft(LIST_PROPERTY_INDENTATION);

                if (sinistreBien.getBienConcerne().getAdresse() != null) {
                    adresseDifferenteList.add(new ListItem(sinistreBien.getBienConcerne().getAdresse(),
                                    IPdfCreator.SINISTRE_BODY_FONT));
                }

                if (sinistreBien.getBienConcerne().getCodePostal() != null
                                || sinistreBien.getBienConcerne().getVille() != null) {
                    String content = "";
                    if (sinistreBien.getBienConcerne().getCodePostal() != null) {
                        content = sinistreBien.getBienConcerne().getCodePostal() + " ";
                    }
                    if (sinistreBien.getBienConcerne().getVille() != null) {
                        content += sinistreBien.getBienConcerne().getVille();
                    }
                    adresseDifferenteList.add(new ListItem(content, IPdfCreator.SINISTRE_BODY_FONT));
                }

                pdf.add(adresseDifferenteList);
            }
            pdf.add(Chunk.NEWLINE);
        }
    }

    /**
     * Crée le bloc de la date du sinistre
     * 
     * @param sinistreBien
     * @throws DocumentException
     */
    private void createDateSinistre(Document pdf, SinistreMRHDegatsEaux sinistreBien) throws DocumentException {
        if (sinistreBien.getDateSinistre() != null) {
            Phrase phrase = new Phrase();
            String content = "Date du sinistre (ou à défaut date à laquelle vous en avez eu connaissance) : ";
            phrase.add(new Chunk(content, IPdfCreator.SINISTRE_BODY_BOLD));
            phrase.add(new Chunk(FormattingUtils.formatToDate(sinistreBien.getDateSinistre()),
                            IPdfCreator.SINISTRE_BODY_FONT));
            pdf.add(phrase);
            pdf.add(Chunk.NEWLINE);
        }
    }

    /**
     * Crée le bloc de cause du sinistre
     * 
     * @param sinistreBien
     * @throws DocumentException
     */
    private void createCauseSinistre(Document pdf, SinistreMRHDegatsEaux sinistreBien) throws DocumentException {
        if (sinistreBien.getCause() != null) {
            PdfPTable table = pdfCreator.createTable(new int[] {15, 70 });
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            table.getDefaultCell().setPadding(0f);
            if (NO_BORDER) {
                table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            }
            table.setSpacingBefore(0f);
            table.setSpacingAfter(0f);

            PdfPTable tableColumn1 = pdfCreator.createTable(new int[] {15 });
            tableColumn1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            if (NO_BORDER) {
                tableColumn1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            }

            PdfPTable tableColumn2 = pdfCreator.createTable(new int[] {70 });
            tableColumn2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            if (NO_BORDER) {
                tableColumn2.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            }

            String content = "Cause du sinistre : ";
            tableColumn1.addCell(new Phrase(content, IPdfCreator.SINISTRE_BODY_BOLD));

            List<Cause> causes = sinistreBien.getCause();

            for (Cause cause : causes) {
                if (cause != null && cause.getValue() != null) {
                    content = cause.getValue();
                    tableColumn2.addCell(new Phrase("- " + content, IPdfCreator.SINISTRE_BODY_FONT));
                }
            }

            if (sinistreBien.getAutreCause() != null && !sinistreBien.getAutreCause().equals("")) {
                tableColumn2.addCell(new Phrase("- " + sinistreBien.getAutreCause(), IPdfCreator.SINISTRE_BODY_FONT));
            }
            
            if (sinistreBien.getInfiltration() != null) {
            	List<Infiltration> causeInfiltrations = sinistreBien.getInfiltration();
                if (causeInfiltrations != null && causeInfiltrations.size() > 0) {
                	content = "- infiltrations par  : ";
	                	for (int i = 0; i < causeInfiltrations.size(); i++) {
	                        if (causeInfiltrations.get(i) != null && causeInfiltrations.get(i).getValue() != null
	                                        && !causeInfiltrations.get(i).getValue().equals("")) {
	                            content += causeInfiltrations.get(i).getValue();
	                            if (i != causeInfiltrations.size() - 1) {
	                                content += ", ";
	                            }
	                        }
	                    }
	                tableColumn2.addCell(new Phrase(content, IPdfCreator.SINISTRE_BODY_FONT));
                }
            }
            table.addCell(tableColumn1);
            table.addCell(tableColumn2);
            pdf.add(table);
        }
    }

    /**
     * Crée le bloc lié aux origines du sinistre
     * 
     * @param sinistreBien
     * @throws DocumentException
     */
    private void createOrigineSinistre(Document pdf, SinistreMRHDegatsEaux sinistreBien) throws DocumentException {
        if (sinistreBien.getOrigineSinistre() != null && !sinistreBien.getOrigineSinistre().equals("")) {
            Phrase phrase = new Phrase();
            phrase.add(new Chunk("Lieu  d'origine du sinistre : ", IPdfCreator.SINISTRE_BODY_BOLD));
            phrase.add(new Chunk(sinistreBien.getOrigineSinistre(), IPdfCreator.SINISTRE_BODY_FONT));
            pdf.add(phrase);
            pdf.add(Chunk.NEWLINE);
            pdf.add(Chunk.NEWLINE);
        }
    }

    /**
     * Crée le bloc lié aux causes réparées
     * 
     * @param sinistreBien
     * @throws DocumentException
     */
    private void createCauseRepare(Document pdf, SinistreMRHDegatsEaux sinistreBien) throws DocumentException {
        if (sinistreBien.getIsCauseReparee() != null) {
            Phrase phrase = new Phrase();
            phrase.add(new Chunk("La cause du sinistre ", IPdfCreator.SINISTRE_BODY_BOLD));
            if (Boolean.TRUE.equals(sinistreBien.getIsCauseReparee())) {
                phrase.add(new Chunk("est réparée.", IPdfCreator.SINISTRE_BODY_FONT));
            } else {
                phrase.add(new Chunk("n'est pas réparée.", IPdfCreator.SINISTRE_BODY_FONT));
            }
            pdf.add(phrase);
            pdf.add(Chunk.NEWLINE);
            pdf.add(Chunk.NEWLINE);
        }
    }

    /**
     * Crée le bloc lié aux conséquences
     * 
     * @param sinistreBien
     * @throws DocumentException
     */
    private void createConsequence(Document pdf, SinistreMRHDegatsEaux sinistreBien) throws DocumentException {
        if (sinistreBien.getConsequence() != null) {
            if (sinistreBien.getConsequence().getIsDommageSubiParTiers() != null) {
                Phrase phrase = new Phrase();
                phrase.add(new Chunk("Vous nous déclarez ", IPdfCreator.SINISTRE_BODY_BOLD));

                if ("oui".equals(sinistreBien.getConsequence().getIsDommageSubiParTiers())) {
                    phrase.add(new Chunk("qu'un tiers a subi des dommages", IPdfCreator.SINISTRE_BODY_FONT));
                } else if ("ne sais pas".equals(sinistreBien.getConsequence().getIsDommageSubiParTiers())) { 
                	phrase.add(new Chunk("que vous ne savez pas si un tiers a subi de dommages", IPdfCreator.SINISTRE_BODY_FONT));
                } else {
                    phrase.add(new Chunk("qu'aucun tiers n'a subi de dommages", IPdfCreator.SINISTRE_BODY_FONT));
                }
                phrase.add(new Chunk(".", IPdfCreator.SINISTRE_BODY_FONT));

                pdf.add(phrase);
                pdf.add(Chunk.NEWLINE);
                pdf.add(Chunk.NEWLINE);

                createDommageTiers(pdf, sinistreBien);
            }
            if (sinistreBien.getConsequence().getIsDommageSubi() != null) {
                if (Boolean.TRUE.equals(sinistreBien.getConsequence().getIsDommageSubi())) {
                    pdf.add(new Phrase("Vous nous déclarez avoir subi des  dommages.", IPdfCreator.SINISTRE_BODY_FONT));
                } else {
                    pdf.add(new Phrase("Vous nous déclarez ne pas avoir subi de dommages.",
                                    IPdfCreator.SINISTRE_BODY_FONT));
                }
                pdf.add(Chunk.NEWLINE);
            }
        }
    }

    /**
     * Crée le bloc des dommages subis par des tiers
     * 
     * @param sinistreBien
     * @throws DocumentException
     */
    private void createDommageTiers(Document pdf, SinistreMRHDegatsEaux sinistreBien) throws DocumentException {
        Tiers tiers = sinistreBien.getConsequence().getDommageTiers();
        if (tiers != null) {
            PdfPTable table = pdfCreator.createTable(new int[] {11, 50 });
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            table.getDefaultCell().setFixedHeight(20f);
            table.setKeepTogether(Boolean.TRUE);
            if (NO_BORDER) {
                table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            }
            table.setSpacingBefore(5f);
            table.setSpacingAfter(5f);

            if (tiers.getNom() != null) {
                table.addCell(new Phrase("Nom du tiers : ", IPdfCreator.SINISTRE_BODY_BOLD));
                table.addCell(new Phrase(tiers.getNom(), IPdfCreator.SINISTRE_BODY_FONT));
            }
            if (tiers.getPrenom() != null) {
                table.addCell(new Phrase("Prénom : ", IPdfCreator.SINISTRE_BODY_BOLD));
                table.addCell(new Phrase(tiers.getPrenom(), IPdfCreator.SINISTRE_BODY_FONT));
            }
            if (tiers.getAdresse() != null || tiers.getCodePostal() != null || tiers.getVille() != null) {
                PdfPTable tableAdresseTiers = pdfCreator.createTable(new int[] {30 });
                tableAdresseTiers.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                tableAdresseTiers.getDefaultCell().setBorder(Rectangle.NO_BORDER);

                if (tiers.getAdresse() != null) {
                    tableAdresseTiers.addCell(new Phrase(tiers.getAdresse(), IPdfCreator.SINISTRE_BODY_FONT));
                }

                String codePostalAndVille = "";

                if (tiers.getCodePostal() != null) {
                    codePostalAndVille += tiers.getCodePostal() + " ";
                }
                if (tiers.getVille() != null) {
                    codePostalAndVille += tiers.getVille();
                }
                if (tiers.getCodePostal() != null || tiers.getVille() != null) {
                    tableAdresseTiers.addCell(new Phrase(codePostalAndVille, IPdfCreator.SINISTRE_BODY_FONT));
                }

                PdfPCell cellAdresse = new PdfPCell();
                cellAdresse.setFixedHeight(35f);
                cellAdresse.setBorder(Rectangle.NO_BORDER);
                cellAdresse.addElement(tableAdresseTiers);

                table.addCell(new Phrase("Adresse du tiers: ", IPdfCreator.SINISTRE_BODY_BOLD));
                table.addCell(cellAdresse);
            }
            if (tiers.getAssureur() != null) {
                table.addCell(new Phrase("Assureur du tiers : ", IPdfCreator.SINISTRE_BODY_BOLD));
                table.addCell(new Phrase(tiers.getAssureur(), IPdfCreator.SINISTRE_BODY_FONT));
            }

            pdf.add(table);
        }
    }

    /**
     * Crée le bloc lié aux dommages
     * 
     * @param sinistreBien
     * @throws DocumentException
     */
    private void createDommages(Document pdf, SinistreMRHDegatsEaux sinistreBien) throws DocumentException {
        Dommages dommages = sinistreBien.getDommages();

        PdfPTable tableEtape3Title = pdfCreator.createTable(new int[] {100 });
        tableEtape3Title.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        tableEtape3Title.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        tableEtape3Title.getDefaultCell().setBackgroundColor(GENERALI_COLOR);
        tableEtape3Title.addCell(new Phrase("ETAPE 3 : LES DOMMAGES", ETAPE_FONT));

        pdf.add(tableEtape3Title);
        pdf.add(Chunk.NEWLINE);

        if (dommages != null) {

            if (dommages.getNbPiecesEndommagees() != null) {
                Phrase phrase = new Phrase();
                phrase.add(new Chunk("Nombre de pièces endommagées : ", IPdfCreator.SINISTRE_BODY_BOLD));
                phrase
                                .add(new Chunk(String.valueOf(dommages.getNbPiecesEndommagees()),
                                                IPdfCreator.SINISTRE_BODY_FONT));
                pdf.add(phrase);
            }

            List<Piece> pieces = dommages.getPieces();
            if (pieces != null) {
                for (int i = 0; i < pieces.size(); i++) {

                    PdfPTable tableDommagePiece;
                    Piece piece = pieces.get(i);
                    PdfPCell cell;

                    if (piece != null) {
                        tableDommagePiece = pdfCreator.createTable(new int[] {100 });
                        tableDommagePiece.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                        if (NO_BORDER) {
                            tableDommagePiece.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                        }
                        tableDommagePiece.setKeepTogether(Boolean.TRUE);
                        tableDommagePiece.setSpacingBefore(0f);
                        tableDommagePiece.setSpacingAfter(0f);

                        pdf.add(Chunk.NEWLINE);
                        Phrase phrase = new Phrase();
                        phrase.add(new Chunk("PIECE " + (i + 1), IPdfCreator.BODY_FONT_BOLD_UNDERLINE));
                        if (piece.getTypePiece() != null && StringUtils.isNotBlank(piece.getTypePiece())) {
                            phrase.add(new Chunk(" : " + piece.getTypePiece(), IPdfCreator.SINISTRE_BODY_FONT));
                        }

                        cell = new PdfPCell();
                        if (NO_BORDER) {
                            cell.setBorder(Rectangle.NO_BORDER);
                        }
                        cell.addElement(phrase);

                        tableDommagePiece.addCell(cell);

                        String content = "";
                        if (piece.getLongueur() != null && StringUtils.isNotBlank(piece.getLongueur())) {
                            String longueur = piece.getLongueur().replace(".", ",");
                            content = "Longueur : " + longueur + " m";
                        }
                        if (piece.getLargeur() != null && StringUtils.isNotBlank(piece.getLargeur())) {
                            String largeur = piece.getLargeur().replace(".", ",");
                            content += "       Largeur  : " + largeur + " m";
                        }
                        if (piece.getLongueur() != null || piece.getLargeur() != null) {
                            cell = new PdfPCell();
                            if (NO_BORDER) {
                                cell.setBorder(Rectangle.NO_BORDER);
                            }
                            cell.setFixedHeight(20f);
                            cell.addElement(new Phrase(content, IPdfCreator.SINISTRE_BODY_FONT));
                            tableDommagePiece.addCell(cell);
                        }

                        cell = new PdfPCell();
                        if (NO_BORDER) {
                            cell.setBorder(Rectangle.NO_BORDER);
                        }
                        cell
                                        .addElement(new Phrase("Eléments et surfaces endommagés : ",
                                                        IPdfCreator.SINISTRE_BODY_BOLD));
                        tableDommagePiece.addCell(cell);

                        createDommagesMurs(tableDommagePiece, piece);

                        createDommagesPlafonds(tableDommagePiece, piece);

                        createDommagesSol(tableDommagePiece, piece);

                        createDommagesMobilier(tableDommagePiece, piece);

                        createDommagesCommentaire(tableDommagePiece, piece);

                        pdf.add(tableDommagePiece);
                    }
                }
            }
        }
    }

    /**
     * Crée la liste des dommages sur les murs
     * 
     * @param tableDommagePiece
     * @param piece
     */
    private void createDommagesMurs(PdfPTable tableDommagePiece, Piece piece) {
        Murs murs = piece.getMurs();
        com.lowagie.text.List mursList = null;

        if (murs != null) {
            mursList = new com.lowagie.text.List(com.lowagie.text.List.UNORDERED);
            mursList.setListSymbol("");
            mursList.setIndentationLeft(LIST_PROPERTY_INDENTATION);

            mursList.add(new ListItem("Murs : ", IPdfCreator.SINISTRE_BODY_FONT));

            com.lowagie.text.List mursSubList = new com.lowagie.text.List(com.lowagie.text.List.UNORDERED);
            mursSubList.setListSymbol("- ");
            mursSubList.setIndentationLeft(LIST_VALUE_INDENTATION);

            if (murs.getPapierPeint() != null && StringUtils.isNotBlank(murs.getPapierPeint())) {
                String papierPeint = murs.getPapierPeint().replace(".", ",");
                mursSubList.add(new ListItem("papier peint : " + papierPeint + " m²", IPdfCreator.SINISTRE_BODY_FONT));
            }
            if (murs.getPeinture() != null && StringUtils.isNotBlank(murs.getPeinture())) {
                String peinture = murs.getPeinture().replace(".", ",");
                mursSubList.add(new ListItem("peinture : " + peinture + " m²", IPdfCreator.SINISTRE_BODY_FONT));
            }
            if (murs.getAutre() != null
                            && murs.getSurfaceAutre() != null
                            && (StringUtils.isNotBlank(murs.getAutre()) || StringUtils.isNotBlank(murs
                                            .getSurfaceAutre()))) {
                String surfaceAutre = murs.getSurfaceAutre().replace(".", ",");
                mursSubList.add(new ListItem(murs.getAutre() + " : " + surfaceAutre + "m²",
                                IPdfCreator.SINISTRE_BODY_FONT));
            }
            mursList.add(mursSubList);
        }
        if (mursList != null) {
            PdfPCell cell = new PdfPCell();
            if (NO_BORDER) {
                cell.setBorder(Rectangle.NO_BORDER);
            }
            cell.addElement(mursList);
            tableDommagePiece.addCell(cell);
        }
    }

    /**
     * Crée la liste des dommages sur les plafonds
     * 
     * @param tableDommagePiece
     * @param piece
     */
    private void createDommagesPlafonds(PdfPTable tableDommagePiece, Piece piece) {
        Plafonds plafonds = piece.getPlafonds();
        com.lowagie.text.List plafondsList = null;

        if (plafonds != null) {
            plafondsList = new com.lowagie.text.List(com.lowagie.text.List.UNORDERED);
            plafondsList.setListSymbol("");
            plafondsList.setIndentationLeft(LIST_PROPERTY_INDENTATION);

            com.lowagie.text.List plafondsSubList = new com.lowagie.text.List(com.lowagie.text.List.UNORDERED);
            plafondsSubList.setListSymbol("- ");
            plafondsSubList.setIndentationLeft(LIST_VALUE_INDENTATION);

            plafondsList.add(new ListItem("Plafonds : ", IPdfCreator.SINISTRE_BODY_FONT));

            if (plafonds.getPapierPeint() != null && StringUtils.isNotBlank(plafonds.getPapierPeint())) {
                String papierPeint = plafonds.getPapierPeint().replace(".", ",");
                plafondsSubList.add(new ListItem("papier peint : " + papierPeint + " m²",
                                IPdfCreator.SINISTRE_BODY_FONT));
            }
            if (plafonds.getPeinture() != null && StringUtils.isNotBlank(plafonds.getPeinture())) {
                String peinture = plafonds.getPeinture().replace(".", ",");
                plafondsSubList.add(new ListItem("peinture : " + peinture + " m²", IPdfCreator.SINISTRE_BODY_FONT));
            }
            if (plafonds.getAutre() != null
                            && plafonds.getSurfaceAutres() != null
                            && (StringUtils.isNotBlank(plafonds.getAutre()) || StringUtils.isNotBlank(plafonds
                                            .getSurfaceAutres()))) {
                String autreSurface = plafonds.getSurfaceAutres().replace(".", ",");
                plafondsSubList.add(new ListItem(plafonds.getAutre() + " : " + autreSurface + " m²",
                                IPdfCreator.SINISTRE_BODY_FONT));
            }
            plafondsList.add(plafondsSubList);
        }
        if (plafondsList != null) {
            PdfPCell cell = new PdfPCell();
            if (NO_BORDER) {
                cell.setBorder(Rectangle.NO_BORDER);
            }
            cell.addElement(plafondsList);
            tableDommagePiece.addCell(cell);
        }
    }

    /**
     * Crée la liste des dommages sur sol
     * 
     * @param tableDommagePiece
     * @param piece
     */
    private void createDommagesSol(PdfPTable tableDommagePiece, Piece piece) {
        Sol sol = piece.getSol();
        com.lowagie.text.List solList = null;

        if (sol != null) {
            solList = new com.lowagie.text.List(com.lowagie.text.List.UNORDERED);
            solList.setListSymbol("");
            solList.setIndentationLeft(LIST_PROPERTY_INDENTATION);

            com.lowagie.text.List solSubList = new com.lowagie.text.List(com.lowagie.text.List.UNORDERED);
            solSubList.setListSymbol("- ");
            solSubList.setIndentationLeft(LIST_VALUE_INDENTATION);

            solList.add(new ListItem("Sols : ", IPdfCreator.SINISTRE_BODY_FONT));

            if (sol.getParquet() != null && StringUtils.isNotBlank(sol.getParquet())) {
                String parquet = sol.getParquet().replace(".", ",");
                solSubList.add(new ListItem("parquet : " + parquet + " m²", IPdfCreator.SINISTRE_BODY_FONT));
            }
            if (sol.getCarrelage() != null && StringUtils.isNotBlank(sol.getCarrelage())) {
                String carrelage = sol.getCarrelage().replace(".", ",");
                solSubList.add(new ListItem("carrelage : " + carrelage + " m²", IPdfCreator.SINISTRE_BODY_FONT));
            }
            if (sol.getMoquette() != null && StringUtils.isNotBlank(sol.getMoquette())) {
                String moquette = sol.getMoquette().replace(".", ",");
                solSubList.add(new ListItem("moquette : " + moquette + " m²", IPdfCreator.SINISTRE_BODY_FONT));
            }
            if (sol.getRevetementPlastique() != null && StringUtils.isNotBlank(sol.getRevetementPlastique())) {
                String revetement = sol.getRevetementPlastique().replace(".", ",");
                solSubList.add(new ListItem("revetement plastique : " + revetement + " m²",
                                IPdfCreator.SINISTRE_BODY_FONT));
            }
            if (sol.getAutre() != null
                            && sol.getSurfaceAutres() != null
                            && (StringUtils.isNotBlank(sol.getAutre()) || StringUtils
                                            .isNotBlank(sol.getSurfaceAutres()))) {
                String surfaceAutre = sol.getSurfaceAutres().replace(".", ",");
                solSubList.add(new ListItem(sol.getAutre() + " : " + surfaceAutre + " m²",
                                IPdfCreator.SINISTRE_BODY_FONT));
            }
            solList.add(solSubList);
        }
        if (solList != null) {
            PdfPCell cell = new PdfPCell();
            if (NO_BORDER) {
                cell.setBorder(Rectangle.NO_BORDER);
            }
            cell.addElement(solList);
            tableDommagePiece.addCell(cell);
        }
    }

    /**
     * Crée la liste des dommages sur le mobilier
     * 
     * @param tableDommagePiece
     * @param piece
     */
    private void createDommagesMobilier(PdfPTable tableDommagePiece, Piece piece) {
        Mobilier mobilier = piece.getMobilier();
        com.lowagie.text.List mobilierList = null;
        if (mobilier != null && mobilier.getMobilierEndommage() != null
                        && StringUtils.isNotBlank(mobilier.getMobilierEndommage())) {

            mobilierList = new com.lowagie.text.List(com.lowagie.text.List.UNORDERED);
            mobilierList.setListSymbol("");
            mobilierList.setIndentationLeft(20f);

            mobilierList.add(new ListItem("Mobilier : " + mobilier.getMobilierEndommage(),
                            IPdfCreator.SINISTRE_BODY_FONT));
        }
        if (mobilierList != null) {
            PdfPCell cell = new PdfPCell();
            if (NO_BORDER) {
                cell.setBorder(Rectangle.NO_BORDER);
            }
            cell.addElement(mobilierList);
            tableDommagePiece.addCell(cell);
        }
    }

    /**
     * Crée le commentaire des dommages
     * 
     * @param tableDommagePiece
     * @param piece
     */
    private void createDommagesCommentaire(PdfPTable tableDommagePiece, Piece piece) {
        if (piece.getCommentaire() != null && StringUtils.isNotBlank(piece.getCommentaire())) {
            PdfPCell cellCommentaires = new PdfPCell();

            cellCommentaires.setBorder(Rectangle.NO_BORDER);

            Phrase phrase2 = new Phrase();
            phrase2.add(new Chunk("Commentaires : ", IPdfCreator.SINISTRE_BODY_BOLD));
            phrase2.add(new Chunk(piece.getCommentaire(), IPdfCreator.SINISTRE_BODY_FONT));

            cellCommentaires.addElement(phrase2);

            tableDommagePiece.addCell(cellCommentaires);
        }
    }
}
