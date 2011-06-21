package Ajax.TRAVAUX.MARCHES.AJOUT;

import Models.ETUDES.Entreprises.EntreprisesDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.ButtonDAO;
import Models.TOOLS.Ajaxmodels.GetMessageInformationDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.InputDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.SelectDAO;
import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Controleur.MegaControleur;
import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class SaisieMarches extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        try {
            
            //RECUPERATION DES OBJETS
            EntreprisesDAO entreprise= null;
            entreprise = creerObjet(request,entreprise);
            
            entreprise.Set();
            
            //INITIALISATION DU FLUX DE COMMUNICATION AVEC LA PAGE JSP
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            //LIGNE ROUGE
            out.println("<div id=ajout>");
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation1",".: SNCF :: TRAVAUX :: SAISIE MARCHES :.<br>");
            out.println("</div>");  //FIN AJOUT
            
            //DIV DE MISE EN FORME CADRE GRIS
            out.println("<div id=Divcontenu>");
            
            //DIV DE MISE EN FORME ALIGNEMENT GAUCHE
            out.println("<div Style='text-align: left'>");
            
            //CONTENU DE LA PAGE AFFICHAGE
            
            //NUMERO DE COMMANDE
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>Commande : ");
            InputDAO.AddInput(out,"text","commande","250px");
            
            //ENTREPRISE
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>Entreprise : ");
            SelectDAO.AddSelect(out,"entreprise",entreprise.list_CodeEntreprise,entreprise.list_Entreprise,1);
            
            //CALQUES ET SAISIE DAO
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation1","<br><br>CALQUES ET SAISIE DAO ( Série de prix études signalisation 10/2004 )<br><br>");
            
            TOOLS_HTML.OpenTable(out,"60%");
            out.println("<th></th>");
            out.println("<th>Prix de base en €</th>");
            out.println("<th>Majoration / Minoration en %</th>");
            out.println("<th>Prix Total</th>");
            
            out.println("<tr>");
            
            out.println("<td>");
            out.println("Etablissement de document ( le pli )");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","EtablissementDocumentPrix","100px","13.72","onkeyup=javascript:CalculePrixEtablissement()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","EtablissementDocumentPourcentage","100px","0.00","onkeyup=javascript:CalculePrixEtablissement()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","EtablissementDocumentPrixTotale","100px","13.72",true);
            out.println("</td>");
            
            out.println("</tr>");
            out.println("<tr>");
            
            out.println("<td>");
            out.println("Modification de document ( le pli )");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","ModificationDocumentPrix","100px","5.72","onkeyup=javascript:CalculePrixModification()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","ModificationDocumentPourcentage","100px","0.00","onkeyup=javascript:CalculePrixModification()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","ModificationDocumentPrixTotale","100px","5.72",true);
            out.println("</td>");
            
            out.println("</tr>");
            out.println("<tr>");
            
            out.println("<td>");
            out.println("Traitement RC ( le folio )");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","TraitementPrix","100px","4.70","onkeyup=javascript:CalculePrixTraitement()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","TraitementPourcentage","100px","0.00","onkeyup=javascript:CalculePrixTraitement()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","TraitementPrixTotale","100px","4.70",true);
            out.println("</td>");
            
            out.println("</tr>");
            out.println("<tr>");
            
            out.println("<td>");
            out.println("Teintage de plan ( le Kg )");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","TeintagePrix","100px","58.37","onkeyup=javascript:CalculePrixTeintage()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","TeintagePourcentage","100px","0.00","onkeyup=javascript:CalculePrixTeintage()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","TeintagePrixTotale","100px","58.37",true);
            out.println("</td>");
            
            out.println("</tr>");
            out.println("<tr>");
            
            out.println("<td>");
            out.println("Bordage de plan ( le ml )");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","BordagePrix","100px","0.50","onkeyup=javascript:CalculePrixBordage()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","BordagePourcentage","100px","0.00","onkeyup=javascript:CalculePrixBordage()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","BordagePrixTotale","100px","0.50",true);
            out.println("</td>");
            
            out.println("</tr>");
            
            out.println("</table>");
            
            //REPROGRAPHIE
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation1","<br><br>REPROGRAPHIE ( Série de prix reprographie 02/1991 )<br><br>");
            
            TOOLS_HTML.OpenTable(out,"60%");
            out.println("<th></th>");
            out.println("<th>Prix de base en €</th>");
            out.println("<th>Majoration en %</th>");
            out.println("<th>Prix Total</th>");
            
            out.println("<tr>");
            
            out.println("<td>");
            out.println("Tirage Helio ( le kg )");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","HelioPrix","100px","21.34","onkeyup=javascript:CalculePrixHelio()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","HelioPourcentage","100px","0.00","onkeyup=javascript:CalculePrixHelio()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","HelioPrixTotale","100px","21.34",true);
            out.println("</td>");
            
            out.println("</tr>");
            out.println("<tr>");
            
            out.println("<td>");
            out.println("Photocopie Calque ( le kg )");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","PhotocopiePrix","100px","13.72","onkeyup=javascript:CalculePrixPhotocopie()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","PhotocopiePourcentage","100px","0.00","onkeyup=javascript:CalculePrixPhotocopie()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","PhotocopiePrixTotale","100px","13.72",true);
            out.println("</td>");
            
            out.println("</tr>");
            out.println("<tr>");
            
            out.println("<td>");
            out.println("CC 1 Pli A4");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","CC1Prix","100px","0.95","onkeyup=javascript:CalculePrixCC1()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","CC1Pourcentage","100px","0.00","onkeyup=javascript:CalculePrixCC1()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","CC1PrixTotale","100px","0.95",true);
            out.println("</td>");
            
            out.println("</tr>");
            out.println("<tr>");
            
            out.println("<td>");
            out.println("CC 2 Pli A4");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","CC2Prix","100px","1.58","onkeyup=javascript:CalculePrixCC2()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","CC2Pourcentage","100px","0.00","onkeyup=javascript:CalculePrixCC2()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","CC2PrixTotale","100px","1.58",true);
            out.println("</td>");
            
            out.println("</tr>");
            out.println("<tr>");
            
            out.println("<td>");
            out.println("CC 3 Pli A4");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","CC3Prix","100px","2.13","onkeyup=javascript:CalculePrixCC3()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","CC3Pourcentage","100px","0.00","onkeyup=javascript:CalculePrixCC3()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","CC3PrixTotale","100px","2.13",true);
            out.println("</td>");
            
            out.println("</tr>");
            out.println("<tr>");
            
            out.println("<td>");
            out.println("CC >3 Pli A4");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","CCSupPrix","100px","0.55","onkeyup=javascript:CalculePrixCCSup()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","CCSupPourcentage","100px","0.00","onkeyup=javascript:CalculePrixCCSup()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","CCSupPrixTotale","100px","0.55",true);
            out.println("</td>");
            
            out.println("</tr>");
            
            out.println("</table>");
            
            //DAO
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation1","<br><br>DAO ( Série de prix DAO du 05 / 2004 )<br><br>");
            
            TOOLS_HTML.OpenTable(out,"60%");
            out.println("<th></th>");
            out.println("<th>Prix de base en €</th>");
            out.println("<th>Majoration en %</th>");
            out.println("<th>Prix Total</th>");
            
            out.println("<tr>");
            
            out.println("<td>");
            out.println("Tracé DAO 1 pli A4");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","DAO1A4Prix","100px","0.61","onkeyup=javascript:CalculePrixDAO1A4()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","DAO1A4Pourcentage","100px","0.00","onkeyup=javascript:CalculePrixDAO1A4()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","DAO1A4PrixTotale","100px","0.61",true);
            out.println("</td>");
            
            out.println("</tr>");
            out.println("<tr>");
            
            out.println("<td>");
            out.println("Tracé DAO 2 pli A4");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","DAO2A4Prix","100px","0.76","onkeyup=javascript:CalculePrixDAO2A4()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","DAO2A4Pourcentage","100px","0.00","onkeyup=javascript:CalculePrixDAO2A4()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","DAO2A4PrixTotale","100px","0.76",true);
            out.println("</td>");
            
            out.println("</tr>");
            out.println("<tr>");
            
            out.println("<td>");
            out.println("Tracé DAO 3 pli A4");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","DAO3A4Prix","100px","0.91","onkeyup=javascript:CalculePrixDAO3A4()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","DAO3A4Pourcentage","100px","0.00","onkeyup=javascript:CalculePrixDAO3A4()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","DAO3A4PrixTotale","100px","0.91",true);
            out.println("</td>");
            
            out.println("</tr>");
            
            out.println("<tr>");
            
            out.println("<td>");
            out.println("Supplement");
            out.println("</td>");
            out.println("<td>");
            
            InputDAO.AddInput(out,"text","SupplementPrix","100px","0.08","onkeyup=javascript:CalculePrixSupplement()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","SupplementPourcentage","100px","0.00","onkeyup=javascript:CalculePrixSupplement()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","SupplementPrixTotale","100px","0.08",true);
            out.println("</td>");
            
            out.println("</tr>");
            
            out.println("</table>");
            
            
            //DIV DE MISE EN FORME ENREGISTREMENT
            out.println("<div id=Enregistrement>");
            out.println("</div>");  //FIN Enregistrement
            
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestTravaux('Enregistrement','EnregistrerCommande','Champ')","Enregistrer");
            ButtonDAO.AddButtonStyle(out,1,"javascript:Ajax('informations','AccueilTravaux')","Retour");
            //FIN DU CONTENU DE LA PAGE
            
            out.println("</div>");  //FIN ALIGN LEFT
            out.println("</div>");  //FIN Divcontenu
            
            //FERMETURE DU FLUX DE SORTIE
            out.close();
            
        } catch (SQLException ex) {
            retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState());
            System.out.println("\n\nDEBUT ERREUR<==========================================\n");
            ex.printStackTrace();
            System.out.println("\nFIN ERREUR<==============================================\n\n");
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
}
