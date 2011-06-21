package fr.generali.declaration.sinistre.auto.common.command {
	
	import flash.events.EventDispatcher;
	
	import fr.generali.declaration.sinistre.auto.common.models.proxy.DeclarationSinistreAutoProxy;
	import fr.generali.declaration.sinistre.auto.common.views.events.DeclarationSinistreAutoEventResult;
	import fr.generali.gfb.amf.declarations.sinistre.auto.DeclarationAutoRemoteService;
	import fr.generali.gfb.amf.declarations.sinistre.auto.dto.AbstractDeclarationSinistreAutoDto;
	import fr.generali.gfb.amf.declarations.sinistre.auto.dto.DeclarationSinistreAccidentIncendieDto;
	import fr.generali.gfb.amf.declarations.sinistre.auto.dto.DeclarationSinistreVandalismeDto;
	import fr.generali.gfb.amf.declarations.sinistre.auto.dto.DeclarationSinistreVolDto;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	
	public class DeclarationSinistreAutoCommand extends EventDispatcher implements IResponder {
		
		public static const NAME:String = "DeclarationSinistreAutoCommand";
		private static var _instance:DeclarationSinistreAutoCommand = null;

		private var _declarationSinistreAutoProxy:DeclarationSinistreAutoProxy = null;
		private var _url:String = null;
		
		public function DeclarationSinistreAutoCommand(url:String) {
			this._url = url;
		}
		
		public static function getInstance(url:String):DeclarationSinistreAutoCommand {
			if(_instance)
				return _instance;
			_instance = new DeclarationSinistreAutoCommand(url);
				return _instance;
		}
		
		public function execute(methode:String, dto:AbstractDeclarationSinistreAutoDto):void {
			trace(NAME + ":execute");

			// Utilisation du proxy pour appeler le Delegate et mettre en cache dans le proxy les résultats
			_declarationSinistreAutoProxy = DeclarationSinistreAutoProxy.getInstance(this._url);
			_declarationSinistreAutoProxy.declarationAutoDelegate.setResponder(this);
			
			// Appel du service
			switch(methode) {
				case DeclarationAutoRemoteService.ACCIDENT:
					_declarationSinistreAutoProxy.declarationAutoDelegate.declarerAccident(dto as DeclarationSinistreAccidentIncendieDto);
					break;

				case DeclarationAutoRemoteService.INCENDIE:
					_declarationSinistreAutoProxy.declarationAutoDelegate.declarerIncendie(dto as DeclarationSinistreAccidentIncendieDto);
					break;

				case DeclarationAutoRemoteService.VANDALISME:
					_declarationSinistreAutoProxy.declarationAutoDelegate.declarerVandalisme(dto as DeclarationSinistreVandalismeDto);
					break;

				case DeclarationAutoRemoteService.VOL:
					_declarationSinistreAutoProxy.declarationAutoDelegate.declarerVol(dto as DeclarationSinistreVolDto);
					break;
			}
		}
		
		// Données reçues
		public function result(event:Object):void {
			trace(NAME + ":result");

			// Lancement de l'event
			var e:DeclarationSinistreAutoEventResult = new DeclarationSinistreAutoEventResult(DeclarationSinistreAutoEventResult.RESULT);
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