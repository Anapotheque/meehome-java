package fr.generali.gfb.ws.sinistre.pdf;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import junit.framework.Assert;

import org.easymock.classextension.EasyMock;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;
import org.unitils.UnitilsJUnit4;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;
import org.unitils.easymock.EasyMockUnitils;
import org.unitils.easymock.annotation.Mock;
import org.unitils.inject.annotation.TestedObject;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;

import fr.generali.espaceclient.proxy.generic.domain.Bureau;
import fr.generali.espaceclient.proxy.generic.domain.InformationIntermediaire;
import fr.generali.espaceclient.proxy.generic.domain.Intermediaire;
import fr.generali.espaceclient.proxy.generic.domain.Representant;
import fr.generali.gfb.ws.sinistre.domain.reduit.contract.CoordonneesIntermediaires;
import fr.generali.gfb.ws.sinistre.dto.OrigineDeclaration.TypeOrigine;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.Assure;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.Sinistre;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.vol.SinistreMRHVol;

/**
 * @author Mickael Morier
 */

@Transactional(TransactionMode.DISABLED)
public class CommonPdfSinistreServiceTest extends UnitilsJUnit4 {

    @TestedObject
    private CommonPdfSinistreService testedObject;

    @Mock
    private Document mockPdf;

    /**
     * test la méthode initializeCoordonneesClient. Regarde si l'objet n'est pas
     * nul et contient bien 1 ligne
     * 
     * @throws ParseException
     * @throws DocumentException
     */
    @Test
    public void testInitializeCoordonneesClient() throws ParseException {
        DeclarationSinistre declaration = initDeclarationSinistreMRH();

        PdfPTable actual = testedObject.initializeCoordonneesClient(declaration);

        Assert.assertNotNull(actual);
        Assert.assertEquals(1, actual.getRows().size());
    }

    /**
     * test la méthode createCoordonneesClient. Regarde si l'objet n'est pas nul
     * et contient bien 3 lignes
     * 
     * @throws NoSuchMethodException
     * @throws ParseException
     */
    @Test
    public void testCreateCoordonneesClient() throws NoSuchMethodException, ParseException {
        int[] tableWidthColumn = {30 };
        Assure assure = initDeclarationSinistreMRH().getAssure();

        Method method =
                        CommonPdfSinistreService.class.getDeclaredMethod("createCoordonneesClient", int[].class,
                                        Assure.class);
        ReflectionUtils.makeAccessible(method);
        PdfPTable actual =
                        (PdfPTable ) ReflectionUtils.invokeMethod(method, testedObject, new Object[] {tableWidthColumn,
                                        assure });

        Assert.assertNotNull(actual);
        Assert.assertEquals(3, actual.getRows().size());
    }

    /**
     * test la méthode initializeDate. Regarde si l'objet n'est pas nul et
     * contient bien 1 ligne
     * 
     * @throws ParseException
     * @throws DocumentException
     */
    @Test
    public void testInitializeDate() throws ParseException {
        DeclarationSinistre declaration = initDeclarationSinistreMRH();

        PdfPTable actual = testedObject.initializeDate(declaration);

        Assert.assertNotNull(actual);
        Assert.assertEquals(1, actual.getRows().size());
    }

    /**
     * test la méthode createDate
     * 
     * @throws NoSuchMethodException
     * @throws ParseException
     */
    @Test
    public void testCreateDate() throws NoSuchMethodException, ParseException {
        Sinistre sinistre = initDeclarationSinistreMRH().getSinistre();
        String expected = "Paris, le 10 avril 2009";

        Method method = CommonPdfSinistreService.class.getDeclaredMethod("createDate", Sinistre.class);
        ReflectionUtils.makeAccessible(method);
        Object actual = ReflectionUtils.invokeMethod(method, testedObject, new Object[] {sinistre });

        Assert.assertNotNull(actual);
        Assert.assertEquals(expected, actual);
    }

    /**
     * test la méthode initializeObjet
     * 
     * @throws DocumentException
     */
    @Test
    public void testInitializeObject() throws DocumentException {
        Paragraph actual = testedObject.initializeObjet("texte");

        Assert.assertNotNull(actual);
        Assert.assertEquals("texte", actual.getContent());
    }

