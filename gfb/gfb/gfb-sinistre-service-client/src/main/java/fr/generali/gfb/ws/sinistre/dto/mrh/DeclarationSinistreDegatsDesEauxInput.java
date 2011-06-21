package fr.generali.gfb.ws.sinistre.dto.mrh;

import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import fr.generali.gfb.ws.sinistre.dto.AbstractDeclarationSinistreInput;
import fr.generali.gfb.ws.sinistre.dto.AssureInput;

public class DeclarationSinistreDegatsDesEauxInput extends AbstractDeclarationSinistreInput {

    private BienInput bien;

    /*
     * Informations relatives aux causes du sinistre
     */
    private Collection<String> causes;

    private Collection<String> infiltrations;

    private String autreCause;

    private String origineSinistre;

    private Boolean isCauseReparee;

    /*
     * Informations relatives aux consequences
     */

    private Boolean isDommageSubi;

    private String isDommageSubiParTiers;

    private String nomTiers;

    private String prenomTiers;

    private String adresseTiers;

    private String codePostalTiers;

    private String villeTiers;

    private String assureurTiers;

    /*
     * Informations relatives aux dommages
     */
    private Integer nbPiecesEndommagees;

    private Collection<PieceInput> pieces;

    /**
     * Default constructor
     */
    public DeclarationSinistreDegatsDesEauxInput() {
        super();
    }

    /**
     * Full constructor
     * 
     * @param dateSinistre
     * @param assure
     * @param isResidencePrincipale
     * @param adresseBienConcerne
     * @param codePostalBienConcerne
     * @param villeBienConcerne
     * @param qualite
     * @param causes
     * @param infiltrations
     * @param autreCause
     * @param origineSinistre
     * @param isCauseReparee
     * @param isDommageSubi
     * @param isDommageSubiParTiers
     * @param nomTiers
     * @param prenomTiers
     * @param adresseTiers
     * @param codePostalTiers
     * @param villeTiers
     * @param assureurTiers
     * @param nbPiecesEndommagees
     * @param pieces
     */
    public DeclarationSinistreDegatsDesEauxInput(String origineString, Date dateSinistre, AssureInput assure,
                    BienInput bien, Collection<String> causes, Collection<String> infiltrations, String autreCause,
                    String origineSinistre, Boolean isCauseReparee, Boolean isDommageSubi,
                    String isDommageSubiParTiers, String nomTiers, String prenomTiers, String adresseTiers,
                    String codePostalTiers, String villeTiers, String assureurTiers, Integer nbPiecesEndommagees,
                    Collection<PieceInput> pieces) {
        super(origineString, dateSinistre, assure);
        this.bien = bien;
        this.causes = causes;
        this.infiltrations = infiltrations;
        this.autreCause = autreCause;
        this.origineSinistre = origineSinistre;
        this.isCauseReparee = isCauseReparee;
        this.isDommageSubi = isDommageSubi;
        this.isDommageSubiParTiers = isDommageSubiParTiers;
        this.nomTiers = nomTiers;
        this.prenomTiers = prenomTiers;
        this.adresseTiers = adresseTiers;
        this.codePostalTiers = codePostalTiers;
        this.villeTiers = villeTiers;
        this.assureurTiers = assureurTiers;
        this.nbPiecesEndommagees = nbPiecesEndommagees;
        this.pieces = pieces;
    }

    @Override
    public String toString() {
        return super.toString() + ReflectionToStringBuilder.toString(this);
    }

    public Collection<String> getCauses() {
        return causes;
    }

    public Collection<String> getInfiltrations() {
        return infiltrations;
    }

    public String getAutreCause() {
        return autreCause;
    }

    public String getOrigineSinistre() {
        return origineSinistre;
    }

    public Boolean getIsCauseReparee() {
        return isCauseReparee;
    }

    public Boolean getIsDommageSubi() {
        return isDommageSubi;
    }

    public String getIsDommageSubiParTiers() {
        return isDommageSubiParTiers;
    }

    public String getNomTiers() {
        return nomTiers;
    }

    public String getPrenomTiers() {
        return prenomTiers;
    }

    public String getAdresseTiers() {
        return adresseTiers;
    }

    public String getCodePostalTiers() {
        return codePostalTiers;
    }

    public String getVilleTiers() {
        return villeTiers;
    }

    public String getAssureurTiers() {
        return assureurTiers;
    }

    public Integer getNbPiecesEndommagees() {
        return nbPiecesEndommagees;
    }

    public Collection<PieceInput> getPieces() {
        return pieces;
    }

    public BienInput getBien() {
        return bien;
    }

    public DeclarationSinistreDegatsDesEauxInput bien(BienInput bien) {
        this.bien = bien;
        return this;
    }

    public DeclarationSinistreDegatsDesEauxInput causes(Collection<String> causes) {
        this.causes = causes;
        return this;
    }

    public DeclarationSinistreDegatsDesEauxInput infiltrations(Collection<String> infiltrations) {
        this.infiltrations = infiltrations;
        return this;
    }

    public DeclarationSinistreDegatsDesEauxInput autreCause(String autreCause) {
        this.autreCause = autreCause;
        return this;
    }

    public DeclarationSinistreDegatsDesEauxInput origineSinistre(String origineSinistre) {
        this.origineSinistre = origineSinistre;
        return this;
    }

