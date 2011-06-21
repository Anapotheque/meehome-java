/**
 * 
 */
package fr.generali.gfb.ws.sinistre.persistence.domain.constants;

/**
 * @author ARUL Aguilane
 */
public enum EtapeSinistreEnum {

    PERSISTENCE("Persistence"),

    PDF("Pdf"),

    MAIL("Mail");

    private final String label;

    /**
     * @param label
     */
    private EtapeSinistreEnum(String label) {
        this.label = label;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

}
