/**make poem move around the protected area**/
package Words.controller;
import java.io.Serializable;

import Words.model.*;

public class MovePoem extends Move implements Serializable{
	final Poem poem;
	int oldx;
	int oldy;
	int newx;
	int newy;
	
	/**constructor**/
	public MovePoem(Poem poem,int oldx,int oldy,int newx,int newy){
		this.poem = poem;
		this.oldx = oldx;
		this.oldy = oldy;
		this.newx = newx;
		this.newy = newy;
	}
	
	/**move poem**/
	@Override
	public boolean execute(){
		poem.setLocation(newx, newy, newx, newy);
		return true;
	}
	
	/**undo move**/
	@Override
	public boolean undo(){
		poem.setLocation(oldx,oldy,newx,newy);
		return true;
	}
	
	/**redo we need to move the poem immediately**/
	public boolean redo(){
		poem.setLocation(newx,newy,oldx,oldy);
		return true;
	}
}
