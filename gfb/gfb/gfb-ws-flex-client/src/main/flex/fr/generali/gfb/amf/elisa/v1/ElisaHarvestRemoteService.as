package fr.generali.gfb.amf.elisa.v1 {

	import fr.generali.gfb.amf.BaseRemoteService;
	import fr.generali.gfb.amf.RemoteConstantes;
	import fr.generali.gfb.amf.elisa.v1.dto.DtoRoClient;
	import fr.generali.gfb.amf.elisa.v1.dto.DtoRoDernierePeriode;
	import fr.generali.gfb.amf.elisa.v1.dto.DtoRoHypotheses;
	
	import mx.rpc.IResponder;
	
	/**
	 * sbouclier
	 * 
	 * Remote Service pour interrogation du GFB par AMF
	 */
	public class ElisaHarvestRemoteService extends BaseRemoteService implements IElisaHarvestRemoteService {
		
		// Services Elisa coté GFB
		public static const RECUPERER_LIST_PROFESSION_OPERATION:String		= "recupererListeProfessions";
		public static const TARIFER_RETRAITE_OBLIGATOIRE_OPERATION:String	= "tariferRetraiteObligatoire";
		public static const RECUPERER_INFORMATIONS_COMPLEMENTAIRES_OPERATION:String		= "recupererInformationsComplementaires";
		
		public function ElisaHarvestRemoteService(urlRemoteService:String, responder: IResponder = null) {
			super(urlRemoteService, RemoteConstantes.ELISA_HARVEST_AMF_SERVICE_DESTINATION, responder);
		}
		
		// Récupération de la liste des professions
		public function recupererListeProfessions(login:String, mdp:String):void {
			callService(RECUPERER_LIST_PROFESSION_OPERATION, login, mdp);
		}
		
		// Tarification de retraite
		public function tariferRetraiteObligatoire(dtoRoHypotheses:DtoRoHypotheses, login:String, mdp:String):void {
			callService(TARIFER_RETRAITE_OBLIGATOIRE_OPERATION, dtoRoHypotheses, login, mdp);
		}
		
		public function recupererInformationsComplementaires(client:DtoRoClient,period:DtoRoDernierePeriode,login:String, mdp:String):void
		{
			callService(RECUPERER_INFORMATIONS_COMPLEMENTAIRES_OPERATION, client, period, login, mdp);
		}
	}
}