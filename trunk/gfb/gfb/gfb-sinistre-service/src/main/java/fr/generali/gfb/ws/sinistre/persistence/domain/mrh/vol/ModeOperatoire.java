/**
 * 
 */
package fr.generali.gfb.ws.sinistre.persistence.domain.mrh.vol;

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
@Table(name = "DS_VOL_ELEM_MODE_OPERATOIRE")
public class ModeOperatoire {

    /**
     * Identifiant pour l'entite Hibernate
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "VALUE")
    private String value;

    public ModeOperatoire() {
        super();
    }

    public ModeOperatoire(String value) {
        super();
        this.value = value;
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

}
