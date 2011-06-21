package Models.ETUDES.Etudes;

import Models.TOOLS.Controleur.MegaControleur;
import Models.ETUDES.Entreprises.EntreprisesDAO;
import Models.ETUDES.Gares.GaresDAO;
import Models.TOOLS.Tools;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

//RABALLAND Romain v3.0

public class EtudesDAO extends Tools {
    
    //DECLARATION DES LISTES
    public ArrayList list_codeEtude;
    public ArrayList list_codeGare;
    public ArrayList list_codeEntreprise;
    public ArrayList list_indice;
    public ArrayList list_detail;
    public ArrayList list_miseEnService;
    public ArrayList list_envoiTx;
    public ArrayList list_sescoTx;
    public ArrayList list_Difficulte;
    public ArrayList list_PoidsKg;
    public ArrayList list_Contraires;
    public ArrayList list_Autres;
    public ArrayList list_Relations;
    public ArrayList list_Materiel;
    public ArrayList list_Delais;
    public ArrayList list_Reports;
    public ArrayList list_Metre;
    public ArrayList list_Ressource;
    public ArrayList list_Projet;
    public ArrayList list_EtudeTravaux;
    public ArrayList list_Conforme;
    
    public ArrayList list_Show;
    public ArrayList list_TitreShow;
    public ArrayList list_SizeTitre_Show;
    
    public ArrayList list_Search;
    public ArrayList list_Titre_Search;
    public ArrayList list_SizeTitre_Search;
    
    public ArrayList indice;
    
    String requete = null;
    
    public float[] widthsShow =       {0.08f,0.25f,0.12f,0.10f,0.10f,0.1f,0.27f};
    
    String REQUETE_SHOW = "SELECT Etudes.Indice, Gares.Gare, Entreprises.Entreprise, Etudes.MiseEnService, Etudes.EnvoiTx, Etudes.SescoTx, Etudes.Detail "+
            "FROM (Gares RIGHT JOIN Etudes ON Gares.CodeGare = Etudes.CodeGare) LEFT JOIN Entreprises ON Etudes.CodeEntreprise = Entreprises.CodeEntreprise "+
            "ORDER BY Gares.gare";
    
    String REQUETE_DATA = "SELECT Etudes.*" +
            "FROM (Gares RIGHT JOIN Etudes ON Gares.CodeGare = Etudes.CodeGare) LEFT JOIN Entreprises ON Etudes.CodeEntreprise = Entreprises.CodeEntreprise "+
            "ORDER BY Gares.gare";
    
    String REQUETE_SEARCH2 = "SELECT Etudes.Indice, Gares.Gare, Entreprises.Entreprise, Etudes.MiseEnService, Etudes.EnvoiTx, Etudes.SescoTx, Etudes.Detail "+
            "FROM (Gares RIGHT JOIN Etudes ON Gares.CodeGare = Etudes.CodeGare) LEFT JOIN Entreprises ON Etudes.CodeEntreprise = Entreprises.CodeEntreprise "+
            "ORDER BY Gares.gare";
    String REQUETE_SEARCH3 = "SELECT * FROM Etudes";
    
    //DECLARATION DU CONSTRUCTEUR
    public EtudesDAO(DataSource ds){
        super(ds);
        SetList();
    }
    
