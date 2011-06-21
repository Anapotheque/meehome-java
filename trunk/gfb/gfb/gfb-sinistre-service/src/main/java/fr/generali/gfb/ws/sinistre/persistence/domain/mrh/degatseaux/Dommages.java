/**
 * 
 */
package fr.generali.gfb.ws.sinistre.persistence.domain.mrh.degatseaux;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;

/**
 * @author ARUL Aguilane
 */
@Embeddable
public class Dommages {

    /**
     * Nombre de pièces endommagés
     * <ul>
     * <li>0</li>
     * <li>1</li>
     * <li>2</li>
     * <li>3</li>
     * <li>4</li>
     * <li>5</li>
     * </ul>
     */
    @Column(name = "NB_PIECES_ENDOMMAGES")
    private Integer nbPiecesEndommagees;

    /**
     * Détails des dommages sur les pièces
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    @JoinColumn(name = "PIECES_FK")
    private List<Piece> pieces;

    /**
     * @return the nbPiecesEndommagees
     */
    public Integer getNbPiecesEndommagees() {
        return nbPiecesEndommagees;
    }

    /**
     * @param nbPiecesEndommagees the nbPiecesEndommagees to set
     */
    public void setNbPiecesEndommagees(Integer nbPiecesEndommagees) {
        this.nbPiecesEndommagees = nbPiecesEndommagees;
    }

    /**
     * @return the pieces
     */
    public List<Piece> getPieces() {
        return pieces;
    }

    /**
     * @param pieces the pieces to set
     */
    public void setPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

}
