package Models.ETUDES.Fmr;

import Models.TOOLS.Controleur.MegaControleur;
import Models.ETUDES.Gares.GaresDAO;
import Models.TOOLS.Tools;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

//RABALLAND Romain v3.0

public class FmrDAO extends Tools {
    
    //DECLARATION DES LISTES
    public ArrayList list_CodeFmr;
    public ArrayList list_CodeGare;
    public ArrayList list_NumeroFmr;
    public ArrayList list_Etablissement;
    public ArrayList list_envoi;
    public ArrayList list_traitement;
    public ArrayList list_accord;
    public ArrayList list_incorporation;
    public ArrayList list_memo;
    public ArrayList list_show;
    public ArrayList list_Ressource;
    public ArrayList list_Titre;
    public ArrayList list_SizeTitre;
    
    String requete = null;
    
    public float[] widthsShow =       {0.05f,0.22f,0.14f,0.14f,0.12f,0.09f,0.13f,0.11f};
    
    //DECLARATION DU CONSTRUCTEUR
    public FmrDAO(DataSource ds){
        super(ds);
        SetList();
    }
    
    //INITIALISATION DES DONNEES
    public void SetList(){
        
        list_Titre = new ArrayList();
        list_SizeTitre = new ArrayList();
        
        list_Titre.add("N°");
        list_Titre.add("gare");
        list_Titre.add("etablissement");
        list_Titre.add("envoi");
        list_Titre.add("traitement");
        list_Titre.add("accord");
        list_Titre.add("incorporation");
        list_Titre.add("observations");
        
        list_SizeTitre.add("12%");
        list_SizeTitre.add("12%");
        list_SizeTitre.add("12%");
        list_SizeTitre.add("12%");
        list_SizeTitre.add("12%");
        list_SizeTitre.add("12%");
        list_SizeTitre.add("12%");
        list_SizeTitre.add("12%");
        
        list_Ressource = new ArrayList();
        
        list_Ressource.add("gare");
        list_Ressource.add("numero fmr");
        list_Ressource.add("etablissement");
        list_Ressource.add("envoi");
        list_Ressource.add("traitement");
        list_Ressource.add("accord");
        list_Ressource.add("incorporation");
        list_Ressource.add("observations");
        
    }
    
    //INITIALISATION DES DONNEES
    public void Set(HttpServletRequest request)
    throws SQLException{
        
        ResultSet result = null;
        
        list_CodeFmr = new ArrayList();
        list_CodeGare = new ArrayList();
        list_NumeroFmr = new ArrayList();
        list_Etablissement = new ArrayList();
        list_envoi = new ArrayList();
        list_traitement = new ArrayList();
        list_accord = new ArrayList();
        list_incorporation = new ArrayList();
        list_memo = new ArrayList();
        
        list_show = new ArrayList();
        
        
        requete = "SELECT * FROM Fmr";
        result = RequeteSelect(result,requete);
        while (result.next()){
            list_CodeFmr.add(result.getString(1));
            list_CodeGare.add(result.getString(2));
            list_NumeroFmr.add(result.getString(3));
            list_Etablissement.add(result.getString(4));
            list_envoi.add(result.getString(5));
            list_traitement.add(result.getString(6));
            list_accord.add(result.getString(7));
            list_incorporation.add(result.getString(8));
            list_memo.add(result.getString(9));
        }
        
        requete = "SELECT fmr.numeroFmr, gares.gare, fmr.etablissement, fmr.envoi, fmr.traitement, fmr.accord, fmr.incorporation, fmr.memo " +
                "FROM fmr LEFT JOIN gares ON gares.codeGare = fmr.codeGare";
        
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
        }
        
