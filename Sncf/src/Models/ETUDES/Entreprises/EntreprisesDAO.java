package Models.ETUDES.Entreprises;

import Models.TOOLS.Tools;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

//@author RABALLAND Romain
//@version v3.0

public class EntreprisesDAO extends Tools {
    
    //DECLARATION DES LISTES
    public ArrayList list_CodeEntreprise;
    public ArrayList list_CodeEntreprise_forshow;
    public ArrayList list_Entreprise;
    public ArrayList list_Batiment;
    public ArrayList list_Rue;
    public ArrayList list_LieuDit;
    public ArrayList list_CodePostal;
    public ArrayList list_Ville;
    public ArrayList list_Specialite;
    public ArrayList list_Telephone;
    public ArrayList list_Fax;
    public ArrayList list_Correspondant;
    public ArrayList list_Memo;
    public ArrayList list_Actif;
    public ArrayList list_show;
    public ArrayList list_New;
    public ArrayList list_Mod;
    public ArrayList list_NewMod_taille;
    public ArrayList list_NewMod_tailleMax;
    public ArrayList list_Value;
    public ArrayList list_Ressource;
    public ArrayList list_TitreShow;
    public ArrayList list_SizeTitre;
    public ArrayList list_Search;
    
    //TAILLE D'AFFICHAGE POUR PDF
    public float[] widthsShow =       {0.15f,0.15f,0.15f,0.08f,0.08f,0.14f,0.25f};
    
    //DECLARATION DU CONSTRUCTEUR
    public EntreprisesDAO(DataSource ds){
        super(ds);
        SetList();
    }
    
    //INITIALISATION DES DONNEES
    public void SetList(){
        
        list_New = new ArrayList();
        list_NewMod_taille = new ArrayList();
        list_NewMod_tailleMax = new ArrayList();
        list_Value = new ArrayList();
        list_Ressource = new ArrayList();
        list_TitreShow = new ArrayList();
        list_SizeTitre = new ArrayList();
        
        list_Ressource.add("code entreprise");
        list_Ressource.add("entreprise");
        list_Ressource.add("batiment");
        list_Ressource.add("rue");
        list_Ressource.add("lieu dit");
        list_Ressource.add("code postal");
        list_Ressource.add("ville");
        list_Ressource.add("specialite");
        list_Ressource.add("telephone");
        list_Ressource.add("fax");
        list_Ressource.add("correspondant");
        list_Ressource.add("observations");
        list_Ressource.add("actif");
        
        list_TitreShow.add("entreprise");
        list_TitreShow.add("ville");
        list_TitreShow.add("specialite");
        list_TitreShow.add("tel");
        list_TitreShow.add("fax");
        list_TitreShow.add("correspondant");
        list_TitreShow.add("observations");
        
        list_SizeTitre.add("14%");
        list_SizeTitre.add("14%");
        list_SizeTitre.add("14%");
        list_SizeTitre.add("14%");
        list_SizeTitre.add("14%");
        list_SizeTitre.add("14%");
        list_SizeTitre.add("30%");
        
        list_New.add("entreprise");
        list_New.add("batiment");
        list_New.add("rue");
        list_New.add("lieudit");
        list_New.add("codePostal");
        list_New.add("ville");
        list_New.add("specialite");
        list_New.add("telephone");
        list_New.add("fax");
        list_New.add("correspondant");
        list_New.add("memo");
        list_New.add("actif");
        
        list_NewMod_taille.add("145px");
        list_NewMod_taille.add("145px");
        list_NewMod_taille.add("145px");
        list_NewMod_taille.add("145px");
        list_NewMod_taille.add("40px");
        list_NewMod_taille.add("145px");
        list_NewMod_taille.add("145px");
        list_NewMod_taille.add("100px");
        list_NewMod_taille.add("100px");
        list_NewMod_taille.add("145px");
        list_NewMod_taille.add("145px");
        list_NewMod_taille.add("145px");
        
        list_NewMod_tailleMax.add("300");
        list_NewMod_tailleMax.add("300");
        list_NewMod_tailleMax.add("300");
        list_NewMod_tailleMax.add("300");
        list_NewMod_tailleMax.add("5");
        list_NewMod_tailleMax.add("300");
        list_NewMod_tailleMax.add("300");
        list_NewMod_tailleMax.add("10");
        list_NewMod_tailleMax.add("10");
        list_NewMod_tailleMax.add("300");
        list_NewMod_tailleMax.add("300");
        list_NewMod_tailleMax.add("300");
        
        list_Value.add("A REMPLIR");
        list_Value.add("");
        list_Value.add("");
        list_Value.add("");
        list_Value.add("");
        list_Value.add("");
        list_Value.add("Etudes de signalisation");
        list_Value.add("");
        list_Value.add("");
        list_Value.add("");
        list_Value.add("");
        list_Value.add("");
        list_Value.add("");
    }
    
