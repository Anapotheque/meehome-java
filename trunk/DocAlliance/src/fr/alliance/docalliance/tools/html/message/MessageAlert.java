/*
 * Message.java
 *
 * Created on 23 mars 2007, 15:53
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fr.alliance.docalliance.tools.html.message;

/**
 *
 * @author Rhum1
 */
public class MessageAlert {
    
    public static final int WARNING = 0;
    public static final int VALIDATION = 1;
    public static final int ERREUR = 2;
    public static final int ADMIN = 3;
    
    private String FLUX;
    
    public MessageAlert(String msg, int typeMsg){
        this.FLUX = new String();
        
        switch (typeMsg){
            case (WARNING) : FLUX += "<div id='warning' onclick=javascript:hidden('warning')>";
                                FLUX += "<div class='header-warning'></div>";
                                FLUX += "<div class='content-warning'>";
                                FLUX += msg;
                                FLUX += "</div>";
                                FLUX += "<div class='bottom-warning'></div>";
                                FLUX += "</div>";
                                break;
            case (VALIDATION) : FLUX += "<div id='validation' onclick=javascript:hidden('validation')>";
                                FLUX += "<div class='header-validation'></div>";
                                FLUX += "<div class='content-validation'>";
                                FLUX += msg;
                                FLUX += "</div>";
                                FLUX += "<div class='bottom-validation'></div>";
                                FLUX += "</div>";
                                break;
            case (ERREUR) : FLUX += "<div id='erreur' onclick=javascript:hidden('erreur')>";
                                FLUX += "<div class='header-erreur'></div>";
                                FLUX += "<div class='content-erreur'>";
                                FLUX += msg;
                                FLUX += "</div>";
                                FLUX += "<div class='bottom-erreur'></div>";
                                FLUX += "</div>";
                                break;
            case (ADMIN) : FLUX += "<div id='admin' onclick=javascript:hidden('admin')>";
                                FLUX += "<div class='header-admin'></div>";
                                FLUX += "<div class='content-admin'>";
                                FLUX += msg;
                                FLUX += "</div>";
                                FLUX += "<div class='bottom-admin'></div>";
                                FLUX += "</div>";
                                break;
        }
    }
    
    public String getFLUX(){
        return this.FLUX;
    }
}
