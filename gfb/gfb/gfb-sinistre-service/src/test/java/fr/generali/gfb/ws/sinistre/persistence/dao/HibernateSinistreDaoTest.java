/**
 * 
 */
package fr.generali.gfb.ws.sinistre.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;

import fr.generali.gfb.ws.sinistre.SinistreObjectInitialization;
import fr.generali.gfb.ws.sinistre.persistence.domain.auto.SinistreAuto;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.BienConcerne;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;
import fr.generali.gfb.ws.sinistre.persistence.domain.constants.TypeSinistreEnum;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.brisglace.SinistreBrisGlace;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.brisglace.TypeBiensEndommages;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux.Cause;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux.Consequence;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux.Dommages;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux.Infiltration;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux.Piece;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux.SinistreMRHDegatsEaux;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.dommageselectriques.SinistreMRHElectrique;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.tempetegrele.ElementsEndommages;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.tempetegrele.SinistreMRHTempeteGrele;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.vol.ModeOperatoire;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.vol.SinistreMRHVol;

/**
 * @author ARUL Aguilane
 */
@SpringApplicationContext("fr/generali/gfb/ws/sinistre/persistence/dao/applicationContext-test.xml")
@Transactional(TransactionMode.DEFAULT)
public class HibernateSinistreDaoTest extends UnitilsJUnit4 {

    @SpringBean("hibernateSinistreDao")
    private HibernateSinistreDao hibernateSinistreDao;

    /**
     * Ce test verife la sauvegarde d'un Sinistre Bris Glace
     */
    @Test
    public void testCreateSinistreBrisGlace() {

        DeclarationSinistre declarationSinistre =
                        SinistreObjectInitialization.initDeclarationSinistre(TypeSinistreEnum.MRH_BRIS_GLACE);

        // Sauvegarde de l'objet
        Long id = this.hibernateSinistreDao.save(declarationSinistre);

        // Recuperation de l'objet sauvegarde
        DeclarationSinistre declarationSinistreDb = this.hibernateSinistreDao.get(id);
        ((SinistreBrisGlace ) declarationSinistreDb.getSinistre()).getTypeBiensEndommages().size();
        // Verification de l'objet recupere
        assertEquals(declarationSinistre.getNumClient(), declarationSinistreDb.getNumClient());
        assertEquals(declarationSinistre.getNumeroContrat(), declarationSinistreDb.getNumeroContrat());

        // On vérifie les informations sur l'Assure
        assertEquals(declarationSinistre.getAssure().getNom(), declarationSinistreDb.getAssure().getNom());
        assertEquals(declarationSinistre.getAssure().getPrenom(), declarationSinistreDb.getAssure().getPrenom());
        assertEquals(declarationSinistre.getAssure().getAdresse(), declarationSinistreDb.getAssure().getAdresse());
        assertEquals(declarationSinistre.getAssure().getCodePostal(), declarationSinistreDb.getAssure().getCodePostal());
        assertEquals(declarationSinistre.getAssure().getVille(), declarationSinistreDb.getAssure().getVille());
        assertEquals(declarationSinistre.getAssure().getQualite(), declarationSinistreDb.getAssure().getQualite());
        assertEquals(declarationSinistre.getAssure().getEmail(), declarationSinistreDb.getAssure().getEmail());
        assertEquals(declarationSinistre.getAssure().getTelephoneMobile(), declarationSinistreDb.getAssure()
                        .getTelephoneMobile());
        assertEquals(declarationSinistre.getAssure().getTelephoneDomicile(), declarationSinistreDb.getAssure()
                        .getTelephoneDomicile());
        assertEquals(declarationSinistre.getAssure().getTelephoneBureau(), declarationSinistreDb.getAssure()
                        .getTelephoneBureau());

        // Verification de l'objet Sinistre
        SinistreBrisGlace sinistreBrisGlace = (SinistreBrisGlace ) declarationSinistre.getSinistre();
        SinistreBrisGlace sinistreBrisGlaceDb = (SinistreBrisGlace ) declarationSinistreDb.getSinistre();

        assertEquals(sinistreBrisGlace.getDateSinistre(), sinistreBrisGlaceDb.getDateSinistre());
        assertEquals(sinistreBrisGlace.getTypeSinistre(), sinistreBrisGlaceDb.getTypeSinistre());
        assertEquals(sinistreBrisGlace.getDateDeclaration(), sinistreBrisGlaceDb.getDateDeclaration());

        assertEquals(sinistreBrisGlace.getCirconstances(), sinistreBrisGlaceDb.getCirconstances());
        assertEquals(sinistreBrisGlace.getDateAchatBien(), sinistreBrisGlaceDb.getDateAchatBien());
        assertEquals(sinistreBrisGlace.getCommentaires(), sinistreBrisGlaceDb.getCommentaires());

        List<TypeBiensEndommages> typeBiensEndommages = sinistreBrisGlace.getTypeBiensEndommages();
        List<TypeBiensEndommages> typeBiensEndommagesDb = sinistreBrisGlaceDb.getTypeBiensEndommages();

        assertEquals(typeBiensEndommages.size(), typeBiensEndommagesDb.size());

        // On vérifie le contenu de la liste ( biens endommages )
        for (int i = 0; i < typeBiensEndommages.size(); i++) {
            assertEquals(typeBiensEndommages.get(i).getValue(), typeBiensEndommagesDb.get(i).getValue());
        }
    }

