package com.generali.degatDesEaux.model
{
	[Bindable]
	public class PieceVO
	{
		private var _ID:String="";
		private var _name:String;
		private var _type_piece:String="";
		private var _longueur:String=""	;
		private var _largeur:String="";
		private var _wall:WallVO = new WallVO();
		private var _plafond:WallVO = new WallVO();
		private var _sol:SolVO=new SolVO();
		private var _mobilier:MobilierVO = new MobilierVO();
		
		private var _pieceDetails:String="";
		public function PieceVO(id:String)
		{
			this._ID = id;
		}
		
		public function get ID():String{
			return this._ID;
		}
		
		
		public function get name():String{
			return this._name;
		}
		public function set name(val:String):void {
			this._name = val;
		}
		public function get pieceDetails():String{
			return this._pieceDetails;
		}
		public function set pieceDetails(val:String):void {
			this._pieceDetails = val;
		}
		public function get type_piece():String{return this._type_piece;	}		
		public function set type_piece(val:String):void{	this._type_piece = val;	} 
		
		public function get longueur():String{return this._longueur;	}		
		public function set longueur(val:String):void{	this._longueur = val;	} 
		
		public function get largeur():String{return this._largeur;	}		
		public function set largeur(val:String):void{	this._largeur = val;	} 
		
		
		public function get wall():WallVO{return this._wall;	}		
		public function set wall(val:WallVO):void{	this._wall = val;	} 
		
		public function get plafond():WallVO{return this._plafond;	}		
		public function set plafond(val:WallVO):void{	this._plafond = val;	} 
		
		
		public function get sol():SolVO{return this._sol;	}		
		public function set sol(val:SolVO):void{	this._sol = val;	} 
		
		public function get mobilier():MobilierVO{return this._mobilier;	}		
		public function set mobilier(val:MobilierVO):void{	this._mobilier = val;	} 
		
		public function clear():void{
		_wall = new WallVO();
		_plafond = new WallVO();
		_sol =new SolVO();
		_mobilier = new MobilierVO();	
		}
	}
}