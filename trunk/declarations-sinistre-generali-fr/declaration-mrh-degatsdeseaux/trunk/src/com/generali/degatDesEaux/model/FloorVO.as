package com.generali.degatDesEaux.model
{
	[Bindable]
	public class FloorVO
	{
		
		private var _parquet:String="";
		private var _carrelage:String="";
		private var _moquette:String="";
		private var _revetement:String="";		
		private var _other:String = "";
		private var _otherValue:String="";
		
		
		public function FloorVO()
		{
		}
		
		public function get parquet():String{
			return this._parquet;
		}		
		public function set parquet(val:String):void{
			this._parquet = val;
		}
		
		public function get carrelage():String{
			return this._carrelage;
		}		
		public function set carrelage(val:String):void{
			this._carrelage = val;
		}
		
		
		public function get moquette():String{
			return this._moquette;
		}		
		public function set moquette(val:String):void{
			this._moquette = val;
		}
		
		public function get revetement():String{
			return this._revetement;
		}		
		public function set revetement(val:String):void{
			this._revetement = val;
		}
		
		public function get other():String{
			return this._other;
		}		
		public function set other(val:String):void{
			this._other = val;
		}
		
		public function get otherValue():String{
			return this._otherValue;
		}		
		public function set otherValue(val:String):void{
			this._otherValue = val;
		}


	}
}