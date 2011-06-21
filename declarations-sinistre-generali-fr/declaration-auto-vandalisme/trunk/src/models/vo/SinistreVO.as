package models.vo {
	import mx.messaging.channels.StreamingAMFChannel;
	
	[Bindable]
	public class SinistreVO {

		private var _date:String = "";
		private var _selectHoraire:String = "";
		private var _heureDebut:int = 0;
		private var _minuteDebut:int = 0;
		private var _heureFin:int = 0;
		private var _minuteFin:int = 0;
		private var _circonstances:String = "";
		private var _plainte:String = "";
		
		public function SinistreVO() {
		}

		/****************************
		 * 		SETTERS/GETTERS		*
		 ****************************/

		public function set date(val:String):void {
			this._date = val;
		}
		public function get date():String {
			return this._date;
		}

		public function set selectHoraire(val:String):void {
			this._selectHoraire = val;
		}
		public function get selectHoraire():String {
			return this._selectHoraire;
		}

		public function set heureDebut(val:int):void {
			this._heureDebut = val;
		}
		public function get heureDebut():int {
			return this._heureDebut;
		}

		public function set minuteDebut(val:int):void {
			this._minuteDebut = val;
		}
		public function get minuteDebut():int {
			return this._minuteDebut;
		}

		public function set heureFin(val:int):void {
			this._heureFin = val;
		}
		public function get heureFin():int {
			return this._heureFin;
		}
		
		public function set minuteFin(val:int):void {
			this._minuteFin = val;
		}
		public function get minuteFin():int {
			return this._minuteFin;
		}
		
		public function set circonstances(val:String):void {
			this._circonstances = val;
		}
		public function get circonstances():String {
			return this._circonstances;
		}
		
		public function set plainte(val:String):void {
			this._plainte = val;
		}
		public function get plainte():String {
			return this._plainte;
		}

		/************************
		 * 		FONCTIONS		*
		 ************************/

		public function getPostParams(params:Object):void {			
			params['sinDate'] = this.date;
			params['sinSelectHoraire'] = this.heureDebut;
			params['sinHeureDebut'] = this.heureDebut;
			params['sinMinuteDebut'] = this.minuteDebut;
			params['sinHeureFin'] = this.heureFin;
			params['sinMinuteFin'] = this.minuteFin;
			params['sinCirconstances'] = this.circonstances;
			params['sinPlainte'] = this.plainte;
		}

		public function toString():String {
			var str:String = "\ndate = " + this.date
				+ "\nselectHoraire = " + this.selectHoraire 
				+ "\nheureDebut = " + this.heureDebut
				+ "\nminuteDebut = " + this.minuteDebut
				+ "\nheureFin = " + this.heureFin
				+ "\nminuteFin = " + this.minuteFin
				+ "\ncirconstances = " + this.circonstances
				+ "\nplainte = " + this.plainte
				+ "\n";
			return str;
		}		
	}
}