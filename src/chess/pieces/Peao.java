package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Peao extends ChessPiece {

	public Peao(Board board, Color color) {
		super(board, color);
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumms()];
		
		Position p = new Position(0,0);
		
		if(getColor() == Color.WHITE) {
			p.setValues(position.getRow() - 1, position.getColumm());
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumm()] = true;
			}
			
			p.setValues(position.getRow() - 2, position.getColumm());
			Position p2 = new Position(position.getRow() - 1, position.getColumm());
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
				mat[p.getRow()][p.getColumm()] = true;
			}
			
			p.setValues(position.getRow() - 1, position.getColumm() - 1);
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumm()] = true;
			}
			
			p.setValues(position.getRow() - 1, position.getColumm() + 1);
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumm()] = true;
			}
		}
		
		else {
			p.setValues(position.getRow() + 1, position.getColumm());
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumm()] = true;
			}
			
			p.setValues(position.getRow() + 2, position.getColumm());
			Position p2 = new Position(position.getRow() + 1, position.getColumm());
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
				mat[p.getRow()][p.getColumm()] = true;
			}
			
			p.setValues(position.getRow() + 1, position.getColumm() - 1);
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumm()] = true;
			}
			
			p.setValues(position.getRow() + 1, position.getColumm() + 1);
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumm()] = true;
			}
		}
		
		return mat;
	}
	
	@Override
	public String toString() {
		return "P";
	}
	
}
