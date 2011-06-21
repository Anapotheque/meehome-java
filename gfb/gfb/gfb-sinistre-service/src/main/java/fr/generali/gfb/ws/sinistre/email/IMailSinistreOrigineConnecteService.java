/**
 * 
 */
package fr.generali.gfb.ws.sinistre.email;

import fr.generali.espaceclient.proxy.generic.domain.InformationIntermediaire;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;

/**
 * Service d'envoie de mail
 * 
 * @author Aguilane ARUL
 */
public interface IMailSinistreOrigineConnecteService {

    /**
     * Mail envoyé au Gestionnaire Generali (si Numéro de contrat autre que à 9
     * caractères alphanumériques débutant par « 12 » ou « 160 »)
     * 
     * @param declarationSinistre la déclaration de sinistre
     * @param attachementName le nom de la pièce jointe
     * @param attachement la pièce jointe
     * @param mailBureauPrincipal l'email du bureau principal
     */
    void sendMailGestionnaireGenerali(String attachementName, byte[] attachement, String mailBureauPrincipal,boolean isCourtier);

    /**
     * Mail envoyé au client / internaute
     * 
     * @param declarationSinistre la déclaration de sinistre
     * @param attachementName le nom de la pièce jointe
     * @param attachement la pièce jointe
     */
    void sendMailClientInternaute(DeclarationSinistre declarationSinistre, String attachementName, byte[] attachement, boolean isCourtier,InformationIntermediaire infosPortefeuille);

}
