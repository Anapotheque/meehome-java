package Models.ETUDES.Rch;

import Models.TOOLS.Controleur.MegaControleur;
import Models.ETUDES.Agents.AgentsDAO;
import Models.ETUDES.Entreprises.EntreprisesDAO;
import Models.ETUDES.Etudes.EtudesDAO;
import Models.TOOLS.Tools;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

//RABALLAND Romain v3.0

public class RchDAO extends Tools {
    
    //DECLARATION DES LISTES
    public ArrayList list_CodeRch;
    public ArrayList list_CodeEtude;
    public ArrayList list_Emission;
    public ArrayList list_Reception;
    public ArrayList list_Observations;
    public ArrayList list_CodeEntreprise;
    public ArrayList list_CodeAgent;
    public ArrayList list_RemisAgent;
    public ArrayList list_Expedition;
    public ArrayList list_Archive;
    public ArrayList list_Ressource;
    public ArrayList list_show;
    public ArrayList list_Titre;
    public ArrayList list_SizeTitre;
    String requete = null;
    
    public float[] widthsShow =       {0.05f,0.06f,0.1f,0.13f,0.12f,0.12f,0.12f,0.11f,0.05f,0.14f};
    
    //DECLARATION DU CONSTRUCTEUR
    public RchDAO(DataSource ds){
        super(ds);
        SetList();
    }
    
    //INITIALISATION DES DONNEES
    public void SetList(){
        
        list_Titre = new ArrayList();
        list_SizeTitre = new ArrayList();
        
        list_Titre.add("N°");
        list_Titre.add("indice");
        list_Titre.add("agent");
        list_Titre.add("entreprise");
        list_Titre.add("emission");
        list_Titre.add("reception");
        list_Titre.add("remis");
        list_Titre.add("conforme");
        list_Titre.add("arch");
        list_Titre.add("observations");
        
        list_SizeTitre.add("10%");
        list_SizeTitre.add("10%");
        list_SizeTitre.add("10%");
        list_SizeTitre.add("10%");
        list_SizeTitre.add("10%");
        list_SizeTitre.add("10%");
        list_SizeTitre.add("10%");
        list_SizeTitre.add("10%");
        list_SizeTitre.add("10%");
        list_SizeTitre.add("10%");
        
        list_Ressource = new ArrayList();
        
        list_Ressource.add("numero rch");
        list_Ressource.add("indice");
        list_Ressource.add("agent");
        list_Ressource.add("entreprise");
        list_Ressource.add("emission");
        list_Ressource.add("reception");
        list_Ressource.add("remis");
        list_Ressource.add("conforme");
        list_Ressource.add("archive");
        list_Ressource.add("observations");
        
    }
    
    //INITIALISATION DES DONNEES
    public void Set(HttpServletRequest request)
    throws SQLException{
                
        list_CodeRch = new ArrayList();
        list_CodeEtude = new ArrayList();
        list_Emission = new ArrayList();
        list_Reception = new ArrayList();
        list_Observations = new ArrayList();
        list_CodeEntreprise = new ArrayList();
        list_CodeAgent = new ArrayList();
        list_RemisAgent = new ArrayList();
        list_Expedition = new ArrayList();
        list_Archive = new ArrayList();
        
        list_show = new ArrayList();
        ResultSet result = null;
        
        requete = "SELECT * " +
                "FROM Rch " +
                "ORDER BY 'codeRch' DESC";
        result = RequeteSelect(result,requete);
        
        while (result.next()){
            
            list_CodeRch.add(result.getString(1));
            list_CodeEtude.add(result.getString(2));
            list_Emission.add(result.getString(3));
            list_Reception.add(result.getString(4));
            list_Observations.add(result.getString(5));
            list_CodeEntreprise.add(result.getString(6));
            list_CodeAgent.add(result.getString(7));
            list_RemisAgent.add(result.getString(8));
            list_Expedition.add(result.getString(9));
            list_Archive.add(result.getString(10));
        }
        
        requete = "SELECT Rch.codeRch, Etudes.Indice, agents.nom, Entreprises.Entreprise, Rch.Emission, Rch.Reception, Rch.RemisAgent, Rch.Expedition, Rch.Archive, Rch.Observation "+
                "FROM Agents RIGHT OUTER JOIN (Etudes RIGHT OUTER JOIN (Entreprises RIGHT OUTER JOIN Rch ON (Rch.codeEntreprise = Entreprises.codeEntreprise)) ON (Rch.codeEtude = etudes.codeEtude)) ON Agents.codeAgent = Rch.codeAgent "+
                "ORDER BY Rch.codeRch DESC";
        
        result = RequeteSelect(result,requete);
        
        while (result.next()){
            list_show.add(result.getString(1));
            list_show.add(result.getString(2));
            list_show.add(result.getString(3));
            list_show.add(result.getString(4));
            list_show.add(result.getString(5));
            list_show.add(result.getString(6));
            list_show.add(result.getString(7));
            list_show.add(result.getString(8));
            list_show.add(result.getString(9));
            list_show.add(result.getString(10));
            
        }
        list_Observations = Transform(list_Observations);
        list_show = Transform(list_show);
        Close();
        result.close();
        
    }
    
    //AJOUT D'UN NOUVEL ELEMENT DANS LA BASE
    public void New(ArrayList list)
    throws SQLException{
        list = DetectionCaractere(list);
        RequeteUpdate("INSERT INTO `Rch`(`codeRch`,`codeEtude`,`codeAgent`,`codeEntreprise`,`emission`,`reception`,`observation`,`remisAgent`,`expedition`,`archive`) VALUES ('"+list.get(0)+"','"+list.get(1)+"','"+list.get(2)+"','"+list.get(3)+"','"+list.get(4)+"','"+list.get(5)+"','"+list.get(6)+"','"+list.get(7)+"','"+list.get(8)+"','"+list.get(9)+"')");
        
    }
    
