package Ajax.ETUDES.Rch;

import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Controleur.MegaControleur;
import Models.ETUDES.Rch.RchDAO;
import Models.TOOLS.iText.DVSG.Impression;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

//@author RABALLAND Romain ------------------------------------------------------------------------------
//@version v3.0 ------------------------------------------------------------------------------------------------------------

public class SearchRch extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //INITIALISATION DU FLUX DE COMMUNICATION AVEC LA PAGE JSP
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        //SET DATA
        ArrayList listParam;
        listParam = new ArrayList();
        listParam.add("codeRch");
        listParam.add("codeAffaire");
        
        ArrayList listAff;
        listAff = new ArrayList();
        listAff.add("Numero retour chantier");
        listAff.add("Numero Affaire");
        
        //GET DATA
        String mot_clef = null;
        String filtre = null;
        String choix = TransformString(request.getParameter("choix_menu"));
        if(request.getParameter("mot_clef")!=null){
            mot_clef = TransformString(request.getParameter("mot_clef"));
            filtre = TransformString(request.getParameter("filtre"));
        }
        
        //RECUPERATION DES OBJETS
        RchDAO rch = null;
        rch = creerObjet(request, rch);
        
        if(choix.equals("null")){
            
            //AFFICHAGE
            TOOLS_HTML.Search(request,response,out,"Rch",listParam,listAff);
            out.close();
            
        }
        
        else if(choix.equals("Search")){
            
            boolean result = false;
            try {
                result = rch.Search(request, mot_clef, filtre);
            }        catch(SQLException ex) {
                retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState());
                System.out.println("\n\nDEBUT ERREUR<==========================================\n");
                ex.printStackTrace();
                System.out.println("\nFIN ERREUR<==============================================\n\n");
            }
            
            //AFFICHAGE
            if(result){
                //GENERATION DU PDF LIE
                Impression.genererPDF(response, request,rch.widthsShow,rch.list_Titre,rch.list_show,"RCH FILTRES");
                
                TOOLS_HTML.Show(request,out,"Rch",rch.list_Titre,rch.list_show,rch.list_CodeRch,rch.list_SizeTitre,rch.list_Titre.size(),true);
            }else
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
