
package Models.TOOLS.Ajaxmodels;

import Models.TOOLS.Ajaxmodels.DVSG.AjaxModels_Onglets;
import Models.TOOLS.Controleur.MegaControleur;
import Models.ETUDES.Agents.AgentsDAO;
import Models.ETUDES.Entreprises.EntreprisesDAO;
import Models.ETUDES.Gares.GaresDAO;
import Models.TOOLS.Utilisateurs.UtilisateursDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//RABALLAND Romain v3.0

public class TOOLS_HTML extends MegaControleur{
    
    static private UtilisateursDAO utilisateur = null;
    static private String chaine = "";
    
    static public void NewButtonMenu(PrintWriter out, String servlet, String name, String choix_menu) {
        OpenDiv(out,"bouton1");
        OpenUL(out);
        OpenLI(out);
        out.println("<a href=javascript:getHTMLCodeRequest('informations','Show"+servlet+"','choix_menu="+choix_menu+"','null') onClick=show()>"+name+"</a>");
        CloseLI(out);
        CloseUL(out);
        CloseDiv(out);
    }
    static public void NewButtonMenu(PrintWriter out, String servlet, String name) {
        OpenDiv(out,"bouton1");
        out.println("<UL><LI>");
        out.println("<a href=javascript:getHTMLCodeRequest('informations','"+servlet+"','choix_menu=Null','null') onClick=show()>"+name+"</a>");
        out.println("</LI></UL></DIV>");
    }
    static public void AddButtonMenuTable(PrintWriter out, String servlet, String name) {
        out.println("<TD>");
        OpenDiv(out,"bouton1");
        out.println("<UL><LI>");
        out.println("<a href=javascript:getHTMLCodeRequest('informations','"+servlet+"','choix_menu=Null','null') onClick=show()>"+name+"</a>");
        out.println("</LI></UL></DIV></TD>");
    }
    
    static public void New(PrintWriter out, ArrayList list_New, ArrayList list_Ressource, ArrayList size, ArrayList sizeMax, ArrayList list_Value, String servlet) {
        
        TOOLS_HTML.getMessage(out,"h1","Veuillez renseignez tous les champs qui vous seront utiles");
        TOOLS_HTML.OpenTable(out,"100%",0);
        TOOLS_HTML.OpenForm(out,"Champ");
        int j = 0;
        for(int i = 0; i < list_New.size(); i ++){
            
            chaine = ""+list_Ressource.get(i+1)+"";
            chaine = chaine.toUpperCase();
            
            if(j == 0)
                out.println("<TR>");
            out.println("<td align = right>"+chaine+" : </td><td align = left><input type='text' value='"+list_Value.get(i)+"' name='"+list_New.get(i)+"' style='width:"+size.get(i)+"' maxlength='"+sizeMax.get(i)+"'></td>");
            j++;
            if(j == (list_New.size()/3) ){
                out.println("</TR>");
                j = 0;
            }
        }
        out.println("<TR>");
        TOOLS_HTML.OpenTdTable(out,8,"right");
        TOOLS_HTML.AddButtonNew(out,"informations","New"+servlet+"","New","Champ","Enregistrer","ajouter.gif","ajouter");
        out.println("</TD></TR></FORM></TABLE></DIV>");
    }
    
    static public void Mod(PrintWriter out, ArrayList list_New, ArrayList list_Ressource, ArrayList size, ArrayList sizeMax, ArrayList list_Mod, String servlet) {
        
        TOOLS_HTML.getMessage(out,"h1","Veuillez renseignez tous les champs qui vous seront utiles");
        TOOLS_HTML.OpenDiv(out,"corps_table");
        TOOLS_HTML.OpenTable(out,"100%",0);
        TOOLS_HTML.OpenForm(out,"Champ");
        int j = 0;
        for(int i = 0; i < list_New.size(); i ++){
            
            chaine = ""+list_Ressource.get(i+1)+"";
            chaine = chaine.toUpperCase();
            
            if(j == 0)
                TOOLS_HTML.OpenTrTable(out);
            out.println("<td align = right>"+chaine+" : </td><td align = left><input type='text' value='"+list_Mod.get(i)+"' name='"+list_New.get(i)+"' style='width:"+size.get(i)+"' maxlength='"+sizeMax.get(i)+"'></td>");
            j++;
            if(j == (list_New.size()/3) ){
                TOOLS_HTML.CloseTrTable(out);
                j = 0;
            }
        }
        TOOLS_HTML.OpenTrTable(out);
        TOOLS_HTML.OpenTdTable(out,8,"right");
        TOOLS_HTML.AddButtonMod(out,"informations","Modify"+servlet+"","Modify","champ","Enregistrer","ajouter.gif","ajouter");
        TOOLS_HTML.CloseTdTable(out);
        TOOLS_HTML.CloseTrTable(out);
        TOOLS_HTML.CloseForm(out);
        TOOLS_HTML.CloseTable(out);
        TOOLS_HTML.CloseDiv(out);
        
    }
    
