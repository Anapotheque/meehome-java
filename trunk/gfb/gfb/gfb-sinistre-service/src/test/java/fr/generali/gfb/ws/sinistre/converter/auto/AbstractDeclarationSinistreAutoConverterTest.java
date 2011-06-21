package fr.generali.gfb.ws.sinistre.converter.auto;

import static org.junit.Assert.assertEquals;

import org.apache.commons.lang.NotImplementedException;
import org.junit.Test;

import fr.generali.gfb.ws.sinistre.persistence.domain.constants.TypeSinistreEnum;

public class AbstractDeclarationSinistreAutoConverterTest {
    @Test
    public void getInstance() {
        // les converters sont singleton, on peut utiliser equals
        assertEquals(DeclarationSinistreAccidentConverter.getInstance(), AbstractDeclarationSinistreAutoConverter
                        .getInstance(TypeSinistreEnum.AUTO_ACCIDENT));
        assertEquals(DeclarationSinistreIncendieConverter.getInstance(), AbstractDeclarationSinistreAutoConverter
                        .getInstance(TypeSinistreEnum.AUTO_INCENDIE));
        assertEquals(DeclarationSinistreVandalismeConverter.getInstance(), AbstractDeclarationSinistreAutoConverter
                        .getInstance(TypeSinistreEnum.AUTO_VANDALISME));
        assertEquals(DeclarationSinistreVolConverter.getInstance(), AbstractDeclarationSinistreAutoConverter
                        .getInstance(TypeSinistreEnum.AUTO_VOL));
    }

    @Test(expected = NotImplementedException.class)
    public void getInstanceKO() {
        // les converters sont singleton, on peut utiliser equals
        AbstractDeclarationSinistreAutoConverter.getInstance(TypeSinistreEnum.MRH_BRIS_GLACE);

    }

}
