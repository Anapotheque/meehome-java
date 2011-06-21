package fr.alliance.docalliance.tools.system;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DataDate {
    
    static public String GetDate(){
        
        GregorianCalendar d = new GregorianCalendar();
        
        int jour = d.get(Calendar.DAY_OF_MONTH);
        int mois = d.get(Calendar.MONTH)+1;
        int annee = d.get(Calendar.YEAR);
        
        int heure = d.get(Calendar.HOUR);
        int minute = d.get(Calendar.MINUTE);
        int seconde = d.get(Calendar.SECOND);
        
        String date = annee+"/"+mois+"/"+jour;
        String time = heure+" : "+minute+" : "+seconde;
        
        return date;
    }
    
    static public Date getTimeDate(){
        GregorianCalendar d = new GregorianCalendar();
        return d.getTime();
    }
    
    static public String getActuallyDate(){
    
        GregorianCalendar d = new GregorianCalendar();
        
        int jour = d.get(Calendar.DAY_OF_MONTH);
        int mois = d.get(Calendar.MONTH)+1;
        int annee = d.get(Calendar.YEAR);
        
        String date = ""+jour+"_"+mois+"_"+annee;
        
        return date;
    }
}
