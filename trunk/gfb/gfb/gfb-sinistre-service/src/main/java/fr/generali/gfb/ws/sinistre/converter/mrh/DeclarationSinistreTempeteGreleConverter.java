package fr.generali.gfb.ws.sinistre.converter.mrh;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.generali.espaceclient.common.util.CollectionUtils;
import fr.generali.gfb.ws.sinistre.converter.IDeclarationSinistreConverter;
import fr.generali.gfb.ws.sinistre.dto.IDeclarationSinistreInput;
import fr.generali.gfb.ws.sinistre.dto.mrh.DeclarationSinistreTempeteGreleInput;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.Sinistre;
import fr.generali.gfb.ws.sinistre.persistence.domain.constants.TypeSinistreEnum;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.tempetegrele.ElementsEndommages;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.tempetegrele.SinistreMRHTempeteGrele;

public final class DeclarationSinistreTempeteGreleConverter extends AbstractDeclarationSinistreMRHConverter {

    private static DeclarationSinistreTempeteGreleConverter instance;

    private DeclarationSinistreTempeteGreleConverter() {

    }

    @Override
    protected Sinistre convertSinistre(IDeclarationSinistreInput input) {
        DeclarationSinistreTempeteGreleInput tgInput = (DeclarationSinistreTempeteGreleInput ) input;
        SinistreMRHTempeteGrele tgOutput = new SinistreMRHTempeteGrele();

        tgOutput.setTypeSinistre(TypeSinistreEnum.MRH_TEMPETE_GRELE);
        tgOutput.setDateDeclaration(new Date());
        tgOutput.setDateSinistre(tgInput.getDateSinistre());

        tgOutput.setBienConcerne(BienConcerneHelper.convert(tgInput.getBien()));
        tgOutput.setCirconstances(tgInput.getCirconstances());
        tgOutput.setDateConstructionBatiment(tgInput.getDateConstruction());
        tgOutput.setDommages(tgInput.getDommages());
        elementsEndommages(tgInput, tgOutput);
        tgOutput.setIsLogementHabitable(tgInput.getIsLogementHabitable());

        return tgOutput;
    }

    private void elementsEndommages(DeclarationSinistreTempeteGreleInput tgInput, SinistreMRHTempeteGrele tgOutput) {
        if (CollectionUtils.isNotEmpty(tgInput.getElementsEndommages())) {
            List<ElementsEndommages> elementsEndommagesOutput = new ArrayList<ElementsEndommages>();
            for (String elementEndommage : tgInput.getElementsEndommages()) {
                elementsEndommagesOutput.add(new ElementsEndommages(elementEndommage));
            }
            tgOutput.setElementsEndommages(elementsEndommagesOutput);
        }
    }

    public static IDeclarationSinistreConverter getInstance() {
        if (instance == null) {
            instance = new DeclarationSinistreTempeteGreleConverter();
        }
        return instance;
    }

}
