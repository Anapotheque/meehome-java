package Models.TOOLS.Controleur;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import Models.CALQUES.CalquesDAO;
import Models.CALQUES.CasesDAO;
import Models.CALQUES.HistoriqueCalquesDAO;
import Models.COURRIER.COURRIERDEPART.CourrierDepartDAO;
import Models.COURRIER.COURRIER_ES.CourriersDAO;
import Models.ETUDES.Affaires.AffairesDAO;
import Models.ETUDES.Agents.AgentsDAO;
import Models.ETUDES.Entreprises.EntreprisesDAO;
import Models.ETUDES.Etudes.EtudesDAO;
import Models.ETUDES.Etudes.Link.LinkDAOEtudes_Affaires;
import Models.ETUDES.Etudes.Link.LinkDAOEtudes_Agents;
import Models.ETUDES.Even.EvenDAO;
import Models.ETUDES.Factures.FacturesDAO;
import Models.ETUDES.Fmr.FmrDAO;
import Models.ETUDES.Gares.GaresDAO;
import Models.ETUDES.Gares.Link.LinkDAOGare_Even;
import Models.ETUDES.Outils.HistoriqueGaresDAO;
import Models.ETUDES.Outils.QualitesDAO;
import Models.ETUDES.Outils.SuivisDAO;
import Models.ETUDES.Rch.RchDAO;
import Models.TOOLS.Date.DataDate;
import Models.TOOLS.Utilisateurs.UtilisateursDAO;
import Models.TRAVAUX.CommandesDAO;
import Models.TRAVAUX.TravauxDAO;

//RABALLAND Romain v3.0

public class MegaControleur extends HttpServlet {
    
    //RESSOURCE POUR LA BASE DE DONNEE MySQL
    static public DataSource ds;
    
    static public String cheminEnregistrementPDF = "D:\\SERVEUR\\Tomcat 5.5\\webapps\\SNCF\\PDF\\";
    static public String cheminOuverturePDF = "/SNCF/PDF/";
    
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    //                                                                      FONCTION PRINCIPALE DU CONTROLEUR
    
    
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------
    
    //CHARGEMENT A L'APPEL DE LA CLASSE
    public void init() throws ServletException {
        
        try {
            Context initCtx = new InitialContext();
            ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/SNCF");
        } catch (Exception ex) {
            System.out.println("\n\nDEBUT ERREUR<==========================================\n");
            ex.printStackTrace();
            System.out.println("\nFIN ERREUR<==============================================\n\n");
        }
    }
    
    
    //REDIRECTION DES PAGES JSP ET SERVLETS
    protected void retourVue(HttpServletRequest request, HttpServletResponse response, String chemin) throws ServletException, IOException {
        
        try {
            getServletContext().getRequestDispatcher(chemin).forward(request,response);
        } catch(Exception ex) {
            System.out.println("\n\nDEBUT ERREUR<==========================================\n");
            ex.printStackTrace();
            System.out.println("\nFIN ERREUR<==============================================\n\n");
        }
    }
    
    
    
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    //                                                                      CONTROLEUR POUR ETUDE
    
    
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    
    
    static public UtilisateursDAO creerObjet(HttpServletRequest request, UtilisateursDAO model) {
        
        //On recupere la session si elle est deja ouverte sinon on ouvre une nouvelle session-------------------------------------------
        
        HttpSession session = request.getSession(true);
        
        // On verifie si l'objet est deja dans la session------------------------------------------------------------------------
        
        model = (UtilisateursDAO) session.getAttribute("modelUtilisateurs");
        
        // Creation du modele ------------------------------------------------------------------------------------------------------
        
        if (model==null){
            
            //On passe la datasource 'ds' au model-----------------------------------------------------------------------
            
            model = new UtilisateursDAO(ds);
            
            //On attirbu un identifiant 'model' à la session-----------------------------------------------
            
            session.setAttribute("modelUtilisateurs", model);
            
        }
        
        return model;
        
    }
    
    
    
