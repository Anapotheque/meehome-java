package fr.generali.gfb.ws.sinistre.converter.mrh;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import fr.generali.espaceclient.common.util.CollectionUtils;
import fr.generali.gfb.ws.sinistre.converter.IDeclarationSinistreConverter;
import fr.generali.gfb.ws.sinistre.dto.IDeclarationSinistreInput;
import fr.generali.gfb.ws.sinistre.dto.mrh.DeclarationSinistreDegatsDesEauxInput;
import fr.generali.gfb.ws.sinistre.dto.mrh.PieceInput;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.Sinistre;
import fr.generali.gfb.ws.sinistre.persistence.domain.constants.TypeSinistreEnum;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux.Cause;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux.Consequence;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux.Dommages;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux.Infiltration;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux.Mobilier;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux.Murs;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux.Piece;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux.Plafonds;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux.SinistreMRHDegatsEaux;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux.Sol;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux.Tiers;

public final class DeclarationSinistreDegatsDesEauxConverter extends AbstractDeclarationSinistreMRHConverter {

    private static DeclarationSinistreDegatsDesEauxConverter instance;

    private DeclarationSinistreDegatsDesEauxConverter() {

    }

    @Override
    protected Sinistre convertSinistre(IDeclarationSinistreInput input) {
        DeclarationSinistreDegatsDesEauxInput ddeInput = (DeclarationSinistreDegatsDesEauxInput ) input;
        SinistreMRHDegatsEaux ddeOutput = new SinistreMRHDegatsEaux();

        ddeOutput.setTypeSinistre(TypeSinistreEnum.MRH_DEGATS_DES_EAUX);
        ddeOutput.setDateDeclaration(new Date());
        ddeOutput.setDateSinistre(ddeInput.getDateSinistre());

        bienConcerne(ddeInput, ddeOutput);
        causes(ddeInput, ddeOutput);
        consequences(ddeInput, ddeOutput);
        dommages(ddeInput, ddeOutput);
        return ddeOutput;
    }

    private void dommages(DeclarationSinistreDegatsDesEauxInput ddeInput, SinistreMRHDegatsEaux ddeOutput) {
        Dommages dommagesOutput = new Dommages();
        dommagesOutput.setNbPiecesEndommagees(ddeInput.getNbPiecesEndommagees());
        dommagesOutput.setPieces(listePieces(ddeInput));
        ddeOutput.setDommages(dommagesOutput);

    }

    private List<Piece> listePieces(DeclarationSinistreDegatsDesEauxInput ddeInput) {
        if (CollectionUtils.isNotEmpty(ddeInput.getPieces())) {
            List<Piece> pieces = new ArrayList<Piece>();
            for (PieceInput pieceInput : ddeInput.getPieces()) {
                String type = pieceInput.getTypePiece();
                String longueur = pieceInput.getLongueur();
                String largeur = pieceInput.getLargeur();
                String commentaire = pieceInput.getCommentaire();
                String nom = pieceInput.getNom();
                pieces.add(new Piece(type, nom, longueur, largeur, commentaire, mur(pieceInput), plafond(pieceInput),
                                mobilier(pieceInput), sol(pieceInput)));
            }
            return pieces;
        }
        return null;
    }

    private Murs mur(PieceInput pieceInput) {
        String murPapierPeint = pieceInput.getMurPapierPeint();
        String murPeinture = pieceInput.getMurPeinture();
        String murAutre = pieceInput.getMurAutre();
        String murAutreSurface = pieceInput.getMurSurfaceAutre();

        if (StringUtils.isNotBlank(murPapierPeint) || StringUtils.isNotBlank(murPeinture)
                        || StringUtils.isNotBlank(murAutre) || StringUtils.isNotBlank(murAutreSurface)) {
            return new Murs(murPapierPeint, murPeinture, murAutre, murAutreSurface);
        }

        return null;
    }

    private Plafonds plafond(PieceInput pieceInput) {
        String plafondPapierPeint = pieceInput.getPlafondPapierPeint();
        String plafondPeinture = pieceInput.getPlafondPeinture();
        String plafondAutre = pieceInput.getPlafondAutre();
        String plafondAutreSurface = pieceInput.getPlafondSurfaceAutre();

        if (StringUtils.isNotBlank(plafondPapierPeint) || StringUtils.isNotBlank(plafondPeinture)
                        || StringUtils.isNotBlank(plafondAutre) || StringUtils.isNotBlank(plafondAutreSurface)) {
            return new Plafonds(plafondPapierPeint, plafondPeinture, plafondAutre, plafondAutreSurface);
        }

        return null;
    }