    /**
     * test la méthode initializeIntroductionText lorsque c'est un client
     * Internet
     * 
     * @throws DocumentException
     * @throws ParseException
     */
    @Test
    public void testInitializeIntroductionTextClientInternet() throws DocumentException, ParseException {
        Boolean isClientInternet = Boolean.TRUE;
        String typeDeclaration = "habitation";
        DeclarationSinistre declaration = initDeclarationSinistreMRH();

        EasyMock.expect(mockPdf.add(null)).andReturn(null).times(7);
        EasyMockUnitils.replay();

        testedObject.initializeIntroductionText(mockPdf, declaration, isClientInternet, typeDeclaration,
                        TypeOrigine.CONNECTE, false);
    }

    /**
     * test la méthode initializeIntroductionText lorsque ce n'est pas un client
     * Internet
     * 
     * @throws DocumentException
     * @throws ParseException
     */
    @Test
    public void testInitializeIntroductionTextNonInternet() throws DocumentException, ParseException {
        // Ce test est tres etrange TODO !!!
        // le mock test si l'ajout de ligne vaut null mais ça ne sera jamais le
        // cas...

        Boolean isClientInternet = Boolean.FALSE;
        String typeDeclaration = "habitation";
        DeclarationSinistre declaration = initDeclarationSinistreMRH();

        EasyMock.expect(mockPdf.add(null)).andReturn(null).times(6);
        EasyMockUnitils.replay();

        testedObject.initializeIntroductionText(mockPdf, declaration, isClientInternet, typeDeclaration,
                        TypeOrigine.CONNECTE, false);
    }

    /**
     * test la méthode createIntroductionText
     * 
     * @throws NoSuchMethodException
     * @throws ParseException
     */
    @Test
    public void testCreateIntroductionText() throws NoSuchMethodException, ParseException {
        String typeDeclaration = "habitation";
        DeclarationSinistre declaration = initDeclarationSinistreMRH();
        String expected =
                        "Vous nous avez déclaré un sinistre habitation sur votre espace client le 10 avril 2009. Afin de faciliter le suivi de votre déclaration, nous vous adressons ci-dessous le récapitulatif des informations que vous avez saisies.";

        Method method =
                        CommonPdfSinistreService.class.getDeclaredMethod("createIntroductionText",
                                        DeclarationSinistre.class, String.class, boolean.class);
        ReflectionUtils.makeAccessible(method);
        Object actual =
                        ReflectionUtils.invokeMethod(method, testedObject, new Object[] {declaration, typeDeclaration,
                                        false });

        Assert.assertNotNull(actual);
        Assert.assertEquals(expected, actual);
    }

    /**
     * test la méthode createIntroductionTextClientInternet lorsque c'est un
     * client Internet
     * 
     * @throws NoSuchMethodException
     */
    @Test
    public void testCreateIntroductionTextClientInternetOui() throws NoSuchMethodException {
        Boolean isClientInternet = Boolean.TRUE;
        boolean isCourtier = false;
        String expected =
                        "Nous vous recontacterons rapidement pour recueillir les éléments complémentaires nécessaires au traitement de votre demande. Dans cette attente, nous sommes à votre disposition pour répondre à vos questions par mail à l'adresse serviceclientinternet@generali.fr.";

        Method method =
                        CommonPdfSinistreService.class.getDeclaredMethod("createIntroductionTextClientInternet",
                                        Boolean.class, boolean.class);
        ReflectionUtils.makeAccessible(method);
        Object actual =
                        ReflectionUtils
                                        .invokeMethod(method, testedObject,
                                                        new Object[] {isClientInternet, isCourtier });

        Assert.assertNotNull(actual);
        Assert.assertEquals(expected, actual);
    }

    /**
     * test la méthode createIntroductionTextClientInternet lorsque ce n'est pas
     * un client Internet
     * 
     * @throws NoSuchMethodException
     */
    @Test
    public void testCreateIntroductionTextClientInternetNon() throws Exception {
        Boolean isClientInternet = Boolean.FALSE;
        boolean isCourtier = false;
        String expected =
                        "Votre agence GENERALI vous recontactera rapidement pour recueillir les éléments complémentaires nécessaires au traitement de votre demande. Nous vous rappelons ci-dessous ses coordonnées : ";

        Method method =
                        CommonPdfSinistreService.class.getDeclaredMethod("createIntroductionTextClientInternet",
                                        Boolean.class, boolean.class);
        ReflectionUtils.makeAccessible(method);
        Object actual =
                        ReflectionUtils
                                        .invokeMethod(method, testedObject,
                                                        new Object[] {isClientInternet, isCourtier });

        Assert.assertNotNull(actual);
        Assert.assertEquals(expected, actual);
    }

