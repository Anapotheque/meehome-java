package Models.CALQUES;

import Models.TOOLS.Tools;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

//RABALLAND Romain v3.0
public class CasesDAO extends Tools{
    
    /** Contructeur*/
    public CasesDAO(DataSource ds) {
        super(ds);
    }
    
    public ArrayList listCodeCases = null;
    public ArrayList listnumeroCases = null;
    
    public String codeCase = "";
    
    public boolean GetListCases(String codeCollection)
    throws SQLException{
        
        listCodeCases = new ArrayList();
        listnumeroCases = new ArrayList();
        
        ResultSet result = null;
        
        if(codeCollection.equals(""))
            result = RequeteSelect(result,"SELECT DISTINCT cases.codeCase,cases.numeroCase FROM cases INNER JOIN calques ON (cases.codeCase = calques.codeCase) ORDER BY numeroCase");
        else
            result = RequeteSelect(result,"SELECT cases.codeCase,cases.numeroCase FROM cases WHERE cases.codeGare = "+codeCollection);
        
        while (result.next()){
            
            listCodeCases.add(result.getString(1));
            listnumeroCases.add(result.getString(2));
            
        }
        
        Close();
        result.close();
        
        return listCodeCases.size()!=0?true:false;
        
    }
    
    public String GetNumeroCases(String codeCase)
    throws SQLException{
        String numeroCase = "";
        ResultSet result = null;
        result = RequeteSelect(result,"SELECT numeroCase FROM cases WHERE cases.codeCase = "+codeCase);
        
        while (result.next()){
            numeroCase = result.getString(1);
        }
        Close();
        result.close();
        return numeroCase;
    }
    
    public void New(String numeroCase, String codeGare)
    throws SQLException{
        
        RequeteUpdate("INSERT INTO Cases VALUES (null,'"+numeroCase+"',"+codeGare+")");
        
    }
}
