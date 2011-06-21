package models.vo {
	
	[Bindable]
	public class DommagesVO {
		
		private var _description:String = "";
		private var _depotGarage:String = "";
		private var _coordGarage:String = "";
		private var _lieuVehicule:String = "";
		private var _effetsPersonnels:String = "";

		public function DommagesVO() {
		}

		/****************************
		 * 		SETTERS/GETTERS		*
		 ****************************/

		public function set description(val:String):void {
			this._description = val;
		}
		public function get description():String {
			return this._description;
		}

		public function set depotGarage(val:String):void {
			this._depotGarage = val;
		}
		public function get depotGarage():String {
			return this._depotGarage;
		}

		public function set coordGarage(val:String):void {
			this._coordGarage = val;
		}
		public function get coordGarage():String {
			return this._coordGarage;
		}

		public function set lieuVehicule(val:String):void {
			this._lieuVehicule = val;
		}
		public function get lieuVehicule():String {
			return this._lieuVehicule;
		}

		public function set effetsPersonnels(val:String):void {
			this._effetsPersonnels = val;
		}
		public function get effetsPersonnels():String {
			return this._effetsPersonnels;
		}

		/************************
		 * 		FONCTIONS		*
		 ************************/

		public function getPostParams(params:Object):void {			
			params['domDescription'] = this.description;
			params['domDepotGarage'] = this.depotGarage;
			params['domCoordGarage'] = this.coordGarage;
			params['domLieuVehicule'] = this.lieuVehicule;
			params['domEffetsPersonnels'] = this.effetsPersonnels;
		}

		public function toString():String {
			var str:String = "description = " + this.description 
				+ "\ndepotGarage = " + this.depotGarage
				+ "\ncoordGarage = " + this.coordGarage
				+ "\nlieuVehicule = " + this.lieuVehicule
				+ "\neffetsPersonnels = " + this.effetsPersonnels
				+ "\n";
			return str;
		}
	}
}