    //MODIFICATION D'UN ELEMENT DE LA BASE
    public void Mod(String codeRch, ArrayList list)
    throws SQLException{
        list = DetectionCaractere(list);
        RequeteUpdate("UPDATE `Rch` SET `codeRch` = '"+list.get(0)+"',`codeEtude` = '"+list.get(1)+"',`codeAgent` = '"+list.get(2)+"',`codeEntreprise` = '"+list.get(3)+"',`emission` = '"+list.get(4)+"',`reception` = '"+list.get(5)+"',`observation` = '"+list.get(6)+"',`remisAgent` = '"+list.get(7)+"',`expedition` = '"+list.get(8)+"',`archive` = '"+list.get(9)+"' WHERE `codeRch` ="+codeRch);
        
    }
    
    //RECHERCHE D'UN ELEMENT DE LA BASE
    public boolean Search(HttpServletRequest request, String mot_clef, String filtre)
    throws SQLException{
                
        ResultSet result = null;
        
        list_CodeRch = new ArrayList();
        list_CodeEtude = new ArrayList();
        list_Emission = new ArrayList();
        list_Reception = new ArrayList();
        list_Observations = new ArrayList();
        list_CodeEntreprise = new ArrayList();
        list_CodeAgent = new ArrayList();
        list_RemisAgent = new ArrayList();
        list_Expedition = new ArrayList();
        list_Archive = new ArrayList();
        
        list_show = new ArrayList();
        
        mot_clef = DetectionCaractere(mot_clef);
        if(filtre.equals("codeAffaire"))
            requete = "SELECT * " +
                    "FROM rch " +
                    "WHERE codeEtude = ANY (select codeEtude FROM etudes_has_affaires " +
                                            "WHERE "+filtre+" = '"+mot_clef+"')" +
                    "ORDER BY Rch.codeRch DESC";
        
        else
            requete = "SELECT * " +
                    "FROM Rch " +
                    "WHERE "+filtre+" LIKE '%"+mot_clef+"%'" +
                    "ORDER BY Rch.codeRch DESC";
        
        result = RequeteSelect(result,requete);
        
        while (result.next()){
            
            list_CodeRch.add(result.getString(1));
            list_CodeEtude.add(result.getString(2));
            list_Emission.add(result.getString(3));
            list_Reception.add(result.getString(4));
            list_Observations.add(result.getString(5));
            list_CodeEntreprise.add(result.getString(6));
            list_CodeAgent.add(result.getString(7));
            list_RemisAgent.add(result.getString(8));
            list_Expedition.add(result.getString(9));
            list_Archive.add(result.getString(10));
        }
        
        if(filtre.equals("codeAffaire"))
            requete = "SELECT Rch.codeRch, Etudes.Indice, agents.nom, Entreprises.Entreprise, Rch.Emission, Rch.Reception, Rch.RemisAgent, Rch.Expedition, Rch.Archive, Rch.Observation "+
                    "FROM Agents RIGHT OUTER JOIN (Etudes RIGHT OUTER JOIN (Entreprises RIGHT OUTER JOIN Rch ON (Rch.codeEntreprise = Entreprises.codeEntreprise)) ON (Rch.codeEtude = etudes.codeEtude)) ON Agents.codeAgent = Rch.codeAgent "+
                    "WHERE Rch.codeEtude = ANY (select codeEtude from etudes_has_affaires where "+filtre+" = '"+mot_clef+"') "+
                    "ORDER BY Rch.codeRch DESC";
        
        else
            requete = "SELECT Rch.codeRch, Etudes.Indice, agents.nom, Entreprises.Entreprise, Rch.Emission, Rch.Reception, Rch.RemisAgent, Rch.Expedition, Rch.Archive, Rch.Observation "+
                    "FROM Agents RIGHT OUTER JOIN (Etudes RIGHT OUTER JOIN (Entreprises RIGHT OUTER JOIN Rch ON (Rch.codeEntreprise = Entreprises.codeEntreprise)) ON (Rch.codeEtude = etudes.codeEtude)) ON Agents.codeAgent = Rch.codeAgent "+
                    "WHERE Rch."+filtre+" LIKE '%"+mot_clef+"%' "+
                    "ORDER BY Rch.codeRch DESC";
        
        result = RequeteSelect(result,requete);
        
        while (result.next()){
            list_show.add(result.getString(1));
            list_show.add(result.getString(2));
            list_show.add(result.getString(3));
            list_show.add(result.getString(4));
            list_show.add(result.getString(5));
            list_show.add(result.getString(6));
            list_show.add(result.getString(7));
            list_show.add(result.getString(8));
            list_show.add(result.getString(9));
            list_show.add(result.getString(10));
        }
                list_Observations = Transform(list_Observations);
        list_show = Transform(list_show);
        Close();
        result.close();
        
        if(list_show.size() != 0)return true;
        else return false;
    }
    
//RECUPERE LE CODE MAX
    public int GetLastCodeRch()
    throws SQLException{
        String codeRch = null;
        int code = 0;
        ResultSet result = null;
        
        result = RequeteSelect(result,"select max(codeRch) from Rch");
        while (result.next()){
            codeRch = result.getString(1);
            if(codeRch == null)
                code = 2006000;
            else
                code = Integer.parseInt(codeRch)+1;
        }
        
        Close();
        result.close();
        
        return code;
    }
}

