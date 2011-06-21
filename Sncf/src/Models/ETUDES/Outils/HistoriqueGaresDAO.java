package Models.ETUDES.Outils;

import Models.TOOLS.Tools;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

//RABALLAND Romain v3.0

public class HistoriqueGaresDAO extends Tools {
    
    //DECLARATION DU CONSTRUCTEUR
    public HistoriqueGaresDAO(DataSource ds) {
        super(ds);
        SetList();
    }
    
    public ArrayList list_show;
    public ArrayList list_TitrePT;
    public ArrayList list_SizeTitrePT;
    
    public ArrayList list_TitreSE;
    public ArrayList list_SizeTitreSE;
    
    //TAILLE D'AFFICHAGE POUR PDF
    public float[] widthsShowPT =       {0.05f,0.45f,0.1f,0.1f,0.1f,0.1f,0.1f};
    public float[] widthsShowSE =       {0.07f,0.07f,0.12f,0.1f,0.2f,0.1f,0.1f,0.1f,0.1f,0.1f,0.08f};
    
    //INITIALISATION DES DONNEES
    public void SetList(){
        
        
        list_TitrePT = new ArrayList();
        list_SizeTitrePT = new ArrayList();
        
        list_TitrePT.add("indice");
        list_TitrePT.add("affaire");
        list_TitrePT.add("agent");
        list_TitrePT.add("Projet");
        list_TitrePT.add("Travaux");
        list_TitrePT.add("Conforme");
        list_TitrePT.add("MES");
        
        list_SizeTitrePT.add("10%");
        list_SizeTitrePT.add("30%");
        list_SizeTitrePT.add("20%");
        list_SizeTitrePT.add("10%");
        list_SizeTitrePT.add("10%");
        list_SizeTitrePT.add("10%");
        list_SizeTitrePT.add("10%");
        
        list_TitreSE = new ArrayList();
        list_SizeTitreSE = new ArrayList();
        
        list_TitreSE.add("indice");
        list_TitreSE.add("N°Et");
        list_TitreSE.add("entreprise");
        list_TitreSE.add("N° Aff");
        list_TitreSE.add("Affaire");
        list_TitreSE.add("Agent");
        list_TitreSE.add("Envoi");
        list_TitreSE.add("Seco");
        list_TitreSE.add("MES");
        list_TitreSE.add("R/Ch");
        list_TitreSE.add("Arch");
        
        list_SizeTitreSE.add("5%");
        list_SizeTitreSE.add("5%");
        list_SizeTitreSE.add("25%");
        list_SizeTitreSE.add("5%");
        list_SizeTitreSE.add("30%");
        list_SizeTitreSE.add("5%");
        list_SizeTitreSE.add("5%");
        list_SizeTitreSE.add("5%");
        list_SizeTitreSE.add("5%");
        list_SizeTitreSE.add("5%");
        list_SizeTitreSE.add("5%");
    }
    
    public boolean SetHistorySEGares(int codeGare)
    throws SQLException{
        
        list_show = new ArrayList();
        
        ResultSet result = null;
        
        String REQUETE = "SELECT etudes.indice, etudes.codeEtude, entreprises.entreprise, affaires.codeAffaire, affaires.nom, agents.nom, etudes.envoiTx, etudes.sescoTx, etudes.miseEnService, Rch.codeRch, Rch.archive "+
                "FROM "+
                "((affaires INNER JOIN etudes_has_affaires ON (affaires.codeAffaire = etudes_has_affaires.codeAffaire)) "+
                "INNER JOIN "+
                "(agents INNER JOIN etudes_has_agents ON (agents.codeAgent = etudes_has_agents.codeAgent)) "+
                "ON (etudes_has_affaires.codeEtude = etudes_has_agents.codeEtude)) "+
                "INNER JOIN "+
                "(rch INNER JOIN "+
                "(etudes INNER JOIN entreprises ON (etudes.codeEntreprise = entreprises.codeEntreprise)) "+
                "ON (rch.codeEtude = etudes.codeEtude)) "+
                "ON (etudes_has_affaires.codeEtude = etudes.codeEtude) "+
                "WHERE etudes.codeGare = '"+codeGare+"' "+
                "ORDER BY etudes.indice DESC";
        
        result = RequeteSelect(result,REQUETE);
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
            list_show.add(result.getString(11));
            
        }
        
        Close();
        result.close();
        if(list_show.size() !=0)
            return true;
        else return false;
    }
    
    public boolean SetHistoryPTGares(int codeGare)
    throws SQLException{
        
        list_show = new ArrayList();
        
        ResultSet result = null;
        
        String REQUETE = "SELECT indice, affaires.nom, agents.nom, projet, etudeTravaux, conforme, miseenservice "+
                "FROM "+
                "(affaires INNER JOIN etudes_has_affaires ON (affaires.codeAffaire = etudes_has_affaires.codeAffaire)) "+
                "INNER JOIN "+
                "(agents "+
                "INNER JOIN "+
                "(etudes INNER JOIN etudes_has_agents ON (etudes.codeEtude = etudes_has_agents.codeEtude)) "+
                "ON (agents.codeAgent = etudes_has_agents.codeAgent) ) "+
                "ON (etudes_has_affaires.codeEtude = etudes.codeEtude) "+
                "WHERE etudes.codeGare = "+codeGare+" "+
                "ORDER BY etudes.indice DESC";
        
        result = RequeteSelect(result,REQUETE);
        while (result.next()){
            
            list_show.add(result.getString(1));
            list_show.add(result.getString(2));
            list_show.add(result.getString(3));
            list_show.add(result.getString(4));
            list_show.add(result.getString(5));
            list_show.add(result.getString(6));
            list_show.add(result.getString(7));
            
        }
        
        Close();
        result.close();
        if(list_show.size() !=0)
            return true;
        else return false;
    }
    
}
