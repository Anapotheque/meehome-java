package Models.TOOLS.Ajaxmodels;

import java.io.PrintWriter;

//RABALLAND Romain v3.0
public class GetMessageInformationDAO {
    
    static public void getMessageInformation(PrintWriter out, String id, String msg) {
        out.println("<span id="+id+">"+msg+"</span>");
    }
    
}
