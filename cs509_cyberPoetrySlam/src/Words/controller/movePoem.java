package Words.controller;
import Words.model.*;

public class movePoem {
	final Poem poem;
	int oldx;
	int oldy;
	int newx;
	int newy;
	
	public movePoem(Poem poem,int oldx,int oldy,int newx,int newy){
		this.poem = poem;
		this.oldx = oldx;
		this.oldy = oldy;
		this.newx = newx;
		this.newy = newy;
	}
	
	public boolean execute(){
		poem.setLocation(newx, newx, oldx, oldy);
		return true;
	}
	
	public boolean undo(){
		poem.setLocation(oldx,oldy,oldx,oldy);
		return true;
	}
}
