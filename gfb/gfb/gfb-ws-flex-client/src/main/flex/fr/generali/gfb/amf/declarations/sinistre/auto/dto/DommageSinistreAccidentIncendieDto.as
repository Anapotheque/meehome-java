package fr.generali.gfb.amf.declarations.sinistre.auto.dto
{
    import mx.collections.ArrayCollection;


    [RemoteClass(alias="fr.generali.gfb.ws.sinistre.dto.auto.DommageAccidentIncendieInput")]
    public class DommageSinistreAccidentIncendieDto extends DommageDto {

        private var _autresDommagesMateriels:Boolean;

        private var _descriptionDommagesMateriels:String;

        private var _autresPersonnes:String;

        private var _dommagesCorporel:Boolean;

        public function DommageSinistreAccidentIncendieDto()
		{
			
		}

		/*
        public function DommageSinistreAccidentIncendieDto(description:String, depotGarage:Boolean, coordonnesGarage:String, lieuVehicule:String, 
				        autresDommagesMateriels:Boolean, descriptionDommagesMateriels:String, autresPersonnes:String, dommagesCorporel:Boolean )
				        {
				        	super(description, depotGarage, coordonnesGarage, lieuVehicule);
				        	this._autresDommagesMateriels = autresDommagesMateriels;
				        	this._descriptionDommagesMateriels = descriptionDommagesMateriels;
				        	this._autresPersonnes = autresPersonnes;
				        	this._dommagesCorporel = dommagesCorporel;
				        }
		*/

        public function get autresDommagesMateriels():Boolean{ return this._autresDommagesMateriels;}
        public function set autresDommagesMateriels(val:Boolean):void { this._autresDommagesMateriels=val;}

        public function get descriptionDommagesMateriels():String{ return this._descriptionDommagesMateriels;}
        public function set descriptionDommagesMateriels(val:String):void { this._descriptionDommagesMateriels=val;}

        public function get autresPersonnes():String{ return this._autresPersonnes;}
        public function set autresPersonnes(val:String):void { this._autresPersonnes=val;}

        public function get dommagesCorporel():Boolean{ return this._dommagesCorporel;}
        public function set dommagesCorporel(val:Boolean):void { this._dommagesCorporel=val;}
    }
    
}
