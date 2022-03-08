package chess;

import boardgame.Board;
import chess.pieces.Rei;
import chess.pieces.Torre;

public class ChessMatch {
	
	private Board board;
	
	public ChessMatch() {
		board = new Board(8, 8); //tamanho do tabuleiro de xadrez
		initialSetup();
	}
	
	public ChessPiece[][] getPieces() { //retornando uma matriz de peças correspondentes a partida
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumms()];
		for(int i = 0; i < board.getRows(); i++) {
			for(int j = 0; j < board.getColumms(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return mat;
	}
	
	private void placeNewPiece(char columm, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(columm, row).toPosition());
	}
	private void initialSetup() { //Método responsável para iniciar a partida de xadrez, 
								  //colocando as peças no tabuleiro
		placeNewPiece('b', 6, new Torre(board, Color.WHITE));
		placeNewPiece('e', 8, new Rei(board, Color.BLACK));
		placeNewPiece('e', 1, new Rei(board, Color.WHITE));
	}
}
