package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Bispo extends ChessPiece {

	public Bispo(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "B";
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumms()];
		
		Position p = new Position(0, 0);
		
		// nw
		p.setValues(position.getRow() - 1, position.getColumm() - 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumm()] = true;
			p.setValues(p.getRow() - 1, p.getColumm() - 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumm()] = true;
		}
		
		// ne
		p.setValues(position.getRow() - 1, position.getColumm() + 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumm()] = true;
			p.setValues(p.getRow() - 1, p.getColumm() + 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumm()] = true;
		}
		
		// se
		p.setValues(position.getRow() + 1, position.getColumm() + 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumm()] = true;
			p.setValues(p.getRow() + 1, p.getColumm() + 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumm()] = true;
		}
		
		// sw
		p.setValues(position.getRow() + 1, position.getColumm() - 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumm()] = true;
			p.setValues(p.getRow() + 1, p.getColumm() - 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumm()] = true;
		}
		
		return mat;
	}
}
