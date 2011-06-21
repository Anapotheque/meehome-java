package fr.generali.gfb.amf.declarations.sinistre.auto.dto
{
	
    import fr.generali.gfb.amf.declarations.sinistre.commun.dto.*;

    [RemoteClass(alias="fr.generali.gfb.ws.sinistre.dto.auto.DeclarationSinistreVandalismeInput")]
    public class DeclarationSinistreVandalismeDto extends AbstractDeclarationSinistreAutoDto {

        private var _plainte:Boolean;

        private var _dommage:DommageDto;


		public function DeclarationSinistreVandalismeDto() 
		{
			
		}

		/*
		public function DeclarationSinistreVandalismeDto(origineDeclaration:OrigineDeclaration,
		                dateSinistre:Date , assure:AssureDto, heureDebut:String, minuteDebut:String, heureFin:String, minuteFin:String, 
		                circonstances:String, immatriculation:String, plainte:Boolean, dommage:DommageDto) 
		{
		    super(origine, dateSinistre, assure, heureDebut, minuteDebut, heureFin, minuteFin, circonstances, immatriculation);
		    this._plainte = plainte;
		    this._dommage = dommage;
    	}
    	*/

        public function get plainte():Boolean{ return this._plainte;}
        public function set plainte(val:Boolean):void { this._plainte=val;}

        public function get dommage():DommageDto{ return this._dommage;}
        public function set dommage(obj:DommageDto):void { this._dommage=obj;}

    }
	
}