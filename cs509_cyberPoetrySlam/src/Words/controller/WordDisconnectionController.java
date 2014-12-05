package Words.controller;

import Words.model.*;
import Words.view.*;

public class WordDisconnectionController {
	final Model model;
	final ApplicationCanvas panel;
	final Board b;
	Poem disConnectPoem;
	
	public WordDisconnectionController(Model model,ApplicationCanvas a,Poem p){
		this.model = model;
		this.panel = a;
		this.b = model.getBoard();
		disConnectPoem = p;
	}
	
	public void disconnectEdgeWord(int type,Row r){
		Word w = model.getSelectedWordinPoem();
		DisconnectionMove move = new DisconnectionMove(this.disConnectPoem,w,r,this.model,type);
		if(move.execute()){
		panel.repaint();
		}
		
		model.getMoves().push(move);
	}
}