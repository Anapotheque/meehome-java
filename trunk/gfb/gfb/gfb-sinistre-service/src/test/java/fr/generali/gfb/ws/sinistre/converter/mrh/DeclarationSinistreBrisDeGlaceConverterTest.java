package fr.generali.gfb.ws.sinistre.converter.mrh;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import fr.generali.gfb.ws.sinistre.MockInputs;
import fr.generali.gfb.ws.sinistre.dto.OrigineDeclaration;
import fr.generali.gfb.ws.sinistre.dto.mrh.DeclarationSinistreBrisGlaceInput;
import fr.generali.gfb.ws.sinistre.dto.mrh.TypeBienEndommageBDG;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;
import fr.generali.gfb.ws.sinistre.persistence.domain.constants.TypeSinistreEnum;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.brisglace.SinistreBrisGlace;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.brisglace.TypeBiensEndommages;

public class DeclarationSinistreBrisDeGlaceConverterTest {

    @Test
    public void extraireDeclarationSinistreBrisDeGlace() {
        Set<TypeBienEndommageBDG> typeBiensEndommages = new HashSet<TypeBienEndommageBDG>();
        typeBiensEndommages.add(new TypeBienEndommageBDG("value", "surface"));
        typeBiensEndommages.add(new TypeBienEndommageBDG("value", "surface"));

        final Date dateSinistre = new Date();
        final Date dateAchatBien = new Date();
        DeclarationSinistreBrisGlaceInput input =
                        new DeclarationSinistreBrisGlaceInput( "ESPACE_CLIENT",
                                        dateSinistre, MockInputs.INPUT_ASSURE, "circonstances", dateAchatBien,
                                        "commentaires", typeBiensEndommages);

        final DeclarationSinistre declarationSinistre =
                        DeclarationSinistreBrisDeGlaceConverter.getInstance().convert(input);

        final SinistreBrisGlace sinistreBDG = (SinistreBrisGlace ) declarationSinistre.getSinistre();
        assertEquals(TypeSinistreEnum.MRH_BRIS_GLACE, sinistreBDG.getTypeSinistre());
        assertEquals(dateSinistre, sinistreBDG.getDateSinistre());
        assertNotNull(sinistreBDG.getDateDeclaration());
        assertEquals(dateAchatBien, sinistreBDG.getDateAchatBien());
        assertEquals("circonstances", sinistreBDG.getCirconstances());
        assertEquals("commentaires", sinistreBDG.getCommentaires());
        for (TypeBiensEndommages type : sinistreBDG.getTypeBiensEndommages()) {
            assertEquals("value", type.getValue());
            assertEquals("surface", type.getSurface());
        }
    }
}
