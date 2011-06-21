package fr.alliance.docalliance.service;

import java.util.List;

import fr.alliance.docalliance.dao.DaoCreateException;
import fr.alliance.docalliance.dao.DaoDeleteException;
import fr.alliance.docalliance.dao.DaoFindException;
import fr.alliance.docalliance.dao.DaoUpdateException;
import fr.alliance.docalliance.model.Pdf;

public interface IPdfService {
    
    static final String PDF_SERVICE = "pdfService";

    void saveOnePdf(Pdf pdf) throws DaoCreateException;
    void modifierPdf(Pdf pdf) throws DaoUpdateException;
    List getPdfsByType(Integer typePdf) throws DaoFindException;
    Pdf getPdfById(int idPdf) throws DaoFindException;
    void deleteOnePdf(Pdf pdf) throws DaoDeleteException;
}
