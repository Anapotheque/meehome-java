package fr.generali.gfb.amf.declarations.sinistre.auto.dto
{

[RemoteClass(alias="fr.generali.gfb.ws.sinistre.dto.auto.DommageInput")]
 public class DommageDto {

    private var _description:String;

    private var _depotGarage:Boolean;

    private var _coordonnesGarage:String;

    private var _lieuVehicule:String;
    
    public function DommageDto() 
    {
        super();
    }

	/*
    public function DommageDto(description:String, depotGarage:Boolean, coordonnesGarage:String, lieuVehicule:String) {
        super();
        this._description = description;
        this._depotGarage = depotGarage;
        this._coordonnesGarage = coordonnesGarage;
        this._lieuVehicule = lieuVehicule;
    }
    */    

    public function get description():String{ return this._description;}
    public function set description(val:String):void { this._description=val;}

    public function get depotGarage():Boolean{ return this._depotGarage;}
    public function set depotGarage(val:Boolean):void { this._depotGarage=val;}

    public function get coordonnesGarage():String{ return this._coordonnesGarage;}
    public function set coordonnesGarage(val:String):void { this._coordonnesGarage=val;}

    public function get lieuVehicule():String{ return this._lieuVehicule;}
    public function set lieuVehicule(val:String):void { this._lieuVehicule=val;}
 	
 	}
    
}
