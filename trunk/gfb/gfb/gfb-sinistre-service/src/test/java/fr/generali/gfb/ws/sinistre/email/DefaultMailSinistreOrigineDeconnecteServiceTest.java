package fr.generali.gfb.ws.sinistre.email;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Method;
import java.util.Properties;

import org.easymock.classextension.EasyMock;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;
import org.unitils.UnitilsJUnit4;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;
import org.unitils.easymock.EasyMockUnitils;
import org.unitils.easymock.annotation.Mock;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;

import fr.generali.espaceclient.common.service.mail.IMailService;
import fr.generali.espaceclient.common.util.HtmlTextBuilder;
import fr.generali.espaceclient.proxy.generic.domain.InformationIntermediaire;
import fr.generali.gfb.ws.sinistre.DeclarationSinistreException;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.Assure;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;

@Transactional(TransactionMode.DISABLED)
public class DefaultMailSinistreOrigineDeconnecteServiceTest extends UnitilsJUnit4 {

    @TestedObject
    private DefaultMailSinistreOrigineDeconnecteService testedObject;

    @Mock
    @InjectIntoByType
    private Properties properties;

    @Mock
    @InjectIntoByType
    private IMailService mailSender;

    @Test
    public void isReseauSalarie() {
        assertFalse(DefaultMailSinistreOrigineDeconnecteService.isReseauSalarie(null));
        assertFalse(DefaultMailSinistreOrigineDeconnecteService.isReseauSalarie(""));
        assertFalse(DefaultMailSinistreOrigineDeconnecteService.isReseauSalarie("12345678"));
        assertFalse(DefaultMailSinistreOrigineDeconnecteService.isReseauSalarie("012345678"));
        assertTrue(DefaultMailSinistreOrigineDeconnecteService.isReseauSalarie("123456789"));
        assertTrue(DefaultMailSinistreOrigineDeconnecteService.isReseauSalarie("163456789"));
        assertTrue(DefaultMailSinistreOrigineDeconnecteService.isReseauSalarie("253456789"));
    }

    @Test
    public void getGestionnaireGeneraliEmailReseauSalarie() throws DeclarationSinistreException {
        final String reseauSalarieKey = "sinistre.email.destinataire.gestionnaire_generali.reseau_salarie";
        final String expected = "subido@el.train";
        EasyMock.expect(properties.getProperty(reseauSalarieKey)).andReturn(expected).anyTimes();
        EasyMockUnitils.replay();
        assertEquals(expected, testedObject.getGestionnaireGeneraliEmail("123456789"));
    }

    @Test
    public void getGestionnaireGeneraliEmailCourtier() throws DeclarationSinistreException {
        final String reseauCourtierKey = "sinistre.email.destinataire.gestionnaire_generali.reseau_agent_courtier";
        final String expected = "subido@el.train";
        EasyMock.expect(properties.getProperty(reseauCourtierKey)).andReturn(expected).anyTimes();
        EasyMockUnitils.replay();
        assertEquals(expected, testedObject.getGestionnaireGeneraliEmail("012345678"));
    }

    @Test
    public void getGestionnaireGeneraliEmailCourtierNotFound() {
        final String expected = ""; // !!! Pas de mail trouvé
        final String reseauKey = "sinistre.email.destinataire.gestionnaire_generali.reseau_agent_courtier";
        EasyMock.expect(properties.getProperty(reseauKey)).andReturn(expected).anyTimes();
        EasyMockUnitils.replay();
        try {
            testedObject.getGestionnaireGeneraliEmail("012345678");
            fail();
        } catch (final DeclarationSinistreException e) {
        }

    }

    @Test
    public void getGestionnaireGeneraliEmailSalarieNotFound() {
        final String expected = ""; // !!! Pas de mail trouvé
        final String reseauKey = "sinistre.email.destinataire.gestionnaire_generali.reseau_salarie";
        EasyMock.expect(properties.getProperty(reseauKey)).andReturn(expected).anyTimes();
        EasyMockUnitils.replay();
        try {
            testedObject.getGestionnaireGeneraliEmail("123456789");
            fail();
        } catch (final DeclarationSinistreException e) {
        }
    }

    @Test
    public void buildDefaultMailClientInternaute() throws SecurityException, NoSuchMethodException {
        final String mail = recupererCorpsMailClient();
        assertTrue(mail.indexOf("Vous venez de nous déclarer un sinistre") != -1);
    }

