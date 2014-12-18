package Words.controller;
import Words.model.*;

public class ShiftRowMove extends Move{
	Poem shiftPoem;
	Row shiftRow;
	int oldx;
	int oldy;
	int newx;
	int newy;
	
	public ShiftRowMove(Poem shiftPoem,Row shiftRow,int newx,int newy,int oldx,int oldy){
		this.shiftPoem = shiftPoem;
		this.shiftRow = shiftRow;
		this.oldx = oldx;
		this.oldy = oldy;
		this.newx = newx;
		this.newy = newy;
	}
	
	@Override
	public boolean execute(){
		if(shiftRow.equals(shiftPoem.getFirstRow())){
			shiftRow.setLocation(newx,newy,newx,newy);
			shiftPoem.setLocationAfterConnection(newx,newy);
		}
		else{
			
			shiftRow.setLocation(newx,newy,newx,newy);
		}
		return true;
	}
	
	@Override
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
