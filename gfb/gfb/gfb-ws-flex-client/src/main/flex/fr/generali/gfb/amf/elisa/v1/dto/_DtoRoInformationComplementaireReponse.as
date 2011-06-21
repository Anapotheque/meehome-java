    
package fr.generali.gfb.amf.elisa.v1.dto {
    

  import mx.events.PropertyChangeEvent;
    
  import flash.events.EventDispatcher;    
  import mx.core.IUID;
  import mx.utils.UIDUtil;
    
  /* [ExcludeClass] */
  public class _DtoRoInformationComplementaireReponse extends flash.events.EventDispatcher implements mx.core.IUID {
  
    /* Constructor */
    public function _DtoRoInformationComplementaireReponse():void {
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
    
      
    /* Property "codeCaisse" */
    private var _codeCaisse:String;
    
    [Bindable(event="propertyChange")]
	public function get codeCaisse():String {
      return _codeCaisse;
    }
    public function set codeCaisse(value:String):void {
      const previous:String = this._codeCaisse;
      if (previous != value) {
        _codeCaisse = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "codeCaisse", previous, _codeCaisse
        );
        dispatchEvent(ev);
      }
    }
    
    /* Property "id" */
    private var _id:String;
    
    [Bindable(event="propertyChange")]
	public function get id():String {
      return _id;
    }
    public function set id(value:String):void {
      const previous:String = this._id;
      if (previous != value) {
        _id = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "id", previous, _id
        );
        dispatchEvent(ev);
      }
    }
    
    /* Property "typeQuestion" */
    private var _typeQuestion:String;
    
    [Bindable(event="propertyChange")]
	public function get typeQuestion():String {
      return _typeQuestion;
    }
    public function set typeQuestion(value:String):void {
      const previous:String = this._typeQuestion;
      if (previous != value) {
        _typeQuestion = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "typeQuestion", previous, _typeQuestion
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
      