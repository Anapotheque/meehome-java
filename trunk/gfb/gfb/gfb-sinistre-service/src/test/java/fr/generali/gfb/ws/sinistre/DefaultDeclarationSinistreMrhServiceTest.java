package fr.generali.gfb.ws.sinistre;

import java.util.Properties;

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

import fr.generali.espaceclient.eai.nonpivot.LogAdminData;
import fr.generali.espaceclient.eai.service.IProducerService;
import fr.generali.espaceclient.proxy.generic.domain.Bureau;
import fr.generali.espaceclient.proxy.generic.domain.InformationIntermediaire;
import fr.generali.espaceclient.proxy.generic.domain.Intermediaire;
import fr.generali.espaceclient.proxy.generic.exception.GenericAccessException;
import fr.generali.espaceclient.proxy.generic.service.IGenericFct01Service;
import fr.generali.gfb.ws.sinistre.dto.AbstractDeclarationSinistreInput;
import fr.generali.gfb.ws.sinistre.dto.AssureInput;
import fr.generali.gfb.ws.sinistre.dto.IDeclarationSinistreInput;
import fr.generali.gfb.ws.sinistre.dto.mrh.DeclarationSinistreBrisGlaceInput;
import fr.generali.gfb.ws.sinistre.dto.mrh.DeclarationSinistreDegatsDesEauxInput;
import fr.generali.gfb.ws.sinistre.dto.mrh.DeclarationSinistreDommageElectriqueInput;
import fr.generali.gfb.ws.sinistre.dto.mrh.DeclarationSinistreTempeteGreleInput;
import fr.generali.gfb.ws.sinistre.dto.mrh.DeclarationSinistreVolInput;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;
import fr.generali.socle.exceptions.BusinessException;
import fr.generali.socle.exceptions.TechnicalException;

@Transactional(TransactionMode.DISABLED)
public class DefaultDeclarationSinistreMrhServiceTest extends UnitilsJUnit4 {

    @TestedObject
    private DefaultDeclarationSinistreMrhService service;

    @Mock
    @InjectIntoByType
    private ISinistreOrigineDeconnecteService sinistreOrigineDeconnecteService;

    @Mock
    @InjectIntoByType
    private ISinistreOrigineConnecteService sinistreOrigineConnecteService;

    @Mock
    @InjectIntoByType
    private IGenericFct01Service genericClientService;

    @Mock
    @InjectIntoByType
    Properties properties;
    
    @Mock
    @InjectIntoByType
    private IProducerService producerService;

    private final String mailTrieste = StringUtils.EMPTY;

    /**
     * @throws DeclarationSinistreException
     */
    @Test
    public void declarerBrisDeGlaceDeconnecte() throws DeclarationSinistreException {

        final DeclarationSinistreBrisGlaceInput input =
                        new DeclarationSinistreBrisGlaceInput().origine("EMM").assure(new AssureInput());
        sinistreOrigineDeconnecteService.traitementSinistre((DeclarationSinistre ) EasyMock.anyObject());
        EasyMock.expectLastCall().once();

        EasyMockUnitils.replay();

        service.declarerBrisDeGlace(input);

    }

