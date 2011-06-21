package fr.generali.gfb.ws.sinistre;

import fr.generali.gfb.ws.sinistre.dto.auto.DeclarationSinistreAccidentIncendieInput;
import fr.generali.gfb.ws.sinistre.dto.auto.DeclarationSinistreVandalismeInput;
import fr.generali.gfb.ws.sinistre.dto.auto.DeclarationSinistreVolInput;

public interface IDeclarationSinistreAutoService {

    /**
     * @param input
     * @throws DeclarationSinistreException
     */
    void declarerAccident(final DeclarationSinistreAccidentIncendieInput input) throws DeclarationSinistreException;

    /**
     * @param input
     * @throws DeclarationSinistreException
     */
    void declarerIncendie(final DeclarationSinistreAccidentIncendieInput input) throws DeclarationSinistreException;

    /**
     * @param input
     * @throws DeclarationSinistreException
     */
    void declarerVol(final DeclarationSinistreVolInput input) throws DeclarationSinistreException;

    /**
     * @param input
     * @throws DeclarationSinistreException
     */
    void declarerVandalisme(final DeclarationSinistreVandalismeInput input) throws DeclarationSinistreException;

}
