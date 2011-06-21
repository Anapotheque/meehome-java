package com.generali.utils
{
	import mx.core.Application;
	
	public class Util
	{
		private static const DEFAULT_SRV_URL:String = "https://www.souscription.generali.fr/sinistre/declaration-sinistre";
  		private static const DEFAULT_CANCEL_URL:String = "http://www.generali.fr/espace-client/clients.jsp"; 
	
		// Gestion des modes
		public static const MODE_TEST:String = "test";
		public static const SUIVI_PROD_MAIL_ESPACE_CLIENT:String = "EspaceClient_SuiviProd@generali.fr";
		
		public function Util()
		{ 
			
		}
		
		public static function getCancelUrl():String
		{
			var url:String = Application.application.parameters.cancelUrl;
			if (url==null)
				return DEFAULT_CANCEL_URL; 
			return url;
		}
		public static function getServerUrl():String
		{
			var url:String = Application.application.parameters.serverUrl;
			if (url==null)
				return DEFAULT_SRV_URL; 
			return url;
		}
		/** 
  		 * Return  disabled days for calendar from the current date to 10/01/2030
  		 * 
  		**/
  		public static function getDisabledRangeDays():Array {
   			var today:Date = new Date();
  			today.date +=1; 
  			return [new Date(2006,0,11),{rangeStart: today, rangeEnd: new Date(2030,1,10)}];
  		}
	}	
	
}