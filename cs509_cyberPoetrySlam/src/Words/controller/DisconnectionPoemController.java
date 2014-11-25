package Words.controller;
import Words.view.*;
import Words.model.*;

public class DisconnectionPoemController {
	Model model;
	ApplicationCanvas panel;
	Poem disconnectPoem;
	Row disconnectRow;
	Board board;
	
	public DisconnectionPoemController(Model model,ApplicationCanvas panel, Poem disconnectPoem,Row disconnectRow){
		this.model = model;
		this.panel = panel;
		this.disconnectPoem = disconnectPoem;
		this.disconnectRow = disconnectRow;
		this.board = model.getBoard();
	}
	
	public void disconnectRow(){
		DisconnectionPoemMove move = new DisconnectionPoemMove(disconnectPoem,disconnectRow,board);
		if(move.execute()){
			panel.repaint();
		}
		
		model.getMoves().push(move);
	}
}
