package fr.generali.gfb.amf.elisa.v1.command {
	import flash.events.EventDispatcher;
	
	import fr.generali.gfb.amf.elisa.v1.ElisaHarvestRemoteService;
	import fr.generali.gfb.amf.elisa.v1.event.ElisaHarvestEvent;
	import fr.generali.gfb.amf.elisa.v1.proxy.ElisaHarvestProxy;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	
	public class ElisaHarvestCommand extends EventDispatcher implements IResponder {
		
		public static const NAME:String = "ElisaHarvestCommand";
		private static var _instance:ElisaHarvestCommand = null;
		private static var _urlGFB:String = "crotte";	// URL du GeneraliFrontBroker

		private var _elisaHarvestProxy:ElisaHarvestProxy = null;
		
		// Ne pas utiliser: Singleton
		public function ElisaHarvestCommand(urlGFB:String){_urlGFB=urlGFB}

		public static function getInstance(urlGFB:String):ElisaHarvestCommand {
			if(_instance)
				return _instance;
			_instance = new ElisaHarvestCommand(urlGFB);
				return _instance;
		}
		
		public function get proxy() : ElisaHarvestProxy {
			return _elisaHarvestProxy;
		}
		
		/*public function set urlGFB(url:String):void {
			this._urlGFB = url;
		}*/
		
		public function execute(methode:String, login:String, mdp:String):void {
			trace(NAME + ":execute");
			
			if(_urlGFB == null) throw new Error("Merci de spécifier l'url du GeneraliFrontBroker");

			// Utilisation du proxy pour appeler le Service et mettre en cache dans le proxy les résultats
			_elisaHarvestProxy = ElisaHarvestProxy.getInstance(_urlGFB);
			_elisaHarvestProxy.elisaHarvestRemoteService.setResponder(this);

			// Appel du service
			if(methode == ElisaHarvestRemoteService.RECUPERER_LIST_PROFESSION_OPERATION) {
				_elisaHarvestProxy.elisaHarvestRemoteService.recupererListeProfessions(login, mdp);
			}
		}

		// Données reçues
		public function result(event:Object):void {
			trace(NAME + ":result = " + event.result);
			
			// Lancement de l'event
			var e:ElisaHarvestEvent = new ElisaHarvestEvent(ElisaHarvestEvent.RESULT);
			e.data = event.result;
			dispatchEvent(e);
		}

		// Erreur de réception
		public function fault(event:Object):void {
			trace(NAME + ":fault");
			trace("ERROR: " + event.fault.faultString);

			// Lancement de l'event en FaultEvent
			dispatchEvent(event as FaultEvent);
		}

		
	}
}