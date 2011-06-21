/*
 * GestionApplet.java
 *
 * Created on 23 mars 2007, 10:06
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fr.alliance.docalliance.tools.html.applet;

/**
 *
 * @author Rhum1
 */
public class GestionApplet {
    
    private String FLUX;
    
    /** Creates a new instance of GestionApplet */
    public GestionApplet(String name, String targetClass, String archive, String width, String height) {
        FLUX = new String();
        FLUX += "<applet name='"+name+"' MAYSCRIPT code='"+targetClass+"' archive='"+archive+"' width='"+width+"' height='"+height+"'>"; 
    }
    
    public void add(Parametre parametre){
        FLUX += "<param name='"+parametre.name+"' value='"+parametre.value+"'>";
    }
    
    public void close(){
        FLUX += "</applet>";
    }
    public String getFLUX(){
        return this.FLUX;
    }
}
