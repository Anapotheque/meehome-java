package fr.generali.gfb.amf.exemple.dto {
	import mx.collections.ArrayCollection;
	
	[RemoteClass(alias="fr.generali.gfb.ws.exemple.dto.ListeNombresEntierDto")]
	public class ListeNombresEntierDto {
		private var _listNombresEntier:ArrayCollection = new ArrayCollection();
		
		public function ListeNombresEntierDto(){}
		
		public function set listNombresEntier(l:ArrayCollection) : void {
			this._listNombresEntier = l;
		}
		
		public function get listNombresEntier() : ArrayCollection {
			return _listNombresEntier;
		}
	}
}