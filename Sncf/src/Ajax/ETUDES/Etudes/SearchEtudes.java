package Ajax.ETUDES.Etudes;

import Models.ETUDES.Affaires.AffairesDAO;
import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Controleur.MegaControleur;
import Models.ETUDES.Etudes.EtudesDAO;
import Models.TOOLS.Ajaxmodels.GetMessageInformationDAO;
import Models.TOOLS.iText.DVSG.Impression;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

//@author RABALLAND Romain
//@version v3.0

public class SearchEtudes extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //INITIALISATION DU FLUX DE COMMUNICATION AVEC LA PAGE JSP
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        //SET DATA
        ArrayList listParam;
        listParam = new ArrayList();
        listParam.add("gare");
        listParam.add("codeAffaire");
        listParam.add("nom");
        
        ArrayList listAff;
        listAff = new ArrayList();
        listAff.add("Nom Gare");
        listAff.add("Code Affaire");
        listAff.add("Nom Agent");
        
        //GET DATA
        String mot_clef = null;
        String filtre = null;
        String choix = TransformString(request.getParameter("choix_menu"));
        if(request.getParameter("mot_clef")!=null){
            mot_clef = TransformString(request.getParameter("mot_clef"));
            filtre = TransformString(request.getParameter("filtre"));
        }
        
        //RECUPERATION DES OBJETS
        EtudesDAO etude = null;
        etude = creerObjet(request, etude);
        
        AffairesDAO affaire = null;
        affaire= creerObjet(request, affaire);
        
        if(choix.equals("null")){
            
            //AFFICHAGE
            TOOLS_HTML.Search(request,response,out,"Etudes",listParam,listAff);
            out.close();
            
        }
        
        else if(choix.equals("Search")){
            
            boolean result = false;
            try {
                result = etude.Search(request, mot_clef, filtre);
                
                
                //AFFICHAGE
                if(result){
                    //GENERATION DU PDF LIE
                    Impression.genererPDF(response, request,etude.widthsShow,etude.list_TitreShow,etude.list_Search,"ETUDES FILTREES");
                    if(filtre.equals("codeAffaire"))
                    GetMessageInformationDAO.getMessageInformation(out,"TexteInformation4","Nom de l'affaire "+mot_clef+" : "+affaire.GetnomAffaire(mot_clef));
                    TOOLS_HTML.Show(request,out,"Etudes",etude.list_TitreShow,etude.list_Search,etude.list_codeEtude,etude.list_SizeTitre_Show,etude.list_TitreShow.size(),true);
                }else
                    TOOLS_HTML.getMessage(out,"h3","AUCUN RESULTAT N'A ETE OBTENU VEUILLEZ RECOMMENCER");
                
                TOOLS_HTML.CloseDiv(out);
                
                out.println("</div>");
                
                out.close();
            } catch(SQLException ex) {
                retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState());
                
                System.out.println("\n\nDEBUT ERREUR<==========================================\n");
                ex.printStackTrace();
                System.out.println("\nFIN ERREUR<==============================================\n\n");
            } // Charge toutes les informations
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
