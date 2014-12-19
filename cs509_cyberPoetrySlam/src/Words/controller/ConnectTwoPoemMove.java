/**connect two poem in vertical direction
 * we need to identify who is on top**/
package Words.controller;
import java.io.Serializable;

import Words.model.*;
public class ConnectTwoPoemMove extends Move implements Serializable{
	Poem selectedPoem;
	Poem connectPoem;
	Poem newPoem;
	Board b;
	int oldx;
	int oldy;
	int newx;
	int newy;
	int direction;
	
	/**Constructor
	 * oldx and oldy record original location to undo**/
	public ConnectTwoPoemMove(Poem selectedPoem, Poem connectPoem,Board b,int oldx,int oldy,int direction){
		this.selectedPoem = selectedPoem;
		this.connectPoem = connectPoem;
		this.direction = direction;
		this.b = b;
		this.oldx = oldx;
		this.oldy = oldy;
	}
	
	/**execute the operation**/
	@Override
	public boolean execute(){
		newPoem = new Poem(selectedPoem,connectPoem,direction);
		b.addPoems(newPoem);
		b.removePoem(selectedPoem);
		b.removePoem(connectPoem);
		return true;
	}
	
	@Override
	public boolean redo(){
		//newPoem = new Poem(selectedPoem,connectPoem,direction);
		selectedPoem.setLocation(newx,newy,oldx,oldy);
		b.addPoems(newPoem);
		b.removePoem(selectedPoem);
		b.removePoem(connectPoem);
		return true;
	}
	
	/**undo operation**/
	@Override
	public boolean undo(){
		b.removePoem(newPoem);
		b.addPoems(selectedPoem);
		b.addPoems(connectPoem);
		newx = selectedPoem.getX();
		newy = selectedPoem.getY();
		selectedPoem.setLocation(oldx,oldy,selectedPoem.getX(),selectedPoem.getY());
		return true;
	}
}