    /**
     * Ce test verife la sauvegarde d'un Sinistre Degats des Eaux
     */
    @Test
    public void testCreateSinistreDegatsEaux() {

        DeclarationSinistre declarationSinistre =
                        SinistreObjectInitialization.initDeclarationSinistre(TypeSinistreEnum.MRH_DEGATS_DES_EAUX);

        // Sauvegarde de l'objet
        Long id = this.hibernateSinistreDao.save(declarationSinistre);

        // Recuperation de l'objet sauvegarde
        DeclarationSinistre declarationSinistreDb = this.hibernateSinistreDao.get(id);

        // Verification de l'objet recupere
        assertEquals(declarationSinistre.getNumClient(), declarationSinistreDb.getNumClient());
        assertEquals(declarationSinistre.getNumeroContrat(), declarationSinistreDb.getNumeroContrat());

        // On vérifie les informations sur l'Assure
        assertEquals(declarationSinistre.getAssure().getNom(), declarationSinistreDb.getAssure().getNom());
        assertEquals(declarationSinistre.getAssure().getPrenom(), declarationSinistreDb.getAssure().getPrenom());
        assertEquals(declarationSinistre.getAssure().getAdresse(), declarationSinistreDb.getAssure().getAdresse());
        assertEquals(declarationSinistre.getAssure().getCodePostal(), declarationSinistreDb.getAssure().getCodePostal());
        assertEquals(declarationSinistre.getAssure().getVille(), declarationSinistreDb.getAssure().getVille());
        assertEquals(declarationSinistre.getAssure().getQualite(), declarationSinistreDb.getAssure().getQualite());
        assertEquals(declarationSinistre.getAssure().getEmail(), declarationSinistreDb.getAssure().getEmail());
        assertEquals(declarationSinistre.getAssure().getTelephoneMobile(), declarationSinistreDb.getAssure()
                        .getTelephoneMobile());
        assertEquals(declarationSinistre.getAssure().getTelephoneDomicile(), declarationSinistreDb.getAssure()
                        .getTelephoneDomicile());
        assertEquals(declarationSinistre.getAssure().getTelephoneBureau(), declarationSinistreDb.getAssure()
                        .getTelephoneBureau());

        // Verification de l'objet Sinistre
        SinistreMRHDegatsEaux sinistreDegatsEaux = (SinistreMRHDegatsEaux ) declarationSinistre.getSinistre();
        SinistreMRHDegatsEaux sinistreDegatsEauxDb = (SinistreMRHDegatsEaux ) declarationSinistreDb.getSinistre();

        BienConcerne bienConcerne = sinistreDegatsEaux.getBienConcerne();
        BienConcerne bienConcerneDb = sinistreDegatsEauxDb.getBienConcerne();

        assertEquals(bienConcerne.getIsResidencePrincipale(), bienConcerneDb.getIsResidencePrincipale());
        assertEquals(bienConcerne.getAdresse(), bienConcerneDb.getAdresse());
        assertEquals(bienConcerne.getCodePostal(), bienConcerneDb.getCodePostal());
        assertEquals(bienConcerne.getVille(), bienConcerneDb.getVille());
        assertEquals(bienConcerne.getQualite(), bienConcerneDb.getQualite());

        List<Cause> cause = sinistreDegatsEaux.getCause();
        List<Cause> causeDb = sinistreDegatsEauxDb.getCause();

        assertEquals(cause.size(), causeDb.size());
        for (int i = 0; i < cause.size(); i++) {
            assertEquals(cause.get(i), causeDb.get(i));
        }

        List<Infiltration> infiltration = sinistreDegatsEaux.getInfiltration();
        List<Infiltration> infiltrationDb = sinistreDegatsEauxDb.getInfiltration();

        assertEquals(infiltration.size(), infiltrationDb.size());
        for (int i = 0; i < infiltration.size(); i++) {
            assertEquals(infiltration.get(i), infiltrationDb.get(i));
        }

        assertEquals(sinistreDegatsEaux.getAutreCause(), sinistreDegatsEauxDb.getAutreCause());
        assertEquals(sinistreDegatsEaux.getOrigineSinistre(), sinistreDegatsEauxDb.getOrigineSinistre());
        assertEquals(sinistreDegatsEaux.getIsCauseReparee(), sinistreDegatsEauxDb.getIsCauseReparee());

        Consequence consequence = sinistreDegatsEaux.getConsequence();
        Consequence consequenceDb = sinistreDegatsEauxDb.getConsequence();

        assertEquals(consequence.getIsDommageSubi(), consequenceDb.getIsDommageSubi());
        assertEquals(consequence.getIsDommageSubiParTiers(), consequenceDb.getIsDommageSubiParTiers());
        assertEquals(consequence.getDommageTiers().getNom(), consequenceDb.getDommageTiers().getNom());
        assertEquals(consequence.getDommageTiers().getPrenom(), consequenceDb.getDommageTiers().getPrenom());
        assertEquals(consequence.getDommageTiers().getAdresse(), consequenceDb.getDommageTiers().getAdresse());
        assertEquals(consequence.getDommageTiers().getCodePostal(), consequenceDb.getDommageTiers().getCodePostal());
        assertEquals(consequence.getDommageTiers().getVille(), consequenceDb.getDommageTiers().getVille());
        assertEquals(consequence.getDommageTiers().getAssureur(), consequenceDb.getDommageTiers().getAssureur());

        Dommages dommages = sinistreDegatsEaux.getDommages();
        Dommages dommagesDb = sinistreDegatsEauxDb.getDommages();

        assertEquals(dommages.getNbPiecesEndommagees(), dommagesDb.getNbPiecesEndommagees());

        List<Piece> pieces = dommages.getPieces();
        List<Piece> piecesDb = dommagesDb.getPieces();

        for (int i = 0; i < pieces.size(); i++) {

            Piece piece = pieces.get(i);
            Piece pieceDb = piecesDb.get(i);

            assertEquals(piece.getTypePiece(), pieceDb.getTypePiece());
            assertEquals(piece.getLongueur(), pieceDb.getLongueur());
            assertEquals(piece.getLargeur(), pieceDb.getLargeur());

            assertEquals(piece.getMurs().getPapierPeint(), pieceDb.getMurs().getPapierPeint());
            assertEquals(piece.getMurs().getPeinture(), pieceDb.getMurs().getPeinture());
            assertEquals(piece.getMurs().getAutre(), pieceDb.getMurs().getAutre());
            assertEquals(piece.getMurs().getSurfaceAutre(), pieceDb.getMurs().getSurfaceAutre());

            assertEquals(piece.getPlafonds().getPapierPeint(), pieceDb.getPlafonds().getPapierPeint());
            assertEquals(piece.getPlafonds().getPeinture(), pieceDb.getPlafonds().getPeinture());
            assertEquals(piece.getPlafonds().getAutre(), pieceDb.getPlafonds().getAutre());
            assertEquals(piece.getPlafonds().getSurfaceAutres(), pieceDb.getPlafonds().getSurfaceAutres());

            assertEquals(piece.getMobilier().getMobilierEndommage(), pieceDb.getMobilier().getMobilierEndommage());

            assertEquals(piece.getSol().getParquet(), pieceDb.getSol().getParquet());
            assertEquals(piece.getSol().getCarrelage(), pieceDb.getSol().getCarrelage());
            assertEquals(piece.getSol().getMoquette(), pieceDb.getSol().getMoquette());
            assertEquals(piece.getSol().getRevetementPlastique(), pieceDb.getSol().getRevetementPlastique());
            assertEquals(piece.getSol().getAutre(), pieceDb.getSol().getAutre());
            assertEquals(piece.getSol().getSurfaceAutres(), pieceDb.getSol().getSurfaceAutres());

            assertEquals(piece.getCommentaire(), piece.getCommentaire());

        }

    }

