package com.generali.degatDesEaux.model
{
	[Bindable]
	public class MobilierVO
	{
		
		private var _moobilierDetails:String ="";
	
		public function MobilierVO()
		{
		}
		
		public function get mobilierDetails():String{
			return this._moobilierDetails;
		}
		
		public function set mobilierDetails(val:String):void{
			this._moobilierDetails=val;
		}
	}
}