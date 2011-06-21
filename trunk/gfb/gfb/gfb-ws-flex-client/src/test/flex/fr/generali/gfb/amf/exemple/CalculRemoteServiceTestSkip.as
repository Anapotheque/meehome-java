package fr.generali.gfb.amf.exemple {
import flexunit.framework.TestCase;
import flexunit.framework.TestSuite;

	
	public class CalculRemoteServiceTestSkip extends TestCase {
		public function CalculRemoteServiceTestSkip() {
		}
		
		public function testAdditionner():void {
			assertTrue("hop", true == false);
		}
	}
}