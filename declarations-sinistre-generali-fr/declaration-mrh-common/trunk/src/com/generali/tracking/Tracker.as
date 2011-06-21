package com.generali.tracking


{
	import com.google.analytics.GATracker;
	
	import flash.display.DisplayObject;
	
	import mx.controls.Alert;
	import mx.core.Application;
	
	/**
	 * Encasulates the Trackers
	 * - Google analytics
	 * - TODO NETINSIGHT   
	 */
	public class Tracker implements ITracker 
	{
		/**
		 * Error message
		 */  
		public static const MSG_ERROR:String = " Une erreur technique s'est produite, Merci de bien vouloir réessayer ultérieurement ou de contacter votre interlocuteur Generali habituel.";
		
		/**
		 * Display object 
 		 */ 
		private var _display:DisplayObject;
		
		/**
		 * Tracker account  
		 */
		private var _account:String;
		
		/**
		 * Enable  
		 */
		private var _enabled:Boolean;
		
		/**
		 * Activate the debug mode 
		 */
		private var _debug:Boolean = false;
		
		/**
		 * the root path
		 */  
		private var _rootPath:String;
		 
		/**
		 * some params that can be added
		 */
		private var _params:Object;
		
		
		
		/**
		 * Google Analytics Tracker 
		 */  
		private var _gaTracker:GATracker; 
		
		/**
		 * the current instance 
		 */
		static private var instance:ITracker;
		
		/**
		 *  Initialisation du Tracker
		 */
		public function Tracker(display:DisplayObject,account:String, rootPath:String, enabled:Boolean=true, debug:Boolean=false, params:Object=null)
		{	
			this._display = display;
			this._account = account;
			this._rootPath = rootPath;
			this._enabled = enabled;
			this._debug = debug;
			this._params = params;
			
			if( ! this._enabled){
				return;
			}
			
			//Build the GA tracker
			_buildGATracker();
			
			
		}
		
		/**
		 * Init the Tracker instance 
		 */ 
		public static function initInstance(params:Object=null):ITracker {
			
			try {
				var display:DisplayObject = Application.application.valueOf();
				var account:String = Application.application.parameters.trackingGAAccount;
				
				//TODO get the root path
				var rootPath:String = Application.application.parameters.trackingRootPath;
				
				var debug:Boolean = false;
				if(Application.application.parameters.trackingDebug != null 
					&& "true" == Application.application.parameters.trackingDebug.toLowerCase() ){
					debug = true; 
				}
				
				var enabled:Boolean = new Boolean(Application.application.parameters.trackingEnabled);
				
				instance = new Tracker(display,account, rootPath, enabled, debug, params);
			} catch (e:Error ){
				Alert.show(MSG_ERROR);
				instance = new Tracker(Application.application.valueOf(),null,rootPath,false,false);
			}
			
			return instance;
		}

		/**
		 * Get the current instance
		 */  
		public static function getInstance():ITracker{
			
		   if(instance == null){
				return 	initInstance();
		   }
				
			return instance;
		}
		
		
		/**
		 * Tacker the view représented by the viewId   
		 * 
		 * @param viewId th view Id 
		 * @param the context (used to store infomation about the current view)
		 */ 
		    
		public function trackView(viewId:String, context:Object=null):void {
			
			if( ! this._enabled){
				return;
			}
			
			try {	
			
			   if(_rootPath.charAt(_rootPath.length-1) != '/'  ){
			   		_rootPath = _rootPath +"/";
			   }
			
				var fullPath:String = _rootPath+viewId;
				this._gaTracker.trackPageview(fullPath);
			} catch (e:Error ){
				Alert.show(MSG_ERROR);
			}
		}
		
		/**
		 * build the GA tracker 
		 */ 
		protected function _buildGATracker():void {
			var gaMode:String = "AS3";
		
			if(this._params != null ){
				if( this._params['mode'] != null ){
					gaMode = this._params['mode'];
				}
			}	
		
			this._gaTracker = new GATracker( this._display, this._account, gaMode, this._debug );
		}
		
		   
        /**
         * get the display
         */
        public function get display():DisplayObject
        {
            return _display;
        }  
		
			   
        /**
         * get the display
         */
        public function set display(d:DisplayObject):void
        {
             _display = d;
        }
        
           
        /**
         * get the display
         */
        public function get account():String
        {
            return _account;
        }
		
			   
        /**
         * get the display
         */
        public function set account(a:String):void
        {
             _account = a;
        }
	
		 /**
         * get the 
         */
        public function get debug():Boolean
        {
            return _debug;
        }
		
			   
        /**
         * get the 
         */
        public function set debug(a:Boolean):void
        {
             _debug = a;
        }
		
			 /**
         * get the 
         */
        public function get params():Object
        {
            return _debug;
        }
		
			   
        /**
         * get the 
         */
        public function set params(a:Object):void
        {
             _params = a;
        }
		
	}
		
}