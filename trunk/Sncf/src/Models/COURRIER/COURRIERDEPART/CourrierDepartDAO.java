package Models.COURRIER.COURRIERDEPART;

import Models.TOOLS.Date.DataDate;
import Models.TOOLS.Tools;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.sql.DataSource;

//RABALLAND Romain v3.0
public class CourrierDepartDAO extends Tools {
    
    /** Contructeur*/
    public CourrierDepartDAO(DataSource ds) {
        super(ds);
    }
    
    public String destinataire = "";
    public String designation = "";
    public String codeAgent = "";
    public String codeAffaire = "";
    public int codeLettre = 0;
    
    public ArrayList list_Titre = null;
    public ArrayList list_Size = null;
    public ArrayList list = null;
    public ArrayList list_Code = null;
    
    public String codeLettreModification = "";
    
    public void SetList(){
        
        list_Titre = new ArrayList();
        
        list_Titre.add("CodeLettre");
        list_Titre.add("date");
        list_Titre.add("agent");
        list_Titre.add("designation");
        list_Titre.add("destinataire");
        
        list_Size = new ArrayList();
        
        list_Size.add("20%");
        list_Size.add("20%");
        list_Size.add("20%");
        list_Size.add("20%");
        list_Size.add("20%");
        
    }
    
    public boolean GetListCourrierEnregistrer(String filtre, String code)
    throws SQLException{
        
        SetList();
        
        ResultSet result = null;
        list = new ArrayList();
        list_Code = new ArrayList();
        
        if(filtre.equals("All"))
            result = RequeteSelect(result,"SELECT codeLettre,date,agents.nom,designation, destinataire  FROM courrierdepart INNER JOIN agents ON (courrierdepart.codeAgent = agents.codeAgent)  ORDER BY codeLettre DESC LIMIT 0,1000");
        else if(filtre.equals("parCodeAffaire"))
            result = RequeteSelect(result,"SELECT codeLettre,date,agents.nom,designation, destinataire  FROM courrierdepart INNER JOIN agents ON (courrierdepart.codeAgent = agents.codeAgent) WHERE codeAffaire = "+code+" ORDER BY codeLettre DESC");
        else if(filtre.equals("parCodeAgent"))
            result = RequeteSelect(result,"SELECT codeLettre,date,agents.nom,designation, destinataire  FROM courrierdepart INNER JOIN agents ON (courrierdepart.codeAgent = agents.codeAgent) WHERE courrierdepart.codeAgent = "+code+" ORDER BY codeLettre DESC");
        
        while (result.next()){
            list_Code.add(result.getString(1));
            list.add(result.getString(1));
            list.add(result.getString(2));
            list.add(result.getString(3));
            list.add(result.getString(4));
            list.add(result.getString(5));
        }
        
       list = Transform(list);
        
        Close();
        result.close();
        
        

        
        if(list.size()==0)
            return false;
        else return true;
        
    }
    
    public boolean GetListCourrierModification(String code)
    throws SQLException{
        
        SetList();
        
        ResultSet result = null;
        list = new ArrayList();
        
        result = RequeteSelect(result,"SELECT codeAffaire,codeAgent,designation,destinataire  FROM courrierdepart WHERE codeLettre = "+code);
        
        while (result.next()){
            list.add(result.getString(1));
            list.add(result.getString(2));
            list.add(result.getString(3));
            list.add(result.getString(4));
        }
        
        list = Transform(list);
        
        Close();
        result.close();
        
        return list.size()==0?false:true;
    }
    
    public String GetCodeLettre()
    throws SQLException{
        
        ResultSet result = null;
        String codeLettre = null;
        
        result = RequeteSelect(result,"SELECT max(codeLettre) FROM courrierdepart");
        
        while (result.next()){
            codeLettre = result.getString(1);
        }
        
        Close();
        result.close();
        
        if(codeLettre == null)
            codeLettre = "0";
        
        return codeLettre;
        
    }
    
    public boolean EnregistrementLettre()
    throws SQLException{
        
        designation = DetectionCaractere(designation);
        destinataire = DetectionCaractere(destinataire);
        RequeteUpdate("INSERT INTO courrierdepart VALUES ("+codeLettre+",'"+codeAffaire+"','"+codeAgent+"','"+designation+"','"+destinataire+"','"+DataDate.GetDate()+"')");
        return true;
    }
    
    public void ModificationLettre(ArrayList list)
    throws SQLException{
        
        list = DetectionCaractere(list);
        RequeteUpdate("UPDATE courrierdepart SET codeAffaire = '"+list.get(0)+"',codeAgent = '"+list.get(1)+"',designation = '"+list.get(2)+"',destinataire = '"+list.get(3)+"' WHERE codeLettre = "+codeLettreModification);
        
    }
}
