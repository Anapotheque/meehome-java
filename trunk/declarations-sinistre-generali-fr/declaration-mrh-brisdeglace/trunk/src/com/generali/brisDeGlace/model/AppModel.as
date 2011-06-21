package com.generali.brisDeGlace.model
{
	import com.generali.model.InsuredVO;
	
	[Bindable]
	public class AppModel
	{
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
			 	throw new Error("AppModel is a Singleton!  Use getInstance instead!");
			 init();
		}
		public function init():void{
			
						
		}
		public function clear():void{
			insured = new InsuredVO
			accident = new AccidentVO();
		}
		public var insured:InsuredVO =new InsuredVO();
		public var accident:AccidentVO = new AccidentVO();
	}
}