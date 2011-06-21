package fr.home.my.controleur;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import fr.home.my.services.IRoleService;
import fr.home.my.services.IUtilisateurService;

public class MetaControleur extends HttpServlet {
    
    protected static XmlBeanFactory bf;
    
    public void init() throws ServletException {
        bf=new XmlBeanFactory(new ClassPathResource("classpath:/fr/home/my/services/ApplicationContext.xml"));
    }
    
    /**
     * Redirection du flux vers une autre servlets
     *
     * @param request
     * @param response
     * @param chemin
     * @throws ServletException
     * @throws IOException
     */
    protected void retourVue(HttpServletRequest request, HttpServletResponse response, String chemin) throws ServletException, IOException {
        try {
            getServletContext().getRequestDispatcher(chemin).forward(request,response);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    protected static IUtilisateurService getUtilisateurService(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        IUtilisateurService utilisateurService = null;
        utilisateurService = (IUtilisateurService) session.getAttribute("utilisateurService");
        if (utilisateurService == null){
        	utilisateurService = (IUtilisateurService) bf.getBean("utilisateurService");
            session.setAttribute("utilisateurService", utilisateurService);
        }
        return utilisateurService;
    }
    protected static IRoleService getRoleService(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        IRoleService roleService = null;
        roleService = (IRoleService) session.getAttribute("roleService");
        if (roleService == null){
        	roleService = (IRoleService) bf.getBean("roleService");
            session.setAttribute("utilisateurService", roleService);
        }
        return roleService;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response
            ) throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");
        processRequest(request, response);
    }
    
}
