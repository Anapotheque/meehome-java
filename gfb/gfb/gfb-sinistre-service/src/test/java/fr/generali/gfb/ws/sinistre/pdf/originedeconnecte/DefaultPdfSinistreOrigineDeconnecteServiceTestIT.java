/**
 * 
 */
package fr.generali.gfb.ws.sinistre.pdf.originedeconnecte;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;
import org.unitils.inject.annotation.TestedObject;

import fr.generali.gfb.ws.sinistre.SinistreObjectInitialization;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;
import fr.generali.gfb.ws.sinistre.persistence.domain.constants.TypeSinistreEnum;

/**
 * @author Aguilane ARUL
 */
@Transactional(TransactionMode.DISABLED)
public class DefaultPdfSinistreOrigineDeconnecteServiceTestIT extends UnitilsJUnit4 {

    @TestedObject
    private DefaultPdfSinistreOrigineDeconnecteService service;

    private OutputStream fileOutputStream;

    /**
     * Test la création du PDF des Degats des eaux
     * 
     * @throws Exception
     */

    @Test
    public void testCreatePdfDegatsDesEaux() throws Exception {

        // filename
        File f = File.createTempFile("sinistreDDE",".pdf");
        fileOutputStream = new FileOutputStream(f);

        DeclarationSinistre declarationDDE =
                        SinistreObjectInitialization.initDeclarationSinistre(TypeSinistreEnum.MRH_DEGATS_DES_EAUX);

        // Enregistrement des mocks
        fileOutputStream.write(service.createPdfDegatsEaux(declarationDDE));

        fileOutputStream.close();

        System.out.println("Fichier créé : " + f.getAbsolutePath());
    }

    /**
     * Test la création du PDF des Dommages Electriques
     * 
     * @throws Exception
     */
    @Test
    public void testCreatePdfDommagesElectriques() throws Exception {

        // filename
        File f = File.createTempFile("sinistreDE",".pdf");
        fileOutputStream = new FileOutputStream(f);

        DeclarationSinistre declarationDE =
                        SinistreObjectInitialization.initDeclarationSinistre(TypeSinistreEnum.MRH_DOMMAGES_ELECTRIQUES);

        // Enregistrement des mocks
        fileOutputStream.write(service.createPdfDommageElectrique(declarationDE));

        fileOutputStream.close();

        System.out.println("Fichier créé : " + f.getAbsolutePath());
    }

    /**
     * Test la création du PDF des Bris Glace
     * 
     * @throws Exception
     */
    @Test
    public void testCreatePdfBrisGlace() throws Exception {

        // filename
        File f = File.createTempFile("sinistreBG",".pdf");
        fileOutputStream = new FileOutputStream(f);

        DeclarationSinistre declarationBG =
                        SinistreObjectInitialization.initDeclarationSinistre(TypeSinistreEnum.MRH_BRIS_GLACE);

        // Enregistrement des mocks
        fileOutputStream.write(service.createPdfBrisGlace(declarationBG));

        fileOutputStream.close();

        System.out.println("Fichier créé : " + f.getAbsolutePath());
    }

    @Test
    public void testCreatePdfTempeteGrele() throws Exception {


        File f = File.createTempFile("sinistreTNG",".pdf");
        // filename
        fileOutputStream = new FileOutputStream(f);

        DeclarationSinistre declarationSinistre =
                        SinistreObjectInitialization.initDeclarationSinistre(TypeSinistreEnum.MRH_TEMPETE_GRELE);

        // Enregistrement des mocks
        fileOutputStream.write(service.createPdfTempetesGreles(declarationSinistre));

        fileOutputStream.close();

        System.out.println("Fichier créé : " + f.getAbsolutePath());
    }

    /**
     * Test la création du PDF de Vol/Cambriolage
     * 
     * @throws Exception
     */
    @Test
    public void testCreatePdfVolCambriolage() throws Exception {

        // filename
        File f = File.createTempFile("sinistreVOL",".pdf");
        fileOutputStream = new FileOutputStream(f);

        DeclarationSinistre declarationVOL =
                        SinistreObjectInitialization.initDeclarationSinistre(TypeSinistreEnum.MRH_VOL_CAMBRIOLAGE);

        // Enregistrement des mocks
        fileOutputStream.write(service.createPdfVolCambriolage(declarationVOL));

        fileOutputStream.close();

        System.out.println("Fichier créé : " + f.getAbsolutePath());
    }

}
