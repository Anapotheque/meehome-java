package fr.generali.gfb.ws.sinistre;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.generali.espaceclient.proxy.generic.domain.InformationIntermediaire;
import fr.generali.gfb.ws.sinistre.email.IMailSinistreOrigineConnecteService;
import fr.generali.gfb.ws.sinistre.pdf.origineconnecte.IPdfSinistreOrigineConnecteService;
import fr.generali.gfb.ws.sinistre.persistence.dao.ISinistreDao;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;
import fr.generali.gfb.ws.sinistre.persistence.domain.constants.EtapeSinistreEnum;
import fr.generali.gfb.ws.sinistre.persistence.domain.constants.TypeSinistreEnum;

/**
 * @author ARUL Aguilane
 */
@Service
public class DefaultSinistreOrigineConnecteService implements ISinistreOrigineConnecteService {

    @Autowired
    private IMailSinistreOrigineConnecteService mailService;

    @Autowired
    private IPdfSinistreOrigineConnecteService pdfService;

    @Autowired
    private ISinistreDao sinistreDao;

    private static final String NOM_PDF = "declaration_sinistre.pdf";

    /*
     * (non-Javadoc)
     * @see
     * fr.generali.melovie.sinistre.services.ISinistreService#traitementSinistre
     * (fr.generali.melovie.sinistre.domain.common.DeclarationSinistre)
     */
    public void traitementSinistreMrh(
                    final DeclarationSinistre declarationSinistre,
                    final String mailBureauPrincipal, // NOPMD
                    // by
                    // e004035
                    // on
                    // 18/06/09
                    // 15:39
                    final Boolean isClientInternet, final InformationIntermediaire infosPortefeuille,
                    final String mailTrieste,
                    boolean isCourtier) {

        // sauvegarde de l'objet
        declarationSinistre.setEtape(EtapeSinistreEnum.PERSISTENCE);
        declarationSinistre.setId(sinistreDao.save(declarationSinistre));
        getLogger().info("Persistence de la declaration : Etape Persistence");

        final TypeSinistreEnum typeSinistreEnum = declarationSinistre.getSinistre().getTypeSinistre();
        byte[] pdfAsByte = null;
        if (TypeSinistreEnum.MRH_BRIS_GLACE.equals(typeSinistreEnum)) {
            pdfAsByte = pdfService.createPdfBrisGlaceMRH(declarationSinistre, isClientInternet, infosPortefeuille, isCourtier);
            getLogger().info("Creation du pdf Bris Glace");
        } else if (TypeSinistreEnum.MRH_DOMMAGES_ELECTRIQUES.equals(typeSinistreEnum)) {
            pdfAsByte =
                            pdfService.createPdfDommageElectriqueMRH(declarationSinistre, isClientInternet,
                                            infosPortefeuille, isCourtier);
            getLogger().info("Creation du pdf Dommage Electriques");
        } else if (TypeSinistreEnum.MRH_DEGATS_DES_EAUX.equals(typeSinistreEnum)) {
            pdfAsByte = pdfService.createPdfDegatsEauxMRH(declarationSinistre, isClientInternet, infosPortefeuille, isCourtier);
            getLogger().info("Creation du pdf Dégats des Eaux");
        } else if (TypeSinistreEnum.MRH_TEMPETE_GRELE.equals(typeSinistreEnum)) {
            pdfAsByte = pdfService.createPdfTempetesGrelesMRH(declarationSinistre, isClientInternet, infosPortefeuille, isCourtier);
            getLogger().info("Creation du pdf Tempête/Grêle");
        } else if (TypeSinistreEnum.MRH_VOL_CAMBRIOLAGE.equals(typeSinistreEnum)) {
            pdfAsByte = pdfService.createPdfVolCambriolageMRH(declarationSinistre, isClientInternet, infosPortefeuille, isCourtier);
            getLogger().info("Creation du pdf Vol Cambriolage");
        }

        // sauvegarde de l'objet
        declarationSinistre.setEtape(EtapeSinistreEnum.PDF);
        sinistreDao.saveOrUpdate(declarationSinistre);
        getLogger().info("Persistence de la declaration : Etape Pdf");

        envoiMails(declarationSinistre, pdfAsByte, mailBureauPrincipal, mailTrieste,isCourtier,infosPortefeuille);

        // sauvegarde de l'objet
        declarationSinistre.setEtape(EtapeSinistreEnum.MAIL);
        sinistreDao.saveOrUpdate(declarationSinistre);
        getLogger().info("Persistence de la declaration : Etape Mail");

    }

