package Words.model;

import java.util.Stack;

public class Model {
	Board board;
	//Stack<Move> moves = new Stack<Move>();

	/** Currently selected shape (or null if none). */
	Word selected;
	Poem selectedPoem;

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

	*/

	public void setSelected(Word s) {
		selected = s;
	}
	
	public void setSelectedPoem(Poem p){
		selectedPoem = p;
	}

	public Word getSelected() {
		return selected;
	}
	
	public Poem getSelectedPoem(){
		return selectedPoem;
	}
}
