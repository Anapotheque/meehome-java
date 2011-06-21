package Models.CALQUES;

import Models.TOOLS.Date.DataDate;
import Models.TOOLS.Tools;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

//RABALLAND Romain v3.0
public class CalquesDAO extends Tools{
    
    /** Contructeur*/
    public CalquesDAO(DataSource ds) {
        super(ds);
    }
    
    public ArrayList list = null;
    public ArrayList list_Titre = null;
    public ArrayList list_Size = null;
    
    public ArrayList listCodeCalques = null;
    public ArrayList listnumeroCalques = null;
    public ArrayList listDate = null;
    
    public ArrayList listCodeEntreprise = null;
    
    public String codeCalques = "";
    public String codeCollection = "";
    
    public String codeAgent = "";
    public String codeEntreprise = "";
    
    public String derniereDate = "";
    
    public boolean generatePDF = false;
    public ArrayList listCodeCalqueSortie = null;
    
    private void SetList(){
        
        list_Titre = new ArrayList();
        list_Titre.add("CALQUES");
        list_Titre.add("CASES");
        
        list_Size = new ArrayList();
        list_Size.add("50%");
        list_Size.add("50%");
        
    }
    
    public boolean GetListCalquesNonSortis(String code)
    throws SQLException{
        
        listCodeCalques = new ArrayList();
        listnumeroCalques = new ArrayList();
        
        ResultSet result = null;
        
        result = RequeteSelect(result,"SELECT codeCalques,numeroCalques FROM calques INNER JOIN cases ON (calques.codeCase = cases.codeCase) WHERE dateSortie = '' AND codeGare ="+code+" ORDER BY codeCalques");
        
        while (result.next()){
            listCodeCalques.add(result.getString(1));
            listnumeroCalques.add(result.getString(2));
        }
        
        Close();
        result.close();
        
        return listCodeCalques.size()!=0?true:false;
        
    }
    
    public boolean GetListCalquesDernierRentre(String code)
    throws SQLException{
        
        listCodeCalques = new ArrayList();
        listnumeroCalques = new ArrayList();
        listDate = new ArrayList();
        
        ResultSet result = null;
        
        String requete = "SELECT  calques.codeCalques, calques.numeroCalques, historiquecalques.DateRetour "+
                "FROM historiquecalques RIGHT JOIN "+
                "(calques INNER JOIN cases ON ( calques.codeCase = cases.codeCase )) "+
                "ON ( calques.numeroCalques = historiquecalques.NumeroCalques AND cases.codeGare = historiquecalques.codegare) "+
                "WHERE calques.dateSortie = '' AND cases.codeGare = "+code+" "+
                "ORDER BY codeCalques";
        
        result = RequeteSelect(result,requete);
        
        while (result.next()){
            listCodeCalques.add(result.getString(1));
            listnumeroCalques.add(result.getString(2));
            if(result.getString(3) != null)
                listDate.add(result.getString(3));
            else
                listDate.add("");
        }
        
        Close();
        result.close();
        
        return listCodeCalques.size()!=0?true:false;
        
    }
    
    public boolean SetLastDate(String code)
    throws SQLException{
        
        ResultSet result = null;
        
        String requete = "SELECT MAX(historiquecalques.DateRetour) "+
                "FROM historiquecalques RIGHT JOIN "+
                "(calques INNER JOIN cases ON ( calques.codeCase = cases.codeCase )) "+
                "ON ( calques.numeroCalques = historiquecalques.NumeroCalques AND cases.codeGare = historiquecalques.codegare) "+
                "WHERE calques.dateSortie = '' AND historiquecalques.DateRetour != '' AND cases.codeGare = "+code+" ";
        
        result = RequeteSelect(result,requete);
        
        while (result.next()){
            derniereDate = result.getString(1);
        }
        
        Close();
        result.close();
        
        return derniereDate!=null?true:false;
        
    }
    
    
    public boolean GetListCalquesSortis(String code, boolean GetCodeEntreprise)
    throws SQLException{
        
        listCodeCalques = new ArrayList();
        listnumeroCalques = new ArrayList();
        listCodeEntreprise = new ArrayList();
        
        ResultSet result = null;
        
        result = RequeteSelect(result,"SELECT codeCalques,numeroCalques,codeEntreprise FROM calques INNER JOIN cases ON (calques.codeCase = cases.codeCase) WHERE dateSortie != '' AND codeGare ="+code+" ORDER BY codeCalques");
        
        while (result.next()){
            listCodeCalques.add(result.getString(1));
            listnumeroCalques.add(result.getString(2));
            if(GetCodeEntreprise)
                listCodeEntreprise.add(result.getString(3));
        }
        
        Close();
        result.close();
        
        return listCodeCalques.size()!=0?true:false;
        
    }
    
    public void GetCalquesCases(String code)
    throws SQLException{
        
        SetList();
        
        list = new ArrayList();
        
        ResultSet result = null;
        
        result = RequeteSelect(result,"SELECT numeroCalques, numeroCase FROM calques INNER JOIN cases ON ( calques.codeCase = cases.codeCase ) WHERE codeGare = "+code);
        
        while (result.next()){
            
            list.add(result.getString(1));
            list.add(result.getString(2));
            
        }
        
        Close();
        result.close();
        
    }
    
