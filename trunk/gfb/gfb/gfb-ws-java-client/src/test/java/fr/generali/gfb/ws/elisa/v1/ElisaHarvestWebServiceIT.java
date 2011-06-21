/**
 * Generali Solutions d'assurances - Tous droits réservés &copy; 2007 - 2010
 */
package fr.generali.gfb.ws.elisa.v1;

import static org.junit.Assert.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import fr.generali.gvie.elisa.framework.ws.fault.ElisaWSAuthException;
import fr.generali.gvie.elisa.framework.ws.fault.ElisaWSHypothesesException;
import fr.generali.gvie.elisa.framework.ws.fault.ElisaWSTechnicalException;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoClient;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoHypotheses;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoInformationComplementaireReponse;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoPeriode;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoProfession;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoResultats;
import fr.generali.socle.exception.ws.WSTechnicalException;

/**
 */
public class ElisaHarvestWebServiceIT {

    private static ApplicationContext appCtx;

    /**
     * Objet que l'on teste.
     */
    private static IElisaHarvestWebService elisaHarvestWebService;

    @BeforeClass
    public static void initAppCtx() {
        appCtx =
                        new ClassPathXmlApplicationContext(new String[] {"/applicationContext-test.xml",
                                        "/fr/generali/gfb/ws/applicationContext-ws-client.xml" });
        elisaHarvestWebService =
                        (IElisaHarvestWebService ) appCtx
                                        .getBean("fr.generali.gfb.ws.elisa.v1.IElisaHarvestWebService");
    }

    @Test
    public void testRecuperationListeProfessions() throws WSTechnicalException {
        try {
            List<DtoRoProfession> resultats = elisaHarvestWebService.recupererListeProfessions("servicero", "passwd");
            assertNotNull(resultats);
        } catch (ElisaWSAuthException e) {
            junit.framework.Assert.fail();
            e.printStackTrace();
        } catch (ElisaWSHypothesesException e) {
            junit.framework.Assert.fail();
            e.printStackTrace();
        } catch (ElisaWSTechnicalException e) {
            junit.framework.Assert.fail();
            e.printStackTrace();
        } catch (Throwable e) {
            junit.framework.Assert.fail();
            System.out.println("erreur service web tariferRetraiteObligatoire()" + e);
        }
    }

    @Test
    public void testExpertComptable() {
        // Hypothèses
        DtoRoHypotheses wsRoHypotheses = new DtoRoHypotheses();
        wsRoHypotheses.setDegradSurRetraiteAAcquerir(1.5);
        wsRoHypotheses.setDegradSurRetraiteAcquise(1.5);

        // Client
        DtoRoClient wsRoClient = new DtoRoClient();
        //wsRoClient.setAgeRetraite(65);
        wsRoClient.setDateNaissance(new GregorianCalendar(1960, GregorianCalendar.JANUARY, 1));
        wsRoClient.setSalaire(50000d);

        // Périodes
        List<DtoRoPeriode> wsRoPeriodes = new ArrayList<DtoRoPeriode>();

        // Période 1
        DtoRoPeriode periode1 = new DtoRoPeriode();
        periode1.setDateDebut(new GregorianCalendar(1980, GregorianCalendar.JANUARY, 1));
        periode1.setDateFin(new GregorianCalendar(2006, GregorianCalendar.JANUARY, 1));
        periode1.setCodeProfession("P_EXPCMPT");
        periode1.setJusquaLaRetraite(false);
        periode1.setRevenuAnnuelDebut(40000);
        periode1.setRevenuAnnuelFin(80000);

        List<DtoRoInformationComplementaireReponse> listeInfosCompP1 = new ArrayList<DtoRoInformationComplementaireReponse>();

        DtoRoInformationComplementaireReponse infosCompP1 = new DtoRoInformationComplementaireReponse();
        infosCompP1.setTypeQuestion("LISTE");
        infosCompP1.setId("REV_100PC"); // Réversion des droits à 100 %
        infosCompP1.setValeur(1.0); // 1 = OUI, 0 = NON
        listeInfosCompP1.add(infosCompP1);

        periode1.setInformationComplementaire(listeInfosCompP1);

        wsRoPeriodes.add(periode1);

        wsRoHypotheses.setDtoRoClient(wsRoClient);
        wsRoHypotheses.setPeriodes(wsRoPeriodes);

        try {
            DtoRoResultats resultats =
                            elisaHarvestWebService.tariferRetraiteObligatoire(wsRoHypotheses, "servicero", "passwd");
            assertNotNull(resultats);
            Assert.assertEquals(0, resultats.getNombreEnfantCharge());
            Assert.assertEquals(0, resultats.getNombreEnfantEleve());
            Assert.assertEquals(2025, resultats.getAnneeDepart());
            Assert.assertEquals(20825, (int ) resultats.getRetraiteTotal());
            Assert.assertEquals(5594, (int ) resultats.getRetraiteBase());
            Assert.assertEquals("AGE", resultats.getOptionDepart());

            SimpleDateFormat sdfAnnee = new SimpleDateFormat("dd-MM-yyyy");
            String dateDepart = sdfAnnee.format(resultats.getDateDepart().getTime());
            Assert.assertEquals("01-01-2025", dateDepart);

            Assert.assertEquals(15, (int ) (resultats.getTauxDegradationPondere() * 1000));
            Assert.assertEquals(65, resultats.getAgeDepart());
            Assert.assertEquals(18774, (int ) resultats.getReversion());
            Assert.assertEquals(80000, (int ) resultats.getDernierRevenu());
        } catch (ElisaWSTechnicalException e) {
            junit.framework.Assert.fail();
        } catch (ElisaWSHypothesesException e) {
            junit.framework.Assert.fail();
        } catch (ElisaWSAuthException e) {
            junit.framework.Assert.fail();
        }
    }

    @Test
    public void testGerantCommercant() {
        // Hypothèses
        DtoRoHypotheses wsRoHypotheses = new DtoRoHypotheses();
        wsRoHypotheses.setDegradSurRetraiteAAcquerir(1.5);
        wsRoHypotheses.setDegradSurRetraiteAcquise(1.5);

        // Client
        DtoRoClient wsRoClient = new DtoRoClient();
        //wsRoClient.setAgeRetraite(65);
        wsRoClient.setDateNaissance(new GregorianCalendar(1960, GregorianCalendar.JANUARY, 1));
        wsRoClient.setSalaire(50000d);

        // Périodes
        List<DtoRoPeriode> wsRoPeriodes = new ArrayList<DtoRoPeriode>();

        // Période 1
        DtoRoPeriode periode1 = new DtoRoPeriode();
        periode1.setDateDebut(new GregorianCalendar(1980, GregorianCalendar.JANUARY, 1));
        periode1.setDateFin(new GregorianCalendar(2006, GregorianCalendar.JANUARY, 1));
        periode1.setCodeProfession("P_GERANT_COMMERCANT");
        periode1.setJusquaLaRetraite(true);
        periode1.setRevenuAnnuelDebut(40000);
        periode1.setRevenuAnnuelFin(80000);

        // Pas d'infos complémentaires
        periode1.setInformationComplementaire(new ArrayList<DtoRoInformationComplementaireReponse>());

        wsRoPeriodes.add(periode1);

        wsRoHypotheses.setDtoRoClient(wsRoClient);
        wsRoHypotheses.setPeriodes(wsRoPeriodes);

        try {
            DtoRoResultats resultats =
                            elisaHarvestWebService.tariferRetraiteObligatoire(wsRoHypotheses, "servicero", "passwd");
            assertNotNull(resultats);
            Assert.assertEquals(0, resultats.getNombreEnfantCharge());
            Assert.assertEquals(0, resultats.getNombreEnfantEleve());
            Assert.assertEquals(2025, resultats.getAnneeDepart());
            Assert.assertEquals(22786, (int ) resultats.getRetraiteTotal());
            Assert.assertEquals(20077, (int ) resultats.getRetraiteBase());
            Assert.assertEquals("AGE", resultats.getOptionDepart());

            SimpleDateFormat sdfAnnee = new SimpleDateFormat("dd-MM-yyyy");
            String dateDepart = sdfAnnee.format(resultats.getDateDepart().getTime());
            Assert.assertEquals("01-01-2025", dateDepart);

            Assert.assertEquals(15, (int ) (resultats.getTauxDegradationPondere() * 1000));
            Assert.assertEquals(65, resultats.getAgeDepart());
            Assert.assertEquals(12711, (int ) resultats.getReversion());
            Assert.assertEquals(80000, (int ) resultats.getDernierRevenu());
        } catch (ElisaWSTechnicalException e) {
            junit.framework.Assert.fail();
        } catch (ElisaWSHypothesesException e) {
            junit.framework.Assert.fail();
        } catch (ElisaWSAuthException e) {
            junit.framework.Assert.fail();
        }
    }

