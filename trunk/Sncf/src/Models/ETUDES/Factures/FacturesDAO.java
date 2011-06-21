package Models.ETUDES.Factures;

import Models.TOOLS.Controleur.MegaControleur;
import Models.ETUDES.Entreprises.EntreprisesDAO;
import Models.TOOLS.Tools;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

//@author RABALLAND Romain
//@version v3.0

public class FacturesDAO extends Tools {
    
    //DECLARATION DES LISTES
    public ArrayList list_CodeFacture;
    public ArrayList list_CodeEntreprise;
    public ArrayList list_Objet;
    public ArrayList list_ReferenceFacture;
    public ArrayList list_DateFacture;
    public ArrayList list_Imputation;
    public ArrayList list_Debit;
    public ArrayList list_Credit;
    public ArrayList list_Ressource;
    public ArrayList list_show;
    public ArrayList list_TitreShow;
    public ArrayList list_SizeTitre;
    
    String requete = null;
    
    public float[] widthsShow =       {0.25f,0.25f,0.15f,0.1f,0.1f,0.07f,0.07f};
    
    //DECLARATION DU CONSTRUCTEUR
    public FacturesDAO(DataSource ds){
        super(ds);
        SetList();
    }
    
    //INITIALISATION DES DONNEES
    public void SetList(){
        
        list_TitreShow = new ArrayList();
        list_SizeTitre = new ArrayList();
        
        list_TitreShow.add("entreprise");
        list_TitreShow.add("objet");
        list_TitreShow.add("facture");
        list_TitreShow.add("date");
        list_TitreShow.add("imputation");
        list_TitreShow.add("debit");
        list_TitreShow.add("credit");
        
        list_SizeTitre.add("30%");
        list_SizeTitre.add("10%");
        list_SizeTitre.add("10%");
        list_SizeTitre.add("10%");
        list_SizeTitre.add("10%");
        list_SizeTitre.add("15%");
        list_SizeTitre.add("15%");
        
        list_Ressource = new ArrayList();
        
        list_Ressource.add("entreprise");
        list_Ressource.add("objet");
        list_Ressource.add("facture");
        list_Ressource.add("date");
        list_Ressource.add("imputation");
        list_Ressource.add("debit");
        list_Ressource.add("credit");
        
    }
    
    //INITIALISATION DES DONNEES
    public void Set(HttpServletRequest request)
    throws SQLException{
        
        list_CodeFacture = new ArrayList();
        list_CodeEntreprise = new ArrayList();
        list_Objet = new ArrayList();
        list_ReferenceFacture = new ArrayList();
        list_DateFacture = new ArrayList();
        list_Imputation = new ArrayList();
        list_Debit = new ArrayList();
        list_Credit = new ArrayList();
        
        list_show = new ArrayList();
        ResultSet result = null;
        
        requete = "SELECT * FROM Factures";
        result = RequeteSelect(result,requete);
        while (result.next()){
            list_CodeFacture.add(result.getString(1));
            list_CodeEntreprise.add(result.getString(2));
            list_Objet.add(result.getString(3));
            list_ReferenceFacture.add(result.getString(4));
            list_DateFacture.add(result.getString(5));
            list_Imputation.add(result.getString(6));
            list_Debit.add(result.getString(7));
            list_Credit.add(result.getString(8));
        }
        requete = "SELECT entreprises.entreprise, factures.objet, factures.referencefacture, factures.datefacture, factures.imputation, factures.debit, credit " +
                "FROM entreprises RIGHT JOIN factures ON entreprises.codeEntreprise = factures.codeEntreprise";
        result = RequeteSelect(result,requete);
        while (result.next()){
            list_show.add(result.getString(1));
            list_show.add(result.getString(2));
            list_show.add(result.getString(3));
            list_show.add(result.getString(4));
            list_show.add(result.getString(5));
            list_show.add(result.getString(6));
            list_show.add(result.getString(7));
        }
        
        //DETECTION DE CARACTERES SPECIAUX
        list_Objet = Transform(list_Objet);
        list_ReferenceFacture = Transform(list_ReferenceFacture);
        list_show = Transform(list_show);
        list_Debit = Transform(list_Debit);
        list_Credit = Transform(list_Credit);
        
        Close();
        result.close();
        
    }
    
