package fr.generali.gfb.ws.sinistre.pdf.origineconnecte;

import java.io.ByteArrayOutputStream;

import org.apache.commons.lang.StringUtils;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Phrase;

import fr.generali.espaceclient.proxy.generic.domain.InformationIntermediaire;
import fr.generali.gfb.ws.sinistre.persistence.domain.auto.accident.DommagesAutoIncendieAccident;
import fr.generali.gfb.ws.sinistre.persistence.domain.auto.accident.SinistreAutoAccidentIncendie;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;
import fr.generali.gfb.ws.sinistre.util.pdf.IPdfCreator;
import fr.generali.socle.exceptions.TechnicalException;

/**
 * @author Mickael Morier
 */
public class AutoAccidentIncendiePdfSinistreService extends CommonPdfSinistreOrigineConnecteService {

    public AutoAccidentIncendiePdfSinistreService(IPdfCreator pdfCreator) {
        super(pdfCreator);
    }

    /**
     * Cr�e le document PDF d'un Accident / Incendie Auto � partir de sa
     * d�claration de sinistre
     * 
     * @param declarationSinistre
     * @param isClientInternet
     * @param infosPortefeuille
     * @param libelleSinistre
     * @return document PDF sous forme d'un tableau de byte
     */
    public byte[] createPdfAccidentIncendieAUTO(DeclarationSinistre declarationSinistre, Boolean isClientInternet,
                    InformationIntermediaire infosPortefeuille, String libelleSinistre, boolean isCourtier) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document pdf = pdfCreator.createPdfSinistreDocument(baos, MARGIN_LEFT, MARGIN_RIGHT, MARGIN_TOP, MARGIN_BOTTOM);

        try {
            initializeAutoDeclaSinistre(pdf, declarationSinistre, isClientInternet, infosPortefeuille, libelleSinistre, isCourtier);
            SinistreAutoAccidentIncendie sinistreAccidentIncendie =
                            (SinistreAutoAccidentIncendie ) declarationSinistre.getSinistre();

            // Suite de etape 2 : sinistre : Lieu
            if (sinistreAccidentIncendie.getLieu() != null && !sinistreAccidentIncendie.getLieu().equals("")) {
                pdf.add(new Phrase("Lieu du sinistre : ", IPdfCreator.SINISTRE_BODY_BOLD));
                pdf.add(new Phrase(sinistreAccidentIncendie.getLieu(), IPdfCreator.SINISTRE_BODY_FONT));
                pdf.add(Chunk.NEWLINE);
                pdf.add(Chunk.NEWLINE);
            }
            // Etape 3 : les domages
            if (sinistreAccidentIncendie != null) {
                DommagesAutoIncendieAccident dommagesAccident = sinistreAccidentIncendie.getDommage();
                initializeAutoDommages(pdf, dommagesAccident);
                // Autres dommages mat�riels
                if (dommagesAccident != null && dommagesAccident.getAutresDommagesMateriels()) {
                    pdf.add(new Phrase("L'accident a provoqu� d'autres dommages mat�riels",
                                    IPdfCreator.SINISTRE_BODY_FONT));
                    pdf.add(Chunk.NEWLINE);
                    pdf.add(Chunk.NEWLINE);
                    // description dommages mat�riels
                    if (StringUtils.isNotEmpty(dommagesAccident.getDescriptionDommagesMateriels())) {
                        pdf.add(new Phrase("Autres dommages mat�riels : ", IPdfCreator.SINISTRE_BODY_BOLD));
                        pdf.add(new Phrase(dommagesAccident.getDescriptionDommagesMateriels(),
                                        IPdfCreator.SINISTRE_BODY_FONT));
                        pdf.add(Chunk.NEWLINE);
                        pdf.add(Chunk.NEWLINE);
                    }
                    // Autres personnes concern�es
                    if (StringUtils.isNotEmpty(dommagesAccident.getAutresPersonnes())) {
                        pdf.add(new Phrase("Autres personnes concern�es : ", IPdfCreator.SINISTRE_BODY_BOLD));
                        pdf.add(new Phrase(dommagesAccident.getAutresPersonnes(), IPdfCreator.SINISTRE_BODY_FONT));
                        pdf.add(Chunk.NEWLINE);
                        pdf.add(Chunk.NEWLINE);
                    }
                } else {
                    pdf.add(new Phrase("L'accident n'a pas provoqu� d'autres dommages mat�riels",
                                    IPdfCreator.SINISTRE_BODY_FONT));
                    pdf.add(Chunk.NEWLINE);
                    pdf.add(Chunk.NEWLINE);
                }
                // Dommages corporels
                if (dommagesAccident != null && dommagesAccident.getDommagesCorporel()) {
                    pdf.add(new Phrase("L'accident a provoqu� des dommages corporels", IPdfCreator.SINISTRE_BODY_FONT));
                } else {
                    pdf.add(new Phrase("L'accident n'a pas provoqu� de dommages corporels",
                                    IPdfCreator.SINISTRE_BODY_FONT));
                }
                pdf.add(Chunk.NEWLINE);
                pdf.add(Chunk.NEWLINE);
            }

            pdf.add(initializeEndingText("67".equals(declarationSinistre.getCodeCompagnie())));

        } catch (DocumentException e) {
            throw new TechnicalException("Une exception est survenue lors de la g�n�ration du PDF", e);
        }

        pdf.close();
        return baos.toByteArray();
    }
}
