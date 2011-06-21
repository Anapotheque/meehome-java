package fr.generali.gfb.ws.sinistre.converter.mrh;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import fr.generali.gfb.ws.sinistre.MockInputs;
import fr.generali.gfb.ws.sinistre.dto.mrh.BienInput;
import fr.generali.gfb.ws.sinistre.dto.mrh.DeclarationSinistreVolInput;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;
import fr.generali.gfb.ws.sinistre.persistence.domain.constants.TypeSinistreEnum;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.vol.SinistreMRHVol;

public class DeclarationSinistreVolConverterTest {

    @Test
    public void extraireDeclarationSinistreDDE() {

        /*
         * Valeurs à tester
         */
        final Date dateSinistre = new Date();
        final boolean isResidencePrincipale = false;
        final String adresseBienConcerne = "adresse bien concerne";
        final String codePostal = "code postal";
        final String ville = "ville";
        final String qualite = "qualite";
        final Boolean isOccupantPresent = true;
        final String dureeAbsence = "dureeAbsence";
        final List<String> modesOperatoire = new ArrayList<String>();
        final String modeOperatoire = "modeOperatoire";
        modesOperatoire.add(modeOperatoire);
        final String autreModeOperatoire = "autreModeOperatoire";
        final String biensVoles = "biensVoles";
        final String dommagesImmobiliers = "dommagesImmobiliers";
        final Boolean isPlainteDepose = true;

        /*
         * Création de l'input du converter
         */
        DeclarationSinistreVolInput input = new DeclarationSinistreVolInput();
        input.assure(MockInputs.INPUT_ASSURE).dateSinistre(dateSinistre);
        final BienInput bien =
                        new BienInput().isResidencePrincipale(isResidencePrincipale).adresse(adresseBienConcerne)
                                        .codePostal(codePostal).ville(ville).qualite(qualite);

        input.bien(bien).isOccupantPresent(isOccupantPresent).dureeAbsence(dureeAbsence)
                        .modeOperatoire(modesOperatoire).autreModeOperatoire(autreModeOperatoire)
                        .biensVoles(biensVoles).dommagesImmobiliers(dommagesImmobiliers).isPlainteDepose(
                                        isPlainteDepose);

        /*
         * Conversion
         */
        final DeclarationSinistre declarationSinistre = DeclarationSinistreVolConverter.getInstance().convert(input);
        final SinistreMRHVol sinistreVol = (SinistreMRHVol ) declarationSinistre.getSinistre();

        /*
         * Tests
         */
        assertEquals(TypeSinistreEnum.MRH_VOL_CAMBRIOLAGE, sinistreVol.getTypeSinistre());
        assertEquals(dateSinistre, sinistreVol.getDateSinistre());
        assertNotNull(sinistreVol.getDateDeclaration());

        assertEquals(isResidencePrincipale, sinistreVol.getBienConcerne().getIsResidencePrincipale());
        assertEquals(adresseBienConcerne, sinistreVol.getBienConcerne().getAdresse());
        assertEquals(codePostal, sinistreVol.getBienConcerne().getCodePostal());
        assertEquals(ville, sinistreVol.getBienConcerne().getVille());
        assertEquals(qualite, sinistreVol.getBienConcerne().getQualite());

        assertEquals(isOccupantPresent, sinistreVol.getIsOccupantPresent());
        assertEquals(dureeAbsence, sinistreVol.getDureeAbsence());

        assertEquals(1, sinistreVol.getModeOperatoire().size());
        assertEquals(modeOperatoire, sinistreVol.getModeOperatoire().get(0).getValue());
        assertEquals(autreModeOperatoire, sinistreVol.getAutreModeOperatoire());
        assertEquals(biensVoles, sinistreVol.getBiensVoles());
        assertEquals(dommagesImmobiliers, sinistreVol.getDommagesImmobiliers());
        assertEquals(isPlainteDepose, sinistreVol.getIsPlainteDepose());
    }
}