    /**
     * Ce test verife la sauvegarde d'un Sinistre Electrique
     */
    @Test
    public void testCreateSinistreElectrique() {

        DeclarationSinistre declarationSinistre =
                        SinistreObjectInitialization.initDeclarationSinistre(TypeSinistreEnum.MRH_DOMMAGES_ELECTRIQUES);

        // Sauvegarde de l'objet
        Long id = this.hibernateSinistreDao.save(declarationSinistre);

        // Recuperation de l'objet sauvegarde
        DeclarationSinistre declarationSinistreDb = this.hibernateSinistreDao.get(id);

        // Verification de l'objet recupere
        assertEquals(declarationSinistre.getNumClient(), declarationSinistreDb.getNumClient());
        assertEquals(declarationSinistre.getNumeroContrat(), declarationSinistreDb.getNumeroContrat());

        // On vérifie les informations sur l'Assure
        assertEquals(declarationSinistre.getAssure().getNom(), declarationSinistreDb.getAssure().getNom());
        assertEquals(declarationSinistre.getAssure().getPrenom(), declarationSinistreDb.getAssure().getPrenom());
        assertEquals(declarationSinistre.getAssure().getAdresse(), declarationSinistreDb.getAssure().getAdresse());
        assertEquals(declarationSinistre.getAssure().getCodePostal(), declarationSinistreDb.getAssure().getCodePostal());
        assertEquals(declarationSinistre.getAssure().getVille(), declarationSinistreDb.getAssure().getVille());
        assertEquals(declarationSinistre.getAssure().getQualite(), declarationSinistreDb.getAssure().getQualite());
        assertEquals(declarationSinistre.getAssure().getEmail(), declarationSinistreDb.getAssure().getEmail());
        assertEquals(declarationSinistre.getAssure().getTelephoneMobile(), declarationSinistreDb.getAssure()
                        .getTelephoneMobile());
        assertEquals(declarationSinistre.getAssure().getTelephoneDomicile(), declarationSinistreDb.getAssure()
                        .getTelephoneDomicile());
        assertEquals(declarationSinistre.getAssure().getTelephoneBureau(), declarationSinistreDb.getAssure()
                        .getTelephoneBureau());

        // Verification de l'objet Sinistre
        SinistreMRHElectrique sinistreElectrique = (SinistreMRHElectrique ) declarationSinistre.getSinistre();
        SinistreMRHElectrique sinistreElectriqueDb = (SinistreMRHElectrique ) declarationSinistreDb.getSinistre();

        assertEquals(sinistreElectrique.getTypeAppareil(), sinistreElectriqueDb.getTypeAppareil());
        assertEquals(sinistreElectrique.getMarque(), sinistreElectriqueDb.getMarque());
        assertEquals(sinistreElectrique.getDateAchat(), sinistreElectriqueDb.getDateAchat());
        assertEquals(sinistreElectrique.getValeurAchat(), sinistreElectriqueDb.getValeurAchat());
        assertEquals(sinistreElectrique.getDescriptionDommage(), sinistreElectriqueDb.getDescriptionDommage());
        assertEquals(sinistreElectrique.getModele(), sinistreElectriqueDb.getModele());

    }

