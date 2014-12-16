package Words.controller;
import Words.view.*;
import Words.model.*;

public class DisconnectionPoemController {
	Model model;
	ApplicationCanvas panel;
	Poem disconnectPoem;
	Row disconnectRow;
	Board board;
	
	public DisconnectionPoemController(Model model,ApplicationCanvas panel){
		this.model = model;
		this.panel = panel;
		this.board = model.getBoard();
		this.disconnectRow = model.getSelectedRow();
		this.disconnectPoem = board.getPoemByRow(disconnectRow);
	}
	
	public void disconnectRow(){
		if(disconnectRow == null){ 
			return;
		}
		DisconnectionPoemMove move = new DisconnectionPoemMove(disconnectPoem,disconnectRow,board);
		if(move.execute()){
			panel.repaint();
		}
		
		model.getMoves().push(move);
	}
}
