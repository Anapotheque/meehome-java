/*
 * Ligne.java
 *
 * Created on 22 mars 2007, 10:23
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fr.alliance.docalliance.tools.html.table;

import java.util.ArrayList;

/**
 *
 * @author Rhum1
 */
public class Ligne {
    
    protected ArrayList list_valeur = null;
    protected String color = "";
    
    public Ligne(ArrayList list_valeur, String color){
        this.list_valeur = list_valeur;
        this.color = color;
    }
}
