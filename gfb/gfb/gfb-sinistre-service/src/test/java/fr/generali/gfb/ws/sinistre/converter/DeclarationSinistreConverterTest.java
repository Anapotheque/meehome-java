package fr.generali.gfb.ws.sinistre.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;

import fr.generali.gfb.ws.sinistre.MockInputs;
import fr.generali.gfb.ws.sinistre.converter.mrh.AbstractDeclarationSinistreMRHConverter;
import fr.generali.gfb.ws.sinistre.converter.mrh.DeclarationSinistreBrisDeGlaceConverter;
import fr.generali.gfb.ws.sinistre.dto.IDeclarationSinistreInput;
import fr.generali.gfb.ws.sinistre.dto.OrigineDeclaration;
import fr.generali.gfb.ws.sinistre.dto.mrh.DeclarationSinistreBrisGlaceInput;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.Assure;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;

public class DeclarationSinistreConverterTest {

    @Test
    public void extraireAssure() {
        final Assure assure = AbstractDeclarationSinistreMRHConverter.extraireAssure(MockInputs.INPUT_ASSURE);
        assertEquals("nom", assure.getNom());
        assertEquals("prenom", assure.getPrenom());
        assertEquals("adresse", assure.getAdresse());
        assertEquals("codePostal", assure.getCodePostal());
        assertEquals("ville", assure.getVille());
        assertEquals("email", assure.getEmail());
        assertEquals("telephoneMobile", assure.getTelephoneMobile());
        assertEquals("telephoneDomicile", assure.getTelephoneDomicile());
        assertEquals("telephoneBureau", assure.getTelephoneBureau());
    }

    @Test
    public void extraireDeclarationSinistre() {
        IDeclarationSinistreInput input =
                        new DeclarationSinistreBrisGlaceInput("ESPACE_CLIENT",
                                        new Date(), MockInputs.INPUT_ASSURE, "circonstances", new Date(),
                                        "commentaires", null);
        final DeclarationSinistre declarationSinistre =
                        DeclarationSinistreBrisDeGlaceConverter.getInstance().convert(input);
        assertEquals("numeroContrat", declarationSinistre.getNumeroContrat());
        assertEquals("numeroClient", declarationSinistre.getNumClient());
        assertEquals("idRce", declarationSinistre.getIdClientRce());
        assertEquals("codeCompagnie", declarationSinistre.getCodeCompagnie());
        assertEquals("codePortefeuille", declarationSinistre.getCodePortefeuille());
        assertNotNull(declarationSinistre.getAssure());

    }

}