    @Test
    public void declarerBrisDeGlaceConnecte() throws GenericAccessException, TechnicalException, BusinessException {

        final DeclarationSinistreBrisGlaceInput input =
                        new DeclarationSinistreBrisGlaceInput()
                                        .mailTrieste(StringUtils.EMPTY)
                                        .origine("ESPACE_CLIENT")
                                        .assure(
                                                        new AssureInput().codeCompagnie("01").codePortefeuille("01")
                                                                        .idRceClient("rce").numeroContrat("numcontrat"));
        
        final InformationIntermediaire informationIntermediaire = new InformationIntermediaire();
        informationIntermediaire.setBureau(new Bureau());
        final String mailIntermediaire = "jean-pierre@vénè.re";
        informationIntermediaire.getBureau().setEmail(mailIntermediaire);
        
        Intermediaire intermediaire = new Intermediaire();
        intermediaire.setEmail("email de mon intermediaire");
        intermediaire.setId("identifiant de mon intermedaire");
        intermediaire.setLibelle("libelle de mon intermediaire");
        informationIntermediaire.setIntermediaire(intermediaire);
        
        EasyMock.expect(genericClientService.getInfosPortefeuille("01", "01")).andReturn(informationIntermediaire)
                        .once();

        EasyMock.expect(properties.getProperty("espaceclient.client.environnement")).andReturn("DEVELOPPEMENT").once();
        EasyMock.expect(properties.getProperty("client.sinistre.code.portefeuille.ecole")).andReturn("AZ14").once();
        EasyMock.expect(properties.getProperty("sinistre.email.destinataire.gestionnaire.developpement")).andReturn(
                        "mail@apporteur").once();

        sinistreOrigineConnecteService.traitementSinistreMrh((DeclarationSinistre ) EasyMock.anyObject(), EasyMock
                        .eq("mail@apporteur"), EasyMock.eq(false), EasyMock.eq(informationIntermediaire), EasyMock
                        .eq(mailTrieste),EasyMock.eq(false));
        EasyMock.expectLastCall().once();

        producerService.sendMessageDemandeLogAdmin((LogAdminData)EasyMock.anyObject());
        EasyMock.expectLastCall().once();
        
        EasyMockUnitils.replay();

        service.declarerBrisDeGlace(input);

    }

    @Test
    public void declarerDegatDesEauxDeconnecte() throws DeclarationSinistreException {

        final DeclarationSinistreDegatsDesEauxInput input =
                        new DeclarationSinistreDegatsDesEauxInput().origine("EMM").assure(new AssureInput());
        sinistreOrigineDeconnecteService.traitementSinistre((DeclarationSinistre ) EasyMock.anyObject());
        EasyMock.expectLastCall().once();
        EasyMockUnitils.replay();

        service.declarerDegatDesEaux(input);

    }

    @Test
    public void declarerDegatDesEauxConnecte() throws GenericAccessException, TechnicalException, BusinessException {

        final DeclarationSinistreDegatsDesEauxInput input =
                        new DeclarationSinistreDegatsDesEauxInput().mailTrieste(StringUtils.EMPTY).origine(
                                        "ESPACE_CLIENT").assure(
                                        new AssureInput().codeCompagnie("01").codePortefeuille("01").idRceClient("rce")
                                                        .numeroContrat("numcontrat"));
        final InformationIntermediaire informationIntermediaire = new InformationIntermediaire();
        informationIntermediaire.setBureau(new Bureau());
        final String mailIntermediaire = "jean-pierre@vénè.re";
        informationIntermediaire.getBureau().setEmail(mailIntermediaire);
        
        Intermediaire intermediaire = new Intermediaire();
        intermediaire.setEmail("email de mon intermediaire");
        intermediaire.setId("identifiant de mon intermedaire");
        intermediaire.setLibelle("libelle de mon intermediaire");
        informationIntermediaire.setIntermediaire(intermediaire);
        
        EasyMock.expect(genericClientService.getInfosPortefeuille("01", "01")).andReturn(informationIntermediaire)
                        .once();

        EasyMock.expect(properties.getProperty("espaceclient.client.environnement")).andReturn("DEVELOPPEMENT").once();
        EasyMock.expect(properties.getProperty("client.sinistre.code.portefeuille.ecole")).andReturn("AZ14").once();
        EasyMock.expect(properties.getProperty("sinistre.email.destinataire.gestionnaire.developpement")).andReturn(
                        "mail@apporteur").once();

        sinistreOrigineConnecteService.traitementSinistreMrh((DeclarationSinistre ) EasyMock.anyObject(), EasyMock
                        .eq("mail@apporteur"), EasyMock.eq(false), EasyMock.eq(informationIntermediaire), EasyMock
                        .eq(mailTrieste),EasyMock.eq(false));
        EasyMock.expectLastCall().once();

        producerService.sendMessageDemandeLogAdmin((LogAdminData)EasyMock.anyObject());
        EasyMock.expectLastCall().once();
        
        EasyMockUnitils.replay();

        service.declarerDegatDesEaux(input);

    }