    static public FacturesDAO creerObjet(HttpServletRequest request, FacturesDAO model) {
        
        //On recupere la session si elle est deja ouverte sinon on ouvre une nouvelle session-------------------------------------------
        
        HttpSession session = request.getSession(true);
        
        // On verifie si l'objet est deja dans la session------------------------------------------------------------------------
        
        model = (FacturesDAO) session.getAttribute("modelFacture");
        
        // Creation du modele ------------------------------------------------------------------------------------------------------
        
        if (model==null){
            
            //On passe la datasource 'ds' au model-----------------------------------------------------------------------
            
            model = new FacturesDAO(ds);
            
            //On attirbu un identifiant 'model' à la session-----------------------------------------------
            
            session.setAttribute("modelFacture", model);
            
        }
        
        return model;
        
    }
    
    static public EntreprisesDAO creerObjet(HttpServletRequest request, EntreprisesDAO model) {
        
        //On recupere la session si elle est deja ouverte sinon on ouvre une nouvelle session-------------------------------------------
        
        HttpSession session = request.getSession(true);
        
        // On verifie si l'objet est deja dans la session------------------------------------------------------------------------
        
        model = (EntreprisesDAO) session.getAttribute("modelEntreprise");
        
        // Creation du modele ------------------------------------------------------------------------------------------------------
        
        if (model==null){
            
            //On passe la datasource 'ds' au model-----------------------------------------------------------------------
            
            model = new EntreprisesDAO(ds);
            
            //On attirbu un identifiant 'model' à la session-----------------------------------------------
            
            session.setAttribute("modelEntreprise", model);
            
        }
        
        return model;
        
    }
    
    static public RchDAO creerObjet(HttpServletRequest request, RchDAO model) {
        
        //On recupere la session si elle est deja ouverte sinon on ouvre une nouvelle session-------------------------------------------
        
        HttpSession session = request.getSession(true);
        
        // On verifie si l'objet est deja dans la session------------------------------------------------------------------------
        
        model = (RchDAO) session.getAttribute("modelRch");
        
        // Creation du modele ------------------------------------------------------------------------------------------------------
        
        if (model==null){
            
            //On passe la datasource 'ds' au model-----------------------------------------------------------------------
            
            model = new RchDAO(ds);
            
            //On attirbu un identifiant 'model' à la session-----------------------------------------------
            
            session.setAttribute("modelRch", model);
            
        }
        
        return model;
        
    }
    
    static public SuivisDAO creerObjet(HttpServletRequest request, SuivisDAO model) {
        
        //On recupere la session si elle est deja ouverte sinon on ouvre une nouvelle session-------------------------------------------
        
        HttpSession session = request.getSession(true);
        
        // On verifie si l'objet est deja dans la session------------------------------------------------------------------------
        
        model = (SuivisDAO) session.getAttribute("modelSuivisDAO");
        
        // Creation du modele ------------------------------------------------------------------------------------------------------
        
        if (model==null){
            
            //On passe la datasource 'ds' au model-----------------------------------------------------------------------
            
            model = new SuivisDAO(ds);
            
            //On attirbu un identifiant 'model' à la session-----------------------------------------------
            
            session.setAttribute("modelSuivisDAO", model);
            
        }
        
        return model;
        
    }
    
