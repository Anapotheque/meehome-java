/**
 * 
 */
package fr.generali.gfb.ws.sinistre.persistence.domain.constants;

/**
 * @author ARUL Aguilane
 */
public enum TypeSinistreEnum {

    MRH_BRIS_GLACE("BG", "Bris de glace", "BG"),

    MRH_DEGATS_DES_EAUX("DDE", "Dégâts des eaux", "DDE"),

    MRH_DOMMAGES_ELECTRIQUES("DE", "Dommages électriques", "DE"),
    
    MRH_TEMPETE_GRELE("TG", "Tempête ou grêle", "TG"),
    
    MRH_VOL_CAMBRIOLAGE("VC", "Vol / Cambriolage", "VOL"),    

    AUTO_INCENDIE("IN", "Incendie", "IN"),
    
    AUTO_ACCIDENT("AC", "Accident", "AC"),
    
    AUTO_VANDALISME("VA", "Vandalisme", "VA"),
    
    AUTO_VOL("VO", "Vol", "VO");

    private String code;

    private String label;

    // TODO:Recuperer les valeurs envoyés en parametres
    private String requestParamter;

    /**
     * @param code
     * @param label
     */
    private TypeSinistreEnum(String code, String label, String requestParamter) {
        this.code = code;
        this.label = label;
        this.requestParamter = requestParamter;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @return the requestParamter
     */
    public String getRequestParamter() {
        return requestParamter;
    }
}
