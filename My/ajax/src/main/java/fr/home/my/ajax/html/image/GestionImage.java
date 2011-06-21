/*
 * GestionImage.java
 *
 * Created on 28 mars 2007, 16:42
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fr.home.my.ajax.html.image;

/**
 *
 * @author Rhum1
 */
public class GestionImage {
    
    private String FLUX;
    
    /** Creates a new instance of GestionImage */
    public GestionImage(String src, String width, String height) {
        this.FLUX = new String();
        this.FLUX += "<img src="+src+" width="+width+" height="+height+" ";
    }
    public void addAlt(String alt){
        this.FLUX += "alt="+alt+" ";
    }
    public void close(){
        this.FLUX += "/>";
    }
    public String getFLUX(){
        return this.FLUX;
    }
    
}
