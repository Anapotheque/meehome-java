/**
 * 
 */
package fr.generali.gfb.ws.sinistre.pdf.origineconnecte;

import fr.generali.espaceclient.proxy.generic.domain.InformationIntermediaire;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;

/**
 * Service de creation de pdf
 * 
 * @author Aguilane ARUL
 */
public interface IPdfSinistreOrigineConnecteService {

    /** Libellé "accident" pour le sinistre AccidentIncendieAUTO */
    String LIBELLE_SINISTRE_ACCIDENT = "Accident";

    /** Libellé "incendie" pour le sinistre AccidentIncendieAUTO */
    String LIBELLE_SINISTRE_INCENDIE = "Incendie";

    /**
     * methode qui permet de créer le pdf des dégats des eaux
     * 
     * @param declarationSinistre objet de declaration de sinsitre
     * @param isClientInternet booléen si le client provient d'internet
     * @param infosPortefeuille objet d'info du portefeuille
     * @return tableaux de byte
     */
    byte[] createPdfDegatsEauxMRH(DeclarationSinistre declarationSinistre, Boolean isClientInternet,
                    InformationIntermediaire infosPortefeuille, boolean isCourtier);

    /**
     * methode qui permet de créer le pdf le bris glace
     * 
     * @param declarationSinistre objet de declaration de sinsitre
     * @param isClientInternet booléen si le client provient d'internet
     * @param infosPortefeuille objet d'info du portefeuille
     * @return tableaux de byte
     */
    byte[] createPdfBrisGlaceMRH(DeclarationSinistre declarationSinistre, Boolean isClientInternet,
                    InformationIntermediaire infosPortefeuille, boolean isCourtier);

    /**
     * methode qui permet de créer le pdf de dommage electriques
     * 
     * @param declarationSinistre objet de declaration de sinsitre
     * @param isClientInternet booléen si le client provient d'internet
     * @param infosPortefeuille objet d'info du portefeuille
     * @return tableaux de byte
     */
    byte[] createPdfDommageElectriqueMRH(DeclarationSinistre declarationSinistre, Boolean isClientInternet,
                    InformationIntermediaire infosPortefeuille, boolean isCourtier);

    /**
     * methode qui permet de créer le pdf de tempetes greles
     * 
     * @param declarationSinistre objet de declaration de sinsitre
     * @param isClientInternet booléen si le client provient d'internet
     * @param infosPortefeuille objet d'info du portefeuille
     * @return tableaux de byte
     */
    byte[] createPdfTempetesGrelesMRH(DeclarationSinistre declarationSinistre, Boolean isClientInternet,
                    InformationIntermediaire infosPortefeuille, boolean isCourtier);

    /**
     * methode qui permet de creer le pdf du Vol/Cambriolage
     * 
     * @param declarationSinistre objet de declaration de sinsitre
     * @param isClientInternet booléen si le client provient d'internet
     * @param infosPortefeuille objet d'info du portefeuille
     * @return tableaux de byte
     */
    byte[] createPdfVolCambriolageMRH(DeclarationSinistre declarationSinistre, Boolean isClientInternet,
                    InformationIntermediaire infosPortefeuille, boolean isCourtier);

    /**
     * methode qui permet de creer le pdf du Vol Auto
     * 
     * @param declarationSinistre objet de declaration de sinsitre
     * @param isClientInternet booléen si le client provient d'internet
     * @param infosPortefeuille objet d'info du portefeuille
     * @return tableaux de byte
     */
    byte[] createPdfVolAUTO(DeclarationSinistre declarationSinistre, Boolean isClientInternet,
                    InformationIntermediaire infosPortefeuille, boolean isCourtier);

    /**
     * methode qui permet de creer le pdf du Accident ou Incendie Auto
     * 
     * @param declarationSinistre objet de declaration de sinsitre
     * @param isClientInternet booléen si le client provient d'internet
     * @param infosPortefeuille objet d'info du portefeuille
     * @param libelleSinistre libellé indiquant si accident ou incendie
     * @return tableaux de byte
     */
    byte[] createPdfAccidentIncendieAUTO(DeclarationSinistre declarationSinistre, Boolean isClientInternet,
                    InformationIntermediaire infosPortefeuille, String libelleSinistre, boolean isCourtier);

    /**
     * methode qui permet de creer le pdf du Vandalisme Auto
     * 
     * @param declarationSinistre objet de declaration de sinsitre
     * @param isClientInternet booléen si le client provient d'internet
     * @param infosPortefeuille objet d'info du portefeuille
     * @return tableaux de byte
     */
    byte[] createPdfVandalismeAUTO(DeclarationSinistre declarationSinistre, Boolean isClientInternet,
                    InformationIntermediaire infosPortefeuille, boolean isCourtier);

}
