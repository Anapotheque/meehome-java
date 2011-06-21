/**
 * 
 */
package fr.generali.gfb.services.elisa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoClient;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoDernierePeriode;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoHypotheses;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoInformationComplementaireReponse;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoPeriode;
import fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoResultats;

/**
 * Helper
 */
public abstract class TestUtils {

    public static DtoRoInformationComplementaireReponse newRepInfoComp(String codeCaisse, String typeQuestion, Double valeur, String id) {
        final DtoRoInformationComplementaireReponse obj = new DtoRoInformationComplementaireReponse();
        
        obj.setCodeCaisse(codeCaisse);
        obj.setTypeQuestion(typeQuestion);
        obj.setValeur(valeur);
        obj.setId(id);
        
        return obj;
    }


    public static Calendar stringToCalendar(String format, String dateString) {
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat(format);
            final Calendar cal = GregorianCalendar.getInstance();
            cal.setTime(sdf.parse(dateString));
            return cal;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * @return
     * @throws ParseException 
     */
    public static DtoRoClient newDtoRoClient(Integer ageRetraite, String dateNaissance, String sex, Double salaire, Integer enfants) {
        final DtoRoClient dtoRoClient = new DtoRoClient();
        
        dtoRoClient.setAgeRetraite(ageRetraite);
        dtoRoClient.setDateNaissance(stringToCalendar("yyyy-MM-dd", dateNaissance));
        dtoRoClient.setSex(sex);
        dtoRoClient.setSalaire(salaire);
        dtoRoClient.setEnfants(enfants);
        
        return dtoRoClient;
    }


    public static DtoRoDernierePeriode newDtoRoDernierePeriode(String codeProfession, String dateDebut, Boolean jusquaLaRetraite) {
        final DtoRoDernierePeriode obj = new DtoRoDernierePeriode();
        
        obj.setCodeProfession(codeProfession);
        
        obj.setDateDebut(stringToCalendar("yyyy-MM-dd", dateDebut));
        obj.setJusquaLaRetraite(jusquaLaRetraite);
        
        return obj;
    }

    public static DtoRoHypotheses newDtoRoHypotheses(List<DtoRoPeriode> periodes, DtoRoClient dtoRoClient) {
        final DtoRoHypotheses obj = new DtoRoHypotheses();
        
        obj.setDegradSurRetraiteAAcquerir(1.5); // CONSTANTES
        obj.setDegradSurRetraiteAcquise(1.5); // CONSTANTES
        
        obj.setPeriodes(periodes);
        obj.setDtoRoClient(dtoRoClient);
        
        return obj;
    }
    
    public static DtoRoPeriode newDtoRoPeriode(String codeProfession, String dateDebut, String dateFin, Boolean jusquaLaRetraite,
                    Double revenuAnnuelDebut, Double revenuAnnuelFin,
                    List<DtoRoInformationComplementaireReponse> listeRepInfoComp) {
        return newDtoRoPeriode(null, codeProfession, dateDebut, dateFin, jusquaLaRetraite, null, null, revenuAnnuelDebut, revenuAnnuelFin, listeRepInfoComp);
    }
    
    public static DtoRoPeriode newDtoRoPeriode(Integer ageCharniere, String codeProfession, String dateDebut, String dateFin, Boolean jusquaLaRetraite,
                    Double progressionApresAgeCharniere, Double progressionAvantAgeCharniere, Double revenuAnnuelDebut, Double revenuAnnuelFin,
                    List<DtoRoInformationComplementaireReponse> listeRepInfoComp) {
        final DtoRoPeriode obj = new DtoRoPeriode();
        
        if (ageCharniere!=null) {
            obj.setAgeCharniere(ageCharniere);
        }
        if (codeProfession != null) {
            obj.setCodeProfession(codeProfession);
        }
        
        if(dateDebut != null) {
            obj.setDateDebut(stringToCalendar("yyyy-MM-dd", dateDebut));
        }
        if (dateFin != null) {
            obj.setDateFin(stringToCalendar("yyyy-MM-dd", dateFin));
        }
        if(jusquaLaRetraite != null) {
            obj.setJusquaLaRetraite(jusquaLaRetraite);
        }
        
        if(progressionApresAgeCharniere != null) {
            obj.setProgressionApresAgeCharniere(progressionApresAgeCharniere);
        }
        if (progressionAvantAgeCharniere != null) {
            obj.setProgressionAvantAgeCharniere(progressionAvantAgeCharniere);
        }
        if(revenuAnnuelDebut != null) {
            obj.setRevenuAnnuelDebut(revenuAnnuelDebut);
        }
        if(revenuAnnuelFin != null) {
            obj.setRevenuAnnuelFin(revenuAnnuelFin);
        }
        
        if (listeRepInfoComp != null) {
            obj.setInformationComplementaire(listeRepInfoComp);
        }
        
        return obj;
    }
    
    public static DtoRoResultats newDtoRoResultats(Integer ageDepart, Integer anneeDepart, String dateDepart, Double dernierRevenu, Integer nombreEnfantCharge, Integer nombreEnfantEleve,
                    String optionDepart, Double retraiteBase, Double retraiteTotal, Double reversion, Double tauxDegradationPondere) {
        final DtoRoResultats obj = new DtoRoResultats();
        
        if (ageDepart != null)
            obj.setAgeDepart(ageDepart);
        if (anneeDepart != null)
            obj.setAnneeDepart(anneeDepart);
        if (dateDepart != null)
            obj.setDateDepart(stringToCalendar("yyyy-MM-dd", dateDepart));
        if (dernierRevenu != null)
            obj.setDernierRevenu(dernierRevenu);
        if (nombreEnfantCharge != null)
            obj.setNombreEnfantCharge(nombreEnfantCharge);
        if (nombreEnfantEleve != null)
            obj.setNombreEnfantEleve(nombreEnfantEleve);
        if (optionDepart != null)
            obj.setOptionDepart(optionDepart);
        if (retraiteBase != null)
            obj.setRetraiteBase(retraiteBase);
        if (retraiteTotal != null)
            obj.setRetraiteTotal(retraiteTotal);
        if (reversion != null)
            obj.setReversion(reversion);
        if (tauxDegradationPondere != null)
            obj.setTauxDegradationPondere(tauxDegradationPondere);
        
        return obj;
    }
}
