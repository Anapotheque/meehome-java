
package Models.ETUDES.Outils;

import Models.TOOLS.Tools;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

//RABALLAND Romain v3.0

public class QualitesDAO extends Tools {
    
    /** Creates a new instance of QualitesDAO */
    public QualitesDAO(DataSource ds) {
        super(ds);
        SetList();
    }
    
    public ArrayList list_Show;
    public ArrayList list_Titre;
    public ArrayList list_SizeTitre;
    
    //TAILLE D'AFFICHAGE POUR PDF
    public float[] widthsShow =       {0.2f,0.1f,0.4f,0.1f,0.1f,0.1f};
    
    //INITIALISATION DES DONNEES
    public void SetList(){
        
        list_Titre = new ArrayList();
        
        list_Titre.add("Agents");
        list_Titre.add("N°");
        list_Titre.add("Affaires");
        list_Titre.add("Gares");
        list_Titre.add("indices");
        list_Titre.add("Note/20");
        
        list_SizeTitre = new ArrayList();
        
        list_SizeTitre.add("20%");
        list_SizeTitre.add("10%");
        list_SizeTitre.add("30%");
        list_SizeTitre.add("30%");
        list_SizeTitre.add("5%");
        list_SizeTitre.add("10%");
        
    }
    
    public boolean SetSearchQualite(int codeEntreprise)
    throws SQLException{
        
        list_Show = new ArrayList();
        
        ResultSet result = null;
        
        String requete = "SELECT agents.nom, affaires.codeAffaire, affaires.nom, gares.gare, etudes.indice, etudes.difficulte, etudes.poidsKg, etudes.contraires, etudes.autres, etudes.relations, etudes.materiel, etudes.delais, etudes.reports, etudes.metre "+
                "FROM (affaires INNER JOIN etudes_has_affaires ON (affaires.codeAffaire = etudes_has_affaires.codeAffaire) "+
                "INNER JOIN "+
                "agents INNER JOIN etudes_has_agents ON (agents.codeAgent = etudes_has_agents.codeAgent) "+
                "ON "+
                "(etudes_has_affaires.codeEtude = etudes_has_agents.codeEtude)) "+
                "INNER JOIN "+
                "(etudes INNER JOIN gares ON (etudes.codeGare = gares.codeGare)) "+
                "ON "+
                "(etudes.codeEtude = etudes_has_agents.codeEtude) "+
                "WHERE etudes.codeEntreprise = "+codeEntreprise;
        
        result = RequeteSelect(result,requete);
        while (result.next()){
            
            if(!result.getString(7).equals("0.00")){
            list_Show.add(result.getString(1));
            list_Show.add(result.getString(2));
            list_Show.add(result.getString(3));
            list_Show.add(result.getString(4));
            list_Show.add(result.getString(5));
            
            
            float D = result.getFloat(6);
            float P = result.getFloat(7);
            float C = result.getFloat(8);
            float N = result.getFloat(9);
            float B = result.getFloat(10) + result.getFloat(11) + result.getFloat(12) + result.getFloat(13) + result.getFloat(14);
            
            float note = 15 + B - 2 * (10 * C + N ) / ( D * P );
            
            list_Show.add(""+note);
            }
        }
        
        Close();
        result.close();
        if(list_Show.size() !=0)
            return true;
        else return false;
        
    }
}
