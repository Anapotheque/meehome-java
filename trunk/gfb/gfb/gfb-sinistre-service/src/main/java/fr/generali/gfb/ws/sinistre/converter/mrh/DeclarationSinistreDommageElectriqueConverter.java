package fr.generali.gfb.ws.sinistre.converter.mrh;

import java.util.Date;

import fr.generali.gfb.ws.sinistre.converter.IDeclarationSinistreConverter;
import fr.generali.gfb.ws.sinistre.dto.IDeclarationSinistreInput;
import fr.generali.gfb.ws.sinistre.dto.mrh.DeclarationSinistreDommageElectriqueInput;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.Sinistre;
import fr.generali.gfb.ws.sinistre.persistence.domain.constants.TypeSinistreEnum;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.dommageselectriques.SinistreMRHElectrique;

public final class DeclarationSinistreDommageElectriqueConverter extends AbstractDeclarationSinistreMRHConverter {

    private static DeclarationSinistreDommageElectriqueConverter instance;

    private DeclarationSinistreDommageElectriqueConverter() {

    }

    @Override
    protected Sinistre convertSinistre(IDeclarationSinistreInput input) {
        DeclarationSinistreDommageElectriqueInput ddeInput = (DeclarationSinistreDommageElectriqueInput ) input;
        SinistreMRHElectrique ddeOutput = new SinistreMRHElectrique();

        ddeOutput.setTypeSinistre(TypeSinistreEnum.MRH_DOMMAGES_ELECTRIQUES);
        ddeOutput.setDateDeclaration(new Date());
        ddeOutput.setDateSinistre(ddeInput.getDateSinistre());
        ddeOutput.setTypeAppareil(ddeInput.getType());
        ddeOutput.setMarque(ddeInput.getMarque());
        ddeOutput.setModele(ddeInput.getModele());
        ddeOutput.setDateAchat(ddeInput.getDateAchat());
        ddeOutput.setValeurAchat(ddeInput.getValeurAchat());
        ddeOutput.setDescriptionDommage(ddeInput.getDescriptionDommage());

        return ddeOutput;
    }

    public static IDeclarationSinistreConverter getInstance() {
        if (instance == null) {
            instance = new DeclarationSinistreDommageElectriqueConverter();
        }
        return instance;
    }

}
