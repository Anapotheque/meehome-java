package Models.ETUDES.Affaires;
import Models.TOOLS.Tools;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.sql.*;

//@author RABALLAND Romain
//@version v3.0

public class AffairesDAO extends Tools {
    
    //DECLARATION DES LISTES
    public ArrayList list_CodeAffaire;
    public ArrayList list_Nom;
    public ArrayList list_Observations;
    public ArrayList list_Imputation;
    public ArrayList list_Compte_Etude;
    public ArrayList list_Sycomore;
    public ArrayList list_Parametrage;
    public ArrayList list_Ressource;
    
    public ArrayList list_show;
    public ArrayList list_Search;
    
    public ArrayList list_TitreShow;
    public ArrayList list_TitreSearch;
    public ArrayList list_SizeTitre_Show;
    public ArrayList list_SizeTitre_Search;
    
    public float[] widthsShow =       {0.04f,0.26f,0.11f,0.11f,0.13f,0.15f,0.20f};
    public float[] widthsSearch =     {0.15f,0.04f,0.20f,0.10f,0.10f,0.13f,0.13f,0.15f};
    
    String requete = null;
    
    //DECLARATION DU CONSTRUCTEUR
    public AffairesDAO(DataSource ds){
        super(ds);
        SetList();
    }
    
    //INITIALISATION DES DONNEES
    public void SetList(){
        
        list_TitreShow = new ArrayList();
        list_TitreSearch = new ArrayList();
        
        list_SizeTitre_Show = new ArrayList();
        list_SizeTitre_Search = new ArrayList();
        list_Ressource = new ArrayList();
        
        list_TitreShow.add("N°");
        list_TitreShow.add("nom");
        list_TitreShow.add("verif pt");
        list_TitreShow.add("etude se");
        list_TitreShow.add("parametrage");
        list_TitreShow.add("sycomore");
        list_TitreShow.add("observations");
        
        list_SizeTitre_Show.add("5%");
        list_SizeTitre_Show.add("30%");
        list_SizeTitre_Show.add("10%");
        list_SizeTitre_Show.add("10%");
        list_SizeTitre_Show.add("5%");
        list_SizeTitre_Show.add("15%");
        list_SizeTitre_Show.add("25%");
        
        list_TitreSearch.add("nom gare");
        list_TitreSearch.add("N°");
        list_TitreSearch.add("nom");
        list_TitreSearch.add("verif pt");
        list_TitreSearch.add("etude se");
        list_TitreSearch.add("parametrage");
        list_TitreSearch.add("sycomore");
        list_TitreSearch.add("observations");
        
        list_SizeTitre_Search.add("10%");
        list_SizeTitre_Search.add("5%");
        list_SizeTitre_Search.add("30%");
        list_SizeTitre_Search.add("10%");
        list_SizeTitre_Search.add("10%");
        list_SizeTitre_Search.add("5%");
        list_SizeTitre_Search.add("15%");
        list_SizeTitre_Search.add("15%");
        
        list_Ressource.add("numero affaire");
        list_Ressource.add("nom");
        list_Ressource.add("verif pt");
        list_Ressource.add("etude se");
        list_Ressource.add("parametrage");
        list_Ressource.add("sycomore");
        list_Ressource.add("observations");
        
    }
    
    //INITIALISATION DES DONNEES
    public void Set()
    throws SQLException{
        list_CodeAffaire = new ArrayList();
        list_Nom = new ArrayList();
        list_Observations = new ArrayList();
        list_Imputation = new ArrayList();
        list_Compte_Etude = new ArrayList();
        list_Sycomore = new ArrayList();
        list_Parametrage = new ArrayList();
        
        list_show = new ArrayList();
        
        ResultSet result = null;
        
        result = RequeteSelect(result,"select * from Affaires where nom NOT LIKE'%ZZ%' order by codeAffaire DESC");
        while (result.next()){
            list_CodeAffaire.add(result.getString(1));
            list_Nom.add(result.getString(2));
            list_Observations.add(result.getString(3));
            list_Imputation.add(result.getString(4));
            list_Compte_Etude.add(result.getString(5));
            list_Sycomore.add(result.getString(6));
            list_Parametrage.add(result.getString(7));
            
            list_show.add(result.getString(1));
            list_show.add(result.getString(2));
            list_show.add(result.getString(4));
            list_show.add(result.getString(5));
            list_show.add(result.getString(7));
            list_show.add(result.getString(6));
            list_show.add(result.getString(3));
        }
        
        //DETECTION DE CARACTERES SPECIAUX
        list_show = Transform(list_show);
        list_Nom = Transform(list_Nom);
        list_Observations = Transform(list_Observations);
        list_Imputation = Transform(list_Imputation);
        list_Compte_Etude = Transform(list_Compte_Etude);
        list_Sycomore = Transform(list_Sycomore);
        list_Parametrage = Transform(list_Parametrage);
        
        Close();
        result.close();
        
    }
    
