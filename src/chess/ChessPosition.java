package chess;

import boardgame.Position;

public class ChessPosition {

	private char columm;
	private int row;
	
	public ChessPosition(char columm, int row) {
		if(columm < 'a' || columm > 'h' || row < 1 || row > 8) {
			throw new ChessException("Error instanting ChessPosition. Valid values are from a1 to h8.");
		}
		this.columm = columm;
		this.row = row;
	}

	public char getColumm() {
		return columm;
	}

	public int getRow() {
		return row;
	}

	protected Position toPosition() {
		return new Position(8 - row, columm - 'a');
	}
					//pega uma position de matriz, e converte para a posição de xadrez
	protected static ChessPosition fromPosition(Position position) {
		return new ChessPosition((char) ('a' + position.getColumm()), 8 - position.getRow());	
	}
	
	@Override
	public String toString() {
		return "" + columm + row;
	}
}