    @Test
    public void testAvocat() {
        // Hypothèses
        DtoRoHypotheses wsRoHypotheses = new DtoRoHypotheses();
        wsRoHypotheses.setDegradSurRetraiteAAcquerir(1.5);
        wsRoHypotheses.setDegradSurRetraiteAcquise(1.5);

        // Client
        DtoRoClient wsRoClient = new DtoRoClient();
        //wsRoClient.setAgeRetraite(65);
        wsRoClient.setDateNaissance(new GregorianCalendar(1960, GregorianCalendar.JANUARY, 1));
        wsRoClient.setSalaire(50000d);

        // Périodes
        List<DtoRoPeriode> wsRoPeriodes = new ArrayList<DtoRoPeriode>();

        // Période 1
        DtoRoPeriode periode1 = new DtoRoPeriode();
        periode1.setDateDebut(new GregorianCalendar(1980, GregorianCalendar.JANUARY, 1));
        periode1.setDateFin(new GregorianCalendar(2006, GregorianCalendar.JANUARY, 1));
        periode1.setCodeProfession("P_AVOCAT");
        periode1.setJusquaLaRetraite(false);
        periode1.setRevenuAnnuelDebut(40000);
        periode1.setRevenuAnnuelFin(80000);

        // Pas d'infos complémentaires
        periode1.setInformationComplementaire(new ArrayList<DtoRoInformationComplementaireReponse>());

        wsRoPeriodes.add(periode1);

        wsRoHypotheses.setDtoRoClient(wsRoClient);
        wsRoHypotheses.setPeriodes(wsRoPeriodes);

        try {
            DtoRoResultats resultats =
                            elisaHarvestWebService.tariferRetraiteObligatoire(wsRoHypotheses, "servicero", "passwd");
            assertNotNull(resultats);
            Assert.assertEquals(0, resultats.getNombreEnfantCharge());
            Assert.assertEquals(0, resultats.getNombreEnfantEleve());
            Assert.assertEquals(2025, resultats.getAnneeDepart());
            Assert.assertEquals(17407, (int ) resultats.getRetraiteTotal());
            Assert.assertEquals(10120, (int ) resultats.getRetraiteBase());
            Assert.assertEquals("AGE", resultats.getOptionDepart());

            SimpleDateFormat sdfAnnee = new SimpleDateFormat("dd-MM-yyyy");
            String dateDepart = sdfAnnee.format(resultats.getDateDepart().getTime());
            Assert.assertEquals("01-01-2025", dateDepart);

            Assert.assertEquals(15, (int ) (resultats.getTauxDegradationPondere() * 1000));
            Assert.assertEquals(65, resultats.getAgeDepart());
            Assert.assertEquals(9637, (int ) resultats.getReversion());
            Assert.assertEquals(80000, (int ) resultats.getDernierRevenu());
        } catch (ElisaWSTechnicalException e) {
            junit.framework.Assert.fail();
        } catch (ElisaWSHypothesesException e) {
            junit.framework.Assert.fail();
        } catch (ElisaWSAuthException e) {
            junit.framework.Assert.fail();
        }
    }

    @Test
    public void testMedecin() {

        // Hypothèses
        DtoRoHypotheses wsRoHypotheses = new DtoRoHypotheses();
        wsRoHypotheses.setDegradSurRetraiteAAcquerir(1.5);
        wsRoHypotheses.setDegradSurRetraiteAcquise(1.5);

        // Client
        DtoRoClient wsRoClient = new DtoRoClient();
        //wsRoClient.setAgeRetraite(65);
        wsRoClient.setDateNaissance(new GregorianCalendar(1958, GregorianCalendar.JANUARY, 10));
        wsRoClient.setSalaire(50000d);

        // Périodes
        List<DtoRoPeriode> wsRoPeriodes = new ArrayList<DtoRoPeriode>();

        // Période 1
        DtoRoPeriode periode1 = new DtoRoPeriode();
        periode1.setDateDebut(new GregorianCalendar(1985, GregorianCalendar.JANUARY, 1));
        periode1.setDateFin(new GregorianCalendar(2006, GregorianCalendar.JANUARY, 1));
        periode1.setCodeProfession("P_MEDECIN");
        periode1.setJusquaLaRetraite(false);
        periode1.setRevenuAnnuelDebut(40000);
        periode1.setRevenuAnnuelFin(80000);

        List<DtoRoInformationComplementaireReponse> listeInfosCompP1 = new ArrayList<DtoRoInformationComplementaireReponse>();

        DtoRoInformationComplementaireReponse infosCompP1 = new DtoRoInformationComplementaireReponse();
        infosCompP1.setTypeQuestion("LISTE");
        infosCompP1.setId("CONVENTIONNE"); // Conventionné(e) - Adhésion au
        // régime spécial des Avantages
        // Sociaux Vieillesse
        infosCompP1.setValeur(1.0); // 1 = OUI, 0 = NON
        listeInfosCompP1.add(infosCompP1);

        periode1.setInformationComplementaire(listeInfosCompP1);

        wsRoPeriodes.add(periode1);

        wsRoHypotheses.setDtoRoClient(wsRoClient);
        wsRoHypotheses.setPeriodes(wsRoPeriodes);

        try {
            DtoRoResultats resultats =
                            elisaHarvestWebService.tariferRetraiteObligatoire(wsRoHypotheses, "servicero", "passwd");
            assertNotNull(resultats);
            Assert.assertEquals(0, resultats.getNombreEnfantCharge());
            Assert.assertEquals(0, resultats.getNombreEnfantEleve());
            Assert.assertEquals(2023, resultats.getAnneeDepart());
            Assert.assertEquals(20636, (int ) resultats.getRetraiteTotal());
            Assert.assertEquals(4539, (int ) resultats.getRetraiteBase());
            Assert.assertEquals("AGE", resultats.getOptionDepart());

            SimpleDateFormat sdfAnnee = new SimpleDateFormat("dd-MM-yyyy");
            String dateDepart = sdfAnnee.format(resultats.getDateDepart().getTime());
            Assert.assertEquals("01-02-2023", dateDepart);

            Assert.assertEquals(15, (int ) (resultats.getTauxDegradationPondere() * 1000));
            Assert.assertEquals(65, resultats.getAgeDepart());
            Assert.assertEquals(11388, (int ) resultats.getReversion());
            Assert.assertEquals(80000, (int ) resultats.getDernierRevenu());
        } catch (ElisaWSTechnicalException e) {
            junit.framework.Assert.fail();
        } catch (ElisaWSHypothesesException e) {
            junit.framework.Assert.fail();
        } catch (ElisaWSAuthException e) {
            junit.framework.Assert.fail();
        }
    }

