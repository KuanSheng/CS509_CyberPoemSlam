package Words.controller;
import Words.model.*;
import Words.view.*;

public class RedoController {
	Model model;
	ApplicationCanvas canvas;
	
	public RedoController(Model m, ApplicationCanvas canvas) {
		this.model = m;
		this.canvas = canvas;
	}
	
	public boolean process(){
		Move m = model.removeLastRedoMove();
		if (m == null) {
			return false; 
		}
		m.redo();
		model.getMoves().push(m);
		// force board to redraw
		canvas.repaint();
		return true;
	}
}
