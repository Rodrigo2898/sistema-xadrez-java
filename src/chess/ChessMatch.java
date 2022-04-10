package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.Bispo;
import chess.pieces.Cavalo;
import chess.pieces.Peao;
import chess.pieces.Rainha;
import chess.pieces.Rei;
import chess.pieces.Torre;

public class ChessMatch {
	
	private int turn;
	private Color currentPlayer;
	private Board board;
	private boolean check;
	private boolean checkMate;
	private ChessPiece enPassantVulnerable;
	
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();
	
	public ChessMatch() {
		board = new Board(8, 8); //tamanho do tabuleiro de xadrez
		turn = 1; //turno no inicio da partida vale 1
		currentPlayer = Color.WHITE;
		initialSetup();
	}
	
	public int getTurn() {
		return turn;
	}
	
	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	public boolean getCheckMate() {
		return checkMate;
	}
	
	public ChessPiece getEnPassantVulnerable() {
		return enPassantVulnerable;
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
	
	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
		Position position =  sourcePosition.toPosition();
		validateSourcePosition(position);
		return board.piece(position).possibleMoves(); 
	}
	
					//executando o movimento de xadrez
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);
		
		if(testCheck(currentPlayer)) { //se o jogador tiver se auto colocado em check
			undoMove(source, target, capturedPiece); //desfazendo movimento
			throw new ChessException("You can't put yourself in check");
		}
		
		ChessPiece movedPiece = (ChessPiece)board.piece(target);
		
		check = (testCheck(opponent(currentPlayer))) ? true : false;
		
		if(testCheckMate(opponent(currentPlayer))) {
			checkMate = true;
		}
		else {
			nextTurn();
		}
		
		// Movimento especial en passant
		if(movedPiece instanceof Peao && (target.getRow() == source.getRow() - 2 || target.getRow() == source.getRow() + 2)) {
			enPassantVulnerable = movedPiece;
		}
		else {
			enPassantVulnerable = null;
		}
		
		return (ChessPiece)capturedPiece;
	}
	
	private Piece makeMove(Position source, Position target) {
		ChessPiece p = (ChessPiece)board.removePiece(source); //removendo a peça da posição de origem
		p.increaseMoveCount();
		Piece capturedPiece = board.removePiece(target); //removendo a possível peça da posição de destino
		board.placePiece(p, target); //colocando a posiçao que tava na origem na posição de destino
		
		if(capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		
		// Movimento especial Roque pequeno
		if(p instanceof Rei && target.getColumm() == source.getColumm() + 2) {
			Position sourceT = new Position(source.getRow(), source.getColumm() + 3);
			Position tagetT = new Position(source.getRow(), source.getColumm() + 1);
			ChessPiece torre = (ChessPiece)board.removePiece(sourceT);
			board.placePiece(torre, tagetT);
			torre.increaseMoveCount();
		}
		
		// Movimento especial Roque grande
		if(p instanceof Rei && target.getColumm() == source.getColumm() - 2) {
			Position sourceT = new Position(source.getRow(), source.getColumm() - 4);
			Position tagetT = new Position(source.getRow(), source.getColumm() - 1);
			ChessPiece torre = (ChessPiece)board.removePiece(sourceT);
			board.placePiece(torre, tagetT);
			torre.increaseMoveCount();
		}
		
		// Movimento especial en passant
		if(p instanceof Peao) {
			if(source.getColumm() != target.getColumm() && capturedPiece == null) {
				Position peaoPosicao;
				if(p.getColor() == Color.WHITE) {
					peaoPosicao = new Position(target.getRow() + 1, target.getColumm());
				}
				else {
					peaoPosicao = new Position(target.getRow() - 1, target.getColumm());
				}
				capturedPiece = board.removePiece(peaoPosicao);
				capturedPieces.add(capturedPiece);
				piecesOnTheBoard.remove(capturedPiece);
			}
		}
		return capturedPiece; //retornando a peça capturada
	}
	           //desfazer movimento
	private void undoMove(Position source, Position target, Piece capturedPiece) {
		ChessPiece p = (ChessPiece)board.removePiece(target);
		p.decreaseMoveCount();
		board.placePiece(p, source);
	
		if(capturedPiece != null) {
			board.placePiece(capturedPiece, target); //voltando a peça capturada para a posição de destino
			capturedPieces.remove(capturedPiece); //retirando a peça da lista de peças capturadas
			piecesOnTheBoard.add(capturedPiece);  //adicionando na lista de peças no tabuleiro
		}
		
		// Movimento especial Roque pequeno
		if(p instanceof Rei && target.getColumm() == source.getColumm() + 2) {
			Position sourceT = new Position(source.getRow(), source.getColumm() + 3);
			Position targetT = new Position(source.getRow(), source.getColumm() + 1);
			ChessPiece torre = (ChessPiece)board.removePiece(targetT);
			board.placePiece(torre, sourceT);
			torre.decreaseMoveCount();
		}
		
		// Movimento especial Roque grande
		if(p instanceof Rei && target.getColumm() == source.getColumm() - 2) {
			Position sourceT = new Position(source.getRow(), source.getColumm() - 4);
			Position targetT = new Position(source.getRow(), source.getColumm() - 1);
			ChessPiece torre = (ChessPiece)board.removePiece(targetT);
			board.placePiece(torre, sourceT);
			torre.decreaseMoveCount();
		}
		
		// Movimento especial en passant
		if(p instanceof Peao) {
			if(source.getColumm() != target.getColumm() && capturedPiece == enPassantVulnerable) {
				ChessPiece peao = (ChessPiece)board.removePiece(target);
				Position peaoPosicao;
				if(p.getColor() == Color.WHITE) {
					peaoPosicao = new Position(3, target.getColumm());
				}
				else {
					peaoPosicao = new Position(4, target.getColumm());
				}
				board.placePiece(peao, peaoPosicao);
			}
		}
		
	}
	
	private void validateSourcePosition(Position position) {
		if(!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position");
		}                   //downcasting
		if(currentPlayer != ((ChessPiece)board.piece(position)).getColor()) {
			throw new ChessException("The chosen piece is not yours");
		}
		if(!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible moves for the chosen pieces");
		}
	}
	
	private void validateTargetPosition(Position source, Position target) {
		if(!board.piece(source).possibleMove(target)) {
			throw new ChessException("The chosen position can't move to target position");
		}
	}
	
	private void nextTurn() {
		turn++; //incrementando o turno, mudando a cada rodada
		//se o jogador atual for igual a Color.White, então ele vai ser o Color.Black,
		//caso contrário ele vai ser o Color.White
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	//devolvendo o oponente de uma cor
	private Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	//Localizando o rei de uma determinada cor
	
	private ChessPiece rei(Color color) {
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for(Piece p : list) {
			if(p instanceof Rei) {
				return (ChessPiece)p;
			}
		}
		//Caso o rei não exista
		throw new IllegalStateException("There is no " + color + " king on the board");
	}
	
	private boolean testCheck(Color color) {
		Position kingPosition = rei(color).getChessPosition().toPosition();
		//Para cada peça contida na lista, é verificado se existe algum movimento possível até a posição do rei
		List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
		for(Piece p : opponentPieces) {
			boolean[][] mat = p.possibleMoves();
			if(mat[kingPosition.getRow()][kingPosition.getColumm()]) {
				return true;
			}
		}
		return false;
	}
	
	public boolean testCheckMate(Color color) {
		if(!testCheck(color)) {
			return false;
		}
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		
		//se existir alguma peça p da lista, que possua um movimento que tira do check, se retorna falso
		for(Piece p : list) {
			boolean[][] mat = p.possibleMoves();
			for(int i = 0; i < board.getRows(); i++) {
				for(int j = 0; j < board.getColumms(); j++) {
					if(mat[i][j]) {
						Position source = ((ChessPiece)p).getChessPosition().toPosition();
						Position target = new Position(i, j);
						Piece capturedPiece = makeMove(source, target);
						boolean testCheck = testCheck(color);
						undoMove(source, target, capturedPiece);
						if(!testCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	private void placeNewPiece(char columm, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(columm, row).toPosition());
		piecesOnTheBoard.add(piece); //colocando peças dentro da lista de peças do tabuleiro
	}
	private void initialSetup() { //Método responsável para iniciar a partida de xadrez, 
								 //colocando as peças no tabuleiro
		
		placeNewPiece('a', 1, new Torre(board, Color.WHITE));
		placeNewPiece('b', 1, new Cavalo(board, Color.WHITE));
		placeNewPiece('c', 1, new Bispo(board, Color.WHITE));
		placeNewPiece('d', 1, new Rainha(board, Color.WHITE));
		placeNewPiece('e', 1, new Rei(board, Color.WHITE, this)); //o this está colocando referência para a partida
		placeNewPiece('f', 1, new Bispo(board, Color.WHITE));
		placeNewPiece('g', 1, new Cavalo(board, Color.WHITE));
		placeNewPiece('h', 1, new Torre(board, Color.WHITE));
		placeNewPiece('a', 2, new Peao(board, Color.WHITE, this));
		placeNewPiece('b', 2, new Peao(board, Color.WHITE, this));
		placeNewPiece('c', 2, new Peao(board, Color.WHITE, this));
		placeNewPiece('d', 2, new Peao(board, Color.WHITE, this));
		placeNewPiece('e', 2, new Peao(board, Color.WHITE, this));
		placeNewPiece('f', 2, new Peao(board, Color.WHITE, this));
		placeNewPiece('g', 2, new Peao(board, Color.WHITE, this));
		placeNewPiece('h', 2, new Peao(board, Color.WHITE, this));
		
		placeNewPiece('a', 8, new Torre(board, Color.BLACK));
		placeNewPiece('b', 8, new Cavalo(board, Color.BLACK));
		placeNewPiece('c', 8, new Bispo(board, Color.BLACK));
		placeNewPiece('d', 8, new Rainha(board, Color.BLACK));
		placeNewPiece('e', 8, new Rei(board, Color.BLACK, this));
		placeNewPiece('f', 8, new Bispo(board, Color.BLACK));
		placeNewPiece('g', 8, new Cavalo(board, Color.BLACK));
		placeNewPiece('h', 8, new Torre(board, Color.BLACK));
		placeNewPiece('a', 7, new Peao(board, Color.BLACK, this));
		placeNewPiece('b', 7, new Peao(board, Color.BLACK, this));
		placeNewPiece('c', 7, new Peao(board, Color.BLACK, this));
		placeNewPiece('d', 7, new Peao(board, Color.BLACK, this));
		placeNewPiece('e', 7, new Peao(board, Color.BLACK, this));
		placeNewPiece('f', 7, new Peao(board, Color.BLACK, this));
		placeNewPiece('g', 7, new Peao(board, Color.BLACK, this));
		placeNewPiece('h', 7, new Peao(board, Color.BLACK, this));
		
	}
}
