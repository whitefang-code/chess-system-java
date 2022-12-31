package application;

import java.util.ArrayList;
//https://github.com/acenelio/chess-system-java/blob/master/src/application/UI.java
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(final String[] args) {

		final Scanner sc = new Scanner(System.in);
		final ChessMatch chessMatch = new ChessMatch();
		final List<ChessPiece> captured = new ArrayList<>();

		while (!chessMatch.getCheckMate()) {
			try {
				UI.printMatch(chessMatch, captured);
				System.out.println();
				System.out.print("Source: ");
				final ChessPosition source = UI.readChessPosition(sc);

				final boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves);
				System.out.print("Target : ");
				final ChessPosition target = UI.readChessPosition(sc);
				final ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
				if (capturedPiece != null) {
					captured.add(capturedPiece);
				}
				if (chessMatch.getPromoted() != null) {
					System.out.println("Enter piece for promotion(B/N/R/Q)");
					final String type = sc.nextLine();
					chessMatch.replacePromotedPiece(type);
				}
			} catch (final ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			} catch (final InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		UI.clearScreen();
		UI.printMatch(chessMatch, captured);
	}

}
