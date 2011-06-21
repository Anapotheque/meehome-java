package fr.generali.gfb.amf.declarations.sinistre.auto.dto
{
	
    import fr.generali.gfb.amf.declarations.sinistre.commun.dto.*;

    [RemoteClass(alias="fr.generali.gfb.ws.sinistre.dto.auto.DeclarationSinistreVolInput")]
    public class DeclarationSinistreVolDto extends AbstractDeclarationSinistreAutoDto {

        private var _plainte:Boolean;
        
        private var _type:String;

        private var _dommage:DommageVolDto;

		public function DeclarationSinistreVolDto() 
		{
			super();
		}

		/*
		public function DeclarationSinistreVolDto(origineDeclaration:OrigineDeclaration,
		                dateSinistre:Date , assure:AssureDto, heureDebut:String, minuteDebut:String, heureFin:String, minuteFin:String, 
		                circonstances:String, immatriculation:String, plainte:Boolean, type:String, dommage:DommageVolDto) 
		{
		    super(origine, dateSinistre, assure, heureDebut, minuteDebut, heureFin, minuteFin, circonstances, immatriculation);
		    this._plainte = plainte;
		    this._type = type;
		    this._dommage = dommage;
    	}
    	*/

        public function get plainte():Boolean{ return this._plainte;}
        public function set plainte(val:Boolean):void { this._plainte=val;}

        public function get type():String{ return this._type;}
        public function set type(val:String):void { this._type=val;}

        public function get dommage():DommageVolDto{ return this._dommage;}
        public function set dommage(obj:DommageVolDto):void { this._dommage=obj;}

    }
	
}