    //SUPPRIME TOUT LES ELEMENTS DE LA TABLE
    public void SupprAll()
    throws SQLException{
        
        RequeteUpdate("TRUNCATE TABLE Factures");
        
    }
    
    //AJOUT D'UN NOUVEL ELEMENT DANS LA BASE
    public void New(ArrayList list)
    throws SQLException{
        list = DetectionCaractere(list);
        RequeteUpdate("INSERT INTO `Factures`(`codeFacture`,`codeEntreprise`,`objet`,`referenceFacture`,`dateFacture`,`imputation`,`debit`,`credit`) " +
                "VALUES (null,'"+list.get(0)+"','"+list.get(1)+"','"+list.get(2)+"','"+list.get(3)+"','"+list.get(4)+"','"+list.get(5)+"','"+list.get(6)+"')");
        
    }
    
    //MODIFICATION D'UN ELEMENT DE LA BASE
    public void Mod(String codeFacture, ArrayList list)
    throws SQLException{
        list = DetectionCaractere(list);
        RequeteUpdate("UPDATE `Factures` SET `codeEntreprise` = '"+list.get(0)+"',`objet` = '"+list.get(1)+"',`referenceFacture` = '"+list.get(2)+"',`dateFacture` = '"+list.get(3)+"', `imputation` = '"+list.get(4)+"',`debit` = '"+list.get(5)+"',`credit` = '"+list.get(6)+"' WHERE `codeFacture` ="+codeFacture);
        
    }
    
    //RECHERCHE D'UN ELEMENT DE LA BASE
    public boolean Search(HttpServletRequest request, String mot_clef, String filtre)
    throws SQLException{
        ResultSet result = null;
        EntreprisesDAO entreprise = null;
        entreprise = MegaControleur.creerObjet(request, entreprise);
        
        list_CodeFacture = new ArrayList();
        list_CodeEntreprise = new ArrayList();
        list_Objet = new ArrayList();
        list_ReferenceFacture = new ArrayList();
        list_DateFacture = new ArrayList();
        list_Imputation = new ArrayList();
        list_Debit = new ArrayList();
        list_Credit = new ArrayList();
        
        list_show = new ArrayList();
        mot_clef = DetectionCaractere(mot_clef);
        
        requete = "SELECT * " +
                "FROM factures " +
                "WHERE codeEntreprise = ANY (select codeEntreprise from entreprises where "+filtre+" LIKE '%"+mot_clef+"%')";
        result = RequeteSelect(result,requete);
        while (result.next()){
            
            list_CodeFacture.add(result.getString(1));
            list_CodeEntreprise.add(result.getString(2));
            list_Objet.add(result.getString(3));
            list_ReferenceFacture.add(result.getString(4));
            list_DateFacture.add(result.getString(5));
            list_Imputation.add(result.getString(6));
            list_Debit.add(result.getString(7));
            list_Credit.add(result.getString(8));
        }
        
        requete = "SELECT entreprises.entreprise, factures.objet, factures.referencefacture, factures.datefacture, factures.imputation, factures.debit, credit " +
                "FROM entreprises INNER JOIN factures ON entreprises.codeEntreprise = factures.codeEntreprise "+
                "WHERE factures.codeEntreprise = ANY (select entreprises.codeEntreprise from entreprises where entreprises."+filtre+" LIKE '%"+mot_clef+"%')";
        result = RequeteSelect(result,requete);
        while (result.next()){
            list_show.add(result.getString(1));
            list_show.add(result.getString(2));
            list_show.add(result.getString(3));
            list_show.add(result.getString(4));
            list_show.add(result.getString(5));
            list_show.add(result.getString(6));
            list_show.add(result.getString(7));
        }
        list_Objet = Transform(list_Objet);
        list_ReferenceFacture = Transform(list_ReferenceFacture);
        list_show = Transform(list_show);
        list_Debit = Transform(list_Debit);
        list_Credit = Transform(list_Credit);
        Close();
        result.close();
        
        if(list_show.size() != 0)return true;
        else return false;
    }
}
