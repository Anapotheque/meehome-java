package Models.ETUDES.Agents;

import Models.TOOLS.Tools;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

//@author RABALLAND Romain
//@version v3.0

public class AgentsDAO extends Tools {
    
    //DECLARATION DES LISTES
    public ArrayList list_CodeAgent;
    public ArrayList list_Nom;
    public ArrayList list_Prenom;
    public ArrayList list_Initiales;
    public ArrayList list_Grade;
    public ArrayList list_Qualification;
    public ArrayList list_CP;
    public ArrayList list_TelephoneSncf;
    public ArrayList list_TelephonePtt;
    public ArrayList list_Mail;
    
    public ArrayList list_show;
    
    public ArrayList list_search;
    
    public ArrayList list_New;
    
    public ArrayList list_Mod;
    public ArrayList list_NewMod_taille;
    public ArrayList list_NewMod_tailleMax;
    
    public ArrayList list_Value;
    public ArrayList list_Ressource;
    public ArrayList list_TitreShow;
    public ArrayList list_SizeTitre;
    
    public float[] widthsShow = {0.1f, 0.1f, 0.1f, 0.08f,0.13f,0.09f,0.11f,0.12f,0.20f};
    
    //DECLARATION DU CONSTRUCTEUR
    public AgentsDAO(DataSource ds){
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
        
        list_TitreShow.add("nom");
        list_TitreShow.add("prenom");
        list_TitreShow.add("initiales");
        list_TitreShow.add("grade");
        list_TitreShow.add("qualification");
        list_TitreShow.add("cp");
        list_TitreShow.add("tel sncf");
        list_TitreShow.add("tel ptt");
        list_TitreShow.add("mail");
        
        list_SizeTitre.add("10%");
        list_SizeTitre.add("10%");
        list_SizeTitre.add("10%");
        list_SizeTitre.add("10%");
        list_SizeTitre.add("10%");
        list_SizeTitre.add("10%");
        list_SizeTitre.add("10%");
        list_SizeTitre.add("10%");
        list_SizeTitre.add("10%");
        
        list_Ressource.add("codeAgent");
        list_Ressource.add("nom");
        list_Ressource.add("prenom");
        list_Ressource.add("initiales");
        list_Ressource.add("grade");
        list_Ressource.add("qualification");
        list_Ressource.add("cp");
        list_Ressource.add("telephone sncf");
        list_Ressource.add("telephone ptt");
        list_Ressource.add("mail");
        
        list_New.add("nom");
        list_New.add("prenom");
        list_New.add("initiales");
        list_New.add("grade");
        list_New.add("qualification");
        list_New.add("cp");
        list_New.add("telephonesncf");
        list_New.add("telephoneptt");
        list_New.add("mail");
        
        list_NewMod_taille.add("150px");
        list_NewMod_taille.add("150px");
        list_NewMod_taille.add("40px");
        list_NewMod_taille.add("80px");
        list_NewMod_taille.add("40px");
        list_NewMod_taille.add("60px");
        list_NewMod_taille.add("60px");
        list_NewMod_taille.add("100px");
        list_NewMod_taille.add("150px");
        
        list_NewMod_tailleMax.add("30");
        list_NewMod_tailleMax.add("30");
        list_NewMod_tailleMax.add("4");
        list_NewMod_tailleMax.add("10");
        list_NewMod_tailleMax.add("5");
        list_NewMod_tailleMax.add("8");
        list_NewMod_tailleMax.add("8");
        list_NewMod_tailleMax.add("14");
        list_NewMod_tailleMax.add("30");
        
        list_Value.add("A REMPLIR");
        list_Value.add("A REMPLIR");
        list_Value.add("");
        list_Value.add("");
        list_Value.add("");
        list_Value.add("");
        list_Value.add("");
        list_Value.add("");
        list_Value.add("");
        
    }
    
    //INITIALISATION DES DONNEES
    public void Set()
    throws SQLException{
        
        list_CodeAgent = new ArrayList();
        list_Nom = new ArrayList();
        list_Prenom = new ArrayList();
        list_Initiales = new ArrayList();
        list_Grade = new ArrayList();
        list_Qualification = new ArrayList();
        list_CP = new ArrayList();
        list_TelephoneSncf = new ArrayList();
        list_TelephonePtt = new ArrayList();
        list_Mail = new ArrayList();
        
        list_show = new ArrayList();
        ResultSet result = null;
        
        
        result = RequeteSelect(result,"select * from Agents where nom NOT LIKE'%ZZ%' order by 'nom'");
        
        while (result.next()){
            
            list_CodeAgent.add(result.getString(1));
            list_Nom.add(result.getString(2));
            list_Prenom.add(result.getString(3));
            list_Initiales.add(result.getString(4));
            list_Grade.add(result.getString(5));
            list_Qualification.add(result.getString(6));
            list_CP.add(result.getString(7));
            list_TelephoneSncf.add(result.getString(8));
            list_TelephonePtt.add(result.getString(9));
            list_Mail.add(result.getString(10));
            
            list_show.add(result.getString(2));
            list_show.add(result.getString(3));
            list_show.add(result.getString(4));
            list_show.add(result.getString(5));
            list_show.add(result.getString(6));
            list_show.add(result.getString(7));
            list_show.add(result.getString(8));
            list_show.add(result.getString(9));
            list_show.add(result.getString(10));
            
        }
        
        //DETECTION DE CARACTERES SPECIAUX
        list_show = Transform(list_show);
        list_Nom = Transform(list_Nom);
        list_Prenom = Transform(list_Prenom);
        list_Initiales = Transform(list_Initiales);
        list_Grade = Transform(list_Grade);
        list_Qualification = Transform(list_Qualification);
        list_CP = Transform(list_CP);
        list_TelephoneSncf = Transform(list_TelephoneSncf);
        list_TelephonePtt = Transform(list_TelephonePtt);
        list_Mail = Transform(list_Mail);
        
        Close();
        result.close();
        
    }
    
