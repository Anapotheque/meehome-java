package fr.generali.declaration.sinistre.auto.common.utils {
	import mx.core.Application;

	public class Url {
		//private static const DEFAULT_SRV_URL:String = "https://www.souscription.generali.fr/sinistre/declaration-sinistre";
  		//private static const DEFAULT_CANCEL_URL:String = "http://www.generali.fr/espace-client/clients.jsp";
  		//private static const DEFAULT_GARAGE_URL:String = "http://fc1.1bis.com/generalirep"; 

		public function Url() {}

		public static function getCancelUrl():String {
			var url:String = Application.application.parameters.cancelUrl;
			return url;
		}
		public static function getServerUrl():String {
			var url:String = Application.application.parameters.serverUrl;
			return url;
		}
		public static function getGarageUrl():String {
			var url:String = Application.application.parameters.urlGarage;
			return url;
		}
	}
}