package Words.controller;
import Words.model.*;
import Words.view.*;

public class UndoController {
	Model model;
	ApplicationCanvas canvas;
	
	public UndoController(Model m, ApplicationCanvas canvas) {
		this.model = m;
		this.canvas = canvas;
	}
	
	public boolean process() {
		
		Move m = model.removeLastMove();
		if (m == null) {
			return false; 
		}
		m.undo();

		// force board to redraw
		canvas.repaint();
		return true;
   }
}