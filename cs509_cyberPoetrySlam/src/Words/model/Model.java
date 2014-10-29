package Words.model;

import java.io.Serializable;
import java.util.Stack;

public class Model implements Serializable {
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

	*/

	public void setSelected(Word s) {
		selected = s;
	}

	public Word getSelected() {
		return selected;
	}

    public String toString(){
        String model;
        return board.toString();
    }

    public void restore(Model m){
        board = m.board;
    }
}
