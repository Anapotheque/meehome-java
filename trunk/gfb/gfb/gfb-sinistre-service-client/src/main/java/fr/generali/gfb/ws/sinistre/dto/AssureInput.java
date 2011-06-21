package fr.generali.gfb.ws.sinistre.dto;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class AssureInput {

    private String numeroContrat;
    private String numeroClient;
    private String idRceClient;
    private String codeCompagnie;
    private String codePortefeuille;
    private String nom;
    private String prenom;
    private String adresse;
    private String codePostal;
    private String ville;
    private String email;
    private String telephoneMobile;
    private String telephoneDomicile;
    private String telephoneBureau;
	private String isCourtier;
	private String reseau;
    
    public String getReseau() {
		return reseau;
	}

	public void setReseau(String reseau) {
		this.reseau = reseau;
	}

	/**
     * @param numeroContrat the numeroContrat to set
     */
    public void setNumeroContrat(String numeroContrat) {
        this.numeroContrat = numeroContrat;
    }

    /**
     * @param numeroClient the numeroClient to set
     */
    public void setNumeroClient(String numeroClient) {
        this.numeroClient = numeroClient;
    }

    /**
     * @param idRceClient the idRceClient to set
     */
    public void setIdRceClient(String idRceClient) {
        this.idRceClient = idRceClient;
    }

    /**
     * @param codeCompagnie the codeCompagnie to set
     */
    public void setCodeCompagnie(String codeCompagnie) {
        this.codeCompagnie = codeCompagnie;
    }

    /**
     * @param codePortefeuille the codePortefeuille to set
     */
    public void setCodePortefeuille(String codePortefeuille) {
        this.codePortefeuille = codePortefeuille;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
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
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param telephoneMobile the telephoneMobile to set
     */
    public void setTelephoneMobile(String telephoneMobile) {
        this.telephoneMobile = telephoneMobile;
    }

    /**
     * @param telephoneDomicile the telephoneDomicile to set
     */
    public void setTelephoneDomicile(String telephoneDomicile) {
        this.telephoneDomicile = telephoneDomicile;
    }

    /**
     * @param telephoneBureau the telephoneBureau to set
     */
    public void setTelephoneBureau(String telephoneBureau) {
        this.telephoneBureau = telephoneBureau;
    }

    /**
     * Default constructor
     */
    public AssureInput() {
        super();
    }

    /**
     * Full constructor
     * 
     * @param numeroContrat
     * @param numeroClient
     * @param idRceClient
     * @param codeCompagnie
     * @param codePortefeuille
     * @param nom
     * @param prenom
     * @param adresse
     * @param codePostal
     * @param ville
     * @param qualite
     * @param email
     * @param telephoneMobile
     * @param telephoneDomicile
     * @param telephoneBureau
     */
    public AssureInput(String numeroContrat, String numeroClient, String idRceClient, String codeCompagnie,
                    String codePortefeuille, String nom, String prenom, String adresse, String codePostal,
                    String ville, String email, String telephoneMobile, String telephoneDomicile, String telephoneBureau, String isCourtier) {
        super();
        this.numeroContrat = numeroContrat;
        this.numeroClient = numeroClient;
        this.idRceClient = idRceClient;
        this.codeCompagnie = codeCompagnie;
        this.codePortefeuille = codePortefeuille;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.email = email;
        this.telephoneMobile = telephoneMobile;
        this.telephoneDomicile = telephoneDomicile;
        this.telephoneBureau = telephoneBureau;
        this.isCourtier = isCourtier;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    public String getNumeroContrat() {
        return numeroContrat;
    }

    public String getNumeroClient() {
        return numeroClient;
    }

    public String getIdRceClient() {
        return idRceClient;
    }

    public String getCodeCompagnie() {
        return codeCompagnie;
    }

    public String getCodePortefeuille() {
        return codePortefeuille;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
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

    public String getEmail() {
        return email;
    }

    public String getTelephoneMobile() {
        return telephoneMobile;
    }

    public String getTelephoneDomicile() {
        return telephoneDomicile;
    }

    public String getTelephoneBureau() {
        return telephoneBureau;
    }

    public AssureInput numeroContrat(String numeroContrat) {
        this.numeroContrat = numeroContrat;
        return this;
    }

    public AssureInput numeroClient(String numeroClient) {
        this.numeroClient = numeroClient;
        return this;
    }

    public AssureInput idRceClient(String idRceClient) {
        this.idRceClient = idRceClient;
        return this;
    }

    public AssureInput codeCompagnie(String codeCompagnie) {
        this.codeCompagnie = codeCompagnie;
        return this;
    }

    public AssureInput codePortefeuille(String codePortefeuille) {
        this.codePortefeuille = codePortefeuille;
        return this;
    }

    public AssureInput nom(String nom) {
        this.nom = nom;
        return this;
    }

    public AssureInput prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public AssureInput adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public AssureInput codePostal(String codePostal) {
        this.codePostal = codePostal;
        return this;
    }

    public AssureInput ville(String ville) {
        this.ville = ville;
        return this;
    }

    public AssureInput email(String email) {
        this.email = email;
        return this;
    }

    public AssureInput telephoneMobile(String telephoneMobile) {
        this.telephoneMobile = telephoneMobile;
        return this;
    }

    public AssureInput telephoneDomicile(String telephoneDomicile) {
        this.telephoneDomicile = telephoneDomicile;
        return this;
    }

    public AssureInput telephoneBureau(String telephoneBureau) {
        this.telephoneBureau = telephoneBureau;
        return this;
    }
    
    public AssureInput isCourtier(String isCourtier) {
        this.isCourtier = isCourtier;
        return this;
    }

	public void setIsCourtier(String isCourtier) {
		this.isCourtier = isCourtier;
	}

	public String getIsCourtier() {
		return isCourtier;
	}

}
