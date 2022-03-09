package boardgame;

public abstract class Piece {

	protected Position position;
	private Board board;
	
	
	public Piece(Board board) {
		this.board = board;
		position = null; //posição de uma peça recém criada
	}

	protected Board getBoard() {
		return board;
	}
	
	public abstract boolean[][] possibleMoves();
	
	//método concreto utilizando o método abstrato = hook methods(método que faz um gancho com a subclasse)
	public boolean possibleMove(Position position) { //verificando se é possível mover a peça
		return possibleMoves()[position.getRow()][position.getColumm()];
	}
	
	public boolean isThereAnyPossibleMove() {
		boolean[][]mat = possibleMoves();
		for(int i = 0; i < mat.length; i++) {
			for(int j = 0; j < mat.length; j++) {
				if(mat[i][j]) { //se a matriz na linha i, coluna j for verdadeira, exite um movimento possível
					return true;
				}
			}
		}
		return false;
	}
}
