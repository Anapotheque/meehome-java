
package fr.generali.gfb.amf.elisa.v1.dto {
  
  [RemoteClass(alias="fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoInformationComplementaireReponse")]

  public class DtoRoInformationComplementaireReponse extends fr.generali.gfb.amf.elisa.v1.dto._DtoRoInformationComplementaireReponse {

    /* Constructor */
    public function DtoRoInformationComplementaireReponse():void {
      super();
    }  
    
    public function log():void
    {
    	trace("Reponse (ID : "+id+", code caisse : "+codeCaisse+", type : "+typeQuestion+", valeur : "+valeur+")");
    }
  }

}