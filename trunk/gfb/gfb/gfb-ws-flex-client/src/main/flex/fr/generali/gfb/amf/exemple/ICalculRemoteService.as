package fr.generali.gfb.amf.exemple {
	import fr.generali.gfb.amf.IBaseRemoteService;
	import fr.generali.gfb.amf.exemple.dto.ListeNombresEntierDto;

	public interface ICalculRemoteService extends IBaseRemoteService {		
		function additionner(liste:ListeNombresEntierDto):void;
		function multiplier(liste:ListeNombresEntierDto):void;
	}
}