    public void testChomage() { // TODO

    }

    @Test
    public void testFonctionnaire() {

        // Hypothèses
        DtoRoHypotheses wsRoHypotheses = new DtoRoHypotheses();
        wsRoHypotheses.setDegradSurRetraiteAAcquerir(1.5);
        wsRoHypotheses.setDegradSurRetraiteAcquise(1.5);

        // Client
        DtoRoClient wsRoClient = new DtoRoClient();
        //wsRoClient.setAgeRetraite(65);
        wsRoClient.setDateNaissance(new GregorianCalendar(1960, GregorianCalendar.JANUARY, 1));

        // Périodes
        List<DtoRoPeriode> wsRoPeriodes = new ArrayList<DtoRoPeriode>();

        // Période 1
        DtoRoPeriode periode1 = new DtoRoPeriode();
        periode1.setDateDebut(new GregorianCalendar(1980, GregorianCalendar.JANUARY, 1));
        periode1.setDateFin(new GregorianCalendar(2006, GregorianCalendar.JANUARY, 1));
        periode1.setCodeProfession("P_NONCADRE");
        periode1.setJusquaLaRetraite(false);
        periode1.setRevenuAnnuelDebut(40000);
        periode1.setRevenuAnnuelFin(80000);

        List<DtoRoInformationComplementaireReponse> listeInfosCompP1 = new ArrayList<DtoRoInformationComplementaireReponse>();

        DtoRoInformationComplementaireReponse infosCompP1Act = new DtoRoInformationComplementaireReponse();
        infosCompP1Act.setTypeQuestion("LISTE");
        infosCompP1Act.setId("ACTIVITE"); // Temps partiel pour raison familiale
        infosCompP1Act.setValeur(0.0); // 1 = OUI, 0 = NON
        listeInfosCompP1.add(infosCompP1Act);

        DtoRoInformationComplementaireReponse infosCompP1TxAct = new DtoRoInformationComplementaireReponse();
        infosCompP1TxAct.setTypeQuestion("TAUX");
        infosCompP1TxAct.setId("TX_ACT"); // Taux d'activité
        infosCompP1TxAct.setValeur(0.6); // 1 = OUI, 0 = NON
        listeInfosCompP1.add(infosCompP1TxAct);

        DtoRoInformationComplementaireReponse infosCompP1Type = new DtoRoInformationComplementaireReponse();
        infosCompP1Type.setTypeQuestion("LISTE");
        infosCompP1Type.setId("TYPE"); // Type de cotisant
        infosCompP1Type.setValeur(1); // 0 = A, 1 = B, 2 =C
        listeInfosCompP1.add(infosCompP1Type);

        DtoRoInformationComplementaireReponse infosCompP1Ic = new DtoRoInformationComplementaireReponse();
        infosCompP1Ic.setTypeQuestion("NUM");
        infosCompP1Ic.setId("INDICE"); // Indice de traitement majoré de fin de
        // période
        infosCompP1Ic.setValeur(200); // > 100
        listeInfosCompP1.add(infosCompP1Ic);

        DtoRoInformationComplementaireReponse infosCompP1Bonif = new DtoRoInformationComplementaireReponse();
        infosCompP1Bonif.setTypeQuestion("NUM");
        infosCompP1Bonif.setId("BONIF"); // Taux d'activité
        infosCompP1Bonif.setValeur(3); // > 0
        listeInfosCompP1.add(infosCompP1Bonif);

        periode1.setInformationComplementaire(listeInfosCompP1);

        wsRoPeriodes.add(periode1);

        wsRoHypotheses.setDtoRoClient(wsRoClient);
        wsRoHypotheses.setPeriodes(wsRoPeriodes);

        try {
            DtoRoResultats resultats =
                            elisaHarvestWebService.tariferRetraiteObligatoire(wsRoHypotheses, "servicero", "passwd");
            assertNotNull(resultats);
            Assert.assertEquals(0, resultats.getNombreEnfantCharge());
            Assert.assertEquals(0, resultats.getNombreEnfantEleve());
            Assert.assertEquals(2025, resultats.getAnneeDepart());
            Assert.assertEquals(10460, (int ) resultats.getRetraiteTotal());
            Assert.assertEquals(5324, (int ) resultats.getRetraiteBase());
            Assert.assertEquals("AGE", resultats.getOptionDepart());

            SimpleDateFormat sdfAnnee = new SimpleDateFormat("dd-MM-yyyy");
            String dateDepart = sdfAnnee.format(resultats.getDateDepart().getTime());
            Assert.assertEquals("01-01-2025", dateDepart);

            Assert.assertEquals(15, (int ) (resultats.getTauxDegradationPondere() * 1000));
            Assert.assertEquals(65, resultats.getAgeDepart());
            Assert.assertEquals(6021, (int ) resultats.getReversion());
            Assert.assertEquals(61520, (int ) resultats.getDernierRevenu());
        } catch (ElisaWSTechnicalException e) {
            junit.framework.Assert.fail();
        } catch (ElisaWSHypothesesException e) {
            junit.framework.Assert.fail();
        } catch (ElisaWSAuthException e) {
            junit.framework.Assert.fail();
        }
    }

