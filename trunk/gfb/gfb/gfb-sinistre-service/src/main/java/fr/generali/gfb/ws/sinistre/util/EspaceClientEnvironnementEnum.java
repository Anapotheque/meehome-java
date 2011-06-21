package fr.generali.gfb.ws.sinistre.util;

public enum EspaceClientEnvironnementEnum {
    
    /** environnement de d�veloppement */
    DEVELOPPEMENT("DEVELOPPEMENT"),

    /** environnement d'int�gration */
    INTEGRATION("INTEGRATION"),

    /** environnement de recette */
    RECETTE("RECETTE"),

    /** environnement de pr�production */
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
     * retourne l'�num�ration en fonction du code fourni ou null si le code ne correspond pas
     * 
     * @param code la cl� de l'�num�ration 
     * @return l'�num�ration 
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
