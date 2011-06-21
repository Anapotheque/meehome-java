package com.generali.degatDesEaux.utils
{
	import briffle.dq.interpreter.InterpreterResultEvent;
	import briffle.dq.model.displayObject.DisplayBehavior;
	import briffle.dq.model.displayObject.Transition;
	import briffle.dq.model.elements.Question;
	import briffle.dq.model.elements.base.Element;
	
	import com.generali.views.validation.GeneraliValidator;
	
	import flash.display.DisplayObject;
	import flash.events.Event;
	
	import mx.controls.Alert;
	import mx.core.Container;
	import mx.core.UIComponent;
	import mx.effects.Effect;
	
	public class VisualInterpreter 
	{
		private var parentContainer:Container;
		public function VisualInterpreter(parentContainer:Container)
		{
			this.parentContainer = parentContainer;
		}

		private function findObjectById(id:String): Object
		{
			//var arr :Array = parentContainer.
			/* var arr: Array = parentContainer.transitions; */
			
			return parentContainer[id]; //parentContainer.getChildByName(id);
		}
		
		public function apply(e:InterpreterResultEvent):void
		{
			var dobject:Object
			var o:Object = e.getFormObject();
			if((o!=null) && o is Transition)
			{
				dobject = findObjectById((o as Transition).id);
				if(dobject is Effect)
				{
					(dobject as Effect).play();
				}
			}
			
			//Dispatch an event 
			else if((o!=null) && o is Event)
			  		{	if( e.getResultType()== DisplayBehavior.EVENT.code ){
			 					//var obj:Class = getDefinitionByName( getQualifiedClassName(o))  as Class;
			 					parentContainer.dispatchEvent( o as Event);
							}
							} 
			else if ((o!=null) && o is Element){
				dobject  = findObjectById((o as Element).displayObject.id);
				if (dobject!= null && dobject is DisplayObject){		
					
					// set eleme to visible			
						if( e.getResultType()== DisplayBehavior.Visible.code ){
								(dobject as DisplayObject).visible = true;
							}
					// set eleme to invisible	
						else if( e.getResultType()== DisplayBehavior.InVisible.code ){
							(dobject as DisplayObject).visible = false;
						    }
					// Popup error on the screen
						else if( e.getResultType()== DisplayBehavior.ERROR_POPUP.code ){
								Alert.show((o as Element).state.messages.join(" ;"));
						    }
					//show error in a displayed Area
						else if( e.getResultType()== DisplayBehavior.ERROR_DISPLAYAREA.code ){
								
								var errorDisplayObject:Object  = findObjectById((o as Element).displayObject.displayAreaId);
								if (errorDisplayObject != null)
								{	
									var errors:Array = (o as Element).state.messages;
									if (errors.length>0)					
										(errorDisplayObject as GeneraliValidator).addError((o as Element).state.messages[0].toString());
									if (dobject is UIComponent && o is Question)
									(dobject as UIComponent).setStyle("backgroundColor","#f7b1c8"); 
								}
							}
					
						   // else TODO
					}
				
				} 
		}
	
	}
}