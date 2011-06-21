package fr.generali.gfb.amf.declarations.sinistre.auto.dto
{
    import fr.generali.gfb.amf.declarations.sinistre.commun.dto.*;

    [RemoteClass(alias="fr.generali.gfb.ws.sinistre.dto.auto.DeclarationSinistreAccidentIncendieInput")]
    public class DeclarationSinistreAccidentIncendieDto extends AbstractDeclarationSinistreAutoDto {

        private var _lieu:String;

        private var _dommages:DommageSinistreAccidentIncendieDto;


		public function DeclarationSinistreAccidentIncendieDto() 
		{
		    super();
    	}

		/*
		public function DeclarationSinistreAccidentIncendieDto(origineDeclaration:OrigineDeclaration,
		                dateSinistre:Date , assure:AssureDto, lieu:String, dommages:DommageSinistreAccidentIncendieDto) 
		{
		    super(origineDeclaration, dateSinistre, assure, heureDebut, minuteDebut, heureFin, minuteFin, circonstances, immatriculation);
		    this._lieu = lieu;
		    this._dommages = dommages;
    	}
    	*/

        public function get lieu():String{ return this._lieu;}
        public function set lieu(val:String):void { this._lieu=val;}

        public function get dommages():DommageSinistreAccidentIncendieDto{ return this._dommages;}
        public function set dommages(obj:DommageSinistreAccidentIncendieDto):void { this._dommages=obj;}


    }

    
}
