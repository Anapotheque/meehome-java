package Ajax.ETUDES.Affaires;

import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Controleur.MegaControleur;
import Models.ETUDES.Affaires.AffairesDAO;
import Models.TOOLS.iText.DVSG.Impression;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

//@author RABALLAND Romain ------------------------------------------------------------------------------
//@version v3.0 ------------------------------------------------------------------------------------------------------------

public class SearchAffaires extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //INITIALISATION DU FLUX DE COMMUNICATION AVEC LA PAGE JSP
        response.setContentType("text/html;charset=ISO-8859-1");
        PrintWriter out = response.getWriter();
        
        //SET DATA
        ArrayList listParam;
        listParam = new ArrayList();
        listParam.add("codeAffaire");
        listParam.add("sycomore");
        listParam.add("gare");
        
        ArrayList listAff;
        listAff = new ArrayList();
        listAff.add("Code Affaire");
        listAff.add("Sycomore");
        listAff.add("Nom Gare");
        
        //GET DATA
        String mot_clef = null;
        String filtre = null;
        String choix = TransformString(request.getParameter("choix_menu"));
        if(request.getParameter("mot_clef")!=null){
            mot_clef = TransformString(request.getParameter("mot_clef"));
            filtre = TransformString(request.getParameter("filtre"));
        }
        
        //RECUPERATION DES OBJETS
        AffairesDAO affaire = null;
        affaire = creerObjet(request, affaire);
        
        if(choix.equals("null")){
            
            //AFFICHAGE
            TOOLS_HTML.Search(request,response,out,"Affaires",listParam,listAff);
            out.close();
            
        }
        
        else if(choix.equals("Search")){
            
            boolean result = false;
            try {
                result = affaire.Search(request, mot_clef, filtre);
            } catch (SQLException ex) {
                retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState());
                System.out.println("\n\nDEBUT ERREUR<==========================================\n");
                ex.printStackTrace();
                System.out.println("\nFIN ERREUR<==============================================\n\n");
            }
            
            //AFFICHAGE
            if(result)
                if(filtre.equals("gare")){
                //GENERATION DU PDF LIE
                Impression.genererPDF(response, request,affaire.widthsSearch,affaire.list_TitreSearch,affaire.list_Search,"AFFAIRES FILTREES");
                
                TOOLS_HTML.Show(request,out,"Affaires",affaire.list_TitreSearch,affaire.list_Search,affaire.list_CodeAffaire,affaire.list_SizeTitre_Search,affaire.list_TitreSearch.size(),true);
                
                }else{
                //GENERATION DU PDF LIE
                Impression.genererPDF(response, request,affaire.widthsShow,affaire.list_TitreShow,affaire.list_Search,"AFFAIRES ACTIVES");
                
                TOOLS_HTML.Show(request,out,"Affaires",affaire.list_TitreShow,affaire.list_Search,affaire.list_CodeAffaire,affaire.list_SizeTitre_Show,affaire.list_SizeTitre_Show.size(),true);
                } else
                    TOOLS_HTML.getMessage(out,"h3","AUCUN RESULTAT N'A ETE OBTENU VEUILLEZ RECOMMENCER");
            
            TOOLS_HTML.CloseDiv(out);
            out.close();
            
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