    static public FmrDAO creerObjet(HttpServletRequest request, FmrDAO model) {
        
        //On recupere la session si elle est deja ouverte sinon on ouvre une nouvelle session-------------------------------------------
        
        HttpSession session = request.getSession(true);
        
        // On verifie si l'objet est deja dans la session------------------------------------------------------------------------
        
        model = (FmrDAO) session.getAttribute("modelFmr");
        
        // Creation du modele ------------------------------------------------------------------------------------------------------
        
        if (model==null){
            
            //On passe la datasource 'ds' au model-----------------------------------------------------------------------
            
            model = new FmrDAO(ds);
            
            //On attirbu un identifiant 'model' à la session-----------------------------------------------
            
            session.setAttribute("modelFmr", model);
            
        }
        
        return model;
        
    }
    
    
    static public EtudesDAO creerObjet(HttpServletRequest request, EtudesDAO model) {
        
        //On recupere la session si elle est deja ouverte sinon on ouvre une nouvelle session-------------------------------------------
        
        HttpSession session = request.getSession(true);
        
        // On verifie si l'objet est deja dans la session------------------------------------------------------------------------
        
        model = (EtudesDAO) session.getAttribute("modelEtude");
        
        // Creation du modele ------------------------------------------------------------------------------------------------------
        
        if (model==null){
            
            //On passe la datasource 'ds' au model-----------------------------------------------------------------------
            
            model = new EtudesDAO(ds);
            
            //On attirbu un identifiant 'model' à la session-----------------------------------------------
            
            session.setAttribute("modelEtude", model);
            
        }
        
        return model;
        
    }
    
    
    static public AffairesDAO creerObjet(HttpServletRequest request, AffairesDAO model) {
        
        //On recupere la session si elle est deja ouverte sinon on ouvre une nouvelle session-------------------------------------------
        
        HttpSession session = request.getSession(true);
        
        // On verifie si l'objet est deja dans la session------------------------------------------------------------------------
        
        model = (AffairesDAO) session.getAttribute("modelAffaire");
        
        // Creation du modele ------------------------------------------------------------------------------------------------------
        
        if (model==null){
            
            //On passe la datasource 'ds' au model-----------------------------------------------------------------------
            
            model = new AffairesDAO(ds);
            
            //On attirbu un identifiant 'model' à la session-----------------------------------------------
            
            session.setAttribute("modelAffaire", model);
            
        }
        
        return model;
        
    }
    
    static public AgentsDAO creerObjet(HttpServletRequest request, AgentsDAO model) {
        
        //On recupere la session si elle est deja ouverte sinon on ouvre une nouvelle session-------------------------------------------
        
        HttpSession session = request.getSession(true);
        
        // On verifie si l'objet est deja dans la session------------------------------------------------------------------------
        
        model = (AgentsDAO) session.getAttribute("modelAgent");
        
        // Creation du modele ------------------------------------------------------------------------------------------------------
        
        if (model==null){
            
            //On passe la datasource 'ds' au model-----------------------------------------------------------------------
            
            model = new AgentsDAO(ds);
            
            //On attirbu un identifiant 'model' à la session-----------------------------------------------
            
            session.setAttribute("modelAgent", model);
            
        }
        
        return model;
        
    }
    
    static public GaresDAO creerObjet(HttpServletRequest request, GaresDAO model) {
        
        //On recupere la session si elle est deja ouverte sinon on ouvre une nouvelle session-------------------------------------------
        
        HttpSession session = request.getSession(true);
        
        // On verifie si l'objet est deja dans la session------------------------------------------------------------------------
        
        model = (GaresDAO) session.getAttribute("modelGare");
        
        // Creation du modele ------------------------------------------------------------------------------------------------------
        
        if (model==null){
            
            //On passe la datasource 'ds' au model-----------------------------------------------------------------------
            
            model = new GaresDAO(ds);
            
            //On attirbu un identifiant 'model' à la session-----------------------------------------------
            
            session.setAttribute("modelGare", model);
            
        }
        
        return model;
        
    }
    
    
    static public EvenDAO creerObjet(HttpServletRequest request, EvenDAO model) {
        
        //On recupere la session si elle est deja ouverte sinon on ouvre une nouvelle session-------------------------------------------
        
        HttpSession session = request.getSession(true);
        
        // On verifie si l'objet est deja dans la session------------------------------------------------------------------------
        
        model = (EvenDAO) session.getAttribute("modelEven");
        
        // Creation du modele ------------------------------------------------------------------------------------------------------
        
        if (model==null){
            
            //On passe la datasource 'ds' au model-----------------------------------------------------------------------
            
            model = new EvenDAO(ds);
            
            //On attirbu un identifiant 'model' à la session-----------------------------------------------
            
            session.setAttribute("modelEven", model);
            
        }
        
        return model;
        
    }
    
    static public LinkDAOGare_Even creerObjet(HttpServletRequest request, LinkDAOGare_Even model) {
        
        //On recupere la session si elle est deja ouverte sinon on ouvre une nouvelle session-------------------------------------------
        
        HttpSession session = request.getSession(true);
        
        // On verifie si l'objet est deja dans la session------------------------------------------------------------------------
        
        model = (LinkDAOGare_Even) session.getAttribute("modelLinkG_E");
        
        // Creation du modele ------------------------------------------------------------------------------------------------------
        
        if (model==null){
            
            //On passe la datasource 'ds' au model-----------------------------------------------------------------------
            
            model = new LinkDAOGare_Even(ds);
            
            //On attirbu un identifiant 'model' à la session-----------------------------------------------
            
            session.setAttribute("modelLinkG_E", model);
            
        }
        
        return model;
        
    }
    
