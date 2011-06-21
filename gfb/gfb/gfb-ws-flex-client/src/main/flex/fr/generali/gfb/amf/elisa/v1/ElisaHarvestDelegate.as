package fr.generali.gfb.amf.elisa.v1 {

	import fr.generali.gfb.amf.elisa.v1.dto.DtoRoClient;
	import fr.generali.gfb.amf.elisa.v1.dto.DtoRoDernierePeriode;
	import fr.generali.gfb.amf.elisa.v1.dto.DtoRoHypotheses;
	
	import mx.rpc.AbstractService;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.soap.mxml.WebService;

	public class ElisaHarvestDelegate implements IElisaHarvestRemoteService {
		private var _service:AbstractService = null;
		public function setService(service: AbstractService): void {
			_service = service;
		}
		
		private var _responder: IResponder = null;
		public function setResponder(responder: IResponder): void {
			_responder = responder;
		}
		
		public function ElisaHarvestDelegate() {
			_service = new WebService();
			_service.wsdl = "http://localhost:8080/gfb-ws-webapp/ws/v1/elisaHarvestService?wsdl";
			_service.useProxy = false;
			_service.loadWSDL();
		}
		
		/*public function appelService(login: String, mdp:String): void {
			var token: AsyncToken = _service.appelService.send(login, mdp);
			if (_responder != null)
				token.addResponder(_responder);
		}*/
		
		public function recupererListeProfessions(login:String, mdp:String):void {
			var token: AsyncToken = _service.recupererListeProfessions.send(login, mdp);
			if (_responder != null)
				token.addResponder(_responder);
		}
		
		
		public function tariferRetraiteObligatoire(dtoRoHypotheses:DtoRoHypotheses, login:String, mdp:String):void {
			//appelService(dtoRoHypotheses, login, mdp);
		}
		
		public function recupererInformationsComplementaires(client:DtoRoClient,period:DtoRoDernierePeriode,login:String, mdp:String):void
		{
			
		}


	}
}