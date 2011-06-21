package fr.generali.gfb.amf.elisa.v1.event {
	import flash.events.Event;
	
	/**
	 * Stéphane Bouclier
	 * 
	 * Event renvoyé lors de l'appel des WS ElisaHarvest
	 */
	public class ElisaHarvestEvent extends Event {

		public static const RESULT:String	= "elisaHarvestEventResult";
		
		private var _data:Object;

		public function ElisaHarvestEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false) {
			super(type, bubbles, cancelable);
		}
		
		public function set data(data:Object):void {
			this._data = data;
		}
		public function get data():Object {
			return this._data;
		}
	}
}