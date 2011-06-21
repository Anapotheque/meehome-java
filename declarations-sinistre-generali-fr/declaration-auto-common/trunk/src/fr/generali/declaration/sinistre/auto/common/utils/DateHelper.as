package fr.generali.declaration.sinistre.auto.common.utils {
import mx.controls.DateField;      

	public class DateHelper {      
		//public static const DEFAULT_DATE_FORMAT:String = "DD/MM/YYYY";      
		private var formatString:String;                  
		
		public function DateHelper(format:String) {         
			if (format != null) {            
				formatString = format;         
				} else{
					formatString = "dd/mm/yyyy";
				}     
			}            
			
		public function stringToDate(argument:String):Date {         
			if (argument == null) return null;         
				return DateField.stringToDate(argument, formatString);      
		}            
		
		public function dateToString(argument:Date):String {         
			if (argument == null) return null;         
			return DateField.dateToString(argument, formatString);      
		}

		public static function getDisabledRangeDays():Array {
   			var today:Date = new Date();
  			today.date +=1; 
  			return [new Date(2006,0,11),{rangeStart: today, rangeEnd: new Date(2030,1,10)}];
  		}
	}
}