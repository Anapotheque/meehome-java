package com.generali.events {
	import flash.events.Event;
	
	/**
	 * Event de réception lorsque l'envoi des données du formulaire à GFB a réussi 
	 * @author e02328a
	 * 
	 */	
	public class DeclarationSinistreMrhEventResult extends Event {
		
		public static const RESULT:String	= "declarationSinistreMrhEventResult";
		
		private var _data:String;
		
		public function DeclarationSinistreMrhEventResult(type:String, bubbles:Boolean=false, cancelable:Boolean=false) {
			super(type, bubbles, cancelable);
		}
		
		public function set data(data:String):void {
			this._data = data;
		}
		public function get data():String {
			return this._data;
		}
	}
}