package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	private ChessMatch chessMatch;

	public King(final Board board, final Color color, final ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	@Override
	public String toString() {
		return "K";
	}

	private boolean canMove(final Position position) {
		final ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}

	private boolean testRookcastling(final Position position) {
		final ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;

	}

	@Override
	public boolean[][] possibleMoves() {
		final boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		final Position p = new Position(0, 0);

		// above

		p.setValues(position.getRow() - 1, position.getColumn());

		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// below

		p.setValues(position.getRow() + 1, position.getColumn());

		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// left

		p.setValues(position.getRow(), position.getColumn() - 1);

		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// right

		p.setValues(position.getRow(), position.getColumn() + 1);

		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// nw

		p.setValues(position.getRow() - 1, position.getColumn() - 1);

		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// ne

		p.setValues(position.getRow() - 1, position.getColumn() + 1);

		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// sw

		p.setValues(position.getRow() + 1, position.getColumn() - 1);

		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// se

		p.setValues(position.getRow() + 1, position.getColumn() + 1);

		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		// # specialmove castling

		if (getMoveCount() == 0 && !chessMatch.getCheck()) {
			// #Specialmove castling kingside rook
			final Position posT1 = new Position(position.getRow(), position.getColumn() + 3);
			if (testRookcastling(posT1)) {
				final Position p1 = new Position(position.getRow(), position.getColumn() + 1);
				final Position p2 = new Position(position.getRow(), position.getColumn() + 2);
				if (getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
					mat[position.getRow()][position.getColumn() + 2] = true;
				}
			}
			// queenside Rook
			final Position posT2 = new Position(position.getRow(), position.getColumn() - 4);
			if (testRookcastling(posT2)) {
				final Position p1 = new Position(position.getRow(), position.getColumn() - 1);
				final Position p2 = new Position(position.getRow(), position.getColumn() - 2);
				final Position p3 = new Position(position.getRow(), position.getColumn() - 3);
				if (getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
					mat[position.getRow()][position.getColumn() - 2] = true;
				}
			}
		}

		return mat;
	}

}