    static public LinkDAOEtudes_Affaires creerObjet(HttpServletRequest request, LinkDAOEtudes_Affaires model) {
        
        //On recupere la session si elle est deja ouverte sinon on ouvre une nouvelle session-------------------------------------------
        
        HttpSession session = request.getSession(true);
        
        // On verifie si l'objet est deja dans la session------------------------------------------------------------------------
        
        model = (LinkDAOEtudes_Affaires) session.getAttribute("modelLinkE_A");
        
        // Creation du modele ------------------------------------------------------------------------------------------------------
        
        if (model==null){
            
            //On passe la datasource 'ds' au model-----------------------------------------------------------------------
            
            model = new LinkDAOEtudes_Affaires(ds);
            
            //On attirbu un identifiant 'model' à la session-----------------------------------------------
            
            session.setAttribute("modelLinkE_A", model);
            
        }
        
        return model;
        
    }
    
    static public LinkDAOEtudes_Agents creerObjet(HttpServletRequest request, LinkDAOEtudes_Agents model) {
        
        //On recupere la session si elle est deja ouverte sinon on ouvre une nouvelle session-------------------------------------------
        
        HttpSession session = request.getSession(true);
        
        // On verifie si l'objet est deja dans la session------------------------------------------------------------------------
        
        model = (LinkDAOEtudes_Agents) session.getAttribute("modelLinkE_Ag");
        
        // Creation du modele ------------------------------------------------------------------------------------------------------
        
        if (model==null){
            
            //On passe la datasource 'ds' au model-----------------------------------------------------------------------
            
            model = new LinkDAOEtudes_Agents(ds);
            
            //On attirbu un identifiant 'model' à la session-----------------------------------------------
            
            session.setAttribute("modelLinkE_Ag", model);
            
        }
        
        return model;
        
    }
    
    static public HistoriqueGaresDAO creerObjet(HttpServletRequest request, HistoriqueGaresDAO model) {
        
        //On recupere la session si elle est deja ouverte sinon on ouvre une nouvelle session-------------------------------------------
        
        HttpSession session = request.getSession(true);
        
        // On verifie si l'objet est deja dans la session------------------------------------------------------------------------
        
        model = (HistoriqueGaresDAO) session.getAttribute("modelHistoriqueGaresDAO");
        
        // Creation du modele ------------------------------------------------------------------------------------------------------
        
        if (model==null){
            
            //On passe la datasource 'ds' au model-----------------------------------------------------------------------
            
            model = new HistoriqueGaresDAO(ds);
            
            //On attirbu un identifiant 'model' à la session-----------------------------------------------
            
            session.setAttribute("modelHistoriqueGaresDAO", model);
            
        }
        
        return model;
        
    }
    
    static public QualitesDAO creerObjet(HttpServletRequest request, QualitesDAO model) {
        
        //On recupere la session si elle est deja ouverte sinon on ouvre une nouvelle session-------------------------------------------
        
        HttpSession session = request.getSession(true);
        
        // On verifie si l'objet est deja dans la session------------------------------------------------------------------------
        
        model = (QualitesDAO) session.getAttribute("modelQualitesDAO");
        
        // Creation du modele ------------------------------------------------------------------------------------------------------
        
        if (model==null){
            
            //On passe la datasource 'ds' au model-----------------------------------------------------------------------
            
            model = new QualitesDAO(ds);
            
            //On attirbu un identifiant 'model' à la session-----------------------------------------------
            
            session.setAttribute("modelQualitesDAO", model);
            
        }
        
        return model;
        
    }
    
    
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    //                                                                      CONTROLEUR POUR COURIER
    
    
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    static public CourriersDAO creerObjet(HttpServletRequest request, CourriersDAO model) {
        
        //On recupere la session si elle est deja ouverte sinon on ouvre une nouvelle session-------------------------------------------
        
        HttpSession session = request.getSession(true);
        
        // On verifie si l'objet est deja dans la session------------------------------------------------------------------------
        
        model = (CourriersDAO) session.getAttribute("modelCourriersDAO");
        
        // Creation du modele ------------------------------------------------------------------------------------------------------
        
        if (model==null){
            
            //On passe la datasource 'ds' au model-----------------------------------------------------------------------
            
            model = new CourriersDAO(ds);
            
            //On attirbu un identifiant 'model' à la session-----------------------------------------------
            
            session.setAttribute("modelCourriersDAO", model);
            
        }
        
        return model;
        
    }
    
