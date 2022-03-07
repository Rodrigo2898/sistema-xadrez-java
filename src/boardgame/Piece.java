package boardgame;

public class Piece {

	protected Position position;
	private Board board;
	
	
	public Piece(Board board) {
		this.board = board;
		position = null; //posi��o de uma pe�a rec�m criada
	}

	protected Board getBoard() {
		return board;
	}	
}
