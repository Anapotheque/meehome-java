package fr.generali.gfb.ws.sinistre.pdf.origineconnecte;

import java.io.ByteArrayOutputStream;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Phrase;

import fr.generali.espaceclient.proxy.generic.domain.InformationIntermediaire;
import fr.generali.gfb.ws.sinistre.persistence.domain.auto.vol.DommagesAutoVol;
import fr.generali.gfb.ws.sinistre.persistence.domain.auto.vol.SinistreAutoVol;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;
import fr.generali.gfb.ws.sinistre.util.pdf.IPdfCreator;
import fr.generali.socle.exceptions.TechnicalException;

/**
 * @author Mickael Morier
 */
public class AutoVolPdfSinistreService extends CommonPdfSinistreOrigineConnecteService {

    public AutoVolPdfSinistreService(IPdfCreator pdfCreator) {
        super(pdfCreator);
    }

    /**
     * Crée le document PDF d'un Vol Auto à partir de sa déclaration de sinistre
     * 
     * @param declarationSinistre
     * @param isClientInternet
     * @param infosPortefeuille
     * @return document PDF sous forme d'un tableau de byte
     */
    public byte[] createPdfVolAUTO(DeclarationSinistre declarationSinistre, Boolean isClientInternet,
                    InformationIntermediaire infosPortefeuille, boolean isCourtier) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document pdf = pdfCreator.createPdfSinistreDocument(baos, MARGIN_LEFT, MARGIN_RIGHT, MARGIN_TOP, MARGIN_BOTTOM);

        try {
            initializeAutoDeclaSinistre(pdf, declarationSinistre, isClientInternet, infosPortefeuille,
                            LIBELLE_SINISTRE_VOL,isCourtier);
            SinistreAutoVol sinistreVol = (SinistreAutoVol ) declarationSinistre.getSinistre();

            // depot de plainte
            if (sinistreVol != null && sinistreVol.getPlainte() != null) {
                if (Boolean.TRUE.equals(sinistreVol.getPlainte())) {
                    pdf.add(new Phrase("Vous nous déclarez avoir déposé plainte.", IPdfCreator.SINISTRE_BODY_FONT));
                } else if (Boolean.FALSE.equals(sinistreVol.getPlainte())) {
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
            if (sinistreVol != null) {
                DommagesAutoVol dommagesVol = sinistreVol.getDommage();
                initializeAutoDommages(pdf, dommagesVol);
                // Effets personnels
                if (dommagesVol.getEffetsPersonnels() != null && !dommagesVol.getEffetsPersonnels().equals("")) {
                    pdf.add(new Phrase("Effets personnels ou objets volés à l'intérieur du véhicule : ",
                                    IPdfCreator.SINISTRE_BODY_BOLD));
                }
                pdf.add(new Phrase(dommagesVol.getEffetsPersonnels(), IPdfCreator.SINISTRE_BODY_FONT));
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