    /**
     * test la méthode initCoordonneesAgence. Regarde si l'objet n'est pas nul
     * et contient bien 1 ligne
     * 
     * @throws DocumentException
     */
    @Test
    public void testInitCoordonneesAgence() {
        InformationIntermediaire informationIntermediaire = initInformationIntermediaire("nom", "prenom");

        PdfPTable actual = testedObject.initCoordonneesAgence(informationIntermediaire, false);

        Assert.assertNotNull(actual);
        Assert.assertEquals(1, actual.getRows().size());
    }

    /**
     * test la méthode createNomPrenomIntermediaire sans Nom ni Prenom
     * 
     * @throws NoSuchMethodException
     */
    @Test
    public void testCreateNomPrenomIntermediaireSansNomSansPrenom() throws NoSuchMethodException {
        CoordonneesIntermediaires coordonnees = initCoordonneesIntermediaires(null, null);

        Method method =
                        CommonPdfSinistreService.class.getDeclaredMethod("createNomPrenomIntermediaire",
                                        CoordonneesIntermediaires.class);
        ReflectionUtils.makeAccessible(method);
        Object actual = ReflectionUtils.invokeMethod(method, testedObject, new Object[] {coordonnees });

        Assert.assertNull(actual);
    }

    /**
     * test la méthode createNomPrenomIntermediaire avec Nom mais sans Prenom
     * 
     * @throws NoSuchMethodException
     */
    @Test
    public void testCreateNomPrenomIntermediaireAvecNomSansPrenom() throws NoSuchMethodException {
        CoordonneesIntermediaires coordonnees = initCoordonneesIntermediaires("nom", null);

        Method method =
                        CommonPdfSinistreService.class.getDeclaredMethod("createNomPrenomIntermediaire",
                                        CoordonneesIntermediaires.class);
        ReflectionUtils.makeAccessible(method);
        Object actual = ReflectionUtils.invokeMethod(method, testedObject, new Object[] {coordonnees });

        Assert.assertNotNull(actual);
        Assert.assertEquals("nom ", actual);
    }

    /**
     * test la méthode createNomPrenomIntermediaire sans Nom mais avec Prenom
     * 
     * @throws NoSuchMethodException
     */
    @Test
    public void testCreateNomPrenomIntermediaireSansNomAvecPrenom() throws NoSuchMethodException {
        CoordonneesIntermediaires coordonnees = initCoordonneesIntermediaires(null, "prenom");

        Method method =
                        CommonPdfSinistreService.class.getDeclaredMethod("createNomPrenomIntermediaire",
                                        CoordonneesIntermediaires.class);
        ReflectionUtils.makeAccessible(method);
        Object actual = ReflectionUtils.invokeMethod(method, testedObject, new Object[] {coordonnees });

        Assert.assertNotNull(actual);
        Assert.assertEquals("prenom", actual);
    }

    /**
     * test la méthode createNomPrenomIntermediaire avec Nom et Prenom
     * 
     * @throws NoSuchMethodException
     */
    @Test
    public void testCreateNomPrenomIntermediaireAvecNomAvecPrenom() throws NoSuchMethodException {
        CoordonneesIntermediaires coordonnees = initCoordonneesIntermediaires("nom", "prenom");

        Method method =
                        CommonPdfSinistreService.class.getDeclaredMethod("createNomPrenomIntermediaire",
                                        CoordonneesIntermediaires.class);
        ReflectionUtils.makeAccessible(method);
        Object actual = ReflectionUtils.invokeMethod(method, testedObject, new Object[] {coordonnees });

        Assert.assertNotNull(actual);
        Assert.assertEquals("nom prenom", actual);
    }

    /**
     * test la méthode initializeAssure. Regarde si l'objet n'est pas nul et
     * contient bien 1 ligne
     * 
     * @throws DocumentException
     */
    @Test
    public void testInitializeAssure() {
        Assure assure = initAssure();

        PdfPTable actual = testedObject.initializeAssure(2009.4f, assure, "0123", null, TypeOrigine.CONNECTE);

        Assert.assertNotNull(actual);
        Assert.assertEquals(2, actual.getRows().size());
        Assert.assertEquals(2009.4f, actual.getAbsoluteWidths()[0]);
    }

