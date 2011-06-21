package fr.generali.declaration.sinistre.auto.common.views.events {
	import flash.events.Event;

	public class DeclarationSinistreAutoEvent extends Event {
		public static const TO_ASSURE_EVENT:String = "ToAssureEvent";
		public static const TO_SINISTRE_EVENT:String = "ToSinistreEvent";
		public static const TO_DOMMAGES_EVENT:String = "ToDommagesEvent";
		public static const TO_RECAPITULATIF_EVENT:String = "ToRecapitulatifEvent";
		public static const TO_FINAL_EVENT:String = "ToFinalEvent";

		public function DeclarationSinistreAutoEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false) {
			super(type, bubbles, cancelable);
		}
	}
}