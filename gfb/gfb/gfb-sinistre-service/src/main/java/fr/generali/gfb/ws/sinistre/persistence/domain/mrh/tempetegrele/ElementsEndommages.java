package fr.generali.gfb.ws.sinistre.persistence.domain.mrh.tempetegrele;

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
@Table(name = "DS_TG_ELEM_ELEMENTS_ENDOMMAGES")
public class ElementsEndommages {

    /**
     * Identifiant pour l'entite Hibernate
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "VALUE")
    private String value;

    public ElementsEndommages() {
        super();
    }

    public ElementsEndommages(String value) {
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
