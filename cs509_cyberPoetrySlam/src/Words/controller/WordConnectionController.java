package Words.controller;

import Words.model.*;

import Words.model.Model;
import Words.view.ApplicationCanvas;

public class WordConnectionController{
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
	
	public void connect(){
		Word selectedWord = this.model.getSelected();
		Poem newPoem = new Poem(this.connectWord,selectedWord);	
		b.addPoems(newPoem);
		b.getWords().remove(selectedWord);
		b.getWords().remove(connectWord);
		panel.repaint();
		}
	
}
