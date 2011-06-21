package fr.generali.gfb.amf.declarations.sinistre.mrh
{
	import fr.generali.gfb.amf.declarations.sinistre.mrh.dto.DeclarationSinistreMrhBrisDeGlaceDto;
	import fr.generali.gfb.amf.declarations.sinistre.mrh.dto.DeclarationSinistreMrhDegatsDesEauxDto;
	import fr.generali.gfb.amf.declarations.sinistre.mrh.dto.DeclarationSinistreMrhDommageElectriqueDto;
	import fr.generali.gfb.amf.declarations.sinistre.mrh.dto.DeclarationSinistreMrhTempeteEtGreleDto;
	import fr.generali.gfb.amf.declarations.sinistre.mrh.dto.DeclarationSinistreMrhVolCambriolage;
	
	public interface IDeclarationMrhRemoteService
	{
		
		function declarerBrisDeGlace(declarationSinistreMrhBrisDeGlaceDto:DeclarationSinistreMrhBrisDeGlaceDto):void;
		
		function declarerDegatDesEaux(declarationSinistreMrhDegatsDesEauxDto:DeclarationSinistreMrhDegatsDesEauxDto):void;
		
		function declarerDommageElectrique(declarationSinistreMrhDommageElectriqueDto:DeclarationSinistreMrhDommageElectriqueDto):void;
		
		function declarerTempeteGrele(declarationSinistreMrhTempeteEtGreleDto:DeclarationSinistreMrhTempeteEtGreleDto):void;
		
		function declarerVol(declarationSinistreMrhVolDto:DeclarationSinistreMrhVolCambriolage):void;
		
	}
}