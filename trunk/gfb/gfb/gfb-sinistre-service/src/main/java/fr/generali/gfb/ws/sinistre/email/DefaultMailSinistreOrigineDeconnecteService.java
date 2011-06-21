/**
 * 
 */
package fr.generali.gfb.ws.sinistre.email;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import fr.generali.espaceclient.proxy.generic.domain.InformationIntermediaire;
import fr.generali.gfb.ws.sinistre.DeclarationSinistreException;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;

/**
 */
@Service
public class DefaultMailSinistreOrigineDeconnecteService extends AbstractMailSinistreService implements
                IMailSinistreOrigineDeconnecteService {

    /** Service technique d'envoi de mail */
    private static final String EMAIL_RESEAU_COURTIER =
                    "sinistre.email.destinataire.gestionnaire_generali.reseau_agent_courtier";

    private static final String EMAIL_RESEAU_SALARIE =
                    "sinistre.email.destinataire.gestionnaire_generali.reseau_salarie";

    protected static final String[] PREFIX_NUM_CONTRATS_RESEAU_SALARIE = {"12", "16", "25" };

    protected String getGestionnaireGeneraliEmail(String numContrat) throws DeclarationSinistreException {

        boolean isReseauSalarie = false;
        String destinataire;

        isReseauSalarie = isReseauSalarie(numContrat);

        if (isReseauSalarie) {

            destinataire = properties.getProperty(EMAIL_RESEAU_SALARIE);
        } else {
            destinataire = properties.getProperty(EMAIL_RESEAU_COURTIER);
        }

        if (StringUtils.isEmpty(destinataire)) {
            throw new DeclarationSinistreException("Email du réseau courtier ou salarié vide introuvale");
        }

        return destinataire;
    }

    static boolean isReseauSalarie(String numContrat) {
        if (StringUtils.isNotBlank(numContrat) && numContrat.length() == 9) {
            for (String prefixNumContrat : PREFIX_NUM_CONTRATS_RESEAU_SALARIE) {
                if (numContrat.startsWith(prefixNumContrat)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void sendMailClientInternaute(DeclarationSinistre declarationSinistre, String attachementName,
                    byte[] attachement, boolean isCourtier,InformationIntermediaire infosPortefeuille) {

        super.sendMailClientInternaute(declarationSinistre, attachementName, attachement,isCourtier,infosPortefeuille);
    }

    public void sendMailGestionnaireGenerali(DeclarationSinistre declarationSinistre, String attachementName,
                    byte[] attachement,boolean isCourtier) throws DeclarationSinistreException {
        String destinataire = getGestionnaireGeneraliEmail(declarationSinistre.getNumeroContrat());
        super.sendMailGestionnaireGenerali(destinataire, attachementName, attachement, isCourtier);
    }

    @Override
    protected String composeObjectMailClient() {
        return "Votre déclaration de sinistre en ligne";
    }

    @Override
    protected String composeObjectMailGestionnaire() {
        return "Déclaration de sinistre à traiter";
    }

}
