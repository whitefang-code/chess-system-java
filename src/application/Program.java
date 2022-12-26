package application;

import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(final String[] args) {

		final Scanner sc = new Scanner(System.in);
		final ChessMatch chessMatch = new ChessMatch();

		while (true) {
			UI.printBoard(chessMatch.getPieces());
			System.out.println();
			System.out.print("Source: ");
			final ChessPosition source = UI.readChessPosition(sc);

			System.out.print("Target : ");
			final ChessPosition target = UI.readChessPosition(sc);
			final ChessPiece capturedPiece = chessMatch.performChessMove(source, target);

		}
	}

}
