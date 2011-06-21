/**
 * 
 */
package fr.generali.gfb.ws.sinistre;

import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;

/**
 * @author ARUL Aguilane
 */
public interface ISinistreOrigineDeconnecteService {

    public void traitementSinistre(DeclarationSinistre declarationSinistre) throws DeclarationSinistreException;

}
