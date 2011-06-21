/**
 * 
 */
package fr.generali.gfb.ws.sinistre.email;

import fr.generali.espaceclient.proxy.generic.domain.InformationIntermediaire;
import fr.generali.gfb.ws.sinistre.DeclarationSinistreException;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;

/**
 * Service d'envoie de mail
 * 
 * @author Aguilane ARUL
 */
public interface IMailSinistreOrigineDeconnecteService {

    /**
     * Mail envoy� au Gestionnaire Generali (si Num�ro de contrat autre que � 9
     * caract�res alphanum�riques d�butant par � 12 � ou � 160 �)
     * 
     * @throws DeclarationSinistreException
     */
    public void sendMailGestionnaireGenerali(DeclarationSinistre declarationSinistre, String attachementName,
                    byte[] attachement,boolean isCourtier) throws DeclarationSinistreException;

    /**
     * Mail envoy� au client / internaute
     */
    public void sendMailClientInternaute(DeclarationSinistre declarationSinistre, String attachementName,
                    byte[] attachement, boolean isCourtier,InformationIntermediaire infosPortefeuille);

}
