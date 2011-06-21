package com.generali.tempeteEtGrele.model
{
	import com.generali.model.AddressVO;
	import com.generali.model.InsuredVO;
	
	[Bindable]
	public class DamageVO
	{ 
		private var _damageDate:String = "";
		
		private var _item1:Boolean = false;
		private var _item2:Boolean = false;
		private var _item3:Boolean = false;
		private var _item4:Boolean = false;
		private var _item5:Boolean = false;
		private var _item7:Boolean = false;	
		
		private var _mainResidence : Boolean ;	
		private var _address:AddressVO ;
		private var _quality:int;
		
		private var _damageSituation:String = "";
		private var _other7:String = "";
		private var _constructionDate:String = "";
		private var _description:String = "";
		private var _isSafe : Boolean =true;	
		
		
		
		public function DamageVO()
		{
			_mainResidence = true;
			_address = new AddressVO();
			_quality = 0;
		}
		public function get damageDate():String{ return this._damageDate;}
		public function set damageDate(val:String):void { this._damageDate=val;}
		
		public function get item1():Boolean{ return _item1;}
		public function set item1(val:Boolean):void{_item1 = val;}
		
		
		public function get item2():Boolean{ return _item2;}
		public function set item2(val:Boolean):void{_item2 = val;}
		
		public function get item3():Boolean{ return _item3;}
		public function set item3(val:Boolean):void{_item3 = val;}
		
		public function get item4():Boolean{ return _item4;}
		public function set item4(val:Boolean):void{_item4 = val;}
		
		public function get item5():Boolean{ return _item5;}
		public function set item5(val:Boolean):void{_item5 = val;}
		
		
		public function get item7():Boolean{ return _item7;}
		public function set item7(val:Boolean):void{_item7 = val;}
		
		public function get other7():String{ return _other7;}
		public function set other7(val:String):void{_other7 = val;}
		
		public function get mainResidence():Boolean{ return _mainResidence;}
		public function set mainResidence(val:Boolean):void{_mainResidence = val;}
		
		public function get isSafe():Boolean{ return _isSafe;}
		public function set isSafe(val:Boolean):void{_isSafe = val;}
			
		public function get damageSituation():String{ return _damageSituation;}
		public function set damageSituation(val:String):void{_damageSituation = val;}
		
		public function get constructionDate():String{ return this._constructionDate;}
		public function set constructionDate(val:String):void { this._constructionDate=val;}
		
		public function get description():String{ return _description;}
		public function set description(val:String):void{_description = val;}
		
		public function get address():AddressVO{
			if (_mainResidence)
				return new AddressVO();
			return this._address;
		}		
		public function set address(val:AddressVO):void{
			this._address = val;
		}
		
		public function get quality():int{
			if (_mainResidence)
				return 0;
			return this._quality;
		}		
		public function set quality(val:int):void{
			this._quality = val;
		}
		public function fill(params:Object):void
		{
			
			
			params["sinistreDate"] = _damageDate;
			params["sinistreCirconstances"] = _damageSituation;
			var arr:Array = new Array();
			var arr1:Array = new Array();
			var index:int=1;
			if(_item1){			
				arr.push("Toiture");
			}
			if(_item2){			
				arr.push("Murs");
			}
			if(_item3){			
				arr.push("Cheminée");
			}
			if(_item4){			
				arr.push("Portail/clôture/mur d'enciente");
			}
			if(_item5){			
				arr.push("Plantations");
			}
			
			if(_item7){			
				params["sinistreAutre"] = _other7;
				
			}
			params["sinistreElementsEndommages"] = arr;
			params["sinistreLogementHabitable"] = (_isSafe)?'oui':'non';
			params["sinistreDescriptionDommages"] = _description;
			params["sinistreDateConstruction"] = _constructionDate;
			params["sinistreResidencePrincipale"] = (!_mainResidence)?"il s’agit de la résidence située à l’adresse suivante :":"oui";
			if (!_mainResidence)
			{
				params["sinistreAdresse"]= _address.addressVal;
				params["sinistreCodePostal"] = _address.postal_code;
				params["sinistreVille"] = _address.city;
				params["sinistreQualite"] = InsuredVO.qualityArrColl.getItemAt(_quality);
			}
		}
	}
}