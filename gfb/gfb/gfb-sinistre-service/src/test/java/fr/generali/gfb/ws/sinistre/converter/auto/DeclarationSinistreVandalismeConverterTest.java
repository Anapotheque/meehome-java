package fr.generali.gfb.ws.sinistre.converter.auto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;

import fr.generali.gfb.ws.sinistre.MockInputs;
import fr.generali.gfb.ws.sinistre.dto.auto.DeclarationSinistreVandalismeInput;
import fr.generali.gfb.ws.sinistre.dto.auto.DommageInput;
import fr.generali.gfb.ws.sinistre.persistence.domain.auto.DommagesAuto;
import fr.generali.gfb.ws.sinistre.persistence.domain.auto.vandalisme.SinistreAutoVandalisme;
import fr.generali.gfb.ws.sinistre.persistence.domain.constants.TypeSinistreEnum;

public class DeclarationSinistreVandalismeConverterTest {

    @Test
    public void convertSinistre() {
        // commun à toutes les déclas
        final TypeSinistreEnum typeSinistre = TypeSinistreEnum.AUTO_VANDALISME;
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
        final Boolean plainte = true;

        // dommages communs
        final String coordonnesGarage = "coordonnesGarage";
        final Boolean depotGarage = true;
        final String description = "description";
        final String lieuVehicule = "lieuVehicule";

        final DommageInput dommage =
                        new DommageInput().coordonnesGarage(coordonnesGarage).depotGarage(depotGarage).description(
                                        description).lieuVehicule(lieuVehicule);

        final DeclarationSinistreVandalismeInput input =
                        new DeclarationSinistreVandalismeInput().assure(MockInputs.INPUT_ASSURE).circonstances(
                                        circonstances).dateSinistre(dateSinistre).heureDebut(heureDebut).heureFin(
                                        heureFin).immatriculation(immatriculation).minuteDebut(minuteDebut)
                                        .minuteDebut(minuteDebut).minuteFin(minuteFin).origine(origine)
                                        .dommage(dommage).plainte(plainte);

        final SinistreAutoVandalisme sinistre =
                        (SinistreAutoVandalisme ) DeclarationSinistreVandalismeConverter.getInstance().convert(input)
                                        .getSinistre();

        // commun à toutes les déclas
        assertEquals(dateSinistre, sinistre.getDateSinistre());
        assertNotNull(sinistre.getDateDeclaration());
        assertEquals(typeSinistre, sinistre.getTypeSinistre());

        // commun à toutes les déclas sinistres auto
        assertEquals(Integer.valueOf(heureDebut), sinistre.getHeureDebut());
        assertEquals(Integer.valueOf(minuteDebut), sinistre.getMinuteDebut());
        assertEquals(Integer.valueOf(heureFin), sinistre.getHeureFin());
        assertEquals(Integer.valueOf(minuteFin), sinistre.getMinuteFin());
        assertEquals(circonstances, sinistre.getCirconstances());
        assertEquals(immatriculation, sinistre.getImmatriculation());

        // spécifique vandalisme
        assertEquals(plainte, sinistre.getPlainte());

        final DommagesAuto dommages = sinistre.getDommage();

        // dommages communs
        assertEquals(description, dommages.getDescription());
        assertEquals(depotGarage, dommages.getDepotGarage());
        assertEquals(coordonnesGarage, dommages.getCoordonnesGarage());
        assertEquals(lieuVehicule, dommages.getLieuVehicule());

    }
}
