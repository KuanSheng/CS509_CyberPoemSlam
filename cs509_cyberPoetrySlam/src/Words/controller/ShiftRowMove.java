/**
 * shift one row in a poem
 * created by KuanSheng**/
package Words.controller;
import java.io.Serializable;

import Words.model.*;

public class ShiftRowMove extends Move implements Serializable{
	Poem shiftPoem;
	Row shiftRow;
	int oldx;
	int oldy;
	int newx;
	int newy;
	
	/**constructor**/
	public ShiftRowMove(Poem shiftPoem,Row shiftRow,int newx,int newy,int oldx,int oldy){
		this.shiftPoem = shiftPoem;
		this.shiftRow = shiftRow;
		this.oldx = oldx;
		this.oldy = oldy;
		this.newx = newx;
		this.newy = newy;
	}
	
	/**process the shift operation**/
	@Override
	public boolean execute(){
		if(shiftRow.equals(shiftPoem.getFirstRow())){
			//when shift the first row, we need to update the
			//location of poem
			shiftRow.setLocation(newx,newy,newx,newy);
			shiftPoem.setLocationAfterConnection(newx,newy);
		}
		else{
			
			shiftRow.setLocation(newx,newy,newx,newy);
		}
		return true;
	}
	
	@Override
	/**undo move, just return it to the original location**/
	public boolean undo(){
		if(shiftRow.equals(shiftPoem.getFirstRow())){
			shiftRow.setLocation(oldx,oldy,newx,newy);
			shiftPoem.setLocationAfterConnection(oldx,oldy);
		}
		else{
			shiftRow.setLocation(oldx,oldy,newx,newy);
		}
		return true;
	}
	
	/**redo the operation**/
	@Override
	public boolean redo(){
		if(shiftRow.equals(shiftPoem.getFirstRow())){
			shiftRow.setLocation(newx,newy,oldx,oldy);
			shiftPoem.setLocationAfterConnection(newx,newy);
		}
		else{
			shiftRow.setLocation(newx,newy,oldx,oldy);
		}
		return true;
	}
}
