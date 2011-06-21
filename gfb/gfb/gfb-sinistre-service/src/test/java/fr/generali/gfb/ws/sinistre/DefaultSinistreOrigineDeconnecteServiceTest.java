/**
 * 
 */
package fr.generali.gfb.ws.sinistre;

import org.easymock.EasyMock;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;
import org.unitils.easymock.EasyMockUnitils;
import org.unitils.easymock.annotation.Mock;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;

import fr.generali.gfb.ws.sinistre.email.IMailSinistreOrigineDeconnecteService;
import fr.generali.gfb.ws.sinistre.pdf.originedeconnecte.IPdfSinistreOrigineDeconnecteService;
import fr.generali.gfb.ws.sinistre.persistence.dao.ISinistreDao;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;
import fr.generali.gfb.ws.sinistre.persistence.domain.constants.EtapeSinistreEnum;
import fr.generali.gfb.ws.sinistre.persistence.domain.constants.TypeSinistreEnum;

/**
 * @author ARUL Aguilane
 */
@Transactional(TransactionMode.DISABLED)
public class DefaultSinistreOrigineDeconnecteServiceTest extends UnitilsJUnit4 {

    @TestedObject
    DefaultSinistreOrigineDeconnecteeService sinistreService;

    @Mock
    @InjectIntoByType
    private IMailSinistreOrigineDeconnecteService mailService;

    @Mock
    @InjectIntoByType
    private IPdfSinistreOrigineDeconnecteService pdfService;

    @Mock
    @InjectIntoByType
    private ISinistreDao sinistreDao;

    /**
     * Cette méthode teste l'appel du service pour le sinistre Bris Glace.
     * 
     * @throws DeclarationSinistreException
     */
    @Test
    public void testTraitementSinistreBrisGlace() throws DeclarationSinistreException {

        DeclarationSinistre declarationSinistre =
                        SinistreObjectInitialization.initDeclarationSinistre(TypeSinistreEnum.MRH_BRIS_GLACE);

        byte[] returnedPdf = new byte[] {0, 1, 2 };

        declarationSinistre.setEtape(EtapeSinistreEnum.PERSISTENCE);
        EasyMock.expect(sinistreDao.save(declarationSinistre)).andReturn(1L);

        EasyMock.expect(pdfService.createPdfBrisGlace(declarationSinistre)).andReturn(returnedPdf).once();

        declarationSinistre.setEtape(EtapeSinistreEnum.PDF);
        sinistreDao.saveOrUpdate(declarationSinistre);
        EasyMock.expectLastCall().once();

        mailService.sendMailClientInternaute(declarationSinistre, "declaration_sinistre.pdf", returnedPdf,false,null);
        EasyMock.expectLastCall().once();

        mailService.sendMailGestionnaireGenerali(declarationSinistre, "declaration_sinistre.pdf", returnedPdf,false);
        EasyMock.expectLastCall().once();

        declarationSinistre.setEtape(EtapeSinistreEnum.MAIL);
        sinistreDao.saveOrUpdate(declarationSinistre);
        EasyMock.expectLastCall().once();

        EasyMockUnitils.replay();

        sinistreService.traitementSinistre(declarationSinistre);

    }

    /**
     * Cette méthode teste l'appel du service pour le sinistre Dommages
     * Electriques.
     * 
     * @throws DeclarationSinistreException
     */
    @Test
    public void testTraitementSinistreDommagesElectriques() throws DeclarationSinistreException {

        DeclarationSinistre declarationSinistre =
                        SinistreObjectInitialization.initDeclarationSinistre(TypeSinistreEnum.MRH_DOMMAGES_ELECTRIQUES);

        byte[] returnedPdf = new byte[] {0, 1, 2 };

        declarationSinistre.setEtape(EtapeSinistreEnum.PERSISTENCE);
        EasyMock.expect(sinistreDao.save(declarationSinistre)).andReturn(1L);

        EasyMock.expect(pdfService.createPdfDommageElectrique(declarationSinistre)).andReturn(returnedPdf).once();

        declarationSinistre.setEtape(EtapeSinistreEnum.PDF);
        sinistreDao.saveOrUpdate(declarationSinistre);
        EasyMock.expectLastCall().once();

        mailService.sendMailClientInternaute(declarationSinistre, "declaration_sinistre.pdf", returnedPdf,false,null);
        EasyMock.expectLastCall().once();

        mailService.sendMailGestionnaireGenerali(declarationSinistre, "declaration_sinistre.pdf", returnedPdf,false);
        EasyMock.expectLastCall().once();

        declarationSinistre.setEtape(EtapeSinistreEnum.MAIL);
        sinistreDao.saveOrUpdate(declarationSinistre);
        EasyMock.expectLastCall().once();

        EasyMockUnitils.replay();

        sinistreService.traitementSinistre(declarationSinistre);
    }

