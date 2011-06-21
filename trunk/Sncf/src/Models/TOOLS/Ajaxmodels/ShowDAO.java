package Models.TOOLS.Ajaxmodels;

import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

//RABALLAND Romain v3.0
public class ShowDAO {
    
    static public void TitleTable(PrintWriter out,ArrayList list_Titre,ArrayList size_Titre) {
        
        String chaine= null;
        
        for(int i=0;i < list_Titre.size();i++){
            chaine = ""+list_Titre.get(i);
            chaine = chaine.toUpperCase();
            out.println("<th width="+size_Titre.get(i)+">"+chaine+"</th>");
        }
    }
    
    
    static public void SeparatorTable(PrintWriter out,ArrayList list) {
        out.println("<tr><td colspan='"+list.size()+"'><hr width=100%></td></tr>");
    }
    
    static public void CorpsTable(PrintWriter out, ArrayList list, int taille) {
        
        int e = 1;
        int l = 0;
        int k = 0;
        
        String color = "#EEEEEE";
        
        out.println("<tr>");
        for(int i=0;i < list.size();i++){
            
            out.println("<td style='background-color: "+color+"; padding: 20px;'>"+list.get(i)+"</td>");
            if(e == taille){
                out.println("</tr>");
                out.println("<tr>");
                l++;
                if(k == 0)
                    k++;
                else
                    k--;
                e = 0;
            }
            if(k == 1)
                color = "#FFFFFF";
            else
                color = "#EEEEEE";
            e++;
        }
        out.println("</tr>");
    }
    
    static public void Show(PrintWriter out,String msg, ArrayList list_Titre, ArrayList list, ArrayList size){
        
//        TOOLS_HTML.OpenDiv(out,"DivLoad2");
//        TOOLS_HTML.getMessage(out,"h5","VOTRE PAGE EST EN COURS DE CHARGEMENT VEUILLEZ PATIENTER");
//        out.println("</div>");
        
        TOOLS_HTML.getMessage(out,"h2",msg);
        
        TOOLS_HTML.OpenTable(out,"100%");
        TitleTable(out,list_Titre,size);
        SeparatorTable(out,list_Titre);
        CorpsTable(out, list, list_Titre.size());
        out.println("</table>");
        
    }
    
}
