package Words.model;

import java.io.Serializable;
import java.util.Stack;

import Words.controller.*;

public class Model implements Serializable{
	Board board;
	Stack<Move> moves = new Stack<Move>();

	/** Currently selected shape (or null if none). */
	Word selected;
	Word selectedWordinPoem;
	Poem selectedPoem;
	Row  selectedRow;
	Area selectedArea;

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
	
	public void setSelectedRow(Row r){
		selectedRow = r;
	}
	
	public void setSelectedWordinPoem(Word w){
		selectedWordinPoem = w;
	}
	
	public void setSelectedArea(int x,int y, int width,int height){
		Area area = new Area(x,y,width,height);
		this.selectedArea = area;
	}

	public Word getSelected() {
		return this.selected;
	}
	
	public Poem getSelectedPoem(){
		return this.selectedPoem;
	}
	
	public Row getSelectedRow(){
		return this.selectedRow;
	}
	
	public Word getSelectedWordinPoem(){
		return selectedWordinPoem;
	}
	
	public Area getSelectedArea(){
		return this.selectedArea;
	}

    public String toString(){
        String model;
        return board.toString();
    }

    public void restore(Model m){
        board = m.board;
    }
    
    public void recordMove(moveWord m){
    	moves.add(m);
    }
    
    public void recordConnectionMove(ConnectionMove m){
       moves.add(m);
    }
    
    public Move removeLastMove(){
    	if (moves.isEmpty()) {
    		return null; 
    		}
		return moves.pop();
    }
    
    public Stack<Move> getMoves(){
    	return moves;
    }
}