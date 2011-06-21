    
package fr.generali.gfb.amf.elisa.v1.dto {
    
  import mx.collections.ArrayCollection;

  import mx.events.PropertyChangeEvent;
    
  import flash.events.EventDispatcher;    
  import mx.core.IUID;
  import mx.utils.UIDUtil;
    
  /* [ExcludeClass] */
  public class _DtoRoProfession extends flash.events.EventDispatcher implements mx.core.IUID {
  
    /* Constructor */
    public function _DtoRoProfession():void {
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
    
      
    /* Property "codeRegimeRetraite" */
    private var _codeRegimeRetraite:String;
    
    [Bindable(event="propertyChange")]
	public function get codeRegimeRetraite():String {
      return _codeRegimeRetraite;
    }
    public function set codeRegimeRetraite(value:String):void {
      const previous:String = this._codeRegimeRetraite;
      if (previous != value) {
        _codeRegimeRetraite = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "codeRegimeRetraite", previous, _codeRegimeRetraite
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
    
    /* Property "libelleRevenu" */
    private var _libelleRevenu:String;
    
    [Bindable(event="propertyChange")]
	public function get libelleRevenu():String {
      return _libelleRevenu;
    }
    public function set libelleRevenu(value:String):void {
      const previous:String = this._libelleRevenu;
      if (previous != value) {
        _libelleRevenu = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "libelleRevenu", previous, _libelleRevenu
        );
        dispatchEvent(ev);
      }
    }
    
    /* Property "presenceRevenu" */
    private var _presenceRevenu:String;
    
    [Bindable(event="propertyChange")]
	public function get presenceRevenu():String {
      return _presenceRevenu;
    }
    public function set presenceRevenu(value:String):void {
      const previous:String = this._presenceRevenu;
      if (previous != value) {
        _presenceRevenu = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "presenceRevenu", previous, _presenceRevenu
        );
        dispatchEvent(ev);
      }
    }
    
    /* Property "questionsComplementaires" */
    private var _questionsComplementaires:mx.collections.ArrayCollection;
    
    [Bindable(event="propertyChange")]
	public function get questionsComplementaires():mx.collections.ArrayCollection {
      return _questionsComplementaires;
    }
    public function set questionsComplementaires(value:mx.collections.ArrayCollection):void {
      const previous:mx.collections.ArrayCollection = this._questionsComplementaires;
      if (previous != value) {
        _questionsComplementaires = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "questionsComplementaires", previous, _questionsComplementaires
        );
        dispatchEvent(ev);
      }
    }
    
  }
  
}
      