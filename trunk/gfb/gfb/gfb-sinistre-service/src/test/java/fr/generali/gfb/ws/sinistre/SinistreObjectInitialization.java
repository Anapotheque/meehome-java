/**
 * 
 */
package fr.generali.gfb.ws.sinistre;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.generali.gfb.ws.sinistre.persistence.domain.auto.DommagesAuto;
import fr.generali.gfb.ws.sinistre.persistence.domain.auto.accident.DommagesAutoIncendieAccident;
import fr.generali.gfb.ws.sinistre.persistence.domain.auto.accident.SinistreAutoAccidentIncendie;
import fr.generali.gfb.ws.sinistre.persistence.domain.auto.vandalisme.SinistreAutoVandalisme;
import fr.generali.gfb.ws.sinistre.persistence.domain.auto.vol.DommagesAutoVol;
import fr.generali.gfb.ws.sinistre.persistence.domain.auto.vol.SinistreAutoVol;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.Assure;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.BienConcerne;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.Sinistre;
import fr.generali.gfb.ws.sinistre.persistence.domain.constants.TypeSinistreEnum;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.brisglace.SinistreBrisGlace;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.brisglace.TypeBiensEndommages;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux.Cause;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux.Consequence;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux.Dommages;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux.Infiltration;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux.Mobilier;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux.Murs;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux.Piece;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux.Plafonds;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux.SinistreMRHDegatsEaux;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux.Sol;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux.Tiers;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.dommageselectriques.SinistreMRHElectrique;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.tempetegrele.ElementsEndommages;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.tempetegrele.SinistreMRHTempeteGrele;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.vol.ModeOperatoire;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.vol.SinistreMRHVol;

/**
 * @author Aguilane ARUL
 */
public abstract class SinistreObjectInitialization {

    private static Assure initAssure() {
        Assure assure = new Assure();
        assure.setNom("test");
        assure.setPrenom("test");
        assure.setAdresse("1 Allée Maupassant");
        assure.setCodePostal("75018");
        assure.setVille("Paris 18");
        assure.setQualite("Propriétaire non occupant");
        assure.setTelephoneBureau("0152658963");
        assure.setTelephoneDomicile("0175658963");
        assure.setTelephoneMobile("0675658963");
        assure.setEmail("aa-test@free.fr");
        return assure;
    }

    public static DeclarationSinistre initDeclarationSinistre(TypeSinistreEnum typeSinistre) {

        DeclarationSinistre declaration = new DeclarationSinistre();
        declaration.setNumeroContrat("AH9866522");
        declaration.setNumClient("15236858");
        declaration.setAssure(initAssure());

        if (TypeSinistreEnum.MRH_BRIS_GLACE.equals(typeSinistre)) {
            declaration.setSinistre(initSinistreBrisGlace());
        } else if (TypeSinistreEnum.MRH_DEGATS_DES_EAUX.equals(typeSinistre)) {
            declaration.setSinistre(initSinistreDegatsDesEaux());
        } else if (TypeSinistreEnum.MRH_DOMMAGES_ELECTRIQUES.equals(typeSinistre)) {
            declaration.setSinistre(initSinistreElectrique());
        } else if (TypeSinistreEnum.MRH_TEMPETE_GRELE.equals(typeSinistre)) {
            declaration.setSinistre(initSinistreTempeteGrele());
        } else if (TypeSinistreEnum.MRH_VOL_CAMBRIOLAGE.equals(typeSinistre)) {
            declaration.setSinistre(initSinistreVolCambriolage());
        } else if (TypeSinistreEnum.AUTO_ACCIDENT.equals(typeSinistre)) {
            declaration.setSinistre(initSinistreAutoAccidentIncendie(typeSinistre));
        } else if (TypeSinistreEnum.AUTO_INCENDIE.equals(typeSinistre)) {
            declaration.setSinistre(initSinistreAutoAccidentIncendie(typeSinistre));
        } else if (TypeSinistreEnum.AUTO_VANDALISME.equals(typeSinistre)) {
            declaration.setSinistre(initSinistreAutoVandalisme());
        } else if (TypeSinistreEnum.AUTO_VOL.equals(typeSinistre)) {
            declaration.setSinistre(initSinistreAutoVol());
        }

        else {
            throw new IllegalArgumentException("Le type de sinistre n'est pas reconnu" + typeSinistre);
        }

        return declaration;
    }

