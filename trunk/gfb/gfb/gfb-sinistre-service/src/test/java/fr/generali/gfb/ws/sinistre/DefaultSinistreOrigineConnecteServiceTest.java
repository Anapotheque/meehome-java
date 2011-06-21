/**
 * 
 */
package fr.generali.gfb.ws.sinistre;

import org.apache.commons.lang.StringUtils;
import org.easymock.EasyMock;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;
import org.unitils.easymock.EasyMockUnitils;
import org.unitils.easymock.annotation.Mock;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;

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
@Transactional(TransactionMode.DISABLED)
public class DefaultSinistreOrigineConnecteServiceTest extends UnitilsJUnit4 {

    @TestedObject
    DefaultSinistreOrigineConnecteService sinistreService;

    @Mock
    @InjectIntoByType
    private IMailSinistreOrigineConnecteService mailService;

    @Mock
    @InjectIntoByType
    private IPdfSinistreOrigineConnecteService pdfService;

    @Mock
    @InjectIntoByType
    private ISinistreDao sinistreDao;

    /*
     * Dummy objects
     */
    private final byte[] returnedPdf = new byte[] {0, 1, 2 };

    private static final String NOM_PDF = "declaration_sinistre.pdf";

    private final Boolean isClientInternet = null;

    private final String mailBureau = null;

    private final InformationIntermediaire informationIntermediaire = new InformationIntermediaire();

    private String mailTrieste = StringUtils.EMPTY;

    /**
     * Cette méthode teste l'appel du service pour le sinistre Bris Glace.
     */
    @Test
    public void testTraitementSinistreBrisGlace() {

        DeclarationSinistre declarationSinistre =
                        SinistreObjectInitialization.initDeclarationSinistre(TypeSinistreEnum.MRH_BRIS_GLACE);

        declarationSinistre.setEtape(EtapeSinistreEnum.PERSISTENCE);
        EasyMock.expect(sinistreDao.save(declarationSinistre)).andReturn(1L);

        EasyMock.expect(
                        pdfService.createPdfBrisGlaceMRH(declarationSinistre, isClientInternet,
                                        informationIntermediaire,false)).andReturn(returnedPdf).once();

        declarationSinistre.setEtape(EtapeSinistreEnum.PDF);
        sinistreDao.saveOrUpdate(declarationSinistre);
        EasyMock.expectLastCall().once();

        mailService.sendMailClientInternaute(declarationSinistre, NOM_PDF, returnedPdf,false,null);
        EasyMock.expectLastCall().once();

        mailService.sendMailGestionnaireGenerali(NOM_PDF, returnedPdf, mailBureau,false);
        EasyMock.expectLastCall().once();

        declarationSinistre.setEtape(EtapeSinistreEnum.MAIL);
        sinistreDao.saveOrUpdate(declarationSinistre);
        EasyMock.expectLastCall().once();

        EasyMockUnitils.replay();

        sinistreService.traitementSinistreMrh(declarationSinistre, mailBureau, isClientInternet,
                        informationIntermediaire, mailTrieste,false);

    }

    /**
     * Cette méthode teste l'appel du service pour le sinistre Dommages
     * Electriques.
     */
    @Test
    public void testTraitementSinistreDommagesElectriques() {

        final DeclarationSinistre declarationSinistre =
                        SinistreObjectInitialization.initDeclarationSinistre(TypeSinistreEnum.MRH_DOMMAGES_ELECTRIQUES);

        declarationSinistre.setEtape(EtapeSinistreEnum.PERSISTENCE);
        EasyMock.expect(sinistreDao.save(declarationSinistre)).andReturn(1L);

        EasyMock.expect(
                        pdfService.createPdfDommageElectriqueMRH(declarationSinistre, isClientInternet,
                                        informationIntermediaire,false)).andReturn(returnedPdf).once();

        declarationSinistre.setEtape(EtapeSinistreEnum.PDF);
        sinistreDao.saveOrUpdate(declarationSinistre);
        EasyMock.expectLastCall().once();

        mailService.sendMailClientInternaute(declarationSinistre, NOM_PDF, returnedPdf,false,null);
        EasyMock.expectLastCall().once();

        mailService.sendMailGestionnaireGenerali(NOM_PDF, returnedPdf, mailBureau,false);
        EasyMock.expectLastCall().once();

        declarationSinistre.setEtape(EtapeSinistreEnum.MAIL);
        sinistreDao.saveOrUpdate(declarationSinistre);
        EasyMock.expectLastCall().once();

        EasyMockUnitils.replay();

        sinistreService.traitementSinistreMrh(declarationSinistre, mailBureau, isClientInternet,
                        informationIntermediaire, mailTrieste,false);
    }

