package fr.generali.gfb.amf {
	import mx.messaging.ChannelSet;
	import mx.messaging.channels.AMFChannel;
	import mx.rpc.AbstractOperation;
	import mx.rpc.AbstractService;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.remoting.RemoteObject;
	
	/**
	 * Classe de base d'un remote service à étendre obligatoirement pour faciliter le client consommateur
	 * 
	 * @author Stéphane Bouclier <sbouclier@generali.fr>
	 */
	public class BaseRemoteService implements IBaseRemoteService {
		
		public var urlRemoteService: String;
		public var destination: String;
		
		// Service utilisé
		protected var _service: AbstractService;
		public function setService(service: AbstractService): void {
			_service = service;
		}

		// Responder
		protected var _responder: IResponder;
		public function setResponder(responder: IResponder): void {
			_responder = responder;
		}

		// Ne plus utiliser le responder dans le constructeur, on fait un setResponder à partir du service dans la Command
		public function BaseRemoteService(urlRemoteService: String, destination: String, responder: IResponder = null) {
			this.urlRemoteService = urlRemoteService;
			this._responder = responder;
			this.destination = destination;
		}		
		
		// Initialisation du channel
		private function initializeDefaultService(): void {
			_service = new RemoteObject(destination);
			_service.channelSet = new ChannelSet();
			_service.channelSet.addChannel(new AMFChannel("default", urlRemoteService + RemoteConstantes.AMF_PATH_CHANNEL));
		}
		
		// Fonction d'appel
		protected function callService(serviceName: String, ...args): void {
			if (_service == null)
				initializeDefaultService();
			var operation: AbstractOperation = _service.getOperation(serviceName);
			operation.arguments = args;
			var token: AsyncToken = operation.send();
			if (_responder != null)
				token.addResponder(_responder);
		}
	}
}