package application;

import chess.ChessMatch;

public class Program {

	public static void main(final String[] args) {

		final ChessMatch chessMatch = new ChessMatch();
		UI.printBoard(chessMatch.getPieces());

	}

}
