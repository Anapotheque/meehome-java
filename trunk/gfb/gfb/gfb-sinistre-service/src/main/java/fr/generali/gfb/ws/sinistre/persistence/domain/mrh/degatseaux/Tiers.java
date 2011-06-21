/**
 * 
 */
package fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author ARUL Aguilane
 */
@Embeddable
public class Tiers {

    @Column(name = "TIERS_NOM")
    private String nom;

    @Column(name = "TIERS_PRENOM")
    private String prenom;

    @Column(name = "TIERS_ADRESSE")
    private String adresse;

    @Column(name = "TIERS_CODE_POSTAL")
    private String codePostal;

    @Column(name = "TIERS_VILLE")
    private String ville;

    /**
     * assureur de la personnes tiers ayant subi des conséquences.
     */
    @Column(name = "TIERS_ASSUREUR")
    private String assureur;

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
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
     * @return the assureur
     */
    public String getAssureur() {
        return assureur;
    }

    /**
     * @param assureur the assureur to set
     */
    public void setAssureur(String assureur) {
        this.assureur = assureur;
    }

}
