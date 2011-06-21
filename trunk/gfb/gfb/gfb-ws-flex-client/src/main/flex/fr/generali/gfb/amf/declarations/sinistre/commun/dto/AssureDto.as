package fr.generali.gfb.amf.declarations.sinistre.commun.dto {
	
	[RemoteClass(alias="fr.generali.gfb.ws.sinistre.dto.AssureInput")]
	public class AssureDto {
		
	    private var _numeroContrat:String;
		private var _numeroClient:String;
		private var _idRceClient:String;
		private var _codeCompagnie:String;
		private var _codePortefeuille:String;
	    private var _nom:String;
		private var _prenom:String;
		private var _adresse:String;
		private var _codePostal:String;
		private var _ville:String;
		private var _qualite:String;
		private var _email:String;
		private var _telephoneMobile:String;
		private var _telephoneDomicile:String;
		private var _telephoneBureau:String;	
		private var _isCourtier:String;
		private var _reseau:String;


	    public function AssureDto() {
	        super();
	    }
	
		public function get numeroContrat():String{ return this._numeroContrat;}
		public function set numeroContrat(val:String):void { this._numeroContrat=val;}

		public function get numeroClient():String{ return this._numeroClient;}
		public function set numeroClient(val:String):void { this._numeroClient=val;}

		public function get idRceClient():String{ return this._idRceClient;}
		public function set idRceClient(val:String):void { this._idRceClient=val;}

		public function get codeCompagnie():String{ return this._codeCompagnie;}
		public function set codeCompagnie(val:String):void { this._codeCompagnie=val;}

		public function get codePortefeuille():String{ return this._codePortefeuille;}
		public function set codePortefeuille(val:String):void { this._codePortefeuille=val;}

		public function get nom():String{ return this._nom;}
		public function set nom(val:String):void { this._nom=val;}
		
		public function get prenom():String{ return this._prenom;}
		public function set prenom(val:String):void { this._prenom=val;}

		public function get adresse():String{ return this._adresse;}
		public function set adresse(val:String):void { this._adresse=val;}

		public function get codePostal():String{ return this._codePostal;}
		public function set codePostal(val:String):void { this._codePostal=val;}

		public function get ville():String{ return this._ville;}
		public function set ville(val:String):void { this._ville=val;}

		public function get qualite():String{ return this._qualite;}
		public function set qualite(val:String):void { this._qualite=val;}

		public function get email():String{ return this._email;}
		public function set email(val:String):void { this._email=val;}

		public function get telephoneMobile():String{ return this._telephoneMobile;}
		public function set telephoneMobile(val:String):void { this._telephoneMobile=val;}

		public function get telephoneDomicile():String{ return this._telephoneDomicile;}
		public function set telephoneDomicile(val:String):void { this._telephoneDomicile=val;}

		public function get telephoneBureau():String{ return this._telephoneBureau;}
		public function set telephoneBureau(val:String):void { this._telephoneBureau=val;}	
		
		public function get isCourtier():String{ return this._isCourtier;}
	    public function set isCourtier(val:String):void { this._isCourtier=val;}
	    
	    public function get reseau():String{ return this._reseau;}
	    public function set reseau(val:String):void { this._reseau=val;}
	    
	    
	}
}