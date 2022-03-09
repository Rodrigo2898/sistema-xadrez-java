package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.Rei;
import chess.pieces.Torre;

public class ChessMatch {
	
	private Board board;
	
	public ChessMatch() {
		board = new Board(8, 8); //tamanho do tabuleiro de xadrez
		initialSetup();
	}
	
	public ChessPiece[][] getPieces() { //retornando uma matriz de pe�as correspondentes a partida
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumms()];
		for(int i = 0; i < board.getRows(); i++) {
			for(int j = 0; j < board.getColumms(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return mat;
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		Piece capturedPiece = makeMove(source, target);
		return (ChessPiece)capturedPiece;
	}
	
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source); //removendo a pe�a da posi��o de origem
		Piece capturedPiece = board.removePiece(target); //removendo a poss�vel pe�a da posi��o de destino
		board.placePiece(p, target); //colocando a posi�ao que tava na origem na posi��o de destino
		return capturedPiece; //retornando a pe�a capturada
	}
	
	private void validateSourcePosition(Position position) {
		if(!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position");
		}
		if(!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible moves for the chosen pieces");
		}
	}
	
	private void placeNewPiece(char columm, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(columm, row).toPosition());
	}
	private void initialSetup() { //M�todo respons�vel para iniciar a partida de xadrez, 
								  //colocando as pe�as no tabuleiro
		placeNewPiece('c', 1, new Torre(board, Color.WHITE));
        placeNewPiece('c', 2, new Torre(board, Color.WHITE));
        placeNewPiece('d', 2, new Torre(board, Color.WHITE));
        placeNewPiece('e', 2, new Torre(board, Color.WHITE));
        placeNewPiece('e', 1, new Torre(board, Color.WHITE));
        placeNewPiece('d', 1, new Rei(board, Color.WHITE));

        placeNewPiece('c', 7, new Torre(board, Color.BLACK));
        placeNewPiece('c', 8, new Torre(board, Color.BLACK));
        placeNewPiece('d', 7, new Torre(board, Color.BLACK));
        placeNewPiece('e', 7, new Torre(board, Color.BLACK));
        placeNewPiece('e', 8, new Torre(board, Color.BLACK));
        placeNewPiece('d', 8, new Rei(board, Color.BLACK));
	}
}