    /**
     * Cette méthode teste l'appel du service pour le sinistre Dégats des Eaux.
     */
    @Test
    public void testTraitementSinistreDegatsEaux() {

        DeclarationSinistre declarationSinistre =
                        SinistreObjectInitialization.initDeclarationSinistre(TypeSinistreEnum.MRH_DEGATS_DES_EAUX);

        byte[] returnedPdf = new byte[] {0, 1, 2 };

        declarationSinistre.setEtape(EtapeSinistreEnum.PERSISTENCE);
        EasyMock.expect(sinistreDao.save(declarationSinistre)).andReturn(1L);

        EasyMock.expect(
                        pdfService.createPdfDegatsEauxMRH(declarationSinistre, isClientInternet,
                                        informationIntermediaire,false)).andReturn(returnedPdf).once();

        declarationSinistre.setEtape(EtapeSinistreEnum.PDF);
        sinistreDao.saveOrUpdate(declarationSinistre);
        EasyMock.expectLastCall().once();

        mailService.sendMailClientInternaute(declarationSinistre, NOM_PDF, returnedPdf,false,null);
        EasyMock.expectLastCall().once();

        mailService.sendMailGestionnaireGenerali(NOM_PDF, returnedPdf, mailBureau,false);
        EasyMock.expectLastCall().once();

        declarationSinistre.setEtape(EtapeSinistreEnum.MAIL);
        sinistreDao.saveOrUpdate(declarationSinistre);
        EasyMock.expectLastCall().once();

        EasyMockUnitils.replay();

        sinistreService.traitementSinistreMrh(declarationSinistre, mailBureau, isClientInternet,
                        informationIntermediaire, mailTrieste,false);
    }

    /**
     * Cette méthode teste l'appel du service pour le sinistre Tempetes et
     * Greles.
     */
    @Test
    public void testTraitementTempeteGrele() {

        DeclarationSinistre declarationSinistre =
                        SinistreObjectInitialization.initDeclarationSinistre(TypeSinistreEnum.MRH_TEMPETE_GRELE);

        byte[] returnedPdf = new byte[] {0, 1, 2 };

        declarationSinistre.setEtape(EtapeSinistreEnum.PERSISTENCE);
        EasyMock.expect(sinistreDao.save(declarationSinistre)).andReturn(1L);

        EasyMock.expect(
                        pdfService.createPdfTempetesGrelesMRH(declarationSinistre, isClientInternet,
                                        informationIntermediaire,false)).andReturn(returnedPdf).once();

        declarationSinistre.setEtape(EtapeSinistreEnum.PDF);
        sinistreDao.saveOrUpdate(declarationSinistre);
        EasyMock.expectLastCall().once();

        mailService.sendMailClientInternaute(declarationSinistre, NOM_PDF, returnedPdf,false,null);
        EasyMock.expectLastCall().once();

        mailService.sendMailGestionnaireGenerali(NOM_PDF, returnedPdf, mailBureau,false);
        EasyMock.expectLastCall().once();

        declarationSinistre.setEtape(EtapeSinistreEnum.MAIL);
        sinistreDao.saveOrUpdate(declarationSinistre);
        EasyMock.expectLastCall().once();

        EasyMockUnitils.replay();

        sinistreService.traitementSinistreMrh(declarationSinistre, mailBureau, isClientInternet,
                        informationIntermediaire, mailTrieste,false);
    }

