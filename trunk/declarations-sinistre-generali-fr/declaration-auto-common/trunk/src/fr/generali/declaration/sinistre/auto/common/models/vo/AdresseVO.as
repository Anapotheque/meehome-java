package fr.generali.declaration.sinistre.auto.common.models.vo {
	
	[Bindable]
	public class AdresseVO {

		private var _adresse:String="";
		private var _codePostal:String = "";
		private var _ville:String = "";

		public function AdresseVO() {}

		/****************************
		 * 		SETTERS/GETTERS		*
		 ****************************/

		public function set adresse(val:String):void {
			this._adresse = val; 
		}
		public function get adresse():String {
			return this._adresse;
		}
		
		public function set codePostal(val:String):void {
			this._codePostal = val;
		}
		public function get codePostal():String {
			return this._codePostal;
		}
		
		public function set ville(val:String):void {
			this._ville = val;
		}
		public function get ville():String {
			return this._ville;
		}

		/************************
		 * 		FONCTIONS		*
		 ************************/

		public function getPostParams(params:Object):void {
			params['adrAdresse'] = this.adresse;
			params['adrCodePostal'] = this.codePostal;
			params['adrVille'] = this.ville;
		}

		public function toString():String {
			var str:String = "adresse = " + this.adresse 
				+ "\ncodePostal = " + this.codePostal
				+ "\nville = " + this.ville
				+ "\n";
			return str;
		}
	}
}