    @Test
    public void buildDefaultMailGestionnaireGenerali() throws SecurityException, NoSuchMethodException {
        final String mail = recupererCorpsMailGestionnaire();
        assertTrue(mail.indexOf("Veuillez trouver ci-joint une déclaration de sinistre déposée par") != -1);
    }

    @Test
    public void sendMailClientInternaute() throws SecurityException, NoSuchMethodException {
        final String expediteur = "no@generali.fr";
        EasyMock.expect(properties.getProperty("sinistre.email.expediteur.client")).andReturn(expediteur).once();
        final DeclarationSinistre decla = new DeclarationSinistre();
        final Assure assure = new Assure();
        final String destinataire = "jean.pierre@houdin.fr";
        assure.setEmail(destinataire);
        decla.setAssure(assure);
        final String attachementName = "attachementName";
        final byte[] attachement = new byte[0];
        final String mail = recupererCorpsMailClient();
        mailSender.sendMail(EasyMock.eq(expediteur), EasyMock.aryEq(new String[] {destinataire }), (String ) EasyMock
                        .anyObject(), EasyMock.eq("Votre déclaration de sinistre en ligne"), EasyMock.eq(mail),
                        EasyMock.eq(attachementName), EasyMock.aryEq(attachement), EasyMock.eq(true));
        EasyMock.expectLastCall().once();

        EasyMockUnitils.replay();

        testedObject.sendMailClientInternaute(decla, attachementName, attachement,false,null);
    }

    private String recupererCorpsMailClient() throws NoSuchMethodException {
        final Method method =
                        AbstractMailSinistreService.class.getDeclaredMethod("buildDefaultMailClientInternaute",
                                        HtmlTextBuilder.class, boolean.class, boolean.class, InformationIntermediaire.class);
        ReflectionUtils.makeAccessible(method);
        final HtmlTextBuilder htmlTextBuilder = new HtmlTextBuilder(true, new StringBuffer());
        ReflectionUtils.invokeMethod(method, testedObject, new Object[] {htmlTextBuilder, false, false, null });

        final String mail = htmlTextBuilder.getBody().toString();
        return mail;
    }

    @Test
    public void sendMailGestionnaireGenerali() throws SecurityException, NoSuchMethodException,
                    DeclarationSinistreException {
        final String expediteur = "no@generali.fr";
        EasyMock.expect(properties.getProperty("sinistre.email.expediteur.gestionnaire_generali"))
                        .andReturn(expediteur).once();
        final String mailDestinataireKey = "sinistre.email.destinataire.gestionnaire_generali.reseau_agent_courtier";
        final String destinataire = "jean.pierre@houdin.fr";
        EasyMock.expect(properties.getProperty(mailDestinataireKey)).andReturn(destinataire).once();
        final DeclarationSinistre decla = new DeclarationSinistre();
        final Assure assure = new Assure();
        assure.setEmail(destinataire);
        decla.setAssure(assure);

        final String attachementName = "attachementName";
        final byte[] attachement = new byte[0];

        final String mail = recupererCorpsMailGestionnaire();

        mailSender.sendMail(EasyMock.eq(expediteur), EasyMock.aryEq(new String[] {destinataire }), (String ) EasyMock
                        .anyObject(), EasyMock.eq("Déclaration de sinistre à traiter"), EasyMock.eq(mail), EasyMock
                        .eq(attachementName), EasyMock.aryEq(attachement), EasyMock.eq(true));
        EasyMock.expectLastCall().once();

        EasyMockUnitils.replay();

        testedObject.sendMailGestionnaireGenerali(decla, attachementName, attachement,false);
    }

    private String recupererCorpsMailGestionnaire() throws NoSuchMethodException {
        final Method method =
                        AbstractMailSinistreService.class.getDeclaredMethod("buildDefaultMailGestionnaireGenerali",
                                        HtmlTextBuilder.class,boolean.class);
        ReflectionUtils.makeAccessible(method);
        final HtmlTextBuilder htmlTextBuilder = new HtmlTextBuilder(true, new StringBuffer());
        ReflectionUtils.invokeMethod(method, testedObject, new Object[] {htmlTextBuilder,false });

        final String mail = htmlTextBuilder.getBody().toString();
        return mail;
    }
}
