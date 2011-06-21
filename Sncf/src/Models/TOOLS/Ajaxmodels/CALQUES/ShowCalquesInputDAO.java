package Models.TOOLS.Ajaxmodels.CALQUES;

import Models.ETUDES.Entreprises.EntreprisesDAO;
import Models.TOOLS.Ajaxmodels.*;
import Models.TOOLS.Controleur.MegaControleur;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

//RABALLAND Romain v3.0
public class ShowCalquesInputDAO extends MegaControleur {
    
    static public void show(PrintWriter out,ArrayList listCode, ArrayList listNumero, boolean checked){
        
        TOOLS_HTML.OpenTable(out,"100%");
        out.println("<tr>");
        
        int taille_ligne = 0;
        
        for(int i = 0 ; i < listCode.size(); i++){
            
            out.println("<td style='width: 2%'>");
            if(!checked)
                addCheckBox(out,listCode,listNumero,i);
            else
                addCheckBoxSelected(out,listCode,listNumero,i);
            out.println("<td style='width: 2%'>");
            
            taille_ligne++;
            
            if(taille_ligne == 10){
                out.println("</tr><tr>");
                taille_ligne = 0;
            }
        }
        
        out.println("</tr></table>");
        
    }
    
    static public void showSortie(PrintWriter out,ArrayList listCode, ArrayList listNumero, ArrayList listEntreprise, EntreprisesDAO entreprise){
        
        TOOLS_HTML.OpenTable(out,"100%");
        out.println("<tr>");
        
        int taille_ligne = 0;
        
        for(int i = 0 ; i < listCode.size(); i++){
            
            out.println("<td style='width: 2%'>");
            
            addCheckBox(out,listCode,listNumero,listEntreprise, entreprise, i);
            
            out.println("<td style='width: 2%'>");
            
            taille_ligne++;
            
            if(taille_ligne == 10){
                out.println("</tr><tr>");
                taille_ligne = 0;
            }
        }
        
        out.println("</tr></table>");
        
    }
    
    static public void showDernierRentre(PrintWriter out,ArrayList listCode, ArrayList listNumero, ArrayList listDate, String DerniereDate){
        
        TOOLS_HTML.OpenTable(out,"100%");
        out.println("<tr>");
        
        int taille_ligne = 0;
        
        for(int i = 0 ; i < listCode.size(); i++){
            
            out.println("<td style='width: 2%'>");
            
            if(listDate.get(i).equals(DerniereDate))
                addCheckBoxSelected(out,listCode,listNumero, i);
            else
                addCheckBox(out,listCode,listNumero, i);
            
            out.println("<td style='width: 2%'>");
            
            taille_ligne++;
            
            if(taille_ligne == 10){
                out.println("</tr><tr>");
                taille_ligne = 0;
            }
        }
        
        out.println("</tr></table>");
        
    }
    
    static private void addCheckBox(PrintWriter out,ArrayList listCode, ArrayList listNumero, int i){
        
        TOOLS_HTML.OpenTable(out,"30%");
        out.println("<tr>");
        out.println("<td><input type='checkbox' name='box' value='"+listCode.get(i)+"'></td><td>"+listNumero.get(i)+"</td>");
        out.println("</tr></table>");
        
    }
    
    static private void addCheckBox(PrintWriter out,ArrayList listCode, ArrayList listNumero, ArrayList listEntreprise, EntreprisesDAO entreprise,  int i){
        try {
            
            TOOLS_HTML.OpenTable(out,"30%");
            out.println("<tr>");
            out.println("<td><input type='checkbox' name='box' value='"+listCode.get(i)+"'></td><td>"+listNumero.get(i)+"</td><td>"+entreprise.GetnomEntreprise(""+listEntreprise.get(i))+"</td>");
            out.println("</tr></table>");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    static private void addCheckBoxSortie(PrintWriter out,ArrayList listCode, ArrayList listNumero, ArrayList listEntreprise, int i){
        
        TOOLS_HTML.OpenTable(out,"30%");
        out.println("<tr>");
        out.println("<td><input type='checkbox' name='box' value='"+listCode.get(i)+"'></td><td>"+listNumero.get(i)+"</td>");
        out.println("</tr></table>");
        
    }
    
    static private void addCheckBoxSelected(PrintWriter out,ArrayList listCode, ArrayList listNumero, int i){
        
        TOOLS_HTML.OpenTable(out,"30%");
        out.println("<tr>");
        out.println("<td><input type='checkbox' name='box' value='"+listCode.get(i)+"' checked></td><td>"+listNumero.get(i)+"</td>");
        out.println("</tr></table>");
        
    }
    
}
