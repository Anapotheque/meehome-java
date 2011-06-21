/**
 * 
 */
package fr.generali.gfb.ws.sinistre;

import fr.generali.espaceclient.proxy.generic.domain.InformationIntermediaire;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;

/**
 * Service utilisé dans le cas d'une origine connectée (type espace client)
 * 
 * @author ARUL Aguilane
 */
public interface ISinistreOrigineConnecteService {

    /**
     * @param declarationSinistre la déclaration de sinistre
     * @param mailBureauPrincipal l'email du bureau principal
     * @param isClientInternet booléen déterminant si le client provient
     *            d'Internet
     * @param infosPortefeuille les informations du portefeuille
     */
    void traitementSinistreMrh(DeclarationSinistre declarationSinistre, String mailBureauPrincipal,
                    Boolean isClientInternet, InformationIntermediaire infosPortefeuille, String mailTrieste, boolean isCourtier);

    /**
     * @param declarationSinistre la déclaration de sinistre
     * @param mailBureauPrincipal l'email du bureau principal
     * @param isClientInternet booléen déterminant si le client provient
     *            d'Internet
     * @param infosPortefeuille les informations du portefeuille
     */
    void traitementSinistreAuto(DeclarationSinistre declarationSinistre, String mailBureauPrincipal,
                    Boolean isClientInternet, InformationIntermediaire infosPortefeuille, String mailTrieste, boolean isCourtier);

}
