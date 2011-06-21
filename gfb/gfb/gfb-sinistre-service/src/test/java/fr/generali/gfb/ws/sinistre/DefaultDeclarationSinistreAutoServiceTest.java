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
import fr.generali.gfb.ws.sinistre.dto.auto.DeclarationSinistreAccidentIncendieInput;
import fr.generali.gfb.ws.sinistre.dto.auto.DeclarationSinistreVandalismeInput;
import fr.generali.gfb.ws.sinistre.dto.auto.DeclarationSinistreVolInput;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;
import fr.generali.socle.exceptions.BusinessException;
import fr.generali.socle.exceptions.TechnicalException;

@Transactional(TransactionMode.DISABLED)
public class DefaultDeclarationSinistreAutoServiceTest extends UnitilsJUnit4 {

    @TestedObject
    private DefaultDeclarationSinistreAutoService service;

    @Mock
    @InjectIntoByType
    private ISinistreOrigineConnecteService sinistreOrigineConnecteService;

    @Mock
    @InjectIntoByType
    private IGenericFct01Service genericClientService;

    @Mock
    @InjectIntoByType
    private IProducerService producerService;

    
    @Mock
    @InjectIntoByType
    Properties properties;

    private final String mailTrieste = StringUtils.EMPTY;

    @Test
    public void declarerAccident() throws GenericAccessException, TechnicalException, BusinessException {

        final DeclarationSinistreAccidentIncendieInput input =
                        new DeclarationSinistreAccidentIncendieInput().mailTrieste(StringUtils.EMPTY).origine(
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

        sinistreOrigineConnecteService.traitementSinistreAuto((DeclarationSinistre ) EasyMock.anyObject(), EasyMock
                        .eq("mail@apporteur"), EasyMock.eq(false), EasyMock.eq(informationIntermediaire), EasyMock
                        .eq(mailTrieste),EasyMock.eq(false));
        EasyMock.expectLastCall().once();
        
        producerService.sendMessageDemandeLogAdmin((LogAdminData)EasyMock.anyObject());
        EasyMock.expectLastCall().once();
        
        EasyMockUnitils.replay();

        service.declarerAccident(input);
    }

    @Test(expected = TechnicalException.class)
    public void declarerSinisreKoLogAdminBusinessException() throws GenericAccessException, TechnicalException, BusinessException {

        final DeclarationSinistreAccidentIncendieInput input =
                        new DeclarationSinistreAccidentIncendieInput().mailTrieste(StringUtils.EMPTY).origine(
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

        sinistreOrigineConnecteService.traitementSinistreAuto((DeclarationSinistre ) EasyMock.anyObject(), EasyMock
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

        service.declarerAccident(input);
    }
    
    
    @Test
    public void declarerIncendie() throws GenericAccessException, TechnicalException, BusinessException {

        final DeclarationSinistreAccidentIncendieInput input =
                        new DeclarationSinistreAccidentIncendieInput().mailTrieste(StringUtils.EMPTY).origine(
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

        sinistreOrigineConnecteService.traitementSinistreAuto((DeclarationSinistre ) EasyMock.anyObject(), EasyMock
                        .eq("mail@apporteur"), EasyMock.eq(false), EasyMock.eq(informationIntermediaire), EasyMock
                        .eq(mailTrieste),EasyMock.eq(false));
        EasyMock.expectLastCall().once();

        producerService.sendMessageDemandeLogAdmin((LogAdminData)EasyMock.anyObject());
        EasyMock.expectLastCall().once();
        
        EasyMockUnitils.replay();

        service.declarerIncendie(input);
    }

    @Test
    public void declarerVandalisme() throws GenericAccessException, TechnicalException, BusinessException {

        final DeclarationSinistreVandalismeInput input =
                        new DeclarationSinistreVandalismeInput()
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

        sinistreOrigineConnecteService.traitementSinistreAuto((DeclarationSinistre ) EasyMock.anyObject(), EasyMock
                        .eq("mail@apporteur"), EasyMock.eq(false), EasyMock.eq(informationIntermediaire), EasyMock
                        .eq(mailTrieste),EasyMock.eq(false));
        EasyMock.expectLastCall().once();

        producerService.sendMessageDemandeLogAdmin((LogAdminData)EasyMock.anyObject());
        EasyMock.expectLastCall().once();
        
        EasyMockUnitils.replay();

        service.declarerVandalisme(input);
    }

    @Test
    public void declarerVol() throws GenericAccessException, TechnicalException, BusinessException {

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

        sinistreOrigineConnecteService.traitementSinistreAuto((DeclarationSinistre ) EasyMock.anyObject(), EasyMock
                        .eq("mail@apporteur"), EasyMock.eq(false), EasyMock.eq(informationIntermediaire), EasyMock
                        .eq(mailTrieste),EasyMock.eq(false));
        EasyMock.expectLastCall().once();

        producerService.sendMessageDemandeLogAdmin((LogAdminData)EasyMock.anyObject());
        EasyMock.expectLastCall().once();
        
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
            return this.mailTrieste(mailTrieste);
        }

    }

    @Test(expected = IllegalArgumentException.class)
    public void verifierAssureInputCodeCompagnie() {
        final AssureInput assureInput = minimalAssureInputConnecte();
        DefaultDeclarationSinistreAutoService.verifierAssureInput(assureInput.codeCompagnie(null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void verifierAssureInputCodePortefeuille() {
        final AssureInput assureInput = minimalAssureInputConnecte();
        DefaultDeclarationSinistreAutoService.verifierAssureInput(assureInput.codePortefeuille(null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void verifierAssureInputIdRce() {
        final AssureInput assureInput = minimalAssureInputConnecte();
        DefaultDeclarationSinistreAutoService.verifierAssureInput(assureInput.idRceClient(null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void verifierAssureInputNumeroContrat() {
        final AssureInput assureInput = minimalAssureInputConnecte();
        DefaultDeclarationSinistreAutoService.verifierAssureInput(assureInput.numeroContrat(null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void verifierInputOrigine() {
        final DummyDeclarationSinistreInput input = (DummyDeclarationSinistreInput ) minimalInputConnecte();
        DefaultDeclarationSinistreAutoService.verifierInput(input.origine(null));
    }

    @Test
    public void verifierInputModeConnecteOk() {
        final DummyDeclarationSinistreInput input = (DummyDeclarationSinistreInput ) minimalInputConnecte();
        DefaultDeclarationSinistreAutoService.verifierInput(input);
    }

    @Test(expected = IllegalArgumentException.class)
    public void verifierInputModeDeconnecteKO() {
        final DummyDeclarationSinistreInput input = (DummyDeclarationSinistreInput ) minimalInputConnecte();
        DefaultDeclarationSinistreAutoService.verifierInput(input.origine("EMM"));
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
