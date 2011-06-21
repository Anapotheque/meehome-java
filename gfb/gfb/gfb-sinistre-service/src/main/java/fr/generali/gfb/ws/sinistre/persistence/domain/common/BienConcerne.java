/**
 * 
 */
package fr.generali.gfb.ws.sinistre.persistence.domain.common;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author ARUL Aguilane
 */
@Embeddable
public class BienConcerne {

    /**
     * Il s�agit de ma r�sidence principale
     */
    @Column(name = "IS_RESIDENCE_PRINCIPALE")
    private Boolean isResidencePrincipale;

    /**
     * adresse si diff�rente de la r�sidence principale de l'assur�.
     */
    @Column(name = "ADRESSE")
    private String adresse;

    /**
     * code postal si diff�rente de la r�sidence principale de l'assur�.
     */
    @Column(name = "CODE_POSTAL")
    private String codePostal;

    /**
     * ville si diff�rente de la r�sidence principale de l'assur�.
     */
    @Column(name = "VILLE")
    private String ville;

    /**
     * Qualit� de l'assur�:
     * <ul>
     * <li>Propri�taire occupant</li>
     * <li>Propri�taire non occupant</li>
     * <li>Locataire</li>
     * <li>Copropri�taire occupant</li>
     * <li>Copropri�taire non occupant</li>
     * </ul>
     */
    @Column(name = "QUALITE")
    private String qualite;

    /**
     * @return the isResidencePrincipale
     */
    public Boolean getIsResidencePrincipale() {
        return isResidencePrincipale;
    }

    /**
     * @param isResidencePrincipale the isResidencePrincipale to set
     */
    public void setIsResidencePrincipale(Boolean isResidencePrincipale) {
        this.isResidencePrincipale = isResidencePrincipale;
    }

    /**
     * @return the adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * @param adresse the adresse to set
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * @return the codePostal
     */
    public String getCodePostal() {
        return codePostal;
    }

    /**
     * @param codePostal the codePostal to set
     */
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    /**
     * @return the ville
     */
    public String getVille() {
        return ville;
    }

    /**
     * @param ville the ville to set
     */
    public void setVille(String ville) {
        this.ville = ville;
    }

    /**
     * @return the qualite
     */
    public String getQualite() {
        return qualite;
    }

    /**
     * @param qualite the qualite to set
     */
    public void setQualite(String qualite) {
        this.qualite = qualite;
    }

}
