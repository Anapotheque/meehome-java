package Models.TRAVAUX;

import Models.TOOLS.Tools;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

//RABALLAND Romain v3.0
public class TravauxDAO extends Tools{
    
    /** Contructeur*/
    public TravauxDAO(DataSource ds) {
        super(ds);
    }
    
    
    public ArrayList list = null;
    public ArrayList listPDF = null;
    public ArrayList listCode = null;
    public ArrayList list_Titre = null;
    public ArrayList list_TitrePDF = null;
    public ArrayList list_Size = null;
    
    public String codeFiche = "";
    public String numOE = "";
    
    public float[] widthsShow =       {0.015f,0.01f,0.01f,0.01f,0.01f,0.01f,0.01f,0.01f,0.01f,0.01f,0.01f,0.01f,0.015f,0.015f,0.01f,0.01f};
    
    private void SetListPDF(){
        
        list_TitrePDF = new ArrayList();
        list_TitrePDF.add("Fiche de travail");
        list_TitrePDF.add("Tirages");
        list_TitrePDF.add("1 Pli DAO");
        list_TitrePDF.add("2 Pli DAO");
        list_TitrePDF.add("3 Pli DAO");
        list_TitrePDF.add("Photocopie");
        list_TitrePDF.add("C/C 1 Pli");
        list_TitrePDF.add("C/C 2 Pli");
        list_TitrePDF.add("C/C 3 Pli");
        list_TitrePDF.add("C/C > à 3 Pli");
        list_TitrePDF.add("Bord");
        list_TitrePDF.add("Supplement");
        list_TitrePDF.add("Etablissement");
        list_TitrePDF.add("Modification");
        list_TitrePDF.add("Traitement");
        list_TitrePDF.add("Teintage");
        
    }
    
    private void SetList(){
        
        list_Titre = new ArrayList();
        list_Titre.add("numero Fiche");
        list_Titre.add("Date Fiche");
        list_Titre.add("Code Affaire");
        list_Titre.add("Affaire");
        list_Titre.add("Agent");
        list_Titre.add("Code Commande");
        list_Titre.add("Facturation");
        
        list_Size = new ArrayList();
        list_Size.add("");
        list_Size.add("");
        list_Size.add("");
        list_Size.add("");
        list_Size.add("");
        list_Size.add("");
        list_Size.add("");
    }
    
    public boolean SetPDF(String numOE)
    throws SQLException{
        
        listPDF = new ArrayList();
        ResultSet result = null;
        
        SetListPDF();
        
        String requete = "SELECT CodeAffaire,CodeFiche,Helio,UnPliDAO,DeuxPliDAO,TroisPliDAO,KgCalques,UnPli,DeuxPli,TroisPli,SupTroisPli,bordage,Supplement,Etablissement,Modification,Traitement,teintage "+
                "FROM  travaux "+
                "WHERE NumOE = "+numOE+" " +
                "ORDER BY CodeAffaire";
        
        result = RequeteSelect(result,requete);
        
        while (result.next()){
            listPDF.add(result.getString(1));
            listPDF.add(result.getString(2));
            listPDF.add(result.getString(3));
            listPDF.add(result.getString(4));
            listPDF.add(result.getString(5));
            listPDF.add(result.getString(6));
            listPDF.add(result.getString(7));
            listPDF.add(result.getString(8));
            listPDF.add(result.getString(9));
            listPDF.add(result.getString(10));
            listPDF.add(result.getString(11));
            listPDF.add(result.getString(12));
            listPDF.add(result.getString(13));
            listPDF.add(result.getString(14));
            listPDF.add(result.getString(15));
            listPDF.add(result.getString(16));
            listPDF.add(result.getString(17));
        }
        
        if(list != null)
            list = Transform(list);
        
        Close();
        result.close();
        return list != null?true:false;
        
    }
    
    public boolean Set(boolean allTravaux)
    throws SQLException{
        
        list = new ArrayList();
        listCode = new ArrayList();
        ResultSet result = null;
        
        SetList();
        
        String requete = "";
        
        if(!allTravaux)
            requete = "SELECT A.CodeFiche, A.DateFiche, A.CodeAffaire, B.nom, E.nom, A.CodeCommande, A.Facturation "+
                    "FROM agents E INNER JOIN "+
                    "(travaux A INNER JOIN affaires B ON (A.CodeAffaire = B.codeAffaire)) ON (E.codeAgent = A.CodeAgent) "+
                    "WHERE NumOE = 0 " +
                    "ORDER BY CodeFiche DESC";
        else
            requete = "SELECT A.CodeFiche, A.DateFiche, A.CodeAffaire, B.nom, E.nom, A.CodeCommande, A.Facturation "+
                    "FROM agents E INNER JOIN "+
                    "(travaux A INNER JOIN affaires B ON (A.CodeAffaire = B.codeAffaire)) ON (E.codeAgent = A.CodeAgent) "+
                    "ORDER BY CodeFiche DESC";
        
        result = RequeteSelect(result,requete);
        
        while (result.next()){
            listCode.add(result.getString(1));
            list.add(result.getString(1));
            list.add(result.getString(2));
            list.add(result.getString(3));
            list.add(result.getString(4));
            list.add(result.getString(5));
            list.add(result.getString(6));
            list.add(result.getString(7));
        }
        
        list = Transform(list);
        
        Close();
        result.close();
        return list.size()!=0?true:false;
        
    }
    
    public void New(ArrayList list)
    throws SQLException{
        list = DetectionCaractere(list);
        RequeteUpdate("INSERT INTO Travaux VALUES (null,'"+list.get(0)+"','"+list.get(1)+"','"+list.get(2)+"','"+list.get(3)+"','"+list.get(4)+"','dd/mm/aaaa',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0)");
    }
    
