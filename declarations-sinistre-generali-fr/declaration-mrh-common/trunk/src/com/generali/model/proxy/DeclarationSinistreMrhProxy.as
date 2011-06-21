package com.generali.model.proxy {
	import flash.events.EventDispatcher;
	
	import fr.generali.gfb.amf.declarations.sinistre.mrh.DeclarationMrhRemoteService;

	public class DeclarationSinistreMrhProxy extends EventDispatcher {

		public static const NAME:String = "DeclarationsSinistreMrhProxy";
		private static var _instance : DeclarationSinistreMrhProxy;

		public var declarationMrhDelegate:DeclarationMrhRemoteService = null;
		private var _data:String = null;
		private var _url:String = null;

		/************************
		 *		Singleton		*
		 ************************/

		public function DeclarationSinistreMrhProxy(url:String) {
			if(DeclarationSinistreMrhProxy._instance != null )
				throw new Error("Merci d'utiliser getInstance()");
			this._url = url;
			initializeProxy();
		}

		public static function getInstance(url:String) : DeclarationSinistreMrhProxy {
			if(_instance == null)
				_instance = new DeclarationSinistreMrhProxy(url);
			return _instance;
		}

		public function initializeProxy(): void {
			declarationMrhDelegate = new DeclarationMrhRemoteService(this._url);
		}

		public function get data():String { return this._data; }
		public function set data(data:String):void {
			this._data = data;
		}
	}
}