    @Test
    public void testCadre() {

        // Hypothèses
        DtoRoHypotheses wsRoHypotheses = new DtoRoHypotheses();
        wsRoHypotheses.setDegradSurRetraiteAAcquerir(1.5);
        wsRoHypotheses.setDegradSurRetraiteAcquise(1.5);

        // Client
        DtoRoClient wsRoClient = new DtoRoClient();
        //wsRoClient.setAgeRetraite(65);
        wsRoClient.setDateNaissance(new GregorianCalendar(1950, GregorianCalendar.JANUARY, 1));

        // Périodes
        List<DtoRoPeriode> wsRoPeriodes = new ArrayList<DtoRoPeriode>();

        // Période 1
        DtoRoPeriode periode1 = new DtoRoPeriode();
        periode1.setDateDebut(new GregorianCalendar(1980, GregorianCalendar.JANUARY, 1));
        periode1.setDateFin(new GregorianCalendar(2009, GregorianCalendar.JANUARY, 1));
        periode1.setCodeProfession("P_CADRE");
        periode1.setJusquaLaRetraite(true);
        periode1.setRevenuAnnuelDebut(15000);
        periode1.setRevenuAnnuelFin(40000);
        periode1.setAgeCharniere(65);
        periode1.setProgressionAvantAgeCharniere(1);
        periode1.setProgressionApresAgeCharniere(1);

        List<DtoRoInformationComplementaireReponse> listeInfosCompP1 = new ArrayList<DtoRoInformationComplementaireReponse>();

        DtoRoInformationComplementaireReponse infosCompP1 = new DtoRoInformationComplementaireReponse();
        infosCompP1.setTypeQuestion("TAUX");
        infosCompP1.setId("TX_ACT"); // Taux d'activité
        infosCompP1.setValeur(1); // 0 < Tx < 1
        listeInfosCompP1.add(infosCompP1);

        periode1.setInformationComplementaire(listeInfosCompP1);

        wsRoPeriodes.add(periode1);

        wsRoHypotheses.setDtoRoClient(wsRoClient);
        wsRoHypotheses.setPeriodes(wsRoPeriodes);

        try {
            DtoRoResultats resultats =
                            elisaHarvestWebService.tariferRetraiteObligatoire(wsRoHypotheses, "servicero", "passwd");
            assertNotNull(resultats);
            Assert.assertEquals(0, resultats.getNombreEnfantCharge());
            Assert.assertEquals(0, resultats.getNombreEnfantEleve());
            Assert.assertEquals(2015, resultats.getAnneeDepart());
            Assert.assertEquals(21425, (int ) resultats.getRetraiteTotal());
            Assert.assertEquals(13539, (int ) resultats.getRetraiteBase());
            Assert.assertEquals("AGE", resultats.getOptionDepart());

            SimpleDateFormat sdfAnnee = new SimpleDateFormat("dd-MM-yyyy");
            String dateDepart = sdfAnnee.format(resultats.getDateDepart().getTime());
            Assert.assertEquals("01-01-2015", dateDepart);

            Assert.assertEquals(15, (int ) (resultats.getTauxDegradationPondere() * 1000));
            Assert.assertEquals(65, resultats.getAgeDepart());
            Assert.assertEquals(12101, (int ) resultats.getReversion());
            Assert.assertEquals(32425, (int ) resultats.getDernierRevenu());
        } catch (ElisaWSTechnicalException e) {
            junit.framework.Assert.fail();
        } catch (ElisaWSHypothesesException e) {
            junit.framework.Assert.fail();
        } catch (ElisaWSAuthException e) {
            junit.framework.Assert.fail();
        }
    }

    @Test
    public void testNonCadre() {

        // Hypothèses
        DtoRoHypotheses wsRoHypotheses = new DtoRoHypotheses();
        wsRoHypotheses.setDegradSurRetraiteAAcquerir(1.5);
        wsRoHypotheses.setDegradSurRetraiteAcquise(1.5);

        // Client
        DtoRoClient wsRoClient = new DtoRoClient();
        //wsRoClient.setAgeRetraite(65);
        wsRoClient.setDateNaissance(new GregorianCalendar(1990, GregorianCalendar.JANUARY, 1));

        // Périodes
        List<DtoRoPeriode> wsRoPeriodes = new ArrayList<DtoRoPeriode>();

        // Période 1
        DtoRoPeriode periode1 = new DtoRoPeriode();
        periode1.setDateDebut(new GregorianCalendar(2009, GregorianCalendar.JANUARY, 1));
        periode1.setDateFin(new GregorianCalendar(2009, GregorianCalendar.DECEMBER, 1));
        periode1.setCodeProfession("P_NONCADRE");
        periode1.setJusquaLaRetraite(true);
        periode1.setRevenuAnnuelDebut(25632);
        periode1.setRevenuAnnuelFin(25632);
        periode1.setAgeCharniere(65);
        periode1.setProgressionAvantAgeCharniere(3);
        periode1.setProgressionApresAgeCharniere(3);

        List<DtoRoInformationComplementaireReponse> listeInfosCompP1 = new ArrayList<DtoRoInformationComplementaireReponse>();

        DtoRoInformationComplementaireReponse infosCompP1 = new DtoRoInformationComplementaireReponse();
        infosCompP1.setTypeQuestion("TAUX");
        infosCompP1.setId("TX_ACT"); // Taux d'activité
        infosCompP1.setValeur(1); // 0 < Tx < 1
        listeInfosCompP1.add(infosCompP1);

        periode1.setInformationComplementaire(listeInfosCompP1);

        wsRoPeriodes.add(periode1);

        wsRoHypotheses.setDtoRoClient(wsRoClient);
        wsRoHypotheses.setPeriodes(wsRoPeriodes);

        try {
            DtoRoResultats resultats =
                            elisaHarvestWebService.tariferRetraiteObligatoire(wsRoHypotheses, "servicero", "passwd");
            assertNotNull(resultats);
            Assert.assertEquals(0, resultats.getNombreEnfantCharge());
            Assert.assertEquals(0, resultats.getNombreEnfantEleve());
            Assert.assertEquals(2055, resultats.getAnneeDepart());
            Assert.assertEquals(20626, (int ) resultats.getRetraiteTotal());
            Assert.assertEquals(21442, (int ) resultats.getRetraiteBase());
            Assert.assertEquals("AGE", resultats.getOptionDepart());

            SimpleDateFormat sdfAnnee = new SimpleDateFormat("dd-MM-yyyy");
            String dateDepart = sdfAnnee.format(resultats.getDateDepart().getTime());
            Assert.assertEquals("01-01-2055", dateDepart);

            Assert.assertEquals(15, (int ) (resultats.getTauxDegradationPondere() * 1000));
            Assert.assertEquals(65, resultats.getAgeDepart());
            Assert.assertEquals(11724, (int ) resultats.getReversion());
            Assert.assertEquals(72368, (int ) resultats.getDernierRevenu());
        } catch (ElisaWSTechnicalException e) {
            junit.framework.Assert.fail();
        } catch (ElisaWSHypothesesException e) {
            junit.framework.Assert.fail();
        } catch (ElisaWSAuthException e) {
            junit.framework.Assert.fail();
        }
    }

    @Test
    public void testHurricaneCarter() {

        // Hypothèses
        DtoRoHypotheses wsRoHypotheses = new DtoRoHypotheses();
        wsRoHypotheses.setDegradSurRetraiteAAcquerir(1.5);
        wsRoHypotheses.setDegradSurRetraiteAcquise(1.5);

        // Client
        DtoRoClient wsRoClient = new DtoRoClient();
        //wsRoClient.setAgeRetraite(65);
        wsRoClient.setDateNaissance(new GregorianCalendar(1975, GregorianCalendar.JANUARY, 1));

        // Périodes
        List<DtoRoPeriode> wsRoPeriodes = new ArrayList<DtoRoPeriode>();

        // Période 1
        DtoRoPeriode periode1 = new DtoRoPeriode();
        periode1.setDateDebut(new GregorianCalendar(2000, GregorianCalendar.JANUARY, 1));
        periode1.setDateFin(new GregorianCalendar(2009, GregorianCalendar.JANUARY, 1));
        periode1.setCodeProfession("P_CADRE");
        periode1.setJusquaLaRetraite(true);
        periode1.setRevenuAnnuelDebut(50000);
        periode1.setRevenuAnnuelFin(80000);
        periode1.setAgeCharniere(65);
        periode1.setProgressionAvantAgeCharniere(2);
        periode1.setProgressionApresAgeCharniere(2);

        List<DtoRoInformationComplementaireReponse> listeInfosCompP1 = new ArrayList<DtoRoInformationComplementaireReponse>();

        DtoRoInformationComplementaireReponse infosCompP1 = new DtoRoInformationComplementaireReponse();
        infosCompP1.setTypeQuestion("TAUX");
        infosCompP1.setId("TX_ACT"); // Taux d'activité
        infosCompP1.setValeur(1); // 0 < Tx < 1
        listeInfosCompP1.add(infosCompP1);

        periode1.setInformationComplementaire(listeInfosCompP1);

        wsRoPeriodes.add(periode1);

        wsRoHypotheses.setDtoRoClient(wsRoClient);
        wsRoHypotheses.setPeriodes(wsRoPeriodes);

        try {
            DtoRoResultats resultats =
                            elisaHarvestWebService.tariferRetraiteObligatoire(wsRoHypotheses, "servicero", "passwd");
            assertNotNull(resultats);
            Assert.assertEquals(0, resultats.getNombreEnfantCharge());
            Assert.assertEquals(0, resultats.getNombreEnfantEleve());
            Assert.assertEquals(2040, resultats.getAnneeDepart());
            Assert.assertEquals(37479, (int ) resultats.getRetraiteTotal());
            Assert.assertEquals(16735, (int ) resultats.getRetraiteBase());
            Assert.assertEquals("AGE", resultats.getOptionDepart());

            SimpleDateFormat sdfAnnee = new SimpleDateFormat("dd-MM-yyyy");
            String dateDepart = sdfAnnee.format(resultats.getDateDepart().getTime());
            Assert.assertEquals("01-01-2040", dateDepart);

            Assert.assertEquals(15, (int ) (resultats.getTauxDegradationPondere() * 1000));
            Assert.assertEquals(65, resultats.getAgeDepart());
            Assert.assertEquals(21849, (int ) resultats.getReversion());
            Assert.assertEquals(110670, (int ) resultats.getDernierRevenu());
        } catch (ElisaWSTechnicalException e) {
            junit.framework.Assert.fail();
        } catch (ElisaWSHypothesesException e) {
            junit.framework.Assert.fail();
        } catch (ElisaWSAuthException e) {
            junit.framework.Assert.fail();
        }
    }

