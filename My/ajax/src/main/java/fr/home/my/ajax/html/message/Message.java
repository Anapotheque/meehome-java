/*
 * Message.java
 *
 * Created on 30 mars 2007, 12:22
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fr.home.my.ajax.html.message;

/**
 *
 * @author Rhum1
 */
public class Message {
    
    public static String FLUX; 
    
    /** Creates a new instance of Message */
    public Message(String msg, String color) {
        this.FLUX = new String();
        this.FLUX = "<span style='color:"+color+"'>"+msg+"</span>";
    }
    public String getFLUX(){
        return this.FLUX;
    }
}
