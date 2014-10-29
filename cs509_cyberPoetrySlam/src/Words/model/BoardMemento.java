package Words.model;

import java.io.Serializable;
import java.util.ArrayList;

public class BoardMemento implements Serializable {
ArrayList<Word> stored = new ArrayList<Word>();
	
	/**
	 * Has special permissions to be able to inspect all attributes of Shape objects
	 * and make copy as needed.
	 * 
	 * @param shapes
	 */
	public BoardMemento(ArrayList<Word> shapes) {
		for (Word s : shapes) {
			stored.add(s);
		}
	}

	/**
	 * Unique tag for memento objects on disk
	 * 
	 * @see java.io.Serializable
	 */
	private static final long serialVersionUID = 7708517838167328419L;
}
