package models {
	import fr.generali.declaration.sinistre.auto.common.models.vo.AssureVO;
	
	import models.vo.DommagesVO;
	import models.vo.SinistreVO;
	
	import mx.core.Application;

	[Bindable]
	public class AppModel {

		private static var _instance:AppModel;

		// FlashVars
		public var idClientRCE:String;
		public var codePortefeuille:String;
		public var codeCompagnie:String;
		
		public var assure:AssureVO = new AssureVO();
		public var sinistre:SinistreVO = new SinistreVO();
		public var dommages:DommagesVO = new DommagesVO();

		public function AppModel() {
			if(!_instance) {
				_instance=this;
			}
		}

		public static function getInstance():AppModel {
			if(_instance)
				return _instance;
			_instance = new AppModel();
				return _instance;
		}
		
		public function clear():void {
			this.assure = new AssureVO();
			this.sinistre = new SinistreVO();
			this.dommages = new DommagesVO();
		}
		
		public function getPostParams():Object {
			var params:Object = new Object();
			params["sinistreType"]= Application.application.APPLICATION_TYPE;

			params['idClientRCE'] = this.idClientRCE;
			params['codePortefeuille'] = this.codePortefeuille;
			params['codeCompagnie'] = this.codeCompagnie;

			this.assure.getPostParams(params);
			this.sinistre.getPostParams(params);
			this.dommages.getPostParams(params);

			return params;
		}
		
		public function toString():String {
			var str:String = "idClientRCE => [\n" + this.idClientRCE + "]\n"
				+ "\ncodePortefeuille => [\n" + this.codePortefeuille + "]\n"
				+ "\ncodeCompagnie => [\n" + this.codeCompagnie + "]\n"
				+ "\nassure => [\n" + this.assure + "]\n"
				+ "\nsinistre =>\n" + this.sinistre + "]\n"
				+ "\ndommages =>\n" + this.dommages + "]\n";
			return str;
		}
		
		/*
		public function toXML():XML {
			var xml:XML = new XML();
			return xml;
		}*/
	}
}