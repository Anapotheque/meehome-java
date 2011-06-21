/*
 * PDFFiltre.java
 *
 * Created on 9 mars 2007, 11:04
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fr.alliance.docalliance.applet;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author ylr
 */
public class PDFFiltre extends FileFilter{
    
    private String []Suffixes;
    private String Description;
    
    public PDFFiltre(String []Suffixes, String Description){
        this.Suffixes = Suffixes;
        this.Description = Description;
    }
    
    boolean appartient( String suffixe ){
        for( int i = 0; i<Suffixes.length; ++i){       
            if(suffixe.equals(Suffixes[i])) return true;
        }
        return false;
    }
    
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }   
        String suffixe = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');
        if (i > 0 &&  i < s.length() - 1) {
            suffixe = s.substring(i+1).toLowerCase();  
        }
        return suffixe != null && appartient(suffixe);
    }
    
    // la description du filtre
    public String getDescription() {
        return Description;   
    }
}
