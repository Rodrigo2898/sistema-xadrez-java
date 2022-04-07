package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Rei extends ChessPiece {
	
	private ChessMatch chessMatch;

	public Rei(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}
	
	@Override
	public String toString() {
		return "R"; // R = Rei
	}
	
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		
		return p == null || p.getColor() != getColor();
	}
	
	//Verificando se o roque � poss�vel
	private boolean	testRookCastiling(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p != null && p instanceof Torre && p.getColor() == getColor() && p.getMoveCount() == 0;
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
		
		// Movimento especial Roque
		if(getMoveCount() == 0 && !chessMatch.getCheck()) {
			// Movimento especial Roque pequeno
			Position posT1 = new Position(position.getRow(), position.getColumm() + 3);
			if(testRookCastiling(posT1)) {
				Position p1 = new Position(position.getRow(), position.getColumm() + 1);
				Position p2 = new Position(position.getRow(), position.getColumm() + 2);
				if(getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
					mat[position.getRow()][position.getColumm() + 2] = true;
				}
			}
			
			//Movimento especial Roque grande
			Position posT2 = new Position(position.getRow(), position.getColumm() - 4);
			if(testRookCastiling(posT2)) {
				Position p1 = new Position(position.getRow(), position.getColumm() - 1);
				Position p2 = new Position(position.getRow(), position.getColumm() - 2);
				Position p3 = new Position(position.getRow(), position.getColumm() - 3);
				if(getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
					mat[position.getRow()][position.getColumm() - 2] = true;
				}
			}
		}
		return mat;
	}
}
