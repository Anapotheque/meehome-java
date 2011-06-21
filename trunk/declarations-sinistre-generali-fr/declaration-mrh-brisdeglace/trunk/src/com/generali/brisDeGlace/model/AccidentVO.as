package com.generali.brisDeGlace.model
{
	[Bindable]
	public class AccidentVO
	{
		private var _accidentDate:String = "";
		
		private var _accidentdGoodType1:Boolean = false;
		private var _accidentdGoodType2:Boolean = false;
		private var _accidentdGoodType3:Boolean = false;
		private var _accidentdGoodType4:Boolean = false;
		private var _accidentdGoodType5:Boolean = false;
		private var _accidentdGoodType6:Boolean = false;
		private var _accidentdGoodType7:Boolean = false;	
		
		
		private var _accidentdGoodSurface1:String = "";
		private var _accidentdGoodSurface2:String = "";
		private var _accidentdGoodSurface3:String = "";
		private var _accidentdGoodSurface4:String = "";
		private var _accidentdGoodSurface5:String = "";
		private var _accidentdGoodSurface6:String = "";
		private var _accidentdGoodSurface7:String = "";	
		
		private var _otherAccidentedGood7:String = "";	
		
			
		private var _accidentSituation:String = "";
		private var _purchaseDate:String = "";
		private var _comments:String = "";
		
		
		public function AccidentVO()
		{
		}
		public function get accidentDate():String{ return this._accidentDate;}
		public function set accidentDate(val:String):void { this._accidentDate=val;}
		
		public function get accidentdGoodType1():Boolean{ return _accidentdGoodType1;}
		public function set accidentdGoodType1(val:Boolean):void{_accidentdGoodType1 = val;}
		public function get accidentdGoodSurface1():String{ return _accidentdGoodSurface1;}
		public function set accidentdGoodSurface1(val:String):void{_accidentdGoodSurface1 = val;}
		
		
		public function get accidentdGoodType2():Boolean{ return _accidentdGoodType2;}
		public function set accidentdGoodType2(val:Boolean):void{_accidentdGoodType2 = val;}
		public function get accidentdGoodSurface2():String{ return _accidentdGoodSurface2;}
		public function set accidentdGoodSurface2(val:String):void{_accidentdGoodSurface2 = val;}
		
		public function get accidentdGoodType3():Boolean{ return _accidentdGoodType3;}
		public function set accidentdGoodType3(val:Boolean):void{_accidentdGoodType3 = val;}
		public function get accidentdGoodSurface3():String{ return _accidentdGoodSurface3;}
		public function set accidentdGoodSurface3(val:String):void{_accidentdGoodSurface3 = val;}
		
		public function get accidentdGoodType4():Boolean{ return _accidentdGoodType4;}
		public function set accidentdGoodType4(val:Boolean):void{_accidentdGoodType4 = val;}
		public function get accidentdGoodSurface4():String{ return _accidentdGoodSurface4;}
		public function set accidentdGoodSurface4(val:String):void{_accidentdGoodSurface4 = val;}
		
		public function get accidentdGoodType5():Boolean{ return _accidentdGoodType5;}
		public function set accidentdGoodType5(val:Boolean):void{_accidentdGoodType5 = val;}
		public function get accidentdGoodSurface5():String{ return _accidentdGoodSurface5;}
		public function set accidentdGoodSurface5(val:String):void{_accidentdGoodSurface5 = val;}
		
		public function get accidentdGoodType6():Boolean{ return _accidentdGoodType6;}
		public function set accidentdGoodType6(val:Boolean):void{_accidentdGoodType6 = val;}
		public function get accidentdGoodSurface6():String{ return _accidentdGoodSurface6;}
		public function set accidentdGoodSurface6(val:String):void{_accidentdGoodSurface6 = val;}
		
		public function get accidentdGoodType7():Boolean{ return _accidentdGoodType7;}
		public function set accidentdGoodType7(val:Boolean):void{_accidentdGoodType7 = val;}
		public function get accidentdGoodSurface7():String{ return _accidentdGoodSurface7;}
		public function set accidentdGoodSurface7(val:String):void{_accidentdGoodSurface7 = val;}
		
		
		public function get otherAccidentedGood7():String{ return _otherAccidentedGood7;}
		public function set otherAccidentedGood7(val:String):void{_otherAccidentedGood7 = val;}
		
			
		public function get accidentSituation():String{ return _accidentSituation;}
		public function set accidentSituation(val:String):void{_accidentSituation = val;}
		
		public function get purchaseDate():String{ return this._purchaseDate;}
		public function set purchaseDate(val:String):void { this._purchaseDate=val;}
		
		public function get comments():String{ return _comments;}
		public function set comments(val:String):void{_comments = val;}
		
		public function fill(params:Object):void
		{
			
			
			params["sinistreDate"] = _accidentDate;
			params["sinistreCirconstances"] = _accidentSituation;
			params["sinistreCommentaires"] = _comments;
			if(_accidentdGoodType4 || _accidentdGoodType5 || _accidentdGoodType6 || _accidentdGoodType7){			
			params["sinistreDateAchatBien"] = _purchaseDate;
			}
			else
			{
				params["sinistreDateAchatBien"] = "";
			}
			var arr:Array = new Array();
			var arr1:Array = new Array();
			var index:int=0;
			if(_accidentdGoodType1){			
				params["typeBiensEndommages_value"+"_"+index] = "Simple vitrage";
				params["typeBiensEndommages_surface"+"_"+index] = _accidentdGoodSurface1;
				index++;
			}
			if(_accidentdGoodType2){			
				params["typeBiensEndommages_value"+"_"+index] = "Double vitrage";
				params["typeBiensEndommages_surface"+"_"+index] = _accidentdGoodSurface2;
				index++;
			}
			if(_accidentdGoodType3){			
				params["typeBiensEndommages_value"+"_"+index] = "Sur-vitrage";
				params["typeBiensEndommages_surface"+"_"+index] = _accidentdGoodSurface3;
				index++;
			}
			if(_accidentdGoodType4){			
				params["typeBiensEndommages_value"+"_"+index] = "Insert";
				params["typeBiensEndommages_surface"+"_"+index] = _accidentdGoodSurface4;
				index++;
			}
			if(_accidentdGoodType5){			
				params["typeBiensEndommages_value"+"_"+index] = "Vitre de meuble";
				params["typeBiensEndommages_surface"+"_"+index] = _accidentdGoodSurface5;
				index++;
			}
			if(_accidentdGoodType6){			
				params["typeBiensEndommages_value"+"_"+index] = "Miroir";
				params["typeBiensEndommages_surface"+"_"+index] = _accidentdGoodSurface6;
				index++;
			}
			if(_accidentdGoodType7){			
				params["typeBiensEndommages_value"+"_"+index] = _otherAccidentedGood7;
				params["typeBiensEndommages_surface"+"_"+index] = _accidentdGoodSurface7;
				index++;
			}	
			params["NbrTotalTypeBiensEndommages"] = index;
		}
	}
}