package Words.controller;

import java.awt.event.MouseAdapter;
import Words.model.*;

import Words.model.Model;
import Words.view.ApplicationCanvas;

public class WordConnectionController extends MouseAdapter {
	final Model model;
	final ApplicationCanvas panel;
	final Board b;
	int oldx;
	int oldy;
	Word connectWord = null;
	Poem connectPoem = null;
	
	public WordConnectionController(Model m,ApplicationCanvas p,Word w,int oldx, int oldy){
		this.model = m;
		this.panel = p;
		this.connectWord = w;
		this.oldx = oldx;
		this.oldy = oldy;
		b = model.getBoard();
	}
	
	public WordConnectionController(Model m, ApplicationCanvas a, Poem p,int oldx,int oldy){
		this.model = m;
		this.panel = a;
		this.connectPoem = p;
		this.oldx = oldx;
		this.oldy = oldy;
		b = model.getBoard();
	}
	
	public void connect(){
		if(connectWord == null){
			return;
		}
		Word selectedWord = this.model.getSelected();
		ConnectionMove move = new ConnectionMove(selectedWord,connectWord,b,this.oldx,this.oldy);
		if(move.execute()){
			panel.repaint();
		}
		
		model.getMoves().push(move);
	}
	
	public void connectPoem(int connectionType){
		if(connectPoem == null){
			return;
		}
		Word selectedWord = this.model.getSelected();
		ConnectionPoemMove move = new ConnectionPoemMove(connectPoem,selectedWord,b,oldx,oldy,connectionType);
		if(move.execute()){
			panel.repaint();
		}
		
		model.getMoves().push(move);
	}
}
