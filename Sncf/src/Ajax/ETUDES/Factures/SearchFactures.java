package Ajax.ETUDES.Factures;

import Models.TOOLS.Ajaxmodels.TOOLS_HTML;
import Models.TOOLS.Controleur.MegaControleur;
import Models.ETUDES.Factures.FacturesDAO;
import Models.TOOLS.iText.DVSG.Impression;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

//@author RABALLAND Romain ------------------------------------------------------------------------------
//@version v3.0 ------------------------------------------------------------------------------------------------------------

public class SearchFactures extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //INITIALISATION DU FLUX DE COMMUNICATION AVEC LA PAGE JSP
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        //SET DATA
        ArrayList listParam;
        listParam = new ArrayList();
        listParam.add("entreprise");
        
        ArrayList listAff;
        listAff = new ArrayList();
        listAff.add("Nom Entreprise");
        
        //GET DATA
        String mot_clef = null;
        String filtre = null;
        String choix = TransformString(request.getParameter("choix_menu"));
        if(request.getParameter("mot_clef")!=null){
            mot_clef = TransformString(request.getParameter("mot_clef"));
            filtre = TransformString(request.getParameter("filtre"));
        }
        
        //RECUPERATION DES OBJETS
        FacturesDAO bfc = null;
        bfc = creerObjet(request, bfc);
        
        if(choix.equals("null")){
            
            //AFFICHAGE
            TOOLS_HTML.Search(request,response,out,"Factures",listParam,listAff);
            out.close();
            
        }
        
        else if(choix.equals("Search")){
            
            boolean result = false;
            try {
                result = bfc.Search(request, mot_clef, filtre);
            }        catch(SQLException ex) {
                retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState());
                System.out.println("\n\nDEBUT ERREUR<==========================================\n");
                ex.printStackTrace();
                System.out.println("\nFIN ERREUR<==============================================\n\n");
            }
            
            
            
            //AFFICHAGE
            if(result){
                //GENERATION DU PDF LIE
                Impression.genererPDF(response, request,bfc.widthsShow,bfc.list_TitreShow,bfc.list_show,"FACTURES FILTREES");
                
                TOOLS_HTML.Show(request,out,"Factures",bfc.list_TitreShow,bfc.list_show,bfc.list_CodeFacture,bfc.list_SizeTitre,bfc.list_TitreShow.size(),true);
            }else
                TOOLS_HTML.getMessage(out,"h3","AUCUN RESULTAT N'A ETE OBTENU VEUILLEZ RECOMMENCER");
            
            TOOLS_HTML.CloseDiv(out);
            
            out.println("</div>");
            
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
