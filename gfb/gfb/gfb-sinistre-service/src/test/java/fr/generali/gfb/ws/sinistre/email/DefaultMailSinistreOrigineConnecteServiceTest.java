package fr.generali.gfb.ws.sinistre.email;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.Properties;

import org.easymock.classextension.EasyMock;
import org.junit.Assert;
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
import fr.generali.gfb.ws.sinistre.persistence.domain.common.Assure;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;

@Transactional(TransactionMode.DISABLED)
public class DefaultMailSinistreOrigineConnecteServiceTest extends UnitilsJUnit4 {

    @TestedObject
    private DefaultMailSinistreOrigineConnecteService testedObject;

    @Mock
    @InjectIntoByType
    private Properties properties;

    @Mock
    @InjectIntoByType
    private IMailService mailSender;

    /**
     * test de la méthode composeObject lorsqu'on est en production
     * 
     * @throws NoSuchMethodException
     */
    @Test
    public void composeObjectInProduction() throws NoSuchMethodException {
        Method method =
                        DefaultMailSinistreOrigineConnecteService.class
                                        .getDeclaredMethod("composeObject", String.class);
        ReflectionUtils.makeAccessible(method);
        testedObject.properties = properties;

        EasyMock.expect(properties.getProperty("espaceclient.client.environnement")).andReturn("PRODUCTION").anyTimes();
        EasyMockUnitils.replay();

        String actual =
                        (String ) ReflectionUtils
                                        .invokeMethod(method, testedObject, new Object[] {"ClientInternaute" });
        Assert.assertNotNull(actual);
        Assert.assertEquals("Votre déclaration de sinistre en ligne", actual);

        actual = (String ) ReflectionUtils.invokeMethod(method, testedObject, new Object[] {"GestionnaireGenerali" });
        Assert.assertNotNull(actual);
        Assert.assertEquals("Déclaration de sinistre à traiter", actual);
    }

    /**
     * test de la méthode composeObject lorsqu'on a pas réussi à déterminer
     * notre environnement
     * 
     * @throws NoSuchMethodException
     */
    @Test
    public void composeObjectNull() throws NoSuchMethodException {
        Method method =
                        DefaultMailSinistreOrigineConnecteService.class
                                        .getDeclaredMethod("composeObject", String.class);
        ReflectionUtils.makeAccessible(method);
        testedObject.properties = properties;

        EasyMock.expect(properties.getProperty("espaceclient.client.environnement")).andReturn(null).anyTimes();
        EasyMockUnitils.replay();

        String actual =
                        (String ) ReflectionUtils
                                        .invokeMethod(method, testedObject, new Object[] {"ClientInternaute" });
        Assert.assertNotNull(actual);
        Assert.assertEquals("Votre déclaration de sinistre en ligne", actual);

        actual = (String ) ReflectionUtils.invokeMethod(method, testedObject, new Object[] {"GestionnaireGenerali" });
        Assert.assertNotNull(actual);
        Assert.assertEquals("Déclaration de sinistre à traiter", actual);
    }

    /**
     * test de la méthode composeObject lorsqu'on n'est pas en production
     * 
     * @throws NoSuchMethodException
     */
    @Test
    public void composeObjectNotInProduction() throws NoSuchMethodException {
        Method method =
                        DefaultMailSinistreOrigineConnecteService.class
                                        .getDeclaredMethod("composeObject", String.class);
        ReflectionUtils.makeAccessible(method);
        testedObject.properties = properties;

        EasyMock.expect(properties.getProperty("espaceclient.client.environnement")).andReturn("RECETTE").anyTimes();
        EasyMockUnitils.replay();

        String actual =
                        (String ) ReflectionUtils
                                        .invokeMethod(method, testedObject, new Object[] {"ClientInternaute" });
        Assert.assertNotNull(actual);
        Assert.assertEquals("ENVIRONNEMENT DE RECETTE : Votre déclaration de sinistre en ligne", actual);

        actual = (String ) ReflectionUtils.invokeMethod(method, testedObject, new Object[] {"GestionnaireGenerali" });
        Assert.assertNotNull(actual);
        Assert.assertEquals("ENVIRONNEMENT DE RECETTE : Déclaration de sinistre à traiter", actual);
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
        EasyMock.expect(properties.getProperty("espaceclient.client.environnement")).andReturn("PRODUCTION").once();
        DeclarationSinistre decla = new DeclarationSinistre();
        Assure assure = new Assure();
        final String destinataire = "jean.pierre@houdin.fr";
        assure.setEmail(destinataire);
        decla.setAssure(assure);

        String attachementName = "attachementName";
        byte[] attachement = new byte[0];

        final String mail = recupererCorpsMailClient();

        mailSender.sendMail(EasyMock.eq(expediteur), EasyMock.aryEq(new String[] {destinataire }), EasyMock
                        .eq(destinataire), EasyMock.eq("Votre déclaration de sinistre en ligne"), EasyMock.eq(mail),
                        EasyMock.eq(attachementName), EasyMock.aryEq(attachement), EasyMock.eq(true));
        EasyMock.expectLastCall().once();

        EasyMockUnitils.replay();

        testedObject.sendMailClientInternaute(decla, attachementName, attachement,false,null);
    }

    private String recupererCorpsMailClient() throws NoSuchMethodException {
        Method method =
                        AbstractMailSinistreService.class.getDeclaredMethod("buildDefaultMailClientInternaute",
                                        HtmlTextBuilder.class, boolean.class, boolean.class, InformationIntermediaire.class);
        ReflectionUtils.makeAccessible(method);
        HtmlTextBuilder htmlTextBuilder = new HtmlTextBuilder(true, new StringBuffer());
        ReflectionUtils.invokeMethod(method, testedObject, new Object[] {htmlTextBuilder, false,false,null });

        final String mail = htmlTextBuilder.getBody().toString();
        return mail;
    }

    @Test
    public void sendMailGestionnaireGenerali() throws SecurityException, NoSuchMethodException {
        final String expediteur = "no@generali.fr";
        EasyMock.expect(properties.getProperty("sinistre.email.expediteur.gestionnaire_generali"))
                        .andReturn(expediteur).once();
        EasyMock.expect(properties.getProperty("espaceclient.client.environnement")).andReturn("PRODUCTION").once();
        final String destinataire = "jean.pierre@houdin.fr";

        final String attachementName = "attachementName";
        final byte[] attachement = new byte[0];

        final String mail = recupererCorpsMailGestionnaire();

        mailSender.sendMail(EasyMock.eq(expediteur), EasyMock.aryEq(new String[] {destinataire }), EasyMock
                        .eq(destinataire), EasyMock.eq("Déclaration de sinistre à traiter"), EasyMock.eq(mail),
                        EasyMock.eq(attachementName), EasyMock.aryEq(attachement), EasyMock.eq(true));
        EasyMock.expectLastCall().once();

        EasyMockUnitils.replay();

        testedObject.sendMailGestionnaireGenerali(attachementName, attachement, destinataire,false);
    }

    private String recupererCorpsMailGestionnaire() throws NoSuchMethodException {
        Method method =
                        AbstractMailSinistreService.class.getDeclaredMethod("buildDefaultMailGestionnaireGenerali",
                                        HtmlTextBuilder.class,boolean.class);
        ReflectionUtils.makeAccessible(method);
        HtmlTextBuilder htmlTextBuilder = new HtmlTextBuilder(true, new StringBuffer());
        ReflectionUtils.invokeMethod(method, testedObject, new Object[] {htmlTextBuilder,false });

        final String mail = htmlTextBuilder.getBody().toString();
        return mail;
    }
}
