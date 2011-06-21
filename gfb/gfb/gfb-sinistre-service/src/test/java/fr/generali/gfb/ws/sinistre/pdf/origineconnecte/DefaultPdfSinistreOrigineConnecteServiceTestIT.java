/**
 * 
 */
package fr.generali.gfb.ws.sinistre.pdf.origineconnecte;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
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
public class DefaultPdfSinistreOrigineConnecteServiceTestIT extends UnitilsJUnit4 {

    private static final InformationIntermediaire INFOS_PORTEFEUILLE;
    static {
        INFOS_PORTEFEUILLE = new InformationIntermediaire();
        Accord accord = new Accord();
        accord.setNature(Nature.AGENT);
        Bureau bureau = new Bureau();
        bureau.setEmail("emailBureau");
        bureau.setFax("faxBureau");
        bureau.setNom("nomBureau");
        bureau.setStatut("statut");
        bureau.setTelephone("telephoneBureau");
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
    private DefaultPdfSinistreOrigineConnecteService service;

    private OutputStream fileOutputStream;

    /**
     * Test la création du PDF des Degats des eaux
     * 
     * @throws Exception
     */

    private static final String FILE_OUTPUT = "target/";

    @Test
    public void testCreatePdfDegatsDesEaux() throws Exception {

        // filename
        fileOutputStream = new FileOutputStream(new File(FILE_OUTPUT + "sinistreDDE.pdf"));

        DeclarationSinistre declarationDDE =
                        SinistreObjectInitialization.initDeclarationSinistre(TypeSinistreEnum.MRH_DEGATS_DES_EAUX);

        // Enregistrement des mocks
        fileOutputStream.write(service.createPdfDegatsEauxMRH(declarationDDE, true, INFOS_PORTEFEUILLE,false));

        fileOutputStream.close();

    }

    /**
     * Test la création du PDF des Dommages Electriques
     * 
     * @throws Exception
     */
    @Test
    public void testCreatePdfDommagesElectriques() throws Exception {

        // filename
        fileOutputStream = new FileOutputStream(new File(FILE_OUTPUT + "sinistreDE.pdf"));

        DeclarationSinistre declarationDE =
                        SinistreObjectInitialization.initDeclarationSinistre(TypeSinistreEnum.MRH_DOMMAGES_ELECTRIQUES);

        // Enregistrement des mocks
        fileOutputStream.write(service.createPdfDommageElectriqueMRH(declarationDE, true, INFOS_PORTEFEUILLE,false));

        fileOutputStream.close();

    }

    /**
     * Test la création du PDF des Bris Glace
     * 
     * @throws Exception
     */
    @Test
    public void testCreatePdfBrisGlace() throws Exception {

        // filename
        fileOutputStream = new FileOutputStream(new File(FILE_OUTPUT + "sinistreBG.pdf"));

        DeclarationSinistre declarationBG =
                        SinistreObjectInitialization.initDeclarationSinistre(TypeSinistreEnum.MRH_BRIS_GLACE);

        // Enregistrement des mocks
        fileOutputStream.write(service.createPdfBrisGlaceMRH(declarationBG, true, INFOS_PORTEFEUILLE,false));

        fileOutputStream.close();

    }

    @Test
    public void testCreatePdfTempeteGrele() throws Exception {

        // filename
        fileOutputStream = new FileOutputStream(new File(FILE_OUTPUT + "sinistreTNG.pdf"));

        DeclarationSinistre declarationSinistre =
                        SinistreObjectInitialization.initDeclarationSinistre(TypeSinistreEnum.MRH_TEMPETE_GRELE);

        // Enregistrement des mocks
        fileOutputStream.write(service.createPdfTempetesGrelesMRH(declarationSinistre, true, INFOS_PORTEFEUILLE,false));

        fileOutputStream.close();

    }

    /**
     * Test la création du PDF de Vol/Cambriolage
     * 
     * @throws Exception
     */
    @Test
    public void testCreatePdfVolCambriolage() throws Exception {

        // filename
        fileOutputStream = new FileOutputStream(new File(FILE_OUTPUT + "sinistreVOL.pdf"));

        DeclarationSinistre declarationVOL =
                        SinistreObjectInitialization.initDeclarationSinistre(TypeSinistreEnum.MRH_VOL_CAMBRIOLAGE);

        // Enregistrement des mocks
        fileOutputStream.write(service.createPdfVolCambriolageMRH(declarationVOL, true, INFOS_PORTEFEUILLE,false));

        fileOutputStream.close();

    }

    /**
     * Test la création du PDF de Auto accident/incendie
     * 
     * @throws Exception
     */
    @Test
    public void testCreatePdfAccidentIncendie() throws Exception {

        // filename
        fileOutputStream = new FileOutputStream(new File(FILE_OUTPUT + "sinistreAutoAccidentIncendie.pdf"));

        DeclarationSinistre declaration =
                        SinistreObjectInitialization.initDeclarationSinistre(TypeSinistreEnum.AUTO_ACCIDENT);

        // Enregistrement des mocks
        fileOutputStream.write(service.createPdfAccidentIncendieAUTO(declaration, true, INFOS_PORTEFEUILLE,
                        TypeSinistreEnum.AUTO_ACCIDENT.getLabel(),false));

        fileOutputStream.close();

    }

    /**
     * Test la création du PDF de Auto vandalisme
     * 
     * @throws Exception
     */
    @Test
    public void testCreatePdfAutoVandalisme() throws Exception {

        // filename
        fileOutputStream = new FileOutputStream(new File(FILE_OUTPUT + "sinistreAutoVandalisme.pdf"));

        DeclarationSinistre declaration =
                        SinistreObjectInitialization.initDeclarationSinistre(TypeSinistreEnum.AUTO_VANDALISME);

        // Enregistrement des mocks
        fileOutputStream.write(service.createPdfVandalismeAUTO(declaration, true, INFOS_PORTEFEUILLE,false));

        fileOutputStream.close();

    }

    /**
     * Test la création du PDF de Auto vol
     * 
     * @throws Exception
     */
    @Test
    public void testCreatePdfAutoVol() throws Exception {

        // filename
        fileOutputStream = new FileOutputStream(new File(FILE_OUTPUT + "sinistreAutoVol.pdf"));

        DeclarationSinistre declaration =
                        SinistreObjectInitialization.initDeclarationSinistre(TypeSinistreEnum.AUTO_VOL);

        // Enregistrement des mocks
        fileOutputStream.write(service.createPdfVolAUTO(declaration, true, INFOS_PORTEFEUILLE,false));

        fileOutputStream.close();

    }

}