    public void Mod(ArrayList list)
    throws SQLException{
        list = DetectionCaractere(list);
        RequeteUpdate("UPDATE Travaux SET `DateFiche` = '"+list.get(0)+"',`CodeAffaire` = '"+list.get(1)+"',`CodeAgent` = '"+list.get(2)+"',`CodeCommande` = '"+list.get(3)+"',`Facturation` = '"+list.get(4)+"' WHERE `CodeFiche` ="+codeFiche);
    }
    
    public void ModFiche(ArrayList list)
    throws SQLException{
        list = DetectionCaractere(list);
        RequeteUpdate("UPDATE Travaux SET `Livraison` = '"+list.get(0)+"',`Helio` = '"+list.get(1)+"',`UnPliDAO` = '"+list.get(2)+"',`DeuxPliDAO` = '"+list.get(3)+"',`TroisPliDAO` = '"+list.get(4)+"',`Supplement` = '"+list.get(5)+"',`KgCalques` = '"+list.get(6)+"',`UnPli` = '"+list.get(7)+"',`DeuxPli` = '"+list.get(8)+"',`TroisPli` = '"+list.get(9)+"',`SupTroisPli` = '"+list.get(10)+"',`bordage` = '"+list.get(11)+"',`Etablissement` = '"+list.get(12)+"',`Modification` = '"+list.get(13)+"',`Traitement` = '"+list.get(14)+"',`teintage` = '"+list.get(15)+"',`NumOE` = '"+list.get(16)+"' WHERE `CodeFiche` ="+codeFiche);
    }
    
    public void AttributionNumOE(ArrayList list)
    throws SQLException{
        list = DetectionCaractere(list);
        RequeteUpdate("UPDATE Travaux SET `NumOE` = "+list.get(0)+" WHERE `CodeCommande` = "+list.get(1)+" AND `NumOE` = 0");
    }
    
    //RECUPERE LE CODE MAXIMUM
    public int GetLastnumero()
    throws SQLException{
        int code = 0;
        ResultSet result = null;
        
        result = RequeteSelect(result,"select max(CodeFiche) from Travaux");
        while (result.next()){
            if(result.getString(1) == null)
                code = 0;
            else code = Integer.parseInt(new String(result.getString(1)))+1;
        }
        
        Close();
        result.close();
        
        return code;
    }
    
    public String GetNumeroAffaire(String code)
    throws SQLException{
        String string = "";
        ResultSet result = null;
        
        result = RequeteSelect(result,"SELECT CodeAffaire FROM Travaux WHERE CodeFiche = "+code);
        while (result.next()){
            string = result.getString(1);
        }
        
        Close();
        result.close();
        
        return string;
    }
    
    public String GetNumeroAgent(String code)
    throws SQLException{
        String string = "";
        ResultSet result = null;
        
        result = RequeteSelect(result,"SELECT CodeAgent FROM Travaux WHERE CodeFiche = "+code);
        while (result.next()){
            string = result.getString(1);
        }
        
        Close();
        result.close();
        
        return string;
    }
    
    public String GetNumeroCommande(String code)
    throws SQLException{
        String string = "";
        ResultSet result = null;
        
        result = RequeteSelect(result,"SELECT CodeCommande FROM Travaux WHERE CodeFiche = "+code);
        while (result.next()){
            string = result.getString(1);
        }
        
        Close();
        result.close();
        
        return string;
    }
    
    public String GetDateFiche(String code)
    throws SQLException{
        String string = "";
        ResultSet result = null;
        
        result = RequeteSelect(result,"SELECT DateFiche FROM Travaux WHERE CodeFiche = "+code);
        while (result.next()){
            string = result.getString(1);
        }
        
        Close();
        result.close();
        
        return string;
    }
    
    public String GetImputation(String code)
    throws SQLException{
        String string = "";
        ResultSet result = null;
        
        result = RequeteSelect(result,"SELECT Facturation FROM Travaux WHERE CodeFiche = "+code);
        while (result.next()){
            string = result.getString(1);
        }
        
        Close();
        result.close();
        
        return string;
    }
    
    public ArrayList GetInfosTravaux(String code)
    throws SQLException{
        ArrayList list = new ArrayList();
        ResultSet result = null;
        
        result = RequeteSelect(result,"SELECT Livraison, Helio, KgCalques, teintage, UnpliDAO, DeuxPliDAO, TroisPliDAO, Supplement, Etablissement, Modification, Traitement, bordage, UnPli, DeuxPli, TroisPli, SupTroisPli, NumOE FROM Travaux WHERE CodeFiche = "+code);
        while (result.next()){
            list.add(result.getString(1));
            list.add(result.getString(2));
            list.add(result.getString(3));
            
            if(result.getString(4) == null) list.add("0");
            else list.add(result.getString(4));
            
            list.add(result.getString(5));
            list.add(result.getString(6));
            list.add(result.getString(7));
            
            if(result.getString(8) == null) list.add("0");
            else list.add(result.getString(8));
            
            if(result.getString(9) == null) list.add("0");
            else list.add(result.getString(9));
            
            if(result.getString(10) == null) list.add("0");
            else list.add(result.getString(10));
            
            if(result.getString(11) == null) list.add("0");
            else list.add(result.getString(11));
            
            list.add(result.getString(12));
            list.add(result.getString(13));
            list.add(result.getString(14));
            list.add(result.getString(15));
            list.add(result.getString(16));
            list.add(result.getString(17));
        }
        
        Close();
        result.close();
        
        return list;
    }
}
