    
package fr.generali.gfb.amf.elisa.v1.dto {
    
  import mx.collections.ArrayCollection;

  import mx.events.PropertyChangeEvent;
    
  import flash.events.EventDispatcher;    
  import mx.core.IUID;
  import mx.utils.UIDUtil;
    
  /* [ExcludeClass] */
  public class _DtoRoQuestionsComplementaires extends flash.events.EventDispatcher implements mx.core.IUID {
  
    /* Constructor */
    public function _DtoRoQuestionsComplementaires():void {
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
    
    /* Property "itemListeQuestions" */
    private var _itemListeQuestions:mx.collections.ArrayCollection;
    
    [Bindable(event="propertyChange")]
	public function get itemListeQuestions():mx.collections.ArrayCollection {
      return _itemListeQuestions;
    }
    public function set itemListeQuestions(value:mx.collections.ArrayCollection):void {
      const previous:mx.collections.ArrayCollection = this._itemListeQuestions;
      if (previous != value) {
        _itemListeQuestions = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "itemListeQuestions", previous, _itemListeQuestions
        );
        dispatchEvent(ev);
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
    
    /* Property "valeurDefaut" */
    private var _valeurDefaut:Number;
    
    [Bindable(event="propertyChange")]
	public function get valeurDefaut():Number {
      return _valeurDefaut;
    }
    public function set valeurDefaut(value:Number):void {
      const previous:Number = this._valeurDefaut;
      if (previous != value) {
        _valeurDefaut = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "valeurDefaut", previous, _valeurDefaut
        );
        dispatchEvent(ev);
      }
    }
    
    /* Property "valeurMaximum" */
    private var _valeurMaximum:Number;
    
    [Bindable(event="propertyChange")]
	public function get valeurMaximum():Number {
      return _valeurMaximum;
    }
    public function set valeurMaximum(value:Number):void {
      const previous:Number = this._valeurMaximum;
      if (previous != value) {
        _valeurMaximum = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "valeurMaximum", previous, _valeurMaximum
        );
        dispatchEvent(ev);
      }
    }
    
    /* Property "valeurMinimum" */
    private var _valeurMinimum:Number;
    
    [Bindable(event="propertyChange")]
	public function get valeurMinimum():Number {
      return _valeurMinimum;
    }
    public function set valeurMinimum(value:Number):void {
      const previous:Number = this._valeurMinimum;
      if (previous != value) {
        _valeurMinimum = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "valeurMinimum", previous, _valeurMinimum
        );
        dispatchEvent(ev);
      }
    }
    
  }
  
}
      