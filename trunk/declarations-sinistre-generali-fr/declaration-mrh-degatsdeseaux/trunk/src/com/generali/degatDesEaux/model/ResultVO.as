package com.generali.degatDesEaux.model
{
	import com.generali.model.AddressVO;
	
	[Bindable]
	public class ResultVO
	{
		private var _has_damage:Boolean = true; // 1 oui 2 non -1 aucune entrée
		///// 1 ---> oui
		///// 2 ---> non
		///// 3 ---> Ne sais pas
		private var _other_person:int=2;
		private var _last_name:String="";
		private var _first_name:String ="";
		private var _address:AddressVO = new AddressVO();
		private var _insurer:String ="";
		
		
		public function ResultVO()
		{
		}
		
		public function get has_damage():Boolean{ return _has_damage};
		public function set has_damage(val:Boolean):void  { this._has_damage = val;}
		
		public function get other_person():int{
			return this._other_person;
		}		
		public function set other_person(val:int):void{
			this._other_person = val;
		}
			public function get last_name():String{
			if(other_person!=1)
			return "";	
			return this._last_name;
		}		
		public function set last_name(val:String):void{
			
			this._last_name = val;
		}
		
		public function get first_name():String{
			if(other_person!=1)
			return "";
			return this._first_name;
		}		
		public function set first_name(val:String):void{
			this._first_name = val;
		}
		
		public function get address():AddressVO{
			if(other_person!=1)
			return new AddressVO();
			return this._address;
		}		
		public function set address(val:AddressVO):void{
			this._address = val;
		}
		
		public function get insurer():String{
			if(other_person!=1)
			return "";
			return this._insurer;
		}		
		public function set insurer(val:String):void{
			this._insurer = val;
		}
		
		public function fill(params:Object):void
		{
			var otherPerson:String = null;
			if(other_person==1)
			{
				otherPerson = "Oui";
				params["tiersNom"]=last_name;
				params["tiersPrenom"]=first_name;
				params["tiersAdresse"]=address.addressVal;
				params["tiersCodePostal"]=address.postal_code;
				params["tiersVille"]=address.city;
				params["tiersAssureur"]=insurer;

			}
			if(other_person==2)
				otherPerson = "Non";
			if(other_person==3)
				otherPerson = "Ne sais Pas";
			if(otherPerson!=null)
				params["consequenceDommageSubiParTiers"] = otherPerson;
			
			params["consequenceDommageSubi"]=(has_damage)?"Oui":"Non";
			
		}

	}
}