/**
 * created by KuanSheng**/
package Words.controller;
import Words.model.*;
import Words.view.*;

public class ShiftRowController {
	Model model;
	ApplicationCanvas panel;
	Poem shiftPoem;
	Row shiftRow;
	int newx;
	int newy;
	int oldx;
	int oldy;
	
	/**Constructor**/
	public ShiftRowController(Model model,ApplicationCanvas panel,Poem shiftpoem,Row shiftrow,int newx,int newy,int oldx,int oldy){
		this.model = model;
		this.panel = panel;
		this.shiftPoem = shiftpoem;
		this.shiftRow = shiftrow;
		this.newx = newx;
		this.newy = newy;
		this.oldx = oldx;
		this.oldy = oldy;
	}
	
	/**execute the shift operation**/
	public void shift(){
		ShiftRowMove move = new ShiftRowMove(shiftPoem,shiftRow,newx,newy,oldx,oldy);
		if(move.execute()){
			panel.repaint();
		}
		model.getMoves().push(move);
	}
}
