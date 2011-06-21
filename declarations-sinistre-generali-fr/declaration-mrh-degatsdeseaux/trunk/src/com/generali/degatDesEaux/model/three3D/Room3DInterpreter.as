package com.generali.degatDesEaux.model.three3D
{
	[Bindable]
	public class Room3DInterpreter
	{
		private var _textures:Textures = new Textures();
		private var _roomType:RoomType = new RoomType();
		private var _roomPartType:RoomPartType = new RoomPartType();
		private var _roomPartCategoryType:RoomPartCategoryType = new RoomPartCategoryType();
		public function Room3DInterpreter()
		{
		}
		
		public function get textures():Textures
		{
			return _textures; 
		}
		
		public function get roomType():RoomType
		{
			return _roomType;
		}
		
		public function get roomPartType():RoomPartType
		{
			return _roomPartType;
		}
		
		public function get roomPartCategoryType():RoomPartCategoryType
		{
			return _roomPartCategoryType;
		}
	}
}