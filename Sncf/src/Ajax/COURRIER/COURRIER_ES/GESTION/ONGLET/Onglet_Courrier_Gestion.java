package Ajax.COURRIER.COURRIER_ES.GESTION.ONGLET;

import Models.COURRIER.COURRIER_ES.CourriersDAO;
import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Controleur.MegaControleur;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

//RABALLAND Romain v3.0
public class Onglet_Courrier_Gestion extends MegaControleur {
    
    static public void getOnglet(HttpServletRequest request, PrintWriter out, String choix_menu, String servlet)
    throws ServletException, IOException {
        
        
        //CHARGEMENT DES OBJETS
        CourriersDAO courrier = null;
        courrier = creerObjet(request,courrier);
        
        
        TOOLS_HTML.OpenDiv(out,"tabsJ");
        TOOLS_HTML.OpenUL(out);
        
        
        if(choix_menu.equals("TEXTE")){
            out.println("<li id='current'><a href='#'><span>Texte</span></a></li>");
            out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('dataGestion','GetTexteGrasGestionCourrier','Null','')><span>Texte Gras</span></a></li>");
            out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('dataGestion','GetPJGestionCourrier','Null','')><span>Piece Jointe</span></a></li>");
            out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('dataGestion','GetCopieGestionCourrier','Null','')><span>Copie</span></a></li>");
            out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('dataGestion','GetARGestionCourrier','Null','')><span>Accusé</span></a></li>");
            out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('dataGestion','GetSuiviGestionCourrier','Null','')><span>Suivi</span></a></li>");
            out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('Save','"+servlet+"','Null','Champ')><span>Enregistrer</span></a></li>");
        }
        
        
        if(choix_menu.equals("TEXTEGRAS")){
            out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('dataGestion','GetTexteGestionCourrier','Null','')><span>Texte</span></a></li>");
            out.println("<li id='current'><a href='#'><span>Texte Gras</span></a></li>");
            out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('dataGestion','GetPJGestionCourrier','Null','')><span>Piece Jointe</span></a></li>");
            out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('dataGestion','GetCopieGestionCourrier','Null','')><span>Copie</span></a></li>");
            out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('dataGestion','GetARGestionCourrier','Null','')><span>Accusé</span></a></li>");
            out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('dataGestion','GetSuiviGestionCourrier','Null','')><span>Suivi</span></a></li>");
            out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('Save','"+servlet+"','Null','Champ')><span>Enregistrer</span></a></li>");
        }
        
        
        if(choix_menu.equals("PJ")){
            out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('dataGestion','GetTexteGestionCourrier','Null','')><span>Texte</span></a></li>");
            out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('dataGestion','GetTexteGrasGestionCourrier','Null','')><span>Texte Gras</span></a></li>");
            out.println("<li id='current'><a href='#'><span>Piece Jointe</span></a></li>");
            out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('dataGestion','GetCopieGestionCourrier','Null','')><span>Copie</span></a></li>");
            out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('dataGestion','GetARGestionCourrier','Null','')><span>Accusé</span></a></li>");
            out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('dataGestion','GetSuiviGestionCourrier','Null','')><span>Suivi</span></a></li>");
            out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('Save','"+servlet+"','Null','Champ')><span>Enregistrer</span></a></li>");
        }
        
        
        if(choix_menu.equals("COPIE")){
            out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('dataGestion','GetTexteGrasGestionCourrier','Null','')><span>Texte</span></a></li>");
            out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('dataGestion','GetTexteGrasGestionCourrier','Null','')><span>Texte Gras</span></a></li>");
            out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('dataGestion','GetPJGestionCourrier','Null','')><span>Piece Jointe</span></a></li>");
            out.println("<li id='current'><a href='#'><span>Copie</span></a></li>");
            out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('dataGestion','GetARGestionCourrier','Null','')><span>Accusé</span></a></li>");
            out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('dataGestion','GetSuiviGestionCourrier','Null','')><span>Suivi</span></a></li>");
            out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('Save','"+servlet+"','Null','Champ')><span>Enregistrer</span></a></li>");
        }
        
        
        if(choix_menu.equals("AR")){
            out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('dataGestion','GetTexteGrasGestionCourrier','Null','')><span>Texte</span></a></li>");
            out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('dataGestion','GetTexteGrasGestionCourrier','Null','')><span>Texte Gras</span></a></li>");
            out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('dataGestion','GetPJGestionCourrier','Null','')><span>Piece Jointe</span></a></li>");
            out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('dataGestion','GetCopieGestionCourrier','Null','')><span>Copie</span></a></li>");
            out.println("<li id='current'><a href='#'><span>Accusé</span></a></li>");
            out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('dataGestion','GetSuiviGestionCourrier','Null','')><span>Suivi</span></a></li>");
            out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('Save','"+servlet+"','Null','Champ')><span>Enregistrer</span></a></li>");
        }
        
        if(choix_menu.equals("SUIVI")){
            out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('dataGestion','GetTexteGrasGestionCourrier','Null','')><span>Texte</span></a></li>");
            out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('dataGestion','GetTexteGrasGestionCourrier','Null','')><span>Texte Gras</span></a></li>");
            out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('dataGestion','GetPJGestionCourrier','Null','')><span>Piece Jointe</span></a></li>");
            out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('dataGestion','GetCopieGestionCourrier','Null','')><span>Copie</span></a></li>");
            out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('dataGestion','GetARGestionCourrier','Null','')><span>Accusé</span></a></li>");
            out.println("<li id='current'><a href='#'><span>Suivi</span></a></li>");
            out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('Save','"+servlet+"','Null','Champ')><span>Enregistrer</span></a></li>");
        }
        
        TOOLS_HTML.CloseUL(out);
        TOOLS_HTML.CloseDiv(out); //FIN DIV CORPS
        
    }
}
