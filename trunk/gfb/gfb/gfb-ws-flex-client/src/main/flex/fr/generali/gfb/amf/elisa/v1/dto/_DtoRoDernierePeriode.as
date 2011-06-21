    
package fr.generali.gfb.amf.elisa.v1.dto {
    

  import mx.events.PropertyChangeEvent;
    
  import flash.events.EventDispatcher;    
  import mx.core.IUID;
  import mx.utils.UIDUtil;
    
  /* [ExcludeClass] */
  public class _DtoRoDernierePeriode extends flash.events.EventDispatcher implements mx.core.IUID {
  
    /* Constructor */
    public function _DtoRoDernierePeriode():void {
      super();
    }
    
    // implementors of IUID must have a uid property
    private var _uid:String;
    
    [Transient]
    [Bindable(event="propertyChange")] 
    public function get uid():String {
      // If the uid hasn't been assigned a value, just create a new one.
      if (_uid == null) {
        _uid = mx.utils.UIDUtil.createUID();
      }
      return _uid;
    }

    public function set uid(value:String):void {
      const previous:String = _uid;
      if (previous != value) {
        _uid = value;
        dispatchEvent(
          mx.events.PropertyChangeEvent.createUpdateEvent(
            this, "uid", previous, value
          )
        );            
      }
    }
    
      
    /* Property "codeProfession" */
    private var _codeProfession:String;
    
    [Bindable(event="propertyChange")]
	public function get codeProfession():String {
      return _codeProfession;
    }
    public function set codeProfession(value:String):void {
      const previous:String = this._codeProfession;
      if (previous != value) {
        _codeProfession = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "codeProfession", previous, _codeProfession
        );
        dispatchEvent(ev);
      }
    }
    
    /* Property "dateDebut" */
    private var _dateDebut:Date;
    
    [Bindable(event="propertyChange")]
	public function get dateDebut():Date {
      return _dateDebut;
    }
    public function set dateDebut(value:Date):void {
      const previous:Date = this._dateDebut;
      if (previous != value) {
        _dateDebut = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "dateDebut", previous, _dateDebut
        );
        dispatchEvent(ev);
      }
    }
    
    /* Property "jusquaLaRetraite" */
    private var _jusquaLaRetraite:Boolean;
    
    [Bindable(event="propertyChange")]
	public function get jusquaLaRetraite():Boolean {
      return _jusquaLaRetraite;
    }
    public function set jusquaLaRetraite(value:Boolean):void {
      const previous:Boolean = this._jusquaLaRetraite;
      if (previous != value) {
        _jusquaLaRetraite = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "jusquaLaRetraite", previous, _jusquaLaRetraite
        );
        dispatchEvent(ev);
      }
    }
    
  }
  
}
      