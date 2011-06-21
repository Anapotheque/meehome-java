    
package fr.generali.gfb.amf.elisa.v1.dto {
    
  import mx.collections.ArrayCollection;

  import mx.events.PropertyChangeEvent;
    
  import flash.events.EventDispatcher;    
  import mx.core.IUID;
  import mx.utils.UIDUtil;
    
  /* [ExcludeClass] */
  public class _DtoRoInformationComplementaire extends flash.events.EventDispatcher implements mx.core.IUID {
  
    /* Constructor */
    public function _DtoRoInformationComplementaire():void {
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
    
    /* Property "reponses" */
    private var _reponses:mx.collections.ArrayCollection;
    
    [Bindable(event="propertyChange")]
	public function get reponses():mx.collections.ArrayCollection {
      return _reponses;
    }
    public function set reponses(value:mx.collections.ArrayCollection):void {
      const previous:mx.collections.ArrayCollection = this._reponses;
      if (previous != value) {
        _reponses = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "reponses", previous, _reponses
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
    
    /* Property "valeurDefaut" */
    private var _valeurDefaut:String;
    
    [Bindable(event="propertyChange")]
	public function get valeurDefaut():String {
      return _valeurDefaut;
    }
    public function set valeurDefaut(value:String):void {
      const previous:String = this._valeurDefaut;
      if (previous != value) {
        _valeurDefaut = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "valeurDefaut", previous, _valeurDefaut
        );
        dispatchEvent(ev);
      }
    }
    
  }
  
}
      