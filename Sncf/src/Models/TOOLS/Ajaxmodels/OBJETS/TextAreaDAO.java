package Models.TOOLS.Ajaxmodels.OBJETS;

import java.io.PrintWriter;

//RABALLAND Romain v3.0

public class TextAreaDAO {
    
    static public void TextArea(PrintWriter out, String name, int row, int cols, String taille){
        out.println("<textarea name="+name+" rows="+row+" cols="+cols+" style='width:"+taille+"'></textarea>");
    }
    static public void TextArea(PrintWriter out, String name, int row, int cols, String taille, String value){
        out.println("<textarea name="+name+" rows="+row+" cols="+cols+" style='width:"+taille+"'>"+value+"</textarea>");
    }
    static public void TextArea(PrintWriter out, String name, int row, int cols, String taille, String value,boolean readonly){
        if(readonly)
            out.println("<textarea name="+name+" rows="+row+" cols="+cols+" style='width:"+taille+"' readonly>"+value+"</textarea>");
        else
            out.println("<textarea name="+name+" rows="+row+" cols="+cols+" style='width:"+taille+"'>"+value+"</textarea>");
    }
}
