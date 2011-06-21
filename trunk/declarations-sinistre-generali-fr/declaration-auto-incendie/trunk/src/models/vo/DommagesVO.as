package models.vo {
	
	[Bindable]
	public class DommagesVO {
		
		//Dommages materiels
		private var _description:String = "";
		private var _depotGarage:String = "";
		private var _coordGarage:String = "";
		private var _lieuVehicule:String = "";
		//Autres dommages materiels
		private var _autresDommagesMat:String = "";
		private var _descriptionDommagesMat:String = "";
		private var _autresPersonnes:String = "";
		//Dommages corporels
		private var _dommagesCorporel:String = "";		
		

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

		public function set autresDommagesMat(val:String):void {
			this._autresDommagesMat = val;
		}
		public function get autresDommagesMat():String {
			return this._autresDommagesMat;
		}
		
		public function set descriptionDommagesMat(val:String):void {
			this._descriptionDommagesMat = val;
		}
		public function get descriptionDommagesMat():String {
			return this._descriptionDommagesMat;
		}
		
		public function set autresPersonnes(val:String):void {
			this._autresPersonnes = val;
		}
		public function get autresPersonnes():String {
			return this._autresPersonnes;
		}
		
		public function set dommagesCorporel(val:String):void {
			this._dommagesCorporel = val;
		}
		public function get dommagesCorporel():String {
			return this._dommagesCorporel;
		}
	
		
		/************************
		 * 		FONCTIONS		*
		 ************************/
		
		public function getPostParams(params:Object):void {			
			params['domDescription'] = this.description;
			params['domDepotGarage'] = this.depotGarage;
			params['domCoordGarage'] = this.coordGarage;
			params['domLieuVehicule'] = this.lieuVehicule;
			params['domAutresDommagesMat'] = this.autresDommagesMat;
			params['domDescriptionDommagesMat'] = this.descriptionDommagesMat;
			params['domAutresPersonnes'] = this.autresPersonnes;
			params['domDommagesCorporel'] = this.dommagesCorporel;
		}

		public function toString():String {
			var str:String = "description = " + this.description 
				+ "\ndepotGarage = " + this.depotGarage
				+ "\ncoordGarage = " + this.coordGarage
				+ "\nlieuVehicule = " + this.lieuVehicule
				+ "\nautresDommagesMat = " + this.autresDommagesMat
				+ "\ndescriptionDommagesMat = " + this.descriptionDommagesMat
				+ "\nautresPersonnes = " + this.autresPersonnes
				+ "\ndommagesCorporel = " + this.dommagesCorporel				
				+ "\n";
			return str;
		}
	}
}