    //INITIALISATION DES DONNEES
    public void SetList(){
        
        list_TitreShow = new ArrayList();
        list_SizeTitre_Show = new ArrayList();
        
        list_Titre_Search = new ArrayList();
        list_SizeTitre_Search = new ArrayList();
        
        list_Ressource = new ArrayList();
        
        list_TitreShow.add("indice");
        list_TitreShow.add("gare");
        list_TitreShow.add("entreprise");
        list_TitreShow.add("mes");
        list_TitreShow.add("envoi");
        list_TitreShow.add("sesco");
        list_TitreShow.add("affaire");
        
        list_SizeTitre_Show.add("14%");
        list_SizeTitre_Show.add("14%");
        list_SizeTitre_Show.add("14%");
        list_SizeTitre_Show.add("14%");
        list_SizeTitre_Show.add("14%");
        list_SizeTitre_Show.add("14%");
        list_SizeTitre_Show.add("14%");
        
        list_Titre_Search.add("codeAffaire");
        list_Titre_Search.add("indice");
        list_Titre_Search.add("gare");
        list_Titre_Search.add("entreprise");
        list_Titre_Search.add("mes");
        list_Titre_Search.add("envoi");
        list_Titre_Search.add("sesco");
        list_Titre_Search.add("affaire");
        
        list_SizeTitre_Search.add("14%");
        list_SizeTitre_Search.add("14%");
        list_SizeTitre_Search.add("14%");
        list_SizeTitre_Search.add("14%");
        list_SizeTitre_Search.add("14%");
        list_SizeTitre_Search.add("14%");
        list_SizeTitre_Search.add("14%");
        
        list_Ressource.add("indice");
        list_Ressource.add("gare");
        list_Ressource.add("entreprise");
        list_Ressource.add("mise en service");
        list_Ressource.add("envoi tx");
        list_Ressource.add("sesco tx");
        list_Ressource.add("affaire");
        
    }
    
    
    
    
    
    
    
    
    //INITIALISATION DES DONNEES DE L'OBJET
    public void Set()
    throws SQLException{
        
        list_codeEtude = new ArrayList();
        list_codeGare = new ArrayList();
        list_codeEntreprise = new ArrayList();
        list_indice = new ArrayList();
        list_detail = new ArrayList();
        list_miseEnService = new ArrayList();
        list_envoiTx = new ArrayList();
        list_sescoTx = new ArrayList();
        list_Difficulte = new ArrayList();
        list_PoidsKg = new ArrayList();
        list_Contraires = new ArrayList();
        list_Autres = new ArrayList();
        list_Relations = new ArrayList();
        list_Materiel = new ArrayList();
        list_Delais = new ArrayList();
        list_Reports = new ArrayList();
        list_Metre = new ArrayList();
        list_Show = new ArrayList();
        list_Projet = new ArrayList();
        list_EtudeTravaux = new ArrayList();
        list_Conforme = new ArrayList();
        
        ResultSet result = null;
        
        result = RequeteSelect(result,REQUETE_DATA);
        while (result.next()){
            list_codeEtude.add(result.getString(1));
            list_codeGare.add(result.getString(2));
            list_codeEntreprise.add(result.getString(3));
            list_indice.add(result.getString(4));
            list_detail.add(result.getString(5));
            list_miseEnService.add(result.getString(6));
            list_envoiTx.add(result.getString(7));
            list_sescoTx.add(result.getString(8));
            list_Difficulte.add(result.getString(9));
            list_PoidsKg.add(result.getString(10));
            list_Contraires.add(result.getString(11));
            list_Autres.add(result.getString(12));
            list_Relations.add(result.getString(13));
            list_Materiel.add(result.getString(14));
            list_Delais.add(result.getString(15));
            list_Reports.add(result.getString(16));
            list_Metre.add(result.getString(17));
            list_Projet.add(result.getString(18));
            list_EtudeTravaux.add(result.getString(19));
            list_Conforme.add(result.getString(20));
        }
        
        result = RequeteSelect(result,REQUETE_SHOW);
        while (result.next()){
            list_Show.add(result.getString(1));
            list_Show.add(result.getString(2));
            list_Show.add(result.getString(3));
            list_Show.add(result.getString(4));
            list_Show.add(result.getString(5));
            list_Show.add(result.getString(6));
            list_Show.add(result.getString(7));
        }
        list_Show = Transform(list_Show);
        list_detail = Transform(list_detail);
        Close();
        result.close();
        
    }
    
//AJOUT D'UN NOUVEL ELEMENT DANS LA BASE
    public void New(ArrayList list)
    throws SQLException{
        list = DetectionCaractere(list);
        RequeteUpdate("INSERT INTO `Etudes`(`codeEtude`,`codeGare`,`codeEntreprise`,`indice`,`detail`,`miseEnService`,`envoiTx`,`sescoTx`,`difficulte`,`poidsKg`,`contraires`,`autres`,`relations`,`materiel`,`delais`,`reports`,`metre`,`projet`,`etudeTravaux`,`conforme`) VALUES (null,'"+list.get(0)+"','"+list.get(1)+"','"+list.get(2)+"','"+list.get(3)+"','JJ/MM/AAAA','JJ/MM/AAAA',null,null,null,null,null,null,null,null,null,null,'JJ/MM/AAAA','JJ/MM/AAAA','JJ/MM/AAAA')");
        
    }
    
//MODIFICATION D'UN ELEMENT DE LA BASE
    public void Mod(String codeEtude, ArrayList list)
    throws SQLException{
        list = DetectionCaractere(list);
        RequeteUpdate("UPDATE `Etudes` SET `codeEtude` = '"+codeEtude+"',`codeGare` = '"+list.get(0)+"',`codeEntreprise` = '"+list.get(1)+"',`indice` = '"+list.get(2)+"',`detail` = '"+list.get(3)+"',`miseEnService` = '"+list.get(4)+"',`envoiTx` = '"+list.get(5)+"',`sescoTx` = '"+list.get(6)+"',`difficulte` = '"+list.get(7)+"',`poidsKg` = '"+list.get(8)+"',`contraires` = '"+list.get(9)+"',`autres` = '"+list.get(10)+"',`relations` = '"+list.get(11)+"', `materiel` = '"+list.get(12)+"',`delais` = '"+list.get(13)+"',`reports` = '"+list.get(14)+"',`metre` = '"+list.get(15)+"',`projet` = '"+list.get(16)+"',`etudeTravaux` = '"+list.get(17)+"',`conforme` = '"+list.get(18)+"' WHERE `codeEtude` ="+codeEtude);
        
    }
    
//RECHERCHE D'UN ELEMENT DE LA BASE
    public boolean Search(HttpServletRequest request, String mot_clef, String filtre)
    throws SQLException{
        GaresDAO gare = null;
        EntreprisesDAO entreprise = null;
        gare = MegaControleur.creerObjet(request, gare);
        entreprise = MegaControleur.creerObjet(request, entreprise);
        
        list_codeEtude = new ArrayList();
        list_codeGare = new ArrayList();
        list_codeEntreprise = new ArrayList();
        list_indice = new ArrayList();
        list_detail = new ArrayList();
        list_miseEnService = new ArrayList();
        list_envoiTx = new ArrayList();
        list_sescoTx = new ArrayList();
        list_Difficulte = new ArrayList();
        list_PoidsKg = new ArrayList();
        list_Contraires = new ArrayList();
        list_Autres = new ArrayList();
        list_Relations = new ArrayList();
        list_Materiel = new ArrayList();
        list_Delais = new ArrayList();
        list_Reports = new ArrayList();
        list_Metre = new ArrayList();
        list_Projet = new ArrayList();
        list_EtudeTravaux = new ArrayList();
        list_Conforme = new ArrayList();
        
        list_Search = new ArrayList();
        
        ResultSet result = null;
        mot_clef = DetectionCaractere(mot_clef);
        if(filtre.equals("gare")){
            
            requete = "SELECT Etudes.* "+
                    "FROM Etudes "+
                    "WHERE  Etudes.codeGare = ANY (SELECT Gares.CodeGare FROM Gares WHERE gare LIKE '%"+mot_clef+"%')";
            
        } else if(filtre.equals("codeAffaire"))
            requete = "select Etudes.* from etudes where codeEtude = ANY (select codeEtude from etudes_has_affaires where "+filtre+" LIKE '"+mot_clef+"')";
        else if(filtre.equals("nom"))
            requete = "select Etudes.* from etudes where codeEtude = ANY (select codeEtude from etudes_has_agents where codeAgent = ANY (select codeAgent from agents where "+filtre+" LIKE '%"+mot_clef+"%'))";
        
        result = RequeteSelect(result,requete);
        while (result.next()){
            
            list_codeEtude.add(result.getString(1));
            list_codeGare.add(result.getString(2));
            list_codeEntreprise.add(result.getString(3));
            list_indice.add(result.getString(4));
            list_detail.add(result.getString(5));
            list_miseEnService.add(result.getString(6));
            list_envoiTx.add(result.getString(7));
            list_sescoTx.add(result.getString(8));
            list_Difficulte.add(result.getString(9));
            list_PoidsKg.add(result.getString(10));
            list_Contraires.add(result.getString(11));
            list_Autres.add(result.getString(12));
            list_Relations.add(result.getString(13));
            list_Materiel.add(result.getString(14));
            list_Delais.add(result.getString(15));
            list_Reports.add(result.getString(16));
            list_Metre.add(result.getString(17));
            list_Projet.add(result.getString(18));
            list_EtudeTravaux.add(result.getString(19));
            list_Conforme.add(result.getString(20));
            
            list_Search.add(result.getString(4));
            list_Search.add(gare.GetnomGare(result.getString(2)));
            list_Search.add(entreprise.GetnomEntreprise(result.getString(3)));
            list_Search.add(result.getString(6));
            list_Search.add(result.getString(8));
            list_Search.add(result.getString(7));
            list_Search.add(result.getString(5));
        }
        list_Search = Transform(list_Search);
        list_detail = Transform(list_detail);
        Close();
        result.close();
        
        if(list_Search.size() != 0)return true;
        else return false;
    }
    
//RECUPERATION DU CODE MAX
    public String GetLastCodeEtude()
    throws SQLException{
        String codeEtude = null;
        ResultSet result = null;
        
        result = RequeteSelect(result,"select max(codeEtude) from Etudes");
        while (result.next()){
            codeEtude = new String(result.getString(1));
        }
        
        Close();
        result.close();
        
        return codeEtude;
    }
    
//RECUPERATION D'UNE LISTE DE NOM EN FONCTION D'UN CODE
    public void GetindiceEtude(String code)
    throws SQLException{
        indice = new ArrayList();
        ResultSet result = null;
        
        result = RequeteSelect(result,"select indice from Etudes where codeGare="+code+"");
        while (result.next()){
            indice.add(result.getString(1));
        }
        
        Close();
        result.close();
        
    }
    
//RECUPERATION d'UN NOM EN FONCTION D'UN CODE
    public String GetindiceEtudeSolo(String code)
    throws SQLException{
        String indice = null;
        ResultSet result = null;
        
        result = RequeteSelect(result,"select indice from Etudes where codeEtude="+code+"");
        while (result.next()){
            indice = result.getString(1);
        }
        
        return indice;
    }
    
//RECUPERATION D'UNE LISTE DE CODE EN FONCTION D'UNE LISTE DE CODE
    public ArrayList GetcodeEtude(ArrayList code)
    throws SQLException{
        ArrayList codeEtude;
        codeEtude = new ArrayList();
        ResultSet result = null;
        
        for(int i = 0; i < code.size(); i++){
            result = RequeteSelect(result,"select codeEtude from etudes where codeGare="+code.get(i)+"");
            while (result.next()){
                codeEtude.add(result.getString(1));
            }
        }
        
        Close();
        result.close();
        
        return codeEtude;
    }
    
