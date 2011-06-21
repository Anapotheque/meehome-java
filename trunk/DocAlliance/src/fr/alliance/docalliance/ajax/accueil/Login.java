/*
 * Login.java
 *
 * Created on 20 mars 2007, 09:28
 */

package fr.alliance.docalliance.ajax.accueil;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.alliance.docalliance.controleur.MetaControleur;
import fr.alliance.docalliance.dao.DaoFindException;
import fr.alliance.docalliance.model.User;
import fr.alliance.docalliance.service.IUserService;
import fr.alliance.docalliance.tools.html.message.MessageAlert;

/**
 * Servlet de login, permet de s'identifier et de se faire rediriger vers l'accueil
 * @author Yves Le Rumeur, Romain Raballand
 * @version 1.0
 */

public class Login extends MetaControleur {
    
    //Serialisation de la servlet
    private static final long serialVersionUID = 1L;
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        // Recupere la session
        HttpSession session = request.getSession(true);
        
        //initialisation du flux de sortie
        PrintWriter out = response.getWriter();
        
        try {
            
            //Recuperation des données formulaire
            String login = request.getParameter("login");
            System.out.println("\nlogin : "+login+"\n");
            
            String password = request.getParameter("password");
            System.out.println("\npassword : "+password+"\n");
            
            //Recuperation du service pour IUser
            IUserService iuserservice = getService(request);
            
            //Si la personne est autorisée à se connecter
            if(iuserservice.isAuthorized(login,password)){
                
                //Recuperation de l'objet User pour cet identifiant
                User user = iuserservice.getUserByLogin(login);
                
                //Mise en session de l'identifiant
                session.setAttribute("user", user);
                
                //Renvoi vers la page d'accueil pour la navigation
                retourVue(request,response,"/Accueil");
                
                //La personne n'est pas autorisée, on affiche un message d'erreur
            }else{
                
                //Réinitialisation du champ de connection
                out.print("<form id='champ' action=javascript:Ajax('rechargementMenu','Login','champ')>");
                out.print("<input type='text' id='login' name='login' value='"+login+"'><br>");
                out.print("<input type='password' id='login' name='password' value='"+password+"'><br>");
                out.print("<input type='submit' value='Connexion'>");
                out.print("</form>");
                
                //Message d'erreur
                MessageAlert message = new MessageAlert("Vous n'etes pas autorisé à vous connecter",MessageAlert.ADMIN);
                out.print(message.getFLUX());
            }
            
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
