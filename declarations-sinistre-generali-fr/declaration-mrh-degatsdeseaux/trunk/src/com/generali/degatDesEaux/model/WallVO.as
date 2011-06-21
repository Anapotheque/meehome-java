package com.generali.degatDesEaux.model
{
	[Bindable]
	public class WallVO
	{
		private var _papier_peint:String="";;
		private var _peinture:String="";;
		private var _autre:String = "";
		private var _autreVAL:String="";;
			
		public function WallVO()
		{
		}
		
		public function get papier_peint():String{
			return this._papier_peint;
		}		
		public function set papier_peint(val:String):void{
			this._papier_peint = val;
		}
		
		public function get peinture():String{
			return this._peinture;
		}		
		public function set peinture(val:String):void{
			this._peinture = val;
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