    //AJOUT D'UN NOUVEL ELEMENT DANS LA BASE
    public void New(ArrayList list)
    throws SQLException{
        
        list = DetectionCaractere(list);
        RequeteUpdate("INSERT INTO `Affaires`(`codeAffaire`,`nom`,`observations`,`imputation`,`compteEtude`,`sycomore`,`parametrage`) VALUES ('"+list.get(0)+"','"+list.get(1)+"','"+list.get(2)+"','"+list.get(3)+"','"+list.get(4)+"','"+list.get(5)+"','"+list.get(6)+"')");
        
    }
    
    //MODIFICATION D'UN ELEMENT DE LA BASE
    public void Mod(String code, ArrayList list)
    throws SQLException{
        
        list = DetectionCaractere(list);
        RequeteUpdate("UPDATE `Affaires` SET `nom` = '"+list.get(0)+"',`observations` = '"+list.get(1)+"',`imputation` = '"+list.get(2)+"',`compteEtude` = '"+list.get(3)+"',`sycomore` = '"+list.get(4)+"',`parametrage` = '"+list.get(5)+"' WHERE `codeAffaire` ="+code);
        
    }
    
    //RECHERCHE D'UN ELEMENT DE LA BASE
    public boolean Search(HttpServletRequest request, String mot_clef, String filtre)
    throws SQLException{
        
        //CREATION ET INITIALISATION DES LISTES
        list_CodeAffaire = new ArrayList();
        list_Nom = new ArrayList();
        list_Observations = new ArrayList();
        list_Imputation = new ArrayList();
        list_Compte_Etude = new ArrayList();
        list_Sycomore = new ArrayList();
        list_Parametrage = new ArrayList();
        list_Search = new ArrayList();
        ResultSet result = null;
        
        mot_clef = DetectionCaractere(mot_clef);
        
        if(filtre.equals("gare"))
            requete = "SELECT Gares.Gare, affaires.codeAffaire, nom, imputation, compteEtude, parametrage, sycomore, observations FROM Affaires INNER JOIN ((Gares INNER JOIN Etudes ON Gares.CodeGare = Etudes.CodeGare) INNER JOIN etudes_has_affaires ON Etudes.CodeEtude = etudes_has_affaires.CodeEtude) ON Affaires.CodeAffaire = etudes_has_affaires.CodeAffaire WHERE Gares.Gare like '"+mot_clef+"%' ORDER BY Gares.Gare";
        else if(filtre.equals("codeAffaire"))
            requete = "select codeAffaire, nom, imputation, compteEtude, parametrage, sycomore, observations from Affaires where "+filtre+"='"+mot_clef+"' order by codeAffaire DESC";
        else if(filtre.equals("sycomore"))
            requete = "select codeAffaire, nom, imputation, compteEtude, parametrage, sycomore, observations from Affaires where "+filtre+" LIKE '%"+mot_clef+"%' order by codeAffaire DESC";
        result = RequeteSelect(result,requete);
        while (result.next()){
            
            
            
            if(filtre.equals("gare")){
                list_CodeAffaire.add(result.getString(2));
                list_Nom.add(result.getString(3));
                list_Imputation.add(result.getString(4));
                list_Compte_Etude.add(result.getString(5));
                list_Parametrage.add(result.getString(6));
                list_Sycomore.add(result.getString(7));
                list_Observations.add(result.getString(8));
                
                list_Search.add(result.getString(1));
                list_Search.add(result.getString(2));
                list_Search.add(result.getString(3));
                list_Search.add(result.getString(4));
                list_Search.add(result.getString(5));
                list_Search.add(result.getString(6));
                list_Search.add(result.getString(7));
                list_Search.add(result.getString(8));
            } else {
                
                list_CodeAffaire.add(result.getString(1));
                list_Nom.add(result.getString(2));
                list_Imputation.add(result.getString(3));
                list_Compte_Etude.add(result.getString(4));
                list_Parametrage.add(result.getString(5));
                list_Sycomore.add(result.getString(6));
                list_Observations.add(result.getString(7));
                
                list_Search.add(result.getString(1));
                list_Search.add(result.getString(2));
                list_Search.add(result.getString(3));
                list_Search.add(result.getString(4));
                list_Search.add(result.getString(5));
                list_Search.add(result.getString(6));
                list_Search.add(result.getString(7));
            }
            
            
        }
        
        //DETECTION DE CARACTERES SPECIAUX
        list_Search = Transform(list_Search);
        list_Nom = Transform(list_Nom);
        list_Observations = Transform(list_Observations);
        list_Imputation = Transform(list_Imputation);
        list_Compte_Etude = Transform(list_Compte_Etude);
        list_Sycomore = Transform(list_Sycomore);
        list_Parametrage = Transform(list_Parametrage);
        
        Close();
        result.close();
        
        if(list_Search.size() != 0)return true;
        else return false;
    }
    
    
    //RECUPERATION D'UNE LISTE D'AFFAIRE AVEC CODE AFFAIRE
    public void GetListCodeAffaire(String filtre)
    throws SQLException{
        
        list_CodeAffaire = new ArrayList();
        list_show = new ArrayList();
        
        ResultSet result = null;
        
        if(filtre.equals("ParCodeAffaire"))
            result = RequeteSelect(result,"SELECT codeAffaire, nom FROM affaires ORDER BY codeAffaire");
        else if(filtre.equals("ParObjets"))
            result = RequeteSelect(result,"SELECT nom, codeAffaire FROM affaires ORDER BY nom");
        while (result.next()){
            list_CodeAffaire.add(result.getString(1));
            list_show.add(result.getString(1));
            list_show.add(result.getString(2));
        }
        list_CodeAffaire = Transform(list_CodeAffaire);
        list_show = Transform(list_show);
    }
    
