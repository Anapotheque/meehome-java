package fr.generali.gfb.amf.declarations.sinistre.mrh.dto
{
	[RemoteClass(alias="fr.generali.gfb.ws.sinistre.dto.mrh.BienInput")]
	public class BienInputDto
	{

	    /*
	     * Informations relatives au bien
	     */
	    private var _isResidencePrincipale:Boolean;
	
	    private var _adresse:String;
	
	    private var _codePostal:String;
	
	    private var _ville:String;
	
	    //private var _qualite:String;

        public function BienInputDto() 
        {
        	
        }

		/*
        public function BienInputDto(isResidencePrincipale:Boolean, adresse:String, codePostal:String,
        							 ville:String, qualite:String) {
            this._isResidencePrincipale = isResidencePrincipale;
            this._adresse = adresse;
            this._codePostal = codePostal;
            this._ville = _ville;
            //this._qualite = qualite;            
        }
        */

        public function get isResidencePrincipale():Boolean {return _isResidencePrincipale;}
		public function set isResidencePrincipale(val:Boolean):void { this._isResidencePrincipale=val;}
        public function get adresse():String {return _adresse;}   
        public function set adresse(val:String):void { this._adresse=val;}  		
        public function get codePostal():String {return _codePostal;}   
        public function set codePostal(val:String):void { this._codePostal=val;}  		
        public function get ville():String {return _ville;}   
        public function set ville(val:String):void { this._ville=val;}  		
        //public function get qualite():String {return _qualite;}   
        //public function set qualite(val:String):void { this._qualite=val;}  		
	}
}