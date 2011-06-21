package fr.generali.gfb.ws.sinistre.pdf.origineconnecte;

import org.springframework.stereotype.Service;

import fr.generali.espaceclient.proxy.generic.domain.InformationIntermediaire;
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
public class DefaultPdfSinistreOrigineConnecteService implements IPdfSinistreOrigineConnecteService {

    /**
     * {@inheritDoc}
     */
    public byte[] createPdfBrisGlaceMRH(DeclarationSinistre declarationSinistre, Boolean isClientInternet,
                    InformationIntermediaire infosPortefeuille, boolean isCourtier) {
        IPdfCreator pdfCreator = new TextPdfCreator();
        MrhBrisGlacePdfSinistreService mrhBrisGlacePdfSinistreService =
                        new MrhBrisGlacePdfSinistreService(pdfCreator, TypeOrigine.CONNECTE);
        return mrhBrisGlacePdfSinistreService.createPdfBrisGlaceMRH(declarationSinistre, isClientInternet,
                        infosPortefeuille,isCourtier);
    }

    /**
     * {@inheritDoc}
     */
    public byte[] createPdfDegatsEauxMRH(DeclarationSinistre declarationSinistre, Boolean isClientInternet,
                    InformationIntermediaire infosPortefeuille, boolean isCourtier) {
        IPdfCreator pdfCreator = new TextPdfCreator();
        MrhDegatsEauxPdfSinistreService mrhDegatsEauxPdfSinistreService =
                        new MrhDegatsEauxPdfSinistreService(pdfCreator, TypeOrigine.CONNECTE);
        return mrhDegatsEauxPdfSinistreService.createPdfDegatsEauxMRH(declarationSinistre, isClientInternet,
                        infosPortefeuille,isCourtier);
    }

    /**
     * {@inheritDoc}
     */
    public byte[] createPdfDommageElectriqueMRH(DeclarationSinistre declarationSinistre, Boolean isClientInternet,
                    InformationIntermediaire infosPortefeuille, boolean isCourtier) {
        IPdfCreator pdfCreator = new TextPdfCreator();
        MrhDommageElectriquePdfSinistreService mrhDommageElectriquePdfSinistreService =
                        new MrhDommageElectriquePdfSinistreService(pdfCreator, TypeOrigine.CONNECTE);
        return mrhDommageElectriquePdfSinistreService.createPdfDommageElectriqueMRH(declarationSinistre,
                        isClientInternet, infosPortefeuille,isCourtier);

    }

    /**
     * {@inheritDoc}
     */
    public byte[] createPdfTempetesGrelesMRH(DeclarationSinistre declarationSinistre, Boolean isClientInternet,
                    InformationIntermediaire infosPortefeuille, boolean isCourtier) {
        IPdfCreator pdfCreator = new TextPdfCreator();
        MrhTempeteGrelePdfSinistreService mrhTempeteGrelePdfSinistreService =
                        new MrhTempeteGrelePdfSinistreService(pdfCreator, TypeOrigine.CONNECTE);
        return mrhTempeteGrelePdfSinistreService.createPdfTempetesGrelesMRH(declarationSinistre, isClientInternet,
                        infosPortefeuille,isCourtier);
    }

    /**
     * {@inheritDoc}
     */
    public byte[] createPdfVolCambriolageMRH(DeclarationSinistre declarationSinistre, Boolean isClientInternet,
                    InformationIntermediaire infosPortefeuille, boolean isCourtier) {
        IPdfCreator pdfCreator = new TextPdfCreator();
        MrhVolCambriolagePdfSinistreService mrhVolCambriolagePdfSinistreService =
                        new MrhVolCambriolagePdfSinistreService(pdfCreator, TypeOrigine.CONNECTE);
        return mrhVolCambriolagePdfSinistreService.createPdfVolCambriolageMRH(declarationSinistre, isClientInternet,
                        infosPortefeuille,isCourtier);
    }

    /**
     * {@inheritDoc}
     */
    public byte[] createPdfAccidentIncendieAUTO(DeclarationSinistre declarationSinistre, Boolean isClientInternet,
                    InformationIntermediaire infosPortefeuille, String libelleSinistre, boolean isCourtier) {
        IPdfCreator pdfCreator = new TextPdfCreator();
        AutoAccidentIncendiePdfSinistreService autoAccidentIncendiePdfSinistreService =
                        new AutoAccidentIncendiePdfSinistreService(pdfCreator);
        return autoAccidentIncendiePdfSinistreService.createPdfAccidentIncendieAUTO(declarationSinistre,
                        isClientInternet, infosPortefeuille, libelleSinistre,isCourtier);
    }

    /**
     * {@inheritDoc}
     */
    public byte[] createPdfVandalismeAUTO(DeclarationSinistre declarationSinistre, Boolean isClientInternet,
                    InformationIntermediaire infosPortefeuille, boolean isCourtier) {
        IPdfCreator pdfCreator = new TextPdfCreator();
        AutoVandalismePdfSinistreService autoVandalismePdfSinistreService =
                        new AutoVandalismePdfSinistreService(pdfCreator);
        return autoVandalismePdfSinistreService.createPdfVandalismeAUTO(declarationSinistre, isClientInternet,
                        infosPortefeuille,isCourtier);
    }

    /**
     * {@inheritDoc}
     */
    public byte[] createPdfVolAUTO(DeclarationSinistre declarationSinistre, Boolean isClientInternet,
                    InformationIntermediaire infosPortefeuille, boolean isCourtier) {
        IPdfCreator pdfCreator = new TextPdfCreator();
        AutoVolPdfSinistreService autoVolPdfSinistreService = new AutoVolPdfSinistreService(pdfCreator);
        return autoVolPdfSinistreService.createPdfVolAUTO(declarationSinistre, isClientInternet, infosPortefeuille,isCourtier);
    }

}
