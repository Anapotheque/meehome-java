package fr.generali.gfb.amf.declarations.sinistre.auto.dto
{
import fr.generali.gfb.amf.declarations.sinistre.commun.dto.*;

[RemoteClass(alias="fr.generali.gfb.ws.sinistre.dto.auto.AbstractDeclarationSinistreAutoInput")]
	public class AbstractDeclarationSinistreAutoDto extends AbstractDeclarationSinistreDto
	{
	    private var _heureDebut:String;
	
	    private var _minuteDebut:String;
	
	    private var _heureFin:String;
	
	    private var _minuteFin:String;
	
	    private var _circonstances:String;
	
	    private var _immatriculation:String;
	    
		public function AbstractDeclarationSinistreAutoDto() 
		{
			super();	
		}


		/*
		public function AbstractDeclarationSinistreAutoDto(origineDeclaration:OrigineDeclaration,
		                dateSinistre:Date , assure:AssureDto, heureDebut:String, minuteDebut:String, heureFin:String,
		                minuteFin:String, circonstances:String, immatriculation:String) 
		{
		    super(origine, dateSinistre, assure);
		    this._heureDebut = heureDebut;
		    this._minuteDebut = minuteDebut;
		    this._heureFin = heureFin;
		    this._minuteFin = minuteFin;
		    this._circonstances = circonstances;
		    this._immatriculation = immatriculation;
		}
		*/	    
	
	    public function get heureDebut():String{ return this._heureDebut;}
	    public function set heureDebut(val:String):void { this._heureDebut=val;}
	
	    public function get minuteDebut():String{ return this._minuteDebut;}
	    public function set minuteDebut(val:String):void { this._minuteDebut=val;}
	
	    public function get heureFin():String{ return this._heureFin;}
	    public function set heureFin(val:String):void { this._heureFin=val;}
	
	    public function get minuteFin():String{ return this._minuteFin;}
	    public function set minuteFin(val:String):void { this._minuteFin=val;}
	
	    public function get circonstances():String{ return this._circonstances;}
	    public function set circonstances(val:String):void { this._circonstances=val;}
	
	    public function get immatriculation():String{ return this._immatriculation;}
	    public function set immatriculation(val:String):void { this._immatriculation=val;}
	}
}
