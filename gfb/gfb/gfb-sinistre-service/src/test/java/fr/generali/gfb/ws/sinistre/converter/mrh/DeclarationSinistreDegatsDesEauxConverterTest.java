package fr.generali.gfb.ws.sinistre.converter.mrh;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import fr.generali.gfb.ws.sinistre.MockInputs;
import fr.generali.gfb.ws.sinistre.dto.mrh.BienInput;
import fr.generali.gfb.ws.sinistre.dto.mrh.DeclarationSinistreDegatsDesEauxInput;
import fr.generali.gfb.ws.sinistre.dto.mrh.PieceInput;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;
import fr.generali.gfb.ws.sinistre.persistence.domain.constants.TypeSinistreEnum;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux.Cause;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux.Infiltration;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux.Piece;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux.SinistreMRHDegatsEaux;

public class DeclarationSinistreDegatsDesEauxConverterTest {

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
        final Collection<String> causes = new ArrayList<String>();
        causes.add("value");
        causes.add("value");
        final Collection<String> infiltrations = new ArrayList<String>();
        infiltrations.add("value");
        infiltrations.add("value");
        final String autreCause = "autreCause";
        final String origine = "origine";
        final Boolean isCauseReparee = true;
        final Boolean isDommageSubi = true;
        final String isDommageSubiParTiers = "oui";
        final String nomTiers = "nomTiers";
        final String prenomTiers = "prenomTiers";
        final String adresseTiers = "adresseTiers";
        final String villeTiers = "villeTiers";
        final String cpTiers = "cpTiers";
        final String assureurTiers = "assureurTiers";
        final Integer nbPieces = 15000;
        final Set<PieceInput> pieces = new HashSet<PieceInput>();
        final PieceInput pieceInput =
                        new PieceInput("typePiece", "nom", "longueur", "largeur", "commentaire", "murPapierPeint",
                                        "murPeinture", "murAutre", "murSurface", "plafondPapierPeint",
                                        "plafondPeinture", "plafondAutre", "plafondSurfaceAutre", "mobilierEndommage",
                                        "solParquet", "solCarrelage", "solMoquette", "solRevetementPlastique",
                                        "solAutre", "solAutreSurface");
        pieces.add(pieceInput);
        /*
         * Création de l'input du converter
         */
        DeclarationSinistreDegatsDesEauxInput input = new DeclarationSinistreDegatsDesEauxInput();
        input.assure(MockInputs.INPUT_ASSURE).dateSinistre(dateSinistre);
        final BienInput bien =
                        new BienInput().isResidencePrincipale(isResidencePrincipale).adresse(adresseBienConcerne)
                                        .codePostal(codePostal).ville(ville).qualite(qualite);
        input.bien(bien).causes(causes).infiltrations(infiltrations).autreCause(autreCause).origineSinistre(origine)
                        .isCauseReparee(isCauseReparee).isDommageSubi(isDommageSubi).isDommageSubiParTiers(
                                        isDommageSubiParTiers).nomTiers(nomTiers).prenomTiers(prenomTiers)
                        .adresseTiers(adresseTiers).villeTiers(villeTiers).codePostalTiers(cpTiers).assureurTiers(
                                        assureurTiers).nbPiecesEndommagees(nbPieces).pieces(pieces);

        /*
         * Conversion
         */
        final DeclarationSinistre declarationSinistre =
                        DeclarationSinistreDegatsDesEauxConverter.getInstance().convert(input);
        final SinistreMRHDegatsEaux sinistreDDE = (SinistreMRHDegatsEaux ) declarationSinistre.getSinistre();

        /*
         * Tests
         */
        assertEquals(TypeSinistreEnum.MRH_DEGATS_DES_EAUX, sinistreDDE.getTypeSinistre());
        assertEquals(dateSinistre, sinistreDDE.getDateSinistre());
        assertNotNull(sinistreDDE.getDateDeclaration());

        assertEquals(isResidencePrincipale, sinistreDDE.getBienConcerne().getIsResidencePrincipale());
        assertEquals(adresseBienConcerne, sinistreDDE.getBienConcerne().getAdresse());
        assertEquals(codePostal, sinistreDDE.getBienConcerne().getCodePostal());
        assertEquals(ville, sinistreDDE.getBienConcerne().getVille());
        assertEquals(qualite, sinistreDDE.getBienConcerne().getQualite());
        for (Cause cause : sinistreDDE.getCause()) {
            assertEquals("value", cause.getValue());
        }
        for (Infiltration infiltration : sinistreDDE.getInfiltration()) {
            assertEquals("value", infiltration.getValue());
        }
        assertEquals(autreCause, sinistreDDE.getAutreCause());
        assertEquals(origine, sinistreDDE.getOrigineSinistre());
        assertEquals(isCauseReparee, sinistreDDE.getIsCauseReparee());
        assertEquals(isDommageSubi, sinistreDDE.getConsequence().getIsDommageSubi());
        assertEquals(isDommageSubiParTiers, sinistreDDE.getConsequence().getIsDommageSubiParTiers());
        assertEquals(nomTiers, sinistreDDE.getConsequence().getDommageTiers().getNom());
        assertEquals(prenomTiers, sinistreDDE.getConsequence().getDommageTiers().getPrenom());
        assertEquals(adresseTiers, sinistreDDE.getConsequence().getDommageTiers().getAdresse());
        assertEquals(villeTiers, sinistreDDE.getConsequence().getDommageTiers().getVille());
        assertEquals(cpTiers, sinistreDDE.getConsequence().getDommageTiers().getCodePostal());
        assertEquals(assureurTiers, sinistreDDE.getConsequence().getDommageTiers().getAssureur());
        assertEquals(nbPieces, sinistreDDE.getDommages().getNbPiecesEndommagees());

        final Piece piece = sinistreDDE.getDommages().getPieces().get(0);
        assertEquals("typePiece", piece.getTypePiece());
        assertEquals("nom", piece.getNom());
        assertEquals("longueur", piece.getLongueur());
        assertEquals("largeur", piece.getLargeur());
        assertEquals("commentaire", piece.getCommentaire());
        assertEquals("murPapierPeint", piece.getMurs().getPapierPeint());
        assertEquals("murPeinture", piece.getMurs().getPeinture());
        assertEquals("murAutre", piece.getMurs().getAutre());
        assertEquals("murSurface", piece.getMurs().getSurfaceAutre());
        assertEquals("plafondPapierPeint", piece.getPlafonds().getPapierPeint());
        assertEquals("plafondPeinture", piece.getPlafonds().getPeinture());
        assertEquals("plafondAutre", piece.getPlafonds().getAutre());
        assertEquals("plafondSurfaceAutre", piece.getPlafonds().getSurfaceAutres());
        assertEquals("mobilierEndommage", piece.getMobilier().getMobilierEndommage());
        assertEquals("solParquet", piece.getSol().getParquet());
        assertEquals("solCarrelage", piece.getSol().getCarrelage());
        assertEquals("solMoquette", piece.getSol().getMoquette());
        assertEquals("solRevetementPlastique", piece.getSol().getRevetementPlastique());
        assertEquals("solAutre", piece.getSol().getAutre());
        assertEquals("solAutreSurface", piece.getSol().getSurfaceAutres());

    }
}
