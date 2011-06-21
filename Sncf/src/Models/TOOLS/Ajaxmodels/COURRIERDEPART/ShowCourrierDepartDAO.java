package Models.TOOLS.Ajaxmodels.COURRIERDEPART;

import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Utilisateurs.UtilisateursDAO;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//RABALLAND Romain v3.0
public class ShowCourrierDepartDAO {
    
    static private UtilisateursDAO utilisateur = null;
    static private String chaine = "";
    
    static public UtilisateursDAO getUtilisateur(HttpServletRequest request,UtilisateursDAO utilisateur) {
        HttpSession session = request.getSession(true);
        utilisateur = (UtilisateursDAO) session.getAttribute("modelUtilisateurs");
        return utilisateur;
    }
    
    static public String AddChoiceTable(HttpServletRequest request, PrintWriter out, String servlet, String code, int h, int j,String color) {
        
        utilisateur = getUtilisateur(request, utilisateur);
        
        if(utilisateur.TestAdministrateur() || utilisateur.TestModerateur()){
            
            chaine = "<td style='background-color: "+color+"'><a href=javascript:getHTMLCodeRequestCourrier('data','Modify"+servlet+"','','','"+code+"')><img src='images/modifier.gif' alt='Modifier' onMouseOver='change_image("+h+",modifiergris)' border='0' onMouseOut='change_image("+h+",modifier)'/></a>";
            if(utilisateur.TestAdministrateur())
                chaine = chaine +"<a href=javascript:getHTMLCodeRequestCourrier('data','Delete"+servlet+"','','','"+code+"')><img src='images/supprimer.gif' alt='Supprimer' onMouseOver='change_image("+j+",supprimergris)' border='0' onMouseOut='change_image("+j+",supprimer)'/></a></td>";
            
        }
        return chaine;
    }
    
    static public void NewCorpsTable(HttpServletRequest request, PrintWriter out, ArrayList list, ArrayList list_code, int taille, String servlet, boolean recherche) {
        
        int h=1;
        int j=2;
        
        if(recherche){
            h=2;
            j=3;
            TOOLS_HTML.getMessage(out,"h6","NOMBRE DE RESULTATS : "+list.size()/taille);
        }
        
        int e = 1;
        int l = 0;
        int k = 0;
        
        String color = "#EEEEEE";
        
        TOOLS_HTML.OpenTrTable(out);
        for(int i=0;i < list.size();i++){
            
            out.println("<td style='background-color: "+color+"; padding: 20px;'>"+list.get(i)+"</td>");
            if(e == taille){
                if(utilisateur.TestAdministrateur() || utilisateur.TestModerateur())
                    out.println(AddChoiceTable(request,out,servlet,""+list_code.get(l)+"",h,j,color));
                TOOLS_HTML.CloseTrTable(out);
                TOOLS_HTML.OpenTrTable(out);
                l++;
                if(k == 0)
                    k++;
                else
                    k--;
                e = 0;
                
                if(utilisateur.TestAdministrateur())
                    h = h +2;
                else
                    h++;
                
                j = j +2;
            }
            if(k == 1)
                color = "#FFFFFF";
            else
                color = "#EEEEEE";
            e++;
        }
        TOOLS_HTML.CloseTrTable(out);
    }
    
    static public void NewTitleTable(HttpServletRequest request, PrintWriter out,ArrayList list,ArrayList size, boolean simple) {
        
        utilisateur = getUtilisateur(request, utilisateur);
        
        for(int i=0;i < list.size();i++){
            chaine = ""+list.get(i)+"";
            chaine = chaine.toUpperCase();
            out.println("<th width="+size.get(i)+">"+chaine+"</th>");
        }
        if((utilisateur.TestAdministrateur() || utilisateur.TestModerateur()) && !simple)
            out.println("<th>ACTIONS</th>");
    }
    
    static public void NewSeparatorTable(HttpServletRequest request, PrintWriter out,ArrayList list, String width, int size) {
        
        utilisateur = getUtilisateur(request, utilisateur);
        
        if(utilisateur.TestAdministrateur() || utilisateur.TestModerateur())
            out.println("<tr><td colspan='"+(list.size()+1)+"'><hr width="+width+"  size="+size+"></td></tr>");
        else
            out.println("<tr><td colspan='"+(list.size())+"'><hr width="+width+"></td></tr>");
    }
    
    static public void Show(HttpServletRequest request, PrintWriter out, String servlet, ArrayList list_Titre, ArrayList list, ArrayList list_code, ArrayList size, boolean search){
        
        TOOLS_HTML.OpenTable(out,"100%");
        NewTitleTable(request,out,list_Titre,size,false);
        NewSeparatorTable(request,out,list_Titre,"100%",1);
        NewCorpsTable(request, out, list, list_code, list_Titre.size(), servlet,search);
        out.println("</table>");
        
    }
    
}