    //INITIALISATION DES DONNEES DE L'OBJET
    public void Set()
    throws SQLException{
        
        list_CodeEntreprise = new ArrayList();
        list_CodeEntreprise_forshow = new ArrayList();
        list_Entreprise = new ArrayList();
        list_Batiment = new ArrayList();
        list_Rue = new ArrayList();
        list_LieuDit = new ArrayList();
        list_CodePostal = new ArrayList();
        list_Ville = new ArrayList();
        list_Specialite = new ArrayList();
        list_Telephone = new ArrayList();
        list_Fax = new ArrayList();
        list_Correspondant = new ArrayList();
        list_Memo = new ArrayList();
        list_Actif = new ArrayList();
        list_show = new ArrayList();
        
        ResultSet result = null;
        
        result = RequeteSelect(result,"select * from Entreprises where entreprise NOT LIKE'%ZZ%' order by 'entreprise'");
        while (result.next()){
            list_CodeEntreprise.add(result.getString(1));
            list_Entreprise.add(result.getString(2));
            list_Batiment.add(result.getString(3));
            list_Rue.add(result.getString(4));
            list_LieuDit.add(result.getString(5));
            list_CodePostal.add(result.getString(6));
            list_Ville.add(result.getString(7));
            list_Specialite.add(result.getString(8));
            list_Telephone.add(result.getString(9));
            list_Fax.add(result.getString(10));
            list_Correspondant.add(result.getString(11));
            list_Memo.add(result.getString(12));
            list_Actif.add(result.getString(13));
            if(!result.getString(1).equals("-1")){
                list_CodeEntreprise_forshow.add(result.getString(1));
                list_show.add(result.getString(2));
                list_show.add(result.getString(7));
                list_show.add(result.getString(8));
                list_show.add(result.getString(9));
                list_show.add(result.getString(10));
                list_show.add(result.getString(11));
                list_show.add(result.getString(12));
            }
        }
        list_Entreprise = Transform(list_Entreprise);
        list_Batiment = Transform(list_Batiment);
        list_Rue = Transform(list_Rue);
        list_LieuDit = Transform(list_LieuDit);
        list_Ville = Transform(list_Ville);
        list_Specialite = Transform(list_Specialite);
        list_Correspondant = Transform(list_Correspondant);
        list_Memo = Transform(list_Memo);
        list_Actif = Transform(list_Actif);
        list_show = Transform(list_show);
        Close();
        result.close();
        
    }
    
    //AJOUT D'UN NOUVEL ELEMENT DANS LA BASE
    public void New(ArrayList list)
    throws SQLException{
        list = DetectionCaractere(list);
        RequeteUpdate("INSERT INTO `Entreprises`(`codeEntreprise`,`entreprise`,`batiment`,`rue`,`lieudit`,`codePostal`,`ville`,`specialite`,`telephone`,`fax`,`correspondant`,`memo`,`actif`) VALUES (null,'"+list.get(0)+"','"+list.get(1)+"','"+list.get(2)+"','"+list.get(3)+"','"+list.get(4)+"','"+list.get(5)+"','"+list.get(6)+"','"+list.get(7)+"','"+list.get(8)+"','"+list.get(9)+"','"+list.get(10)+"','"+list.get(11)+"')");
        
    }
    
