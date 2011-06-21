package fr.generali.declaration.sinistre.auto.common.models.vo {

	[Bindable]
	public class AssureVO {

		private var _nom:String = "";
		private var _prenom:String = "";
		private var _adresse:AdresseVO = new AdresseVO();
		private var _mail:String = "";
		private var _telMobile:String = "";
		private var _telDomicile:String = "";
		private var _telBureau:String = "";
		private var _contrat:ContratVO = new ContratVO();
		private var _isCourtier:String = "false";

		public function AssureVO() {
		}

		/****************************
		 * 		SETTERS/GETTERS		*
		 ****************************/

		public function set nom(val:String):void {
			this._nom = val;
		}
		public function get nom():String {
			return this._nom;
		}
		public function get isCourtier():String{
			return this._isCourtier;
		}		
		public function set isCourtier(val:String):void{
			this._isCourtier = val;
		}

		public function set prenom(val:String):void {
			this._prenom = val;
		}
		public function get prenom():String {
			return this._prenom;
		}

		public function set adresse(val:AdresseVO):void {
			this._adresse = val;
		}
		public function get adresse():AdresseVO {
			return this._adresse;
		}

		public function set mail(val:String):void {
			this._mail = val;
		}
		public function get mail():String {
			return this._mail;
		}

		public function set telMobile(val:String):void {
			this._telMobile = val;
		}
		public function get telMobile():String {
			return this._telMobile;
		}

		public function set telDomicile(val:String):void {
			this._telDomicile = val;
		}
		public function get telDomicile():String {
			return this._telDomicile;
		}

		public function set telBureau(val:String):void {
			this._telBureau = val;
		}
		public function get telBureau():String {
			return this._telBureau;
		}

		public function set contrat(val:ContratVO):void {
			this._contrat = val;
		}
		public function get contrat():ContratVO {
			return this._contrat;
		}
		
		/************************
		 * 		FONCTIONS		*
		 ************************/

		public function getPostParams(params:Object):void {
			params['assureNom'] = this.nom;
			params['assurePrenom'] = this.prenom;
			this.adresse.getPostParams(params);
			params['assureMail'] = this.mail;
			params['assureTelMobile'] = this.telMobile;
			params['assureTelDomicile'] = this.telDomicile;
			params['assureTelBureau'] = this.telBureau;
			params['isCourtier'] = this.isCourtier;
			this.contrat.getPostParams(params);
		}

		public function toString():String {
			var str:String = "nom = " + this.nom 
				+ "\nprenom = " + this.prenom
				+ "\nadresse = " + this.adresse
				+ "\nmail = " + this.mail
				+ "\ntelMobile = " + this.telMobile
				+ "\ntelDomicile = " + this.telDomicile
				+ "\ntelBureau = " + this.telBureau
				+ "\ncontrat = " + this.contrat
				+ "\nisCourtier = " + this.isCourtier
				+ "\n";
			return str;
		}
	}
}