    //RECUPERATION D'UNE LISTE D'INDICE EN FONCTION D'UN CODE GARE
    public ArrayList GetListIndice(String codeGare)
    throws SQLException{
        ArrayList indice;
        indice = new ArrayList();
        ResultSet result = null;
        
        result = RequeteSelect(result,"select indice from etudes where codeGare="+codeGare+"");
        while (result.next()){
            indice.add(result.getString(1));
        }
        
        Close();
        result.close();
        
        return indice;
    }
    
    //RECUPERATION D'UNE LISTE DE DETAIL EN FONCTION D'UN CODE GARE
    public ArrayList GetListDetail(String codeGare)
    throws SQLException{
        ArrayList detail;
        detail = new ArrayList();
        ResultSet result = null;
        
        result = RequeteSelect(result,"select detail from etudes where codeGare="+codeGare+"");
        while (result.next()){
            if(result.getString(1).equals(""))
                detail.add("PAS DE RENSEIGNEMENTS");
            else
                detail.add(result.getString(1));
        }
        detail = Transform(detail);
        Close();
        result.close();
        
        return detail;
    }
    
    //RECUPERATION D'UNE LISTE DE DETAIL EN FONCTION D'UN CODE GARE
    public ArrayList GetListCodeEtude(String codeGare)
    throws SQLException{
        ArrayList codeEtude;
        codeEtude = new ArrayList();
        ResultSet result = null;
        
        result = RequeteSelect(result,"select codeEtude from etudes where codeGare="+codeGare+"");
        while (result.next()){
            codeEtude.add(result.getString(1));
        }
        
        Close();
        result.close();
        
        return codeEtude;
    }
    
