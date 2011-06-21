/*
 * GestionTitre.java
 *
 * Created on 28 mars 2007, 10:24
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fr.alliance.docalliance.tools.html.Data;

/**
 *
 * @author Rhum1
 */
public class GestionData {
    
    private String FLUX;
    
    /** Creates a new instance of GestionTitre */
    public GestionData(String classDiv) {
        this.FLUX = new String();
        this.FLUX += "<div class='"+classDiv+"'>";
    }
    public void add(String msg){
        this.FLUX += msg;
    }
    public void close(){
        this.FLUX +="</div>";
    }
    public String getFLUX(){
        return this.FLUX;
    }
}
