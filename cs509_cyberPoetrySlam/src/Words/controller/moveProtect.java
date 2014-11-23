package Words.controller;
import Words.model.*;

public class moveProtect extends Move{
	int oldx;
	int oldy;
	int newx;
	int newy;
	Board b;
	Word word;
	
	public moveProtect(Word word, Board b,int oldx, int oldy, int newx,int newy){
		this.oldx = oldx;
		this.oldy = oldy;
		this.newx = newx;
		this.newy = newy;
		this.word = word;
		this.b = b;
	}
	
	@Override
	public boolean execute(){
		if(oldy > 300&&newy < 300){
			b.protectWords(word);
		}
		
		if(oldy < 300&&newy > 300){
			b.releaseWords(word);
		}
		
		word.setLocation(newx,newy);
		return true;
	}
	
	@Override
	public boolean undo(){
		if(oldy > 300&&newy < 300){
			b.releaseWords(word);
		}
		
		if(oldy < 300&&newy > 300){
			b.protectWords(word);
		}
		
		word.setLocation(oldx,oldy);
		return true;
	}
}
