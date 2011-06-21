package com.generali.degatDesEaux.model
{
	import com.generali.model.AddressVO;
	import com.generali.model.InsuredVO;
	
	[Bindable]
	public class ResidenceVO
	{
		private var _main_residence:Boolean = true;
		private var _address:AddressVO = new AddressVO();
		//private var _quality:int = 0;
		public function ResidenceVO()
		{
		}
		public function get main_residence():Boolean{
			return this._main_residence;
		}
		public function set main_residence(val:Boolean):void{
			this._main_residence = val;
		}
		
		public function get address():AddressVO{
			//if (_main_residence)
			//	return new AddressVO();
			return this._address;
		}		
		public function set address(val:AddressVO):void{
			this._address = val;
		}
		
		/*
		public function get quality():int{
			if (_main_residence)
				return 0;
			return this._quality;
		}		
		public function set quality(val:int):void{
			this._quality = val;
		}
		*/
		public function fill(params:Object):void
		{
			params["sinistreResidencePrincipale"] = (main_residence)?"Oui":"Non";
			if (!main_residence)
			{
				params["sinistreAdresse"]= address.addressVal;
				params["sinistreCodePostal"] = address.postal_code;
				params["sinistreVille"] = address.city;
				//params["sinistreQualite"] = InsuredVO.qualityArrColl.getItemAt(quality);
			}
		
		}

	}
}