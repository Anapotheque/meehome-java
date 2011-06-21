package Models.ETUDES.Even;

import Models.TOOLS.Tools;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

//@author RABALLAND Romain
//@version v3.0

public class EvenDAO extends Tools {
    
    //DECLARATION DES LISTES
    public ArrayList list_CodeEven;
    public ArrayList list_nom;
    public ArrayList list_Ressource;
    public ArrayList list_show;
    public ArrayList list_Titre;
    public ArrayList list_SizeTitre;
    
    //TAILLE D'AFFICHAGE POUR PDF
    public float[] widthsShow =       {1f};
    
    //DECLARATION DU CONSTRUCTEUR
    public EvenDAO(DataSource ds){
        super(ds);
        SetList();
    }
    
    //INITIALISATION DES DONNEES
    public void SetList(){
        list_Titre = new ArrayList();
        list_SizeTitre = new ArrayList();
        list_Ressource = new ArrayList();
        
        list_Titre.add("Nom Even");
        
        list_SizeTitre.add("100%");
        
        list_Ressource.add("Nom Even");
    }
    
    //INITIALISATION DES DONNEES
    public void Set()
    throws SQLException{
        
        ResultSet result = null;
        list_CodeEven = new ArrayList();
        list_nom = new ArrayList();
        list_show = new ArrayList();
        
        result = RequeteSelect(result,"select * from Even where nom NOT LIKE'%ZZ%' order by 'nom' ");
        while (result.next()){
            list_CodeEven.add(result.getString(1));
            list_nom.add(result.getString(2));
            list_show.add(result.getString(2));
        }
        list_nom = Transform(list_nom);
        list_show = Transform(list_show);
        Close();
        result.close();
        
    }
    
    //AJOUT D'UN NOUVEL ELEMENT DANS LA BASE
    public void NewEven(ArrayList list)
    throws SQLException{
        list = DetectionCaractere(list);
        RequeteUpdate("INSERT INTO `Even`(`codeEven`,`nom`) VALUES (null,'"+list.get(0)+"')");
        
    }
    
    //MODIFICATION D'UN ELEMENT DE LA BASE
    public void ModEven(String codeEven, ArrayList list)
    throws SQLException{
        list = DetectionCaractere(list);
        RequeteUpdate("UPDATE `Even` SET `nom` = '"+list.get(0)+"' WHERE `codeEven` ="+codeEven);
        
    }
    
    //RECHERCHE D'UN ELEMENT DE LA BASE
    public boolean Search(String mot_clef, String filtre)
    throws SQLException{
        
        ResultSet result = null;
        
        list_CodeEven = new ArrayList();
        list_nom = new ArrayList();
        
        list_show = new ArrayList();
        mot_clef = DetectionCaractere(mot_clef);
        result = RequeteSelect(result,"select * from Even where "+filtre+" LIKE '%"+mot_clef+"%' order by 'nom'");
        while (result.next()){
            list_CodeEven.add(result.getString(1));
            list_nom.add(result.getString(2));
            list_show.add(result.getString(2));
        }
        list_nom = Transform(list_nom);
        list_show = Transform(list_show);
        Close();
        result.close();
        
        if(list_CodeEven.size() != 0)return true;
        else return false;
    }
    
    //RECUPERE LE CODE MAXIMUM
    public String GetLastCodeEven()
    throws SQLException{
        String codeEven = null;
        ResultSet result = null;
        
        result = RequeteSelect(result,"select max(codeEven) from Even");
        while (result.next()){
            codeEven = result.getString(1);
        }
        
        Close();
        result.close();
        
        return codeEven;
    }
    
    //RECUPERE LE NOM DE l'EVEN EN FONCTION DU CODE GARE
    public String GetnomEven(String codeGare)
    throws SQLException{
        
        String nomEven = "";
        ResultSet result = null;
        
        result = RequeteSelect(result,"select nom from Even INNER JOIN gares_has_even ON (even.codeEven = gares_has_even.codeEven) WHERE gares_has_even.codeGare = "+codeGare);
        while (result.next()){
            nomEven = result.getString(1);
        }
        nomEven = Transform(nomEven);
        Close();
        result.close();
        
        return nomEven;
    }
    
    //RECUPERE LE CODE DE l'EVEN EN FONCTION DU CODE GARE
    public String GetcodeEven(String codeGare)
    throws SQLException{
        
        String codeEven = "";
        ResultSet result = null;
        
        result = RequeteSelect(result,"select even.codeEven from Even INNER JOIN gares_has_even ON (even.codeEven = gares_has_even.codeEven) WHERE gares_has_even.codeGare = "+codeGare);
        while (result.next()){
            codeEven = result.getString(1);
        }
        
        Close();
        result.close();
        
        return codeEven;
    }
}


