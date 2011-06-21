package Models.TOOLS.Ajaxmodels.OBJETS;

import java.io.PrintWriter;

//RABALLAND Romain v3.0
public class RadioDAO {
    
    static public void AddInputRadio(PrintWriter out, String nameInput, String value, boolean checked) {
        if(checked)
        out.println("<input type='radio' name="+nameInput+" value="+value+" checked>");
        else
        out.println("<input type='radio' name="+nameInput+" value="+value+">");
    }
    
}
