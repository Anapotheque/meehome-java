package fr.generali.gfb.ws.sinistre.dto.mrh;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class PieceInput {

    private String typePiece;
    
    private String nom;

    private String longueur;

    private String largeur;

    private String commentaire;

    private String murPapierPeint;

    private String murPeinture;

    private String murAutre;

    private String murSurfaceAutre;

    private String plafondPapierPeint;

    private String plafondPeinture;

    private String plafondAutre;

    private String plafondSurfaceAutre;

    private String mobilierEndommage;

    private String solParquet;

    private String solCarrelage;

    private String solMoquette;

    private String solRevetementPlastique;

    private String solAutre;

    private String solAutreSurface;

    /**
     * Default constructor
     */
    public PieceInput() {
        super();
    }

    /**
     * Full constructor
     * 
     * @param typePiece
     * @param nom
     * @param longueur
     * @param largeur
     * @param commentaire
     * @param murPapierPeint
     * @param murPeinture
     * @param murAutre
     * @param murSurfaceAutre
     * @param plafondPapierPeint
     * @param plafondPeinture
     * @param plafondAutre
     * @param plafondSurfaceAutre
     * @param mobilierEndommage
     * @param solParquet
     * @param solCarrelage
     * @param solMoquette
     * @param solRevetementPlastique
     * @param solAutre
     * @param solAutreSurface
     */
    public PieceInput(String typePiece, String nom, String longueur, String largeur, String commentaire, String murPapierPeint,
                    String murPeinture, String murAutre, String murSurfaceAutre, String plafondPapierPeint,
                    String plafondPeinture, String plafondAutre, String plafondSurfaceAutre, String mobilierEndommage,
                    String solParquet, String solCarrelage, String solMoquette, String solRevetementPlastique,
                    String solAutre, String solAutreSurface) {
        super();
        this.typePiece = typePiece;
        this.nom = nom;
        this.longueur = longueur;
        this.largeur = largeur;
        this.commentaire = commentaire;
        this.murPapierPeint = murPapierPeint;
        this.murPeinture = murPeinture;
        this.murAutre = murAutre;
        this.murSurfaceAutre = murSurfaceAutre;
        this.plafondPapierPeint = plafondPapierPeint;
        this.plafondPeinture = plafondPeinture;
        this.plafondAutre = plafondAutre;
        this.plafondSurfaceAutre = plafondSurfaceAutre;
        this.mobilierEndommage = mobilierEndommage;
        this.solParquet = solParquet;
        this.solCarrelage = solCarrelage;
        this.solMoquette = solMoquette;
        this.solRevetementPlastique = solRevetementPlastique;
        this.solAutre = solAutre;
        this.solAutreSurface = solAutreSurface;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    public String getTypePiece() {
        return typePiece;
    }

    public String getNom() {
        return nom;
    }
    
    public String getLongueur() {
        return longueur;
    }

    public String getLargeur() {
        return largeur;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public String getMurPapierPeint() {
        return murPapierPeint;
    }

    public String getMurPeinture() {
        return murPeinture;
    }

    public String getMurAutre() {
        return murAutre;
    }

    public String getMurSurfaceAutre() {
        return murSurfaceAutre;
    }

    public String getPlafondPapierPeint() {
        return plafondPapierPeint;
    }

    public String getPlafondPeinture() {
        return plafondPeinture;
    }

    public String getPlafondAutre() {
        return plafondAutre;
    }

    public String getPlafondSurfaceAutre() {
        return plafondSurfaceAutre;
    }

    public String getMobilierEndommage() {
        return mobilierEndommage;
    }

    public String getSolParquet() {
        return solParquet;
    }

    public String getSolCarrelage() {
        return solCarrelage;
    }

    public String getSolMoquette() {
        return solMoquette;
    }

    public String getSolRevetementPlastique() {
        return solRevetementPlastique;
    }

    public String getSolAutre() {
        return solAutre;
    }

    public String getSolAutreSurface() {
        return solAutreSurface;
    }

    /**
     * @param typePiece the typePiece to set
     */
    public void setTypePiece(String typePiece) {
        this.typePiece = typePiece;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @param longueur the longueur to set
     */
    public void setLongueur(String longueur) {
        this.longueur = longueur;
    }

    /**
     * @param largeur the largeur to set
     */
    public void setLargeur(String largeur) {
        this.largeur = largeur;
    }

    /**
     * @param commentaire the commentaire to set
     */
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    /**
     * @param murPapierPeint the murPapierPeint to set
     */
    public void setMurPapierPeint(String murPapierPeint) {
        this.murPapierPeint = murPapierPeint;
    }

    /**
     * @param murPeinture the murPeinture to set
     */
    public void setMurPeinture(String murPeinture) {
        this.murPeinture = murPeinture;
    }

    /**
     * @param murAutre the murAutre to set
     */
    public void setMurAutre(String murAutre) {
        this.murAutre = murAutre;
    }

    /**
     * @param murSurfaceAutre the murSurfaceAutre to set
     */
    public void setMurSurfaceAutre(String murSurfaceAutre) {
        this.murSurfaceAutre = murSurfaceAutre;
    }

    /**
     * @param plafondPapierPeint the plafondPapierPeint to set
     */
    public void setPlafondPapierPeint(String plafondPapierPeint) {
        this.plafondPapierPeint = plafondPapierPeint;
    }

    /**
     * @param plafondPeinture the plafondPeinture to set
     */
    public void setPlafondPeinture(String plafondPeinture) {
        this.plafondPeinture = plafondPeinture;
    }

    /**
     * @param plafondAutre the plafondAutre to set
     */
    public void setPlafondAutre(String plafondAutre) {
        this.plafondAutre = plafondAutre;
    }

    /**
     * @param plafondSurfaceAutre the plafondSurfaceAutre to set
     */
    public void setPlafondSurfaceAutre(String plafondSurfaceAutre) {
        this.plafondSurfaceAutre = plafondSurfaceAutre;
    }

    /**
     * @param mobilierEndommage the mobilierEndommage to set
     */
    public void setMobilierEndommage(String mobilierEndommage) {
        this.mobilierEndommage = mobilierEndommage;
    }

    /**
     * @param solParquet the solParquet to set
     */
    public void setSolParquet(String solParquet) {
        this.solParquet = solParquet;
    }

    /**
     * @param solCarrelage the solCarrelage to set
     */
    public void setSolCarrelage(String solCarrelage) {
        this.solCarrelage = solCarrelage;
    }

    /**
     * @param solMoquette the solMoquette to set
     */
    public void setSolMoquette(String solMoquette) {
        this.solMoquette = solMoquette;
    }

    /**
     * @param solRevetementPlastique the solRevetementPlastique to set
     */
    public void setSolRevetementPlastique(String solRevetementPlastique) {
        this.solRevetementPlastique = solRevetementPlastique;
    }

    /**
     * @param solAutre the solAutre to set
     */
    public void setSolAutre(String solAutre) {
        this.solAutre = solAutre;
    }

    /**
     * @param solAutreSurface the solAutreSurface to set
     */
    public void setSolAutreSurface(String solAutreSurface) {
        this.solAutreSurface = solAutreSurface;
    }
    
    

}
