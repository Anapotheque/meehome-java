package fr.generali.gfb.amf.declarations.sinistre.mrh.dto		
{
	import mx.collections.ArrayCollection;

	import fr.generali.gfb.amf.declarations.sinistre.commun.dto.*;
	
	[RemoteClass(alias="fr.generali.gfb.ws.sinistre.dto.mrh.DeclarationSinistreBrisGlaceInput")]
	public  class DeclarationSinistreMrhBrisDeGlaceDto extends AbstractDeclarationSinistreDto
	{

	    /*
	     * Informations relatives au sinistre bris de glace
	     */
	    private var _circonstances:String;
	
	    private var _dateAchatBien:Date;
	
	    private var _commentaires:String ;
	
		private var _typeBiensEndommages:ArrayCollection = new ArrayCollection();


		public function DeclarationSinistreMrhBrisDeGlaceDto() 
		{
			super();
		}

		/*
		public function DeclarationSinistreMrhBrisDeGlaceDto(origineDeclaration:OrigineDeclaration,
		                dateSinistre:Date , assure:AssureDto, circonstances:String,
		                dateAchatBien:Date , commentaires:String , typeBiensEndommages:ArrayCollection) 
		{
		    super(origine, dateSinistre, assure);
		    this._circonstances = circonstances;
		    this._dateAchatBien = dateAchatBien;
		    this._commentaires = commentaires;
		    this._typeBiensEndommages = typeBiensEndommages; 
    	}
    	*/

		public function get circonstances():String{ return this._circonstances;}
		public function set circonstances(val:String):void { this._circonstances=val;}

		public function get dateAchatBien():Date{ return this._dateAchatBien;}
		public function set dateAchatBien(val:Date):void { this._dateAchatBien=val;}

		public function get commentaires():String{ return this._commentaires;}
		public function set commentaires(val:String):void { this._commentaires=val;}

		public function get typeBiensEndommages():ArrayCollection{ return this._typeBiensEndommages;}
		public function set typeBiensEndommages(obj:ArrayCollection):void { this._typeBiensEndommages=obj;}
		
	}
}