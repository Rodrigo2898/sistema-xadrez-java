package chess;

import boardgame.Board;
import boardgame.Piece;

public abstract class ChessPiece extends Piece {

	private Color color;

	public ChessPiece(Board board, Color color) {
		super(board); //Repassando para o construtor da superclasse
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	
	
}
