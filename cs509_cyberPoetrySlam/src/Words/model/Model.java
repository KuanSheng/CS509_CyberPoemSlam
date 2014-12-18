/**
 * Model must store the state of moving words, if the decision is made to go in the flicker free direction.
 * Note change impacts number of areas.**/

package Words.model;

import java.io.Serializable;
import java.util.Stack;

import Words.controller.*;
import Words.view.SwapTable;

public class Model implements Serializable{
	Board board;
	Stack<Move> moves = new Stack<Move>();
	Stack<Move> redoMoves = new Stack<Move>();


    /** Currently selected shape (or null if none). */
    Word highlightWord;
	Word selected;
	Word selectedWordinPoem;
	Poem selectedPoem;
	Poem submittedPoem;
	Row  selectedRow;
	Area selectedArea;
	
	/**constructor**/
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

    public void setHighlightWord(Word highlightWord) {
        this.highlightWord = highlightWord;
    }
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
	
	public void setSubmittedPoem(Poem p){
		this.submittedPoem = p;
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
	
	public Poem getSubmittedPoem(){
		return this.submittedPoem;
	}

    public String toString(){
        String model;
        return board.toString();
    }

    public void restore(Model m){
        board = m.board;
    }
    
    public void recordMove(MoveWord m){
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
    
    public Move removeLastRedoMove(){
    	if(redoMoves.isEmpty()){
    		return null;
    	    }
        return redoMoves.pop();
    }
    
    public Stack<Move> getMoves(){
    	return moves;
    }
    
    public Stack<Move> getRedoMoves(){
    	return redoMoves;
    }

    public Word getHighlightWord() {
        return highlightWord;
    }
}