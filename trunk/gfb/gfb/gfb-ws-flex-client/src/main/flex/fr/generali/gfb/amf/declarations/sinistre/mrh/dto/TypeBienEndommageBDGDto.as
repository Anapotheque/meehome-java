package fr.generali.gfb.amf.declarations.sinistre.mrh.dto
{
	[RemoteClass(alias="fr.generali.gfb.ws.sinistre.dto.mrh.TypeBienEndommageBDG")]
	public class TypeBienEndommageBDGDto
	{
	
	private var _value:String;
	
	private var _surface:String;

        public function TypeBienEndommageBDGDto() 
        {
        	
        }
/*
        public function TypeBienEndommageBDGDto(value:String, surface:String) {
            super();
            this._value = value;
            this._surface = surface;
        }
*/
        public function get value():String {return _value;}
		public function set value(val:String):void { this._value=val;}
        public function get surface():String {return _surface;}   
        public function set surface(val:String):void { this._surface=val;}     
    }

}



