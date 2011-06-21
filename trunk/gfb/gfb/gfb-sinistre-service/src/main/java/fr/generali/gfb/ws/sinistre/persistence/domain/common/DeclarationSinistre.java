/**
 * 
 */
package fr.generali.gfb.ws.sinistre.persistence.domain.common;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.NotNull;
import org.hibernate.validator.Valid;

import fr.generali.gfb.ws.sinistre.persistence.domain.constants.EtapeSinistreEnum;

/**
 * @author ARUL Aguilane
 */
@Entity
@Table(name = "DS_DECLARATION_SINISTRE")
public class DeclarationSinistre {

    /**
     * Identifiant pour l'entite Hibernate
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * numéro de contrat
     */
    @Column(name = "NUM_CONTRAT")
    private String numeroContrat;

    /**
     * numéro de client
     */
    @Column(name = "NUM_CLIENT")
    private String numClient;

    /**
     * numéro RCE de client
     */
    @Column(name = "ID_RCE_CLIENT")
    private String idClientRce;

    /**
     * code compagnie
     */
    @Column(name = "CODE_CIE")
    private String codeCompagnie;

    /**
     * code portefeuille
     */
    @Column(name = "CODE_PORTEFEUILLE")
    private String codePortefeuille;

    /**
     * informations sur l'assuré
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ASSURE_FK")
    @Valid
    @NotNull
    private Assure assure;

    /**
     * Dommage:
     * <ul>
     * <li>Bris de glace</li>
     * <li>Catastrophes naturelles et technologiques</li>
     * <li>Dégâts des eaux</li>
     * <li>Dommages électriques</li>
     * <li>Incendie</li>
     * <li>Responsabilité civile</li>
     * <li>Tempête ou grêle</li>
     * <li>Vol / Cambriolage</li>
     * </ul>
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SINISTRE_FK")
    @Valid
    @NotNull
    private Sinistre sinistre;

    /** Etape dans le traitement des informations */
    @Enumerated(EnumType.STRING)
    @Column(name = "ETAPE")
    private EtapeSinistreEnum etape;

    /**
     * @return the numeroContrat
     */
    public String getNumeroContrat() {
        return numeroContrat;
    }

    /**
     * @param numeroContrat the numeroContrat to set
     */
    public void setNumeroContrat(String numeroContrat) {
        this.numeroContrat = numeroContrat;
    }

    /**
     * @return the assure
     */
    public Assure getAssure() {
        return assure;
    }

    /**
     * @param assure the assure to set
     */
    public void setAssure(Assure assure) {
        this.assure = assure;
    }

    /**
     * @return the dommage
     */
    public Sinistre getSinistre() {
        return sinistre;
    }

    /**
     * @param dommage the dommage to set
     */
    public void setSinistre(Sinistre sinistre) {
        this.sinistre = sinistre;
    }

    /**
     * @return the numClient
     */
    public String getNumClient() {
        return numClient;
    }

    /**
     * @param numClient the numClient to set
     */
    public void setNumClient(String numClient) {
        this.numClient = numClient;
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
     * @return the etape
     */
    public EtapeSinistreEnum getEtape() {
        return etape;
    }

    /**
     * @param etape the etape to set
     */
    public void setEtape(EtapeSinistreEnum etape) {
        this.etape = etape;
    }

    public String getIdClientRce() {
        return idClientRce;
    }

    public void setIdClientRce(String idClientRce) {
        this.idClientRce = idClientRce;
    }

    public String getCodeCompagnie() {
        return codeCompagnie;
    }

    public void setCodeCompagnie(String codeCompagnie) {
        this.codeCompagnie = codeCompagnie;
    }

    public String getCodePortefeuille() {
        return codePortefeuille;
    }

    public void setCodePortefeuille(String codePortefeuille) {
        this.codePortefeuille = codePortefeuille;
    }

}
