package Ajax.ETUDES.Fmr;

import Models.TOOLS.Ajaxmodels.DVSG.AjaxModels_Onglets;
import Models.TOOLS.Controleur.MegaControleur;
import Models.ETUDES.Fmr.FmrDAO;
import Models.ETUDES.Gares.GaresDAO;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

//@author RABALLAND Romain ------------------------------------------------------------------------------
//@version v3.0 ------------------------------------------------------------------------------------------------------------

public class NewFmr extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //GET DATA
        String choix = new String(request.getParameter("choix_menu"));
        
        if(choix.equals("Null")){
            
            try {
                
                //
                FmrDAO fmr = null;
                fmr = creerObjet(request, fmr);
                
                GaresDAO gare = null;
                gare = creerObjet(request, gare);
                gare.SetGareSE();
                
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                
                AjaxModels_Onglets.getOnglet(request,response,"Creation","Fmr");
                
                out.println("<table width=80%>");
                
                out.println("<th>GARE</th>");
                out.println("<th>NUMERO FMR</th>");
                out.println("<th>ETABLISSEMENT</th>");
                out.println("<th>ENVOI</th>");
                out.println("<th>TRAITEMENT</th>");
                out.println("<th>ACCORD</th>");
                out.println("<th>INCORPORATION</th>");
                out.println("<th>OBSERVATIONS</th>");
                out.println("<th>ACTION</th>");
                
                out.println("<tr>");
                out.println("<td colspan='9'><hr with=100%>");
                out.println("</tr>");
                
                out.println("<tr>");
                
                out.println("<form name='champ'>");
                
                //--------------------------------------------------------------------------------
                
                out.println("<td><select name='Gare'>");
                out.println("<option value='' selected>Aucunes Gares</option>");
                
                for(int i=0; i<gare.list_CodeGare.size(); i++)
                    out.println("<option value='"+gare.list_CodeGare.get(i)+"'>"+gare.list_Gare.get(i)+"</option>");
                
                out.println("</select></td>");
                
                
                //--------------------------------------------------------------------------------
                
                out.println("<td><input type='text' value='"+fmr.GetLastnumeroFMR()+"' name='numeroFmr'></td>");
                
                out.println("<td><input type='text' value='DD/MM/AAAA' name='etablissement'></td>");
                out.println("<td><input type='text' value='DD/MM/AAAA' name='envoi'></td>");
                out.println("<td><input type='text' value='DD/MM/AAAA' name='traitement'></td>");
                
                //--------------------------------------------------------------------------------
                
                out.println("<td><select name='accord'>");
                out.println("<option value='N'>Non</option>");
                out.println("<option value='O'>Oui</option>");
                out.println("</select></td>");
                
                
                //--------------------------------------------------------------------------------
                
                out.println("<td><input type='text' value='' name='incorporation'></td>");
                out.println("<td><input type='text' value='En attente de traitement' name='memo'></td>");
                
                out.println("<td><a href=javascript:getHTMLCodeRequest('informations','NewFmr','choix_menu=New','champ')><img src='images/ajouter.gif' width='48' height='48' alt='Enregistrer' border='0' onMouseOver='change_image(1,ajoutergris)' onMouseOut='change_image(1,ajouter)'/></a></td>");
                out.println("</tr>");
                out.println("</form>");
                
                out.println("</table>");
                
                out.println("</div>");
                out.close();
                
            }
            //Gestion des erreurs -------------------------------------------------------------
            
            catch(SQLException ex) {
                retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState());
                System.out.println("\n\nDEBUT ERREUR<==========================================\n");
                ex.printStackTrace();
                System.out.println("\nFIN ERREUR<==============================================\n\n");
                
            }
        }
        
        // On enregistre le resultat si le choix vaut 'New'--------------------------------------
        
        else if(choix.equals("New")){
            
            String codeGare = new String(request.getParameter("codeGare"));
            String numeroFmr = new String(request.getParameter("numeroFmr"));
            
            
            String etablissement = new String(request.getParameter("etablissement"));
            String envoi = new String(request.getParameter("envoi"));
            String traitement = new String(request.getParameter("traitement"));
            
            String accord = new String(request.getParameter("accord"));
            String incorporation = new String(request.getParameter("incorporation"));
            String memo = new String(request.getParameter("memo"));
            
            ArrayList list = new ArrayList();
            
            list.add(codeGare);
            list.add(numeroFmr);
            
            
            list.add(etablissement);
            list.add(envoi);
            list.add(traitement);
            list.add(accord);
            list.add(incorporation);
            list.add(memo);
            
            list = TransformString(list);
            
            try {
                
                FmrDAO fmr = null;
                fmr = creerObjet(request, fmr);
                
                fmr.NewFmr(list);
                retourVue(request, response,"/ShowFmr?choix_menu=Null");
                
            } catch(SQLException ex) {
                retourVue(request, response,"/Erreur?message="+ex.getMessage()+"&code="+ex.getErrorCode()+"&etat="+ex.getSQLState()+"&retour=NewFmr");
                System.out.println("\n\nDEBUT ERREUR<==========================================\n");
                ex.printStackTrace();
                System.out.println("\nFIN ERREUR<==============================================\n\n");
                
            }
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