    static public void NewButtonCreation(PrintWriter out, String servlet, String name) {
        OpenDiv(out,"bouton1");
        OpenUL(out);
        OpenLI(out);
        out.println("<a href=javascript:getHTMLCodeRequest('informations','New"+servlet+"','choix_menu=Null','null');>"+name+"</a>");
        CloseLI(out);
        CloseUL(out);
        CloseDiv(out);
    }
    
    static public void getMessage(PrintWriter out, String format, String msg) {
        out.println("<"+format+">"+msg+"</"+format+">");
    }
    static public void Br(PrintWriter out,int nbr) {
        for(int i = 0; i<nbr; i++)
            out.println("<br>");
    }
    static public void OpenForm(PrintWriter out, String name) {
        out.println("<form name="+name+">");
    }
    static public void CloseForm(PrintWriter out) {
        out.println("</form>");
    }
    static public void OpenDL(PrintWriter out) {
        out.println("<dl>");
    }
    static public void CloseDL(PrintWriter out) {
        out.println("</dl>");
    }
    static public void OpenDD(PrintWriter out, String name) {
        out.println("<dd id='"+name+"'>");
    }
    static public void CloseDD(PrintWriter out) {
        out.println("</dd>");
    }
    static public void OpenDT(PrintWriter out) {
        out.println("<dt>");
    }
    static public void CloseDT(PrintWriter out) {
        out.println("</dt>");
    }
    static public void OpenDiv(PrintWriter out, String name) {
        out.println("<div id="+name+">");
    }
    static public void CloseDiv(PrintWriter out) {
        out.println("</div>");
    }
    static public void OpenTable(PrintWriter out, String width, int border) {
        out.println("<table width="+width+" border="+border+">");
    }
    static public void OpenTable(PrintWriter out, String width) {
        out.println("<table width="+width+" border=0>");
    }
    static public void CloseTable(PrintWriter out) {
        out.println("</table>");
    }
    static public void OpenUL(PrintWriter out) {
        out.println("<ul>");
    }
    static public void CloseUL(PrintWriter out) {
        out.println("</ul>");
    }
    static public void OpenLI(PrintWriter out) {
        out.println("<li>");
    }
    static public void CloseLI(PrintWriter out) {
        out.println("</li>");
    }
    static public void OpenTrTable(PrintWriter out) {
        out.println("<tr>");
    }
    static public void CloseTrTable(PrintWriter out) {
        out.println("</tr>");
    }
    
    static public void OpenTdTable(PrintWriter out, int colspan, String align) {
        out.println("<td colspan="+colspan+" align='"+align+"'>");
    }
    
    static public void OpenTdTable(PrintWriter out) {
        out.println("<td>");
    }
    