    static public CourrierDepartDAO creerObjet(HttpServletRequest request, CourrierDepartDAO model) {
        
        //On recupere la session si elle est deja ouverte sinon on ouvre une nouvelle session-------------------------------------------
        
        HttpSession session = request.getSession(true);
        
        // On verifie si l'objet est deja dans la session------------------------------------------------------------------------
        
        model = (CourrierDepartDAO) session.getAttribute("modelCourrierDepartDAO");
        
        // Creation du modele ------------------------------------------------------------------------------------------------------
        
        if (model==null){
            
            //On passe la datasource 'ds' au model-----------------------------------------------------------------------
            
            model = new CourrierDepartDAO(ds);
            
            //On attirbu un identifiant 'model' à la session-----------------------------------------------
            
            session.setAttribute("modelCourrierDepartDAO", model);
            
        }
        
        return model;
        
    }
    
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    //                                                                      CONTROLEUR POUR CALQUES
    
    
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------
    
    static public CalquesDAO creerObjet(HttpServletRequest request, CalquesDAO model) {
        
        //On recupere la session si elle est deja ouverte sinon on ouvre une nouvelle session-------------------------------------------
        
        HttpSession session = request.getSession(true);
        
        // On verifie si l'objet est deja dans la session------------------------------------------------------------------------
        
        model = (CalquesDAO) session.getAttribute("modelCourrierCalquesDAO");
        
        // Creation du modele ------------------------------------------------------------------------------------------------------
        
        if (model==null){
            
            //On passe la datasource 'ds' au model-----------------------------------------------------------------------
            
            model = new CalquesDAO(ds);
            
            //On attirbu un identifiant 'model' à la session-----------------------------------------------
            
            session.setAttribute("modelCourrierCalquesDAO", model);
            
        }
        
        return model;
        
    }
    
    static public CasesDAO creerObjet(HttpServletRequest request, CasesDAO model) {
        
        //On recupere la session si elle est deja ouverte sinon on ouvre une nouvelle session-------------------------------------------
        
        HttpSession session = request.getSession(true);
        
        // On verifie si l'objet est deja dans la session------------------------------------------------------------------------
        
        model = (CasesDAO) session.getAttribute("modelCourrierCasesDAO");
        
        // Creation du modele ------------------------------------------------------------------------------------------------------
        
        if (model==null){
            
            //On passe la datasource 'ds' au model-----------------------------------------------------------------------
            
            model = new CasesDAO(ds);
            
            //On attirbu un identifiant 'model' à la session-----------------------------------------------
            
            session.setAttribute("modelCourrierCasesDAO", model);
            
        }
        
        return model;
        
    }
    
    static public HistoriqueCalquesDAO creerObjet(HttpServletRequest request, HistoriqueCalquesDAO model) {
        
        //On recupere la session si elle est deja ouverte sinon on ouvre une nouvelle session-------------------------------------------
        
        HttpSession session = request.getSession(true);
        
        // On verifie si l'objet est deja dans la session------------------------------------------------------------------------
        
        model = (HistoriqueCalquesDAO) session.getAttribute("modelCourrierHistoriqueCalquesDAO");
        
        // Creation du modele ------------------------------------------------------------------------------------------------------
        
        if (model==null){
            
            //On passe la datasource 'ds' au model-----------------------------------------------------------------------
            
            model = new HistoriqueCalquesDAO(ds);
            
            //On attirbu un identifiant 'model' à la session-----------------------------------------------
            
            session.setAttribute("modelCourrierHistoriqueCalquesDAO", model);
            
        }
        
        return model;
        
    }
    
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    //                                                                      CONTROLEUR POUR TRAVAUX
    
    
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------
    
