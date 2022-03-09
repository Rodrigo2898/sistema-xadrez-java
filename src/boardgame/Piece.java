package boardgame;

public abstract class Piece {

	protected Position position;
	private Board board;
	
	
	public Piece(Board board) {
		this.board = board;
		position = null; //posi��o de uma pe�a rec�m criada
	}

	protected Board getBoard() {
		return board;
	}
	
	public abstract boolean[][] possibleMoves();
	
	//m�todo concreto utilizando o m�todo abstrato = hook methods(m�todo que faz um gancho com a subclasse)
	public boolean possibleMove(Position position) { //verificando se � poss�vel mover a pe�a
		return possibleMoves()[position.getRow()][position.getColumm()];
	}
	
	public boolean isThereAnyPossibleMove() {
		boolean[][]mat = possibleMoves();
		for(int i = 0; i < mat.length; i++) {
			for(int j = 0; j < mat.length; j++) {
				if(mat[i][j]) { //se a matriz na linha i, coluna j for verdadeira, exite um movimento poss�vel
					return true;
				}
			}
		}
		return false;
	}
}