    static public void CloseTdTable(PrintWriter out) {
        out.println("</td>");
    }
    static public void AddButtonNew(PrintWriter out, String div, String servlet, String choix_menu, String form, String nameLink, String Img1, String Img2) {
        out.println("<a href=javascript:getHTMLCodeRequest('"+div+"','"+servlet+"','choix_menu="+choix_menu+"','"+form+"')><img src='images/"+Img1+"' width='48' height='48' alt='"+nameLink+"' border='0' onMouseOver='change_image(1,"+Img2+"gris)' onMouseOut='change_image(1,"+Img2+")'/></a>");
    }
    static public void AddButton(PrintWriter out, String div, String servlet, String choix_menu, String form, String nameLink, String Img1, String Img2) {
        out.println("<a href=javascript:getHTMLCodeRequest('"+div+"','"+servlet+"','choix_menu="+choix_menu+"','"+form+"')><img src='images/"+Img1+"' width='48' height='48' alt='"+nameLink+"' border='0' onMouseOver='change_image(1,"+Img2+"gris)' onMouseOut='change_image(1,"+Img2+")'/></a>");
    }
    static public void AddButtonMod(PrintWriter out, String div, String servlet, String choix_menu, String form, String nameLink, String Img1, String Img2) {
        out.println("<a href=javascript:getHTMLCodeRequest('"+div+"','"+servlet+"','choix_menu="+choix_menu+"','"+form+"','null')><img src='images/"+Img1+"' width='48' height='48' alt='"+nameLink+"' border='0' onMouseOver='change_image(1,"+Img2+"gris)' onMouseOut='change_image(1,"+Img2+")'/></a>");
    }
    
    
    static public void Select(PrintWriter out, String name,ArrayList listParam, ArrayList listAff) {
        
        out.println("<select name="+name+">");
        for(int i = 0 ; i < listParam.size(); i ++)
            out.println("<option value="+listParam.get(i)+">"+listAff.get(i)+"</option>");
        out.println("</select>");
        
    }
    static public void Input(PrintWriter out, String type, String nameInput, String taille, String value) {
        out.println("<input type="+type+" value='"+value+"' name="+nameInput+" style='width:"+taille+"'>");
    }
    static public void Input(PrintWriter out, String type, String nameInput, String taille) {
        out.println("<input type="+type+" name="+nameInput+" style='width:"+taille+"'>");
    }
    