        list_Etablissement = Transform(list_Etablissement);
        list_memo = Transform(list_memo);
        list_show = Transform(list_show);
        Close();
        result.close();
    }
    
    //AJOUT D'UN NOUVEL ELEMENT DANS LA BASE
    public void NewFmr(ArrayList list)
    throws SQLException{
        list = DetectionCaractere(list);
        RequeteUpdate("INSERT INTO `Fmr`(`codeFmr`,`codeGare`,`numeroFmr`,`etablissement`,`envoi`,`traitement`,`accord`,`incorporation`,`memo`) VALUES (null,'"+list.get(0)+"','"+list.get(1)+"','"+list.get(2)+"','"+list.get(3)+"','"+list.get(4)+"','"+list.get(5)+"','"+list.get(6)+"','"+list.get(7)+"')");
        
    }
    
    //MODIFICATION D'UN ELEMENT DE LA BASE
    public void ModFmr(String codeFmr, ArrayList list)
    throws SQLException{
        list = DetectionCaractere(list);
        RequeteUpdate("UPDATE `Fmr` SET `codeGare` = '"+list.get(0)+"',`numeroFmr` = '"+list.get(1)+"',`etablissement` = '"+list.get(2)+"',`envoi` = '"+list.get(3)+"',`traitement` = '"+list.get(4)+"',`accord` = '"+list.get(5)+"', `incorporation` = '"+list.get(6)+"',`memo` = '"+list.get(7)+"' WHERE `codeFmr` ="+codeFmr);
        
    }
    
    //RECHERCHE D'UN ELEMENT DE LA BASE
    public boolean Search(HttpServletRequest request, String mot_clef, String filtre)
    throws SQLException{
        ResultSet result = null;
        GaresDAO gare = null;
        gare = MegaControleur.creerObjet(request, gare);
        
        list_CodeFmr = new ArrayList();
        list_CodeGare = new ArrayList();
        list_NumeroFmr = new ArrayList();
        list_Etablissement = new ArrayList();
        list_envoi = new ArrayList();
        list_traitement = new ArrayList();
        list_accord = new ArrayList();
        list_incorporation = new ArrayList();
        list_memo = new ArrayList();
        
        list_show = new ArrayList();
        
        mot_clef = DetectionCaractere(mot_clef);
        if(filtre.equals("gare"))
            requete = "SELECT * " +
                    "FROM fmr " +
                    "WHERE codeGare = ANY (select codeGare from gares where "+filtre+" LIKE '%"+mot_clef+"%')";
        else
            requete = "SELECT * " +
                    "FROM Fmr " +
                    "WHERE "+filtre+" LIKE '%"+mot_clef+"%' order by '"+filtre+"'";
        
        result = RequeteSelect(result,requete);
        
        while (result.next()){
            list_CodeFmr.add(result.getString(1));
            list_CodeGare.add(result.getString(2));
            list_NumeroFmr.add(result.getString(3));
            list_Etablissement.add(result.getString(4));
            list_envoi.add(result.getString(5));
            list_traitement.add(result.getString(6));
            list_accord.add(result.getString(7));
            list_incorporation.add(result.getString(8));
            list_memo.add(result.getString(9));
        }
        
        if(filtre.equals("gare"))
            requete = "SELECT fmr.numeroFmr, gares.gare, fmr.etablissement, fmr.envoi, fmr.traitement, fmr.accord, fmr.incorporation, fmr.memo " +
                    "FROM fmr LEFT JOIN gares ON gares.codeGare = fmr.codeGare "+
                    "WHERE fmr.codeGare = ANY (select gares.codeGare from gares where gares."+filtre+" LIKE '%"+mot_clef+"%')";
        else
            requete = "SELECT fmr.numeroFmr, gares.gare, fmr.etablissement, fmr.envoi, fmr.traitement, fmr.accord, fmr.incorporation, fmr.memo " +
                    "FROM fmr LEFT JOIN gares ON gares.codeGare = fmr.codeGare "+
                    "WHERE fmr."+filtre+" LIKE '%"+mot_clef+"%' order by fmr."+filtre;
        
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
        }
        list_Etablissement = Transform(list_Etablissement);
        list_memo = Transform(list_memo);
        list_show = Transform(list_show);
        Close();
        result.close();
        
        if(list_show.size() != 0)return true;
        else return false;
    }
    
    //RECUPERE LE CODE MAXIMUM
    public int GetLastnumeroFMR()
    throws SQLException{
        int codeFmr = 0;
        ResultSet result = null;
        
        result = RequeteSelect(result,"select max(numeroFMR) from Fmr");
        while (result.next()){
            if(result.getString(1) == null)
                codeFmr = 0;
            else codeFmr = Integer.parseInt(new String(result.getString(1)))+1;
        }
        
        Close();
        result.close();
        
        return codeFmr;
    }
}