    @Test
    public void declarerDommageElectriqueDeconnecte() throws DeclarationSinistreException {

        final DeclarationSinistreDommageElectriqueInput input =
                        new DeclarationSinistreDommageElectriqueInput().origine("EMM").assure(new AssureInput());
        sinistreOrigineDeconnecteService.traitementSinistre((DeclarationSinistre ) EasyMock.anyObject());
        EasyMock.expectLastCall().once();
        EasyMockUnitils.replay();

        service.declarerDommageElectrique(input);

    }

    @Test
    public void declarerDommageElectriqueConnecte() throws GenericAccessException, TechnicalException, BusinessException {

        final DeclarationSinistreDommageElectriqueInput input =
                        new DeclarationSinistreDommageElectriqueInput().mailTrieste(StringUtils.EMPTY).origine(
                                        "ESPACE_CLIENT").assure(
                                        new AssureInput().codeCompagnie("01").codePortefeuille("01").idRceClient("rce")
                                                        .numeroContrat("numcontrat"));
        final InformationIntermediaire informationIntermediaire = new InformationIntermediaire();
        informationIntermediaire.setBureau(new Bureau());
        final String mailIntermediaire = "jean-pierre@vénè.re";
        informationIntermediaire.getBureau().setEmail(mailIntermediaire);
        
        Intermediaire intermediaire = new Intermediaire();
        intermediaire.setEmail("email de mon intermediaire");
        intermediaire.setId("identifiant de mon intermedaire");
        intermediaire.setLibelle("libelle de mon intermediaire");
        informationIntermediaire.setIntermediaire(intermediaire);
        
        EasyMock.expect(genericClientService.getInfosPortefeuille("01", "01")).andReturn(informationIntermediaire)
                        .once();

        EasyMock.expect(properties.getProperty("espaceclient.client.environnement")).andReturn("DEVELOPPEMENT").once();
        EasyMock.expect(properties.getProperty("client.sinistre.code.portefeuille.ecole")).andReturn("AZ14").once();
        EasyMock.expect(properties.getProperty("sinistre.email.destinataire.gestionnaire.developpement")).andReturn(
                        "mail@apporteur").once();

        sinistreOrigineConnecteService.traitementSinistreMrh((DeclarationSinistre ) EasyMock.anyObject(), EasyMock
                        .eq("mail@apporteur"), EasyMock.eq(false), EasyMock.eq(informationIntermediaire), EasyMock
                        .eq(mailTrieste),EasyMock.eq(false));
        EasyMock.expectLastCall().once();

        producerService.sendMessageDemandeLogAdmin((LogAdminData)EasyMock.anyObject());
        EasyMock.expectLastCall().once();
        
        EasyMockUnitils.replay();

        service.declarerDommageElectrique(input);
    }

    @Test
    public void declarerTempeteGreleDeconnecte() throws DeclarationSinistreException {

        final DeclarationSinistreTempeteGreleInput input =
                        new DeclarationSinistreTempeteGreleInput().origine("EMM").assure(new AssureInput());
        sinistreOrigineDeconnecteService.traitementSinistre((DeclarationSinistre ) EasyMock.anyObject());
        EasyMock.expectLastCall().once();
        EasyMockUnitils.replay();

        service.declarerTempeteGrele(input);

    }

    @Test
    public void declarerTempeteGreleConnecte() throws GenericAccessException, TechnicalException, BusinessException {

        final DeclarationSinistreTempeteGreleInput input =
                        new DeclarationSinistreTempeteGreleInput().mailTrieste(StringUtils.EMPTY).origine(
                                        "ESPACE_CLIENT").assure(
                                        new AssureInput().codeCompagnie("01").codePortefeuille("01").idRceClient("rce")
                                                        .numeroContrat("numcontrat"));
        final InformationIntermediaire informationIntermediaire = new InformationIntermediaire();
        informationIntermediaire.setBureau(new Bureau());
        final String mailIntermediaire = "jean-pierre@vénè.re";
        informationIntermediaire.getBureau().setEmail(mailIntermediaire);
        
        Intermediaire intermediaire = new Intermediaire();
        intermediaire.setEmail("email de mon intermediaire");
        intermediaire.setId("identifiant de mon intermedaire");
        intermediaire.setLibelle("libelle de mon intermediaire");
        informationIntermediaire.setIntermediaire(intermediaire);
        
        EasyMock.expect(genericClientService.getInfosPortefeuille("01", "01")).andReturn(informationIntermediaire)
                        .once();

        EasyMock.expect(properties.getProperty("espaceclient.client.environnement")).andReturn("DEVELOPPEMENT").once();
        EasyMock.expect(properties.getProperty("client.sinistre.code.portefeuille.ecole")).andReturn("AZ14").once();
        EasyMock.expect(properties.getProperty("sinistre.email.destinataire.gestionnaire.developpement")).andReturn(
                        "mail@apporteur").once();

        sinistreOrigineConnecteService.traitementSinistreMrh((DeclarationSinistre ) EasyMock.anyObject(), EasyMock
                        .eq("mail@apporteur"), EasyMock.eq(false), EasyMock.eq(informationIntermediaire), EasyMock
                        .eq(mailTrieste),EasyMock.eq(false));
        EasyMock.expectLastCall().once();

        producerService.sendMessageDemandeLogAdmin((LogAdminData)EasyMock.anyObject());
        EasyMock.expectLastCall().once();
        
        EasyMockUnitils.replay();

        service.declarerTempeteGrele(input);
    }

