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
     * Mail envoy� au Gestionnaire Generali (si Num�ro de contrat autre que � 9
     * caract�res alphanum�riques d�butant par � 12 � ou � 160 �)
     * 
     * @param declarationSinistre la d�claration de sinistre
     * @param attachementName le nom de la pi�ce jointe
     * @param attachement la pi�ce jointe
     * @param mailBureauPrincipal l'email du bureau principal
     */
    void sendMailGestionnaireGenerali(String attachementName, byte[] attachement, String mailBureauPrincipal,boolean isCourtier);

    /**
     * Mail envoy� au client / internaute
     * 
     * @param declarationSinistre la d�claration de sinistre
     * @param attachementName le nom de la pi�ce jointe
     * @param attachement la pi�ce jointe
     */
    void sendMailClientInternaute(DeclarationSinistre declarationSinistre, String attachementName, byte[] attachement, boolean isCourtier,InformationIntermediaire infosPortefeuille);

}
