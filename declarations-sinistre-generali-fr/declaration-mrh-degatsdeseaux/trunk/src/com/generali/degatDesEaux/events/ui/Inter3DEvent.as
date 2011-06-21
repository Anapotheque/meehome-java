package com.generali.degatDesEaux.events.ui
{
	import flash.events.MouseEvent;
	
	public class Inter3DEvent extends MouseEvent
	{
		public static const SHOW_WALL:String = "SHOW_WALL";
		public static const SHOW_SOL:String = "SHOW_SOL";
		public static const SHOW_PLAFOND:String = "SHOW_PLAFOND";
		public static const SHOW_MOBILIER:String = "SHOW_MOBILIER";
		
		
		public var typePice : String;
		public var index:int;
		public function Inter3DEvent(name:String,typePice:String,index:int)
		{
			super(name);
			this.typePice = typePice;
			this.index =index;
		}

	}
}