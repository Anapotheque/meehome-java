package com.generali.degatDesEaux.events.ui
{
	import flash.events.Event;
	
	public class ClearEvent extends Event
	{
		public   static  var ID:String = "ClearForms";
		public function ClearEvent()
		{
			super(ID);
		}
	}
}