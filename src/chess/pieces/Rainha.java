package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rainha extends ChessPiece {

	public Rainha(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "Q"; 
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumms()];
		
		Position p = new Position(0,0);
		
				// Movimento acima da peça
				p.setValues(position.getRow() - 1, position.getColumm());
				
				//enquanto a posição p existir e não existir uma peça
				while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
					mat[p.getRow()][p.getColumm()] = true;
					p.setRow(p.getRow() - 1);
				} //verificando se existe uma peça adversária no final
				if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
					mat[p.getRow()][p.getColumm()] = true;
				}
				
				// Movimento a esquerda da peça
				p.setValues(position.getRow(), position.getColumm() - 1);
				
				while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
					mat[p.getRow()][p.getColumm()] = true;
					p.setColumm(p.getColumm() - 1);
				}
				if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
					mat[p.getRow()][p.getColumm()] = true;
				}
				
				// Movimento a direita da peça
				p.setValues(position.getRow(), position.getColumm() + 1);
				
				while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
					mat[p.getRow()][p.getColumm()] = true;
					p.setColumm(p.getColumm() + 1);
				}
				if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
					mat[p.getRow()][p.getColumm()] = true;
				}
				
				// Movimento abaixo da peça
				p.setValues(position.getRow() + 1, position.getColumm());
				
				while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
					mat[p.getRow()][p.getColumm()] = true;
					p.setRow(p.getRow() + 1);
				}
				if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
					mat[p.getRow()][p.getColumm()] = true;
				}
		
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