    /**
     * Ce test verife la sauvegarde d'un Sinistre Tempete/Grele
     */
    @Test
    public void testCreateSinistreTempeteGrele() {

        DeclarationSinistre declarationSinistre =
                        SinistreObjectInitialization.initDeclarationSinistre(TypeSinistreEnum.MRH_TEMPETE_GRELE);

        // Sauvegarde de l'objet
        Long id = this.hibernateSinistreDao.save(declarationSinistre);

        // Recuperation de l'objet sauvegarde
        DeclarationSinistre declarationSinistreDb = this.hibernateSinistreDao.get(id);

        // Verification de l'objet recupere
        assertEquals(declarationSinistre.getNumClient(), declarationSinistreDb.getNumClient());
        assertEquals(declarationSinistre.getNumeroContrat(), declarationSinistreDb.getNumeroContrat());

        // On vérifie les informations sur l'Assure
        assertEquals(declarationSinistre.getAssure().getNom(), declarationSinistreDb.getAssure().getNom());
        assertEquals(declarationSinistre.getAssure().getPrenom(), declarationSinistreDb.getAssure().getPrenom());
        assertEquals(declarationSinistre.getAssure().getAdresse(), declarationSinistreDb.getAssure().getAdresse());
        assertEquals(declarationSinistre.getAssure().getCodePostal(), declarationSinistreDb.getAssure().getCodePostal());
        assertEquals(declarationSinistre.getAssure().getVille(), declarationSinistreDb.getAssure().getVille());
        assertEquals(declarationSinistre.getAssure().getQualite(), declarationSinistreDb.getAssure().getQualite());
        assertEquals(declarationSinistre.getAssure().getEmail(), declarationSinistreDb.getAssure().getEmail());
        assertEquals(declarationSinistre.getAssure().getTelephoneMobile(), declarationSinistreDb.getAssure()
                        .getTelephoneMobile());
        assertEquals(declarationSinistre.getAssure().getTelephoneDomicile(), declarationSinistreDb.getAssure()
                        .getTelephoneDomicile());
        assertEquals(declarationSinistre.getAssure().getTelephoneBureau(), declarationSinistreDb.getAssure()
                        .getTelephoneBureau());

        // Verification de l'objet Sinistre
        SinistreMRHTempeteGrele sinistreTempeteGrele = (SinistreMRHTempeteGrele ) declarationSinistre.getSinistre();
        SinistreMRHTempeteGrele sinistreTempeteGreleDb = (SinistreMRHTempeteGrele ) declarationSinistreDb.getSinistre();

        BienConcerne bienConcerne = sinistreTempeteGrele.getBienConcerne();
        BienConcerne bienConcerneDb = sinistreTempeteGreleDb.getBienConcerne();

        assertEquals(bienConcerne.getIsResidencePrincipale(), bienConcerneDb.getIsResidencePrincipale());
        assertEquals(bienConcerne.getAdresse(), bienConcerneDb.getAdresse());
        assertEquals(bienConcerne.getCodePostal(), bienConcerneDb.getCodePostal());
        assertEquals(bienConcerne.getVille(), bienConcerneDb.getVille());
        assertEquals(bienConcerne.getQualite(), bienConcerneDb.getQualite());

        assertEquals(sinistreTempeteGrele.getDateConstructionBatiment(), sinistreTempeteGreleDb
                        .getDateConstructionBatiment());
        assertEquals(sinistreTempeteGrele.getCirconstances(), sinistreTempeteGreleDb.getCirconstances());
        assertEquals(sinistreTempeteGrele.getDommages(), sinistreTempeteGreleDb.getDommages());
        assertEquals(sinistreTempeteGrele.getIsLogementHabitable(), sinistreTempeteGreleDb.getIsLogementHabitable());

        List<ElementsEndommages> elementsEndommages = sinistreTempeteGrele.getElementsEndommages();
        List<ElementsEndommages> elementsEndommagesDb = sinistreTempeteGreleDb.getElementsEndommages();

        assertEquals(elementsEndommages.size(), elementsEndommagesDb.size());
        for (int i = 0; i < elementsEndommages.size(); i++) {
            assertEquals(elementsEndommages.get(i), elementsEndommagesDb.get(i));
        }

    }

