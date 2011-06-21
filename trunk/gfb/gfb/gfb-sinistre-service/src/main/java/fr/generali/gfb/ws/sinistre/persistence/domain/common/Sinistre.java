/**
 * 
 */
package fr.generali.gfb.ws.sinistre.persistence.domain.common;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import fr.generali.gfb.ws.sinistre.persistence.domain.constants.TypeSinistreEnum;

/**
 * @author ARUL Aguilane
 */
// @MappedSuperclass
@Entity(name = "DS_SINISTRE")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TYPE_SINISTRE")
public abstract class Sinistre {

    /**
     * Identifiant pour l'entite Hibernate
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    /**
     * date du sinistre
     */
    @Column(name = "DATE_SINISTRE")
    private Date dateSinistre;

    /**
     * Type de sinistre :
     * <ul>
     * <li>Bris de glace</li>
     * <li>Catastrophes Naturelles et technologiques</li>
     * <li>Dégâts des eaux</li>
     * <li>Dommages électriques</li>
     * <li>Incendie</li>
     * <li>Responsabilité civile</li>
     * <li>Tempête ou grêle</li>
     * <li>Vol / Cambriolage</li>
     * </ul>
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE_SINISTRE")
    private TypeSinistreEnum typeSinistre;

    /**
     * date de la déclaration du sinistre
     */
    @Column(name = "DATE_DECLARATION")
    private Date dateDeclaration;

    /**
     * @return the dateSinistre
     */
    public Date getDateSinistre() {
        return dateSinistre;
    }

    /**
     * @param dateSinistre the dateSinistre to set
     */
    public void setDateSinistre(Date dateSinistre) {
        this.dateSinistre = dateSinistre;
    }

    /**
     * @return the typeSinistre
     */
    public TypeSinistreEnum getTypeSinistre() {
        return typeSinistre;
    }

    /**
     * @param typeSinistre the typeSinistre to set
     */
    public void setTypeSinistre(TypeSinistreEnum typeSinistre) {
        this.typeSinistre = typeSinistre;
    }

    /**
     * @return the dateDeclaration
     */
    public Date getDateDeclaration() {
        return dateDeclaration;
    }

    /**
     * @param dateDeclaration the dateDeclaration to set
     */
    public void setDateDeclaration(Date dateDeclaration) {
        this.dateDeclaration = dateDeclaration;
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