    @Test
    public void testBillyElliott() {

        // Hypothèses
        DtoRoHypotheses wsRoHypotheses = new DtoRoHypotheses();
        wsRoHypotheses.setDegradSurRetraiteAAcquerir(1.5);
        wsRoHypotheses.setDegradSurRetraiteAcquise(1.5);

        // Client
        DtoRoClient wsRoClient = new DtoRoClient();
        //wsRoClient.setAgeRetraite(65);
        wsRoClient.setDateNaissance(new GregorianCalendar(1990, GregorianCalendar.JANUARY, 1));

        // Périodes
        List<DtoRoPeriode> wsRoPeriodes = new ArrayList<DtoRoPeriode>();

        // Période 1
        DtoRoPeriode periode1 = new DtoRoPeriode();
        periode1.setDateDebut(new GregorianCalendar(2009, GregorianCalendar.JANUARY, 1));
        periode1.setDateFin(new GregorianCalendar(2009, GregorianCalendar.DECEMBER, 1));
        periode1.setCodeProfession("P_NONCADRE");
        periode1.setJusquaLaRetraite(true);
        periode1.setRevenuAnnuelDebut(25632);
        periode1.setRevenuAnnuelFin(25632);
        periode1.setAgeCharniere(65);
        periode1.setProgressionAvantAgeCharniere(3);
        periode1.setProgressionApresAgeCharniere(3);

        List<DtoRoInformationComplementaireReponse> listeInfosCompP1 = new ArrayList<DtoRoInformationComplementaireReponse>();

        DtoRoInformationComplementaireReponse infosCompP1 = new DtoRoInformationComplementaireReponse();
        infosCompP1.setTypeQuestion("TAUX");
        infosCompP1.setId("TX_ACT"); // Taux d'activité
        infosCompP1.setValeur(1); // 0 < Tx < 1
        listeInfosCompP1.add(infosCompP1);

        periode1.setInformationComplementaire(listeInfosCompP1);

        wsRoPeriodes.add(periode1);

        wsRoHypotheses.setDtoRoClient(wsRoClient);
        wsRoHypotheses.setPeriodes(wsRoPeriodes);

        try {
            DtoRoResultats resultats =
                            elisaHarvestWebService.tariferRetraiteObligatoire(wsRoHypotheses, "servicero", "passwd");
            assertNotNull(resultats);
            Assert.assertEquals(0, resultats.getNombreEnfantCharge());
            Assert.assertEquals(0, resultats.getNombreEnfantEleve());
            Assert.assertEquals(2055, resultats.getAnneeDepart());
            Assert.assertEquals(20626, (int ) resultats.getRetraiteTotal());
            Assert.assertEquals(21442, (int ) resultats.getRetraiteBase());
            Assert.assertEquals("AGE", resultats.getOptionDepart());

            SimpleDateFormat sdfAnnee = new SimpleDateFormat("dd-MM-yyyy");
            String dateDepart = sdfAnnee.format(resultats.getDateDepart().getTime());
            Assert.assertEquals("01-01-2055", dateDepart);

            Assert.assertEquals(15, (int ) (resultats.getTauxDegradationPondere() * 1000));
            Assert.assertEquals(65, resultats.getAgeDepart());
            Assert.assertEquals(11724, (int ) resultats.getReversion());
            Assert.assertEquals(72368, (int ) resultats.getDernierRevenu());
        } catch (ElisaWSTechnicalException e) {
            junit.framework.Assert.fail();
        } catch (ElisaWSHypothesesException e) {
            junit.framework.Assert.fail();
        } catch (ElisaWSAuthException e) {
            junit.framework.Assert.fail();
        }
    }

    @Test
    public void testJeanDeFlorette() {
        // Hypothèses
        DtoRoHypotheses wsRoHypotheses = new DtoRoHypotheses();
        wsRoHypotheses.setDegradSurRetraiteAAcquerir(1.5);
        wsRoHypotheses.setDegradSurRetraiteAcquise(1.5);

        // Client
        DtoRoClient wsRoClient = new DtoRoClient();
        //wsRoClient.setAgeRetraite(65);
        wsRoClient.setDateNaissance(new GregorianCalendar(1956, GregorianCalendar.JANUARY, 1));

        // Périodes
        List<DtoRoPeriode> wsRoPeriodes = new ArrayList<DtoRoPeriode>();

        // Période 1
        DtoRoPeriode periode1 = new DtoRoPeriode();
        periode1.setDateDebut(new GregorianCalendar(1976, GregorianCalendar.JANUARY, 1));
        periode1.setDateFin(new GregorianCalendar(2009, GregorianCalendar.JANUARY, 1));
        periode1.setCodeProfession("P_AVOCAT");
        periode1.setJusquaLaRetraite(true);
        periode1.setRevenuAnnuelDebut(150046);
        periode1.setRevenuAnnuelFin(150046);
        periode1.setAgeCharniere(65);
        periode1.setProgressionAvantAgeCharniere(4);
        periode1.setProgressionApresAgeCharniere(4);

        // Pas d'infos complémentaires
        periode1.setInformationComplementaire(new ArrayList<DtoRoInformationComplementaireReponse>());

        wsRoPeriodes.add(periode1);

        wsRoHypotheses.setDtoRoClient(wsRoClient);
        wsRoHypotheses.setPeriodes(wsRoPeriodes);

        try {
            DtoRoResultats resultats =
                            elisaHarvestWebService.tariferRetraiteObligatoire(wsRoHypotheses, "servicero", "passwd");
            assertNotNull(resultats);
            Assert.assertEquals(0, resultats.getNombreEnfantCharge());
            Assert.assertEquals(0, resultats.getNombreEnfantEleve());
            Assert.assertEquals(2021, resultats.getAnneeDepart());
            Assert.assertEquals(48194, (int ) resultats.getRetraiteTotal());
            Assert.assertEquals(18718, (int ) resultats.getRetraiteBase());
            Assert.assertEquals("AGE", resultats.getOptionDepart());

            SimpleDateFormat sdfAnnee = new SimpleDateFormat("dd-MM-yyyy");
            String dateDepart = sdfAnnee.format(resultats.getDateDepart().getTime());
            Assert.assertEquals("01-01-2021", dateDepart);

            Assert.assertEquals(15, (int ) (resultats.getTauxDegradationPondere() * 1000));
            Assert.assertEquals(65, resultats.getAgeDepart());
            Assert.assertEquals(27331, (int ) resultats.getReversion());
            Assert.assertEquals(222104, (int ) resultats.getDernierRevenu());
        } catch (ElisaWSTechnicalException e) {
            junit.framework.Assert.fail();
        } catch (ElisaWSHypothesesException e) {
            junit.framework.Assert.fail();
        } catch (ElisaWSAuthException e) {
            junit.framework.Assert.fail();
        }
    }

