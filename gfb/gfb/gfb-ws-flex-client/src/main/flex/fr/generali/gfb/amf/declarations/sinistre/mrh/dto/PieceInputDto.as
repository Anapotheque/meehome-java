package fr.generali.gfb.amf.declarations.sinistre.mrh.dto
{
    [RemoteClass(alias="fr.generali.gfb.ws.sinistre.dto.mrh.PieceInput")]
    public class PieceInputDto
    {
    
    private var _typePiece:String;
    
    private var _nom:String;

    private var _longueur:String;

    private var _largeur:String;

    private var _commentaire:String;

    private var _murPapierPeint:String;

    private var _murPeinture:String;

    private var _murAutre:String;

    private var _murSurfaceAutre:String;

    private var _plafondPapierPeint:String;

    private var _plafondPeinture:String;

    private var _plafondAutre:String;

    private var _plafondSurfaceAutre:String;

    private var _mobilierEndommage:String;

    private var _solParquet:String;

    private var _solCarrelage:String;

    private var _solMoquette:String;

    private var _solRevetementPlastique:String;

    private var _solAutre:String;

    private var _solAutreSurface:String;
    

        public function PieceInputDto() 
        {
        	
        }

        /*
        public function PieceInputDto(typePiece:String, nom:String, longueur:String, largeur:String, commentaire:String, murPapierPeint:String,
                        murPeinture:String, murAutre:String, murSurfaceAutre:String, plafondPapierPeint:String,
                        plafondPeinture:String, plafondAutre:String, plafondSurfaceAutre:String, mobilierEndommage:String,
                        solParquet:String, solCarrelage:String, solMoquette:String, solRevetementPlastique:String,
                        solAutre:String, solAutreSurface:String) {
            super();
            this._typePiece = typePiece;
            this._nom = nom;
            this._longueur = longueur;
            this._largeur = largeur;
            this._commentaire = commentaire;
            this._murPapierPeint = murPapierPeint;
            this._murPeinture = murPeinture;
            this._murAutre = murAutre;
            this._murSurfaceAutre = murSurfaceAutre;
            this._plafondPapierPeint = plafondPapierPeint;
            this._plafondPeinture = plafondPeinture;
            this._plafondAutre = plafondAutre;
            this._plafondSurfaceAutre = plafondSurfaceAutre;
            this._mobilierEndommage = mobilierEndommage;
            this._solParquet = solParquet;
            this._solCarrelage = solCarrelage;
            this._solMoquette = solMoquette;
            this._solRevetementPlastique = solRevetementPlastique;
            this._solAutre = solAutre;
            this._solAutreSurface = solAutreSurface;
            
        }
        */

        public function get typePiece():String {return _typePiece;}
        public function set typePiece(val:String):void { this._typePiece=val;}
        public function get nom():String {return _nom;}
        public function set nom(val:String):void { this._nom=val;}        
        public function get longueur():String {return _longueur;}   
        public function set longueur(val:String):void { this._longueur=val;}
        public function get largeur():String {return _largeur;}   
        public function set largeur(val:String):void { this._largeur=val;}     
        public function get commentaire():String {return _commentaire;}   
        public function set commentaire(val:String):void { this._commentaire=val;}     
        public function get murPapierPeint():String {return _murPapierPeint;}   
        public function set murPapierPeint(val:String):void { this._murPapierPeint=val;}     
        public function get murPeinture():String {return _murPeinture;}   
        public function set murPeinture(val:String):void { this._murPeinture=val;}
        public function get murAutre():String {return _murAutre;}   
        public function set murAutre(val:String):void { this._murAutre=val;}     
        public function get murSurfaceAutre():String {return _murSurfaceAutre;}   
        public function set murSurfaceAutre(val:String):void { this._murSurfaceAutre=val;}     
        public function get plafondPapierPeint():String {return _plafondPapierPeint;}           
        public function set plafondPapierPeint(val:String):void { this._plafondPapierPeint=val;}     
        public function get plafondPeinture():String {return _plafondPeinture;}           
        public function set plafondPeinture(val:String):void { this._plafondPeinture=val;}     
        public function get plafondAutre():String {return _plafondAutre;}   
        public function set plafondAutre(val:String):void { this._plafondAutre=val;}     
        public function get plafondSurfaceAutre():String {return _plafondSurfaceAutre;}   
        public function set plafondSurfaceAutre(val:String):void { this._plafondSurfaceAutre=val;}     
        public function get mobilierEndommage():String {return _mobilierEndommage;}   
        public function set mobilierEndommage(val:String):void { this._mobilierEndommage=val;}     
        public function get solParquet():String {return _solParquet;}   
        public function set solParquet(val:String):void { this._solParquet=val;}     
        public function get solCarrelage():String {return _solCarrelage;}   
        public function set solCarrelage(val:String):void { this._solCarrelage=val;}     
        public function get solMoquette():String {return _solMoquette;}   
        public function set solMoquette(val:String):void { this._solMoquette=val;}     
        public function get solRevetementPlastique():String {return _solRevetementPlastique;}   
        public function set solRevetementPlastique(val:String):void { this._solRevetementPlastique=val;}     
        public function get solAutre():String {return _solAutre;}   
        public function set solAutre(val:String):void { this._solAutre=val;}     
        public function get solAutreSurface():String {return _solAutreSurface;}   
        public function set solAutreSurface(val:String):void { this._solAutreSurface=val;}     
    }

}