package Words.controller;
import Words.model.*;

import java.io.Serializable;

public class ConnectionMove extends Move implements Serializable{ // todo check why without Serializable Java complines about store/restore
	//generated poem after connecttion
	Poem newPoem;
	final Word connectWord;
	final Word selectedWord;
	final Board b;
	//original location of selected word
	int oldx;
	int oldy;
	//connection type: 1 or 2
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
	/**connect two words**/
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
		
		b.addPoems(newPoem);
		b.getWords().remove(selectedWord);
		b.getWords().remove(this.connectWord);
		return true;
	}
	
	@Override
	/*undo connection operation**/
	public boolean undo(){
		b.getWords().add(selectedWord);
		b.getProtectedWords().add(selectedWord);
		
		b.getWords().add(connectWord);
		b.getProtectedWords().add(connectWord);
		
		b.removePoem(newPoem);
		
		connectWord.setLocation(oldx,oldy);
		return true;
	}
}
