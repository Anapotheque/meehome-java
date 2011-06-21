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
            temp = temp.replace("â\u0082¬","&euro;").replace("'","&acute;").replace("\u0092","'").replace("€","&euro;").replace("\u0080","&euro;").replace("©","&copy;").replace("™","&trade;").replace("µ","&micro;").replace("à","&agrave;").replace("á","&aacute;").replace("â","&acirc;").replace("ã","&atilde;").replace("ä","&auml;").replace("ç","&ccedil;").replace("è","&egrave;").replace("é","&eacute;").replace("ê","&ecirc;").replace("ë","&euml;").replace("î","&icirc;").replace("ï","&iuml;").replace("ñ","&ntilde;").replace("ô","&ocirc;").replace("ö","&ouml;").replace("ù","&ugrave;").replace("ú","&uacute;").replace("ü","&uuml;");
            NewList.add(temp);
        }
        return NewList;
    }
    
    //TRANSFORMATION DES CHAINES DE CARACTERES
    public String Transform(String chaine){
        chaine = chaine.replace("â\u0082¬","&euro;").replace("'","&acute;").replace("\u0092","'").replace("€","&euro;").replace("\u0080","&euro;").replace("©","&copy;").replace("™","&trade;").replace("µ","&micro;").replace("à","&agrave;").replace("á","&aacute;").replace("â","&acirc;").replace("ã","&atilde;").replace("ä","&auml;").replace("ç","&ccedil;").replace("è","&egrave;").replace("é","&eacute;").replace("ê","&ecirc;").replace("ë","&euml;").replace("î","&icirc;").replace("ï","&iuml;").replace("ñ","&ntilde;").replace("ô","&ocirc;").replace("ö","&ouml;").replace("ù","&ugrave;").replace("ú","&uacute;").replace("ü","&uuml;");
        return chaine;
    }
    
    public ArrayList InverseTransform(ArrayList list){
        
        ArrayList NewList = new ArrayList();
        String temp = "";
        for(int i = 0 ; i < list.size(); i++){
            temp = ""+list.get(i);
            temp = temp.replace("&acute;","'").replace("\u0092","'").replace("&euro;","€").replace("&copy;","©").replace("&trade;","™").replace("&micro;","µ").replace("&agrave;","à").replace("&aacute;","á").replace("&acirc;","â").replace("&atilde;","ã").replace("&auml;","ä").replace("&ccedil;","ç").replace("&egrave;","è").replace("&eacute;","é").replace("&ecirc;","ê").replace("&euml;","ë").replace("&icirc;","î").replace("&iuml;","ï").replace("&ntilde;","ñ").replace("&ocirc;","ô").replace("&ouml;","ö").replace("&ugrave;","ù").replace("&uacute;","ú").replace("&uuml;","ü");
            NewList.add(temp);
        }
        return NewList;
    }
    
    //TRANSFORMATION DES CHAINES DE CARACTERES
    public String InverseTransform(String chaine){
        chaine = chaine.replace("&acute;","'").replace("\u0092","'").replace("&euro;","€").replace("&copy;","©").replace("&trade;","™").replace("&micro;","µ").replace("&agrave;","à").replace("&aacute;","á").replace("&acirc;","â").replace("&atilde;","ã").replace("&auml;","ä").replace("&ccedil;","ç").replace("&egrave;","è").replace("&eacute;","é").replace("&ecirc;","ê").replace("&euml;","ë").replace("&icirc;","î").replace("&iuml;","ï").replace("&ntilde;","ñ").replace("&ocirc;","ô").replace("&ouml;","ö").replace("&ugrave;","ù").replace("&uacute;","ú").replace("&uuml;","ü");
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

