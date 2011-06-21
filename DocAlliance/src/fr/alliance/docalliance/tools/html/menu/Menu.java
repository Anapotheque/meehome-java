/*
 * SMenu.java
 *
 * Created on 21 mars 2007, 18:02
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fr.alliance.docalliance.tools.html.menu;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rhum1
 */
public class Menu {
    
    protected String titre = "";
    protected String link = "";
    protected ArrayList list_SMenu = null;
    
    public Menu(String titre, String link){
        this.titre = titre;
        this.link = link;
    }
    public Menu(String titre, ArrayList list_SMenu){
        this.titre = titre;
        this.list_SMenu = list_SMenu;
    }
    
//    public void add(SMenu smenu){
//        if(this.list_SMenu == null || this.list_SMenu.isEmpty()){
//            this.list_SMenu = new ArrayList();
//        }
//        this.list_SMenu.add(smenu);
//    }
}
