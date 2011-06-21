package Models.ETUDES.Outils;

import Models.TOOLS.Tools;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

//RABALLAND Romain v3.0

public class SuivisDAO extends Tools{
    
    /** Creates a new instance of QualitesDAO */
    public SuivisDAO(DataSource ds) {
        super(ds);
        SetList();
    }
    
    public ArrayList list_Show;
    public ArrayList list_TitreSE;
    public ArrayList list_SizeTitreSE;
    public ArrayList list_TitrePT;
    public ArrayList list_SizeTitrePT;
    
    //TAILLE D'AFFICHAGE POUR PDF
    public float[] widthsShowSE =       {0.1f,0.1f,0.1f,0.1f,0.1f,0.1f,0.1f,0.1f,0.1f,0.1f};
    public float[] widthsShowPT =       {0.2f,0.1f,0.2f,0.1f,0.1f,0.1f,0.1f,0.1f};
    
    //INITIALISATION DES DONNEES
    public void SetList(){
        
        list_TitreSE = new ArrayList();
        
        list_TitreSE.add("indice");
        list_TitreSE.add("gare");
        list_TitreSE.add("N Aff");
        list_TitreSE.add("Affaires");
        list_TitreSE.add("entreprise");
        list_TitreSE.add("envoi");
        list_TitreSE.add("sesco");
        list_TitreSE.add("MES");
        list_TitreSE.add("Rch");
        list_TitreSE.add("Arch");
        
        list_SizeTitreSE = new ArrayList();
        
        list_SizeTitreSE.add("10%");
        list_SizeTitreSE.add("10%");
        list_SizeTitreSE.add("10%");
        list_SizeTitreSE.add("10%");
        list_SizeTitreSE.add("10%");
        list_SizeTitreSE.add("10%");
        list_SizeTitreSE.add("10%");
        list_SizeTitreSE.add("10%");
        list_SizeTitreSE.add("10%");
        list_SizeTitreSE.add("10%");
        
        list_TitrePT = new ArrayList();
        
        list_TitrePT.add("indice");
        list_TitrePT.add("gare");
        list_TitrePT.add("N Aff");
        list_TitrePT.add("Affaire");
        list_TitrePT.add("projet");
        list_TitrePT.add("etude");
        list_TitrePT.add("conforme");
        list_TitrePT.add("MES");
        
        list_SizeTitrePT = new ArrayList();
        
        list_SizeTitrePT.add("10%");
        list_SizeTitrePT.add("20%");
        list_SizeTitrePT.add("10%");
        list_SizeTitrePT.add("20%");
        list_SizeTitrePT.add("10%");
        list_SizeTitrePT.add("10%");
        list_SizeTitrePT.add("10%");
        list_SizeTitrePT.add("10%");
    }
    
    public boolean SetSuiviSE(int codeAgent)
    throws SQLException{
        
        
        String REQUETE_SUIVI_PAR_AGENT_SE = "SELECT etudes.indice, gares.gare, affaires.codeAffaire, affaires.nom, entreprises.entreprise, etudes.envoiTx, etudes.sescoTx, etudes.miseEnService, rch.codeRch, rch.archive "+
                "FROM "+
                "(Rch INNER JOIN "+
                "(entreprises INNER JOIN "+
                "(etudes INNER JOIN gares ON (etudes.codeGAre = gares.codeGAre)) "+
                "ON (etudes.codeEntreprise = entreprises.codeEntreprise)) "+
                "ON (rch.codeEtude = etudes.codeEtude)) "+
                "INNER JOIN "+
                "(affaires INNER JOIN etudes_has_affaires ON (affaires.codeAffaire = etudes_has_affaires.codeAffaire) "+
                "INNER JOIN "+
                "agents INNER JOIN etudes_has_agents ON (agents.codeAgent = etudes_has_agents.codeAgent) "+
                "ON (etudes_has_agents.codeEtude = etudes_has_affaires.codeEtude)) "+
                "ON (etudes_has_agents.codeEtude = etudes.codeEtude) "+
                "WHERE agents.codeAgent = "+codeAgent+" AND gares.groupe = 'SE' "+
                "ORDER BY etudes.indice";
        
        list_Show = new ArrayList();
        
        ResultSet result = null;
        
        result = RequeteSelect(result,REQUETE_SUIVI_PAR_AGENT_SE);
        while (result.next()){
            
            list_Show.add(result.getString(1));
            list_Show.add(result.getString(2));
            list_Show.add(result.getString(3));
            list_Show.add(result.getString(4));
            list_Show.add(result.getString(5));
            list_Show.add(result.getString(6));
            list_Show.add(result.getString(7));
            list_Show.add(result.getString(8));
            list_Show.add(result.getString(9));
            list_Show.add(result.getString(10));
            
        }
        
        Close();
        result.close();
        if(list_Show.size() !=0)
            return true;
        else return false;
        
    }
    
    public boolean SetSuiviPT(int codeAgent)
    throws SQLException{
        
        
    String REQUETE_SUIVI_PAR_AGENT_PT = "SELECT etudes.indice, gares.gare, affaires.codeAffaire, affaires.nom, etudes.projet, etudes.etudeTravaux, etudes.conforme, etudes.miseEnService "+
            "FROM "+
            "(Rch INNER JOIN "+
            "(etudes INNER JOIN gares ON (etudes.codeGAre = gares.codeGAre)) "+
            "ON (rch.codeEtude = etudes.codeEtude)) "+
            "INNER JOIN "+
            "(affaires INNER JOIN etudes_has_affaires ON (affaires.codeAffaire = etudes_has_affaires.codeAffaire) "+
            "INNER JOIN "+
            "agents INNER JOIN etudes_has_agents ON (agents.codeAgent = etudes_has_agents.codeAgent) "+
            "ON (etudes_has_agents.codeEtude = etudes_has_affaires.codeEtude)) "+
            "ON (etudes_has_agents.codeEtude = etudes.codeEtude) "+
            "WHERE agents.codeAgent = "+codeAgent+" and gares.groupe = 'PT' "+
            "ORDER BY etudes.indice";
        
        list_Show = new ArrayList();
        
        ResultSet result = null;
        
        result = RequeteSelect(result,REQUETE_SUIVI_PAR_AGENT_PT);
        while (result.next()){
            
            list_Show.add(result.getString(1));
            list_Show.add(result.getString(2));
            list_Show.add(result.getString(3));
            list_Show.add(result.getString(4));
            list_Show.add(result.getString(5));
            list_Show.add(result.getString(6));
            list_Show.add(result.getString(7));
            list_Show.add(result.getString(8));
            
        }
        
        Close();
        result.close();
        if(list_Show.size() !=0)
            return true;
        else return false;
        
    }
    
}
