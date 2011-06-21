    
package fr.generali.gfb.amf.elisa.v1.dto {
    
  import fr.generali.gfb.amf.elisa.v1.dto.DtoRoClient;
  import mx.collections.ArrayCollection;

  import mx.events.PropertyChangeEvent;
    
  import flash.events.EventDispatcher;    
  import mx.core.IUID;
  import mx.utils.UIDUtil;
    
  /* [ExcludeClass] */
  public class _DtoRoHypotheses extends flash.events.EventDispatcher implements mx.core.IUID {
  
    /* Constructor */
    public function _DtoRoHypotheses():void {
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
    
      
    /* Property "degradSurRetraiteAAcquerir" */
    private var _degradSurRetraiteAAcquerir:Number;
    
    [Bindable(event="propertyChange")]
	public function get degradSurRetraiteAAcquerir():Number {
      return _degradSurRetraiteAAcquerir;
    }
    public function set degradSurRetraiteAAcquerir(value:Number):void {
      const previous:Number = this._degradSurRetraiteAAcquerir;
      if (previous != value) {
        _degradSurRetraiteAAcquerir = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "degradSurRetraiteAAcquerir", previous, _degradSurRetraiteAAcquerir
        );
        dispatchEvent(ev);
      }
    }
    
    /* Property "degradSurRetraiteAcquise" */
    private var _degradSurRetraiteAcquise:Number;
    
    [Bindable(event="propertyChange")]
	public function get degradSurRetraiteAcquise():Number {
      return _degradSurRetraiteAcquise;
    }
    public function set degradSurRetraiteAcquise(value:Number):void {
      const previous:Number = this._degradSurRetraiteAcquise;
      if (previous != value) {
        _degradSurRetraiteAcquise = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "degradSurRetraiteAcquise", previous, _degradSurRetraiteAcquise
        );
        dispatchEvent(ev);
      }
    }
    
    /* Property "dtoRoClient" */
    private var _dtoRoClient:fr.generali.gfb.amf.elisa.v1.dto.DtoRoClient;
    
    [Bindable(event="propertyChange")]
	public function get dtoRoClient():fr.generali.gfb.amf.elisa.v1.dto.DtoRoClient {
      return _dtoRoClient;
    }
    public function set dtoRoClient(value:fr.generali.gfb.amf.elisa.v1.dto.DtoRoClient):void {
      const previous:fr.generali.gfb.amf.elisa.v1.dto.DtoRoClient = this._dtoRoClient;
      if (previous != value) {
        _dtoRoClient = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "dtoRoClient", previous, _dtoRoClient
        );
        dispatchEvent(ev);
      }
    }
    
    /* Property "periodes" */
    private var _periodes:mx.collections.ArrayCollection;
    
    [Bindable(event="propertyChange")]
	public function get periodes():mx.collections.ArrayCollection {
      return _periodes;
    }
    public function set periodes(value:mx.collections.ArrayCollection):void {
      const previous:mx.collections.ArrayCollection = this._periodes;
      if (previous != value) {
        _periodes = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "periodes", previous, _periodes
        );
        dispatchEvent(ev);
      }
    }
    
  }
  
}
      