    public void traitementSinistreAuto(final DeclarationSinistre declarationSinistre, final String mailBureauPrincipal,
                    final Boolean isClientInternet, final InformationIntermediaire infosPortefeuille,
                    final String mailTrieste, boolean isCourtier) {

        // sauvegarde de l'objet
        declarationSinistre.setEtape(EtapeSinistreEnum.PERSISTENCE);
        declarationSinistre.setId(sinistreDao.save(declarationSinistre));
        getLogger().info("Persistence de la declaration : Etape Persistence");

        final TypeSinistreEnum typeSinistreEnum = declarationSinistre.getSinistre().getTypeSinistre();
        byte[] pdfAsByte = null;
        if (TypeSinistreEnum.AUTO_ACCIDENT.equals(typeSinistreEnum)) {
            pdfAsByte =
                            pdfService.createPdfAccidentIncendieAUTO(declarationSinistre, isClientInternet,
                                            infosPortefeuille,
                                            IPdfSinistreOrigineConnecteService.LIBELLE_SINISTRE_ACCIDENT, isCourtier);
            getLogger().info("Creation du pdf Accident AUTO");
        } else if (TypeSinistreEnum.AUTO_INCENDIE.equals(typeSinistreEnum)) {
            pdfAsByte =
                            pdfService.createPdfAccidentIncendieAUTO(declarationSinistre, isClientInternet,
                                            infosPortefeuille,
                                            IPdfSinistreOrigineConnecteService.LIBELLE_SINISTRE_INCENDIE, isCourtier);
            getLogger().info("Creation du pdf Incendie AUTO");
        } else if (TypeSinistreEnum.AUTO_VOL.equals(typeSinistreEnum)) {
            pdfAsByte = pdfService.createPdfVolAUTO(declarationSinistre, isClientInternet, infosPortefeuille, isCourtier);
            getLogger().info("Creation du pdf Vol AUTO");
        } else if (TypeSinistreEnum.AUTO_VANDALISME.equals(typeSinistreEnum)) {
            pdfAsByte = pdfService.createPdfVandalismeAUTO(declarationSinistre, isClientInternet, infosPortefeuille, isCourtier);
            getLogger().info("Creation du pdf Vandalisme AUTO");
        }

        // sauvegarde de l'objet
        declarationSinistre.setEtape(EtapeSinistreEnum.PDF);
        sinistreDao.saveOrUpdate(declarationSinistre);
        getLogger().info("Persistence de la declaration : Etape Pdf");

        envoiMails(declarationSinistre, pdfAsByte, mailBureauPrincipal, mailTrieste,isCourtier,infosPortefeuille);

        // sauvegarde de l'objet
        declarationSinistre.setEtape(EtapeSinistreEnum.MAIL);
        sinistreDao.saveOrUpdate(declarationSinistre);
        getLogger().info("Persistence de la declaration : Etape Mail");

    }

    /**
     * @return the mailService
     */
    public IMailSinistreOrigineConnecteService getMailService() {
        return mailService;
    }

    /**
     * @param mailService the mailService to set
     */
    public void setMailService(final IMailSinistreOrigineConnecteService mailService) { // NOPMD
        // by
        // e004035
        // on
        // 18/06/09
        // 15:39
        this.mailService = mailService;
    }

    /**
     * @return the pdfService
     */
    public IPdfSinistreOrigineConnecteService getPdfService() {
        return pdfService;
    }

    /**
     * @param pdfService the pdfService to set
     */
    public void setPdfService(final IPdfSinistreOrigineConnecteService pdfService) {
        this.pdfService = pdfService;
    }

    /**
     * @return the sinistreDao
     */
    public ISinistreDao getSinistreDao() {
        return sinistreDao;
    }

    /**
     * @param sinistreDao the sinistreDao to set
     */
    public void setSinistreDao(final ISinistreDao sinistreDao) {
        this.sinistreDao = sinistreDao;
    }

    private Logger getLogger() {
        return LoggerFactory.getLogger(this.getClass());
    }

    protected void envoiMails(DeclarationSinistre declarationSinistre, byte[] pdfAsByte, String mailBureauPrincipal,
                    String mailTrieste, boolean isCourtier, InformationIntermediaire infosPortefeuille) {

        mailService.sendMailClientInternaute(declarationSinistre, NOM_PDF, pdfAsByte,isCourtier,infosPortefeuille);
        if (!StringUtils.isEmpty(mailTrieste)) {
            mailBureauPrincipal = mailTrieste;
        }
        mailService.sendMailGestionnaireGenerali(NOM_PDF, pdfAsByte, mailBureauPrincipal,isCourtier);
    }
}
