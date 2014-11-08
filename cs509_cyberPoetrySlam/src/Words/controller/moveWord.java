package Words.controller;
import Words.model.*;

public class moveWord {
	final Word word;
	int oldx;
	int oldy;
	int newx;
	int newy;
	
	public moveWord(Word w,int oldx,int oldy,int newx,int newy){
		this.word = w;
		this.oldx = oldx;
		this.oldy = oldy;
		this.newx = newx;
		this.newy = newy;
	}
	
	public boolean execute(){
		word.setLocation(newx,newy);
		return true;
	}
	
	public boolean undo(){
		word.setLocation(oldx, oldy);
		return true;
	}
}
