/*
 * GestionCss.java
 *
 * Created on 11 avril 2007, 16:54
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fr.alliance.docalliance.tools.html.css;

/**
 *
 * @author Rhum1
 */
public class GestionCss {
    
    private String FLUX;
    
    /** Creates a new instance of GestionCss */
    public GestionCss() {
        this.FLUX = new String();
    }
    public void add(String chemin){
        this.FLUX += "<link type='text/css' rel='stylesheet' href='"+chemin+"' />";
    }
    public String getFLUX(){
        return this.FLUX;
    }
}