    @Test
    public void testErinBrokovich() {
        // Hypothèses
        DtoRoHypotheses wsRoHypotheses = new DtoRoHypotheses();
        wsRoHypotheses.setDegradSurRetraiteAAcquerir(1.5);
        wsRoHypotheses.setDegradSurRetraiteAcquise(1.5);

        // Client
        DtoRoClient wsRoClient = new DtoRoClient();
        //wsRoClient.setAgeRetraite(65);
        wsRoClient.setDateNaissance(new GregorianCalendar(1947, GregorianCalendar.JANUARY, 1));

        // Périodes
        List<DtoRoPeriode> wsRoPeriodes = new ArrayList<DtoRoPeriode>();

        // Période 1
        DtoRoPeriode periode1 = new DtoRoPeriode();
        periode1.setDateDebut(new GregorianCalendar(1975, GregorianCalendar.JANUARY, 1));
        periode1.setDateFin(new GregorianCalendar(2009, GregorianCalendar.JANUARY, 1));
        periode1.setCodeProfession("P_EXPCMPT");
        periode1.setJusquaLaRetraite(true);
        periode1.setRevenuAnnuelDebut(50000);
        periode1.setRevenuAnnuelFin(365000);
        periode1.setAgeCharniere(65);
        periode1.setProgressionAvantAgeCharniere(1);
        periode1.setProgressionApresAgeCharniere(0);

        List<DtoRoInformationComplementaireReponse> listeInfosCompP1 = new ArrayList<DtoRoInformationComplementaireReponse>();

        DtoRoInformationComplementaireReponse infosCompP1 = new DtoRoInformationComplementaireReponse();
        infosCompP1.setTypeQuestion("LISTE");
        infosCompP1.setId("REV_100PC"); // Réversion des droits à 100 %
        infosCompP1.setValeur(1.0); // 1 = OUI, 0 = NON
        listeInfosCompP1.add(infosCompP1);

        periode1.setInformationComplementaire(listeInfosCompP1);

        wsRoPeriodes.add(periode1);

        wsRoHypotheses.setDtoRoClient(wsRoClient);
        wsRoHypotheses.setPeriodes(wsRoPeriodes);

        try {
            DtoRoResultats resultats =
                            elisaHarvestWebService.tariferRetraiteObligatoire(wsRoHypotheses, "servicero", "passwd");
            assertNotNull(resultats);
            Assert.assertEquals(0, resultats.getNombreEnfantCharge());
            Assert.assertEquals(0, resultats.getNombreEnfantEleve());
            Assert.assertEquals(2012, resultats.getAnneeDepart());
            Assert.assertEquals(44995, (int ) resultats.getRetraiteTotal());
            Assert.assertEquals(8435, (int ) resultats.getRetraiteBase());
            Assert.assertEquals("AGE", resultats.getOptionDepart());

            SimpleDateFormat sdfAnnee = new SimpleDateFormat("dd-MM-yyyy");
            String dateDepart = sdfAnnee.format(resultats.getDateDepart().getTime());
            Assert.assertEquals("01-01-2012", dateDepart);

            Assert.assertEquals(15, (int ) (resultats.getTauxDegradationPondere() * 1000));
            Assert.assertEquals(65, resultats.getAgeDepart());
            Assert.assertEquals(22825, (int ) resultats.getReversion());
            Assert.assertEquals(368649, (int ) resultats.getDernierRevenu());
        } catch (ElisaWSTechnicalException e) {
            junit.framework.Assert.fail();
        } catch (ElisaWSHypothesesException e) {
            junit.framework.Assert.fail();
        } catch (ElisaWSAuthException e) {
            junit.framework.Assert.fail();
        }
    }

    @Test
    public void testEdWood() {

        // Hypothèses
        DtoRoHypotheses wsRoHypotheses = new DtoRoHypotheses();
        wsRoHypotheses.setDegradSurRetraiteAAcquerir(1.5);
        wsRoHypotheses.setDegradSurRetraiteAcquise(1.5);

        // Client
        DtoRoClient wsRoClient = new DtoRoClient();
        //wsRoClient.setAgeRetraite(65);
        wsRoClient.setDateNaissance(new GregorianCalendar(1972, GregorianCalendar.JANUARY, 10));

        // Périodes
        List<DtoRoPeriode> wsRoPeriodes = new ArrayList<DtoRoPeriode>();

        // Période 1
        DtoRoPeriode periode1 = new DtoRoPeriode();
        periode1.setDateDebut(new GregorianCalendar(1987, GregorianCalendar.JANUARY, 1));
        periode1.setDateFin(new GregorianCalendar(2009, GregorianCalendar.JANUARY, 1));
        periode1.setCodeProfession("P_MEDECIN");
        periode1.setJusquaLaRetraite(true);
        periode1.setRevenuAnnuelDebut(22000);
        periode1.setRevenuAnnuelFin(58000);
        periode1.setAgeCharniere(65);
        periode1.setProgressionAvantAgeCharniere(1);
        periode1.setProgressionApresAgeCharniere(1);

        List<DtoRoInformationComplementaireReponse> listeInfosCompP1 = new ArrayList<DtoRoInformationComplementaireReponse>();

        DtoRoInformationComplementaireReponse infosCompP1 = new DtoRoInformationComplementaireReponse();
        infosCompP1.setTypeQuestion("LISTE");
        infosCompP1.setId("CONVENTIONNE"); // Conventionné(e) - Adhésion au
        // régime spécial des Avantages
        // Sociaux Vieillesse
        infosCompP1.setValeur(1.0); // 1 = OUI, 0 = NON
        listeInfosCompP1.add(infosCompP1);

        periode1.setInformationComplementaire(listeInfosCompP1);

        wsRoPeriodes.add(periode1);

        wsRoHypotheses.setDtoRoClient(wsRoClient);
        wsRoHypotheses.setPeriodes(wsRoPeriodes);

        try {
            DtoRoResultats resultats =
                            elisaHarvestWebService.tariferRetraiteObligatoire(wsRoHypotheses, "servicero", "passwd");
            assertNotNull(resultats);
            Assert.assertEquals(0, resultats.getNombreEnfantCharge());
            Assert.assertEquals(0, resultats.getNombreEnfantEleve());
            Assert.assertEquals(2037, resultats.getAnneeDepart());
            Assert.assertEquals(36634, (int ) resultats.getRetraiteTotal());
            Assert.assertEquals(13592, (int ) resultats.getRetraiteBase());
            Assert.assertEquals("AGE", resultats.getOptionDepart());

            SimpleDateFormat sdfAnnee = new SimpleDateFormat("dd-MM-yyyy");
            String dateDepart = sdfAnnee.format(resultats.getDateDepart().getTime());
            Assert.assertEquals("01-02-2037", dateDepart);

            Assert.assertEquals(15, (int ) (resultats.getTauxDegradationPondere() * 1000));
            Assert.assertEquals(65, resultats.getAgeDepart());
            Assert.assertEquals(20013, (int ) resultats.getReversion());
            Assert.assertEquals(75876, (int ) resultats.getDernierRevenu());
        } catch (ElisaWSTechnicalException e) {
            junit.framework.Assert.fail();
        } catch (ElisaWSHypothesesException e) {
            junit.framework.Assert.fail();
        } catch (ElisaWSAuthException e) {
            junit.framework.Assert.fail();
        }
    }

