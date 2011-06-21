package fr.generali.declaration.sinistre.auto.common.utils
{
	import flash.external.ExternalInterface;
	
	public class PopUp
	{
		public function PopUp()
		{
		}
		
		public static function openWindow(url : String, width:String, height:String, window : String = "_blank", features : String = "") : void {
			ExternalInterface.call("popUp", url, width, height, "resizable = yes");
		}

	}
}