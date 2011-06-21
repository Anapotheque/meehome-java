/*
 * GestionScripts.java
 *
 * Created on 11 avril 2007, 17:02
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fr.home.my.ajax.html.scripts;

/**
 *
 * @author Rhum1
 */
public class GestionScripts {
    
    private String FLUX;
    
    /** Creates a new instance of GestionScripts */
    public GestionScripts() {
        this.FLUX = new String();
    }
    public void add(String chemin){
        this.FLUX += "<script src='"+chemin+"' type='text/javascript'></script>";
    }
    public String getFLUX(){
        return this.FLUX;
    }
}
