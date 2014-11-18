package Words.controller;
import Words.model.*;

public class ShiftRowMove extends Move{
	Poem shiftPoem;
	Row shiftRow;
	int oldx;
	int oldy;
	int newx;
	int newy;
	
	public ShiftRowMove(Poem shiftPoem,Row shiftRow,int oldx,int oldy,int newx,int newy){
		this.shiftPoem = shiftPoem;
		this.shiftRow = shiftRow;
		this.oldx = oldy;
		this.oldy = oldy;
		this.newx = newx;
		this.newy = newy;
	}
	
	@Override
	public boolean execute(){
		if(shiftRow.equals(shiftPoem.getFirstRow())){
			shiftRow.setLocation(newx,newy,oldx,oldy);
			shiftPoem.setLocation(newx,newy,oldx,oldy);
		}
		else{
			shiftRow.setLocation(newx,newy,oldx,oldy);
		}
		return true;
	}
	
	@Override
	public boolean undo(){
		if(shiftRow.equals(shiftPoem.getFirstRow())){
			shiftRow.setLocation(oldx,oldy,oldx,oldy);
			shiftPoem.setLocation(oldx,oldy,oldx,oldy);
		}
		else{
			shiftRow.setLocation(newx,newy,oldx,oldy);
		}
		return true;
	}
}