    private static Sinistre initSinistreAutoVol() {
        SinistreAutoVol sinistre = new SinistreAutoVol();
        // super common
        sinistre.setDateDeclaration(new Date());
        sinistre.setTypeSinistre(TypeSinistreEnum.AUTO_VOL);
        sinistre.setDateSinistre(new Date());

        // common
        Integer heureDebut = 12;
        Integer minuteDebut = 0;
        Integer heureFin = 13;
        Integer minuteFin = 5;
        String circonstances = "circonstances";
        String immatriculation = "immatriculation";
        sinistre.setCirconstances(circonstances);
        sinistre.setHeureDebut(heureDebut);
        sinistre.setMinuteDebut(minuteDebut);
        sinistre.setHeureFin(heureFin);
        sinistre.setMinuteFin(minuteFin);
        sinistre.setImmatriculation(immatriculation);

        // spé
        sinistre.setPlainte(true);
        DommagesAutoVol dommage = new DommagesAutoVol();
        dommage.setCoordonnesGarage("coordonnesGarage");
        dommage.setDepotGarage(true);
        dommage.setDescription("description");
        dommage.setLieuVehicule("lieuVehicule");
        dommage.setEffetsPersonnels("effetsPersonnels");
        sinistre.setDommage(dommage);

        return sinistre;
    }

    private static Sinistre initSinistreAutoVandalisme() {
        SinistreAutoVandalisme sinistre = new SinistreAutoVandalisme();
        // super common
        sinistre.setDateDeclaration(new Date());
        sinistre.setTypeSinistre(TypeSinistreEnum.AUTO_VANDALISME);
        sinistre.setDateSinistre(new Date());

        // common
        Integer heureDebut = 12;
        Integer minuteDebut = 0;
        Integer heureFin = 13;
        Integer minuteFin = 5;
        String circonstances = "circonstances";
        String immatriculation = "immatriculation";
        sinistre.setCirconstances(circonstances);
        sinistre.setHeureDebut(heureDebut);
        sinistre.setMinuteDebut(minuteDebut);
        sinistre.setHeureFin(heureFin);
        sinistre.setMinuteFin(minuteFin);
        sinistre.setImmatriculation(immatriculation);

        // spé
        sinistre.setPlainte(true);
        DommagesAuto dommage = new DommagesAuto();
        sinistre.setDommage(dommage);
        dommage.setCoordonnesGarage("coordonnesGarage");
        dommage.setDepotGarage(true);
        dommage.setDescription("description");
        dommage.setLieuVehicule("lieuVehicule");
        sinistre.setDommage(dommage);

        return sinistre;
    }

    private static Sinistre initSinistreAutoAccidentIncendie(TypeSinistreEnum typeSinistre) {
        SinistreAutoAccidentIncendie sinistre = new SinistreAutoAccidentIncendie();

        // super common
        sinistre.setDateDeclaration(new Date());
        sinistre.setTypeSinistre(typeSinistre);
        sinistre.setDateSinistre(new Date());

        // common
        Integer heureDebut = 12;
        Integer minuteDebut = 0;
        Integer heureFin = 13;
        Integer minuteFin = 5;
        String circonstances = "circonstances";
        String immatriculation = "immatriculation";
        sinistre.setCirconstances(circonstances);
        sinistre.setHeureDebut(heureDebut);
        sinistre.setMinuteDebut(minuteDebut);
        sinistre.setHeureFin(heureFin);
        sinistre.setMinuteFin(minuteFin);
        sinistre.setImmatriculation(immatriculation);

        // spé
        sinistre.setLieu("lieu");
        DommagesAutoIncendieAccident dommage = new DommagesAutoIncendieAccident();
        dommage.setAutresDommagesMateriels(true);
        dommage.setAutresPersonnes("autresPersonnes");
        dommage.setCoordonnesGarage("coordonnesGarage");
        dommage.setDepotGarage(true);
        dommage.setDescription("description");
        dommage.setDommagesCorporel(true);
        dommage.setDescriptionDommagesMateriels("descriptionDommagesMateriels");
        dommage.setLieuVehicule("lieuVehicule");
        sinistre.setDommage(dommage);

        return sinistre;

    }

