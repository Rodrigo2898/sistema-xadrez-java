package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class Rei extends ChessPiece {

	public Rei(Board board, Color color) {
		super(board, color);
	}
	
	public String toString() {
		return "R"; // R = Rei
	}
}
