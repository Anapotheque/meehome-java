/**
 * Generali Solutions d'assurances - Tous droits réservés &copy; 2007 - 2010
 */
 package fr.generali.gfb.services.elisa;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static org.junit.Assert.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.unitils.UnitilsJUnit4;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.CalendarConverter;

import fr.generali.gvie.elisa.framework.ws.fault.ElisaWSAuthException;
import fr.generali.gvie.elisa.framework.ws.fault.ElisaWSHypothesesException;
import fr.generali.gvie.elisa.framework.ws.fault.ElisaWSTechnicalException;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoClient;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoDernierePeriode;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoHypotheses;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoInformationComplementaire;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoInformationComplementaireReponse;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoPeriode;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoProfession;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoQuestionsComplementaires;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoReponsesItem;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoResultats;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoRevenu;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:fr/generali/gfb/services/applicationContext-services-test.xml",
                "classpath:fr/generali/gfb/services/applicationContext-services.xml"})
public class ElisaHarvestServiceTestIT extends UnitilsJUnit4 {

    private static String[] listeCodeProfessionsSimulateur = new String [] {
        "P_ARCHI",
        "P_ARTISAN",
        "P_AUXMED",
        "P_AVOCAT",
        "P_CHIRDEN", 
        "P_EXPCMPT", 
        "P_GERANT_COMMERCANT",
        "P_INACTSS",
        "P_MEDECIN",
        "P_NOTAIRE",
        "P_CADRE",
        "P_NONCADRE"};
    
    private static String ELISA_WS_LOGIN = "servicero";
    private static String ELISA_WS_PASSWORD = "passwd";
    
    private Object itemToPrintAfterTest = null;

    private static final XStream XSTREAM = new XStream();

    @Before
    public void preTest() {
        System.out.println("\n --- ========== ---");
    }

    /**
     * Affiche le retour des appels des WS ELISA dans la log du test d'intégration au format XML via XStream. 
     * Cela permet de parcourir rapidement le contenu du résultat.
     */
    @After
    public void printOutResult() {
        LOGGER.info(" --- DTO RETOUR ---");
        LOGGER.info(XSTREAM.toXML(itemToPrintAfterTest));
    }

    @BeforeClass
    public static void initAppCtx() {
        XSTREAM.alias("DtoRoClient", DtoRoClient.class);
        XSTREAM.alias("DtoRoPeriode", DtoRoPeriode.class);
        XSTREAM.alias("DtoRoResultats", DtoRoResultats.class);
        XSTREAM.alias("DtoRoHypotheses", DtoRoHypotheses.class);
        XSTREAM.alias("DtoRoDernierePeriode", DtoRoDernierePeriode.class);
        XSTREAM.alias("DtoRoInformationComplementaire", DtoRoInformationComplementaire.class);
        XSTREAM.alias("DtoRoProfession", DtoRoProfession.class);
        XSTREAM.alias("DtoRoInformationComplementaireReponse", DtoRoInformationComplementaireReponse.class);
        XSTREAM.alias("DtoRoReponsesItem", DtoRoReponsesItem.class); 
        XSTREAM.alias("DtoRoQuestionsComplementaires", DtoRoQuestionsComplementaires.class); 
        XSTREAM.alias("DtoRoRevenu", DtoRoRevenu.class); 
        
        XSTREAM.registerConverter(new CalendarConverter(new SimpleDateFormat("dd/MM/yyyy")));
 
    }
    
    /**
     * CSP testées Avocat (P_AVOCAT) Expert comptable (P_EXPCMPT) Gérant
     * majoritaire de société commerciale (P_GERANT_COMMERCANT) Médecin
     * (P_MEDECIN) Inactif (P_CHOMAGE) Fonctionnaire (P_FONCT) Salarié cadre
     * (P_CADRE) Salarié non cadre (P_NONCADRE)
     */

    /**
     * Logger
     */
    private static Logger LOGGER = LoggerFactory.getLogger(ElisaHarvestServiceTestIT.class);

    @Autowired
    private IElisaHarvestService elisaHarvestService;

