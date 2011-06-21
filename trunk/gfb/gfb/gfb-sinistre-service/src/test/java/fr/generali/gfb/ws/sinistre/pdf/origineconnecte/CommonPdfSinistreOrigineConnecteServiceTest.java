package fr.generali.gfb.ws.sinistre.pdf.origineconnecte;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPTable;

import fr.generali.espaceclient.proxy.generic.domain.Bureau;
import fr.generali.espaceclient.proxy.generic.domain.InformationIntermediaire;
import fr.generali.espaceclient.proxy.generic.domain.Intermediaire;
import fr.generali.gfb.ws.sinistre.persistence.domain.auto.DommagesAuto;
import fr.generali.gfb.ws.sinistre.persistence.domain.auto.SinistreAuto;
import fr.generali.gfb.ws.sinistre.persistence.domain.auto.vol.SinistreAutoVol;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.Assure;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;

/**
 * @author Mickael Morier
 */

@Transactional(TransactionMode.DISABLED)
public class CommonPdfSinistreOrigineConnecteServiceTest extends UnitilsJUnit4 {

    @TestedObject
    private CommonPdfSinistreOrigineConnecteService testedObject;

    @Mock
    private Document mockPdf;

    // ///////////////

    /**
     * test la méthode initializeAutoDeclaSinistre sans DateSinistre et sans
     * Circonstances
     * 
     * @throws DocumentException
     * @throws ParseException
     */
    @Test
    public void testInitializeAutoDeclaSinistreSansDateSinistreSansCirconstances() throws DocumentException,
                    ParseException {
        DeclarationSinistre declarationSinistre = initDeclarationSinistreAuto();
        Boolean isClientInternet = Boolean.TRUE;
        InformationIntermediaire infosPortefeuille = new InformationIntermediaire();
        infosPortefeuille.setBureau(new Bureau());
        infosPortefeuille.setIntermediaire(new Intermediaire());
        String typeSinistre = "accident";

        declarationSinistre.getSinistre().setDateSinistre(null);
        ((SinistreAuto ) declarationSinistre.getSinistre()).setCirconstances(null);

        EasyMock.expect(mockPdf.add(null)).andReturn(null).atLeastOnce();
        EasyMock.expect(mockPdf.getPageSize()).andReturn(new Rectangle(2009, 4)).once();
        EasyMockUnitils.replay();

        testedObject.initializeAutoDeclaSinistre(mockPdf, declarationSinistre, isClientInternet, infosPortefeuille,
                        typeSinistre,false);
    }

    /**
     * test la méthode initializeAutoDeclaSinistre avec DateSinistre et sans
     * Circonstances
     * 
     * @throws DocumentException
     * @throws ParseException
     */
    @Test
    public void testInitializeAutoDeclaSinistreAvecDateSinistreSansCirconstances() throws DocumentException,
                    ParseException {
        DeclarationSinistre declarationSinistre = initDeclarationSinistreAuto();
        Boolean isClientInternet = Boolean.TRUE;
        InformationIntermediaire infosPortefeuille = new InformationIntermediaire();
        infosPortefeuille.setBureau(new Bureau());
        infosPortefeuille.setIntermediaire(new Intermediaire());
        String typeSinistre = "accident";

        ((SinistreAuto ) declarationSinistre.getSinistre()).setCirconstances(null);

        EasyMock.expect(mockPdf.add(null)).andReturn(null).times(11);
        EasyMock.expect(mockPdf.getPageSize()).andReturn(new Rectangle(2009, 4)).once();
        EasyMock.expect(mockPdf.add(null)).andReturn(null).times(7);
        EasyMockUnitils.replay();

        testedObject.initializeAutoDeclaSinistre(mockPdf, declarationSinistre, isClientInternet, infosPortefeuille,
                        typeSinistre,false);
    }

    /**
     * test la méthode initializeAutoDeclaSinistre avec DateSinistre et avec
     * Circonstances
     * 
     * @throws DocumentException
     * @throws ParseException
     */
    @Test
    public void testInitializeAutoDeclaSinistreAvecDateSinistreAvecCirconstances() throws DocumentException,
                    ParseException {
        DeclarationSinistre declarationSinistre = initDeclarationSinistreAuto();
        Boolean isClientInternet = Boolean.TRUE;
        InformationIntermediaire infosPortefeuille = new InformationIntermediaire();
        infosPortefeuille.setBureau(new Bureau());
        infosPortefeuille.setIntermediaire(new Intermediaire());
        String typeSinistre = "accident";

        EasyMock.expect(mockPdf.add(null)).andReturn(null).times(11);
        EasyMock.expect(mockPdf.getPageSize()).andReturn(new Rectangle(2009, 4)).once();
        EasyMock.expect(mockPdf.add(null)).andReturn(null).times(11);
        EasyMockUnitils.replay();

        testedObject.initializeAutoDeclaSinistre(mockPdf, declarationSinistre, isClientInternet, infosPortefeuille,
                        typeSinistre,false);
    }

    /**
     * test la méthode initializeAssureAuto
     * 
     * @throws NoSuchMethodException
     * @throws ParseException
     */
    @Test
    public void testInitializeAssureAuto() throws NoSuchMethodException, ParseException {
        float width = 2009.4f;
        DeclarationSinistre declaration = initDeclarationSinistreAuto();
        Assure assure = declaration.getAssure();
        String numContrat = declaration.getNumeroContrat();
        String numClient = declaration.getNumClient();
        String immatriculation = ((SinistreAuto ) declaration.getSinistre()).getImmatriculation();

        Method method =
                        CommonPdfSinistreOrigineConnecteService.class.getDeclaredMethod("initializeAssureAuto",
                                        float.class, Assure.class, String.class, String.class, String.class);
        ReflectionUtils.makeAccessible(method);
        PdfPTable actual =
                        (PdfPTable ) ReflectionUtils.invokeMethod(method, testedObject, new Object[] {width, assure,
                                        numContrat, numClient, immatriculation });

        Assert.assertNotNull(actual);
        Assert.assertEquals(width, actual.getAbsoluteWidths()[0]);
        Assert.assertEquals(2, actual.getRows().size());
    }

