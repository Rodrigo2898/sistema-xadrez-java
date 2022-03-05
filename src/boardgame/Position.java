package boardgame;

public class Position {
	
	private int row;
	private int columm;

	public Position(int row, int columm) {
		this.row = row;
		this.columm = columm;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumm() {
		return columm;
	}

	public void setColumm(int columm) {
		this.columm = columm;
	}
	
	@Override
	public String toString() {
		//Imprimindo a posição na tela
		return row + ", " + columm;
	}
}
