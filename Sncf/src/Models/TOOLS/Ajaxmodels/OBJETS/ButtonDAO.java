package Models.TOOLS.Ajaxmodels.OBJETS;

import Models.TOOLS.Ajaxmodels.*;
import java.io.PrintWriter;

//RABALLAND Romain v3.0
public class ButtonDAO extends TOOLS_HTML{
    
    static public void AddButton(PrintWriter out, int button, String servlet, String name){
        OpenDiv(out,"bouton"+button);
        out.println("<ul><li>");
        out.println("<a href=javascript:getHTMLCodeRequest('informations','"+servlet+"','choix_menu=Null','null')>"+name+"</a>");
        out.println("</li></ul></div>");
    }
    static public void AddButton(PrintWriter out, int button, String servlet, String choix_menu, String name){
        OpenDiv(out,"bouton"+button);
        out.println("<ul><li>");
        out.println("<a href=javascript:getHTMLCodeRequest('informations','"+servlet+"','"+choix_menu+"','null')>"+name+"</a>");
        out.println("</li></ul></div>");
    }
    static public void AddButtonPosition(PrintWriter out, int button, String servlet, String name, String position){
        out.println("<div style='text-align: "+position+"' >");
        OpenDiv(out,"bouton"+button);
        out.println("<ul><li>");
        out.println("<a href=javascript:getHTMLCodeRequest('informations','"+servlet+"','choix_menu=Null','null')>"+name+"</a>");
        out.println("</li></ul></div></div>");
    }
    static public void AddButtonStyle(PrintWriter out, int button, String liaison, String name){
        OpenDiv(out,"bouton"+button);
        out.println("<ul><li>");
        out.println("<a href="+liaison+">"+name+"</a>");
        out.println("</li></ul></div>");
    }
}
