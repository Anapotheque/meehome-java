/*
 * UploadPDF.java
 *
 * Created on 15 mars 2007, 10:10
 */

package fr.alliance.docalliance.ajax.mail.tools;

import fr.alliance.docalliance.tools.system.DataDate;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import fr.alliance.docalliance.controleur.MetaControleur;
import fr.alliance.docalliance.dao.DaoCreateException;
import fr.alliance.docalliance.model.Pdf;
import fr.alliance.docalliance.service.IGlobalService;
import fr.alliance.docalliance.service.IPdfService;
import fr.alliance.docalliance.tools.html.message.MessageAlert;

/**
 * Servlet appelé par l'applet lors de la selection de fichier et permettant d'uploader des fichiers pdf et de les sauvegarder en base
 * @author Yves Le Rumeur, Romain Raballand
 * @version
 */
public class UploadPDF extends MetaControleur {
    
    //Chemin d'enregistrement
    private static String PATH;
    private String typeFile = null;
    private String fileName = null;
    
    private static final int PAYE = 0;
    private static final int NEWSLETTER = 1;
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        XmlBeanFactory bf = new XmlBeanFactory(new ClassPathResource("spring-config.xml"));
        IGlobalService service = (IGlobalService) bf.getBean("service");
        
        try {
            
            PATH = new String();
            
            //Recuperation du type de fichier
            this.typeFile = request.getParameter("typeFile");
            //Recuperation du nom de fichier
            this.fileName = request.getParameter("fileName");
            
            switch(Pdf.InterpreteTypeFile(this.typeFile)){
                case PAYE :
                    PATH += "C:\\PDF\\PAYE\\";
                    break;
                case NEWSLETTER :
                    PATH += "C:\\PDF\\NEWSLETTER\\";
                    break;
            }
            
            //Creation du repertoire d'accueil des données
            PATH += DataDate.getActuallyDate()+"\\";
            File rep = new File(PATH);
            rep.mkdir();
            
            //Initialisation des flux
            InputStream in = request.getInputStream();
            DataInputStream dis = new DataInputStream(in);
            File serverFile = new File(PATH+"/"+this.fileName);
            FileOutputStream fos = new FileOutputStream(serverFile);
            
            byte[] tab = new byte[1024];
            
            int lu = dis.read(tab);
            
            while(lu>=0) {
                fos.write(tab, 0, lu);
                lu = dis.read(tab);
            }
            
            //Fermeture du flux
            fos.flush();
            fos.close();
            dis.close();
            
            //creation d'un objet PDF
            Pdf pdf = new Pdf();
            //Attribution d'un chemin d'acces
            pdf.setAbsolutPath(serverFile.getAbsolutePath());
            //Attribution du nom
            pdf.setNom(this.fileName);
            //Attribution de la section du pdf
            pdf.setSection(Pdf.InterpreteTypeFile(this.typeFile));
            //Attribution du serveur ( Non defini )
            pdf.setServeur("");
            //Attribution envoi ou pas
            pdf.setSend(false);
            
            //Sauvegarde d'un PDF
            service.saveOnePdf(pdf);
            
            //Traitement des erreurs liées au modele de données ( Hibernate )
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DaoCreateException e) {
            e.printStackTrace();
        }
    }
    
}