    @Test
    public void testTraitementAccident() {

        DeclarationSinistre declarationSinistre =
                        SinistreObjectInitialization.initDeclarationSinistre(TypeSinistreEnum.AUTO_ACCIDENT);

        byte[] returnedPdf = new byte[] {0, 1, 2 };

        declarationSinistre.setEtape(EtapeSinistreEnum.PERSISTENCE);
        EasyMock.expect(sinistreDao.save(declarationSinistre)).andReturn(1L);

        EasyMock.expect(
                        pdfService.createPdfAccidentIncendieAUTO(declarationSinistre, isClientInternet,
                                        informationIntermediaire, "Accident",false)).andReturn(returnedPdf).once();

        declarationSinistre.setEtape(EtapeSinistreEnum.PDF);
        sinistreDao.saveOrUpdate(declarationSinistre);
        EasyMock.expectLastCall().once();

        mailService.sendMailClientInternaute(declarationSinistre, NOM_PDF, returnedPdf,false,null);
        EasyMock.expectLastCall().once();

        mailService.sendMailGestionnaireGenerali(NOM_PDF, returnedPdf, mailBureau,false);
        EasyMock.expectLastCall().once();

        declarationSinistre.setEtape(EtapeSinistreEnum.MAIL);
        sinistreDao.saveOrUpdate(declarationSinistre);
        EasyMock.expectLastCall().once();

        EasyMockUnitils.replay();

        sinistreService.traitementSinistreAuto(declarationSinistre, mailBureau, isClientInternet,
                        informationIntermediaire, mailTrieste,false);
    }

    @Test
    public void testTraitementIncendie() {

        DeclarationSinistre declarationSinistre =
                        SinistreObjectInitialization.initDeclarationSinistre(TypeSinistreEnum.AUTO_INCENDIE);

        byte[] returnedPdf = new byte[] {0, 1, 2 };

        declarationSinistre.setEtape(EtapeSinistreEnum.PERSISTENCE);
        EasyMock.expect(sinistreDao.save(declarationSinistre)).andReturn(1L);

        EasyMock.expect(
                        pdfService.createPdfAccidentIncendieAUTO(declarationSinistre, isClientInternet,
                                        informationIntermediaire, "Incendie",false)).andReturn(returnedPdf).once();

        declarationSinistre.setEtape(EtapeSinistreEnum.PDF);
        sinistreDao.saveOrUpdate(declarationSinistre);
        EasyMock.expectLastCall().once();

        mailService.sendMailClientInternaute(declarationSinistre, NOM_PDF, returnedPdf,false,null);
        EasyMock.expectLastCall().once();

        mailService.sendMailGestionnaireGenerali(NOM_PDF, returnedPdf, mailBureau,false);
        EasyMock.expectLastCall().once();

        declarationSinistre.setEtape(EtapeSinistreEnum.MAIL);
        sinistreDao.saveOrUpdate(declarationSinistre);
        EasyMock.expectLastCall().once();

        EasyMockUnitils.replay();

        sinistreService.traitementSinistreAuto(declarationSinistre, mailBureau, isClientInternet,
                        informationIntermediaire, mailTrieste,false);
    }

    @Test
    public void testEnvoiMailsTrieste() {

        DeclarationSinistre declarationSinistre =
                        SinistreObjectInitialization.initDeclarationSinistre(TypeSinistreEnum.AUTO_ACCIDENT);
        byte[] returnedPdf = new byte[] {0, 1, 2 };
        String mailBureauPrincipal = "toto@mail.fr";

        mailTrieste = "tata@mail.fr";

        mailService.sendMailClientInternaute(declarationSinistre, NOM_PDF, returnedPdf,false,null);
        EasyMock.expectLastCall().once();

        mailService.sendMailGestionnaireGenerali(NOM_PDF, returnedPdf, mailTrieste,false);
        EasyMock.expectLastCall().once();

        EasyMockUnitils.replay();

        sinistreService.envoiMails(declarationSinistre, returnedPdf, mailBureauPrincipal, mailTrieste,false,null);
        mailTrieste = StringUtils.EMPTY;;

    }

    @Test
    public void testEnvoiMailsNonTrieste() {

        DeclarationSinistre declarationSinistre =
                        SinistreObjectInitialization.initDeclarationSinistre(TypeSinistreEnum.AUTO_ACCIDENT);
        byte[] returnedPdf = new byte[] {0, 1, 2 };
        String mailBureauPrincipal = "toto@mail.fr";

        mailService.sendMailClientInternaute(declarationSinistre, NOM_PDF, returnedPdf,false,null);
        EasyMock.expectLastCall().once();

        mailService.sendMailGestionnaireGenerali(NOM_PDF, returnedPdf, mailBureau,false);
        EasyMock.expectLastCall().once();

        EasyMockUnitils.replay();

        sinistreService.envoiMails(declarationSinistre, returnedPdf, mailBureauPrincipal, mailTrieste,false,null);

    }
}
