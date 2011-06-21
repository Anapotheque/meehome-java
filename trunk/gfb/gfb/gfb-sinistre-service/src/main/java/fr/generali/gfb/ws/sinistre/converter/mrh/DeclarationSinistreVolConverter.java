package fr.generali.gfb.ws.sinistre.converter.mrh;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.generali.espaceclient.common.util.CollectionUtils;
import fr.generali.gfb.ws.sinistre.converter.IDeclarationSinistreConverter;
import fr.generali.gfb.ws.sinistre.dto.IDeclarationSinistreInput;
import fr.generali.gfb.ws.sinistre.dto.mrh.DeclarationSinistreVolInput;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.Sinistre;
import fr.generali.gfb.ws.sinistre.persistence.domain.constants.TypeSinistreEnum;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.vol.ModeOperatoire;
import fr.generali.gfb.ws.sinistre.persistence.domain.mrh.vol.SinistreMRHVol;

public final class DeclarationSinistreVolConverter extends AbstractDeclarationSinistreMRHConverter {

    private static DeclarationSinistreVolConverter instance;

    private DeclarationSinistreVolConverter() {
        super();
    }

    @Override
    protected Sinistre convertSinistre(final IDeclarationSinistreInput input) {
        final DeclarationSinistreVolInput volInput = (DeclarationSinistreVolInput ) input;
        final SinistreMRHVol volOutput = new SinistreMRHVol();

        volOutput.setTypeSinistre(TypeSinistreEnum.MRH_VOL_CAMBRIOLAGE);
        volOutput.setDateDeclaration(new Date());
        volOutput.setDateSinistre(volInput.getDateSinistre());

        volOutput.setBienConcerne(BienConcerneHelper.convert(volInput.getBien()));
        volOutput.setBiensVoles(volInput.getBiensVoles());
        volOutput.setDommagesImmobiliers(volInput.getDommagesImmobiliers());
        volOutput.setDureeAbsence(volInput.getDureeAbsence());
        volOutput.setIsOccupantPresent(volInput.getIsOccupantPresent());
        volOutput.setIsPlainteDepose(volInput.getIsPlainteDepose());
        volOutput.setModeOperatoire(listeModeOperatoire(volInput));
        volOutput.setAutreModeOperatoire(volInput.getAutreModeOperatoire());

        return volOutput;
    }

    private List<ModeOperatoire> listeModeOperatoire(final DeclarationSinistreVolInput volInput) {
        if (CollectionUtils.isNotEmpty(volInput.getModesOperatoire())) {
            final List<ModeOperatoire> listeModeOperatoireOutput = new ArrayList<ModeOperatoire>();
            for (String modeOperatoireInput : volInput.getModesOperatoire()) {
                listeModeOperatoireOutput.add(new ModeOperatoire(modeOperatoireInput));
            }
            return listeModeOperatoireOutput;
        }
        return null;

    }

    public static IDeclarationSinistreConverter getInstance() {
        if (instance == null) {
            instance = new DeclarationSinistreVolConverter();
        }
        return instance;
    }

}
