package Words.controller;
import Words.model.*;

import java.io.Serializable;

public class moveWord extends Move implements Serializable{
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
	
	@Override
	public boolean execute(){
		word.setLocation(newx,newy);
		return true;
	}
	
	@Override
	public boolean undo(){
		word.setLocation(oldx, oldy);
		return true;
	}
	
	@Override
	public boolean redo(){
		word.setLocation(newx,newy);
		return true;
	}
}
