package fr.generali.gfb.ws.sinistre.email;

import java.text.MessageFormat;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import fr.generali.espaceclient.common.service.mail.IMailService;
import fr.generali.espaceclient.common.util.HtmlTextBuilder;
import fr.generali.espaceclient.proxy.generic.domain.Bureau;
import fr.generali.espaceclient.proxy.generic.domain.InformationIntermediaire;
import fr.generali.gfb.ws.sinistre.persistence.domain.common.DeclarationSinistre;

abstract public class AbstractMailSinistreService {
    @Autowired
    @Qualifier("sinistreProperties")
    protected Properties properties;

    /** Service technique d'envoi de mail */
    @Autowired
    protected IMailService mailSender;

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractMailSinistreService.class);

    private static final String EXPEDITEUR_CLIENT = "sinistre.email.expediteur.client";

    private void buildDefaultMailClientInternaute(HtmlTextBuilder htmlTextBuilder, boolean isGPROX, boolean isCourtier,
                    InformationIntermediaire infosPortefeuille) {
        htmlTextBuilder.appendLine("Madame, Monsieur,");
        htmlTextBuilder.appendEmptyLine(1);

        if (isCourtier) {
            htmlTextBuilder.appendLine("Vous venez de nous déclarer un sinistre en ligne. "
                            + "Vous trouverez en pièce jointe le récapitulatif des éléments que vous avez saisis. "
                            + "Votre déclaration va être transmise à " + "votre courtier "
                            + "qui vous recontactera rapidement.");
        } else {
            htmlTextBuilder.appendLine("Vous venez de nous déclarer un sinistre en ligne. "
                            + "Vous trouverez en pièce jointe le récapitulatif des éléments que vous avez saisis. "
                            + "Votre déclaration va être transmise à " + "votre interlocuteur Generali "
                            + "qui vous recontactera rapidement.");
        }

        htmlTextBuilder.appendEmptyLine(1);
        htmlTextBuilder.appendLine("Veuillez agréer, Madame, Monsieur, l'expression de nos sincères salutations.");
        htmlTextBuilder.appendEmptyLine(1);

        if (infosPortefeuille != null) {

            htmlTextBuilder.appendLine("Pour plus d'informations sur vos contrats : ");
            htmlTextBuilder.appendEmptyLine(1);

            // Init des coordonnées intermediaire
            final Bureau bureau = infosPortefeuille.getBureau();
            final String intermediraire = infosPortefeuille.getIntermediaire().getLibelle();

            if (isCourtier) {
                htmlTextBuilder.appendLine("Votre Cabinet de courtage : ");
            } else {
                htmlTextBuilder.appendLine("Votre agent : ");
            }

            if (intermediraire != null) {
                htmlTextBuilder.appendLine(intermediraire);
            }

            final String ligne2 = bureau.getLigne2();
            if (ligne2 != null && StringUtils.isNotBlank(ligne2)) {
                htmlTextBuilder.appendLine(ligne2);
            }

            final String ligne3 = bureau.getLigne3();
            if (ligne3 != null && StringUtils.isNotBlank(ligne3)) {
                htmlTextBuilder.appendLine(ligne3);
            }

            final String ligne4 = bureau.getLigne4();
            if (ligne4 != null && StringUtils.isNotBlank(ligne4)) {
                htmlTextBuilder.appendLine(ligne4);
            }

            final String ligne5 = bureau.getLigne5();
            if (ligne5 != null && StringUtils.isNotBlank(ligne5)) {
                htmlTextBuilder.appendLine(ligne5);
            }

            final String ligne6 = bureau.getLigne6();
            if (ligne6 != null && StringUtils.isNotBlank(ligne6)) {
                htmlTextBuilder.appendLine(ligne6);
            }

            final String ligne7 = bureau.getLigne7();
            if (ligne7 != null && StringUtils.isNotBlank(ligne7)) {
                htmlTextBuilder.appendLine(ligne7);
            }

            final String telephone = bureau.getTelephone();
            if (telephone != null && StringUtils.isNotBlank(telephone)) {
                htmlTextBuilder.appendLine("Tél : " + telephone);
            }

            final String fax = bureau.getFax();
            if (fax != null && StringUtils.isNotBlank(fax)) {
                htmlTextBuilder.appendLine("Fax : " + fax);
            }

            final String email = bureau.getEmail();
            if (email != null && StringUtils.isNotBlank(email)) {
                htmlTextBuilder.appendLine("Mail : " + email);
            }
        } else {
            htmlTextBuilder.appendLine("Votre Service Client Internet Generali");
        }

        htmlTextBuilder.appendEmptyLine(2);
        htmlTextBuilder.appendLine(htmlTextBuilder.formatString(
                        "Generali IARD, Société Anonyme - Entreprise régie par le Code des assurances "
                                        + "- au capital de 59 493 775 euros, "
                                        + "inscrite au RCS de Paris sous le numéro 552 062 663 "
                                        + "et dont le siège sociale est situé au "
                                        + "7 boulevard Haussmann 75456 Paris cedex 09", false, true));
        htmlTextBuilder.appendLine(htmlTextBuilder.formatString("Adresse de correspondance : "
                        + "Generali Iard - 7 boulevard Haussmann 75456 Paris cedex 09", false, true));
        // if (isGPROX) {
        // htmlTextBuilder.appendLine("Téléphone : 0 969 369 969");
        // } else {
        // htmlTextBuilder.appendLine("Téléphone : 01 58 38 40 00");
        // }
    }

    private void buildDefaultMailGestionnaireGenerali(HtmlTextBuilder htmlTextBuilder, boolean isCourtier) {

        htmlTextBuilder.appendLine("Bonjour,");
        htmlTextBuilder.appendEmptyLine(1);

        if (isCourtier) {
            htmlTextBuilder
                            .appendLine("Veuillez trouver ci-joint une déclaration de sinistre déposée par "
                                            + "un de vos assurés via son espace client Generali. "
                                            + "Merci de le contacter au plus vite pour lui accusé réception et donner la suite appropriée à sa déclaration de sinistre.");
            htmlTextBuilder.appendEmptyLine();
            htmlTextBuilder.appendLine("Cordialement,");
            htmlTextBuilder.appendEmptyLine(1);
            htmlTextBuilder.appendLine("Votre service Client Internet");

        } else {
            htmlTextBuilder.appendLine("Veuillez trouver ci-joint une déclaration de sinistre déposée par "
                            + "un de nos assurés sur notre site Internet. "
                            + "Merci de contacter notre assuré pour lui en accuser réception "
                            + "et y donner la suite appropriée.");
            htmlTextBuilder.appendEmptyLine();
            htmlTextBuilder.appendLine("Bien cordialement,");
            htmlTextBuilder.appendEmptyLine(1);
            htmlTextBuilder.appendLine("L'Équipe Internet");
        }
    }

    /**
     * Envoie le mail au client
     * 
     * @param declarationSinistre
     * @param attachementName
     * @param attachement
     * @param isCourtier
     */
    protected void sendMailClientInternaute(DeclarationSinistre declarationSinistre, String attachementName,
                    byte[] attachement, boolean isCourtier, InformationIntermediaire infosPortefeuille) {

        String destinataire = declarationSinistre.getAssure().getEmail();

        String expediteur = properties.getProperty(EXPEDITEUR_CLIENT);

        HtmlTextBuilder htmlTextBuilder = new HtmlTextBuilder(true, new StringBuffer());

        String objet = composeObjectMailClient();

        buildDefaultMailClientInternaute(htmlTextBuilder, "67".equals(declarationSinistre.getCodeCompagnie()),
                        isCourtier, infosPortefeuille);

        mailSender.sendMail(expediteur, new String[] {destinataire }, destinataire, objet, htmlTextBuilder.getBody()
                        .toString(), attachementName, attachement, htmlTextBuilder.isAsHtml());

        LOGGER.info(MessageFormat.format("Envoi de mail au client, expediteur:{0}, destinataire:{1}", new Object[] {
                        expediteur, destinataire }));

    }

    /**
     * Envoie le mail au gestionnaire
     * 
     * @param destinataire
     * @param attachementName
     * @param attachement
     */
    protected void sendMailGestionnaireGenerali(String destinataire, String attachementName, byte[] attachement,
                    boolean isCourtier) {

        String expediteur = properties.getProperty("sinistre.email.expediteur.gestionnaire_generali");

        HtmlTextBuilder htmlTextBuilder = new HtmlTextBuilder(true, new StringBuffer());

        String objet = composeObjectMailGestionnaire();

        buildDefaultMailGestionnaireGenerali(htmlTextBuilder, isCourtier);

        mailSender.sendMail(expediteur, new String[] {destinataire }, destinataire, objet, htmlTextBuilder.getBody()
                        .toString(), attachementName, attachement, htmlTextBuilder.isAsHtml());

        LOGGER.info(MessageFormat.format("Envoi de mail au gestionnaire Generali, expediteur:{0}, destinataire:{1}",
                        new Object[] {expediteur, destinataire }));
    }

    /**
     * @return L'objet du mail à envoyer au client
     */
    abstract protected String composeObjectMailClient();

    /**
     * @return L'objet du mail à envoyer au gestionnaire
     */
    abstract protected String composeObjectMailGestionnaire();
}
