
package fr.generali.gfb.amf.elisa.v1.dto {
  
  [RemoteClass(alias="fr.generali.gvie.elisa.ws.retraiteobligatoire.DtoRoProfession")]

  public class DtoRoProfession extends fr.generali.gfb.amf.elisa.v1.dto._DtoRoProfession {

    /* Constructor */
    public function DtoRoProfession():void {
      super();
    }  
    
    public function getQuestionComplementaireByID(id:String):DtoRoQuestionsComplementaires
    {
    	if(questionsComplementaires==null)
    		return null;
    	
    	for each (var question:DtoRoQuestionsComplementaires in questionsComplementaires)
    	{
    		if(question.id==id)
    			return question;
    	}
    	return null;
    }
    
  }

}