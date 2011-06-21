package fr.generali.gfb.ws.sinistre.domain.reduit.contract;

import fr.generali.espaceclient.proxy.generic.domain.InformationIntermediaire;

public class CoordonneesIntermediaires implements ICoordonneesIntermediaire {

    private InformationIntermediaire infosPortefeuille;

    private String supprimeEspace(String chaine){
        String toReturn = chaine;
        if(toReturn != null) {
            toReturn = chaine.trim();
        }
        if("".equals(toReturn)){
            toReturn = null;
        }
        return toReturn;
    }
    
    
    public String getAdresse2() {
        if(infosPortefeuille != null && infosPortefeuille.getBureau() != null){
            return supprimeEspace(infosPortefeuille.getBureau().getLigne2());
        }
        return null;
    }

    public String getAdresse3() {
        if(infosPortefeuille != null && infosPortefeuille.getBureau() != null){
            return supprimeEspace(infosPortefeuille.getBureau().getLigne3());
        }
        return null;
    }

    public String getAdresse4() {
        if(infosPortefeuille != null && infosPortefeuille.getBureau() != null){
            return supprimeEspace(infosPortefeuille.getBureau().getLigne4());
        }
        return null;
    }

    public String getAdresse5() {
        if(infosPortefeuille != null && infosPortefeuille.getBureau() != null){
            return supprimeEspace(infosPortefeuille.getBureau().getLigne5());
        }
        return null;
    }

    public String getAdresse6() {
        if(infosPortefeuille != null && infosPortefeuille.getBureau() != null){
            return supprimeEspace(infosPortefeuille.getBureau().getLigne6());
        }
        return null;
    }

    public String getAdresse7() {
        if(infosPortefeuille != null && infosPortefeuille.getBureau() != null){
            return supprimeEspace(infosPortefeuille.getBureau().getLigne7());
        }
        return null;
    }
    
    public String getEmail() {
        if(infosPortefeuille != null && infosPortefeuille.getBureau() != null){
            return supprimeEspace(infosPortefeuille.getBureau().getEmail());
        }
        return null;
    }

    public String getNom() {
        if(infosPortefeuille != null && infosPortefeuille.getRepresentant() != null){
            return supprimeEspace(infosPortefeuille.getRepresentant().getNom());
        }
        return null;
    }

    public String getPrenom() {
        if(infosPortefeuille != null && infosPortefeuille.getRepresentant() != null){
            return supprimeEspace(infosPortefeuille.getRepresentant().getPrenom());
        }
        return null;
    }

    public String getTelephone() {
        if(infosPortefeuille != null && infosPortefeuille.getBureau() != null){
            return supprimeEspace(infosPortefeuille.getBureau().getTelephone());
        }
        return null;
    }
  
    public String getNumOrias() {
        if(infosPortefeuille != null && infosPortefeuille.getRepresentant() != null){
            return supprimeEspace(infosPortefeuille.getRepresentant().getOrias());
        }
        return null;
    }
  

    public CoordonneesIntermediaires(InformationIntermediaire infosPortefeuille) {
        this.infosPortefeuille = infosPortefeuille;
    }
    
    public Boolean getInfosPortefeuilleNull(){
        if(infosPortefeuille == null){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }


    public String getFax() {
        if(infosPortefeuille != null && infosPortefeuille.getBureau() != null){
            return supprimeEspace(infosPortefeuille.getBureau().getFax());
        }
        return null;
    }


	public String getNomBureau() {
		if (infosPortefeuille != null && infosPortefeuille.getBureau() != null) {
			return supprimeEspace(infosPortefeuille.getBureau().getNom());
		}
		return null;
	}
}
