package com.generali.events.ui
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	/**
	 * The Cairngorm event broadcast when the user attempts to delete an application
	 */
	public class NextSectionEvent extends MouseEvent
	{	
		public static const TO_SINISTRE:String = "ToSinistreEvent";
		public static const TO_INSURED:String = "ToInsured";
		public static const TO_SINISTRE_SECOND:String = "ToSinistre2Event";
		public static const TO_DAMAGE:String = "ToDamageEvent";
		public static const TO_RECAP:String = "ToRecapEvent";
		public static const TO_FINAL:String = "ToFinalEvent";
	
		public var ID:String;
		
		public function NextSectionEvent(name:String) :void 
		{
			super(name);
			ID=name;			
		}
		
		override public function clone():Event{
			return new NextSectionEvent(ID);
		} 
		
	}	
}
