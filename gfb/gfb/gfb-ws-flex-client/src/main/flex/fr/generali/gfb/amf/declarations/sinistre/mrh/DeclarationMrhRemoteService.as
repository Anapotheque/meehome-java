package fr.generali.gfb.amf.declarations.sinistre.mrh
{
	import fr.generali.gfb.amf.BaseRemoteService;
	import fr.generali.gfb.amf.RemoteConstantes;
	import fr.generali.gfb.amf.declarations.sinistre.mrh.dto.DeclarationSinistreMrhBrisDeGlaceDto;
	import fr.generali.gfb.amf.declarations.sinistre.mrh.dto.DeclarationSinistreMrhDegatsDesEauxDto;
	import fr.generali.gfb.amf.declarations.sinistre.mrh.dto.DeclarationSinistreMrhDommageElectriqueDto;
	import fr.generali.gfb.amf.declarations.sinistre.mrh.dto.DeclarationSinistreMrhTempeteEtGreleDto;
	import fr.generali.gfb.amf.declarations.sinistre.mrh.dto.DeclarationSinistreMrhVolCambriolage;
	
	import mx.rpc.IResponder;		
	
	public class DeclarationMrhRemoteService extends BaseRemoteService implements  IDeclarationMrhRemoteService
	{
		public static const BRIS_DE_GLACE:String		= "declarerBrisDeGlace";
		public static const DEGAT_DES_EAUX:String		= "declarerDegatDesEaux";
		public static const DOMMAGE_ELECTRIQUE:String	= "declarerDommageElectrique";
		public static const TEMPETE_ET_GRELE:String		= "declarerTempeteGrele";
		public static const VOL:String					= "declarerVol";
		
		public function DeclarationMrhRemoteService(urlRemoteService: String, responder: IResponder = null) {
			super(urlRemoteService, RemoteConstantes.MRH_AMF_SERVICE_DESTINATION, responder);
		}
		
		public function declarerBrisDeGlace(declarationSinistreMrhBrisDeGlaceDto:DeclarationSinistreMrhBrisDeGlaceDto):void
		{
	    	callService(BRIS_DE_GLACE, declarationSinistreMrhBrisDeGlaceDto);
		}

		public function declarerDegatDesEaux(declarationSinistreMrhDegatsDesEauxDto:DeclarationSinistreMrhDegatsDesEauxDto):void
		{
			callService(DEGAT_DES_EAUX, declarationSinistreMrhDegatsDesEauxDto);
		}

		public function declarerDommageElectrique(declarationSinistreMrhDommageElectriqueDto:DeclarationSinistreMrhDommageElectriqueDto):void
		{
			callService(DOMMAGE_ELECTRIQUE, declarationSinistreMrhDommageElectriqueDto);
		}

		public function declarerTempeteGrele(declarationSinistreMrhTempeteEtGreleDto:DeclarationSinistreMrhTempeteEtGreleDto):void
		{
			callService(TEMPETE_ET_GRELE, declarationSinistreMrhTempeteEtGreleDto);
		}

		public function declarerVol(declarationSinistreMrhVolDto:DeclarationSinistreMrhVolCambriolage):void
		{
			callService(VOL, declarationSinistreMrhVolDto);
		}
		
	}
	
}