package fr.generali.gfb.amf.elisa.v1.proxy {
	import flash.events.EventDispatcher;
	
	import fr.generali.gfb.amf.elisa.v1.ElisaHarvestRemoteService;
	
	/**
	 * Stéphane Bouclier
	 * 
	 * Proxy pour l'appel des WS ElisaHarvest
	 */
	public class ElisaHarvestProxy extends EventDispatcher {
		
		public static const NAME:String = "ElisaHarvestProxy";
		private static var _instance : ElisaHarvestProxy;
		private static var _urlGFB:String = null;

		public var elisaHarvestRemoteService:ElisaHarvestRemoteService = null;

		public function ElisaHarvestProxy(urlGFB:String) {
			if(ElisaHarvestProxy._instance != null )
				throw new Error("Merci d'utiliser getInstance()");
			
			_urlGFB = urlGFB;
			initializeProxy();
		}

		public static function getInstance(urlGFB:String) : ElisaHarvestProxy {
			if(_instance == null) {
				_instance = new ElisaHarvestProxy(urlGFB);
			}
			return _instance;
		}

		public function initializeProxy(): void {
			elisaHarvestRemoteService = new ElisaHarvestRemoteService(_urlGFB);
		}
	}
}