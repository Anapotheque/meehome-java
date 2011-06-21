package Models.CALQUES;

import Models.TOOLS.Tools;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

//RABALLAND Romain v3.0
public class HistoriqueCalquesDAO extends Tools{
    
    /** Contructeur*/
    public HistoriqueCalquesDAO(DataSource ds) {
        super(ds);
    }
    
    public ArrayList list = null;
    public ArrayList list_Titre = null;
    public ArrayList list_Size = null;
    
    private void SetList(){
        
        list_Titre = new ArrayList();
        list_Titre.add("NUMEROS");
        list_Titre.add("ENTREPRISES");
        list_Titre.add("AGENTS");
        list_Titre.add("GARES");
        list_Titre.add("SORTIE");
        list_Titre.add("RETOUR");
        
        list_Size = new ArrayList();
        list_Size.add("15%");
        list_Size.add("15%");
        list_Size.add("15%");
        list_Size.add("15%");
        list_Size.add("20%");
        list_Size.add("20%");
        
    }
    
    public boolean Search(String code,String choix)
    throws SQLException{
        
        list = new ArrayList();
        ResultSet result = null;
        
        SetList();
        
        String requete = "";
        
        if(choix.equals("Agent"))
            requete = "SELECT historiquecalques.NumeroCalques, entreprises.entreprise, agents.nom, gares.gare, historiquecalques.DateSortie, historiquecalques.DateRetour "+
                    "FROM "+
                    "gares INNER JOIN "+
                    "(agents INNER JOIN "+
                    "(historiquecalques INNER JOIN entreprises ON ( historiquecalques.codeEntreprise = entreprises.codeEntreprise)) "+
                    "ON (agents.codeAgent = historiquecalques.codeAgent)) "+
                    "ON (gares.codeGare = historiquecalques.codegare) "+
                    "WHERE historiquecalques.codeAgent = "+code+" "+
                    "ORDER BY historiquecalques.DateSortie DESC";
        
        if(choix.equals("Entreprise"))
            requete = "SELECT historiquecalques.NumeroCalques, entreprises.entreprise, agents.nom, gares.gare, historiquecalques.DateSortie, historiquecalques.DateRetour "+
                    "FROM "+
                    "gares INNER JOIN "+
                    "(agents INNER JOIN "+
                    "(historiquecalques INNER JOIN entreprises ON ( historiquecalques.codeEntreprise = entreprises.codeEntreprise)) "+
                    "ON (agents.codeAgent = historiquecalques.codeAgent)) "+
                    "ON (gares.codeGare = historiquecalques.codegare) "+
                    "WHERE historiquecalques.codeEntreprise = "+code+" "+
                    "ORDER BY historiquecalques.DateSortie DESC";
        
        if(choix.equals("Gare"))
            requete = "SELECT historiquecalques.NumeroCalques, entreprises.entreprise, agents.nom, gares.gare, historiquecalques.DateSortie, historiquecalques.DateRetour "+
                    "FROM "+
                    "gares INNER JOIN "+
                    "(agents INNER JOIN "+
                    "(historiquecalques INNER JOIN entreprises ON ( historiquecalques.codeEntreprise = entreprises.codeEntreprise)) "+
                    "ON (agents.codeAgent = historiquecalques.codeAgent)) "+
                    "ON (gares.codeGare = historiquecalques.codegare) "+
                    "WHERE historiquecalques.codeGare = "+code+" "+
                    "ORDER BY historiquecalques.DateSortie DESC";
        
        result = RequeteSelect(result,requete);
        
        while (result.next()){
            
            list.add(result.getString(1));
            list.add(result.getString(2));
            list.add(result.getString(3));
            list.add(result.getString(4));
            list.add(result.getString(5));
            list.add(result.getString(6));
            
        }
        
        Close();
        result.close();
        
        return list.size()!=0?true:false;
        
    }
    
}
