package Models.TOOLS;

import java.sql.*;
import javax.sql.*;
import java.sql.Connection;
import java.util.ArrayList;

//@author RABALLAND Romain
//@version v3.0

public class Tools{
    
    //DECLARATION DES VARIABLE DE CONNEXION A LA BASE
    public DataSource ds = null;
    private Connection connexion = null;
    static private Statement stmt = null;
    private ResultSet result = null;
    
    public String cheminOuverturePDF = "/SNCF/PDF/";
    
    //DECLARATION DU CONSTRUCTEUR
    public Tools(DataSource ds){
        this.ds = ds;
    }
    
    //METHODE DE SUPPRESSION D'ELEMENT DE LA BASE
    public void Delete(String clef, String nomClef, String nomTable)
    throws SQLException{
        RequeteUpdate("DELETE FROM "+nomTable+" WHERE "+nomClef+"="+clef);
    }
    
    //METHODE DE SUPPRESSION D'ELEMENT DE LA BASE
    public void Delete(ArrayList clef, String nomClef, String nomTable)
    throws SQLException{
        
        String requete = "DELETE FROM "+nomTable+" WHERE ";
        String addRequete ="";
        for(int i = 0; i < clef.size()-1; i++)
            addRequete += nomClef+" = "+clef.get(i)+" OR ";
        addRequete += nomClef+" = "+clef.get(clef.size()-1);
        
        requete += addRequete;
        RequeteUpdate(requete);
        
    }
    
    //EXECUTE UNE REQUETE AVEC RETOUR DU RESULTSET
    public ResultSet RequeteSelect(ResultSet result, String requete)
    throws SQLException{
        connexion = ds.getConnection();
        
        stmt = connexion.createStatement();
        result = stmt.executeQuery(requete);
        return result;
    }
    
    //EXECUTE UNE MISE A JOUR DE LA BASE FERMETURE DE CONNECTION APRES EXECUTION
    public void RequeteUpdate(String requete)
    throws SQLException{
        connexion = ds.getConnection();
        
        stmt = connexion.createStatement();
        stmt.executeUpdate(requete);
        Close();
    }
    
    //FERMETURE DES CONNEXIONS
    public void Close()
    throws SQLException{
        
        GetStatement().close();
        GetConnexion().close();
        
    }
    
    //POUR RECUPERATION DES VARIABLES
    public Connection GetConnexion(){
        return connexion;
    }
    public Statement GetStatement(){
        return stmt;
    }
    
    //TRANSFORMATION DES CHAINES DE CARACTERES
    public ArrayList Transform(ArrayList list){
        
        ArrayList NewList = new ArrayList();
        String temp = "";
        for(int i = 0 ; i < list.size(); i++){
            temp = ""+list.get(i);
            temp = temp.replace("�\u0082�","&euro;").replace("'","&acute;").replace("\u0092","'").replace("�","&euro;").replace("\u0080","&euro;").replace("�","&copy;").replace("�","&trade;").replace("�","&micro;").replace("�","&agrave;").replace("�","&aacute;").replace("�","&acirc;").replace("�","&atilde;").replace("�","&auml;").replace("�","&ccedil;").replace("�","&egrave;").replace("�","&eacute;").replace("�","&ecirc;").replace("�","&euml;").replace("�","&icirc;").replace("�","&iuml;").replace("�","&ntilde;").replace("�","&ocirc;").replace("�","&ouml;").replace("�","&ugrave;").replace("�","&uacute;").replace("�","&uuml;");
            NewList.add(temp);
        }
        return NewList;
    }
    
    //TRANSFORMATION DES CHAINES DE CARACTERES
    public String Transform(String chaine){
        chaine = chaine.replace("�\u0082�","&euro;").replace("'","&acute;").replace("\u0092","'").replace("�","&euro;").replace("\u0080","&euro;").replace("�","&copy;").replace("�","&trade;").replace("�","&micro;").replace("�","&agrave;").replace("�","&aacute;").replace("�","&acirc;").replace("�","&atilde;").replace("�","&auml;").replace("�","&ccedil;").replace("�","&egrave;").replace("�","&eacute;").replace("�","&ecirc;").replace("�","&euml;").replace("�","&icirc;").replace("�","&iuml;").replace("�","&ntilde;").replace("�","&ocirc;").replace("�","&ouml;").replace("�","&ugrave;").replace("�","&uacute;").replace("�","&uuml;");
        return chaine;
    }
    
    public ArrayList InverseTransform(ArrayList list){
        
        ArrayList NewList = new ArrayList();
        String temp = "";
        for(int i = 0 ; i < list.size(); i++){
            temp = ""+list.get(i);
            temp = temp.replace("&acute;","'").replace("\u0092","'").replace("&euro;","�").replace("&copy;","�").replace("&trade;","�").replace("&micro;","�").replace("&agrave;","�").replace("&aacute;","�").replace("&acirc;","�").replace("&atilde;","�").replace("&auml;","�").replace("&ccedil;","�").replace("&egrave;","�").replace("&eacute;","�").replace("&ecirc;","�").replace("&euml;","�").replace("&icirc;","�").replace("&iuml;","�").replace("&ntilde;","�").replace("&ocirc;","�").replace("&ouml;","�").replace("&ugrave;","�").replace("&uacute;","�").replace("&uuml;","�");
            NewList.add(temp);
        }
        return NewList;
    }
    
    //TRANSFORMATION DES CHAINES DE CARACTERES
    public String InverseTransform(String chaine){
        chaine = chaine.replace("&acute;","'").replace("\u0092","'").replace("&euro;","�").replace("&copy;","�").replace("&trade;","�").replace("&micro;","�").replace("&agrave;","�").replace("&aacute;","�").replace("&acirc;","�").replace("&atilde;","�").replace("&auml;","�").replace("&ccedil;","�").replace("&egrave;","�").replace("&eacute;","�").replace("&ecirc;","�").replace("&euml;","�").replace("&icirc;","�").replace("&iuml;","�").replace("&ntilde;","�").replace("&ocirc;","�").replace("&ouml;","�").replace("&ugrave;","�").replace("&uacute;","�").replace("&uuml;","�");
        return chaine;
    }
    
    static public ArrayList DetectionCaractere(ArrayList list_old){
        
        ArrayList list_new;
        list_new = new ArrayList();
        
        String temp = "";
        for(int i = 0; i < list_old.size(); i++){
            temp = ""+list_old.get(i);
            list_new.add(temp.replace("'","\u0092"));
        }
        return list_new;
        
    }
        static public ArrayList DetectionPoint(ArrayList list_old){
        
        ArrayList list_new;
        list_new = new ArrayList();
        
        String temp = "";
        for(int i = 0; i < list_old.size(); i++){
            temp = ""+list_old.get(i);
            list_new.add(temp.replace(".",","));
        }
        return list_new;
        
    }
    
    static public String DetectionCaractere(String chaine_old){
        String chaine_new;
        String temp = "";
        
        chaine_new = chaine_old.replace("'","\u0092");
        return chaine_new;
    }
    
        static public String DetectionPoint(String chaine_old){
        String chaine_new;
        String temp = "";
        
        chaine_new = chaine_old.replace(".",",");
        return chaine_new;
    }
    
    
}

