package fr.generali.gfb.ws.sinistre.dto;

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public abstract class AbstractDeclarationSinistreInput implements IDeclarationSinistreInput {

    /**
     * Cette chaine est l'équivalent d'"origine" et doit être obligatoirement
     * renseignée si "origine" ne l'est pas.
     */
    private String origine;

    /**
     * Informations relatives au contrat
     */
    private AssureInput assure;

    /**
     * Informations relatives au sinistre
     */
    private Date dateSinistre;

    /**
     * Mail Trieste renseiggné dans le cas d'un courtier Trieste (sinon chaine
     * vide)
     */
    protected String mailTrieste;

    /**
     * Full constructor
     * 
     * @param origine Provenance de la declaration de sinistre. Si l'origine est
     *            un espace connecté (type espace client), certains paramètres
     *            en entrée du service sont obligatoires et leurs valeurs sont
     *            trustables. Dans le cas déconnecté, aucunne vérification n'est
     *            faite.
     * @param dateSinistre
     * @param assure
     */
    protected AbstractDeclarationSinistreInput(String origineString, Date dateSinistre, AssureInput assure) {
        super();
        this.dateSinistre = dateSinistre;
        this.assure = assure;
        origine(origineString);
    }

    protected AbstractDeclarationSinistreInput() {
        super();
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    public Date getDateSinistre() {
        return dateSinistre;
    }

    public AssureInput getAssure() {
        return assure;
    }

    public String getOrigine() {
        return origine;
    }

    public AbstractDeclarationSinistreInput assure(AssureInput assure) {
        this.assure = assure;
        return this;
    }

    public AbstractDeclarationSinistreInput dateSinistre(Date dateSinistre) {
        this.dateSinistre = dateSinistre;
        return this;
    }

    /**
     * Provenance de la declaration de sinistre. Si l'origine est un espace
     * connecté (type espace client), certains paramètres en entrée du service
     * sont obligatoires et leurs valeurs sont trustables. Dans le cas
     * déconnecté, aucunne vérification n'est faite. Les valeurs disponibles
     * sont celles de l'enum OrigineDeclaration
     */
    public void setOrigine(String origine) {
        this.origine = origine;
    }

    /**
     * @param assure the assure to set
     */
    public void setAssure(AssureInput assure) {
        this.assure = assure;
    }

    /**
     * @param dateSinistre the dateSinistre to set
     */
    public void setDateSinistre(Date dateSinistre) {
        this.dateSinistre = dateSinistre;
    }

    public AbstractDeclarationSinistreInput origine(String origine) {
        this.origine = origine;
        return this;
    }

    public String getMailTrieste() {
        return mailTrieste;
    }

    public void setMailTrieste(String mailTrieste) {
        this.mailTrieste = mailTrieste;
    }

    public abstract AbstractDeclarationSinistreInput mailTrieste(String mailTrieste);
}
