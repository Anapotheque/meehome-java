package fr.generali.gfb.ws.sinistre;

import fr.generali.gfb.ws.sinistre.dto.mrh.DeclarationSinistreDegatsDesEauxInput;
import fr.generali.gfb.ws.sinistre.dto.mrh.DeclarationSinistreDommageElectriqueInput;
import fr.generali.gfb.ws.sinistre.dto.mrh.DeclarationSinistreBrisGlaceInput;
import fr.generali.gfb.ws.sinistre.dto.mrh.DeclarationSinistreTempeteGreleInput;
import fr.generali.gfb.ws.sinistre.dto.mrh.DeclarationSinistreVolInput;

public interface IDeclarationSinistreMrhService {

    /**
     * @param input
     * @throws DeclarationSinistreException
     */
    void declarerBrisDeGlace(final DeclarationSinistreBrisGlaceInput input) throws DeclarationSinistreException;

    /**
     * @param input
     * @throws DeclarationSinistreException
     */
    void declarerDegatDesEaux(final DeclarationSinistreDegatsDesEauxInput input) throws DeclarationSinistreException;

    /**
     * @param input
     * @throws DeclarationSinistreException
     */
    void declarerDommageElectrique(final DeclarationSinistreDommageElectriqueInput input)
                    throws DeclarationSinistreException;

    /**
     * @param input
     * @throws DeclarationSinistreException
     */
    void declarerTempeteGrele(final DeclarationSinistreTempeteGreleInput input) throws DeclarationSinistreException;

    /**
     * @param input
     * @throws DeclarationSinistreException
     */
    void declarerVol(final DeclarationSinistreVolInput input) throws DeclarationSinistreException;
}
