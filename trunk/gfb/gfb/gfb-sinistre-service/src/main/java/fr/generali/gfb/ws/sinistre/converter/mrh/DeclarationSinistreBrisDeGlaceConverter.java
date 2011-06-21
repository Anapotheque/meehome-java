package fr.generali.gfb.ws.sinistre.converter.mrh;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.generali.espaceclient.common.util.CollectionUtils;
import fr.generali.gfb.ws.sinistre.dto.IDeclarationSinistreInput;
import fr.generali.gfb.ws.sinistre.dto.mrh.DeclarationSinistreBrisGlaceInput;
import fr.generali.gfb.ws.sinistre.dto.mrh.TypeBienEndommageBDG;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.Sinistre;
import fr.generali.gfb.ws.sinistre.persistence.domain.constants.TypeSinistreEnum;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.brisglace.SinistreBrisGlace;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.brisglace.TypeBiensEndommages;

public final class DeclarationSinistreBrisDeGlaceConverter extends AbstractDeclarationSinistreMRHConverter {

    private static DeclarationSinistreBrisDeGlaceConverter instance;

    private DeclarationSinistreBrisDeGlaceConverter() {
    }

    public static DeclarationSinistreBrisDeGlaceConverter getInstance() {
        if (instance == null) {
            instance = new DeclarationSinistreBrisDeGlaceConverter();
        }
        return instance;
    }

    @Override
    protected Sinistre convertSinistre(IDeclarationSinistreInput input) {
        DeclarationSinistreBrisGlaceInput bdgInput = (DeclarationSinistreBrisGlaceInput ) input;
        final SinistreBrisGlace sinistreBDG = new SinistreBrisGlace();
        sinistreBDG.setTypeSinistre(TypeSinistreEnum.MRH_BRIS_GLACE);
        sinistreBDG.setDateSinistre(bdgInput.getDateSinistre());
        sinistreBDG.setDateDeclaration(new Date());
        sinistreBDG.setDateAchatBien(bdgInput.getDateAchatBien());
        sinistreBDG.setCirconstances(bdgInput.getCirconstances());
        sinistreBDG.setCommentaires(bdgInput.getCommentaires());
        if (CollectionUtils.isNotEmpty(bdgInput.getTypeBiensEndommages())) {
            List<TypeBiensEndommages> typeBiensEndommagesList = new ArrayList<TypeBiensEndommages>();
            for (TypeBienEndommageBDG typeBienEndommage : bdgInput.getTypeBiensEndommages()) {
                typeBiensEndommagesList.add(new TypeBiensEndommages(typeBienEndommage.getValue(), typeBienEndommage
                                .getSurface()));
            }
            sinistreBDG.setTypeBiensEndommages(typeBiensEndommagesList);
        }
        return sinistreBDG;
    }

}
