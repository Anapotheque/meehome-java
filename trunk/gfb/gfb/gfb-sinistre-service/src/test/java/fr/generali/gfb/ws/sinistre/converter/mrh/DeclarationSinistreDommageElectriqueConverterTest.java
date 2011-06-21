package fr.generali.gfb.ws.sinistre.converter.mrh;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;

import fr.generali.gfb.ws.sinistre.MockInputs;
import fr.generali.gfb.ws.sinistre.dto.mrh.DeclarationSinistreDommageElectriqueInput;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;
import fr.generali.gfb.ws.sinistre.persistence.domain.constants.TypeSinistreEnum;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.dommageselectriques.SinistreMRHElectrique;

public class DeclarationSinistreDommageElectriqueConverterTest {

    @Test
    public void extraireDeclarationSinistreDDE() {

        /*
         * Valeurs à tester
         */
        Date dateSinistre = new Date();
        String type = "type";
        String marque = "marque";
        String modele = "modele";
        Date dateAchat = new Date();
        String valeurAchat = "valeurAchat";
        String description = "description";

        /*
         * Création de l'input du converter
         */
        DeclarationSinistreDommageElectriqueInput input = new DeclarationSinistreDommageElectriqueInput();
        input.assure(MockInputs.INPUT_ASSURE).dateSinistre(dateSinistre);
        input.type(type).marque(marque).modele(modele).dateAchat(dateAchat).valeurAchat(valeurAchat).description(
                        description);

        /*
         * Conversion
         */
        final DeclarationSinistre declarationSinistre =
                        DeclarationSinistreDommageElectriqueConverter.getInstance().convert(input);
        final SinistreMRHElectrique sinistreElec = (SinistreMRHElectrique ) declarationSinistre.getSinistre();

        /*
         * Tests
         */
        assertEquals(TypeSinistreEnum.MRH_DOMMAGES_ELECTRIQUES, sinistreElec.getTypeSinistre());
        assertEquals(dateSinistre, sinistreElec.getDateSinistre());
        assertNotNull(sinistreElec.getDateDeclaration());
        assertEquals(sinistreElec.getTypeAppareil(), type);
        assertEquals(sinistreElec.getMarque(), marque);
        assertEquals(sinistreElec.getModele(), modele);
        assertEquals(sinistreElec.getDateAchat(), dateAchat);
        assertEquals(sinistreElec.getValeurAchat(), valeurAchat);
        assertEquals(sinistreElec.getDescriptionDommage(), description);

    }
}
