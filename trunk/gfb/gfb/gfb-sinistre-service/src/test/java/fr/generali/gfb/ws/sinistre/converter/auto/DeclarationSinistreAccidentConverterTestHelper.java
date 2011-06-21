package fr.generali.gfb.ws.sinistre.converter.auto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import fr.generali.gfb.ws.sinistre.MockInputs;
import fr.generali.gfb.ws.sinistre.converter.IDeclarationSinistreConverter;
import fr.generali.gfb.ws.sinistre.dto.auto.DeclarationSinistreAccidentIncendieInput;
import fr.generali.gfb.ws.sinistre.dto.auto.DommageAccidentIncendieInput;
import fr.generali.gfb.ws.sinistre.persistence.domain.auto.accident.DommagesAutoIncendieAccident;
import fr.generali.gfb.ws.sinistre.persistence.domain.auto.accident.SinistreAutoAccidentIncendie;

public class DeclarationSinistreAccidentConverterTestHelper {

    public static SinistreAutoAccidentIncendie assertIncendieEtAccident(
                    IDeclarationSinistreConverter declarationSinistreConverter) {

        // commun à toutes les déclas
        final String origine = "EMM";
        final Date dateSinistre = new Date();

        // commun à toutes les déclas sinistres auto
        final String heureDebut = "0";
        final String minuteDebut = "0";
        final String heureFin = "1";
        final String minuteFin = "0";
        final String circonstances = "circonstances";
        final String immatriculation = "immatriculation";

        // spécifiques à la déclaration accident/incendie
        final String lieu = "lieu";

        // dommages communs
        final String coordonnesGarage = "coordonnesGarage";
        final Boolean depotGarage = true;
        final String description = "description";
        final String lieuVehicule = "lieuVehicule";

        // dommages spécifiques

        final Boolean autresDommagesMateriels = true;
        final String autresPersonnes = "autresPersonnes";
        final String descriptionDommagesMateriels = "descriptionDommagesMateriels";
        final Boolean dommagesCorporel = true;
        final DommageAccidentIncendieInput dommage =
                        new DommageAccidentIncendieInput().autresDommagesMateriels(autresDommagesMateriels)
                                        .autresPersonnes(autresPersonnes).coordonnesGarage(coordonnesGarage)
                                        .depotGarage(depotGarage).description(description)
                                        .descriptionDommagesMateriels(descriptionDommagesMateriels).dommagesCorporel(
                                                        dommagesCorporel).lieuVehicule(lieuVehicule);

        final DeclarationSinistreAccidentIncendieInput input =
                        new DeclarationSinistreAccidentIncendieInput().assure(MockInputs.INPUT_ASSURE).circonstances(
                                        circonstances).dateSinistre(dateSinistre).heureDebut(heureDebut).heureFin(
                                        heureFin).immatriculation(immatriculation).minuteDebut(minuteDebut)
                                        .minuteDebut(minuteDebut).minuteFin(minuteFin).origine(origine).lieu(lieu)
                                        .dommages(dommage);

        final SinistreAutoAccidentIncendie sinistre =
                        (SinistreAutoAccidentIncendie ) declarationSinistreConverter.convert(input).getSinistre();

        assertEquals(dateSinistre, sinistre.getDateSinistre());
        assertNotNull(sinistre.getDateDeclaration());

        // commun à toutes les déclas sinistres auto
        assertEquals(Integer.valueOf(heureDebut), sinistre.getHeureDebut());
        assertEquals(Integer.valueOf(minuteDebut), sinistre.getMinuteDebut());
        assertEquals(Integer.valueOf(heureFin), sinistre.getHeureFin());
        assertEquals(Integer.valueOf(minuteFin), sinistre.getMinuteFin());
        assertEquals(circonstances, sinistre.getCirconstances());
        assertEquals(immatriculation, sinistre.getImmatriculation());
        assertEquals(lieu, sinistre.getLieu());

        final DommagesAutoIncendieAccident dommages = sinistre.getDommage();

        // dommages communs
        assertEquals(description, dommages.getDescription());
        assertEquals(depotGarage, dommages.getDepotGarage());
        assertEquals(coordonnesGarage, dommages.getCoordonnesGarage());
        assertEquals(lieuVehicule, dommages.getLieuVehicule());

        // spécifiques à la déclaration accident/incendie
        assertEquals(autresDommagesMateriels, dommages.getAutresDommagesMateriels());
        assertEquals(descriptionDommagesMateriels, dommages.getDescriptionDommagesMateriels());
        assertEquals(autresPersonnes, dommages.getAutresPersonnes());
        assertEquals(dommagesCorporel, dommages.getDommagesCorporel());

        return sinistre;
    }
}
