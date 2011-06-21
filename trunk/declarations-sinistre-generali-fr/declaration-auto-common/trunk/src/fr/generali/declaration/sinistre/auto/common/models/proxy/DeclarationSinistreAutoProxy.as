package fr.generali.declaration.sinistre.auto.common.models.proxy {
	import flash.events.EventDispatcher;
	
	import fr.generali.gfb.amf.declarations.sinistre.auto.DeclarationAutoRemoteService;

	public class DeclarationSinistreAutoProxy extends EventDispatcher {

		public static const NAME:String = "DeclarationsSinistreAutoProxy";
		private static var _instance : DeclarationSinistreAutoProxy;

		public var declarationAutoDelegate:DeclarationAutoRemoteService = null;
		private var _data:String = null;
		private var _url:String = null;

		/************************
		 *		Singleton		*
		 ************************/

		public function DeclarationSinistreAutoProxy(url:String) {
			if(DeclarationSinistreAutoProxy._instance != null )
				throw new Error("Merci d'utiliser getInstance()");
			this._url = url;
			initializeProxy();
		}

		public static function getInstance(url:String) : DeclarationSinistreAutoProxy {
			if(_instance == null)
				_instance = new DeclarationSinistreAutoProxy(url);
			return _instance;
		}

		public function initializeProxy(): void {
			declarationAutoDelegate = new DeclarationAutoRemoteService(this._url);
		}

		public function get data():String { return this._data; }
		public function set data(data:String):void {
			this._data = data;
		}
	}
}