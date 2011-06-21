package fr.generali.declaration.sinistre.auto.common.models.vo {
	
	[Bindable]
	public class ContratVO {

		private var _numeroContrat:String = "";
		private var _numeroClient:String = "";
		private var _immatriculationVehicule:String = "";

		public function ContratVO() {
		}
		
		/****************************
		 * 		SETTERS/GETTERS		*
		 ****************************/
		 
		public function set numeroContrat(val:String):void {
			this._numeroContrat = val;
		}
		public function get numeroContrat():String {
			return this._numeroContrat;
		}
		
		public function set numeroClient(val:String):void {
			this._numeroClient = val;
		}
		public function get numeroClient():String {
			return this._numeroClient;
		}
		
		public function set immatriculationVehicule(val:String):void {
			this._immatriculationVehicule = val;
		}
		public function get immatriculationVehicule():String {
			return this._immatriculationVehicule;
		}
		
		/************************
		 * 		FONCTIONS		*
		 ************************/

		public function getPostParams(params:Object):void {			
			params['ctrNumeroContrat'] = this.numeroContrat;
			params['ctrNumeroClient'] = this.numeroClient;
			params['ctrImmatriculationVehicule'] = this.immatriculationVehicule;
		}

		public function toString():String {
			var str:String = "numeroContrat = " + this.numeroContrat 
				+ "\nnumeroClient = " + this.numeroClient
				+ "\nimmatriculationVehicule = " + this.immatriculationVehicule
				+ "\n";
			return str;
		}
	}
}