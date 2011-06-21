/*
 * GestionTable.java
 *
 * Created on 22 mars 2007, 10:22
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fr.home.my.ajax.html.table;

/**
 *
 * @author Rhum1
 */
public class GestionTable {
    
    private String FLUX;
    private boolean overflow = true;
    
    public GestionTable(String height) {
        FLUX = new String();
        FLUX += "<div class='GestionTable'><table style='height:"+height+"'>";
    }
    public void Add(Titre titre){
        FLUX += "<tr>";
        for(int i = 0; i < titre.list_valeur.size(); i ++){
            FLUX += "<th>"+titre.list_valeur.get(i)+"</th>";
        }
        FLUX += "</tr>";
    }
    public void Add(Ligne ligne){
        FLUX += "<tr>";
        for(int i = 0; i < ligne.list_valeur.size(); i ++){
            FLUX += "<td style='color: "+ligne.color+"'>"+ligne.list_valeur.get(i)+"</td>";
        }
        FLUX += "</tr>";
    }
    public void close(){
        FLUX += "</table></div>";
    }
    public String getFLUX(){
        return this.FLUX;
    }
}
