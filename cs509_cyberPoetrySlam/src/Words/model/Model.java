package Words.model;

import java.util.Stack;

public class Model {
	Board board;
	//Stack<Move> moves = new Stack<Move>();
	
	/** Currently selected shape (or null if none). */
	Word selected;

	public Model(Board b) {
		board = b;
	}
	
	public Model() {
		this(new Board());
	}
	
	public void setBoard(Board b) {
		board = b;
	}

	public Board getBoard() {
		return board;
	}

	/**public void recordMove(MoveWord move) {
		moves.add(move);
	}

	public Move removeLastMove() {
		if (moves.isEmpty()) { return null; }
		return moves.pop();
	}*/

	public void setSelected(Word s) {
		selected = s;
	}

	public Word getSelected() {
		return selected;
	}
}
