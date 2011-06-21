/**
 * 
 */
package fr.generali.gfb.ws.sinistre.pdf.originedeconnecte;

import org.springframework.stereotype.Service;

import fr.generali.gfb.ws.sinistre.dto.OrigineDeclaration.TypeOrigine;
import fr.generali.gfb.ws.sinistre.pdf.MrhBrisGlacePdfSinistreService;
import fr.generali.gfb.ws.sinistre.pdf.MrhDegatsEauxPdfSinistreService;
import fr.generali.gfb.ws.sinistre.pdf.MrhDommageElectriquePdfSinistreService;
import fr.generali.gfb.ws.sinistre.pdf.MrhTempeteGrelePdfSinistreService;
import fr.generali.gfb.ws.sinistre.pdf.MrhVolCambriolagePdfSinistreService;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;
import fr.generali.gfb.ws.sinistre.util.pdf.IPdfCreator;
import fr.generali.gfb.ws.sinistre.util.pdf.TextPdfCreator;

/**
 * @author Aguilane ARUL
 */
@Service
public class DefaultPdfSinistreOrigineDeconnecteService implements IPdfSinistreOrigineDeconnecteService {

    public byte[] createPdfBrisGlace(DeclarationSinistre declarationSinistre) {
        IPdfCreator pdfCreator = new TextPdfCreator();
        MrhBrisGlacePdfSinistreService mrhBrisGlacePdfSinistreService =
                        new MrhBrisGlacePdfSinistreService(pdfCreator, TypeOrigine.DECONNECTE);
        return mrhBrisGlacePdfSinistreService.createPdfBrisGlaceMRH(declarationSinistre, false, null,false);

    }

    public byte[] createPdfDegatsEaux(DeclarationSinistre declarationSinistre) {
        IPdfCreator pdfCreator = new TextPdfCreator();
        MrhDegatsEauxPdfSinistreService mrhBrisGlacePdfSinistreService =
                        new MrhDegatsEauxPdfSinistreService(pdfCreator, TypeOrigine.DECONNECTE);
        return mrhBrisGlacePdfSinistreService.createPdfDegatsEauxMRH(declarationSinistre, false, null,false);
    }

    public byte[] createPdfDommageElectrique(DeclarationSinistre declarationSinistre) {
        IPdfCreator pdfCreator = new TextPdfCreator();
        MrhDommageElectriquePdfSinistreService mrhDommageElectriquePdfSinistreService =
                        new MrhDommageElectriquePdfSinistreService(pdfCreator, TypeOrigine.DECONNECTE);
        return mrhDommageElectriquePdfSinistreService.createPdfDommageElectriqueMRH(declarationSinistre, false, null,false);
    }

    public byte[] createPdfTempetesGreles(DeclarationSinistre declarationSinistre) {
        IPdfCreator pdfCreator = new TextPdfCreator();
        MrhTempeteGrelePdfSinistreService mrhTempeteGrelePdfSinistreService =
                        new MrhTempeteGrelePdfSinistreService(pdfCreator, TypeOrigine.DECONNECTE);
        return mrhTempeteGrelePdfSinistreService.createPdfTempetesGrelesMRH(declarationSinistre, false, null,false);

    }

    public byte[] createPdfVolCambriolage(DeclarationSinistre declarationSinistre) {
        IPdfCreator pdfCreator = new TextPdfCreator();
        MrhVolCambriolagePdfSinistreService mrhVolCambriolagePdfSinistreService =
                        new MrhVolCambriolagePdfSinistreService(pdfCreator, TypeOrigine.DECONNECTE);
        return mrhVolCambriolagePdfSinistreService.createPdfVolCambriolageMRH(declarationSinistre, false, null,false);
    }

}
