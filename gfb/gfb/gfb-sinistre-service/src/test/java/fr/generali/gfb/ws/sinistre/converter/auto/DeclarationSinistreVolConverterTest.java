package fr.generali.gfb.ws.sinistre.converter.auto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;

import fr.generali.gfb.ws.sinistre.MockInputs;
import fr.generali.gfb.ws.sinistre.dto.auto.DeclarationSinistreVolInput;
import fr.generali.gfb.ws.sinistre.dto.auto.DommageVolInput;
import fr.generali.gfb.ws.sinistre.persistence.domain.auto.vol.DommagesAutoVol;
import fr.generali.gfb.ws.sinistre.persistence.domain.auto.vol.SinistreAutoVol;
import fr.generali.gfb.ws.sinistre.persistence.domain.constants.TypeSinistreEnum;

public class DeclarationSinistreVolConverterTest {

    @Test
    public void convertSinistre() {
        // commun � toutes les d�clas
        final TypeSinistreEnum typeSinistre = TypeSinistreEnum.AUTO_VOL;
        final String origine = "EMM";
        final Date dateSinistre = new Date();

        // commun � toutes les d�clas sinistres auto
        final String heureDebut = "0";
        final String minuteDebut = "0";
        final String heureFin = "1";
        final String minuteFin = "0";
        final String circonstances = "circonstances";
        final String immatriculation = "immatriculation";

        // sp�cifiques � la d�claration accident/incendie
        final Boolean plainte = true;
        final String type = "type";

        // dommages communs
        final String coordonnesGarage = "coordonnesGarage";
        final Boolean depotGarage = true;
        final String description = "description";
        final String lieuVehicule = "lieuVehicule";
        final String effetsPersonnels = "effetsPersonnels";

        final DommageVolInput dommage =
                        new DommageVolInput().coordonnesGarage(coordonnesGarage).depotGarage(depotGarage).description(
                                        description).lieuVehicule(lieuVehicule).effetsPersonnels(effetsPersonnels);

        final DeclarationSinistreVolInput input =
                        new DeclarationSinistreVolInput().assure(MockInputs.INPUT_ASSURE).circonstances(circonstances)
                                        .dateSinistre(dateSinistre).heureDebut(heureDebut).heureFin(heureFin)
                                        .immatriculation(immatriculation).minuteDebut(minuteDebut).minuteDebut(
                                                        minuteDebut).minuteFin(minuteFin).origine(origine).dommage(
                                                        dommage).plainte(plainte).type(type);

        final SinistreAutoVol sinistre =
                        (SinistreAutoVol ) DeclarationSinistreVolConverter.getInstance().convert(input).getSinistre();

        // commun � toutes les d�clas
        assertEquals(dateSinistre, sinistre.getDateSinistre());
        assertNotNull(sinistre.getDateDeclaration());
        assertEquals(typeSinistre, sinistre.getTypeSinistre());

        // commun � toutes les d�clas sinistres auto
        assertEquals(Integer.valueOf(heureDebut), sinistre.getHeureDebut());
        assertEquals(Integer.valueOf(minuteDebut), sinistre.getMinuteDebut());
        assertEquals(Integer.valueOf(heureFin), sinistre.getHeureFin());
        assertEquals(Integer.valueOf(minuteFin), sinistre.getMinuteFin());
        assertEquals(circonstances, sinistre.getCirconstances());
        assertEquals(immatriculation, sinistre.getImmatriculation());

        // sp�cifique vol
        assertEquals(plainte, sinistre.getPlainte());
        assertEquals(type, sinistre.getType());

        final DommagesAutoVol dommages = sinistre.getDommage();

        // dommages communs
        assertEquals(description, dommages.getDescription());
        assertEquals(depotGarage, dommages.getDepotGarage());
        assertEquals(coordonnesGarage, dommages.getCoordonnesGarage());
        assertEquals(lieuVehicule, dommages.getLieuVehicule());
        assertEquals(effetsPersonnels, dommages.getEffetsPersonnels());

    }
}
