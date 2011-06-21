package fr.alliance.docalliance.dao;

import java.util.HashMap;
import java.util.List;

public interface IPdfDao extends IGenericDao {
	
    static final String PDF_DAO = "PdfDAO";

    /**
     * R�cup�re une liste de pdf � partir de son type
     * @param typePdf Type de PDF
     * @throws DaoFindException 
     * @return Liste liste des object pdf correspondants aux crit�res de la recherche
     */
    List getPdfsByFilter(HashMap<String, Object> filter) throws DaoFindException;
}
