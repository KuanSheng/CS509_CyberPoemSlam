/**
 * connect two words, direction decides who is in front.
 * it should be stateless!
 * created and modified by Kuan**/
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
	int type;
	
	public ConnectionMove(Word connectWord, Word selectedWord,Board b, int oldx, int oldy){
		this.connectWord = connectWord;
		this.selectedWord = selectedWord;
		this.b = b;
		this.oldx = oldx;
		this.oldy = oldy;
		this.type = b.getOverlapType(selectedWord, connectWord);
	}
	
	@Override
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
		System.out.println(newPoem.getRowNumber());
		b.getWords().remove(selectedWord);
		b.getWords().remove(this.connectWord);
		return true;
	}
	
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
}
