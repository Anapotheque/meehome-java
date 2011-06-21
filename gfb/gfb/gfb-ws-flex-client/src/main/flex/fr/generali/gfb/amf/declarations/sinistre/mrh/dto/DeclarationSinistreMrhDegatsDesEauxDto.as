package fr.generali.gfb.amf.declarations.sinistre.mrh.dto
{
	
        import mx.collections.ArrayCollection;
        
        import fr.generali.gfb.amf.declarations.sinistre.commun.dto.*;
        
        [RemoteClass(alias="fr.generali.gfb.ws.sinistre.dto.mrh.DeclarationSinistreDegatsDesEauxInput")]
        public  class DeclarationSinistreMrhDegatsDesEauxDto extends AbstractDeclarationSinistreDto {

		private var _bien:BienInputDto;  

        /*
         * Informations relatives aux causes du sinistre
         */

        private var _causes:ArrayCollection = new ArrayCollection();

        private var _infiltrations:ArrayCollection = new ArrayCollection();

        private var _autreCause:String;

        private var _origineSinistre:String;

        private var _isCauseReparee:Boolean;

        
        /*
         * Informations relatives aux consequences
         */

        private var _isDommageSubi:Boolean;

        private var _isDommageSubiParTiers:String;

        private var _nomTiers:String;

        private var _prenomTiers:String;

        private var _adresseTiers:String;

        private var _codePostalTiers:String;

        private var _villeTiers:String;

        private var _assureurTiers:String;

        /*
         * Informations relatives aux dommages
         */
        
        private var _nbPiecesEndommagees:int;

        private var _pieces:ArrayCollection = new ArrayCollection();

        public function DeclarationSinistreMrhDegatsDesEauxDto() {
	        super();
        }

        public function get bien():BienInputDto{ return this._bien;}
        public function set bien(val:BienInputDto):void { this._bien=val;}

        public function get causes():ArrayCollection{ return this._causes;}
        public function set causes(val:ArrayCollection):void { this._causes=val;}
        
        public function get infiltrations():ArrayCollection{ return this._infiltrations;}
        public function set infiltrations(val:ArrayCollection):void { this._infiltrations=val;}
        
        public function get autreCause():String{ return this._autreCause;}
        public function set autreCause(val:String):void { this._autreCause=val;}
        
        public function get origineSinistre():String{ return this._origineSinistre;}
        public function set origineSinistre(val:String):void { this._origineSinistre=val;}
        
        public function get isCauseReparee():Boolean{ return this._isCauseReparee;}
        public function set isCauseReparee(val:Boolean):void { this._isCauseReparee=val;}
        
        public function get isDommageSubi():Boolean{ return this._isDommageSubi;}
        public function set isDommageSubi(val:Boolean):void { this._isDommageSubi=val;}
        
        public function get isDommageSubiParTiers():String{ return this._isDommageSubiParTiers;}
        public function set isDommageSubiParTiers(val:String):void { this._isDommageSubiParTiers=val;}
        
        public function get nomTiers():String{ return this._nomTiers;}
        public function set nomTiers(val:String):void { this._nomTiers=val;}
        
        public function get prenomTiers():String{ return this._prenomTiers;}
        public function set prenomTiers(val:String):void { this._prenomTiers=val;}
        
        public function get adresseTiers():String{ return this._adresseTiers;}
        public function set adresseTiers(val:String):void { this._adresseTiers=val;}
        
        public function get codePostalTiers():String{ return this._codePostalTiers;}
        public function set codePostalTiers(val:String):void { this._codePostalTiers=val;}
        
        public function get villeTiers():String{ return this._villeTiers;}
        public function set villeTiers(val:String):void { this._villeTiers=val;}
        
        public function get assureurTiers():String{ return this._assureurTiers;}
        public function set assureurTiers(val:String):void { this._assureurTiers=val;}
        
        public function get nbPiecesEndommagees():int{ return this._nbPiecesEndommagees;}
        public function set nbPiecesEndommagees(val:int):void { this._nbPiecesEndommagees=val;}
        
        public function get pieces():ArrayCollection{ return this._pieces;}
        public function set pieces(val:ArrayCollection):void { this._pieces=val;}
    }
}