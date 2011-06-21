package fr.generali.gfb.ws.sinistre.converter.auto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.generali.gfb.ws.sinistre.persistence.domain.auto.accident.SinistreAutoAccidentIncendie;
import fr.generali.gfb.ws.sinistre.persistence.domain.constants.TypeSinistreEnum;

public class DeclarationSinistreAccidentConverterTest {

    @Test
    public void convertSinistre() {

        SinistreAutoAccidentIncendie sinistre =
                        DeclarationSinistreAccidentConverterTestHelper
                                        .assertIncendieEtAccident(DeclarationSinistreAccidentConverter.getInstance());
        assertEquals(TypeSinistreEnum.AUTO_ACCIDENT, sinistre.getTypeSinistre());
    }

}
