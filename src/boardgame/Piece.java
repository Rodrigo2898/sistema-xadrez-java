package boardgame;

public class Piece {

	protected Position position;
	private Board board;
	
	
	public Piece(Board board) {
		this.board = board;
		position = null; //posição de uma peça recém criada
	}

	protected Board getBoard() {
		return board;
	}	
}
