package fr.generali.gfb.amf.declarations.sinistre.auto
{
	import fr.generali.gfb.amf.declarations.sinistre.auto.dto.DeclarationSinistreAccidentIncendieDto;
	import fr.generali.gfb.amf.declarations.sinistre.auto.dto.DeclarationSinistreVandalismeDto;
	import fr.generali.gfb.amf.declarations.sinistre.auto.dto.DeclarationSinistreVolDto;
	
	public interface IDeclarationAutoRemoteService
	{

		function declarerAccident(declarationSinistreAccidentIncendieDto:DeclarationSinistreAccidentIncendieDto):void;
		
		function declarerIncendie(declarationSinistreAccidentIncendieDto:DeclarationSinistreAccidentIncendieDto):void;
		
		function declarerVandalisme(declarationSinistreVandalismeDto:DeclarationSinistreVandalismeDto):void
		
		function declarerVol(declarationSinistreVolDto:DeclarationSinistreVolDto):void
		
				
	}
}