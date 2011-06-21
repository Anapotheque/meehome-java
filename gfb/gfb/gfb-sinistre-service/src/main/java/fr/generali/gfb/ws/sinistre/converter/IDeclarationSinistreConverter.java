package fr.generali.gfb.ws.sinistre.converter;

import fr.generali.gfb.ws.sinistre.dto.IDeclarationSinistreInput;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;

public interface IDeclarationSinistreConverter {
    public DeclarationSinistre convert(IDeclarationSinistreInput input);

}
