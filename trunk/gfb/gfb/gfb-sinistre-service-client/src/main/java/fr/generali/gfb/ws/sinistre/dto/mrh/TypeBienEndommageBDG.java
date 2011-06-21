package fr.generali.gfb.ws.sinistre.dto.mrh;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class TypeBienEndommageBDG {

    private String value;

    private String surface;

    public TypeBienEndommageBDG() {
        super();
    }

    public TypeBienEndommageBDG(String value, String surface) {
        super();
        this.value = value;
        this.surface = surface;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    public String getValue() {
        return value;
    }

    public String getSurface() {
        return surface;
    };
    
    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @param surface the surface to set
     */
    public void setSurface(String surface) {
        this.surface = surface;
    }    

}
