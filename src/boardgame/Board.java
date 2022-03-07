package boardgame;

public class Board {
	
	private int rows;
	private int columms;
	private Piece[][] pieces;
	
	public Board(int rows, int columms) {
		this.rows = rows;
		this.columms = columms;
		pieces = new Piece[rows][columms];
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumms() {
		return columms;
	}

	public void setColumms(int columms) {
		this.columms = columms;
	}
	
	public Piece piece(int rows, int columms) {
		return pieces[rows][columms];
	}
	
	public Piece piece(Position position) {
		return pieces[position.getRow()][position.getColumm()];
	}
	
	public void placePiece(Piece piece, Position position) {
		pieces[position.getRow()][position.getColumm()] = piece;
		piece.position = position;
	}
}
