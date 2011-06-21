package fr.generali.gfb.ws.sinistre.util;

public enum EspaceClientEnvironnementEnum {
    
    /** environnement de développement */
    DEVELOPPEMENT("DEVELOPPEMENT"),

    /** environnement d'intégration */
    INTEGRATION("INTEGRATION"),

    /** environnement de recette */
    RECETTE("RECETTE"),

    /** environnement de préproduction */
    PREPRODUCTION("PREPRODUCTION"),

    /** environnement de production */
    PRODUCTION("PRODUCTION");

    private String code;

    private EspaceClientEnvironnementEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    /**
     * retourne l'énumération en fonction du code fourni ou null si le code ne correspond pas
     * 
     * @param code la clé de l'énumération 
     * @return l'énumération 
     */
    public static EspaceClientEnvironnementEnum findByValue(String code) {
        if (code == null) {
            return null;
        }
        
        try {
            return EspaceClientEnvironnementEnum.valueOf(code.toUpperCase());   
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
