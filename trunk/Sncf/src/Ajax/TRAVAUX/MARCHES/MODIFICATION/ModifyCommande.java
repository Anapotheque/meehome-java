package Ajax.TRAVAUX.MARCHES.MODIFICATION;

import Models.ETUDES.Entreprises.EntreprisesDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.ButtonDAO;
import Models.TOOLS.Ajaxmodels.GetMessageInformationDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.InputDAO;
import Models.TOOLS.Ajaxmodels.OBJETS.SelectDAO;
import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Controleur.MegaControleur;
import Models.TRAVAUX.CommandesDAO;
import Models.TRAVAUX.TravauxDAO;
import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class ModifyCommande extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        try {
            
            //RECUPERATION DES OBJETS
            EntreprisesDAO entreprise= null;
            entreprise = creerObjet(request,entreprise);
            
            CommandesDAO commande= null;
            commande = creerObjet(request,commande);
            
            entreprise.Set();
            
            commande.codeCommande = request.getParameter("CodeCommande");
            
            commande.GetCommande(commande.codeCommande);
            
            float prix = 0;
            float pourcent = 0;
            
            String Prixtemp = "";
            String Pourcenttemp = "";
            
            float total = 0;
            
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
            
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>Commande : ");
            InputDAO.AddInput(out,"text","commande","250px",""+commande.list.get(0));
            
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","<br><br>Entreprise : ");
            SelectDAO.GetSelect(out,"entreprise",""+commande.list.get(1),entreprise.GetnomEntreprise(""+commande.list.get(1)),entreprise.list_CodeEntreprise,entreprise.list_Entreprise,1);
            
            
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
            InputDAO.AddInput(out,"text","EtablissementDocumentPrix","100px",""+commande.list.get(2),"onkeyup=javascript:CalculePrixEtablissement()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","EtablissementDocumentPourcentage","100px",""+commande.list.get(3),"onkeyup=javascript:CalculePrixEtablissement()");
            out.println("</td>");
            out.println("<td>");
            
            prix = Float.parseFloat(""+commande.list.get(2));
            pourcent = Float.parseFloat(""+commande.list.get(3));
            total = prix + pourcent*prix/100;
            
            InputDAO.AddInput(out,"text","EtablissementDocumentPrixTotale","100px",""+total,true);
            out.println("</td>");
            
            out.println("</tr>");
            out.println("<tr>");
            
            out.println("<td>");
            out.println("Modification de document ( le pli )");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","ModificationDocumentPrix","100px",""+commande.list.get(4),"onkeyup=javascript:CalculePrixModification()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","ModificationDocumentPourcentage","100px",""+commande.list.get(5),"onkeyup=javascript:CalculePrixModification()");
            out.println("</td>");
            out.println("<td>");
            
            prix = Float.parseFloat(""+commande.list.get(3));
            pourcent = Float.parseFloat(""+commande.list.get(4));
            total = prix + pourcent*prix/100;
            
            InputDAO.AddInput(out,"text","ModificationDocumentPrixTotale","100px",""+total,true);
            out.println("</td>");
            
            out.println("</tr>");
            out.println("<tr>");
            
            out.println("<td>");
            out.println("Traitement RC ( le folio )");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","TraitementPrix","100px",""+commande.list.get(6),"onkeyup=javascript:CalculePrixTraitement()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","TraitementPourcentage","100px",""+commande.list.get(7),"onkeyup=javascript:CalculePrixTraitement()");
            out.println("</td>");
            out.println("<td>");
            
            prix = Float.parseFloat(""+commande.list.get(5));
            pourcent = Float.parseFloat(""+commande.list.get(6));
            total = prix + pourcent*prix/100;
            
            InputDAO.AddInput(out,"text","TraitementPrixTotale","100px",""+total,true);
            out.println("</td>");
            
            out.println("</tr>");
            out.println("<tr>");
            
            out.println("<td>");
            out.println("Teintage de plan ( le Kg )");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","TeintagePrix","100px",""+commande.list.get(8),"onkeyup=javascript:CalculePrixTeintage()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","TeintagePourcentage","100px",""+commande.list.get(9),"onkeyup=javascript:CalculePrixTeintage()");
            out.println("</td>");
            out.println("<td>");
            
            prix = Float.parseFloat(""+commande.list.get(7));
            pourcent = Float.parseFloat(""+commande.list.get(8));
            total = prix + pourcent*prix/100;
            
            InputDAO.AddInput(out,"text","TeintagePrixTotale","100px",""+total,true);
            out.println("</td>");
            
            out.println("</tr>");
            out.println("<tr>");
            
            out.println("<td>");
            out.println("Bordage de plan ( le ml )");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","BordagePrix","100px",""+commande.list.get(10),"onkeyup=javascript:CalculePrixBordage()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","BordagePourcentage","100px",""+commande.list.get(11),"onkeyup=javascript:CalculePrixBordage()");
            out.println("</td>");
            out.println("<td>");
            
            prix = Float.parseFloat(""+commande.list.get(9));
            pourcent = Float.parseFloat(""+commande.list.get(10));
            total = prix + pourcent*prix/100;
            
            InputDAO.AddInput(out,"text","BordagePrixTotale","100px",""+total,true);
            out.println("</td>");
            
            out.println("</tr>");
            
            out.println("</table>");
            
            //REPROGRAPHIE
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation1","<br><br>REPROGRAPHIE ( Série de prix reprographie 02/1991 )<br><br>");
            
            TOOLS_HTML.OpenTable(out,"60%");
            out.println("<th></th>");
            out.println("<th>Majoration en €</th>");
            out.println("<th>Majoration en %</th>");
            out.println("<th>Prix Total</th>");
            
            out.println("<tr>");
            
            out.println("<td>");
            out.println("Tirage Helio ( le kg )");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","HelioPrix","100px",""+commande.list.get(12),"onkeyup=javascript:CalculePrixHelio()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","HelioPourcentage","100px",""+commande.list.get(13),"onkeyup=javascript:CalculePrixHelio()");
            out.println("</td>");
            out.println("<td>");
            
            prix = Float.parseFloat(""+commande.list.get(11));
            pourcent = Float.parseFloat(""+commande.list.get(12));
            total = prix + pourcent*prix/100;
            
            InputDAO.AddInput(out,"text","HelioPrixTotale","100px",""+total,true);
            out.println("</td>");
            
            out.println("</tr>");
            out.println("<tr>");
            
            out.println("<td>");
            out.println("Photocopie Calque ( le kg )");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","PhotocopiePrix","100px",""+commande.list.get(14),"onkeyup=javascript:CalculePrixPhotocopie()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","PhotocopiePourcentage","100px",""+commande.list.get(15),"onkeyup=javascript:CalculePrixPhotocopie()");
            out.println("</td>");
            out.println("<td>");
            
            prix = Float.parseFloat(""+commande.list.get(13));
            pourcent = Float.parseFloat(""+commande.list.get(14));
            total = prix + pourcent*prix/100;
            
            InputDAO.AddInput(out,"text","PhotocopiePrixTotale","100px",""+total,true);
            out.println("</td>");
            
            out.println("</tr>");
            out.println("<tr>");
            
            out.println("<td>");
            out.println("CC 1 Pli A4");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","CC1Prix","100px",""+commande.list.get(16),"onkeyup=javascript:CalculePrixCC1()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","CC1Pourcentage","100px",""+commande.list.get(17),"onkeyup=javascript:CalculePrixCC1()");
            out.println("</td>");
            out.println("<td>");
            
            prix = Float.parseFloat(""+commande.list.get(15));
            pourcent = Float.parseFloat(""+commande.list.get(16));
            total = prix + pourcent*prix/100;
            
            InputDAO.AddInput(out,"text","CC1PrixTotale","100px",""+total,true);
            out.println("</td>");
            
            out.println("</tr>");
            out.println("<tr>");
            
            out.println("<td>");
            out.println("CC 2 Pli A4");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","CC2Prix","100px",""+commande.list.get(18),"onkeyup=javascript:CalculePrixCC2()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","CC2Pourcentage","100px",""+commande.list.get(19),"onkeyup=javascript:CalculePrixCC2()");
            out.println("</td>");
            out.println("<td>");
            
            prix = Float.parseFloat(""+commande.list.get(17));
            pourcent = Float.parseFloat(""+commande.list.get(18));
            total = prix + pourcent*prix/100;
            
            InputDAO.AddInput(out,"text","CC2PrixTotale","100px",""+total,true);
            out.println("</td>");
            
            out.println("</tr>");
            out.println("<tr>");
            
            out.println("<td>");
            out.println("CC 3 Pli A4");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","CC3Prix","100px",""+commande.list.get(20),"onkeyup=javascript:CalculePrixCC3()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","CC3Pourcentage","100px",""+commande.list.get(21),"onkeyup=javascript:CalculePrixCC3()");
            out.println("</td>");
            out.println("<td>");
            
            prix = Float.parseFloat(""+commande.list.get(19));
            pourcent = Float.parseFloat(""+commande.list.get(20));
            total = prix + pourcent*prix/100;
            
            InputDAO.AddInput(out,"text","CC3PrixTotale","100px",""+total,true);
            out.println("</td>");
            
            out.println("</tr>");
            out.println("<tr>");
            
            out.println("<td>");
            out.println("CC >3 Pli A4");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","CCSupPrix","100px",""+commande.list.get(22),"onkeyup=javascript:CalculePrixCCSup()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","CCSupPourcentage","100px",""+commande.list.get(23),"onkeyup=javascript:CalculePrixCCSup()");
            out.println("</td>");
            out.println("<td>");
            
            prix = Float.parseFloat(""+commande.list.get(21));
            pourcent = Float.parseFloat(""+commande.list.get(22));
            total = prix + pourcent*prix/100;
            
            InputDAO.AddInput(out,"text","CCSupPrixTotale","100px",""+total,true);
            out.println("</td>");
            
            out.println("</tr>");
            
            out.println("</table>");
            
            //DAO
            GetMessageInformationDAO.getMessageInformation(out,"TexteInformation1","<br><br>DAO ( Série de prix DAO du 05 / 2004 )<br><br>");
            
            TOOLS_HTML.OpenTable(out,"60%");
            out.println("<th></th>");
            out.println("<th>Majoration en €</th>");
            out.println("<th>Majoration en %</th>");
            out.println("<th>Prix Total</th>");
            
            out.println("<tr>");
            
            out.println("<td>");
            out.println("Tracé DAO 1 pli A4");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","DAO1A4Prix","100px",""+commande.list.get(24),"onkeyup=javascript:CalculePrixDAO1A4()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","DAO1A4Pourcentage","100px",""+commande.list.get(25),"onkeyup=javascript:CalculePrixDAO1A4()");
            out.println("</td>");
            out.println("<td>");
            
            prix = Float.parseFloat(""+commande.list.get(23));
            pourcent = Float.parseFloat(""+commande.list.get(24));
            total = prix + pourcent*prix/100;
            
            InputDAO.AddInput(out,"text","DAO1A4PrixTotale","100px",""+total,true);
            out.println("</td>");
            
            out.println("</tr>");
            out.println("<tr>");
            
            out.println("<td>");
            out.println("Tracé DAO 2 pli A4");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","DAO2A4Prix","100px",""+commande.list.get(26),"onkeyup=javascript:CalculePrixDAO2A4()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","DAO2A4Pourcentage","100px",""+commande.list.get(27),"onkeyup=javascript:CalculePrixDAO2A4()");
            out.println("</td>");
            out.println("<td>");
            
            prix = Float.parseFloat(""+commande.list.get(25));
            pourcent = Float.parseFloat(""+commande.list.get(26));
            total = prix + pourcent*prix/100;
            
            InputDAO.AddInput(out,"text","DAO2A4PrixTotale","100px",""+total,true);
            out.println("</td>");
            
            out.println("</tr>");
            out.println("<tr>");
            
            out.println("<td>");
            out.println("Tracé DAO 3 pli A4");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","DAO3A4Prix","100px",""+commande.list.get(28),"onkeyup=javascript:CalculePrixDAO3A4()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","DAO3A4Pourcentage","100px",""+commande.list.get(29),"onkeyup=javascript:CalculePrixDAO3A4()");
            out.println("</td>");
            out.println("<td>");
            
            prix = Float.parseFloat(""+commande.list.get(27));
            pourcent = Float.parseFloat(""+commande.list.get(28));
            total = prix + pourcent*prix/100;
            
            InputDAO.AddInput(out,"text","DAO3A4PrixTotale","100px",""+total,true);
            out.println("</td>");
            
            out.println("</tr>");
            
            out.println("<tr>");
            
            out.println("<td>");
            out.println("Supplement");
            out.println("</td>");
            out.println("<td>");
            
            InputDAO.AddInput(out,"text","SupplementPrix","100px",""+commande.list.get(30),"onkeyup=javascript:CalculePrixSupplement()");
            out.println("</td>");
            out.println("<td>");
            InputDAO.AddInput(out,"text","SupplementPourcentage","100px",""+commande.list.get(31),"onkeyup=javascript:CalculePrixSupplement()");
            out.println("</td>");
            out.println("<td>");
            
            prix = Float.parseFloat(""+commande.list.get(29));
            pourcent = Float.parseFloat(""+commande.list.get(30));
            total = prix + pourcent*prix/100;
            
            InputDAO.AddInput(out,"text","SupplementPrixTotale","100px",""+total,true);
            out.println("</td>");
            
            out.println("</tr>");
            
            out.println("</table>");
            
            
            //DIV DE MISE EN FORME ENREGISTREMENT
            out.println("<div id=Enregistrement>");
            out.println("</div>");  //FIN Enregistrement
            
            ButtonDAO.AddButtonStyle(out,1,"javascript:getHTMLCodeRequestTravaux('Enregistrement','EnregistrerModificationCommandes','Champ')","Enregistrer");
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