    /**
     * Ce test verife la sauvegarde d'un Sinistre Vol
     */
    @Test
    public void testCreateSinistreSinistreVol() {

        DeclarationSinistre declarationSinistre =
                        SinistreObjectInitialization.initDeclarationSinistre(TypeSinistreEnum.MRH_VOL_CAMBRIOLAGE);

        // Sauvegarde de l'objet
        Long id = this.hibernateSinistreDao.save(declarationSinistre);

        // Recuperation de l'objet sauvegarde
        DeclarationSinistre declarationSinistreDb = this.hibernateSinistreDao.get(id);

        // Verification de l'objet recupere
        assertEquals(declarationSinistre.getNumClient(), declarationSinistreDb.getNumClient());
        assertEquals(declarationSinistre.getNumeroContrat(), declarationSinistreDb.getNumeroContrat());

        // On vérifie les informations sur l'Assure
        assertEquals(declarationSinistre.getAssure().getNom(), declarationSinistreDb.getAssure().getNom());
        assertEquals(declarationSinistre.getAssure().getPrenom(), declarationSinistreDb.getAssure().getPrenom());
        assertEquals(declarationSinistre.getAssure().getAdresse(), declarationSinistreDb.getAssure().getAdresse());
        assertEquals(declarationSinistre.getAssure().getCodePostal(), declarationSinistreDb.getAssure().getCodePostal());
        assertEquals(declarationSinistre.getAssure().getVille(), declarationSinistreDb.getAssure().getVille());
        assertEquals(declarationSinistre.getAssure().getQualite(), declarationSinistreDb.getAssure().getQualite());
        assertEquals(declarationSinistre.getAssure().getEmail(), declarationSinistreDb.getAssure().getEmail());
        assertEquals(declarationSinistre.getAssure().getTelephoneMobile(), declarationSinistreDb.getAssure()
                        .getTelephoneMobile());
        assertEquals(declarationSinistre.getAssure().getTelephoneDomicile(), declarationSinistreDb.getAssure()
                        .getTelephoneDomicile());
        assertEquals(declarationSinistre.getAssure().getTelephoneBureau(), declarationSinistreDb.getAssure()
                        .getTelephoneBureau());

        // Verification de l'objet Sinistre
        SinistreMRHVol sinistreVol = (SinistreMRHVol ) declarationSinistre.getSinistre();
        SinistreMRHVol sinistreVolDb = (SinistreMRHVol ) declarationSinistreDb.getSinistre();

        BienConcerne bienConcerne = sinistreVol.getBienConcerne();
        BienConcerne bienConcerneDb = sinistreVolDb.getBienConcerne();

        assertEquals(bienConcerne.getIsResidencePrincipale(), bienConcerneDb.getIsResidencePrincipale());
        assertEquals(bienConcerne.getAdresse(), bienConcerneDb.getAdresse());
        assertEquals(bienConcerne.getCodePostal(), bienConcerneDb.getCodePostal());
        assertEquals(bienConcerne.getVille(), bienConcerneDb.getVille());
        assertEquals(bienConcerne.getQualite(), bienConcerneDb.getQualite());

        assertEquals(sinistreVol.getIsOccupantPresent(), sinistreVolDb.getIsOccupantPresent());
        assertEquals(sinistreVol.getDureeAbsence(), sinistreVolDb.getDureeAbsence());

        List<ModeOperatoire> modeOperatoire = sinistreVol.getModeOperatoire();
        List<ModeOperatoire> modeOperatoireDb = sinistreVolDb.getModeOperatoire();

        assertEquals(modeOperatoire.size(), modeOperatoireDb.size());
        for (int i = 0; i < modeOperatoire.size(); i++) {
            assertEquals(modeOperatoire.get(i), modeOperatoireDb.get(i));
        }

        assertEquals(sinistreVol.getAutreModeOperatoire(), sinistreVolDb.getAutreModeOperatoire());
        assertEquals(sinistreVol.getBiensVoles(), sinistreVolDb.getBiensVoles());
        assertEquals(sinistreVol.getDommagesImmobiliers(), sinistreVolDb.getDommagesImmobiliers());
        assertEquals(sinistreVol.getIsPlainteDepose(), sinistreVolDb.getIsPlainteDepose());

    }

