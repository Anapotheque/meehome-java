/**
 * 
 */
package fr.generali.gfb.ws.sinistre.persistence.domain.mrh.brisglace;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ARUL Aguilane
 */
@Entity
@Table(name = "DS_BG_ELEM_BIENS_ENDOMMAGES")
public class TypeBiensEndommages {

    /**
     * Identifiant pour l'entite Hibernate
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "VALUE")
    private String value;

    @Column(name = "SURFACE")
    private String surface;

    public TypeBiensEndommages() {
        super();
    }

    public TypeBiensEndommages(String value, String surface) {
        super();
        this.value = value;
        this.surface = surface;
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

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the surface
     */
    public String getSurface() {
        return surface;
    }

    /**
     * @param surface the surface to set
     */
    public void setSurface(String surface) {
        this.surface = surface;
    }

}
