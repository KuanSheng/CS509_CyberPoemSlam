/**
 * Always be careful about the object transform!
 * it may kill your undo function!
 *                                  By Kuan**/
package Words.controller;
import Words.model.*;
import Words.view.*;

public class UndoController {
	Model model;
	ApplicationCanvas canvas;
	
	/**constructor**/
	public UndoController(Model m, ApplicationCanvas canvas) {
		this.model = m;
		this.canvas = canvas;
	}
	
	/**Execute the undo operation**/
	public boolean process() {
		
		Move m = model.removeLastMove();
		if (m == null) {
			return false; 
		}
		m.undo();
		//record it for redo
		model.getRedoMoves().push(m);
		// force board to redraw
		canvas.repaint();
		return true;
   }
}
