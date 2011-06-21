package com.generali.utils
{
	import mx.controls.Alert;
	import mx.controls.Label;
	import mx.core.IFlexDisplayObject;
	import mx.rpc.events.FaultEvent;
	
	public class RecapGenerali
	{
		
		public static const MSG_ANNULATION:String = "Vous êtes sur le point d'annuler votre déclaration en ligne.\n"+ 
		"Attention! cette annulation supprime toutes les informations saisies.\n"+ 
		"Souhaitez-vous annuler la déclaration ?";
			 
		private static const MSG_TIMEOUT:String = "Le serveur est momentanément indisponible.\nMerci de bien vouloir réessayer ultérieurement ou de contacter votre interlocuteur Generali habituel. ";
		private	static const MSG_UNREACHABLE_SERVER:String = "Une erreur s'est produite. Votre demande n'a pas été prise en compte.\nMerci de bien vouloir réessayer ultérieurement ou de contacter votre interlocuteur Generali habituel.";
		public static const MSG_SERVER_INITIALISATION:String = "L'Url du serveur n'est pas renseignée pour l'action suivante : ";
		
		[Embed(source="/assets/info_icon.png")]
        private var IconWarning:Class;
		
		public function RecapGenerali():void
		{
			
		}
	  
		
		public function styleText(text:Label):void
		{
	 	 		text.setStyle("textDecoration","underline");
	 	}
	 	
	 	public function styleTextOut(text:Label):void
	 	{
	 	 		text.setStyle("textDecoration","");
	 	}
	 	
		public function faultHandler(e:FaultEvent):void
		{
        	Alert.okLabel = "Fermer";				
			var faultCode:String = e.fault.faultCode.toString();
			if (faultCode.indexOf("Client.Error.RequestTimeout")>=0)
			{
		        Alert.show(MSG_TIMEOUT, "Serveur Timeout", 0x4 ,null,null,IconWarning);
		    }
		    else if (faultCode.indexOf("Server.Error.Request")>=0)
		    {
		        Alert.show(MSG_UNREACHABLE_SERVER, "Serveur Introuvable", 0x4 ,null,null,IconWarning);
		    }
		    else if (faultCode.indexOf("Channel.Security.Error")>=0) 
			{
				Alert.show(MSG_UNREACHABLE_SERVER, "Serveur Introuvable", 0x4 ,null,null,IconWarning);
			}
		    else
		    {
		    	Alert.show(MSG_UNREACHABLE_SERVER, "Serveur Introuvable", 0x4 ,null,null,IconWarning);
		    }
	 	} 
	 	public function getPrintableParams(p:Object):String
  		{
  			var printableParams:String="";
  			for (var key:Object in p) {
				printableParams+="param['"+key+"'] = "+p[key]+"\n";
			}
  			return printableParams;
  		}
	}
}