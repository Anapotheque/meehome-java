/*
 * Menu.java
 *
 * Created on 21 mars 2007, 17:57
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fr.alliance.docalliance.tools.html.menu;

/**
 *
 * @author Alliance-Concept
 */
public class GestionMenu {
    
    private String FLUX;
    private static int nombreMenu = 0;
    
    public GestionMenu(){
        FLUX = new String();
        nombreMenu = 0;
        FLUX += "<div id='menu-deroulant'>";
    }
    
    public void add(Menu menu){
        if(menu != null){
            if(menu.list_SMenu == null){
                FLUX += "<dl><dt onmouseover=javascript:montre('smenu"+nombreMenu+"')><a href="+menu.link+">"+menu.titre+"</a></dt></dl>";
            }else{
                FLUX += "<dl><dt onmouseover=javascript:montre('smenu"+nombreMenu+"')>"+menu.titre;
                FLUX += "<dd id='smenu"+nombreMenu+"' onmouseover=javascript:montre('smenu"+nombreMenu+"') onmouseout=javascript:montre('') onclick=javascript:montre('')>";
                FLUX += "<ul>";
                for(int i = 0 ; i < menu.list_SMenu.size(); i++){
                    SMenu smenu = (SMenu) menu.list_SMenu.get(i);
                    FLUX += "<li><a href="+smenu.link+">"+smenu.titre+"</a></li>";
                }
                FLUX += "</ul></dd></dt></dl>";
            }
            nombreMenu++;
        }
    }
    
    public String getFLUX(){
        return this.FLUX;
    }
    
    public void close(){
        FLUX += "</div>";
    }
}
