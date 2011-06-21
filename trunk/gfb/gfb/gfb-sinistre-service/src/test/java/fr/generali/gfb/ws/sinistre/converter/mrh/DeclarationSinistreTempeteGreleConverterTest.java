package fr.generali.gfb.ws.sinistre.converter.mrh;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.junit.Test;

import fr.generali.gfb.ws.sinistre.MockInputs;
import fr.generali.gfb.ws.sinistre.dto.mrh.BienInput;
import fr.generali.gfb.ws.sinistre.dto.mrh.DeclarationSinistreTempeteGreleInput;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;
import fr.generali.gfb.ws.sinistre.persistence.domain.constants.TypeSinistreEnum;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.tempetegrele.SinistreMRHTempeteGrele;

public class DeclarationSinistreTempeteGreleConverterTest {

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
        final Date dateConstruction = new Date();
        final String circonstances = "";
        final Collection<String> elementsEndommages = new ArrayList<String>();
        final String elementEndommage = "elementEndommage";
        elementsEndommages.add(elementEndommage);
        final String dommages = "";
        final Boolean isLogementHabitable = true;

        /*
         * Création de l'input du converter
         */
        DeclarationSinistreTempeteGreleInput input = new DeclarationSinistreTempeteGreleInput();
        final BienInput bien =
                        new BienInput().isResidencePrincipale(isResidencePrincipale).adresse(adresseBienConcerne)
                                        .codePostal(codePostal).ville(ville).qualite(qualite);
        input.assure(MockInputs.INPUT_ASSURE).dateSinistre(dateSinistre);
        input.bien(bien).dateConstruction(dateConstruction).circonstances(circonstances).elementsEndommages(
                        elementsEndommages).dommages(dommages).isLogementHabitable(isLogementHabitable);

        /*
         * Conversion
         */
        final DeclarationSinistre declarationSinistre =
                        DeclarationSinistreTempeteGreleConverter.getInstance().convert(input);
        final SinistreMRHTempeteGrele sinistreTG = (SinistreMRHTempeteGrele ) declarationSinistre.getSinistre();

        /*
         * Tests
         */
        assertEquals(TypeSinistreEnum.MRH_TEMPETE_GRELE, sinistreTG.getTypeSinistre());
        assertEquals(dateSinistre, sinistreTG.getDateSinistre());
        assertNotNull(sinistreTG.getDateDeclaration());

        assertEquals(isResidencePrincipale, sinistreTG.getBienConcerne().getIsResidencePrincipale());
        assertEquals(adresseBienConcerne, sinistreTG.getBienConcerne().getAdresse());
        assertEquals(codePostal, sinistreTG.getBienConcerne().getCodePostal());
        assertEquals(ville, sinistreTG.getBienConcerne().getVille());
        assertEquals(qualite, sinistreTG.getBienConcerne().getQualite());

        assertEquals(dateConstruction, sinistreTG.getDateConstructionBatiment());
        assertEquals(circonstances, sinistreTG.getCirconstances());
        assertEquals(1, sinistreTG.getElementsEndommages().size());
        assertEquals(elementEndommage, sinistreTG.getElementsEndommages().get(0).getValue());
        assertEquals(dommages, sinistreTG.getDommages());
        assertEquals(isLogementHabitable, sinistreTG.getIsLogementHabitable());
    }
}
