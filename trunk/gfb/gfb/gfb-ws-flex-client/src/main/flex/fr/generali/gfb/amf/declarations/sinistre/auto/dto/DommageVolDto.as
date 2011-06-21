package fr.generali.gfb.amf.declarations.sinistre.auto.dto
{

[RemoteClass(alias="fr.generali.gfb.ws.sinistre.dto.auto.DommageVolInput")]
	public class DommageVolDto extends DommageDto {

    private var _effetsPersonnels:String;

    public function DommageVolDto()
    {
    	super();
    }
    
    /*
    public function DommageVolDto(description:String, depotGarage:Boolean, coordonnesGarage:String, lieuVehicule:String, 
    						   effetsPersonnels:String) {
        super(description, depotGarage, coordonnesGarage, lieuVehicule);
        this._effetsPersonnels = effetsPersonnels;
    } 
    */   

    public function get effetsPersonnels():String{ return this._effetsPersonnels;}
    public function set effetsPersonnels(val:String):void { this._effetsPersonnels=val;}

 	}
}