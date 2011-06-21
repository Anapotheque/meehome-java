package fr.generali.gfb.amf.declarations.sinistre.mrh.dto
{
	import fr.generali.gfb.amf.declarations.sinistre.commun.dto.*;
	import mx.collections.ArrayCollection;
	
	[RemoteClass(alias="fr.generali.gfb.ws.sinistre.dto.mrh.DeclarationSinistreVolInput")]
	public class DeclarationSinistreMrhVolCambriolage  extends AbstractDeclarationSinistreDto {

	    private var _bien:BienInputDto;
	    private var _isOccupantPresent:Boolean;
	    private var _dureeAbsence:String ;
	    private var _modesOperatoire:ArrayCollection;
	    private var _autreModeOperatoire:String;
	    private var _biensVoles:String;
	    private var _dommagesImmobiliers:String;
	    private var _isPlainteDepose:Boolean;
		
	    public function DeclarationSinistreMrhVolCambriolage() {
			super();
	    }

	    public function get bien():BienInputDto{ return this._bien;}
	    public function set bien(val:BienInputDto):void { this._bien=val;}
	
	    public function get isOccupantPresent():Boolean{ return this._isOccupantPresent;}
	    public function set isOccupantPresent(val:Boolean):void { this._isOccupantPresent=val;}
	
	    public function get dureeAbsence():String{ return this._dureeAbsence;}
	    public function set dureeAbsence(val:String):void { this._dureeAbsence=val;}
	
	    public function get modesOperatoire():ArrayCollection{ return this._modesOperatoire;}
	    public function set modesOperatoire(val:ArrayCollection):void { this._modesOperatoire=val;}
	
	    public function get autreModeOperatoire():String{ return this._autreModeOperatoire;}
	    public function set autreModeOperatoire(val:String):void { this._autreModeOperatoire=val;}
	
	    public function get biensVoles():String{ return this._biensVoles;}
	    public function set biensVoles(val:String):void { this._biensVoles=val;}
	
	    public function get dommagesImmobiliers():String{ return this._dommagesImmobiliers;}
	    public function set dommagesImmobiliers(val:String):void { this._dommagesImmobiliers=val;}

	    public function get isPlainteDepose():Boolean{ return this._isPlainteDepose;}
	    public function set isPlainteDepose(val:Boolean):void { this._isPlainteDepose=val;}
	}
}