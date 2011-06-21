package com.generali.degatDesEaux.model
{
	[Bindable]
	public class AccidentVO
	{
		private var _accidentDate:String = "";
		
		private var _cause1:Boolean = false;
		private var _cause2:Boolean = false;
		private var _cause3:Boolean = false;
		private var _cause4:Boolean = false;
		private var _cause5:Boolean = false;
		private var _cause6:Boolean = false;
		private var _cause7:Boolean = false;
		
		private var _cause51:Boolean = false;
		private var _cause52:Boolean = false;
		private var _cause53:Boolean = false;
		private var _cause54:Boolean = false;
		private var _cause55:Boolean = false;
		
		private var _other55:String = "";
		private var _other6:String = "";
		
		private var _origin:int;
		private var _otherOrigin:String;
		private var _fixable:Boolean;
		
		
		public function AccidentVO()
		{
		}
		public function get accidentDate():String{ return this._accidentDate;}
		public function set accidentDate(val:String):void { this._accidentDate=val;}
		
		public function get cause1():Boolean{ return _cause1;}
		public function set cause1(val:Boolean):void{_cause1 = val;}
		
		public function get cause2():Boolean{ return _cause2;}
		public function set cause2(val:Boolean):void{_cause2 = val;}
		
		public function get cause3():Boolean{ return _cause3;}
		public function set cause3(val:Boolean):void{_cause3 = val;}
		
		public function get cause4():Boolean{ return _cause4;}
		public function set cause4(val:Boolean):void{_cause4 = val;}
		
		public function get cause5():Boolean{ return _cause5;}
		public function set cause5(val:Boolean):void{_cause5 = val;}
		
		public function get cause6():Boolean{ return _cause6;}
		public function set cause6(val:Boolean):void{_cause6 = val;}
		
		public function get cause7():Boolean{ return _cause7;}
		public function set cause7(val:Boolean):void{_cause7 = val;}
		
		public function get cause51():Boolean{ return _cause51 && _cause5;}
		public function set cause51(val:Boolean):void{_cause51 = val;}
		
		public function get cause52():Boolean{ return _cause52 && _cause5;}
		public function set cause52(val:Boolean):void{_cause52 = val;}
		
		public function get cause53():Boolean{ return _cause53 && _cause5;}
		public function set cause53(val:Boolean):void{_cause53 = val;}
		
		public function get cause54():Boolean{ return _cause54 && _cause5;}
		public function set cause54(val:Boolean):void{_cause54 = val;}
		
		public function get cause55():Boolean{ return _cause55 && _cause5;}
		public function set cause55(val:Boolean):void{_cause55 = val;}
		
		public function get other55():String{ 
		 if (! _cause5){
		 	return "";
		 }
		return _other55;
		}
		public function set other55(val:String):void{_other55 = val;}
		
		public function get other6():String{ 
		 if (! _cause6){
		 	return "";
		 }
		return _other6;
		}
		public function set other6(val:String):void{_other6 = val;}
		
		public function get origin():int{ return _origin;}
		public function set origin(val:int):void{_origin = val;}

		public function set otherOrigin(val:String):void{ _otherOrigin = val;}

		public function get otherOrigin():String{ 
		 if (! (_origin == 5)){
		 	return "";
		 }
		return _otherOrigin;
		}
		
		public function get fixable():Boolean{ return _fixable;}
		public function set fixable(val:Boolean):void{_fixable = val;}
		
		
		public function fill(params:Object):void
		{
			
			params["sinistreDate"] = _accidentDate;
			var arr:Array = new Array();
			if(_cause1)
				arr.push("Fuite d'une canalisation, d'un tuyau");
			if(_cause2)
				arr.push("Fuite ou débordement de gouttières");
			if(_cause3)
				arr.push("Débordement d'appareils (évier, lavabo, machine à laver...)");
			if(_cause4)
				arr.push("Débordement ou renversement de récipients (bassine, seau, aquarium...)");	
			if(_cause7)
				arr.push("Cause inconnue");
			params["sinistreCause"]=arr;
			
			var arrInf:Array = new Array();
			if(_cause51)
				arrInf.push("Toiture");
			if(_cause52)
				arrInf.push("Terrasse");
			if(_cause53)
				arrInf.push("Façade");
			if(cause54)
				arrInf.push("Balcon");
			if(_other55!="")
				arrInf.push(other55);
			params["sinistreInfiltration"]=arrInf;
			
			params["sinistreAutreCause"] = other6;
			
			var originStg:String=null;
			if(_origin==1)
				originStg = "Dans les locaux occupés par l'assuré";
			if(_origin==2)
				originStg = "Dans les parties communes de l'immeuble";
			if(_origin==3)
				originStg = "Dans un appartement voisin";
			if(_origin==4)
				originStg = "Dans un immeuble voisin";
			if(_origin==5)
				originStg = _otherOrigin;
			
			if(originStg!=null)
				params["sinistreOrigine"] = originStg;
				
				
			params["sinistreCauseReparee"] = (fixable)?"Oui":"Non";
		}
	}
}