    /**
     * test la méthode createNomPrenomAssure sans Nom ni Prenom
     * 
     * @throws NoSuchMethodException
     */
    @Test
    public void testCreateNomPrenomAssureSansNomSansPrenom() throws NoSuchMethodException {
        Assure assure = new Assure();
        assure.setNom(null);
        assure.setPrenom(null);

        Method method = CommonPdfSinistreService.class.getDeclaredMethod("createNomPrenomAssure", Assure.class);
        ReflectionUtils.makeAccessible(method);
        Object actual = ReflectionUtils.invokeMethod(method, testedObject, new Object[] {assure });

        Assert.assertNotNull(actual);
        Assert.assertEquals("", actual);
    }

    /**
     * test la méthode createNomPrenomAssure avec Nom mais sans Prenom
     * 
     * @throws NoSuchMethodException
     */
    @Test
    public void testCreateNomPrenomAssureAvecNomSansPrenom() throws NoSuchMethodException {
        Assure assure = new Assure();
        assure.setNom("nom");
        assure.setVille(null);

        Method method = CommonPdfSinistreService.class.getDeclaredMethod("createNomPrenomAssure", Assure.class);
        ReflectionUtils.makeAccessible(method);
        Object actual = ReflectionUtils.invokeMethod(method, testedObject, new Object[] {assure });

        Assert.assertNotNull(actual);
        Assert.assertEquals("NOM ", actual);
    }

    /**
     * test la méthode createNomPrenomAssure sans Nom mais avec Prenom
     * 
     * @throws NoSuchMethodException
     */
    @Test
    public void testCreateNomPrenomAssureSansNomAvecPrenom() throws NoSuchMethodException {
        Assure assure = new Assure();
        assure.setNom(null);
        assure.setPrenom("prenom");

        Method method = CommonPdfSinistreService.class.getDeclaredMethod("createNomPrenomAssure", Assure.class);
        ReflectionUtils.makeAccessible(method);
        Object actual = ReflectionUtils.invokeMethod(method, testedObject, new Object[] {assure });

        Assert.assertNotNull(actual);
        Assert.assertEquals("Prenom", actual);
    }

    /**
     * test la méthode createNomPrenomAssure avec Nom et Prenom
     * 
     * @throws NoSuchMethodException
     */
    @Test
    public void testCreateNomPrenomAssureAvecNomAvecPrenom() throws NoSuchMethodException {
        Assure assure = new Assure();
        assure.setNom("nom");
        assure.setPrenom("prenom");

        Method method = CommonPdfSinistreService.class.getDeclaredMethod("createNomPrenomAssure", Assure.class);
        ReflectionUtils.makeAccessible(method);
        Object actual = ReflectionUtils.invokeMethod(method, testedObject, new Object[] {assure });

        Assert.assertNotNull(actual);
        Assert.assertEquals("NOM Prenom", actual);
    }

    /**
     * test la méthode createCodePostalAndVilleAssure sans CodePostal ni Ville
     * 
     * @throws NoSuchMethodException
     */
    @Test
    public void testCreateCodePostalAndVilleAssureSansCodePostalSansVille() throws NoSuchMethodException {
        Assure assure = new Assure();
        assure.setCodePostal(null);
        assure.setVille(null);

        Method method =
                        CommonPdfSinistreService.class
                                        .getDeclaredMethod("createCodePostalAndVilleAssure", Assure.class);
        ReflectionUtils.makeAccessible(method);
        Object actual = ReflectionUtils.invokeMethod(method, testedObject, new Object[] {assure });

        Assert.assertNotNull(actual);
        Assert.assertEquals("", actual);
    }

    /**
     * test la méthode createCodePostalAndVilleAssure avec CodePostal mais sans
     * Ville
     * 
     * @throws NoSuchMethodException
     */
    @Test
    public void testCreateCodePostalAndVilleAssureAvecCodePostalSansVille() throws NoSuchMethodException {
        Assure assure = new Assure();
        assure.setCodePostal("93200");
        assure.setVille(null);

        Method method =
                        CommonPdfSinistreService.class
                                        .getDeclaredMethod("createCodePostalAndVilleAssure", Assure.class);
        ReflectionUtils.makeAccessible(method);
        Object actual = ReflectionUtils.invokeMethod(method, testedObject, new Object[] {assure });

        Assert.assertNotNull(actual);
        Assert.assertEquals("93200 ", actual);
    }

