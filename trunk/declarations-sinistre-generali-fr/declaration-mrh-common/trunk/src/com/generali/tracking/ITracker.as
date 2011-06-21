package com.generali.tracking
{
	/**
	 * The tracker must impklelment this interface
	 */ 
	public interface ITracker
	{
		/**
		 * Tacker the view représented by the viewId   
		 * 
		 * @param viewId th view Id 
		 * @param the context (used to store infomation about the current view)
		 */ 
		function trackView(viewId:String, context:Object=null):void;   
		
	}
	
}