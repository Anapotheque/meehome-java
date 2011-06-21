package fr.generali.gfb.amf.declarations.sinistre.auto
{
	import fr.generali.gfb.amf.BaseRemoteService;
	import fr.generali.gfb.amf.RemoteConstantes;
	import fr.generali.gfb.amf.declarations.sinistre.auto.dto.DeclarationSinistreAccidentIncendieDto;
	import fr.generali.gfb.amf.declarations.sinistre.auto.dto.DeclarationSinistreVandalismeDto;
	import fr.generali.gfb.amf.declarations.sinistre.auto.dto.DeclarationSinistreVolDto;
	
	import mx.rpc.IResponder;		
	
	public class DeclarationAutoRemoteService extends BaseRemoteService implements  IDeclarationAutoRemoteService
	{
		public static const ACCIDENT:String		= "declarerAccident";
		public static const INCENDIE:String		= "declarerIncendie";
		public static const VANDALISME:String	= "declarerVandalisme";
		public static const VOL:String			= "declarerVol";

		public function DeclarationAutoRemoteService(urlRemoteService: String, responder: IResponder = null) {
			super(urlRemoteService, RemoteConstantes.AUTO_AMF_SERVICE_DESTINATION, responder);
		}
		
		public function declarerAccident(declarationSinistreAccidentIncendieDto:DeclarationSinistreAccidentIncendieDto):void
		{
	    	callService(ACCIDENT, declarationSinistreAccidentIncendieDto);
		}

		public function declarerIncendie(declarationSinistreAccidentIncendieDto:DeclarationSinistreAccidentIncendieDto):void
		{
	    	callService(INCENDIE, declarationSinistreAccidentIncendieDto);
		}

		public function declarerVandalisme(declarationSinistreVandalismeDto:DeclarationSinistreVandalismeDto):void
		{
	    	callService(VANDALISME, declarationSinistreVandalismeDto);
		}

		public function declarerVol (declarationSinistreVolDto:DeclarationSinistreVolDto):void
		{
	    	callService(VOL, declarationSinistreVolDto);
		}
	}
}