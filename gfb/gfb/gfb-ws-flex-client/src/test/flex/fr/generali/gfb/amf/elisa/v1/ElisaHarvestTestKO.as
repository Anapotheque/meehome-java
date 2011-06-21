package fr.generali.gfb.amf.elisa.v1
{
	import flexunit.framework.TestCase;
	
	import mx.rpc.IResponder;
	
	public class ElisaHarvestTestKO extends TestCase implements IResponder
	{
		
		public var elisaHarvestDelegate: IElisaHarvestRemoteService;
		
		
		public function ElisaHarvestTest(){}
		
		public function testRecupererListeProfessions():void {
			assertTrue("hop", true == true);
			
			elisaHarvestDelegate = new ElisaHarvestDelegate();
			
			// On envoie
			//trace("Envoi des données à Harvest:\n" + param);
			//elisaHarvestDelegate.setResponder(this);
			elisaHarvestDelegate.recupererListeProfessions("servicero","passwd");
		}
		
		// Données reçues
		public function result(event:Object):void {
			trace(":onResult RECEIVE_HARVEST_DATA");
			//harvestProxy.harvestData = new XML(event.result);
		}

		// Erreur de réception
		public function fault(event:Object):void {
			trace(":onError SEND_HARVEST_DATA_ERROR");
			//sendNotification(ApplicationFacade.RECEIVE_HARVEST_DATA_ERROR, event.fault.faultString);
		}

	}
}