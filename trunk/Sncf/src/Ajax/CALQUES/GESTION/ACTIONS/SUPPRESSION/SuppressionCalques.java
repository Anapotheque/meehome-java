package Ajax.CALQUES.GESTION.ACTIONS.SUPPRESSION;

import Models.CALQUES.CalquesDAO;
import Models.TOOLS.Controleur.MegaControleur;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

//RABALLAND Romain v3.0

public class SuppressionCalques extends MegaControleur {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        ArrayList list_Code;
        
        try {
            
            CalquesDAO calques = null;
            calques = creerObjet(request, calques);
            
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            String codeCalques = request.getParameter("codeCalques");
            
            list_Code = new ArrayList();
            
            //FONCTION PERMETTANT DE DECHIFFRER LA LIGNE DE COMMANDE VENANT DU JS
            for(int i = 1 ; i < codeCalques.length(); i++){
                String detect = "";
                String code = "";
                while(!detect.equals("@")){
                    detect = ""+codeCalques.charAt(i+1);
                    code += codeCalques.charAt(i);
                    i++;
                }
                list_Code.add(code);
            }
            
            for(int i = 0; i < list_Code.size(); i ++)
                calques.Delete(""+list_Code.get(i),"codeCalques","calques");            
            
            retourVue(request,response,"/GetCalquesGestions?&SauvegardeCodeCalque_OR_Case=YES");
            out.close();
            
        }  catch (SQLException ex) {
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
