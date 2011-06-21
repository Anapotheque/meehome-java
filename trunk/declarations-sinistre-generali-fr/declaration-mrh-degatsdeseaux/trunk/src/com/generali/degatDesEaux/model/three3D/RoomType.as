package com.generali.degatDesEaux.model.three3D
{
	import vegas.data.map.HashMap;
	/* represents Groups in the 3D component */
	public class RoomType
	{
		private var roomTypes:HashMap = new HashMap();
		
				
		public static const SALLE_DE_BAINS:String = "Salle de bains";
		public static const CUISINE:String = "Cuisine";
		public static const SALON: String= "Salon";
		public static const CHAMBRE:String = "Chambre";
		public static const WC:String = "WC";
		public static const AUTRE:String = "Autre";
		
		
		public function RoomType()
		{
			loadRoomTypes();
		}
		
		private function loadRoomTypes():void
		{
			roomTypes.put(SALLE_DE_BAINS,"bathroom");
			roomTypes.put(CUISINE,"kitchen");
			roomTypes.put(SALON,"saloon");
			roomTypes.put(CHAMBRE,"room");
			roomTypes.put(WC,"WC");
			roomTypes.put(AUTRE,"other_group");
		}
		
		public function get3DRoomType(uiRoomType:String):String
		{
			return roomTypes.get(uiRoomType);
		}

	}
}