    @Test
    public void testRecuperationListeProfessions() {

        try {
            final List<DtoRoProfession> resultats = elisaHarvestService.recupererListeProfessions(ELISA_WS_LOGIN, ELISA_WS_PASSWORD);
            this.itemToPrintAfterTest = resultats;
            assertNotNull(resultats);
        } catch (Exception e) {
            fail();
            LOGGER.error("erreur service web recupererListeProfessions()", e);
        }
    }
    

    @Test
    public void testRecupererInformationsComplementaires() {
        for (String codeProfession : listeCodeProfessionsSimulateur) {
            final DtoRoClient dtoRoClient = TestUtils.newDtoRoClient(65, "1978-05-30", "M", 43000D, 2);
            final DtoRoDernierePeriode dtoRoDernierePeriode = TestUtils.newDtoRoDernierePeriode(codeProfession, "2001-10-10", true);
            LOGGER.info(" --- DTOS APPEL --- codeProfession = " + codeProfession + " ---");
            LOGGER.info(XSTREAM.toXML(dtoRoClient));
            LOGGER.info(XSTREAM.toXML(dtoRoDernierePeriode));
            
            try {
                
                final List<DtoRoInformationComplementaire> resultats = elisaHarvestService.recupererInformationsComplementaires(dtoRoClient, dtoRoDernierePeriode, ELISA_WS_LOGIN, ELISA_WS_PASSWORD);
                this.itemToPrintAfterTest = resultats;
                LOGGER.info(" --- DTO RETOUR --- codeProfession = " + codeProfession + " ---");
                LOGGER.info(XSTREAM.toXML(itemToPrintAfterTest));
                assertNotNull(resultats);
                //assertEquals(2, resultats.size());
                
            } catch (Exception e) {
                LOGGER.error("erreur service web recupererInformationsComplementaires()", e);
                fail(e.getMessage());
            }
        }
        this.itemToPrintAfterTest = null;
    }
    
    @Test
    public void testExpertComptable() {
        tariferEtVerifierLeResultat(TestUtils.newDtoRoHypotheses(Arrays.asList(
                        TestUtils.newDtoRoPeriode("P_EXPCMPT", "1980-01-01", "2006-01-01", false, 40000d, 80000d,
                                        // Réversion des droits à 100 %; 1 = OUI, 0 = NON
                                        Arrays.asList(TestUtils.newRepInfoComp(null, "LISTE", 1d, "REV_100PC")))), 
                                        TestUtils.newDtoRoClient(65, "1960-01-01", "M", 50000D, 0)),
                        TestUtils.newDtoRoResultats(65, 2025, "2025-01-01", 80000d, 0, 0, "AGE", 5594d, 20825d, 18774d, 0.015d));
    }

    @Test
    public void testMedecin() {
        tariferEtVerifierLeResultat(TestUtils.newDtoRoHypotheses(Arrays.asList(
                        TestUtils.newDtoRoPeriode("P_MEDECIN", "1985-01-01", "2006-01-01", false, 40000d, 80000d, 
                                        // Conventionné(e) - Adhésion au régime spécial des Avantages Sociaux Vieillesse; 1 = OUI, 0 = NON
                                        Arrays.asList(TestUtils.newRepInfoComp(null, "LISTE", 1d, "CONVENTIONNE")))
                        ), TestUtils.newDtoRoClient(65, "1958-01-10", "M", 50000d, 0)),
                        TestUtils.newDtoRoResultats(65, 2023, "2023-02-01", 80000d, 0, 0, "AGE", 4539d, 20636d, 11388d, 0.015d));
    }

    public void testChomage() { 
        // TODO
    }

    public void testFonctionnaire() {
        tariferEtVerifierLeResultat(TestUtils.newDtoRoHypotheses(Arrays.asList(
                        TestUtils.newDtoRoPeriode("P_NONCADRE", "1980-01-01", "2006-01-01", false, 40000d, 80000d, Arrays.asList(
                                        // Temps partiel pour raison familiale; 1 = OUI, 0 = NON
                                           TestUtils.newRepInfoComp(null, "LISTE", 0d, "ACTIVITE"),
                                        // Taux d'activité,  1 = OUI, 0 = NON
                                           TestUtils.newRepInfoComp(null, "TAUX", 0.6, "TX_ACT"),
                                        // Type de cotisant; 0 = A, 1 = B, 2 =C
                                           TestUtils.newRepInfoComp(null, "LISTE", 1d, "TYPE"),
                                        // Indice de traitement majoré de fin de période; > 100
                                           TestUtils.newRepInfoComp(null, "NUM", 200d, "INDICE"),
                                        // Taux d'activité, > 0
                                           TestUtils.newRepInfoComp(null, "NUM", 3d, "BONIF")
                                   ))
                            ), TestUtils.newDtoRoClient(65, "1960-01-01", "M", 50000d, 0)),
                        TestUtils.newDtoRoResultats(65, 2025, "2025-01-01", 61520d, 0, 0, "AGE", 5324d, 10303d, 5931d, 0.015d));
    }

