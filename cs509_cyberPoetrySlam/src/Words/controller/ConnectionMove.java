package Words.controller;
import Words.model.*;

public class ConnectionMove extends Move{
	Poem newPoem;
	final Word connectWord;
	final Word selectedWord;
	final Board b;
	int oldx;
	int oldy;
	
	public ConnectionMove(Word connectWord, Word selectedWord,Board b, int oldx, int oldy){
		this.connectWord = connectWord;
		this.selectedWord = selectedWord;
		this.b = b;
		this.oldx = oldx;
		this.oldy = oldy;
	}
	
	@Override
	public boolean execute(){
		int type = b.getOverlapType(selectedWord, this.connectWord);
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
