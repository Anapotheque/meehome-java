package Models.TOOLS.Ajaxmodels.OBJETS;

import java.io.PrintWriter;

//RABALLAND Romain v3.0
public class InputDAO {
    static public void AddInput(PrintWriter out, String type, String nameInput, String taille, String value) {
        out.println("<input type="+type+" value='"+value+"' name="+nameInput+" style='width:"+taille+"'>");
    }
        static public void AddInput(PrintWriter out, String type, String nameInput, String taille, String value, String function) {
        out.println("<input type="+type+" value='"+value+"' name="+nameInput+" style='width:"+taille+"' "+function+">");
    }
        static public void AddInput(PrintWriter out, String type, String nameInput, String taille, String value, boolean readonly) {
        if(readonly)
            out.println("<input type="+type+" value='"+value+"' name="+nameInput+" style='width:"+taille+"' readonly>");
        else
            out.println("<input type="+type+" value='"+value+"' name="+nameInput+" style='width:"+taille+"'>");
    }
    static public void AddInput(PrintWriter out, String type, String nameInput, String taille) {
        out.println("<input type="+type+" name="+nameInput+" style='width:"+taille+"'>");
    }
    static public void AddInput(PrintWriter out, String type, String nameInput) {
        out.println("<input type="+type+" name="+nameInput+">");
    }
}
