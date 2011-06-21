/**
 * 
 */
package fr.generali.gfb.ws.sinistre.persistence.domain.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ARUL Aguilane
 */

/**
 * informations concernant l'assuré
 */
@Entity
@Table(name = "DS_ASSURE")
public class Assure {

    /**
     * Identifiant pour l'entite Hibernate
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ASSURE_NOM")
    private String nom;

    @Column(name = "ASSURE_PRENOM")
    private String prenom;

    @Column(name = "ASSURE_ADRESSE")
    private String adresse;

    @Column(name = "ASSURE_CODE_POSTAL")
    private String codePostal;

    @Column(name = "ASSURE_VILLE")
    private String ville;

    /**
     * <ul>
     * <li>Propriétaire occupant</li>
     * <li>Propriétaire non occupant</li>
     * <li>Locataire (valeur par défaut)</li>
     * <li>Copropriétaire occupant</li>
     * <li>Copropriétaire non occupant</li>
     * </ul>
     */
    @Column(name = "ASSURE_QUALITE")
    private String qualite;

    @Column(name = "ASSURE_EMAIL")
    private String email;

    @Column(name = "ASSURE_TELEPHONE_MOBILE")
    private String telephoneMobile;

    @Column(name = "ASSURE_TELEPHONE_DOMICILE")
    private String telephoneDomicile;

    @Column(name = "ASSURE_TELEPHONE_BUREAU")
    private String telephoneBureau;

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

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the telephoneMobile
     */
    public String getTelephoneMobile() {
        return telephoneMobile;
    }

    /**
     * @param telephoneMobile the telephoneMobile to set
     */
    public void setTelephoneMobile(String telephoneMobile) {
        this.telephoneMobile = telephoneMobile;
    }

    /**
     * @return the telephoneDomicile
     */
    public String getTelephoneDomicile() {
        return telephoneDomicile;
    }

    /**
     * @param telephoneDomicile the telephoneDomicile to set
     */
    public void setTelephoneDomicile(String telephoneDomicile) {
        this.telephoneDomicile = telephoneDomicile;
    }

    /**
     * @return the telephoneBureau
     */
    public String getTelephoneBureau() {
        return telephoneBureau;
    }

    /**
     * @param telephoneBureau the telephoneBureau to set
     */
    public void setTelephoneBureau(String telephoneBureau) {
        this.telephoneBureau = telephoneBureau;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

}