    //AJOUT D'UN NOUVEL ELEMENT DANS LA BASE
    public void New(ArrayList list)
    throws SQLException{
        
        list = DetectionCaractere(list);
        RequeteUpdate("INSERT INTO `Agents`(`codeAgent`,`nom`,`prenom`,`initiales`,`grade`,`qualification`,`CP`,`telephoneSncf`,`telephonePtt`,`mail`) VALUES (null,'"+list.get(0)+"','"+list.get(1)+"','"+list.get(2)+"','"+list.get(3)+"','"+list.get(4)+"','"+list.get(5)+"','"+list.get(6)+"','"+list.get(7)+"','"+list.get(8)+"')");
        
    }
    
    //MODIFICATION D'UN ELEMENT DE LA BASE
    public void Mod(String codeAgent, ArrayList list)
    throws SQLException{
        
        list = DetectionCaractere(list);
        RequeteUpdate("UPDATE Agents SET nom='"+list.get(0)+"',prenom='"+list.get(1)+"',`initiales` = '"+list.get(2)+"',`grade` = '"+list.get(3)+"', `qualification` = '"+list.get(4)+"',`CP` = '"+list.get(5)+"',`telephoneSncf` = '"+list.get(6)+"',`telephonePtt` = '"+list.get(7)+"', `mail` = '"+list.get(8)+"' WHERE `codeAgent` ="+codeAgent);
        
    }
    
    //CHARGEMENT DES ELEMENTS POUR MODIFICATION
    public void InitMod(String code)
    throws SQLException{
        list_Mod = new ArrayList();
        ResultSet result = null;
        
        result = RequeteSelect(result,"select * from Agents WHERE codeAgent="+code);
        while (result.next()){
            
            list_CodeAgent.add(result.getString(1));
            
            list_Mod.add(result.getString(2));
            list_Mod.add(result.getString(3));
            list_Mod.add(result.getString(4));
            list_Mod.add(result.getString(5));
            list_Mod.add(result.getString(6));
            list_Mod.add(result.getString(7));
            list_Mod.add(result.getString(8));
            list_Mod.add(result.getString(9));
            list_Mod.add(result.getString(10));
        }
        
        list_Mod = Transform(list_Mod);
        
        Close();
        result.close();
        
    }
    
    //RECHERCHE D'UN ELEMENT DE LA BASE
    public boolean Search(String mot_clef, String filtre)
    throws SQLException{
        
        list_CodeAgent = new ArrayList();
        list_search = new ArrayList();
        ResultSet result = null;
        mot_clef = DetectionCaractere(mot_clef);
        result = RequeteSelect(result,"select * from Agents where "+filtre+" LIKE '"+mot_clef+"%' order by 'nom'");
        while (result.next()){
            list_CodeAgent.add(result.getString(1));
            list_search.add(result.getString(2));
            list_search.add(result.getString(3));
            list_search.add(result.getString(4));
            list_search.add(result.getString(5));
            list_search.add(result.getString(6));
            list_search.add(result.getString(7));
            list_search.add(result.getString(8));
            list_search.add(result.getString(9));
            list_search.add(result.getString(10));
        }
        
        list_search = Transform(list_search);
        
        Close();
        result.close();
        
        if(list_CodeAgent.size() != 0)return true;
        else return false;
    }
    
    //RECUPERATION DU NOM EN FONCTION DU CODE
    public String GetnomAgent(String code)throws SQLException{
        ResultSet result = null;
        String nomAgent = "";
        
        result = RequeteSelect(result,"select nom from Agents where codeAgent="+code+"");
        while (result.next()){
            nomAgent = result.getString(1);
        }
        nomAgent =  Transform(nomAgent);
        Close();
        result.close();
        
        return nomAgent;
    }
    
    //RECUPERATION DU CODE EN FONCTION DU NOM
    public ArrayList GetcodeAgent(String name)throws SQLException{
        ArrayList list;
        list = new ArrayList();
        ResultSet result = null;
        
        result = RequeteSelect(result,"select codeAgent from Agents where nom LIKE '%"+name+"%'");
        while (result.next()){
            list.add(result.getString(1));
        }
        list =  Transform(list);
        Close();
        result.close();
        
        return list;
    }
    
    //RECUPERATION DE DONNEE AGENT POUR COURRIER EN FONCTIO NDU CODE AGENT
    public ArrayList GetInfosAgentCourrier(String codeAgent)throws SQLException{
        ArrayList list;
        list = new ArrayList();
        ResultSet result = null;
        
        result = RequeteSelect(result,"select nom,prenom,telephonePtt,telephoneSncf,mail,initiales from Agents where codeAgent = "+codeAgent);
        while (result.next()){
            list.add(result.getString(1));
            list.add(result.getString(2));
            list.add(result.getString(3));
            list.add(result.getString(4));
            list.add(result.getString(5));
            list.add(result.getString(6));
        }
        list =  Transform(list);
        Close();
        result.close();
        
        return list;
    }
}
