package fr.generali.declaration.sinistre.auto.common.views.events
{
	import flash.events.Event;

	public class GestionEffetsPersonnelEvent extends Event
	{
		public static const TO_AFFICHE_EFFETS_PERSONNEL:String = "ToEffetsPersonnelEvent";
		public static const TO_AFFICHE_GARAGE_AGREE:String = "ToGarageAgreeEvent";
		
		public function GestionEffetsPersonnelEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false) {
			super(type, bubbles, cancelable);		
		}
	}
}