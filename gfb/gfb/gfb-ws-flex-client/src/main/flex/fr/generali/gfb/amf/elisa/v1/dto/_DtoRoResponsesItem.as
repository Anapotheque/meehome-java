    
package fr.generali.gfb.amf.elisa.v1.dto {
    

  import mx.events.PropertyChangeEvent;
    
  import flash.events.EventDispatcher;    
  import mx.core.IUID;
  import mx.utils.UIDUtil;
    
  /* [ExcludeClass] */
  public class _DtoRoResponsesItem extends flash.events.EventDispatcher implements mx.core.IUID {
  
    /* Constructor */
    public function _DtoRoResponsesItem():void {
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
    
      
    /* Property "libelle" */
    private var _libelle:String;
    
    [Bindable(event="propertyChange")]
	public function get libelle():String {
      return _libelle;
    }
    public function set libelle(value:String):void {
      const previous:String = this._libelle;
      if (previous != value) {
        _libelle = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "libelle", previous, _libelle
        );
        dispatchEvent(ev);
      }
    }
    
    /* Property "valeur" */
    private var _valeur:Number;
    
    [Bindable(event="propertyChange")]
	public function get valeur():Number {
      return _valeur;
    }
    public function set valeur(value:Number):void {
      const previous:Number = this._valeur;
      if (previous != value) {
        _valeur = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "valeur", previous, _valeur
        );
        dispatchEvent(ev);
      }
    }
    
  }
  
}
      