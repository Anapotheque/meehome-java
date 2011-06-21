/**
 * 
 */
package fr.generali.gfb.ws.sinistre.pdf.originedeconnecte;

import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;

/**
 * Service de creation de pdf
 * 
 * @author Aguilane ARUL
 */
public interface IPdfSinistreOrigineDeconnecteService {

    /**
     * methode qui permet de créer le pdf des dégats des eaux
     * 
     * @param declarationSinistre Objet de declaration de sinsitre
     * @return tableaux de byte
     */
    public byte[] createPdfDegatsEaux(DeclarationSinistre declarationSinistre);

    /**
     * methode qui permet de créer le pdf le bris glace
     * 
     * @param declarationSinistre Objet de declaration de sinsitre
     * @return tableaux de byte
     */
    public byte[] createPdfBrisGlace(DeclarationSinistre declarationSinistre);

    /**
     * methode qui permet de créer le pdf de dommage electriques
     * 
     * @param declarationSinistre Objet de declaration de sinsitre
     * @return tableaux de byte
     */
    public byte[] createPdfDommageElectrique(DeclarationSinistre declarationSinistre);

    /**
     * methode qui permet de créer le pdf de tempetes greles
     * 
     * @param declarationSinistre Objet de declaration de sinsitre
     * @return tableaux de byte
     */
    public byte[] createPdfTempetesGreles(DeclarationSinistre declarationSinistre);

    /**
     * methode qui permet de creer le pdf du Vol/Cambriolage
     * 
     * @param declarationSinistre Objet de declaration de sinsitre
     * @return tableaux de byte
     */
    public byte[] createPdfVolCambriolage(DeclarationSinistre declarationSinistre);

}
