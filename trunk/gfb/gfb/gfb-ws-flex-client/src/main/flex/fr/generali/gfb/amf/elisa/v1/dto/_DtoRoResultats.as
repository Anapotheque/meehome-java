    
package fr.generali.gfb.amf.elisa.v1.dto {
    

  import mx.events.PropertyChangeEvent;
    
  import flash.events.EventDispatcher;    
  import mx.core.IUID;
  import mx.utils.UIDUtil;
    
  /* [ExcludeClass] */
  public class _DtoRoResultats extends flash.events.EventDispatcher implements mx.core.IUID {
  
    /* Constructor */
    public function _DtoRoResultats():void {
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
    
      
    /* Property "ageDepart" */
    private var _ageDepart:Number;
    
    [Bindable(event="propertyChange")]
	public function get ageDepart():Number {
      return _ageDepart;
    }
    public function set ageDepart(value:Number):void {
      const previous:Number = this._ageDepart;
      if (previous != value) {
        _ageDepart = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "ageDepart", previous, _ageDepart
        );
        dispatchEvent(ev);
      }
    }
    
    /* Property "anneeDepart" */
    private var _anneeDepart:Number;
    
    [Bindable(event="propertyChange")]
	public function get anneeDepart():Number {
      return _anneeDepart;
    }
    public function set anneeDepart(value:Number):void {
      const previous:Number = this._anneeDepart;
      if (previous != value) {
        _anneeDepart = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "anneeDepart", previous, _anneeDepart
        );
        dispatchEvent(ev);
      }
    }
    
    /* Property "dateDepart" */
    private var _dateDepart:Date;
    
    [Bindable(event="propertyChange")]
	public function get dateDepart():Date {
      return _dateDepart;
    }
    public function set dateDepart(value:Date):void {
      const previous:Date = this._dateDepart;
      if (previous != value) {
        _dateDepart = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "dateDepart", previous, _dateDepart
        );
        dispatchEvent(ev);
      }
    }
    
    /* Property "dernierRevenu" */
    private var _dernierRevenu:Number;
    
    [Bindable(event="propertyChange")]
	public function get dernierRevenu():Number {
      return _dernierRevenu;
    }
    public function set dernierRevenu(value:Number):void {
      const previous:Number = this._dernierRevenu;
      if (previous != value) {
        _dernierRevenu = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "dernierRevenu", previous, _dernierRevenu
        );
        dispatchEvent(ev);
      }
    }
    
    /* Property "nombreEnfantCharge" */
    private var _nombreEnfantCharge:Number;
    
    [Bindable(event="propertyChange")]
	public function get nombreEnfantCharge():Number {
      return _nombreEnfantCharge;
    }
    public function set nombreEnfantCharge(value:Number):void {
      const previous:Number = this._nombreEnfantCharge;
      if (previous != value) {
        _nombreEnfantCharge = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "nombreEnfantCharge", previous, _nombreEnfantCharge
        );
        dispatchEvent(ev);
      }
    }
    
    /* Property "nombreEnfantEleve" */
    private var _nombreEnfantEleve:Number;
    
    [Bindable(event="propertyChange")]
	public function get nombreEnfantEleve():Number {
      return _nombreEnfantEleve;
    }
    public function set nombreEnfantEleve(value:Number):void {
      const previous:Number = this._nombreEnfantEleve;
      if (previous != value) {
        _nombreEnfantEleve = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "nombreEnfantEleve", previous, _nombreEnfantEleve
        );
        dispatchEvent(ev);
      }
    }
    
    /* Property "optionDepart" */
    private var _optionDepart:String;
    
    [Bindable(event="propertyChange")]
	public function get optionDepart():String {
      return _optionDepart;
    }
    public function set optionDepart(value:String):void {
      const previous:String = this._optionDepart;
      if (previous != value) {
        _optionDepart = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "optionDepart", previous, _optionDepart
        );
        dispatchEvent(ev);
      }
    }
    
    /* Property "retraiteBase" */
    private var _retraiteBase:Number;
    
    [Bindable(event="propertyChange")]
	public function get retraiteBase():Number {
      return _retraiteBase;
    }
    public function set retraiteBase(value:Number):void {
      const previous:Number = this._retraiteBase;
      if (previous != value) {
        _retraiteBase = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "retraiteBase", previous, _retraiteBase
        );
        dispatchEvent(ev);
      }
    }
    
    /* Property "retraiteTotal" */
    private var _retraiteTotal:Number;
    
    [Bindable(event="propertyChange")]
	public function get retraiteTotal():Number {
      return _retraiteTotal;
    }
    public function set retraiteTotal(value:Number):void {
      const previous:Number = this._retraiteTotal;
      if (previous != value) {
        _retraiteTotal = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "retraiteTotal", previous, _retraiteTotal
        );
        dispatchEvent(ev);
      }
    }
    
    /* Property "reversion" */
    private var _reversion:Number;
    
    [Bindable(event="propertyChange")]
	public function get reversion():Number {
      return _reversion;
    }
    public function set reversion(value:Number):void {
      const previous:Number = this._reversion;
      if (previous != value) {
        _reversion = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "reversion", previous, _reversion
        );
        dispatchEvent(ev);
      }
    }
    
    /* Property "tauxDegradationPondere" */
    private var _tauxDegradationPondere:Number;
    
    [Bindable(event="propertyChange")]
	public function get tauxDegradationPondere():Number {
      return _tauxDegradationPondere;
    }
    public function set tauxDegradationPondere(value:Number):void {
      const previous:Number = this._tauxDegradationPondere;
      if (previous != value) {
        _tauxDegradationPondere = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "tauxDegradationPondere", previous, _tauxDegradationPondere
        );
        dispatchEvent(ev);
      }
    }
    
  }
  
}
      