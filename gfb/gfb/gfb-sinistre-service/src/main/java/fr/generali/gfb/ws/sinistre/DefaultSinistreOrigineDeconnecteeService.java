package fr.generali.gfb.ws.sinistre;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import fr.generali.gfb.ws.sinistre.email.IMailSinistreOrigineDeconnecteService;
import fr.generali.gfb.ws.sinistre.pdf.originedeconnecte.IPdfSinistreOrigineDeconnecteService;
import fr.generali.gfb.ws.sinistre.persistence.dao.ISinistreDao;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;
import fr.generali.gfb.ws.sinistre.persistence.domain.constants.EtapeSinistreEnum;
import fr.generali.gfb.ws.sinistre.persistence.domain.constants.TypeSinistreEnum;

/**
 * @author ARUL Aguilane
 */
@Service
@Qualifier("defaultSinistreService")
public class DefaultSinistreOrigineDeconnecteeService implements ISinistreOrigineDeconnecteService {

    @Autowired
    private IMailSinistreOrigineDeconnecteService mailService;

    @Autowired
    private IPdfSinistreOrigineDeconnecteService pdfService;

    @Autowired
    private ISinistreDao sinistreDao;

    public void traitementSinistre(DeclarationSinistre declarationSinistre) throws DeclarationSinistreException {

        // sauvegarde de l'objet
        declarationSinistre.setEtape(EtapeSinistreEnum.PERSISTENCE);
        declarationSinistre.setId(sinistreDao.save(declarationSinistre));
        getLogger().info("Persistence de la declaration : Etape Persistence");

        // Génération du pdf sauvegarde de l'objet
        byte[] pdf = pdf(declarationSinistre);
        declarationSinistre.setEtape(EtapeSinistreEnum.PDF);
        sinistreDao.saveOrUpdate(declarationSinistre);
        getLogger().info("Persistence de la declaration : Etape Pdf");

        // Envoie de mail et sauvegarde de l'objet
        mailService.sendMailGestionnaireGenerali(declarationSinistre, "declaration_sinistre.pdf", pdf,false);
        mailService.sendMailClientInternaute(declarationSinistre, "declaration_sinistre.pdf", pdf,false,null);
        declarationSinistre.setEtape(EtapeSinistreEnum.MAIL);
        sinistreDao.saveOrUpdate(declarationSinistre);
        getLogger().info("Persistence de la declaration : Etape Mail");
    }

    private byte[] pdf(DeclarationSinistre declarationSinistre) throws AssertionError {
        TypeSinistreEnum typeSinistreEnum = declarationSinistre.getSinistre().getTypeSinistre();

        byte[] pdfAsByte = null;
        if (TypeSinistreEnum.MRH_BRIS_GLACE.equals(typeSinistreEnum)) {
            pdfAsByte = pdfService.createPdfBrisGlace(declarationSinistre);
            getLogger().info("Creation du pdf Bris Glace");
        } else if (TypeSinistreEnum.MRH_DOMMAGES_ELECTRIQUES.equals(typeSinistreEnum)) {
            pdfAsByte = pdfService.createPdfDommageElectrique(declarationSinistre);
            getLogger().info("Creation du pdf Dommage Electriques");
        } else if (TypeSinistreEnum.MRH_DEGATS_DES_EAUX.equals(typeSinistreEnum)) {
            pdfAsByte = pdfService.createPdfDegatsEaux(declarationSinistre);
            getLogger().info("Creation du pdf Dégats des Eaux");
        } else if (TypeSinistreEnum.MRH_TEMPETE_GRELE.equals(typeSinistreEnum)) {
            pdfAsByte = pdfService.createPdfTempetesGreles(declarationSinistre);
            getLogger().info("Creation du pdf Tempête/Grêle");
        } else if (TypeSinistreEnum.MRH_VOL_CAMBRIOLAGE.equals(typeSinistreEnum)) {
            pdfAsByte = pdfService.createPdfVolCambriolage(declarationSinistre);
            getLogger().info("Creation du pdf Vol Cambriolage");
        } else {
            throw new AssertionError("Type de sinistre inconnu : " + typeSinistreEnum);
        }
        return pdfAsByte;
    }

    /**
     * @return the mailService
     */
    public IMailSinistreOrigineDeconnecteService getMailService() {
        return mailService;
    }

    /**
     * @param mailService the mailService to set
     */
    public void setMailService(IMailSinistreOrigineDeconnecteService mailService) {
        this.mailService = mailService;
    }

    /**
     * @return the pdfService
     */
    public IPdfSinistreOrigineDeconnecteService getPdfService() {
        return pdfService;
    }

    /**
     * @param pdfService the pdfService to set
     */
    public void setPdfService(IPdfSinistreOrigineDeconnecteService pdfService) {
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
    public void setSinistreDao(ISinistreDao sinistreDao) {
        this.sinistreDao = sinistreDao;
    }

    private Logger getLogger() {
        return LoggerFactory.getLogger(this.getClass());
    }
}
