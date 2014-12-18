/**
 * created and modified by KuanSheng
 * record every word move operation**/
package Words.controller;
import Words.model.*;

import java.io.Serializable;

public class MoveWord extends Move implements Serializable{
	final Word word;
	int oldx;
	int oldy;
	int newx;
	int newy;
	
	/**Constructor**/
	public MoveWord(Word w,int oldx,int oldy,int newx,int newy){
		this.word = w;
		this.oldx = oldx;
		this.oldy = oldy;
		this.newx = newx;
		this.newy = newy;
	}
	
	/**execute the operation**/
	@Override
	public boolean execute(){
		word.setLocation(newx,newy);
		return true;
	}
	
	/**undo the operation**/
	@Override
	public boolean undo(){
		word.setLocation(oldx, oldy);
		return true;
	}
	
	/**redo the operation**/
	@Override
	public boolean redo(){
		word.setLocation(newx,newy);
		return true;
	}
}