    @Test
    public void testForrestGump() {

        // Hypothèses
        DtoRoHypotheses wsRoHypotheses = new DtoRoHypotheses();
        wsRoHypotheses.setDegradSurRetraiteAAcquerir(1.5);
        wsRoHypotheses.setDegradSurRetraiteAcquise(1.5);

        // Client
        DtoRoClient wsRoClient = new DtoRoClient();
        //wsRoClient.setAgeRetraite(65);
        wsRoClient.setDateNaissance(new GregorianCalendar(1980, GregorianCalendar.JANUARY, 1));

        // Périodes
        List<DtoRoPeriode> wsRoPeriodes = new ArrayList<DtoRoPeriode>();

        // Période 1
        DtoRoPeriode periode1 = new DtoRoPeriode();
        periode1.setDateDebut(new GregorianCalendar(2001, GregorianCalendar.JANUARY, 1));
        periode1.setDateFin(new GregorianCalendar(2010, GregorianCalendar.JANUARY, 1));
        periode1.setCodeProfession("P_FONCT");
        periode1.setJusquaLaRetraite(true);
        periode1.setRevenuAnnuelDebut(19000);
        periode1.setRevenuAnnuelFin(27500);
        periode1.setAgeCharniere(65);
        periode1.setProgressionAvantAgeCharniere(2);
        periode1.setProgressionApresAgeCharniere(2);

        List<DtoRoInformationComplementaireReponse> listeInfosCompP1 = new ArrayList<DtoRoInformationComplementaireReponse>();

        DtoRoInformationComplementaireReponse infosCompP1Act = new DtoRoInformationComplementaireReponse();
        infosCompP1Act.setTypeQuestion("LISTE");
        infosCompP1Act.setId("ACTIVITE"); // Temps partiel pour raison familiale
        infosCompP1Act.setValeur(1); // 1 = OUI, 0 = NON
        listeInfosCompP1.add(infosCompP1Act);

        DtoRoInformationComplementaireReponse infosCompP1TxAct = new DtoRoInformationComplementaireReponse();
        infosCompP1TxAct.setTypeQuestion("TAUX");
        infosCompP1TxAct.setId("TX_ACT"); // Taux d'activité
        infosCompP1TxAct.setValeur(1);
        listeInfosCompP1.add(infosCompP1TxAct);

        DtoRoInformationComplementaireReponse infosCompP1Type = new DtoRoInformationComplementaireReponse();
        infosCompP1Type.setTypeQuestion("LISTE");
        infosCompP1Type.setId("TYPE"); // Type de cotisant
        infosCompP1Type.setValeur(0); // 0 = A, 1 = B, 2 =C
        listeInfosCompP1.add(infosCompP1Type);

        DtoRoInformationComplementaireReponse infosCompP1Ic = new DtoRoInformationComplementaireReponse();
        infosCompP1Ic.setTypeQuestion("NUM");
        infosCompP1Ic.setId("INDICE"); // Indice de traitement majoré de fin de
        // période
        infosCompP1Ic.setValeur(100); // > 100
        listeInfosCompP1.add(infosCompP1Ic);

        DtoRoInformationComplementaireReponse infosCompP1Bonif = new DtoRoInformationComplementaireReponse();
        infosCompP1Bonif.setTypeQuestion("NUM");
        infosCompP1Bonif.setId("BONIF"); // Nombre de trimestres de bonification
        infosCompP1Bonif.setValeur(3); // > 0
        listeInfosCompP1.add(infosCompP1Bonif);

        periode1.setInformationComplementaire(listeInfosCompP1);

        wsRoPeriodes.add(periode1);

        wsRoHypotheses.setDtoRoClient(wsRoClient);
        wsRoHypotheses.setPeriodes(wsRoPeriodes);

        try {
            DtoRoResultats resultats =
                            elisaHarvestWebService.tariferRetraiteObligatoire(wsRoHypotheses, "servicero", "passwd");
            assertNotNull(resultats);
            Assert.assertEquals(0, resultats.getNombreEnfantCharge());
            Assert.assertEquals(0, resultats.getNombreEnfantEleve());
            Assert.assertEquals(2045, resultats.getAnneeDepart());
            Assert.assertEquals(28812, (int ) resultats.getRetraiteTotal());
            Assert.assertEquals(48899, (int ) resultats.getRetraiteBase());
            Assert.assertEquals("AGE", resultats.getOptionDepart());

            SimpleDateFormat sdfAnnee = new SimpleDateFormat("dd-MM-yyyy");
            String dateDepart = sdfAnnee.format(resultats.getDateDepart().getTime());
            Assert.assertEquals("01-01-2045", dateDepart);

            Assert.assertEquals(15, (int ) (resultats.getTauxDegradationPondere() * 1000));
            Assert.assertEquals(65, resultats.getAgeDepart());
            Assert.assertEquals(14406, (int ) resultats.getReversion());
            Assert.assertEquals(43296, (int ) resultats.getDernierRevenu());
        } catch (ElisaWSTechnicalException e) {
            junit.framework.Assert.fail();
        } catch (ElisaWSHypothesesException e) {
            junit.framework.Assert.fail();
        } catch (ElisaWSAuthException e) {
            junit.framework.Assert.fail();
        }
    }

