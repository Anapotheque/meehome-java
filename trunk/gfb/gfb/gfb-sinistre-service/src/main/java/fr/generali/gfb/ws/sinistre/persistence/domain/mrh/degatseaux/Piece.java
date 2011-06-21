/**
 * 
 */
package fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ARUL Aguilane
 */
@Entity
@Table(name = "DS_DDE_PIECE")
public class Piece {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Type de piece:
     * <ul>
     * <li>Salle de bains</li>
     * <li>Cuisine</li>
     * <li>Salon</li>
     * <li>Chambre</li>
     * <li>WC</li>
     * <li>Autre</li>
     * </ul>
     */
    @Column(name = "TYPE_PIECE")
    private String typePiece;

    @Column(name = "NOM")
    private String nom;

    @Column(name = "LONGUEUR")
    private String longueur;

    @Column(name = "LARGEUR")
    private String largeur;

    /**
     * Dommages sur les murs
     */
    @Embedded
    private Murs murs;

    /**
     * Dommages sur les plafonds
     */
    @Embedded
    private Plafonds plafonds;

    /**
     * Dommages sur les mobiliers
     */
    @Embedded
    private Mobilier mobilier;

    /**
     * Dommages sur le sol
     */
    @Embedded
    private Sol sol;

    /**
     * commentaire si besoin
     */
    @Column(name = "COMMENTAIRE", length = 1000)
    private String commentaire;

    public Piece() {
        super();
    }

    public Piece(String typePiece, String nom, String longueur, String largeur, String commentaire, Murs murs,
                    Plafonds plafonds, Mobilier mobilier, Sol sol) {
        super();
        this.typePiece = typePiece;
        this.nom = nom;
        this.longueur = longueur;
        this.largeur = largeur;
        this.murs = murs;
        this.plafonds = plafonds;
        this.mobilier = mobilier;
        this.sol = sol;
        this.commentaire = commentaire;
    }

    /**
     * @return the typePiece
     */
    public String getTypePiece() {
        return typePiece;
    }

    /**
     * @param typePiece the typePiece to set
     */
    public void setTypePiece(String typePiece) {
        this.typePiece = typePiece;
    }

    /**
     * @return the longueur
     */
    public String getLongueur() {
        return longueur;
    }

    /**
     * @param longueur the longueur to set
     */
    public void setLongueur(String longueur) {
        this.longueur = longueur;
    }

    /**
     * @return the largeur
     */
    public String getLargeur() {
        return largeur;
    }

    /**
     * @param largeur the largeur to set
     */
    public void setLargeur(String largeur) {
        this.largeur = largeur;
    }

    /**
     * @return the murs
     */
    public Murs getMurs() {
        return murs;
    }

    /**
     * @param murs the murs to set
     */
    public void setMurs(Murs murs) {
        this.murs = murs;
    }

    /**
     * @return the plafonds
     */
    public Plafonds getPlafonds() {
        return plafonds;
    }

    /**
     * @param plafonds the plafonds to set
     */
    public void setPlafonds(Plafonds plafonds) {
        this.plafonds = plafonds;
    }

    /**
     * @return the mobilier
     */
    public Mobilier getMobilier() {
        return mobilier;
    }

    /**
     * @param mobilier the mobilier to set
     */
    public void setMobilier(Mobilier mobilier) {
        this.mobilier = mobilier;
    }

    /**
     * @return the sol
     */
    public Sol getSol() {
        return sol;
    }

    /**
     * @param sol the sol to set
     */
    public void setSol(Sol sol) {
        this.sol = sol;
    }

    /**
     * @return the commentaire
     */
    public String getCommentaire() {
        return commentaire;
    }

    /**
     * @param commentaire the commentaire to set
     */
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
