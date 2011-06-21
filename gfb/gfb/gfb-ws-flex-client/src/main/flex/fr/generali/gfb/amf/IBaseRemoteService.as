package fr.generali.gfb.amf {
	import mx.rpc.AbstractService;
	import mx.rpc.IResponder;
	
	/**
	 * Interface de base d'un remote service
	 * 
	 * @author Stéphane Bouclier <sbouclier@generali.fr>
	 */
	public interface IBaseRemoteService {
		/* Responder utilisé*/
		function setResponder(responder: IResponder): void;
		
		/* Service consommateur */
		function setService(service: AbstractService): void;
	}
}