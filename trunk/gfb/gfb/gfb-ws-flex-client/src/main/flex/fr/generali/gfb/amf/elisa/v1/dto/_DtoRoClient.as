    
package fr.generali.gfb.amf.elisa.v1.dto {
    

  import mx.events.PropertyChangeEvent;
    
  import flash.events.EventDispatcher;    
  import mx.core.IUID;
  import mx.utils.UIDUtil;
    
  /* [ExcludeClass] */
  public class _DtoRoClient extends flash.events.EventDispatcher implements mx.core.IUID {
  
    /* Constructor */
    public function _DtoRoClient():void {
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
    
      
    /* Property "ageRetraite" */
    private var _ageRetraite:Number;
    
    [Bindable(event="propertyChange")]
	public function get ageRetraite():Number {
      return _ageRetraite;
    }
    public function set ageRetraite(value:Number):void {
      const previous:Number = this._ageRetraite;
      if (previous != value) {
        _ageRetraite = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "ageRetraite", previous, _ageRetraite
        );
        dispatchEvent(ev);
      }
    }
    
    /* Property "dateNaissance" */
    private var _dateNaissance:Date;
    
    [Bindable(event="propertyChange")]
	public function get dateNaissance():Date {
      return _dateNaissance;
    }
    public function set dateNaissance(value:Date):void {
      const previous:Date = this._dateNaissance;
      if (previous != value) {
        _dateNaissance = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "dateNaissance", previous, _dateNaissance
        );
        dispatchEvent(ev);
      }
    }
    
    /* Property "enfants" */
    private var _enfants:Number;
    
    [Bindable(event="propertyChange")]
	public function get enfants():Number {
      return _enfants;
    }
    public function set enfants(value:Number):void {
      const previous:Number = this._enfants;
      if (previous != value) {
        _enfants = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "enfants", previous, _enfants
        );
        dispatchEvent(ev);
      }
    }
    
    /* Property "salaire" */
    private var _salaire:Number;
    
    [Bindable(event="propertyChange")]
	public function get salaire():Number {
      return _salaire;
    }
    public function set salaire(value:Number):void {
      const previous:Number = this._salaire;
      if (previous != value) {
        _salaire = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "salaire", previous, _salaire
        );
        dispatchEvent(ev);
      }
    }
    
    /* Property "sex" */
    private var _sex:String;
    
    [Bindable(event="propertyChange")]
	public function get sex():String {
      return _sex;
    }
    public function set sex(value:String):void {
      const previous:String = this._sex;
      if (previous != value) {
        _sex = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "sex", previous, _sex
        );
        dispatchEvent(ev);
      }
    }
    
  }
  
}
      