    //RECUPERATION DU NUMERO MAXIMUM D'UNE CLEF
    public int GetCodeAffaire()
    throws SQLException{
        int code = 0;
        ResultSet result = null;
        
        result = RequeteSelect(result,"select max(codeAffaire) from Affaires");
        while (result.next()){
            if(result.getString(1) == null)
                code = 0;
            else
                code = Integer.parseInt(result.getString(1))+1;
        }
        Close();
        result.close();
        
        return code;
    }
    
    //RECUPERATION D'UN NOM EN FONCTION D'UN CODE
    public String GetnomAffaire(String code)
    throws SQLException{
        String nom = null;
        ResultSet result = null;
        
        result = RequeteSelect(result,"select nom from affaires where codeAffaire="+code+"");
        while (result.next()){
            nom = result.getString(1);
        }
        nom =  Transform(nom);
        
        return nom;
    }
    
    //RECUPERATION D'UN NOM EN FONCTION D'UN CODE
    public String GetImputationAffaire(String code)
    throws SQLException{
        String imputation = null;
        ResultSet result = null;
        
        result = RequeteSelect(result,"select imputation from affaires where codeAffaire="+code+"");
        while (result.next()){
            imputation = result.getString(1);
        }
        imputation =  Transform(imputation);
        return imputation;
    }
        //RECUPERATION D'UN NOM EN FONCTION D'UN CODE
    public String GetCompteEtude(String code)
    throws SQLException{
        String compteEtude = null;
        ResultSet result = null;
        
        result = RequeteSelect(result,"select compteEtude from affaires where codeAffaire="+code+"");
        while (result.next()){
            compteEtude = result.getString(1);
        }
        compteEtude =  Transform(compteEtude);
        return compteEtude;
    }
}