    @Test
    public void declarerVolDeconnecte() throws DeclarationSinistreException {

        final DeclarationSinistreVolInput input =
                        new DeclarationSinistreVolInput().origine("EMM").assure(new AssureInput());
        sinistreOrigineDeconnecteService.traitementSinistre((DeclarationSinistre ) EasyMock.anyObject());
        EasyMock.expectLastCall().once();
        EasyMockUnitils.replay();

        service.declarerVol(input);

    }

    @Test
    public void declarerVolConnecte() throws GenericAccessException, TechnicalException, BusinessException {

        final DeclarationSinistreVolInput input =
                        new DeclarationSinistreVolInput()
                                        .mailTrieste(StringUtils.EMPTY)
                                        .origine("ESPACE_CLIENT")
                                        .assure(
                                                        new AssureInput().codeCompagnie("01").codePortefeuille("01")
                                                                        .idRceClient("rce").numeroContrat("numcontrat"));
        final InformationIntermediaire informationIntermediaire = new InformationIntermediaire();
        informationIntermediaire.setBureau(new Bureau());
        final String mailIntermediaire = "jean-pierre@vénè.re";
        informationIntermediaire.getBureau().setEmail(mailIntermediaire);
        
        Intermediaire intermediaire = new Intermediaire();
        intermediaire.setEmail("email de mon intermediaire");
        intermediaire.setId("identifiant de mon intermedaire");
        intermediaire.setLibelle("libelle de mon intermediaire");
        informationIntermediaire.setIntermediaire(intermediaire);
        
        EasyMock.expect(genericClientService.getInfosPortefeuille("01", "01")).andReturn(informationIntermediaire)
                        .once();

        EasyMock.expect(properties.getProperty("espaceclient.client.environnement")).andReturn("DEVELOPPEMENT").once();
        EasyMock.expect(properties.getProperty("client.sinistre.code.portefeuille.ecole")).andReturn("AZ14").once();
        EasyMock.expect(properties.getProperty("sinistre.email.destinataire.gestionnaire.developpement")).andReturn(
                        "mail@apporteur").once();

        sinistreOrigineConnecteService.traitementSinistreMrh((DeclarationSinistre ) EasyMock.anyObject(), EasyMock
                        .eq("mail@apporteur"), EasyMock.eq(false), EasyMock.eq(informationIntermediaire), EasyMock
                        .eq(mailTrieste),EasyMock.eq(false));
        EasyMock.expectLastCall().once();

        producerService.sendMessageDemandeLogAdmin((LogAdminData)EasyMock.anyObject());
        EasyMock.expectLastCall().once();
        
        EasyMockUnitils.replay();

        service.declarerVol(input);
    }

