package com.generali.utils {
	import mx.preloaders.DownloadProgressBar;
	import flash.events.ProgressEvent;

	public class CustomPreloader extends DownloadProgressBar {
		public function CustomPreloader(){
			super();
		}

		override protected function progressHandler(event:ProgressEvent):void{
			super.progressHandler(event);
			initializingLabel = "Chargement";
		}
	}
}
