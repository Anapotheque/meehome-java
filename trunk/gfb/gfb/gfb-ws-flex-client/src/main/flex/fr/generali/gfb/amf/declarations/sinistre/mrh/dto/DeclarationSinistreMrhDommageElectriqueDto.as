package fr.generali.gfb.amf.declarations.sinistre.mrh.dto
{
	import fr.generali.gfb.amf.declarations.sinistre.commun.dto.*;
	
	[RemoteClass(alias="fr.generali.gfb.ws.sinistre.dto.mrh.DeclarationSinistreDommageElectriqueInput")]
	public class DeclarationSinistreMrhDommageElectriqueDto extends AbstractDeclarationSinistreDto
	{
		
	    private var _type:String;
	
	    private var _marque:String;
	
	    private var _modele:String;
	
	    private var _dateAchat:Date;
	
	    private var _valeurAchat:String;
	
	    private var _descriptionDommage:String;
	

        public function DeclarationSinistreMrhDommageElectriqueDto() {
	        super();
        }
        	
        public function get type():String{ return this._type;}
        public function set type(val:String):void { this._type=val;}

        public function get marque():String{ return this._marque;}
        public function set marque(val:String):void { this._marque=val;}

        public function get modele():String{ return this._modele;}
        public function set modele(val:String):void { this._modele=val;}

        public function get dateAchat():Date{ return this._dateAchat;}
        public function set dateAchat(val:Date):void { this._dateAchat=val;}

        public function get valeurAchat():String{ return this._valeurAchat;}
        public function set valeurAchat(val:String):void { this._valeurAchat=val;}

        public function get descriptionDommage():String{ return this._descriptionDommage;}
        public function set descriptionDommage(val:String):void { this._descriptionDommage=val;}
        
 	}

}