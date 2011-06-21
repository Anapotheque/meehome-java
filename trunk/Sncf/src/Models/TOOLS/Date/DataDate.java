package Models.TOOLS.Date;

import java.util.Calendar;
import java.util.GregorianCalendar;

//RABALLAND Romain v3.0
public class DataDate {
    
    static public String GetDate(){
        
        GregorianCalendar d = new GregorianCalendar();
        
        int jour = d.get(Calendar.DAY_OF_MONTH);
        int mois = d.get(Calendar.MONTH)+1;
        int annee = d.get(Calendar.YEAR);
        
        String Date = jour+"/"+mois+"/"+annee;
        
        return Date;
    }
}