    static public void NewCorpsTable(HttpServletRequest request, PrintWriter out, ArrayList list, int taille) {
        
        int e = 1;
        int l = 0;
        int k = 0;
        
        String color = "#EEEEEE";
        
        OpenTrTable(out);
        for(int i=0;i < list.size();i++){
            
            out.println("<td style='background-color: "+color+"; padding: 20px;'>"+list.get(i)+"</td>");
            if(e == taille){
                CloseTrTable(out);
                OpenTrTable(out);
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
        CloseTrTable(out);
    }
    
    static public void NewCorpsTable(HttpServletRequest request, PrintWriter out, ArrayList list, ArrayList list_code, int taille, String servlet, boolean recherche) {
        
        int h=1;
        int j=2;
        
        if(recherche){
            h=2;
            j=3;
            getMessage(out,"h6","NOMBRE DE RESULTATS : "+list.size()/taille);
        }
        
        int e = 1;
        int l = 0;
        int k = 0;
        
        String color = "#EEEEEE";
        
        OpenTrTable(out);
        for(int i=0;i < list.size();i++){
            
            out.println("<td style='background-color: "+color+"; padding: 20px;'>"+list.get(i)+"</td>");
            if(e == taille){
                if(utilisateur.TestAdministrateur() || utilisateur.TestModerateur())
                    out.println(AddChoiceTable(request,out,servlet,""+list_code.get(l)+"",h,j,color));
                CloseTrTable(out);
                OpenTrTable(out);
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
        CloseTrTable(out);
    }
    
    static public String AddChoiceTable(HttpServletRequest request, PrintWriter out, String servlet, String code, int h, int j,String color) {
        
        utilisateur = getUtilisateur(request, utilisateur);
        
        if(utilisateur.TestAdministrateur() || utilisateur.TestModerateur()){
            
            chaine = "<td style='background-color: "+color+"'><a href=javascript:getHTMLCodeRequest('informations','Modify"+servlet+"','choix_menu=null','no_form','"+code+"')><img src='images/modifier.gif' alt='Modifier' onMouseOver='change_image("+h+",modifiergris)' border='0' onMouseOut='change_image("+h+",modifier)'/></a>";
            if(utilisateur.TestAdministrateur())
                chaine = chaine +"<a href=javascript:getHTMLCodeRequest('informations','Delete"+servlet+"','choix_menu=Delete','no_form','"+code+"')><img src='images/supprimer.gif' alt='Supprimer' onMouseOver='change_image("+j+",supprimergris)' border='0' onMouseOut='change_image("+j+",supprimer)'/></a></td>";
            
        }
        return chaine;
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
    
    static public UtilisateursDAO getUtilisateur(HttpServletRequest request,UtilisateursDAO utilisateur) {
        HttpSession session = request.getSession(true);
        utilisateur = (UtilisateursDAO) session.getAttribute("modelUtilisateurs");
        return utilisateur;
    }
    
    
    static public void Search(HttpServletRequest request, HttpServletResponse response, PrintWriter out, String servlet, ArrayList listParam, ArrayList listAff){
        try {
            
            AjaxModels_Onglets.getOnglet(request,response,"Recherche",servlet);
            TOOLS_HTML.OpenForm(out,"Champ");
            TOOLS_HTML.getMessage(out,"h1","RECHERCHE PAR MOTS CLEFS");
            TOOLS_HTML.OpenTable(out,"20%");
            
            TOOLS_HTML.OpenTrTable(out);
            TOOLS_HTML.OpenTdTable(out);
            
            TOOLS_HTML.Select(out,"filtre",listParam,listAff);
            TOOLS_HTML.CloseTdTable(out);
            
            TOOLS_HTML.OpenTdTable(out);
            TOOLS_HTML.Input(out,"Text","mot_clef","145px","");
            TOOLS_HTML.CloseTdTable(out);
            
            TOOLS_HTML.OpenTdTable(out);
            
            TOOLS_HTML.AddButton(out,"informationsRecherche","Search"+servlet+"","Search","champ","Rechercher","search.gif","search");
            TOOLS_HTML.CloseTdTable(out);
            
            TOOLS_HTML.CloseTrTable(out);
            TOOLS_HTML.CloseTable(out);
            TOOLS_HTML.CloseForm(out);
            
            TOOLS_HTML.OpenDiv(out,"informationsRecherche");
            
            TOOLS_HTML.CloseDiv(out);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ServletException ex) {
            ex.printStackTrace();
        }
        
    }
    
        static public void Search(HttpServletRequest request, HttpServletResponse response, PrintWriter out, String servlet, AgentsDAO agent, boolean PT){
        try {
            
            AjaxModels_Onglets.getOnglet(request,response,"Outils",servlet);
            TOOLS_HTML.OpenForm(out,"Champ");
            if(PT) TOOLS_HTML.getMessage(out,"h1","SUIVI PAR AGENT PT");
            else TOOLS_HTML.getMessage(out,"h1","SUIVI PAR AGENT SE");
            TOOLS_HTML.OpenTable(out,"20%");
            
            TOOLS_HTML.OpenTrTable(out);
            
            TOOLS_HTML.OpenTdTable(out);
            
            TOOLS_HTML.Select(out,"filtre",agent.list_CodeAgent,agent.list_Nom);
            TOOLS_HTML.CloseTdTable(out);
            
            TOOLS_HTML.OpenTdTable(out);
            if(PT) TOOLS_HTML.AddButton(out,"informationsRecherche","SuivisAgentPT","SuivisAgentPT","champ","Rechercher","search.gif","search");
            else TOOLS_HTML.AddButton(out,"informationsRecherche","SuivisAgentSE","SuivisAgentSE","champ","Rechercher","search.gif","search");
            TOOLS_HTML.CloseTdTable(out);
            
            TOOLS_HTML.CloseTrTable(out);
            TOOLS_HTML.CloseTable(out);
            TOOLS_HTML.CloseForm(out);
            
            TOOLS_HTML.OpenDiv(out,"informationsRecherche");
            
            TOOLS_HTML.CloseDiv(out);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ServletException ex) {
            ex.printStackTrace();
        }
        
    }
    
    static public void Search(HttpServletRequest request, HttpServletResponse response, PrintWriter out, String servlet, GaresDAO gare, boolean PT){
        try {
            
            AjaxModels_Onglets.getOnglet(request,response,"Outils",servlet);
            TOOLS_HTML.OpenForm(out,"Champ");
            if(PT) TOOLS_HTML.getMessage(out,"h1","HISTORIQUE DES GARES PT");
            else TOOLS_HTML.getMessage(out,"h1","HISTORIQUE DES GARES SE");
            TOOLS_HTML.OpenTable(out,"20%");
            
            TOOLS_HTML.OpenTrTable(out);
            
            TOOLS_HTML.OpenTdTable(out);
            
            ArrayList list_Gare = null;
            ArrayList list_CodeGare = null;
            list_Gare = new ArrayList();
            list_CodeGare = new ArrayList();
            
            if(PT){
                for(int i = 0 ; i < gare.list_CodeGare.size() ; i++)
                    if(gare.list_Groupe.get(i).equals("PT")){
                    list_Gare.add(gare.list_Gare.get(i));
                    list_CodeGare.add(gare.list_CodeGare.get(i));
                    }
            } else{
                for(int i = 0 ; i < gare.list_CodeGare.size() ; i++)
                    if(gare.list_Groupe.get(i).equals("SE")){
                    list_Gare.add(gare.list_Gare.get(i));
                    list_CodeGare.add(gare.list_CodeGare.get(i));
                    }
            }
            
            TOOLS_HTML.Select(out,"filtre",list_CodeGare,list_Gare);
            TOOLS_HTML.CloseTdTable(out);
            
            TOOLS_HTML.OpenTdTable(out);
            if(PT) TOOLS_HTML.AddButton(out,"informationsRecherche","HistoriquesPT","HistoriquesPT","champ","Rechercher","search.gif","search");
            else TOOLS_HTML.AddButton(out,"informationsRecherche","HistoriquesSE","HistoriquesSE","champ","Rechercher","search.gif","search");
            TOOLS_HTML.CloseTdTable(out);
            
            TOOLS_HTML.CloseTrTable(out);
            TOOLS_HTML.CloseTable(out);
            TOOLS_HTML.CloseForm(out);
            
            TOOLS_HTML.OpenDiv(out,"informationsRecherche");
            
            TOOLS_HTML.CloseDiv(out);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ServletException ex) {
            ex.printStackTrace();
        }
        
    }
    
    static public void Search(HttpServletRequest request, HttpServletResponse response, PrintWriter out, String servlet, EntreprisesDAO entreprise){
        try {
            
            AjaxModels_Onglets.getOnglet(request,response,"Outils",servlet);
            TOOLS_HTML.OpenForm(out,"Champ");
            TOOLS_HTML.getMessage(out,"h1","QUALITE DES ETUDES DES ENTREPRISES");
            TOOLS_HTML.OpenTable(out,"20%");
            
            TOOLS_HTML.OpenTrTable(out);
            
            ArrayList list_Entreprise = null;
            ArrayList list_CodeEntreprise = null;
            list_Entreprise = new ArrayList();
            list_CodeEntreprise = new ArrayList();
            
            for(int i = 0 ; i < entreprise.list_CodeEntreprise.size() ; i++)
                if(entreprise.list_Specialite.get(i).equals("Etudes de signalisation")){
                list_Entreprise.add(entreprise.list_Entreprise.get(i));
                list_CodeEntreprise.add(entreprise.list_CodeEntreprise.get(i));
                }
            
            TOOLS_HTML.OpenTdTable(out);
            TOOLS_HTML.Select(out,"filtre",list_CodeEntreprise,list_Entreprise);
            TOOLS_HTML.CloseTdTable(out);
            
            TOOLS_HTML.OpenTdTable(out);
            TOOLS_HTML.AddButton(out,"informationsRecherche","Qualites","Qualites","Champ","Rechercher","search.gif","search");
            TOOLS_HTML.CloseTdTable(out);
            
            TOOLS_HTML.CloseTrTable(out);
            TOOLS_HTML.CloseTable(out);
            TOOLS_HTML.CloseForm(out);
            
            TOOLS_HTML.OpenDiv(out,"informationsRecherche");
            
            TOOLS_HTML.CloseDiv(out);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ServletException ex) {
            ex.printStackTrace();
        }
        
    }
    
    static public void NewDivAjax(PrintWriter out, String name){
        out.println("<div id='"+name+"' onload=DivLoad2.style.visibility='hidden';></div>");
    }
    
    static public void Show(HttpServletRequest request, PrintWriter out, String servlet, ArrayList list_Ressource, ArrayList list, ArrayList list_code, ArrayList size, int size_Table, boolean search){
        
        TOOLS_HTML.OpenTable(out,"100%");
        TOOLS_HTML.NewTitleTable(request,out,list_Ressource,size,false);
        TOOLS_HTML.NewSeparatorTable(request,out,list_Ressource,"100%",1);
        TOOLS_HTML.NewCorpsTable(request, out, list, list_code, size_Table, servlet,search);
        TOOLS_HTML.CloseTable(out);
        
    }
    
    static public void Show(HttpServletRequest request, PrintWriter out, ArrayList list_Ressource, ArrayList list, ArrayList size, int size_Table){
        
        TOOLS_HTML.OpenTable(out,"100%");
        TOOLS_HTML.NewTitleTable(request,out,list_Ressource,size,true);
        TOOLS_HTML.NewSeparatorTable(request,out,list_Ressource,"100%",1);
        TOOLS_HTML.NewCorpsTable(request, out, list, size_Table);
        TOOLS_HTML.CloseTable(out);
        
    }
    
    
}


