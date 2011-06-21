package fr.generali.gfb.ws.sinistre.dto.mrh;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class BienInput {
    /*
     * Informations relatives au bien
     */
    private Boolean isResidencePrincipale;

    private String adresse;

    private String codePostal;

    private String ville;

    private String qualite;

    public BienInput(Boolean isResidencePrincipale, String adresse, String codePostal, String ville, String qualite) {
        super();
        this.isResidencePrincipale = isResidencePrincipale;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.qualite = qualite;
    }

    public BienInput() {
        super();
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    public Boolean getIsResidencePrincipale() {
        return isResidencePrincipale;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public String getVille() {
        return ville;
    }

    public String getQualite() {
        return qualite;
    }

    public BienInput isResidencePrincipale(Boolean isResidencePrincipale) {
        this.isResidencePrincipale = isResidencePrincipale;
        return this;
    }

    public BienInput adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public BienInput codePostal(String codePostal) {
        this.codePostal = codePostal;
        return this;
    }

    public BienInput ville(String ville) {
        this.ville = ville;
        return this;
    }

    public BienInput qualite(String qualite) {
        this.qualite = qualite;
        return this;
    }

    /**
     * @param isResidencePrincipale the isResidencePrincipale to set
     */
    public void setIsResidencePrincipale(Boolean isResidencePrincipale) {
        this.isResidencePrincipale = isResidencePrincipale;
    }

    /**
     * @param adresse the adresse to set
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * @param codePostal the codePostal to set
     */
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    /**
     * @param ville the ville to set
     */
    public void setVille(String ville) {
        this.ville = ville;
    }

    /**
     * @param qualite the qualite to set
     */
    public void setQualite(String qualite) {
        this.qualite = qualite;
    }

}
