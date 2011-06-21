package com.generali.degatDesEaux.utils
{
	import briffle.dq.model.PathContext;
	
	import com.flexriver.briffle.ilog.service.ServiceProvider;
	import com.flexriver.briffle.rulesengine.RuleEngineConstants;
	import com.flexriver.briffle.rulesengine.RuleSet;
	import com.flexriver.briffle.rulesengine.RulesEngineAdministrator;
	import com.flexriver.briffle.rulesengine.RulesEngineEvent;
	import com.flexriver.briffle.rulesengine.parser.IRuleSetParser;
	import com.flexriver.briffle.rulesengine.runtime.RulesEngineRuntime;
	import com.flexriver.briffle.rulesengine.runtime.RulesEngineSession;
	import com.flexriver.common.logging.DefaultLogger;
	import com.flexriver.common.logging.LogFactory;
	import com.generali.degatDesEaux.events.rules.RuleEngineExecutedEvent;
	
	import flash.events.EventDispatcher;
	
	public class RuleEngineStub extends EventDispatcher
	{
		public var service:ServiceProvider=new ServiceProvider("0.2");
		private var nameRs:String ="";
		private static var _instance:RuleEngineStub;
		private var _logger:DefaultLogger = LogFactory.getLogger(RuleEngineConstants.RULE_ENGINE_CATEGORY);
	  		
		public static function getInstance():RuleEngineStub
		{
			if (_instance==null)
				_instance = new RuleEngineStub(); 
			
			return _instance;
		}
		public function RuleEngineStub():void
		{
			if(_instance!=null)
			 	throw new Error("RuleEngineStub is a Singleton!  Use getInstance instead!");
		}
		
		public function init(xml:XML):void 
		{
			service.addEventListener(RulesEngineEvent.RUNNING,engineRunningHandler);
			service.addEventListener(RulesEngineEvent.FINISHED,engineFinishedHandler);
			service.addEventListener(RulesEngineEvent.STOPPED,engineStoppedHandler);
		 
	 		var parser:IRuleSetParser=service.getRuleSetParser();		 	
			var ruleset:RuleSet=parser.parse(xml);
			//ruleset.validate();

			var admin:RulesEngineAdministrator=service.getRulesEngineAdministrator();
			admin.registerRuleSet(ruleset.name,ruleset);
			nameRs = ruleset.name;
			if(_logger.isDebug())
				_logger.logDebug(ruleset.toString());
		}
		
		protected function engineRunningHandler(event:RulesEngineEvent):void
		{
			if(_logger.isDebug())
				_logger.logDebug("[Event] Rules engine executing RuleSet "+event.session.ruleset.name);
		}

		protected function engineStoppedHandler(event:RulesEngineEvent):void
		{
			if(_logger.isDebug())
			 _logger.logDebug("[Event] Rules engine stopped while executing RuleSet "+event.session.ruleset.name);
		}

		protected function engineFinishedHandler(event:RulesEngineEvent):void
		{
			if(_logger.isDebug())
			 	_logger.logDebug("[Event] Rules engine finished executing RuleSet "+event.session.ruleset.name);
			dispatchEvent(new RuleEngineExecutedEvent());
		}
		
		public function execute(context:PathContext,id:String=null): void
		{
			if (nameRs!="")
			{
				var runtime:RulesEngineRuntime=service.getRulesEngineRuntime();
				var session:RulesEngineSession=runtime.createSession(nameRs);
				session.addObject("pcontext",context);
				if (id==null)
				{
					 session.execute();
				}
				else
				{
					 session.execute(id);
				}
				session.release(); 
			}
		}
	
	}
}