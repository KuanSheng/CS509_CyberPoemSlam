package Words.controller;
import Words.model.*;
import Words.view.*;

public class WordPoemConnectionController {
	final Model model;
	final ApplicationCanvas panel;
	final Board b;
	int oldx;
	int oldy;
	int direction;
	Poem selectedPoem = null;
	Poem connectPoem = null;
	
	public WordPoemConnectionController(Model model,ApplicationCanvas panel,int oldx,int oldy,Poem selectedPoem,Poem connectPoem){
		this.model = model;
		this.panel = panel;
		this.b = model.getBoard();
		this.oldx = oldx;
		this.oldy = oldy;
		this.selectedPoem = selectedPoem;
		this.connectPoem = connectPoem;
	}
	
	public void connectPoem(){
		if(selectedPoem.getY() < connectPoem.getY()){
			direction = 1;
		}
		else{
			direction = 2;
		}
		ConnectTwoPoemMove move = new ConnectTwoPoemMove(selectedPoem,connectPoem,b,oldx,oldy,direction);
		if(move.execute()){
			panel.repaint();
		}
		
		model.getMoves().push(move);
	}
}