package fr.generali.gfb.amf.exemple {
	import fr.generali.gfb.amf.BaseRemoteService;
	import fr.generali.gfb.amf.RemoteConstantes;
	import fr.generali.gfb.amf.exemple.dto.ListeNombresEntierDto;
	
	import mx.rpc.IResponder;

	public class CalculRemoteService extends BaseRemoteService implements ICalculRemoteService {
		public function CalculRemoteService(urlRemoteService: String, responder: IResponder = null) {
			super(urlRemoteService, RemoteConstantes.EXEMPLE_CALCUL_AMF_SERVICE_DESTINATION, responder);
		}

		public function additionner(liste:ListeNombresEntierDto) : void {
			callService("additionner", liste);
		}

		public function multiplier(liste:ListeNombresEntierDto) : void {
			callService("multiplier", liste);
		}
	}
}