    /**
     * test la méthode createCodePostalAndVilleAssure sans CodePostal mais avec
     * Ville
     * 
     * @throws NoSuchMethodException
     */
    @Test
    public void testCreateCodePostalAndVilleAssureSansCodePostalAvecVille() throws NoSuchMethodException {
        Assure assure = new Assure();
        assure.setCodePostal(null);
        assure.setVille("ville");

        Method method =
                        CommonPdfSinistreService.class
                                        .getDeclaredMethod("createCodePostalAndVilleAssure", Assure.class);
        ReflectionUtils.makeAccessible(method);
        Object actual = ReflectionUtils.invokeMethod(method, testedObject, new Object[] {assure });

        Assert.assertNotNull(actual);
        Assert.assertEquals("ville", actual);
    }

    /**
     * test la méthode createCodePostalAndVilleAssure avec CodePostal et Ville
     * 
     * @throws NoSuchMethodException
     */
    @Test
    public void testCreateCodePostalAndVilleAssureAvecCodePostalAvecVille() throws NoSuchMethodException {
        Assure assure = new Assure();
        assure.setCodePostal("93200");
        assure.setVille("ville");

        Method method =
                        CommonPdfSinistreService.class
                                        .getDeclaredMethod("createCodePostalAndVilleAssure", Assure.class);
        ReflectionUtils.makeAccessible(method);
        Object actual = ReflectionUtils.invokeMethod(method, testedObject, new Object[] {assure });

        Assert.assertNotNull(actual);
        Assert.assertEquals("93200 ville", actual);
    }

    /**
     * test la méthode initializeEndingText. Regarde si l'objet n'est pas nul et
     * contient bien trois paragraphe
     */
    @Test
    public void testInitializeEndingText() {
        PdfPTable actual = testedObject.initializeEndingText(false);

        Assert.assertNotNull(actual);
        Assert.assertEquals(3, actual.getRows().size());
    }

    /**
     * test la méthode createBoxEndText
     * 
     * @throws NoSuchMethodException
     */
    @Test
    public void testCreateBoxEndText() throws NoSuchMethodException {
        String expected =
                        "L'enregistrement de cette déclaration ne constitue pas un accord de prise en charge, ni une appréciation des responsabilités de la part de GENERALI IARD. Il vous incombe de conserver tous les éléments permettant la constatation et l'estimation de vos dommages.";

        Method method = CommonPdfSinistreService.class.getDeclaredMethod("createBoxEndText");
        ReflectionUtils.makeAccessible(method);
        Object actual = ReflectionUtils.invokeMethod(method, testedObject);

        Assert.assertNotNull(actual);
        Assert.assertEquals(expected, actual);
    }

    /**
     * test la méthode createLastEndText
     * 
     * @throws NoSuchMethodException
     */
    @Test
    public void testCreateLastEndText() throws NoSuchMethodException {
        String expected =
                        "Conformément à la loi Informatique et Libertés du 6 janvier 1978, vous disposez d'un droit d'accès et de rectification des données qui vous concernent. Vous pouvez exercer ce droit en vous adressant à GENERALI IARD - 7 boulevard HAUSSMANN 75456 PARIS CEDEX 09. Ces informations sont destinées à Generali IARD et sont nécessaires au traitement de votre dossier. Ces informations sont susceptibles d'être transmises à des tiers pour les besoins de la gestion de votre dossier, notamment à votre conseiller. Vous acceptez expressément que les données vous concernant leur soient ainsi transmises.";

        Method method = CommonPdfSinistreService.class.getDeclaredMethod("createLastEndText", boolean.class);
        ReflectionUtils.makeAccessible(method);
        Object actual = ReflectionUtils.invokeMethod(method, testedObject, new Object[] {false });

        Assert.assertNotNull(actual);
        Assert.assertEquals(expected, actual);
    }

    /**
     * test la méthode insertSpaceCharacterToPhoneNumber dans le cas nominal
     * 
     * @throws NoSuchMethodException
     */
    @Test
    public void testInsertSpaceCharacterToPhoneNumberCasNominal() throws NoSuchMethodException {
        Method method =
                        CommonPdfSinistreService.class.getDeclaredMethod("insertSpaceCharacterToPhoneNumber",
                                        String.class);
        ReflectionUtils.makeAccessible(method);
        Object actual = ReflectionUtils.invokeMethod(method, testedObject, new Object[] {"0102030405" });

        Assert.assertEquals("01 02 03 04 05", actual);
    }

