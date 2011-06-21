package com.generali.degatDesEaux.events.ui
{
	import flash.events.MouseEvent;
	
	
	/**
	 * The Cairngorm event broadcast when the user attempts to delete an application
	 */
	public class SteperUpdateEvent extends MouseEvent
	 
	{	
		
		public static const TO_DECREMENT:String = "ToDecrement";
		public var deleted:int;
		public function SteperUpdateEvent(name:String,deleted:int) :void 
		{
			super(name);		
			this.deleted = deleted;	
		}
		
	}	
}