    private static Sinistre initSinistreVolCambriolage() {

        SinistreMRHVol sinistreVol = new SinistreMRHVol();
        sinistreVol.setDateDeclaration(new Date());
        sinistreVol.setTypeSinistre(TypeSinistreEnum.MRH_VOL_CAMBRIOLAGE);
        sinistreVol.setDateSinistre(new Date());

        sinistreVol.setBienConcerne(initBienConcerne());

        sinistreVol.setIsOccupantPresent(Boolean.FALSE);

        sinistreVol.setDureeAbsence("160");

        List<ModeOperatoire> modeOperatoire = new ArrayList<ModeOperatoire>();

        ModeOperatoire value1 = new ModeOperatoire();
        ModeOperatoire value2 = new ModeOperatoire();
        ModeOperatoire value3 = new ModeOperatoire();
        ModeOperatoire value4 = new ModeOperatoire();
        ModeOperatoire value5 = new ModeOperatoire();

        value1.setValue("Effraction");
        value2.setValue("Escalade");
        value3.setValue("Usage de fausse clé");
        value4.setValue("Violence");
        value5.setValue("Autre, précisez");

        modeOperatoire.add(value1);
        modeOperatoire.add(value2);
        modeOperatoire.add(value3);
        modeOperatoire.add(value4);
        modeOperatoire.add(value5);

        sinistreVol.setModeOperatoire(modeOperatoire);

        sinistreVol.setAutreModeOperatoire("modes inconnues utilses");

        sinistreVol.setBiensVoles("tv, table basses");

        sinistreVol.setDommagesImmobiliers("tables, meubles TV");

        sinistreVol.setIsPlainteDepose(Boolean.FALSE);

        return sinistreVol;
    }

