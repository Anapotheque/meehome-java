package com.generali.traking


{
	import flash.display.DisplayObject;
	import flash.events.Event;
	
	/**
	 * Encasulates the Trackers
	 * - Google analytics
	 * - TODO NETINSIGHT   
	 */
	public class TrackingEvent extends Event
	{
		
		public static const TRACK_VIEW = "TRACK_VIEW";
		
		/**
		 * View Id  
 		 */ 
		private _viewId:String;
		
		/**
		 * Context  
		 */
		private _context:Object=null;
				
		/**
		 *  Initialisation du Tracker
		 */
		public function TrackingEvent(viewId:String, context:Object)
		{	
			this._viewId = viewId;
			this._context = context;
		}
		   
        /**
         * get the display
         */
        public function get viewId():String
        {
            return _viewId;
        }
			   
        /**
         * get the display
         */
        public function set viewId(d:String):void
        {
             _viewId = d;
        }
        
           
        /**
         * get the display
         */
        public function get context():String
        {
            return _context;
        }
		
			   
        /**
         * get the display
         */
        public function set context(a:String):void
        {
             _context = a;
        }
	
		
	}
		
}