package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rei extends ChessPiece {

	public Rei(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "R"; // R = Rei
	}
	
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		
		return p == null || p.getColor() != getColor();
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumms()];
		
		Position p =  new Position(0,0);
		
		// Movimento acima da pe�a
		p.setValues(position.getRow() - 1, position.getColumm());
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumm()] = true;
		}
		
		// Movimento abaixo da pe�a
		p.setValues(position.getRow() + 1, position.getColumm());
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumm()] = true;
		}
		
		// Movimento a esquerda da pe�a
		p.setValues(position.getRow(), position.getColumm() - 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumm()] = true;
		}
		
		// Movimento a direita da pe�a
		p.setValues(position.getRow(), position.getColumm() + 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumm()] = true;
		}
		
		// Movimento noroeste
		p.setValues(position.getRow() - 1, position.getColumm() - 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumm()] = true;
		}
		
		// Movimento nordeste
		p.setValues(position.getRow() - 1, position.getColumm() + 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumm()] = true;
		}
		
		// Movimento sudoeste
		p.setValues(position.getRow() + 1, position.getColumm() - 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumm()] = true;
		}
		
		// Movimento sudeste
		p.setValues(position.getRow() + 1, position.getColumm() + 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumm()] = true;
		}
		return mat;
	}
}
