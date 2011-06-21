package Models.ETUDES.Etudes.Link;

import Models.TOOLS.Tools;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

//@author RABALLAND Romain
//@version v3.0

public class LinkDAOEtudes_Affaires extends Tools {
    
    public ArrayList list_codeAffaires;
    public ArrayList list_codeEtudes;
    public String nomAffaire = "";
    public String nomEtude = "";

    //DECLARATION DU CONSTRUCTEUR
    public LinkDAOEtudes_Affaires(DataSource ds){
        super(ds);
    }

    //RECUPERATION DU NOM EN FONCTION DU CODE
    public void GetnomAffaire(String codeAffaire)
    throws SQLException{
        ResultSet result = null;

            result = RequeteSelect(result,"select nom from Affaires where codeAffaire="+codeAffaire+"");
            while (result.next()){
                nomAffaire = result.getString(1);
            }



            Close();
            result.close();
        
    }
    
    //RECUPERATION DU NOM EN FONCTION DU CODE
    public void GetindiceEtude(String codeEtude)
    throws SQLException{
        ResultSet result = null;

            result = RequeteSelect(result,"select indice from Etudes where codeEtude="+codeEtude+"");
            while (result.next()){
                nomEtude = result.getString(1);
            }



            Close();
            result.close();
        
    }
    
    //INITIALISATION DES DONNEES
    public void SetLink(String codeEtude, String codeAffaire)
    throws SQLException{
        ResultSet result = null;

            list_codeAffaires = new ArrayList();
            list_codeEtudes = new ArrayList();
            if(codeAffaire == null){
                result = RequeteSelect(result,"select codeAffaire from etudes_has_affaires where codeEtude="+codeEtude+"");
                while (result.next()){
                    list_codeAffaires.add(result.getString(1));
                }
            } else if(codeEtude == null){
                result = RequeteSelect(result,"select codeEtude from etudes_has_affaires where codeAffaire="+codeAffaire+"");
                while (result.next()){
                    list_codeEtudes.add(result.getString(1));
                }
            }

            Close();
            result.close();
        
    }
    
    //AJOUT D'UN NOUVEL ELEMENT DANS LA BASE
    public void NewLink(String codeEtude,String codeAffaire)
    throws SQLException{

            RequeteUpdate("INSERT INTO `etudes_has_affaires`(`codeEtude`,`codeAffaire`) VALUES ('"+codeEtude+"','"+codeAffaire+"')");

    }
    
    //SUPPRESSION D'UN ELEEMENT DANS LA BASE
    public void DeleteLink(String codeEtude,String codeAffaire)
    throws SQLException{

            RequeteUpdate("DELETE FROM `etudes_has_affaires` WHERE codeEtude="+codeEtude+" AND codeAffaire="+codeAffaire+"");

    }
    
    //RECUPERATION D'UN CODE EN FONCTION D'UN CODE
    public ArrayList GetcodeEtude(String code)
    throws SQLException{
        ArrayList codeEtude;
        codeEtude = new ArrayList();
        ResultSet result = null;

            result = RequeteSelect(result,"select codeEtude from etudes_has_affaires where codeAffaire LIKE'%"+code+"%'");
            while (result.next()){
                codeEtude.add(result.getString(1));
            }

            Close();
            result.close();
        
        return codeEtude;
    }
    
    //RECUPERATION D'UNE LISTE DE CODE EN FONCTION D'UNE LISTE DE CODE
    public ArrayList GetcodeAffaire(ArrayList code)
    throws SQLException{
        ArrayList codeAffaire;
        codeAffaire = new ArrayList();
        ResultSet result = null;

            for(int i = 0 ; i < code.size(); i++){
                result = RequeteSelect(result,"select codeAffaire from etudes_has_affaires where codeEtude="+code.get(i));
                while (result.next()){
                    codeAffaire.add(result.getString(1));
                }
            }

            Close();
            result.close();
        
        return codeAffaire;
    }
}

