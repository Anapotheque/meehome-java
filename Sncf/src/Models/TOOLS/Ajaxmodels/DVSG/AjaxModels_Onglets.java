package Models.TOOLS.Ajaxmodels.DVSG;


import Models.TOOLS.Ajaxmodels.*;
import Models.TOOLS.Ajaxmodels.OBJETS.ListDAO;
import Models.TOOLS.Utilisateurs.UtilisateursDAO;
import Models.TOOLS.iText.DVSG.Impression;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//RABALLAND Romain v3.0

public class AjaxModels_Onglets {
    
    static public void getOnglet(HttpServletRequest request, HttpServletResponse response, String choix, String servlet)
    throws ServletException, IOException {
        
        UtilisateursDAO utilisateur = null;
        utilisateur = TOOLS_HTML.getUtilisateur(request, utilisateur);
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        TOOLS_HTML.OpenDiv(out,"corps");
                
        TOOLS_HTML.OpenDiv(out,"tabsJ");
        TOOLS_HTML.OpenUL(out);
        
        if(choix.equals("Affichage")){
            
            out.println("<li id='current'><a href='#'><span>Affichage</span></a></li>");
            if(utilisateur.TestAdministrateur() || utilisateur.TestModerateur())
                out.println("<li><a href=javascript:getHTMLCodeRequest('informations','New"+servlet+"','choix_menu=Null','null')><span>Creation</span></a></li> ");
            out.println("<li><a href=javascript:getHTMLCodeRequest('informations','Search"+servlet+"','choix_menu=null','null')><span>Recherche</span></a></li>");
            ListDAO.AddAffPDF(out,request);
            
            if(servlet.equals("Factures"))
                out.println("<li><a href=javascript:getHTMLCodeRequest('informations','DeleteFactures','choix_menu=SupprAll','null')><span>Tout supprimer</span></a></li> ");
            
            if(servlet.equals("Even"))
                out.println("<li><a href=javascript:getHTMLCodeRequest('informations','ShowGares','choix_menu=Null','null')><span>Gares</span></a></li> ");
            if(servlet.equals("Gares")){
                out.println("<li><a href=javascript:getHTMLCodeRequest('informations','ShowEven','choix_menu=Null','null')><span>Even</span></a></li> ");
                }
            
            if(!servlet.equals("Utilisateurs"))
                out.println("<li><a href=javascript:getHTMLCodeRequest('informations','IndexDVSG','choix_menu=Null')><span>Retour</span></a></li>");
            
            out.println("<li><a href=javascript:getHTMLCodeRequest('informations','connection','mode_connect=Null','null')><span>Quitter</span></a></li>");
            
            TOOLS_HTML.CloseUL(out);
            TOOLS_HTML.CloseDiv(out);
            TOOLS_HTML.Br(out,2);
            
            TOOLS_HTML.getMessage(out,"h5","SNCF.ETUDES."+servlet.toUpperCase()+".AFFICHAGE");
            
        }
        if(choix.equals("Creation")){
            out.println("<li id='current'><a href='#'><span>Creation</span></a></li> ");
            out.println("<li><a href=javascript:getHTMLCodeRequest('informations','Show"+servlet+"','choix_menu=Null','null') onClick=show()><span>Retour</span></a></li>");
            TOOLS_HTML.CloseUL(out);
            TOOLS_HTML.CloseDiv(out);
            TOOLS_HTML.Br(out,2);
            
            TOOLS_HTML.getMessage(out,"h5","SNCF.ETUDES."+servlet.toUpperCase()+".CREATION");
            
        }
        if(choix.equals("Recherche")){
            out.println("<li id='current'><a href='#'><span>Recherche</span></a></li>");
            ListDAO.AddAffPDF(out,request);
            out.println("<li><a href=javascript:getHTMLCodeRequest('informations','Show"+servlet+"','choix_menu=Null','null') onClick=show()><span>Retour</span></a></li>");
            
            TOOLS_HTML.CloseUL(out);
            TOOLS_HTML.CloseDiv(out);
            TOOLS_HTML.Br(out,2);
            
            TOOLS_HTML.getMessage(out,"h5","SNCF.ETUDES."+servlet.toUpperCase()+".RECHERCHE");
            
        }
        
        if(choix.equals("Outils")){
            ListDAO.AddAffPDF(out,request);
            out.println("<li><a href=javascript:getHTMLCodeRequest('informations','Outils','choix_menu=Null','null') onClick=show()><span>Retour</span></a></li>");
            out.println("<li><a href=javascript:getHTMLCodeRequest('informations','connection','mode_connect=Null','null')><span>Quitter</span></a></li>");
            TOOLS_HTML.CloseUL(out);
            TOOLS_HTML.CloseDiv(out);
            TOOLS_HTML.Br(out,2);
            
        }
        
        if(choix.equals("Modify")){
            out.println("<li id='current'><a href='#'><span>Modifier</span></a></li>");
            out.println("<li><a href=javascript:getHTMLCodeRequest('informations','Show"+servlet+"','choix_menu=Null','null') onClick=show()><span>Retour</span></a></li>");
            
            TOOLS_HTML.CloseUL(out);
            TOOLS_HTML.CloseDiv(out);
            TOOLS_HTML.Br(out,2);
            
            TOOLS_HTML.getMessage(out,"h5","SNCF.ETUDES."+servlet.toUpperCase()+".MODIFICATION");
            
        }
        
        if(servlet.equals("Inscription")){
            out.println("<li id='current'><a href='#'><span>Inscription</span></a></li>");
            TOOLS_HTML.getMessage(out,"h4",servlet.toUpperCase());
            TOOLS_HTML.getMessage(out,"h5","SNCF.DVSG."+servlet.toUpperCase()+".INSCRIPTION");
        }
        if(servlet.equals("DoneInscription")){
            out.println("<li id='current'><a href='#'><span>Action</span></a></li>");
            
            TOOLS_HTML.CloseUL(out);
            TOOLS_HTML.CloseDiv(out);
            TOOLS_HTML.Br(out,2);
            
            TOOLS_HTML.getMessage(out,"h5","SNCF.ETUDES."+servlet.toUpperCase()+".SUCCES");
        }
    }
}
