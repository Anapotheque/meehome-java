package Models.TOOLS.Utilisateurs;
import Models.TOOLS.Tools;
import java.sql.*;
import java.util.ArrayList;
import javax.sql.*;

//@author RABALLAND Romain
//@version v3.0

public class UtilisateursDAO extends Tools {
    
    //DECLARATION DES LISTES
    public ArrayList list_codeUser;
    public ArrayList list_login;
    public ArrayList list_pwd;
    public ArrayList list_nom;
    public ArrayList list_prenom;
    public ArrayList list_sous_groupe;
    public ArrayList list_role;
    public ArrayList list_bonus;
    
    public String NamePDF = "";
    public String role = "";
    public String sous_groupe = "";
    public String nom = "";
    public String prenom = "";
    public String bonus = "";
    
    //DECLARATION DU CONSTRUCTEUR
    public UtilisateursDAO(DataSource ds){
        super(ds);
    }
    
    //INITIALISATION DES DONNEES
    public void SetUtilisateur()
    throws SQLException{
        ResultSet result = null;
        list_codeUser = new ArrayList();
        list_login = new ArrayList();
        list_pwd = new ArrayList();
        list_nom = new ArrayList();
        list_prenom = new ArrayList();
        list_sous_groupe = new ArrayList();
        list_role = new ArrayList();
        list_bonus = new ArrayList();
        
        result = RequeteSelect(result,"select * from Users order by 'nom'");
        while (result.next()){
            list_codeUser.add(result.getString(1));
            list_login.add(result.getString(2));
            list_pwd.add(result.getString(3));
            list_nom.add(result.getString(4));
            list_prenom.add(result.getString(5));
            list_sous_groupe.add(result.getString(6));
            list_role.add(result.getString(7));
            list_bonus.add(result.getString(8));
        }
        Close();
        result.close();
    }
    
    //AJOUT D'UN NOUVEL ELEMENT DANS LA BASE
    public void NewUtilisateur(ArrayList list)
    throws SQLException{
        list = DetectionCaractere(list);
        RequeteUpdate("INSERT INTO `users` VALUES (null,'"+list.get(0)+"','"+list.get(1)+"','"+list.get(2)+"','"+list.get(3)+"','"+list.get(4)+"','"+list.get(5)+"',0)");
    }
    
    //SUPPRESSION D'UN ELEMENT DE LA BASE
    public void SuppUtilisateur(String codeUser)
    throws SQLException{
        codeUser = DetectionCaractere(codeUser);
        RequeteUpdate("DELETE FROM `users` WHERE `codeUser`="+codeUser);
        
    }
    
    //MODIFICATION D'UN ELEMENT DE LA BASE
    public void ModUtilisateur(String codeUser, ArrayList list)
    throws SQLException{
        
        codeUser = DetectionCaractere(codeUser);
        list = DetectionCaractere(list);
        RequeteUpdate("UPDATE `users` SET `login` = '"+list.get(0)+"',`pwd` = '"+list.get(1)+"',`nom` = '"+list.get(2)+"',`prenom` = '"+list.get(3)+"',`sous_groupe` = '"+list.get(4)+"',`role` = '"+list.get(5)+"',`bonus` = '"+list.get(6)+"' WHERE `codeUser` ="+codeUser);
        
    }
    
    //RECHERCHE D'UN ELEMENT DE LA BASE
    public boolean SearchUtilisateur(String mot_clef)
    throws SQLException{
        ResultSet result = null;
        list_codeUser = new ArrayList();
        list_login = new ArrayList();
        list_pwd = new ArrayList();
        list_nom = new ArrayList();
        list_prenom = new ArrayList();
        list_sous_groupe = new ArrayList();
        list_role = new ArrayList();
        list_bonus = new ArrayList();
        
        mot_clef = DetectionCaractere(mot_clef);
        
        result = RequeteSelect(result,"select * from Users where nom LIKE '%"+mot_clef+"%' order by 'nom'");
        while (result.next()){
            list_codeUser.add(result.getString(1));
            list_login.add(result.getString(2));
            list_pwd.add(result.getString(3));
            list_nom.add(result.getString(4));
            list_prenom.add(result.getString(5));
            list_sous_groupe.add(result.getString(6));
            list_role.add(result.getString(7));
            list_bonus.add(result.getString(8));
        }
        
        Close();
        result.close();
        
        if(list_codeUser.size() != 0)return true;
        else return false;
    }
    
    //Methode de verification d'existance dans la base ( permet aussi de verifié le role de l'utilisateur)
    public void ControleIdentity(String login, String password)
    throws SQLException{
        ResultSet result = null;
        
        login = DetectionCaractere(login);
        password = DetectionCaractere(password);
        result = RequeteSelect(result,"select * from Users where login='"+login+"' and pwd='"+password+"'");
        if (result.next()){
            NamePDF = result.getString(2);
            nom = result.getString(4);
            prenom = result.getString(5);
            sous_groupe = result.getString(6);
            role = result.getString(7);
            bonus = result.getString(8);
        } else role = "Intru";
        
        Close();
        result.close();
        
    }
    
    //METOHODE DE TEST DE ROLE
    public boolean TestAdministrateur()    {
        return role.equals("Administrateur")?true:false;
    }
    public boolean TestModerateur()    {
        return role.equals("Moderateur")?true:false;
    }
    public boolean TestUtilisateur()    {
        return role.equals("Utilisateur")?true:false;
    }
    public boolean TestIntru()    {
        return role.equals("Intru")?true:false;
    }
    public boolean TestVerificateur()    {
        return sous_groupe.equals("Verification")?true:false;
    }
    public boolean TestManageur()    {
        return sous_groupe.equals("Management")?true:false;
    }
    public boolean TestBonus()    {
        return bonus.equals("1")?true:false;
    }
}