    /**
     * test la méthode insertSpaceCharacterToPhoneNumber lorsque le numéro de
     * téléphone est nul ou vide
     * 
     * @throws NoSuchMethodException
     */
    @Test
    public void testInsertSpaceCharacterToPhoneNumberTelephoneNulOuVide() throws NoSuchMethodException {
        Method method =
                        CommonPdfSinistreService.class.getDeclaredMethod("insertSpaceCharacterToPhoneNumber",
                                        String.class);
        ReflectionUtils.makeAccessible(method);
        Object actualEmpty = ReflectionUtils.invokeMethod(method, testedObject, new Object[] {"" });
        Object actualNull = ReflectionUtils.invokeMethod(method, testedObject, new Object[] {null });

        Assert.assertEquals("", actualEmpty);
        Assert.assertEquals(null, actualNull);
    }

    /**
     * test la méthode insertSpaceCharacterToPhoneNumber lorsque le numéro de
     * téléphone est trop long
     * 
     * @throws NoSuchMethodException
     */
    @Test
    public void testInsertSpaceCharacterToPhoneNumberTelephoneTropLong() throws NoSuchMethodException {
        Method method =
                        CommonPdfSinistreService.class.getDeclaredMethod("insertSpaceCharacterToPhoneNumber",
                                        String.class);
        ReflectionUtils.makeAccessible(method);
        Object actual = ReflectionUtils.invokeMethod(method, testedObject, new Object[] {"010203040506" });

        Assert.assertEquals("010203040506", actual);
    }

    /**
     * méthode initialisant et retournant une déclaration de sinistre MRH
     * 
     * @return DeclarationSinistre
     * @throws ParseException
     */
    private DeclarationSinistre initDeclarationSinistreMRH() throws ParseException {
        DeclarationSinistre declaration = new DeclarationSinistre();

        Sinistre sinistre = new SinistreMRHVol();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sinistre.setDateDeclaration(sdf.parse("10/04/2009"));
        sinistre.setDateSinistre(sdf.parse("06/04/2009"));
        declaration.setSinistre(sinistre);

        declaration.setAssure(initAssure());

        return declaration;
    }

    /**
     * méthode initialisant et retournant des Assure
     * 
     * @param declaration
     * @return Assure
     */
    private Assure initAssure() {
        Assure assure = new Assure();
        assure.setPrenom("prenom");
        assure.setNom("nom");
        assure.setAdresse("adresse");
        assure.setCodePostal("93200");
        assure.setVille("ville");
        assure.setEmail("email");
        assure.setQualite("qualite");
        assure.setTelephoneBureau("telephoneBureau");
        assure.setTelephoneDomicile("telephoneDomicile");
        assure.setTelephoneMobile("telephoneMobile");
        return assure;
    }

    /**
     * méthode initialisant et retournant des CoordonneesIntermediaires
     * 
     * @param nom
     * @param prenom
     * @return CoordonneesIntermediaires
     */
    private CoordonneesIntermediaires initCoordonneesIntermediaires(String nom, String prenom) {
        InformationIntermediaire information = initInformationIntermediaire(nom, prenom);
        CoordonneesIntermediaires coordonnees = new CoordonneesIntermediaires(information);
        return coordonnees;
    }

    /**
     * méthode initialisant et retournant des InformationIntermediaire
     * 
     * @param nom
     * @param prenom
     * @return InformationIntermediaire
     */
    private InformationIntermediaire initInformationIntermediaire(String nom, String prenom) {
        Representant representant = new Representant();
        representant.setNom(nom);
        representant.setPrenom(prenom);

        Intermediaire intermediaire = new Intermediaire();
        intermediaire.setLibelle("libelle");

        Bureau bureau = new Bureau();
        bureau.setEmail("email");
        bureau.setFax("fax");
        bureau.setTelephone("telephone");
        bureau.setLigne2("ligne2");
        bureau.setLigne3("ligne3");
        bureau.setLigne4("ligne4");
        bureau.setLigne5("ligne5");
        bureau.setLigne6("ligne6");

        InformationIntermediaire information = new InformationIntermediaire();
        information.setRepresentant(representant);
        information.setBureau(bureau);
        information.setIntermediaire(intermediaire);

        return information;
    }

}
