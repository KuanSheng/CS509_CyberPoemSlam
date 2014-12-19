/**
 * modify redo and exectue because I found that move is different between first executed and 
 * redo.**/
/**created and modified by kuan**/
package Words.controller;
import Words.model.*;

import java.io.Serializable;

public class ConnectionMove extends Move implements Serializable{ // todo check why without Serializable Java complines about store/restore
	Poem newPoem;
	final Word connectWord;
	final Word selectedWord;
	final Board b;
	int oldx;
	int oldy;
	int newx;
	int newy;
	int type;
	
	/**constructor**/
	public ConnectionMove(Word connectWord, Word selectedWord,Board b, int oldx, int oldy){
		this.connectWord = connectWord;
	    this.selectedWord = selectedWord;
		this.b = b;
		this.oldx = oldx;
		this.oldy = oldy;
		this.type = b.getOverlapType(selectedWord, connectWord);
	}

	@Override
	/**execute the operation types represents connection direction**/
	public boolean execute(){
	    //type = b.getOverlapType(selectedWord, this.connectWord);
		switch(type){
		case 3:
			newPoem = new Poem(this.connectWord,selectedWord,1);
		    break;
		case 4:
			newPoem = new Poem(this.connectWord,selectedWord,1);
			break;
		case 2:
			newPoem = new Poem(selectedWord,this.connectWord,2);
			break;
		case 1:
			newPoem = new Poem(selectedWord,this.connectWord,2);
			break;
		case 5:
			newPoem = new Poem(this.connectWord,selectedWord,1);
			break;
		case 6:
			newPoem = new Poem(selectedWord,this.connectWord,2);
			break;
		default:
			newPoem = new Poem(this.connectWord,selectedWord,1);
			break;
		}
		this.newx = connectWord.getX();
		this.newy = connectWord.getY();
		b.addPoems(newPoem);
		b.getWords().remove(this.selectedWord);
		b.getProtectedWords().remove(this.selectedWord);
		b.getWords().remove(this.connectWord);
		b.getProtectedWords().remove(this.connectWord);
		//System.out.println("whywhywhy");
		return true;
	}
	
	/**undo operation**/
	@Override
	public boolean undo(){
		b.getWords().add(selectedWord);
		b.getProtectedWords().add(selectedWord);
		
		b.getWords().add(connectWord);
		b.getProtectedWords().add(connectWord);
		
		b.removePoem(newPoem);
		
		connectWord.setLocation(oldx,oldy);
		return true;
	}
	
	/**add a redo operation because I found that when it is executed the second time,
	 * something has changed, we do not need to new a poem.**/
	@Override
	public boolean redo(){
	
		connectWord.setLocation(newx,newy);
		b.addPoems(newPoem);
		//System.out.println(newPoem.getRowNumber());
		b.getWords().remove(this.selectedWord);
		b.getWords().remove(this.connectWord);
		b.getProtectedWords().remove(this.selectedWord);
		b.getProtectedWords().remove(this.connectWord);
		return true;
	}
}