    /**
     * Ce test verife la sauvegarde d'un Sinistre Vol
     */
    @Test
    public void testCreateSinistreSinistreAutoAccident() {

        DeclarationSinistre declarationSinistre =
                        SinistreObjectInitialization.initDeclarationSinistre(TypeSinistreEnum.AUTO_ACCIDENT);

        // Sauvegarde de l'objet
        Long id = this.hibernateSinistreDao.save(declarationSinistre);

        // Recuperation de l'objet sauvegarde
        DeclarationSinistre declarationSinistreDb = this.hibernateSinistreDao.get(id);

    }

    /**
     * Ce test verife la sauvegarde d'un Sinistre Vol
     */
    @Test
    public void testCreateSinistreSinistreAutoVandalisme() {

        DeclarationSinistre declarationSinistre =
                        SinistreObjectInitialization.initDeclarationSinistre(TypeSinistreEnum.AUTO_VANDALISME);

        // Sauvegarde de l'objet
        Long id = this.hibernateSinistreDao.save(declarationSinistre);

        // Recuperation de l'objet sauvegarde
        DeclarationSinistre declarationSinistreDb = this.hibernateSinistreDao.get(id);
        assertEquals(Integer.valueOf(12), ((SinistreAuto ) declarationSinistreDb.getSinistre()).getHeureDebut());

    }
}
