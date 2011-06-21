package Models.ETUDES.Gares.Link;

import Models.TOOLS.Tools;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

//@author RABALLAND Romain
//@version v3.0

public class LinkDAOGare_Even extends Tools {
    
    //DECLARATION DES LISTES
    public ArrayList list_codeGare;
    public ArrayList list_codeEven;
    public String nomEven = "";
    public String nomGare = "";
    
    //DECLARATION DU CONSTRUCTEUR
    public LinkDAOGare_Even(DataSource ds){
        super(ds);
    }
    
    //RECUPERATION DU NOM EN FONCTION DU CODE
    public void GetnomEven(String codeEven)
    throws SQLException{
        ResultSet result = null;

            result = RequeteSelect(result,"select nom from Even where codeEven="+codeEven+"");
            while (result.next()){
                nomEven = result.getString(1);
            }

            Close();
            result.close();
        
    }
    
    //RECUPERATION DU NOM EN FONCTION DU CODE
    public void GetnomGare(String codeGare)throws SQLException{
        ResultSet result = null;

            result = RequeteSelect(result,"select gare from Gares where codeGare="+codeGare+"");
            while (result.next()){
                nomGare = result.getString(1);
            }

            Close();
            result.close();
        
    }
    
    //INITIALISATION DES DONNEES
    public void SetLink(String codeGare, String codeEven)
    throws SQLException{
        list_codeGare = new ArrayList();
        list_codeEven = new ArrayList();
        ResultSet result = null;

            if(codeEven == null){
                result = RequeteSelect(result,"select codeEven from gares_has_even where codeGare="+codeGare+"");
                while (result.next()){
                    list_codeEven.add(result.getString(1));
                }
            } else if(codeGare == null){
                result = RequeteSelect(result,"select codeGare from gares_has_even where codeEven="+codeEven+"");
                while (result.next()){
                    list_codeGare.add(result.getString(1));
                }
            }

            Close();
            result.close();
        
    }
    
    //AJOUT D'UN NOUVEL ELEMENT DANS LA BASE
    public void NewLink(String codeGare,String codeEven)
    throws SQLException{

            RequeteUpdate("INSERT INTO `gares_has_even`(`codeGare`,`codeEven`) VALUES ('"+codeGare+"','"+codeEven+"')");

    }
    
    //SUPPRESSION D'UN ELEEMENT DANS LA BASE
    public void DeleteLink(String codeGare,String codeEven)
    throws SQLException{

            RequeteUpdate("DELETE FROM `gares_has_even` WHERE codeGare="+codeGare+" AND codeEven="+codeEven+"");

    }
}

