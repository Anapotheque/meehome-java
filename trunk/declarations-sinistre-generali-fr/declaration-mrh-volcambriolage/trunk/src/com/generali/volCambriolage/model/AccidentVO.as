package com.generali.volCambriolage.model
{
	import com.generali.model.AddressVO;
	import com.generali.model.InsuredVO;
	
	[Bindable]
	public class AccidentVO
	{
		private var _accidentDate:String = "";
		
		
		private var _mode1:Boolean = false; // Effraction
		private var _mode2:Boolean = false; // Escalade
		private var _mode3:Boolean = false; // usage de fausse clé
		private var _mode5:Boolean = false; // Violence 
		private var _mode6:Boolean = false; // Autre
		private var _otherMode:String = "";	
		
		private var _mainResidence : Boolean ;	
		private var _address:AddressVO = new AddressVO();
		//private var _quality:int;
		
		private var _stolenGoods:String = "";
		private var _timeAway:String = "";
		private var _description:String = "";
		private var _claim : Boolean =true;	
		private var _occupant : Boolean =false;	
		
		public function AccidentVO()
		{
			_mainResidence = true;
			_address = new AddressVO();
			//_quality = 0;
		}
		public function get accidentDate():String{ return this._accidentDate;}
		public function set accidentDate(val:String):void { this._accidentDate=val;}
		
		public function get mode1():Boolean{ return _mode1;}
		public function set mode1(val:Boolean):void{_mode1 = val;}
		
		
		public function get mode2():Boolean{ return _mode2;}
		public function set mode2(val:Boolean):void{_mode2 = val;}
		
		public function get mode3():Boolean{ return _mode3;}
		public function set mode3(val:Boolean):void{_mode3 = val;}
		
		
		public function get mode5():Boolean{ return _mode5;}
		public function set mode5(val:Boolean):void{_mode5 = val;}
		
		public function get mode6():Boolean{ return _mode6;}
		public function set mode6(val:Boolean):void{_mode6 = val;}
		
		public function get otherMode():String{ return _otherMode;}
		public function set otherMode(val:String):void{_otherMode = val;}
		
		public function get mainResidence():Boolean{ return _mainResidence;}
		public function set mainResidence(val:Boolean):void{_mainResidence = val;}
		
		public function get claim():Boolean{ return _claim;}
		public function set claim(val:Boolean):void{_claim = val;}
		
		public function get occupant():Boolean{ return _occupant;}
		public function set occupant(val:Boolean):void{_occupant = val;}
		
			
		public function get stolenGoods():String{ return _stolenGoods;}
		public function set stolenGoods(val:String):void{_stolenGoods = val;}
		
		public function get timeAway():String{ return this._timeAway;}
		public function set timeAway(val:String):void
		 { this._timeAway=val;}
		
		public function get description():String{ return _description;}
		public function set description(val:String):void{_description = val;}
		
		public function get address():AddressVO{
			//if (_mainResidence)
			//	return new AddressVO();
			return this._address;
		}		
		public function set address(val:AddressVO):void{
			this._address = val;
		}
		
		/*
		public function get quality():int{
			if (_mainResidence)
				return 0;
			return this._quality;
		}		
		public function set quality(val:int):void{
			this._quality = val;
		}
		*/
		public function fill(params:Object):void
		{		
			params["sinistreDate"] = _accidentDate;
			params["sinistreResidencePrincipale"] = (!_mainResidence)?$("WHICHADDRESS"):$("YES");
			if (!mainResidence)
			{
				params["sinistreAdresse"] = _address.addressVal;
				params["sinistreCodePostal"] = _address.postal_code;
				params["sinistreVille"] = _address.city;
				//params["sinistreQualite"] = InsuredVO.qualityArrColl.getItemAt(_quality);
			}
			
			params["sinistreOccupant"] = (_occupant)?$("YES"):$("NO");
			if (_occupant)
				params["sinistreAbsence"] = "";
			else
				params["sinistreAbsence"] = _timeAway;
			params["sinistreBiens"]  = _stolenGoods;			
			var arr:Array = new Array();
			var arr1:Array = new Array();
			var index:int=1;			
			if(_mode1){			
				arr.push($("BREAKIN"));}
			if(_mode2){			
				arr.push($("CLIMBING"));}
			if(_mode3){ 			
				arr.push($("FALSEKEY"));}
			if(_mode5){			
				arr.push($("VIOLENCE"));}
			if(_mode6){			
				arr.push(_otherMode);}
			params["sinistreMode"] = arr;
			params["sinistrePlainte"] = (claim)?$("YES"):$("NO");
			params["sinistreDommages"] = description;			
		}
	}
}