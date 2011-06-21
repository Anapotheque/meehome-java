package Models.TRAVAUX;

import Models.TOOLS.Tools;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

//RABALLAND Romain v3.0
public class CommandesDAO extends Tools {
    
    /** Contructeur*/
    public CommandesDAO(DataSource ds) {
        super(ds);
    }
    
    public ArrayList list = null;
    public ArrayList listCode = null;
    public ArrayList listNomEntreprise = null;
    public ArrayList listNumCommande = null;
    public ArrayList list_Titre = null;
    public ArrayList list_Size = null;
    
    public String codeCommande = "";
    
    public void SetList(){
        
        list_Titre = new ArrayList();
        list_Titre.add("NUMERO COMMANDE");
        list_Titre.add("ENTREPRISES");
        
        list_Size = new ArrayList();
        list_Size.add("50%");
        list_Size.add("50%");
    }
    
    public boolean Set()
    throws SQLException{
        
        list = new ArrayList();
        listCode = new ArrayList();
        listNomEntreprise = new ArrayList();
        listNumCommande = new ArrayList();
        
        ResultSet result = null;
        
        SetList();
        
        String requete = "SELECT CodeCommande, NumeroCommande, entreprises.entreprise " +
                "FROM Commandes INNER JOIN entreprises ON (Commandes.CodeEntreprise = entreprises.codeEntreprise) " +
                "ORDER BY NumeroCommande";
        result = RequeteSelect(result,requete);
        
        while (result.next()){
            listCode.add(result.getString(1));
            listNumCommande.add(result.getString(2));
            listNomEntreprise.add(result.getString(3));
            list.add(result.getString(2));
            list.add(result.getString(3));
        }
        
        list = Transform(list);
        
        Close();
        result.close();
        return list.size()!=0?true:false;
        
    }
    
    public boolean GetCommande(String codeCommande)
    throws SQLException{
        
        list = new ArrayList();
        ResultSet result = null;
        
        String requete = "SELECT * " +
                "FROM Commandes " +
                "WHERE CodeCommande = "+codeCommande+" "+
                "ORDER BY NumeroCommande ";
        result = RequeteSelect(result,requete);
        
        while (result.next()){
            list.add(result.getString(2));
            list.add(result.getString(3));
            list.add(result.getString(4));
            list.add(result.getString(5));
            list.add(result.getString(6));
            list.add(result.getString(7));
            list.add(result.getString(8));
            list.add(result.getString(9));
            list.add(result.getString(10));
            list.add(result.getString(11));
            list.add(result.getString(12));
            list.add(result.getString(13));
            list.add(result.getString(14));
            list.add(result.getString(15));
            list.add(result.getString(16));
            list.add(result.getString(17));
            list.add(result.getString(18));
            list.add(result.getString(19));
            list.add(result.getString(20));
            list.add(result.getString(21));
            list.add(result.getString(22));
            list.add(result.getString(23));
            list.add(result.getString(24));
            list.add(result.getString(25));
            list.add(result.getString(26));
            list.add(result.getString(27));
            list.add(result.getString(28));
            list.add(result.getString(29));
            list.add(result.getString(30));
            list.add(result.getString(31));
            list.add(result.getString(32));
            list.add(result.getString(33));
        }
        
        list = Transform(list);
        
        Close();
        result.close();
        return list.size()!=0?true:false;
    }
    
        public boolean GetPourcentPDF(String codeCommande)
    throws SQLException{
        
        list = new ArrayList();
        ResultSet result = null;
        
        String requete = "SELECT * " +
                "FROM Commandes " +
                "WHERE CodeCommande = "+codeCommande+" "+
                "ORDER BY NumeroCommande ";
        result = RequeteSelect(result,requete);
        
        while (result.next()){
            list.add(result.getString(5));
            list.add(result.getString(7));
            list.add(result.getString(9));
            list.add(result.getString(11));
            list.add(result.getString(13));
            list.add(result.getString(15));
            list.add(result.getString(17));
            list.add(result.getString(19));
            list.add(result.getString(21));
            list.add(result.getString(23));
            list.add(result.getString(25));
            list.add(result.getString(27));
            list.add(result.getString(29));
            list.add(result.getString(31));
            list.add(result.getString(33));
        }
        
        list = Transform(list);
        
        Close();
        result.close();
        return list.size()!=0?true:false;
    }
    