    @Test
    public void testCadre() {
        tariferEtVerifierLeResultat(TestUtils.newDtoRoHypotheses(Arrays.asList(
                        TestUtils.newDtoRoPeriode("P_CADRE", "1980-01-01", "2006-01-01", false, 40000d, 80000d,
                                        Arrays.asList(TestUtils.newRepInfoComp(null, "TAUX", 0.6, "TX_ACT")))), 
                                        TestUtils.newDtoRoClient(65, "1960-01-01", "M", 50000d, 0)),
                        TestUtils.newDtoRoResultats(65, 2025, "2025-01-01", 62319d, 0, 0, "AGE", 5324d, 19517d, 11455d, 0.015d));
    }

    @Test
    public void testSalarieARRCO() {
        tariferEtVerifierLeResultat(TestUtils.newDtoRoHypotheses(Arrays.asList(
                        TestUtils.newDtoRoPeriode("P_CADRE", "1973-01-01", "2010-06-18", false, 10000d, 25000d, Arrays.asList(
                                            TestUtils.newRepInfoComp(null, "TAUX", 0.0, "TX_TA"),
                                            TestUtils.newRepInfoComp(null, "TAUX", 0.0, "TX_TB"),
                                            TestUtils.newRepInfoComp(null, "TAUX", 0.0, "TX_TC"),
                                            TestUtils.newRepInfoComp(null, "TAUX", 1.0, "TX_ACT"),
                                            TestUtils.newRepInfoComp("ARRCO", "TAUX", 0.06, "TX_TA"),
                                            TestUtils.newRepInfoComp("AGIRC", "TAUX", 0.1624, "TX_TB"),
                                            TestUtils.newRepInfoComp("AGIRCTC", "TAUX", 0.1624, "TX_TC")
                                            ))), 
                                        TestUtils.newDtoRoClient(65, "1953-01-01", "M", 25000d, 0)),
                        TestUtils.newDtoRoResultats(65, 2025, "2025-01-01", 62319d, 0, 0, "AGE", 5324d, 19517d, 11455d, 0.015d));
    }