    //RECUPERATION DU DETAIL EN FONCTION D'UN CODE GARE
    public String GetDetail(String codeEtude)
    throws SQLException{
        String detail = "";
        ResultSet result = null;
        
        result = RequeteSelect(result,"select detail from etudes where codeEtude="+codeEtude+"");
        while (result.next()){
            if(result.getString(1).equals(""))
                detail = "PAS DE RENSEIGNEMENTS";
            else
                detail = result.getString(1);
        }
        detail = Transform(detail);
        Close();
        result.close();
        
        return detail;
    }
    
    //RECUPERATION DU CODE AFFAIRE EN FONCTION DU CODE ETUDE
    public String GetcodeAffaire(String codeEtude)
    throws SQLException{
        String codeAffaire = "";
        ResultSet result = null;
        
        String REQUETE = "SELECT etudes_has_affaires.codeAffaire "+
                "FROM etudes INNER JOIN etudes_has_affaires ON ( etudes.codeEtude = etudes_has_affaires.codeEtude) "+
                "WHERE etudes_has_affaires.codeEtude = "+codeEtude;
        result = RequeteSelect(result,REQUETE);
        while (result.next()){
            codeAffaire = result.getString(1);
        }
        
        Close();
        result.close();
        
        return codeAffaire;
    }
}