    private static SinistreMRHDegatsEaux initSinistreDegatsDesEaux() {

        SinistreMRHDegatsEaux sinistreDegatsEaux = new SinistreMRHDegatsEaux();
        sinistreDegatsEaux.setDateDeclaration(new Date());
        sinistreDegatsEaux.setTypeSinistre(TypeSinistreEnum.MRH_DEGATS_DES_EAUX);
        sinistreDegatsEaux.setDateSinistre(new Date());

        sinistreDegatsEaux.setOrigineSinistre("Dans les locaux occupés par l’assuré");

        sinistreDegatsEaux.setBienConcerne(initBienConcerne());

        List<Cause> causes = new ArrayList<Cause>();
        Cause cause1 = new Cause();
        Cause cause2 = new Cause();
        Cause cause3 = new Cause();
        Cause cause4 = new Cause();
        Cause cause5 = new Cause();

        cause1.setValue("Fuite sur canalisation");
        cause2.setValue("Fuite ou débordement de chéneaux ou de gouttières");
        cause3.setValue("Infiltrations");
        cause4.setValue("Autre cause");
        cause5.setValue("Cause inconnue");

        causes.add(cause1);
        causes.add(cause2);
        causes.add(cause3);
        causes.add(cause4);
        causes.add(cause5);

        sinistreDegatsEaux.setCause(causes);

        List<Infiltration> infiltrations = new ArrayList<Infiltration>();
        Infiltration value1 = new Infiltration();
        Infiltration value2 = new Infiltration();
        Infiltration value3 = new Infiltration();
        Infiltration value4 = new Infiltration();

        value1.setValue("Toiture");
        value2.setValue("Terrasse");
        value3.setValue("Façade");
        value4.setValue("Balcon");

        infiltrations.add(value1);
        infiltrations.add(value2);
        infiltrations.add(value3);
        infiltrations.add(value4);

        sinistreDegatsEaux.setInfiltration(infiltrations);

        sinistreDegatsEaux.setAutreCause("c'est une autre cause");

        sinistreDegatsEaux.setOrigineSinistre("Dans un immeuble voisin");

        sinistreDegatsEaux.setIsCauseReparee(Boolean.FALSE);

        Consequence consequence = new Consequence();
        consequence.setIsDommageSubi(Boolean.TRUE);
        consequence.setIsDommageSubiParTiers("oui");

        Tiers tiers = new Tiers();
        tiers.setNom("nom locataire");
        tiers.setPrenom("prénom locataire");
        tiers.setAdresse("1 Allée Emile Zola");
        tiers.setCodePostal("75010");
        tiers.setVille("Paris 10");
        tiers.setAssureur("Assureur Beta");

        consequence.setDommageTiers(tiers);

        sinistreDegatsEaux.setConsequence(consequence);

        Dommages bien = new Dommages();
        bien.setNbPiecesEndommagees(5);
        bien.setPieces(initPieces(5));
        sinistreDegatsEaux.setDommages(bien);

        return sinistreDegatsEaux;
    }

    private static List<Piece> initPieces(int nbPiece) {

        List<Piece> pieces = new ArrayList<Piece>();

        for (int i = 0; i < nbPiece; i++) {

            Piece piece = new Piece();

            piece.setTypePiece("Salle de bains");
            piece.setLongueur("9.5");
            piece.setLargeur("10.455");

            Murs murs = new Murs();
            murs.setPapierPeint("7.458");
            murs.setPeinture("8.44");
            murs.setAutre("bois");
            murs.setSurfaceAutre("9.44");
            piece.setMurs(murs);

            Plafonds plafonds = new Plafonds();
            plafonds.setPapierPeint("7");
            plafonds.setPeinture("8");
            plafonds.setAutre("plastique");
            plafonds.setSurfaceAutres("9");
            piece.setPlafonds(plafonds);

            Mobilier mobilier = new Mobilier();
            mobilier.setMobilierEndommage("meuble tv, armoire, placard");
            piece.setMobilier(mobilier);

            Sol sol = new Sol();
            sol.setParquet("7");
            sol.setCarrelage("8");
            sol.setMoquette("9");
            sol.setRevetementPlastique("10");
            sol.setAutre("Plastique");
            sol.setSurfaceAutres("11");
            piece.setSol(sol);

            piece.setCommentaire("commentaire bidon...");

            pieces.add(piece);
        }

        return pieces;
    }

    private static SinistreBrisGlace initSinistreBrisGlace() {

        SinistreBrisGlace sinistreBrisGlace = new SinistreBrisGlace();
        sinistreBrisGlace.setTypeSinistre(TypeSinistreEnum.MRH_BRIS_GLACE);
        sinistreBrisGlace.setDateDeclaration(new Date());
        sinistreBrisGlace.setDateSinistre(new Date());

        sinistreBrisGlace.setCirconstances("circontances inconnues");
        sinistreBrisGlace.setDateAchatBien(new Date());
        sinistreBrisGlace.setCommentaires("no comment");

        List<TypeBiensEndommages> typeBiensEndommages = new ArrayList<TypeBiensEndommages>();
        TypeBiensEndommages value1 = new TypeBiensEndommages();
        TypeBiensEndommages value2 = new TypeBiensEndommages();
        TypeBiensEndommages value3 = new TypeBiensEndommages();
        TypeBiensEndommages value4 = new TypeBiensEndommages();

        value1.setValue("Simple vitrage");
        value1.setSurface("18.551");

        value2.setValue("Double vitrage");
        value2.setSurface("26,551");

        value3.setValue("Sur-vitrage");
        value3.setSurface(null);

        value4.setValue("Insert");

        typeBiensEndommages.add(value1);
        typeBiensEndommages.add(value2);
        typeBiensEndommages.add(value3);
        typeBiensEndommages.add(value4);

        sinistreBrisGlace.setTypeBiensEndommages(typeBiensEndommages);

        return sinistreBrisGlace;
    }

