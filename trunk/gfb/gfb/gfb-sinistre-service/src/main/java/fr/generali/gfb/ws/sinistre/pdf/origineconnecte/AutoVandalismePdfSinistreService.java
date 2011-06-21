package fr.generali.gfb.ws.sinistre.pdf.origineconnecte;

import java.io.ByteArrayOutputStream;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Phrase;

import fr.generali.espaceclient.proxy.generic.domain.InformationIntermediaire;
import fr.generali.gfb.ws.sinistre.persistence.domain.auto.DommagesAuto;
import fr.generali.gfb.ws.sinistre.persistence.domain.auto.vandalisme.SinistreAutoVandalisme;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;
import fr.generali.gfb.ws.sinistre.util.pdf.IPdfCreator;
import fr.generali.socle.exceptions.TechnicalException;

/**
 * @author Mickael Morier
 */
public class AutoVandalismePdfSinistreService extends CommonPdfSinistreOrigineConnecteService {

    public AutoVandalismePdfSinistreService(IPdfCreator pdfCreator) {
        super(pdfCreator);
    }

    /**
     * Crée le document PDF d'un Accident / Incendie Auto à partir de sa
     * déclaration de sinistre
     * 
     * @param declarationSinistre
     * @param isClientInternet
     * @param infosPortefeuille
     * @return document PDF sous forme d'un tableau de byte
     */
    public byte[] createPdfVandalismeAUTO(DeclarationSinistre declarationSinistre, Boolean isClientInternet,
                    InformationIntermediaire infosPortefeuille, boolean isCourtier) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document pdf = pdfCreator.createPdfSinistreDocument(baos, MARGIN_LEFT, MARGIN_RIGHT, MARGIN_TOP, MARGIN_BOTTOM);

        try {
            initializeAutoDeclaSinistre(pdf, declarationSinistre, isClientInternet, infosPortefeuille,
                            LIBELLE_SINISTRE_VANDALISME,isCourtier);
            SinistreAutoVandalisme sinistreVandalisme = (SinistreAutoVandalisme ) declarationSinistre.getSinistre();

            // depot de plainte
            if (sinistreVandalisme != null && sinistreVandalisme.getPlainte() != null) {
                if (Boolean.TRUE.equals(sinistreVandalisme.getPlainte())) {
                    pdf.add(new Phrase("Vous nous déclarez avoir déposé plainte.", IPdfCreator.SINISTRE_BODY_FONT));
                } else if (Boolean.FALSE.equals(sinistreVandalisme.getPlainte())) {
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
            // Etape 3 : les domages
            if (sinistreVandalisme != null) {
                DommagesAuto dommages = sinistreVandalisme.getDommage();
                initializeAutoDommages(pdf, dommages);
            }
            pdf.add(initializeEndingText("67".equals(declarationSinistre.getCodeCompagnie())));

        } catch (DocumentException e) {
            throw new TechnicalException("Une exception est survenue lors de la génération du PDF", e);
        }

        pdf.close();
        return baos.toByteArray();
    }
}
