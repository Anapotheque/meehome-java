package com.generali.model
{
	import mx.collections.ArrayCollection;
	
	[Bindable]
	public class InsuredVO
	{
		private static var _instance:InsuredVO;
		
		private var _last_name:String ="";
		private var _first_name:String ="";
		private var _address : AddressVO = new AddressVO();
		private var _quality:int = 0;
		private var _mail:String = "";
		private var _mobile_number:String ="";
		private var _house_number:String = "";
		private var _work_number:String ="";
		private var _contract_number:String ="";
		private var _client_number:String ="";
		private var _isCourtier:String = "false";
		
		public static var qualityArrColl:ArrayCollection = new ArrayCollection(["Locataire","Propriétaire occupant","Propriétaire non occupant","Copropriétaire occupant","Copropriétaire non occupant"]);

		public function InsuredVO()
		{
		}
		
		public static function getInstance() : InsuredVO {
			if(_instance == null)
				_instance = new InsuredVO();
			return _instance;
		}
		
		public function get isCourtier():String{
			return this._isCourtier;
		}		
		public function set isCourtier(val:String):void{
			this._isCourtier = val;
		}
		
		public function get last_name():String{
			return this._last_name;
		}		
		public function set last_name(val:String):void{
			this._last_name = val;
		}
		
		public function get first_name():String{
			return this._first_name;
		}		
		public function set first_name(val:String):void{
			this._first_name = val;
		}
		
		public function get address():AddressVO{
			return this._address;
		}		
		public function set address(val:AddressVO):void{
			this._address = val;
		}
		
		public function get quality():int{
			return this._quality;
		}		
		public function set quality(val:int):void{
			this._quality = val;
		}
		
		public function get mail():String{
			return this._mail;
		}		
		public function set mail(val:String):void{
			this._mail = val;
		}
		
		public function get mobile_number():String{
			return this._mobile_number;
		}		
		public function set mobile_number(val:String):void{
			this._mobile_number = val;
		}
		
		public function get house_number():String{
			return this._house_number;
		}		
		public function set house_number(val:String):void{
			this._house_number = val;
		}
		
		public function get work_number():String{
			return this._work_number;
		}		
		public function set work_number(val:String):void{
			this._work_number = val;
		}
		
		public function get contract_number():String{
			return this._contract_number;
		}		
		public function set contract_number(val:String):void{
			this._contract_number = val;
		}
		
		public function get client_number():String{
			return this._client_number;
		}		
		public function set client_number(val:String):void{
			this._client_number = val;
		}

		public function fill(params:Object):void {
			params["assureNom"]=last_name;
			params["assurePrenom"]=first_name
			params["assureAdresse"]=address.addressVal;
			params["assureCodePostal"]=address.postal_code;
			params["assureVille"]=address.city
			params["assureQualite"]=qualityArrColl.getItemAt(this.quality);
			params["assureMail"]=mail;
			params["assureTelMobile"]=mobile_number;
			params["assureTelDomicile"]=house_number;
			params["assureTelBureau"]=work_number;
			params["numContrat"]=contract_number;
			params["numClient"]=client_number;
			params["isCourtier"]=isCourtier;
		}		
	}
}