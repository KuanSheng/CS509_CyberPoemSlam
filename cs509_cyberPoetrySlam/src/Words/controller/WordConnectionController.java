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
		int type = b.getOverlapType(selectedWord, this.connectWord);
		System.out.println(type);
		Poem newPoem;
		
		switch(type){
		case 3:
			newPoem = new Poem(this.connectWord,selectedWord,1);
		    break;
		
		case 4:
			newPoem = new Poem(this.connectWord,selectedWord,1);
			break;
		case 2:
			newPoem = new Poem(selectedWord,this.connectWord,2);
			break;
		case 1:
			newPoem = new Poem(selectedWord,this.connectWord,2);
			break;
		case 5:
			newPoem = new Poem(this.connectWord,selectedWord,1);
			break;
		case 6:
			newPoem = new Poem(selectedWord,this.connectWord,2);
			break;
		default:
			newPoem = new Poem(this.connectWord,selectedWord,1);
			break;
		}
		
		b.addPoems(newPoem);
		b.getWords().remove(selectedWord);
		b.getWords().remove(this.connectWord);
		panel.repaint();
		}
	
}
