    
package fr.generali.gfb.amf.elisa.v1.dto {
    
  import mx.collections.ArrayCollection;

  import mx.events.PropertyChangeEvent;
    
  import flash.events.EventDispatcher;    
  import mx.core.IUID;
  import mx.utils.UIDUtil;
    
  /* [ExcludeClass] */
  public class _DtoRoPeriode extends flash.events.EventDispatcher implements mx.core.IUID {
  
    /* Constructor */
    public function _DtoRoPeriode():void {
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
    
      
    /* Property "ageCharniere" */
    private var _ageCharniere:Number;
    
    [Bindable(event="propertyChange")]
	public function get ageCharniere():Number {
      return _ageCharniere;
    }
    public function set ageCharniere(value:Number):void {
      const previous:Number = this._ageCharniere;
      if (previous != value) {
        _ageCharniere = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "ageCharniere", previous, _ageCharniere
        );
        dispatchEvent(ev);
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
    
    /* Property "dateFin" */
    private var _dateFin:Date;
    
    [Bindable(event="propertyChange")]
	public function get dateFin():Date {
      return _dateFin;
    }
    public function set dateFin(value:Date):void {
      const previous:Date = this._dateFin;
      if (previous != value) {
        _dateFin = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "dateFin", previous, _dateFin
        );
        dispatchEvent(ev);
      }
    }
    
    /* Property "informationComplementaire" */
    private var _informationComplementaire:mx.collections.ArrayCollection;
    
    [Bindable(event="propertyChange")]
	public function get informationComplementaire():mx.collections.ArrayCollection {
      return _informationComplementaire;
    }
    public function set informationComplementaire(value:mx.collections.ArrayCollection):void {
      const previous:mx.collections.ArrayCollection = this._informationComplementaire;
      if (previous != value) {
        _informationComplementaire = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "informationComplementaire", previous, _informationComplementaire
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
    
    /* Property "progressionApresAgeCharniere" */
    private var _progressionApresAgeCharniere:Number;
    
    [Bindable(event="propertyChange")]
	public function get progressionApresAgeCharniere():Number {
      return _progressionApresAgeCharniere;
    }
    public function set progressionApresAgeCharniere(value:Number):void {
      const previous:Number = this._progressionApresAgeCharniere;
      if (previous != value) {
        _progressionApresAgeCharniere = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "progressionApresAgeCharniere", previous, _progressionApresAgeCharniere
        );
        dispatchEvent(ev);
      }
    }
    
    /* Property "progressionAvantAgeCharniere" */
    private var _progressionAvantAgeCharniere:Number;
    
    [Bindable(event="propertyChange")]
	public function get progressionAvantAgeCharniere():Number {
      return _progressionAvantAgeCharniere;
    }
    public function set progressionAvantAgeCharniere(value:Number):void {
      const previous:Number = this._progressionAvantAgeCharniere;
      if (previous != value) {
        _progressionAvantAgeCharniere = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "progressionAvantAgeCharniere", previous, _progressionAvantAgeCharniere
        );
        dispatchEvent(ev);
      }
    }
    
    /* Property "revenuAnnuelDebut" */
    private var _revenuAnnuelDebut:Number;
    
    [Bindable(event="propertyChange")]
	public function get revenuAnnuelDebut():Number {
      return _revenuAnnuelDebut;
    }
    public function set revenuAnnuelDebut(value:Number):void {
      const previous:Number = this._revenuAnnuelDebut;
      if (previous != value) {
        _revenuAnnuelDebut = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "revenuAnnuelDebut", previous, _revenuAnnuelDebut
        );
        dispatchEvent(ev);
      }
    }
    
    /* Property "revenuAnnuelFin" */
    private var _revenuAnnuelFin:Number;
    
    [Bindable(event="propertyChange")]
	public function get revenuAnnuelFin():Number {
      return _revenuAnnuelFin;
    }
    public function set revenuAnnuelFin(value:Number):void {
      const previous:Number = this._revenuAnnuelFin;
      if (previous != value) {
        _revenuAnnuelFin = value;
        const ev:mx.events.PropertyChangeEvent = mx.events.PropertyChangeEvent.createUpdateEvent(
          this, "revenuAnnuelFin", previous, _revenuAnnuelFin
        );
        dispatchEvent(ev);
      }
    }
    
  }
  
}
      