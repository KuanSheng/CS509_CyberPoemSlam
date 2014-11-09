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
	Word connectWord;
	Poem connectPoem;
	
	public WordConnectionController(Model m,ApplicationCanvas p,Word w,int oldx, int oldy){
		this.model = m;
		this.panel = p;
		this.connectWord = w;
		this.oldx = oldx;
		this.oldy = oldy;
		b = model.getBoard();
	}
	
	public WordConnectionController(Model m, ApplicationCanvas a, Poem p){
		this.model = m;
		this.panel = a;
		this.connectPoem = p;
		b = model.getBoard();
	}
	
	public void connect(){
		Word selectedWord = this.model.getSelected();
		ConnectionMove move = new ConnectionMove(selectedWord,connectWord,b,this.oldx,this.oldy);
		if(move.execute()){
			panel.repaint();
		}
		
		model.getMoves().push(move);
	}
	
	public void connectPoem(int connectionType){
		Word selectedWord = model.getSelected();
		if(connectionType == 1||connectionType == 4||connectionType == 5){
		Row r = connectPoem.getOverlapRow(selectedWord);
		r.addWord(selectedWord);
		selectedWord.setLocation(r.getX()-selectedWord.getWidth(), r.getY());
		r.setLocationAfterConnection(selectedWord.getX(),selectedWord.getY());
		connectPoem.setLocationAfterConnection(r.getX(),r.getY());
		b.getWords().remove(selectedWord);
		panel.repaint();
		}
		
		else if(connectionType == 2||connectionType == 3||connectionType == 6){
			Row r = connectPoem.getOverlapRow(selectedWord);
			
			r.addWord(selectedWord);
			selectedWord.setLocation(r.getX()+r.getWidth()-selectedWord.getWidth(),r.getY());
			b.getWords().remove(selectedWord);
			panel.repaint();
		}
	}
}
