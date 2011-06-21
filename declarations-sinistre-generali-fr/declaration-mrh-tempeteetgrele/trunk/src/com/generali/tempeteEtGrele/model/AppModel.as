package com.generali.tempeteEtGrele.model
{
	import com.generali.tempeteEtGrele.model.ResidenceVO;
	import com.generali.model.InsuredVO;

	[Bindable]
	public class AppModel {
		
		public const DEFAULT_PIECE:String = "Pièce ";		
		
		private static var _instance:AppModel;
		
		public var insured:InsuredVO =new InsuredVO();
		public var residence:ResidenceVO = new ResidenceVO();
		public var accident:AccidentVO = new AccidentVO();
		
		public static function getInstance():AppModel{
			if (_instance==null)
				_instance = new AppModel(); 
			return _instance;
		}
		public function AppModel(){
			if(_instance!=null)
			 	throw new Error("AppModel is a Singleton!  Use getInstance instead!");
			 init();
		}
		
		public function init():void{}
		
		public function clear():void{
			residence = new ResidenceVO();
			insured = new InsuredVO();
			accident = new AccidentVO();
		}
	}
}