    static public CommandesDAO creerObjet(HttpServletRequest request, CommandesDAO model) {
        
        //On recupere la session si elle est deja ouverte sinon on ouvre une nouvelle session-------------------------------------------
        
        HttpSession session = request.getSession(true);
        
        // On verifie si l'objet est deja dans la session------------------------------------------------------------------------
        
        model = (CommandesDAO) session.getAttribute("modelCourrierCommandesDAO");
        
        // Creation du modele ------------------------------------------------------------------------------------------------------
        
        if (model==null){
            
            //On passe la datasource 'ds' au model-----------------------------------------------------------------------
            
            model = new CommandesDAO(ds);
            
            //On attirbu un identifiant 'model' à la session-----------------------------------------------
            
            session.setAttribute("modelCourrierCommandesDAO", model);
            
        }
        
        return model;
        
    }
    
    static public TravauxDAO creerObjet(HttpServletRequest request, TravauxDAO model) {
        
        //On recupere la session si elle est deja ouverte sinon on ouvre une nouvelle session-------------------------------------------
        
        HttpSession session = request.getSession(true);
        
        // On verifie si l'objet est deja dans la session------------------------------------------------------------------------
        
        model = (TravauxDAO) session.getAttribute("modelCourrierTravauxDAO");
        
        // Creation du modele ------------------------------------------------------------------------------------------------------
        
        if (model==null){
            
            //On passe la datasource 'ds' au model-----------------------------------------------------------------------
            
            model = new TravauxDAO(ds);
            
            //On attirbu un identifiant 'model' à la session-----------------------------------------------
            
            session.setAttribute("modelCourrierTravauxDAO", model);
            
        }
        
        return model;
        
    }
    
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    //                                                                      OUTILS DIVERS
    
    
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------
    
    static public ArrayList DetectionCaractere(ArrayList list_old){
        
        ArrayList list_new;
        list_new = new ArrayList();
        
        String temp = "";
        for(int i = 0; i < list_old.size(); i++){
            temp = ""+list_old.get(i);
            list_new.add(temp.replace("'","\u0092"));
        }
        return list_new;
        
    }
    
    static public String DetectionCaractere(String chaine_old){
        String chaine_new;
        String temp = "";
        
        chaine_new = chaine_old.replace("'","\u0092");
        return chaine_new;
    }
    
    
    static public ArrayList TransformString(ArrayList list_old){
        ArrayList list_new;
        list_new = new ArrayList();
        String particulier1 = "\\";
        String particulier2 = "\\\\";
        String temp = "";
        for(int i = 0; i < list_old.size(); i++){
            temp = ""+list_old.get(i);
            list_new.add(temp.replace("Â«","«").replace("Â»","»").replace("Ãª","ê").replace("Ã©","é").replace("Ã¨","è").replace("Ã¢","â").replace("Ã ","à").replace("Ã´","ô").replace("Ã¯","ï").replace("Ã§","ç").replace("Ã¹","ù").replace("Â²","²").replace("Â°","°").replace("Â£","£").replace("Â$","$").replace("Â¤","¤").replace("Âµ","µ").replace("Â§","§").replace("â?¬","€").replace(particulier1,particulier2).replaceAll("\u0092","'").replaceAll("\u0085","."));
        }
        return list_new;
    }
    
    static public String TransformString(String chaine_old){
        String chaine_new;
        String particulier1 = "\\";
        String particulier2 = "\\\\";
        String temp = "";
        
        chaine_new = chaine_old.replace("Â«","«").replace("Â»","»").replace("Ãª","ê").replace("Ã©","é").replace("Ã¨","è").replace("Ã¢","â").replace("Ã ","à").replace("Ã´","ô").replace("Ã¯","ï").replace("Ã§","ç").replace("Ã¹","ù").replace("Â²","²").replace("Â°","°").replace("Â£","£").replace("Â$","$").replace("Â¤","¤").replace("Âµ","µ").replace("Â§","§").replace("â?¬","€").replace(particulier1,particulier2).replaceAll("\u0092","'").replaceAll("\u0085",".");
        return chaine_new;
    }
    
