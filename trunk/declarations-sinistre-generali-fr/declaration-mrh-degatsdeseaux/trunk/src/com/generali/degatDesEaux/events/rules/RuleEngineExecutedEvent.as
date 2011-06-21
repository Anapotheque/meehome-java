package com.generali.degatDesEaux.events.rules
{
	import flash.events.Event;
	
	public class RuleEngineExecutedEvent extends Event
	{
		public static const ID : String = "RuleEngineExecutedEvent";
		public function RuleEngineExecutedEvent()
		{
			super(ID);
		}

	}
}