    public boolean GetPrixPDF(String codeCommande)
    throws SQLException{
        
        list = new ArrayList();
        ResultSet result = null;
        
        String requete = "SELECT * " +
                "FROM Commandes " +
                "WHERE CodeCommande = "+codeCommande+" "+
                "ORDER BY NumeroCommande ";
        result = RequeteSelect(result,requete);
        
        while (result.next()){
            list.add(result.getString(4));
            list.add(result.getString(6));
            list.add(result.getString(8));
            list.add(result.getString(10));
            list.add(result.getString(12));
            list.add(result.getString(14));
            list.add(result.getString(16));
            list.add(result.getString(18));
            list.add(result.getString(20));
            list.add(result.getString(22));
            list.add(result.getString(24));
            list.add(result.getString(26));
            list.add(result.getString(28));
            list.add(result.getString(30));
            list.add(result.getString(32));
        }
        
        list = Transform(list);
        
        Close();
        result.close();
        return list.size()!=0?true:false;
    }
    
    public void New(ArrayList list)
    throws SQLException{
        list = DetectionCaractere(list);
        RequeteUpdate("INSERT INTO Commandes VALUES (null,'"+list.get(0)+"','"+list.get(1)+"','"+list.get(2)+"','"+list.get(3)+"','"+list.get(4)+"','"+list.get(5)+"','"+list.get(6)+"','"+list.get(7)+"','"+list.get(8)+"','"+list.get(9)+"','"+list.get(10)+"','"+list.get(11)+"','"+list.get(12)+"','"+list.get(13)+"','"+list.get(14)+"','"+list.get(15)+"','"+list.get(16)+"','"+list.get(17)+"','"+list.get(18)+"','"+list.get(19)+"','"+list.get(20)+"','"+list.get(21)+"','"+list.get(22)+"','"+list.get(23)+"','"+list.get(24)+"','"+list.get(25)+"','"+list.get(26)+"','"+list.get(27)+"','"+list.get(28)+"','"+list.get(29)+"','"+list.get(30)+"','"+list.get(31)+"')");
    }
    
    public void Mod(ArrayList list)
    throws SQLException{
        list = DetectionCaractere(list);
        RequeteUpdate("UPDATE Commandes SET `NumeroCommande` = '"+list.get(0)+"',`CodeEntreprise` = '"+list.get(1)+"',`Etablissement1` = '"+list.get(2)+"',`Etablissement2` = '"+list.get(3)+"',`Modification1` = '"+list.get(4)+"',`Modification2` = '"+list.get(5)+"',`Traitement1` = '"+list.get(6)+"',`Traitement2` = '"+list.get(7)+"',`Teintage1` = '"+list.get(8)+"',`Teintage2` = '"+list.get(9)+"',`Bordage1` = '"+list.get(10)+"',`Bordage2` = '"+list.get(11)+"',`Helio1` = '"+list.get(12)+"',`Helio2` = '"+list.get(13)+"',`Photocopie1` = '"+list.get(14)+"',`Photocopie2` = '"+list.get(15)+"',`CC1-1` = '"+list.get(16)+"',`CC1-2` = '"+list.get(17)+"',`CC2-1` = '"+list.get(18)+"',`CC2-2` = '"+list.get(19)+"',`CC3-1` = '"+list.get(20)+"',`CC3-2` = '"+list.get(21)+"',`CC1` = '"+list.get(22)+"',`CC2` = '"+list.get(23)+"',`DAO1-1` = '"+list.get(24)+"',`DAO1-2` = '"+list.get(25)+"',`DAO2-1` = '"+list.get(26)+"',`DAO2-2` = '"+list.get(27)+"',`DAO3-1` = '"+list.get(28)+"',`DAO3-2` = '"+list.get(29)+"',`Supplement1` = '"+list.get(30)+"',`Supplement2` = '"+list.get(31)+"' WHERE `codeCommande` ="+codeCommande);
    }
    
    public String GetNumCommande(String code)
    throws SQLException{
        String string = "";
        ResultSet result = null;
        
        result = RequeteSelect(result,"SELECT NumeroCommande FROM Commandes WHERE CodeCommande = "+code);
        while (result.next()){
            string = result.getString(1);
        }
        
        Close();
        result.close();
        
        return string;
    }
    
    public String GetEntrepriseCommande(String code)
    throws SQLException{
        String string = "";
        ResultSet result = null;
        
        result = RequeteSelect(result,"SELECT entreprises.entreprise FROM Commandes INNER JOIN Entreprises ON Commandes.CodeEntreprise = Entreprises.CodeEntreprise WHERE Commandes.CodeCommande = "+code);
        while (result.next()){
            string = result.getString(1);
        }
        
        Close();
        result.close();
        
        return string;
    }
    
    public String GetLastNumOE()
    throws SQLException{
        
        ResultSet result = null;
        String code = null;
        
        result = RequeteSelect(result,"SELECT max(NumOE) FROM travaux");
        
        while (result.next()){
            code = result.getString(1);
        }
        
        Close();
        result.close();
        
        if(code == null)
            code = "0";
        
        return code;
        
    }
    
}

