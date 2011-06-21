package com.generali.degatDesEaux.model
{
	[Bindable]
	public class SolVO
	{
		
		private var _parquet:String="";
		private var _carrelage:String="";
		private var _moquette:String="";
		private var _revetement:String="";		
		private var _autre:String = "";
		private var _autreVAL:String="";
		
		
		public function SolVO()
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
		
		public function get autre():String{
			return this._autre;
		}		
		public function set autre(val:String):void{
			this._autre = val;
		}
		
		public function get autreVAL():String{
			return this._autreVAL;
		}		
		public function set autreVAL(val:String):void{
			this._autreVAL = val;
		}


	}
}