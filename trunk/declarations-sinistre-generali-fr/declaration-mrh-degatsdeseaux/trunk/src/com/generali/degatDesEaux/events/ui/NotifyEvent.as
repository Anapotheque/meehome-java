package com.generali.degatDesEaux.events.ui
{
	import flash.events.MouseEvent;
	
	
	/**
	 * The Cairngorm event broadcast when the user attempts to delete an application
	 */
	public class NotifyEvent extends MouseEvent
	 
	{	

		public static const Remove_piece:String = "Remove";
		
		public function NotifyEvent(name:String) :void 
		{
			super(name);			
		}
		
	}	
}
