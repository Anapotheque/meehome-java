package fr.generali.gfb.amf.declarations.sinistre.mrh.dto
{
	import fr.generali.gfb.amf.declarations.sinistre.commun.dto.*;
	
	import mx.collections.ArrayCollection;
		
	[RemoteClass(alias="fr.generali.gfb.ws.sinistre.dto.mrh.DeclarationSinistreTempeteGreleInput")]
	public class DeclarationSinistreMrhTempeteEtGreleDto  extends AbstractDeclarationSinistreDto
	{


    private var _bien:BienInputDto;

    private var _dateConstruction:Date ;

    private var _circonstances:String ;

    private var _elementsEndommages:ArrayCollection = new ArrayCollection();

    private var _dommages:String;

    private var _isLogementHabitable:Boolean;

    public function DeclarationSinistreMrhTempeteEtGreleDto() {
        super();
    }


        public function get bien():BienInputDto{ return this._bien;}
        public function set bien(val:BienInputDto):void { this._bien=val;}

        public function get dateConstruction():Date{ return this._dateConstruction;}
        public function set dateConstruction(val:Date):void { this._dateConstruction=val;}

        public function get circonstances():String{ return this._circonstances;}
        public function set circonstances(val:String):void { this._circonstances=val;}

        public function get elementsEndommages():ArrayCollection{ return this._elementsEndommages;}
        public function set elementsEndommages(val:ArrayCollection):void { this._elementsEndommages=val;}

        public function get dommages():String{ return this._dommages;}
        public function set dommages(val:String):void { this._dommages=val;}
        
        public function get isLogementHabitable():Boolean{ return this._isLogementHabitable;}
        public function set isLogementHabitable(val:Boolean):void { this._isLogementHabitable=val;}

	}
}