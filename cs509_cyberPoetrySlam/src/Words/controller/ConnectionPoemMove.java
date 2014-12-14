/**
 * connect selectedWord with connectPoem
 * direction decides who is in front
 * record oldx and oldy of word for undo
 * created and modified by Kuan**/
/**
 * Finally I know why controller should be stateless!!
 * Otherwise undo will destroy everything!
 * --Kuan**/
package Words.controller;
import Words.model.*;

public class ConnectionPoemMove extends Move{
	Poem connectPoem;
	Word selectedWord;
	Board b;
	Row r;
	int oldx;
	int oldy;
	int connectionType;
	
	/**constructor**/
	public ConnectionPoemMove(Poem connectionPoem,Word selectedWord,Board b,int oldx, int oldy,int connectionType){
		this.connectPoem = connectionPoem;
		this.selectedWord = selectedWord;
		this.b = b;
		this.oldx = oldx;
		this.oldy = oldy;
		this.connectionType = connectionType;
		this.r = connectPoem.getOverlapRow(selectedWord);
	}
	
	@Override
	/**connect selected word and poem**/
	public boolean execute(){
		//word in front of poem
		if(connectionType == 1||connectionType == 4||connectionType == 5){
		//r = connectPoem.getOverlapRow(selectedWord);
		r.addWord(selectedWord);
		selectedWord.setLocation(r.getX()-selectedWord.getWidth(), r.getY());
		r.setLocationAfterConnection(selectedWord.getX(),selectedWord.getY());
		connectPoem.setLocationAfterConnection(r.getX(),r.getY());
		b.getWords().remove(selectedWord);
		}
		//poem in front of word
		else if(connectionType == 2||connectionType == 3||connectionType == 6){
			//Row r = connectPoem.getOverlapRow(selectedWord);
			r.addWord(selectedWord);
			selectedWord.setLocation(r.getX()+r.getWidth()-selectedWord.getWidth(),r.getY());
			b.getWords().remove(selectedWord);
		}
		return true;
	}
	
	@Override
	/**undo connection move**/
	public boolean undo(){
		//Row r = connectPoem.getOverlapRow(selectedWord);
		//first word
		if(selectedWord.getX() == r.getX()){
			//add word into board again
			r.removeWord(selectedWord);
			b.getWords().add(selectedWord);
			b.getProtectedWords().add(selectedWord);
			//set location of row
			r.setLocationAfterConnection(r.getX()+selectedWord.getWidth(),r.getY());
			connectPoem.setLocationAfterConnection(r.getX(), r.getY());
			selectedWord.setLocation(oldx,oldy);
		}
		else{
            r.removeWord(selectedWord);
			b.getWords().add(selectedWord);
			b.getProtectedWords().add(selectedWord);
			//set location
			selectedWord.setLocation(oldx,oldy);
		}
		return true;
	}
}
