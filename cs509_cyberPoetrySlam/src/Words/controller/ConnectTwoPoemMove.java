package Words.controller;
import Words.model.*;

public class ConnectTwoPoemMove extends Move{
	Poem selectedPoem;
	Poem connectPoem;
	Poem newPoem;
	Board b;
	int oldx;
	int oldy;
	int direction;
	
	/**constructor**/
	public ConnectTwoPoemMove(Poem selectedPoem, Poem connectPoem,Board b,int oldx,int oldy,int direction){
		this.selectedPoem = selectedPoem;
		this.connectPoem = connectPoem;
		//decide which poem on top
		this.direction = direction;
		this.b = b;
		this.oldx = oldx;
		this.oldy = oldy;
	}
	
	@Override
	/**connect two poem**/
	public boolean execute(){
		newPoem = new Poem(selectedPoem,connectPoem,direction);
		b.addPoems(newPoem);
		b.removePoem(selectedPoem);
		b.removePoem(connectPoem);
		return true;
	}
	
	@Override
	/**undo disconnect operation**/
	public boolean undo(){
		b.removePoem(newPoem);
		b.addPoems(selectedPoem);
		b.addPoems(connectPoem);
		selectedPoem.setLocation(oldx,oldy,selectedPoem.getX(),selectedPoem.getY());
		return true;
	}
}
