/*
 * GestionButton.java
 *
 * Created on 28 mars 2007, 11:27
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fr.home.my.ajax.html.input;

/**
 *
 * @author Rhum1
 */
public class GestionInput {
    
    private String FLUX;
    
    /** Creates a new instance of GestionButton */
    public GestionInput() {
        this.FLUX = new String();
        this.FLUX += "<input ";
    }
    public void addtype(String type){
        this.FLUX += "type='"+type+"' ";
    }
    public void addId(String id){
        this.FLUX += "id='"+id+"' name='"+id+"' ";
    }
    public void addClassStyle(String classDiv){
        this.FLUX += "class='"+classDiv+"' ";
    }
    public void addOnclick(String function){
        this.FLUX += "onClick="+function+" ";
    }
    public void addFunction(String function){
        this.FLUX += " "+function+" ";
    }
    public void addValue(String msg){
        this.FLUX += "value='"+msg+"' ";
    }
    public void addStyle(String style){
        this.FLUX += "style='"+style+"' ";
    }
    public void close(){
        this.FLUX += " />";
    }
    public String getFLUX() {
        return FLUX;
    }
}
