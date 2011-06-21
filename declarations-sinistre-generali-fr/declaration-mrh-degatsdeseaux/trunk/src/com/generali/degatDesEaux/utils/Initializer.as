package com.generali.degatDesEaux.utils
{
	import briffle.dq.model.ElementFactory;
	import briffle.dq.model.Form;
	import briffle.dq.model.displayObject.DisplayBehavior;
	import briffle.dq.model.displayObject.Transition;
	import briffle.dq.model.displayObject.TransitionType;
	import briffle.dq.model.elements.Answer;
	import briffle.dq.model.elements.Chapter;
	import briffle.dq.model.elements.ElementType;
	import briffle.dq.model.elements.Section;
	import briffle.dq.model.elements.SingleAnswerQuestion;
	import briffle.dq.model.elements.base.ElementAttribute;
	
	import com.generali.events.ui.NextSectionEvent;
	
	
	public class Initializer
	{
		
		private static var _instance:Initializer;
		private var _form:Form 
		public function  get form() :	Form {
			return this._form;
		}
		public static function getInstance():Initializer
		{
			if (_instance==null)
				_instance = new Initializer(); 
			
			return _instance;
		}
		public function Initializer()
		{
			if(_instance!=null)
			{
			 	throw new Error("Initializer is a Singleton!  Use getInstance instead!");
			}
			init();
		}
		
		
		private  function init():void
		{
			_form = Form.getInstance();
			/////////////////////////////////////////////// Insured /////////////////////////////////////////////////////////////////////////////////
			var chInsured:Chapter = ElementFactory.createElement("C1",_form,ElementType.CHAPTER) as Chapter;
			var insuredSection:Section = ElementFactory.createElement("S1",chInsured,ElementType.SECTION) as Section;  

			var q_lastname : SingleAnswerQuestion = ElementFactory.createElement("Q1",insuredSection,ElementType.SINGLE_SELECTED_ANSWER_QUESTION) as SingleAnswerQuestion;
			var q_firstname :SingleAnswerQuestion = ElementFactory.createElement("Q2",insuredSection,ElementType.SINGLE_SELECTED_ANSWER_QUESTION) as SingleAnswerQuestion; 				 
			var q_address : SingleAnswerQuestion = ElementFactory.createElement("Q3",insuredSection,ElementType.SINGLE_SELECTED_ANSWER_QUESTION) as SingleAnswerQuestion;
			var q_postal_code :SingleAnswerQuestion = ElementFactory.createElement("Q4",insuredSection,ElementType.SINGLE_SELECTED_ANSWER_QUESTION) as SingleAnswerQuestion;
			var q_city :SingleAnswerQuestion = ElementFactory.createElement("Q5",insuredSection,ElementType.SINGLE_SELECTED_ANSWER_QUESTION) as SingleAnswerQuestion; 				 
			var q_quality :SingleAnswerQuestion = ElementFactory.createElement("Q6",insuredSection,ElementType.SINGLE_SELECTED_ANSWER_QUESTION) as SingleAnswerQuestion;
			var q_mail :SingleAnswerQuestion = ElementFactory.createElement("Q7",insuredSection,ElementType.SINGLE_SELECTED_ANSWER_QUESTION) as SingleAnswerQuestion; 				 
			var q_home_number :SingleAnswerQuestion = ElementFactory.createElement("Q8",insuredSection,ElementType.SINGLE_SELECTED_ANSWER_QUESTION) as SingleAnswerQuestion;
			var q_mon_number :SingleAnswerQuestion = ElementFactory.createElement("Q9",insuredSection,ElementType.SINGLE_SELECTED_ANSWER_QUESTION) as SingleAnswerQuestion; 				 
			var q_work_number :SingleAnswerQuestion = ElementFactory.createElement("Q10",insuredSection,ElementType.SINGLE_SELECTED_ANSWER_QUESTION) as SingleAnswerQuestion;
			
			insuredSection.displayObject.naturalParentId = "insured";
			q_lastname.displayObject.naturalParentId  = "insured";
			q_lastname.displayObject.naturalParentId  = "insured";
			q_firstname.displayObject.naturalParentId  = "insured";				 
			q_address.displayObject.naturalParentId  = "insured";
			q_postal_code.displayObject.naturalParentId  = "insured";
			q_city.displayObject.naturalParentId  = "insured"; 				 
			q_quality.displayObject.naturalParentId  = "insured";
			q_mail.displayObject.naturalParentId  = "insured"; 				 
			q_home_number.displayObject.naturalParentId  = "insured";
			q_mon_number.displayObject.naturalParentId  = "insured";				 
			q_work_number.displayObject.naturalParentId  = "insured";
					 
			insuredSection.displayObject.displayAreaId = "txtError";
			q_lastname.displayObject.displayAreaId  = "txtError";
			q_lastname.displayObject.displayAreaId  = "txtError";
			q_firstname.displayObject.displayAreaId  = "txtError";			 
			q_address.displayObject.displayAreaId  = "txtError";
			q_postal_code.displayObject.displayAreaId  = "txtError";
			q_city.displayObject.displayAreaId  = "txtError";				 
			q_quality.displayObject.displayAreaId  = "txtError";
			q_mail.displayObject.displayAreaId  = "txtError"; 				 
			q_home_number.displayObject.displayAreaId  = "txtError";
			q_mon_number.displayObject.displayAreaId  = "txtError";		 
			q_work_number.displayObject.displayAreaId  = "txtError";
			
			insuredSection.displayObject.addBehavior(ElementAttribute.Error,DisplayBehavior.ERROR_DISPLAYAREA);			
			q_lastname.displayObject.addBehavior(ElementAttribute.Error,DisplayBehavior.ERROR_DISPLAYAREA);
			q_lastname.displayObject.addBehavior(ElementAttribute.Error,DisplayBehavior.ERROR_DISPLAYAREA);
			q_firstname.displayObject.addBehavior(ElementAttribute.Error,DisplayBehavior.ERROR_DISPLAYAREA);		 
			q_address.displayObject.addBehavior(ElementAttribute.Error,DisplayBehavior.ERROR_DISPLAYAREA);
			q_postal_code.displayObject.addBehavior(ElementAttribute.Error,DisplayBehavior.ERROR_DISPLAYAREA);
			q_city.displayObject.addBehavior(ElementAttribute.Error,DisplayBehavior.ERROR_DISPLAYAREA);		 
			q_quality.displayObject.addBehavior(ElementAttribute.Error,DisplayBehavior.ERROR_DISPLAYAREA);
			q_mail.displayObject.addBehavior(ElementAttribute.Error,DisplayBehavior.ERROR_DISPLAYAREA); 
			q_home_number.displayObject.addBehavior(ElementAttribute.Error,DisplayBehavior.ERROR_DISPLAYAREA);
			q_mon_number.displayObject.addBehavior(ElementAttribute.Error,DisplayBehavior.ERROR_DISPLAYAREA);
			q_work_number.displayObject.addBehavior(ElementAttribute.Error,DisplayBehavior.ERROR_DISPLAYAREA);
			
			
			///////////////////////////// contract ///////////////////
			
			var chContract:Chapter = ElementFactory.createElement("C5",_form,ElementType.CHAPTER) as Chapter;
			var ContractSection:Section = ElementFactory.createElement("S4",chContract,ElementType.SECTION) as Section;  
		    var q_contract : SingleAnswerQuestion = ElementFactory.createElement("Q13",ContractSection,ElementType.SINGLE_SELECTED_ANSWER_QUESTION) as SingleAnswerQuestion;
		    
		    q_contract.displayObject.addBehavior(ElementAttribute.Error,DisplayBehavior.ERROR_DISPLAYAREA);
			q_contract.displayObject.naturalParentId  = "insured"; 		
			q_contract.displayObject.displayAreaId  = "txtError"; 
			
			///////////////////////////// sinistre ///////////////////
					
			var chsinistre:Chapter = ElementFactory.createElement("C2",_form,ElementType.CHAPTER) as Chapter;			 
			var residSecondaireSection:Section = ElementFactory.createElement("S3",chsinistre,ElementType.SECTION) as Section;  
		    var q_residence : SingleAnswerQuestion = ElementFactory.createElement("Q11",sinistreSection,ElementType.SINGLE_SELECTED_ANSWER_QUESTION) as SingleAnswerQuestion;
		  
		    var a6:Answer = (new Answer("A6")); 
		    a6.setValue("oui");
		    var a7:Answer = (new Answer("A7")); 
		    a7.setValue("non");		    
		    q_residence.addPossibleAnswers(a6); 
		    q_residence.addPossibleAnswers(a7);		    
		    	    
		   
		    residSecondaireSection.displayObject.parentId = "sinistre";
		     
		    residSecondaireSection.displayObject.naturalParentId="sinistre";
		    residSecondaireSection.displayObject.addBehavior(ElementAttribute.Activate,DisplayBehavior.Visible);
		    residSecondaireSection.displayObject.addBehavior(ElementAttribute.Deactivate,DisplayBehavior.InVisible);
		    residSecondaireSection.displayObject.addTransition(new Transition("showAddressTransition",TransitionType._TOSHOW));
		    residSecondaireSection.displayObject.addTransition(new Transition("hideAddressTransition",TransitionType._TOHIDE));
		
		    ///////////////////////////// Dammage ///////////////////
				
			var sinistreSection:Section = ElementFactory.createElement("S2",chsinistre,ElementType.SECTION) as Section;	
			var q_damage : SingleAnswerQuestion = ElementFactory.createElement("Q12",sinistreSection,ElementType.SINGLE_SELECTED_ANSWER_QUESTION) as SingleAnswerQuestion;
		    q_damage.displayObject.naturalParentId = "sinistre2";
		    
		    var a8:Answer = (new Answer("A8")); 
		    a8.setValue("oui");
		    var a9:Answer = (new Answer("A9")); 
		    a9.setValue("non");	
		    q_damage.addPossibleAnswers(a8); 
		    q_damage.addPossibleAnswers(a9);
		    
			var chDamage:Chapter = ElementFactory.createElement("C3",_form,ElementType.CHAPTER) as Chapter;
			var chRecap:Chapter = ElementFactory.createElement("C4",_form,ElementType.CHAPTER) as Chapter;
			
			sinistreSection.displayObject.naturalParentId = "sinistre2";
			sinistreSection.displayObject.addBehavior(ElementAttribute.Activate,DisplayBehavior.EVENT);
			sinistreSection.displayObject.addBehavior(ElementAttribute.Deactivate,DisplayBehavior.EVENT);
			
			//sinistreSection.displayObject.addEvent(ElementAttribute.Activate,new NextSectionEvent(NextSectionEvent.TO_DAMAGE));
			sinistreSection.displayObject.addEvent(ElementAttribute.Deactivate,new NextSectionEvent(NextSectionEvent.TO_RECAP));
			
		    //////////////////////////////////////////////////////////////////
		    
		}

		
	}
}