package Models.TOOLS.Ajaxmodels.OBJETS;

import Models.TOOLS.Ajaxmodels.*;
import java.io.PrintWriter;
import java.util.ArrayList;

//RABALLAND Romain v3.0

public class SelectDAO extends TOOLS_HTML {
    
        static public void AddMultSelect(PrintWriter out, String name, ArrayList listValue, ArrayList listOption1, ArrayList listOption2, int size, String fonction){
        
        out.println("<select name="+name+" size="+size+" "+fonction+">");
        
        out.println("<option value=null selected>Choisissez</option>");
        
        for(int i=0 ; i<listValue.size() ; i++)
            out.println("<option value="+listValue.get(i)+">"+listOption1.get(i)+" -- "+listOption2.get(i)+"</option>");
        
        out.println("</select>");
        
    }
    
    static public void AddSelect(PrintWriter out, String name, ArrayList listValue, ArrayList listOption, int size){
        
        out.println("<select name="+name+" size="+size+">");
        
        out.println("<option value=null selected>Choisissez</option>");
        
        for(int i=0 ; i<listValue.size() ; i++)
            out.println("<option value="+listValue.get(i)+">"+listOption.get(i)+"</option>");
        
        out.println("</select>");
        
    }
    
    static public void AddSelect(PrintWriter out, String name, ArrayList listValue, ArrayList listOption, int size, boolean choisissez){
        
        out.println("<select name="+name+" size="+size+">");
        
        if(choisissez)
            out.println("<option value=null selected>Choisissez</option>");
        
        for(int i=0 ; i<listValue.size() ; i++)
            out.println("<option value="+listValue.get(i)+">"+listOption.get(i)+"</option>");
        
        out.println("</select>");
        
    }
    static public void AddSelect(PrintWriter out, String name, ArrayList listValue, ArrayList listOption, boolean multiple, int size){
        
        out.println("<select name="+name+" size="+size+" multiple>");
        
        out.println("<option value=null selected>Choisissez</option>");
        
        for(int i=0 ; i<listValue.size() ; i++)
            out.println("<option value="+listValue.get(i)+">"+listOption.get(i)+"</option>");
        
        out.println("</select>");
        
    }
    static public void AddSelect(PrintWriter out, String name, ArrayList listValue, ArrayList listOption, int size, String fonction){
        
        out.println("<select name="+name+" size="+size+" "+fonction+">");
        
        out.println("<option value=null selected>Choisissez</option>");
        
        for(int i=0 ; i<listValue.size() ; i++)
            out.println("<option value="+listValue.get(i)+">"+listOption.get(i)+"</option>");
        
        out.println("</select>");
    }
    static public void GetSelect(PrintWriter out, String name, String firstSelectValue, String firstSelectOption, ArrayList listValue, ArrayList listOption, int size){
        
        out.println("<select name="+name+" size="+size+" >");
        
        out.println("<option value="+firstSelectValue+" selected>"+firstSelectOption+"</option>");
        
        for(int i=0 ; i<listValue.size() ; i++)
            if(!listValue.get(i).equals(firstSelectValue))
                out.println("<option value="+listValue.get(i)+">"+listOption.get(i)+"</option>");
        
        out.println("</select>");
    }
    static public void GetSelect(PrintWriter out, String name, String firstSelectValue, String firstSelectOption, ArrayList listValue, ArrayList listOption, int size, String fonction){
        
        out.println("<select name="+name+" size="+size+" "+fonction+">");
        
        out.println("<option value="+firstSelectValue+" selected>"+firstSelectOption+"</option>");
        
        for(int i=0 ; i<listValue.size() ; i++)
            if(!listValue.get(i).equals(firstSelectValue))
                out.println("<option value="+listValue.get(i)+">"+listOption.get(i)+"</option>");
        
        out.println("</select>");
    }
    
        static public void GetMultSelect(PrintWriter out, String name, String firstSelectValue, String firstSelectOption, ArrayList listValue, ArrayList listOption1, ArrayList listOption2, int size){
        
        out.println("<select name="+name+" size="+size+">");
        
        out.println("<option value="+firstSelectValue+" selected>"+firstSelectOption+"</option>");
        
        for(int i=0 ; i<listValue.size() ; i++)
            if(!listValue.get(i).equals(firstSelectValue))
                out.println("<option value="+listValue.get(i)+">"+listOption2.get(i)+" -- "+listOption1.get(i)+"</option>");
        
        out.println("</select>");
    }
}
