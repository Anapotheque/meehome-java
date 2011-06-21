/*
 * GestionCheckBox.java
 *
 * Created on 23 mars 2007, 15:33
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fr.home.my.ajax.html.checkbox;

/**
 *
 * @author Rhum1
 */
public class GestionCheckBox {
    
    private String FLUX;
    private String id;
    
    /** Creates a new instance of GestionCheckBox */
    public GestionCheckBox(String id) {
        this.FLUX = new String();
        this.id = new String();
        this.id = id;
        FLUX += "<ul class='checklist'>";
    }
    public void add(Option option){
        FLUX += "<li><label><input value='"+option.id+"' id='"+this.id+"' name='"+this.id+"' type='checkbox'/>"+option.value+"</label></li>";           
    }
    public void close(){
        FLUX += "</ul>";
    }
    public String getFLUX(){
        return this.FLUX;
    }
}
