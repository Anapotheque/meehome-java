package fr.generali.gfb.amf.elisa.v1 {
	import fr.generali.gfb.amf.IBaseRemoteService;
	import fr.generali.gfb.amf.elisa.v1.dto.DtoRoClient;
	import fr.generali.gfb.amf.elisa.v1.dto.DtoRoDernierePeriode;
	import fr.generali.gfb.amf.elisa.v1.dto.DtoRoHypotheses;
	
	/**
	 * sbouclier
	 * 
	 * Exemple d'utilisation
	 * _elisaHarvestCommand = ElisaHarvestCommand.getInstance("http://localhost:8080/gfb-ws-webapp");	
	 * _elisaHarvestCommand.addEventListener(ElisaHarvestEvent.RESULT, showElisaHarvestResult);
	 * _elisaHarvestCommand.addEventListener(FaultEvent.FAULT, showElisaHarvestFault);
	 * 
	 * _elisaHarvestCommand.execute(ElisaHarvestRemoteService.RECUPERER_LIST_PROFESSION_OPERATION, "servicero", "passwd");
	 */
	public interface IElisaHarvestRemoteService extends IBaseRemoteService {
		function recupererListeProfessions(login:String, mdp:String):void;
		function tariferRetraiteObligatoire(dtoRoHypotheses:DtoRoHypotheses, login:String, mdp:String):void;
		function recupererInformationsComplementaires(client:DtoRoClient,period:DtoRoDernierePeriode,login:String, mdp:String):void;
	}
}