    private static SinistreMRHElectrique initSinistreElectrique() {

        SinistreMRHElectrique sinistreElectrique = new SinistreMRHElectrique();
        sinistreElectrique.setTypeSinistre(TypeSinistreEnum.MRH_DOMMAGES_ELECTRIQUES);
        sinistreElectrique.setDateDeclaration(new Date());
        sinistreElectrique.setDateSinistre(new Date());

        sinistreElectrique.setTypeAppareil("Lave-linge");
        sinistreElectrique.setMarque("Marque BetaLaveLinge");
        sinistreElectrique.setDateAchat(new Date());
        sinistreElectrique.setValeurAchat("499.99");
        sinistreElectrique.setDescriptionDommage("appareil endommagé");
        sinistreElectrique.setModele("XLS-1888-0888");

        return sinistreElectrique;

    }

    private static SinistreMRHTempeteGrele initSinistreTempeteGrele() {

        SinistreMRHTempeteGrele sinistreTempeteGrele = new SinistreMRHTempeteGrele();
        sinistreTempeteGrele.setTypeSinistre(TypeSinistreEnum.MRH_TEMPETE_GRELE);
        sinistreTempeteGrele.setDateDeclaration(new Date());
        sinistreTempeteGrele.setDateSinistre(new Date());

        sinistreTempeteGrele.setBienConcerne(initBienConcerne());
        sinistreTempeteGrele.setDateConstructionBatiment(new Date());
        sinistreTempeteGrele.setCirconstances("dans des circonstances inconnues");

        List<ElementsEndommages> elementsEndommages = new ArrayList<ElementsEndommages>();

        ElementsEndommages value1 = new ElementsEndommages();
        ElementsEndommages value2 = new ElementsEndommages();
        ElementsEndommages value3 = new ElementsEndommages();
        ElementsEndommages value4 = new ElementsEndommages();
        ElementsEndommages value5 = new ElementsEndommages();
        ElementsEndommages value6 = new ElementsEndommages();
        ElementsEndommages value7 = new ElementsEndommages();
        ElementsEndommages value8 = new ElementsEndommages();

        value1.setValue("Toiture");
        value2.setValue("Murs");
        value3.setValue("Cheminée");
        value4.setValue("Portail/Clotûre");
        value5.setValue("Plantations");
        value6.setValue("Installations");
        value7.setValue("extérieures");
        value8.setValue("Autre");

        elementsEndommages.add(value1);
        elementsEndommages.add(value2);
        elementsEndommages.add(value3);
        elementsEndommages.add(value4);
        elementsEndommages.add(value5);
        elementsEndommages.add(value6);
        elementsEndommages.add(value7);
        elementsEndommages.add(value8);

        sinistreTempeteGrele.setElementsEndommages(elementsEndommages);

        sinistreTempeteGrele.setDommages("Vitres cassée, portes abimes");

        sinistreTempeteGrele.setIsLogementHabitable(Boolean.TRUE);

        return sinistreTempeteGrele;

    }

    private static BienConcerne initBienConcerne() {

        BienConcerne bienConcerne = new BienConcerne();

        bienConcerne.setIsResidencePrincipale(Boolean.FALSE);
        bienConcerne.setAdresse("1 Allée Fragonard");
        bienConcerne.setCodePostal("75018");
        bienConcerne.setVille("Paris 18");
        bienConcerne.setQualite("Locataire");

        return bienConcerne;
    }

}
