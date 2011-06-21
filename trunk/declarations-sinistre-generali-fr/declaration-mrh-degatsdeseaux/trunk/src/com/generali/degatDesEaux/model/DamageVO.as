package com.generali.degatDesEaux.model
{
	import vegas.data.map.HashMap;
	[Bindable]
	public class DamageVO
	{
		private var _pieceMap:HashMap = new HashMap();
		
		public function DamageVO()
		{
		}
		
		public function getPieceById(key:String):PieceVO{
			return _pieceMap.get(key) as PieceVO;
		}
		
		public function get pieceMap():HashMap{
			return _pieceMap;
		}
		public function addPiece(piece:PieceVO):void{
			_pieceMap.put(piece.ID,piece);
		}
		public function removePiece(key:String):void{
			_pieceMap.remove(key);
		}
		
		public function fill(params:Object):void
		{
			if (AppModel.getInstance().result.has_damage && !_pieceMap.isEmpty())
			{
				params["dommageNbPiecesEndommagees"]=_pieceMap.size();
				var index:int = 0;
				for each(var piece:PieceVO in _pieceMap.getValues())
				{
					params["pieceType_"+index]=piece.type_piece;
					params["pieceNom_"+index]=piece.name;
					params["pieceLongueur_"+index]=piece.longueur;
					params["pieceLargeur_"+index]=piece.largeur;
					
					params["murPapierPeint_"+index]=piece.wall.papier_peint;
					params["murPeinture_"+index]=piece.wall.peinture;
					params["murAutre_"+index]=piece.wall.autre;
					params["murAutreSurface_"+index]=piece.wall.autreVAL;
					
					params["plafondPapierPeint_" +index]=piece.plafond.papier_peint;
					params["plafondPeinture_"+index]=piece.plafond.peinture;
					params["plafondAutre_"+index]=piece.plafond.autre;
					params["plafondAutreSurface_"+index]=piece.plafond.autreVAL;
					
					params["mobilierEndommage_"+index]=piece.mobilier.mobilierDetails;
					
					params["solParquet_"+index] = piece.sol.parquet;
					params["solCarrelage_"+index] = piece.sol.carrelage
					params["solMoquette_"+index] = piece.sol.moquette;
					params["solRevetementPlastique_"+index] = piece.sol.revetement;
					params["solAutre_"+index] = piece.sol.autre;
					params["solAutreSurface_"+index] = piece.sol.autreVAL;
				
					params["pieceCommentaires_"+index] = piece.pieceDetails;
					
					index = index +1;
				}
				
			}
		}
	}
}