    //CHARGEMENT DES ELEMENTS POUR MODIFICATION
    public void InitMod(String code)
    throws SQLException{
        
        ResultSet result = null;
        list_Mod = new ArrayList();
        
        result = RequeteSelect(result,"select * from Entreprises WHERE `codeEntreprise` ="+code);
        while (result.next()){
            
            list_Mod.add(result.getString(2));
            list_Mod.add(result.getString(3));
            list_Mod.add(result.getString(4));
            list_Mod.add(result.getString(5));
            list_Mod.add(result.getString(6));
            list_Mod.add(result.getString(7));
            list_Mod.add(result.getString(8));
            list_Mod.add(result.getString(9));
            list_Mod.add(result.getString(10));
            list_Mod.add(result.getString(11));
            list_Mod.add(result.getString(12));
            list_Mod.add(result.getString(13));
        }
        list_Mod = Transform(list_Mod);
        
        Close();
        result.close();
        
    }
    
    //MODIFICATION D'UN ELEMENT DE LA BASE
    public void Mod(String codeEntreprise, ArrayList list)
    throws SQLException{
        list = DetectionCaractere(list);
        RequeteUpdate("UPDATE Entreprises SET entreprise= '"+list.get(0)+"',`batiment` = '"+list.get(1)+"',`rue` = '"+list.get(2)+"',`lieudit` = '"+list.get(3)+"', `codePostal` = '"+list.get(4)+"',`ville` = '"+list.get(5)+"',`specialite` = '"+list.get(6)+"',`telephone` = '"+list.get(7)+"', `fax` = '"+list.get(8)+"', `correspondant` = '"+list.get(9)+"', `memo` = '"+list.get(10)+"', `actif` = '"+list.get(11)+"' WHERE `codeEntreprise` ="+codeEntreprise);
        
    }
    
    //RECHERCHE D'UN ELEMENT DE LA BASE
    public boolean Search(String mot_clef, String filtre)
    throws SQLException{
        list_CodeEntreprise = new ArrayList();
        list_CodeEntreprise_forshow = new ArrayList();
        list_Search = new ArrayList();
        ResultSet result = null;
        mot_clef = DetectionCaractere(mot_clef);
        result = RequeteSelect(result,"select * from Entreprises where "+filtre+" LIKE '"+mot_clef+"%' order by 'entreprise'");
        while (result.next()){
            list_CodeEntreprise.add(result.getString(1));
            if(!result.getString(1).equals("-1")){
                list_CodeEntreprise_forshow.add(result.getString(1));
                list_Search.add(result.getString(2));
                list_Search.add(result.getString(7));
                list_Search.add(result.getString(8));
                list_Search.add(result.getString(9));
                list_Search.add(result.getString(10));
                list_Search.add(result.getString(11));
                list_Search.add(result.getString(12));
            }
        }
        list_Search = Transform(list_Search);
        list_CodeEntreprise_forshow = Transform(list_CodeEntreprise_forshow);
        Close();
        result.close();
        
        if(list_CodeEntreprise.size() != 0)return true;
        else return false;
    }
    
    //RECUPERATION DU NOM EN FONCTION DU CODE
    public String GetnomEntreprise(String codeEntreprise)throws SQLException{
        ResultSet result = null;
        String nomEntreprise = "";
        
        result = RequeteSelect(result,"select entreprise from Entreprises where codeEntreprise="+codeEntreprise+"");
        while (result.next()){
            nomEntreprise = result.getString(1);
        }
        nomEntreprise = Transform(nomEntreprise);
        Close();
        result.close();
        
        return nomEntreprise;
    }
    
    //RECUPERATION DU CODE EN FONCTION DU NOM
    public ArrayList GetcodeEntreprise(String name)throws SQLException{
        ArrayList list;
        list = new ArrayList();
        ResultSet result = null;
        
        result = RequeteSelect(result,"select codeEntreprise from Entreprises where entreprise LIKE '%"+name+"%'");
        while (result.next()){
            list.add(result.getString(1));
        }
        list = Transform(list);
        Close();
        result.close();
        
        return list;
    }
}
