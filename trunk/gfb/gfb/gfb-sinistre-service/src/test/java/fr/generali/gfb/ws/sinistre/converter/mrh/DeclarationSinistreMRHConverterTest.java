package fr.generali.gfb.ws.sinistre.converter.mrh;

import static org.junit.Assert.assertTrue;

import org.apache.commons.lang.NotImplementedException;
import org.junit.Test;

import fr.generali.gfb.ws.sinistre.persistence.domain.constants.TypeSinistreEnum;

public class DeclarationSinistreMRHConverterTest {

    @Test
    public void getInstance() {
        assertTrue(AbstractDeclarationSinistreMRHConverter.getInstance(TypeSinistreEnum.MRH_BRIS_GLACE) instanceof DeclarationSinistreBrisDeGlaceConverter);
        assertTrue(AbstractDeclarationSinistreMRHConverter.getInstance(TypeSinistreEnum.MRH_DEGATS_DES_EAUX) instanceof DeclarationSinistreDegatsDesEauxConverter);
        assertTrue(AbstractDeclarationSinistreMRHConverter.getInstance(TypeSinistreEnum.MRH_DOMMAGES_ELECTRIQUES) instanceof DeclarationSinistreDommageElectriqueConverter);
        assertTrue(AbstractDeclarationSinistreMRHConverter.getInstance(TypeSinistreEnum.MRH_TEMPETE_GRELE) instanceof DeclarationSinistreTempeteGreleConverter);
        assertTrue(AbstractDeclarationSinistreMRHConverter.getInstance(TypeSinistreEnum.MRH_VOL_CAMBRIOLAGE) instanceof DeclarationSinistreVolConverter);
    }

    @Test(expected = NotImplementedException.class)
    public void getInstanceAutoAccident() {
        AbstractDeclarationSinistreMRHConverter.getInstance(TypeSinistreEnum.AUTO_ACCIDENT);
    }

    @Test(expected = NotImplementedException.class)
    public void getInstanceAutoIncendie() {
        AbstractDeclarationSinistreMRHConverter.getInstance(TypeSinistreEnum.AUTO_INCENDIE);
    }

    @Test(expected = NotImplementedException.class)
    public void getInstanceAutoVandalisme() {
        AbstractDeclarationSinistreMRHConverter.getInstance(TypeSinistreEnum.AUTO_VANDALISME);
    }

    @Test(expected = NotImplementedException.class)
    public void getInstanceAutoVol() {
        AbstractDeclarationSinistreMRHConverter.getInstance(TypeSinistreEnum.AUTO_VOL);
    }

    @Test(expected = NotImplementedException.class)
    public void getInstanceNull() {
        AbstractDeclarationSinistreMRHConverter.getInstance(null);
    }
}
