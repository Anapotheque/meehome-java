package com.generali.degatDesEaux.model.three3D
{
	import vegas.data.map.HashMap;
	
	/* represents Different Choice Element for a given group in the 3D component */
	public class Textures
	{
		private var textureWallMap:HashMap = new HashMap();
		private var textureCeilingMap:HashMap = new HashMap();
		private var textureFloorMap:HashMap  = new HashMap();
		private var textureRoomPartMap:HashMap = new HashMap();
		
		public static const PARQUET:String = "parquet";
		public static const CARRELAGE:String = "carrelage";
		public static const MOQUETTE:String = "moquette";
		public static const REVETEMENT:String = "revetement";
		public static const AUTRE:String = "autre";
		public static const DEFAULT:String = "default";
		
		public static const PAPIERPEINT:String   ="papierpeint";
		public static const PEINTURE:String     ="peinture";
		
		public function Textures()
		{
			loadTextureMaps();
		}

		public function loadTextureMaps():void
		{
			/* <room1 type="Salle de bains">
			  <roomPartType="walls">
			  	<texture id="papierpient","wallpaper1"/>
			  	<texture id="peinture","paint2"/>
			  	<texture id="autre","other"/>
			  	<texture id="default","other"/>
			  </roomPartType>
			 </room1> */
			/** walls **/
			textureWallMap.put(PAPIERPEINT,"wallpaper1");
			textureWallMap.put(PEINTURE,"paint2");
			textureWallMap.put(AUTRE,"other");
			textureWallMap.put(DEFAULT,"other");
			textureRoomPartMap.put(RoomPartType.WALLS,textureWallMap);
			
			/** ceiling **/
			textureCeilingMap.put(PAPIERPEINT,"wallpaper1");
			textureCeilingMap.put(PEINTURE,"paint");
			textureCeilingMap.put(AUTRE,"other");
			textureCeilingMap.put(DEFAULT,"other");
			textureRoomPartMap.put(RoomPartType.CEILING,textureCeilingMap);
			
			/** floor **/
			textureFloorMap.put(PARQUET,"wood1");
			textureFloorMap.put(CARRELAGE,"tiles2");
			textureFloorMap.put(MOQUETTE,"carpet1");
			textureFloorMap.put(REVETEMENT,"plastic1");
			textureFloorMap.put(AUTRE,"other");
			textureFloorMap.put(DEFAULT,"other");
			textureRoomPartMap.put(RoomPartType.FLOOR,textureFloorMap);

		}
		
		public function getTextureByRoomPart(roomPart:String, uiTexture:String):String
		{
			var textMap:Object = textureRoomPartMap.get(roomPart);
			if(textMap)
			{
				return (textMap as HashMap).get(uiTexture);
			}
			return null;
		}
	}
}