    public boolean GetCalques(String code)
    throws SQLException{
        
        listCodeCalques = new ArrayList();
        listnumeroCalques = new ArrayList();
        
        ResultSet result = null;
        
        result = RequeteSelect(result,"SELECT codeCalques, numeroCalques FROM calques INNER JOIN cases ON ( calques.codeCase = cases.codeCase ) WHERE codeGare = "+code);
        
        while (result.next()){
            
            listCodeCalques.add(result.getString(1));
            listnumeroCalques.add(result.getString(2));
            
        }
        
        Close();
        result.close();
        
        return listCodeCalques.size()!=0?true:false;
        
    }
    
    public boolean GetCalques(String codeGare,String codeCase)
    throws SQLException{
        
        listCodeCalques = new ArrayList();
        listnumeroCalques = new ArrayList();
        
        ResultSet result = null;
        
        result = RequeteSelect(result,"SELECT codeCalques, numeroCalques FROM calques INNER JOIN cases ON ( calques.codeCase = cases.codeCase ) WHERE cases.codeGare = "+codeGare+" AND calques.codeCase = "+codeCase);
        
        while (result.next()){
            
            listCodeCalques.add(result.getString(1));
            listnumeroCalques.add(result.getString(2));
            
        }
        
        Close();
        result.close();
        
        return listCodeCalques.size()!=0?true:false;
        
    }
    
    public String GetNumeroCalque(String codeCalque)
    throws SQLException{
        String numeroCase = "";
        ResultSet result = null;
        result = RequeteSelect(result,"SELECT numeroCalques FROM calques WHERE codeCalques = "+codeCalque);
        
        while (result.next()){
            numeroCase = result.getString(1);
        }
        Close();
        result.close();
        return numeroCase;
    }
    
    public ArrayList ReOrganisationCaseCalquePourImpression(ArrayList listCodeCalques)
    throws SQLException{
        
        ArrayList list = null;
        list = new ArrayList();
        
        ResultSet result = null;
        
        
        
        String clauseWhere = "";
        for(int i = 1 ; i < listCodeCalques.size(); i++)
            clauseWhere += "OR codeCalques = "+listCodeCalques.get(i)+" ";
        
        String requete = "SELECT numeroCase, numeroCalques "+
                "FROM calques INNER JOIN cases ON (calques.codeCase = cases.codeCase) "+
                "WHERE codeCalques = "+listCodeCalques.get(0)+" "+clauseWhere+" "+
                "ORDER BY numeroCase";
        
        result = RequeteSelect(result,requete);
        
        while (result.next()){
            list.add(result.getString(1));
            list.add(result.getString(2));
        }
        
        Close();
        result.close();
        
        return list;
    }
    
    public boolean TestCalquesPresent(String codeCalque)
    throws SQLException{
        
        String dateSortie = null;
        ResultSet result = null;
        result = RequeteSelect(result,"SELECT dateSortie FROM calques WHERE codeCalques = "+codeCalque);
        
        while (result.next()){
            dateSortie = result.getString(1);
        }
        Close();
        result.close();
        
        return (dateSortie==null || dateSortie.equals(""))?true:false;
    }
    
    public String GetDateSortie(String codeCalque)
    throws SQLException{
        
        String dateSortie = "";
        ResultSet result = null;
        result = RequeteSelect(result,"SELECT dateSortie FROM calques WHERE codeCalques = "+codeCalque);
        
        while (result.next()){
            dateSortie = result.getString(1);
        }
        Close();
        result.close();
        
        return dateSortie;
    }
    
    public void New(String numeroCalques, String codeCase)
    throws SQLException{
        numeroCalques = DetectionCaractere(numeroCalques);
        RequeteUpdate("INSERT INTO Calques VALUES (null,'"+numeroCalques+"',"+codeCase+",'','','')");
    }
    
    public void Mod(String numeroCalques, String codeCase)
    throws SQLException{
        numeroCalques = DetectionCaractere(numeroCalques);
        RequeteUpdate("UPDATE Calques SET numeroCalques = '"+numeroCalques+"', codeCase = '"+codeCase+"' WHERE codeCalques ="+codeCalques);
        
    }
    
    public void Sortie(String codeCalque, String codeAgent, String codeEntreprise, String dateSortie)
    throws SQLException{
        RequeteUpdate("UPDATE Calques SET codeAgent = '"+codeAgent+"', codeEntreprise = '"+codeEntreprise+"', dateSortie = '"+dateSortie+"' WHERE codeCalques ="+codeCalque);
    }
    
    public void Retour(String codeCalque, String dateSortie)
    throws SQLException{
        RequeteUpdate("UPDATE Calques SET codeAgent = '', codeEntreprise = '', dateSortie = '' WHERE codeCalques ="+codeCalque);
        RequeteUpdate("INSERT INTO historiquecalques VALUES ('"+GetNumeroCalque(codeCalque)+"','"+codeEntreprise+"',"+codeAgent+",'"+dateSortie+"','"+codeCollection+"','"+DataDate.GetDate()+"')");
        
    }
    
    public void Migration(String codeCase)
    throws SQLException{
        RequeteUpdate("UPDATE Calques SET codeCase = '"+codeCase+"' WHERE codeCalques ="+codeCalques);
        
    }
    
}
