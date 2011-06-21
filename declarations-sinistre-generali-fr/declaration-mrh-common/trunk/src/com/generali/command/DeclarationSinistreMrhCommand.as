package com.generali.command {
	
	import com.generali.events.DeclarationSinistreMrhEventResult;
	import com.generali.model.proxy.DeclarationSinistreMrhProxy;
	import com.generali.tracking.Tracker;
	
	import flash.events.EventDispatcher;
	
	import fr.generali.gfb.amf.declarations.sinistre.commun.dto.AbstractDeclarationSinistreDto;
	import fr.generali.gfb.amf.declarations.sinistre.mrh.DeclarationMrhRemoteService;
	import fr.generali.gfb.amf.declarations.sinistre.mrh.dto.DeclarationSinistreMrhBrisDeGlaceDto;
	import fr.generali.gfb.amf.declarations.sinistre.mrh.dto.DeclarationSinistreMrhDegatsDesEauxDto;
	import fr.generali.gfb.amf.declarations.sinistre.mrh.dto.DeclarationSinistreMrhDommageElectriqueDto;
	import fr.generali.gfb.amf.declarations.sinistre.mrh.dto.DeclarationSinistreMrhTempeteEtGreleDto;
	import fr.generali.gfb.amf.declarations.sinistre.mrh.dto.DeclarationSinistreMrhVolCambriolage;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	
	public class DeclarationSinistreMrhCommand extends EventDispatcher implements IResponder {
		
		public static const NAME:String = "DeclarationSinistreMrhCommand";
		private static var _instance:DeclarationSinistreMrhCommand = null;

		private var _declarationSinistreMrhProxy:DeclarationSinistreMrhProxy = null;
		private var _url:String = null;
		
		public function DeclarationSinistreMrhCommand(url:String) {
			this._url = url;
		}
		
		public static function getInstance(url:String):DeclarationSinistreMrhCommand {
			if(_instance)
				return _instance;
			_instance = new DeclarationSinistreMrhCommand(url);
				return _instance;
		}
		
		public function execute(methode:String, dto:AbstractDeclarationSinistreDto):void {
			trace(NAME + ":execute");

			// Utilisation du proxy pour appeler le Delegate et mettre en cache dans le proxy les résultats
			_declarationSinistreMrhProxy = DeclarationSinistreMrhProxy.getInstance(this._url);
			_declarationSinistreMrhProxy.declarationMrhDelegate.setResponder(this);
			
			
			Tracker.getInstance();
			
			
			// Appel du service
			switch(methode) {
				case DeclarationMrhRemoteService.BRIS_DE_GLACE:
					_declarationSinistreMrhProxy.declarationMrhDelegate.declarerBrisDeGlace(dto as DeclarationSinistreMrhBrisDeGlaceDto);
					break;

				case DeclarationMrhRemoteService.DEGAT_DES_EAUX:
					_declarationSinistreMrhProxy.declarationMrhDelegate.declarerDegatDesEaux(dto as DeclarationSinistreMrhDegatsDesEauxDto);
					break;

				case DeclarationMrhRemoteService.DOMMAGE_ELECTRIQUE:
					_declarationSinistreMrhProxy.declarationMrhDelegate.declarerDommageElectrique(dto as DeclarationSinistreMrhDommageElectriqueDto);
					break;

				case DeclarationMrhRemoteService.TEMPETE_ET_GRELE:
					_declarationSinistreMrhProxy.declarationMrhDelegate.declarerTempeteGrele(dto as DeclarationSinistreMrhTempeteEtGreleDto);
					break;
					
				case DeclarationMrhRemoteService.VOL:
					_declarationSinistreMrhProxy.declarationMrhDelegate.declarerVol(dto as DeclarationSinistreMrhVolCambriolage);
					break;
			}
		}
		
		// Données reçues
		public function result(event:Object):void {
			trace(NAME + ":result");

			// Lancement de l'event
			var e:DeclarationSinistreMrhEventResult = new DeclarationSinistreMrhEventResult(DeclarationSinistreMrhEventResult.RESULT);
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