/*
 * GestionSelect.java
 *
 * Created on 27 mars 2007, 12:41
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fr.alliance.docalliance.tools.html.select;

/**
 *
 * @author Rhum1
 */
public class GestionSelect {
    
    private String FLUX;
    
    /** Creates a new instance of GestionCheckBox */
    public GestionSelect(String id) {
        FLUX = new String();
        FLUX += "<select  id='"+id+"' name='"+id+"'>";
    }
    public void add(Option option){
        FLUX += "<option value='"+option.id+"'>"+option.value+"</option>";           
    }
    public void close(){
        FLUX += "</select>";
    }
    public String getFLUX(){
        return this.FLUX;
    }
    
}