    static public void Mouchard(HttpServletRequest request, UtilisateursDAO utilisateur, String action, String table){
        
        //création de l'objet GregorianCalendar
        GregorianCalendar d = new GregorianCalendar();
        
        String heure = ""+d.get(Calendar.HOUR_OF_DAY);
        if(heure.length() == 1){
            heure = "0"+heure;
        }
        
        String minute = ""+d.get(Calendar.MINUTE);
        if(minute.length() == 1){
            minute = "0"+minute;
        }
        
        String seconde = ""+d.get(Calendar.SECOND);
        if(seconde.length() == 1){
            seconde = "0"+seconde;
        }
        
        String remote_adresse = request.getRemoteAddr();
        
        if(action.equals("Connection")){
            if(!utilisateur.TestIntru()){
                System.out.println("\nMOUCHARD DE CONNECTION----------------------------------------------------------");
                System.out.println("Connection le : "+DataDate.GetDate()+" "+heure+":"+minute+":"+seconde+"");
                System.out.println("IP : "+remote_adresse);
                System.out.println("Agent : "+utilisateur.nom+" "+utilisateur.prenom);
                System.out.println("Role : "+utilisateur.role);
                System.out.println("Sous-Groupe : "+utilisateur.sous_groupe);
                System.out.println("--------------------------------------------------------------------------------");
            }else{
                System.out.println("\nMOUCHARD DE CONNECTION----------------------------------------------------------");
                System.out.println("Un intru tente de se connecter à la base");
                System.out.println("Connection le : "+DataDate.GetDate()+" "+heure+":"+minute+":"+seconde+"");
                System.out.println("IP : "+remote_adresse);
                System.out.println("--------------------------------------------------------------------------------");
            }
        } else if(action.equals("Deconnection")){
            System.out.println("\nMOUCHARD------------------------------------------------------------------------");
            System.out.println("L'agent "+utilisateur.nom+" "+utilisateur.prenom+" vient de se deconnecter");
            System.out.println("le : "+DataDate.GetDate()+" "+heure+":"+minute+":"+seconde+"");
            System.out.println("IP : "+remote_adresse);
            System.out.println("--------------------------------------------------------------------------------");
        } else if(action.equals("Jeux")){
            System.out.println("\nMOUCHARD------------------------------------------------------------------------");
            System.out.println("L'agent "+utilisateur.nom+" "+utilisateur.prenom+" entre dans la section 'JEUX'");
            System.out.println("le : "+DataDate.GetDate()+" "+heure+":"+minute+":"+seconde+"");
            System.out.println("IP : "+remote_adresse);
            System.out.println("--------------------------------------------------------------------------------");
        }        else if(action.equals("Modification")){
            System.out.println("\nMOUCHARD------------------------------------------------------------------------");
            System.out.println("L'agent "+utilisateur.nom+" "+utilisateur.prenom+" effectue une MODIFICATION dans la table "+table);
            System.out.println("le : "+DataDate.GetDate()+" "+heure+":"+minute+":"+seconde+"");
            System.out.println("IP : "+remote_adresse);
            System.out.println("--------------------------------------------------------------------------------");
        } else if(action.equals("Suppression")){
            System.out.println("\nMOUCHARD------------------------------------------------------------------------");
            System.out.println("L'agent "+utilisateur.nom+" "+utilisateur.prenom+" effectue une SUPPRESSION dans la table "+table);
            System.out.println("le : "+DataDate.GetDate()+" "+heure+":"+minute+":"+seconde+"");
            System.out.println("IP : "+remote_adresse);
            System.out.println("--------------------------------------------------------------------------------");
        } else if(action.equals("Ajout")){
            System.out.println("\nMOUCHARD------------------------------------------------------------------------");
            System.out.println("L'agent "+utilisateur.nom+" "+utilisateur.prenom+" effectue une AJOUT dans la table "+table);
            System.out.println("le : "+DataDate.GetDate()+" "+heure+":"+minute+":"+seconde+"");
            System.out.println("IP : "+remote_adresse);
            System.out.println("--------------------------------------------------------------------------------");
        }
        
        
        
    }
    
}
