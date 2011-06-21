package Models.ETUDES.Gares;

import Models.TOOLS.Tools;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

//@author RABALLAND Romain
//@version v3.0

public class GaresDAO extends Tools {
    
    //DECLARATION DES LISTES
    public ArrayList list_CodeGare;
    public ArrayList list_Gare;
    public ArrayList list_Km;
    public ArrayList list_Groupe;
    public ArrayList list_Infos;
    public ArrayList list_Ressource;
    public ArrayList list_show;
    public ArrayList list_Titre;
    public ArrayList list_SizeTitre;
    
    //TAILLE D'AFFICHAGE POUR PDF
    public float[] widthsShow =       {0.25f,0.1f,0.1f,0.55f};
    
    
    //DECLARATION DU CONSTRUCTEUR
    public GaresDAO(DataSource ds){
        super(ds);
        SetList();
    }
    
    //INITIALISATION DES DONNEES
    public void SetList(){
        
        list_Titre = new ArrayList();
        
        list_Titre.add("nom gare");
        list_Titre.add("ligne / km");
        list_Titre.add("groupe");
        list_Titre.add("observations");
        
        list_SizeTitre = new ArrayList();
        
        list_SizeTitre.add("30%");
        list_SizeTitre.add("12%");
        list_SizeTitre.add("12%");
        list_SizeTitre.add("30%");
        
        list_Ressource = new ArrayList();
        
        list_Ressource.add("nom gare");
        list_Ressource.add("ligne / km");
        list_Ressource.add("groupe");
        list_Ressource.add("observations");
        
    }
    
    //INITIALISATION DES DONNEES
    public void Set()
    throws SQLException{
        
        list_CodeGare = new ArrayList();
        list_Gare = new ArrayList();
        list_Km = new ArrayList();
        list_Groupe = new ArrayList();
        list_Infos = new ArrayList();
        
        list_show = new ArrayList();
        
        ResultSet result = null;
        
        result = RequeteSelect(result,"select * from Gares where gare NOT LIKE'%ZZ%' order by 'gare'");
        while (result.next()){
            list_CodeGare.add(result.getString(1));
            list_Gare.add(result.getString(2));
            list_Km.add(result.getString(3));
            list_Groupe.add(result.getString(4));
            list_Infos.add(result.getString(5));
            list_show.add(result.getString(2));
            list_show.add(result.getString(3));
            list_show.add(result.getString(4));
            list_show.add(result.getString(5));
        }
        list_show = Transform(list_show);
        list_Gare = Transform(list_Gare);
        list_Infos = Transform(list_Infos);
        Close();
        result.close();
        
    }
    
    //INITIALISATION DES DONNEES
    public void SetGareSE()
    throws SQLException{
        
        list_CodeGare = new ArrayList();
        list_Gare = new ArrayList();
        list_Km = new ArrayList();
        list_Groupe = new ArrayList();
        list_Infos = new ArrayList();
        
        list_show = new ArrayList();
        
        ResultSet result = null;
        
        result = RequeteSelect(result,"select * from Gares where gare NOT LIKE'%ZZ%' AND groupe = 'SE' order by 'gare'");
        while (result.next()){
            list_CodeGare.add(result.getString(1));
            list_Gare.add(result.getString(2));
            list_Km.add(result.getString(3));
            list_Groupe.add(result.getString(4));
            list_Infos.add(result.getString(5));
            list_show.add(result.getString(2));
            list_show.add(result.getString(3));
            list_show.add(result.getString(4));
            list_show.add(result.getString(5));
        }
        list_show = Transform(list_show);
        list_Gare = Transform(list_Gare);
        list_Infos = Transform(list_Infos);
        Close();
        result.close();
        
    }
    
    //AJOUT D'UN NOUVEL ELEMENT DANS LA BASE
    public void NewGare(ArrayList list)
    throws SQLException{
        list = DetectionCaractere(list);
        RequeteUpdate("INSERT INTO `Gares`(`codeGare`,`gare`,`km`,`groupe`,`infos`) VALUES (null,'"+list.get(0)+"','"+list.get(1)+"','"+list.get(2)+"','"+list.get(3)+"')");
        
    }
    
    //MODIFICATION D'UN ELEMENT DE LA BASE
    public void ModGare(String codeGare, ArrayList list)
    throws SQLException{
        list = DetectionCaractere(list);
        RequeteUpdate("UPDATE `Gares` SET `gare` = '"+list.get(0)+"',`km` = '"+list.get(1)+"',`groupe` = '"+list.get(2)+"',`infos` = '"+list.get(3)+"' WHERE `codeGare` ="+codeGare);
        
    }
    
    //RECHERCHE D'UN ELEMENT DE LA BASE
    public boolean Search(String mot_clef, String filtre)
    throws SQLException{
        
        list_CodeGare = new ArrayList();
        list_Gare = new ArrayList();
        list_Km = new ArrayList();
        list_Groupe = new ArrayList();
        list_Infos = new ArrayList();
        
        list_show = new ArrayList();
        mot_clef = DetectionCaractere(mot_clef);
        ResultSet result = null;
        
        result = RequeteSelect(result,"select * from Gares where "+filtre+" LIKE '%"+mot_clef+"%' order by 'codeGare'");
        while (result.next()){
            
            list_CodeGare.add(result.getString(1));
            list_Gare.add(result.getString(2));
            list_Km.add(result.getString(3));
            list_Groupe.add(result.getString(4));
            list_Infos.add(result.getString(5));
            
            list_show.add(result.getString(2));
            list_show.add(result.getString(3));
            list_show.add(result.getString(4));
            list_show.add(result.getString(5));
        }
        list_show = Transform(list_show);
        list_Gare = Transform(list_Gare);
        list_Infos = Transform(list_Infos);
        Close();
        result.close();
        
        if(list_CodeGare.size() != 0)return true;
        else return false;
    }
    
    //RECUPERE LE CODE MAX
    public String GetLastCodeGare()
    throws SQLException{
        String codeGare = null;
        ResultSet result = null;
        
        result = RequeteSelect(result,"select max(codeGare) from Gares");
        while (result.next()){
            codeGare = new String(result.getString(1));
        }
        
        Close();
        result.close();
        
        return codeGare;
    }
    
    //RECUPERE UN NOM EN FONCTION D'UN CODE
    public String GetnomGare(String code)
    throws SQLException{
        String nomGare = null;
        ResultSet result = null;
        
        result = RequeteSelect(result,"select gare from Gares where codeGare="+code+"");
        while (result.next()){
            nomGare = result.getString(1);
        }
        nomGare = Transform(nomGare);
        Close();
        result.close();
        
        return nomGare;
    }
    
    //RECUPERE UNE LISTE DE CODE EN FONCTION D'UN NOM
    public ArrayList GetcodeGare(String name)
    throws SQLException{
        ArrayList list_CodeGare;
        list_CodeGare = new ArrayList();
        ResultSet result = null;
        
        result = RequeteSelect(result,"select codeGare from Gares where gare LIKE '%"+name+"%' order by gare");
        while (result.next()){
            list_CodeGare.add(result.getString(1));
        }
        
        Close();
        result.close();
        
        return list_CodeGare;
    }
    
    //RECUPERE LES KM EN FONCTION D'UN CODE GARE
    public String GetKmGare(String codeGare)
    throws SQLException{
        String km ="";
        ResultSet result = null;
        
        result = RequeteSelect(result,"select km from Gares where codeGare = "+codeGare);
        while (result.next()){
            km = result.getString(1);
        }
        
        Close();
        result.close();
        
        return km;
    }
}

