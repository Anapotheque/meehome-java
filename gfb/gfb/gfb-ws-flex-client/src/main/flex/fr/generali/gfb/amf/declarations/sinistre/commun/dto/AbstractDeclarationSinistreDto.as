package fr.generali.gfb.amf.declarations.sinistre.commun.dto
{
	
	import fr.generali.gfb.amf.declarations.sinistre.commun.dto.AssureDto;
	
	[RemoteClass(alias="fr.generali.gfb.ws.sinistre.dto.AbstractDeclarationSinistreInput")]
	public class AbstractDeclarationSinistreDto
	{
		
	    /**
	     * Provenance de la declaration de sinistre. Si l'origine est un espace
	     * connecté (type espace client), certains paramètres en entrée du service
	     * sont obligatoires et leurs valeurs sont trustables. Dans le cas
	     * déconnecté, aucunne vérification n'est faite.
	     */
	    //private var _origine:OrigineDeclaration;
	    
	    private var _origineString:String;
		
		private var _dateSinistre:Date;
		
		private var _assure:AssureDto;

		private var _mailTrieste:String;

	    public function AbstractDeclarationSinistreDto()
	    {
	    	
	    }
	    /*
	    public function AbstractDeclarationSinistreDto(origine:OrigineDeclaration, dateSinistre:Date , assure:AssureDto ) {
	        super();
	        this._origine = origine;
	        this._dateSinistre = dateSinistre;
	        this._assure = assure;
    	}
    	*/

		//public function get origine():OrigineDeclaration{ return this._origine;}
		//public function set origine(obj:OrigineDeclaration):void { this._origine=obj;}

		public function get origine():String{ return this._origineString;}
		public function set origine(val:String):void { this._origineString=val;}

		public function get assure():AssureDto{ return this._assure;}
		public function set assure(obj:AssureDto):void { this._assure=obj;}

		public function get dateSinistre():Date{ return this._dateSinistre;}
		public function set dateSinistre(val:Date):void { this._dateSinistre=val;}

		public function get mailTrieste():String{ return this._mailTrieste;}
		public function set mailTrieste(val:String):void { this._mailTrieste=val;}

	}

}