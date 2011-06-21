package Models.ETUDES.Etudes.Link;

import Models.TOOLS.Tools;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

//@author RABALLAND Romain
//@version v3.0

public class LinkDAOEtudes_Agents extends Tools {
    
    public ArrayList list_codeAgents;
    public ArrayList list_codeEtudes;
    public String nomAgent = "";
    public String prenomAgent = "";
    public String nomEtude = "";
    
    //DECLARATION DU CONSTRUCTEUR
    public LinkDAOEtudes_Agents(DataSource ds){
        super(ds);
    }
    
    //RECUPERATION DU NOM EN FONCTION DU CODE
    public void GetnomAgent(String codeAgent)
    throws SQLException{
        ResultSet result = null;

            result = RequeteSelect(result,"select nom, prenom from Agents where codeAgent="+codeAgent+"");
            while (result.next()){
                nomAgent = result.getString(1);
                prenomAgent = result.getString(2);
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
    public void SetLink(String codeEtude, String codeAgent)
    throws SQLException{
        ResultSet result = null;

            list_codeAgents = new ArrayList();
            list_codeEtudes = new ArrayList();
            if(codeAgent == null){
                result = RequeteSelect(result,"select codeAgent from etudes_has_agents where codeEtude="+codeEtude+"");
                while (result.next()){
                    list_codeAgents.add(result.getString(1));
                }
            } else if(codeEtude == null){
                result = RequeteSelect(result,"select codeEtude from etudes_has_agents where codeAgent="+codeAgent+"");
                while (result.next()){
                    list_codeEtudes.add(result.getString(1));
                }
            }

            Close();
            result.close();
        
    }
    
    //AJOUT D'UN NOUVEL ELEMENT DANS LA BASE
    public void NewLink(String codeEtude,String codeAgent)
    throws SQLException{

            RequeteUpdate("INSERT INTO `etudes_has_agents`(`codeEtude`,`codeAgent`) VALUES ('"+codeEtude+"','"+codeAgent+"')");

    }
    
    //SUPPRESSION D'UN ELEEMENT DANS LA BASE
    public void DeleteLink(String codeEtude,String codeAgent)
    throws SQLException{

            RequeteUpdate("DELETE FROM `etudes_has_agents` WHERE codeEtude="+codeEtude+" AND codeAgent="+codeAgent+"");

    }
    
    //RECUPERATION D'UNE LISTE DE CODE EN FONCTION D'UNE LISTE DE CODE
    public ArrayList GetcodeEtude(ArrayList code)
    throws SQLException{
        ArrayList list;
        list = new ArrayList();
        ResultSet result = null;

            for(int i = 0 ; i < code.size(); i ++){
                result = RequeteSelect(result,"select codeEtude from etudes_has_agents where codeAgent="+code.get(i));
                while (result.next()){ //Si on a des information alors on enregistre le tout dans l'objet utilisateur sinon on est declaré comme intru'
                    list.add(result.getString(1));
                }
            }

            Close();
            result.close();
        
        return list;
    }
}