    public DeclarationSinistreDegatsDesEauxInput isCauseReparee(Boolean isCauseReparee) {
        this.isCauseReparee = isCauseReparee;
        return this;
    }

    public DeclarationSinistreDegatsDesEauxInput isDommageSubi(Boolean isDommageSubi) {
        this.isDommageSubi = isDommageSubi;
        return this;
    }

    public DeclarationSinistreDegatsDesEauxInput isDommageSubiParTiers(String isDommageSubiParTiers) {
        this.isDommageSubiParTiers = isDommageSubiParTiers;
        return this;
    }

    public DeclarationSinistreDegatsDesEauxInput nomTiers(String nomTiers) {
        this.nomTiers = nomTiers;
        return this;
    }

    public DeclarationSinistreDegatsDesEauxInput prenomTiers(String prenomTiers) {
        this.prenomTiers = prenomTiers;
        return this;
    }

    public DeclarationSinistreDegatsDesEauxInput adresseTiers(String adresseTiers) {
        this.adresseTiers = adresseTiers;
        return this;
    }

    public DeclarationSinistreDegatsDesEauxInput codePostalTiers(String codePostalTiers) {
        this.codePostalTiers = codePostalTiers;
        return this;
    }

    public DeclarationSinistreDegatsDesEauxInput villeTiers(String villeTiers) {
        this.villeTiers = villeTiers;
        return this;
    }

    public DeclarationSinistreDegatsDesEauxInput assureurTiers(String assureurTiers) {
        this.assureurTiers = assureurTiers;
        return this;
    }

    public DeclarationSinistreDegatsDesEauxInput nbPiecesEndommagees(Integer nbPiecesEndommagees) {
        this.nbPiecesEndommagees = nbPiecesEndommagees;
        return this;
    }

    public DeclarationSinistreDegatsDesEauxInput pieces(Collection<PieceInput> pieces) {
        this.pieces = pieces;
        return this;
    }

    @Override
    public DeclarationSinistreDegatsDesEauxInput assure(AssureInput assure) {
        super.assure(assure);
        return this;
    }

    @Override
    public DeclarationSinistreDegatsDesEauxInput dateSinistre(Date dateSinistre) {
        super.dateSinistre(dateSinistre);
        return this;
    }

    @Override
    public DeclarationSinistreDegatsDesEauxInput origine(String origineString) {
        super.origine(origineString);
        return this;
    }

    /**
     * @param bien the bien to set
     */
    public void setBien(BienInput bien) {
        this.bien = bien;
    }

    /**
     * @param causes the causes to set
     */
    public void setCauses(Collection<String> causes) {
        this.causes = causes;
    }

    /**
     * @param infiltrations the infiltrations to set
     */
    public void setInfiltrations(Collection<String> infiltrations) {
        this.infiltrations = infiltrations;
    }

    /**
     * @param autreCause the autreCause to set
     */
    public void setAutreCause(String autreCause) {
        this.autreCause = autreCause;
    }

    /**
     * @param origineSinistre the origineSinistre to set
     */
    public void setOrigineSinistre(String origineSinistre) {
        this.origineSinistre = origineSinistre;
    }

    /**
     * @param isCauseReparee the isCauseReparee to set
     */
    public void setIsCauseReparee(Boolean isCauseReparee) {
        this.isCauseReparee = isCauseReparee;
    }

    /**
     * @param isDommageSubi the isDommageSubi to set
     */
    public void setIsDommageSubi(Boolean isDommageSubi) {
        this.isDommageSubi = isDommageSubi;
    }

    /**
     * @param isDommageSubiParTiers the isDommageSubiParTiers to set
     */
    public void setIsDommageSubiParTiers(String isDommageSubiParTiers) {
        this.isDommageSubiParTiers = isDommageSubiParTiers;
    }

    /**
     * @param nomTiers the nomTiers to set
     */
    public void setNomTiers(String nomTiers) {
        this.nomTiers = nomTiers;
    }

    /**
     * @param prenomTiers the prenomTiers to set
     */
    public void setPrenomTiers(String prenomTiers) {
        this.prenomTiers = prenomTiers;
    }

    /**
     * @param adresseTiers the adresseTiers to set
     */
    public void setAdresseTiers(String adresseTiers) {
        this.adresseTiers = adresseTiers;
    }

    /**
     * @param codePostalTiers the codePostalTiers to set
     */
    public void setCodePostalTiers(String codePostalTiers) {
        this.codePostalTiers = codePostalTiers;
    }

    /**
     * @param villeTiers the villeTiers to set
     */
    public void setVilleTiers(String villeTiers) {
        this.villeTiers = villeTiers;
    }

    /**
     * @param assureurTiers the assureurTiers to set
     */
    public void setAssureurTiers(String assureurTiers) {
        this.assureurTiers = assureurTiers;
    }

    /**
     * @param nbPiecesEndommagees the nbPiecesEndommagees to set
     */
    public void setNbPiecesEndommagees(Integer nbPiecesEndommagees) {
        this.nbPiecesEndommagees = nbPiecesEndommagees;
    }

    /**
     * @param pieces the pieces to set
     */
    public void setPieces(Collection<PieceInput> pieces) {
        this.pieces = pieces;
    }

    @Override
    public DeclarationSinistreDegatsDesEauxInput mailTrieste(String mailTrieste) {
        this.mailTrieste = mailTrieste;
        return this;
    }
}
