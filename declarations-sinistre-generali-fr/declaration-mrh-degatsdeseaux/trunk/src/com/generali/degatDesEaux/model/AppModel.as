package com.generali.degatDesEaux.model
{
	import com.generali.degatDesEaux.model.three3D.Room3DInterpreter;
	import com.generali.degatDesEaux.model.three3D.RoomType;
	import com.generali.model.InsuredVO;
	[Bindable]
	public class AppModel
	{
		public const DEFAULT_PIECE:String = "Pièce ";		
		private static var _instance:AppModel;
		public static function getInstance():AppModel
		{
			if (_instance==null)
				_instance = new AppModel(); 
			
			return _instance;
		}
		public function AppModel()
		{
			if(_instance!=null)
			{
			 	throw new Error("AppModel is a Singleton!  Use getInstance instead!");
			}
			 init();
		}
		public function init():void{
			
						
		}
		public function getPieceIndex(type_piece:String):int
		{
			for (var i:int = 1 ; i< dpTypePiece.length;i++)
			{
				if (type_piece == dpTypePiece[i].toString())
				{
					return i;
				}
			}
			return 0;
		}
		public function clear():void{
			insured =new InsuredVO();
			residence= new ResidenceVO();
			accident = new AccidentVO();
			result  = new ResultVO();
			damage  = new DamageVO();
		}
		
		public var insured:InsuredVO =new InsuredVO();
		public var residence:ResidenceVO = new ResidenceVO();
		public var accident:AccidentVO = new AccidentVO();
		public var result:ResultVO = new ResultVO();
		public var damage:DamageVO = new DamageVO();
		public var dpTypePiece:Array = ['Sélectionnez',RoomType.SALLE_DE_BAINS,RoomType.CUISINE,RoomType.SALON,RoomType.CHAMBRE,RoomType.WC,RoomType.AUTRE];

		public var room3DInterpreter:Room3DInterpreter = new Room3DInterpreter();
	}
}