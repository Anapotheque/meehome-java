package Ajax.COURRIER.COURRIER_ES.SELECTION.Onglets;


import Models.COURRIER.COURRIER_ES.CourriersDAO;
import Models.TOOLS.Ajaxmodels.*;
import Models.TOOLS.Controleur.MegaControleur;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

//RABALLAND Romain v3.0

public class OngletCourrier extends MegaControleur {
    
    static public void getOnglet(HttpServletRequest request, PrintWriter out, String choix_menu, String servlet)
    throws ServletException, IOException {
        try {
            
            //CHARGEMENT DES OBJETS
            CourriersDAO courrier = null;
            courrier = creerObjet(request,courrier);
            
            TOOLS_HTML.OpenDiv(out,"tabsJ");
            TOOLS_HTML.OpenUL(out);

            if(choix_menu.equals("Donnee")){
                out.println("<li id='current'><a href='#'><span>Donnee</span></a></li>");
                out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('DataLettre','TexteCourrier','Null','')><span>Texte</span></a></li>");
                out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('DataLettre','PJCourrier','Null','')><span>Piece Jointe</span></a></li>");
                out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('DataLettre','CopieCourrier','Null','')><span>Copie</span></a></li>");
                if(courrier.TestAccuseReceptionLettre() == 1 && !courrier.codeLettre.equals("33"))
                    out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('DataLettre','ARCourrier','Null','')><span>Accusé</span></a></li>");
                if(courrier.TestSuiviLettre() == 1)
                    out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('DataLettre','SuiviCourrier','Null','')><span>Suivi</span></a></li>");
                out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('Save','"+servlet+"','Null','Champ')><span>Enregistrer</span></a></li>");
                out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('Save','GenerationPDFCourrier','Null')><span>Creation PDF</span></a></li>");
            }
            
            else if(choix_menu.equals("Texte")){
                out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('DataLettre','ShowDonneeCourrier','Null','Champ')><span>Donnee</span></a></li>");
                out.println("<li id='current'><a href='#'><span>Texte</span></a></li>");
                out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('DataLettre','PJCourrier','Null','')><span>Piece Jointe</span></a></li>");
                out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('DataLettre','CopieCourrier','Null','')><span>Copie</span></a></li>");
                if(courrier.TestAccuseReceptionLettre() == 1)
                    out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('DataLettre','ARCourrier','Null','')><span>Accusé</span></a></li>");
                if(courrier.TestSuiviLettre() == 1)
                    out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('DataLettre','SuiviCourrier','Null','')><span>Suivi</span></a></li>");
                out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('Save','"+servlet+"','Null','Champ')><span>Enregistrer</span></a></li>");
                out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('Save','GenerationPDFCourrier','Null')><span>Creation PDF</span></a></li>");
                
            }
            
            
            else if(choix_menu.equals("PJ")){
                out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('DataLettre','ShowDonneeCourrier','Null','Champ')><span>Donnee</span></a></li>");
                out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('DataLettre','TexteCourrier','Null','')><span>Texte</span></a></li>");
                out.println("<li id='current'><a href='#'><span>Piece Jointe</span></a></li>");
                out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('DataLettre','CopieCourrier','Null','')><span>Copie</span></a></li>");
                if(courrier.TestAccuseReceptionLettre() == 1)
                    out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('DataLettre','ARCourrier','Null','')><span>Accusé</span></a></li>");
                if(courrier.TestSuiviLettre() == 1)
                    out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('DataLettre','SuiviCourrier','Null','')><span>Suivi</span></a></li>");
                out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('Save','"+servlet+"','Null','Champ')><span>Enregistrer</span></a></li>");
                out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('Save','GenerationPDFCourrier','Null')><span>Creation PDF</span></a></li>");
                
            }
            
            
            else if(choix_menu.equals("Copie")){
                out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('DataLettre','ShowDonneeCourrier','Null','Champ')><span>Donnee</span></a></li>");
                out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('DataLettre','TexteCourrier','Null','')><span>Texte</span></a></li>");
                out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('DataLettre','PJCourrier','Null','')><span>Piece Jointe</span></a></li>");
                out.println("<li id='current'><a href='#'><span>Copie</span></a></li>");
                if(courrier.TestAccuseReceptionLettre() == 1)
                    out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('DataLettre','ARCourrier','Null','')><span>Accusé</span></a></li>");
                if(courrier.TestSuiviLettre() == 1)
                    out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('DataLettre','SuiviCourrier','Null','')><span>Suivi</span></a></li>");
                out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('Save','"+servlet+"','Null','Champ')><span>Enregistrer</span></a></li>");
                out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('Save','GenerationPDFCourrier','Null')><span>Creation PDF</span></a></li>");
                
            }
            
            
            else if(choix_menu.equals("AR")){
                out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('DataLettre','ShowDonneeCourrier','Null','Champ')><span>Donnee</span></a></li>");
                out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('DataLettre','TexteCourrier','Null','')><span>Texte</span></a></li>");
                out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('DataLettre','PJCourrier','Null','')><span>Piece Jointe</span></a></li>");
                out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('DataLettre','CopieCourrier','Null','')><span>Copie</span></a></li>");
                if(courrier.TestAccuseReceptionLettre() == 1)
                    out.println("<li id='current'><a href='#'><span>Accusé</span></a></li>");
                if(courrier.TestSuiviLettre() == 1)
                    out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('DataLettre','SuiviCourrier','Null','')><span>Suivi</span></a></li>");
                out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('Save','"+servlet+"','Null','Champ')><span>Enregistrer</span></a></li>");
                out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('Save','GenerationPDFCourrier','Null')><span>Creation PDF</span></a></li>");
                
            }
            
            
            else if(choix_menu.equals("Suivi")){
                out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('DataLettre','ShowDonneeCourrier','Null','Champ')><span>Donnee</span></a></li>");
                out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('DataLettre','TexteCourrier','Null','')><span>Texte</span></a></li>");
                out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('DataLettre','PJCourrier','Null','')><span>Piece Jointe</span></a></li>");
                out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('DataLettre','ARCourrier','Null','')><span>Copie</span></a></li>");
                if(courrier.TestAccuseReceptionLettre() == 1)
                    out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('DataLettre','ARCourrier','Null','')><span>Accusé</span></a></li>");
                if(courrier.TestSuiviLettre() == 1)
                    out.println("<li id='current'><a href='#'><span>Suivi</span></a></li>");
                out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('Save','"+servlet+"','Null','Champ')><span>Enregistrer</span></a></li>");
                out.println("<li><a href=javascript:getHTMLCodeRequestCourrier('Save','GenerationPDFCourrier','Null')><span>Creation PDF</span></a></li>");
                
            }
            
            
            TOOLS_HTML.CloseUL(out);
            TOOLS_HTML.CloseDiv(out); //FIN DIV CORPS
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
}
