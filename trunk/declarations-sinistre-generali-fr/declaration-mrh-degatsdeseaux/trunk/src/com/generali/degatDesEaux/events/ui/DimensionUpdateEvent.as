package com.generali.degatDesEaux.events.ui
{
	import flash.events.Event;
	
	/**
	 * Event broadcasted when a dimension changes.
	 */
	 
	public class DimensionUpdateEvent extends Event
	{	
		public static const ID:String = "DimensionUpdateEvent";
		private var _roomPartType:String;
		private var _texture:String;
		
		public function DimensionUpdateEvent(roomPartType:String, texture:String) :void 
		{
			super(ID);	
			_texture = texture;		
			_roomPartType = roomPartType;
		}
		
		public function get roomPartType():String
		{
			return _roomPartType; 
		}
		
		public function get texture():String
		{
			return _texture; 
		}
		
		override public function clone():Event{
			return new DimensionUpdateEvent(_roomPartType,_texture);
		}
	}	
}
