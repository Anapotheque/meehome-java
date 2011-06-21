package com.generali.events.ui
{
	import flash.events.Event;
	
	public class InitApplicationEvent extends Event
	{
		public static const ID:String = "InitApplication";
		
		public function InitApplicationEvent(name:String) :void 
		{
			super(name);		
		}

	}
}