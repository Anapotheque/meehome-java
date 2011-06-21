/**
 * 
 */
package fr.generali.gfb.ws.sinistre.pdf.originedeconnecte;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.util.PDFTextStripper;
import org.unitils.UnitilsJUnit4;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;
import org.unitils.inject.annotation.TestedObject;

import fr.generali.espaceclient.proxy.generic.domain.Accord;
import fr.generali.espaceclient.proxy.generic.domain.Bureau;
import fr.generali.espaceclient.proxy.generic.domain.Domaine;
import fr.generali.espaceclient.proxy.generic.domain.EtatPortefeuille;
import fr.generali.espaceclient.proxy.generic.domain.InformationIntermediaire;
import fr.generali.espaceclient.proxy.generic.domain.Inspecteur;
import fr.generali.espaceclient.proxy.generic.domain.Intermediaire;
import fr.generali.espaceclient.proxy.generic.domain.Nature;
import fr.generali.espaceclient.proxy.generic.domain.Portefeuille;
import fr.generali.espaceclient.proxy.generic.domain.Representant;
import fr.generali.gfb.ws.sinistre.SinistreObjectInitialization;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;
import fr.generali.gfb.ws.sinistre.persistence.domain.constants.TypeSinistreEnum;

/**
 * @author Aguilane ARUL
 */
@Transactional(TransactionMode.DISABLED)
public class DefaultPdfSinistreOrigineDeconnecteServicePdfTest extends UnitilsJUnit4 {

    private static final InformationIntermediaire INFOS_PORTEFEUILLE;
    static {
        INFOS_PORTEFEUILLE = new InformationIntermediaire();
        Accord accord = new Accord();
        accord.setNature(Nature.AGENT);
        Bureau bureau = new Bureau();
        bureau.setEmail("email");
        bureau.setFax("fax");
        bureau.setNom("nom");
        bureau.setStatut("statut");
        bureau.setTelephone("telephone");
        Inspecteur inspecteur = new Inspecteur();
        inspecteur.setCivilite("civiolite");
        inspecteur.setDomaine(Domaine.AGRICOLE);
        inspecteur.setEmail("email");
        inspecteur.setNom("nom");
        inspecteur.setPrenom("prenom");
        inspecteur.setTelephone("telephone");
        List<Inspecteur> inspecteurs = Arrays.asList(inspecteur);
        Intermediaire intermediaire = new Intermediaire();
        intermediaire.setEmail("email");
        intermediaire.setId("id");
        intermediaire.setLibelle("libelle");
        Portefeuille portefeuille = new Portefeuille();
        portefeuille.setCodeCompagnie("compagnie");
        portefeuille.setCodePortefeuille("codePortefeuille");
        portefeuille.setEtat(EtatPortefeuille.OUVERT);
        Representant representant = new Representant();
        representant.setEmail("email");
        representant.setFax("fax");
        representant.setNom("nom");
        representant.setOrias("orias");
        representant.setPrenom("prenom");
        representant.setTelephone("telephone");

        INFOS_PORTEFEUILLE.setAccord(accord);
        INFOS_PORTEFEUILLE.setBureau(bureau);
        INFOS_PORTEFEUILLE.setInspecteurs(inspecteurs);
        INFOS_PORTEFEUILLE.setIntermediaire(intermediaire);
        INFOS_PORTEFEUILLE.setOrias("orias");
        INFOS_PORTEFEUILLE.setPortefeuille(portefeuille);
        INFOS_PORTEFEUILLE.setRepresentant(representant);
    }

    @TestedObject
    private DefaultPdfSinistreOrigineDeconnecteService service;

    private static String extractPdfText(byte[] pdfData) throws IOException {
        PDDocument pdfDocument = PDDocument.load(new ByteArrayInputStream(pdfData));
        try {
            return new PDFTextStripper().getText(pdfDocument);
        } finally {
            pdfDocument.close();
        }
    }

    /**
     * Test la création du PDF des Degats des eaux
     * 
     * @throws Exception
     */

    @Test
    public void testCreatePdfDegatsDesEaux() throws Exception {
        DeclarationSinistre declarationDDE =
                        SinistreObjectInitialization.initDeclarationSinistre(TypeSinistreEnum.MRH_DEGATS_DES_EAUX);
        byte[] pdf = service.createPdfDegatsEaux(declarationDDE);
        assertTrue(extractPdfText(pdf).contains("Numéro de client"));
        //assertTrue(extractPdfText(pdf).contains("01.58.38.43.96"));
        assertTrue(!extractPdfText(pdf).contains("nom prenom"));
    }

    /**
     * Test la création du PDF des Dommages Electriques
     * 
     * @throws Exception
     */
    @Test
    public void testCreatePdfDommagesElectriques() throws Exception {
        DeclarationSinistre declarationDE =
                        SinistreObjectInitialization.initDeclarationSinistre(TypeSinistreEnum.MRH_DOMMAGES_ELECTRIQUES);
        byte[] pdf = service.createPdfDommageElectrique(declarationDE);

        assertTrue(extractPdfText(pdf).contains("Numéro de client"));
        //assertTrue(extractPdfText(pdf).contains("01.58.38.43.96"));
        assertTrue(!extractPdfText(pdf).contains("nom prenom"));

    }

    /**
     * Test la création du PDF des Bris Glace
     * 
     * @throws Exception
     */
    @Test
    public void testCreatePdfBrisGlace() throws Exception {
        DeclarationSinistre declarationBG =
                        SinistreObjectInitialization.initDeclarationSinistre(TypeSinistreEnum.MRH_BRIS_GLACE);
        byte[] pdf = service.createPdfBrisGlace(declarationBG);
        assertTrue(extractPdfText(pdf).contains("Numéro de client"));
        //assertTrue(extractPdfText(pdf).contains("01.58.38.43.96"));
        assertTrue(!extractPdfText(pdf).contains("nom prenom"));
    }

    @Test
    public void testCreatePdfTempeteGrele() throws Exception {
        DeclarationSinistre declarationSinistre =
                        SinistreObjectInitialization.initDeclarationSinistre(TypeSinistreEnum.MRH_TEMPETE_GRELE);
        byte[] pdf = service.createPdfTempetesGreles(declarationSinistre);
        assertTrue(extractPdfText(pdf).contains("Numéro de client"));
        //assertTrue(extractPdfText(pdf).contains("01.58.38.43.96"));
        assertTrue(!extractPdfText(pdf).contains("nom prenom"));

    }

    /**
     * Test la création du PDF de Vol/Cambriolage
     * 
     * @throws Exception
     */
    @Test
    public void testCreatePdfVolCambriolage() throws Exception {
        DeclarationSinistre declarationVOL =
                        SinistreObjectInitialization.initDeclarationSinistre(TypeSinistreEnum.MRH_VOL_CAMBRIOLAGE);
        // Enregistrement des mocks
        byte[] pdf = service.createPdfVolCambriolage(declarationVOL);
        assertTrue(extractPdfText(pdf).contains("Numéro de client"));
        //assertTrue(extractPdfText(pdf).contains("01.58.38.43.96"));
        assertTrue(!extractPdfText(pdf).contains("nom prenom"));

    }
}