    /**
     * test la méthode initializeAutoDommages avec description
     * 
     * @throws DocumentException
     */
    @Test
    public void testInitializeAutoDommagesDescription() throws DocumentException {
        DommagesAuto dommages = new DommagesAuto();
        dommages.setDescription("description");

        EasyMock.expect(mockPdf.add(null)).andReturn(null).times(6);
        EasyMockUnitils.replay();

        testedObject.initializeAutoDommages(mockPdf, dommages);
    }

    /**
     * test la méthode initializeAutoDommages avec DepotGarage et sans
     * LieuVehicule
     * 
     * @throws DocumentException
     */
    @Test
    public void testInitializeAutoDommagesAvecDepotGarageSansLieuVehicule() throws DocumentException {
        DommagesAuto dommages = new DommagesAuto();
        dommages.setDepotGarage(Boolean.TRUE);

        EasyMock.expect(mockPdf.add(null)).andReturn(null).times(2);
        EasyMockUnitils.replay();

        testedObject.initializeAutoDommages(mockPdf, dommages);
    }

    /**
     * test la méthode initializeAutoDommages avec DepotGarage et LieuVehicule
     * 
     * @throws DocumentException
     */
    @Test
    public void testInitializeAutoDommagesAvecDepotGarageAvecLieuVehicule() throws DocumentException {
        DommagesAuto dommages = new DommagesAuto();
        dommages.setDepotGarage(Boolean.TRUE);
        dommages.setLieuVehicule("lieuVehicule");

        EasyMock.expect(mockPdf.add(null)).andReturn(null).times(6);
        EasyMockUnitils.replay();

        testedObject.initializeAutoDommages(mockPdf, dommages);
    }

    /**
     * test la méthode initializeAutoDommages sans DepotGarage et sans
     * CoordonnesGarage
     * 
     * @throws DocumentException
     */
    @Test
    public void testInitializeAutoDommagesSansDepotGarageSansCoordonnesGarage() throws DocumentException {
        DommagesAuto dommages = new DommagesAuto();
        dommages.setDepotGarage(Boolean.FALSE);

        EasyMock.expect(mockPdf.add(null)).andReturn(null).times(2);
        EasyMockUnitils.replay();

        testedObject.initializeAutoDommages(mockPdf, dommages);
    }

    /**
     * test la méthode initializeAutoDommages sans DepotGarage et Avec
     * CoordonnesGarage
     * 
     * @throws DocumentException
     */
    @Test
    public void testInitializeAutoDommagesSansDepotGarageAvecCoordonnesGarage() throws DocumentException {
        DommagesAuto dommages = new DommagesAuto();
        dommages.setDepotGarage(Boolean.FALSE);
        dommages.setCoordonnesGarage("coordonnesGarage");

        EasyMock.expect(mockPdf.add(null)).andReturn(null).times(6);
        EasyMockUnitils.replay();

        testedObject.initializeAutoDommages(mockPdf, dommages);
    }

    @Test
    public void pad() {
        assertEquals("01", CommonPdfSinistreOrigineConnecteService.pad0(1));
    }

    @Test
    public void isAfficherHeure() {
        SinistreAuto sinistre = new SinistreAuto().heureDebut(0).minuteDebut(0).heureFin(1).minuteFin(0);
        assertTrue(CommonPdfSinistreOrigineConnecteService.isAfficherHeure(sinistre));
        sinistre = new SinistreAuto().heureDebut(0).minuteDebut(0).heureFin(0).minuteFin(0);
        assertFalse(CommonPdfSinistreOrigineConnecteService.isAfficherHeure(sinistre));
        sinistre = new SinistreAuto().heureDebut(null).minuteDebut(0).heureFin(1).minuteFin(0);
        assertFalse(CommonPdfSinistreOrigineConnecteService.isAfficherHeure(sinistre));
        sinistre = new SinistreAuto().heureDebut(0).minuteDebut(null).heureFin(1).minuteFin(0);
        assertFalse(CommonPdfSinistreOrigineConnecteService.isAfficherHeure(sinistre));
        sinistre = new SinistreAuto().heureDebut(0).minuteDebut(0).heureFin(null).minuteFin(0);
        assertFalse(CommonPdfSinistreOrigineConnecteService.isAfficherHeure(sinistre));
        sinistre = new SinistreAuto().heureDebut(0).minuteDebut(0).heureFin(1).minuteFin(null);
        assertFalse(CommonPdfSinistreOrigineConnecteService.isAfficherHeure(sinistre));
    }

    /**
     * méthode initialisant et retournant une déclaration de sinistre Auto
     * 
     * @return DeclarationSinistre
     * @throws ParseException
     */
    private DeclarationSinistre initDeclarationSinistreAuto() throws ParseException {
        DeclarationSinistre declaration = new DeclarationSinistre();
        declaration.setNumeroContrat("0123");
        declaration.setNumClient("numClient");

        SinistreAuto sinistre = new SinistreAutoVol();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sinistre.setDateDeclaration(sdf.parse("10/04/2009"));
        sinistre.setDateSinistre(sdf.parse("10/04/2009"));
        sinistre.setImmatriculation("456 MPC 75");
        sinistre.setCirconstances("circonstances");

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

}
