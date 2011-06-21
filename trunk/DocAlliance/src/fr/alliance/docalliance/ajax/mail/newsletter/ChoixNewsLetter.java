package fr.alliance.docalliance.ajax.mail.newsletter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import fr.alliance.docalliance.ajax.mail.tools.List_Contact;
import fr.alliance.docalliance.controleur.MetaControleur;
import fr.alliance.docalliance.dao.DaoFindException;
import fr.alliance.docalliance.model.Pdf;
import fr.alliance.docalliance.service.IGlobalService;
import fr.alliance.docalliance.service.IPdfService;
import fr.alliance.docalliance.tools.html.message.MessageAlert;

/**
 * Servlet pour le choix des contacts à qui envoyer la newsLetter
 * @author Yves Le Rumeur, Romain Raballand
 * @version 1.0
 */

public class ChoixNewsLetter extends MetaControleur {
    
    private static final long serialVersionUID = 1L;
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        IGlobalService service = getService(request);
        
        // Recupere la session
        HttpSession session = request.getSession(true);
        
        //Initialisation du flux de sortie
        PrintWriter out = response.getWriter();
        
        try {
            
            //Recuperation de l'objet PDF
            Pdf pdf = service.getPdfById(Integer.parseInt(request.getParameter("idPDF1")));
            
            //Mise en session de l'objet Pdf
            session.setAttribute("Pdf",pdf);
            
            //Affichage de la liste des contacts
            List_Contact.GetList_Contact(request, response);
            
            //Traitement des erreurs liées au modele de données ( Hibernate )
        } catch (DaoFindException e) {
            MessageAlert message = new MessageAlert("Probleme : <br><br>"+e.getMessage(),MessageAlert.ERREUR);
            out.print(message.getFLUX());
            e.printStackTrace();
        }
        
        //Fermeture du flux de sortie
        out.flush();
        out.close();
    }
    
}