    @Test
    public void testGilbertGrape() {
        // Hypothèses
        DtoRoHypotheses wsRoHypotheses = new DtoRoHypotheses();
        wsRoHypotheses.setDegradSurRetraiteAAcquerir(1.5);
        wsRoHypotheses.setDegradSurRetraiteAcquise(1.5);

        // Client
        DtoRoClient wsRoClient = new DtoRoClient();
        //wsRoClient.setAgeRetraite(65);
        wsRoClient.setDateNaissance(new GregorianCalendar(1970, GregorianCalendar.DECEMBER, 22));

        // Périodes
        List<DtoRoPeriode> wsRoPeriodes = new ArrayList<DtoRoPeriode>();

        // ----- Période 1 -----
        DtoRoPeriode periode1 = new DtoRoPeriode();
        periode1.setDateDebut(new GregorianCalendar(1995, GregorianCalendar.JANUARY, 1));
        periode1.setDateFin(new GregorianCalendar(1998, GregorianCalendar.JANUARY, 1));
        periode1.setCodeProfession("P_NONCADRE");
        periode1.setJusquaLaRetraite(false);
        periode1.setRevenuAnnuelDebut(20000);
        periode1.setRevenuAnnuelFin(40000);
        periode1.setAgeCharniere(65);
        periode1.setProgressionAvantAgeCharniere(2);
        periode1.setProgressionApresAgeCharniere(2);
        
        List<DtoRoInformationComplementaireReponse> listeInfosCompP1 = new ArrayList<DtoRoInformationComplementaireReponse>();

        DtoRoInformationComplementaireReponse infosCompP1 = new DtoRoInformationComplementaireReponse();
        infosCompP1.setTypeQuestion("TAUX");
        infosCompP1.setId("TX_ACT"); // Taux d'activité
        infosCompP1.setValeur(1); // 0 < Tx < 1
        listeInfosCompP1.add(infosCompP1);

        periode1.setInformationComplementaire(listeInfosCompP1);

        wsRoPeriodes.add(periode1);

        // ----- Période 2 -----
        DtoRoPeriode periode2 = new DtoRoPeriode();
        periode2.setDateDebut(new GregorianCalendar(1998, GregorianCalendar.JANUARY, 1));
        periode2.setDateFin(new GregorianCalendar(2010, GregorianCalendar.JANUARY, 1));
        periode2.setCodeProfession("P_EXPCMPT");
        periode2.setJusquaLaRetraite(true);
        periode2.setRevenuAnnuelDebut(58000);
        periode2.setRevenuAnnuelFin(73500);
        periode2.setAgeCharniere(65);
        periode2.setProgressionAvantAgeCharniere(2);
        periode2.setProgressionApresAgeCharniere(2);

        List<DtoRoInformationComplementaireReponse> listeInfosCompP2 = new ArrayList<DtoRoInformationComplementaireReponse>();

        DtoRoInformationComplementaireReponse infosCompP2 = new DtoRoInformationComplementaireReponse();
        infosCompP2.setTypeQuestion("LISTE");
        infosCompP2.setId("REV_100PC"); // Réversion des droits à 100 %
        infosCompP2.setValeur(1.0); // 1 = OUI, 0 = NON
        listeInfosCompP2.add(infosCompP2);

        periode2.setInformationComplementaire(listeInfosCompP2);

        wsRoPeriodes.add(periode2);

        wsRoHypotheses.setDtoRoClient(wsRoClient);
        wsRoHypotheses.setPeriodes(wsRoPeriodes);

        try {
            DtoRoResultats resultats =
                            elisaHarvestWebService.tariferRetraiteObligatoire(wsRoHypotheses, "servicero", "passwd");
            assertNotNull(resultats);
            Assert.assertEquals(0, resultats.getNombreEnfantCharge());
            Assert.assertEquals(0, resultats.getNombreEnfantEleve());
            Assert.assertEquals(2036, resultats.getAnneeDepart());
            Assert.assertEquals(32789, (int) resultats.getRetraiteTotal());
            Assert.assertEquals(10618, (int) resultats.getRetraiteBase());
            Assert.assertEquals("AGE", resultats.getOptionDepart());

            SimpleDateFormat sdfAnnee = new SimpleDateFormat("dd-MM-yyyy");
            String dateDepart = sdfAnnee.format(resultats.getDateDepart().getTime());
            Assert.assertEquals("01-01-2036", dateDepart);

            Assert.assertEquals(15, (int) (resultats.getTauxDegradationPondere() * 1000));
            Assert.assertEquals(65, resultats.getAgeDepart());
            Assert.assertEquals(16712, (int) resultats.getReversion());
            Assert.assertEquals(120584, (int) resultats.getDernierRevenu());
        } catch (ElisaWSTechnicalException e) {
            junit.framework.Assert.fail();
        } catch (ElisaWSHypothesesException e) {
            junit.framework.Assert.fail();
        } catch (ElisaWSAuthException e) {
            junit.framework.Assert.fail();
        }
    }
    
    @Test
    public void testRichardAnconina() {

        // Hypothèses
        DtoRoHypotheses wsRoHypotheses = new DtoRoHypotheses();
        wsRoHypotheses.setDegradSurRetraiteAAcquerir(1.5);
        wsRoHypotheses.setDegradSurRetraiteAcquise(1.5);

        // Client
        DtoRoClient wsRoClient = new DtoRoClient();
        //wsRoClient.setAgeRetraite(65);
        wsRoClient.setDateNaissance(new GregorianCalendar(1953, GregorianCalendar.JANUARY, 1));
        wsRoClient.setSalaire(38000);

        // Périodes
        List<DtoRoPeriode> wsRoPeriodes = new ArrayList<DtoRoPeriode>();
        
        // ----- Période 1 -----
        DtoRoPeriode periode1 = new DtoRoPeriode();
        periode1.setDateDebut(new GregorianCalendar(1971, GregorianCalendar.JANUARY, 1));
        periode1.setDateFin(new GregorianCalendar(1975, GregorianCalendar.JANUARY, 1));
        periode1.setCodeProfession("P_CHOMAGE");
        periode1.setJusquaLaRetraite(true);
        
        // ----- Période 2 -----
        DtoRoPeriode periode2 = new DtoRoPeriode();
        periode2.setDateDebut(new GregorianCalendar(1975, GregorianCalendar.JANUARY, 1));
        periode2.setDateFin(new GregorianCalendar(2010, GregorianCalendar.JANUARY, 1));
        periode2.setCodeProfession("P_CADRE");
        periode2.setJusquaLaRetraite(true);
        periode2.setRevenuAnnuelDebut(15320);
        periode2.setRevenuAnnuelFin(38000);
        periode2.setAgeCharniere(45);
        periode2.setProgressionAvantAgeCharniere(4);
        periode2.setProgressionApresAgeCharniere(4);

        List<DtoRoInformationComplementaireReponse> listeInfosCompP2 = new ArrayList<DtoRoInformationComplementaireReponse>();

        DtoRoInformationComplementaireReponse infosCompP2 = new DtoRoInformationComplementaireReponse();
        infosCompP2.setTypeQuestion("TAUX");
        infosCompP2.setId("TX_ACT"); // Taux d'activité
        infosCompP2.setValeur(1); // 0 < Tx < 1
        listeInfosCompP2.add(infosCompP2);

        periode2.setInformationComplementaire(listeInfosCompP2);

        wsRoPeriodes.add(periode2);

        wsRoHypotheses.setDtoRoClient(wsRoClient);
        wsRoHypotheses.setPeriodes(wsRoPeriodes);

        try {
            DtoRoResultats resultats =
                            elisaHarvestWebService.tariferRetraiteObligatoire(wsRoHypotheses, "servicero", "passwd");
            assertNotNull(resultats);
            Assert.assertEquals(0, resultats.getNombreEnfantCharge());
            Assert.assertEquals(0, resultats.getNombreEnfantEleve());
            Assert.assertEquals(2018, resultats.getAnneeDepart());
            Assert.assertEquals(29775, (int) resultats.getRetraiteTotal());
            Assert.assertEquals(17694, (int) resultats.getRetraiteBase());
            Assert.assertEquals("AGE", resultats.getOptionDepart());

            SimpleDateFormat sdfAnnee = new SimpleDateFormat("dd-MM-yyyy");
            String dateDepart = sdfAnnee.format(resultats.getDateDepart().getTime());
            Assert.assertEquals("01-01-2018", dateDepart);

            Assert.assertEquals(15, (int) (resultats.getTauxDegradationPondere() * 1000));
            Assert.assertEquals(65, resultats.getAgeDepart());
            Assert.assertEquals(16924, (int) resultats.getReversion());
            Assert.assertEquals(38954, (int) resultats.getDernierRevenu());
        } catch (ElisaWSTechnicalException e) {
            junit.framework.Assert.fail();
        } catch (ElisaWSHypothesesException e) {
            junit.framework.Assert.fail();
        } catch (ElisaWSAuthException e) {
            junit.framework.Assert.fail();
        }
    }
}
