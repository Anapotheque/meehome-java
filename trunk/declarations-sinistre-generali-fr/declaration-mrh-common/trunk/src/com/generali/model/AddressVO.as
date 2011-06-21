package com.generali.model
{
	import mx.collections.ArrayCollection;
	
	
	[Bindable]
	public class AddressVO
	{
		
		private var _addressVal:String="";
		private var _postal_code:String = "";
		private var _city:String = "";
		
		public var test:String = "a";
		
		
		public function AddressVO()
		{
		}
		
		public function get addressVal():String{
			return this._addressVal;
		}
		
		public function set addressVal(val:String):void{
			this._addressVal = val;
		} 
		
		public function get postal_code():String{
			return this._postal_code;
		}
		
		public function set postal_code(val:String):void{
			this._postal_code = val;
		} 
		
		public function get city():String{
			return this._city;
		}
		
		public function set city(val:String):void{
			this._city = val;
		} 
	}
}