    /**
     * Cette méthode teste l'appel du service pour le sinistre Dégats des Eaux.
     * 
     * @throws DeclarationSinistreException
     */
    @Test
    public void testTraitementSinistreDegatsEaux() throws DeclarationSinistreException {

        DeclarationSinistre declarationSinistre =
                        SinistreObjectInitialization.initDeclarationSinistre(TypeSinistreEnum.MRH_DEGATS_DES_EAUX);

        byte[] returnedPdf = new byte[] {0, 1, 2 };

        declarationSinistre.setEtape(EtapeSinistreEnum.PERSISTENCE);
        EasyMock.expect(sinistreDao.save(declarationSinistre)).andReturn(1L);

        EasyMock.expect(pdfService.createPdfDegatsEaux(declarationSinistre)).andReturn(returnedPdf).once();

        declarationSinistre.setEtape(EtapeSinistreEnum.PDF);
        sinistreDao.saveOrUpdate(declarationSinistre);
        EasyMock.expectLastCall().once();

        mailService.sendMailClientInternaute(declarationSinistre, "declaration_sinistre.pdf", returnedPdf,false,null);
        EasyMock.expectLastCall().once();

        mailService.sendMailGestionnaireGenerali(declarationSinistre, "declaration_sinistre.pdf", returnedPdf,false);
        EasyMock.expectLastCall().once();

        declarationSinistre.setEtape(EtapeSinistreEnum.MAIL);
        sinistreDao.saveOrUpdate(declarationSinistre);
        EasyMock.expectLastCall().once();

        EasyMockUnitils.replay();

        sinistreService.traitementSinistre(declarationSinistre);
    }

    /**
     * Cette méthode teste l'appel du service pour le sinistre Tempetes et
     * Greles.
     * 
     * @throws DeclarationSinistreException
     */
    @Test
    public void testTraitementTempeteGrele() throws DeclarationSinistreException {

        DeclarationSinistre declarationSinistre =
                        SinistreObjectInitialization.initDeclarationSinistre(TypeSinistreEnum.MRH_TEMPETE_GRELE);

        byte[] returnedPdf = new byte[] {0, 1, 2 };

        declarationSinistre.setEtape(EtapeSinistreEnum.PERSISTENCE);
        EasyMock.expect(sinistreDao.save(declarationSinistre)).andReturn(1L);

        EasyMock.expect(pdfService.createPdfTempetesGreles(declarationSinistre)).andReturn(returnedPdf).once();

        declarationSinistre.setEtape(EtapeSinistreEnum.PDF);
        sinistreDao.saveOrUpdate(declarationSinistre);
        EasyMock.expectLastCall().once();

        mailService.sendMailClientInternaute(declarationSinistre, "declaration_sinistre.pdf", returnedPdf,false,null);
        EasyMock.expectLastCall().once();

        mailService.sendMailGestionnaireGenerali(declarationSinistre, "declaration_sinistre.pdf", returnedPdf,false);
        EasyMock.expectLastCall().once();

        declarationSinistre.setEtape(EtapeSinistreEnum.MAIL);
        sinistreDao.saveOrUpdate(declarationSinistre);
        EasyMock.expectLastCall().once();

        EasyMockUnitils.replay();

        sinistreService.traitementSinistre(declarationSinistre);
    }

}
