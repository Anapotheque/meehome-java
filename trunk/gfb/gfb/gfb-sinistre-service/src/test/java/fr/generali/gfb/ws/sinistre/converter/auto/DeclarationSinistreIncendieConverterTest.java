package fr.generali.gfb.ws.sinistre.converter.auto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.generali.gfb.ws.sinistre.persistence.domain.auto.accident.SinistreAutoAccidentIncendie;
import fr.generali.gfb.ws.sinistre.persistence.domain.constants.TypeSinistreEnum;

public class DeclarationSinistreIncendieConverterTest {

    @Test
    public void convertSinistre() {

        SinistreAutoAccidentIncendie sinistre =
                        DeclarationSinistreAccidentConverterTestHelper
                                        .assertIncendieEtAccident(DeclarationSinistreIncendieConverter.getInstance());
        assertEquals(TypeSinistreEnum.AUTO_INCENDIE, sinistre.getTypeSinistre());
    }

}