    @Test(expected = TechnicalException.class)
    public void declarerSinistreKoLogAdminBusinessException() throws GenericAccessException, TechnicalException, BusinessException {

        final DeclarationSinistreVolInput input =
                        new DeclarationSinistreVolInput()
                                        .mailTrieste(StringUtils.EMPTY)
                                        .origine("ESPACE_CLIENT")
                                        .assure(
                                                        new AssureInput().codeCompagnie("01").codePortefeuille("01")
                                                                        .idRceClient("rce").numeroContrat("numcontrat"));
        final InformationIntermediaire informationIntermediaire = new InformationIntermediaire();
        informationIntermediaire.setBureau(new Bureau());
        final String mailIntermediaire = "jean-pierre@vénè.re";
        informationIntermediaire.getBureau().setEmail(mailIntermediaire);
        
        Intermediaire intermediaire = new Intermediaire();
        intermediaire.setEmail("email de mon intermediaire");
        intermediaire.setId("identifiant de mon intermedaire");
        intermediaire.setLibelle("libelle de mon intermediaire");
        informationIntermediaire.setIntermediaire(intermediaire);
        
        EasyMock.expect(genericClientService.getInfosPortefeuille("01", "01")).andReturn(informationIntermediaire)
                        .once();

        EasyMock.expect(properties.getProperty("espaceclient.client.environnement")).andReturn("DEVELOPPEMENT").once();
        EasyMock.expect(properties.getProperty("client.sinistre.code.portefeuille.ecole")).andReturn("AZ14").once();
        EasyMock.expect(properties.getProperty("sinistre.email.destinataire.gestionnaire.developpement")).andReturn(
                        "mail@apporteur").once();

        sinistreOrigineConnecteService.traitementSinistreMrh((DeclarationSinistre ) EasyMock.anyObject(), EasyMock
                        .eq("mail@apporteur"), EasyMock.eq(false), EasyMock.eq(informationIntermediaire), EasyMock
                        .eq(mailTrieste),EasyMock.eq(false));
        EasyMock.expectLastCall().once();

        producerService.sendMessageDemandeLogAdmin((LogAdminData)EasyMock.anyObject());
        EasyMock.expectLastCall().andThrow(new BusinessException(){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
        	
        });
        
        EasyMockUnitils.replay();

        service.declarerVol(input);
    }
    
    /*
     * Tests des méthodes utilitaires:
     */

    static private class DummyDeclarationSinistreInput extends AbstractDeclarationSinistreInput {

        @Override
        public DummyDeclarationSinistreInput mailTrieste(String mailTrieste) {
            this.mailTrieste = mailTrieste;
            return this;
        }

    }

    @Test(expected = IllegalArgumentException.class)
    public void verifierAssureInputModeConnecteCodeCompagnie() {
        final AssureInput assureInput = minimalAssureInputConnecte();
        DefaultDeclarationSinistreMrhService.verifierAssureInputModeConnecte(assureInput.codeCompagnie(null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void verifierAssureInputModeConnecteCodePortefeuille() {
        final AssureInput assureInput = minimalAssureInputConnecte();
        DefaultDeclarationSinistreMrhService.verifierAssureInputModeConnecte(assureInput.codePortefeuille(null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void verifierAssureInputModeConnecteIdRce() {
        final AssureInput assureInput = minimalAssureInputConnecte();
        DefaultDeclarationSinistreMrhService.verifierAssureInputModeConnecte(assureInput.idRceClient(null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void verifierAssureInputModeConnecteNumeroContrat() {
        final AssureInput assureInput = minimalAssureInputConnecte();
        DefaultDeclarationSinistreMrhService.verifierAssureInputModeConnecte(assureInput.numeroContrat(null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void verifierInputModeConnecteOrigine() {
        final DummyDeclarationSinistreInput input = (DummyDeclarationSinistreInput ) minimalInputConnecte();
        DefaultDeclarationSinistreMrhService.verifierInput(input.origine(null));
    }

    @Test
    public void verifierInputModeConnecteOk() {
        final DummyDeclarationSinistreInput input = (DummyDeclarationSinistreInput ) minimalInputConnecte();
        DefaultDeclarationSinistreMrhService.verifierInput(input);
    }

    private IDeclarationSinistreInput minimalInputConnecte() {
        return new DummyDeclarationSinistreInput().assure(minimalAssureInputConnecte()).origine("ESPACE_CLIENT");
    }

    private AssureInput minimalAssureInputConnecte() {
        final AssureInput assureInput =
                        new AssureInput().codeCompagnie("codeCompagnie").codePortefeuille("codePortefeille")
                                        .idRceClient("idRCE").numeroContrat("numeroContrat");
        return assureInput;
    }
}
