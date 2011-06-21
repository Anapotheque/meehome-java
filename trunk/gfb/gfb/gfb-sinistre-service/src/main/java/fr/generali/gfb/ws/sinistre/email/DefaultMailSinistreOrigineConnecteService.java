/**
 * 
 */
package fr.generali.gfb.ws.sinistre.email;

import org.springframework.stereotype.Service;

import fr.generali.espaceclient.proxy.generic.domain.InformationIntermediaire;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;
import fr.generali.gfb.ws.sinistre.util.EspaceClientEnvironnementEnum;

/**
 * @author ARUL Aguilane
 */
@Service
public class DefaultMailSinistreOrigineConnecteService extends AbstractMailSinistreService implements
                IMailSinistreOrigineConnecteService {

    /** libellé client internaute */
    private static final String CLIENT_INTERNAUTE = "ClientInternaute";

    /** libellé gestionnaire Generali */
    private static final String GESTIONNAIRE_GENERALI = "GestionnaireGenerali";

    /**
     * @see fr.generali.melovie.sinistre.services.email.IMailSinistreDEService#sendMailClientInternaute(fr.generali.melovie.sinistre.domain.common.DeclarationSinistre,
     *      java.lang.String, byte[])
     */
    @Override
    public void sendMailClientInternaute(DeclarationSinistre declarationSinistre, String attachementName,
                    byte[] attachement, boolean isCourtier,InformationIntermediaire infosPortefeuille) {
        super.sendMailClientInternaute(declarationSinistre, attachementName, attachement,isCourtier,infosPortefeuille);
    }

    /**
     * @see fr.generali.melovie.sinistre.services.email.IMailSinistreDEService#sendMailGestionnaireGenerali(fr.generali.melovie.sinistre.domain.common.DeclarationSinistre,
     *      java.lang.String, byte[])
     */
    public void sendMailGestionnaireGenerali(String attachementName, byte[] attachement, String mailDestinataire,boolean isCourtier) {
        super.sendMailGestionnaireGenerali(mailDestinataire, attachementName, attachement,isCourtier);
    }

    /**
     * retourne l'objet du mail selon l'environnement et le destinataire
     * 
     * @param destinataire indique si le destinataire est gestionnaire Generali
     *            ou client Internet
     * @return l'objet du mail
     */
    private String composeObject(String destinataire) {
        String objet = "";

        final EspaceClientEnvironnementEnum env =
                        EspaceClientEnvironnementEnum.findByValue(properties
                                        .getProperty("espaceclient.client.environnement"));

        if (env != null && !env.equals(EspaceClientEnvironnementEnum.PRODUCTION)) {
            objet = "ENVIRONNEMENT DE " + env.getCode() + " : ";
        }

        if (GESTIONNAIRE_GENERALI.equals(destinataire)) {
            objet += "Déclaration de sinistre à traiter";
        } else if (CLIENT_INTERNAUTE.equals(destinataire)) {
            objet += "Votre déclaration de sinistre en ligne";
        }

        return objet;
    }

    @Override
    protected String composeObjectMailClient() {
        return composeObject(CLIENT_INTERNAUTE);
    }

    @Override
    protected String composeObjectMailGestionnaire() {
        return composeObject(GESTIONNAIRE_GENERALI);
    }

}
