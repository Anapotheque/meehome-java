package com.generali.dommageElectrique.model
{	
	[Bindable]
	public class AccidentVO
	{
		private var _accidentDate:String = "";
		
		
		
		
		private var _deviceType:String = "";
		private var _brand:String = "";
		private var _model:String = "";
		private var _purchaseCost:String = "";
		private var _purchaseDate:String = "";
		private var _decription:String = "";
		
		
		public function AccidentVO()
		{
		}
		public function get accidentDate():String{ return this._accidentDate;}
		public function set accidentDate(val:String):void { this._accidentDate=val;}
		
		public function get deviceType():String{ return _deviceType;}
		public function set deviceType(val:String):void{_deviceType = val;}
				
		public function get brand():String{ return _brand;}
		public function set brand(val:String):void{_brand = val;}
		
		public function get model():String{ return _model;}
		public function set model(val:String):void{_model = val;}
		
		public function get purchaseCost():String{ return _purchaseCost;}
		public function set purchaseCost(val:String):void{_purchaseCost = val;}
		
		public function get purchaseDate():String{ return this._purchaseDate;}
		public function set purchaseDate(val:String):void { this._purchaseDate=val;}
		
		public function get decription():String{ return _decription;}
		public function set decription(val:String):void{_decription = val;}
		
		public function fill(params:Object):void
		{
			params["sinistreDate"] = _accidentDate;
			params["sinistreTypeAppareil"] = _deviceType;
			params["sinistreMarque"]=_brand;
			params["sinistreModele"]=_model;
			params["sinistreValeurAchat"] = _purchaseCost;
			params["sinistreDateAchat"] = _purchaseDate;
			params["sinistreCommentaires"] = _decription;
		}
	}
}