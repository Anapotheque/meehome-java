
package fr.generali.gfb.amf.elisa.v1.dto {
  
  [RemoteClass(alias="fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoClient")]

  public class DtoRoClient extends fr.generali.gfb.amf.elisa.v1.dto._DtoRoClient {

    /* Constructor */
    public function DtoRoClient():void {
      super();
    }  
    
    public function log():void
    {
    	trace("Client (age retraite : "+ageRetraite+", date naissance : "+dateNaissance+", enfants : "+enfants+", salaire : "+salaire+", sex : "+sex+")");
    }
    
  }

}