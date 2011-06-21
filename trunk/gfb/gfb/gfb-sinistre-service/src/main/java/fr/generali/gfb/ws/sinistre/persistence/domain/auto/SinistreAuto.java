/**
 * 
 */
package fr.generali.gfb.ws.sinistre.persistence.domain.auto;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import fr.generali.gfb.ws.sinistre.persistence.domain.common.Sinistre;

/**
 * @author ARUL Aguilane
 */
@Entity
@Table(name = "DS_SIN_AUTO")
@DiscriminatorValue("AUTO")
public class SinistreAuto extends Sinistre {

    @Column(name = "HEURE_DEBUT", length = 2)
    private Integer heureDebut;

    @Column(name = "MINUTE_DEBUT", length = 2)
    private Integer minuteDebut;

    @Column(name = "HEURE_FIN", length = 2)
    private Integer heureFin;

    @Column(name = "MINUTE_FIN", length = 2)
    private Integer minuteFin;

    @Column(name = "CIRCONSTANCES", length = 1000)
    private String circonstances;

    @Column(name = "IMMATRICULATION", length = 20)
    private String immatriculation;

    public Integer getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(Integer heureDebut) {
        this.heureDebut = heureDebut;
    }

    public SinistreAuto heureDebut(Integer heureDebut) {
        setHeureDebut(heureDebut);
        return this;
    }

    public Integer getMinuteDebut() {
        return minuteDebut;
    }

    public void setMinuteDebut(Integer minuteDebut) {
        this.minuteDebut = minuteDebut;
    }

    public SinistreAuto minuteDebut(Integer minuteDebut) {
        setMinuteDebut(minuteDebut);
        return this;
    }

    public Integer getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(Integer heureFin) {
        this.heureFin = heureFin;
    }

    public SinistreAuto heureFin(Integer heureFin) {
        setHeureFin(heureFin);
        return this;
    }

    public Integer getMinuteFin() {
        return minuteFin;
    }

    public void setMinuteFin(Integer minuteFin) {
        this.minuteFin = minuteFin;
    }

    public SinistreAuto minuteFin(Integer minuteFin) {
        setMinuteFin(minuteFin);
        return this;

    }

    public String getCirconstances() {
        return circonstances;
    }

    public void setCirconstances(String circonstances) {
        this.circonstances = circonstances;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

}
