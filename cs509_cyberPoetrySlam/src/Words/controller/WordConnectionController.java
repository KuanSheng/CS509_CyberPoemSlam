package Words.controller;

import java.awt.event.MouseAdapter;
import Words.model.*;

import Words.model.Model;
import Words.view.ApplicationCanvas;

public class WordConnectionController extends MouseAdapter {
	final Model model;
	final ApplicationCanvas panel;
	final Board b;
	Word connectWord;
	
	public WordConnectionController(Model m,ApplicationCanvas p,Word w){
		this.model = m;
		this.panel = p;
		this.connectWord = w;
		b = model.getBoard();
	}
	
	public void MouseReleased(){
		Word selectedWord = this.model.getSelected();
		Poem newPoem = new Poem(selectedWord,this.connectWord);	
		b.addPoems(newPoem);
		b.getWords().remove(selectedWord);
		b.getWords().remove(connectWord);
	}
	
}
