package Models.TOOLS.Ajaxmodels.OBJETS;

import Models.TOOLS.Ajaxmodels.*;
import Models.TOOLS.Utilisateurs.UtilisateursDAO;
import Models.TOOLS.iText.DVSG.Impression;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;

//RABALLAND Romain v3.0

public class ListDAO extends TOOLS_HTML{
    
    static public void AddAffPDF(PrintWriter out, HttpServletRequest request){
        UtilisateursDAO utilisateur = null;
        utilisateur = TOOLS_HTML.getUtilisateur(request, utilisateur);
        out.println("<li><a href="+cheminOuverturePDF+utilisateur.NamePDF+"_data.pdf target='_blank'><span>PDF</span></a></li>");
    }
}
