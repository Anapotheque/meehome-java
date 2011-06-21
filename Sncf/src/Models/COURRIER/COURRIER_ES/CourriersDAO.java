package Models.COURRIER.COURRIER_ES;

import Models.ETUDES.Affaires.AffairesDAO;
import Models.ETUDES.Etudes.EtudesDAO;
import Models.ETUDES.Even.EvenDAO;
import Models.ETUDES.Gares.GaresDAO;
import Models.TOOLS.Tools;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

//RABALLAND Romain v3.0
public class CourriersDAO extends Tools{
    
    public ArrayList list_CodeLettre = null;
    public ArrayList list_CodeCategorie = null;
    public ArrayList list_nomLettre = null;
    public ArrayList list_TexteLettre = null;
    public ArrayList list_TexteGras = null;
    public ArrayList list_Categorie = null;
    public ArrayList list_PJ = null;
    
    public String codeAgent = "";
    public String codeGare = "";
    public String codeEtude = "";
    public String codeCategorie = "";
    public String codeLettre = "";
    
    public String numeroSesco = "";
    public String dateIndice = "";
    public int nombreExemplaire = 0;
    public String destinataire = "";
    public String indice = "";
    public String Num_Affaire = "";
    public String objet = "";
    public String detail = "";
    public String texte = "";
    public String texteGras = "";
    public String PJ = "";
    public String copie = "";
    
    public boolean modification = false;
    
    
    /** Contructeur*/
    public CourriersDAO(DataSource ds) {
        super(ds);
    }
    