    private Mobilier mobilier(PieceInput pieceInput) {
        Mobilier mobilier = null;

        String mobilierEndommage = pieceInput.getMobilierEndommage();

        if (StringUtils.isNotBlank(mobilierEndommage)) {
            mobilier = new Mobilier(mobilierEndommage);
        }
        return mobilier;
    }

    private Sol sol(PieceInput pieceInput) {
        Sol sol = null;

        String solParquet = pieceInput.getSolParquet();
        String solCarrelage = pieceInput.getSolCarrelage();
        String solMoquette = pieceInput.getSolMoquette();
        String solRevetementPlastique = pieceInput.getSolRevetementPlastique();
        String solAutre = pieceInput.getSolAutre();
        String solAutreSurface = pieceInput.getSolAutreSurface();

        if (StringUtils.isNotBlank(solParquet) || StringUtils.isNotBlank(solCarrelage)
                        || StringUtils.isNotBlank(solMoquette) || StringUtils.isNotBlank(solRevetementPlastique)
                        || StringUtils.isNotBlank(solAutre) || StringUtils.isNotBlank(solAutreSurface)) {
            sol = new Sol(solParquet, solCarrelage, solMoquette, solRevetementPlastique, solAutre, solAutreSurface);
        }
        return sol;
    }

    private void consequences(DeclarationSinistreDegatsDesEauxInput ddeInput, SinistreMRHDegatsEaux ddeOutput) {
        Consequence consequenceOutput = new Consequence();
        consequenceOutput.setIsDommageSubiParTiers(ddeInput.getIsDommageSubiParTiers());
        consequenceOutput.setIsDommageSubi(ddeInput.getIsDommageSubi());
        if ("oui".equals(ddeInput.getIsDommageSubiParTiers())) {
            Tiers tiersOutput = new Tiers();
            tiersOutput.setNom(ddeInput.getNomTiers());
            tiersOutput.setPrenom(ddeInput.getPrenomTiers());
            tiersOutput.setAdresse(ddeInput.getAdresseTiers());
            tiersOutput.setCodePostal(ddeInput.getCodePostalTiers());
            tiersOutput.setVille(ddeInput.getVilleTiers());
            tiersOutput.setAssureur(ddeInput.getAssureurTiers());
            consequenceOutput.setDommageTiers(tiersOutput);
        }
        ddeOutput.setConsequence(consequenceOutput);
    }

    private void causes(DeclarationSinistreDegatsDesEauxInput ddeInput, SinistreMRHDegatsEaux ddeOutput) {
        ddeOutput.setCause(listeDeCauses(ddeInput));
        ddeOutput.setInfiltration(listeInfiltrations(ddeInput));
        ddeOutput.setAutreCause(ddeInput.getAutreCause());
        ddeOutput.setOrigineSinistre(ddeInput.getOrigineSinistre());
        ddeOutput.setIsCauseReparee(ddeInput.getIsCauseReparee());
    }

    private List<Infiltration> listeInfiltrations(DeclarationSinistreDegatsDesEauxInput ddeInput) {
        if (CollectionUtils.isNotEmpty(ddeInput.getCauses())) {
            List<Infiltration> infiltrations = new ArrayList<Infiltration>();
            for (String infiltration : ddeInput.getInfiltrations()) {
                infiltrations.add(new Infiltration(infiltration));
            }
            return infiltrations;
        } else {
            return null;
        }
    }

    private List<Cause> listeDeCauses(DeclarationSinistreDegatsDesEauxInput ddeInput) {
        if (CollectionUtils.isNotEmpty(ddeInput.getCauses())) {
            List<Cause> causes = new ArrayList<Cause>();
            for (String cause : ddeInput.getCauses()) {
                causes.add(new Cause(cause));
            }
            return causes;
        } else {
            return null;
        }
    }

    private void bienConcerne(DeclarationSinistreDegatsDesEauxInput ddeInput, SinistreMRHDegatsEaux ddeOutput) {
        ddeOutput.setBienConcerne(BienConcerneHelper.convert(ddeInput.getBien()));
    }

    public static IDeclarationSinistreConverter getInstance() {
        if (instance == null) {
            instance = new DeclarationSinistreDegatsDesEauxConverter();
        }
        return instance;
    }

}
