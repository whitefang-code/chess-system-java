package boardgame;

public class Board {

	private int rows;
	private int columns;
	private Piece[][] pieces;

	public Board(final int rows, final int columns) {
		if (rows < 1 || columns < 1) {
			throw new BoardException("Error creating board: there must be at least 1 row and 1 column");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	/*
	 * public void setRows(final int rows) {
	 * this.rows = rows;
	 * }
	 */

	public int getColumns() {
		return columns;
	}

	/*
	 * public void setColumns(final int columns) {
	 * this.columns = columns;
	 * }
	 */

	public Piece piece(final int row, final int column) {
		if (!positionExists(row, column)) {
			throw new BoardException("Position not on the board");
		}
		return pieces[row][column];
	}

	public Piece piece(final Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}

		return pieces[position.getRow()][position.getColumn()];
	}

	public void placePiece(final Piece piece, final Position position) {
		if (thereIsAPiece(position)) {
			throw new BoardException("There is already a piece on position" + position);
		}

		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}

	private boolean positionExists(final int row, final int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}

	public boolean positionExists(final Position position) {

		return positionExists(position.getRow(), position.getColumn());
	}

	public boolean thereIsAPiece(final Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		return piece(position) != null;
	}

}