    public void SetDefautSetting(EvenDAO even, EtudesDAO etude, AffairesDAO affaire, GaresDAO gare){
        try {
            
            //DEFINITION DU NOMBRE D'EXEMLPAIRE PAR DEFAUT
            nombreExemplaire = 3;
            
            //DEFINITION DES DONNEES SELECTIONNEES PAR DEFAUT
            destinataire = InverseTransform(even.GetnomEven(codeGare).replaceAll("\u0092","'").replaceAll("\u0085",".").replaceAll("\u0093","\"").replaceAll("\u0094","\""));
            indice = InverseTransform(etude.GetindiceEtudeSolo(codeEtude).replaceAll("\u0092","'").replaceAll("\u0085",".").replaceAll("\u0093","\"").replaceAll("\u0094","\""));
            Num_Affaire = InverseTransform(etude.GetcodeAffaire(codeEtude)+" - "+gare.GetKmGare(codeGare).replaceAll("\u0093","\"").replaceAll("\u0094","\""));
            objet = InverseTransform(affaire.GetnomAffaire(etude.GetcodeAffaire(codeEtude)).replaceAll("\u0092","'").replaceAll("\u0085",".").replaceAll("\u0093","\"").replaceAll("\u0094","\""));
            detail = InverseTransform(etude.GetDetail(codeEtude).replaceAll("\u0092","'").replaceAll("\u0085",".").replaceAll("\u0093","\"").replaceAll("\u0094","\""));
            
            //TEXTE DE LA LETTRE PAR DEFAUT
            texte = InverseTransform(GetTexteLettre().replaceAll("\u0092","'").replaceAll("\u0085",".").replaceAll("\u0093","\"").replaceAll("\u0094","\"").replace("@Gare@",gare.GetnomGare(codeGare)).replace("@Indice@",etude.GetindiceEtudeSolo(codeEtude)).replace("@indice@",etude.GetindiceEtudeSolo(codeEtude)).replace("Ã?Â«","\"").replace("Ã?Â»","\""));
            texteGras = InverseTransform(GetTexteGras().replaceAll("\u0092","'").replaceAll("\u0085",".").replaceAll("\u0093","\"").replaceAll("\u0094","\"").replace("@Gare@",gare.GetnomGare(codeGare)).replace("@Indice@",etude.GetindiceEtudeSolo(codeEtude)).replace("@indice@",etude.GetindiceEtudeSolo(codeEtude)).replace("Ã?Â«","\"").replace("Ã?Â»","\""));
            
            //PIECE JOINTE DE LA LETTRE PAR DEFAUT
            PJ = InverseTransform(GetPJLettre().replaceAll("\u0092","'").replaceAll("\u0085",".").replaceAll("\u0093","\"").replaceAll("\u0094","\""));
            
            //COPIE DE LA LETTRE PAR DEFAUT
            copie = InverseTransform(GetCopieLettre().replaceAll("\u0092","'").replaceAll("\u0085",".").replaceAll("\u0093","\"").replaceAll("\u0094","\""));
            
            //NUMERO SESCO ET DATE INDICE NON DEFINI PAR DEFAUT
            numeroSesco = "";
            dateIndice = "";
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void SetDefautSettingGESTION(){
        try {
            
            //TEXTE DE LA LETTRE PAR DEFAUT
            texte = InverseTransform(GetTexteLettre().replaceAll("\u0092","'").replaceAll("\u0085",".").replaceAll("\u0093","\"").replaceAll("\u0094","\"").replace("Ã?Â«","\"").replace("Ã?Â»","\""));
            texteGras = InverseTransform(GetTexteGras().replaceAll("\u0092","'").replaceAll("\u0085",".").replaceAll("\u0093","\"").replaceAll("\u0094","\"").replace("Ã?Â«","\"").replace("Ã?Â»","\""));
            
            //PIECE JOINTE DE LA LETTRE PAR DEFAUT
            PJ = InverseTransform(GetPJLettre().replaceAll("\u0092","'").replaceAll("\u0085",".").replaceAll("\u0093","\"").replaceAll("\u0094","\""));
            
            //COPIE DE LA LETTRE PAR DEFAUT
            copie = InverseTransform(GetCopieLettre().replaceAll("\u0092","'").replaceAll("\u0085",".").replaceAll("\u0093","\"").replaceAll("\u0094","\""));
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public String GetnomCategorie(String code)
    throws SQLException{
        
        String texte = "";
        ResultSet result = null;
        
        result = RequeteSelect(result,"SELECT categorie FROM categories WHERE codeCategorie = "+code);
        while (result.next()){
            texte = result.getString(1);
        }
        texte = Transform(texte);
        Close();
        result.close();
        return texte;
    }
    
    public String GetnomLettre(String code)
    throws SQLException{
        
        String texte = "";
        ResultSet result = null;
        
        result = RequeteSelect(result,"SELECT nom FROM lettres WHERE codeLettre = "+code);
        while (result.next()){
            texte = result.getString(1);
        }
        texte = Transform(texte);
        Close();
        result.close();
        return texte;
    }
    
    public void SetCategorie()
    throws SQLException{
        
        list_CodeCategorie = new ArrayList();
        list_Categorie = new ArrayList();
        
        ResultSet result = null;
        
        result = RequeteSelect(result,"SELECT * FROM categories ORDER BY categorie");
        while (result.next()){
            list_CodeCategorie.add(result.getString(1));
            list_Categorie.add(result.getString(2));
        }
        list_Categorie = Transform(list_Categorie);
        Close();
        result.close();
        
    }
    
    public void SetLettre()
    throws SQLException{
        
        list_CodeLettre = new ArrayList();
        list_nomLettre = new ArrayList();
        
        ResultSet result = null;
        
        result = RequeteSelect(result,"SELECT codelettre, nom FROM lettres WHERE codecategorie = "+codeCategorie+" ORDER BY nom");
        
        while (result.next()){
            list_CodeLettre.add(result.getString(1));
            list_nomLettre.add(result.getString(2));
        }
        list_nomLettre = Transform(list_nomLettre);
        Close();
        result.close();
        
    }
    
    public void SetLettre(String code)
    throws SQLException{
        
        list_CodeLettre = new ArrayList();
        list_nomLettre = new ArrayList();
        
        ResultSet result = null;
        
        result = RequeteSelect(result,"SELECT codelettre, nom FROM lettres WHERE codecategorie = "+code+" ORDER BY nom");
        
        while (result.next()){
            list_CodeLettre.add(result.getString(1));
            list_nomLettre.add(result.getString(2));
        }
        list_nomLettre = Transform(list_nomLettre);
        Close();
        result.close();
        
    }
    
    public String GetTexteLettre()
    throws SQLException{
        
        String texte = "";
        ResultSet result = null;
        
        result = RequeteSelect(result,"SELECT texte FROM lettres WHERE codeLettre = "+codeLettre);
        while (result.next()){
            texte = result.getString(1);
        }
        texte = Transform(texte);
        Close();
        result.close();
        return texte;
    }
    
    public String GetTexteGras()
    throws SQLException{
        
        String texteGras = "";
        ResultSet result = null;
        
        result = RequeteSelect(result,"SELECT avertissement FROM lettres WHERE codeLettre = "+codeLettre);
        while (result.next()){
            texteGras = result.getString(1);
        }
        texteGras = Transform(texteGras);
        Close();
        result.close();
        return texteGras;
    }
    
    public String GetPJLettre()
    throws SQLException{
        
        String pj = "";
        ResultSet result = null;
        
        result = RequeteSelect(result,"SELECT pj FROM lettres WHERE codeLettre = "+codeLettre);
        while (result.next()){
            pj = result.getString(1);
        }
        pj = Transform(pj);
        Close();
        result.close();
        return pj;
    }
    
    public String GetCopieLettre()
    throws SQLException{
        
        String copie = "";
        ResultSet result = null;
        
        result = RequeteSelect(result,"SELECT copies FROM lettres WHERE codeLettre = "+codeLettre);
        while (result.next()){
            copie = result.getString(1);
        }
        copie = Transform(copie);
        Close();
        result.close();
        return copie;
    }
    
    public int TestAccuseReceptionLettre()
    throws SQLException{
        
        int test = 0;
        ResultSet result = null;
        
        result = RequeteSelect(result,"SELECT ar FROM lettres WHERE codeLettre = "+codeLettre);
        while (result.next()){
            test = Integer.parseInt(result.getString(1));
        }
        Close();
        result.close();
        return test;
    }
    
    public int TestSuiviLettre()
    throws SQLException{
        
        int test = 0;
        ResultSet result = null;
        
        result = RequeteSelect(result,"SELECT suivi FROM lettres WHERE codeLettre = "+codeLettre);
        while (result.next()){
            test = Integer.parseInt(result.getString(1));
        }
        
        Close();
        result.close();
        return test;
    }
    
    public boolean TestContenuCategorie(String code)
    throws SQLException{
        
        ResultSet result = null;
        int count = 0;
        
        result = RequeteSelect(result,"SELECT count(*) FROM Lettres WHERE codeCategorie = "+code);
        while (result.next()){
            count = Integer.parseInt(result.getString(1));
        }
        
        Close();
        result.close();
        return count==0?false:true;
    }
    
    
    public void EnregistrementTexte(String string)
    throws SQLException{
        
        string = DetectionCaractere(string);
        RequeteUpdate("UPDATE lettres SET texte = '"+string+"' WHERE codeLettre = "+codeLettre);
        
    }
    
    public void EnregistrementTexteGras(String string)
    throws SQLException{
        
        string = DetectionCaractere(string);
        RequeteUpdate("UPDATE lettres SET avertissement = '"+string+"' WHERE codeLettre = "+codeLettre);
        
    }
    
    public void EnregistrementCopies(String string)
    throws SQLException{
        
        string = DetectionCaractere(string);
        RequeteUpdate("UPDATE lettres SET copies = '"+string+"' WHERE codeLettre = "+codeLettre);
        
    }
    
    public void EnregistrementPJ(String string)
    throws SQLException{
        
        string = DetectionCaractere(string);
        RequeteUpdate("UPDATE lettres SET pj = '"+string+"' WHERE codeLettre = "+codeLettre);
        
    }
    
    public void EnregistrementAR(String string)
    throws SQLException{
        
        string = DetectionCaractere(string);
        RequeteUpdate("UPDATE lettres SET ar = '"+string+"' WHERE codeLettre = "+codeLettre);
        
    }
    
    public void EnregistrementSuivi(String string)
    throws SQLException{
        
        string = DetectionCaractere(string);
        RequeteUpdate("UPDATE lettres SET suivi = '"+string+"' WHERE codeLettre = "+codeLettre);
        
    }
    
    public void AjoutCategorie(String string)
    throws SQLException{
        
        string = DetectionCaractere(string);
        RequeteUpdate("INSERT INTO categories VALUES (null,'"+string+"')");
        
    }
    
    public void AjoutLettre(String string)
    throws SQLException{
        
        string = DetectionCaractere(string);
        RequeteUpdate("INSERT INTO lettres VALUES (null,"+codeCategorie+",'"+string+"','A REMPLIR','A REMPLIR','A REMPLIR',0,'A REMPLIR',0,'A REMPLIR','A REMPLIR')");
        
    }
    
    public void ModificationCategorie(String string, String code)
    throws SQLException{
        
        string = DetectionCaractere(string);
        RequeteUpdate("UPDATE categories SET categorie='"+string+"' WHERE codeCategorie = "+code);
        
    }
    
    public void ModificationLettre(String string, String code)
    throws SQLException{
        
        string = DetectionCaractere(string);
        RequeteUpdate("UPDATE lettres SET nom='"+string+"' WHERE codeLettre = "+code);
        
    }
}