    @Test
    public void testNonCadre() {
        tariferEtVerifierLeResultat(TestUtils.newDtoRoHypotheses(Arrays.asList(
                        TestUtils.newDtoRoPeriode("P_NONCADRE", "1980-01-01", "2006-01-01", false, 40000d, 80000d, 
                                        Arrays.asList(TestUtils.newRepInfoComp(null, "TAUX", 0.6, "TX_ACT")))), 
                                        TestUtils.newDtoRoClient(65, "1960-01-01", "M", 50000d, 0)),
                        TestUtils.newDtoRoResultats(65, 2025, "2025-01-01", 61520d, 0, 0, "AGE", 5324d, 10460d, 6021d, 0.015d));
    }
    
    
    /**
     * @param wsRoHypotheses
     */
    private void tariferEtVerifierLeResultat(DtoRoHypotheses wsRoHypotheses, DtoRoResultats expectedRestultat) {
        LOGGER.info("\n --- DTOS APPEL ---");
        LOGGER.info(XSTREAM.toXML(wsRoHypotheses));
        
        try {
            final DtoRoResultats resultats =
                            elisaHarvestService.tariferRetraiteObligatoire(wsRoHypotheses, ELISA_WS_LOGIN, ELISA_WS_PASSWORD);
            this.itemToPrintAfterTest = resultats;
            assertNotNull(resultats);
            
            assertEquals(expectedRestultat.getNombreEnfantCharge(), resultats.getNombreEnfantCharge());
            assertEquals(expectedRestultat.getNombreEnfantEleve(), resultats.getNombreEnfantEleve());
            assertEquals(expectedRestultat.getAnneeDepart(), resultats.getAnneeDepart());
            assertEquals(Math.floor(expectedRestultat.getRetraiteTotal()), Math.floor(resultats.getRetraiteTotal()));
            assertEquals(Math.floor(expectedRestultat.getRetraiteBase()), Math.floor(resultats.getRetraiteBase()));
            assertEquals(expectedRestultat.getOptionDepart(), resultats.getOptionDepart());

            final SimpleDateFormat sdfAnnee = new SimpleDateFormat("dd-MM-yyyy");
            if (expectedRestultat.getDateDepart() != null) {
                assertEquals(sdfAnnee.format(expectedRestultat.getDateDepart().getTime()), sdfAnnee.format(resultats.getDateDepart().getTime()));
            }

            assertEquals(Math.floor(1000*expectedRestultat.getTauxDegradationPondere()), Math.floor(1000*resultats.getTauxDegradationPondere()));
            assertEquals(expectedRestultat.getAgeDepart(), resultats.getAgeDepart());
            assertEquals(Math.floor(expectedRestultat.getReversion()), Math.floor(resultats.getReversion()));
            assertEquals(Math.floor(expectedRestultat.getDernierRevenu()), Math.floor(resultats.getDernierRevenu()));
            

        } catch (ElisaWSAuthException e) {
            LOGGER.error("Erreur à l'authentification : " + e.getMessage(), e);
            fail(e.getMessage());

        } catch (ElisaWSHypothesesException e) {
            LOGGER.error("Erreur à la validation des hypothèses : " + e.getMessage(), e);
            fail(e.getMessage());

        } catch (ElisaWSTechnicalException e) {
            LOGGER.error("Erreur technique : " + e.getMessage() , e);
            fail(e.getMessage());

        } catch (Exception e) {
            LOGGER.error("erreur service web tariferRetraiteObligatoire()", e);
            fail(e.getMessage());
        }
    }

    public void testTariferRetraiteObligatoire() {

        try {
            final DtoRoHypotheses wsRoHypotheses = TestUtils.newDtoRoHypotheses(Arrays.asList(
                            TestUtils.newDtoRoPeriode("P_AGASSUR", "2001-06-21", "2003-01-30", false, 28000d, 29000d, 
                                            Arrays.asList(TestUtils.newRepInfoComp(null, "NUM", 29500d, "REVENU_FIN"))),
                                            TestUtils.newDtoRoPeriode("P_AUXMED", "2003-02-01", "2005-01-30", false, 34000d, 37000d, 
                                            Arrays.asList(TestUtils.newRepInfoComp(null, "LISTE", 1d, "CONVENTIONNE"))),
                                            TestUtils.newDtoRoPeriode(45, "P_EXPCMPT_SALARIE", "2005-03-01", null, true, 1d, 1.2d, 34000d, 15000d, 
                                            Arrays.asList(
                                                            TestUtils.newRepInfoComp(null, "TAUX", 1d, "TX_ACT"), 
                                                            TestUtils.newRepInfoComp(null, "LISTE", 0d, "CADRE"),
                                                            TestUtils.newRepInfoComp(null, "LISTE", 0d, "REV_100PC"))
                                            )),
                                            TestUtils.newDtoRoClient(65, "1977-09-10", "M", 15000d, 0));

            final DtoRoResultats resultats =
                            elisaHarvestService.tariferRetraiteObligatoire(wsRoHypotheses, ELISA_WS_LOGIN, ELISA_WS_PASSWORD);
            this.itemToPrintAfterTest = resultats;

            assertNotNull(resultats);

        } catch (ElisaWSAuthException e) {
            LOGGER.error("Erreur à l'authentification : " + e.getMessage(), e);
            fail(e.getMessage());

        } catch (ElisaWSHypothesesException e) {
            LOGGER.error("Erreur à la validation des hypothèses : " + e.getMessage(), e);
            fail(e.getMessage());

        } catch (ElisaWSTechnicalException e) {
            LOGGER.error("Erreur technique : " + e.getMessage() , e);
            fail(e.getMessage());

        } catch (Exception e) {
            LOGGER.error("erreur service web tariferRetraiteObligatoire()", e);
            fail(e.getMessage());
        }
    }
}