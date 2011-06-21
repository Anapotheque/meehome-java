package fr.alliance.docalliance.controleur;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import fr.alliance.docalliance.service.IGlobalService;

public class MetaControleur extends HttpServlet {
    
    protected static XmlBeanFactory bf;
    
    public void init() throws ServletException {
        bf=new XmlBeanFactory(new ClassPathResource("spring-config.xml"));
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
    
    protected static IGlobalService getService(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        IGlobalService service = null;
        service = (IGlobalService) session.getAttribute("service");
        if (service==null){
            service = (IGlobalService) bf.getBean("service");
            session.setAttribute("service", service);
        }
        return service;
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
